# [828. Count Unique Characters of All Substrings of a Given String](https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string)

[中文文档](/solution/0800-0899/0828.Count%20Unique%20Characters%20of%20All%20Substrings%20of%20a%20Given%20String/README.md)

## Description

<p>Let&#39;s define a function <code>countUniqueChars(s)</code>&nbsp;that returns the number of unique characters on <code>s</code>, for example if <code>s = &quot;LEETCODE&quot;</code>&nbsp;then <code>&quot;L&quot;</code>, <code>&quot;T&quot;</code>,<code>&quot;C&quot;</code>,<code>&quot;O&quot;</code>,<code>&quot;D&quot;</code> are the unique characters since they appear only once in <code>s</code>, therefore&nbsp;<code>countUniqueChars(s) = 5</code>.<br />
<br />
On this problem given a string <code>s</code> we need to return the sum of&nbsp;<code>countUniqueChars(t)</code>&nbsp;where <code>t</code> is a substring of <code>s</code>. Notice that some substrings can be repeated so on this case you have to count the repeated ones too.</p>

<p>Since the answer can be very large, return&nbsp;the answer&nbsp;modulo&nbsp;<code>10 ^ 9 + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ABC&quot;
<strong>Output:</strong> 10
<strong>Explanation: </strong>All possible substrings are: &quot;A&quot;,&quot;B&quot;,&quot;C&quot;,&quot;AB&quot;,&quot;BC&quot; and &quot;ABC&quot;.
Evey substring is composed with only unique letters.
Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ABA&quot;
<strong>Output:</strong> 8
<strong>Explanation: </strong>The same as example 1, except <code>countUniqueChars</code>(&quot;ABA&quot;) = 1.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;LEETCODE&quot;
<strong>Output:</strong> 92
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 10^4</code></li>
	<li><code>s</code>&nbsp;contain upper-case English letters only.</li>
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
