# [1828. Queries on Number of Points Inside a Circle](https://leetcode.com/problems/queries-on-number-of-points-inside-a-circle)

[中文文档](/solution/1800-1899/1828.Queries%20on%20Number%20of%20Points%20Inside%20a%20Circle/README.md)

## Description

<p>You are given an array <code>points</code> where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> is the coordinates of the <code>i<sup>th</sup></code> point on a 2D plane. Multiple points can have the <strong>same</strong> coordinates.</p>

<p>You are also given an array <code>queries</code> where <code>queries[j] = [x<sub>j</sub>, y<sub>j</sub>, r<sub>j</sub>]</code> describes a circle centered at <code>(x<sub>j</sub>, y<sub>j</sub>)</code> with a radius of <code>r<sub>j</sub></code>.</p>

<p>For each query <code>queries[j]</code>, compute the number of points <strong>inside</strong> the <code>j<sup>th</sup></code> circle. Points <strong>on the border</strong> of the circle are considered <strong>inside</strong>.</p>

<p>Return <em>an array </em><code>answer</code><em>, where </em><code>answer[j]</code><em> is the answer to the </em><code>j<sup>th</sup></code><em> query</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/25/chrome_2021-03-25_22-34-16.png" style="width: 500px; height: 418px;" />
<pre>
<strong>Input:</strong> points = [[1,3],[3,3],[5,3],[2,2]], queries = [[2,3,1],[4,3,1],[1,1,2]]
<strong>Output:</strong> [3,2,2]
<b>Explanation: </b>The points and circles are shown above.
queries[0] is the green circle, queries[1] is the red circle, and queries[2] is the blue circle.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/25/chrome_2021-03-25_22-42-07.png" style="width: 500px; height: 390px;" />
<pre>
<strong>Input:</strong> points = [[1,1],[2,2],[3,3],[4,4],[5,5]], queries = [[1,2,2],[2,2,2],[4,3,2],[4,3,3]]
<strong>Output:</strong> [2,3,2,4]
<b>Explanation: </b>The points and circles are shown above.
queries[0] is green, queries[1] is red, queries[2] is blue, and queries[3] is purple.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 500</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>​​​​​​i</sub>, y<sub>​​​​​​i</sub> &lt;= 500</code></li>
	<li><code>1 &lt;= queries.length &lt;= 500</code></li>
	<li><code>queries[j].length == 3</code></li>
	<li><code>0 &lt;= x<sub>j</sub>, y<sub>j</sub> &lt;= 500</code></li>
	<li><code>1 &lt;= r<sub>j</sub> &lt;= 500</code></li>
	<li>All coordinates are integers.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you find the answer for each query in better complexity than <code>O(n)</code>?</p>


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
