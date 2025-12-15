---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3772.Maximum%20Subgraph%20Score%20in%20a%20Tree/README_EN.md
rating: 2234
source: Weekly Contest 479 Q4
tags:
    - Tree
    - Depth-First Search
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3772. Maximum Subgraph Score in a Tree](https://leetcode.com/problems/maximum-subgraph-score-in-a-tree)

[中文文档](/solution/3700-3799/3772.Maximum%20Subgraph%20Score%20in%20a%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given an <strong>undirected tree</strong> with <code>n</code> nodes, numbered from 0 to <code>n - 1</code>. It is represented by a 2D integer array <code>edges</code>​​​​​​​ of length <code>n - 1</code>, where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree.</p>

<p>You are also given an integer array <code>good</code> of length <code>n</code>, where <code>good[i]</code> is 1 if the <code>i<sup>th</sup></code> node is good, and 0 if it is bad.</p>

<p>Define the <strong>score</strong> of a <strong>subgraph</strong> as the number of good nodes minus the number of bad nodes in that subgraph.</p>

<p>For each node <code>i</code>, find the <strong>maximum</strong> possible score among all <strong>connected subgraphs</strong> that contain node <code>i</code>.</p>

<p>Return an array of <code>n</code> integers where the <code>i<sup>th</sup></code> element is the <strong>maximum</strong> score for node <code>i</code>.</p>

<p>A <strong>subgraph</strong> is a graph whose vertices and edges are subsets of the original graph.</p>

<p>A <strong>connected subgraph</strong> is a subgraph in which every pair of its vertices is reachable from one another using only its edges.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="Tree Example 1" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3700-3799/3772.Maximum%20Subgraph%20Score%20in%20a%20Tree/images/tree1fixed.png" style="width: 271px; height: 51px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1],[1,2]], good = [1,0,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1,1]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Green nodes are good and red nodes are bad.</li>
	<li>For each node, the best connected subgraph containing it is the whole tree, which has 2 good nodes and 1 bad node, resulting in a score of 1.</li>
	<li>Other connected subgraphs containing a node may have the same score.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="Tree Example 2" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3700-3799/3772.Maximum%20Subgraph%20Score%20in%20a%20Tree/images/tree2.png" style="width: 211px; height: 231px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, edges = [[1,0],[1,2],[1,3],[3,4]], good = [0,1,0,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,3,2,3,3]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Node 0: The best connected subgraph consists of nodes <code>0, 1, 3, 4</code>, which has 3 good nodes and 1 bad node, resulting in a score of <code>3 - 1 = 2</code>.</li>
	<li>Nodes 1, 3, and 4: The best connected subgraph consists of nodes <code>1, 3, 4</code>, which has 3 good nodes, resulting in a score of 3.</li>
	<li>Node 2: The best connected subgraph consists of nodes <code>1, 2, 3, 4</code>, which has 3 good nodes and 1 bad node, resulting in a score of <code>3 - 1 = 2</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<p><img alt="Tree Example 3" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3700-3799/3772.Maximum%20Subgraph%20Score%20in%20a%20Tree/images/tree3.png" style="width: 161px; height: 51px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, edges = [[0,1]], good = [0,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,-1]</span></p>

<p><strong>Explanation:</strong></p>

<p>For each node, including the other node only adds another bad node, so the best score for both nodes is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>good.length == n</code></li>
	<li><code>0 &lt;= good[i] &lt;= 1</code></li>
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
