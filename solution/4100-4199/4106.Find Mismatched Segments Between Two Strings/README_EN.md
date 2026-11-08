---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4106. Find Mismatched Segments Between Two Strings](https://leetcode.com/problems/find-mismatched-segments-between-two-strings)

[中文文档](/solution/4100-4199/4106.Find%20Mismatched%20Segments%20Between%20Two%20Strings/README.md)

## Description

<!-- description:start -->

<p>You are given two strings <code>s1</code> and <code>s2</code> of the same length.</p>

<p>A <strong>mismatched segment</strong> is a <strong>maximal contiguous</strong> range of indices where the characters of <code>s1</code> and <code>s2</code> differ at every index.</p>

<p>Return a 2D array containing <code>[sub1, sub2]</code> for each mismatched segment, where <code>sub1</code> and <code>sub2</code> are the corresponding <strong>substrings</strong> from <code>s1</code> and <code>s2</code>, respectively. Return the segments in <strong>increasing</strong> order of their starting indices. If there are no mismatched segments, return an empty array.</p>

<p>A <strong>substring</strong> is a contiguous <b>non-empty</b> sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s1 = &quot;earth&quot;, s2 = &quot;empty&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;ar&quot;,&quot;mp&quot;],[&quot;h&quot;,&quot;y&quot;]]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The characters at indices 1 and 2 differ, forming the mismatched segment <code>[&quot;ar&quot;,&quot;mp&quot;]</code>.</li>
	<li>The characters at index 4 differ, forming the mismatched segment <code>[&quot;h&quot;,&quot;y&quot;]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s1 = &quot;action&quot;, s2 = &quot;nation&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;ac&quot;,&quot;na&quot;]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The characters at indices 0 and 1 differ, forming the mismatched segment <code>[&quot;ac&quot;,&quot;na&quot;]</code>. All remaining characters are equal in both strings.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s1 = &quot;hot&quot;, s2 = &quot;hot&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>The two strings are identical, so there are no mismatched segments.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s1.length == s2.length &lt;= 100</code></li>
	<li><code>s1</code> and <code>s2</code> consist of lowercase English letters.</li>
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
