# [1003. Check If Word Is Valid After Substitutions](https://leetcode.com/problems/check-if-word-is-valid-after-substitutions)

[中文文档](/solution/1000-1099/1003.Check%20If%20Word%20Is%20Valid%20After%20Substitutions/README.md)

## Description

<p>Given a string <code>s</code>, determine if it is <strong>valid</strong>.</p>

<p>A string <code>s</code> is <strong>valid</strong> if, starting with an empty string <code>t = &quot;&quot;</code>, you can <strong>transform </strong><code>t</code><strong> into </strong><code>s</code> after performing the following operation <strong>any number of times</strong>:</p>

<ul>
	<li>Insert string <code>&quot;abc&quot;</code> into any position in <code>t</code>. More formally, <code>t</code> becomes <code>t<sub>left</sub> + &quot;abc&quot; + t<sub>right</sub></code>, where <code>t == t<sub>left</sub> + t<sub>right</sub></code>. Note that <code>t<sub>left</sub></code> and <code>t<sub>right</sub></code> may be <strong>empty</strong>.</li>
</ul>

<p>Return <code>true</code> <em>if </em><code>s</code><em> is a <strong>valid</strong> string, otherwise, return</em> <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabcbc&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong>
&quot;&quot; -&gt; &quot;<u>abc</u>&quot; -&gt; &quot;a<u>abc</u>bc&quot;
Thus, &quot;aabcbc&quot; is valid.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcabcababcc&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong>
&quot;&quot; -&gt; &quot;<u>abc</u>&quot; -&gt; &quot;abc<u>abc</u>&quot; -&gt; &quot;abcabc<u>abc</u>&quot; -&gt; &quot;abcabcab<u>abc</u>c&quot;
Thus, &quot;abcabcababcc&quot; is valid.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abccba&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible to get &quot;abccba&quot; using the operation.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>s</code> consists of letters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code></li>
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
