# [1410. HTML Entity Parser](https://leetcode.com/problems/html-entity-parser)

[中文文档](/solution/1400-1499/1410.HTML%20Entity%20Parser/README.md)

## Description

<p><strong>HTML entity parser</strong> is the parser that takes HTML code as input and replace all the entities of the special characters by the characters itself.</p>

<p>The special characters and their entities for HTML are:</p>

<ul>
	<li><strong>Quotation Mark:</strong>&nbsp;the entity is <code>&amp;quot;</code> and&nbsp;symbol character is <code>&quot;</code>.</li>
	<li><strong>Single Quote&nbsp;Mark:</strong>&nbsp;the entity is <code>&amp;apos;</code> and&nbsp;symbol character is <code>&#39;</code>.</li>
	<li><strong>Ampersand:</strong>&nbsp;the entity is <code>&amp;amp;</code> and symbol character is <code>&amp;</code>.</li>
	<li><strong>Greater Than Sign:</strong>&nbsp;the entity is <code>&amp;gt;</code>&nbsp;and symbol character is <code>&gt;</code>.</li>
	<li><strong>Less Than Sign:</strong>&nbsp;the entity is <code>&amp;lt;</code>&nbsp;and symbol character is <code>&lt;</code>.</li>
	<li><strong>Slash:</strong>&nbsp;the entity is <code>&amp;frasl;</code> and&nbsp;symbol character is <code>/</code>.</li>
</ul>

<p>Given the input <code>text</code> string to the HTML parser, you have to implement the entity parser.</p>

<p>Return <em>the text</em> after replacing the entities by the special characters.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;&amp;amp; is an HTML entity but &amp;ambassador; is not.&quot;
<strong>Output:</strong> &quot;&amp; is an HTML entity but &amp;ambassador; is not.&quot;
<strong>Explanation:</strong> The parser will replace the &amp;amp; entity by &amp;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;and I quote: &amp;quot;...&amp;quot;&quot;
<strong>Output:</strong> &quot;and I quote: \&quot;...\&quot;&quot;
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;Stay home! Practice on Leetcode :)&quot;
<strong>Output:</strong> &quot;Stay home! Practice on Leetcode :)&quot;
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;x &amp;gt; y &amp;amp;&amp;amp; x &amp;lt; y is always false&quot;
<strong>Output:</strong> &quot;x &gt; y &amp;&amp; x &lt; y is always false&quot;
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;leetcode.com&amp;frasl;problemset&amp;frasl;all&quot;
<strong>Output:</strong> &quot;leetcode.com/problemset/all&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 10^5</code></li>
	<li>The string may contain any possible characters out of all the 256&nbsp;ASCII characters.</li>
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
