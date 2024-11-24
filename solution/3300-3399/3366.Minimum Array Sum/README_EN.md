---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3366.Minimum%20Array%20Sum/README_EN.md
---

<!-- problem:start -->

# [3366. Minimum Array Sum](https://leetcode.com/problems/minimum-array-sum)

[中文文档](/solution/3300-3399/3366.Minimum%20Array%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and three integers <code>k</code>, <code>op1</code>, and <code>op2</code>.</p>

<p>You can perform the following operations on <code>nums</code>:</p>

<ul>
	<li><strong>Operation 1</strong>: Choose an index <code>i</code> and divide <code>nums[i]</code> by 2, <strong>rounding up</strong> to the nearest whole number. You can perform this operation at most <code>op1</code> times, and not more than <strong>once</strong> per index.</li>
	<li><strong>Operation 2</strong>: Choose an index <code>i</code> and subtract <code>k</code> from <code>nums[i]</code>, but only if <code>nums[i]</code> is greater than or equal to <code>k</code>. You can perform this operation at most <code>op2</code> times, and not more than <strong>once</strong> per index.</li>
</ul>

<p><strong>Note:</strong> Both operations can be applied to the same index, but at most once each.</p>

<p>Return the <strong>minimum</strong> possible <strong>sum</strong> of all elements in <code>nums</code> after performing any number of operations.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,8,3,19,3], k = 3, op1 = 1, op2 = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">23</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Apply Operation 2 to <code>nums[1] = 8</code>, making <code>nums[1] = 5</code>.</li>
	<li>Apply Operation 1 to <code>nums[3] = 19</code>, making <code>nums[3] = 10</code>.</li>
	<li>The resulting array becomes <code>[2, 5, 3, 10, 3]</code>, which has the minimum possible sum of 23 after applying the operations.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,3], k = 3, op1 = 2, op2 = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Apply Operation 1 to <code>nums[0] = 2</code>, making <code>nums[0] = 1</code>.</li>
	<li>Apply Operation 1 to <code>nums[1] = 4</code>, making <code>nums[1] = 2</code>.</li>
	<li>Apply Operation 2 to <code>nums[2] = 3</code>, making <code>nums[2] = 0</code>.</li>
	<li>The resulting array becomes <code>[1, 2, 0]</code>, which has the minimum possible sum of 3 after applying the operations.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code><font face="monospace">0 &lt;= nums[i] &lt;= 10<sup>5</sup></font></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= op1, op2 &lt;= nums.length</code></li>
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
