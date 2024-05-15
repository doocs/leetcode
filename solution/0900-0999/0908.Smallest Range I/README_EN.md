---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0908.Smallest%20Range%20I/README_EN.md
tags:
    - Array
    - Math
---

# [908. Smallest Range I](https://leetcode.com/problems/smallest-range-i)

[中文文档](/solution/0900-0999/0908.Smallest%20Range%20I/README.md)

## Description

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>In one operation, you can choose any index <code>i</code> where <code>0 &lt;= i &lt; nums.length</code> and change <code>nums[i]</code> to <code>nums[i] + x</code> where <code>x</code> is an integer from the range <code>[-k, k]</code>. You can apply this operation <strong>at most once</strong> for each index <code>i</code>.</p>

<p>The <strong>score</strong> of <code>nums</code> is the difference between the maximum and minimum elements in <code>nums</code>.</p>

<p>Return <em>the minimum <strong>score</strong> of </em><code>nums</code><em> after applying the mentioned operation at most once for each index in it</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1], k = 0
<strong>Output:</strong> 0
<strong>Explanation:</strong> The score is max(nums) - min(nums) = 1 - 1 = 0.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,10], k = 2
<strong>Output:</strong> 6
<strong>Explanation:</strong> Change nums to be [2, 8]. The score is max(nums) - min(nums) = 8 - 2 = 6.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,6], k = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong> Change nums to be [4, 4, 4]. The score is max(nums) - min(nums) = 4 - 4 = 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

### Solution 1: Mathematics

According to the problem description, we can add $k$ to the maximum value in the array and subtract $k$ from the minimum value. This can reduce the difference between the maximum and minimum values in the array.

Therefore, the final answer is $\max(nums) - \min(nums) - 2 \times k$.

The time complexity is $O(n)$, where $n$ is the length of the array `nums`. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def smallestRangeI(self, nums: List[int], k: int) -> int:
        mx, mi = max(nums), min(nums)
        return max(0, mx - mi - k * 2)
```

```java
class Solution {
    public int smallestRangeI(int[] nums, int k) {
        int mx = 0;
        int mi = 10000;
        for (int v : nums) {
            mx = Math.max(mx, v);
            mi = Math.min(mi, v);
        }
        return Math.max(0, mx - mi - k * 2);
    }
}
```

```cpp
class Solution {
public:
    int smallestRangeI(vector<int>& nums, int k) {
        auto [mi, mx] = minmax_element(nums.begin(), nums.end());
        return max(0, *mx - *mi - k * 2);
    }
};
```

```go
func smallestRangeI(nums []int, k int) int {
	mi, mx := slices.Min(nums), slices.Max(nums)
	return max(0, mx-mi-k*2)
}
```

```ts
function smallestRangeI(nums: number[], k: number): number {
    const mx = Math.max(...nums);
    const mi = Math.min(...nums);
    return Math.max(mx - mi - k * 2, 0);
}
```

```rust
impl Solution {
    pub fn smallest_range_i(nums: Vec<i32>, k: i32) -> i32 {
        let max = nums.iter().max().unwrap();
        let min = nums.iter().min().unwrap();
        (0).max(max - min - k * 2)
    }
}
```

<!-- tabs:end -->

<!-- end -->
