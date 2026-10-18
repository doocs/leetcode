---
comments: true
difficulty: Hard
rating: 2104
source: Weekly Contest 523 Q4
---

<!-- problem:start -->

# [4081. Prime Subset Selection II](https://leetcode.com/problems/prime-subset-selection-ii)

[中文文档](/solution/4000-4099/4081.Prime%20Subset%20Selection%20II/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>n</code> and <code>s</code>.</p>

<p>Consider all <strong><span data-keyword="prime-number">prime</span></strong> numbers less than or equal to <code>n</code>. Select a <strong><span data-keyword="subset">subset</span></strong> of these primes such that:</p>

<ul>
	<li>Each prime can be selected <strong>at most once</strong>.</li>
	<li>The <strong>sum</strong> of the selected primes is as <strong>large</strong> as possible while remaining <strong>less than or equal</strong> to <code>s</code>.</li>
	<li>Among subsets with the <strong>maximum</strong> sum, select one containing the <strong>fewest</strong> primes.</li>
	<li>If multiple subsets remain tied, select the one whose elements, written in increasing order, form the <strong><span data-keyword="lexicographically-smaller-array">lexicographically smallest</span></strong> array.</li>
</ul>

<p>Return an integer array containing the selected primes in <strong>increasing</strong> order. If no valid <strong>non-empty</strong> subset exists, return an empty array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 7, s = 20</span></p>

<p><strong>Output:</strong> <span class="example-io">[2, 3, 5, 7]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The prime numbers less than or equal to <code>n = 7</code> are 2, 3, 5, and 7.</li>
	<li>The sum of all these primes is <code>2 + 3 + 5 + 7 = 17</code>, which is less than or equal to <code>s = 20</code>.</li>
	<li>Thus, the answer is <code>[2, 3, 5, 7]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 15, s = 18</span></p>

<p><strong>Output:</strong> <span class="example-io">[5,13]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The prime numbers less than or equal to 15 are 2, 3, 5, 7, 11, and 13.</li>
	<li>The subsets with sum 18 are <code>[5, 13]</code>, <code>[7, 11]</code>, <code>[2, 3, 13]</code>, and <code>[2, 5, 11]</code>. Since this sum equals <code>s</code>, it is the maximum possible sum.</li>
	<li>Among these subsets, <code>[5, 13]</code> and <code>[7, 11]</code> contain the fewest primes: two.</li>
	<li>Between these two subsets, <code>[5, 13]</code> is lexicographically smaller because its first element, 5, is smaller than 7. Thus, the answer is <code>[5, 13]</code>.</li>
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
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= s &lt;= 1000</code></li>
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
