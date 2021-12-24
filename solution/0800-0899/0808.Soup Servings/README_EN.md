# [808. Soup Servings](https://leetcode.com/problems/soup-servings)

[中文文档](/solution/0800-0899/0808.Soup%20Servings/README.md)

## Description

<p>There are two types of soup: type A and type B. Initially we have <code>N</code> ml of each type of soup. There are four kinds of operations:</p>

<ol>
	<li>Serve&nbsp;100 ml of soup A and 0 ml of soup B</li>
	<li>Serve&nbsp;75 ml of soup A and 25&nbsp;ml of soup B</li>
	<li>Serve 50 ml of soup A and 50 ml of soup B</li>
	<li>Serve 25&nbsp;ml of soup A and 75&nbsp;ml of soup B</li>
</ol>

<p>When we serve some soup, we give it to someone and we no longer have it.&nbsp; Each turn,&nbsp;we will choose from the four operations with equal probability 0.25. If the remaining volume of soup is not enough to complete the operation, we will serve&nbsp;as much as we can.&nbsp; We stop once we no longer have some quantity of both types of soup.</p>

<p>Note that we do not have the operation where all 100 ml&#39;s of soup B are used first.&nbsp;&nbsp;</p>

<p>Return the probability that soup A will be empty&nbsp;first, plus half the probability that A and B become empty at the same time.</p>

<p>&nbsp;</p>

<pre>

<strong>Example:</strong>

<strong>Input:</strong> N = 50

<strong>Output:</strong> 0.625

<strong>Explanation:</strong> 

If we choose the first two operations, A will become empty first. For the third operation, A and B will become empty at the same time. For the fourth operation, B will become empty first. So the total probability of A becoming empty first plus half the probability that A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) = 0.625.



</pre>

<p><strong>Notes: </strong></p>

<ul>
	<li><code>0 &lt;= N &lt;= 10^9</code>.&nbsp;</li>
	<li>Answers within&nbsp;<code>10^-6</code>&nbsp;of the true value will be accepted as correct.</li>
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
