---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3720.Lexicographically%20Smallest%20Permutation%20Greater%20Than%20Target/README_EN.md
rating: 1958
source: Weekly Contest 472 Q3
tags:
    - Greedy
    - Hash Table
    - String
    - Counting
    - Enumeration
---

<!-- problem:start -->

# [3720. Lexicographically Smallest Permutation Greater Than Target](https://leetcode.com/problems/lexicographically-smallest-permutation-greater-than-target)

[中文文档](/solution/3700-3799/3720.Lexicographically%20Smallest%20Permutation%20Greater%20Than%20Target/README.md)

## Description

<!-- description:start -->

<p>You are given two strings <code>s</code> and <code>target</code>, both having length <code>n</code>, consisting of lowercase English letters.</p>

<p>Return the <strong>lexicographically smallest <span data-keyword="permutation-string">permutation</span></strong> of <code>s</code> that is <strong>strictly</strong> greater than <code>target</code>. If no permutation of <code>s</code> is lexicographically strictly greater than <code>target</code>, return an empty string.</p>

<p>A string <code>a</code> is <strong>lexicographically strictly greater </strong>than a string <code>b</code> (of the same length) if in the first position where <code>a</code> and <code>b</code> differ, string <code>a</code> has a letter that appears later in the alphabet than the corresponding letter in <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;, target = &quot;bba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;bca&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The permutations of <code>s</code> (in lexicographical order) are <code>&quot;abc&quot;</code>, <code>&quot;acb&quot;</code>, <code>&quot;bac&quot;</code>, <code>&quot;bca&quot;</code>, <code>&quot;cab&quot;</code>, and <code>&quot;cba&quot;</code>.</li>
	<li>The lexicographically smallest permutation that is strictly greater than <code>target</code> is <code>&quot;bca&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;leet&quot;, target = &quot;code&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;eelt&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The permutations of <code>s</code> (in lexicographical order) are <code>&quot;eelt&quot;</code>, <code>&quot;eetl&quot;</code>, <code>&quot;elet&quot;</code>, <code>&quot;elte&quot;</code>, <code>&quot;etel&quot;</code>, <code>&quot;etle&quot;</code>, <code>&quot;leet&quot;</code>, <code>&quot;lete&quot;</code>, <code>&quot;ltee&quot;</code>, <code>&quot;teel&quot;</code>, <code>&quot;tele&quot;</code>, and <code>&quot;tlee&quot;</code>.</li>
	<li>The lexicographically smallest permutation that is strictly greater than <code>target</code> is <code>&quot;eelt&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;baba&quot;, target = &quot;bbaa&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The permutations of <code>s</code> (in lexicographical order) are <code>&quot;aabb&quot;</code>, <code>&quot;abab&quot;</code>, <code>&quot;abba&quot;</code>, <code>&quot;baab&quot;</code>, <code>&quot;baba&quot;</code>, and <code>&quot;bbaa&quot;</code>.</li>
	<li>None of them is lexicographically strictly greater than <code>target</code>. Therefore, the answer is <code>&quot;&quot;</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length == target.length &lt;= 300</code></li>
	<li><code>s</code> and <code>target</code> consist of only lowercase English letters.</li>
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
