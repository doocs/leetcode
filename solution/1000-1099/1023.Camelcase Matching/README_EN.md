# [1023. Camelcase Matching](https://leetcode.com/problems/camelcase-matching)

[中文文档](/solution/1000-1099/1023.Camelcase%20Matching/README.md)

## Description

<p>A query word matches a given <code>pattern</code> if we can insert <strong>lowercase</strong> letters to the pattern word so that it equals the <code>query</code>. (We may insert each character at any position, and may insert 0 characters.)</p>

<p>Given a list of <code>queries</code>, and a <code>pattern</code>, return an <code>answer</code> list of booleans, where <code>answer[i]</code> is true if and only if <code>queries[i]</code> matches the <code>pattern</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>queries = <span id="example-input-1-1">[&quot;FooBar&quot;,&quot;FooBarTest&quot;,&quot;FootBall&quot;,&quot;FrameBuffer&quot;,&quot;ForceFeedBack&quot;]</span>, pattern = <span id="example-input-1-2">&quot;FB&quot;</span>

<strong>Output: </strong><span id="example-output-1">[true,false,true,true,false]</span>

<strong>Explanation: </strong>

&quot;FooBar&quot; can be generated like this &quot;F&quot; + &quot;oo&quot; + &quot;B&quot; + &quot;ar&quot;.

&quot;FootBall&quot; can be generated like this &quot;F&quot; + &quot;oot&quot; + &quot;B&quot; + &quot;all&quot;.

&quot;FrameBuffer&quot; can be generated like this &quot;F&quot; + &quot;rame&quot; + &quot;B&quot; + &quot;uffer&quot;.</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>queries = <span id="example-input-2-1">[&quot;FooBar&quot;,&quot;FooBarTest&quot;,&quot;FootBall&quot;,&quot;FrameBuffer&quot;,&quot;ForceFeedBack&quot;]</span>, pattern = <span id="example-input-2-2">&quot;FoBa&quot;</span>

<strong>Output: </strong><span id="example-output-2">[true,false,true,false,false]</span>

<strong>Explanation: </strong>

&quot;FooBar&quot; can be generated like this &quot;Fo&quot; + &quot;o&quot; + &quot;Ba&quot; + &quot;r&quot;.

&quot;FootBall&quot; can be generated like this &quot;Fo&quot; + &quot;ot&quot; + &quot;Ba&quot; + &quot;ll&quot;.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong>queries = <span id="example-input-3-1">[&quot;FooBar&quot;,&quot;FooBarTest&quot;,&quot;FootBall&quot;,&quot;FrameBuffer&quot;,&quot;ForceFeedBack&quot;]</span>, pattern = <span id="example-input-3-2">&quot;FoBaT&quot;</span>

<strong>Output: </strong><span id="example-output-3">[false,true,false,false,false]</span>

<strong>Explanation: </strong>

&quot;FooBarTest&quot; can be generated like this &quot;Fo&quot; + &quot;o&quot; + &quot;Ba&quot; + &quot;r&quot; + &quot;T&quot; + &quot;est&quot;.

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= queries.length &lt;= 100</code></li>
	<li><code>1 &lt;= queries[i].length &lt;= 100</code></li>
	<li><code>1 &lt;= pattern.length &lt;= 100</code></li>
	<li>All strings consists only of lower and upper case English letters.</li>
</ol>

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
