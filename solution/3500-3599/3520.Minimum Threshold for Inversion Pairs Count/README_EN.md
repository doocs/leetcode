---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3520.Minimum%20Threshold%20for%20Inversion%20Pairs%20Count/README_EN.md
tags:
    - Binary Indexed Tree
    - Segment Tree
    - Array
    - Binary Search
---

<!-- problem:start -->

# [3520. Minimum Threshold for Inversion Pairs Count 🔒](https://leetcode.com/problems/minimum-threshold-for-inversion-pairs-count)

[中文文档](/solution/3500-3599/3520.Minimum%20Threshold%20for%20Inversion%20Pairs%20Count/README.md)

## Description

<!-- description:start -->

<p>You are given an array of integers <code>nums</code> and an integer <code>k</code>.</p>

<p>An inversion pair with a <strong>threshold</strong> <code>x</code> is defined as a pair of indices <code>(i, j)</code> such that:</p>

<ul>
	<li><code>i &lt; j</code></li>
	<li><code>nums[i] &gt; nums[j]</code></li>
	<li>The difference between the two numbers is <strong>at most</strong> <code>x</code> (i.e. <code>nums[i] - nums[j] &lt;= x</code>).</li>
</ul>

<p>Your task is to determine the <strong>minimum</strong> integer <code>min_threshold</code> such that there are <strong>at least</strong> <code>k</code> inversion pairs with threshold <code>min_threshold</code>.</p>

<p>If no such integer exists, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,3,2,1], k = 7</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>For threshold <code>x = 2</code>, the pairs are:</p>

<ol>
	<li><code>(3, 4)</code> where <code>nums[3] == 4</code> and <code>nums[4] == 3</code>.</li>
	<li><code>(2, 5)</code> where <code>nums[2] == 3</code> and <code>nums[5] == 2</code>.</li>
	<li><code>(3, 5)</code> where <code>nums[3] == 4</code> and <code>nums[5] == 2</code>.</li>
	<li><code>(4, 5)</code> where <code>nums[4] == 3</code> and <code>nums[5] == 2</code>.</li>
	<li><code>(1, 6)</code> where <code>nums[1] == 2</code> and <code>nums[6] == 1</code>.</li>
	<li><code>(2, 6)</code> where <code>nums[2] == 3</code> and <code>nums[6] == 1</code>.</li>
	<li><code>(4, 6)</code> where <code>nums[4] == 3</code> and <code>nums[6] == 1</code>.</li>
	<li><code>(5, 6)</code> where <code>nums[5] == 2</code> and <code>nums[6] == 1</code>.</li>
</ol>

<p>There are less than <code>k</code> inversion pairs if we choose any integer less than 2 as threshold.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [10,9,9,9,1], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p>For threshold <code>x = 8</code>, the pairs are:</p>

<ol>
	<li><code>(0, 1)</code> where <code>nums[0] == 10</code> and <code>nums[1] == 9</code>.</li>
	<li><code>(0, 2)</code> where <code>nums[0] == 10</code> and <code>nums[2] == 9</code>.</li>
	<li><code>(0, 3)</code> where <code>nums[0] == 10</code> and <code>nums[3] == 9</code>.</li>
	<li><code>(1, 4)</code> where <code>nums[1] == 9</code> and <code>nums[4] == 1</code>.</li>
	<li><code>(2, 4)</code> where <code>nums[2] == 9</code> and <code>nums[4] == 1</code>.</li>
	<li><code>(3, 4)</code> where <code>nums[3] == 9</code> and <code>nums[4] == 1</code>.</li>
</ol>

<p>There are less than <code>k</code> inversion pairs if we choose any integer less than 8 as threshold.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
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
