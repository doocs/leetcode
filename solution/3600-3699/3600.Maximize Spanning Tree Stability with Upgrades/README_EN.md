---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3600.Maximize%20Spanning%20Tree%20Stability%20with%20Upgrades/README_EN.md
rating: 2301
source: Weekly Contest 456 Q4
tags:
    - Greedy
    - Union Find
    - Graph
    - Binary Search
    - Minimum Spanning Tree
---

<!-- problem:start -->

# [3600. Maximize Spanning Tree Stability with Upgrades](https://leetcode.com/problems/maximize-spanning-tree-stability-with-upgrades)

[中文文档](/solution/3600-3699/3600.Maximize%20Spanning%20Tree%20Stability%20with%20Upgrades/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>, representing <code>n</code> nodes numbered from 0 to <code>n - 1</code> and a list of <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, s<sub>i</sub>, must<sub>i</sub>]</code>:</p>

<ul>
	<li><code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> indicates an undirected edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>.</li>
	<li><code>s<sub>i</sub></code> is the strength of the edge.</li>
	<li><code>must<sub>i</sub></code> is an integer (0 or 1). If <code>must<sub>i</sub> == 1</code>, the edge <strong>must</strong> be included in the<strong> </strong><strong>spanning tree</strong>. These edges <strong>cannot</strong> be <strong>upgraded</strong>.</li>
</ul>

<p>You are also given an integer <code>k</code>, the <strong>maximum</strong> number of upgrades you can perform. Each upgrade <strong>doubles</strong> the strength of an edge, and each eligible edge (with <code>must<sub>i</sub> == 0</code>) can be upgraded <strong>at most</strong> once.</p>

<p>The <strong>stability</strong> of a spanning tree is defined as the <strong>minimum</strong> strength score among all edges included in it.</p>

<p>Return the <strong>maximum</strong> possible stability of any valid spanning tree. If it is impossible to connect all nodes, return <code>-1</code>.</p>

<p><strong>Note</strong>: A <strong>spanning tree</strong> of a graph with <code>n</code> nodes is a subset of the edges that connects all nodes together (i.e. the graph is <strong>connected</strong>) <em>without</em> forming any cycles, and uses <strong>exactly</strong> <code>n - 1</code> edges.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,2,1],[1,2,3,0]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Edge <code>[0,1]</code> with strength = 2 must be included in the spanning tree.</li>
	<li>Edge <code>[1,2]</code> is optional and can be upgraded from 3 to 6 using one upgrade.</li>
	<li>The resulting spanning tree includes these two edges with strengths 2 and 6.</li>
	<li>The minimum strength in the spanning tree is 2, which is the maximum possible stability.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,4,0],[1,2,3,0],[0,2,1,0]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Since all edges are optional and up to <code>k = 2</code> upgrades are allowed.</li>
	<li>Upgrade edges <code>[0,1]</code> from 4 to 8 and <code>[1,2]</code> from 3 to 6.</li>
	<li>The resulting spanning tree includes these two edges with strengths 8 and 6.</li>
	<li>The minimum strength in the tree is 6, which is the maximum possible stability.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,1,1],[1,2,1,1],[2,0,1,1]], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>All edges are mandatory and form a cycle, which violates the spanning tree property of acyclicity. Thus, the answer is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, s<sub>i</sub>, must<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>1 &lt;= s<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>must<sub>i</sub></code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>0 &lt;= k &lt;= n</code></li>
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
