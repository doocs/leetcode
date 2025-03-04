---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3464.Maximize%20the%20Distance%20Between%20Points%20on%20a%20Square/README_EN.md
tags:
    - Greedy
    - Array
    - Binary Search
---

<!-- problem:start -->

# [3464. Maximize the Distance Between Points on a Square](https://leetcode.com/problems/maximize-the-distance-between-points-on-a-square)

[中文文档](/solution/3400-3499/3464.Maximize%20the%20Distance%20Between%20Points%20on%20a%20Square/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code><font face="monospace">side</font></code>, representing the edge length of a square with corners at <code>(0, 0)</code>, <code>(0, side)</code>, <code>(side, 0)</code>, and <code>(side, side)</code> on a Cartesian plane.</p>

<p>You are also given a <strong>positive</strong> integer <code>k</code> and a 2D integer array <code>points</code>, where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents the coordinate of a point lying on the <strong>boundary</strong> of the square.</p>

<p>You need to select <code>k</code> elements among <code>points</code> such that the <strong>minimum</strong> Manhattan distance between any two points is <strong>maximized</strong>.</p>

<p>Return the <strong>maximum</strong> possible <strong>minimum</strong> Manhattan distance between the selected <code>k</code> points.</p>

<p>The Manhattan Distance between two cells <code>(x<sub>i</sub>, y<sub>i</sub>)</code> and <code>(x<sub>j</sub>, y<sub>j</sub>)</code> is <code>|x<sub>i</sub> - x<sub>j</sub>| + |y<sub>i</sub> - y<sub>j</sub>|</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">side = 2, points = [[0,2],[2,0],[2,2],[0,0]], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3464.Maximize%20the%20Distance%20Between%20Points%20on%20a%20Square/images/4080_example0_revised.png" style="width: 200px; height: 200px;" /></p>

<p>Select all four points.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">side = 2, points = [[0,0],[1,2],[2,0],[2,2],[2,1]], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3464.Maximize%20the%20Distance%20Between%20Points%20on%20a%20Square/images/4080_example1_revised.png" style="width: 211px; height: 200px;" /></p>

<p>Select the points <code>(0, 0)</code>, <code>(2, 0)</code>, <code>(2, 2)</code>, and <code>(2, 1)</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">side = 2, points = [[0,0],[0,1],[0,2],[1,2],[2,0],[2,2],[2,1]], k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3464.Maximize%20the%20Distance%20Between%20Points%20on%20a%20Square/images/4080_example2_revised.png" style="width: 200px; height: 200px;" /></p>

<p>Select the points <code>(0, 0)</code>, <code>(0, 1)</code>, <code>(0, 2)</code>, <code>(1, 2)</code>, and <code>(2, 2)</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= side &lt;= 10<sup>9</sup></code></li>
	<li><code>4 &lt;= points.length &lt;= min(4 * side, 15 * 10<sup>3</sup>)</code></li>
	<li><code>points[i] == [xi, yi]</code></li>
	<li>The input is generated such that:
	<ul>
		<li><code>points[i]</code> lies on the boundary of the square.</li>
		<li>All <code>points[i]</code> are <strong>unique</strong>.</li>
	</ul>
	</li>
	<li><code>4 &lt;= k &lt;= min(25, points.length)</code></li>
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
