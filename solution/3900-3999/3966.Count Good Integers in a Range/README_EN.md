---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3966.Count%20Good%20Integers%20in%20a%20Range/README_EN.md
rating: 2006
source: Biweekly Contest 185 Q4
---

<!-- problem:start -->

# [3966. Count Good Integers in a Range](https://leetcode.com/problems/count-good-integers-in-a-range)

[中文文档](/solution/3900-3999/3966.Count%20Good%20Integers%20in%20a%20Range/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code>l</code>, <code>r</code> and <code>k</code>.</p>

<p>A number is considered <strong>good</strong> if the <strong>absolute difference</strong> between every pair of <strong>adjacent</strong> digits is <strong>at most</strong> <code>k</code>.</p>

<p>Return the number of <strong>good</strong> integers in the range <code>[l, r]</code> (inclusive).</p>

<p>The <strong>absolute difference</strong> between values <code>x</code> and <code>y</code> is defined as <code>abs(x - y)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = 10, r = 15, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The good integers in the range are 10, 11, and 12.</li>
	<li>For 10, <code>abs(1 - 0) = 1</code>.</li>
	<li>For 11, <code>abs(1 - 1) = 0</code>.</li>
	<li>For 12, <code>abs(1 - 2) = 1</code>.</li>
	<li>All these differences are at most <code>k = 1</code>. Thus, the answer is 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = 201, r = 204, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The good integers in the range are 201 and 202.</li>
	<li>For 201, <code>abs(2 - 0) = 2</code> and <code>abs(0 - 1) = 1</code>.</li>
	<li>For 202, <code>abs(2 - 0) = 2</code> and <code>abs(0 - 2) = 2</code>.</li>
	<li>Thus, the answer is 2.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>10 &lt;= l &lt;= r &lt;= 10<sup>15</sup></code></li>
	<li><code>0 &lt;= k &lt;= 9</code></li>
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
