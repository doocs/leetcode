---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2656.Maximum%20Sum%20With%20Exactly%20K%20Elements/README.md
rating: 1213
source: 第 103 场双周赛 Q1
tags:
    - 贪心
    - 数组
---

# [2656. K 个元素的最大和](https://leetcode.cn/problems/maximum-sum-with-exactly-k-elements)

[English Version](/solution/2600-2699/2656.Maximum%20Sum%20With%20Exactly%20K%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code> 和一个整数&nbsp;<code>k</code>&nbsp;。你需要执行以下操作<strong>&nbsp;恰好</strong> <code>k</code>&nbsp;次，最大化你的得分：</p>

<ol>
	<li>从 <code>nums</code>&nbsp;中选择一个元素&nbsp;<code>m</code>&nbsp;。</li>
	<li>将选中的元素&nbsp;<code>m</code>&nbsp;从数组中删除。</li>
	<li>将新元素&nbsp;<code>m + 1</code>&nbsp;添加到数组中。</li>
	<li>你的得分增加&nbsp;<code>m</code>&nbsp;。</li>
</ol>

<p>请你返回执行以上操作恰好 <code>k</code>&nbsp;次后的最大得分。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,4,5], k = 3
<b>输出：</b>18
<b>解释：</b>我们需要从 nums 中恰好选择 3 个元素并最大化得分。
第一次选择 5 。和为 5 ，nums = [1,2,3,4,6] 。
第二次选择 6 。和为 6 ，nums = [1,2,3,4,7] 。
第三次选择 7 。和为 5 + 6 + 7 = 18 ，nums = [1,2,3,4,8] 。
所以我们返回 18 。
18 是可以得到的最大答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [5,5,5], k = 2
<b>输出：</b>11
<b>解释：</b>我们需要从 nums 中恰好选择 2 个元素并最大化得分。
第一次选择 5 。和为 5 ，nums = [5,5,6] 。
第二次选择 6 。和为 6 ，nums = [5,5,7] 。
所以我们返回 11 。
11 是可以得到的最大答案。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
</ul>

## 解法

### 方法一：贪心 + 数学

我们注意到，要使得最终的得分最大，我们应该尽可能地使得每次选择的元素最大。因此，我们第一次选择数组中的最大元素 $x$，第二次选择 $x+1$，第三次选择 $x+2$，以此类推，直到第 $k$ 次选择 $x+k-1$。这样的选择方式可以保证每次选择的元素都是当前数组中的最大值，因此最终的得分也是最大的。答案即为 $k$ 个 $x$ 的和加上 $0+1+2+\cdots+(k-1)$，即 $k \times x + (k - 1) \times k / 2$。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def maximizeSum(self, nums: List[int], k: int) -> int:
        x = max(nums)
        return k * x + k * (k - 1) // 2
```

```java
class Solution {
    public int maximizeSum(int[] nums, int k) {
        int x = 0;
        for (int v : nums) {
            x = Math.max(x, v);
        }
        return k * x + k * (k - 1) / 2;
    }
}
```

```cpp
class Solution {
public:
    int maximizeSum(vector<int>& nums, int k) {
        int x = *max_element(nums.begin(), nums.end());
        return k * x + k * (k - 1) / 2;
    }
};
```

```go
func maximizeSum(nums []int, k int) int {
	x := slices.Max(nums)
	return k*x + k*(k-1)/2
}
```

```ts
function maximizeSum(nums: number[], k: number): number {
    const x = Math.max(...nums);
    return k * x + (k * (k - 1)) / 2;
}
```

```rust
impl Solution {
    pub fn maximize_sum(nums: Vec<i32>, k: i32) -> i32 {
        let mut mx = 0;

        for &n in &nums {
            if n > mx {
                mx = n;
            }
        }

        ((0 + k - 1) * k) / 2 + k * mx
    }
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```rust
impl Solution {
    pub fn maximize_sum(nums: Vec<i32>, k: i32) -> i32 {
        let mx = *nums.iter().max().unwrap_or(&0);

        ((0 + k - 1) * k) / 2 + k * mx
    }
}
```

<!-- tabs:end -->

<!-- end -->
