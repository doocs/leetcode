---
comments: true
difficulty: Easy
---

<!-- problem:start -->

# [4097. Minimum Value of Merged Integers](https://leetcode.com/problems/minimum-value-of-merged-integers)

[中文文档](/solution/4000-4099/4097.Minimum%20Value%20of%20Merged%20Integers/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of even length <code>n</code>.</p>

<p>You have to <strong>merge</strong> the elements of <code>nums</code> in pairs by merging <code>nums[0]</code> with <code>nums[1]</code>, <code>nums[2]</code> with <code>nums[3]</code>, and so on, until <code>nums[n - 2]</code> with <code>nums[n - 1]</code>.</p>

<p>Return the <strong>minimum</strong> value among all the resulting merged integers.</p>

<p>To <strong>merge</strong> two integers, concatenate all the decimal digits of the first integer followed by all the decimal digits of the second integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [23,55,42,21]</span></p>

<p><strong>Output:</strong> <span class="example-io">2355</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Merging <code>nums[0] = 23</code> and <code>nums[1] = 55</code> gives 2355.</li>
	<li>Merging <code>nums[2] = 42</code> and <code>nums[3] = 21</code> gives 4221.</li>
	<li>The minimum value among the merged numbers is <code>min(2355, 4221) = 2355</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [34,7,11,11]</span></p>

<p><strong>Output:</strong> <span class="example-io">347</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Merging <code>nums[0] = 34</code> and <code>nums[1] = 7</code> gives 347.</li>
	<li>Merging <code>nums[2] = 11</code> and <code>nums[3] = 11</code> gives 1111.</li>
	<li>The minimum value among the merged numbers is <code>min(347, 1111) = 347</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [9,20]</span></p>

<p><strong>Output:</strong> <span class="example-io">920</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Merging <code>nums[0] = 9</code> and <code>nums[1] = 20</code> gives 920.</li>
	<li>There is only one merged number, so the minimum value is 920.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>n</code> is even</li>
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
