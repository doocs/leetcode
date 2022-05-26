# [1208. Get Equal Substrings Within Budget](https://leetcode.com/problems/get-equal-substrings-within-budget)

[中文文档](/solution/1200-1299/1208.Get%20Equal%20Substrings%20Within%20Budget/README.md)

## Description

<p>You are given two strings <code>s</code> and <code>t</code> of the same length. You want to change <code>s</code> to <code>t</code>. Changing the <code>i</code>-th character of <code>s</code> to <code>i</code>-th character of <code>t</code> costs <code>|s[i] - t[i]|</code> that is, the absolute difference between the ASCII values of the characters.</p>

<p>You are also given an integer <code>maxCost</code>.</p>

<p>Return the maximum length of a substring of <code>s</code> that can be changed to be the same as the corresponding substring of <code>t</code>with a cost less than or equal to <code>maxCost</code>.</p>

<p>If there is no substring from&nbsp;<code>s</code> that can be changed to its corresponding substring from <code>t</code>, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, t = &quot;bcdf&quot;, maxCost = 3
<strong>Output:</strong> 3
<strong>Explanation: </strong>&quot;abc&quot; of s can change to &quot;bcd&quot;. That costs 3, so the maximum length is 3.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, t = &quot;cdef&quot;, maxCost = 3
<strong>Output:</strong> 1
<strong>Explanation: </strong>Each character in s costs 2 to change to charactor in <code>t, so the maximum length is 1.</code>
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, t = &quot;acde&quot;, maxCost = 0
<strong>Output:</strong> 1
<strong>Explanation: </strong>You can&#39;t make any change, so the maximum length is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 10^5</code></li>
	<li><code>0 &lt;= maxCost &lt;= 10^6</code></li>
	<li><code>s</code> and&nbsp;<code>t</code> only contain lower case English letters.</li>
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
