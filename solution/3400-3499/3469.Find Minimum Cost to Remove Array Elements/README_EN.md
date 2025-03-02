---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3469.Find%20Minimum%20Cost%20to%20Remove%20Array%20Elements/README_EN.md
---

<!-- problem:start -->

# [3469. Find Minimum Cost to Remove Array Elements](https://leetcode.com/problems/find-minimum-cost-to-remove-array-elements)

[中文文档](/solution/3400-3499/3469.Find%20Minimum%20Cost%20to%20Remove%20Array%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>. Your task is to remove <strong>all elements</strong> from the array by performing one of the following operations at each step until <code>nums</code> is empty:</p>

<ul>
	<li>Choose any two elements from the first three elements of <code>nums</code> and remove them. The cost of this operation is the <strong>maximum</strong> of the two elements removed.</li>
	<li>If fewer than three elements remain in <code>nums</code>, remove all the remaining elements in a single operation. The cost of this operation is the <strong>maximum</strong> of the remaining elements.</li>
</ul>

<p>Return the <strong>minimum</strong> cost required to remove all the elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [6,2,8,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>Initially, <code>nums = [6, 2, 8, 4]</code>.</p>

<ul>
	<li>In the first operation, remove <code>nums[0] = 6</code> and <code>nums[2] = 8</code> with a cost of <code>max(6, 8) = 8</code>. Now, <code>nums = [2, 4]</code>.</li>
	<li>In the second operation, remove the remaining elements with a cost of <code>max(2, 4) = 4</code>.</li>
</ul>

<p>The cost to remove all elements is <code>8 + 4 = 12</code>. This is the minimum cost to remove all elements in <code>nums</code>. Hence, the output is 12.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,3,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>Initially, <code>nums = [2, 1, 3, 3]</code>.</p>

<ul>
	<li>In the first operation, remove <code>nums[0] = 2</code> and <code>nums[1] = 1</code> with a cost of <code>max(2, 1) = 2</code>. Now, <code>nums = [3, 3]</code>.</li>
	<li>In the second operation remove the remaining elements with a cost of <code>max(3, 3) = 3</code>.</li>
</ul>

<p>The cost to remove all elements is <code>2 + 3 = 5</code>. This is the minimum cost to remove all elements in <code>nums</code>. Hence, the output is 5.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
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
