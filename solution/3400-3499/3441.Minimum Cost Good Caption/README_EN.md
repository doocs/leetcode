---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3441.Minimum%20Cost%20Good%20Caption/README_EN.md
rating: 2764
source: Biweekly Contest 149 Q4
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [3441. Minimum Cost Good Caption](https://leetcode.com/problems/minimum-cost-good-caption)

[中文文档](/solution/3400-3499/3441.Minimum%20Cost%20Good%20Caption/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>caption</code> of length <code>n</code>. A <strong>good</strong> caption is a string where <strong>every</strong> character appears in groups of <strong>at least 3</strong> consecutive occurrences.</p>

<p>For example:</p>

<ul>
	<li><code>&quot;aaabbb&quot;</code> and <code>&quot;aaaaccc&quot;</code> are <strong>good</strong> captions.</li>
	<li><code>&quot;aabbb&quot;</code> and <code>&quot;ccccd&quot;</code> are <strong>not</strong> good captions.</li>
</ul>

<p>You can perform the following operation <strong>any</strong> number of times:</p>

<p>Choose an index <code>i</code> (where <code>0 &lt;= i &lt; n</code>) and change the character at that index to either:</p>

<ul>
	<li>The character immediately <strong>before</strong> it in the alphabet (if <code>caption[i] != &#39;a&#39;</code>).</li>
	<li>The character immediately <strong>after</strong> it in the alphabet (if <code>caption[i] != &#39;z&#39;</code>).</li>
</ul>

<p>Your task is to convert the given <code>caption</code> into a <strong>good</strong> caption using the <strong>minimum</strong> number of operations, and return it. If there are <strong>multiple</strong> possible good captions, return the <strong><span data-keyword="lexicographically-smaller-string">lexicographically smallest</span></strong> one among them. If it is <strong>impossible</strong> to create a good caption, return an empty string <code>&quot;&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">caption = &quot;cdcd&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;cccc&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>It can be shown that the given caption cannot be transformed into a good caption with fewer than 2 operations. The possible good captions that can be created using exactly 2 operations are:</p>

<ul>
	<li><code>&quot;dddd&quot;</code>: Change <code>caption[0]</code> and <code>caption[2]</code> to their next character <code>&#39;d&#39;</code>.</li>
	<li><code>&quot;cccc&quot;</code>: Change <code>caption[1]</code> and <code>caption[3]</code> to their previous character <code>&#39;c&#39;</code>.</li>
</ul>

<p>Since <code>&quot;cccc&quot;</code> is lexicographically smaller than <code>&quot;dddd&quot;</code>, return <code>&quot;cccc&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">caption = &quot;aca&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;aaa&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>It can be proven that the given caption requires at least 2 operations to be transformed into a good caption. The only good caption that can be obtained with exactly 2 operations is as follows:</p>

<ul>
	<li>Operation 1: Change <code>caption[1]</code> to <code>&#39;b&#39;</code>. <code>caption = &quot;aba&quot;</code>.</li>
	<li>Operation 2: Change <code>caption[1]</code> to <code>&#39;a&#39;</code>. <code>caption = &quot;aaa&quot;</code>.</li>
</ul>

<p>Thus, return <code>&quot;aaa&quot;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">caption = &quot;bc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>It can be shown that the given caption cannot be converted to a good caption by using any number of operations.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= caption.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>caption</code> consists only of lowercase English letters.</li>
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
