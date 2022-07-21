# [1147. Longest Chunked Palindrome Decomposition](https://leetcode.com/problems/longest-chunked-palindrome-decomposition)

[中文文档](/solution/1100-1199/1147.Longest%20Chunked%20Palindrome%20Decomposition/README.md)

## Description

<p>You are given a string <code>text</code>. You should split it to k substrings <code>(subtext<sub>1</sub>, subtext<sub>2</sub>, ..., subtext<sub>k</sub>)</code> such that:</p>

<ul>
	<li><code>subtext<sub>i</sub></code> is a <strong>non-empty</strong> string.</li>
	<li>The concatenation of all the substrings is equal to <code>text</code> (i.e., <code>subtext<sub>1</sub> + subtext<sub>2</sub> + ... + subtext<sub>k</sub> == text</code>).</li>
	<li><code>subtext<sub>i</sub> == subtext<sub>k - i + 1</sub></code> for all valid values of <code>i</code> (i.e., <code>1 &lt;= i &lt;= k</code>).</li>
</ul>

<p>Return the largest possible value of <code>k</code>.</p>

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
<strong>Explanation:</strong> We can split the string on &quot;(a)(nt)(a)(pre)(za)(tep)(za)(pre)(a)(nt)(a)&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 1000</code></li>
	<li><code>text</code> consists only of lowercase English characters.</li>
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
