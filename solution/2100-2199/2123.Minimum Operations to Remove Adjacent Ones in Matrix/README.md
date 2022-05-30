# [2123. 使矩阵中的 1 互不相邻的最小操作数](https://leetcode.cn/problems/minimum-operations-to-remove-adjacent-ones-in-matrix)

[English Version](/solution/2100-2199/2123.Minimum%20Operations%20to%20Remove%20Adjacent%20Ones%20in%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>下标从 0 开始&nbsp;</strong>的矩阵 <code>grid</code>。每次操作，你可以把 <code>grid</code>&nbsp;中的 一个&nbsp;<code>1</code> 变成&nbsp;<code>0</code> 。</p>

<p>如果一个矩阵中，没有 <code>1</code> 与其它的 <code>1</code> <strong>四连通</strong>（也就是说所有 <code>1</code> 在上下左右四个方向上不能与其他 <code>1</code> 相邻），那么该矩阵就是 <strong>完全独立</strong> 的。</p>

<p>请返回让&nbsp;<code>grid</code> 成为 <strong>完全独立</strong> 的矩阵的 <em>最小操作数</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2123.Minimum%20Operations%20to%20Remove%20Adjacent%20Ones%20in%20Matrix/images/image-20211223181501-1.png" style="width: 644px; height: 250px;">
<pre><strong>输入:</strong> grid = [[1,1,0],[0,1,1],[1,1,1]]
<strong>输出:</strong> 3
<strong>解释:</strong> 可以进行三次操作（把 grid[0][1], grid[1][2] 和 grid[2][1] 变成 0）。
操作后的矩阵中的所有的 1 与其它 1 均不相邻，因此矩阵是完全独立的。
</pre>

<p><strong>示例 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2123.Minimum%20Operations%20to%20Remove%20Adjacent%20Ones%20in%20Matrix/images/image-20211223181518-2.png" style="height: 250px; width: 255px;">
<pre><strong>输入:</strong> grid = [[0,0,0],[0,0,0],[0,0,0]]
<strong>输出:</strong> 0
<strong>解释:</strong> 矩阵中没有 1，此时矩阵也是完全独立的，因此无需操作，返回 0。
</pre>

<p><strong>示例 3:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2123.Minimum%20Operations%20to%20Remove%20Adjacent%20Ones%20in%20Matrix/images/image-20211223181817-3.png" style="width: 165px; height: 167px;">
<pre><strong>输入:</strong> grid = [[0,1],[1,0]]
<strong>输出:</strong> 0
<strong>解释:</strong> 矩阵中的所有的 1 与其它 1 均不相邻，已经是完全独立的，因此无需操作，返回 0。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>grid[i][j]</code> 是&nbsp;<code>0</code>&nbsp;或者&nbsp;<code>1</code>.</li>
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

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
