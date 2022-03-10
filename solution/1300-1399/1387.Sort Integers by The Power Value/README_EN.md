# [1387. Sort Integers by The Power Value](https://leetcode.com/problems/sort-integers-by-the-power-value)

[中文文档](/solution/1300-1399/1387.Sort%20Integers%20by%20The%20Power%20Value/README.md)

## Description

<p>The power of an integer <code>x</code> is defined as the number of steps needed to transform <code>x</code> into <code>1</code> using the following steps:</p>

<ul>
	<li>if <code>x</code> is even then <code>x = x / 2</code></li>
	<li>if <code>x</code> is odd then <code>x = 3 * x + 1</code></li>
</ul>

<p>For example, the power of <code>x = 3</code> is <code>7</code> because <code>3</code> needs <code>7</code> steps to become <code>1</code> (<code>3 --&gt; 10 --&gt; 5 --&gt; 16 --&gt; 8 --&gt; 4 --&gt; 2 --&gt; 1</code>).</p>

<p>Given three integers <code>lo</code>, <code>hi</code> and <code>k</code>. The task is to sort all integers in the interval <code>[lo, hi]</code> by the power value in <strong>ascending order</strong>, if two or more integers have <strong>the same</strong> power value sort them by <strong>ascending order</strong>.</p>

<p>Return the <code>k<sup>th</sup></code> integer in the range <code>[lo, hi]</code> sorted by the power value.</p>

<p>Notice that for any integer <code>x</code> <code>(lo &lt;= x &lt;= hi)</code> it is <strong>guaranteed</strong> that <code>x</code> will transform into <code>1</code> using these steps and that the power of <code>x</code> is will <strong>fit</strong> in a 32-bit signed integer.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> lo = 12, hi = 15, k = 2
<strong>Output:</strong> 13
<strong>Explanation:</strong> The power of 12 is 9 (12 --&gt; 6 --&gt; 3 --&gt; 10 --&gt; 5 --&gt; 16 --&gt; 8 --&gt; 4 --&gt; 2 --&gt; 1)
The power of 13 is 9
The power of 14 is 17
The power of 15 is 17
The interval sorted by the power value [12,13,14,15]. For k = 2 answer is the second element which is 13.
Notice that 12 and 13 have the same power value and we sorted them in ascending order. Same for 14 and 15.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> lo = 7, hi = 11, k = 4
<strong>Output:</strong> 7
<strong>Explanation:</strong> The power array corresponding to the interval [7, 8, 9, 10, 11] is [16, 3, 19, 6, 14].
The interval sorted by power is [8, 10, 11, 7, 9].
The fourth number in the sorted array is 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= lo &lt;= hi &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= hi - lo + 1</code></li>
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
