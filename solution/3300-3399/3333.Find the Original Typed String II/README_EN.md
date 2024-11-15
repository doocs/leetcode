---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3333.Find%20the%20Original%20Typed%20String%20II/README_EN.md
rating: 2628
source: Biweekly Contest 142 Q4
tags:
    - String
    - Dynamic Programming
    - Prefix Sum
---

<!-- problem:start -->

# [3333. Find the Original Typed String II](https://leetcode.com/problems/find-the-original-typed-string-ii)

[中文文档](/solution/3300-3399/3333.Find%20the%20Original%20Typed%20String%20II/README.md)

## Description

<!-- description:start -->

<p>Alice is attempting to type a specific string on her computer. However, she tends to be clumsy and <strong>may</strong> press a key for too long, resulting in a character being typed <strong>multiple</strong> times.</p>

<p>You are given a string <code>word</code>, which represents the <strong>final</strong> output displayed on Alice&#39;s screen. You are also given a <strong>positive</strong> integer <code>k</code>.</p>

<p>Return the total number of <em>possible</em> original strings that Alice <em>might</em> have intended to type, if she was trying to type a string of size <strong>at least</strong> <code>k</code>.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;aabbccdd&quot;, k = 7</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The possible strings are: <code>&quot;aabbccdd&quot;</code>, <code>&quot;aabbccd&quot;</code>, <code>&quot;aabbcdd&quot;</code>, <code>&quot;aabccdd&quot;</code>, and <code>&quot;abbccdd&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;aabbccdd&quot;, k = 8</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The only possible string is <code>&quot;aabbccdd&quot;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;aaabbb&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>word</code> consists only of lowercase English letters.</li>
	<li><code>1 &lt;= k &lt;= 2000</code></li>
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
