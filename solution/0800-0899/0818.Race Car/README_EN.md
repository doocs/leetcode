# [818. Race Car](https://leetcode.com/problems/race-car)

[中文文档](/solution/0800-0899/0818.Race%20Car/README.md)

## Description

<p>Your car starts at position 0 and speed +1 on an infinite number line.&nbsp; (Your car can go into negative positions.)</p>

<p>Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).</p>

<p>When you get an instruction &quot;A&quot;, your car does the following:&nbsp;<code>position += speed, speed *= 2</code>.</p>

<p>When you get an instruction &quot;R&quot;, your car does the following: if your speed is positive then&nbsp;<code>speed = -1</code>&nbsp;, otherwise&nbsp;<code>speed = 1</code>.&nbsp; (Your position stays the same.)</p>

<p>For example, after commands &quot;AAR&quot;, your car goes to positions 0-&gt;1-&gt;3-&gt;3, and your speed goes to 1-&gt;2-&gt;4-&gt;-1.</p>

<p>Now for some target position, say the <strong>length</strong> of the shortest sequence of instructions to get there.</p>

<pre>

<strong>Example 1:</strong>

<strong>Input:</strong> 

target = 3

<strong>Output:</strong> 2

<strong>Explanation:</strong> 

The shortest instruction sequence is &quot;AA&quot;.

Your position goes from 0-&gt;1-&gt;3.

</pre>

<pre>

<strong>Example 2:</strong>

<strong>Input:</strong> 

target = 6

<strong>Output:</strong> 5

<strong>Explanation:</strong> 

The shortest instruction sequence is &quot;AAARA&quot;.

Your position goes from 0-&gt;1-&gt;3-&gt;7-&gt;7-&gt;6.

</pre>

<p>&nbsp;</p>

<p><strong>Note: </strong></p>

<ul>
	<li><code>1 &lt;= target &lt;= 10000</code>.</li>
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
