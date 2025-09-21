---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3689.Maximum%20Total%20Subarray%20Value%20I/README_EN.md
---

<!-- problem:start -->

# [3689. Maximum Total Subarray Value I](https://leetcode.com/problems/maximum-total-subarray-value-i)

[中文文档](/solution/3600-3699/3689.Maximum%20Total%20Subarray%20Value%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and an integer <code>k</code>.</p>

<p>You need to choose <strong>exactly</strong> <code>k</code> non-empty <span data-keyword="subarray-nonempty">subarrays</span> <code>nums[l..r]</code> of <code>nums</code>. Subarrays may overlap, and the exact same subarray (same <code>l</code> and <code>r</code>) <strong>can</strong> be chosen more than once.</p>

<p>The <strong>value</strong> of a subarray <code>nums[l..r]</code> is defined as: <code>max(nums[l..r]) - min(nums[l..r])</code>.</p>

<p>The <strong>total value</strong> is the sum of the <strong>values</strong> of all chosen subarrays.</p>

<p>Return the <strong>maximum</strong> possible total value you can achieve.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal approach is:</p>

<ul>
	<li>Choose <code>nums[0..1] = [1, 3]</code>. The maximum is 3 and the minimum is 1, giving a value of <code>3 - 1 = 2</code>.</li>
	<li>Choose <code>nums[0..2] = [1, 3, 2]</code>. The maximum is still 3 and the minimum is still 1, so the value is also <code>3 - 1 = 2</code>.</li>
</ul>

<p>Adding these gives <code>2 + 2 = 4</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,2,5,1], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal approach is:</p>

<ul>
	<li>Choose <code>nums[0..3] = [4, 2, 5, 1]</code>. The maximum is 5 and the minimum is 1, giving a value of <code>5 - 1 = 4</code>.</li>
	<li>Choose <code>nums[0..3] = [4, 2, 5, 1]</code>. The maximum is 5 and the minimum is 1, so the value is also <code>4</code>.</li>
	<li>Choose <code>nums[2..3] = [5, 1]</code>. The maximum is 5 and the minimum is 1, so the value is again <code>4</code>.</li>
</ul>

<p>Adding these gives <code>4 + 4 + 4 = 12</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 5 * 10<sup>​​​​​​​4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simple Observation

We can observe that the value of a subarray only depends on the global maximum and minimum values. Therefore, we just need to find the global maximum and minimum, then multiply their difference by $k$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxTotalValue(self, nums: List[int], k: int) -> int:
        return k * (max(nums) - min(nums))
```

#### Java

```java
class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int mx = 0, mn = 1 << 30;
        for (int x : nums) {
            mx = Math.max(mx, x);
            mn = Math.min(mn, x);
        }
        return 1L * k * (mx - mn);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxTotalValue(vector<int>& nums, int k) {
        auto [mn, mx] = minmax_element(nums.begin(), nums.end());
        return 1LL * k * (*mx - *mn);
    }
};
```

#### Go

```go
func maxTotalValue(nums []int, k int) int64 {
	return int64(k * (slices.Max(nums) - slices.Min(nums)))
}
```

#### TypeScript

```ts
function maxTotalValue(nums: number[], k: number): number {
    const mn = Math.min(...nums);
    const mx = Math.max(...nums);
    return k * (mx - mn);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
