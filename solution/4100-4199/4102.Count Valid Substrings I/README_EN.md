---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4102. Count Valid Substrings I](https://leetcode.com/problems/count-valid-substrings-i)

[中文文档](/solution/4100-4199/4102.Count%20Valid%20Substrings%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting only of the characters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code>.</p>

<p>A string is called <strong>valid</strong> if it does not contain <code>&quot;abc&quot;</code> as a <strong>substring</strong>.</p>

<p>Return the number of <strong>valid substrings</strong> in <code>s</code>. Substrings with different starting or ending indices are counted separately.</p>

<p>A <strong>substring</strong> is a contiguous <b>non-empty</b> sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abac&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>The following substrings don&#39;t contain <code>&quot;abc&quot;</code>​​​​​​​ as a substring:</p>

<ul>
	<li><code>s[0..0] = &quot;a&quot;</code></li>
	<li><code>s[0..1] = &quot;ab&quot;</code></li>
	<li><code>s[0..2] = &quot;aba&quot;</code></li>
	<li><code>s[0..3] = &quot;abac&quot;</code>​​​​​​​</li>
	<li><code>s[1..1] = &quot;b&quot;</code></li>
	<li><code>s[1..2] = &quot;ba&quot;</code></li>
	<li><code>s[1..3] = &quot;bac&quot;</code></li>
	<li><code>s[2..2] = &quot;a&quot;</code></li>
	<li><code>s[2..3] = &quot;ac&quot;</code></li>
	<li><code>s[3..3] = &quot;c&quot;</code></li>
</ul>

<p>Thus, the answer is 10.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The following substrings don&#39;t contain <code>&quot;abc&quot;</code>​​​​​​​ as a substring:</p>

<ul>
	<li><code>s[0..0] = &quot;a&quot;</code></li>
	<li><code>s[0..1] = &quot;ab&quot;</code></li>
	<li><code>s[1..1] = &quot;b&quot;</code></li>
	<li><code>s[1..2] = &quot;bc&quot;</code></li>
	<li><code>s[2..2] = &quot;c&quot;</code>​​​​​​​</li>
</ul>

<p>Thus, the answer is 5.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;ac&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>All substrings in <code>s</code> don&#39;t contain <code>&quot;abc&quot;</code> as a substring​​​​​​​. Thus, the answer is 3.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code>​​​​​​​</li>
	<li><code>s</code> only consists of <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code> characters.</li>
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
