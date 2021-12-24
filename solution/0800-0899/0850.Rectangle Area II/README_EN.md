# [850. Rectangle Area II](https://leetcode.com/problems/rectangle-area-ii)

[中文文档](/solution/0800-0899/0850.Rectangle%20Area%20II/README.md)

## Description

<p>We are given a list of (axis-aligned) <code>rectangles</code>. Each <code>rectangle[i] = [x<sub>i1</sub>, y<sub>i1</sub>, x<sub>i2</sub>, y<sub>i2</sub>] </code>, where <code>(x<sub>i1</sub>, y<sub>i1</sub>)</code> are the coordinates of the bottom-left corner, and <code>(x<sub>i2</sub>, y<sub>i2</sub>)</code> are the coordinates of the top-right corner of the <code>i<sup>th</sup></code> rectangle.</p>

<p>Find the total area covered by all <code>rectangles</code> in the plane. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0850.Rectangle%20Area%20II/images/rectangle_area_ii_pic.png" style="width: 600px; height: 450px;" />
<pre>
<strong>Input:</strong> rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
<strong>Output:</strong> 6
<strong>Explanation: </strong>As illustrated in the picture.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> rectangles = [[0,0,1000000000,1000000000]]
<strong>Output:</strong> 49
<strong>Explanation: </strong>The answer is 10<sup>18</sup> modulo (10<sup>9</sup> + 7), which is (10<sup>9</sup>)<sup>2</sup> = (-7)<sup>2</sup> = 49.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= rectangles.length &lt;= 200</code></li>
	<li><code><font face="monospace">rectanges[i].length = 4</font></code></li>
	<li><code>0 &lt;= rectangles[i][j] &lt;= 10<sup>9</sup></code></li>
	<li>The total area covered by all rectangles will never exceed <code>2<sup>63</sup> - 1</code> and thus will fit in a <strong>64-bit</strong> signed integer.</li>
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
