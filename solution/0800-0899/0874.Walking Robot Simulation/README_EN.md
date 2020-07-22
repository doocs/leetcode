# [874. Walking Robot Simulation](https://leetcode.com/problems/walking-robot-simulation)

[中文文档](/solution/0800-0899/0874.Walking%20Robot%20Simulation/README.md)

## Description
<p>A robot on an infinite grid starts at point (0, 0) and faces north.&nbsp; The robot can receive one of three possible types of commands:</p>



<ul>

	<li><code>-2</code>: turn left 90 degrees</li>

	<li><code>-1</code>: turn right 90 degrees</li>

	<li><code>1 &lt;= x &lt;= 9</code>: move forward <code>x</code> units</li>

</ul>



<p>Some of the grid squares are obstacles.&nbsp;</p>



<p>The <code>i</code>-th obstacle is at grid point <code>(obstacles[i][0], obstacles[i][1])</code></p>



<p>If the robot would try to move onto them, the robot stays on the previous grid square instead (but still continues following the rest of the route.)</p>



<p>Return the <strong>square</strong> of the maximum Euclidean distance that the robot will be from the origin.</p>



<p>&nbsp;</p>



<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong>commands = <span id="example-input-1-1">[4,-1,3]</span>, obstacles = <span id="example-input-1-2">[]</span>

<strong>Output: </strong><span id="example-output-1">25</span>

<span>Explanation: </span>robot will go to (3, 4)

</pre>



<div>

<p><strong>Example 2:</strong></p>



<pre>

<strong>Input: </strong>commands = <span id="example-input-2-1">[4,-1,4,-2,4]</span>, obstacles = <span id="example-input-2-2">[[2,4]]</span>

<strong>Output: </strong><span id="example-output-2">65</span>

<strong>Explanation</strong>: robot will be stuck at (1, 4) before turning left and going to (1, 8)

</pre>

</div>



<p>&nbsp;</p>



<p><strong>Note:</strong></p>



<ol>

	<li><code>0 &lt;= commands.length &lt;= 10000</code></li>

	<li><code>0 &lt;= obstacles.length &lt;= 10000</code></li>

	<li><code>-30000 &lt;= obstacle[i][0] &lt;= 30000</code></li>

	<li><code>-30000 &lt;= obstacle[i][1] &lt;= 30000</code></li>

	<li>The answer is guaranteed to be less than <code>2 ^ 31</code>.</li>

</ol>




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