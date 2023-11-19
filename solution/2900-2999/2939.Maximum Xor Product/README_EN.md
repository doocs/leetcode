# [2939. Maximum Xor Product](https://leetcode.com/problems/maximum-xor-product)

[中文文档](/solution/2900-2999/2939.Maximum%20Xor%20Product/README.md)

## Description

<p>Given three integers <code>a</code>, <code>b</code>, and <code>n</code>, return <em>the <strong>maximum value</strong> of</em> <code>(a XOR x) * (b XOR x)</code> <em>where</em> <code>0 &lt;= x &lt; 2<sup>n</sup></code>.</p>

<p>Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9 </sup>+ 7</code>.</p>

<p><strong>Note</strong> that <code>XOR</code> is the bitwise XOR operation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = 12, b = 5, n = 4
<strong>Output:</strong> 98
<strong>Explanation:</strong> For x = 2, (a XOR x) = 14 and (b XOR x) = 7. Hence, (a XOR x) * (b XOR x) = 98. 
It can be shown that 98 is the maximum value of (a XOR x) * (b XOR x) for all 0 &lt;= x &lt; 2<sup>n</sup><span style="font-size: 10.8333px;">.</span>
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = 6, b = 7 , n = 5
<strong>Output:</strong> 930
<strong>Explanation:</strong> For x = 25, (a XOR x) = 31 and (b XOR x) = 30. Hence, (a XOR x) * (b XOR x) = 930.
It can be shown that 930 is the maximum value of (a XOR x) * (b XOR x) for all 0 &lt;= x &lt; 2<sup>n</sup>.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> a = 1, b = 6, n = 3
<strong>Output:</strong> 12
<strong>Explanation:</strong> For x = 5, (a XOR x) = 4 and (b XOR x) = 3. Hence, (a XOR x) * (b XOR x) = 12.
It can be shown that 12 is the maximum value of (a XOR x) * (b XOR x) for all 0 &lt;= x &lt; 2<sup>n</sup>.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= a, b &lt; 2<sup>50</sup></code></li>
	<li><code>0 &lt;= n &lt;= 50</code></li>
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
