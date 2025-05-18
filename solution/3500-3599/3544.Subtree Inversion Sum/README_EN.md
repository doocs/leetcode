---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3544.Subtree%20Inversion%20Sum/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3544. Subtree Inversion Sum](https://leetcode.com/problems/subtree-inversion-sum)

[中文文档](/solution/3500-3599/3544.Subtree%20Inversion%20Sum/README.md)

## Description

<!-- description:start -->

<p data-end="551" data-start="302">You are given an undirected tree rooted at node <code>0</code>, with <code>n</code> nodes numbered from 0 to <code>n - 1</code>. The tree is represented by a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>.</p>

<p data-end="670" data-start="553">You are also given an integer array <code>nums</code> of length <code>n</code>, where <code>nums[i]</code> represents the value at node <code>i</code>, and an integer <code>k</code>.</p>

<p data-end="763" data-start="672">You may perform <strong>inversion operations</strong> on a subset of nodes subject to the following rules:</p>

<ul data-end="1247" data-start="765">
	<li data-end="890" data-start="765">
	<p data-end="799" data-start="767"><strong data-end="799" data-start="767">Subtree Inversion Operation:</strong></p>

    <ul data-end="890" data-start="802">
    	<li data-end="887" data-start="802">
    	<p data-end="887" data-start="804">When you invert a node, every value in the <span data-keyword="subtree-of-node">subtree</span> rooted at that node is multiplied by -1.</p>
    	</li>
    </ul>
    </li>
    <li data-end="1247" data-start="891">
    <p data-end="931" data-start="893"><strong data-end="931" data-start="893">Distance Constraint on Inversions:</strong></p>

    <ul data-end="1247" data-start="934">
    	<li data-end="1020" data-start="934">
    	<p data-end="1020" data-start="936">You may only invert a node if it is &quot;sufficiently far&quot; from any other inverted node.</p>
    	</li>
    	<li data-end="1247" data-start="1023">
    	<p data-end="1247" data-start="1025">Specifically, if you invert two nodes <code>a</code> and <code>b</code> such that one is an ancestor of the other (i.e., if <code>LCA(a, b) = a</code> or <code>LCA(a, b) = b</code>), then the distance (the number of edges on the unique path between them) must be at least <code>k</code>.</p>
    	</li>
    </ul>
    </li>

</ul>

<p data-end="1358" data-start="1249">Return the <strong>maximum</strong> possible <strong>sum</strong> of the tree&#39;s node values after applying <strong>inversion operations</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], nums = [4,-8,-6,3,7,-2,5], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">27</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3544.Subtree%20Inversion%20Sum/images/tree1-3.jpg" style="width: 311px; height: 202px;" /></p>

<ul>
	<li>Apply inversion operations at nodes 0, 3, 4 and 6.</li>
	<li>The final <code data-end="1726" data-start="1720">nums</code> array is <code data-end="1760" data-start="1736">[-4, 8, 6, 3, 7, 2, 5]</code>, and the total sum is 27.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1],[1,2],[2,3],[3,4]], nums = [-1,3,-2,4,-5], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3544.Subtree%20Inversion%20Sum/images/tree2-1.jpg" style="width: 371px; height: 71px;" /></p>

<ul>
	<li>Apply the inversion operation at node 4.</li>
	<li data-end="2632" data-start="2483">The final <code data-end="2569" data-start="2563">nums</code> array becomes <code data-end="2603" data-start="2584">[-1, 3, -2, 4, 5]</code>, and the total sum is 9.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1],[0,2]], nums = [0,-1,-2], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Apply inversion operations at nodes 1 and 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>nums.length == n</code></li>
	<li><code>-5 * 10<sup>4</sup> &lt;= nums[i] &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
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
