# [809. Expressive Words](https://leetcode.com/problems/expressive-words)

[中文文档](/solution/0800-0899/0809.Expressive%20Words/README.md)

## Description

<p>Sometimes people repeat letters to represent extra feeling, such as &quot;hello&quot; -&gt; &quot;heeellooo&quot;, &quot;hi&quot; -&gt; &quot;hiiii&quot;.&nbsp; In these strings like &quot;heeellooo&quot;, we have <em>groups</em> of adjacent letters that are all the same:&nbsp; &quot;h&quot;, &quot;eee&quot;, &quot;ll&quot;, &quot;ooo&quot;.</p>

<p>For some given string <code>S</code>, a query word is <em>stretchy</em> if it can be made to be equal to <code>S</code> by any&nbsp;number of&nbsp;applications of the following <em>extension</em> operation: choose a group consisting of&nbsp;characters <code>c</code>, and add some number of characters <code>c</code> to the group so that the size of the group is 3 or more.</p>

<p>For example, starting with &quot;hello&quot;, we could do an extension on the group &quot;o&quot; to get &quot;hellooo&quot;, but we cannot get &quot;helloo&quot; since the group &quot;oo&quot; has size less than 3.&nbsp; Also, we could do another extension like &quot;ll&quot; -&gt; &quot;lllll&quot; to get &quot;helllllooo&quot;.&nbsp; If <code>S = &quot;helllllooo&quot;</code>, then the query word &quot;hello&quot; would be stretchy because of these two extension operations:&nbsp;<code>query = &quot;hello&quot; -&gt; &quot;hellooo&quot; -&gt;&nbsp;&quot;helllllooo&quot; = S</code>.</p>

<p>Given a list of query words, return the number of words that are stretchy.&nbsp;</p>

<p>&nbsp;</p>

<pre>
<strong>Example:</strong>
<strong>Input:</strong> 
S = &quot;heeellooo&quot;
words = [&quot;hello&quot;, &quot;hi&quot;, &quot;helo&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
We can extend &quot;e&quot; and &quot;o&quot; in the word &quot;hello&quot; to get &quot;heeellooo&quot;.
We can&#39;t extend &quot;helo&quot; to get &quot;heeellooo&quot; because the group &quot;ll&quot; is not size 3 or more.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= len(S) &lt;= 100</code>.</li>
	<li><code>0 &lt;= len(words) &lt;= 100</code>.</li>
	<li><code>0 &lt;= len(words[i]) &lt;= 100</code>.</li>
	<li><code>S</code> and all words in <code>words</code>&nbsp;consist only of&nbsp;lowercase letters</li>
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
