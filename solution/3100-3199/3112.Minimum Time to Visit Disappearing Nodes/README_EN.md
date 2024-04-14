# [3112. Minimum Time to Visit Disappearing Nodes](https://leetcode.com/problems/minimum-time-to-visit-disappearing-nodes)

[中文文档](/solution/3100-3199/3112.Minimum%20Time%20to%20Visit%20Disappearing%20Nodes/README.md)

<!-- tags: -->

## Description

<p>There is an undirected graph of <code>n</code> nodes. You are given a 2D array <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, length<sub>i</sub>]</code> describes an edge between node <code>u<sub>i</sub></code> and node <code>v<sub>i</sub></code> with a traversal time of <code>length<sub>i</sub></code> units.</p>

<p>Additionally, you are given an array <code>disappear</code>, where <code>disappear[i]</code> denotes the time when the node <code>i</code> disappears from the graph and you won&#39;t be able to visit it.</p>

<p><strong>Notice</strong> that the graph might be disconnected and might contain multiple edges.</p>

<p>Return the array <code>answer</code>, with <code>answer[i]</code> denoting the <strong>minimum</strong> units of time required to reach node <code>i</code> from node 0. If node <code>i</code> is <strong>unreachable</strong> from node 0 then <code>answer[i]</code> is <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img 10px="" alt="" padding:="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3112.Minimum%20Time%20to%20Visit%20Disappearing%20Nodes/images/example1.png" style="width: 350px; height: 210px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io"> n = 3, edges = [[0,1,2],[1,2,1],[0,2,4]], disappear = [1,1,5]</span></p>

<p><strong>Output:</strong> <span class="example-io"> [0,-1,4]</span></p>

<p><strong>Explanation:</strong></p>

<p>We are starting our journey from node 0, and our goal is to find the minimum time required to reach each node before it disappears.</p>

<ul>
	<li>For node 0, we don&#39;t need any time as it is our starting point.</li>
	<li>For node 1, we need at least 2 units of time to traverse <code>edges[0]</code>. Unfortunately, it disappears at that moment, so we won&#39;t be able to visit it.</li>
	<li>For node 2, we need at least 4 units of time to traverse <code>edges[2]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<p><img 10px="" alt="" padding:="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3112.Minimum%20Time%20to%20Visit%20Disappearing%20Nodes/images/example2.png" style="width: 350px; height: 210px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io"> n = 3, edges = [[0,1,2],[1,2,1],[0,2,4]], disappear = [1,3,5]</span></p>

<p><strong>Output:</strong> <span class="example-io"> [0,2,3]</span></p>

<p><strong>Explanation:</strong></p>

<p>We are starting our journey from node 0, and our goal is to find the minimum time required to reach each node before it disappears.</p>

<ul>
	<li>For node 0, we don&#39;t need any time as it is the starting point.</li>
	<li>For node 1, we need at least 2 units of time to traverse <code>edges[0]</code>.</li>
	<li>For node 2, we need at least 3 units of time to traverse <code>edges[0]</code> and <code>edges[1]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, edges = [[0,1,1]], disappear = [1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,-1]</span></p>

<p><strong>Explanation:</strong></p>

<p>Exactly when we reach node 1, it disappears.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>, length<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= length<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>disappear.length == n</code></li>
	<li><code>1 &lt;= disappear[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
