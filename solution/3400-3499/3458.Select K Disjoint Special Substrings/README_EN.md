---
comments: true
difficulty: Medium
rating: 2220
source: Weekly Contest 437 Q3
tags:
    - Greedy
    - Hash Table
    - String
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [3458. Select K Disjoint Special Substrings](https://leetcode.com/problems/select-k-disjoint-special-substrings)

[中文文档](/solution/3400-3499/3458.Select%20K%20Disjoint%20Special%20Substrings/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code> of length <code>n</code> and an integer <code>k</code>, determine whether it is possible to select <code>k</code> disjoint <strong>special substrings</strong>.</p>

<p>A <strong>special substring</strong> is a <span data-keyword="substring-nonempty">substring</span> where:</p>

<ul>
	<li>Any character present inside the substring should not appear outside it in the string.</li>
	<li>The substring is not the entire string <code>s</code>.</li>
</ul>

<p><strong>Note</strong> that all <code>k</code> substrings must be disjoint, meaning they cannot overlap.</p>

<p>Return <code>true</code> if it is possible to select <code>k</code> such disjoint special substrings; otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcdbaefab&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>We can select two disjoint special substrings: <code>&quot;cd&quot;</code> and <code>&quot;ef&quot;</code>.</li>
	<li><code>&quot;cd&quot;</code> contains the characters <code>&#39;c&#39;</code> and <code>&#39;d&#39;</code>, which do not appear elsewhere in <code>s</code>.</li>
	<li><code>&quot;ef&quot;</code> contains the characters <code>&#39;e&#39;</code> and <code>&#39;f&#39;</code>, which do not appear elsewhere in <code>s</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;cdefdc&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>There can be at most 2 disjoint special substrings: <code>&quot;e&quot;</code> and <code>&quot;f&quot;</code>. Since <code>k = 3</code>, the output is <code>false</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abeabe&quot;, k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= k &lt;= 26</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> A special substring is the span from a letter's first to last occurrence, and every letter inside must stay inside that span. $n\le 5\times 10^4$, $k\le 26$.
>
> Each letter yields at most one candidate interval. Selecting $k$ disjoint ones is an interval-graph independent set, solvable by sorting right ends.
>
> Compute $[\textit{first},\textit{last}]$ for $26$ letters, close each interval under contained letters, sort by right end, and greedily pick. Feasible if at least $k$ intervals are taken.

<!-- thinking:end -->

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
