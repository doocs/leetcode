---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3953.Maximum%20Score%20with%20Co-Prime%20Element/README_EN.md
---

<!-- problem:start -->

# [3953. Maximum Score with Co-Prime Element](https://leetcode.com/problems/maximum-score-with-co-prime-element)

[中文文档](/solution/3900-3999/3953.Maximum%20Score%20with%20Co-Prime%20Element/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and an integer <code>maxVal</code>.</p>

<p>You <strong>may</strong> change any element in <code>nums</code> to any positive integer <strong>less than or equal</strong> to <code>maxVal</code>. Each such change costs 1.</p>

<p>Two integers are <strong>co-prime</strong> if their <span data-keyword="gcd-function"><strong>greatest common divisor (GCD)</strong></span> is 1.</p>

<p>After all modifications, you <strong>must</strong> choose an index <code>i</code> such that, <code>nums[i]</code> is <strong>co-prime</strong> with every other element <code>nums[j]</code>.</p>

<p>Let:</p>

<ul>
	<li><code>selectedValue</code> be the final value of <code>nums[i]</code> after modifications.</li>
	<li><code>modificationCost</code> be the total number of elements changed.</li>
</ul>

<p>The score is defined as <code>score = selectedValue - modificationCost</code>.</p>

<p>Return the <strong>maximum</strong> possible score.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,4,6], maxVal = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Change <code>nums[2]</code> from 6 to 5, which costs 1. Choose <code>nums[2] = 5</code>, since it is co-prime with 3 and 4.</p>

<ul>
	<li><code>selectedValue = 5</code></li>
	<li><code>modificationCost = 1</code></li>
	<li>The score is <code>5 - 1 = 4</code></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], maxVal = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>No modifications are required. Choose <code>nums[2] = 3</code>, since it is co-prime with 1 and 2.</p>

<ul>
	<li><code>selectedValue = 3</code></li>
	<li><code>modificationCost = 0</code></li>
	<li>The score is <code>3 - 0 = 3</code></li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2], maxVal = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Change <code>nums[0]</code> from 2 to 1, which costs 1. Choose <code>nums[1] = 2</code>, since it is co-prime with 1.</p>

<ul>
	<li><code>selectedValue = 2</code></li>
	<li><code>modificationCost = 1</code></li>
	<li>The score is ​​​​​​​<code>2 - 1 = 1</code></li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= maxVal &lt;= 10<sup>​​​​​​​5</sup></code></li>
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
