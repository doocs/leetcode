# [2117. Abbreviating the Product of a Range](https://leetcode.com/problems/abbreviating-the-product-of-a-range)

[中文文档](/solution/2100-2199/2117.Abbreviating%20the%20Product%20of%20a%20Range/README.md)

## Description

<p>You are given two positive integers <code>left</code> and <code>right</code> with <code>left &lt;= right</code>. Calculate the <strong>product</strong> of all integers in the <strong>inclusive</strong> range <code>[left, right]</code>.</p>

<p>Since the product may be very large, you will <strong>abbreviate</strong> it following these steps:</p>

<ol>
	<li>Count all <strong>trailing</strong> zeros in the product and <strong>remove</strong> them. Let us denote this count as <code>C</code>.<br />
	For example, there are <code>3</code> trailing zeros in <code>1000</code>, and there are <code>0</code> trailing zeros in <code>546</code>.</li>
	<li>Denote the remaining number of digits in the product as <code>d</code>. If <code>d &gt; 10</code>, then express the product as <code>&lt;pre&gt;...&lt;suf&gt;</code> where <code>&lt;pre&gt;</code> denotes the <strong>first</strong> <code>5</code> digits of the product, and <code>&lt;suf&gt;</code> denotes the <strong>last</strong> <code>5</code> digits of the product <strong>after</strong> removing all trailing zeros. If <code>d &lt;= 10</code>, we keep it unchanged.<br />
	For example, we express <code>1234567654321</code> as <code>12345...54321</code>, but <code>1234567</code> is represented as <code>1234567</code>.</li>
	<li>Finally, represent the product as a <strong>string</strong> <code>&quot;&lt;pre&gt;...&lt;suf&gt;eC&quot;</code>.<br />
	For example, <code>12345678987600000</code> will be represented as <code>&quot;12345...89876e5&quot;</code>.</li>
</ol>

<p>Return <em>a string denoting the <strong>abbreviated product</strong> of all integers in the <strong>inclusive</strong> range</em> <code>[left, right]</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> left = 1, right = 4
<strong>Output:</strong> &quot;24e0&quot;
<strong>Explanation:</strong>
The product is 1 &times; 2 &times; 3 &times; 4 = 24.
There are no trailing zeros, so 24 remains the same. The abbreviation will end with &quot;e0&quot;.
Since the number of digits is 2, which is less than 10, we do not have to abbreviate it further.
Thus, the final representation is &quot;24e0&quot;. 
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> left = 2, right = 11
<strong>Output:</strong> &quot;399168e2&quot;
<strong>Explanation:</strong>
The product is 39916800.
There are 2 trailing zeros, which we remove to get 399168. The abbreviation will end with &quot;e2&quot;.
The number of digits after removing the trailing zeros is 6, so we do not abbreviate it further.
Hence, the abbreviated product is &quot;399168e2&quot;.  
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/11/17/productdrawio.png" style="width: 250px; height: 140px;" />
<pre>
<strong>Input:</strong> left = 999998, right = 1000000
<strong>Output:</strong> &quot;99999...00002e6&quot;
<strong>Explanation:</strong>
The above diagram shows how we abbreviate the product to &quot;99999...00002e6&quot;.
- It has 6 trailing zeros. The abbreviation will end with &quot;e6&quot;.
- The first 5 digits are 99999.
- The last 5 digits after removing trailing zeros is 00002.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> left = 256, right = 65535
<strong>Output:</strong> &quot;23510...78528e16317&quot;
<strong>Explanation:</strong>
The product is a very large number:
- It has 16317 trailing zeros. The abbreviation will end with &quot;e16317&quot;.
- The first 5 digits are 23510.
- The last 5 digits after removing trailing zeros is 78528.
Thus, the abbreviated product is &quot;23510...78528e16317&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= left &lt;=&nbsp;right &lt;= 10<sup>6</sup></code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
