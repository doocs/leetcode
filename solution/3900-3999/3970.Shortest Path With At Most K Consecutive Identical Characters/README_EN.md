---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3970.Shortest%20Path%20With%20At%20Most%20K%20Consecutive%20Identical%20Characters/README_EN.md
rating: 1840
source: Weekly Contest 507 Q3
---

<!-- problem:start -->

# [3970. Shortest Path With At Most K Consecutive Identical Characters](https://leetcode.com/problems/shortest-path-with-at-most-k-consecutive-identical-characters)

[中文文档](/solution/3900-3999/3970.Shortest%20Path%20With%20At%20Most%20K%20Consecutive%20Identical%20Characters/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> representing the number of nodes in a <strong>directed weighted</strong> graph, numbered from 0 to <code>n - 1</code>. This is represented by a 2D integer array <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> represents a directed edge from node <code>u<sub>i</sub></code> to node <code>v<sub>i</sub></code> with weight <code>w<sub>i</sub></code>.</p>

<p>You are also given a string <code>labels</code> of length <code>n</code>, where <code>labels[i]</code> is the character assigned to node <code>i</code>, and an integer <code>k</code>.</p>

<p>Return the <strong>minimum</strong> <strong>total</strong> edge weight of a path from node 0 to node <code>n - 1</code> such that the concatenation of the labels of the nodes along the path contains <strong>at most</strong> <code>k</code> <strong>consecutive</strong> <strong>identical</strong> characters. If no valid path exists, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,1],[1,2,1],[0,2,3]], labels = &quot;aab&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal valid path from node 0 to node 2 is as follows:</p>

<ul>
	<li>Use <code>edges[2] = [0, 2, 3]</code> to reach node 2 with a weight <code>w<sub>i</sub> = 3</code>.</li>
</ul>
The corresponding concatenation of labels is <code>&quot;ab&quot;</code>, which satisfies at most <code>k = 1</code> consecutive identical characters. Thus, the answer is 3.</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,1],[1,2,1],[0,2,3]], labels = &quot;aab&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal valid path from node 0 to node 2 is as follows:</p>

<ul>
	<li>Use <code>edges[0] = [0, 1, 1]</code> to reach node 1 with weight <code>w<sub>i</sub> = 1</code>.</li>
	<li>Use <code>edges[1] = [1, 2, 1]</code> to reach node 2 with weight <code>w<sub>i</sub> = 1</code>.</li>
</ul>
The corresponding concatenation of labels is <code>&quot;aab&quot;</code>, which satisfies at most <code>k = 2</code> consecutive identical characters. Thus, the answer is 2.</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,1],[1,2,1]], labels = &quot;aaa&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no valid path from node 0 to node 2 that satisfies at most <code>k = 2</code> consecutive identical characters. Thus, the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == labels.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>labels</code> consists of lowercase English letters</li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
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
