# [1284. 转化为全零矩阵的最少反转次数](https://leetcode-cn.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix)

[English Version](/solution/1200-1299/1284.Minimum%20Number%20of%20Flips%20to%20Convert%20Binary%20Matrix%20to%20Zero%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的二进制矩阵&nbsp;<code>mat</code>。</p>

<p>每一步，你可以选择一个单元格并将它反转（反转表示 0 变 1 ，1 变 0 ）。如果存在和它相邻的单元格，那么这些相邻的单元格也会被反转。（注：相邻的两个单元格共享同一条边。）</p>

<p>请你返回将矩阵&nbsp;<code>mat</code> 转化为全零矩阵的<em>最少反转次数</em>，如果无法转化为全零矩阵，请返回&nbsp;<strong>-1</strong>&nbsp;。</p>

<p>二进制矩阵的每一个格子要么是 0 要么是 1 。</p>

<p>全零矩阵是所有格子都为 0 的矩阵。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1284.Minimum%20Number%20of%20Flips%20to%20Convert%20Binary%20Matrix%20to%20Zero%20Matrix/images/matrix.png" style="height: 86px; width: 409px;"></p>

<pre><strong>输入：</strong>mat = [[0,0],[0,1]]
<strong>输出：</strong>3
<strong>解释：</strong>一个可能的解是反转 (1, 0)，然后 (0, 1) ，最后是 (1, 1) 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>mat = [[0]]
<strong>输出：</strong>0
<strong>解释：</strong>给出的矩阵是全零矩阵，所以你不需要改变它。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>mat = [[1,1,1],[1,0,1],[0,0,0]]
<strong>输出：</strong>6
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>mat = [[1,0,0],[1,0,0]]
<strong>输出：</strong>-1
<strong>解释：</strong>该矩阵无法转变成全零矩阵
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m ==&nbsp;mat.length</code></li>
	<li><code>n ==&nbsp;mat[0].length</code></li>
	<li><code>1 &lt;= m&nbsp;&lt;= 3</code></li>
	<li><code>1 &lt;= n&nbsp;&lt;= 3</code></li>
	<li><code>mat[i][j]</code>&nbsp;是 0 或 1 。</li>
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
