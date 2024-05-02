# [3134. Find the Median of the Uniqueness Array](https://leetcode.com/problems/find-the-median-of-the-uniqueness-array)

[中文文档](/solution/3100-3199/3134.Find%20the%20Median%20of%20the%20Uniqueness%20Array/README.md)

<!-- tags:Array,Hash Table,Binary Search,Sliding Window -->

## Description

<p>You are given an integer array <code>nums</code>. The <strong>uniqueness array</strong> of <code>nums</code> is the sorted array that contains the number of distinct elements of all the <span data-keyword="subarray-nonempty">subarrays</span> of <code>nums</code>. In other words, it is a sorted array consisting of <code>distinct(nums[i..j])</code>, for all <code>0 &lt;= i &lt;= j &lt; nums.length</code>.</p>

<p>Here, <code>distinct(nums[i..j])</code> denotes the number of distinct elements in the subarray that starts at index <code>i</code> and ends at index <code>j</code>.</p>

<p>Return the <strong>median</strong> of the <strong>uniqueness array</strong> of <code>nums</code>.</p>

<p><strong>Note</strong> that the <strong>median</strong> of an array is defined as the middle element of the array when it is sorted in non-decreasing order. If there are two choices for a median, the <strong>smaller</strong> of the two values is taken.<!-- notionvc: 7e0f5178-4273-4a82-95ce-3395297921dc --></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The uniqueness array of <code>nums</code> is <code>[distinct(nums[0..0]), distinct(nums[1..1]), distinct(nums[2..2]), distinct(nums[0..1]), distinct(nums[1..2]), distinct(nums[0..2])]</code> which is equal to <code>[1, 1, 1, 2, 2, 3]</code>. The uniqueness array has a median of 1. Therefore, the answer is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,4,3,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The uniqueness array of <code>nums</code> is <code>[1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3]</code>. The uniqueness array has a median of 2. Therefore, the answer is 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,5,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The uniqueness array of <code>nums</code> is <code>[1, 1, 1, 1, 2, 2, 2, 3, 3, 3]</code>. The uniqueness array has a median of 2. Therefore, the answer is 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
