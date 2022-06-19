# [1032. Stream of Characters](https://leetcode.com/problems/stream-of-characters)

[中文文档](/solution/1000-1099/1032.Stream%20of%20Characters/README.md)

## Description

<p>Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a given array of strings <code>words</code>.</p>

<p>For example, if <code>words = [&quot;abc&quot;, &quot;xyz&quot;]</code>&nbsp;and the stream added the four characters (one by one) <code>&#39;a&#39;</code>, <code>&#39;x&#39;</code>, <code>&#39;y&#39;</code>, and <code>&#39;z&#39;</code>, your algorithm should detect that the suffix <code>&quot;xyz&quot;</code> of the characters <code>&quot;axyz&quot;</code> matches <code>&quot;xyz&quot;</code> from <code>words</code>.</p>

<p>Implement the <code>StreamChecker</code> class:</p>

<ul>
	<li><code>StreamChecker(String[] words)</code> Initializes the object with the strings array <code>words</code>.</li>
	<li><code>boolean query(char letter)</code> Accepts a new character from the stream and returns <code>true</code> if any non-empty suffix from the stream forms a word that is in <code>words</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;StreamChecker&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;]
[[[&quot;cd&quot;, &quot;f&quot;, &quot;kl&quot;]], [&quot;a&quot;], [&quot;b&quot;], [&quot;c&quot;], [&quot;d&quot;], [&quot;e&quot;], [&quot;f&quot;], [&quot;g&quot;], [&quot;h&quot;], [&quot;i&quot;], [&quot;j&quot;], [&quot;k&quot;], [&quot;l&quot;]]
<strong>Output</strong>
[null, false, false, false, true, false, true, false, false, false, false, false, true]

<strong>Explanation</strong>
StreamChecker streamChecker = new StreamChecker([&quot;cd&quot;, &quot;f&quot;, &quot;kl&quot;]);
streamChecker.query(&quot;a&quot;); // return False
streamChecker.query(&quot;b&quot;); // return False
streamChecker.query(&quot;c&quot;); // return False
streamChecker.query(&quot;d&quot;); // return True, because &#39;cd&#39; is in the wordlist
streamChecker.query(&quot;e&quot;); // return False
streamChecker.query(&quot;f&quot;); // return True, because &#39;f&#39; is in the wordlist
streamChecker.query(&quot;g&quot;); // return False
streamChecker.query(&quot;h&quot;); // return False
streamChecker.query(&quot;i&quot;); // return False
streamChecker.query(&quot;j&quot;); // return False
streamChecker.query(&quot;k&quot;); // return False
streamChecker.query(&quot;l&quot;); // return True, because &#39;kl&#39; is in the wordlist
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 2000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 200</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
	<li><code>letter</code> is a lowercase English letter.</li>
	<li>At most <code>4 * 10<sup>4</sup></code> calls will be made to query.</li>
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
