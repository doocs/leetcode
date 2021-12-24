# [1416. Restore The Array](https://leetcode.com/problems/restore-the-array)

[中文文档](/solution/1400-1499/1416.Restore%20The%20Array/README.md)

## Description

<p>A program was supposed to print an array of integers. The program forgot to print whitespaces and the array is printed as a string of digits and all we know is that all integers in the array were in the range&nbsp;<code>[1, k]</code>&nbsp;and there are no leading zeros in the array.</p>

<p>Given the string <code>s</code> and the integer <code>k</code>. There can be multiple ways to restore the array.</p>

<p>Return <em>the number of possible array</em> that can be printed as a string <code>s</code>&nbsp;using the mentioned program.</p>

<p>The number of ways could be very large so return it <strong>modulo</strong> <code>10^9 + 7</code></p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> s = &quot;1000&quot;, k = 10000

<strong>Output:</strong> 1

<strong>Explanation:</strong> The only possible array is [1000]

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> s = &quot;1000&quot;, k = 10

<strong>Output:</strong> 0

<strong>Explanation:</strong> There cannot be an array that was printed this way and has all integer &gt;= 1 and &lt;= 10.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> s = &quot;1317&quot;, k = 2000

<strong>Output:</strong> 8

<strong>Explanation:</strong> Possible arrays are [1317],[131,7],[13,17],[1,317],[13,1,7],[1,31,7],[1,3,17],[1,3,1,7]

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> s = &quot;2020&quot;, k = 30

<strong>Output:</strong> 1

<strong>Explanation:</strong> The only possible array is [20,20]. [2020] is invalid because 2020 &gt; 30. [2,020] is ivalid because 020 contains leading zeros.

</pre>

<p><strong>Example 5:</strong></p>

<pre>

<strong>Input:</strong> s = &quot;1234567890&quot;, k = 90

<strong>Output:</strong> 34

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code>.</li>
	<li><code>s</code> consists of only digits and doesn&#39;t contain leading zeros.</li>
	<li><code>1 &lt;= k &lt;= 10^9</code>.</li>
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
