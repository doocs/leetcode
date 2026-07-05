---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3980.Minimum%20Operations%20to%20Transform%20Binary%20String/README_EN.md
---

<!-- problem:start -->

# [3980. Minimum Operations to Transform Binary String](https://leetcode.com/problems/minimum-operations-to-transform-binary-string)

[中文文档](/solution/3900-3999/3980.Minimum%20Operations%20to%20Transform%20Binary%20String/README.md)

## Description

<!-- description:start -->

<p>You are given two <span data-keyword="binary-string">binary strings</span> <code>s1</code> and <code>s2</code> of the same length <code>n</code>.</p>

<p>You can perform the following operations on <code>s1</code> any number of times, in any order:</p>

<ul>
	<li>Choose an index <code>i</code> such that <code>s1[i] == &#39;0&#39;</code>, and change it to <code>&#39;1&#39;</code>.</li>
	<li>Choose an index <code>i</code> such that <code>0 &lt;= i &lt; n - 1</code>, and both <code>s1[i]</code> and <code>s1[i + 1]</code> are <code>&#39;1&#39;</code>. Change both characters to <code>&#39;0&#39;</code>.</li>
</ul>

<p>Return the <strong>minimum</strong> number of operations required to make <code>s1</code> equal to <code>s2</code>. If it is impossible, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s1 = &quot;11&quot;, s2 = &quot;00&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Change indices 0 and 1 from <code>&#39;1&#39;</code> to <code>&#39;0&#39;</code> in one operation, so <code>&quot;11&quot;</code> becomes <code>&quot;00&quot;</code>. Thus, the answer is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s1 = &quot;01&quot;, s2 = &quot;10&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Change index 0 from <code>&#39;0&#39;</code> to <code>&#39;1&#39;</code>, so <code>&quot;01&quot;</code> becomes <code>&quot;11&quot;</code>.</li>
	<li>Change indices 0 and 1 from <code>&#39;1&#39;</code> to <code>&#39;0&#39;</code>, so <code>&quot;11&quot;</code> becomes <code>&quot;00&quot;</code>.</li>
	<li>Change index 0 from <code>&#39;0&#39;</code> to <code>&#39;1&#39;</code>, so <code>&quot;00&quot;</code> becomes <code>&quot;10&quot;</code>.</li>
	<li>Thus, the answer is 3.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s1 = &quot;1&quot;, s2 = &quot;0&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>The first operation cannot change <code>&#39;1&#39;</code> to <code>&#39;0&#39;</code>, and the second operation requires two adjacent characters. Therefore, it is impossible.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == s1.length == s2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s1</code> and <code>s2</code> consist only of <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code>.</li>
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
