---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3988.Create%20Grid%20With%20Exactly%20K%20Paths%20I/README_EN.md
---

<!-- problem:start -->

# [3988. Create Grid With Exactly K Paths I](https://leetcode.com/problems/create-grid-with-exactly-k-paths-i)

[中文文档](/solution/3900-3999/3988.Create%20Grid%20With%20Exactly%20K%20Paths%20I/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code>m</code>, <code>n</code>, and <code>k</code>.</p>

<p>Construct <strong>any</strong> <code>m x n</code> grid consisting only of the characters <code>&#39;.&#39;</code> and <code>&#39;#&#39;</code>, where:</p>

<ul>
	<li><code>&#39;.&#39;</code> represents a free cell.</li>
	<li><code>&#39;#&#39;</code> represents an obstacle cell.</li>
</ul>

<p>A <strong>valid path</strong> is a sequence of free cells that:</p>

<ul>
	<li>Starts at the top-left cell <code>(0, 0)</code>.</li>
	<li>Ends at the bottom-right cell <code>(m - 1, n - 1)</code>.</li>
	<li>Moves only:
	<ul>
		<li>Right, from <code>(i, j)</code> to <code>(i, j + 1)</code>, or</li>
		<li>Down, from <code>(i, j)</code> to <code>(i + 1, j)</code>.</li>
	</ul>
	</li>
</ul>

<p>Return any grid such that there are <strong>exactly</strong> <code>k</code> <strong>valid paths</strong> from the top-left cell to the bottom-right cell. If no such grid exists, return an empty array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 2, n = 3, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;...&quot;,&quot;#..&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3988.Create%20Grid%20With%20Exactly%20K%20Paths%20I/images/screenshot-2026-05-27-at-113554am.png" style="width: 200px; height: 90px;" /></p>

<p>There are exactly <code>k = 2</code> valid paths from <code>(0, 0)</code> to <code>(1, 2)</code>:</p>

<ul>
	<li><code>(0, 0) &rarr; (0, 1) &rarr; (0, 2) &rarr; (1, 2)</code></li>
	<li><code>(0, 0) &rarr; (0, 1) &rarr; (1, 1) &rarr; (1, 2)</code></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 3, n = 3, k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;..#&quot;,&quot;...&quot;,&quot;#..&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3988.Create%20Grid%20With%20Exactly%20K%20Paths%20I/images/screenshot-2026-05-27-at-113452am.png" style="width: 250px; height: 178px;" /></p>

<p>There are exactly <code>k = 4</code> valid paths from <code>(0, 0)</code> to <code>(2, 2)</code>:</p>

<ul>
	<li><code>(0, 0) &rarr; (0, 1) &rarr; (1, 1) &rarr; (1, 2) &rarr; (2, 2)</code></li>
	<li><code>(0, 0) &rarr; (0, 1) &rarr; (1, 1) &rarr; (2, 1) &rarr; (2, 2)</code></li>
	<li><code>(0, 0) &rarr; (1, 0) &rarr; (1, 1) &rarr; (1, 2) &rarr; (2, 2)</code></li>
	<li><code>(0, 0) &rarr; (1, 0) &rarr; (1, 1) &rarr; (2, 1) &rarr; (2, 2)</code></li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 1, n = 4, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong>​</p>

<p>No grid exists with exactly <code>k = 2</code> valid paths for a <code>1 x 4</code> grid, so the answer is an empty array.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 10</code></li>
	<li><code>1 &lt;= k &lt;= 4</code></li>
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
