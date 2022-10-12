# [1274. Number of Ships in a Rectangle](https://leetcode.com/problems/number-of-ships-in-a-rectangle)

[中文文档](/solution/1200-1299/1274.Number%20of%20Ships%20in%20a%20Rectangle/README.md)

## Description

<p><em>(This problem is an <strong>interactive problem</strong>.)</em></p>

<p>Each ship is located at an integer point on the sea represented by a cartesian plane, and each integer point may contain at most 1 ship.</p>

<p>You have a function <code>Sea.hasShips(topRight, bottomLeft)</code> which takes two points as arguments and returns <code>true</code> If there is at least one ship in the rectangle represented by the two points, including on the boundary.</p>

<p>Given two points: the top right and bottom left corners of a rectangle, return the number of ships present in that rectangle. It is guaranteed that there are <strong>at most 10 ships</strong> in that rectangle.</p>

<p>Submissions making <strong>more than 400 calls</strong> to <code>hasShips</code> will be judged <em>Wrong Answer</em>. Also, any solutions that attempt to circumvent the judge will result in disqualification.</p>

<p>&nbsp;</p>
<p><strong class="example">Example :</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1274.Number%20of%20Ships%20in%20a%20Rectangle/images/1445_example_1.png" style="width: 496px; height: 500px;" />
<pre>
<strong>Input:</strong> 
ships = [[1,1],[2,2],[3,3],[5,5]], topRight = [4,4], bottomLeft = [0,0]
<strong>Output:</strong> 3
<strong>Explanation:</strong> From [0,0] to [4,4] we can count 3 ships within the range.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> ans = [[1,1],[2,2],[3,3]], topRight = [1000,1000], bottomLeft = [0,0]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>On the input <code>ships</code> is only given to initialize the map internally. You must solve this problem &quot;blindfolded&quot;. In other words, you must find the answer using the given <code>hasShips</code> API, without knowing the <code>ships</code> position.</li>
	<li><code>0 &lt;= bottomLeft[0] &lt;= topRight[0] &lt;= 1000</code></li>
	<li><code>0 &lt;= bottomLeft[1] &lt;= topRight[1] &lt;= 1000</code></li>
	<li><code>topRight != bottomLeft</code></li>
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
