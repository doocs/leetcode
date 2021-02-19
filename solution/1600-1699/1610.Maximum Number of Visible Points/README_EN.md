# [1610. Maximum Number of Visible Points](https://leetcode.com/problems/maximum-number-of-visible-points)

[中文文档](/solution/1600-1699/1610.Maximum%20Number%20of%20Visible%20Points/README.md)

## Description

<p>You are given an array <code>points</code>, an integer <code>angle</code>, and your <code>location</code>, where <code>location = [pos<sub>x</sub>, pos<sub>y</sub>]</code> and <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> both denote <strong>integral coordinates</strong> on the X-Y plane.</p>

<p>Initially, you are facing directly east from your position. You <strong>cannot move</strong> from your position, but you can <strong>rotate</strong>. In other words, <code>pos<sub>x</sub></code> and <code>pos<sub>y</sub></code> cannot be changed. Your field of view in <strong>degrees</strong> is represented by <code>angle</code>, determining how wide you can see from any given view direction. Let <code>d</code> be the amount in degrees that you rotate counterclockwise. Then, your field of view is the <strong>inclusive</strong> range of angles <code>[d - angle/2, d + angle/2]</code>.</p>

<p>
<video autoplay="" controls="" height="360" muted="" style="max-width:100%;height:auto;" width="480"><source src="https://assets.leetcode.com/uploads/2020/09/30/angle.mp4" type="video/mp4" />Your browser does not support the video tag or this video format.</video>
</p>

<p>You can <strong>see</strong> some set of points if, for each point, the <strong>angle</strong> formed by the point, your position, and the immediate east direction from your position is <strong>in your field of view</strong>.</p>

<p>There can be multiple points at one coordinate. There may be points at your location, and you can always see these points regardless of your rotation. Points do not obstruct your vision to other points.</p>

<p>Return <em>the maximum number of points you can see</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/09/30/89a07e9b-00ab-4967-976a-c723b2aa8656.png" style="width: 400px; height: 300px;" />
<pre>
<strong>Input:</strong> points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The shaded region represents your field of view. All points can be made visible in your field of view, including [3,3] even though [2,2] is in front and in the same line of sight.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> All points can be made visible in your field of view, including the one at your location.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/09/30/5010bfd3-86e6-465f-ac64-e9df941d2e49.png" style="width: 690px; height: 348px;" />
<pre>
<strong>Input:</strong> points = [[1,0],[2,1]], angle = 13, location = [1,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> You can only see one of the two points, as shown above.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 10<sup>5</sup></code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>location.length == 2</code></li>
	<li><code>0 &lt;= angle &lt; 360</code></li>
	<li><code>0 &lt;= pos<sub>x</sub>, pos<sub>y</sub>, x<sub>i</sub>, y<sub>i</sub> &lt;= 100</code></li>
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
