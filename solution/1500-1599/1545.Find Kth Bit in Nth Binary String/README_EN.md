# [1545. Find Kth Bit in Nth Binary String](https://leetcode.com/problems/find-kth-bit-in-nth-binary-string)

[中文文档](/solution/1500-1599/1545.Find%20Kth%20Bit%20in%20Nth%20Binary%20String/README.md)

## Description

<p>Given two positive integers&nbsp;<code>n</code>&nbsp;and <code>k</code>,&nbsp;the binary string&nbsp;&nbsp;<code>S<sub>n</sub></code>&nbsp;is formed as follows:</p>

<ul>
	<li><code>S<sub>1</sub>&nbsp;= &quot;0&quot;</code></li>
	<li><code>S<sub><span style="font-size: 10.8333px;">i</span></sub>&nbsp;=&nbsp;S<sub><span style="font-size: 10.8333px;">i-1</span></sub>&nbsp;+ &quot;1&quot; + reverse(invert(S<sub><span style="font-size: 10.8333px;">i-1</span></sub>))</code>&nbsp;for&nbsp;<code>i &gt; 1</code></li>
</ul>

<p>Where&nbsp;<code>+</code>&nbsp;denotes the concatenation operation,&nbsp;<code>reverse(x)</code>&nbsp;returns the reversed string <font face="monospace">x,</font>&nbsp;and&nbsp;<code>invert(x)</code>&nbsp;inverts all the bits in <font face="monospace">x</font> (0 changes to 1 and 1 changes to 0).</p>

<p>For example, the first 4 strings in the above sequence are:</p>

<ul>
	<li><code>S<sub>1&nbsp;</sub>= &quot;0&quot;</code></li>
	<li><code>S<sub>2&nbsp;</sub>= &quot;0<strong>1</strong>1&quot;</code></li>
	<li><code>S<sub>3&nbsp;</sub>= &quot;011<strong>1</strong>001&quot;</code></li>
	<li><code>S<sub>4</sub> = &quot;0111001<strong>1</strong>0110001&quot;</code></li>
</ul>

<p>Return <em>the</em> <code>k<sup>th</sup></code> <em>bit</em> <em>in</em>&nbsp;<code>S<sub>n</sub></code>. It is guaranteed that&nbsp;<code>k</code>&nbsp;is valid for the given&nbsp;<code>n</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> n = 3, k = 1

<strong>Output:</strong> &quot;0&quot;

<strong>Explanation: </strong>S<sub>3</sub>&nbsp;is &quot;<strong><u>0</u></strong>111001&quot;. The first bit is &quot;0&quot;.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> n = 4, k = 11

<strong>Output:</strong> &quot;1&quot;

<strong>Explanation: </strong>S<sub>4</sub>&nbsp;is &quot;0111001101<strong><u>1</u></strong>0001&quot;. The 11th bit is &quot;1&quot;.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> n = 1, k = 1

<strong>Output:</strong> &quot;0&quot;

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> n = 2, k = 3

<strong>Output:</strong> &quot;1&quot;

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 20</code></li>
	<li><code>1 &lt;= k &lt;= 2<sup>n</sup> - 1</code></li>
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
