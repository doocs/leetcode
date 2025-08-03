---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3605.Minimum%20Stability%20Factor%20of%20Array/README_EN.md
rating: 2409
source: Biweekly Contest 160 Q4
tags:
    - Greedy
    - Segment Tree
    - Array
    - Math
    - Binary Search
    - Number Theory
---

<!-- problem:start -->

# [3605. Minimum Stability Factor of Array](https://leetcode.com/problems/minimum-stability-factor-of-array)

[中文文档](/solution/3600-3699/3605.Minimum%20Stability%20Factor%20of%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>maxC</code>.</p>

<p>A <strong><span data-keyword="subarray">subarray</span></strong> is called <strong>stable</strong> if the <em>highest common factor (HCF)</em> of all its elements is <strong>greater than or equal to</strong> 2.</p>

<p>The <strong>stability factor</strong> of an array is defined as the length of its <strong>longest</strong> stable subarray.</p>

<p>You may modify <strong>at most</strong> <code>maxC</code> elements of the array to any integer.</p>

<p>Return the <strong>minimum</strong> possible stability factor of the array after at most <code>maxC</code> modifications. If no stable subarray remains, return 0.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>The <strong>highest common factor (HCF)</strong> of an array is the largest integer that evenly divides all the array elements.</li>
	<li>A <strong>subarray</strong> of length 1 is stable if its only element is greater than or equal to 2, since <code>HCF([x]) = x</code>.</li>
</ul>

<div class="notranslate" style="all: initial;"> </div>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,5,10], maxC = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The stable subarray <code>[5, 10]</code> has <code>HCF = 5</code>, which has a stability factor of 2.</li>
	<li>Since <code>maxC = 1</code>, one optimal strategy is to change <code>nums[1]</code> to <code>7</code>, resulting in <code>nums = [3, 7, 10]</code>.</li>
	<li>Now, no subarray of length greater than 1 has <code>HCF &gt;= 2</code>. Thus, the minimum possible stability factor is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,6,8], maxC = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The subarray <code>[2, 6, 8]</code> has <code>HCF = 2</code>, which has a stability factor of 3.</li>
	<li>Since <code>maxC = 2</code>, one optimal strategy is to change <code>nums[1]</code> to 3 and <code>nums[2]</code> to 5, resulting in <code>nums = [2, 3, 5]</code>.</li>
	<li>Now, no subarray of length greater than 1 has <code>HCF &gt;= 2</code>. Thus, the minimum possible stability factor is 1.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,9,6], maxC = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The stable subarrays are:
	<ul>
		<li><code>[2, 4]</code> with <code>HCF = 2</code> and stability factor of 2.</li>
		<li><code>[9, 6]</code> with <code>HCF = 3</code> and stability factor of 2.</li>
	</ul>
	</li>
	<li>Since <code>maxC = 1</code>, the stability factor of 2 cannot be reduced due to two separate stable subarrays. Thus, the minimum possible stability factor is 2.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= maxC &lt;= n</code></li>
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
