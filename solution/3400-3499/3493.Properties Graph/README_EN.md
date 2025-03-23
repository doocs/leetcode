---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3493.Properties%20Graph/README_EN.md
---

<!-- problem:start -->

# [3493. Properties Graph](https://leetcode.com/problems/properties-graph)

[中文文档](/solution/3400-3499/3493.Properties%20Graph/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>properties</code> having dimensions <code>n x m</code> and an integer <code>k</code>.</p>

<p>Define a function <code>intersect(a, b)</code> that returns the <strong>number of distinct integers</strong> common to both arrays <code>a</code> and <code>b</code>.</p>

<p>Construct an <strong>undirected</strong> graph where each index <code>i</code> corresponds to <code>properties[i]</code>. There is an edge between node <code>i</code> and node <code>j</code> if and only if <code>intersect(properties[i], properties[j]) &gt;= k</code>, where <code>i</code> and <code>j</code> are in the range <code>[0, n - 1]</code> and <code>i != j</code>.</p>

<p>Return the number of <strong>connected components</strong> in the resulting graph.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">properties = [[1,2],[1,1],[3,4],[4,5],[5,6],[7,7]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The graph formed has 3 connected components:</p>

<p><img height="171" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3493.Properties%20Graph/images/image.png" width="279" /></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">properties = [[1,2,3],[2,3,4],[4,3,5]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The graph formed has 1 connected component:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3493.Properties%20Graph/images/screenshot-from-2025-02-27-23-58-34.png" style="width: 219px; height: 171px;" /></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">properties = [[1,1],[1,1]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><code>intersect(properties[0], properties[1]) = 1</code>, which is less than <code>k</code>. This means there is no edge between <code>properties[0]</code> and <code>properties[1]</code> in the graph.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == properties.length &lt;= 100</code></li>
	<li><code>1 &lt;= m == properties[i].length &lt;= 100</code></li>
	<li><code>1 &lt;= properties[i][j] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= m</code></li>
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
