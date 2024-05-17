---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0908.Smallest%20Range%20I/README.md
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [908. 最小差值 I](https://leetcode.cn/problems/smallest-range-i)

[English Version](/solution/0900-0999/0908.Smallest%20Range%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>，和一个整数 <code>k</code> 。</p>

<p>在一个操作中，您可以选择 <code>0 &lt;= i &lt; nums.length</code> 的任何索引 <code>i</code> 。将 <code>nums[i]</code> 改为 <code>nums[i] + x</code> ，其中 <code>x</code> 是一个范围为 <code>[-k, k]</code> 的整数。对于每个索引 <code>i</code> ，最多 <strong>只能 </strong>应用 <strong>一次</strong> 此操作。</p>

<p><code>nums</code>&nbsp;的&nbsp;<strong>分数&nbsp;</strong>是&nbsp;<code>nums</code>&nbsp;中最大和最小元素的差值。&nbsp;</p>

<p><em>在对&nbsp; <code>nums</code> 中的每个索引最多应用一次上述操作后，返回&nbsp;<code>nums</code> 的最低 <strong>分数</strong></em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1], k = 0
<strong>输出：</strong>0
<strong>解释：</strong>分数是 max(nums) - min(nums) = 1 - 1 = 0。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,10], k = 2
<strong>输出：</strong>6
<strong>解释：</strong>将 nums 改为 [2,8]。分数是 max(nums) - min(nums) = 8 - 2 = 6。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,6], k = 3
<strong>输出：</strong>0
<strong>解释：</strong>将 nums 改为 [4,4,4]。分数是 max(nums) - min(nums) = 4 - 4 = 0。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

根据题目描述，我们可以将数组中的最大值加上 $k$，最小值减去 $k$，这样可以使得数组中的最大值和最小值之差变小。

因此，最终的答案就是 $\max(nums) - \min(nums) - 2 \times k$。

时间复杂度 $O(n)$，其中 $n$ 为数组 `nums` 的长度。空间复杂度 $O(1)$。

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

<!-- solution:end -->

<!-- problem:end -->
