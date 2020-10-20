# [1473. Paint House III](https://leetcode.com/problems/paint-house-iii)

[中文文档](/solution/1400-1499/1473.Paint%20House%20III/README.md)

## Description

<p>There is&nbsp;a row of&nbsp;<code>m</code>&nbsp;houses in a small city, each house must be painted with one of the&nbsp;<code>n</code>&nbsp;colors (labeled from 1 to <code>n</code>), some houses that has been painted last summer should not be painted again.</p>

<p>A neighborhood is a maximal group of continuous houses that are painted with the same color. (For example: houses = [1,2,2,3,3,2,1,1] contains 5 neighborhoods&nbsp; [{1}, {2,2}, {3,3}, {2}, {1,1}]).</p>

<p>Given an array <code>houses</code>, an&nbsp;<code>m * n</code>&nbsp;matrix <code>cost</code> and&nbsp;an integer <code><font face="monospace">target</font></code>&nbsp;where:</p>

<ul>
	<li><code>houses[i]</code>:&nbsp;is the color of the house <code>i</code>, <strong>0</strong> if the house is not painted yet.</li>
	<li><code>cost[i][j]</code>: is the cost of paint the house <code>i</code> with the color <code>j+1</code>.</li>
</ul>

<p>Return the minimum cost of painting all the&nbsp;remaining houses in such a way that there are exactly <code>target</code> neighborhoods, if&nbsp;not possible return <strong>-1</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
<strong>Output:</strong> 9
<strong>Explanation:</strong> Paint houses of this way [1,2,2,1,1]
This array contains target = 3 neighborhoods, [{1}, {2,2}, {1,1}].
Cost of paint all houses (1 + 1 + 1 + 1 + 5) = 9.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
<strong>Output:</strong> 11
<strong>Explanation:</strong> Some houses are already painted, Paint the houses of this way [2,2,1,2,2]
This array contains target = 3 neighborhoods, [{2,2}, {1}, {2,2}]. 
Cost of paint the first and last house (10 + 1) = 11.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> houses = [0,0,0,0,0], cost = [[1,10],[10,1],[1,10],[10,1],[1,10]], m = 5, n = 2, target = 5
<strong>Output:</strong> 5
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3, target = 3
<strong>Output:</strong> -1
<strong>Explanation:</strong> Houses are already painted with a total of 4 neighborhoods [{3},{1},{2},{3}] different of target = 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == houses.length == cost.length</code></li>
	<li><code>n == cost[i].length</code></li>
	<li><code>1 &lt;= m &lt;= 100</code></li>
	<li><code>1 &lt;= n &lt;= 20</code></li>
	<li><code>1 &lt;= target&nbsp;&lt;= m</code></li>
	<li><code>0 &lt;= houses[i]&nbsp;&lt;= n</code></li>
	<li><code>1 &lt;= cost[i][j] &lt;= 10^4</code></li>
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
