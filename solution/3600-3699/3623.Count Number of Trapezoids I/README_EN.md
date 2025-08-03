---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/README_EN.md
rating: 1579
source: Weekly Contest 459 Q2
tags:
    - Geometry
    - Array
    - Hash Table
    - Math
---

<!-- problem:start -->

# [3623. Count Number of Trapezoids I](https://leetcode.com/problems/count-number-of-trapezoids-i)

[中文文档](/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/README.md)

## Description

<!-- description:start -->

<p data-end="189" data-start="146">You are given a 2D integer array <code>points</code>, where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents the coordinates of the <code>i<sup>th</sup></code> point on the Cartesian plane.</p>

<p data-end="579" data-start="405">A <strong>horizontal</strong> <strong>trapezoid</strong> is a convex quadrilateral with <strong data-end="496" data-start="475">at least one pair</strong> of horizontal sides (i.e. parallel to the x-axis). Two lines are parallel if and only if they have the same slope.</p>

<p data-end="579" data-start="405">Return the <em data-end="330" data-start="297"> number of unique </em><strong><em>horizontal</em> <em>trapezoids</em></strong> that can be formed by choosing any four distinct points from <code>points</code>.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">points = [[1,0],[2,0],[3,0],[2,2],[3,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/images/desmos-graph-6.png" style="width: 250px; height: 250px;" /> <img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/images/desmos-graph-7.png" style="width: 250px; height: 250px;" /> <img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/images/desmos-graph-8.png" style="width: 250px; height: 250px;" /></p>

<p>There are three distinct ways to pick four points that form a horizontal trapezoid:</p>

<ul>
	<li data-end="247" data-start="193">Using points <code data-end="213" data-start="206">[1,0]</code>, <code data-end="222" data-start="215">[2,0]</code>, <code data-end="231" data-start="224">[3,2]</code>, and <code data-end="244" data-start="237">[2,2]</code>.</li>
	<li data-end="305" data-start="251">Using points <code data-end="271" data-start="264">[2,0]</code>, <code data-end="280" data-start="273">[3,0]</code>, <code data-end="289" data-start="282">[3,2]</code>, and <code data-end="302" data-start="295">[2,2]</code>.</li>
	<li data-end="361" data-start="309">Using points <code data-end="329" data-start="322">[1,0]</code>, <code data-end="338" data-start="331">[3,0]</code>, <code data-end="347" data-start="340">[3,2]</code>, and <code data-end="360" data-start="353">[2,2]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">points = [[0,0],[1,0],[0,1],[2,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3623.Count%20Number%20of%20Trapezoids%20I/images/desmos-graph-5.png" style="width: 250px; height: 250px;" /></p>

<p>There is only one horizontal trapezoid that can be formed.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>4 &lt;= points.length &lt;= 10<sup>5</sup></code></li>
	<li><code>&ndash;10<sup>8</sup> &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>8</sup></code></li>
	<li>All points are pairwise distinct.</li>
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
