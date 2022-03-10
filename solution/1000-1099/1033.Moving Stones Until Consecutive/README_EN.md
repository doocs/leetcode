# [1033. Moving Stones Until Consecutive](https://leetcode.com/problems/moving-stones-until-consecutive)

[中文文档](/solution/1000-1099/1033.Moving%20Stones%20Until%20Consecutive/README.md)

## Description

<p>There are three stones in different positions on the X-axis. You are given three integers <code>a</code>, <code>b</code>, and <code>c</code>, the positions of the stones.</p>

<p>In one move, you pick up a stone at an endpoint (i.e., either the lowest or highest position stone), and move it to an unoccupied position between those endpoints. Formally, let&#39;s say the stones are currently at positions <code>x</code>, <code>y</code>, and <code>z</code> with <code>x &lt; y &lt; z</code>. You pick up the stone at either position <code>x</code> or position <code>z</code>, and move that stone to an integer position <code>k</code>, with <code>x &lt; k &lt; z</code> and <code>k != y</code>.</p>

<p>The game ends when you cannot make any more moves (i.e., the stones are in three consecutive positions).</p>

<p>Return <em>an integer array </em><code>answer</code><em> of length </em><code>2</code><em> where</em>:</p>

<ul>
	<li><code>answer[0]</code> <em>is the minimum number of moves you can play, and</em></li>
	<li><code>answer[1]</code> <em>is the maximum number of moves you can play</em>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = 1, b = 2, c = 5
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> Move the stone from 5 to 3, or move the stone from 5 to 4 to 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = 4, b = 3, c = 2
<strong>Output:</strong> [0,0]
<strong>Explanation:</strong> We cannot make any moves.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> a = 3, b = 5, c = 1
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> Move the stone from 1 to 4; or move the stone from 1 to 2 to 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a, b, c &lt;= 100</code></li>
	<li><code>a</code>, <code>b</code>, and <code>c</code> have different values.</li>
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
