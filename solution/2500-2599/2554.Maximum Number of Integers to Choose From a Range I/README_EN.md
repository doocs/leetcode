# [2554. Maximum Number of Integers to Choose From a Range I](https://leetcode.com/problems/maximum-number-of-integers-to-choose-from-a-range-i)

[中文文档](/solution/2500-2599/2554.Maximum%20Number%20of%20Integers%20to%20Choose%20From%20a%20Range%20I/README.md)

## Description

<p>You are given an integer array <code>banned</code> and two integers <code>n</code> and <code>maxSum</code>. You are choosing some number of integers following the below rules:</p>

<ul>
	<li>The chosen integers have to be in the range <code>[1, n]</code>.</li>
	<li>Each integer can be chosen <strong>at most once</strong>.</li>
	<li>The chosen integers should not be in the array <code>banned</code>.</li>
	<li>The sum of the chosen integers should not exceed <code>maxSum</code>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of integers you can choose following the mentioned rules</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> banned = [1,6,5], n = 5, maxSum = 6
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can choose the integers 2 and 4.
2 and 4 are from the range [1, 5], both did not appear in banned, and their sum is 6, which did not exceed maxSum.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> banned = [1,2,3,4,5,6,7], n = 8, maxSum = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> You cannot choose any integer while following the mentioned conditions.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> banned = [11], n = 7, maxSum = 50
<strong>Output:</strong> 7
<strong>Explanation:</strong> You can choose the integers 1, 2, 3, 4, 5, 6, and 7.
They are from the range [1, 7], all did not appear in banned, and their sum is 28, which did not exceed maxSum.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= banned.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= banned[i], n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= maxSum &lt;= 10<sup>9</sup></code></li>
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
