---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3519.Count%20Numbers%20with%20Non-Decreasing%20Digits/README_EN.md
---

<!-- problem:start -->

# [3519. Count Numbers with Non-Decreasing Digits](https://leetcode.com/problems/count-numbers-with-non-decreasing-digits)

[中文文档](/solution/3500-3599/3519.Count%20Numbers%20with%20Non-Decreasing%20Digits/README.md)

## Description

<!-- description:start -->

<p>You are given two integers, <code>l</code> and <code>r</code>, represented as strings, and an integer <code>b</code>. Return the count of integers in the inclusive range <code>[l, r]</code> whose digits are in <strong>non-decreasing</strong> order when represented in base <code>b</code>.</p>

<p>An integer is considered to have <strong>non-decreasing</strong> digits if, when read from left to right (from the most significant digit to the least significant digit), each digit is greater than or equal to the previous one.</p>

<p>Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = &quot;23&quot;, r = &quot;28&quot;, b = 8</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The numbers from 23 to 28 in base 8 are: 27, 30, 31, 32, 33, and 34.</li>
	<li>Out of these, 27, 33, and 34 have non-decreasing digits. Hence, the output is 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = &quot;2&quot;, r = &quot;7&quot;, b = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The numbers from 2 to 7 in base 2 are: 10, 11, 100, 101, 110, and 111.</li>
	<li>Out of these, 11 and 111 have non-decreasing digits. Hence, the output is 2.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code><font face="monospace">1 &lt;= l.length &lt;= r.length &lt;= 100</font></code></li>
	<li><code>2 &lt;= b &lt;= 10</code></li>
	<li><code>l</code> and <code>r</code> consist only of digits.</li>
	<li>The value represented by <code>l</code> is less than or equal to the value represented by <code>r</code>.</li>
	<li><code>l</code> and <code>r</code> do not contain leading zeros.</li>
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
