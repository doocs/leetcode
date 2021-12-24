# [756. Pyramid Transition Matrix](https://leetcode.com/problems/pyramid-transition-matrix)

[中文文档](/solution/0700-0799/0756.Pyramid%20Transition%20Matrix/README.md)

## Description

<p>We are stacking blocks to form a pyramid. Each block has a color which is a one-letter string.</p>

<p>We are allowed to place any color block <code>C</code> on top of two adjacent blocks of colors <code>A</code> and <code>B</code>, if and only if <code>ABC</code> is an allowed triple.</p>

<p>We start with a bottom row of <code>bottom</code>, represented as a single string. We also start with a list of allowed triples <code>allowed</code>. Each allowed triple is represented as a string of length <code>3</code>.</p>

<p>Return <code>true</code> <em>if we can build the pyramid all the way to the top, otherwise</em> <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> bottom = &quot;BCD&quot;, allowed = [&quot;BCG&quot;,&quot;CDE&quot;,&quot;GEA&quot;,&quot;FFF&quot;]
<strong>Output:</strong> true
<strong>Explanation:</strong>
We can stack the pyramid like this:
    A
   / \
  G   E
 / \ / \
B   C   D

We are allowed to place G on top of B and C because BCG is an allowed triple.  Similarly, we can place E on top of C and D, then A on top of G and E.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> bottom = &quot;AABA&quot;, allowed = [&quot;AAA&quot;,&quot;AAB&quot;,&quot;ABA&quot;,&quot;ABB&quot;,&quot;BAC&quot;]
<strong>Output:</strong> false
<strong>Explanation:</strong>
We cannot stack the pyramid to the top.
Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= bottom.length &lt;= 8</code></li>
	<li><code>0 &lt;= allowed.length &lt;= 200</code></li>
	<li><code>allowed[i].length == 3</code></li>
	<li>The letters in all input strings are from the set <code>{&#39;A&#39;, &#39;B&#39;, &#39;C&#39;, &#39;D&#39;, &#39;E&#39;, &#39;F&#39;, &#39;G&#39;}</code>.</li>
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
