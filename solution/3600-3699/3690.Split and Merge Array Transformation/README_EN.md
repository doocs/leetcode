---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3690.Split%20and%20Merge%20Array%20Transformation/README_EN.md
---

<!-- problem:start -->

# [3690. Split and Merge Array Transformation](https://leetcode.com/problems/split-and-merge-array-transformation)

[中文文档](/solution/3600-3699/3690.Split%20and%20Merge%20Array%20Transformation/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>nums1</code> and <code>nums2</code>, each of length <code>n</code>. You may perform the following <strong>split-and-merge operation</strong> on <code>nums1</code> any number of times:</p>

<ol>
	<li>Choose a subarray <code>nums1[L..R]</code>.</li>
	<li>Remove that subarray, leaving the prefix <code>nums1[0..L-1]</code> (empty if <code>L = 0</code>) and the suffix <code>nums1[R+1..n-1]</code> (empty if <code>R = n - 1</code>).</li>
	<li>Re-insert the removed subarray (in its original order) at <strong>any</strong> position in the remaining array (i.e., between any two elements, at the very start, or at the very end).</li>
</ol>

<p>Return the <strong>minimum</strong> number of <strong>split-and-merge operations</strong> needed to transform <code>nums1</code> into <code>nums2</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [3,1,2], nums2 = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Split out the subarray <code>[3]</code> (<code>L = 0</code>, <code>R = 0</code>); the remaining array is <code>[1,2]</code>.</li>
	<li>Insert <code>[3]</code> at the end; the array becomes <code>[1,2,3]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = </span>[1,1,2,3,4,5]<span class="example-io">, nums2 = </span>[5,4,3,2,1,1]</p>

<p><strong>Output: </strong>3</p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Remove <code>[1,1,2]</code> at indices <code>0 - 2</code>; remaining is <code>[3,4,5]</code>; insert <code>[1,1,2]</code> at position <code>2</code>, resulting in <code>[3,4,1,1,2,5]</code>.</li>
	<li>Remove <code>[4,1,1]</code> at indices <code>1 - 3</code>; remaining is <code>[3,2,5]</code>; insert <code>[4,1,1]</code> at position <code>3</code>, resulting in <code>[3,2,5,4,1,1]</code>.</li>
	<li>Remove <code>[3,2]</code> at indices <code>0 - 1</code>; remaining is <code>[5,4,1,1]</code>; insert <code>[3,2]</code> at position <code>2</code>, resulting in <code>[5,4,3,2,1,1]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums1.length == nums2.length &lt;= 6</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums2</code> is a <strong>permutation</strong> of <code>nums1</code>.</li>
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
