# [1057. Campus Bikes](https://leetcode.com/problems/campus-bikes)

[中文文档](/solution/1000-1099/1057.Campus%20Bikes/README.md)

## Description

<p>On a campus represented on the X-Y plane, there are <code>n</code> workers and <code>m</code> bikes, with <code>n &lt;= m</code>.</p>

<p>You are given an array <code>workers</code> of length <code>n</code> where <code>workers[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> is the position of the <code>i<sup>th</sup></code> worker. You are also given an array <code>bikes</code> of length <code>m</code> where <code>bikes[j] = [x<sub>j</sub>, y<sub>j</sub>]</code> is the position of the <code>j<sup>th</sup></code> bike. All the given positions are <strong>unique</strong>.</p>

<p>Assign a bike to each worker. Among the available bikes and workers, we choose the <code>(worker<sub>i</sub>, bike<sub>j</sub>)</code> pair with the shortest <strong>Manhattan distance</strong> between each other and assign the bike to that worker.</p>

<p>If there are multiple <code>(worker<sub>i</sub>, bike<sub>j</sub>)</code> pairs with the same shortest <strong>Manhattan distance</strong>, we choose the pair with <strong>the smallest worker index</strong>. If there are multiple ways to do that, we choose the pair with <strong>the smallest bike index</strong>. Repeat this process until there are no available workers.</p>

<p>Return <em>an array </em><code>answer</code><em> of length </em><code>n</code><em>, where </em><code>answer[i]</code><em> is the index (<strong>0-indexed</strong>) of the bike that the </em><code>i<sup>th</sup></code><em> worker is assigned to</em>.</p>

<p>The <strong>Manhattan distance</strong> between two points <code>p1</code> and <code>p2</code> is <code>Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1057.Campus%20Bikes/images/1261_example_1_v2.png" style="width: 376px; height: 366px;" />
<pre>
<strong>Input:</strong> workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
<strong>Output:</strong> [1,0]
<strong>Explanation:</strong> Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1057.Campus%20Bikes/images/1261_example_2_v2.png" style="width: 376px; height: 366px;" />
<pre>
<strong>Input:</strong> workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
<strong>Output:</strong> [0,2,1]
<strong>Explanation:</strong> Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == workers.length</code></li>
	<li><code>m == bikes.length</code></li>
	<li><code>1 &lt;= n &lt;= m &lt;= 1000</code></li>
	<li><code>workers[i].length == bikes[j].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt; 1000</code></li>
	<li><code>0 &lt;= x<sub>j</sub>, y<sub>j</sub> &lt; 1000</code></li>
	<li>All worker and bike locations are <strong>unique</strong>.</li>
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
