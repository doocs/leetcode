# [972. Equal Rational Numbers](https://leetcode.com/problems/equal-rational-numbers)

[中文文档](/solution/0900-0999/0972.Equal%20Rational%20Numbers/README.md)

## Description

<p>Given two strings <code>s</code> and <code>t</code>, each of which represents a non-negative rational number, return <code>true</code> if and only if they represent the same number. The strings may use parentheses to denote the repeating part of the rational number.</p>

<p>A <strong>rational number</strong> can be represented using up to three parts: <code>&lt;IntegerPart&gt;</code>, <code>&lt;NonRepeatingPart&gt;</code>, and a <code>&lt;RepeatingPart&gt;</code>. The number will be represented in one of the following three ways:</p>

<ul>
	<li><code>&lt;IntegerPart&gt;</code>
    <ul>
    	<li>For example, <code>12</code>, <code>0</code>, and <code>123</code>.</li>
    </ul>
    </li>
    <li><code>&lt;IntegerPart&gt;<strong>&lt;.&gt;</strong>&lt;NonRepeatingPart&gt;</code>
    <ul>
    	<li>For example, <code>0.5</code>, <code>1.</code>, <code>2.12</code>, and <code>123.0001</code>.</li>
    </ul>
    </li>
    <li><code>&lt;IntegerPart&gt;<strong>&lt;.&gt;</strong>&lt;NonRepeatingPart&gt;<strong>&lt;(&gt;</strong>&lt;RepeatingPart&gt;<strong>&lt;)&gt;</strong></code>
    <ul>
    	<li>For example, <code>0.1(6)</code>, <code>1.(9)</code>, <code>123.00(1212)</code>.</li>
    </ul>
    </li>
</ul>

<p>The repeating portion of a decimal expansion is conventionally denoted within a pair of round brackets. For example:</p>

<ul>
	<li><code>1/6 = 0.16666666... = 0.1(6) = 0.1666(6) = 0.166(66)</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0.(52)&quot;, t = &quot;0.5(25)&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> Because &quot;0.(52)&quot; represents 0.52525252..., and &quot;0.5(25)&quot; represents 0.52525252525..... , the strings represent the same number.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0.1666(6)&quot;, t = &quot;0.166(66)&quot;
<strong>Output:</strong> true
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0.9(9)&quot;, t = &quot;1.&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> &quot;0.9(9)&quot; represents 0.999999999... repeated forever, which equals 1.  [<a href="https://en.wikipedia.org/wiki/0.999..." target="_blank">See this link for an explanation.</a>]
&quot;1.&quot; represents the number 1, which is formed correctly: (IntegerPart) = &quot;1&quot; and (NonRepeatingPart) = &quot;&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>Each part consists only of digits.</li>
	<li>The <code>&lt;IntegerPart&gt;</code> does not have leading zeros (except for the zero itself).</li>
	<li><code>1 &lt;= &lt;IntegerPart&gt;.length &lt;= 4</code></li>
	<li><code>0 &lt;= &lt;NonRepeatingPart&gt;.length &lt;= 4</code></li>
	<li><code>1 &lt;= &lt;RepeatingPart&gt;.length &lt;= 4</code></li>
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
