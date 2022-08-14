# [982. 按位与为零的三元组](https://leetcode.cn/problems/triples-with-bitwise-and-equal-to-zero)

[English Version](/solution/0900-0999/0982.Triples%20with%20Bitwise%20AND%20Equal%20To%20Zero/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，返回其中 <strong>按位与三元组</strong> 的数目。</p>

<p><strong>按位与三元组</strong> 是由下标 <code>(i, j, k)</code> 组成的三元组，并满足下述全部条件：</p>

<ul>
	<li><code>0 &lt;= i &lt; nums.length</code></li>
	<li><code>0 &lt;= j &lt; nums.length</code></li>
	<li><code>0 &lt;= k &lt; nums.length</code></li>
	<li><code>nums[i] &amp; nums[j] &amp; nums[k] == 0</code> ，其中 <code>&amp;</code> 表示按位与运算符。</li>
</ul>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,1,3]
<strong>输出：</strong>12
<strong>解释：</strong>可以选出如下 i, j, k 三元组：
(i=0, j=0, k=1) : 2 &amp; 2 &amp; 1
(i=0, j=1, k=0) : 2 &amp; 1 &amp; 2
(i=0, j=1, k=1) : 2 &amp; 1 &amp; 1
(i=0, j=1, k=2) : 2 &amp; 1 &amp; 3
(i=0, j=2, k=1) : 2 &amp; 3 &amp; 1
(i=1, j=0, k=0) : 1 &amp; 2 &amp; 2
(i=1, j=0, k=1) : 1 &amp; 2 &amp; 1
(i=1, j=0, k=2) : 1 &amp; 2 &amp; 3
(i=1, j=1, k=0) : 1 &amp; 1 &amp; 2
(i=1, j=2, k=0) : 1 &amp; 3 &amp; 2
(i=2, j=0, k=1) : 3 &amp; 2 &amp; 1
(i=2, j=1, k=0) : 3 &amp; 1 &amp; 2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,0,0]
<strong>输出：</strong>27
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt; 2<sup>16</sup></code></li>
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
