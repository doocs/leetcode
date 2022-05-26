# [2118. Build the Equation](https://leetcode.com/problems/build-the-equation)

[中文文档](/solution/2100-2199/2118.Build%20the%20Equation/README.md)

## Description

<p>Table: <code>Terms</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| power       | int  |
| factor      | int  |
+-------------+------+
power is the primary key column for this table.
Each row of this table contains information about one term of the equation.
power is an integer in the range [0, 100].
factor is an integer in the range [-100, 100] and cannot be zero.
</pre>

<p>&nbsp;</p>

<p>You have a very powerful program that can solve any equation of one variable in the world. The equation passed to the program must be formatted as follows:</p>

<ul>
	<li>The left-hand side (LHS) should contain all the terms.</li>
	<li>The right-hand side (RHS) should be zero.</li>
	<li>Each term of the LHS should follow the format <code>&quot;&lt;sign&gt;&lt;fact&gt;X^&lt;pow&gt;&quot;</code>&nbsp;where:
	<ul>
		<li><code>&lt;sign&gt;</code> is either <code>&quot;+&quot;</code> or <code>&quot;-&quot;</code>.</li>
		<li><code>&lt;fact&gt;</code> is the <strong>absolute value</strong> of the <code>factor</code>.</li>
		<li><code>&lt;pow&gt;</code> is the value of the <code>power</code>.</li>
	</ul>
	</li>
	<li>If the power is <code>1</code>, do not add <code>&quot;^&lt;pow&gt;&quot;</code>.
	<ul>
		<li>For example, if <code>power = 1</code> and <code>factor = 3</code>, the term will be <code>&quot;+3X&quot;</code>.</li>
	</ul>
	</li>
	<li>If the power is <code>0</code>, add neither <code>&quot;X&quot;</code> nor <code>&quot;^&lt;pow&gt;&quot;</code>.
	<ul>
		<li>For example, if <code>power = 0</code> and <code>factor = -3</code>, the term will be <code>&quot;-3&quot;</code>.</li>
	</ul>
	</li>
	<li>The powers in the LHS should be sorted in <strong>descending order</strong>.</li>
</ul>

<p>Write an SQL query to build the equation.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Terms table:
+-------+--------+
| power | factor |
+-------+--------+
| 2     | 1      |
| 1     | -4     |
| 0     | 2      |
+-------+--------+
<strong>Output:</strong> 
+--------------+
| equation     |
+--------------+
| +1X^2-4X+2=0 |
+--------------+
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
Terms table:
+-------+--------+
| power | factor |
+-------+--------+
| 4     | -4     |
| 2     | 1      |
| 1     | -1     |
+-------+--------+
<strong>Output:</strong> 
+-----------------+
| equation        |
+-----------------+
| -4X^4+1X^2-1X=0 |
+-----------------+
</pre>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> What will be changed in your solution if the power is not a primary key but each power should be unique in the answer?</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
