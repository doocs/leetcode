# [1628. Design an Expression Tree With Evaluate Function](https://leetcode.com/problems/design-an-expression-tree-with-evaluate-function)

[中文文档](/solution/1600-1699/1628.Design%20an%20Expression%20Tree%20With%20Evaluate%20Function/README.md)

## Description

<p>Given the <code>postfix</code> tokens of an arithmetic expression, build and return <em>the binary expression tree that represents this expression.</em></p>



<p><b>Postfix</b> notation is a notation for writing arithmetic expressions in which the operands (numbers) appear before their operators. For example, the postfix tokens of the expression <code>4*(5-(7+2))</code> are represented in the array <code>postfix = [&quot;4&quot;,&quot;5&quot;,&quot;7&quot;,&quot;2&quot;,&quot;+&quot;,&quot;-&quot;,&quot;*&quot;]</code>.</p>



<p>The class <code>Node</code> is an interface you should use to implement the binary expression tree. The returned tree will be tested using the <code>evaluate</code> function, which is supposed to evaluate the tree&#39;s value. You should not remove the <code>Node</code> class; however, you can modify it as you wish, and you can define other classes to implement it if needed.</p>



<p>A <strong><a href="https://en.wikipedia.org/wiki/Binary_expression_tree" target="_blank">binary expression tree</a></strong> is a kind of binary tree used to represent arithmetic expressions. Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (numbers), and internal nodes (nodes with two children) correspond to the operators <code>&#39;+&#39;</code> (addition), <code>&#39;-&#39;</code> (subtraction), <code>&#39;*&#39;</code> (multiplication), and <code>&#39;/&#39;</code> (division).</p>



<p>It&#39;s guaranteed that no subtree will yield a value that exceeds <code>10<sup>9</sup></code> in absolute value, and all the operations are valid (i.e., no division by zero).</p>



<p><strong>Follow up:</strong> Could you design the expression tree such that it is more modular? For example, is your design able to support additional operators without making changes to your existing <code>evaluate</code> implementation?</p>



<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>



<p><strong><img alt="" src="/solution/1600-1699/1628.Design an Expression Tree With Evaluate Function/images/untitled-diagram.png" style="width: 242px; height: 241px;" /></strong></p>



<pre>

<strong>Input:</strong> s = [&quot;3&quot;,&quot;4&quot;,&quot;+&quot;,&quot;2&quot;,&quot;*&quot;,&quot;7&quot;,&quot;/&quot;]

<strong>Output:</strong> 2

<strong>Explanation:</strong> this expression evaluates to the above binary tree with expression (<code>(3+4)*2)/7) = 14/7 = 2.</code>

</pre>



<p><strong>Example 2:</strong></p>



<p><strong><img alt="" src="/solution/1600-1699/1628.Design an Expression Tree With Evaluate Function/images/untitled-diagram2.png" style="width: 222px; height: 232px;" /></strong></p>



<pre>

<strong>Input:</strong> s = [&quot;4&quot;,&quot;5&quot;,&quot;7&quot;,&quot;2&quot;,&quot;+&quot;,&quot;-&quot;,&quot;*&quot;]

<strong>Output:</strong> -16

<strong>Explanation:</strong> this expression evaluates to the above binary tree with expression 4*(5-<code>(2+7)) = 4*(-4) = -16.</code>

</pre>



<p><strong>Example 3:</strong></p>



<pre>

<strong>Input:</strong> s = [&quot;4&quot;,&quot;2&quot;,&quot;+&quot;,&quot;3&quot;,&quot;5&quot;,&quot;1&quot;,&quot;-&quot;,&quot;*&quot;,&quot;+&quot;]

<strong>Output:</strong> 18

</pre>



<p><strong>Example 4:</strong></p>



<pre>

<strong>Input:</strong> s = [&quot;100&quot;,&quot;200&quot;,&quot;+&quot;,&quot;2&quot;,&quot;/&quot;,&quot;5&quot;,&quot;*&quot;,&quot;7&quot;,&quot;+&quot;]

<strong>Output:</strong> 757

</pre>



<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>



<ul>
	<li><code>1 &lt;= s.length &lt; 100</code></li>
	<li><code>s.length</code> is odd.</li>
	<li><code>s</code> consists of numbers and the characters <code>&#39;+&#39;</code>, <code>&#39;-&#39;</code>, <code>&#39;*&#39;</code>, and <code>&#39;/&#39;</code>.</li>
	<li>If <code>s[i]</code> is a number, its integer representation is no more than <code>10<sup>5</sup></code>.</li>
	<li>It is guaranteed that <code>s</code> is a valid expression.</li>
	<li>The absolute value of the result and intermediate values will not exceed <code>10<sup>9</sup></code>.</li>
	<li>It is guaranteed that no expression will include division by zero.</li>
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
