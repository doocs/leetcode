# [1057. Campus Bikes](https://leetcode.com/problems/campus-bikes)

[中文文档](/solution/1000-1099/1057.Campus%20Bikes/README.md)

## Description

<p>On a campus represented as a 2D grid, there are <code>N</code> workers and <code>M</code> bikes, with <code>N &lt;= M</code>. Each worker and bike is a 2D coordinate on this grid.</p>

<p>Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.</p>

<p>The Manhattan distance between two points <code>p1</code> and <code>p2</code> is <code>Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|</code>.</p>

<p>Return a vector <code>ans</code> of length <code>N</code>, where <code>ans[i]</code> is the index (0-indexed) of the bike that the <code>i</code>-th worker is assigned to.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1057.Campus%20Bikes/images/1261_example_1_v2.png" style="width: 264px; height: 264px;" /></p>

<pre>

<strong>Input: </strong>workers = <span id="example-input-1-1">[[0,0],[2,1]]</span>, bikes = <span id="example-input-1-2">[[1,2],[3,3]]</span>

<strong>Output: </strong><span id="example-output-1">[1,0]</span>

<strong>Explanation: </strong>

Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].

</pre>

<p><strong>Example 2:</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1057.Campus%20Bikes/images/1261_example_2_v2.png" style="width: 264px; height: 264px;" /></p>

<pre>

<strong>Input: </strong>workers = <span id="example-input-2-1">[[0,0],[1,1],[2,0]]</span>, bikes = <span id="example-input-2-2">[[1,0],[2,2],[2,1]]</span>

<strong>Output: </strong><span id="example-output-2">[0,2,1]</span>

<strong>Explanation: </strong>

Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>0 &lt;= workers[i][j], bikes[i][j] &lt; 1000</code></li>
	<li>All worker and bike locations are distinct.</li>
	<li><code>1 &lt;= workers.length &lt;= bikes.length &lt;= 1000</code></li>
</ol>

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
