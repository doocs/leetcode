# [736. Parse Lisp Expression](https://leetcode.com/problems/parse-lisp-expression)

[中文文档](/solution/0700-0799/0736.Parse%20Lisp%20Expression/README.md)

## Description

<p>You are given a string expression representing a Lisp-like expression to return the integer value of.</p>

<p>The syntax for these expressions is given as follows.</p>

<ul>
	<li>An expression is either an integer, let expression, add expression, mult expression, or an assigned variable. Expressions always evaluate to a single integer.</li>
	<li>(An integer could be positive or negative.)</li>
	<li>A let expression takes the form <code>&quot;(let v<sub>1</sub> e<sub>1</sub> v<sub>2</sub> e<sub>2</sub> ... v<sub>n</sub> e<sub>n</sub> expr)&quot;</code>, where let is always the string <code>&quot;let&quot;</code>, then there are one or more pairs of alternating variables and expressions, meaning that the first variable <code>v<sub>1</sub></code> is assigned the value of the expression <code>e<sub>1</sub></code>, the second variable <code>v<sub>2</sub></code> is assigned the value of the expression <code>e<sub>2</sub></code>, and so on sequentially; and then the value of this let expression is the value of the expression <code>expr</code>.</li>
	<li>An add expression takes the form <code>&quot;(add e<sub>1</sub> e<sub>2</sub>)&quot;</code> where add is always the string <code>&quot;add&quot;</code>, there are always two expressions <code>e<sub>1</sub></code>, <code>e<sub>2</sub></code> and the result is the addition of the evaluation of <code>e<sub>1</sub></code> and the evaluation of <code>e<sub>2</sub></code>.</li>
	<li>A mult expression takes the form <code>&quot;(mult e<sub>1</sub> e<sub>2</sub>)&quot;</code> where mult is always the string <code>&quot;mult&quot;</code>, there are always two expressions <code>e<sub>1</sub></code>, <code>e<sub>2</sub></code> and the result is the multiplication of the evaluation of e1 and the evaluation of e2.</li>
	<li>For this question, we will use a smaller subset of variable names. A variable starts with a lowercase letter, then zero or more lowercase letters or digits. Additionally, for your convenience, the names <code>&quot;add&quot;</code>, <code>&quot;let&quot;</code>, and <code>&quot;mult&quot;</code> are protected and will never be used as variable names.</li>
	<li>Finally, there is the concept of scope. When an expression of a variable name is evaluated, within the context of that evaluation, the innermost scope (in terms of parentheses) is checked first for the value of that variable, and then outer scopes are checked sequentially. It is guaranteed that every expression is legal. Please see the examples for more details on the scope.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;(let x 2 (mult x (let x 3 y 4 (add x y))))&quot;
<strong>Output:</strong> 14
<strong>Explanation:</strong> In the expression (add x y), when checking for the value of the variable x,
we check from the innermost scope to the outermost in the context of the variable we are trying to evaluate.
Since x = 3 is found first, the value of x is 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;(let x 3 x 2 x)&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> Assignment in let statements is processed sequentially.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;(let x 1 y 2 x (add x y) (add x y))&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The first (add x y) evaluates as 3, and is assigned to x.
The second (add x y) evaluates as 3+2 = 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= expression.length &lt;= 2000</code></li>
	<li>There are no leading or trailing spaces in <code>expression</code>.</li>
	<li>All tokens are separated by a single space in <code>expression</code>.</li>
	<li>The answer and all intermediate calculations of that answer are guaranteed to fit in a <strong>32-bit</strong> integer.</li>
	<li>The expression is guaranteed to be legal and evaluate to an integer.</li>
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
