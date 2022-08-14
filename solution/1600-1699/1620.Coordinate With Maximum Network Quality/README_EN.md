# [1620. Coordinate With Maximum Network Quality](https://leetcode.com/problems/coordinate-with-maximum-network-quality)

[中文文档](/solution/1600-1699/1620.Coordinate%20With%20Maximum%20Network%20Quality/README.md)

## Description

<p>You are given an array of network towers <code>towers</code>, where <code>towers[i] = [x<sub>i</sub>, y<sub>i</sub>, q<sub>i</sub>]</code> denotes the <code>i<sup>th</sup></code> network tower with location <code>(x<sub>i</sub>, y<sub>i</sub>)</code> and quality factor <code>q<sub>i</sub></code>. All the coordinates are <strong>integral coordinates</strong> on the X-Y plane, and the distance between the two coordinates is the <strong>Euclidean distance</strong>.</p>

<p>You are also given an integer <code>radius</code> where a tower is <strong>reachable</strong> if the distance is <strong>less than or equal to</strong> <code>radius</code>. Outside that distance, the signal becomes garbled, and the tower is <strong>not reachable</strong>.</p>

<p>The signal quality of the <code>i<sup>th</sup></code> tower at a coordinate <code>(x, y)</code> is calculated with the formula <code>&lfloor;q<sub>i</sub> / (1 + d)&rfloor;</code>, where <code>d</code> is the distance between the tower and the coordinate. The <strong>network quality</strong> at a coordinate is the sum of the signal qualities from all the <strong>reachable</strong> towers.</p>

<p>Return <em>the array </em><code>[c<sub>x</sub>, c<sub>y</sub>]</code><em> representing the <strong>integral</strong> coordinate </em><code>(c<sub>x</sub>, c<sub>y</sub>)</code><em> where the <strong>network quality</strong> is maximum. If there are multiple coordinates with the same <strong>network quality</strong>, return the lexicographically minimum <strong>non-negative</strong> coordinate.</em></p>

<p><strong>Note:</strong></p>

<ul>
	<li>A coordinate <code>(x1, y1)</code> is lexicographically smaller than <code>(x2, y2)</code> if either:

    <ul>
    	<li><code>x1 &lt; x2</code>, or</li>
    	<li><code>x1 == x2</code> and <code>y1 &lt; y2</code>.</li>
    </ul>
    </li>
    <li><code>&lfloor;val&rfloor;</code> is the greatest integer less than or equal to <code>val</code> (the floor function).</li>

</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1620.Coordinate%20With%20Maximum%20Network%20Quality/images/untitled-diagram.png" style="width: 176px; height: 176px;" />
<pre>
<strong>Input:</strong> towers = [[1,2,5],[2,1,7],[3,1,9]], radius = 2
<strong>Output:</strong> [2,1]
<strong>Explanation:</strong> At coordinate (2, 1) the total quality is 13.
- Quality of 7 from (2, 1) results in &lfloor;7 / (1 + sqrt(0)&rfloor; = &lfloor;7&rfloor; = 7
- Quality of 5 from (1, 2) results in &lfloor;5 / (1 + sqrt(2)&rfloor; = &lfloor;2.07&rfloor; = 2
- Quality of 9 from (3, 1) results in &lfloor;9 / (1 + sqrt(1)&rfloor; = &lfloor;4.5&rfloor; = 4
No other coordinate has a higher network quality.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> towers = [[23,11,21]], radius = 9
<strong>Output:</strong> [23,11]
<strong>Explanation:</strong> Since there is only one tower, the network quality is highest right at the tower&#39;s location.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> towers = [[1,2,13],[2,1,7],[0,1,9]], radius = 2
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> Coordinate (1, 2) has the highest network quality.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= towers.length &lt;= 50</code></li>
	<li><code>towers[i].length == 3</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub>, q<sub>i</sub> &lt;= 50</code></li>
	<li><code>1 &lt;= radius &lt;= 50</code></li>
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
