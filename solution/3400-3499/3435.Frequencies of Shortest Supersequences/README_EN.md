---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3435.Frequencies%20of%20Shortest%20Supersequences/README_EN.md
tags:
    - Bit Manipulation
    - Graph
    - Topological Sort
    - Array
    - String
    - Enumeration
---

<!-- problem:start -->

# [3435. Frequencies of Shortest Supersequences](https://leetcode.com/problems/frequencies-of-shortest-supersequences)

[中文文档](/solution/3400-3499/3435.Frequencies%20of%20Shortest%20Supersequences/README.md)

## Description

<!-- description:start -->

<p>You are given an array of strings <code>words</code>. Find all <strong>shortest common supersequences (SCS)</strong> of <code><font face="monospace">words</font></code> that are not <span data-keyword="permutation-string">permutations</span> of each other.</p>

<p>A <strong>shortest common supersequence</strong> is a string of <strong>minimum</strong> length that contains each string in <code>words</code> as a <span data-keyword="subsequence-string-nonempty">subsequence</span>.</p>

<p>Return a 2D array of integers <code>freqs</code> that represent all the SCSs. Each <code>freqs[i]</code> is an array of size 26, representing the frequency of each letter in the lowercase English alphabet for a single SCS. You may return the frequency arrays in any order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;ab&quot;,&quot;ba&quot;]</span></p>

<p><strong>Output: </strong>[[1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]]</p>

<p><strong>Explanation:</strong></p>

<p>The two SCSs are <code>&quot;aba&quot;</code> and <code>&quot;bab&quot;</code>. The output is the letter frequencies for each one.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;aa&quot;,&quot;ac&quot;]</span></p>

<p><strong>Output: </strong>[[2,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]]</p>

<p><strong>Explanation:</strong></p>

<p>The two SCSs are <code>&quot;aac&quot;</code> and <code>&quot;aca&quot;</code>. Since they are permutations of each other, keep only <code>&quot;aac&quot;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = </span>[&quot;aa&quot;,&quot;bb&quot;,&quot;cc&quot;]</p>

<p><strong>Output: </strong>[[2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]]</p>

<p><strong>Explanation:</strong></p>

<p><code>&quot;aabbcc&quot;</code> and all its permutations are SCSs.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 256</code></li>
	<li><code>words[i].length == 2</code></li>
	<li>All strings in <code>words</code> will altogether be composed of no more than 16 unique lowercase letters.</li>
	<li>All strings in <code>words</code> are unique.</li>
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
