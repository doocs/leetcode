---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3371.Identify%20the%20Largest%20Outlier%20in%20an%20Array/README_EN.md
rating: 1643
source: Weekly Contest 426 Q2
tags:
    - Array
    - Hash Table
    - Counting
    - Enumeration
---

<!-- problem:start -->

# [3371. Identify the Largest Outlier in an Array](https://leetcode.com/problems/identify-the-largest-outlier-in-an-array)

[中文文档](/solution/3300-3399/3371.Identify%20the%20Largest%20Outlier%20in%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>. This array contains <code>n</code> elements, where <strong>exactly</strong> <code>n - 2</code> elements are <strong>special</strong><strong> numbers</strong>. One of the remaining <strong>two</strong> elements is the <em>sum</em> of these <strong>special numbers</strong>, and the other is an <strong>outlier</strong>.</p>

<p>An <strong>outlier</strong> is defined as a number that is <em>neither</em> one of the original special numbers <em>nor</em> the element representing the sum of those numbers.</p>

<p><strong>Note</strong> that special numbers, the sum element, and the outlier must have <strong>distinct</strong> indices, but <em>may </em>share the <strong>same</strong> value.</p>

<p>Return the <strong>largest</strong><strong> </strong>potential<strong> outlier</strong> in <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,5,10]</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>The special numbers could be 2 and 3, thus making their sum 5 and the outlier 10.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-2,-1,-3,-6,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The special numbers could be -2, -1, and -3, thus making their sum -6 and the outlier 4.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1,1,1,5,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The special numbers could be 1, 1, 1, 1, and 1, thus making their sum 5 and the other 5 as the outlier.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li>The input is generated such that at least <strong>one</strong> potential outlier exists in <code>nums</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Enumeration

We use a hash table $\textit{cnt}$ to record the frequency of each element in the array $\textit{nums}$.

Next, we enumerate each element $x$ in the array $\textit{nums}$ as a possible outlier. For each $x$, we calculate the sum $t$ of all elements in the array $\textit{nums}$ except $x$. If $t$ is not even, or half of $t$ is not in $\textit{cnt}$, then $x$ does not meet the condition, and we skip this $x$. Otherwise, if $x$ is not equal to half of $t$, or $x$ appears more than once in $\textit{cnt}$, then $x$ is a possible outlier, and we update the answer.

After enumerating all elements, we return the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getLargestOutlier(self, nums: List[int]) -> int:
        s = sum(nums)
        cnt = Counter(nums)
        ans = -inf
        for x, v in cnt.items():
            t = s - x
            if t % 2 or cnt[t // 2] == 0:
                continue
            if x != t // 2 or v > 1:
                ans = max(ans, x)
        return ans
```

#### Java

```java
class Solution {
    public int getLargestOutlier(int[] nums) {
        int s = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            s += x;
            cnt.merge(x, 1, Integer::sum);
        }
        int ans = Integer.MIN_VALUE;
        for (var e : cnt.entrySet()) {
            int x = e.getKey(), v = e.getValue();
            int t = s - x;
            if (t % 2 != 0 || !cnt.containsKey(t / 2)) {
                continue;
            }
            if (x != t / 2 || v > 1) {
                ans = Math.max(ans, x);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int getLargestOutlier(vector<int>& nums) {
        int s = 0;
        unordered_map<int, int> cnt;
        for (int x : nums) {
            s += x;
            cnt[x]++;
        }
        int ans = INT_MIN;
        for (auto [x, v] : cnt) {
            int t = s - x;
            if (t % 2 || !cnt.contains(t / 2)) {
                continue;
            }
            if (x != t / 2 || v > 1) {
                ans = max(ans, x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func getLargestOutlier(nums []int) int {
	s := 0
	cnt := map[int]int{}
	for _, x := range nums {
		s += x
		cnt[x]++
	}
	ans := math.MinInt32
	for x, v := range cnt {
		t := s - x
		if t%2 != 0 || cnt[t/2] == 0 {
			continue
		}
		if x != t/2 || v > 1 {
			ans = max(ans, x)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function getLargestOutlier(nums: number[]): number {
    let s = 0;
    const cnt: Record<number, number> = {};
    for (const x of nums) {
        s += x;
        cnt[x] = (cnt[x] || 0) + 1;
    }
    let ans = -Infinity;
    for (const [x, v] of Object.entries(cnt)) {
        const t = s - +x;
        if (t % 2 || !cnt.hasOwnProperty((t / 2) | 0)) {
            continue;
        }
        if (+x != ((t / 2) | 0) || v > 1) {
            ans = Math.max(ans, +x);
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn get_largest_outlier(nums: Vec<i32>) -> i32 {
        let mut s = 0;
        let mut cnt = HashMap::new();
        for &x in &nums {
            s += x;
            *cnt.entry(x).or_insert(0) += 1;
        }

        let mut ans = i32::MIN;
        for (&x, &v) in &cnt {
            let t = s - x;
            if t % 2 != 0 {
                continue;
            }
            let y = t / 2;
            if let Some(&count_y) = cnt.get(&y) {
                if x != y || v > 1 {
                    ans = ans.max(x);
                }
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int GetLargestOutlier(int[] nums) {
        int s = 0;
        var cnt = new Dictionary<int, int>();
        foreach (int x in nums) {
            s += x;
            if (!cnt.ContainsKey(x)) cnt[x] = 0;
            cnt[x]++;
        }

        int ans = int.MinValue;
        foreach (var kv in cnt) {
            int x = kv.Key, v = kv.Value;
            int t = s - x;
            if (t % 2 != 0) continue;
            int y = t / 2;
            if (cnt.ContainsKey(y)) {
                if (x != y || v > 1) {
                    ans = Math.Max(ans, x);
                }
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
