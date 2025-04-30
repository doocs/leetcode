---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3518.Smallest%20Palindromic%20Rearrangement%20II/README_EN.md
rating: 2375
source: Weekly Contest 445 Q3
tags:
    - Hash Table
    - Math
    - String
    - Combinatorics
    - Counting
---

<!-- problem:start -->

# [3518. Smallest Palindromic Rearrangement II](https://leetcode.com/problems/smallest-palindromic-rearrangement-ii)

[中文文档](/solution/3500-3599/3518.Smallest%20Palindromic%20Rearrangement%20II/README.md)

## Description

<!-- description:start -->

<p data-end="332" data-start="99">You are given a <strong><span data-keyword="palindrome-string">palindromic</span></strong> string <code>s</code> and an integer <code>k</code>.</p>

<p>Return the <strong>k-th</strong> <strong><span data-keyword="lexicographically-smaller-string">lexicographically smallest</span></strong> palindromic <span data-keyword="permutation-string">permutation</span> of <code>s</code>. If there are fewer than <code>k</code> distinct palindromic permutations, return an empty string.</p>

<p><strong>Note:</strong> Different rearrangements that yield the same palindromic string are considered identical and are counted once.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abba&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;baab&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The two distinct palindromic rearrangements of <code>&quot;abba&quot;</code> are <code>&quot;abba&quot;</code> and <code>&quot;baab&quot;</code>.</li>
	<li>Lexicographically, <code>&quot;abba&quot;</code> comes before <code>&quot;baab&quot;</code>. Since <code>k = 2</code>, the output is <code>&quot;baab&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aa&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>There is only one palindromic rearrangement: <code data-end="1112" data-start="1106">&quot;aa&quot;</code>.</li>
	<li>The output is an empty string since <code>k = 2</code> exceeds the number of possible rearrangements.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;bacab&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;abcba&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The two distinct palindromic rearrangements of <code>&quot;bacab&quot;</code> are <code>&quot;abcba&quot;</code> and <code>&quot;bacab&quot;</code>.</li>
	<li>Lexicographically, <code>&quot;abcba&quot;</code> comes before <code>&quot;bacab&quot;</code>. Since <code>k = 1</code>, the output is <code>&quot;abcba&quot;</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>s</code> is guaranteed to be palindromic.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>6</sup></code></li>
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
