# [2088. Count Fertile Pyramids in a Land](https://leetcode.com/problems/count-fertile-pyramids-in-a-land)

[中文文档](/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/README.md)

## Description

<p>A farmer has a <strong>rectangular grid</strong> of land with <code>m</code> rows and <code>n</code> columns that can be divided into unit cells. Each cell is either <strong>fertile</strong> (represented by a <code>1</code>) or <strong>barren</strong> (represented by a <code>0</code>). All cells outside the grid are considered barren.</p>

<p>A <strong>pyramidal plot</strong> of land can be defined as a set of cells with the following criteria:</p>

<ol>
	<li>The number of cells in the set has to be <strong>greater than </strong><code>1</code> and all cells must be <strong>fertile</strong>.</li>
	<li>The <strong>apex</strong> of a pyramid is the <strong>topmost</strong> cell of the pyramid. The <strong>height</strong> of a pyramid is the number of rows it covers. Let <code>(r, c)</code> be the apex of the pyramid, and its height be <code>h</code>. Then, the plot comprises of cells <code>(i, j)</code> where <code>r &lt;= i &lt;= r + h - 1</code> <strong>and</strong> <code>c - (i - r) &lt;= j &lt;= c + (i - r)</code>.</li>
</ol>

<p>An <strong>inverse pyramidal plot</strong> of land can be defined as a set of cells with similar criteria:</p>

<ol>
	<li>The number of cells in the set has to be <strong>greater than </strong><code>1</code> and all cells must be <strong>fertile</strong>.</li>
	<li>The <strong>apex</strong> of an inverse pyramid is the <strong>bottommost</strong> cell of the inverse pyramid. The <strong>height</strong> of an inverse pyramid is the number of rows it covers. Let <code>(r, c)</code> be the apex of the pyramid, and its height be <code>h</code>. Then, the plot comprises of cells <code>(i, j)</code> where <code>r - h + 1 &lt;= i &lt;= r</code> <strong>and</strong> <code>c - (r - i) &lt;= j &lt;= c + (r - i)</code>.</li>
</ol>

<p>Some examples of valid and invalid pyramidal (and inverse pyramidal) plots are shown below. Black cells indicate fertile cells.</p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/image.png" style="width: 700px; height: 156px;" />
<p>Given a <strong>0-indexed</strong> <code>m x n</code> binary matrix <code>grid</code> representing the farmland, return <em>the <strong>total number</strong> of pyramidal and inverse pyramidal plots that can be found in</em> <code>grid</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/1.jpg" style="width: 575px; height: 109px;" />
<pre>
<strong>Input:</strong> grid = [[0,1,1,0],[1,1,1,1]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The 2 possible pyramidal plots are shown in blue and red respectively.
There are no inverse pyramidal plots in this grid. 
Hence total number of pyramidal and inverse pyramidal plots is 2 + 0 = 2.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/2.jpg" style="width: 502px; height: 120px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,1],[1,1,1]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The pyramidal plot is shown in blue, and the inverse pyramidal plot is shown in red. 
Hence the total number of plots is 1 + 1 = 2.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/3.jpg" style="width: 676px; height: 148px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,1,1,0],[1,1,1,1,1],[1,1,1,1,1],[0,1,0,0,1]]
<strong>Output:</strong> 13
<strong>Explanation:</strong> There are 7 pyramidal plots, 3 of which are shown in the 2nd and 3rd figures.
There are 6 inverse pyramidal plots, 2 of which are shown in the last figure.
The total number of plots is 7 + 6 = 13.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
