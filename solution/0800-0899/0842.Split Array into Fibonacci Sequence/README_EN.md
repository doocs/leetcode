# [842. Split Array into Fibonacci Sequence](https://leetcode.com/problems/split-array-into-fibonacci-sequence)

[中文文档](/solution/0800-0899/0842.Split%20Array%20into%20Fibonacci%20Sequence/README.md)

## Description

<p>You are given a string of digits <code>num</code>, such as <code>&quot;123456579&quot;</code>. We can split it into a Fibonacci-like sequence <code>[123, 456, 579]</code>.</p>

<p>Formally, a <strong>Fibonacci-like</strong> sequence is a list <code>f</code> of non-negative integers such that:</p>

<ul>
	<li><code>0 &lt;= f[i] &lt; 2<sup>31</sup></code>, (that is, each integer fits in a <strong>32-bit</strong> signed integer type),</li>
	<li><code>f.length &gt;= 3</code>, and</li>
	<li><code>f[i] + f[i + 1] == f[i + 2]</code> for all <code>0 &lt;= i &lt; f.length - 2</code>.</li>
</ul>

<p>Note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number <code>0</code> itself.</p>

<p>Return any Fibonacci-like sequence split from <code>num</code>, or return <code>[]</code> if it cannot be done.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;1101111&quot;
<strong>Output:</strong> [11,0,11,11]
<strong>Explanation:</strong> The output [110, 1, 111] would also be accepted.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;112358130&quot;
<strong>Output:</strong> []
<strong>Explanation:</strong> The task is impossible.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;0123&quot;
<strong>Output:</strong> []
<strong>Explanation:</strong> Leading zeroes are not allowed, so &quot;01&quot;, &quot;2&quot;, &quot;3&quot; is not valid.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 200</code></li>
	<li><code>num</code> contains only digits.</li>
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
