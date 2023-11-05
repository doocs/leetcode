# [2923. Find Champion I](https://leetcode.com/problems/find-champion-i)

[中文文档](/solution/2900-2999/2923.Find%20Champion%20I/README.md)

## Description

<p>There are <code>n</code> teams numbered from <code>0</code> to <code>n - 1</code> in a tournament.</p>

<p>Given a <strong>0-indexed</strong> 2D boolean matrix <code>grid</code> of size <code>n * n</code>. For all <code>i, j</code> that <code>0 &lt;= i, j &lt;= n - 1</code> and <code>i != j</code> team <code>i</code> is <strong>stronger</strong> than team <code>j</code> if <code>grid[i][j] == 1</code>, otherwise, team <code>j</code> is <strong>stronger</strong> than team <code>i</code>.</p>

<p>Team <code>a</code> will be the <strong>champion</strong> of the tournament if there is no team <code>b</code> that is stronger than team <code>a</code>.</p>

<p>Return <em>the team that will be the champion of the tournament.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> grid = [[0,1],[0,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are two teams in this tournament.
grid[0][1] == 1 means that team 0 is stronger than team 1. So team 0 will be the champion.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[0,0,1],[1,0,1],[0,0,0]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There are three teams in this tournament.
grid[1][0] == 1 means that team 1 is stronger than team 0.
grid[1][2] == 1 means that team 1 is stronger than team 2.
So team 1 will be the champion.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
	<li>For all <code>i, j</code> that <code>i != j</code>, <code>grid[i][j] != grid[j][i]</code>.</li>
	<li>The input is generated such that if team <code>a</code> is stronger than team <code>b</code> and team <code>b</code> is stronger than team <code>c</code>, then team <code>a</code> is stronger than team <code>c</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
