# [2157. Groups of Strings](https://leetcode.com/problems/groups-of-strings)

[中文文档](/solution/2100-2199/2157.Groups%20of%20Strings/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of strings <code>words</code>. Each string consists of <strong>lowercase English letters</strong> only. No letter occurs more than once in any string of <code>words</code>.</p>

<p>Two strings <code>s1</code> and <code>s2</code> are said to be <strong>connected</strong> if the set of letters of <code>s2</code> can be obtained from the set of letters of <code>s1</code> by any <strong>one</strong> of the following operations:</p>

<ul>
	<li>Adding exactly one letter to the set of the letters of <code>s1</code>.</li>
	<li>Deleting exactly one letter from the set of the letters of <code>s1</code>.</li>
	<li>Replacing exactly one letter from the set of the letters of <code>s1</code> with any letter, <strong>including</strong> itself.</li>
</ul>

<p>The array <code>words</code> can be divided into one or more non-intersecting <strong>groups</strong>. A string belongs to a group if any <strong>one</strong> of the following is true:</p>

<ul>
	<li>It is connected to <strong>at least one</strong> other string of the group.</li>
	<li>It is the <strong>only</strong> string present in the group.</li>
</ul>

<p>Note that the strings in <code>words</code> should be grouped in such a manner that a string belonging to a group cannot be connected to a string present in any other group. It can be proved that such an arrangement is always unique.</p>

<p>Return <em>an array</em> <code>ans</code> <em>of size</em> <code>2</code> <em>where:</em></p>

<ul>
	<li><code>ans[0]</code> <em>is the <strong>total number</strong> of groups</em> <code>words</code> <em>can be divided into, and</em></li>
	<li><code>ans[1]</code> <em>is the <strong>size of the largest</strong> group</em>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;b&quot;,&quot;ab&quot;,&quot;cde&quot;]
<strong>Output:</strong> [2,3]
<strong>Explanation:</strong>
- words[0] can be used to obtain words[1] (by replacing &#39;a&#39; with &#39;b&#39;), and words[2] (by adding &#39;b&#39;). So words[0] is connected to words[1] and words[2].
- words[1] can be used to obtain words[0] (by replacing &#39;b&#39; with &#39;a&#39;), and words[2] (by adding &#39;a&#39;). So words[1] is connected to words[0] and words[2].
- words[2] can be used to obtain words[0] (by deleting &#39;b&#39;), and words[1] (by deleting &#39;a&#39;). So words[2] is connected to words[0] and words[1].
- words[3] is not connected to any string in words.
Thus, words can be divided into 2 groups [&quot;a&quot;,&quot;b&quot;,&quot;ab&quot;] and [&quot;cde&quot;]. The size of the largest group is 3.  
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;ab&quot;,&quot;abc&quot;]
<strong>Output:</strong> [1,3]
<strong>Explanation:</strong>
- words[0] is connected to words[1].
- words[1] is connected to words[0] and words[2].
- words[2] is connected to words[1].
Since all strings are connected to each other, they should be grouped together.
Thus, the size of the largest group is 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 26</code></li>
	<li><code>words[i]</code> consists of lowercase English letters only.</li>
	<li>No letter occurs more than once in <code>words[i]</code>.</li>
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
