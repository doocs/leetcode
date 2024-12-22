---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3395.Subsequences%20with%20a%20Unique%20Middle%20Mode%20I/README_EN.md
---

<!-- problem:start -->

# [3395. Subsequences with a Unique Middle Mode I](https://leetcode.com/problems/subsequences-with-a-unique-middle-mode-i)

[中文文档](/solution/3300-3399/3395.Subsequences%20with%20a%20Unique%20Middle%20Mode%20I/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code>, find the number of subsequences of size 5 of&nbsp;<code>nums</code> with a <strong>unique middle mode</strong>.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>mode</strong> of a sequence of numbers is defined as the element that appears the <strong>maximum</strong> number of times in the sequence.</p>

<p>A sequence of numbers contains a<strong> unique mode</strong> if it has only one mode.</p>

<p>A sequence of numbers <code>seq</code> of size 5 contains a <strong>unique middle mode</strong> if the <em>middle element</em> (<code>seq[2]</code>) is a <strong>unique mode</strong>.</p>

<p>A <strong>subsequence</strong> is a <b>non-empty</b> array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1,1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p><code>[1, 1, 1, 1, 1]</code> is the only subsequence of size 5 that can be formed, and it has a unique middle mode of 1. This subsequence can be formed in 6 different ways, so the output is 6.&nbsp;</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,2,3,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p><code>[1, 2, 2, 3, 4]</code> and <code>[1, 2, 3, 3, 4]</code>&nbsp;each have a unique middle mode because the number at index 2 has the greatest frequency in the subsequence. <code>[1, 2, 2, 3, 3]</code> does not have a unique middle mode because 2 and 3 appear twice.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,1,2,3,4,5,6,7,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no subsequence of length 5 with a unique middle mode.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>5 &lt;= nums.length &lt;= 1000</code></li>
	<li><code><font face="monospace">-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></font></code></li>
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
