# [770. Basic Calculator IV](https://leetcode.com/problems/basic-calculator-iv)

[中文文档](/solution/0700-0799/0770.Basic%20Calculator%20IV/README.md)

## Description

<p>Given an <code>expression</code>&nbsp;such as <code>expression = &quot;e + 8 - a + 5&quot;</code> and an evaluation map such as <code>{&quot;e&quot;: 1}</code> (given in terms of <code>evalvars = [&quot;e&quot;]</code> and <code>evalints = [1]</code>), return a list of tokens representing the simplified expression, such as <code>[&quot;-1*a&quot;,&quot;14&quot;]</code></p>

<ul>
	<li>An expression alternates chunks and symbols, with a space separating each chunk and symbol.</li>
	<li>A chunk is either an expression in parentheses, a variable, or a non-negative integer.</li>
	<li>A variable is a string of lowercase letters (not including digits.) Note that variables can be multiple letters, and note that variables never have a leading coefficient or unary operator like <code>&quot;2x&quot;</code> or <code>&quot;-x&quot;</code>.</li>
</ul>

<p>Expressions are evaluated in the usual order: brackets first, then multiplication, then addition and subtraction. For example, <code>expression = &quot;1 + 2 * 3&quot;</code> has an answer of <code>[&quot;7&quot;]</code>.</p>

<p>The format of the output is as follows:</p>

<ul>
	<li>For each term of free variables with non-zero coefficient, we write the free variables within a term in sorted order lexicographically. For example, we would never write a term like <code>&quot;b*a*c&quot;</code>, only <code>&quot;a*b*c&quot;</code>.</li>
	<li>Terms have degree equal to the number of free variables being multiplied, counting multiplicity. (For example, <code>&quot;a*a*b*c&quot;</code> has degree 4.) We write the largest degree terms of our answer first, breaking ties by lexicographic order ignoring the leading coefficient of the term.</li>
	<li>The leading coefficient of the term is placed directly to the left with an asterisk separating it from the variables (if they exist.)&nbsp; A leading coefficient of 1 is still printed.</li>
	<li>An example of a well formatted answer is <code>[&quot;-2*a*a*a&quot;, &quot;3*a*a*b&quot;, &quot;3*b*b&quot;, &quot;4*a&quot;, &quot;5*c&quot;, &quot;-6&quot;]</code>&nbsp;</li>
	<li>Terms (including constant terms) with coefficient 0 are not included.&nbsp; For example, an expression of &quot;0&quot; has an output of [].</li>
</ul>

<p><strong>Examples:</strong></p>

<pre>

<strong>Input:</strong> expression = &quot;e + 8 - a + 5&quot;, evalvars = [&quot;e&quot;], evalints = [1]

<strong>Output:</strong> [&quot;-1*a&quot;,&quot;14&quot;]



<strong>Input:</strong> expression = &quot;e - 8 + temperature - pressure&quot;,

evalvars = [&quot;e&quot;, &quot;temperature&quot;], evalints = [1, 12]

<strong>Output:</strong> [&quot;-1*pressure&quot;,&quot;5&quot;]



<strong>Input:</strong> expression = &quot;(e + 8) * (e - 8)&quot;, evalvars = [], evalints = []

<strong>Output:</strong> [&quot;1*e*e&quot;,&quot;-64&quot;]



<strong>Input:</strong> expression = &quot;7 - 7&quot;, evalvars = [], evalints = []

<strong>Output:</strong> []



<strong>Input:</strong> expression = &quot;a * b * c + b * a * c * 4&quot;, evalvars = [], evalints = []

<strong>Output:</strong> [&quot;5*a*b*c&quot;]



<strong>Input:</strong> expression = &quot;((a - b) * (b - c) + (c - a)) * ((a - b) + (b - c) * (c - a))&quot;,

evalvars = [], evalints = []

<strong>Output:</strong> [&quot;-1*a*a*b*b&quot;,&quot;2*a*a*b*c&quot;,&quot;-1*a*a*c*c&quot;,&quot;1*a*b*b*b&quot;,&quot;-1*a*b*b*c&quot;,&quot;-1*a*b*c*c&quot;,&quot;1*a*c*c*c&quot;,&quot;-1*b*b*b*c&quot;,&quot;2*b*b*c*c&quot;,&quot;-1*b*c*c*c&quot;,&quot;2*a*a*b&quot;,&quot;-2*a*a*c&quot;,&quot;-2*a*b*b&quot;,&quot;2*a*c*c&quot;,&quot;1*b*b*b&quot;,&quot;-1*b*b*c&quot;,&quot;1*b*c*c&quot;,&quot;-1*c*c*c&quot;,&quot;-1*a*a&quot;,&quot;1*a*b&quot;,&quot;1*a*c&quot;,&quot;-1*b*c&quot;]

</pre>

<p><strong>Note:</strong></p>

<ol>
	<li><code>expression</code> will have length in range <code>[1, 250]</code>.</li>
	<li><code>evalvars, evalints</code> will have equal lengths in range <code>[0, 100]</code>.</li>
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
