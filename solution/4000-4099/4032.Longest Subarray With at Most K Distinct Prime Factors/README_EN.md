---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4032.Longest%20Subarray%20With%20at%20Most%20K%20Distinct%20Prime%20Factors/README_EN.md
---

<!-- problem:start -->

# [4032. Longest Subarray With at Most K Distinct Prime Factors](https://leetcode.com/problems/longest-subarray-with-at-most-k-distinct-prime-factors)

[中文文档](/solution/4000-4099/4032.Longest%20Subarray%20With%20at%20Most%20K%20Distinct%20Prime%20Factors/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> consisting of positive integers and an integer <code>k</code>.</p>

<p>The <strong>prime factor set</strong> of a <span data-keyword="subarray-nonempty"><strong>subarray</strong></span> is the <strong>union</strong> of the distinct <span data-keyword="prime-number"><strong>prime</strong></span> factors of all its elements.</p>

<p>Return the length of the <strong>longest</strong> subarray whose prime factor set contains <strong>at most</strong> <code>k</code> distinct prime factors. If no such subarray exists, return 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,6,10,12,11], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Consider the subarray <code>[6, 10, 12]</code>:</p>

<ul>
	<li>The distinct prime factors of 6 are <code>{2, 3}</code>.</li>
	<li>The distinct prime factors of 10 are <code>{2, 5}</code>.</li>
	<li>The distinct prime factors of 12 are <code>{2, 3}</code>.</li>
	<li>The union of these sets is <code>{2, 3, 5}</code>, which contains 3 distinct prime factors.</li>
</ul>

<p>No longer subarray satisfies the condition. Therefore, the answer is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,6,9,18], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Consider the entire array <code>[4, 6, 9, 18]</code>:</p>

<ul>
	<li>The distinct prime factors of 4 are <code>{2}</code>.</li>
	<li>The distinct prime factors of 6 are <code>{2, 3}</code>.</li>
	<li>The distinct prime factors of 9 are <code>{3}</code>.</li>
	<li>The distinct prime factors of 18 are <code>{2, 3}</code>.</li>
	<li>The union of these sets is <code>{2, 3}</code>, which contains 2 distinct prime factors.</li>
</ul>

<p>Since <code>2 &lt;= 4</code>, the entire array is valid. Therefore, the answer is 4.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,10,15], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Every subarray of length at least 2 has prime factor set <code>{2, 3, 5}</code>, which contains 3 distinct prime factors.</p>

<p>Since <code>3 &gt; 2</code>, only subarrays of length 1 are valid. Therefore, the answer is 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
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
