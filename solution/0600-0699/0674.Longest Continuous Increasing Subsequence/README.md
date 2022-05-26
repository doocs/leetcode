# [674. 最长连续递增序列](https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence)

[English Version](/solution/0600-0699/0674.Longest%20Continuous%20Increasing%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个未经排序的整数数组，找到最长且<strong>连续</strong>的的递增序列。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> [1,3,5,4,7]
<strong>输出:</strong> 3
<strong>解释:</strong> 最长连续递增序列是 [1,3,5], 长度为3。
尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。 
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> [2,2,2,2,2]
<strong>输出:</strong> 1
<strong>解释:</strong> 最长连续递增序列是 [2], 长度为1。
</pre>

<p><strong>注意：</strong>数组长度不会超过10000。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

设 f(i) 表示将数组第 i 项作为最长连续递增子序列的最后一项时，子序列的长度。

那么，当 `nums[i - 1] < nums[i]`，即 `f(i) = f(i - 1)` + 1，否则 `f(i) = 1`。问题转换为求 f(i) (`i ∈ [0 ,n - 1]`) 的最大值。

由于 f(i) 只与前一项 f(i - 1) 有关联，故不需要用一个数组存储。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findLengthOfLCIS(self, nums: List[int]) -> int:
        n = len(nums)
        if n < 2:
            return n
        res = f = 1
        for i in range(1, n):
            f = 1 + (f if nums[i - 1] < nums[i] else 0)
            res = max(res, f)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int n;
        if ((n = nums.length) < 2) return n;
        int res = 1, f = 1;
        for (int i = 1; i < n; ++i) {
            f = 1 + (nums[i - 1] < nums[i] ? f : 0);
            res = Math.max(res, f);
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
