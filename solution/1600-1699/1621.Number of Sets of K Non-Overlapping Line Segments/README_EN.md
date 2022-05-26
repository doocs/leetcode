# [1621. Number of Sets of K Non-Overlapping Line Segments](https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments)

[中文文档](/solution/1600-1699/1621.Number%20of%20Sets%20of%20K%20Non-Overlapping%20Line%20Segments/README.md)

## Description

<p>Given <code>n</code> points on a 1-D plane, where the <code>i<sup>th</sup></code> point (from <code>0</code> to <code>n-1</code>) is at <code>x = i</code>, find the number of ways we can draw <strong>exactly</strong> <code>k</code> <strong>non-overlapping</strong> line segments such that each segment covers two or more points. The endpoints of each segment must have <strong>integral coordinates</strong>. The <code>k</code> line segments <strong>do not</strong> have to cover all <code>n</code> points, and they are <strong>allowed</strong> to share endpoints.</p>

<p>Return <em>the number of ways we can draw </em><code>k</code><em> non-overlapping line segments</em><em>.</em> Since this number can be huge, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1621.Number%20of%20Sets%20of%20K%20Non-Overlapping%20Line%20Segments/images/ex1.png" style="width: 179px; height: 222px;" />
<pre>
<strong>Input:</strong> n = 4, k = 2
<strong>Output:</strong> 5
<strong>Explanation:</strong> The two line segments are shown in red and blue.
The image above shows the 5 different ways {(0,2),(2,3)}, {(0,1),(1,3)}, {(0,1),(2,3)}, {(1,2),(2,3)}, {(0,1),(1,2)}.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong> The 3 ways are {(0,1)}, {(0,2)}, {(1,2)}.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 30, k = 7
<strong>Output:</strong> 796297179
<strong>Explanation:</strong> The total number of possible ways to draw 7 line segments is 3796297200. Taking this number modulo 10<sup>9</sup> + 7 gives us 796297179.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= n-1</code></li>
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
