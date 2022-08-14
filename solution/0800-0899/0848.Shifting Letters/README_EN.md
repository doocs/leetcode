# [848. Shifting Letters](https://leetcode.com/problems/shifting-letters)

[中文文档](/solution/0800-0899/0848.Shifting%20Letters/README.md)

## Description

<p>You are given a string <code>s</code> of lowercase English letters and an integer array <code>shifts</code> of the same length.</p>

<p>Call the <code>shift()</code> of a letter, the next letter in the alphabet, (wrapping around so that <code>&#39;z&#39;</code> becomes <code>&#39;a&#39;</code>).</p>

<ul>
	<li>For example, <code>shift(&#39;a&#39;) = &#39;b&#39;</code>, <code>shift(&#39;t&#39;) = &#39;u&#39;</code>, and <code>shift(&#39;z&#39;) = &#39;a&#39;</code>.</li>
</ul>

<p>Now for each <code>shifts[i] = x</code>, we want to shift the first <code>i + 1</code> letters of <code>s</code>, <code>x</code> times.</p>

<p>Return <em>the final string after all such shifts to s are applied</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc&quot;, shifts = [3,5,9]
<strong>Output:</strong> &quot;rpl&quot;
<strong>Explanation:</strong> We start with &quot;abc&quot;.
After shifting the first 1 letters of s by 3, we have &quot;dbc&quot;.
After shifting the first 2 letters of s by 5, we have &quot;igc&quot;.
After shifting the first 3 letters of s by 9, we have &quot;rpl&quot;, the answer.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaa&quot;, shifts = [1,2,3]
<strong>Output:</strong> &quot;gfd&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>shifts.length == s.length</code></li>
	<li><code>0 &lt;= shifts[i] &lt;= 10<sup>9</sup></code></li>
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
