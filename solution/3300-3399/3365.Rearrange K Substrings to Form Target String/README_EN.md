---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3365.Rearrange%20K%20Substrings%20to%20Form%20Target%20String/README_EN.md
---

<!-- problem:start -->

# [3365. Rearrange K Substrings to Form Target String](https://leetcode.com/problems/rearrange-k-substrings-to-form-target-string)

[中文文档](/solution/3300-3399/3365.Rearrange%20K%20Substrings%20to%20Form%20Target%20String/README.md)

## Description

<!-- description:start -->

<p>You are given two strings <code>s</code> and <code>t</code>, both of which are anagrams of each other, and an integer <code>k</code>.</p>

<p>Your task is to determine whether it is possible to split the string <code>s</code> into <code>k</code> equal-sized substrings, rearrange the substrings, and concatenate them in <em>any order</em> to create a new string that matches the given string <code>t</code>.</p>

<p>Return <code>true</code> if this is possible, otherwise, return <code>false</code>.</p>

<p>An <strong>anagram</strong> is a word or phrase formed by rearranging the letters of a different word or phrase, using all the original letters exactly once.</p>

<p>A <strong>substring</strong> is a contiguous <b>non-empty</b> sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcd&quot;, t = &quot;cdab&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Split <code>s</code> into 2 substrings of length 2: <code>[&quot;ab&quot;, &quot;cd&quot;]</code>.</li>
	<li>Rearranging these substrings as <code>[&quot;cd&quot;, &quot;ab&quot;]</code>, and then concatenating them results in <code>&quot;cdab&quot;</code>, which matches <code>t</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aabbcc&quot;, t = &quot;bbaacc&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Split <code>s</code> into 3 substrings of length 2: <code>[&quot;aa&quot;, &quot;bb&quot;, &quot;cc&quot;]</code>.</li>
	<li>Rearranging these substrings as <code>[&quot;bb&quot;, &quot;aa&quot;, &quot;cc&quot;]</code>, and then concatenating them results in <code>&quot;bbaacc&quot;</code>, which matches <code>t</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aabbcc&quot;, t = &quot;bbaacc&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Split <code>s</code> into 2 substrings of length 3: <code>[&quot;aab&quot;, &quot;bcc&quot;]</code>.</li>
	<li>These substrings cannot be rearranged to form <code>t = &quot;bbaacc&quot;</code>, so the output is <code>false</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length == t.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
	<li><code>s.length</code> is divisible by <code>k</code>.</li>
	<li><code>s</code> and <code>t</code> consist only of lowercase English letters.</li>
	<li>The input is generated such that<!-- notionvc: 53e485fc-71ce-4032-aed1-f712dd3822ba --> <code>s</code> and <code>t</code> are anagrams of each other.</li>
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
