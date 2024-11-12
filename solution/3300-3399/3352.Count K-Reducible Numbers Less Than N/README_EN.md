---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3352.Count%20K-Reducible%20Numbers%20Less%20Than%20N/README_EN.md
tags:
    - Math
    - String
    - Dynamic Programming
    - Combinatorics
---

<!-- problem:start -->

# [3352. Count K-Reducible Numbers Less Than N](https://leetcode.com/problems/count-k-reducible-numbers-less-than-n)

[中文文档](/solution/3300-3399/3352.Count%20K-Reducible%20Numbers%20Less%20Than%20N/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>binary</strong> string <code>s</code> representing a number <code>n</code> in its binary form.</p>

<p>You are also given an integer <code>k</code>.</p>

<p>An integer <code>x</code> is called <strong>k-reducible</strong> if performing the following operation <strong>at most</strong> <code>k</code> times reduces it to 1:</p>

<ul>
	<li>Replace <code>x</code> with the <strong>count</strong> of <span data-keyword="set-bit">set bits</span> in its binary representation.</li>
</ul>

<p>For example, the binary representation of 6 is <code>&quot;110&quot;</code>. Applying the operation once reduces it to 2 (since <code>&quot;110&quot;</code> has two set bits). Applying the operation again to 2 (binary <code>&quot;10&quot;</code>) reduces it to 1 (since <code>&quot;10&quot;</code> has one set bit).</p>

<p>Return an integer denoting the number of positive integers <strong>less</strong> than <code>n</code> that are <strong>k-reducible</strong>.</p>

<p>Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;111&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation: </strong></p>

<p><code>n = 7</code>. The 1-reducible integers less than 7 are 1, 2, and 4.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1000&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p><code>n = 8</code>. The 2-reducible integers less than 8 are 1, 2, 3, 4, 5, and 6.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no positive integers less than <code>n = 1</code>, so the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 800</code></li>
	<li><code>s</code> has no leading zeros.</li>
	<li><code>s</code> consists only of the characters <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code>.</li>
	<li><code>1 &lt;= k &lt;= 5</code></li>
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
