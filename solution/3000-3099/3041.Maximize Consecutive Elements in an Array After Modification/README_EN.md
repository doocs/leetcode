---
comments: true
difficulty: Hard
rating: 2231
source: Biweekly Contest 124 Q4
tags:
    - Array
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [3041. Maximize Consecutive Elements in an Array After Modification](https://leetcode.com/problems/maximize-consecutive-elements-in-an-array-after-modification)

[中文文档](/solution/3000-3099/3041.Maximize%20Consecutive%20Elements%20in%20an%20Array%20After%20Modification/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> consisting of <strong>positive</strong> integers.</p>

<p>Initially, you can increase the value of <strong>any</strong> element in the array by <strong>at most</strong> <code>1</code>.</p>

<p>After that, you need to select <strong>one or more</strong> elements from the final array such that those elements are <strong>consecutive</strong> when sorted in increasing order. For example, the elements <code>[3, 4, 5]</code> are consecutive while <code>[3, 4, 6]</code> and <code>[1, 1, 2, 3]</code> are not.<!-- notionvc: 312f8c5d-40d0-4cd1-96cc-9e96a846735b --></p>

<p>Return <em>the <strong>maximum</strong> number of elements that you can select</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,5,1,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can increase the elements at indices 0 and 3. The resulting array is nums = [3,1,5,2,1].
We select the elements [<u><strong>3</strong></u>,<u><strong>1</strong></u>,5,<u><strong>2</strong></u>,1] and we sort them to obtain [1,2,3], which are consecutive.
It can be shown that we cannot select more than 3 consecutive elements.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,7,10]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The maximum consecutive elements that we can select is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> Each element may increase by at most $1$, and we then select the longest run of consecutive integers. With $n \le 10^5$, matching a candidate run from every possible start is too slow.
>
> After sorting, every unseen value is at least as large as the current one. Once a run breaks, endings from earlier values are useless, so the state only needs to sit next to the previous raw value.
>
> A number either stays unchanged or becomes one larger, which means two lengths are enough relative to the previous raw value: one ending at that value, and one ending at that value plus one.
>
> The gap from the previous raw value decides which length the new element can extend. An equal value can only be appended after it is increased; a gap of $1$ extends both lengths; a gap of $2$ extends only the increased ending; a larger gap starts a new run. Sorting first and then scanning once follows that order.

<!-- thinking:end -->

We sort $\textit{nums}$ in non-decreasing order and scan from left to right. Let $\textit{prev}$ be the previous raw value. $f_0$ is the longest consecutive length ending at $\textit{prev}$, and $f_1$ is the longest consecutive length ending at $\textit{prev}+1$. Each element may stay unchanged or increase by $1$, and a consecutive run contains each value at most once, so endings older than $\textit{prev}$ do not need to be kept.

Let the current element be $x$. The transition depends on the gap between $x$ and $\textit{prev}$. Afterwards $\textit{prev}$ becomes $x$, and the two lengths are read relative to this new value:

- If $x = \textit{prev}$, the current element cannot be used as $\textit{prev}$ again. Increase it by $1$ and append it to the run ending at $\textit{prev}$, which gives $f_1 = f_0 + 1$.
- If $x = \textit{prev}+1$, keeping the raw value extends the run ending at $\textit{prev}$, and increasing it extends the run ending at $\textit{prev}+1$, so both $f_0$ and $f_1$ grow by $1$.
- If $x = \textit{prev}+2$, only the run ending at $\textit{prev}+1$ can take the raw value, so the new $f_0 = f_1 + 1$. Increasing the current element starts a run by itself, so $f_1 = 1$.
- Otherwise neither run can be extended, and both $f_0$ and $f_1$ reset to $1$.

The answer is the maximum of $f_0$ and $f_1$ over the scan.

The time complexity is $O(n \log n)$ and the space complexity is $O(\log n)$, where $n$ is the length of $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSelectedElements(self, nums: list[int]) -> int:
        nums.sort()
        ans = 1
        prev = -inf
        f0 = f1 = 1
        for x in nums:
            if x == prev:
                f1 = f0 + 1
            elif x == prev + 1:
                f0 += 1
                f1 += 1
            elif x == prev + 2:
                f0 = f1 + 1
                f1 = 1
            else:
                f0 = f1 = 1
            ans = max(ans, f0, f1)
            prev = x
        return ans
```

#### Java

```java
class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        int ans = 1, prev = Integer.MIN_VALUE;
        int f0 = 1, f1 = 1;
        for (int x : nums) {
            if (x == prev) {
                f1 = f0 + 1;
            } else if (x == prev + 1) {
                ++f0;
                ++f1;
            } else if (x == prev + 2) {
                f0 = f1 + 1;
                f1 = 1;
            } else {
                f0 = 1;
                f1 = 1;
            }
            ans = Math.max(ans, Math.max(f0, f1));
            prev = x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxSelectedElements(vector<int>& nums) {
        ranges::sort(nums);
        int ans = 1, prev = INT_MIN;
        int f0 = 1, f1 = 1;
        for (int x : nums) {
            if (x == prev) {
                f1 = f0 + 1;
            } else if (x == prev + 1) {
                ++f0;
                ++f1;
            } else if (x == prev + 2) {
                f0 = f1 + 1;
                f1 = 1;
            } else {
                f0 = 1;
                f1 = 1;
            }
            ans = max({ans, f0, f1});
            prev = x;
        }
        return ans;
    }
};
```

#### Go

```go
func maxSelectedElements(nums []int) int {
	sort.Ints(nums)
	ans, prev := 1, math.MinInt32
	f0, f1 := 1, 1
	for _, x := range nums {
		if x == prev {
			f1 = f0 + 1
		} else if x == prev+1 {
			f0++
			f1++
		} else if x == prev+2 {
			f0 = f1 + 1
			f1 = 1
		} else {
			f0, f1 = 1, 1
		}
		ans = max(ans, f0, f1)
		prev = x
	}
	return ans
}
```

#### TypeScript

```ts
function maxSelectedElements(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let ans = 1;
    let prev = -Infinity;
    let f0 = 1;
    let f1 = 1;
    for (const x of nums) {
        if (x === prev) {
            f1 = f0 + 1;
        } else if (x === prev + 1) {
            ++f0;
            ++f1;
        } else if (x === prev + 2) {
            f0 = f1 + 1;
            f1 = 1;
        } else {
            f0 = 1;
            f1 = 1;
        }
        ans = Math.max(ans, f0, f1);
        prev = x;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_selected_elements(mut nums: Vec<i32>) -> i32 {
        nums.sort_unstable();
        let mut ans = 1;
        let mut prev = i32::MIN;
        let mut f0 = 1;
        let mut f1 = 1;
        for &x in &nums {
            if x == prev {
                f1 = f0 + 1;
            } else if x == prev + 1 {
                f0 += 1;
                f1 += 1;
            } else if x == prev + 2 {
                f0 = f1 + 1;
                f1 = 1;
            } else {
                f0 = 1;
                f1 = 1;
            }
            ans = ans.max(f0).max(f1);
            prev = x;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
