---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4079. Prime Subset Selection I](https://leetcode.com/problems/prime-subset-selection-i)

[中文文档](/solution/4000-4099/4079.Prime%20Subset%20Selection%20I/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>n</code> and <code>s</code>.</p>

<p>Consider all <strong><span data-keyword="prime-number">prime</span></strong> numbers less than or equal to <code>n</code>. Select some of these primes such that:</p>

<ul>
	<li>The <strong>sum</strong> of the selected primes is less than or equal to <code>s</code>.</li>
	<li>The number of selected primes is <strong>maximized</strong>.</li>
	<li>Each prime can be selected <strong>at most once</strong>.</li>
</ul>

<p>Return an integer array containing the selected primes in <strong>increasing</strong> order. If multiple optimal selections exist, return <strong>any</strong> of them.</p>

<p>If no prime can be selected, return an <strong>empty</strong> array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 7, s = 20</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,3,5,7]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The prime numbers less than or equal to <code>n = 7</code> are 2, 3, 5, and 7.</li>
	<li>The sum of all these primes is <code>2 + 3 + 5 + 7 = 17</code>, which is less than or equal to <code>s = 20</code>.</li>
	<li>Thus, the answer is <code>[2, 3, 5, 7]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 15, s = 15</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,5,7]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The prime numbers less than or equal to <code>n = 15</code> are 2, 3, 5, 7, 11, and 13.</li>
	<li>One possible selection is <code>[3, 5, 7]</code>, whose sum is <code>3 + 5 + 7 = 15</code>.</li>
	<li>The four smallest available primes have sum <code>2 + 3 + 5 + 7 = 17</code>, which exceeds <code>s = 15</code>. Therefore, selecting four or more primes is impossible, and the maximum number of selected primes is three.</li>
	<li>Thus, <code>[3, 5, 7]</code> is one possible answer. Other valid answers include <code>[2, 3, 5]</code>, <code>[2, 3, 7]</code>, and <code>[2, 5, 7]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, s = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The smallest prime number is 2, which is greater than <code>s = 1</code>.</li>
	<li>Thus, no prime can be selected, so the answer is an empty array.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= s &lt;= 10<sup>9</sup></code></li>
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
