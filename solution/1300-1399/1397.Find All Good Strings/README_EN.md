# [1397. Find All Good Strings](https://leetcode.com/problems/find-all-good-strings)

[中文文档](/solution/1300-1399/1397.Find%20All%20Good%20Strings/README.md)

## Description

<p>Given the strings <code>s1</code> and <code>s2</code> of size <code>n</code> and the string <code>evil</code>, return <em>the number of <strong>good</strong> strings</em>.</p>

<p>A <strong>good</strong> string has size <code>n</code>, it is alphabetically greater than or equal to <code>s1</code>, it is alphabetically smaller than or equal to <code>s2</code>, and it does not contain the string <code>evil</code> as a substring. Since the answer can be a huge number, return this <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2, s1 = &quot;aa&quot;, s2 = &quot;da&quot;, evil = &quot;b&quot;
<strong>Output:</strong> 51 
<strong>Explanation:</strong> There are 25 good strings starting with &#39;a&#39;: &quot;aa&quot;,&quot;ac&quot;,&quot;ad&quot;,...,&quot;az&quot;. Then there are 25 good strings starting with &#39;c&#39;: &quot;ca&quot;,&quot;cc&quot;,&quot;cd&quot;,...,&quot;cz&quot; and finally there is one good string starting with &#39;d&#39;: &quot;da&quot;.&nbsp;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 8, s1 = &quot;leetcode&quot;, s2 = &quot;leetgoes&quot;, evil = &quot;leet&quot;
<strong>Output:</strong> 0 
<strong>Explanation:</strong> All strings greater than or equal to s1 and smaller than or equal to s2 start with the prefix &quot;leet&quot;, therefore, there is not any good string.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 2, s1 = &quot;gx&quot;, s2 = &quot;gz&quot;, evil = &quot;x&quot;
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>s1.length == n</code></li>
	<li><code>s2.length == n</code></li>
	<li><code>s1 &lt;= s2</code></li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= evil.length &lt;= 50</code></li>
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
