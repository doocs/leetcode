---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3694.Distinct%20Points%20Reachable%20After%20Substring%20Removal/README_EN.md
rating: 1739
source: Biweekly Contest 166 Q3
---

<!-- problem:start -->

# [3694. Distinct Points Reachable After Substring Removal](https://leetcode.com/problems/distinct-points-reachable-after-substring-removal)

[中文文档](/solution/3600-3699/3694.Distinct%20Points%20Reachable%20After%20Substring%20Removal/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of characters <code>&#39;U&#39;</code>, <code>&#39;D&#39;</code>, <code>&#39;L&#39;</code>, and <code>&#39;R&#39;</code>, representing moves on an infinite 2D Cartesian grid.</p>

<ul>
	<li><code>&#39;U&#39;</code>: Move from <code>(x, y)</code> to <code>(x, y + 1)</code>.</li>
	<li><code>&#39;D&#39;</code>: Move from <code>(x, y)</code> to <code>(x, y - 1)</code>.</li>
	<li><code>&#39;L&#39;</code>: Move from <code>(x, y)</code> to <code>(x - 1, y)</code>.</li>
	<li><code>&#39;R&#39;</code>: Move from <code>(x, y)</code> to <code>(x + 1, y)</code>.</li>
</ul>

<p>You are also given a positive integer <code>k</code>.</p>

<p>You <strong>must</strong> choose and remove <strong>exactly one</strong> contiguous substring of length <code>k</code> from <code>s</code>. Then, start from coordinate <code>(0, 0)</code> and perform the remaining moves in order.</p>

<p>Return an integer denoting the number of <strong>distinct</strong> final coordinates reachable.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;LUL&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>After removing a substring of length 1, <code>s</code> can be <code>&quot;UL&quot;</code>, <code>&quot;LL&quot;</code> or <code>&quot;LU&quot;</code>. Following these moves, the final coordinates will be <code>(-1, 1)</code>, <code>(-2, 0)</code> and <code>(-1, 1)</code> respectively. There are two distinct points <code>(-1, 1)</code> and <code>(-2, 0)</code> so the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;UDLR&quot;, k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>After removing a substring of length 4, <code>s</code> can only be the empty string. The final coordinates will be <code>(0, 0)</code>. There is only one distinct point <code>(0, 0)</code> so the answer is 1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;UU&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>After removing a substring of length 1, <code>s</code> becomes <code>&quot;U&quot;</code>, which always ends at <code>(0, 1)</code>, so there is only one distinct final coordinate.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only <code>&#39;U&#39;</code>, <code>&#39;D&#39;</code>, <code>&#39;L&#39;</code>, and <code>&#39;R&#39;</code>.</li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
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
