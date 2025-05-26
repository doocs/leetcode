---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3524.Find%20X%20Value%20of%20Array%20I/README_EN.md
tags:
    - Array
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [3524. Find X Value of Array I](https://leetcode.com/problems/find-x-value-of-array-i)

[中文文档](/solution/3500-3599/3524.Find%20X%20Value%20of%20Array%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an array of <strong>positive</strong> integers <code>nums</code>, and a <strong>positive</strong> integer <code>k</code>.</p>

<p>You are allowed to perform an operation <strong>once</strong> on <code>nums</code>, where in each operation you can remove any <strong>non-overlapping</strong> prefix and suffix from <code>nums</code> such that <code>nums</code> remains <strong>non-empty</strong>.</p>

<p>You need to find the <strong>x-value</strong> of <code>nums</code>, which is the number of ways to perform this operation so that the <strong>product</strong> of the remaining elements leaves a <em>remainder</em> of <code>x</code> when divided by <code>k</code>.</p>

<p>Return an array <code>result</code> of size <code>k</code> where <code>result[x]</code> is the <strong>x-value</strong> of <code>nums</code> for <code>0 &lt;= x &lt;= k - 1</code>.</p>

<p>A <strong>prefix</strong> of an array is a <span data-keyword="subarray">subarray</span> that starts from the beginning of the array and extends to any point within it.</p>

<p>A <strong>suffix</strong> of an array is a <span data-keyword="subarray">subarray</span> that starts at any point within the array and extends to the end of the array.</p>

<p><strong>Note</strong> that the prefix and suffix to be chosen for the operation can be <strong>empty</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[9,2,4]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>x = 0</code>, the possible operations include all possible ways to remove non-overlapping prefix/suffix that do not remove <code>nums[2] == 3</code>.</li>
	<li>For <code>x = 1</code>, the possible operations are:
	<ul>
		<li>Remove the empty prefix and the suffix <code>[2, 3, 4, 5]</code>. <code>nums</code> becomes <code>[1]</code>.</li>
		<li>Remove the prefix <code>[1, 2, 3]</code> and the suffix <code>[5]</code>. <code>nums</code> becomes <code>[4]</code>.</li>
	</ul>
	</li>
	<li>For <code>x = 2</code>, the possible operations are:
	<ul>
		<li>Remove the empty prefix and the suffix <code>[3, 4, 5]</code>. <code>nums</code> becomes <code>[1, 2]</code>.</li>
		<li>Remove the prefix <code>[1]</code> and the suffix <code>[3, 4, 5]</code>. <code>nums</code> becomes <code>[2]</code>.</li>
		<li>Remove the prefix <code>[1, 2, 3]</code> and the empty suffix. <code>nums</code> becomes <code>[4, 5]</code>.</li>
		<li>Remove the prefix <code>[1, 2, 3, 4]</code> and the empty suffix. <code>nums</code> becomes <code>[5]</code>.</li>
	</ul>
	</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,4,8,16,32], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">[18,1,2,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>x = 0</code>, the only operations that <strong>do not</strong> result in <code>x = 0</code> are:

    <ul>
    	<li>Remove the empty prefix and the suffix <code>[4, 8, 16, 32]</code>. <code>nums</code> becomes <code>[1, 2]</code>.</li>
    	<li>Remove the empty prefix and the suffix <code>[2, 4, 8, 16, 32]</code>. <code>nums</code> becomes <code>[1]</code>.</li>
    	<li>Remove the prefix <code>[1]</code> and the suffix <code>[4, 8, 16, 32]</code>. <code>nums</code> becomes <code>[2]</code>.</li>
    </ul>
    </li>
    <li>For <code>x = 1</code>, the only possible operation is:
    <ul>
    	<li>Remove the empty prefix and the suffix <code>[2, 4, 8, 16, 32]</code>. <code>nums</code> becomes <code>[1]</code>.</li>
    </ul>
    </li>
    <li>For <code>x = 2</code>, the possible operations are:
    <ul>
    	<li>Remove the empty prefix and the suffix <code>[4, 8, 16, 32]</code>. <code>nums</code> becomes <code>[1, 2]</code>.</li>
    	<li>Remove the prefix <code>[1]</code> and the suffix <code>[4, 8, 16, 32]</code>. <code>nums</code> becomes <code>[2]</code>.</li>
    </ul>
    </li>
    <li>For <code>x = 3</code>, there is no possible way to perform the operation.</li>

</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2,1,1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[9,6]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 5</code></li>
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
