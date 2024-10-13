---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3321.Find%20X-Sum%20of%20All%20K-Long%20Subarrays%20II/README_EN.md
---

<!-- problem:start -->

# [3321. Find X-Sum of All K-Long Subarrays II](https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-ii)

[中文文档](/solution/3300-3399/3321.Find%20X-Sum%20of%20All%20K-Long%20Subarrays%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> of <code>n</code> integers and two integers <code>k</code> and <code>x</code>.</p>

<p>The <strong>x-sum</strong> of an array is calculated by the following procedure:</p>

<ul>
	<li>Count the occurrences of all elements in the array.</li>
	<li>Keep only the occurrences of the top <code>x</code> most frequent elements. If two elements have the same number of occurrences, the element with the <strong>bigger</strong> value is considered more frequent.</li>
	<li>Calculate the sum of the resulting array.</li>
</ul>

<p><strong>Note</strong> that if an array has less than <code>x</code> distinct elements, its <strong>x-sum</strong> is the sum of the array.</p>

<p>Return an integer array <code>answer</code> of length <code>n - k + 1</code> where <code>answer[i]</code> is the <strong>x-sum</strong> of the <span data-keyword="subarray-nonempty">subarray</span> <code>nums[i..i + k - 1]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2,2,3,4,2,3], k = 6, x = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[6,10,12]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For subarray <code>[1, 1, 2, 2, 3, 4]</code>, only elements 1 and 2 will be kept in the resulting array. Hence, <code>answer[0] = 1 + 1 + 2 + 2</code>.</li>
	<li>For subarray <code>[1, 2, 2, 3, 4, 2]</code>, only elements 2 and 4 will be kept in the resulting array. Hence, <code>answer[1] = 2 + 2 + 2 + 4</code>. Note that 4 is kept in the array since it is bigger than 3 and 1 which occur the same number of times.</li>
	<li>For subarray <code>[2, 2, 3, 4, 2, 3]</code>, only elements 2 and 3 are kept in the resulting array. Hence, <code>answer[2] = 2 + 2 + 2 + 3 + 3</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,8,7,8,7,5], k = 2, x = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[11,15,15,15,12]</span></p>

<p><strong>Explanation:</strong></p>

<p>Since <code>k == x</code>, <code>answer[i]</code> is equal to the sum of the subarray <code>nums[i..i + k - 1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= x &lt;= k &lt;= nums.length</code></li>
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
