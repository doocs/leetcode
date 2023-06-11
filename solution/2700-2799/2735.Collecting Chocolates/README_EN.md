# [2735. Collecting Chocolates](https://leetcode.com/problems/collecting-chocolates)

[中文文档](/solution/2700-2799/2735.Collecting%20Chocolates/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of size <code>n</code> representing the cost of collecting different chocolates. Each chocolate is of a different type, and originally, the chocolate at <code>i<sup>th</sup></code> index is of <code>i<sup>th</sup></code> type.</p>

<p>In one operation, you can do the following with an incurred <strong>cost</strong> of <code>x</code>:</p>

<ul>
	<li>Simultaneously change the chocolate of <code>i<sup>th</sup></code> type to (<code>i + 1)<sup>th</sup></code> type for all indexes <code>i</code> where <code>0 &lt;= i &lt; n - 1</code>. When&nbsp;<code>i == n - 1</code>, that chocolate will be changed to type of the chocolate at index <code>0</code>.</li>
</ul>

<p>Return <em>the minimum cost to collect chocolates of all types, given that you can perform as many operations as you would like.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [20,1,15], x = 5
<strong>Output:</strong> 13
<strong>Explanation:</strong> Initially, the chocolate types are [0,1,2]. We will buy the 1<sup>st</sup>&nbsp;type of chocolate at a cost of 1.
Now, we will perform the operation at a cost of 5, and the types of chocolates will become [2,0,1]. We will buy the 0<sup>th </sup>type of chocolate at a cost of 1.
Now, we will again perform the operation at a cost of 5, and the chocolate types will become [1,2,0]. We will buy the 2<sup>nd </sup>type of chocolate at a cost of 1. 
Thus, the total cost will become (1 + 5 + 1 + 5 + 1) = 13. We can prove that this is optimal.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3], x = 4
<strong>Output:</strong> 6
<strong>Explanation:</strong> We will collect all three types of chocolates at their own price without performing any operations. Therefore, the total cost is 1 + 2 + 3 = 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= x &lt;= 10<sup>9</sup></code></li>
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
