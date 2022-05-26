# [1433. Check If a String Can Break Another String](https://leetcode.com/problems/check-if-a-string-can-break-another-string)

[中文文档](/solution/1400-1499/1433.Check%20If%20a%20String%20Can%20Break%20Another%20String/README.md)

## Description

<p>Given two strings: <code>s1</code> and <code>s2</code> with the same&nbsp;size, check if some&nbsp;permutation of string <code>s1</code> can break&nbsp;some&nbsp;permutation of string <code>s2</code> or vice-versa. In other words <code>s2</code> can break <code>s1</code>&nbsp;or vice-versa.</p>

<p>A string <code>x</code>&nbsp;can break&nbsp;string <code>y</code>&nbsp;(both of size <code>n</code>) if <code>x[i] &gt;= y[i]</code>&nbsp;(in alphabetical order)&nbsp;for all <code>i</code>&nbsp;between <code>0</code> and <code>n-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;abc&quot;, s2 = &quot;xya&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> &quot;ayx&quot; is a permutation of s2=&quot;xya&quot; which can break to string &quot;abc&quot; which is a permutation of s1=&quot;abc&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;abe&quot;, s2 = &quot;acd&quot;
<strong>Output:</strong> false 
<strong>Explanation:</strong> All permutations for s1=&quot;abe&quot; are: &quot;abe&quot;, &quot;aeb&quot;, &quot;bae&quot;, &quot;bea&quot;, &quot;eab&quot; and &quot;eba&quot; and all permutation for s2=&quot;acd&quot; are: &quot;acd&quot;, &quot;adc&quot;, &quot;cad&quot;, &quot;cda&quot;, &quot;dac&quot; and &quot;dca&quot;. However, there is not any permutation from s1 which can break some permutation from s2 and vice-versa.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;leetcodee&quot;, s2 = &quot;interview&quot;
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>s1.length == n</code></li>
	<li><code>s2.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li>All strings consist of lowercase English letters.</li>
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
