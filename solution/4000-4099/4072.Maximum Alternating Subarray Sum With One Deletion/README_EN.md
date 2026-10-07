---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4072. Maximum Alternating Subarray Sum With One Deletion](https://leetcode.com/problems/maximum-alternating-subarray-sum-with-one-deletion)

[中文文档](/solution/4000-4099/4072.Maximum%20Alternating%20Subarray%20Sum%20With%20One%20Deletion/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>You may delete <strong>at most one</strong> element from <code>nums</code>, then choose a <strong><span data-keyword="subarray-nonempty">subarray</span></strong> of the resulting array.</p>

<p>Return the <strong>maximum</strong> possible <strong>alternating sum</strong> of the chosen subarray.</p>

<p>The <strong>alternating sum</strong> of an array is the sum of its elements at even indices minus the sum of its elements at odd indices. The chosen subarray is <strong>reindexed starting from 0</strong> before calculating its alternating sum.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,-5,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">11</span></p>

<p><strong>Explanation:</strong></p>

<p>Choose not to delete an element and select the entire array. Its alternating sum is <code>5 - (-5) + 1 = 11</code>, which is the maximum possible.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [10,-5,-100]</span></p>

<p><strong>Output:</strong> <span class="example-io">110</span></p>

<p><strong>Explanation:</strong></p>

<p>Delete <code>nums[1] = -5</code> to obtain <code>[10,-100]</code>, then select the entire resulting array. Its alternating sum is <code>10 - (-100) = 110</code>, which is the maximum possible.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>Choose not to delete an element and select the subarray <code>[7]</code>. Its alternating sum is 7, which is the maximum possible.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
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
