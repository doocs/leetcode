# [2203. 得到要求路径的最小带权子图](https://leetcode-cn.com/problems/minimum-weighted-subgraph-with-the-required-paths)

[English Version](/solution/2200-2299/2203.Minimum%20Weighted%20Subgraph%20With%20the%20Required%20Paths/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;，它表示一个 <strong>带权有向</strong> 图的节点数，节点编号为&nbsp;<code>0</code> 到&nbsp;<code>n - 1</code>&nbsp;。</p>

<p>同时给你一个二维整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [from<sub>i</sub>, to<sub>i</sub>, weight<sub>i</sub>]</code>&nbsp;，表示从&nbsp;<code>from<sub>i</sub></code>&nbsp;到&nbsp;<code>to<sub>i</sub></code>&nbsp;有一条边权为&nbsp;<code>weight<sub>i</sub></code>&nbsp;的 <strong>有向</strong> 边。</p>

<p>最后，给你三个 <strong>互不相同</strong>&nbsp;的整数&nbsp;<code>src1</code>&nbsp;，<code>src2</code>&nbsp;和&nbsp;<code>dest</code>&nbsp;，表示图中三个不同的点。</p>

<p>请你从图中选出一个 <b>边权和最小</b>&nbsp;的子图，使得从 <code>src1</code>&nbsp;和 <code>src2</code>&nbsp;出发，在这个子图中，都 <strong>可以</strong>&nbsp;到达 <code>dest</code>&nbsp;。如果这样的子图不存在，请返回 <code>-1</code>&nbsp;。</p>

<p><strong>子图</strong>&nbsp;中的点和边都应该属于原图的一部分。子图的边权和定义为它所包含的所有边的权值之和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2203.Minimum%20Weighted%20Subgraph%20With%20the%20Required%20Paths/images/example1drawio.png" style="width: 263px; height: 250px;" /></p>

<pre>
<b>输入：</b>n = 6, edges = [[0,2,2],[0,5,6],[1,0,3],[1,4,5],[2,1,1],[2,3,3],[2,3,4],[3,4,2],[4,5,1]], src1 = 0, src2 = 1, dest = 5
<b>输出：</b>9
<strong>解释：</strong>
上图为输入的图。
蓝色边为最优子图之一。
注意，子图 [[1,0,3],[0,5,6]] 也能得到最优解，但无法在满足所有限制的前提下，得到更优解。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2203.Minimum%20Weighted%20Subgraph%20With%20the%20Required%20Paths/images/example2-1drawio.png" style="width: 350px; height: 51px;" /></p>

<pre>
<b>输入：</b>n = 3, edges = [[0,1,1],[2,1,1]], src1 = 0, src2 = 1, dest = 2
<b>输出：</b>-1
<strong>解释：</strong>
上图为输入的图。
可以看到，不存在从节点 1 到节点 2 的路径，所以不存在任何子图满足所有限制。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= from<sub>i</sub>, to<sub>i</sub>, src1, src2, dest &lt;= n - 1</code></li>
	<li><code>from<sub>i</sub> != to<sub>i</sub></code></li>
	<li><code>src1</code>&nbsp;，<code>src2</code>&nbsp;和&nbsp;<code>dest</code>&nbsp;两两不同。</li>
	<li><code>1 &lt;= weight[i] &lt;= 10<sup>5</sup></code></li>
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

```ts

```

### **...**

```

```

<!-- tabs:end -->
