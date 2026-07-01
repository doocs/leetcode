---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3973.Distinct%20Gate%20Paths%20to%20LCA/README_EN.md
tags:
    - Bit Manipulation
    - Tree
    - Depth-First Search
    - Array
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [3973. Distinct Gate Paths to LCA 🔒](https://leetcode.com/problems/distinct-gate-paths-to-lca)

[中文文档](/solution/3900-3999/3973.Distinct%20Gate%20Paths%20to%20LCA/README.md)

## Description

<!-- description:start -->

<p>You are given an undirected tree rooted at node 0 with <code>n</code> nodes numbered from 0 to <code>n - 1</code>, represented by an array <code>parent</code> where <code>parent[i]</code> is the parent of node <code>i</code>.</p>

<p>Each node <code>i</code> has three types of gates, given in a 2D array <code>gates</code> where <code>gates[i] = [red<sub>i</sub>, blue<sub>i</sub>, white<sub>i</sub>]</code> which represents the number of <strong>red</strong>, <strong>blue</strong>, and <strong>white</strong> gates at node <code>i</code>.</p>

<ul>
	<li><strong>Red</strong> gate: usable only with a <strong>red</strong> card.</li>
	<li><strong>Blue</strong> gate: usable only with a <strong>blue</strong> card.</li>
	<li><strong>White</strong> gate: usable with <strong>either</strong> card, but <strong>flips</strong> the card color when used.</li>
</ul>

<p>Alice and Bob start at given nodes with either a red or blue card (<code>1</code> = red, <code>0</code> = blue). They must <strong>independently</strong> move <strong>upward</strong> to their <strong>lowest common ancestor (LCA)</strong>.</p>

<p>At each node, a person may move to their parent <strong>only if</strong> they can use <strong>at least</strong> one gate at that node with their current card. <strong>White</strong> gates may be used any number of times to flip the card color.</p>

<p><strong>Movement rules (one move = from <code>u</code> to <code>parent[u]</code>):</strong></p>

<ul>
	<li>Movement is only upward toward the root.</li>
	<li>At node <code>u</code>, pick <strong>exactly</strong> one specific gate instance. Identical gates are treated as <strong>separate</strong> and counted individually.</li>
	<li>If holding a <strong>red</strong> card: use a red gate to remain red, or a white gate to <strong>change</strong> to blue.</li>
	<li>If holding a <strong>blue</strong> card: use a blue gate to remain blue, or a white gate to <strong>change</strong> to red.</li>
	<li>If no usable gate exists at <code>u</code>, the sequence ends.</li>
</ul>

<p>You are also given a 2D array <code>queries</code> where <code>queries[i] = [aNode<sub>i</sub>, aCard<sub>i</sub>, bNode<sub>i</sub>, bCard<sub>i</sub>]</code>:</p>

<ul>
	<li><code>aNode<sub>i</sub></code>, <code>aCard<sub>i</sub></code>: Alice&#39;s starting node and card.</li>
	<li><code>bNode<sub>i</sub></code>, <code>bCard<sub>i</sub></code>: Bob&#39;s starting node and card.</li>
</ul>

<p>For each query, count the number of <strong>distinct</strong> valid ways <strong>modulo</strong> <code>10<sup>9</sup> + 7</code> for both to reach their <strong>LCA</strong>.</p>

<p>After computing the result for all queries, return the <strong>bitwise XOR</strong> of those values.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>Two ways are distinct if the set of gates used <strong>differs</strong> for either Alice or Bob.</li>
	<li>If any person is already at the <strong>LCA</strong>, then the number of ways for them is 1.</li>
	<li>The <strong>lowest common ancestor (LCA)</strong> is defined between two nodes <code>a</code> and <code>b</code> as the lowest node in a tree that has both <code>a</code> and <code>b</code> as descendants (where a node is allowed to be a descendant of itself).</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, parent = [-1,0,0], gates = [[1,0,1],[0,1,1],[1,1,0]], queries = [[1,0,2,0],[1,1,2,0],[1,0,2,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th align="center"><code>i</code></th>
			<th align="center">Alice<br />
			[Node, Card]</th>
			<th align="center">Bob<br />
			[Node, Card]</th>
			<th align="center">LCA</th>
			<th align="center">Alice<br />
			Path</th>
			<th align="center">Bob<br />
			Path</th>
			<th align="center">Alice Ways</th>
			<th align="center">Bob Ways</th>
			<th align="center">Total Ways</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center">0</td>
			<td align="center">[1, 0]: Blue</td>
			<td align="center">[2, 0]: Blue</td>
			<td align="center">0</td>
			<td align="center">1 &rarr; 0</td>
			<td align="center">2 &rarr; 0</td>
			<td align="center">2 (1 Blue + 1 White at node 1)</td>
			<td align="center">1 (1 Blue at node 2)</td>
			<td align="center">2 &times; 1 = 2</td>
		</tr>
		<tr>
			<td align="center">1</td>
			<td align="center">[1, 1]: Red</td>
			<td align="center">[2, 0]: Blue</td>
			<td align="center">0</td>
			<td align="center">1 &rarr; 0</td>
			<td align="center">2 &rarr; 0</td>
			<td align="center">1 (1 White at node 1)</td>
			<td align="center">1 (1 Blue at node 2)</td>
			<td align="center">1 &times; 1 = 1</td>
		</tr>
		<tr>
			<td align="center">2</td>
			<td align="center">[1, 0]: Blue</td>
			<td align="center">[2, 1]: Red</td>
			<td align="center">0</td>
			<td align="center">1 &rarr; 0</td>
			<td align="center">2 &rarr; 0</td>
			<td align="center">2 (1 Blue + 1 White at node 1)</td>
			<td align="center">1 (1 Red at node 2)</td>
			<td align="center">2 &times; 1 = 2</td>
		</tr>
	</tbody>
</table>

<p>Thus, the XOR of all values: <code>2 XOR 1 XOR 2 = 1</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, parent = [-1,0,1], gates = [[0,1,2],[1,0,1],[0,0,3]], queries = [[2,0,1,0],[2,1,0,0],[1,1,2,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<div class="example-block">
<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th align="center"><code>i</code></th>
			<th align="center">Alice<br />
			[Node, Card]</th>
			<th align="center">Bob<br />
			[Node, Card]</th>
			<th align="center">LCA</th>
			<th align="center">Alice Path</th>
			<th align="center">Bob Path</th>
			<th align="center">Alice Ways</th>
			<th align="center">Bob Ways</th>
			<th align="center">Total Ways</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center">0</td>
			<td align="center">[2, 0]: Blue</td>
			<td align="center">[1, 0]: Blue</td>
			<td align="center">1</td>
			<td align="center">2 &rarr; 1</td>
			<td align="center">1</td>
			<td align="center">3 (3 White at node 2)</td>
			<td align="center">1 (no move)</td>
			<td align="center">3 &times; 1 = 3</td>
		</tr>
		<tr>
			<td align="center">1</td>
			<td align="center">[2, 1]: Red</td>
			<td align="center">[0, 0]: Blue</td>
			<td align="center">0</td>
			<td align="center">2 &rarr; 1 &rarr; 0</td>
			<td align="center">0</td>
			<td align="center">3 (3 White at node 2) &times; 1 (1 White at node 1) = 3</td>
			<td align="center">1 (no move)</td>
			<td align="center">3 &times; 1 = 3</td>
		</tr>
		<tr>
			<td align="center">2</td>
			<td align="center">[1, 1]: Red</td>
			<td align="center">[2, 1]: Red</td>
			<td align="center">1</td>
			<td align="center">1</td>
			<td align="center">2 &rarr; 1</td>
			<td align="center">1 (no move)</td>
			<td align="center">3 (3 White at node 2)</td>
			<td align="center">1 &times; 3 = 3</td>
		</tr>
	</tbody>
</table>

<p>Thus, the XOR of all values: <code>3 XOR 3 XOR 3 = 3</code>.</p>
</div>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong>​​​​​​​</p>

<ul>
	<li><code>2 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>n == parent.length == gates.length</code></li>
	<li><code>parent[0] == -1</code></li>
	<li><code>0 &lt;= parent[i] &lt; n</code> for <code>i</code> in <code>[1, n - 1]</code></li>
	<li><code>gates[i] == [red<sub>i</sub>, blue<sub>i</sub>, white<sub>i</sub>]</code></li>
	<li><code>0 &lt;= red<sub>i</sub>, blue<sub>i</sub>, white<sub>i</sub> &lt;= 10</code></li>
	<li><code>1 &lt;= queries.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>queries[i] = [aNode<sub>i</sub>, aCard<sub>i</sub>, bNode<sub>i</sub>, bCard<sub>i</sub>]</code></li>
	<li><code>0 &lt;= aNode<sub>i</sub>, bNode<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>0 &lt;= aCard<sub>i</sub>, bCard<sub>i</sub> &lt;= 1</code></li>
	<li>The input is generated such that the array <code>parent</code> represents a valid tree.</li>
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
