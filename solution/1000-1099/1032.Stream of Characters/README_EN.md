# [1032. Stream of Characters](https://leetcode.com/problems/stream-of-characters)

[中文文档](/solution/1000-1099/1032.Stream%20of%20Characters/README.md)

## Description

<p>Implement the <code>StreamChecker</code> class as follows:</p>

<ul>
	<li><code>StreamChecker(words)</code>: Constructor, init the data structure with the given words.</li>
	<li><code>query(letter)</code>: returns true if and only if for some <code>k &gt;= 1</code>, the last <code>k</code>&nbsp;characters queried (in order from oldest to newest, including this letter just queried) spell one of the words in the given list.</li>
</ul>

<p>&nbsp;</p>

<p><strong>Example:</strong></p>

<pre>

StreamChecker streamChecker = new StreamChecker([&quot;cd&quot;,&quot;f&quot;,&quot;kl&quot;]); // init the dictionary.

streamChecker.query(&#39;a&#39;);          // return false

streamChecker.query(&#39;b&#39;);          // return false

streamChecker.query(&#39;c&#39;);          // return false

streamChecker.query(&#39;d&#39;);          // return true, because &#39;cd&#39; is in the wordlist

streamChecker.query(&#39;e&#39;);          // return false

streamChecker.query(&#39;f&#39;);          // return true, because &#39;f&#39; is in the wordlist

streamChecker.query(&#39;g&#39;);          // return false

streamChecker.query(&#39;h&#39;);          // return false

streamChecker.query(&#39;i&#39;);          // return false

streamChecker.query(&#39;j&#39;);          // return false

streamChecker.query(&#39;k&#39;);          // return false

streamChecker.query(&#39;l&#39;);          // return true, because &#39;kl&#39; is in the wordlist

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 2000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 2000</code></li>
	<li>Words will only consist of lowercase English letters.</li>
	<li>Queries will only consist of lowercase English letters.</li>
	<li>The number of queries is at most&nbsp;40000.</li>
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
