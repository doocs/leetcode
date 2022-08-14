# [1787. Make the XOR of All Segments Equal to Zero](https://leetcode.com/problems/make-the-xor-of-all-segments-equal-to-zero)

[中文文档](/solution/1700-1799/1787.Make%20the%20XOR%20of%20All%20Segments%20Equal%20to%20Zero/README.md)

## Description

<p>You are given an array <code>nums</code>​​​ and an integer <code>k</code>​​​​​. The <font face="monospace">XOR</font> of a segment <code>[left, right]</code> where <code>left &lt;= right</code> is the <code>XOR</code> of all the elements with indices between <code>left</code> and <code>right</code>, inclusive: <code>nums[left] XOR nums[left+1] XOR ... XOR nums[right]</code>.</p>

<p>Return <em>the minimum number of elements to change in the array </em>such that the <code>XOR</code> of all segments of size <code>k</code>​​​​​​ is equal to zero.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,0,3,0], k = 1
<strong>Output:</strong> 3
<strong>Explanation: </strong>Modify the array from [<u><strong>1</strong></u>,<u><strong>2</strong></u>,0,<u><strong>3</strong></u>,0] to from [<u><strong>0</strong></u>,<u><strong>0</strong></u>,0,<u><strong>0</strong></u>,0].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,5,2,1,7,3,4,7], k = 3
<strong>Output:</strong> 3
<strong>Explanation: </strong>Modify the array from [3,4,<strong><u>5</u></strong>,<strong><u>2</u></strong>,<strong><u>1</u></strong>,7,3,4,7] to [3,4,<strong><u>7</u></strong>,<strong><u>3</u></strong>,<strong><u>4</u></strong>,7,3,4,7].
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,4,1,2,5,1,2,6], k = 3
<strong>Output:</strong> 3
<strong>Explanation: </strong>Modify the array from [1,2,<strong><u>4,</u></strong>1,2,<strong><u>5</u></strong>,1,2,<strong><u>6</u></strong>] to [1,2,<strong><u>3</u></strong>,1,2,<strong><u>3</u></strong>,1,2,<strong><u>3</u></strong>].</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 2000</code></li>
	<li><code>​​​​​​0 &lt;= nums[i] &lt; 2<sup>10</sup></code></li>
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
