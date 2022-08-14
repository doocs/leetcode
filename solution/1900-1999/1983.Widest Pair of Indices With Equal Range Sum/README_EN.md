# [1983. Widest Pair of Indices With Equal Range Sum](https://leetcode.com/problems/widest-pair-of-indices-with-equal-range-sum)

[中文文档](/solution/1900-1999/1983.Widest%20Pair%20of%20Indices%20With%20Equal%20Range%20Sum/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> binary arrays <code>nums1</code> and <code>nums2</code>. Find the <strong>widest</strong> pair of indices <code>(i, j)</code> such that <code>i &lt;= j</code> and <code>nums1[i] + nums1[i+1] + ... + nums1[j] == nums2[i] + nums2[i+1] + ... + nums2[j]</code>.</p>

<p>The <strong>widest</strong> pair of indices is the pair with the <strong>largest</strong> <strong>distance</strong> between <code>i</code> and <code>j</code>. The <strong>distance</strong> between a pair of indices is defined as <code>j - i + 1</code>.</p>

<p>Return <em>the <strong>distance</strong> of the <strong>widest</strong> pair of indices. If no pair of indices meets the conditions, return </em><code>0</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,1,0,1], nums2 = [0,1,1,0]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
If i = 1 and j = 3:
nums1[1] + nums1[2] + nums1[3] = 1 + 0 + 1 = 2.
nums2[1] + nums2[2] + nums2[3] = 1 + 1 + 0 = 2.
The distance between i and j is j - i + 1 = 3 - 1 + 1 = 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [0,1], nums2 = [1,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
If i = 1 and j = 1:
nums1[1] = 1.
nums2[1] = 1.
The distance between i and j is j - i + 1 = 1 - 1 + 1 = 1.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [0], nums2 = [1]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
There are no pairs of indices that meet the requirements.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>nums1[i]</code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>nums2[i]</code> is either <code>0</code> or <code>1</code>.</li>
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
