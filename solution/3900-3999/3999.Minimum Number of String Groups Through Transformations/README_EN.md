---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3999.Minimum%20Number%20of%20String%20Groups%20Through%20Transformations/README_EN.md
---

<!-- problem:start -->

# [3999. Minimum Number of String Groups Through Transformations](https://leetcode.com/problems/minimum-number-of-string-groups-through-transformations)

[中文文档](/solution/3900-3999/3999.Minimum%20Number%20of%20String%20Groups%20Through%20Transformations/README.md)

## Description

<!-- description:start -->

<p>You are given an array of strings <code>words</code>.</p>

<p>Define a <strong>transformation</strong> on a string <code>s</code> as follows:</p>

<ul>
	<li>Let <code>E</code> be the <span data-keyword="subsequence-string">subsequence</span> of characters at even indices of <code>s</code>.</li>
	<li>Let <code>O</code> be the <strong>subsequence</strong> of characters at odd indices of <code>s</code>.</li>
	<li><strong>Independently</strong> cyclically shift <code>E</code> and <code>O</code> by <strong>any</strong> number of positions to the right, possibly zero.</li>
	<li>Reconstruct the string by placing the shifted <code>E</code> characters back into even indices and the shifted <code>O</code> characters back into odd indices.</li>
</ul>

<p>Two strings are <strong>equivalent</strong> if one can be transformed into the other by a <strong>single</strong> transformation.</p>

<p>Partition <code>words</code> into the <strong>minimum</strong> number of groups such that:</p>

<ul>
	<li>Every string belongs to <strong>exactly</strong> one group.</li>
	<li>Every pair of strings in the same group are <strong>equivalent</strong>.</li>
</ul>

<p>Return an integer denoting the <strong>minimum</strong> number of groups.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;ntgwz&quot;,&quot;zwntg&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>&quot;ntgwz&quot;</code>, the even-index subsequence is <code>&quot;ngz&quot;</code> and the odd-index subsequence is <code>&quot;tw&quot;</code>.</li>
	<li>Shift <code>&quot;ngz&quot;</code> right by <code>1</code> position to obtain <code>&quot;zng&quot;</code>, and shift <code>&quot;tw&quot;</code> right by <code>1</code> position to obtain <code>&quot;wt&quot;</code>.</li>
	<li>After reconstructing the string, we obtain <code>&quot;zwntg&quot;</code>.</li>
	<li>Therefore, both strings are equivalent and belong to the same group.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;abc&quot;,&quot;cab&quot;,&quot;bac&quot;,&quot;acb&quot;,&quot;bca&quot;,&quot;cba&quot;]</span></p>

<p><strong>Output:</strong> 3</p>

<p><strong>Explanation:</strong></p>

<p>The strings can be partitioned into the following groups:</p>

<ul>
	<li><code>[&quot;abc&quot;,&quot;cba&quot;]</code></li>
	<li><code>[&quot;cab&quot;,&quot;bac&quot;]</code></li>
	<li><code>[&quot;acb&quot;,&quot;bca&quot;]</code></li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;leet&quot;,&quot;abb&quot;,&quot;bab&quot;,&quot;deed&quot;,&quot;edde&quot;,&quot;code&quot;,&quot;bba&quot;]</span></p>

<p><strong>Output:</strong> 5</p>

<p><strong>Explanation:</strong></p>

<p>The strings can be partitioned into the following groups:</p>

<ul>
	<li><code>[&quot;abb&quot;,&quot;bba&quot;]</code></li>
	<li><code>[&quot;deed&quot;,&quot;edde&quot;]</code></li>
	<li><code>[&quot;leet&quot;]</code></li>
	<li><code>[&quot;bab&quot;]</code></li>
	<li><code>[&quot;code&quot;]</code></li>
</ul>

<p>​​​​​​​​​​​​​​All pairs of strings in each group are equivalent.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 5 * 10<sup>5</sup></code></li>
	<li>The sum of <code>words[i].length</code> does not exceed <code>5 * 10<sup>5</sup></code>.</li>
	<li><code>words[i]</code> consist of lowercase English letters.</li>
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
