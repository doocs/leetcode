---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3530.Maximum%20Profit%20from%20Valid%20Topological%20Order%20in%20DAG/README_EN.md
tags:
    - Bit Manipulation
    - Graph
    - Topological Sort
    - Array
    - Dynamic Programming
    - Bitmask
---

<!-- problem:start -->

# [3530. Maximum Profit from Valid Topological Order in DAG](https://leetcode.com/problems/maximum-profit-from-valid-topological-order-in-dag)

[中文文档](/solution/3500-3599/3530.Maximum%20Profit%20from%20Valid%20Topological%20Order%20in%20DAG/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>Directed Acyclic Graph (DAG)</strong> with <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>, represented by a 2D array <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates a directed edge from node <code>u<sub>i</sub></code> to <code>v<sub>i</sub></code>. Each node has an associated <strong>score</strong> given in an array <code>score</code>, where <code>score[i]</code> represents the score of node <code>i</code>.</p>

<p>You must process the nodes in a <strong>valid topological order</strong>. Each node is assigned a <strong>1-based position</strong> in the processing order.</p>

<p>The <strong>profit</strong> is calculated by summing up the product of each node&#39;s score and its position in the ordering.</p>

<p>Return the <strong>maximum </strong>possible profit achievable with an optimal topological order.</p>

<p>A <strong>topological order</strong> of a DAG is a linear ordering of its nodes such that for every directed edge <code>u &rarr; v</code>, node <code>u</code> comes before <code>v</code> in the ordering.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, edges = [[0,1]], score = [2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3530.Maximum%20Profit%20from%20Valid%20Topological%20Order%20in%20DAG/images/screenshot-2025-03-11-at-021131.png" style="width: 200px; height: 89px;" /></p>

<p>Node 1 depends on node 0, so a valid order is <code>[0, 1]</code>.</p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Node</th>
			<th style="border: 1px solid black;">Processing Order</th>
			<th style="border: 1px solid black;">Score</th>
			<th style="border: 1px solid black;">Multiplier</th>
			<th style="border: 1px solid black;">Profit Calculation</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1st</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2 &times; 1 = 2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2nd</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3 &times; 2 = 6</td>
		</tr>
	</tbody>
</table>

<p>The maximum total profit achievable over all valid topological orders is <code>2 + 6 = 8</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1],[0,2]], score = [1,6,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">25</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3530.Maximum%20Profit%20from%20Valid%20Topological%20Order%20in%20DAG/images/screenshot-2025-03-11-at-023558.png" style="width: 200px; height: 124px;" /></p>

<p>Nodes 1 and 2 depend on node 0, so the most optimal valid order is <code>[0, 2, 1]</code>.</p>

<table data-end="1197" data-start="851" node="[object Object]" style="border: 1px solid black;">
	<thead data-end="920" data-start="851">
		<tr data-end="920" data-start="851">
			<th data-end="858" data-start="851" style="border: 1px solid black;">Node</th>
			<th data-end="877" data-start="858" style="border: 1px solid black;">Processing Order</th>
			<th data-end="885" data-start="877" style="border: 1px solid black;">Score</th>
			<th data-end="898" data-start="885" style="border: 1px solid black;">Multiplier</th>
			<th data-end="920" data-start="898" style="border: 1px solid black;">Profit Calculation</th>
		</tr>
	</thead>
	<tbody data-end="1197" data-start="991">
		<tr data-end="1059" data-start="991">
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1st</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1 &times; 1 = 1</td>
		</tr>
		<tr data-end="1128" data-start="1060">
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2nd</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3 &times; 2 = 6</td>
		</tr>
		<tr data-end="1197" data-start="1129">
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">3rd</td>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">6 &times; 3 = 18</td>
		</tr>
	</tbody>
</table>

<p>The maximum total profit achievable over all valid topological orders is <code>1 + 6 + 18 = 25</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == score.length &lt;= 22</code></li>
	<li><code>1 &lt;= score[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= n * (n - 1) / 2</code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>]</code> denotes a directed edge from <code>u<sub>i</sub></code> to <code>v<sub>i</sub></code>.</li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li>The input graph is <strong>guaranteed</strong> to be a <strong>DAG</strong>.</li>
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
