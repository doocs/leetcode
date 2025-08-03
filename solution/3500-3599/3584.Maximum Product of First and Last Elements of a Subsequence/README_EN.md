---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3584.Maximum%20Product%20of%20First%20and%20Last%20Elements%20of%20a%20Subsequence/README_EN.md
rating: 1763
source: Weekly Contest 454 Q3
tags:
    - Array
    - Two Pointers
---

<!-- problem:start -->

# [3584. Maximum Product of First and Last Elements of a Subsequence](https://leetcode.com/problems/maximum-product-of-first-and-last-elements-of-a-subsequence)

[中文文档](/solution/3500-3599/3584.Maximum%20Product%20of%20First%20and%20Last%20Elements%20of%20a%20Subsequence/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>m</code>.</p>

<p>Return the <strong>maximum</strong> product of the first and last elements of any <strong><span data-keyword="subsequence-array">subsequence</span></strong> of <code>nums</code> of size <code>m</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-1,-9,2,3,-2,-3,1], m = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">81</span></p>

<p><strong>Explanation:</strong></p>

<p>The subsequence <code>[-9]</code> has the largest product of the first and last elements: <code>-9 * -9 = 81</code>. Therefore, the answer is 81.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,-5,5,6,-4], m = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">20</span></p>

<p><strong>Explanation:</strong></p>

<p>The subsequence <code>[-5, 6, -4]</code> has the largest product of the first and last elements.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,-1,2,-6,5,2,-5,7], m = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">35</span></p>

<p><strong>Explanation:</strong></p>

<p>The subsequence <code>[5, 7]</code> has the largest product of the first and last elements.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Maintaining Prefix Extremes

We can enumerate the last element of the subsequence, assuming it is $\textit{nums}[i]$. Then the first element of the subsequence can be $\textit{nums}[j]$, where $j \leq i - m + 1$. Therefore, we use two variables $\textit{mi}$ and $\textit{mx}$ to maintain the prefix minimum and maximum values respectively. When traversing to $\textit{nums}[i]$, we update $\textit{mi}$ and $\textit{mx}$, then calculate the products of $\textit{nums}[i]$ with $\textit{mi}$ and $\textit{mx}$, taking the maximum value.

The time complexity is $O(n)$, where $n$ is the length of array $\textit{nums}$. And the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumProduct(self, nums: List[int], m: int) -> int:
        ans = mx = -inf
        mi = inf
        for i in range(m - 1, len(nums)):
            x = nums[i]
            y = nums[i - m + 1]
            mi = min(mi, y)
            mx = max(mx, y)
            ans = max(ans, x * mi, x * mx)
        return ans
```

#### Java

```java
class Solution {
    public long maximumProduct(int[] nums, int m) {
        long ans = Long.MIN_VALUE;
        int mx = Integer.MIN_VALUE;
        int mi = Integer.MAX_VALUE;
        for (int i = m - 1; i < nums.length; ++i) {
            int x = nums[i];
            int y = nums[i - m + 1];
            mi = Math.min(mi, y);
            mx = Math.max(mx, y);
            ans = Math.max(ans, Math.max(1L * x * mi, 1L * x * mx));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumProduct(vector<int>& nums, int m) {
        long long ans = LLONG_MIN;
        int mx = INT_MIN;
        int mi = INT_MAX;
        for (int i = m - 1; i < nums.size(); ++i) {
            int x = nums[i];
            int y = nums[i - m + 1];
            mi = min(mi, y);
            mx = max(mx, y);
            ans = max(ans, max(1LL * x * mi, 1LL * x * mx));
        }
        return ans;
    }
};
```

#### Go

```go
func maximumProduct(nums []int, m int) int64 {
	ans := int64(math.MinInt64)
	mx := math.MinInt32
	mi := math.MaxInt32

	for i := m - 1; i < len(nums); i++ {
		x := nums[i]
		y := nums[i-m+1]
		mi = min(mi, y)
		mx = max(mx, y)
		ans = max(ans, max(int64(x)*int64(mi), int64(x)*int64(mx)))
	}

	return ans
}
```

#### TypeScript

```ts
function maximumProduct(nums: number[], m: number): number {
    let ans = Number.MIN_SAFE_INTEGER;
    let mx = Number.MIN_SAFE_INTEGER;
    let mi = Number.MAX_SAFE_INTEGER;

    for (let i = m - 1; i < nums.length; i++) {
        const x = nums[i];
        const y = nums[i - m + 1];
        mi = Math.min(mi, y);
        mx = Math.max(mx, y);
        ans = Math.max(ans, x * mi, x * mx);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
