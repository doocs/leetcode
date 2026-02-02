---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3786.Total%20Sum%20of%20Interaction%20Cost%20in%20Tree%20Groups/README_EN.md
rating: 2139
source: Weekly Contest 481 Q4
tags:
    - Tree
    - Depth-First Search
    - Array
---

<!-- problem:start -->

# [3786. Total Sum of Interaction Cost in Tree Groups](https://leetcode.com/problems/total-sum-of-interaction-cost-in-tree-groups)

[中文文档](/solution/3700-3799/3786.Total%20Sum%20of%20Interaction%20Cost%20in%20Tree%20Groups/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> and an undirected tree with <code>n</code> nodes numbered from 0 to <code>n - 1</code>. This is represented by a 2D array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates an undirected edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>.</p>

<p>You are also given an integer array <code>group</code> of length <code>n</code>, where <code>group[i]</code> denotes the group label assigned to node <code>i</code>.</p>

<ul>
	<li>Two nodes <code>u</code> and <code>v</code> are considered part of the same group if <code>group[u] == group[v]</code>.</li>
	<li>The <strong>interaction cost</strong> between <code>u</code> and <code>v</code> is defined as the number of edges on the unique path connecting them in the tree.</li>
</ul>

<p>Return an integer denoting the <strong>sum</strong> of interaction costs over all <strong>unordered</strong> pairs <code>(u, v)</code> with <code>u != v</code> such that <code>group[u] == group[v]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1],[1,2]], group = [1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3700-3799/3786.Total%20Sum%20of%20Interaction%20Cost%20in%20Tree%20Groups/images/screenshot-2025-09-24-at-50538-pm.png" style="width: 250px; height: 57px;" /></strong></p>

<p>All nodes belong to group 1. The interaction costs between the pairs of nodes are:</p>

<ul>
	<li>Nodes <code>(0, 1)</code>: 1</li>
	<li>Nodes <code>(1, 2)</code>: 1</li>
	<li>Nodes <code>(0, 2)</code>: 2</li>
</ul>

<p>Thus, the total interaction cost is <code>1 + 1 + 2 = 4</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1],[1,2]], group = [3,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Nodes 0 and 2 belong to group 3. The interaction cost between this pair is 2.</li>
	<li>Node 1 belongs to a different group and forms no valid pair. Therefore, the total interaction cost is 2.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[0,1],[0,2],[0,3]], group = [1,1,4,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3700-3799/3786.Total%20Sum%20of%20Interaction%20Cost%20in%20Tree%20Groups/images/screenshot-2025-09-24-at-51312-pm.png" style="width: 200px; height: 146px;" /></p>

<p>Nodes belonging to the same groups and their interaction costs are:</p>

<ul>
	<li>Group 1: Nodes <code>(0, 1)</code>: 1</li>
	<li>Group 4: Nodes <code>(2, 3)</code>: 2</li>
</ul>

<p>Thus, the total interaction cost is <code>1 + 2 = 3</code>.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, edges = [[0,1]], group = [9,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>All nodes belong to different groups and there are no valid pairs. Therefore, the total interaction cost is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>group.length == n</code></li>
	<li><code>1 &lt;= group[i] &lt;= 20</code></li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
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
