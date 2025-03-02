---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3472.Longest%20Palindromic%20Subsequence%20After%20at%20Most%20K%20Operations/README_EN.md
---

<!-- problem:start -->

# [3472. Longest Palindromic Subsequence After at Most K Operations](https://leetcode.com/problems/longest-palindromic-subsequence-after-at-most-k-operations)

[中文文档](/solution/3400-3499/3472.Longest%20Palindromic%20Subsequence%20After%20at%20Most%20K%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and an integer <code>k</code>.</p>

<p>In one operation, you can replace the character at any position with the next or previous letter in the alphabet (wrapping around so that <code>&#39;a&#39;</code> is after <code>&#39;z&#39;</code>). For example, replacing <code>&#39;a&#39;</code> with the next letter results in <code>&#39;b&#39;</code>, and replacing <code>&#39;a&#39;</code> with the previous letter results in <code>&#39;z&#39;</code>. Similarly, replacing <code>&#39;z&#39;</code> with the next letter results in <code>&#39;a&#39;</code>, and replacing <code>&#39;z&#39;</code> with the previous letter results in <code>&#39;y&#39;</code>.</p>

<p>Return the length of the <strong>longest <span data-keyword="palindrome-string">palindromic</span> <span data-keyword="subsequence-string-nonempty">subsequence</span></strong> of <code>s</code> that can be obtained after performing <strong>at most</strong> <code>k</code> operations.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abced&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Replace <code>s[1]</code> with the next letter, and <code>s</code> becomes <code>&quot;acced&quot;</code>.</li>
	<li>Replace <code>s[4]</code> with the previous letter, and <code>s</code> becomes <code>&quot;accec&quot;</code>.</li>
</ul>

<p>The subsequence <code>&quot;ccc&quot;</code> forms a palindrome of length 3, which is the maximum.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;</span>aaazzz<span class="example-io">&quot;, k = 4</span></p>

<p><strong>Output:</strong> 6</p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Replace <code>s[0]</code> with the previous letter, and <code>s</code> becomes <code>&quot;zaazzz&quot;</code>.</li>
	<li>Replace <code>s[4]</code> with the next letter, and <code>s</code> becomes <code>&quot;zaazaz&quot;</code>.</li>
	<li>Replace <code>s[3]</code> with the next letter, and <code>s</code> becomes <code>&quot;zaaaaz&quot;</code>.</li>
</ul>

<p>The entire string forms a palindrome of length 6.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 200</code></li>
	<li><code>1 &lt;= k &lt;= 200</code></li>
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
