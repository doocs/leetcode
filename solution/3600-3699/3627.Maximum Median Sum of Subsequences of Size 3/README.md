---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3627.Maximum%20Median%20Sum%20of%20Subsequences%20of%20Size%203/README.md
rating: 1262
source: 第 460 场周赛 Q1
---

<!-- problem:start -->

# [3627. 中位数之和的最大值](https://leetcode.cn/problems/maximum-median-sum-of-subsequences-of-size-3)

[English Version](/solution/3600-3699/3627.Maximum%20Median%20Sum%20of%20Subsequences%20of%20Size%203/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>，其长度可以被 3 整除。</p>

<p>你需要通过多次操作将数组清空。在每一步操作中，你可以从数组中选择任意三个元素，计算它们的&nbsp;<strong>中位数&nbsp;</strong>，并将这三个元素从数组中移除。</p>

<p>奇数长度数组的&nbsp;<strong>中位数&nbsp;</strong>定义为数组按非递减顺序排序后位于中间的元素。</p>

<p>返回通过所有操作得到的&nbsp;<strong>中位数之和的最大值&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,1,3,2,1,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第一步，选择下标为 2、4 和 5 的元素，它们的中位数是 3。移除这些元素后，<code>nums</code> 变为 <code>[2, 1, 2]</code>。</li>
	<li>第二步，选择下标为 0、1 和 2 的元素，它们的中位数是 2。移除这些元素后，<code>nums</code> 变为空数组。</li>
</ul>

<p>因此，中位数之和为 <code>3 + 2 = 5</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,1,10,10,10,10]</span></p>

<p><strong>输出：</strong> <span class="example-io">20</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>第一步，选择下标为 0、2 和 3 的元素，它们的中位数是 10。移除这些元素后，<code>nums</code> 变为 <code>[1, 10, 10]</code>。</li>
	<li>第二步，选择下标为 0、1 和 2 的元素，它们的中位数是 10。移除这些元素后，<code>nums</code> 变为空数组。</li>
</ul>

<p>因此，中位数之和为 <code>10 + 10 = 20</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>nums.length % 3 == 0</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 排序

为了使得中位数之和最大，我们需要尽可能选择较大的元素作为中位数。由于每次操作只能选择三个元素，因此我们可以将数组排序后，从下标 $n / 3$ 元素开始，每两个元素选择一个小的，直到数组末尾。这样可以确保我们选择的中位数是最大的。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumMedianSum(self, nums: List[int]) -> int:
        nums.sort()
        return sum(nums[len(nums) // 3 :: 2])
```

#### Java

```java
class Solution {
    public long maximumMedianSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long ans = 0;
        for (int i = n / 3; i < n; i += 2) {
            ans += nums[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumMedianSum(vector<int>& nums) {
        ranges::sort(nums);
        int n = nums.size();
        long long ans = 0;
        for (int i = n / 3; i < n; i += 2) {
            ans += nums[i];
        }
        return ans;
    }
};
```

#### Go

```go
func maximumMedianSum(nums []int) (ans int64) {
	sort.Ints(nums)
	n := len(nums)
	for i := n / 3; i < n; i += 2 {
		ans += int64(nums[i])
	}
	return
}
```

#### TypeScript

```ts
function maximumMedianSum(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = 0;
    for (let i = n / 3; i < n; i += 2) {
        ans += nums[i];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
