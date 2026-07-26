---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4003.Minimum%20Cost%20Path%20with%20Alternating%20Directions%20III/README_EN.md
---

<!-- problem:start -->

# [4003. Minimum Cost Path with Alternating Directions III](https://leetcode.com/problems/minimum-cost-path-with-alternating-directions-iii)

[中文文档](/solution/4000-4099/4003.Minimum%20Cost%20Path%20with%20Alternating%20Directions%20III/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>m</code> and <code>n</code> representing the number of rows and columns of a grid. Your goal is to reach cell <code>(m - 1, n - 1)</code>. You are also given a 2D integer array <code>penalty</code>.</p>

<p>The cost to enter cell <code>(i, j)</code> is <code>(i + 1) * (j + 1)</code>.</p>

<p>You begin at cell <code>(0, 0)</code> and initially pay its entrance cost. Actions performed after entering <code>(0, 0)</code> are numbered starting from 1.</p>

<p>On each action, you may move to an <strong>adjacent</strong> cell or wait in the current cell. A move follows the parity rule if:</p>

<ul>
	<li>On an <strong>odd-numbered</strong> action, you move <strong>right</strong> or <strong>down</strong>.</li>
	<li>On an <strong>even-numbered</strong> action, you move <strong>left</strong> or <strong>up</strong>.</li>
</ul>

<p>The cost of an action is determined as follows:</p>

<ul>
	<li>If you move according to the parity rule, pay only the entrance cost of the destination cell.</li>
	<li>If you move in a direction that <strong>violates</strong> the parity rule, pay the entrance cost of the destination cell plus <code>penalty[i][j]</code>, where <code>(i, j)</code> is the cell you move from.</li>
	<li>If you <strong>wait</strong> in cell <code>(i, j)</code>, pay <code>penalty[i][j]</code>.</li>
</ul>

<p>After every move or wait, the action number increases by 1. Therefore, the required parity alternates after every action, regardless of whether a penalty was paid.</p>

<p>Return the <strong>minimum</strong> total cost required to reach <code>(m - 1, n - 1)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 2, n = 2, penalty = [[5,3],[1,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal path is:</p>

<ul>
	<li>Start at cell <code>(0, 0)</code> with entry cost <code>(0 + 1) * (0 + 1) = 1</code>.</li>
	<li><strong>Move 1</strong>: Move down to cell <code>(1, 0)</code> with entry cost <code>(1 + 1) * (0 + 1) = 2</code>.</li>
	<li><strong>Move 2</strong>: Move right to cell <code>(1, 1)</code> with entry cost <code>(1 + 1) * (1 + 1) = 4</code> and an extra cost of <code>penalty[1][0] = 1</code> for violating the even parity rule.</li>
</ul>

<p>Thus, the total cost is <code>1 + 2 + 4 + 1 = 8</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 2, n = 2, penalty = [[0,7],[3,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal path is:</p>

<ul>
	<li>Start at cell <code>(0, 0)</code> with entry cost <code>(0 + 1) * (0 + 1) = 1</code>.</li>
	<li><strong>Move 1</strong>: Wait at cell <code>(0, 0)</code> with an extra cost of <code>penalty[0][0] = 0</code> to flip to even parity.</li>
	<li><strong>Move 2</strong>: Move right to cell <code>(0, 1)</code> with entry cost <code>(0 + 1) * (1 + 1) = 2</code> and an extra cost of <code>penalty[0][0] = 0</code> for violating the even parity rule.</li>
	<li><strong>Move 3</strong>: Move down to cell <code>(1, 1)</code> with entry cost <code>(1 + 1) * (1 + 1) = 4</code>.</li>
</ul>

<p>Thus, the total cost is <code>1 + 0 + 2 + 0 + 4 = 7</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 2, n = 3, penalty = [[8,0,9],[7,4,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal path is:</p>

<ul>
	<li>Start at cell <code>(0, 0)</code> with entry cost <code>(0 + 1) * (0 + 1) = 1</code>.</li>
	<li><strong>Move 1</strong>: Move right to cell <code>(0, 1)</code> with entry cost <code>(0 + 1) * (1 + 1) = 2</code>.</li>
	<li><strong>Move 2</strong>: Move right to cell <code>(0, 2)</code> with entry cost <code>(0 + 1) * (2 + 1) = 3</code> and an extra cost of <code>penalty[0][1] = 0</code> for violating the even parity rule.</li>
	<li><strong>Move 3</strong>: Move down to cell <code>(1, 2)</code> with entry cost <code>(1 + 1) * (2 + 1) = 6</code>.</li>
</ul>

<p>Thus, the total cost is <code>1 + 2 + 3 + 0 + 6 = 12</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>penalty.length == m</code></li>
	<li><code>penalty[i].length == n</code></li>
	<li><code>0 &lt;= penalty[i][j] &lt;= 10<sup>5</sup></code></li>
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
