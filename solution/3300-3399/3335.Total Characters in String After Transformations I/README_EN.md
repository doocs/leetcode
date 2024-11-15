---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3335.Total%20Characters%20in%20String%20After%20Transformations%20I/README_EN.md
rating: 1806
source: Weekly Contest 421 Q2
tags:
    - Hash Table
    - Math
    - String
    - Dynamic Programming
    - Counting
---

<!-- problem:start -->

# [3335. Total Characters in String After Transformations I](https://leetcode.com/problems/total-characters-in-string-after-transformations-i)

[中文文档](/solution/3300-3399/3335.Total%20Characters%20in%20String%20After%20Transformations%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and an integer <code>t</code>, representing the number of <strong>transformations</strong> to perform. In one <strong>transformation</strong>, every character in <code>s</code> is replaced according to the following rules:</p>

<ul>
	<li>If the character is <code>&#39;z&#39;</code>, replace it with the string <code>&quot;ab&quot;</code>.</li>
	<li>Otherwise, replace it with the <strong>next</strong> character in the alphabet. For example, <code>&#39;a&#39;</code> is replaced with <code>&#39;b&#39;</code>, <code>&#39;b&#39;</code> is replaced with <code>&#39;c&#39;</code>, and so on.</li>
</ul>

<p>Return the <strong>length</strong> of the resulting string after <strong>exactly</strong> <code>t</code> transformations.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong><!-- notionvc: eb142f2b-b818-4064-8be5-e5a36b07557a --> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcyy&quot;, t = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>First Transformation (t = 1)</strong>:

    <ul>
    	<li><code>&#39;a&#39;</code> becomes <code>&#39;b&#39;</code></li>
    	<li><code>&#39;b&#39;</code> becomes <code>&#39;c&#39;</code></li>
    	<li><code>&#39;c&#39;</code> becomes <code>&#39;d&#39;</code></li>
    	<li><code>&#39;y&#39;</code> becomes <code>&#39;z&#39;</code></li>
    	<li><code>&#39;y&#39;</code> becomes <code>&#39;z&#39;</code></li>
    	<li>String after the first transformation: <code>&quot;bcdzz&quot;</code></li>
    </ul>
    </li>
    <li><strong>Second Transformation (t = 2)</strong>:
    <ul>
    	<li><code>&#39;b&#39;</code> becomes <code>&#39;c&#39;</code></li>
    	<li><code>&#39;c&#39;</code> becomes <code>&#39;d&#39;</code></li>
    	<li><code>&#39;d&#39;</code> becomes <code>&#39;e&#39;</code></li>
    	<li><code>&#39;z&#39;</code> becomes <code>&quot;ab&quot;</code></li>
    	<li><code>&#39;z&#39;</code> becomes <code>&quot;ab&quot;</code></li>
    	<li>String after the second transformation: <code>&quot;cdeabab&quot;</code></li>
    </ul>
    </li>
    <li><strong>Final Length of the string</strong>: The string is <code>&quot;cdeabab&quot;</code>, which has 7 characters.</li>

</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;azbk&quot;, t = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>First Transformation (t = 1)</strong>:

    <ul>
    	<li><code>&#39;a&#39;</code> becomes <code>&#39;b&#39;</code></li>
    	<li><code>&#39;z&#39;</code> becomes <code>&quot;ab&quot;</code></li>
    	<li><code>&#39;b&#39;</code> becomes <code>&#39;c&#39;</code></li>
    	<li><code>&#39;k&#39;</code> becomes <code>&#39;l&#39;</code></li>
    	<li>String after the first transformation: <code>&quot;babcl&quot;</code></li>
    </ul>
    </li>
    <li><strong>Final Length of the string</strong>: The string is <code>&quot;babcl&quot;</code>, which has 5 characters.</li>

</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
	<li><code>1 &lt;= t &lt;= 10<sup>5</sup></code></li>
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
