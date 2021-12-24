# [1498. Number of Subsequences That Satisfy the Given Sum Condition](https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition)

[中文文档](/solution/1400-1499/1498.Number%20of%20Subsequences%20That%20Satisfy%20the%20Given%20Sum%20Condition/README.md)

## Description

<p>Given an array of integers <code>nums</code> and an integer <code>target</code>.</p>

<p>Return the number of <strong>non-empty</strong> subsequences of <code>nums</code> such that the sum of the minimum and maximum element on it is less or equal to&nbsp;<code>target</code>. Since the answer&nbsp;may be too large,&nbsp;return it modulo&nbsp;<code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,5,6,7], target = 9
<strong>Output:</strong> 4
<strong>Explanation: </strong>There are 4 subsequences that satisfy the condition.
[3] -&gt; Min value + max value &lt;= target (3 + 3 &lt;= 9)
[3,5] -&gt; (3 + 5 &lt;= 9)
[3,5,6] -&gt; (3 + 6 &lt;= 9)
[3,6] -&gt; (3 + 6 &lt;= 9)
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3,6,8], target = 10
<strong>Output:</strong> 6
<strong>Explanation: </strong>There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
[3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,3,4,6,7], target = 12
<strong>Output:</strong> 61
<strong>Explanation: </strong>There are 63 non-empty subsequences, two of them don&#39;t satisfy the condition ([6,7], [7]).
Number of valid subsequences (63 - 2 = 61).
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,2,4,1,7,6,8], target = 16
<strong>Output:</strong> 127
<strong>Explanation: </strong>All non-empty subset satisfy the condition (2^7 - 1) = 127</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= target &lt;= 10<sup>6</sup></code></li>
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
