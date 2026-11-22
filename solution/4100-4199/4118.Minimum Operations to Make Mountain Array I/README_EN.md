---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4118. Minimum Operations to Make Mountain Array I](https://leetcode.com/problems/minimum-operations-to-make-mountain-array-i)

[中文文档](/solution/4100-4199/4118.Minimum%20Operations%20to%20Make%20Mountain%20Array%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>.</p>

<p>In one operation, you may <strong>increment</strong> or <strong>decrement</strong> any element of <code>nums</code> by 1.</p>

<p>All elements of <code>nums</code> must remain <strong>positive</strong> (<strong>at least</strong> 1) after every operation.</p>

<p>An array is called a <strong>mountain array</strong> if there exists an index <code>peak</code> such that <code>0 &lt; peak &lt; n - 1</code> and:</p>

<ul>
	<li><code>nums[0] &lt; nums[1] &lt; ... &lt; nums[peak]</code></li>
	<li><code>nums[peak] &gt; nums[peak + 1] &gt; ... &gt; nums[n - 1]</code></li>
</ul>

<p>Return the <strong>minimum</strong> number of operations required to transform <code>nums</code> into a mountain array. If it is impossible, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>​​​​​​​One optimal way is to:</p>

<ul>
	<li>Increment <code>nums[1]</code> by 1 and decrement <code>nums[2]</code> by 1.</li>
	<li>The resulting array is <code>[2, 3, 2]</code>, which is a mountain array with <code>peak = 1</code>.</li>
	<li>Therefore, the total number of operations is <code>1 + 1 = 2</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [9,4,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal way is to:</p>

<ul>
	<li>Increment <code>nums[1]</code> by 1 six times.</li>
	<li>The resulting array is <code>[9, 10, 9]</code>, which is a mountain array with <code>peak = 1</code>.</li>
	<li>Therefore, the total number of operations is 6.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,7,7,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal way is to:</p>

<ul>
	<li>Decrement <code>nums[1]</code> by 1.</li>
	<li>The resulting array is <code>[5, 6, 7, 4]</code>, which is a mountain array with <code>peak = 2</code>.</li>
	<li>Therefore, the total number of operations is 1.</li>
</ul>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>A mountain array must have a peak with at least one element on each side. Since <code>n = 2</code> and operations do not change the array&#39;s length, forming a mountain array is impossible.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
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
