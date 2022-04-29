# [487. 最大连续 1 的个数 II](https://leetcode.cn/problems/max-consecutive-ones-ii)

[English Version](/solution/0400-0499/0487.Max%20Consecutive%20Ones%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二进制数组 <code>nums</code> ，如果最多可以翻转一个 <code>0</code> ，则返回数组中连续 <code>1</code> 的最大个数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,0,1,1,0]
<strong>输出：</strong>4
<strong>解释：</strong>翻转第一个 0 可以得到最长的连续 1。
&nbsp;    当翻转以后，最大连续 1 的个数为 4。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>nums = [1,0,1,1,0,1]
<b>输出：</b>4
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code>&nbsp;不是&nbsp;<code>0</code>&nbsp;就是&nbsp;<code>1</code>.</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果输入的数字是作为<strong> 无限流 </strong>逐个输入如何处理？换句话说，内存不能存储下所有从流中输入的数字。您可以有效地解决吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

用 `prefix[i]` 数组表示以 i 结尾往前累计的最大连续 1 的个数，`suffix[i]` 数组表示以 i 开头往后累计的最大连续 1 的个数。

遍历 `nums` 数组每个为 0 的位置，则位置 i 的最大连续 1 的个数为 `1 + prefix[i-1] + suffix[i+1]`。

当然，如果 `nums` 数组没有 0，即所有元素都是 1，那么结果即为 `nums` 数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        n = len(nums)
        prefix = [0] * n
        suffix = [0] * n
        res = 0
        for i in range(n):
            if i == 0:
                prefix[i] = nums[i]
            else:
                prefix[i] = 0 if nums[i] == 0 else prefix[i - 1] + 1
            res = max(res, prefix[i])

        for i in range(n - 1, -1, -1):
            if i == n - 1:
                suffix[i] = nums[i]
            else:
                suffix[i] = 0 if nums[i] == 0 else suffix[i + 1] + 1

        for i in range(n):
            if nums[i] == 0:
                t = 1
                if i > 0:
                    t += prefix[i - 1]
                if i < n - 1:
                    t += suffix[i + 1]
                res = max(res, t)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

-   双指针，时间复杂度 O(n²)，空间复杂度 O(1)

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int cnt = 1;
            int j = i;
            while (j < n && (cnt > 0 || nums[j] == 1)) {
                if (nums[j] == 0) --cnt;
                ++j;
            }
            res = Math.max(res, j - i);
        }
        return res;
    }
}
```

-   辅助数组，时间复杂度 O(n)，空间复杂度 O(n)

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;

        int[] prefix = new int[n];
        int[] suffix = new int[n];

        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (i == 0) prefix[0] = nums[0];
            else prefix[i] = nums[i] == 0 ? 0 : prefix[i - 1] + 1;
            res = Math.max(res, prefix[i]);
        }

        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1) suffix[n - 1] = nums[n - 1];
            else suffix[i] = nums[i] == 0 ? 0 : suffix[i + 1] + 1;
        }

        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                int t = 1;
                if (i > 0) t += prefix[i - 1];
                if (i < n - 1) t += suffix[i + 1];
                res = Math.max(res, t);
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
