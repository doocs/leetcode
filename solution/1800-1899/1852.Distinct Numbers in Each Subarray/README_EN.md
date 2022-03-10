# [1852. Distinct Numbers in Each Subarray](https://leetcode.com/problems/distinct-numbers-in-each-subarray)

[中文文档](/solution/1800-1899/1852.Distinct%20Numbers%20in%20Each%20Subarray/README.md)

## Description

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, you are asked to construct the array <code>ans</code> of size <code>n-k+1</code> where <code>ans[i]</code> is the number of <strong>distinct</strong> numbers in the subarray <code>nums[i:i+k-1] = [nums[i], nums[i+1], ..., nums[i+k-1]]</code>.</p>

<p>Return <em>the array </em><code>ans</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> nums = [1,2,3,2,2,1,3], k = 3

<strong>Output:</strong> [3,2,2,2,3]

<strong>Explanation: </strong>The number of distinct elements in each subarray goes as follows:

- nums[0:2] = [1,2,3] so ans[0] = 3

- nums[1:3] = [2,3,2] so ans[1] = 2

- nums[2:4] = [3,2,2] so ans[2] = 2

- nums[3:5] = [2,2,1] so ans[3] = 2

- nums[4:6] = [2,1,3] so ans[4] = 3

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> nums = [1,1,1,1,2,3,4], k = 4

<strong>Output:</strong> [1,2,3,4]

<strong>Explanation: </strong>The number of distinct elements in each subarray goes as follows:

- nums[0:3] = [1,1,1,1] so ans[0] = 1

- nums[1:4] = [1,1,1,2] so ans[1] = 2

- nums[2:5] = [1,1,2,3] so ans[2] = 3

- nums[3:6] = [1,2,3,4] so ans[3] = 4

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>

    <li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>

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
