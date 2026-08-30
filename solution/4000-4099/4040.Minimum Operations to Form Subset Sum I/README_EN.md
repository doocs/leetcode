---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4040.Minimum%20Operations%20to%20Form%20Subset%20Sum%20I/README_EN.md
---

<!-- problem:start -->

# [4040. Minimum Operations to Form Subset Sum I](https://leetcode.com/problems/minimum-operations-to-form-subset-sum-i)

[中文文档](/solution/4000-4099/4040.Minimum%20Operations%20to%20Form%20Subset%20Sum%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>sum</code>.</p>

<p>In one <strong>operation</strong>, choose an element with current value <code>x</code> and replace it with either <code>2 * x</code> or <code>floor(x / 2)</code>.</p>

<p>For each element, all <strong>multiplication</strong> operations performed on it must occur <strong>before</strong> any <strong>division</strong> operations performed on it.</p>

<p>Return the <strong>minimum</strong> number of operations needed so that some <span data-keyword="subset">subset</span> of the resulting array has a sum <strong>exactly</strong> equal to <code>sum</code>. If it is impossible, return -1.</p>

<p>The <code>floor()</code> function returns the integer part of the division.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,6,10], sum = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Divide <code>nums[0] = 5</code> twice: <code>5 &rarr; 2 &rarr; 1</code>, costing 2 operations.</li>
	<li>Divide <code>nums[1] = 6</code> once: <code>6 &rarr; 3</code>, costing 1 operation.</li>
	<li>After these operations, <code>nums = [1, 3, 10]</code>. The subset <code>{1, 3}</code> sums to 4 using 3 operations in total.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [10,2], sum = 13</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Divide <code>nums[0] = 10</code> once: <code>10 &rarr; 5</code>, costing 1 operation.</li>
	<li>Multiply <code>nums[1] = 2</code> twice: <code>2 &rarr; 4 &rarr; 8</code>, costing 2 operations.</li>
	<li>After these operations, <code>nums = [5, 8]</code>. The subset <code>{5, 8}</code> sums to 13 using 3 operations in total.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,3], sum = 8</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>No sequence of operations lets a subset of <code>nums</code> sum to 8, so the answer is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 500</code></li>
	<li><code>1 &lt;= sum &lt;= 5000</code></li>
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
