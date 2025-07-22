---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3453.Separate%20Squares%20I/README_EN.md
rating: 1735
source: Biweekly Contest 150 Q2
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [3453. Separate Squares I](https://leetcode.com/problems/separate-squares-i)

[中文文档](/solution/3400-3499/3453.Separate%20Squares%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>squares</code>. Each <code>squares[i] = [x<sub>i</sub>, y<sub>i</sub>, l<sub>i</sub>]</code> represents the coordinates of the bottom-left point and the side length of a square parallel to the x-axis.</p>

<p>Find the <strong>minimum</strong> y-coordinate value of a horizontal line such that the total area of the squares above the line <em>equals</em> the total area of the squares below the line.</p>

<p>Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p><strong>Note</strong>: Squares <strong>may</strong> overlap. Overlapping areas should be counted <strong>multiple times</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">squares = [[0,0,1],[2,2,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1.00000</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3453.Separate%20Squares%20I/images/4062example1drawio.png" style="width: 378px; height: 352px;" /></p>

<p>Any horizontal line between <code>y = 1</code> and <code>y = 2</code> will have 1 square unit above it and 1 square unit below it. The lowest option is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">squares = [[0,0,2],[1,1,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1.16667</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3453.Separate%20Squares%20I/images/4062example2drawio.png" style="width: 378px; height: 352px;" /></p>

<p>The areas are:</p>

<ul>
	<li>Below the line: <code>7/6 * 2 (Red) + 1/6 (Blue) = 15/6 = 2.5</code>.</li>
	<li>Above the line: <code>5/6 * 2 (Red) + 5/6 (Blue) = 15/6 = 2.5</code>.</li>
</ul>

<p>Since the areas above and below the line are equal, the output is <code>7/6 = 1.16667</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= squares.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>squares[i] = [x<sub>i</sub>, y<sub>i</sub>, l<sub>i</sub>]</code></li>
	<li><code>squares[i].length == 3</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= l<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li>The total area of all the squares will not exceed <code>10<sup>12</sup></code>.</li>
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
