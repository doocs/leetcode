# [1258. Synonymous Sentences](https://leetcode.com/problems/synonymous-sentences)

[中文文档](/solution/1200-1299/1258.Synonymous%20Sentences/README.md)

## Description

Given a list of pairs of equivalent words <code>synonyms</code> and a sentence <code>text</code>, Return all possible synonymous sentences <strong>sorted lexicographically</strong>.
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:
</strong>synonyms = [[&quot;happy&quot;,&quot;joy&quot;],[&quot;sad&quot;,&quot;sorrow&quot;],[&quot;joy&quot;,&quot;cheerful&quot;]],
text = &quot;I am happy today but was sad yesterday&quot;
<strong>Output:
</strong>[&quot;I am cheerful today but was sad yesterday&quot;,
&quot;I am cheerful today but was sorrow yesterday&quot;,
&quot;I am happy today but was sad yesterday&quot;,
&quot;I am happy today but was sorrow yesterday&quot;,
&quot;I am joy today but was sad yesterday&quot;,
&quot;I am joy today but was sorrow yesterday&quot;]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> synonyms = [[&quot;happy&quot;,&quot;joy&quot;],[&quot;cheerful&quot;,&quot;glad&quot;]], text = &quot;I am happy today but was sad yesterday&quot;
<strong>Output:</strong> [&quot;I am happy today but was sad yesterday&quot;,&quot;I am joy today but was sad yesterday&quot;]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> synonyms = [[&quot;a&quot;,&quot;b&quot;],[&quot;c&quot;,&quot;d&quot;],[&quot;e&quot;,&quot;f&quot;]], text = &quot;a c e&quot;
<strong>Output:</strong> [&quot;a c e&quot;,&quot;a c f&quot;,&quot;a d e&quot;,&quot;a d f&quot;,&quot;b c e&quot;,&quot;b c f&quot;,&quot;b d e&quot;,&quot;b d f&quot;]
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> synonyms = [[&quot;a&quot;,&quot;QrbCl&quot;]], text = &quot;d QrbCl ya ya NjZQ&quot;
<strong>Output:</strong> [&quot;d QrbCl ya ya NjZQ&quot;,&quot;d a ya ya NjZQ&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;=&nbsp;synonyms.length &lt;= 10</code></li>
	<li><code>synonyms[i].length == 2</code></li>
	<li><code>synonyms[i][0] != synonyms[i][1]</code></li>
	<li>All words consist of at most <code>10</code> English letters only.</li>
	<li><code>text</code>&nbsp;is a single space separated sentence of at most <code>10</code> words.</li>
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
