---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3977.Minimum%20Time%20to%20Reach%20Target%20With%20Limited%20Power/README_EN.md
---

<!-- problem:start -->

# [3977. Minimum Time to Reach Target With Limited Power](https://leetcode.com/problems/minimum-time-to-reach-target-with-limited-power)

[中文文档](/solution/3900-3999/3977.Minimum%20Time%20to%20Reach%20Target%20With%20Limited%20Power/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>directed</strong> weighted graph with <code>n</code> nodes labeled from 0 to <code>n - 1</code>.</p>

<p>The graph is represented by a 2D integer array <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, t<sub>i</sub>]</code> indicates a directed edge from node <code>u<sub>i</sub></code> to node <code>v<sub>i</sub></code> that takes <code>t<sub>i</sub></code> seconds to traverse.</p>

<p>You are also given an integer <code>power</code> representing the initial available power, and an integer array <code>cost</code> of length <code>n</code>, where <code>cost[u]</code> represents the power required to forward the signal from node <code>u</code> through <strong>any</strong> one of its outgoing edges.</p>

<p>You are given two integers <code>source</code> and <code>target</code>.</p>

<p>The signal starts at <code>source</code> at time 0 with <code>power</code> units of power and follows these rules:</p>

<ul>
	<li>The signal may traverse a directed edge from node <code>u</code> only if the remaining power is <strong>at least</strong> <code>cost[u]</code>.</li>
	<li>No power is consumed when the signal arrives at a node, unless it later leaves that node by traversing another edge.</li>
	<li>When the signal is forwarded from node <code>u</code>, the remaining power is <strong>decreased</strong> by <code>cost[u]</code> units.</li>
	<li>Traversing an edge <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, t<sub>i</sub>]</code> <strong>increases</strong> the total time by <code>t<sub>i</sub></code> seconds.</li>
</ul>

<p>Return an integer array <code>answer</code> of size 2, where:</p>

<ul>
	<li><code>answer[0]</code> is the <strong>minimum</strong> time required for the signal to reach node <code>target</code>.</li>
	<li><code>answer[1]</code> is the <strong>maximum</strong> remaining power among all paths that achieve <code>answer[0]</code>.</li>
</ul>

<p>If the signal cannot reach <code>target</code>, return <code>[-1, -1]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3977.Minimum%20Time%20to%20Reach%20Target%20With%20Limited%20Power/images/g1.png" style="width: 197px; height: 200px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, edges = [[0,1,1],[1,4,1],[0,2,1],[2,3,1],[3,4,1]], power = 4, cost = [2,3,1,1,1], source = 0, target = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The signal starts at node 0 with 4 units of power.</li>
	<li>The path <code>0 -&gt; 1 -&gt; 4</code> is not valid, because after leaving node 0, the signal has 2 units of power remaining, which is less than <code>cost[1] = 3</code>.</li>
	<li>The valid path <code>0 -&gt; 2 -&gt; 3 -&gt; 4</code> takes a total time of 3.</li>
	<li>The total power consumed along this path is <code>cost[0] + cost[2] + cost[3] = 4</code>, leaving 0 remaining power.</li>
	<li>Hence, the answer is <code>[3, 0]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3977.Minimum%20Time%20to%20Reach%20Target%20With%20Limited%20Power/images/g22.png" style="width: 167px; height: 170px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,2],[1,2,2],[2,0,2]], power = 3, cost = [1,1,1], source = 1, target = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,3]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Since the <code>source</code> and <code>target</code> are the same node, no traversal is required.</li>
	<li>Hence, the minimum total time taken is 0, and no power is consumed.</li>
	<li>Therefore, the answer is <code>[0, 3]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3977.Minimum%20Time%20to%20Reach%20Target%20With%20Limited%20Power/images/g23.png" style="height: 120px; width: 171px;" />​​​​​​​</p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[0,1,3],[2,3,4]], power = 3, cost = [1,1,1,1], source = 0, target = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,-1]</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no valid path from <code>source</code> to <code>target</code>, therefore return <code>[-1, -1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= edges.length &lt;= 1000</code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, t<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= t<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= power &lt;= 1000</code></li>
	<li><code>cost.length == n</code></li>
	<li><code>1 &lt;= cost[i] &lt;= 2000</code></li>
	<li><code>0 &lt;= source, target &lt;= n - 1</code></li>
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
