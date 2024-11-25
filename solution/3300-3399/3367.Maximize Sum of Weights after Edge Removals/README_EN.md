---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3367.Maximize%20Sum%20of%20Weights%20after%20Edge%20Removals/README_EN.md
---

<!-- problem:start -->

# [3367. Maximize Sum of Weights after Edge Removals](https://leetcode.com/problems/maximize-sum-of-weights-after-edge-removals)

[中文文档](/solution/3300-3399/3367.Maximize%20Sum%20of%20Weights%20after%20Edge%20Removals/README.md)

## Description

<!-- description:start -->

<p>There exists an <strong>undirected</strong> tree with <code>n</code> nodes numbered <code>0</code> to <code>n - 1</code>. You are given a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> indicates that there is an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> with weight <code>w<sub>i</sub></code> in the tree.</p>

<p>Your task is to remove <em>zero or more</em> edges such that:</p>

<ul>
	<li>Each node has an edge with <strong>at most</strong> <code>k</code> other nodes, where <code>k</code> is given.</li>
	<li>The sum of the weights of the remaining edges is <strong>maximized</strong>.</li>
</ul>

<p>Return the <strong>maximum </strong>possible sum of weights for the remaining edges after making the necessary removals.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1,4],[0,2,2],[2,3,12],[2,4,6]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">22</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3367.Maximize%20Sum%20of%20Weights%20after%20Edge%20Removals/images/test1drawio.png" style="width: 250px; height: 250px;" /></p>

<ul>
	<li>Node 2 has edges with 3 other nodes. We remove the edge <code>[0, 2, 2]</code>, ensuring that no node has edges with more than <code>k = 2</code> nodes.</li>
	<li>The sum of weights is 22, and we can&#39;t achieve a greater sum. Thus, the answer is 22.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1,5],[1,2,10],[0,3,15],[3,4,20],[3,5,5],[0,6,10]], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">65</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Since no node has edges connecting it to more than <code>k = 3</code> nodes, we don&#39;t remove any edges.</li>
	<li>The sum of weights is 65. Thus, the answer is 65.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n - 1</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code><font face="monospace">0 &lt;= edges[i][0] &lt;= n - 1</font></code></li>
	<li><code><font face="monospace">0 &lt;= edges[i][1] &lt;= n - 1</font></code></li>
	<li><code><font face="monospace">1 &lt;= edges[i][2] &lt;= 10<sup>6</sup></font></code></li>
	<li>The input is generated such that <code>edges</code> form a valid tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
