# [1116. Print Zero Even Odd](https://leetcode.com/problems/print-zero-even-odd)

[中文文档](/solution/1100-1199/1116.Print%20Zero%20Even%20Odd/README.md)

## Description

<p>Suppose you are given the following code:</p>

<pre>
class ZeroEvenOdd {
&nbsp; public ZeroEvenOdd(int n) { ... }&nbsp;     // constructor
  public void zero(printNumber) { ... }  // only output 0&#39;s
  public void even(printNumber) { ... }  // only output even numbers
  public void odd(printNumber) { ... }   // only output odd numbers
}
</pre>

<p>The same instance of <code>ZeroEvenOdd</code> will be passed to three different threads:</p>

<ol>
	<li>Thread A will call&nbsp;<code>zero()</code>&nbsp;which should only output 0&#39;s.</li>
	<li>Thread B will call&nbsp;<code>even()</code>&nbsp;which should only ouput even numbers.</li>
	<li>Thread C will call <code>odd()</code>&nbsp;which should only output odd numbers.</li>
</ol>

<p>Each of the threads is given a&nbsp;<code>printNumber</code> method to output&nbsp;an integer. Modify the given program to output the series&nbsp;<code>010203040506</code>... where the length of the series must be 2<em>n</em>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>
<b>Input:</b> n = 2
<b>Output:</b> &quot;0102&quot;
<strong>Explanation:</strong> There are three threads being fired asynchronously. One of them calls zero(), the other calls even(), and the last one calls odd(). &quot;0102&quot; is the correct output.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<b>Input:</b> n = 5
<b>Output:</b> &quot;0102030405&quot;
</pre>

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
