# [1116. Print Zero Even Odd](https://leetcode.com/problems/print-zero-even-odd)

[中文文档](/solution/1100-1199/1116.Print%20Zero%20Even%20Odd/README.md)

## Description

<p>You have a function <code>printNumber</code> that can be called with an integer parameter and prints it to the console.</p>

<ul>
	<li>For example, calling <code>printNumber(7)</code> prints <code>7</code> to the console.</li>
</ul>

<p>You are given an instance of the class <code>ZeroEvenOdd</code> that has three functions: <code>zero</code>, <code>even</code>, and <code>odd</code>. The same instance of <code>ZeroEvenOdd</code> will be passed to three different threads:</p>

<ul>
	<li><strong>Thread A:</strong> calls <code>zero()</code> that should only output <code>0</code>&#39;s.</li>
	<li><strong>Thread B:</strong> calls <code>even()</code> that should only output even numbers.</li>
	<li><strong>Thread C:</strong> calls <code>odd()</code> that should only output odd numbers.</li>
</ul>

<p>Modify the given class to output the series <code>&quot;010203040506...&quot;</code> where the length of the series must be <code>2n</code>.</p>

<p>Implement the <code>ZeroEvenOdd</code> class:</p>

<ul>
	<li><code>ZeroEvenOdd(int n)</code> Initializes the object with the number <code>n</code> that represents the numbers that should be printed.</li>
	<li><code>void zero(printNumber)</code> Calls <code>printNumber</code> to output one zero.</li>
	<li><code>void even(printNumber)</code> Calls <code>printNumber</code> to output one even number.</li>
	<li><code>void odd(printNumber)</code> Calls <code>printNumber</code> to output one odd number.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> &quot;0102&quot;
<strong>Explanation:</strong> There are three threads being fired asynchronously.
One of them calls zero(), the other calls even(), and the last one calls odd().
&quot;0102&quot; is the correct output.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> &quot;0102030405&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
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
