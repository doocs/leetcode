---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3574.Maximize%20Subarray%20GCD%20Score/README_EN.md
tags:
    - Array
    - Math
    - Enumeration
    - Number Theory
---

<!-- problem:start -->

# [3574. Maximize Subarray GCD Score](https://leetcode.com/problems/maximize-subarray-gcd-score)

[中文文档](/solution/3500-3599/3574.Maximize%20Subarray%20GCD%20Score/README.md)

## Description

<!-- description:start -->

<p>You are given an array of positive integers <code>nums</code> and an integer <code>k</code>.</p>

<p>You may perform at most <code>k</code> operations. In each operation, you can choose one element in the array and <strong>double</strong> its value. Each element can be doubled <strong>at most</strong> once.</p>

<p>The <strong>score</strong> of a contiguous <strong><span data-keyword="subarray">subarray</span></strong> is defined as the <strong>product</strong> of its length and the <em>greatest common divisor (GCD)</em> of all its elements.</p>

<p>Your task is to return the <strong>maximum</strong> <strong>score</strong> that can be achieved by selecting a contiguous subarray from the modified array.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>The <strong>greatest common divisor (GCD)</strong> of an array is the largest integer that evenly divides all the array elements.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Double <code>nums[0]</code> to 4 using one operation. The modified array becomes <code>[4, 4]</code>.</li>
	<li>The GCD of the subarray <code>[4, 4]</code> is 4, and the length is 2.</li>
	<li>Thus, the maximum possible score is <code>2 &times; 4 = 8</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,5,7], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Double <code>nums[2]</code> to 14 using one operation. The modified array becomes <code>[3, 5, 14]</code>.</li>
	<li>The GCD of the subarray <code>[14]</code> is 14, and the length is 1.</li>
	<li>Thus, the maximum possible score is <code>1 &times; 14 = 14</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,5,5], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The subarray <code>[5, 5, 5]</code> has a GCD of 5, and its length is 3.</li>
	<li>Since doubling any element doesn&#39;t improve the score, the maximum score is <code>3 &times; 5 = 15</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 1500</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
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
