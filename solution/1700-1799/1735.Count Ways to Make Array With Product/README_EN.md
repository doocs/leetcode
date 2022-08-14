# [1735. Count Ways to Make Array With Product](https://leetcode.com/problems/count-ways-to-make-array-with-product)

[中文文档](/solution/1700-1799/1735.Count%20Ways%20to%20Make%20Array%20With%20Product/README.md)

## Description

<p>You are given a 2D integer array, <code>queries</code>. For each <code>queries[i]</code>, where <code>queries[i] = [n<sub>i</sub>, k<sub>i</sub>]</code>, find the number of different ways you can place positive integers into an array of size <code>n<sub>i</sub></code> such that the product of the integers is <code>k<sub>i</sub></code>. As the number of ways may be too large, the answer to the <code>i<sup>th</sup></code> query is the number of ways <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>Return <em>an integer array </em><code>answer</code><em> where </em><code>answer.length == queries.length</code><em>, and </em><code>answer[i]</code><em> is the answer to the </em><code>i<sup>th</sup></code><em> query.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> queries = [[2,6],[5,1],[73,660]]
<strong>Output:</strong> [4,1,50734910]
<strong>Explanation:</strong>&nbsp;Each query is independent.
[2,6]: There are 4 ways to fill an array of size 2 that multiply to 6: [1,6], [2,3], [3,2], [6,1].
[5,1]: There is 1 way to fill an array of size 5 that multiply to 1: [1,1,1,1,1].
[73,660]: There are 1050734917 ways to fill an array of size 73 that multiply to 660. 1050734917 modulo 10<sup>9</sup> + 7 = 50734910.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> queries = [[1,1],[2,2],[3,3],[4,4],[5,5]]
<strong>Output:</strong> [1,2,3,10,5]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>4</sup> </code></li>
	<li><code>1 &lt;= n<sub>i</sub>, k<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
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
