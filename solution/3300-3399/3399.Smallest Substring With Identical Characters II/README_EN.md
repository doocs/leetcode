---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3399.Smallest%20Substring%20With%20Identical%20Characters%20II/README_EN.md
---

<!-- problem:start -->

# [3399. Smallest Substring With Identical Characters II](https://leetcode.com/problems/smallest-substring-with-identical-characters-ii)

[中文文档](/solution/3300-3399/3399.Smallest%20Substring%20With%20Identical%20Characters%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a binary string <code>s</code> of length <code>n</code> and an integer <code>numOps</code>.</p>

<p>You are allowed to perform the following operation on <code>s</code> <strong>at most</strong> <code>numOps</code> times:</p>

<ul>
	<li>Select any index <code>i</code> (where <code>0 &lt;= i &lt; n</code>) and <strong>flip</strong> <code>s[i]</code>. If <code>s[i] == &#39;1&#39;</code>, change <code>s[i]</code> to <code>&#39;0&#39;</code> and vice versa.</li>
</ul>

<p>You need to <strong>minimize</strong> the length of the <strong>longest</strong> substring of <code>s</code> such that all the characters in the substring are <strong>identical</strong>.</p>

<p>Return the <strong>minimum</strong> length after the operations.</p>

<p>A <strong>substring</strong> is a contiguous <b>non-empty</b> sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;000001&quot;, numOps = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p>By changing <code>s[2]</code> to <code>&#39;1&#39;</code>, <code>s</code> becomes <code>&quot;001001&quot;</code>. The longest substrings with identical characters are <code>s[0..1]</code> and <code>s[3..4]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;0000&quot;, numOps = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p>By changing <code>s[0]</code> and <code>s[2]</code> to <code>&#39;1&#39;</code>, <code>s</code> becomes <code>&quot;1010&quot;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;0101&quot;, numOps = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code>.</li>
	<li><code>0 &lt;= numOps &lt;= n</code></li>
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
