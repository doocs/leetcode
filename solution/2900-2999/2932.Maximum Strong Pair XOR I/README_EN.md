# [2932. Maximum Strong Pair XOR I](https://leetcode.com/problems/maximum-strong-pair-xor-i)

[中文文档](/solution/2900-2999/2932.Maximum%20Strong%20Pair%20XOR%20I/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>. A pair of integers <code>x</code> and <code>y</code> is called a <strong>strong</strong> pair if it satisfies the condition:</p>

<ul>
	<li><code>|x - y| &lt;= min(x, y)</code></li>
</ul>

<p>You need to select two integers from <code>nums</code> such that they form a strong pair and their bitwise <code>XOR</code> is the <strong>maximum</strong> among all strong pairs in the array.</p>

<p>Return <em>the <strong>maximum</strong> </em><code>XOR</code><em> value out of all possible strong pairs in the array</em> <code>nums</code>.</p>

<p><strong>Note</strong> that you can pick the same integer twice to form a pair.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5]
<strong>Output:</strong> 7
<strong>Explanation:</strong> There are 11 strong pairs in the array <code>nums</code>: (1, 1), (1, 2), (2, 2), (2, 3), (2, 4), (3, 3), (3, 4), (3, 5), (4, 4), (4, 5) and (5, 5).
The maximum XOR possible from these pairs is 3 XOR 4 = 7.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,100]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are 2 strong pairs in the array <code>nums</code>: (10, 10) and (100, 100).
The maximum XOR possible from these pairs is 10 XOR 10 = 0 since the pair (100, 100) also gives 100 XOR 100 = 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,6,25,30]
<strong>Output:</strong> 7
<strong>Explanation:</strong> There are 6 strong pairs in the array <code>nums</code>: (5, 5), (5, 6), (6, 6), (25, 25), (25, 30) and (30, 30).
The maximum XOR possible from these pairs is 25 XOR 30 = 7 since the only other non-zero XOR value is 5 XOR 6 = 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
