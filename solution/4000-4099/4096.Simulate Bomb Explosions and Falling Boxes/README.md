---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4096. Simulate Bomb Explosions and Falling Boxes 🔒](https://leetcode.cn/problems/simulate-bomb-explosions-and-falling-boxes)

[English Version](/solution/4000-4099/4096.Simulate%20Bomb%20Explosions%20and%20Falling%20Boxes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are given a 2D character array <code>grid</code> of size <code>m x n</code>, where each cell contains one of the following symbols:</p>

<ul>
	<li><code>&#39;#&#39;</code>: a box.</li>
	<li><code>&#39;-&#39;</code>: an empty cell.</li>
	<li><code>&#39;*&#39;</code>: a bomb.</li>
</ul>

<p>Each second, the following events occur <strong>in order</strong>:</p>

<ul>
	<li><strong>Explosions:</strong> A bomb explodes if it is <strong>orthogonally adjacent</strong> to at least one box (shares a side with it). An exploding bomb destroys all boxes in its <strong>8 neighboring cells</strong>, including diagonally adjacent cells, turning them into empty cells. All explosions happen <strong>simultaneously</strong>, based on the grid at the beginning of that second. Empty cells and bombs remain unchanged.</li>
	<li><strong>Gravity:</strong> After the explosions, all <strong>non-static</strong> boxes <strong>simultaneously</strong> fall down by exactly one row. A box is <strong>static</strong> if it is in the <strong>bottommost</strong> row, immediately above a bomb, or immediately above another <strong>static</strong> box.</li>
</ul>

<p>Bombs never move or disappear, and the same bomb may explode multiple times. Boxes above non-static boxes can fall together with them.</p>

<p>Return the final <code>grid</code> after 10<sup>100</sup> seconds.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[&quot;#&quot;,&quot;#&quot;,&quot;#&quot;],[&quot;-&quot;,&quot;-&quot;,&quot;*&quot;],[&quot;-&quot;,&quot;-&quot;,&quot;-&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;-&quot;,&quot;-&quot;,&quot;-&quot;],[&quot;-&quot;,&quot;-&quot;,&quot;*&quot;],[&quot;#&quot;,&quot;-&quot;,&quot;-&quot;]]</span></p>

<p><strong>Explanation:</strong></p>

<p>Let <code>(r, c)</code> represent the cell in the <code>r</code><sup>th</sup> row from the top and <code>c</code><sup>th</sup> column from the left. For example, <code>(0, 0)</code> denotes the top-left most cell.</p>

<p>The 1<sup>st</sup> second:</p>

<ul>
	<li>Since the bomb at <code>(1, 2)</code> is orthogonally adjacent to the box at <code>(0, 2)</code>, the boxes at <code>(0, 1)</code> and <code>(0, 2)</code> become empty cells.</li>
	<li>The box at <code>(0, 0)</code> falls to <code>(1, 0)</code>.</li>
</ul>

<p>The 2<sup>nd</sup> second:</p>

<ul>
	<li>The bomb at <code>(1, 2)</code> is not orthogonally adjacent to any box. Therefore, nothing happens.</li>
	<li>The box at <code>(1, 0)</code> falls to <code>(2, 0)</code>.</li>
</ul>

<p>Nothing happens afterwards.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[&quot;-&quot;,&quot;*&quot;],[&quot;#&quot;,&quot;-&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;-&quot;,&quot;*&quot;],[&quot;#&quot;,&quot;-&quot;]]</span></p>

<p><strong>Explanation:</strong></p>
Nothing happens because the bomb is not orthogonally adjacent to any box.</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[&quot;-&quot;,&quot;*&quot;,&quot;#&quot;],[&quot;#&quot;,&quot;-&quot;,&quot;-&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;-&quot;,&quot;*&quot;,&quot;-&quot;],[&quot;-&quot;,&quot;-&quot;,&quot;-&quot;]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The 1<sup>st</sup> second:</p>

<ul>
	<li>The bomb at <code>(0, 1)</code> is orthogonally adjacent to the box at <code>(0, 2)</code>. Therefore, the boxes at <code>(0, 2)</code> and <code>(1, 0)</code> become empty cells.</li>
</ul>

<p>Nothing happens afterwards.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[&quot;#&quot;,&quot;*&quot;,&quot;-&quot;,&quot;#&quot;],[&quot;-&quot;,&quot;-&quot;,&quot;*&quot;,&quot;-&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;-&quot;,&quot;*&quot;,&quot;-&quot;,&quot;-&quot;],[&quot;-&quot;,&quot;-&quot;,&quot;*&quot;,&quot;-&quot;]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The 1<sup>st</sup> second:</p>

<ul>
	<li>Since the bomb at <code>(0, 1)</code> is orthogonally adjacent to the box at <code>(0, 0)</code>, the box at <code>(0, 0)</code> becomes an empty cell. The other bomb at <code>(1, 2)</code> is not orthogonally adjacent to any box.</li>
	<li>The box at <code>(0, 3)</code> falls to <code>(1, 3)</code>.</li>
</ul>

<p>The 2<sup>nd</sup> second:</p>

<ul>
	<li>Since the bomb at <code>(1, 2)</code> is orthogonally adjacent to the box at <code>(1, 3)</code>, the box at <code>(1, 3)</code> becomes an empty cell. The other bomb at <code>(0, 1)</code> is not orthogonally adjacent to any box.</li>
</ul>

<p>Nothing happens afterwards.</p>
</div>

<p>&nbsp;</p>
<ul>
	<li><code>1 &lt;= m == grid.length &lt;= 100</code></li>
	<li><code>1 &lt;= n == grid[i].length &lt;= 100</code></li>
	<li><code>grid[i][j]</code> is <code>&#39;#&#39;</code>, <code>&#39;-&#39;</code>, or <code>&#39;*&#39;</code>.</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
