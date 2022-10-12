# [1597. Build Binary Expression Tree From Infix Expression](https://leetcode.com/problems/build-binary-expression-tree-from-infix-expression)

[中文文档](/solution/1500-1599/1597.Build%20Binary%20Expression%20Tree%20From%20Infix%20Expression/README.md)

## Description

<p>A <strong><a href="https://en.wikipedia.org/wiki/Binary_expression_tree" target="_blank">binary expression tree</a></strong> is a kind of binary tree used to represent arithmetic expressions. Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (numbers), and internal nodes (nodes with 2 children) correspond to the operators <code>&#39;+&#39;</code> (addition), <code>&#39;-&#39;</code> (subtraction), <code>&#39;*&#39;</code> (multiplication), and <code>&#39;/&#39;</code> (division).</p>

<p>For each internal node with operator <code>o</code>, the <a href="https://en.wikipedia.org/wiki/Infix_notation" target="_blank"><strong>infix expression</strong></a> it represents is <code>(A o B)</code>, where <code>A</code> is the expression the left subtree represents and <code>B</code> is the expression the right subtree represents.</p>

<p>You are given a string <code>s</code>, an <strong>infix expression</strong> containing operands, the operators described above, and parentheses <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code>.</p>

<p>Return <em>any valid <strong>binary expression tree</strong>, whose <strong><a href="https://en.wikipedia.org/wiki/Tree_traversal#In-order_(LNR)" target="_blank">in-order traversal</a></strong> reproduces </em><code>s</code> <em>after omitting the parenthesis from it.</em></p>

<p><strong>Please note that order of operations applies in </strong><code>s</code><strong>.</strong> That is, expressions in parentheses are evaluated first, and multiplication and division happen before addition and subtraction.</p>

<p>Operands must also appear in the <strong>same order</strong> in both <code>s</code> and the in-order traversal of the tree.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1597.Build%20Binary%20Expression%20Tree%20From%20Infix%20Expression/images/ex1-4.png" style="width: 250px; height: 161px;" />
<pre>
<strong>Input:</strong> s = &quot;3*4-2*5&quot;
<strong>Output:</strong> [-,*,*,3,4,2,5]
<strong>Explanation:</strong> The tree above is the only valid tree whose inorder traversal produces s.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1597.Build%20Binary%20Expression%20Tree%20From%20Infix%20Expression/images/ex1-2.png" style="width: 150px; height: 210px;" />
<pre>
<strong>Input:</strong> s = &quot;2-3/(5*2)+1&quot;
<strong>Output:</strong> [+,-,1,2,/,null,null,null,null,3,*,null,null,5,2]
<strong>Explanation:</strong> The inorder traversal of the tree above is 2-3/5*2+1 which is the same as s without the parenthesis. The tree also produces the correct result and its operands are in the same order as they appear in s.
The tree below is also a valid binary expression tree with the same inorder traversal as s, but it not a valid answer because it does not evaluate to the same value.
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1597.Build%20Binary%20Expression%20Tree%20From%20Infix%20Expression/images/ex1-1.png" style="width: 201px; height: 281px;" />
The third tree below is also not valid. Although it produces the same result and is equivalent to the above trees, its inorder traversal does not produce s and its operands are not in the same order as s.
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1597.Build%20Binary%20Expression%20Tree%20From%20Infix%20Expression/images/ex1-3.png" style="width: 281px; height: 281px;" />
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1+2+3+4+5&quot;
<strong>Output:</strong> [+,+,5,+,4,null,null,+,3,null,null,1,2]
<strong>Explanation:</strong> The tree [+,+,5,+,+,null,null,1,2,3,4] is also one of many other valid trees.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of digits and the characters <code>&#39;+&#39;</code>, <code>&#39;-&#39;</code>, <code>&#39;*&#39;</code>, and <code>&#39;/&#39;</code>.</li>
	<li>Operands in <code>s</code> are <strong>exactly</strong> 1 digit.</li>
	<li>It is guaranteed that <code>s</code> is a valid expression.</li>
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
