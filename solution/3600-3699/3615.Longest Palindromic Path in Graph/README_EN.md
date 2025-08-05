---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3615.Longest%20Palindromic%20Path%20in%20Graph/README_EN.md
rating: 2463
source: Weekly Contest 458 Q4
tags:
    - Bit Manipulation
    - Graph
    - String
    - Dynamic Programming
    - Bitmask
---

<!-- problem:start -->

# [3615. Longest Palindromic Path in Graph](https://leetcode.com/problems/longest-palindromic-path-in-graph)

[中文文档](/solution/3600-3699/3615.Longest%20Palindromic%20Path%20in%20Graph/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> and an <strong>undirected</strong> graph with <code>n</code> nodes labeled from 0 to <code>n - 1</code> and a 2D array <code>edges</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>.</p>

<p>You are also given a string <code>label</code> of length <code>n</code>, where <code>label[i]</code> is the character associated with node <code>i</code>.</p>

<p>You may start at any node and move to any adjacent node, visiting each node <strong>at most</strong> once.</p>

<p>Return the <strong>maximum</strong> possible length of a <strong><span data-keyword="palindrome-string">palindrome</span></strong> that can be formed by visiting a set of <strong>unique</strong> nodes along a valid path.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1],[1,2]], label = &quot;aba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Exp</strong><strong>lanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3615.Longest%20Palindromic%20Path%20in%20Graph/images/screenshot-2025-06-13-at-230714.png" style="width: 250px; height: 85px;" /></p>

<ul>
	<li>The longest palindromic path is from node 0 to node 2 via node 1, following the path <code>0 &rarr; 1 &rarr; 2</code> forming string <code>&quot;aba&quot;</code>.</li>
	<li>This is a valid palindrome of length 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1],[0,2]], label = &quot;abc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3615.Longest%20Palindromic%20Path%20in%20Graph/images/screenshot-2025-06-13-at-230017.png" style="width: 200px; height: 150px;" /></p>

<ul>
	<li>No path with more than one node forms a palindrome.</li>
	<li>The best option is any single node, giving a palindrome of length 1.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[0,2],[0,3],[3,1]], label = &quot;bbac&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3615.Longest%20Palindromic%20Path%20in%20Graph/images/screenshot-2025-06-13-at-230508.png" style="width: 200px; height: 200px;" /></p>

<ul>
	<li>The longest palindromic path is from node 0 to node 1, following the path <code>0 &rarr; 3 &rarr; 1</code>, forming string <code>&quot;bcb&quot;</code>.</li>
	<li>This is a valid palindrome of length 3.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 14</code></li>
	<li><code>n - 1 &lt;= edges.length &lt;= n * (n - 1) / 2</code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>label.length == n</code></li>
	<li><code>label</code> consists of lowercase English letters.</li>
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
