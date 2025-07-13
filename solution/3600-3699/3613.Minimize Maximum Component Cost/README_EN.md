---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3613.Minimize%20Maximum%20Component%20Cost/README_EN.md
---

<!-- problem:start -->

# [3613. Minimize Maximum Component Cost](https://leetcode.com/problems/minimize-maximum-component-cost)

[中文文档](/solution/3600-3699/3613.Minimize%20Maximum%20Component%20Cost/README.md)

## Description

<!-- description:start -->

<p data-end="331" data-start="85">You are given an undirected connected graph with <code data-end="137" data-start="134">n</code> nodes labeled from 0 to <code data-end="171" data-start="164">n - 1</code> and a 2D integer array <code data-end="202" data-start="195">edges</code> where <code data-end="234" data-start="209">edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> denotes an undirected edge between node <code data-end="279" data-start="275">u<sub>i</sub></code> and node <code data-end="293" data-start="289">v<sub>i</sub></code> with weight <code data-end="310" data-start="306">w<sub>i</sub></code>, and an integer <code data-end="330" data-start="327">k</code>.</p>

<p data-end="461" data-start="333">You are allowed to remove any number of edges from the graph such that the resulting graph has <strong>at most</strong> <code data-end="439" data-start="436">k</code> connected components.</p>

<p data-end="589" data-start="463">The <strong>cost</strong> of a component is defined as the <strong>maximum</strong> edge weight in that component. If a component has no edges, its cost is 0.</p>

<p data-end="760" data-start="661">Return the <strong>minimum</strong> possible value of the <strong>maximum</strong> cost among all components <strong data-end="759" data-start="736">after such removals</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, edges = [[0,1,4],[1,2,3],[1,3,2],[3,4,6]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3613.Minimize%20Maximum%20Component%20Cost/images/minimizemaximumm.jpg" style="width: 535px; height: 225px;" /></p>

<ul>
	<li data-end="1070" data-start="1021">Remove the edge between nodes 3 and 4 (weight 6).</li>
	<li data-end="1141" data-start="1073">The resulting components have costs of 0 and 4, so the overall maximum cost is 4.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[0,1,5],[1,2,5],[2,3,5]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3613.Minimize%20Maximum%20Component%20Cost/images/minmax2.jpg" style="width: 315px; height: 55px;" /></p>

<ul>
	<li data-end="1315" data-start="1251">No edge can be removed, since allowing only one component (<code>k = 1</code>) requires the graph to stay fully connected.</li>
	<li data-end="1389" data-start="1318">That single component&rsquo;s cost equals its largest edge weight, which is 5.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li>The input graph is connected.</li>
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
