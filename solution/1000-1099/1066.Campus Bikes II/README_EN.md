# [1066. Campus Bikes II](https://leetcode.com/problems/campus-bikes-ii)

[中文文档](/solution/1000-1099/1066.Campus%20Bikes%20II/README.md)

## Description
<p>On a campus represented as a 2D grid, there are <code>N</code> workers and <code>M</code> bikes, with <code>N <= M</code>. Each worker and bike is a 2D coordinate on this grid.</p>

<p>We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.</p>

<p>The Manhattan distance between two points <code>p1</code> and <code>p2</code> is <code>Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|</code>.</p>

<p>Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.</p>

<p> </p>

<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/03/06/1261_example_1_v2.png" style="width: 264px; height: 264px;" /></p>

<pre>
<strong>Input: </strong>workers = <span id="example-input-1-1">[[0,0],[2,1]]</span>, bikes = <span id="example-input-1-2">[[1,2],[3,3]]</span>
<strong>Output: </strong><span id="example-output-1">6</span>
<strong>Explanation: </strong>
We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output is 6.
</pre>

<p><strong>Example 2:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/03/06/1261_example_2_v2.png" style="width: 264px; height: 264px;" /></p>

<pre>
<strong>Input: </strong>workers = <span id="example-input-2-1">[[0,0],[1,1],[2,0]]</span>, bikes = <span id="example-input-2-2">[[1,0],[2,2],[2,1]]</span>
<strong>Output: </strong><span id="example-output-2">4</span>
<strong>Explanation: </strong>
We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2, bike 2 to worker 2 or worker 1. Both assignments lead to sum of the Manhattan distances as 4.
</pre>

<p> </p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000</code></li>
	<li>All worker and bike locations are distinct.</li>
	<li><code>1 <= workers.length <= bikes.length <= 10</code></li>
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