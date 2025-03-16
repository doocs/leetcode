---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3445.Maximum%20Difference%20Between%20Even%20and%20Odd%20Frequency%20II/README_EN.md
rating: 2693
source: Weekly Contest 435 Q4
tags:
    - String
    - Enumeration
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [3445. Maximum Difference Between Even and Odd Frequency II](https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-ii)

[中文文档](/solution/3400-3499/3445.Maximum%20Difference%20Between%20Even%20and%20Odd%20Frequency%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and an integer <code>k</code>. Your task is to find the <strong>maximum</strong> difference between the frequency of <strong>two</strong> characters, <code>freq[a] - freq[b]</code>, in a <span data-keyword="substring">substring</span> <code>subs</code> of <code>s</code>, such that:</p>

<ul>
	<li><code>subs</code> has a size of <strong>at least</strong> <code>k</code>.</li>
	<li>Character <code>a</code> has an <em>odd frequency</em> in <code>subs</code>.</li>
	<li>Character <code>b</code> has an <em>even frequency</em> in <code>subs</code>.</li>
</ul>

<p>Return the <strong>maximum</strong> difference.</p>

<p><strong>Note</strong> that <code>subs</code> can contain more than 2 <strong>distinct</strong> characters.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;12233&quot;, k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>For the substring <code>&quot;12233&quot;</code>, the frequency of <code>&#39;1&#39;</code> is 1 and the frequency of <code>&#39;3&#39;</code> is 2. The difference is <code>1 - 2 = -1</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1122211&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>For the substring <code>&quot;11222&quot;</code>, the frequency of <code>&#39;2&#39;</code> is 3 and the frequency of <code>&#39;1&#39;</code> is 2. The difference is <code>3 - 2 = 1</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;110&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s</code> consists only of digits <code>&#39;0&#39;</code> to <code>&#39;4&#39;</code>.</li>
	<li>The input is generated that at least one substring has a character with an even frequency and a character with an odd frequency.</li>
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
