# [2569. 更新数组后处理求和查询](https://leetcode.cn/problems/handling-sum-queries-after-update)

[English Version](/solution/2500-2599/2569.Handling%20Sum%20Queries%20After%20Update/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;，和一个二维数组&nbsp;<code>queries</code>&nbsp;表示一些操作。总共有 3 种类型的操作：</p>

<ol>
	<li>操作类型 1 为&nbsp;<code>queries[i]&nbsp;= [1, l, r]</code>&nbsp;。你需要将 <code>nums1</code>&nbsp;从下标&nbsp;<code>l</code>&nbsp;到下标 <code>r</code>&nbsp;的所有 <code>0</code>&nbsp;反转成 <code>1</code>&nbsp;或将 <code>1</code>&nbsp;反转成 <code>0</code>&nbsp;。<code>l</code>&nbsp;和 <code>r</code>&nbsp;下标都从 <strong>0</strong>&nbsp;开始。</li>
	<li>操作类型 2 为&nbsp;<code>queries[i]&nbsp;= [2, p, 0]</code>&nbsp;。对于&nbsp;<code>0 &lt;= i &lt; n</code>&nbsp;中的所有下标，令&nbsp;<code>nums2[i] =&nbsp;nums2[i]&nbsp;+ nums1[i]&nbsp;* p</code>&nbsp;。</li>
	<li>操作类型 3 为&nbsp;<code>queries[i]&nbsp;= [3, 0, 0]</code>&nbsp;。求&nbsp;<code>nums2</code>&nbsp;中所有元素的和。</li>
</ol>

<p>请你返回一个数组，包含所有第三种操作类型的答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums1 = [1,0,1], nums2 = [0,0,0], queries = [[1,1,1],[2,1,0],[3,0,0]]
<b>输出：</b>[3]
<strong>解释：</strong>第一个操作后 nums1 变为 [1,1,1] 。第二个操作后，nums2 变成 [1,1,1] ，所以第三个操作的答案为 3 。所以返回 [3] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums1 = [1], nums2 = [5], queries = [[2,0,0],[3,0,0]]
<b>输出：</b>[5]
<b>解释：</b>第一个操作后，nums2 保持不变为 [5] ，所以第二个操作的答案是 5 。所以返回 [5] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length,nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums1.length = nums2.length</code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length = 3</code></li>
	<li><code>0 &lt;= l &lt;= r &lt;= nums1.length - 1</code></li>
	<li><code>0 &lt;= p &lt;= 10<sup>6</sup></code></li>
	<li><code>0 &lt;= nums1[i] &lt;= 1</code></li>
	<li><code>0 &lt;= nums2[i] &lt;= 10<sup>9</sup></code></li>
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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
