---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3486.Longest%20Special%20Path%20II/README_EN.md
rating: 2924
source: Biweekly Contest 152 Q4
tags:
    - Tree
    - Depth-First Search
    - Array
    - Hash Table
    - Prefix Sum
---

<!-- problem:start -->

# [3486. Longest Special Path II](https://leetcode.com/problems/longest-special-path-ii)

[中文文档](/solution/3400-3499/3486.Longest%20Special%20Path%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an undirected tree rooted at node <code>0</code>, with <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code>. This is represented by a 2D array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, length<sub>i</sub>]</code> indicates an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> with length <code>length<sub>i</sub></code>. You are also given an integer array <code>nums</code>, where <code>nums[i]</code> represents the value at node <code>i</code>.</p>

<p>A <strong>special path</strong> is defined as a <strong>downward</strong> path from an ancestor node to a descendant node in which all node values are <strong>distinct</strong>, except for <strong>at most</strong> one value that may appear twice.</p>

<p>Return an array <code data-stringify-type="code">result</code> of size 2, where <code>result[0]</code> is the <b data-stringify-type="bold">length</b> of the <strong>longest</strong> special path, and <code>result[1]</code> is the <b data-stringify-type="bold">minimum</b> number of nodes in all <i data-stringify-type="italic">possible</i> <strong>longest</strong> special paths.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1,1],[1,2,3],[1,3,1],[2,4,6],[4,7,2],[3,5,2],[3,6,5],[6,8,3]], nums = [1,1,0,3,1,2,1,1,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">[9,3]</span></p>

<p><strong>Explanation:</strong></p>

<p>In the image below, nodes are colored by their corresponding values in <code>nums</code>.</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3486.Longest%20Special%20Path%20II/images/e1.png" style="width: 190px; height: 270px;" /></p>

<p>The longest special paths are <code>1 -&gt; 2 -&gt; 4</code> and <code>1 -&gt; 3 -&gt; 6 -&gt; 8</code>, both having a length of 9. The minimum number of nodes across all longest special paths is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[1,0,3],[0,2,4],[0,3,5]], nums = [1,1,0,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">[5,2]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3486.Longest%20Special%20Path%20II/images/e2.png" style="width: 150px; height: 110px;" /></p>

<p>The longest path is <code>0 -&gt; 3</code> consisting of 2 nodes with a length of 5.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 5 * 10<sup><span style="font-size: 10.8333px;">4</span></sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= length<sub>i</sub> &lt;= 10<sup>3</sup></code></li>
	<li><code>nums.length == n</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 5 * 10<sup>4</sup></code></li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
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
