# [1794. Count Pairs of Equal Substrings With Minimum Difference](https://leetcode.com/problems/count-pairs-of-equal-substrings-with-minimum-difference)

[中文文档](/solution/1700-1799/1794.Count%20Pairs%20of%20Equal%20Substrings%20With%20Minimum%20Difference/README.md)

## Description

<p>You are given two strings <code>firstString</code> and <code>secondString</code> that are <strong>0-indexed</strong> and consist only of lowercase English letters. Count the number of index quadruples <code>(i,j,a,b)</code> that satisfy the following conditions:</p>

<ul>
	<li><code>0 &lt;= i &lt;= j &lt; firstString.length</code></li>
	<li><code>0 &lt;= a &lt;= b &lt; secondString.length</code></li>
	<li>The substring of <code>firstString</code> that starts at the <code>i<sup>th</sup></code> character and ends at the <code>j<sup>th</sup></code> character (inclusive) is <strong>equal</strong> to the substring of <code>secondString</code> that starts at the <code>a<sup>th</sup></code> character and ends at the <code>b<sup>th</sup></code> character (inclusive).</li>
	<li><code>j - a</code> is the <strong>minimum</strong> possible value among all quadruples that satisfy the previous conditions.</li>
</ul>

<p>Return <em>the <strong>number</strong> of such quadruples</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> firstString = &quot;abcd&quot;, secondString = &quot;bccda&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> The quadruple (0,0,4,4) is the only one that satisfies all the conditions and minimizes j - a.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> firstString = &quot;ab&quot;, secondString = &quot;cd&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no quadruples satisfying all the conditions.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= firstString.length, secondString.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li>Both strings consist only of lowercase English letters.</li>
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
