---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3576.Transform%20Array%20to%20All%20Equal%20Elements/README_EN.md
---

<!-- problem:start -->

# [3576. Transform Array to All Equal Elements](https://leetcode.com/problems/transform-array-to-all-equal-elements)

[中文文档](/solution/3500-3599/3576.Transform%20Array%20to%20All%20Equal%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of size <code>n</code> containing only <code>1</code> and <code>-1</code>, and an integer <code>k</code>.</p>

<p>You can perform the following operation at most <code>k</code> times:</p>

<ul>
	<li>
	<p>Choose an index <code>i</code> (<code>0 &lt;= i &lt; n - 1</code>), and <strong>multiply</strong> both <code>nums[i]</code> and <code>nums[i + 1]</code> by <code>-1</code>.</p>
	</li>
</ul>

<p><strong>Note</strong> that you can choose the same index <code data-end="459" data-start="456">i</code> more than once in <strong>different</strong> operations.</p>

<p>Return <code>true</code> if it is possible to make all elements of the array <strong>equal</strong> after at most <code>k</code> operations, and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,-1,1,-1,1], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>We can make all elements in the array equal in 2 operations as follows:</p>

<ul>
	<li>Choose index <code>i = 1</code>, and multiply both <code>nums[1]</code> and <code>nums[2]</code> by -1. Now <code>nums = [1,1,-1,-1,1]</code>.</li>
	<li>Choose index <code>i = 2</code>, and multiply both <code>nums[2]</code> and <code>nums[3]</code> by -1. Now <code>nums = [1,1,1,1,1]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-1,-1,-1,1,1,1], k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>It is not possible to make all array elements equal in at most 5 operations.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either -1 or 1.</li>
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
