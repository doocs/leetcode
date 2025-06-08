---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3534.Path%20Existence%20Queries%20in%20a%20Graph%20II/README_EN.md
rating: 2507
source: Weekly Contest 447 Q4
tags:
    - Greedy
    - Graph
    - Array
    - Binary Search
    - Sorting
---

<!-- problem:start -->

# [3534. Path Existence Queries in a Graph II](https://leetcode.com/problems/path-existence-queries-in-a-graph-ii)

[中文文档](/solution/3500-3599/3534.Path%20Existence%20Queries%20in%20a%20Graph%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> representing the number of nodes in a graph, labeled from 0 to <code>n - 1</code>.</p>

<p>You are also given an integer array <code>nums</code> of length <code>n</code> and an integer <code>maxDiff</code>.</p>

<p>An <strong>undirected </strong>edge exists between nodes <code>i</code> and <code>j</code> if the <strong>absolute</strong> difference between <code>nums[i]</code> and <code>nums[j]</code> is <strong>at most</strong> <code>maxDiff</code> (i.e., <code>|nums[i] - nums[j]| &lt;= maxDiff</code>).</p>

<p>You are also given a 2D integer array <code>queries</code>. For each <code>queries[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>, find the <strong>minimum</strong> distance between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code><sub>.</sub> If no path exists between the two nodes, return -1 for that query.</p>

<p>Return an array <code>answer</code>, where <code>answer[i]</code> is the result of the <code>i<sup>th</sup></code> query.</p>

<p><strong>Note:</strong> The edges between the nodes are unweighted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, nums = [1,8,3,4,2], maxDiff = 3, queries = [[0,3],[2,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>The resulting graph is:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3534.Path%20Existence%20Queries%20in%20a%20Graph%20II/images/4149example1drawio.png" style="width: 281px; height: 161px;" /></p>

<table>
	<tbody>
		<tr>
			<th>Query</th>
			<th>Shortest Path</th>
			<th>Minimum Distance</th>
		</tr>
		<tr>
			<td>[0, 3]</td>
			<td>0 &rarr; 3</td>
			<td>1</td>
		</tr>
		<tr>
			<td>[2, 4]</td>
			<td>2 &rarr; 4</td>
			<td>1</td>
		</tr>
	</tbody>
</table>

<p>Thus, the output is <code>[1, 1]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, nums = [5,3,1,9,10], maxDiff = 2, queries = [[0,1],[0,2],[2,3],[4,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,-1,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>The resulting graph is:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3534.Path%20Existence%20Queries%20in%20a%20Graph%20II/images/4149example2drawio.png" style="width: 281px; height: 121px;" /></p>
</div>

<table>
	<tbody>
		<tr>
			<th>Query</th>
			<th>Shortest Path</th>
			<th>Minimum Distance</th>
		</tr>
		<tr>
			<td>[0, 1]</td>
			<td>0 &rarr; 1</td>
			<td>1</td>
		</tr>
		<tr>
			<td>[0, 2]</td>
			<td>0 &rarr; 1 &rarr; 2</td>
			<td>2</td>
		</tr>
		<tr>
			<td>[2, 3]</td>
			<td>None</td>
			<td>-1</td>
		</tr>
		<tr>
			<td>[4, 3]</td>
			<td>3 &rarr; 4</td>
			<td>1</td>
		</tr>
	</tbody>
</table>

<p>Thus, the output is <code>[1, 2, -1, 1]</code>.</p>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, nums = [3,6,1], maxDiff = 1, queries = [[0,0],[0,1],[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,-1,-1]</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no edges between any two nodes because:</p>

<ul>
	<li>Nodes 0 and 1: <code>|nums[0] - nums[1]| = |3 - 6| = 3 &gt; 1</code></li>
	<li>Nodes 0 and 2: <code>|nums[0] - nums[2]| = |3 - 1| = 2 &gt; 1</code></li>
	<li>Nodes 1 and 2: <code>|nums[1] - nums[2]| = |6 - 1| = 5 &gt; 1</code></li>
</ul>

<p>Thus, no node can reach any other node, and the output is <code>[0, -1, -1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= maxDiff &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
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
