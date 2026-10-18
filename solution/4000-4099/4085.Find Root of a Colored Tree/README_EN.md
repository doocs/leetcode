---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4085. Find Root of a Colored Tree](https://leetcode.com/problems/find-root-of-a-colored-tree)

[中文文档](/solution/4000-4099/4085.Find%20Root%20of%20a%20Colored%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given an undirected <strong>tree</strong> of <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>, represented by a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that there is an undirected edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>.</p>

<p>You are also given a string <code>colors</code> of length <code>n</code>, where <code>colors[i]</code> is one of <code>&#39;R&#39;</code>, <code>&#39;B&#39;</code>, or <code>&#39;W&#39;</code>, and denotes the color of node <code>i</code>.</p>

<p>A node chosen as <strong>root</strong> must satisfy both of the following <strong>conditions</strong> once the tree is oriented away from it:</p>

<ol>
	<li><strong>Binary-tree property:</strong> Every node has at most two children.</li>
	<li><strong>Layered color pattern:</strong> All nodes at the same depth have the same color. Starting at depth 0, these colors follow one of the three repeating sequences:
	<ul>
		<li><code>R &rarr; B &rarr; W &rarr; R &rarr; B &rarr; W &rarr; ...</code></li>
		<li><code>B &rarr; W &rarr; R &rarr; B &rarr; W &rarr; R &rarr; ...</code></li>
		<li><code>W &rarr; R &rarr; B &rarr; W &rarr; R &rarr; B &rarr; ...</code></li>
	</ul>
	</li>
</ol>

<p>Return the <strong>index</strong> of any root that satisfies both conditions, or -1 if no such root exists.</p>

<p>A <strong>tree</strong> is a connected undirected graph with no cycles.</p>

<p>The <strong>depth</strong> of a node is the number of edges on the path from the root to that node. The root has depth 0. A <strong>child</strong> of a node is an adjacent node whose depth is one greater.</p>

<p>The <strong>degree</strong> of a node is the number of edges incident to it.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[3,1],[3,2],[1,4],[2,0]], colors = &quot;BRRWB&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4085.Find%20Root%20of%20a%20Colored%20Tree/images/treee.jpg" style="width: 351px; height: 224px;" /></p>

<p>Rooting at node 3 gives each node at most two children and every edge advances to the next color in the cycle (<code>W &rarr; R &rarr; B</code>), so return 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1],[1,2],[2,3]], colors = &quot;RBRB&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4085.Find%20Root%20of%20a%20Colored%20Tree/images/treee2.jpg" style="width: 282px; height: 41px;" /></p>

<p>No root satisfies the required color step on every edge, so return -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>colors.length == n</code></li>
	<li><code>colors[i]</code> is one of <code>&#39;R&#39;</code>, <code>&#39;B&#39;</code>, <code>&#39;W&#39;</code>.</li>
	<li>The input graph is a tree and every node has degree at most three.</li>
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
