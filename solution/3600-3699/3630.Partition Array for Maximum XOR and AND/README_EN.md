---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3630.Partition%20Array%20for%20Maximum%20XOR%20and%20AND/README_EN.md
rating: 2743
source: Weekly Contest 460 Q4
---

<!-- problem:start -->

# [3630. Partition Array for Maximum XOR and AND](https://leetcode.com/problems/partition-array-for-maximum-xor-and-and)

[中文文档](/solution/3600-3699/3630.Partition%20Array%20for%20Maximum%20XOR%20and%20AND/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Partition the array into <strong>three</strong> (possibly empty) <span data-keyword="subsequence-array">subsequences</span> <code>A</code>, <code>B</code>, and <code>C</code> such that every element of <code>nums</code> belongs to <strong>exactly</strong> one subsequence.</p>

<p>Your goal is to <strong>maximize</strong> the value of: <code>XOR(A) + AND(B) + XOR(C)</code></p>

<p>where:</p>

<ul>
	<li><code>XOR(arr)</code> denotes the bitwise XOR of all elements in <code>arr</code>. If <code>arr</code> is empty, its value is defined as 0.</li>
	<li><code>AND(arr)</code> denotes the bitwise AND of all elements in <code>arr</code>. If <code>arr</code> is empty, its value is defined as 0.</li>
</ul>

<p>Return the <strong>maximum</strong> value achievable.</p>

<p><strong>Note:</strong> If multiple partitions result in the same <strong>maximum</strong> sum, you can consider any one of them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal partition is:</p>

<ul>
	<li><code>A = [3], XOR(A) = 3</code></li>
	<li><code>B = [2], AND(B) = 2</code></li>
	<li><code>C = [], XOR(C) = 0</code></li>
</ul>

<p>The maximum value of: <code>XOR(A) + AND(B) + XOR(C) = 3 + 2 + 0 = 5</code>. Thus, the answer is 5.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal partition is:</p>

<ul>
	<li><code>A = [1], XOR(A) = 1</code></li>
	<li><code>B = [2], AND(B) = 2</code></li>
	<li><code>C = [3], XOR(C) = 3</code></li>
</ul>

<p>The maximum value of: <code>XOR(A) + AND(B) + XOR(C) = 1 + 2 + 3 = 6</code>. Thus, the answer is 6.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,6,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal partition is:</p>

<ul>
	<li><code>A = [7], XOR(A) = 7</code></li>
	<li><code>B = [2,3], AND(B) = 2</code></li>
	<li><code>C = [6], XOR(C) = 6</code></li>
</ul>

<p>The maximum value of: <code>XOR(A) + AND(B) + XOR(C) = 7 + 2 + 6 = 15</code>. Thus, the answer is 15.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 19</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
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
