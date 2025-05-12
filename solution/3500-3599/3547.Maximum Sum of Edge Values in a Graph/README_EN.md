---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3547.Maximum%20Sum%20of%20Edge%20Values%20in%20a%20Graph/README_EN.md
---

<!-- problem:start -->

# [3547. Maximum Sum of Edge Values in a Graph](https://leetcode.com/problems/maximum-sum-of-edge-values-in-a-graph)

[中文文档](/solution/3500-3599/3547.Maximum%20Sum%20of%20Edge%20Values%20in%20a%20Graph/README.md)

## Description

<!-- description:start -->

<p>You are given an <strong>und</strong><strong>irected</strong> graph of <code>n</code> nodes, numbered from <code>0</code> to <code>n - 1</code>. Each node is connected to <strong>at most</strong> 2 other nodes.</p>

<p>The graph consists of <code>m</code> edges, represented by a 2D array <code>edges</code>, where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>.</p>

<p data-end="502" data-start="345">You have to assign a <strong>unique</strong> value from <code data-end="391" data-start="388">1</code> to <code data-end="398" data-start="395">n</code> to each node. The value of an edge will be the <strong>product</strong> of the values assigned to the two nodes it connects.</p>

<p data-end="502" data-start="345">Your score is the sum of the values of all edges in the graph.</p>

<p>Return the <strong>maximum</strong> score you can achieve.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3547.Maximum%20Sum%20of%20Edge%20Values%20in%20a%20Graph/images/graphproblemex1drawio.png" style="width: 400px; height: 157px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 7, edges = [[0,1],[1,2],[2,0],[3,4],[4,5],[5,6]]</span></p>

<p><strong>Output:</strong> <span class="example-io">130</span></p>

<p><strong>Explanation:</strong></p>

<p>The diagram above illustrates an optimal assignment of values to nodes. The sum of the values of the edges is: <code>(7 * 6) + (7 * 5) + (6 * 5) + (1 * 3) + (3 * 4) + (4 * 2) = 130</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3547.Maximum%20Sum%20of%20Edge%20Values%20in%20a%20Graph/images/graphproblemex2drawio.png" style="width: 220px; height: 255px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 6, edges = [[0,3],[4,5],[2,0],[1,3],[2,4],[1,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">82</span></p>

<p><strong>Explanation:</strong></p>

<p>The diagram above illustrates an optimal assignment of values to nodes. The sum of the values of the edges is: <code>(1 * 2) + (2 * 4) + (4 * 6) + (6 * 5) + (5 * 3) + (3 * 1) = 82</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>m == edges.length</code></li>
	<li><code>1 &lt;= m &lt;= n</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>There are no repeated edges.</li>
	<li>Each node is connected to at most 2 other nodes.</li>
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
