# [800. Similar RGB Color](https://leetcode.com/problems/similar-rgb-color)

[中文文档](/solution/0800-0899/0800.Similar%20RGB%20Color/README.md)

## Description

<p>In the following, every capital letter represents some hexadecimal digit from <code>0</code> to <code>f</code>.</p>

<p>The red-green-blue color <code>"#AABBCC"</code> can be written as <code>"#ABC"</code> in shorthand.  For example, <code>"#15c"</code> is shorthand for the color <code>"#1155cc"</code>.</p>

<p>Now, say the similarity between two colors <code>"#ABCDEF"</code> and <code>"#UVWXYZ"</code> is <code>-(AB - UV)^2 - (CD - WX)^2 - (EF - YZ)^2</code>.</p>

<p>Given the color <code>"#ABCDEF"</code>, return a 7 character color that is most similar to <code>#ABCDEF</code>, and has a shorthand (that is, it can be represented as some <code>"#XYZ"</code></p>

<pre>
<strong>Example 1:</strong>
<strong>Input:</strong> color = "#09f166"
<strong>Output:</strong> "#11ee66"
<strong>Explanation: </strong> 
The similarity is -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73.
This is the highest among any shorthand color.
</pre>

<p><strong>Note:</strong></p>

<ul>
	<li><code>color</code> is a string of length <code>7</code>.</li>
	<li><code>color</code> is a valid RGB color: for <code>i > 0</code>, <code>color[i]</code> is a hexadecimal digit from <code>0</code> to <code>f</code></li>
	<li>Any answer which has the same (highest) similarity as the best answer will be accepted.</li>
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
