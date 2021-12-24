# [1307. Verbal Arithmetic Puzzle](https://leetcode.com/problems/verbal-arithmetic-puzzle)

[中文文档](/solution/1300-1399/1307.Verbal%20Arithmetic%20Puzzle/README.md)

## Description

<p>Given an equation, represented by <code>words</code> on left side and the <code>result</code> on right side.</p>

<p>You need to check if the equation is solvable&nbsp;under the following rules:</p>

<ul>
	<li>Each character is decoded as one digit (0 - 9).</li>
	<li>Every pair of different characters they must map to different digits.</li>
	<li>Each <code>words[i]</code> and <code>result</code>&nbsp;are decoded as one number <strong>without</strong> leading zeros.</li>
	<li>Sum of numbers on left side (<code>words</code>) will equal to the number on right side (<code>result</code>).&nbsp;</li>
</ul>

<p>Return <code>True</code>&nbsp;if the equation is solvable otherwise&nbsp;return&nbsp;<code>False</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;SEND&quot;,&quot;MORE&quot;], result = &quot;MONEY&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> Map &#39;S&#39;-&gt; 9, &#39;E&#39;-&gt;5, &#39;N&#39;-&gt;6, &#39;D&#39;-&gt;7, &#39;M&#39;-&gt;1, &#39;O&#39;-&gt;0, &#39;R&#39;-&gt;8, &#39;Y&#39;-&gt;&#39;2&#39;
Such that: &quot;SEND&quot; + &quot;MORE&quot; = &quot;MONEY&quot; ,  9567 + 1085 = 10652</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;SIX&quot;,&quot;SEVEN&quot;,&quot;SEVEN&quot;], result = &quot;TWENTY&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> Map &#39;S&#39;-&gt; 6, &#39;I&#39;-&gt;5, &#39;X&#39;-&gt;0, &#39;E&#39;-&gt;8, &#39;V&#39;-&gt;7, &#39;N&#39;-&gt;2, &#39;T&#39;-&gt;1, &#39;W&#39;-&gt;&#39;3&#39;, &#39;Y&#39;-&gt;4
Such that: &quot;SIX&quot; + &quot;SEVEN&quot; + &quot;SEVEN&quot; = &quot;TWENTY&quot; ,  650 + 68782 + 68782 = 138214</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;THIS&quot;,&quot;IS&quot;,&quot;TOO&quot;], result = &quot;FUNNY&quot;
<strong>Output:</strong> true
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;LEET&quot;,&quot;CODE&quot;], result = &quot;POINT&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= words.length &lt;= 5</code></li>
	<li><code>1 &lt;= words[i].length, result.length &lt;= 7</code></li>
	<li><code>words[i], result</code> contain only uppercase English letters.</li>
	<li>The number of different characters used in the expression is at most <code>10</code>.</li>
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
