---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3959.Check%20Good%20Integer/README_EN.md
---

<!-- problem:start -->

# [3959. Check Good Integer](https://leetcode.com/problems/check-good-integer)

[中文文档](/solution/3900-3999/3959.Check%20Good%20Integer/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer <code>n</code>.</p>

<p>Let <code>digitSum</code> be the sum of the digits of <code>n</code>, and let <code>squareSum</code> be the sum of the squares of the digits of <code>n</code>.</p>

<p>An integer is called <strong>good</strong> if <code>squareSum - digitSum &gt;= 50</code>.</p>

<p>Return <code>true</code> if <code>n</code> is good. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1000</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The digits of 1000 are 1, 0, 0, and 0.</li>
	<li>The <code>digitSum</code> is <code>1 + 0 + 0 + 0 = 1</code>.</li>
	<li>The <code>squareSum</code> is <code>1<sup>2</sup> + 0<sup>2</sup> + 0<sup>2</sup> + 0<sup>2</sup> = 1</code>.</li>
	<li>The <code>squareSum - digitSum</code> is <code>1 - 1 = 0</code>. As 0 is not greater than or equal to 50, the output is <code>false</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 19</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The digits of 19 are 1 and 9.</li>
	<li>The <code>digitSum</code> is <code>1 + 9 = 10</code>.</li>
	<li>The <code>squareSum</code> is <code>1<sup>2</sup> + 9<sup>2</sup> = 1 + 81 = 82</code>.</li>
	<li>The <code>squareSum - digitSum</code> is <code>82 - 10 = 72</code>. As 72 is greater than or equal to 50, the output is <code>true</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
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
