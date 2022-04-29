# [1582. 二进制矩阵中的特殊位置](https://leetcode.cn/problems/special-positions-in-a-binary-matrix)

[English Version](/solution/1500-1599/1582.Special%20Positions%20in%20a%20Binary%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>rows x cols</code> 的矩阵 <code>mat</code>，其中 <code>mat[i][j]</code> 是 <code>0</code> 或 <code>1</code>，请返回 <strong>矩阵&nbsp;<em><code>mat</code></em> 中特殊位置的数目</strong> 。</p>

<p><strong>特殊位置</strong> 定义：如果 <code>mat[i][j] == 1</code> 并且第 <code>i</code> 行和第 <code>j</code> 列中的所有其他元素均为 <code>0</code>（行和列的下标均 <strong>从 0 开始</strong> ），则位置 <code>(i, j)</code> 被称为特殊位置。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>mat = [[1,0,0],
&nbsp;           [0,0,<strong>1</strong>],
&nbsp;           [1,0,0]]
<strong>输出：</strong>1
<strong>解释：</strong>(1,2) 是一个特殊位置，因为 mat[1][2] == 1 且所处的行和列上所有其他元素都是 0
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>mat = [[<strong>1</strong>,0,0],
&nbsp;           [0,<strong>1</strong>,0],
&nbsp;           [0,0,<strong>1</strong>]]
<strong>输出：</strong>3
<strong>解释：</strong>(0,0), (1,1) 和 (2,2) 都是特殊位置
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>mat = [[0,0,0,<strong>1</strong>],
&nbsp;           [<strong>1</strong>,0,0,0],
&nbsp;           [0,1,1,0],
&nbsp;           [0,0,0,0]]
<strong>输出：</strong>2
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>mat = [[0,0,0,0,0],
&nbsp;           [<strong>1</strong>,0,0,0,0],
&nbsp;           [0,<strong>1</strong>,0,0,0],
&nbsp;           [0,0,<strong>1</strong>,0,0],
&nbsp;           [0,0,0,1,1]]
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>rows == mat.length</code></li>
	<li><code>cols == mat[i].length</code></li>
	<li><code>1 &lt;= rows, cols &lt;= 100</code></li>
	<li><code>mat[i][j]</code> 是 <code>0</code> 或 <code>1</code></li>
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
