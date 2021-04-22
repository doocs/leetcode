# [1442. Count Triplets That Can Form Two Arrays of Equal XOR](https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor)

[中文文档](/solution/1400-1499/1442.Count%20Triplets%20That%20Can%20Form%20Two%20Arrays%20of%20Equal%20XOR/README.md)

## Description

<p>Given an array of&nbsp;integers <code>arr</code>.</p>



<p>We want to select three indices <code>i</code>, <code>j</code> and <code>k</code> where <code>(0 &lt;= i &lt; j &lt;= k &lt; arr.length)</code>.</p>



<p>Let&#39;s define <code>a</code> and <code>b</code> as follows:</p>



<ul>
	<li><code>a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]</code></li>
	<li><code>b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]</code></li>
</ul>



<p>Note that <strong>^</strong> denotes the <strong>bitwise-xor</strong> operation.</p>



<p>Return <em>the number of triplets</em> (<code>i</code>, <code>j</code> and <code>k</code>) Where <code>a == b</code>.</p>



<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>



<pre>

<strong>Input:</strong> arr = [2,3,1,6,7]

<strong>Output:</strong> 4

<strong>Explanation:</strong> The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)

</pre>



<p><strong>Example 2:</strong></p>



<pre>

<strong>Input:</strong> arr = [1,1,1,1,1]

<strong>Output:</strong> 10

</pre>



<p><strong>Example 3:</strong></p>



<pre>

<strong>Input:</strong> arr = [2,3]

<strong>Output:</strong> 0

</pre>



<p><strong>Example 4:</strong></p>



<pre>

<strong>Input:</strong> arr = [1,3,5,7,9]

<strong>Output:</strong> 3

</pre>



<p><strong>Example 5:</strong></p>



<pre>

<strong>Input:</strong> arr = [7,11,12,9,5,2,7,17,22]

<strong>Output:</strong> 8

</pre>



<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>



<ul>
	<li><code>1 &lt;= arr.length &lt;= 300</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10^8</code></li>
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
