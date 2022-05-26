# [555. Split Concatenated Strings](https://leetcode.com/problems/split-concatenated-strings)

[中文文档](/solution/0500-0599/0555.Split%20Concatenated%20Strings/README.md)

## Description

<p>You are given an array of strings <code>strs</code>. You could concatenate these strings together into a loop, where for each string, you could choose to reverse it or not. Among all the possible loops</p>

<p>Return <em>the lexicographically largest string after cutting the loop, which will make the looped string into a regular one</em>.</p>

<p>Specifically, to find the lexicographically largest string, you need to experience two phases:</p>

<ol>
	<li>Concatenate all the strings into a loop, where you can reverse some strings or not and connect them in the same order as given.</li>
	<li>Cut and make one breakpoint in any place of the loop, which will make the looped string into a regular one starting from the character at the cutpoint.</li>
</ol>

<p>And your job is to find the lexicographically largest one among all the possible regular strings.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;abc&quot;,&quot;xyz&quot;]
<strong>Output:</strong> &quot;zyxcba&quot;
<strong>Explanation:</strong> You can get the looped string &quot;-abcxyz-&quot;, &quot;-abczyx-&quot;, &quot;-cbaxyz-&quot;, &quot;-cbazyx-&quot;, where &#39;-&#39; represents the looped status. 
The answer string came from the fourth looped one, where you could cut from the middle character &#39;a&#39; and get &quot;zyxcba&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;abc&quot;]
<strong>Output:</strong> &quot;cba&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 1000</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 1000</code></li>
	<li><code>1 &lt;= sum(strs[i].length) &lt;= 1000</code></li>
	<li><code>strs[i]</code> consists of lowercase English letters.</li>
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
