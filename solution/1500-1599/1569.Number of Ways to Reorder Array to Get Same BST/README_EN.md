# [1569. Number of Ways to Reorder Array to Get Same BST](https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst)

[中文文档](/solution/1500-1599/1569.Number%20of%20Ways%20to%20Reorder%20Array%20to%20Get%20Same%20BST/README.md)

## Description

<p>Given an array <code>nums</code> that represents a permutation of integers from <code>1</code> to <code>n</code>. We are going to construct a binary search tree (BST) by inserting the elements of <code>nums</code> in order into an initially empty BST. Find the number of different ways to reorder <code>nums</code> so that the constructed BST is identical to that formed from the original array <code>nums</code>.</p>

<ul>
	<li>For example, given <code>nums = [2,1,3]</code>, we will have 2 as the root, 1 as a left child, and 3 as a right child. The array <code>[2,3,1]</code> also yields the same BST but <code>[3,2,1]</code> yields a different BST.</li>
</ul>

<p>Return <em>the number of ways to reorder</em> <code>nums</code> <em>such that the BST formed is identical to the original BST formed from</em> <code>nums</code>.</p>

<p>Since the answer may be very large, <strong>return it modulo </strong><code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1569.Number%20of%20Ways%20to%20Reorder%20Array%20to%20Get%20Same%20BST/images/bb.png" style="width: 121px; height: 101px;" />
<pre>
<strong>Input:</strong> nums = [2,1,3]
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can reorder nums to be [2,3,1] which will yield the same BST. There are no other ways to reorder nums which will yield the same BST.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1569.Number%20of%20Ways%20to%20Reorder%20Array%20to%20Get%20Same%20BST/images/ex1.png" style="width: 241px; height: 161px;" />
<pre>
<strong>Input:</strong> nums = [3,4,5,1,2]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The following 5 arrays will yield the same BST: 
[3,1,2,4,5]
[3,1,4,2,5]
[3,1,4,5,2]
[3,4,1,2,5]
[3,4,1,5,2]
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1569.Number%20of%20Ways%20to%20Reorder%20Array%20to%20Get%20Same%20BST/images/ex4.png" style="width: 121px; height: 161px;" />
<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no other orderings of nums that will yield the same BST.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= nums.length</code></li>
	<li>All integers in <code>nums</code> are <strong>distinct</strong>.</li>
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
