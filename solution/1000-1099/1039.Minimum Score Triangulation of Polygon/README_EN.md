# [1039. Minimum Score Triangulation of Polygon](https://leetcode.com/problems/minimum-score-triangulation-of-polygon)

[中文文档](/solution/1000-1099/1039.Minimum%20Score%20Triangulation%20of%20Polygon/README.md)

## Description

<p>You have a convex <code>n</code>-sided polygon where each vertex has an integer value. You are given an integer array <code>values</code> where <code>values[i]</code> is the value of the <code>i<sup>th</sup></code> vertex (i.e., <strong>clockwise order</strong>).</p>

<p>You will <strong>triangulate</strong> the polygon into <code>n - 2</code> triangles. For each triangle, the value of that triangle is the product of the values of its vertices, and the total score of the triangulation is the sum of these values over all <code>n - 2</code> triangles in the triangulation.</p>

<p>Return <em>the smallest possible total score that you can achieve with some triangulation of the polygon</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1039.Minimum%20Score%20Triangulation%20of%20Polygon/images/shape1.jpg" style="width: 201px; height: 133px;" />
<pre>
<strong>Input:</strong> values = [1,2,3]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The polygon is already triangulated, and the score of the only triangle is 6.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1039.Minimum%20Score%20Triangulation%20of%20Polygon/images/shape2.jpg" style="width: 446px; height: 163px;" />
<pre>
<strong>Input:</strong> values = [3,7,4,5]
<strong>Output:</strong> 144
<strong>Explanation:</strong> There are two triangulations, with possible scores: 3*7*5 + 4*5*7 = 245, or 3*4*5 + 3*4*7 = 144.
The minimum score is 144.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1039.Minimum%20Score%20Triangulation%20of%20Polygon/images/shape3.jpg" style="width: 207px; height: 163px;" />
<pre>
<strong>Input:</strong> values = [1,3,1,4,1,5]
<strong>Output:</strong> 13
<strong>Explanation:</strong> The minimum score triangulation has score 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == values.length</code></li>
	<li><code>3 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= values[i] &lt;= 100</code></li>
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
