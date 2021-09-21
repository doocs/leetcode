# [2013. Detect Squares](https://leetcode.com/problems/detect-squares)

[中文文档](/solution/2000-2099/2013.Detect%20Squares/README.md)

## Description

<p>You are given a stream of points on the X-Y plane. Design an algorithm that:</p>

<ul>
	<li><strong>Adds</strong> new points from the stream into a data structure. <strong>Duplicate</strong> points are allowed and should be treated as different points.</li>
	<li>Given a query point, <strong>counts</strong> the number of ways to choose three points from the data structure such that the three points and the query point form an <strong>axis-aligned square</strong> with <strong>positive area</strong>.</li>
</ul>

<p>An <strong>axis-aligned square</strong> is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis.</p>

<p>Implement the <code>DetectSquares</code> class:</p>

<ul>
	<li><code>DetectSquares()</code> Initializes the object with an empty data structure.</li>
	<li><code>void add(int[] point)</code> Adds a new point <code>point = [x, y]</code> to the data structure.</li>
	<li><code>int count(int[] point)</code> Counts the number of ways to form <strong>axis-aligned squares</strong> with point <code>point = [x, y]</code> as described above.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2013.Detect%20Squares/images/image.png" style="width: 869px; height: 504px;" />
<pre>
<strong>Input</strong>
[&quot;DetectSquares&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;, &quot;count&quot;, &quot;count&quot;, &quot;add&quot;, &quot;count&quot;]
[[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
<strong>Output</strong>
[null, null, null, null, 1, 0, null, 2]

<strong>Explanation</strong>
DetectSquares detectSquares = new DetectSquares();
detectSquares.add([3, 10]);
detectSquares.add([11, 2]);
detectSquares.add([3, 2]);
detectSquares.count([11, 10]); // return 1. You can choose:
// - The first, second, and third points
detectSquares.count([14, 8]); // return 0. The query point cannot form a square with any points in the data structure.
detectSquares.add([11, 2]); // Adding duplicate points is allowed.
detectSquares.count([11, 10]); // return 2. You can choose:
// - The first, second, and third points
// - The first, third, and fourth points

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>point.length == 2</code></li>
	<li><code>0 &lt;= x, y &lt;= 1000</code></li>
	<li>At most <code>5000</code> calls <strong>in total</strong> will be made to <code>add</code> and <code>count</code>.</li>
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
