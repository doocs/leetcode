# [1569. Number of Ways to Reorder Array to Get Same BST](https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst)

[中文文档](/solution/1500-1599/1569.Number%20of%20Ways%20to%20Reorder%20Array%20to%20Get%20Same%20BST/README.md)

## Description

<p>Given an array <code>nums</code>&nbsp;that represents a permutation of integers from&nbsp;<code>1</code>&nbsp;to&nbsp;<code>n</code>. We are going to construct a binary search tree (BST) by inserting the elements of&nbsp;<code>nums</code>&nbsp;in&nbsp;order into an initially empty BST. Find the number of different ways to reorder <code>nums</code> so that the constructed BST is identical to that formed from the original array&nbsp;<code>nums</code>.</p>

<p>For example, given&nbsp;<code>nums = [2,1,3]</code>, we will have 2 as the root, 1 as a left child, and 3 as a right child. The array&nbsp;<code>[2,3,1]</code>&nbsp;also yields the same BST but&nbsp;<code>[3,2,1]</code>&nbsp;yields a different BST.</p>

<p>Return <em>the number of ways to reorder</em>&nbsp;<code>nums</code>&nbsp;<em>such that the BST formed is identical to the original BST formed from</em>&nbsp;<code>nums</code>.</p>

<p>Since the answer may be very large,&nbsp;<strong>return it modulo&nbsp;</strong><code>10^9 + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1569.Number%20of%20Ways%20to%20Reorder%20Array%20to%20Get%20Same%20BST/images/bb.png" style="width: 121px; height: 101px;" /></p>

<pre>
<strong>Input:</strong> nums = [2,1,3]
<strong>Output:</strong> 1
<strong>Explanation: </strong>We can reorder nums to be [2,3,1] which will yield the same BST. There are no other ways to reorder nums which will yield the same BST.
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1569.Number%20of%20Ways%20to%20Reorder%20Array%20to%20Get%20Same%20BST/images/ex1.png" style="width: 241px; height: 161px;" /></strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,5,1,2]
<strong>Output:</strong> 5
<b>Explanation: </b>The following 5 arrays will yield the same BST: 
[3,1,2,4,5]
[3,1,4,2,5]
[3,1,4,5,2]
[3,4,1,2,5]
[3,4,1,5,2]
</pre>

<p><strong>Example 3:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1569.Number%20of%20Ways%20to%20Reorder%20Array%20to%20Get%20Same%20BST/images/ex4.png" style="width: 121px; height: 161px;" /></strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 0
<strong>Explanation: </strong>There are no other orderings of nums that will yield the same BST.
</pre>

<p><strong>Example 4:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1569.Number%20of%20Ways%20to%20Reorder%20Array%20to%20Get%20Same%20BST/images/abc.png" style="width: 241px; height: 161px;" /></strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,2,5,4,6]
<strong>Output:</strong> 19
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> nums = [9,4,2,1,3,6,5,7,8,14,11,10,12,13,16,15,17,18]
<strong>Output:</strong> 216212978
<strong>Explanation: </strong>The number of ways to reorder nums to get the same BST is 3216212999. Taking this number modulo 10^9 + 7 gives 216212978.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= nums.length</code></li>
	<li>All integers in&nbsp;<code>nums</code>&nbsp;are&nbsp;<strong>distinct</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
