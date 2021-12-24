# [800. Similar RGB Color](https://leetcode.com/problems/similar-rgb-color)

[中文文档](/solution/0800-0899/0800.Similar%20RGB%20Color/README.md)

## Description

<p>In the following, every capital letter represents some hexadecimal digit from <code>0</code> to <code>f</code>.</p>

<p>The red-green-blue color <code>&quot;#AABBCC&quot;</code>&nbsp;can be written&nbsp;as&nbsp;<code>&quot;#ABC&quot;</code> in&nbsp;shorthand.&nbsp; For example, <code>&quot;#15c&quot;</code> is shorthand for the color <code>&quot;#1155cc&quot;</code>.</p>

<p>Now, say the similarity between two colors <code>&quot;#ABCDEF&quot;</code> and <code>&quot;#UVWXYZ&quot;</code> is <code>-(AB - UV)^2 -&nbsp;(CD - WX)^2 -&nbsp;(EF - YZ)^2</code>.</p>

<p>Given the color <code>&quot;#ABCDEF&quot;</code>, return a 7 character color&nbsp;that is most similar to <code>#ABCDEF</code>, and has a shorthand (that is, it can be represented as some <code>&quot;#XYZ&quot;</code></p>

<pre>

<strong>Example 1:</strong>

<strong>Input:</strong> color = &quot;#09f166&quot;

<strong>Output:</strong> &quot;#11ee66&quot;

<strong>Explanation: </strong> 

The similarity is -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73.

This is the highest among any shorthand color.

</pre>

<p><strong>Note:</strong></p>

<ul>
	<li><code>color</code> is a string of length <code>7</code>.</li>
	<li><code>color</code> is a valid RGB color: for <code>i &gt; 0</code>, <code>color[i]</code> is a hexadecimal digit from <code>0</code> to <code>f</code></li>
	<li>Any answer which has the same (highest)&nbsp;similarity as the best answer will be accepted.</li>
	<li>All inputs and outputs should use lowercase letters, and the output is 7 characters.</li>
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
