# [1195. Fizz Buzz Multithreaded](https://leetcode.com/problems/fizz-buzz-multithreaded)

[中文文档](/solution/1100-1199/1195.Fizz%20Buzz%20Multithreaded/README.md)

## Description

<p>Write a program that outputs the string representation of numbers from 1 to&nbsp;<i>n</i>, however:</p>

<ul>
	<li>If the number is divisible by 3, output &quot;fizz&quot;.</li>
	<li>If the number is divisible by 5, output&nbsp;&quot;buzz&quot;.</li>
	<li>If the number is divisible by both 3 and 5, output&nbsp;&quot;fizzbuzz&quot;.</li>
</ul>

<p>For example, for&nbsp;<code>n = 15</code>, we output:&nbsp;<code>1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz</code>.</p>

<p>Suppose you are given the following code:</p>

<pre>
class FizzBuzz {
&nbsp; public FizzBuzz(int n) { ... }&nbsp;              // constructor
  public void fizz(printFizz) { ... }          // only output &quot;fizz&quot;
  public void buzz(printBuzz) { ... }          // only output &quot;buzz&quot;
  public void fizzbuzz(printFizzBuzz) { ... }  // only output &quot;fizzbuzz&quot;
  public void number(printNumber) { ... }      // only output the numbers
}</pre>

<p>Implement a multithreaded version of <code>FizzBuzz</code> with <strong>four</strong> threads. The same instance of <code>FizzBuzz</code> will be passed to four different threads:</p>

<ol>
	<li>Thread A will call&nbsp;<code>fizz()</code>&nbsp;to check for divisibility of 3 and outputs&nbsp;<code>fizz</code>.</li>
	<li>Thread B will call&nbsp;<code>buzz()</code>&nbsp;to check for divisibility of 5 and outputs&nbsp;<code>buzz</code>.</li>
	<li>Thread C will call <code>fizzbuzz()</code>&nbsp;to check for divisibility of 3 and 5 and outputs&nbsp;<code>fizzbuzz</code>.</li>
	<li>Thread D will call <code>number()</code> which should only output the numbers.</li>
</ol>

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
