---
comments: true
difficulty: Hard
rating: 2386
source: Weekly Contest 436 Q3
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [3448. Count Substrings Divisible By Last Digit](https://leetcode.com/problems/count-substrings-divisible-by-last-digit)

[中文文档](/solution/3400-3499/3448.Count%20Substrings%20Divisible%20By%20Last%20Digit/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of digits.</p>

<p>Return the <strong>number</strong> of <span data-keyword="substring-nonempty">substrings</span> of <code>s</code> <strong>divisible</strong> by their <strong>non-zero</strong> last digit.</p>

<p><strong>Note</strong>: A substring may contain leading zeros.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;12936&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">11</span></p>

<p><strong>Explanation:</strong></p>

<p>Substrings <code>&quot;29&quot;</code>, <code>&quot;129&quot;</code>, <code>&quot;293&quot;</code> and <code>&quot;2936&quot;</code> are not divisible by their last digit. There are 15 substrings in total, so the answer is <code>15 - 4 = 11</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;5701283&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">18</span></p>

<p><strong>Explanation:</strong></p>

<p>Substrings <code>&quot;01&quot;</code>, <code>&quot;12&quot;</code>, <code>&quot;701&quot;</code>, <code>&quot;012&quot;</code>, <code>&quot;128&quot;</code>, <code>&quot;5701&quot;</code>, <code>&quot;7012&quot;</code>, <code>&quot;0128&quot;</code>, <code>&quot;57012&quot;</code>, <code>&quot;70128&quot;</code>, <code>&quot;570128&quot;</code>, and <code>&quot;701283&quot;</code> are all divisible by their last digit. Additionally, all substrings that are just 1 non-zero digit are divisible by themselves. Since there are 6 such digits, the answer is <code>12 + 6 = 18</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1010101010&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">25</span></p>

<p><strong>Explanation:</strong></p>

<p>Only substrings that end with digit <code>&#39;1&#39;</code> are divisible by their last digit. There are 25 such substrings.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of digits only.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> A substring, read as a decimal integer, must be divisible by its last digit. $n\le 10^5$ forbids testing every substring.
>
> The last digit $d$ lies in $[1,9]$. Divisibility is the value being $0$ modulo $d$. We keep counts of prefixes whose remainder is $r$ when the current digit is the last.
>
> If the prefix remainder is $p$, a start $l$ works when $p\equiv 10^{i-l+1}\cdot(\textit{prefix}_{l-1})\pmod d$. Grouping by the last digit yields a linear digit-DP / prefix count. A last digit $0$ contributes nothing.

<!-- thinking:end -->

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
