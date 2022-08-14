# [1432. Max Difference You Can Get From Changing an Integer](https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer)

[中文文档](/solution/1400-1499/1432.Max%20Difference%20You%20Can%20Get%20From%20Changing%20an%20Integer/README.md)

## Description

<p>You are given an integer <code>num</code>. You will apply the following steps exactly <strong>two</strong> times:</p>

<ul>
	<li>Pick a digit <code>x (0 &lt;= x &lt;= 9)</code>.</li>
	<li>Pick another digit <code>y (0 &lt;= y &lt;= 9)</code>. The digit <code>y</code> can be equal to <code>x</code>.</li>
	<li>Replace all the occurrences of <code>x</code> in the decimal representation of <code>num</code> by <code>y</code>.</li>
	<li>The new integer <strong>cannot</strong> have any leading zeros, also the new integer <strong>cannot</strong> be 0.</li>
</ul>

<p>Let <code>a</code> and <code>b</code> be the results of applying the operations to <code>num</code> the first and second times, respectively.</p>

<p>Return <em>the max difference</em> between <code>a</code> and <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 555
<strong>Output:</strong> 888
<strong>Explanation:</strong> The first time pick x = 5 and y = 9 and store the new integer in a.
The second time pick x = 5 and y = 1 and store the new integer in b.
We have now a = 999 and b = 111 and max difference = 888
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 9
<strong>Output:</strong> 8
<strong>Explanation:</strong> The first time pick x = 9 and y = 9 and store the new integer in a.
The second time pick x = 9 and y = 1 and store the new integer in b.
We have now a = 9 and b = 1 and max difference = 8
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10</code><sup>8</sup></li>
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
