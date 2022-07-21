# [2307. Check for Contradictions in Equations](https://leetcode.com/problems/check-for-contradictions-in-equations)

[中文文档](/solution/2300-2399/2307.Check%20for%20Contradictions%20in%20Equations/README.md)

## Description

<p>You are given a 2D array of strings <code>equations</code> and an array of real numbers <code>values</code>, where <code>equations[i] = [A<sub>i</sub>, B<sub>i</sub>]</code> and <code>values[i]</code> means that <code>A<sub>i</sub> / B<sub>i</sub> = values[i]</code>.</p>

<p>Determine if there exists a contradiction in the equations. Return <code>true</code><em> if there is a contradiction, or </em><code>false</code><em> otherwise</em>.</p>

<p><strong>Note</strong>:</p>

<ul>
	<li>When checking if two numbers are equal, check that their <strong>absolute difference</strong> is less than <code>10<sup>-5</sup></code>.</li>
	<li>The testcases are generated such that there are no cases targeting precision, i.e. using <code>double</code> is enough to solve the problem.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> equations = [[&quot;a&quot;,&quot;b&quot;],[&quot;b&quot;,&quot;c&quot;],[&quot;a&quot;,&quot;c&quot;]], values = [3,0.5,1.5]
<strong>Output:</strong> false
<strong>Explanation:
</strong>The given equations are: a / b = 3, b / c = 0.5, a / c = 1.5
There are no contradictions in the equations. One possible assignment to satisfy all equations is:
a = 3, b = 1 and c = 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> equations = [[&quot;le&quot;,&quot;et&quot;],[&quot;le&quot;,&quot;code&quot;],[&quot;code&quot;,&quot;et&quot;]], values = [2,5,0.5]
<strong>Output:</strong> true
<strong>Explanation:</strong>
The given equations are: le / et = 2, le / code = 5, code / et = 0.5
Based on the first two equations, we get code / et = 0.4.
Since the third equation is code / et = 0.5, we get a contradiction.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= equations.length &lt;= 100</code></li>
	<li><code>equations[i].length == 2</code></li>
	<li><code>1 &lt;= A<sub>i</sub>.length, B<sub>i</sub>.length &lt;= 5</code></li>
	<li><code>A<sub>i</sub></code>, <code>B<sub>i</sub></code> consist of lowercase English letters.</li>
	<li><code>equations.length == values.length</code></li>
	<li><code>0.0 &lt; values[i] &lt;= 10.0</code></li>
	<li><code>values[i]</code> has a maximum of 2 decimal places.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
