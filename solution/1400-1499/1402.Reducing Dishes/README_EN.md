# [1402. Reducing Dishes](https://leetcode.com/problems/reducing-dishes)

[中文文档](/solution/1400-1499/1402.Reducing%20Dishes/README.md)

## Description

<p>A chef&nbsp;has collected data on the <code>satisfaction</code> level of his&nbsp;<code>n</code> dishes.&nbsp;Chef can cook any dish in 1 unit of time.</p>

<p><em>Like-time coefficient</em>&nbsp;of a dish is defined as&nbsp;the time taken to cook that dish including previous dishes multiplied by its satisfaction level &nbsp;i.e.&nbsp; <code>time[i]</code>*<code>satisfaction[i]</code></p>

<p>Return&nbsp;the maximum sum of&nbsp;<em>Like-time coefficient </em>that the chef can obtain after dishes preparation.</p>

<p>Dishes can be prepared in <strong>any </strong>order and the chef can discard some dishes to get this maximum value.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> satisfaction = [-1,-8,0,5,-9]

<strong>Output:</strong> 14

<strong>Explanation: </strong>After Removing the second and last dish, the maximum total <em>Like-time coefficient</em> will be equal to (-1*1 + 0*2 + 5*3 = 14). Each dish is prepared in one unit of time.</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> satisfaction = [4,3,2]

<strong>Output:</strong> 20

<strong>Explanation:</strong> Dishes can be prepared in any order, (2*1 + 3*2 + 4*3 = 20)

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> satisfaction = [-1,-4,-5]

<strong>Output:</strong> 0

<strong>Explanation:</strong> People don&#39;t like the dishes. No dish is prepared.

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> satisfaction = [-2,5,-1,0,3,-3]

<strong>Output:</strong> 35

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == satisfaction.length</code></li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>-10^3 &lt;=&nbsp;satisfaction[i] &lt;= 10^3</code></li>
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
