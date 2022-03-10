# [1030. 距离顺序排列矩阵单元格](https://leetcode-cn.com/problems/matrix-cells-in-distance-order)

[English Version](/solution/1000-1099/1030.Matrix%20Cells%20in%20Distance%20Order/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定四个整数 <code>row</code>&nbsp;,&nbsp; &nbsp;<code>cols</code> ,&nbsp; <code>rCenter</code> 和 <code>cCenter</code> 。有一个&nbsp;<code>rows x cols</code>&nbsp;的矩阵，你在单元格上的坐标是&nbsp;<code>(rCenter, cCenter)</code> 。</p>

<p>返回矩阵中的所有单元格的坐标，并按与<em>&nbsp;</em><code>(rCenter, cCenter)</code><em>&nbsp;</em>的 <strong>距离</strong> 从最小到最大的顺序排。你可以按 <strong>任何</strong> 满足此条件的顺序返回答案。</p>

<p>单元格<code>(r1, c1)</code> 和 <code>(r2, c2)</code> 之间的距离为<code>|r1 - r2| + |c1 - c2|</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>rows = 1, cols = 2, rCenter = 0, cCenter = 0
<strong>输出：</strong>[[0,0],[0,1]]
<strong>解释</strong>：从 (r0, c0) 到其他单元格的距离为：[0,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rows = 2, cols = 2, rCenter = 0, cCenter = 1
<strong>输出：</strong>[[0,1],[0,0],[1,1],[1,0]]
<strong>解释</strong>：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
[[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>rows = 2, cols = 3, rCenter = 1, cCenter = 2
<strong>输出：</strong>[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
<strong>解释</strong>：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= rows, cols &lt;= 100</code></li>
	<li><code>0 &lt;= rCenter &lt; rows</code></li>
	<li><code>0 &lt;= cCenter &lt; cols</code></li>
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
