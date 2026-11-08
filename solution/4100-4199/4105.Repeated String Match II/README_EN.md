---
comments: true
difficulty: Hard
---

<!-- problem:start -->

# [4105. Repeated String Match II](https://leetcode.com/problems/repeated-string-match-ii)

[中文文档](/solution/4100-4199/4105.Repeated%20String%20Match%20II/README.md)

## Description

<!-- description:start -->

<p>You are given two strings <code>a</code> and <code>b</code>​​​​​​​ consisting of lowercase English letters, and two integers <code>x</code> and <code>y</code>.</p>

<p>Return <code>true</code> if the string formed by repeating <code>b</code> <strong>exactly</strong> <code>y</code> times (<code>b * y</code>) is a <strong>substring</strong> of the string formed by repeating <code>a</code> <strong>exactly</strong> <code>x</code> times (<code>a * x</code>). Otherwise, return <code>false</code>.</p>
A <strong>substring</strong> is a contiguous <b>non-empty</b> sequence of characters within a string.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">a = &quot;ab&quot;, b = &quot;abab&quot;, x = 2, y = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>a * x = &quot;ab&quot; * 2 = &quot;abab&quot;</code></li>
	<li><code>b * y = &quot;abab&quot; * 1 = &quot;abab&quot;</code></li>
	<li>They are identical, so <code>b * y</code> is a substring of <code>a * x</code>. Thus, the answer is <code>true</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">a = &quot;abc&quot;, b = &quot;bc&quot;, x = 2, y = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>a * x = &quot;abc&quot; * 2 = &quot;abcabc&quot;</code></li>
	<li><code>b * y = &quot;bc&quot; * 3 = &quot;bcbcbc&quot;</code></li>
	<li>The answer is <code>false</code> as <code>b * y = &quot;bcbcbc&quot;</code> is not a substring of <code>a * x = &quot;abcabc&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">a = &quot;abcd&quot;, b = &quot;cdab&quot;, x = 2, y = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>a * x = &quot;abcd&quot; * 2 = &quot;abcdabcd&quot;</code></li>
	<li><code>b * y = &quot;cdab&quot;</code> appears in <code>a * x = &quot;abcdabcd&quot;</code> at indices <code>[2..5]</code>. Thus, the answer is <code>true</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a.length &lt;= 100</code></li>
	<li><code>1 &lt;= b.length &lt;= 100</code></li>
	<li><code>1 &lt;= x &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= y &lt;= 10<sup>9</sup></code>​​​​​​​</li>
	<li><code>a</code> and <code>b</code> consist of lowercase English letters.</li>
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
