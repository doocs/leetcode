---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3426.Manhattan%20Distances%20of%20All%20Arrangements%20of%20Pieces/README_EN.md
rating: 2443
source: Biweekly Contest 148 Q4
tags:
    - Math
    - Combinatorics
---

<!-- problem:start -->

# [3426. Manhattan Distances of All Arrangements of Pieces](https://leetcode.com/problems/manhattan-distances-of-all-arrangements-of-pieces)

[中文文档](/solution/3400-3499/3426.Manhattan%20Distances%20of%20All%20Arrangements%20of%20Pieces/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code><font face="monospace">m</font></code>, <code><font face="monospace">n</font></code>, and <code>k</code>.</p>

<p>There is a rectangular grid of size <code>m &times; n</code> containing <code>k</code> identical pieces. Return the sum of Manhattan distances between every pair of pieces over all <strong>valid arrangements</strong> of pieces.</p>

<p>A <strong>valid arrangement</strong> is a placement of all <code>k</code> pieces on the grid with <strong>at most</strong> one piece per cell.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>The Manhattan Distance between two cells <code>(x<sub>i</sub>, y<sub>i</sub>)</code> and <code>(x<sub>j</sub>, y<sub>j</sub>)</code> is <code>|x<sub>i</sub> - x<sub>j</sub>| + |y<sub>i</sub> - y<sub>j</sub>|</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 2, n = 2, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p>The valid arrangements of pieces on the board are:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3426.Manhattan%20Distances%20of%20All%20Arrangements%20of%20Pieces/images/4040example1.drawio" /><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3426.Manhattan%20Distances%20of%20All%20Arrangements%20of%20Pieces/images/untitled-diagramdrawio.png" style="width: 441px; height: 204px;" /></p>

<ul>
	<li>In the first 4 arrangements, the Manhattan distance between the two pieces is 1.</li>
	<li>In the last 2 arrangements, the Manhattan distance between the two pieces is 2.</li>
</ul>

<p>Thus, the total Manhattan distance across all valid arrangements is <code>1 + 1 + 1 + 1 + 2 + 2 = 8</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 1, n = 4, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">20</span></p>

<p><strong>Explanation:</strong></p>

<p>The valid arrangements of pieces on the board are:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3426.Manhattan%20Distances%20of%20All%20Arrangements%20of%20Pieces/images/4040example2drawio.png" style="width: 762px; height: 41px;" /></p>

<ul>
	<li>The first and last arrangements have a total Manhattan distance of <code>1 + 1 + 2 = 4</code>.</li>
	<li>The middle two arrangements have a total Manhattan distance of <code>1 + 2 + 3 = 6</code>.</li>
</ul>

<p>The total Manhattan distance between all pairs of pieces across all arrangements is <code>4 + 6 + 6 + 4 = 20</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code><font face="monospace">2 &lt;= k &lt;= m * n</font></code></li>
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
