# [1712. Ways to Split Array Into Three Subarrays](https://leetcode.com/problems/ways-to-split-array-into-three-subarrays)

[中文文档](/solution/1700-1799/1712.Ways%20to%20Split%20Array%20Into%20Three%20Subarrays/README.md)

## Description

<p>A split of an integer array is <strong>good</strong> if:</p>

<ul>
	<li>The array is split into three <strong>non-empty</strong> contiguous subarrays - named <code>left</code>, <code>mid</code>, <code>right</code> respectively from left to right.</li>
	<li>The sum of the elements in <code>left</code> is less than or equal to the sum of the elements in <code>mid</code>, and the sum of the elements in <code>mid</code> is less than or equal to the sum of the elements in <code>right</code>.</li>
</ul>

<p>Given <code>nums</code>, an array of <strong>non-negative</strong> integers, return <em>the number of <strong>good</strong> ways to split</em> <code>nums</code>. As the number may be too large, return it <strong>modulo</strong> <code>10<sup>9 </sup>+ 7</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> nums = [1,1,1]

<strong>Output:</strong> 1

<strong>Explanation:</strong> The only good way to split nums is [1] [1] [1].</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> nums = [1,2,2,2,5,0]

<strong>Output:</strong> 3

<strong>Explanation:</strong> There are three good ways of splitting nums:

[1] [2] [2,2,5,0]

[1] [2,2] [2,5,0]

[1,2] [2,2] [5,0]

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> nums = [3,2,1]

<strong>Output:</strong> 0

<strong>Explanation:</strong> There is no good way to split nums.</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
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
