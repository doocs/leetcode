---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3892.Minimum%20Operations%20to%20Achieve%20At%20Least%20K%20Peaks/README_EN.md
rating: 2280
source: Weekly Contest 496 Q4
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3892. Minimum Operations to Achieve At Least K Peaks](https://leetcode.com/problems/minimum-operations-to-achieve-at-least-k-peaks)

[中文文档](/solution/3800-3899/3892.Minimum%20Operations%20to%20Achieve%20At%20Least%20K%20Peaks/README.md)

## Description

<!-- description:start -->

<p>You are given a ​​​​​​​circular integer array​​​​​​​ <code>nums</code> of length <code>n</code>.</p>

<p>An index <code>i</code> is a <strong>peak</strong> if its value is <strong>strictly greater</strong> than its neighbors:</p>

<ul>
	<li>The <strong>previous</strong> neighbor of <code>i</code> is <code>nums[i - 1]</code> if <code>i &gt; 0</code>, otherwise <code>nums[n - 1]</code>.</li>
	<li>The <strong>next</strong> neighbor of <code>i</code> is <code>nums[i + 1]</code> if <code>i &lt; n - 1</code>, otherwise <code>nums[0]</code>.</li>
</ul>

<p>You are allowed to perform the following operation <strong>any</strong> number of times:</p>

<ul>
	<li>Choose any index <code>i</code> and <strong>increase</strong> <code>nums[i]</code> by 1.</li>
</ul>

<p>Return an integer denoting the <strong>minimum</strong> number of operations required to make the array contain <strong>at least</strong> <code>k</code> peaks. If it is impossible, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,2], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>To achieve at least <code>k = 1</code> peak, we can increase <code>nums[2] = 2</code> to 3.</li>
	<li>After this operation, <code>nums[2] = 3</code> is strictly greater than its neighbors <code>nums[0] = 2</code> and <code>nums[1] = 1</code>.</li>
	<li>Therefore, the minimum number of operations required is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,5,3,6], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The array already contains at least <code>k = 2</code> peaks with zero operations.</li>
	<li>Index 1: <code>nums[1] = 5</code> is strictly greater than its neighbors <code>nums[0] = 4</code> and <code>nums[2] = 3</code>.</li>
	<li>Index 3: <code>nums[3] = 6</code> is strictly greater than its neighbors <code>nums[2] = 3</code> and <code>nums[0] = 4</code>.</li>
	<li>Therefore, the minimum number of operations required is 0.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,7,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>It is impossible to have at least <code>k = 2</code> peaks in this array. Therefore, the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 5000</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= n</code>​​​​​​​</li>
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
