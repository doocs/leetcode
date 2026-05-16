---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3922.Minimum%20Flips%20to%20Make%20Binary%20String%20Coherent/README_EN.md
rating: 1759
source: Biweekly Contest 182 Q2
---

<!-- problem:start -->

# [3922. Minimum Flips to Make Binary String Coherent](https://leetcode.com/problems/minimum-flips-to-make-binary-string-coherent)

[中文文档](/solution/3900-3999/3922.Minimum%20Flips%20to%20Make%20Binary%20String%20Coherent/README.md)

## Description

<!-- description:start -->

<p>You are given a binary string <code>s</code>.</p>

<p>A string is considered <strong>coherent</strong> if it does <strong>not</strong> contain <code>&quot;011&quot;</code> or <code>&quot;110&quot;</code> as <span data-keyword="subsequence-string">subsequences</span>.</p>

<p>In one operation, you can <strong>flip</strong> any character in <code>s</code> (<code>&#39;0&#39;</code> to <code>&#39;1&#39;</code> or <code>&#39;1&#39;</code> to <code>&#39;0&#39;</code>).</p>

<p>Return an integer denoting the <strong>minimum</strong> number of modifications required to make <code>s</code> coherent.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1010&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Flip <code>s[0]</code> to get <code>&quot;0010&quot;</code>, which contains no <code>&quot;011&quot;</code> or <code>&quot;110&quot;</code> subsequences.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;0110&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Flip <code>s[1]</code> to get <code>&quot;0010&quot;</code>, removing all forbidden subsequences <code>&quot;011&quot;</code> and <code>&quot;110&quot;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1000&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The string already has no <code>&quot;011&quot;</code> or <code>&quot;110&quot;</code> subsequences, so no flips are needed.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
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
