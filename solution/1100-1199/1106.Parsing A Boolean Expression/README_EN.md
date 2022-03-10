# [1106. Parsing A Boolean Expression](https://leetcode.com/problems/parsing-a-boolean-expression)

[中文文档](/solution/1100-1199/1106.Parsing%20A%20Boolean%20Expression/README.md)

## Description

<p>Return the result of evaluating a given boolean <code>expression</code>, represented as a string.</p>

<p>An expression can either be:</p>

<ul>
	<li><code>&quot;t&quot;</code>, evaluating to <code>True</code>;</li>
	<li><code>&quot;f&quot;</code>, evaluating to <code>False</code>;</li>
	<li><code>&quot;!(expr)&quot;</code>, evaluating to the logical NOT of the inner expression <code>expr</code>;</li>
	<li><code>&quot;&amp;(expr1,expr2,...)&quot;</code>, evaluating to the logical AND of 2 or more inner expressions <code>expr1, expr2, ...</code>;</li>
	<li><code>&quot;|(expr1,expr2,...)&quot;</code>, evaluating to the logical OR of 2 or more inner expressions <code>expr1, expr2, ...</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;!(f)&quot;
<strong>Output:</strong> true
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;|(f,t)&quot;
<strong>Output:</strong> true
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;&amp;(t,f)&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= expression.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>expression[i]</code> consists of characters in <code>{&#39;(&#39;, &#39;)&#39;, &#39;&amp;&#39;, &#39;|&#39;, &#39;!&#39;, &#39;t&#39;, &#39;f&#39;, &#39;,&#39;}</code>.</li>
	<li><code>expression</code> is a valid expression representing a boolean, as given in the description.</li>
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
