# [2859. Sum of Values at Indices With K Set Bits](https://leetcode.com/problems/sum-of-values-at-indices-with-k-set-bits)

[中文文档](/solution/2800-2899/2859.Sum%20of%20Values%20at%20Indices%20With%20K%20Set%20Bits/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>Return <em>an integer that denotes the <strong>sum</strong> of elements in </em><code>nums</code><em> whose corresponding <strong>indices</strong> have <strong>exactly</strong> </em><code>k</code><em> set bits in their binary representation.</em></p>

<p>The <strong>set bits</strong> in an integer are the <code>1</code>&#39;s present when it is written in binary.</p>

<ul>
	<li>For example, the binary representation of <code>21</code> is <code>10101</code>, which has <code>3</code> set bits.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,10,1,5,2], k = 1
<strong>Output:</strong> 13
<strong>Explanation:</strong> The binary representation of the indices are: 
0 = 000<sub>2</sub>
1 = 001<sub>2</sub>
2 = 010<sub>2</sub>
3 = 011<sub>2</sub>
4 = 100<sub>2 
</sub>Indices 1, 2, and 4 have k = 1 set bits in their binary representation.
Hence, the answer is nums[1] + nums[2] + nums[4] = 13.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,3,2,1], k = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> The binary representation of the indices are:
0 = 00<sub>2</sub>
1 = 01<sub>2</sub>
2 = 10<sub>2</sub>
3 = 11<sub>2
</sub>Only index 3 has k = 2 set bits in its binary representation.
Hence, the answer is nums[3] = 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10</code></li>
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
