---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3456.Find%20Special%20Substring%20of%20Length%20K/README_EN.md
---

<!-- problem:start -->

# [3456. Find Special Substring of Length K](https://leetcode.com/problems/find-special-substring-of-length-k)

[中文文档](/solution/3400-3499/3456.Find%20Special%20Substring%20of%20Length%20K/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and an integer <code>k</code>.</p>

<p>Determine if there exists a <span data-keyword="substring-nonempty">substring</span> of length <strong>exactly</strong> <code>k</code> in <code>s</code> that satisfies the following conditions:</p>

<ol>
	<li>The substring consists of <strong>only one distinct character</strong> (e.g., <code>&quot;aaa&quot;</code> or <code>&quot;bbb&quot;</code>).</li>
	<li>If there is a character <strong>immediately before</strong> the substring, it must be different from the character in the substring.</li>
	<li>If there is a character <strong>immediately after</strong> the substring, it must also be different from the character in the substring.</li>
</ol>

<p>Return <code>true</code> if such a substring exists. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aaabaaa&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>The substring <code>s[4..6] == &quot;aaa&quot;</code> satisfies the conditions.</p>

<ul>
	<li>It has a length of 3.</li>
	<li>All characters are the same.</li>
	<li>The character before <code>&quot;aaa&quot;</code> is <code>&#39;b&#39;</code>, which is different from <code>&#39;a&#39;</code>.</li>
	<li>There is no character after <code>&quot;aaa&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no substring of length 2 that consists of one distinct character and satisfies the conditions.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of lowercase English letters only.</li>
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
