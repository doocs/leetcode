# [1147. Longest Chunked Palindrome Decomposition](https://leetcode.com/problems/longest-chunked-palindrome-decomposition)

[中文文档](/solution/1100-1199/1147.Longest%20Chunked%20Palindrome%20Decomposition/README.md)

## Description
<p>Return the largest possible <code>k</code>&nbsp;such that there exists&nbsp;<code>a_1, a_2, ..., a_k</code>&nbsp;such that:</p>



<ul>

	<li>Each <code>a_i</code> is a non-empty string;</li>

	<li>Their concatenation <code>a_1 + a_2 + ... + a_k</code> is equal to <code>text</code>;</li>

	<li>For all <code>1 &lt;= i &lt;= k</code>,&nbsp;&nbsp;<code>a_i = a_{k+1 - i}</code>.</li>

</ul>



<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>



<pre>

<strong>Input:</strong> text = &quot;ghiabcdefhelloadamhelloabcdefghi&quot;

<strong>Output:</strong> 7

<strong>Explanation:</strong> We can split the string on &quot;(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)&quot;.

</pre>



<p><strong>Example 2:</strong></p>



<pre>

<strong>Input:</strong> text = &quot;merchant&quot;

<strong>Output:</strong> 1

<strong>Explanation:</strong> We can split the string on &quot;(merchant)&quot;.

</pre>



<p><strong>Example 3:</strong></p>



<pre>

<strong>Input:</strong> text = &quot;antaprezatepzapreanta&quot;

<strong>Output:</strong> 11

<strong>Explanation:</strong> We can split the string on &quot;(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)&quot;.

</pre>



<p><strong>Example 4:</strong></p>



<pre>

<strong>Input:</strong> text = &quot;aaa&quot;

<strong>Output:</strong> 3

<strong>Explanation:</strong> We can split the string on &quot;(a)(a)(a)&quot;.

</pre>



<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>



<ul>

	<li><code>text</code> consists only of lowercase English characters.</li>

	<li><code>1 &lt;= text.length &lt;= 1000</code></li>

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