# [809. Expressive Words](https://leetcode.com/problems/expressive-words)

[中文文档](/solution/0800-0899/0809.Expressive%20Words/README.md)

## Description

<p>Sometimes people repeat letters to represent extra feeling. For example:</p>

<ul>
	<li><code>&quot;hello&quot; -&gt; &quot;heeellooo&quot;</code></li>
	<li><code>&quot;hi&quot; -&gt; &quot;hiiii&quot;</code></li>
</ul>

<p>In these strings like <code>&quot;heeellooo&quot;</code>, we have groups of adjacent letters that are all the same: <code>&quot;h&quot;</code>, <code>&quot;eee&quot;</code>, <code>&quot;ll&quot;</code>, <code>&quot;ooo&quot;</code>.</p>

<p>You are given a string <code>s</code> and an array of query strings <code>words</code>. A query word is <strong>stretchy</strong> if it can be made to be equal to <code>s</code> by any number of applications of the following extension operation: choose a group consisting of characters <code>c</code>, and add some number of characters <code>c</code> to the group so that the size of the group is <strong>three or more</strong>.</p>

<ul>
	<li>For example, starting with <code>&quot;hello&quot;</code>, we could do an extension on the group <code>&quot;o&quot;</code> to get <code>&quot;hellooo&quot;</code>, but we cannot get <code>&quot;helloo&quot;</code> since the group <code>&quot;oo&quot;</code> has a size less than three. Also, we could do another extension like <code>&quot;ll&quot; -&gt; &quot;lllll&quot;</code> to get <code>&quot;helllllooo&quot;</code>. If <code>s = &quot;helllllooo&quot;</code>, then the query word <code>&quot;hello&quot;</code> would be <strong>stretchy</strong> because of these two extension operations: <code>query = &quot;hello&quot; -&gt; &quot;hellooo&quot; -&gt; &quot;helllllooo&quot; = s</code>.</li>
</ul>

<p>Return <em>the number of query strings that are <strong>stretchy</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;heeellooo&quot;, words = [&quot;hello&quot;, &quot;hi&quot;, &quot;helo&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
We can extend &quot;e&quot; and &quot;o&quot; in the word &quot;hello&quot; to get &quot;heeellooo&quot;.
We can&#39;t extend &quot;helo&quot; to get &quot;heeellooo&quot; because the group &quot;ll&quot; is not size 3 or more.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;zzzzzyyyyy&quot;, words = [&quot;zzyy&quot;,&quot;zy&quot;,&quot;zyy&quot;]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>s</code> and <code>words[i]</code> consist of lowercase letters.</li>
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
