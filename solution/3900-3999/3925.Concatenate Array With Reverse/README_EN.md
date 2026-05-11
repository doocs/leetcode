---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3925.Concatenate%20Array%20With%20Reverse/README_EN.md
---

<!-- problem:start -->

# [3925. Concatenate Array With Reverse](https://leetcode.com/problems/concatenate-array-with-reverse)

[中文文档](/solution/3900-3999/3925.Concatenate%20Array%20With%20Reverse/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>.</p>

<p>Construct a new array <code>ans</code> of length <code>2 * n</code> such that the first <code>n</code> elements are the same as <code>nums</code>, and the next <code>n</code> elements are the elements of <code>nums</code> in reverse order.</p>

<p>Formally, for <code>0 &lt;= i &lt;= n - 1</code>:</p>

<ul>
	<li><code>ans[i] = nums[i]</code></li>
	<li><code>ans[i + n] = nums[n - i - 1]</code></li>
</ul>

<p>Return an integer array <code>ans</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,3,3,2,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>The first <code>n</code> elements of <code>ans</code> are the same as <code>nums</code>.</p>

<p>For the next <code>n = 3</code> elements, each element is taken from <code>nums</code> in reverse order:</p>

<ul>
	<li><code>ans[3] = nums[2] = 3</code></li>
	<li><code>ans[4] = nums[1] = 2</code></li>
	<li><code>ans[5] = nums[0] = 1</code></li>
</ul>

<p>Thus, <code>ans = [1, 2, 3, 3, 2, 1]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>The array remains the same when reversed. Thus, <code>ans = [1, 1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
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
