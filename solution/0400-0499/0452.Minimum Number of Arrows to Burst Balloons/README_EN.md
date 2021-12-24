# [452. Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons)

[中文文档](/solution/0400-0499/0452.Minimum%20Number%20of%20Arrows%20to%20Burst%20Balloons/README.md)

## Description

<p>There are some spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it&#39;s horizontal, y-coordinates don&#39;t matter, and hence the x-coordinates of start and end of the diameter suffice. The start is always smaller than the end.</p>

<p>An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with <code>x<sub>start</sub></code> and <code>x<sub>end</sub></code> bursts by an arrow shot at <code>x</code> if <code>x<sub>start</sub> &le; x &le; x<sub>end</sub></code>. There is no limit to the number of arrows that can be shot. An arrow once shot keeps traveling up infinitely.</p>

<p>Given an array <code>points</code> where <code>points[i] = [x<sub>start</sub>, x<sub>end</sub>]</code>, return <em>the minimum number of arrows that must be shot to burst all balloons</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> points = [[10,16],[2,8],[1,6],[7,12]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> points = [[1,2],[3,4],[5,6],[7,8]]
<strong>Output:</strong> 4
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> points = [[1,2],[2,3],[3,4],[4,5]]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= points.length &lt;= 10<sup>4</sup></code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>-2<sup>31</sup> &lt;= x<sub>start</sub> &lt; x<sub>end</sub> &lt;= 2<sup>31</sup> - 1</code></li>
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
