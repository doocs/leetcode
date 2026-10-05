---
comments: true
difficulty: 困难
---

<!-- problem:start -->

# [4073. Count Good Strings](https://leetcode.cn/problems/count-good-strings)

[English Version](/solution/4000-4099/4073.Count%20Good%20Strings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are given an integer <code>n</code>.</p>

<p>A string is considered <strong>good</strong> if it consists only of the characters <code>&#39;a&#39;</code> and <code>&#39;b&#39;</code>, and one of the following holds:</p>

<ul>
	<li>It contains exactly one <strong>distinct</strong> character, and its length is <strong>odd</strong>.</li>
	<li>It can be written as <code>s = s1 + s2</code>, where <code>s1</code> and <code>s2</code> are <strong>non-empty good</strong> strings, and the last character of <code>s1</code> is different from the first character of <code>s2</code>.</li>
</ul>

<p>Return the number of <strong>good</strong> strings of length <code>n</code>, modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>Here, <code>+</code> denotes string concatenation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The good strings are <code>&quot;aaab&quot;</code>, <code>&quot;abbb&quot;</code>, <code>&quot;baaa&quot;</code>, <code>&quot;bbba&quot;</code>, <code>&quot;abab&quot;</code>, and <code>&quot;baba&quot;</code>.</li>
	<li>For example, <code>&quot;aaab&quot; = &quot;aaa&quot; + &quot;b&quot;</code>. Both parts are good because each contains one distinct character and has odd length, and their characters at the boundary are different.</li>
	<li>Also, <code>&quot;ab&quot; = &quot;a&quot; + &quot;b&quot;</code> is good, so <code>&quot;abab&quot; = &quot;ab&quot; + &quot;ab&quot;</code> is good because the boundary characters are different.</li>
	<li>Thus, the answer is 6.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The good strings are <code>&quot;aaa&quot;</code>, <code>&quot;bbb&quot;</code>, <code>&quot;aba&quot;</code>, and <code>&quot;bab&quot;</code>. Thus, the answer is 4.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The good strings are <code>&quot;ab&quot;</code> and <code>&quot;ba&quot;</code>. Thus, the answer is 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
