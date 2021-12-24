# [1463. Cherry Pickup II](https://leetcode.com/problems/cherry-pickup-ii)

[中文文档](/solution/1400-1499/1463.Cherry%20Pickup%20II/README.md)

## Description

<p>Given a <code>rows x cols</code> matrix <code>grid</code> representing a field of cherries.&nbsp;Each cell in&nbsp;<code>grid</code>&nbsp;represents the number of cherries that you can collect.</p>

<p>You have two&nbsp;robots that can collect cherries for you, Robot #1 is located at the top-left corner (0,0) , and Robot #2 is located at the top-right corner (0, cols-1) of the grid.</p>

<p>Return the maximum number of cherries collection using both robots&nbsp; by following the rules below:</p>

<ul>
	<li>From a cell (i,j), robots can move to cell (i+1, j-1) , (i+1, j) or (i+1, j+1).</li>
	<li>When any robot is passing through a cell, It picks it up all cherries, and the cell becomes an empty cell (0).</li>
	<li>When both robots stay on the same cell, only one of them takes the cherries.</li>
	<li>Both robots cannot move outside of the grid at&nbsp;any moment.</li>
	<li>Both robots should reach the bottom row in the <code>grid</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1463.Cherry%20Pickup%20II/images/sample_1_1802.png" style="width: 139px; height: 182px;" /></strong></p>

<pre>
<strong>Input:</strong> grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
<strong>Output:</strong> 24
<strong>Explanation:</strong>&nbsp;Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
Total of cherries: 12 + 12 = 24.
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1463.Cherry%20Pickup%20II/images/sample_2_1802.png" style="width: 284px; height: 257px;" /></strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
<strong>Output:</strong> 28
<strong>Explanation:</strong>&nbsp;Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
Total of cherries: 17 + 11 = 28.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0,0,3],[0,0,0,3],[0,0,3,3],[9,0,3,3]]
<strong>Output:</strong> 22
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1],[1,1]]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>rows == grid.length</code></li>
	<li><code>cols == grid[i].length</code></li>
	<li><code>2 &lt;= rows, cols &lt;= 70</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 100&nbsp;</code></li>
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
