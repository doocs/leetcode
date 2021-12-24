# [1738. 找出第 K 大的异或坐标值](https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value)

[English Version](/solution/1700-1799/1738.Find%20Kth%20Largest%20XOR%20Coordinate%20Value/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维矩阵 <code>matrix</code> 和一个整数 <code>k</code> ，矩阵大小为 <code>m x n</code> 由非负整数组成。</p>

<p>矩阵中坐标 <code>(a, b)</code> 的 <strong>值</strong> 可由对所有满足 <code>0 &lt;= i &lt;= a &lt; m</code> 且 <code>0 &lt;= j &lt;= b &lt; n</code> 的元素 <code>matrix[i][j]</code>（<strong>下标从 0 开始计数</strong>）执行异或运算得到。</p>

<p>请你找出 <code>matrix</code> 的所有坐标中第 <code>k</code> 大的值（<strong><code>k</code> 的值从 1 开始计数</strong>）。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>matrix = [[5,2],[1,6]], k = 1
<strong>输出：</strong>7
<strong>解释：</strong>坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>matrix = [[5,2],[1,6]], k = 2
<strong>输出：</strong>5
<strong>解释：</strong>坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>matrix = [[5,2],[1,6]], k = 3
<strong>输出：</strong>4
<strong>解释：</strong>坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>matrix = [[5,2],[1,6]], k = 4
<strong>输出：</strong>0
<strong>解释：</strong>坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>0 &lt;= matrix[i][j] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= m * n</code></li>
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
