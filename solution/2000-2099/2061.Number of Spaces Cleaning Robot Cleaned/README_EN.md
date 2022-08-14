# [2061. Number of Spaces Cleaning Robot Cleaned](https://leetcode.com/problems/number-of-spaces-cleaning-robot-cleaned)

[中文文档](/solution/2000-2099/2061.Number%20of%20Spaces%20Cleaning%20Robot%20Cleaned/README.md)

## Description

<p>A room is represented by a <strong>0-indexed</strong> 2D binary matrix <code>room</code> where a <code>0</code> represents an <strong>empty</strong> space and a <code>1</code> represents a space with an <strong>object</strong>. The top left corner of the room will be empty in all test cases.</p>

<p>A cleaning robot starts at the top left corner of the room and is facing right. The robot will continue heading straight until it reaches the edge of the room or it hits an object, after which it will turn 90 degrees <strong>clockwise</strong> and repeat this process. The starting space and all spaces that the robot visits are <strong>cleaned</strong> by it.</p>

<p>Return <em>the number of <strong>clean</strong> spaces in the room if the robot runs indefinetely.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2061.Number%20of%20Spaces%20Cleaning%20Robot%20Cleaned/images/image-20211101204703-1.png" style="width: 250px; height: 242px;" /></p>

<pre>
<strong>Input:</strong> room = [[0,0,0],[1,1,0],[0,0,0]]
<strong>Output:</strong> 7
<strong>Explanation:</strong>
The robot cleans the spaces at (0, 0), (0, 1), and (0, 2).
The robot is at the edge of the room, so it turns 90 degrees clockwise and now faces down.
The robot cleans the spaces at (1, 2), and (2, 2).
The robot is at the edge of the room, so it turns 90 degrees clockwise and now faces left.
The robot cleans the spaces at (2, 1), and (2, 0).
The robot has cleaned all 7 empty spaces, so return 7.
</pre>

<p><strong>Example 2:</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2061.Number%20of%20Spaces%20Cleaning%20Robot%20Cleaned/images/image-20211101204736-2.png" style="width: 250px; height: 245px;" /></p>

<pre>
<strong>Input:</strong> room = [[0,1,0],[1,0,0],[0,0,0]]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
The robot cleans the space at (0, 0).
The robot hits an object, so it turns 90 degrees clockwise and now faces down.
The robot hits an object, so it turns 90 degrees clockwise and now faces left.
The robot is at the edge of the room, so it turns 90 degrees clockwise and now faces up.
The robot is at the edge of the room, so it turns 90 degrees clockwise and now faces right.
The robot is back at its starting position.
The robot has cleaned 1 space, so return 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == room.length</code></li>
	<li><code>n == room[r].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>room[r][c]</code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>room[0][0] == 0</code></li>
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
