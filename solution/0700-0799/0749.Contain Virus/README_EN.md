# [749. Contain Virus](https://leetcode.com/problems/contain-virus)

[中文文档](/solution/0700-0799/0749.Contain%20Virus/README.md)

## Description

<p>A virus is spreading rapidly, and your task is to quarantine the infected area by installing walls.</p>

<p>The world is modeled as an <code>m x n</code> binary grid <code>isInfected</code>, where <code>isInfected[i][j] == 0</code> represents uninfected cells, and <code>isInfected[i][j] == 1</code> represents cells contaminated with the virus. A wall (and only one wall) can be installed between any two <strong>4-directionally</strong> adjacent cells, on the shared boundary.</p>

<p>Every night, the virus spreads to all neighboring cells in all four directions unless blocked by a wall. Resources are limited. Each day, you can install walls around only one region (i.e., the affected area (continuous block of infected cells) that threatens the most uninfected cells the following night). There <strong>will never be a tie</strong>.</p>

<p>Return <em>the number of walls used to quarantine all the infected regions</em>. If the world will become fully infected, return the number of walls used.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0749.Contain%20Virus/images/virus11-grid.jpg" style="width: 500px; height: 255px;" />
<pre>
<strong>Input:</strong> isInfected = [[0,1,0,0,0,0,0,1],[0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0]]
<strong>Output:</strong> 10
<strong>Explanation:</strong> There are 2 contaminated regions.
On the first day, add 5 walls to quarantine the viral region on the left. The board after the virus spreads is:
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0749.Contain%20Virus/images/virus12edited-grid.jpg" style="width: 500px; height: 257px;" />
On the second day, add 5 walls to quarantine the viral region on the right. The virus is fully contained.
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0749.Contain%20Virus/images/virus13edited-grid.jpg" style="width: 500px; height: 261px;" />
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0749.Contain%20Virus/images/virus2-grid.jpg" style="width: 653px; height: 253px;" />
<pre>
<strong>Input:</strong> isInfected = [[1,1,1],[1,0,1],[1,1,1]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Even though there is only one cell saved, there are 4 walls built.
Notice that walls are only built on the shared boundary of two different cells.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> isInfected = [[1,1,1,0,0,0,0,0,0],[1,0,1,0,1,1,1,1,1],[1,1,1,0,0,0,0,0,0]]
<strong>Output:</strong> 13
<strong>Explanation:</strong> The region on the left only builds two new walls.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m ==&nbsp;isInfected.length</code></li>
	<li><code>n ==&nbsp;isInfected[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>isInfected[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
	<li>There is always a contiguous viral region throughout the described process that will <strong>infect strictly more uncontaminated squares</strong> in the next round.</li>
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
