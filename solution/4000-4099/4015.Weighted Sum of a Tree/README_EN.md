---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4015.Weighted%20Sum%20of%20a%20Tree/README_EN.md
---

<!-- problem:start -->

# [4015. Weighted Sum of a Tree](https://leetcode.com/problems/weighted-sum-of-a-tree)

[中文文档](/solution/4000-4099/4015.Weighted%20Sum%20of%20a%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>parent</code> of length <code>n</code> representing a rooted tree with nodes labeled from 0 to <code>n - 1</code>.</p>

<p>The tree is <strong>rooted</strong> at node 0, so <code>parent[0] = -1</code>. For each node <code>i</code> where <code>1 &lt;= i &lt;= n - 1</code>, <code>parent[i]</code> denotes the parent of node <code>i</code>.</p>

<p>You are also given an integer array <code>nums</code> of length <code>n</code>, where <code>nums[i]</code> denotes the value of node <code>i</code>.</p>

<p>The weight of a node <code>i</code> at depth <code>d</code> is <code>nums[i] * (h - d + 1)</code>, where <code>h</code> is the height of the tree.</p>

<p>Return the <strong>sum</strong> of the weights of all nodes in the tree.</p>

<p>The <strong>depth</strong> of a node is the number of nodes on the path from the root to that node, inclusive, with the root having depth 1.</p>

<p>The <strong>height</strong> of the tree is the maximum depth among all nodes in the tree.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4015.Weighted%20Sum%20of%20a%20Tree/images/t1.png" style="width: 200px; height: 190px;" />​​​​​​​</p>

<p><strong>Input:</strong> <span class="example-io">parent = [-1,0,0,0,2,2], nums = [5,2,3,1,4,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">37</span></p>

<p><strong>Explanation:</strong></p>

<p>The height of the tree is 3.</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Node</th>
			<th style="border: 1px solid black;"><code>nums[i]</code></th>
			<th style="border: 1px solid black;">Depth (<code>d</code>)</th>
			<th style="border: 1px solid black;">Weight</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>5 * (3 - 1 + 1) = 15</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>2 * (3 - 2 + 1) = 4</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>3 * (3 - 2 + 1) = 6</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>1 * (3 - 2 + 1) = 2</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;"><code>4 * (3 - 3 + 1) = 4</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;"><code>6 * (3 - 3 + 1) = 6</code></td>
		</tr>
	</tbody>
</table>

<p>The sum of all node weights is <code>15 + 4 + 6 + 2 + 4 + 6 = 37</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4015.Weighted%20Sum%20of%20a%20Tree/images/t2.png" style="width: 250px; height: 56px;" />​​​​​​​​​​​​​​</p>

<p><strong>Input:</strong> <span class="example-io">parent = [-1,0,1,2], nums = [1,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">20</span></p>

<p><strong>Explanation:</strong></p>

<p>The height of the tree is 4.</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Node</th>
			<th style="border: 1px solid black;"><code>nums[i]</code></th>
			<th style="border: 1px solid black;">Depth (<code>d</code>)</th>
			<th style="border: 1px solid black;">Weight</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>1 * (4 - 1 + 1) = 4</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>2 * (4 - 2 + 1) = 6</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;"><code>3 * (4 - 3 + 1) = 6</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;"><code>4 * (4 - 4 + 1) = 4</code></td>
		</tr>
	</tbody>
</table>

<p>The sum of all node weights is <code>4 + 6 + 6 + 4 = 20</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n == parent.length == nums.length</code></li>
	<li><code>parent[0] == -1</code></li>
	<li><code>0 &lt;= parent[i] &lt;= n - 1</code> for all <code>i</code> in <code>[1, n - 1]</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li>The input is generated such that the array <code>parent</code> represents a valid tree rooted at node 0.</li>
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
