---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3113.Find%20the%20Number%20of%20Subarrays%20Where%20Boundary%20Elements%20Are%20Maximum/README_EN.md
rating: 2046
source: Biweekly Contest 128 Q4
tags:
    - Stack
    - Array
    - Binary Search
    - Monotonic Stack
---

# [3113. Find the Number of Subarrays Where Boundary Elements Are Maximum](https://leetcode.com/problems/find-the-number-of-subarrays-where-boundary-elements-are-maximum)

[中文文档](/solution/3100-3199/3113.Find%20the%20Number%20of%20Subarrays%20Where%20Boundary%20Elements%20Are%20Maximum/README.md)

## Description

<p>You are given an array of <strong>positive</strong> integers <code>nums</code>.</p>

<p>Return the number of <span data-keyword="subarray-nonempty">subarrays</span> of <code>nums</code>, where the <strong>first</strong> and the <strong>last</strong> elements of the subarray are <em>equal</em> to the <strong>largest</strong> element in the subarray.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,3,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 6 subarrays which have the first and the last elements equal to the largest element of the subarray:</p>

<ul>
	<li>subarray <code>[<strong><u>1</u></strong>,4,3,3,2]</code>, with its largest element 1. The first element is 1 and the last element is also 1.</li>
	<li>subarray <code>[1,<u><strong>4</strong></u>,3,3,2]</code>, with its largest element 4. The first element is 4 and the last element is also 4.</li>
	<li>subarray <code>[1,4,<u><strong>3</strong></u>,3,2]</code>, with its largest element 3. The first element is 3 and the last element is also 3.</li>
	<li>subarray <code>[1,4,3,<u><strong>3</strong></u>,2]</code>, with its largest element 3. The first element is 3 and the last element is also 3.</li>
	<li>subarray <code>[1,4,3,3,<u><strong>2</strong></u>]</code>, with its largest element 2. The first element is 2 and the last element is also 2.</li>
	<li>subarray <code>[1,4,<u><strong>3,3</strong></u>,2]</code>, with its largest element 3. The first element is 3 and the last element is also 3.</li>
</ul>

<p>Hence, we return 6.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,3,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 6 subarrays which have the first and the last elements equal to the largest element of the subarray:</p>

<ul>
	<li>subarray <code>[<u><strong>3</strong></u>,3,3]</code>, with its largest element 3. The first element is 3 and the last element is also 3.</li>
	<li>subarray <code>[3,<strong><u>3</u></strong>,3]</code>, with its largest element 3. The first element is 3 and the last element is also 3.</li>
	<li>subarray <code>[3,3,<u><strong>3</strong></u>]</code>, with its largest element 3. The first element is 3 and the last element is also 3.</li>
	<li>subarray <code>[<strong><u>3,3</u></strong>,3]</code>, with its largest element 3. The first element is 3 and the last element is also 3.</li>
	<li>subarray <code>[3,<u><strong>3,3</strong></u>]</code>, with its largest element 3. The first element is 3 and the last element is also 3.</li>
	<li>subarray <code>[<u><strong>3,3,3</strong></u>]</code>, with its largest element 3. The first element is 3 and the last element is also 3.</li>
</ul>

<p>Hence, we return 6.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>There is a single subarray of <code>nums</code> which is <code>[<strong><u>1</u></strong>]</code>, with its largest element 1. The first element is 1 and the last element is also 1.</p>

<p>Hence, we return 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Monotonic Stack

We consider each element $x$ in the array $nums$ as the boundary element and the maximum value of the subarray.

Each subarray of length $1$ meets the condition, and for subarrays with length greater than $1$, all elements in the subarray cannot be greater than the boundary element $x$. We can implement this with a monotonic stack.

We maintain a stack that decreases monotonically from the bottom to the top. Each element in the monotonic stack is a pair $[x, cnt]$, representing the element $x$ and the count $cnt$ of subarrays with $x$ as the boundary element and the maximum value.

We traverse the array $nums$ from left to right. For each element $x$, we continuously pop the top element of the stack until the stack is empty or the first element of the top element of the stack is greater than or equal to $x$. If the stack is empty, or the first element of the top element of the stack is greater than $x$, it means that we have encountered the first subarray with $x$ as the boundary element and the maximum value, and the length of this subarray is $1$, so we push $[x, 1]$ into the stack. If the first element of the top element of the stack is equal to $x$, it means that we have encountered a subarray with $x$ as the boundary element and the maximum value, and we add $1$ to the second element of the top element of the stack. Then, we add the second element of the top element of the stack to the answer.

After the traversal, we return the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def numberOfSubarrays(self, nums: List[int]) -> int:
        stk = []
        ans = 0
        for x in nums:
            while stk and stk[-1][0] < x:
                stk.pop()
            if not stk or stk[-1][0] > x:
                stk.append([x, 1])
            else:
                stk[-1][1] += 1
            ans += stk[-1][1]
        return ans
```

```java
class Solution {
    public long numberOfSubarrays(int[] nums) {
        Deque<int[]> stk = new ArrayDeque<>();
        long ans = 0;
        for (int x : nums) {
            while (!stk.isEmpty() && stk.peek()[0] < x) {
                stk.pop();
            }
            if (stk.isEmpty() || stk.peek()[0] > x) {
                stk.push(new int[] {x, 1});
            } else {
                stk.peek()[1]++;
            }
            ans += stk.peek()[1];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long numberOfSubarrays(vector<int>& nums) {
        vector<pair<int, int>> stk;
        long long ans = 0;
        for (int x : nums) {
            while (!stk.empty() && stk.back().first < x) {
                stk.pop_back();
            }
            if (stk.empty() || stk.back().first > x) {
                stk.push_back(make_pair(x, 1));
            } else {
                stk.back().second++;
            }
            ans += stk.back().second;
        }
        return ans;
    }
};
```

```go
func numberOfSubarrays(nums []int) (ans int64) {
	stk := [][2]int{}
	for _, x := range nums {
		for len(stk) > 0 && stk[len(stk)-1][0] < x {
			stk = stk[:len(stk)-1]
		}
		if len(stk) == 0 || stk[len(stk)-1][0] > x {
			stk = append(stk, [2]int{x, 1})
		} else {
			stk[len(stk)-1][1]++
		}
		ans += int64(stk[len(stk)-1][1])
	}
	return
}
```

```ts
function numberOfSubarrays(nums: number[]): number {
    const stk: number[][] = [];
    let ans = 0;
    for (const x of nums) {
        while (stk.length > 0 && stk.at(-1)![0] < x) {
            stk.pop();
        }
        if (stk.length === 0 || stk.at(-1)![0] > x) {
            stk.push([x, 1]);
        } else {
            stk.at(-1)![1]++;
        }
        ans += stk.at(-1)![1];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
