# [527. Word Abbreviation](https://leetcode.com/problems/word-abbreviation)

[中文文档](/solution/0500-0599/0527.Word%20Abbreviation/README.md)

## Description

<p>Given an array of <strong>distinct</strong> strings <code>words</code>, return <em>the minimal possible <strong>abbreviations</strong> for every word</em>.</p>

<p>The following are the rules for a string abbreviation:</p>

<ol>
	<li>The <strong>initial</strong> abbreviation for each word is: the first character, then the number of characters in between, followed by the last character.</li>
	<li>If more than one word shares the <strong>same</strong> abbreviation, then perform the following operation:
	<ul>
		<li><strong>Increase</strong> the prefix (characters in the first part) of each of their abbreviations by <code>1</code>.
		<ul>
			<li>For example, say you start with the words <code>[&quot;abcdef&quot;,&quot;abndef&quot;]</code> both initially abbreviated as <code>&quot;a4f&quot;</code>. Then, a sequence of operations would be <code>[&quot;a4f&quot;,&quot;a4f&quot;]</code> -&gt; <code>[&quot;ab3f&quot;,&quot;ab3f&quot;]</code> -&gt; <code>[&quot;abc2f&quot;,&quot;abn2f&quot;]</code>.</li>
		</ul>
		</li>
		<li>This operation is repeated until every abbreviation is <strong>unique</strong>.</li>
	</ul>
	</li>
	<li>At the end, if an abbreviation did not make a word shorter, then keep it as the original word.</li>
</ol>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> words = ["like","god","internal","me","internet","interval","intension","face","intrusion"]
<strong>Output:</strong> ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> words = ["aa","aaa"]
<strong>Output:</strong> ["aa","aaa"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 400</code></li>
	<li><code>2 &lt;= words[i].length &lt;= 400</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
	<li>All the strings of <code>words</code> are <strong>unique</strong>.</li>
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
