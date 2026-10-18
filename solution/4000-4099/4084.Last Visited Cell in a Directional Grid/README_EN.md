---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4084. Last Visited Cell in a Directional Grid](https://leetcode.com/problems/last-visited-cell-in-a-directional-grid)

[中文文档](/solution/4000-4099/4084.Last%20Visited%20Cell%20in%20a%20Directional%20Grid/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> matrix <code>grid</code> where each cell contains one of the following characters:</p>

<ul>
	<li><code>&#39;L&#39;</code>: move one cell left.</li>
	<li><code>&#39;R&#39;</code>: move one cell right.</li>
	<li><code>&#39;U&#39;</code>: move one cell up.</li>
	<li><code>&#39;D&#39;</code>: move one cell down.</li>
</ul>

<p>You start at cell <code>(0, 0)</code>, which is considered visited. At each step, move to the cell indicated by the character in your current cell.</p>

<p>The process <strong>ends</strong> if the indicated move would either leave the grid or enter a cell that has already been visited. In either case, the move is <strong>not</strong> made.</p>

<p>Return an integer array <code>[r, c]</code> representing the <strong>row and column indices</strong> of the <strong>last visited cell</strong>. The indices are <strong>0-based</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[&quot;R&quot;,&quot;D&quot;],[&quot;U&quot;,&quot;L&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Start at cell <code>(0, 0)</code>, which contains <code>&#39;R&#39;</code>, so move right to <code>(0, 1)</code>.</li>
	<li>At cell <code>(0, 1)</code>, the character is <code>&#39;D&#39;</code>, so move down to <code>(1, 1)</code>.</li>
	<li>At cell <code>(1, 1)</code>, the character is <code>&#39;L&#39;</code>, so move left to <code>(1, 0)</code>.</li>
	<li>At cell <code>(1, 0)</code>, the character is <code>&#39;U&#39;</code>, which indicates moving to <code>(0, 0)</code>. However, <code>(0, 0)</code> has already been visited, so this move is not made.</li>
	<li>Therefore, the process ends at the last visited cell <code>(1, 0)</code>, so the answer is <code>[1, 0]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[&quot;R&quot;,&quot;R&quot;,&quot;R&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,2]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Start at cell <code>(0, 0)</code>, which contains <code>&#39;R&#39;</code>, so move right to <code>(0, 1)</code>.</li>
	<li>At cell <code>(0, 1)</code>, the character is <code>&#39;R&#39;</code>, so move right to <code>(0, 2)</code>.</li>
	<li>At cell <code>(0, 2)</code>, the character is <code>&#39;R&#39;</code>, which indicates moving to <code>(0, 3)</code>. However, <code>(0, 3)</code> is outside the grid, so this move is not made.</li>
	<li>Therefore, the process ends at the last visited cell <code>(0, 2)</code>, so the answer is <code>[0, 2]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == grid.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= n == grid[i].length &lt;= 10<sup>5</sup></code></li>
	<li><code>m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>grid[i][j]</code> is one of <code>&#39;L&#39;</code>, <code>&#39;R&#39;</code>, <code>&#39;U&#39;</code>, or <code>&#39;D&#39;</code>.</li>
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
