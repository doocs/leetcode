# [964. Least Operators to Express Number](https://leetcode.com/problems/least-operators-to-express-number)

[中文文档](/solution/0900-0999/0964.Least%20Operators%20to%20Express%20Number/README.md)

## Description

<p>Given a single positive integer <code>x</code>, we will write an expression of the form <code>x (op1) x (op2) x (op3) x ...</code>&nbsp;where each operator <code>op1</code>, <code>op2</code>, etc. is either addition, subtraction, multiplication, or division (<code>+</code>, <code>-</code>, <code>*</code>, or <code>/)</code>.&nbsp; For example, with <code>x = 3</code>, we might write <code>3 * 3 / 3 + 3 - 3</code>&nbsp;which is a value of <font face="monospace">3</font>.</p>

<p>When writing such an expression, we adhere to the following conventions:</p>

<ol>
    <li>The division operator (<code>/</code>) returns rational numbers.</li>
    <li>There are no parentheses placed anywhere.</li>
    <li>We use the usual order of operations: multiplication and division happens before addition and subtraction.</li>
    <li>It&#39;s not allowed to use the unary negation&nbsp;operator (<code>-</code>).&nbsp; For example, &quot;<code>x&nbsp;- x</code>&quot;&nbsp;is a valid expression as it only uses subtraction, but &quot;<code>-x +&nbsp;x</code>&quot; is not because it uses negation.</li>
</ol>

<p>We would like to write an expression with the least number of operators such that the expression equals the given <code>target</code>.&nbsp; Return the least number of operators used.</p>

<p>&nbsp;</p>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>x = <span id="example-input-1-1">3</span>, target = <span id="example-input-1-2">19</span>

<strong>Output: </strong><span id="example-output-1">5</span>

<strong>Explanation: </strong><span id="example-output-1">3 * 3 + 3 * 3 + 3 / 3.  The expression contains 5 operations.</span>

</pre>

<p><strong>Example 2:</strong></p>

<div>

<pre>

<strong>Input: </strong>x = <span id="example-input-2-1">5</span>, target = <span id="example-input-2-2">501</span>

<strong>Output: </strong><span id="example-output-2">8</span>

<strong>Explanation: </strong><span id="example-output-1">5 * 5 * 5 * 5 - 5 * 5 * 5 + 5 / 5.  The expression contains 8 operations.</span>

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong>x = <span id="example-input-3-1">100</span>, target = <span id="example-input-3-2">100000000</span>

<strong>Output: </strong><span id="example-output-3">3</span>

<strong>Explanation: </strong><span id="example-output-1">100 * 100 * 100 * 100.  The expression contains 3 operations.</span></pre>

<p>&nbsp;</p>

</div>

</div>

</div>

<p><strong>Note:</strong></p>

<ul>
    <li><code>2 &lt;= x &lt;= 100</code></li>
    <li><code>1 &lt;= target &lt;= 2 * 10^8</code></li>
</ul>

<div>

<div>

<div>&nbsp;</div>

</div>

</div>

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
