# [439. Ternary Expression Parser](https://leetcode.com/problems/ternary-expression-parser)

[中文文档](/solution/0400-0499/0439.Ternary%20Expression%20Parser/README.md)

## Description

<p>Given a string <code>expression</code> representing arbitrarily nested ternary expressions, evaluate the expression, and return <em>the result of it</em>.</p>

<p>You can always assume that the given expression is valid and only contains digits, <code>&#39;?&#39;</code>, <code>&#39;:&#39;</code>, <code>&#39;T&#39;</code>, and <code>&#39;F&#39;</code> where <code>&#39;T&#39;</code> is true and <code>&#39;F&#39;</code> is false. All the numbers in the expression are <strong>one-digit</strong> numbers (i.e., in the range <code>[0, 9]</code>).</p>

<p>The conditional expressions group right-to-left (as usual in most languages), and the result of the expression will always evaluate to either a digit, <code>&#39;T&#39;</code> or <code>&#39;F&#39;</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;T?2:3&quot;
<strong>Output:</strong> &quot;2&quot;
<strong>Explanation:</strong> If true, then result is 2; otherwise result is 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;F?1:T?4:5&quot;
<strong>Output:</strong> &quot;4&quot;
<strong>Explanation:</strong> The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
&quot;(F ? 1 : (T ? 4 : 5))&quot; --&gt; &quot;(F ? 1 : 4)&quot; --&gt; &quot;4&quot;
or &quot;(F ? 1 : (T ? 4 : 5))&quot; --&gt; &quot;(T ? 4 : 5)&quot; --&gt; &quot;4&quot;
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;T?T?F:5:3&quot;
<strong>Output:</strong> &quot;F&quot;
<strong>Explanation:</strong> The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
&quot;(T ? (T ? F : 5) : 3)&quot; --&gt; &quot;(T ? F : 3)&quot; --&gt; &quot;F&quot;
&quot;(T ? (T ? F : 5) : 3)&quot; --&gt; &quot;(T ? F : 5)&quot; --&gt; &quot;F&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>5 &lt;= expression.length &lt;= 10<sup>4</sup></code></li>
	<li><code>expression</code> consists of digits, <code>&#39;T&#39;</code>, <code>&#39;F&#39;</code>, <code>&#39;?&#39;</code>, and <code>&#39;:&#39;</code>.</li>
	<li>It is <strong>guaranteed</strong> that <code>expression</code> is a valid ternary expression and that each number is a <strong>one-digit number</strong>.</li>
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
