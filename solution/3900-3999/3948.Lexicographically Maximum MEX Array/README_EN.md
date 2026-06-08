---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3948.Lexicographically%20Maximum%20MEX%20Array/README_EN.md
rating: 2122
source: Weekly Contest 504 Q4
tags:
    - Greedy
    - Queue
    - Array
    - Hash Table
---

<!-- problem:start -->

# [3948. Lexicographically Maximum MEX Array](https://leetcode.com/problems/lexicographically-maximum-mex-array)

[中文文档](/solution/3900-3999/3948.Lexicographically%20Maximum%20MEX%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>You want to construct an array <code>result</code> by repeatedly performing the following operation until <code>nums</code> becomes empty:</p>

<ul>
	<li>Choose an integer <code>k</code> such that <code>1 &lt;= k &lt;= len(nums)</code>.</li>
	<li>Compute the <strong>MEX</strong> of the first <code>k</code> elements of <code>nums</code>.</li>
	<li>Append this <strong>MEX</strong> to <code>result</code>.</li>
	<li>Remove the first <code>k</code> elements from <code>nums</code>.</li>
</ul>

<p>Return the <strong>lexicographically maximum</strong> array <code>result</code> that can be obtained after performing the operations.</p>

<p>The <strong>MEX</strong> of an array is the <strong>smallest non-negative</strong> integer not present in the array.</p>

<p>An array <code>a</code> is <strong>lexicographically greater</strong> than an array <code>b</code> if in the first position where <code>a</code> and <code>b</code> differ, array <code>a</code> has an element that is greater than the corresponding element in <code>b</code>. If the first <code>min(a.length, b.length)</code> elements do not differ, then the longer array is the <strong>lexicographically greater</strong> one.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,1,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,1]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Take the first <code>k = 2</code> elements <code>[0, 1]</code> which has MEX = 2. Current <code>result = [2]</code>.</li>
	<li>Remaining array <code>[0]</code> has MEX = 1. Thus, the final <code>result = [2, 1]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,0,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">[3]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Take the first <code>k = 3</code> elements <code>[1, 0, 2]</code> which has MEX = 3.</li>
	<li><code><span class="example-io">nums</span></code> is now empty. Thus, the final <code>result = [3]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,0]</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Take <code>k = 1</code>, first element <code>[3]</code> has MEX = 0. Current <code>result = [0]</code>.</li>
	<li>Remaining array <code>[1]</code> has MEX = 0. Thus, the final <code>result = [0, 0]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
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
