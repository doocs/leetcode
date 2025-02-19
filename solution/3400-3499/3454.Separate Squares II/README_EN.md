---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3454.Separate%20Squares%20II/README_EN.md
tags:
    - Segment Tree
    - Array
    - Binary Search
    - Line Sweep
---

<!-- problem:start -->

# [3454. Separate Squares II](https://leetcode.com/problems/separate-squares-ii)

[中文文档](/solution/3400-3499/3454.Separate%20Squares%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>squares</code>. Each <code>squares[i] = [x<sub>i</sub>, y<sub>i</sub>, l<sub>i</sub>]</code> represents the coordinates of the bottom-left point and the side length of a square parallel to the x-axis.</p>

<p>Find the <strong>minimum</strong> y-coordinate value of a horizontal line such that the total area covered by squares above the line <em>equals</em> the total area covered by squares below the line.</p>

<p>Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p><strong>Note</strong>: Squares <strong>may</strong> overlap. Overlapping areas should be counted <strong>only once</strong> in this version.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">squares = [[0,0,1],[2,2,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1.00000</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3454.Separate%20Squares%20II/images/4065example1drawio.png" style="width: 269px; height: 203px;" /></p>

<p>Any horizontal line between <code>y = 1</code> and <code>y = 2</code> results in an equal split, with 1 square unit above and 1 square unit below. The minimum y-value is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">squares = [[0,0,2],[1,1,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1.00000</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3454.Separate%20Squares%20II/images/4065example2drawio.png" style="width: 269px; height: 203px;" /></p>

<p>Since the blue square overlaps with the red square, it will not be counted again. Thus, the line <code>y = 1</code> splits the squares into two equal parts.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= squares.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>squares[i] = [x<sub>i</sub>, y<sub>i</sub>, l<sub>i</sub>]</code></li>
	<li><code>squares[i].length == 3</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= l<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li>The total area of all the squares will not exceed <code>10<sup>15</sup></code>.</li>
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
