---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3952.Maximum%20Total%20Value%20of%20Covered%20Indices/README_EN.md
rating: 1762
source: Biweekly Contest 184 Q3
tags:
    - Greedy
    - Array
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [3952. Maximum Total Value of Covered Indices](https://leetcode.com/problems/maximum-total-value-of-covered-indices)

[中文文档](/solution/3900-3999/3952.Maximum%20Total%20Value%20of%20Covered%20Indices/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and a binary string <code>s</code> of length <code>n</code>, where <code>s[i] == &#39;1&#39;</code> means index <code>i</code> initially contains a <strong>token</strong> and <code>s[i] == &#39;0&#39;</code> means it does not.</p>

<p>You may perform the following operation any number of times:</p>

<ul>
	<li>Choose a token currently located at index <code>i</code>, where <code>i &gt; 0</code>, such that this token has <strong>not</strong> been moved before.</li>
	<li>Move this token from index <code>i</code> to index <code>i - 1</code>.</li>
</ul>

<p>An index is considered <strong>covered</strong> if it contains a token after all moves.</p>

<p>Return an integer denoting the <strong>maximum total value</strong> of <code>nums</code> at the covered indices after optimally performing the operations.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [9,2,6,1], s = &quot;0101&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Initially, indices 1 and 3 contain tokens.</li>
	<li>Move the token from index 3 to index 2.</li>
	<li>Move the token from index 1 to index 0.</li>
	<li>The covered indices are <code>[0, 2]</code>, so the total value is <code>nums[0] + nums[2] = 9 + 6 = 15</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,1,4], s = &quot;001&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Initially, only index 2 contains a token.</li>
	<li>It is optimal to leave the token at index 2.</li>
	<li>The covered index is <code>[2]</code>, so the total value is <code>nums[2] = 4</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [9,3,5], s = &quot;011&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Initially, indices 1 and 2 contain tokens.</li>
	<li>Move the token from index 1 to index 0.</li>
	<li>The covered indices are <code>[0, 2]</code>, so the total value is <code>nums[0] + nums[2] = 9 + 5 = 14</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length == s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li>​​​​​​​<code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code></li>
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
