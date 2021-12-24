# [898. Bitwise ORs of Subarrays](https://leetcode.com/problems/bitwise-ors-of-subarrays)

[中文文档](/solution/0800-0899/0898.Bitwise%20ORs%20of%20Subarrays/README.md)

## Description

<p>We have an array <code>arr</code> of non-negative integers.</p>

<p>For every (contiguous) subarray <code>sub = [arr[i], arr[i + 1], ..., arr[j]]</code> (with <code>i &lt;= j</code>), we take the bitwise OR of all the elements in <code>sub</code>, obtaining a result <code>arr[i] | arr[i + 1] | ... | arr[j]</code>.</p>

<p>Return the number of possible results. Results that occur more than once are only counted once in the final answer</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [0]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is only one possible result: 0.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,1,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The possible subarrays are [1], [1], [2], [1, 1], [1, 2], [1, 1, 2].
These yield the results 1, 1, 2, 1, 3, 3.
There are 3 unique values, so the answer is 3.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,4]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The possible results are 1, 2, 3, 4, 6, and 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i]&nbsp;&lt;= 10<sup>9</sup></code></li>
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
