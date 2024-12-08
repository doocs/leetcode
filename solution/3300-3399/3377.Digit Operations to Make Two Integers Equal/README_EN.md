---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3377.Digit%20Operations%20to%20Make%20Two%20Integers%20Equal/README_EN.md
---

<!-- problem:start -->

# [3377. Digit Operations to Make Two Integers Equal](https://leetcode.com/problems/digit-operations-to-make-two-integers-equal)

[中文文档](/solution/3300-3399/3377.Digit%20Operations%20to%20Make%20Two%20Integers%20Equal/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>n</code> and <code>m</code> that consist of the <strong>same</strong> number of digits.</p>

<p>You can perform the following operations <strong>any</strong> number of times:</p>

<ul>
	<li>Choose <strong>any</strong> digit from <code>n</code> that is not 9 and <strong>increase</strong> it by 1.</li>
	<li>Choose <strong>any</strong> digit from <code>n</code> that is not 0 and <strong>decrease</strong> it by 1.</li>
</ul>

<p>The integer <code>n</code> must not be a <strong>prime</strong> number at any point, including its original value and after each operation.</p>

<p>The cost of a transformation is the sum of <strong>all</strong> values that <code>n</code> takes throughout the operations performed.</p>

<p>Return the <strong>minimum</strong> cost to transform <code>n</code> into <code>m</code>. If it is impossible, return -1.</p>

<p>A prime number is a natural number greater than 1 with only two factors, 1 and itself.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 10, m = 12</span></p>

<p><strong>Output:</strong> <span class="example-io">85</span></p>

<p><strong>Explanation:</strong></p>

<p>We perform the following operations:</p>

<ul>
	<li>Increase the first digit, now <code>n = <u><strong>2</strong></u>0</code>.</li>
	<li>Increase the second digit, now <code>n = 2<strong><u>1</u></strong></code>.</li>
	<li>Increase the second digit, now <code>n = 2<strong><u>2</u></strong></code>.</li>
	<li>Decrease the first digit, now <code>n = <strong><u>1</u></strong>2</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, m = 8</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>It is impossible to make <code>n</code> equal to <code>m</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 6, m = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p>Since 2 is already a prime, we can&#39;t make <code>n</code> equal to <code>m</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, m &lt; 10<sup>4</sup></code></li>
	<li><code>n</code> and <code>m</code> consist of the same number of digits.</li>
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
