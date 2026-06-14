---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3949.Subtree%20Inversion%20Sum%20II/README_EN.md
tags:
    - Tree
    - Depth-First Search
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3949. Subtree Inversion Sum II 🔒](https://leetcode.com/problems/subtree-inversion-sum-ii)

[中文文档](/solution/3900-3999/3949.Subtree%20Inversion%20Sum%20II/README.md)

## Description

<!-- description:start -->

<p data-end="551" data-start="302">You are given an undirected tree rooted at node 0, with <code>n</code> nodes numbered from 0 to <code>n - 1</code>. The tree is represented by a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>.</p>

<p data-end="670" data-start="553">You are also given an integer array <code>nums</code> of length <code>n</code>, where <code>nums[i]</code> represents the value at node <code>i</code>, and an integer <code>k</code>.</p>

<p data-end="763" data-start="672">You may perform <strong>inversion operations</strong> on a <span data-keyword="subset">subset</span> of nodes subject to the following rules:</p>

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
    	<p data-end="1020" data-start="936">You may only invert a node if it is &ldquo;sufficiently far&rdquo; from any other inverted node.</p>
    	</li>
    	<li data-end="1247" data-start="1023">
    	<p data-end="1247" data-start="1025">If you invert two nodes <code>a</code> and <code>b</code>, the <strong>distance</strong> (the number of edges on the unique path between them) must be <strong>at least</strong> <code>k</code>.</p>
    	</li>
    </ul>
    </li>

</ul>

<p data-end="1358" data-start="1249">Return the <strong>maximum</strong> possible <strong>sum</strong> of the tree&rsquo;s node values after applying <strong>inversion operations</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1],[0,2],[0,3],[1,4],[1,5]], nums = [1,0,-10,3,4,5], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">23</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3949.Subtree%20Inversion%20Sum%20II/images/4183example1drawio.png" style="width: 602px; height: 221px;" /></p>

<p>After inverting the subtree rooted at node 2, the maximum sum becomes <code>1 + 0 + 10 + 3 + 4 + 5 = 23</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1],[1,2]], nums = [5,-10,-10], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">25</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3949.Subtree%20Inversion%20Sum%20II/images/4183example2drawio.png" style="width: 531px; height: 63px;" /></strong></p>

<p>After inverting the subtree rooted at node 1, the maximum sum becomes <code>5 + 10 + 10 = 25</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1],[0,2]], nums = [1,-5,-6], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3949.Subtree%20Inversion%20Sum%20II/images/4183example3drawio.png" style="width: 461px; height: 141px;" /></p>

<ul>
	<li>After inverting the subtrees rooted at nodes 1 and 2, <code>nums = [1, 5, 6]</code>.</li>
	<li>This is valid because nodes 1 and 2 are two edges apart (<code>1 &rarr; 0</code> and <code>0 &rarr; 2</code>), which is at least <code>k</code>.</li>
	<li>The maximum sum is <code>1 + 5 + 6 = 12</code>.</li>
</ul>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1],[0,2]], nums = [1,-5,-6], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3949.Subtree%20Inversion%20Sum%20II/images/4183example4drawio.png" style="width: 461px; height: 142px;" /></p>

<ul>
	<li>After inverting the subtree rooted at nodes 0, <code>nums = [-1, 5, 6]</code>.</li>
	<li>The maximum sum is <code>(-1) + 5 + 6 = 10</code>.</li>
	<li>Note that we cannot invert nodes 1 and 2 because their distance is <code>2 &lt; k = 3</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums.length == n</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>2 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= edges[i][0], edges[i][1] &lt; n</code></li>
	<li><code>-4 * 10<sup>4</sup> &lt;= nums[i] &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
	<li>It is guaranteed that <code>edges</code> forms a tree.</li>
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
