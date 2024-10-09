---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2099.Find%20Subsequence%20of%20Length%20K%20With%20the%20Largest%20Sum/README.md
rating: 1447
source: 第 67 场双周赛 Q1
tags:
    - 数组
    - 哈希表
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [2099. 找到和最大的长度为 K 的子序列](https://leetcode.cn/problems/find-subsequence-of-length-k-with-the-largest-sum)

[English Version](/solution/2000-2099/2099.Find%20Subsequence%20of%20Length%20K%20With%20the%20Largest%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。你需要找到&nbsp;<code>nums</code>&nbsp;中长度为 <code>k</code>&nbsp;的 <strong>子序列</strong>&nbsp;，且这个子序列的&nbsp;<strong>和最大&nbsp;</strong>。</p>

<p>请你返回 <strong>任意</strong> 一个长度为&nbsp;<code>k</code>&nbsp;的整数子序列。</p>

<p><strong>子序列</strong>&nbsp;定义为从一个数组里删除一些元素后，不改变剩下元素的顺序得到的数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [2,1,3,3], k = 2
<b>输出：</b>[3,3]
<strong>解释：</strong>
子序列有最大和：3 + 3 = 6 。</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [-1,-2,3,4], k = 3
<b>输出：</b>[-1,3,4]
<b>解释：</b>
子序列有最大和：-1 + 3 + 4 = 6 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [3,4,3,3], k = 2
<b>输出：</b>[3,4]
<strong>解释：</strong>
子序列有最大和：3 + 4 = 7 。
另一个可行的子序列为 [4, 3] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序

我们先创建一个索引数组 $\textit{idx}$，数组中的每个元素是数组 $\textit{nums}$ 的下标。然后我们根据数组 $\textit{nums}$ 的值对索引数组 $\textit{idx}$ 进行排序，排序的规则是 $\textit{nums}[i] < \textit{nums}[j]$，其中 $i$ 和 $j$ 是索引数组 $\textit{idx}$ 中的两个下标。

排序完成后，我们取索引数组 $\textit{idx}$ 的最后 $k$ 个元素，这 $k$ 个元素对应的就是数组 $\textit{nums}$ 中最大的 $k$ 个元素。然后我们对这 $k$ 个下标进行排序，得到的就是最大的 $k$ 个元素在数组 $\textit{nums}$ 中的顺序。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSubsequence(self, nums: List[int], k: int) -> List[int]:
        idx = sorted(range(len(nums)), key=lambda i: nums[i])[-k:]
        return [nums[i] for i in sorted(idx)]
```

#### Java

```java
class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> nums[i] - nums[j]);
        Arrays.sort(idx, n - k, n);
        int[] ans = new int[k];
        for (int i = n - k; i < n; ++i) {
            ans[i - (n - k)] = nums[idx[i]];
        }
        return ans;
    }
}
```

#### Go

```go
func maxSubsequence(nums []int, k int) []int {
	n := len(nums)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return nums[idx[i]] < nums[idx[j]] })
	sort.Ints(idx[n-k:])
	ans := make([]int, k)
	for i := n - k; i < n; i++ {
		ans[i-(n-k)] = nums[idx[i]]
	}
	return ans
}
```

#### TypeScript

```ts
function maxSubsequence(nums: number[], k: number): number[] {
    const n = nums.length;
    const idx: number[] = Array.from({ length: n }, (_, i) => i);
    idx.sort((i, j) => nums[i] - nums[j]);
    return idx
        .slice(n - k)
        .sort((i, j) => i - j)
        .map(i => nums[i]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
