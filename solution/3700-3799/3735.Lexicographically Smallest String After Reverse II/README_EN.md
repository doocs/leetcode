---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3735.Lexicographically%20Smallest%20String%20After%20Reverse%20II/README_EN.md
tags:
    - String
    - Binary Search
    - Suffix Array
    - Hash Function
    - Rolling Hash
---

<!-- problem:start -->

# [3735. Lexicographically Smallest String After Reverse II 🔒](https://leetcode.com/problems/lexicographically-smallest-string-after-reverse-ii)

[中文文档](/solution/3700-3799/3735.Lexicographically%20Smallest%20String%20After%20Reverse%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> of length <code>n</code> consisting of lowercase English letters.</p>

<p>You must perform <strong>exactly</strong> one operation by choosing any integer <code>k</code> such that <code>1 &lt;= k &lt;= n</code> and either:</p>

<ul>
	<li>reverse the <strong>first</strong> <code>k</code> characters of <code>s</code>, or</li>
	<li>reverse the <strong>last</strong> <code>k</code> characters of <code>s</code>.</li>
</ul>

<p>Return the <strong><span data-keyword="lexicographically-smaller-string">lexicographically smallest</span></strong> string that can be obtained after <strong>exactly</strong> one such operation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;dcab&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;acdb&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose <code>k = 3</code>, reverse the first 3 characters.</li>
	<li>Reverse <code>&quot;dca&quot;</code> to <code>&quot;acd&quot;</code>, resulting string <code>s = &quot;acdb&quot;</code>, which is the lexicographically smallest string achievable.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;aabb&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose <code>k = 3</code>, reverse the last 3 characters.</li>
	<li>Reverse <code>&quot;bba&quot;</code> to <code>&quot;abb&quot;</code>, so the resulting string is <code>&quot;aabb&quot;</code>, which is the lexicographically smallest string achievable.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;zxy&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;xzy&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose <code>k = 2</code>, reverse the first 2 characters.</li>
	<li>Reverse <code>&quot;zx&quot;</code> to <code>&quot;xz&quot;</code>, so the resulting string is <code>&quot;xzy&quot;</code>, which is the lexicographically smallest string achievable.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
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
