# [1096. Brace Expansion II](https://leetcode.com/problems/brace-expansion-ii)

[中文文档](/solution/1000-1099/1096.Brace%20Expansion%20II/README.md)

## Description

<p>Under a grammar given below, strings can represent a set of lowercase words.&nbsp; Let&#39;s&nbsp;use <code>R(expr)</code>&nbsp;to denote the <strong>set</strong> of words the expression represents.</p>

<p>Grammar can best be understood through simple examples:</p>

<ul>
	<li>Single letters represent a singleton set containing that word.
	<ul>
		<li><code>R(&quot;a&quot;) = {&quot;a&quot;}</code></li>
		<li><code>R(&quot;w&quot;) = {&quot;w&quot;}</code></li>
	</ul>
	</li>
	<li>When we take a comma delimited list of 2 or more expressions, we take the union of possibilities.
	<ul>
		<li><code>R(&quot;{a,b,c}&quot;) = {&quot;a&quot;,&quot;b&quot;,&quot;c&quot;}</code></li>
		<li><code>R(&quot;{{a,b},{b,c}}&quot;) = {&quot;a&quot;,&quot;b&quot;,&quot;c&quot;}</code>&nbsp;(notice the final set only contains each word at most once)</li>
	</ul>
	</li>
	<li>When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression.
	<ul>
		<li><code>R(&quot;{a,b}{c,d}&quot;) = {&quot;ac&quot;,&quot;ad&quot;,&quot;bc&quot;,&quot;bd&quot;}</code></li>
		<li><code>R(&quot;a{b,c}{d,e}f{g,h}&quot;)&nbsp;= {&quot;abdfg&quot;, &quot;abdfh&quot;, &quot;abefg&quot;, &quot;abefh&quot;, &quot;acdfg&quot;, &quot;acdfh&quot;, &quot;acefg&quot;, &quot;acefh&quot;}</code></li>
	</ul>
	</li>
</ul>

<p>Formally, the 3 rules for our grammar:</p>

<ul>
	<li>For every lowercase letter <code>x</code>, we have <code>R(x) = {x}</code></li>
	<li>For expressions <code>e_1, e_2, ... , e_k</code>&nbsp;with <code>k &gt;= 2</code>, we have <code>R({e_1,e_2,...}) = R(e_1)&nbsp;&cup; R(e_2)&nbsp;&cup; ...</code></li>
	<li>For&nbsp;expressions <code>e_1</code> and <code>e_2</code>, we have <code>R(e_1 + e_2) = {a + b for (a, b) in&nbsp;R(e_1)&nbsp;&times; R(e_2)}</code>, where + denotes concatenation, and &times; denotes the cartesian product.</li>
</ul>

<p>Given an <code>expression</code> representing a set of words under the given grammar, return the&nbsp;sorted list of words that the expression represents.</p>

<p>&nbsp;</p>

<div>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: </strong><span id="example-input-1-1">&quot;{a,b}{c,{d,e}}&quot;</span>
<strong>Output: </strong><span id="example-output-1">[&quot;ac&quot;,&quot;ad&quot;,&quot;ae&quot;,&quot;bc&quot;,&quot;bd&quot;,&quot;be&quot;]</span>
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre>
<strong>Input: </strong><span>&quot;{{a,z},a{b,c},{ab,z}}&quot;</span>
<strong>Output: </strong><span>[&quot;a&quot;,&quot;ab&quot;,&quot;ac&quot;,&quot;z&quot;]</span>
<strong>Explanation: </strong>Each distinct word is written only once in the final answer.
</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ol>
	<li><code>1 &lt;= expression.length &lt;= 60</code></li>
	<li><code>expression[i]</code> consists of <code>&#39;{&#39;</code>, <code>&#39;}&#39;</code>, <code>&#39;,&#39;</code>or lowercase English letters.</li>
	<li>The given&nbsp;<code>expression</code>&nbsp;represents a set of words based on the grammar given in the description.</li>
</ol>
</div>
</div>

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
