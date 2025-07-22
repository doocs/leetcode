---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3410.Maximize%20Subarray%20Sum%20After%20Removing%20All%20Occurrences%20of%20One%20Element/README_EN.md
rating: 2843
source: Biweekly Contest 147 Q4
tags:
    - Segment Tree
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3410. Maximize Subarray Sum After Removing All Occurrences of One Element](https://leetcode.com/problems/maximize-subarray-sum-after-removing-all-occurrences-of-one-element)

[中文文档](/solution/3400-3499/3410.Maximize%20Subarray%20Sum%20After%20Removing%20All%20Occurrences%20of%20One%20Element/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>You can do the following operation on the array <strong>at most</strong> once:</p>

<ul>
	<li>Choose <strong>any</strong> integer <code>x</code> such that <code>nums</code> remains <strong>non-empty</strong> on removing all occurrences of <code>x</code>.</li>
	<li>Remove&nbsp;<strong>all</strong> occurrences of <code>x</code> from the array.</li>
</ul>

<p>Return the <strong>maximum</strong> <span data-keyword="subarray-nonempty">subarray</span> sum across <strong>all</strong> possible resulting arrays.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-3,2,-2,-1,3,-2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>We can have the following arrays after at most one operation:</p>

<ul>
	<li>The original array is <code>nums = [<span class="example-io">-3, 2, -2, -1, <u><strong>3, -2, 3</strong></u></span>]</code>. The maximum subarray sum is <code>3 + (-2) + 3 = 4</code>.</li>
	<li>Deleting all occurences of <code>x = -3</code> results in <code>nums = [2, -2, -1, <strong><u><span class="example-io">3, -2, 3</span></u></strong>]</code>. The maximum subarray sum is <code>3 + (-2) + 3 = 4</code>.</li>
	<li>Deleting all occurences of <code>x = -2</code> results in <code>nums = [<span class="example-io">-3, <strong><u>2, -1, 3, 3</u></strong></span>]</code>. The maximum subarray sum is <code>2 + (-1) + 3 + 3 = 7</code>.</li>
	<li>Deleting all occurences of <code>x = -1</code> results in <code>nums = [<span class="example-io">-3, 2, -2, <strong><u>3, -2, 3</u></strong></span>]</code>. The maximum subarray sum is <code>3 + (-2) + 3 = 4</code>.</li>
	<li>Deleting all occurences of <code>x = 3</code> results in <code>nums = [<span class="example-io">-3, <u><strong>2</strong></u>, -2, -1, -2</span>]</code>. The maximum subarray sum is 2.</li>
</ul>

<p>The output is <code>max(4, 4, 7, 4, 2) = 7</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>It is optimal to not perform any operations.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>6</sup> &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
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
