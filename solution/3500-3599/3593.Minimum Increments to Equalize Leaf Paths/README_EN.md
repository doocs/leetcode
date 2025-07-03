---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3593.Minimum%20Increments%20to%20Equalize%20Leaf%20Paths/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3593. Minimum Increments to Equalize Leaf Paths](https://leetcode.com/problems/minimum-increments-to-equalize-leaf-paths)

[中文文档](/solution/3500-3599/3593.Minimum%20Increments%20to%20Equalize%20Leaf%20Paths/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> and an undirected tree rooted at node 0 with <code>n</code> nodes numbered from 0 to <code>n - 1</code>. This is represented by a 2D array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates an edge from node <code>u<sub>i</sub></code> to <code>v<sub>i</sub></code> .</p>

<p>Each node <code>i</code> has an associated cost given by <code>cost[i]</code>, representing the cost to traverse that node.</p>

<p>The <strong>score</strong> of a path is defined as the sum of the costs of all nodes along the path.</p>

<p>Your goal is to make the scores of all <strong>root-to-leaf</strong> paths <strong>equal</strong> by <strong>increasing</strong> the cost of any number of nodes by <strong>any non-negative</strong> amount.</p>

<p>Return the <strong>minimum</strong> number of nodes whose cost must be increased to make all root-to-leaf path scores equal.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1],[0,2]], cost = [2,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3593.Minimum%20Increments%20to%20Equalize%20Leaf%20Paths/images/screenshot-2025-05-28-at-134018.png" style="width: 180px; height: 145px;" /></p>

<p>There are two root-to-leaf paths:</p>

<ul>
	<li>Path <code>0 &rarr; 1</code> has a score of <code>2 + 1 = 3</code>.</li>
	<li>Path <code>0 &rarr; 2</code> has a score of <code>2 + 3 = 5</code>.</li>
</ul>

<p>To make all root-to-leaf path scores equal to 5, increase the cost of node 1 by 2.<br />
Only one node is increased, so the output is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1],[1,2]], cost = [5,1,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3593.Minimum%20Increments%20to%20Equalize%20Leaf%20Paths/images/screenshot-2025-05-28-at-134249.png" style="width: 230px; height: 75px;" /></p>

<p>There is only<b> </b>one root-to-leaf path:</p>

<ul>
	<li>
	<p>Path <code>0 &rarr; 1 &rarr; 2</code> has a score of <code>5 + 1 + 4 = 10</code>.</p>
	</li>
</ul>

<p>Since only one root-to-leaf path exists, all path costs are trivially equal, and the output is 0.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, edges = [[0,4],[0,1],[1,2],[1,3]], cost = [3,4,1,1,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3593.Minimum%20Increments%20to%20Equalize%20Leaf%20Paths/images/screenshot-2025-05-28-at-135704.png" style="width: 267px; height: 250px;" /></p>

<p>There are three root-to-leaf paths:</p>

<ul>
	<li>Path <code>0 &rarr; 4</code> has a score of <code>3 + 7 = 10</code>.</li>
	<li>Path <code>0 &rarr; 1 &rarr; 2</code> has a score of <code>3 + 4 + 1 = 8</code>.</li>
	<li>Path <code>0 &rarr; 1 &rarr; 3</code> has a score of <code>3 + 4 + 1 = 8</code>.</li>
</ul>

<p>To make all root-to-leaf path scores equal to 10, increase the cost of node 1 by 2. Thus, the output is 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>cost.length == n</code></li>
	<li><code>1 &lt;= cost[i] &lt;= 10<sup>9</sup></code></li>
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
