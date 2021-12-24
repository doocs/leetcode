# [1030. 距离顺序排列矩阵单元格](https://leetcode-cn.com/problems/matrix-cells-in-distance-order)

[English Version](/solution/1000-1099/1030.Matrix%20Cells%20in%20Distance%20Order/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出 <code>R</code> 行 <code>C</code> 列的矩阵，其中的单元格的整数坐标为 <code>(r, c)</code>，满足 <code>0 &lt;= r &lt; R</code> 且 <code>0 &lt;= c &lt; C</code>。</p>

<p>另外，我们在该矩阵中给出了一个坐标为&nbsp;<code>(r0, c0)</code> 的单元格。</p>

<p>返回矩阵中的所有单元格的坐标，并按到 <code>(r0, c0)</code> 的距离从最小到最大的顺序排，其中，两单元格<code>(r1, c1)</code> 和 <code>(r2, c2)</code> 之间的距离是曼哈顿距离，<code>|r1 - r2| + |c1 - c2|</code>。（你可以按任何满足此条件的顺序返回答案。）</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>R = 1, C = 2, r0 = 0, c0 = 0
<strong>输出：</strong>[[0,0],[0,1]]
<strong>解释</strong>：从 (r0, c0) 到其他单元格的距离为：[0,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>R = 2, C = 2, r0 = 0, c0 = 1
<strong>输出：</strong>[[0,1],[0,0],[1,1],[1,0]]
<strong>解释</strong>：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
[[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>R = 2, C = 3, r0 = 1, c0 = 2
<strong>输出：</strong>[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
<strong>解释</strong>：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= R &lt;= 100</code></li>
	<li><code>1 &lt;= C &lt;= 100</code></li>
	<li><code>0 &lt;= r0 &lt; R</code></li>
	<li><code>0 &lt;= c0 &lt; C</code></li>
</ol>

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
