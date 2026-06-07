---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3955.Valid%20Binary%20Strings%20With%20Cost%20Limit/README_EN.md
---

<!-- problem:start -->

# [3955. Valid Binary Strings With Cost Limit](https://leetcode.com/problems/valid-binary-strings-with-cost-limit)

[中文文档](/solution/3900-3999/3955.Valid%20Binary%20Strings%20With%20Cost%20Limit/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>n</code> and <code>k</code>.</p>

<p>The <strong>cost</strong> of a binary string <code>s</code> is defined as the sum of all indices <code>i</code> (0-based) such that <code>s[i] == &#39;1&#39;</code>.</p>

<p>A binary string is considered <strong>valid</strong> if:</p>

<ul>
	<li>It does not contain two consecutive <code>&#39;1&#39;</code> characters.</li>
	<li>Its cost is <strong>less than or equal</strong> to <code>k</code>.</li>
</ul>

<p>Return a list of all valid binary strings of length <code>n</code> in any order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;000&quot;,&quot;010&quot;,&quot;100&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<p>The binary strings of length 3 without consecutive <code>&#39;1&#39;</code> characters are:</p>

<ul>
	<li><code>&quot;000&quot;</code> : <code>cost = 0</code></li>
	<li>&quot;<code>100&quot;</code> : <code>cost = 0</code></li>
	<li><code>&quot;010&quot;</code> : <code>cost = 1</code></li>
	<li><code>&quot;001&quot;</code> : <code>cost = 2</code></li>
	<li><code>&quot;101&quot;</code> : <code>cost = 0 + 2 = 2</code></li>
</ul>

<p>Among these, the strings with cost less than or equal to <code>k = 1</code> are <code>&quot;000&quot;</code>, <code>&quot;010&quot;</code> and <code>&quot;100&quot;</code>.</p>

<p>Thus, the valid strings are <code>[&quot;000&quot;, &quot;010&quot;, &quot;100&quot;]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1, k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;0&quot;,&quot;1&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<p>The valid binary strings of length 1 are <code>&quot;0&quot;</code> and <code>&quot;1&quot;</code>.</p>

<p>Thus the answer is <code>[&quot;0&quot;, &quot;1&quot;]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 12</code></li>
	<li><code>0 &lt;= k &lt;= n * (n - 1) / 2</code></li>
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
