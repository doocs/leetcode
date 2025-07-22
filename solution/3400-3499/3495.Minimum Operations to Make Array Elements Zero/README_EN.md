---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3495.Minimum%20Operations%20to%20Make%20Array%20Elements%20Zero/README_EN.md
rating: 2205
source: Weekly Contest 442 Q4
tags:
    - Bit Manipulation
    - Array
    - Math
---

<!-- problem:start -->

# [3495. Minimum Operations to Make Array Elements Zero](https://leetcode.com/problems/minimum-operations-to-make-array-elements-zero)

[中文文档](/solution/3400-3499/3495.Minimum%20Operations%20to%20Make%20Array%20Elements%20Zero/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D array <code>queries</code>, where <code>queries[i]</code> is of the form <code>[l, r]</code>. Each <code>queries[i]</code> defines an array of integers <code>nums</code> consisting of elements ranging from <code>l</code> to <code>r</code>, both <strong>inclusive</strong>.</p>

<p>In one operation, you can:</p>

<ul>
	<li>Select two integers <code>a</code> and <code>b</code> from the array.</li>
	<li>Replace them with <code>floor(a / 4)</code> and <code>floor(b / 4)</code>.</li>
</ul>

<p>Your task is to determine the <strong>minimum</strong> number of operations required to reduce all elements of the array to zero for each query. Return the sum of the results for all queries.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">queries = [[1,2],[2,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>For <code>queries[0]</code>:</p>

<ul>
	<li>The initial array is <code>nums = [1, 2]</code>.</li>
	<li>In the first operation, select <code>nums[0]</code> and <code>nums[1]</code>. The array becomes <code>[0, 0]</code>.</li>
	<li>The minimum number of operations required is 1.</li>
</ul>

<p>For <code>queries[1]</code>:</p>

<ul>
	<li>The initial array is <code>nums = [2, 3, 4]</code>.</li>
	<li>In the first operation, select <code>nums[0]</code> and <code>nums[2]</code>. The array becomes <code>[0, 3, 1]</code>.</li>
	<li>In the second operation, select <code>nums[1]</code> and <code>nums[2]</code>. The array becomes <code>[0, 0, 0]</code>.</li>
	<li>The minimum number of operations required is 2.</li>
</ul>

<p>The output is <code>1 + 2 = 3</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">queries = [[2,6]]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>For <code>queries[0]</code>:</p>

<ul>
	<li>The initial array is <code>nums = [2, 3, 4, 5, 6]</code>.</li>
	<li>In the first operation, select <code>nums[0]</code> and <code>nums[3]</code>. The array becomes <code>[0, 3, 4, 1, 6]</code>.</li>
	<li>In the second operation, select <code>nums[2]</code> and <code>nums[4]</code>. The array becomes <code>[0, 3, 1, 1, 1]</code>.</li>
	<li>In the third operation, select <code>nums[1]</code> and <code>nums[2]</code>. The array becomes <code>[0, 0, 0, 1, 1]</code>.</li>
	<li>In the fourth operation, select <code>nums[3]</code> and <code>nums[4]</code>. The array becomes <code>[0, 0, 0, 0, 0]</code>.</li>
	<li>The minimum number of operations required is 4.</li>
</ul>

<p>The output is 4.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>queries[i] == [l, r]</code></li>
	<li><code>1 &lt;= l &lt; r &lt;= 10<sup>9</sup></code></li>
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
