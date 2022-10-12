# [2069. Walking Robot Simulation II](https://leetcode.com/problems/walking-robot-simulation-ii)

[中文文档](/solution/2000-2099/2069.Walking%20Robot%20Simulation%20II/README.md)

## Description

<p>A <code>width x height</code> grid is on an XY-plane with the <strong>bottom-left</strong> cell at <code>(0, 0)</code> and the <strong>top-right</strong> cell at <code>(width - 1, height - 1)</code>. The grid is aligned with the four cardinal directions (<code>&quot;North&quot;</code>, <code>&quot;East&quot;</code>, <code>&quot;South&quot;</code>, and <code>&quot;West&quot;</code>). A robot is <strong>initially</strong> at cell <code>(0, 0)</code> facing direction <code>&quot;East&quot;</code>.</p>

<p>The robot can be instructed to move for a specific number of <strong>steps</strong>. For each step, it does the following.</p>

<ol>
	<li>Attempts to move <strong>forward one</strong> cell in the direction it is facing.</li>
	<li>If the cell the robot is <strong>moving to</strong> is <strong>out of bounds</strong>, the robot instead <strong>turns</strong> 90 degrees <strong>counterclockwise</strong> and retries the step.</li>
</ol>

<p>After the robot finishes moving the number of steps required, it stops and awaits the next instruction.</p>

<p>Implement the <code>Robot</code> class:</p>

<ul>
	<li><code>Robot(int width, int height)</code> Initializes the <code>width x height</code> grid with the robot at <code>(0, 0)</code> facing <code>&quot;East&quot;</code>.</li>
	<li><code>void step(int num)</code> Instructs the robot to move forward <code>num</code> steps.</li>
	<li><code>int[] getPos()</code> Returns the current cell the robot is at, as an array of length 2, <code>[x, y]</code>.</li>
	<li><code>String getDir()</code> Returns the current direction of the robot, <code>&quot;North&quot;</code>, <code>&quot;East&quot;</code>, <code>&quot;South&quot;</code>, or <code>&quot;West&quot;</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="example-1" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2069.Walking%20Robot%20Simulation%20II/images/example-1.png" style="width: 498px; height: 268px;" />
<pre>
<strong>Input</strong>
[&quot;Robot&quot;, &quot;step&quot;, &quot;step&quot;, &quot;getPos&quot;, &quot;getDir&quot;, &quot;step&quot;, &quot;step&quot;, &quot;step&quot;, &quot;getPos&quot;, &quot;getDir&quot;]
[[6, 3], [2], [2], [], [], [2], [1], [4], [], []]
<strong>Output</strong>
[null, null, null, [4, 0], &quot;East&quot;, null, null, null, [1, 2], &quot;West&quot;]

<strong>Explanation</strong>
Robot robot = new Robot(6, 3); // Initialize the grid and the robot at (0, 0) facing East.
robot.step(2); // It moves two steps East to (2, 0), and faces East.
robot.step(2); // It moves two steps East to (4, 0), and faces East.
robot.getPos(); // return [4, 0]
robot.getDir(); // return &quot;East&quot;
robot.step(2); // It moves one step East to (5, 0), and faces East.
// Moving the next step East would be out of bounds, so it turns and faces North.
// Then, it moves one step North to (5, 1), and faces North.
robot.step(1); // It moves one step North to (5, 2), and faces <strong>North</strong> (not West).
robot.step(4); // Moving the next step North would be out of bounds, so it turns and faces West.
// Then, it moves four steps West to (1, 2), and faces West.
robot.getPos(); // return [1, 2]
robot.getDir(); // return &quot;West&quot;

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= width, height &lt;= 100</code></li>
	<li><code>1 &lt;= num &lt;= 10<sup>5</sup></code></li>
	<li>At most <code>10<sup>4</sup></code> calls <strong>in total</strong> will be made to <code>step</code>, <code>getPos</code>, and <code>getDir</code>.</li>
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
