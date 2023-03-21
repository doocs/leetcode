# [2117. Abbreviating the Product of a Range](https://leetcode.com/problems/abbreviating-the-product-of-a-range)

[中文文档](/solution/2100-2199/2117.Abbreviating%20the%20Product%20of%20a%20Range/README.md)

## Description

<p>You are given two positive integers <code>left</code> and <code>right</code> with <code>left &lt;= right</code>. Calculate the <strong>product</strong> of all integers in the <strong>inclusive</strong> range <code>[left, right]</code>.</p>

<p>Since the product may be very large, you will <strong>abbreviate</strong> it following these steps:</p>

<ol>
	<li>Count all <strong>trailing</strong> zeros in the product and <strong>remove</strong> them. Let us denote this count as <code>C</code>.
    <ul>
    	<li>For example, there are <code>3</code> trailing zeros in <code>1000</code>, and there are <code>0</code> trailing zeros in <code>546</code>.</li>
    </ul>
    </li>
    <li>Denote the remaining number of digits in the product as <code>d</code>. If <code>d &gt; 10</code>, then express the product as <code>&lt;pre&gt;...&lt;suf&gt;</code> where <code>&lt;pre&gt;</code> denotes the <strong>first</strong> <code>5</code> digits of the product, and <code>&lt;suf&gt;</code> denotes the <strong>last</strong> <code>5</code> digits of the product <strong>after</strong> removing all trailing zeros. If <code>d &lt;= 10</code>, we keep it unchanged.
    <ul>
    	<li>For example, we express <code>1234567654321</code> as <code>12345...54321</code>, but <code>1234567</code> is represented as <code>1234567</code>.</li>
    </ul>
    </li>
    <li>Finally, represent the product as a <strong>string</strong> <code>&quot;&lt;pre&gt;...&lt;suf&gt;eC&quot;</code>.
    <ul>
    	<li>For example, <code>12345678987600000</code> will be represented as <code>&quot;12345...89876e5&quot;</code>.</li>
    </ul>
    </li>
</ol>

<p>Return <em>a string denoting the <strong>abbreviated product</strong> of all integers in the <strong>inclusive</strong> range</em> <code>[left, right]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> left = 1, right = 4
<strong>Output:</strong> &quot;24e0&quot;
<strong>Explanation:</strong> The product is 1 &times; 2 &times; 3 &times; 4 = 24.
There are no trailing zeros, so 24 remains the same. The abbreviation will end with &quot;e0&quot;.
Since the number of digits is 2, which is less than 10, we do not have to abbreviate it further.
Thus, the final representation is &quot;24e0&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> left = 2, right = 11
<strong>Output:</strong> &quot;399168e2&quot;
<strong>Explanation:</strong> The product is 39916800.
There are 2 trailing zeros, which we remove to get 399168. The abbreviation will end with &quot;e2&quot;.
The number of digits after removing the trailing zeros is 6, so we do not abbreviate it further.
Hence, the abbreviated product is &quot;399168e2&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> left = 371, right = 375
<strong>Output:</strong> &quot;7219856259e3&quot;
<strong>Explanation:</strong> The product is 7219856259000.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= left &lt;= right &lt;= 10<sup>4</sup></code></li>
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
