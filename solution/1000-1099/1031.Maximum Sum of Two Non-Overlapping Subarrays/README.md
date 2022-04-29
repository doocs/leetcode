# [1031. 两个非重叠子数组的最大和](https://leetcode.cn/problems/maximum-sum-of-two-non-overlapping-subarrays)

[English Version](/solution/1000-1099/1031.Maximum%20Sum%20of%20Two%20Non-Overlapping%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出非负整数数组 <code>A</code> ，返回两个非重叠（连续）子数组中元素的最大和，子数组的长度分别为 <code>L</code> 和 <code>M</code>。（这里需要澄清的是，长为 L 的子数组可以出现在长为 M 的子数组之前或之后。）</p>

<p>从形式上看，返回最大的 <code>V</code>，而 <code>V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1])</code> 并满足下列条件之一：</p>

<p>&nbsp;</p>

<ul>
	<li><code>0 &lt;= i &lt; i + L - 1 &lt; j &lt; j + M - 1 &lt; A.length</code>, <strong>或</strong></li>
	<li><code>0 &lt;= j &lt; j + M - 1 &lt; i &lt; i + L - 1 &lt; A.length</code>.</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
<strong>输出：</strong>20
<strong>解释：</strong>子数组的一种选择中，[9] 长度为 1，[6,5] 长度为 2。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
<strong>输出：</strong>29
<strong>解释：</strong>子数组的一种选择中，[3,8,1] 长度为 3，[8,9] 长度为 2。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
<strong>输出：</strong>31
<strong>解释：</strong>子数组的一种选择中，[5,6,0,9] 长度为 4，[0,3,8] 长度为 3。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>L &gt;= 1</code></li>
	<li><code>M &gt;= 1</code></li>
	<li><code>L + M &lt;= A.length &lt;= 1000</code></li>
	<li><code>0 &lt;= A[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSumTwoNoOverlap(self, nums: List[int], firstLen: int, secondLen: int) -> int:
        n = len(nums)
        s = [0] * (n + 1)
        for i in range(1, n + 1):
            s[i] = s[i - 1] + nums[i - 1]
        ans1, ans2, fm, sm = 0, 0, 0, 0
        for i in range(n - firstLen - secondLen + 1):
            fm = max(fm, s[i + firstLen] - s[i])
            ans1 = max(fm + s[i + firstLen + secondLen] - s[i + firstLen], ans1)
        for i in range(n - firstLen - secondLen + 1):
            sm = max(sm, s[i + secondLen] - s[i])
            ans2 = max(sm + s[i + firstLen + secondLen] - s[i + secondLen], ans2)
        return max(ans1, ans2)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int ans1 = 0, ans2 = 0, fm = 0, sm = 0;
        for (int i = 0; i < n - firstLen - secondLen + 1; i++) {
            fm = Math.max(s[i + firstLen] - s[i], fm);
            ans1 = Math.max(fm + s[i + firstLen + secondLen] - s[i + firstLen], ans1);
        }
        for (int i = 0; i < n - firstLen - secondLen + 1; i++) {
            sm = Math.max(s[i + secondLen] - s[i], sm);
            ans2 = Math.max(sm + s[i + firstLen + secondLen] - s[i + secondLen], ans2);
        }
        return Math.max(ans1, ans2);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
