---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3515.Shortest%20Path%20in%20a%20Weighted%20Tree/README_EN.md
rating: 2312
source: Biweekly Contest 154 Q4
tags:
    - Tree
    - Depth-First Search
    - Binary Indexed Tree
    - Segment Tree
    - Array
---

<!-- problem:start -->

# [3515. Shortest Path in a Weighted Tree](https://leetcode.com/problems/shortest-path-in-a-weighted-tree)

[中文文档](/solution/3500-3599/3515.Shortest%20Path%20in%20a%20Weighted%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> and an undirected, weighted tree rooted at node 1 with <code>n</code> nodes numbered from 1 to <code>n</code>. This is represented by a 2D array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> indicates an undirected edge from node <code>u<sub>i</sub></code> to <code>v<sub>i</sub></code> with weight <code>w<sub>i</sub></code>.</p>

<p>You are also given a 2D integer array <code>queries</code> of length <code>q</code>, where each <code>queries[i]</code> is either:</p>

<ul>
	<li><code>[1, u, v, w&#39;]</code> &ndash; <strong>Update</strong> the weight of the edge between nodes <code>u</code> and <code>v</code> to <code>w&#39;</code>, where <code>(u, v)</code> is guaranteed to be an edge present in <code>edges</code>.</li>
	<li><code>[2, x]</code> &ndash; <strong>Compute</strong> the <strong>shortest</strong> path distance from the root node 1 to node <code>x</code>.</li>
</ul>

<p>Return an integer array <code>answer</code>, where <code>answer[i]</code> is the <strong>shortest</strong> path distance from node 1 to <code>x</code> for the <code>i<sup>th</sup></code> query of <code>[2, x]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, edges = [[1,2,7]], queries = [[2,2],[1,1,2,4],[2,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[7,4]</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3515.Shortest%20Path%20in%20a%20Weighted%20Tree/images/screenshot-2025-03-13-at-133524.png" style="width: 200px; height: 75px;" /></p>

<ul>
	<li>Query <code>[2,2]</code>: The shortest path from root node 1 to node 2 is 7.</li>
	<li>Query <code>[1,1,2,4]</code>: The weight of edge <code>(1,2)</code> changes from 7 to 4.</li>
	<li>Query <code>[2,2]</code>: The shortest path from root node 1 to node 2 is 4.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[1,2,2],[1,3,4]], queries = [[2,1],[2,3],[1,1,3,7],[2,2],[2,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,4,2,7]</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3515.Shortest%20Path%20in%20a%20Weighted%20Tree/images/screenshot-2025-03-13-at-132247.png" style="width: 180px; height: 141px;" /></p>

<ul>
	<li>Query <code>[2,1]</code>: The shortest path from root node 1 to node 1 is 0.</li>
	<li>Query <code>[2,3]</code>: The shortest path from root node 1 to node 3 is 4.</li>
	<li>Query <code>[1,1,3,7]</code>: The weight of edge <code>(1,3)</code> changes from 4 to 7.</li>
	<li>Query <code>[2,2]</code>: The shortest path from root node 1 to node 2 is 2.</li>
	<li>Query <code>[2,3]</code>: The shortest path from root node 1 to node 3 is 7.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[1,2,2],[2,3,1],[3,4,5]], queries = [[2,4],[2,3],[1,2,3,3],[2,2],[2,3]]</span></p>

<p><strong>Output:</strong> [8,3,2,5]</p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3515.Shortest%20Path%20in%20a%20Weighted%20Tree/images/screenshot-2025-03-13-at-133306.png" style="width: 400px; height: 85px;" /></p>

<ul>
	<li>Query <code>[2,4]</code>: The shortest path from root node 1 to node 4 consists of edges <code>(1,2)</code>, <code>(2,3)</code>, and <code>(3,4)</code> with weights <code>2 + 1 + 5 = 8</code>.</li>
	<li>Query <code>[2,3]</code>: The shortest path from root node 1 to node 3 consists of edges <code>(1,2)</code> and <code>(2,3)</code> with weights <code>2 + 1 = 3</code>.</li>
	<li>Query <code>[1,2,3,3]</code>: The weight of edge <code>(2,3)</code> changes from 1 to 3.</li>
	<li>Query <code>[2,2]</code>: The shortest path from root node 1 to node 2 is 2.</li>
	<li>Query <code>[2,3]</code>: The shortest path from root node 1 to node 3 consists of edges <code>(1,2)</code> and <code>(2,3)</code> with updated weights <code>2 + 3 = 5</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code></li>
	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
	<li><code>1 &lt;= queries.length == q &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code> or <code>4</code>
	<ul>
		<li><code>queries[i] == [1, u, v, w&#39;]</code> or,</li>
		<li><code>queries[i] == [2, x]</code></li>
		<li><code>1 &lt;= u, v, x &lt;= n</code></li>
		<li><code data-end="37" data-start="29">(u, v)</code> is always an edge from <code data-end="74" data-start="67">edges</code>.</li>
		<li><code>1 &lt;= w&#39; &lt;= 10<sup>4</sup></code></li>
	</ul>
	</li>
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
