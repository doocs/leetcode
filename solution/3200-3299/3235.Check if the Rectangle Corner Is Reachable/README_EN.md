---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3235.Check%20if%20the%20Rectangle%20Corner%20Is%20Reachable/README_EN.md
---

<!-- problem:start -->

# [3235. Check if the Rectangle Corner Is Reachable](https://leetcode.com/problems/check-if-the-rectangle-corner-is-reachable)

[中文文档](/solution/3200-3299/3235.Check%20if%20the%20Rectangle%20Corner%20Is%20Reachable/README.md)

## Description

<!-- description:start -->

<p>You are given two positive integers <code>X</code> and <code>Y</code>, and a 2D array <code>circles</code>, where <code>circles[i] = [x<sub>i</sub>, y<sub>i</sub>, r<sub>i</sub>]</code> denotes a circle with center at <code>(x<sub>i</sub>, y<sub>i</sub>)</code> and radius <code>r<sub>i</sub></code>.</p>

<p>There is a rectangle in the coordinate plane with its bottom left corner at the origin and top right corner at the coordinate <code>(X, Y)</code>. You need to check whether there is a path from the bottom left corner to the top right corner such that the <strong>entire path</strong> lies inside the rectangle, <strong>does not</strong> touch or lie inside <strong>any</strong> circle, and touches the rectangle <strong>only</strong> at the two corners.</p>

<p>Return <code>true</code> if such a path exists, and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">X = 3, Y = 4, circles = [[2,1,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3235.Check%20if%20the%20Rectangle%20Corner%20Is%20Reachable/images/example2circle1.png" style="width: 346px; height: 264px;" /></p>

<p>The black curve shows a possible path between <code>(0, 0)</code> and <code>(3, 4)</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">X = 3, Y = 3, circles = [[1,1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3235.Check%20if%20the%20Rectangle%20Corner%20Is%20Reachable/images/example1circle.png" style="width: 346px; height: 264px;" /></p>

<p>No path exists from <code>(0, 0)</code> to <code>(3, 3)</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">X = 3, Y = 3, circles = [[2,1,1],[1,2,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3235.Check%20if%20the%20Rectangle%20Corner%20Is%20Reachable/images/example0circle.png" style="width: 346px; height: 264px;" /></p>

<p>No path exists from <code>(0, 0)</code> to <code>(3, 3)</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= X, Y &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= circles.length &lt;= 1000</code></li>
	<li><code>circles[i].length == 3</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub>, r<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
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
