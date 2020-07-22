# [966. Vowel Spellchecker](https://leetcode.com/problems/vowel-spellchecker)

[中文文档](/solution/0900-0999/0966.Vowel%20Spellchecker/README.md)

## Description
<p>Given a&nbsp;<code>wordlist</code>, we want to implement a spellchecker that converts a query word into a correct word.</p>



<p>For a given <code>query</code> word, the spell checker handles two categories of spelling mistakes:</p>



<ul>

	<li>Capitalization: If the query matches a word in the wordlist (<strong>case-insensitive</strong>), then the query word is returned with the same case as the case in the wordlist.



	<ul>

		<li>Example: <code>wordlist = [&quot;yellow&quot;]</code>, <code>query = &quot;YellOw&quot;</code>: <code>correct = &quot;yellow&quot;</code></li>

		<li>Example: <code>wordlist = [&quot;Yellow&quot;]</code>, <code>query = &quot;yellow&quot;</code>: <code>correct = &quot;Yellow&quot;</code></li>

		<li>Example: <code>wordlist = [&quot;yellow&quot;]</code>, <code>query = &quot;yellow&quot;</code>: <code>correct = &quot;yellow&quot;</code></li>

	</ul>

	</li>

	<li>Vowel Errors: If after replacing the vowels (&#39;a&#39;, &#39;e&#39;, &#39;i&#39;, &#39;o&#39;, &#39;u&#39;) of the query word with any vowel individually, it matches a word in the wordlist (<strong>case-insensitive</strong>), then the query word is returned with the same case as the match in the wordlist.

	<ul>

		<li>Example: <code>wordlist = [&quot;YellOw&quot;]</code>, <code>query = &quot;yollow&quot;</code>: <code>correct = &quot;YellOw&quot;</code></li>

		<li>Example: <code>wordlist = [&quot;YellOw&quot;]</code>, <code>query = &quot;yeellow&quot;</code>: <code>correct = &quot;&quot;</code> (no match)</li>

		<li>Example: <code>wordlist = [&quot;YellOw&quot;]</code>, <code>query = &quot;yllw&quot;</code>: <code>correct = &quot;&quot;</code> (no match)</li>

	</ul>

	</li>

</ul>



<p>In addition, the spell checker operates under the following precedence rules:</p>



<ul>

	<li>When the query exactly matches a word in the wordlist (<strong>case-sensitive</strong>), you should return the same word back.</li>

	<li>When the query matches a word up to capitlization, you should return the first such match in the wordlist.</li>

	<li>When the query matches a word up to vowel errors, you should return the first such match in the wordlist.</li>

	<li>If the query has no matches in the wordlist, you should return the empty string.</li>

</ul>



<p>Given some <code>queries</code>, return a&nbsp;list of words <code>answer</code>, where <code>answer[i]</code>&nbsp;is&nbsp;the correct word for <code>query = queries[i]</code>.</p>



<p>&nbsp;</p>



<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong>wordlist = <span id="example-input-1-1">[&quot;KiTe&quot;,&quot;kite&quot;,&quot;hare&quot;,&quot;Hare&quot;]</span>, queries = <span id="example-input-1-2">[&quot;kite&quot;,&quot;Kite&quot;,&quot;KiTe&quot;,&quot;Hare&quot;,&quot;HARE&quot;,&quot;Hear&quot;,&quot;hear&quot;,&quot;keti&quot;,&quot;keet&quot;,&quot;keto&quot;]</span>

<strong>Output: </strong><span id="example-output-1">[&quot;kite&quot;,&quot;KiTe&quot;,&quot;KiTe&quot;,&quot;Hare&quot;,&quot;hare&quot;,&quot;&quot;,&quot;&quot;,&quot;KiTe&quot;,&quot;&quot;,&quot;KiTe&quot;]</span></pre>



<p>&nbsp;</p>



<p><strong>Note:</strong></p>



<ul>

	<li><code>1 &lt;= wordlist.length &lt;= 5000</code></li>

	<li><code>1 &lt;= queries.length &lt;= 5000</code></li>

	<li><code>1 &lt;= wordlist[i].length &lt;= 7</code></li>

	<li><code>1 &lt;= queries[i].length &lt;= 7</code></li>

	<li>All strings in <code>wordlist</code> and <code>queries</code> consist only of <strong>english</strong>&nbsp;letters.</li>

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