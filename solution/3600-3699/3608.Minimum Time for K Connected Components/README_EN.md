---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3608.Minimum%20Time%20for%20K%20Connected%20Components/README_EN.md
---

<!-- problem:start -->

# [3608. Minimum Time for K Connected Components](https://leetcode.com/problems/minimum-time-for-k-connected-components)

[中文文档](/solution/3600-3699/3608.Minimum%20Time%20for%20K%20Connected%20Components/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> and an undirected graph with <code>n</code> nodes labeled from 0 to <code>n - 1</code>. This is represented by a 2D array <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, time<sub>i</sub>]</code> indicates an undirected edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> that can be removed at <code>time<sub>i</sub></code>.</p>

<p>You are also given an integer <code>k</code>.</p>

<p>Initially, the graph may be connected or disconnected. Your task is to find the <strong>minimum</strong> time <code>t</code> such that after removing all edges with <code>time &lt;= t</code>, the graph contains <strong>at least</strong> <code>k</code> connected components.</p>

<p>Return the <strong>minimum</strong> time <code>t</code>.</p>

<p>A <strong>connected component</strong> is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, edges = [[0,1,3]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3608.Minimum%20Time%20for%20K%20Connected%20Components/images/screenshot-2025-06-01-at-022724.png" style="width: 230px; height: 85px;" /></p>

<ul>
	<li>Initially, there is one connected component <code>{0, 1}</code>.</li>
	<li>At <code>time = 1</code> or <code>2</code>, the graph remains unchanged.</li>
	<li>At <code>time = 3</code>, edge <code>[0, 1]</code> is removed, resulting in <code>k = 2</code> connected components <code>{0}</code>, <code>{1}</code>. Thus, the answer is 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,2],[1,2,4]], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3608.Minimum%20Time%20for%20K%20Connected%20Components/images/screenshot-2025-06-01-at-022812.png" style="width: 180px; height: 164px;" /></p>

<ul>
	<li>Initially, there is one connected component <code>{0, 1, 2}</code>.</li>
	<li>At <code>time = 2</code>, edge <code>[0, 1]</code> is removed, resulting in two connected components <code>{0}</code>, <code>{1, 2}</code>.</li>
	<li>At <code>time = 4</code>, edge <code>[1, 2]</code> is removed, resulting in <code>k = 3</code> connected components <code>{0}</code>, <code>{1}</code>, <code>{2}</code>. Thus, the answer is 4.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,2,5]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3608.Minimum%20Time%20for%20K%20Connected%20Components/images/screenshot-2025-06-01-at-022930.png" style="width: 180px; height: 155px;" /></p>

<ul>
	<li>Since there are already <code>k = 2</code> disconnected components <code>{1}</code>, <code>{0, 2}</code>, no edge removal is needed. Thus, the answer is 0.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, time<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>1 &lt;= time<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li>There are no duplicate edges.</li>
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
