# [1268. Search Suggestions System](https://leetcode.com/problems/search-suggestions-system)

[中文文档](/solution/1200-1299/1268.Search%20Suggestions%20System/README.md)

## Description

<p>Given an array of strings <code>products</code> and a string <code>searchWord</code>. We want to design a system that suggests at most three product names from <code>products</code>&nbsp;after each character of&nbsp;<code>searchWord</code> is typed. Suggested products should have common prefix with the searchWord. If there are&nbsp;more than three products with a common prefix&nbsp;return the three lexicographically minimums products.</p>

<p>Return <em>list of lists</em> of the suggested <code>products</code> after each character of&nbsp;<code>searchWord</code> is typed.&nbsp;</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> products = [&quot;mobile&quot;,&quot;mouse&quot;,&quot;moneypot&quot;,&quot;monitor&quot;,&quot;mousepad&quot;], searchWord = &quot;mouse&quot;
<strong>Output:</strong> [
[&quot;mobile&quot;,&quot;moneypot&quot;,&quot;monitor&quot;],
[&quot;mobile&quot;,&quot;moneypot&quot;,&quot;monitor&quot;],
[&quot;mouse&quot;,&quot;mousepad&quot;],
[&quot;mouse&quot;,&quot;mousepad&quot;],
[&quot;mouse&quot;,&quot;mousepad&quot;]
]
<strong>Explanation:</strong> products sorted lexicographically = [&quot;mobile&quot;,&quot;moneypot&quot;,&quot;monitor&quot;,&quot;mouse&quot;,&quot;mousepad&quot;]
After typing m and mo all products match and we show user [&quot;mobile&quot;,&quot;moneypot&quot;,&quot;monitor&quot;]
After typing mou, mous and mouse the system suggests [&quot;mouse&quot;,&quot;mousepad&quot;]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> products = [&quot;havana&quot;], searchWord = &quot;havana&quot;
<strong>Output:</strong> [[&quot;havana&quot;],[&quot;havana&quot;],[&quot;havana&quot;],[&quot;havana&quot;],[&quot;havana&quot;],[&quot;havana&quot;]]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> products = [&quot;bags&quot;,&quot;baggage&quot;,&quot;banner&quot;,&quot;box&quot;,&quot;cloths&quot;], searchWord = &quot;bags&quot;
<strong>Output:</strong> [[&quot;baggage&quot;,&quot;bags&quot;,&quot;banner&quot;],[&quot;baggage&quot;,&quot;bags&quot;,&quot;banner&quot;],[&quot;baggage&quot;,&quot;bags&quot;],[&quot;bags&quot;]]
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> products = [&quot;havana&quot;], searchWord = &quot;tatiana&quot;
<strong>Output:</strong> [[],[],[],[],[],[],[]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= products.length &lt;= 1000</code></li>
	<li>There are no&nbsp;repeated elements in&nbsp;<code>products</code>.</li>
	<li><code>1 &lt;= &Sigma; products[i].length &lt;= 2 * 10^4</code></li>
	<li>All characters of <code>products[i]</code> are lower-case English letters.</li>
	<li><code>1 &lt;= searchWord.length &lt;= 1000</code></li>
	<li>All characters of <code>searchWord</code>&nbsp;are lower-case English letters.</li>
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
