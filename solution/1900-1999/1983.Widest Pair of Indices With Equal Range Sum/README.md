# [1983. 范围和相等的最宽索引对](https://leetcode.cn/problems/widest-pair-of-indices-with-equal-range-sum)

[English Version](/solution/1900-1999/1983.Widest%20Pair%20of%20Indices%20With%20Equal%20Range%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个 <strong>以0为索引</strong> 的二进制数组 <code>nums1</code> 和 <code>nums2</code> 。找出 <strong>最宽</strong> 的索引对 <code>(i, j)</code> ，使的&nbsp;<code>i &lt;= j</code>&nbsp;并且&nbsp;<code>nums1[i] + nums1[i+1] + ... + nums1[j] == nums2[i] + nums2[i+1] + ... + nums2[j]</code>。</p>

<p><strong>最宽</strong> 的指标对是指在 <code>i </code>和<code> j </code>之间的 <strong>距离最大</strong> 的指标对。一对指标之间的 <strong>距离</strong> 定义为<code> j - i + 1</code> 。</p>

<p>返回 <strong>最宽</strong> 索引对的 <strong>距离</strong> 。如果没有满足条件的索引对，则返回 <code>0</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums1 = [1,1,0,1], nums2 = [0,1,1,0]
<strong>输出:</strong> 3
<strong>解释:</strong>
如果i = 1, j = 3:
Nums1 [1] + Nums1 [2] + Nums1[3] = 1 + 0 + 1 = 2。
Nums2 [1] + Nums2 [2] + Nums2[3] = 1 + 1 + 0 = 2。
i和j之间的距离是j - i + 1 = 3 - 1 + 1 = 3。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums1 = [0,1], nums2 = [1,1]
<strong>输出:</strong> 1
<strong>解释:
</strong>If i = 1 and j = 1:
nums1[1] = 1。
nums2[1] = 1。
i和j之间的距离是j - i + 1 = 1 - 1 + 1 = 1。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> nums1 = [0], nums2 = [1]
<strong>输出:</strong> 0
<strong>解释:
</strong>没有满足要求的索引对。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>nums1[i]</code>&nbsp;仅为&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>.</li>
	<li><code>nums2[i]</code>&nbsp;仅为&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
