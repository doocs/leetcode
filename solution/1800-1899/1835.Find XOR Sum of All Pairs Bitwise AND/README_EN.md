# [1835. Find XOR Sum of All Pairs Bitwise AND](https://leetcode.com/problems/find-xor-sum-of-all-pairs-bitwise-and)

[中文文档](/solution/1800-1899/1835.Find%20XOR%20Sum%20of%20All%20Pairs%20Bitwise%20AND/README.md)

## Description

<p>The <strong>XOR sum</strong> of a list is the bitwise <code>XOR</code> of all its elements. If the list only contains one element, then its <strong>XOR sum</strong> will be equal to this element.</p>

<ul>
	<li>For example, the <strong>XOR sum</strong> of <code>[1,2,3,4]</code> is equal to <code>1 XOR 2 XOR 3 XOR 4 = 4</code>, and the <strong>XOR sum</strong> of <code>[3]</code> is equal to <code>3</code>.</li>
</ul>

<p>You are given two <strong>0-indexed</strong> arrays <code>arr1</code> and <code>arr2</code> that consist only of non-negative integers.</p>

<p>Consider the list containing the result of <code>arr1[i] AND arr2[j]</code> (bitwise <code>AND</code>) for every <code>(i, j)</code> pair where <code>0 &lt;= i &lt; arr1.length</code> and <code>0 &lt;= j &lt; arr2.length</code>.</p>

<p>Return <em>the <strong>XOR sum</strong> of the aforementioned list</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [1,2,3], arr2 = [6,5]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The list = [1 AND 6, 1 AND 5, 2 AND 6, 2 AND 5, 3 AND 6, 3 AND 5] = [0,1,2,0,2,1].
The XOR sum = 0 XOR 1 XOR 2 XOR 0 XOR 2 XOR 1 = 0.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [12], arr2 = [4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The list = [12 AND 4] = [4]. The XOR sum = 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= arr1[i], arr2[j] &lt;= 10<sup>9</sup></code></li>
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
