# [2040. 两个有序数组的第 K 小乘积](https://leetcode.cn/problems/kth-smallest-product-of-two-sorted-arrays)

[English Version](/solution/2000-2099/2040.Kth%20Smallest%20Product%20of%20Two%20Sorted%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你两个 <strong>从小到大排好序</strong>&nbsp;且下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;以及一个整数&nbsp;<code>k</code>&nbsp;，请你返回第<em>&nbsp;</em><code>k</code>&nbsp;（从 <strong>1</strong>&nbsp;开始编号）小的&nbsp;<code>nums1[i] \* nums2[j]</code><em>&nbsp;</em>的乘积，其中<em>&nbsp;</em><code>0 &lt;= i &lt; nums1.length</code><em> </em>且<em> </em><code>0 &lt;= j &lt; nums2.length</code>&nbsp;。

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums1 = [2,5], nums2 = [3,4], k = 2
<b>输出：</b>8
<b>解释：</b>第 2 小的乘积计算如下：
- nums1[0] * nums2[0] = 2 * 3 = 6
- nums1[0] * nums2[1] = 2 * 4 = 8
第 2 小的乘积为 8 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums1 = [-4,-2,0,3], nums2 = [2,4], k = 6
<b>输出：</b>0
<strong>解释：</strong>第 6 小的乘积计算如下：
- nums1[0] * nums2[1] = (-4) * 4 = -16
- nums1[0] * nums2[0] = (-4) * 2 = -8
- nums1[1] * nums2[1] = (-2) * 4 = -8
- nums1[1] * nums2[0] = (-2) * 2 = -4
- nums1[2] * nums2[0] = 0 * 2 = 0
- nums1[2] * nums2[1] = 0 * 4 = 0
第 6 小的乘积为 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums1 = [-2,-1,0,1,2], nums2 = [-3,-1,2,4,5], k = 3
<b>输出：</b>-6
<b>解释：</b>第 3 小的乘积计算如下：
- nums1[0] * nums2[4] = (-2) * 5 = -10
- nums1[0] * nums2[3] = (-2) * 4 = -8
- nums1[4] * nums2[0] = 2 * (-3) = -6
第 3 小的乘积为 -6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums1[i], nums2[j] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums1.length * nums2.length</code></li>
	<li><code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;都是从小到大排好序的。</li>
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
