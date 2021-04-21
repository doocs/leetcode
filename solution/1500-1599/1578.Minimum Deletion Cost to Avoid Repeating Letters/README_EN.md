# [1578. Minimum Deletion Cost to Avoid Repeating Letters](https://leetcode.com/problems/minimum-deletion-cost-to-avoid-repeating-letters)

[中文文档](/solution/1500-1599/1578.Minimum%20Deletion%20Cost%20to%20Avoid%20Repeating%20Letters/README.md)

## Description

<p>Given a&nbsp;string <code>s</code> and an array of integers <code>cost</code> where <code>cost[i]</code>&nbsp;is the cost of deleting the <code>i<sup>th</sup></code> character in <code>s</code>.</p>

<p>Return the minimum cost of deletions such that there are no two identical letters next to each other.</p>

<p>Notice that you will delete the chosen characters at the same time, in other words, after deleting a character, the costs of deleting other characters will not change.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abaac&quot;, cost = [1,2,3,4,5]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Delete the letter &quot;a&quot; with cost 3 to get &quot;abac&quot; (String without two identical letters next to each other).
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc&quot;, cost = [1,2,3]
<strong>Output:</strong> 0
<strong>Explanation:</strong> You don&#39;t need to delete any character because there are no identical letters next to each other.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabaa&quot;, cost = [1,2,3,4,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Delete the first and the last character, getting the string (&quot;aba&quot;).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>s.length == cost.length</code></li>
	<li><code>1 &lt;= s.length, cost.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= cost[i] &lt;=&nbsp;10^4</code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
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
