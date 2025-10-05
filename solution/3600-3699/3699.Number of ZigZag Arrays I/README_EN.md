---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3699.Number%20of%20ZigZag%20Arrays%20I/README_EN.md
rating: 2123
source: Weekly Contest 469 Q3
---

<!-- problem:start -->

# [3699. Number of ZigZag Arrays I](https://leetcode.com/problems/number-of-zigzag-arrays-i)

[中文文档](/solution/3600-3699/3699.Number%20of%20ZigZag%20Arrays%20I/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code>n</code>, <code>l</code>, and <code>r</code>.</p>

<p>A <strong>ZigZag</strong> array of length <code>n</code> is defined as follows:</p>

<ul>
	<li>Each element lies in the range <code>[l, r]</code>.</li>
	<li>No <strong>two</strong> adjacent elements are equal.</li>
	<li>No <strong>three</strong> consecutive elements form a <strong>strictly increasing</strong> or <strong>strictly decreasing</strong> sequence.</li>
</ul>

<p>Return the total number of valid <strong>ZigZag</strong> arrays.</p>

<p>Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>sequence</strong> is said to be <strong>strictly increasing</strong> if each element is strictly greater than its previous one (if exists).</p>

<p>A <strong>sequence</strong> is said to be <strong>strictly decreasing</strong> if each element is strictly smaller than its previous one (if exists).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, l = 4, r = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>There are only 2 valid ZigZag arrays of length <code>n = 3</code> using values in the range <code>[4, 5]</code>:</p>

<ul>
	<li><code>[4, 5, 4]</code></li>
	<li><code>[5, 4, 5]</code>​​​​​​​</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, l = 1, r = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 10 valid ZigZag arrays of length <code>n = 3</code> using values in the range <code>[1, 3]</code>:</p>

<ul>
	<li><code>[1, 2, 1]</code>, <code>[1, 3, 1]</code>, <code>[1, 3, 2]</code></li>
	<li><code>[2, 1, 2]</code>, <code>[2, 1, 3]</code>, <code>[2, 3, 1]</code>, <code>[2, 3, 2]</code></li>
	<li><code>[3, 1, 2]</code>, <code>[3, 1, 3]</code>, <code>[3, 2, 3]</code></li>
</ul>

<p>All arrays meet the ZigZag conditions.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 2000</code></li>
	<li><code>1 &lt;= l &lt; r &lt;= 2000</code></li>
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
