# [808. Soup Servings](https://leetcode.com/problems/soup-servings)

[中文文档](/solution/0800-0899/0808.Soup%20Servings/README.md)

## Description

<p>There are two types of soup: <strong>type A</strong> and <strong>type B</strong>. Initially, we have <code>n</code> ml of each type of soup. There are four kinds of operations:</p>

<ol>
	<li>Serve <code>100</code> ml of <strong>soup A</strong> and <code>0</code> ml of <strong>soup B</strong>,</li>
	<li>Serve <code>75</code> ml of <strong>soup A</strong> and <code>25</code> ml of <strong>soup B</strong>,</li>
	<li>Serve <code>50</code> ml of <strong>soup A</strong> and <code>50</code> ml of <strong>soup B</strong>, and</li>
	<li>Serve <code>25</code> ml of <strong>soup A</strong> and <code>75</code> ml of <strong>soup B</strong>.</li>
</ol>

<p>When we serve some soup, we give it to someone, and we no longer have it. Each turn, we will choose from the four operations with an equal probability <code>0.25</code>. If the remaining volume of soup is not enough to complete the operation, we will serve as much as possible. We stop once we no longer have some quantity of both types of soup.</p>

<p><strong>Note</strong> that we do not have an operation where all <code>100</code> ml&#39;s of <strong>soup B</strong> are used first.</p>

<p>Return <em>the probability that <strong>soup A</strong> will be empty first, plus half the probability that <strong>A</strong> and <strong>B</strong> become empty at the same time</em>. Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 50
<strong>Output:</strong> 0.62500
<strong>Explanation:</strong> If we choose the first two operations, A will become empty first.
For the third operation, A and B will become empty at the same time.
For the fourth operation, B will become empty first.
So the total probability of A becoming empty first plus half the probability that A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) = 0.625.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 100
<strong>Output:</strong> 0.71875
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
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
