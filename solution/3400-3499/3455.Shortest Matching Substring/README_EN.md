---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3455.Shortest%20Matching%20Substring/README_EN.md
rating: 2303
source: Biweekly Contest 150 Q4
tags:
    - Two Pointers
    - String
    - Binary Search
    - String Matching
---

<!-- problem:start -->

# [3455. Shortest Matching Substring](https://leetcode.com/problems/shortest-matching-substring)

[中文文档](/solution/3400-3499/3455.Shortest%20Matching%20Substring/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and a pattern string <code>p</code>, where <code>p</code> contains <strong>exactly two</strong> <code>&#39;*&#39;</code> characters.</p>

<p>The <code>&#39;*&#39;</code> in <code>p</code> matches any sequence of zero or more characters.</p>

<p>Return the length of the <strong>shortest</strong> <span data-keyword="substring">substring</span> in <code>s</code> that matches <code>p</code>. If there is no such substring, return -1.</p>
<strong>Note:</strong> The empty substring is considered valid.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abaacbaecebce&quot;, p = &quot;ba*c*ce&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p>The shortest matching substring of <code>p</code> in <code>s</code> is <code>&quot;<u><strong>ba</strong></u>e<u><strong>c</strong></u>eb<u><strong>ce</strong></u>&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;baccbaadbc&quot;, p = &quot;cc*baa*adb&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no matching substring in <code>s</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;a&quot;, p = &quot;**&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The empty substring is the shortest matching substring.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;madlogic&quot;, p = &quot;*adlogi*&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>The shortest matching substring of <code>p</code> in <code>s</code> is <code>&quot;<strong><u>adlogi</u></strong>&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= p.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
	<li><code>p</code> contains only lowercase English letters and exactly two <code>&#39;*&#39;</code>.</li>
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
