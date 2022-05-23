# [1659. Maximize Grid Happiness](https://leetcode.com/problems/maximize-grid-happiness)

[中文文档](/solution/1600-1699/1659.Maximize%20Grid%20Happiness/README.md)

## Description

<p>You are given four integers, <code>m</code>, <code>n</code>, <code>introvertsCount</code>, and <code>extrovertsCount</code>. You have an <code>m x n</code> grid, and there are two types of people: introverts and extroverts. There are <code>introvertsCount</code> introverts and <code>extrovertsCount</code> extroverts.</p>

<p>You should decide how many people you want to live in the grid and assign each of them one grid cell. Note that you <strong>do not</strong> have to have all the people living in the grid.</p>

<p>The <strong>happiness</strong> of each person is calculated as follows:</p>

<ul>
	<li>Introverts <strong>start</strong> with <code>120</code> happiness and <strong>lose</strong> <code>30</code> happiness for each neighbor (introvert or extrovert).</li>
	<li>Extroverts <strong>start</strong> with <code>40</code> happiness and <strong>gain</strong> <code>20</code> happiness for each neighbor (introvert or extrovert).</li>
</ul>

<p>Neighbors live in the directly adjacent cells north, east, south, and west of a person&#39;s cell.</p>

<p>The <strong>grid happiness</strong> is the <strong>sum</strong> of each person&#39;s happiness. Return<em> the <strong>maximum possible grid happiness</strong>.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1659.Maximize%20Grid%20Happiness/images/grid_happiness.png" style="width: 261px; height: 121px;" />
<pre>
<strong>Input:</strong> m = 2, n = 3, introvertsCount = 1, extrovertsCount = 2
<strong>Output:</strong> 240
<strong>Explanation:</strong> Assume the grid is 1-indexed with coordinates (row, column).
We can put the introvert in cell (1,1) and put the extroverts in cells (1,3) and (2,3).
- Introvert at (1,1) happiness: 120 (starting happiness) - (0 * 30) (0 neighbors) = 120
- Extrovert at (1,3) happiness: 40 (starting happiness) + (1 * 20) (1 neighbor) = 60
- Extrovert at (2,3) happiness: 40 (starting happiness) + (1 * 20) (1 neighbor) = 60
The grid happiness is 120 + 60 + 60 = 240.
The above figure shows the grid in this example with each person&#39;s happiness. The introvert stays in the light green cell while the extroverts live on the light purple cells.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> m = 3, n = 1, introvertsCount = 2, extrovertsCount = 1
<strong>Output:</strong> 260
<strong>Explanation:</strong> Place the two introverts in (1,1) and (3,1) and the extrovert at (2,1).
- Introvert at (1,1) happiness: 120 (starting happiness) - (1 * 30) (1 neighbor) = 90
- Extrovert at (2,1) happiness: 40 (starting happiness) + (2 * 20) (2 neighbors) = 80
- Introvert at (3,1) happiness: 120 (starting happiness) - (1 * 30) (1 neighbor) = 90
The grid happiness is 90 + 80 + 90 = 260.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> m = 2, n = 2, introvertsCount = 4, extrovertsCount = 0
<strong>Output:</strong> 240
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 5</code></li>
	<li><code>0 &lt;= introvertsCount, extrovertsCount &lt;= min(m * n, 6)</code></li>
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
