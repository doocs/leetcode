---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3758.Convert%20Number%20Words%20to%20Digits/README_EN.md
---

<!-- problem:start -->

# [3758. Convert Number Words to Digits 🔒](https://leetcode.com/problems/convert-number-words-to-digits)

[中文文档](/solution/3700-3799/3758.Convert%20Number%20Words%20to%20Digits/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters. <code>s</code> may contain <strong>valid concatenated</strong> English words representing the digits 0 to 9, without spaces.</p>

<p>Your task is to <strong>extract</strong> each valid number word <strong>in order</strong> and convert it to its corresponding digit, producing a string of digits.</p>

<p>Parse <code>s</code> from left to right. At each position:</p>

<ul>
	<li>If a valid number word starts at the current position, append its corresponding digit to the result and advance by the length of that word.</li>
	<li>Otherwise, skip <strong>exactly</strong> one character and continue parsing.</li>
</ul>

<p>Return the resulting digit string. If no number words are found, return an empty string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;onefourthree&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;143&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Parsing from left to right, extract the valid number words &quot;one&quot;, &quot;four&quot;, &quot;three&quot;.</li>
	<li>These map to digits 1, 4, 3. Thus, the final result is <code>&quot;143&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;ninexsix&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;96&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The substring <code>&quot;nine&quot;</code> is a valid number word and maps to 9.</li>
	<li>The character <code>&quot;x&quot;</code> does not match any valid number word prefix and is skipped.</li>
	<li>Then, the substring <code>&quot;six&quot;</code> is a valid number word and maps to 6, so the final result is <code>&quot;96&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;zeero&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>No substring forms a valid number word during left-to-right parsing.</li>
	<li>All characters are skipped and incomplete fragments are ignored, so the result is an empty string.</li>
</ul>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;tw&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>No substring forms a valid number word during left-to-right parsing.</li>
	<li>All characters are skipped and incomplete fragments are ignored, so the result is an empty string.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
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
