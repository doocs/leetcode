# [1444. Number of Ways of Cutting a Pizza](https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza)

[中文文档](/solution/1400-1499/1444.Number%20of%20Ways%20of%20Cutting%20a%20Pizza/README.md)

## Description

<p>Given a rectangular pizza represented as a <code>rows x cols</code>&nbsp;matrix containing the following characters: <code>&#39;A&#39;</code> (an apple) and <code>&#39;.&#39;</code> (empty cell) and given the integer <code>k</code>. You have to cut the pizza into <code>k</code> pieces using <code>k-1</code> cuts.&nbsp;</p>

<p>For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to a person. If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.</p>

<p><em>Return the number of ways of cutting the pizza such that each piece contains <strong>at least</strong> one apple.&nbsp;</em>Since the answer can be a huge number, return this modulo 10^9 + 7.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1444.Number%20of%20Ways%20of%20Cutting%20a%20Pizza/images/ways_to_cut_apple_1.png" style="width: 500px; height: 378px;" /></strong></p>

<pre>

<strong>Input:</strong> pizza = [&quot;A..&quot;,&quot;AAA&quot;,&quot;...&quot;], k = 3

<strong>Output:</strong> 3 

<strong>Explanation:</strong> The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> pizza = [&quot;A..&quot;,&quot;AA.&quot;,&quot;...&quot;], k = 3

<strong>Output:</strong> 1

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> pizza = [&quot;A..&quot;,&quot;A..&quot;,&quot;...&quot;], k = 1

<strong>Output:</strong> 1

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= rows, cols &lt;= 50</code></li>
	<li><code>rows ==&nbsp;pizza.length</code></li>
	<li><code>cols ==&nbsp;pizza[i].length</code></li>
	<li><code>1 &lt;= k &lt;= 10</code></li>
	<li><code>pizza</code> consists of characters <code>&#39;A&#39;</code>&nbsp;and <code>&#39;.&#39;</code> only.</li>
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
