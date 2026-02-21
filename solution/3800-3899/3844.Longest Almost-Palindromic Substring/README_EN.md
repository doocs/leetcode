---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3844.Longest%20Almost-Palindromic%20Substring/README_EN.md
rating: 1989
source: Weekly Contest 489 Q3
---

<!-- problem:start -->

# [3844. Longest Almost-Palindromic Substring](https://leetcode.com/problems/longest-almost-palindromic-substring)

[中文文档](/solution/3800-3899/3844.Longest%20Almost-Palindromic%20Substring/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters.</p>

<p>A <span data-keyword="substring-nonempty">substring</span> is <strong>almost-palindromic</strong> if it becomes a <span data-keyword="palindrome-string">palindrome</span> after removing <strong>exactly</strong> one character from it.</p>

<p>Return an integer denoting the length of the <strong>longest</strong> <strong>almost-palindromic</strong> substring in <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abca&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Choose the substring <code>&quot;<u><strong>abca</strong></u>&quot;</code>.</p>

<ul>
	<li>Remove <code>&quot;ab<u><strong>c</strong></u>a&quot;</code>.</li>
	<li>The string becomes <code>&quot;aba&quot;</code>, which is a palindrome.</li>
	<li>Therefore, <code>&quot;abca&quot;</code> is almost-palindromic.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Choose the substring <code>&quot;<u><strong>abba</strong></u>&quot;</code>.</p>

<ul>
	<li>Remove <code>&quot;a<u><strong>b</strong></u>ba&quot;</code>.</li>
	<li>The string becomes <code>&quot;aba&quot;</code>, which is a palindrome.</li>
	<li>Therefore, <code>&quot;abba&quot;</code> is almost-palindromic.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;zzabba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>Choose the substring <code>&quot;z<u><strong>zabba</strong></u>&quot;</code>.</p>

<ul>
	<li>Remove <code>&quot;<u><strong>z</strong></u>abba&quot;</code>.</li>
	<li>The string becomes <code>&quot;abba&quot;</code>, which is a palindrome.</li>
	<li>Therefore, <code>&quot;zabba&quot;</code> is almost-palindromic.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 2500</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
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
