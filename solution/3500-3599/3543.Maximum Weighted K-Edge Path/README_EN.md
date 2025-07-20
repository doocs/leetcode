---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3543.Maximum%20Weighted%20K-Edge%20Path/README_EN.md
rating: 2110
source: Biweekly Contest 156 Q3
tags:
    - Graph
    - Hash Table
    - Dynamic Programming
---

<!-- problem:start -->

# [3543. Maximum Weighted K-Edge Path](https://leetcode.com/problems/maximum-weighted-k-edge-path)

[中文文档](/solution/3500-3599/3543.Maximum%20Weighted%20K-Edge%20Path/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> and a <strong>Directed Acyclic Graph (DAG)</strong> with <code>n</code> nodes labeled from 0 to <code>n - 1</code>. This is represented by a 2D array <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> indicates a directed edge from node <code>u<sub>i</sub></code> to <code>v<sub>i</sub></code> with weight <code>w<sub>i</sub></code>.</p>

<p>You are also given two integers, <code>k</code> and <code>t</code>.</p>

<p>Your task is to determine the <strong>maximum</strong> possible sum of edge weights for any path in the graph such that:</p>

<ul>
	<li>The path contains <strong>exactly</strong> <code>k</code> edges.</li>
	<li>The total sum of edge weights in the path is <strong>strictly</strong> less than <code>t</code>.</li>
</ul>

<p>Return the <strong>maximum</strong> possible sum of weights for such a path. If no such path exists, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,1],[1,2,2]], k = 2, t = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3543.Maximum%20Weighted%20K-Edge%20Path/images/screenshot-2025-04-10-at-061326.png" style="width: 180px; height: 162px;" /></p>

<ul>
	<li>The only path with <code>k = 2</code> edges is <code>0 -&gt; 1 -&gt; 2</code> with weight <code>1 + 2 = 3 &lt; t</code>.</li>
	<li>Thus, the maximum possible sum of weights less than <code>t</code> is 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,2],[0,2,3]], k = 1, t = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3543.Maximum%20Weighted%20K-Edge%20Path/images/screenshot-2025-04-10-at-061406.png" style="width: 180px; height: 164px;" /></p>

<ul>
	<li>There are two paths with <code>k = 1</code> edge:

    <ul>
    	<li><code>0 -&gt; 1</code> with weight <code>2 &lt; t</code>.</li>
    	<li><code>0 -&gt; 2</code> with weight <code>3 = t</code>, which is not strictly less than <code>t</code>.</li>
    </ul>
    </li>
    <li>Thus, the maximum possible sum of weights less than <code>t</code> is 2.</li>

</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1,6],[1,2,8]], k = 1, t = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3543.Maximum%20Weighted%20K-Edge%20Path/images/screenshot-2025-04-10-at-061442.png" style="width: 180px; height: 154px;" /></p>

<ul>
	<li>There are two paths with k = 1 edge:
	<ul>
		<li><code>0 -&gt; 1</code> with weight <code>6 = t</code>, which is not strictly less than <code>t</code>.</li>
		<li><code>1 -&gt; 2</code> with weight <code>8 &gt; t</code>, which is not strictly less than <code>t</code>.</li>
	</ul>
	</li>
	<li>Since there is no path with sum of weights strictly less than <code>t</code>, the answer is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 300</code></li>
	<li><code>0 &lt;= edges.length &lt;= 300</code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 10</code></li>
	<li><code>0 &lt;= k &lt;= 300</code></li>
	<li><code>1 &lt;= t &lt;= 600</code></li>
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
