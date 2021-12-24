# [1466. Reorder Routes to Make All Paths Lead to the City Zero](https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero)

[中文文档](/solution/1400-1499/1466.Reorder%20Routes%20to%20Make%20All%20Paths%20Lead%20to%20the%20City%20Zero/README.md)

## Description

<p>There are&nbsp;<code>n</code>&nbsp;cities numbered from&nbsp;<code>0</code>&nbsp;to&nbsp;<code>n-1</code> and&nbsp;<code>n-1</code> roads such that&nbsp;there is only one way to travel between two&nbsp;different cities (this network form a tree).&nbsp;Last year,&nbsp;The ministry of transport&nbsp;decided to orient the roads in one direction because they are too narrow.</p>

<p>Roads are represented by&nbsp;<code>connections</code>&nbsp;where&nbsp;<code>connections[i] = [a, b]</code>&nbsp;represents a&nbsp;road&nbsp;from city&nbsp;<code>a</code>&nbsp;to&nbsp;<code>b</code>.</p>

<p>This year, there will be a big event in the capital (city 0), and many people want to travel to this city.</p>

<p>Your task consists of reorienting&nbsp;some roads such that each city can visit the city&nbsp;0. Return the <strong>minimum</strong> number of edges changed.</p>

<p>It&#39;s <strong>guaranteed</strong> that each city can reach the city 0 after reorder.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1466.Reorder%20Routes%20to%20Make%20All%20Paths%20Lead%20to%20the%20City%20Zero/images/sample_1_1819.png" style="width: 240px; height: 150px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
<strong>Output:</strong> 3
<strong>Explanation: </strong>Change the direction of edges show in red such that each node can reach the node 0 (capital).</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1466.Reorder%20Routes%20to%20Make%20All%20Paths%20Lead%20to%20the%20City%20Zero/images/sample_2_1819.png" style="width: 380px; height: 60px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
<strong>Output:</strong> 2
<strong>Explanation: </strong>Change the direction of edges show in red such that each node can reach the node 0 (capital).</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 3, connections = [[1,0],[2,0]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 5 * 10^4</code></li>
	<li><code>connections.length == n-1</code></li>
	<li><code>connections[i].length == 2</code></li>
	<li><code>0 &lt;= connections[i][0], connections[i][1] &lt;= n-1</code></li>
	<li><code>connections[i][0] != connections[i][1]</code></li>
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
