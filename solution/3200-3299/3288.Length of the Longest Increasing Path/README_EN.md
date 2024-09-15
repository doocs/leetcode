---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3288.Length%20of%20the%20Longest%20Increasing%20Path/README_EN.md
---

<!-- problem:start -->

# [3288. Length of the Longest Increasing Path](https://leetcode.com/problems/length-of-the-longest-increasing-path)

[中文文档](/solution/3200-3299/3288.Length%20of%20the%20Longest%20Increasing%20Path/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D array of integers <code>coordinates</code> of length <code>n</code> and an integer <code>k</code>, where <code>0 &lt;= k &lt; n</code>.</p>

<p><code>coordinates[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> indicates the point <code>(x<sub>i</sub>, y<sub>i</sub>)</code> in a 2D plane.</p>

<p>An <strong>increasing path</strong> of length <code>m</code> is defined as a list of points <code>(x<sub>1</sub>, y<sub>1</sub>)</code>, <code>(x<sub>2</sub>, y<sub>2</sub>)</code>, <code>(x<sub>3</sub>, y<sub>3</sub>)</code>, ..., <code>(x<sub>m</sub>, y<sub>m</sub>)</code> such that:</p>

<ul>
	<li><code>x<sub>i</sub> &lt; x<sub>i + 1</sub></code> and <code>y<sub>i</sub> &lt; y<sub>i + 1</sub></code> for all <code>i</code> where <code>1 &lt;= i &lt; m</code>.</li>
	<li><code>(x<sub>i</sub>, y<sub>i</sub>)</code> is in the given coordinates for all <code>i</code> where <code>1 &lt;= i &lt;= m</code>.</li>
</ul>

<p>Return the <strong>maximum</strong> length of an <strong>increasing path</strong> that contains <code>coordinates[k]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">coordinates = [[3,1],[2,2],[4,1],[0,0],[5,3]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><code>(0, 0)</code>, <code>(2, 2)</code>, <code>(5, 3)</code><!-- notionvc: 082cee9e-4ce5-4ede-a09d-57001a72141d --> is the longest increasing path that contains <code>(2, 2)</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">coordinates = [[2,1],[7,0],[5,6]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><code>(2, 1)</code>, <code>(5, 6)</code> is the longest increasing path that contains <code>(5, 6)</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == coordinates.length &lt;= 10<sup>5</sup></code></li>
	<li><code>coordinates[i].length == 2</code></li>
	<li><code>0 &lt;= coordinates[i][0], coordinates[i][1] &lt;= 10<sup>9</sup></code></li>
	<li>All elements in <code>coordinates</code> are <strong>distinct</strong>.<!-- notionvc: 6e412fc2-f9dd-4ba2-b796-5e802a2b305a --><!-- notionvc: c2cf5618-fe99-4909-9b4c-e6b068be22a6 --></li>
	<li><code>0 &lt;= k &lt;= n - 1</code></li>
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
