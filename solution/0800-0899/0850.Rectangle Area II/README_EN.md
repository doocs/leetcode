# [850. Rectangle Area II](https://leetcode.com/problems/rectangle-area-ii)

[中文文档](/solution/0800-0899/0850.Rectangle%20Area%20II/README.md)

## Description

<p>You are given a 2D array of axis-aligned <code>rectangles</code>. Each <code>rectangle[i] = [x<sub>i1</sub>, y<sub>i1</sub>, x<sub>i2</sub>, y<sub>i2</sub>]</code> denotes the <code>i<sup>th</sup></code> rectangle where <code>(x<sub>i1</sub>, y<sub>i1</sub>)</code> are the coordinates of the <strong>bottom-left corner</strong>, and <code>(x<sub>i2</sub>, y<sub>i2</sub>)</code> are the coordinates of the <strong>top-right corner</strong>.</p>

<p>Calculate the <strong>total area</strong> covered by all <code>rectangles</code> in the plane. Any area covered by two or more rectangles should only be counted <strong>once</strong>.</p>

<p>Return <em>the <strong>total area</strong></em>. Since the answer may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0850.Rectangle%20Area%20II/images/rectangle_area_ii_pic.png" style="width: 600px; height: 450px;" />
<pre>
<strong>Input:</strong> rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
<strong>Output:</strong> 6
<strong>Explanation: </strong>A total area of 6 is covered by all three rectangales, as illustrated in the picture.
From (1,1) to (2,2), the green and red rectangles overlap.
From (1,0) to (2,3), all three rectangles overlap.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> rectangles = [[0,0,1000000000,1000000000]]
<strong>Output:</strong> 49
<strong>Explanation: </strong>The answer is 10<sup>18</sup> modulo (10<sup>9</sup> + 7), which is 49.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= rectangles.length &lt;= 200</code></li>
	<li><code>rectanges[i].length == 4</code></li>
	<li><code>0 &lt;= x<sub>i1</sub>, y<sub>i1</sub>, x<sub>i2</sub>, y<sub>i2</sub> &lt;= 10<sup>9</sup></code></li>
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
