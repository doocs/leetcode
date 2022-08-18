# [750. 角矩形的数量](https://leetcode.cn/problems/number-of-corner-rectangles)

[English Version](/solution/0700-0799/0750.Number%20Of%20Corner%20Rectangles/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个只包含 <code>0</code> 和 <code>1</code> 的&nbsp;<code>m x n</code>&nbsp;整数矩阵&nbsp;<code>grid</code>&nbsp;，返回 <em>其中 「<strong>角矩形 」</strong>的数量</em> 。</p>

<p>一个<strong>「角矩形」</strong>是由四个不同的在矩阵上的 <code>1</code> 形成的&nbsp;<strong>轴对齐&nbsp;</strong>的矩形。注意只有角的位置才需要为 <code>1</code>。</p>

<p><strong>注意：</strong>4 个 <code>1</code>&nbsp;的位置需要是不同的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0750.Number%20Of%20Corner%20Rectangles/images/cornerrec1-grid.jpg" /></p>

<pre>
<strong>输入：</strong>grid = [[1,0,0,1,0],[0,0,1,0,1],[0,0,0,1,0],[1,0,1,0,1]]
<strong>输出：</strong>1
<strong>解释：</strong>只有一个角矩形，角的位置为 grid[1][2], grid[1][4], grid[3][2], grid[3][4]。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0750.Number%20Of%20Corner%20Rectangles/images/cornerrec2-grid.jpg" /></p>

<pre>
<strong>输入：</strong>grid = [[1,1,1],[1,1,1],[1,1,1]]
<strong>输出：</strong>9
<strong>解释：</strong>这里有 4 个 2x2 的矩形，4 个 2x3 和 3x2 的矩形和 1 个 3x3&nbsp;的矩形。
</pre>

<p><strong>示例 3：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0750.Number%20Of%20Corner%20Rectangles/images/cornerrec3-grid.jpg" /></p>

<pre>
<strong>输入：</strong>grid = [[1,1,1,1]]
<strong>输出：</strong>0
<strong>解释：</strong>矩形必须有 4 个不同的角。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>grid[i][j]</code>&nbsp;不是&nbsp;<code>0</code>&nbsp;就是&nbsp;<code>1</code></li>
	<li>网格中&nbsp;<code>1</code>&nbsp;的个数在&nbsp;<code>[1, 6000]</code> 范围内</li>
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
