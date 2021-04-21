# [443. String Compression](https://leetcode.com/problems/string-compression)

[中文文档](/solution/0400-0499/0443.String%20Compression/README.md)

## Description

<p>Given an array of characters <code>chars</code>, compress it using the following algorithm:</p>

<p>Begin with an empty string <code>s</code>. For each group of <strong>consecutive repeating characters</strong> in <code>chars</code>:</p>

<ul>
	<li>If the group&#39;s length is 1, append the character to&nbsp;<code>s</code>.</li>
	<li>Otherwise, append the character followed by the group&#39;s length.</li>
</ul>

<p>The compressed string&nbsp;<code>s</code> <strong>should not be returned separately</strong>, but instead be stored&nbsp;<strong>in the input character array&nbsp;<code>chars</code></strong>. Note that group lengths that are 10 or longer will be split into multiple characters in&nbsp;<code>chars</code>.</p>

<p>After you are done <b>modifying the input array</b>, return <em>the new length of the array</em>.</p>
&nbsp;

<p><b>Follow up:</b><br />
Could you solve it using only <code>O(1)</code> extra space?</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> chars = [&quot;a&quot;,&quot;a&quot;,&quot;b&quot;,&quot;b&quot;,&quot;c&quot;,&quot;c&quot;,&quot;c&quot;]
<strong>Output:</strong> Return 6, and the first 6 characters of the input array should be: [&quot;a&quot;,&quot;2&quot;,&quot;b&quot;,&quot;2&quot;,&quot;c&quot;,&quot;3&quot;]
<strong>Explanation:</strong>&nbsp;The groups are &quot;aa&quot;, &quot;bb&quot;, and &quot;ccc&quot;. This compresses to &quot;a2b2c3&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> chars = [&quot;a&quot;]
<strong>Output:</strong> Return 1, and the first character of the input array should be: [&quot;a&quot;]
<strong>Explanation:</strong>&nbsp;The only group is &quot;a&quot;, which remains uncompressed since it&#39;s a single character.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> chars = [&quot;a&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;]
<strong>Output:</strong> Return 4, and the first 4 characters of the input array should be: [&quot;a&quot;,&quot;b&quot;,&quot;1&quot;,&quot;2&quot;].
<strong>Explanation:</strong>&nbsp;The groups are &quot;a&quot; and &quot;bbbbbbbbbbbb&quot;. This compresses to &quot;ab12&quot;.</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> chars = [&quot;a&quot;,&quot;a&quot;,&quot;a&quot;,&quot;b&quot;,&quot;b&quot;,&quot;a&quot;,&quot;a&quot;]
<strong>Output:</strong> Return 6, and the first 6 characters of the input array should be: [&quot;a&quot;,&quot;3&quot;,&quot;b&quot;,&quot;2&quot;,&quot;a&quot;,&quot;2&quot;].
<strong>Explanation:</strong>&nbsp;The groups are &quot;aaa&quot;, &quot;bb&quot;, and &quot;aa&quot;. This compresses to &quot;a3b2a2&quot;. Note that each group is independent even if two groups have the same character.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= chars.length &lt;= 2000</code></li>
	<li><code>chars[i]</code> is a lower-case English letter, upper-case English letter, digit, or symbol.</li>
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
