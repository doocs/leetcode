# [3123. Find Edges in Shortest Paths](https://leetcode.com/problems/find-edges-in-shortest-paths)

[中文文档](/solution/3100-3199/3123.Find%20Edges%20in%20Shortest%20Paths/README.md)

<!-- tags: -->

## Description

<p>You are given an undirected weighted graph of <code>n</code> nodes numbered from 0 to <code>n - 1</code>. The graph consists of <code>m</code> edges represented by a 2D array <code>edges</code>, where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>, w<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> with weight <code>w<sub>i</sub></code>.</p>

<p>Consider all the shortest paths from node 0 to node <code>n - 1</code> in the graph. You need to find a <strong>boolean</strong> array <code>answer</code> where <code>answer[i]</code> is <code>true</code> if the edge <code>edges[i]</code> is part of <strong>at least</strong> one shortest path. Otherwise, <code>answer[i]</code> is <code>false</code>.</p>

<p>Return the array <code>answer</code>.</p>

<p><strong>Note</strong> that the graph may not be connected.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3123.Find%20Edges%20in%20Shortest%20Paths/images/graph35drawio-1.png" style="height: 129px; width: 250px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 6, edges = [[0,1,4],[0,2,1],[1,3,2],[1,4,3],[1,5,1],[2,3,1],[3,5,3],[4,5,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[true,true,true,false,true,true,true,false]</span></p>

<p><strong>Explanation:</strong></p>

<p>The following are <strong>all</strong> the shortest paths between nodes 0 and 5:</p>

<ul>
	<li>The path <code>0 -&gt; 1 -&gt; 5</code>: The sum of weights is <code>4 + 1 = 5</code>.</li>
	<li>The path <code>0 -&gt; 2 -&gt; 3 -&gt; 5</code>: The sum of weights is <code>1 + 1 + 3 = 5</code>.</li>
	<li>The path <code>0 -&gt; 2 -&gt; 3 -&gt; 1 -&gt; 5</code>: The sum of weights is <code>1 + 1 + 2 + 1 = 5</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3123.Find%20Edges%20in%20Shortest%20Paths/images/graphhhh.png" style="width: 185px; height: 136px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[2,0,1],[0,1,1],[0,3,4],[3,2,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[true,false,false,true]</span></p>

<p><strong>Explanation:</strong></p>

<p>There is one shortest path between nodes 0 and 3, which is the path <code>0 -&gt; 2 -&gt; 3</code> with the sum of weights <code>1 + 2 = 3</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>m == edges.length</code></li>
	<li><code>1 &lt;= m &lt;= min(5 * 10<sup>4</sup>, n * (n - 1) / 2)</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li>There are no repeated edges.</li>
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
