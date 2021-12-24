# [489. Robot Room Cleaner](https://leetcode.com/problems/robot-room-cleaner)

[中文文档](/solution/0400-0499/0489.Robot%20Room%20Cleaner/README.md)

## Description

<p>Given a robot cleaner in a room modeled as a grid.</p>

<p>Each cell in the grid can be empty or blocked.</p>

<p>The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.</p>

<p>When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.</p>

<p>Design an algorithm to clean the entire room using only the 4 given APIs shown below.</p>

<pre>

interface Robot {

&nbsp; // returns true if next cell is open and robot moves into the cell.

&nbsp; // returns false if next cell is obstacle and robot stays on the current cell.

&nbsp; boolean move();



  // Robot will stay on the same cell after calling turnLeft/turnRight.

&nbsp; // Each turn will be 90 degrees.

&nbsp; void turnLeft();

&nbsp; void turnRight();



  // Clean the current cell.

  void clean();

}

</pre>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong>

room = [

  [1,1,1,1,1,0,1,1],

  [1,1,1,1,1,0,1,1],

  [1,0,1,1,1,1,1,1],

  [0,0,0,1,0,0,0,0],

  [1,1,1,1,1,1,1,1]

],

row = 1,

col = 3



<strong>Explanation:</strong>

All grids in the room are marked by either 0 or 1.

0 means the cell is blocked, while 1 means the cell is accessible.

The robot initially starts at the position of row=1, col=3.

From the top left corner, its position is one row below and three columns right.

</pre>

<p><strong>Notes:</strong></p>

<ol>
	<li>The input is only given to initialize the room and the robot&#39;s position internally.&nbsp;You must solve this problem &quot;blindfolded&quot;. In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot&#39;s position.</li>
	<li>The robot&#39;s initial position will always be in an accessible cell.</li>
	<li>The initial direction of the robot will be facing up.</li>
	<li>All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.</li>
	<li>Assume all four edges of the grid are all surrounded by wall.</li>
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
