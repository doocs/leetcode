# [770. Basic Calculator IV](https://leetcode.com/problems/basic-calculator-iv)

[中文文档](/solution/0700-0799/0770.Basic%20Calculator%20IV/README.md)

## Description

<p>Given an expression such as <code>expression = &quot;e + 8 - a + 5&quot;</code> and an evaluation map such as <code>{&quot;e&quot;: 1}</code> (given in terms of <code>evalvars = [&quot;e&quot;]</code> and <code>evalints = [1]</code>), return a list of tokens representing the simplified expression, such as <code>[&quot;-1*a&quot;,&quot;14&quot;]</code></p>

<ul>
	<li>An expression alternates chunks and symbols, with a space separating each chunk and symbol.</li>
	<li>A chunk is either an expression in parentheses, a variable, or a non-negative integer.</li>
	<li>A variable is a string of lowercase letters (not including digits.) Note that variables can be multiple letters, and note that variables never have a leading coefficient or unary operator like <code>&quot;2x&quot;</code> or <code>&quot;-x&quot;</code>.</li>
</ul>

<p>Expressions are evaluated in the usual order: brackets first, then multiplication, then addition and subtraction.</p>

<ul>
	<li>For example, <code>expression = &quot;1 + 2 * 3&quot;</code> has an answer of <code>[&quot;7&quot;]</code>.</li>
</ul>

<p>The format of the output is as follows:</p>

<ul>
	<li>For each term of free variables with a non-zero coefficient, we write the free variables within a term in sorted order lexicographically.
	<ul>
		<li>For example, we would never write a term like <code>&quot;b*a*c&quot;</code>, only <code>&quot;a*b*c&quot;</code>.</li>
	</ul>
	</li>
	<li>Terms have degrees equal to the number of free variables being multiplied, counting multiplicity. We write the largest degree terms of our answer first, breaking ties by lexicographic order ignoring the leading coefficient of the term.
	<ul>
		<li>For example, <code>&quot;a*a*b*c&quot;</code> has degree <code>4</code>.</li>
	</ul>
	</li>
	<li>The leading coefficient of the term is placed directly to the left with an asterisk separating it from the variables (if they exist.) A leading coefficient of 1 is still printed.</li>
	<li>An example of a well-formatted answer is <code>[&quot;-2*a*a*a&quot;, &quot;3*a*a*b&quot;, &quot;3*b*b&quot;, &quot;4*a&quot;, &quot;5*c&quot;, &quot;-6&quot;]</code>.</li>
	<li>Terms (including constant terms) with coefficient <code>0</code> are not included.
	<ul>
		<li>For example, an expression of <code>&quot;0&quot;</code> has an output of <code>[]</code>.</li>
	</ul>
	</li>
</ul>

<p><strong>Note:</strong> You may assume that the given expression is always valid. All intermediate results will be in the range of <code>[-2<sup>31</sup>, 2<sup>31</sup> - 1]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;e + 8 - a + 5&quot;, evalvars = [&quot;e&quot;], evalints = [1]
<strong>Output:</strong> [&quot;-1*a&quot;,&quot;14&quot;]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;e - 8 + temperature - pressure&quot;, evalvars = [&quot;e&quot;, &quot;temperature&quot;], evalints = [1, 12]
<strong>Output:</strong> [&quot;-1*pressure&quot;,&quot;5&quot;]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;(e + 8) * (e - 8)&quot;, evalvars = [], evalints = []
<strong>Output:</strong> [&quot;1*e*e&quot;,&quot;-64&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= expression.length &lt;= 250</code></li>
	<li><code>expression</code> consists of lowercase English letters, digits, <code>&#39;+&#39;</code>, <code>&#39;-&#39;</code>, <code>&#39;*&#39;</code>, <code>&#39;(&#39;</code>, <code>&#39;)&#39;</code>, <code>&#39; &#39;</code>.</li>
	<li><code>expression</code> does not contain any leading or trailing spaces.</li>
	<li>All the tokens in <code>expression</code> are separated by a single space.</li>
	<li><code>0 &lt;= evalvars.length &lt;= 100</code></li>
	<li><code>1 &lt;= evalvars[i].length &lt;= 20</code></li>
	<li><code>evalvars[i]</code> consists of lowercase English letters.</li>
	<li><code>evalints.length == evalvars.length</code></li>
	<li><code>-100 &lt;= evalints[i] &lt;= 100</code></li>
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
