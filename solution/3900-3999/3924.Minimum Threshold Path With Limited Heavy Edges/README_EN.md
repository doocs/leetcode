---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3924.Minimum%20Threshold%20Path%20With%20Limited%20Heavy%20Edges/README_EN.md
rating: 2079
source: Biweekly Contest 182 Q4
---

<!-- problem:start -->

# [3924. Minimum Threshold Path With Limited Heavy Edges](https://leetcode.com/problems/minimum-threshold-path-with-limited-heavy-edges)

[中文文档](/solution/3900-3999/3924.Minimum%20Threshold%20Path%20With%20Limited%20Heavy%20Edges/README.md)

## Description

<!-- description:start -->

<p>There is an undirected weighted graph with <code>n</code> nodes labeled from 0 to <code>n - 1</code>.</p>

<p>The graph is represented by a 2D integer array <code>edges</code>, where each edge <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>​​​​​​​i</sub>]</code> indicates that there is an undirected edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> with weight <code>w<sub>​​​​​​​i</sub></code>.</p>

<p>You are also given integers <code>source</code>, <code>target</code> and <code>k</code>.</p>

<p>A <code>threshold</code> value determines whether an edge is considered <strong>light</strong> or <strong>heavy</strong>:</p>

<ul>
	<li>
	<p>An edge is <strong>light</strong> if its weight is <strong>less than</strong> or <strong>equal</strong> to <code>threshold</code>.</p>
	</li>
	<li>
	<p>An edge is <strong>heavy</strong> if its weight is <strong>greater than</strong> <code>threshold</code>.</p>
	</li>
</ul>

<p>A path from <code>source</code> to <code>target</code> is <strong>valid</strong> if it contains <strong>at most</strong> <code>k</code> heavy edges.</p>

<p>Return the <strong>minimum integer </strong><code>threshold</code> such that <strong>at least</strong> one <strong>valid</strong> path exists from <code>source</code> to <code>target</code>. If no such path exists, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong>​​​​​​​​​​​​​​</p>

<p>​​​​​​​<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3924.Minimum%20Threshold%20Path%20With%20Limited%20Heavy%20Edges/images/g6.png" style="width: 324px; height: 200px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 6, edges = [[0,1,5],[1,2,3],[3,4,4],[4,5,1],[1,4,2]], source = 0, target = 3, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The minimum <code>threshold</code> such that a path from node 0 to node 3 uses at most 1 heavy edge is 4.</p>

<ul>
	<li>
	<p>Light edges: <code>[1, 2, 3]</code>, <code>[3, 4, 4]</code>, <code>[4, 5, 1]</code>, <code>[1, 4, 2]</code></p>
	</li>
	<li>
	<p>Heavy edges: <code>[0, 1, 5]</code></p>
	</li>
</ul>

<p>A valid path is <code>0 &rarr; 1 &rarr; 4 &rarr; 3</code>. It uses only 1 heavy edge (<code>[0, 1, 5]</code>), which satisfies the limit <code>k = 1</code>.</p>

<p>Any smaller <code>threshold</code> would make it impossible to reach node 3 without exceeding 1 heavy edge.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3924.Minimum%20Threshold%20Path%20With%20Limited%20Heavy%20Edges/images/g3_f.png" style="width: 324px; height: 162px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 6, edges = [[0,1,3],[1,2,4],[3,4,5],[4,5,6]], source = 0, target = 4, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no path from node 0 to node 4. Since the target cannot be reached, the output is -1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<p><strong class="example"><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3924.Minimum%20Threshold%20Path%20With%20Limited%20Heavy%20Edges/images/g5.png" style="width: 309px; height: 203px;" /></strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[0,1,2],[1,2,2],[2,3,2],[3,0,2]], source = 0, target = 0, k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The source and target are the same node. No edges need to be traversed, so the minimum <code>threshold</code> is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>3</sup>​​​​​​​</code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>3</sup>​​​​​​​</code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub>​​​​​​​ &lt;= n - 1</code></li>
	<li><code>1 &lt;= w<sub>i</sub>​​​​​​​ &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= source, target &lt;= n - 1</code></li>
	<li><code>0 &lt;= k &lt;= edges.length</code></li>
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
