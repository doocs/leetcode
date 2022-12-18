# [2506. Count Pairs Of Similar Strings](https://leetcode.com/problems/count-pairs-of-similar-strings)

[中文文档](/solution/2500-2599/2506.Count%20Pairs%20Of%20Similar%20Strings/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string array <code>words</code>.</p>

<p>Two strings are <strong>similar</strong> if they consist of the same characters.</p>

<ul>
	<li>For example, <code>&quot;abca&quot;</code> and <code>&quot;cba&quot;</code> are similar since both consist of characters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code>.</li>
	<li>However, <code>&quot;abacba&quot;</code> and <code>&quot;bcfd&quot;</code> are not similar since they do not consist of the same characters.</li>
</ul>

<p>Return <em>the number of pairs </em><code>(i, j)</code><em> such that </em><code>0 &lt;= i &lt; j &lt;= word.length - 1</code><em> and the two strings </em><code>words[i]</code><em> and </em><code>words[j]</code><em> are similar</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;aba&quot;,&quot;aabb&quot;,&quot;abcd&quot;,&quot;bac&quot;,&quot;aabc&quot;]
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 2 pairs that satisfy the conditions:
- i = 0 and j = 1 : both words[0] and words[1] only consist of characters &#39;a&#39; and &#39;b&#39;. 
- i = 3 and j = 4 : both words[3] and words[4] only consist of characters &#39;a&#39;, &#39;b&#39;, and &#39;c&#39;. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;aabb&quot;,&quot;ab&quot;,&quot;ba&quot;]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 pairs that satisfy the conditions:
- i = 0 and j = 1 : both words[0] and words[1] only consist of characters &#39;a&#39; and &#39;b&#39;. 
- i = 0 and j = 2 : both words[0] and words[2] only consist of characters &#39;a&#39; and &#39;b&#39;.
- i = 1 and j = 2 : both words[1] and words[2] only consist of characters &#39;a&#39; and &#39;b&#39;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;nba&quot;,&quot;cba&quot;,&quot;dba&quot;]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Since there does not exist any pair that satisfies the conditions, we return 0.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> consist of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
