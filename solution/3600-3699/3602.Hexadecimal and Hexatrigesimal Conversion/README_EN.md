---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3602.Hexadecimal%20and%20Hexatrigesimal%20Conversion/README_EN.md
---

<!-- problem:start -->

# [3602. Hexadecimal and Hexatrigesimal Conversion](https://leetcode.com/problems/hexadecimal-and-hexatrigesimal-conversion)

[中文文档](/solution/3600-3699/3602.Hexadecimal%20and%20Hexatrigesimal%20Conversion/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>.</p>

<p>Return the concatenation of the <strong>hexadecimal</strong> representation of <code>n<sup>2</sup></code> and the <strong>hexatrigesimal</strong> representation of <code>n<sup>3</sup></code>.</p>

<p>A <strong>hexadecimal</strong> number is defined as a base-16 numeral system that uses the digits <code>0 &ndash; 9</code> and the uppercase letters <code>A - F</code> to represent values from 0 to 15.</p>

<p>A <strong>hexatrigesimal</strong> number is defined as a base-36 numeral system that uses the digits <code>0 &ndash; 9</code> and the uppercase letters <code>A - Z</code> to represent values from 0 to 35.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 13</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;A91P1&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>n<sup>2</sup> = 13 * 13 = 169</code>. In hexadecimal, it converts to <code>(10 * 16) + 9 = 169</code>, which corresponds to <code>&quot;A9&quot;</code>.</li>
	<li><code>n<sup>3</sup> = 13 * 13 * 13 = 2197</code>. In hexatrigesimal, it converts to <code>(1 * 36<sup>2</sup>) + (25 * 36) + 1 = 2197</code>, which corresponds to <code>&quot;1P1&quot;</code>.</li>
	<li>Concatenating both results gives <code>&quot;A9&quot; + &quot;1P1&quot; = &quot;A91P1&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 36</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;5101000&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>n<sup>2</sup> = 36 * 36 = 1296</code>. In hexadecimal, it converts to <code>(5 * 16<sup>2</sup>) + (1 * 16) + 0 = 1296</code>, which corresponds to <code>&quot;510&quot;</code>.</li>
	<li><code>n<sup>3</sup> = 36 * 36 * 36 = 46656</code>. In hexatrigesimal, it converts to <code>(1 * 36<sup>3</sup>) + (0 * 36<sup>2</sup>) + (0 * 36) + 0 = 46656</code>, which corresponds to <code>&quot;1000&quot;</code>.</li>
	<li>Concatenating both results gives <code>&quot;510&quot; + &quot;1000&quot; = &quot;5101000&quot;</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
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
