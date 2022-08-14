# [1594. 矩阵的最大非负积](https://leetcode.cn/problems/maximum-non-negative-product-in-a-matrix)

[English Version](/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>rows x cols</code> 的矩阵 <code>grid</code> 。最初，你位于左上角 <code>(0, 0)</code> ，每一步，你可以在矩阵中 <strong>向右</strong> 或 <strong>向下</strong> 移动。</p>

<p>在从左上角 <code>(0, 0)</code> 开始到右下角 <code>(rows - 1, cols - 1)</code> 结束的所有路径中，找出具有 <strong>最大非负积</strong> 的路径。路径的积是沿路径访问的单元格中所有整数的乘积。</p>

<p>返回 <strong>最大非负积 </strong>对<strong><em> </em><code>10<sup>9</sup>&nbsp;+ 7</code></strong> <strong>取余</strong> 的结果。如果最大积为负数，则返回<em> </em><code>-1</code> 。</p>

<p><strong>注意，</strong>取余是在得到最大积之后执行的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>grid = [[-1,-2,-3],
&nbsp;            [-2,-3,-3],
&nbsp;            [-3,-3,-2]]
<strong>输出：</strong>-1
<strong>解释：</strong>从 (0, 0) 到 (2, 2) 的路径中无法得到非负积，所以返回 -1
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>grid = [[<strong>1</strong>,-2,1],
&nbsp;            [<strong>1</strong>,<strong>-2</strong>,1],
&nbsp;            [3,<strong>-4</strong>,<strong>1</strong>]]
<strong>输出：</strong>8
<strong>解释：</strong>最大非负积对应的路径已经用粗体标出 (1 * 1 * -2 * -4 * 1 = 8)
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>grid = [[<strong>1</strong>, 3],
&nbsp;            [<strong>0</strong>,<strong>-4</strong>]]
<strong>输出：</strong>0
<strong>解释：</strong>最大非负积对应的路径已经用粗体标出 (1 * 0 * -4 = 0)
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>grid = [[ <strong>1</strong>, 4,4,0],
&nbsp;            [<strong>-2</strong>, 0,0,1],
&nbsp;            [ <strong>1</strong>,<strong>-1</strong>,<strong>1</strong>,<strong>1</strong>]]
<strong>输出：</strong>2
<strong>解释：</strong>最大非负积对应的路径已经用粗体标出 (1 * -2 * 1 * -1 * 1 * 1 = 2)
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= rows, cols &lt;= 15</code></li>
	<li><code>-4 &lt;= grid[i][j] &lt;= 4</code></li>
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
