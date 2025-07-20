---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3559.Number%20of%20Ways%20to%20Assign%20Edge%20Weights%20II/README_EN.md
rating: 2146
source: Biweekly Contest 157 Q4
tags:
    - Tree
    - Depth-First Search
    - Array
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [3559. Number of Ways to Assign Edge Weights II](https://leetcode.com/problems/number-of-ways-to-assign-edge-weights-ii)

[中文文档](/solution/3500-3599/3559.Number%20of%20Ways%20to%20Assign%20Edge%20Weights%20II/README.md)

## Description

<!-- description:start -->

<p>There is an undirected tree with <code>n</code> nodes labeled from 1 to <code>n</code>, rooted at node 1. The tree is represented by a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that there is an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>.</p>

<p>Initially, all edges have a weight of 0. You must assign each edge a weight of either <strong>1</strong> or <strong>2</strong>.</p>

<p>The <strong>cost</strong> of a path between any two nodes <code>u</code> and <code>v</code> is the total weight of all edges in the path connecting them.</p>

<p>You are given a 2D integer array <code>queries</code>. For each <code>queries[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>, determine the number of ways to assign weights to edges <strong>in the path</strong> such that the cost of the path between <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> is <strong>odd</strong>.</p>

<p>Return an array <code>answer</code>, where <code>answer[i]</code> is the number of valid assignments for <code>queries[i]</code>.</p>

<p>Since the answer may be large, apply <strong>modulo</strong> <code>10<sup>9</sup> + 7</code> to each <code>answer[i]</code>.</p>

<p><strong>Note:</strong> For each query, disregard all edges <strong>not</strong> in the path between node <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3559.Number%20of%20Ways%20to%20Assign%20Edge%20Weights%20II/images/screenshot-2025-03-24-at-060006.png" style="height: 72px; width: 200px;" /></p>

<p><strong>Input:</strong> <span class="example-io">edges = [[1,2]], queries = [[1,1],[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,1]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Query <code>[1,1]</code>: The path from Node 1 to itself consists of no edges, so the cost is 0. Thus, the number of valid assignments is 0.</li>
	<li>Query <code>[1,2]</code>: The path from Node 1 to Node 2 consists of one edge (<code>1 &rarr; 2</code>). Assigning weight 1 makes the cost odd, while 2 makes it even. Thus, the number of valid assignments is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3559.Number%20of%20Ways%20to%20Assign%20Edge%20Weights%20II/images/screenshot-2025-03-24-at-055820.png" style="height: 207px; width: 220px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[1,2],[1,3],[3,4],[3,5]], queries = [[1,4],[3,4],[2,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,1,4]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Query <code>[1,4]</code>: The path from Node 1 to Node 4 consists of two edges (<code>1 &rarr; 3</code> and <code>3 &rarr; 4</code>). Assigning weights (1,2) or (2,1) results in an odd cost. Thus, the number of valid assignments is 2.</li>
	<li>Query <code>[3,4]</code>: The path from Node 3 to Node 4 consists of one edge (<code>3 &rarr; 4</code>). Assigning weight 1 makes the cost odd, while 2 makes it even. Thus, the number of valid assignments is 1.</li>
	<li>Query <code>[2,5]</code>: The path from Node 2 to Node 5 consists of three edges (<code>2 &rarr; 1, 1 &rarr; 3</code>, and <code>3 &rarr; 5</code>). Assigning (1,2,2), (2,1,2), (2,2,1), or (1,1,1) makes the cost odd. Thus, the number of valid assignments is 4.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
	<li><code>edges</code> represents a valid tree.</li>
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
