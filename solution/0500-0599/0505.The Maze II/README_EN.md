# [505. The Maze II](https://leetcode.com/problems/the-maze-ii)

[中文文档](/solution/0500-0599/0505.The%20Maze%20II/README.md)

## Description
<p>There is a <b>ball</b> in a maze with empty spaces and walls. The ball can go through empty spaces by rolling <b>up</b>, <b>down</b>, <b>left</b> or <b>right</b>, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.</p>

<p>Given the ball's <b>start position</b>, the <b>destination</b> and the <b>maze</b>, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of <b>empty spaces</b> traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.</p>

<p>The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.</p>

<p> </p>

<p><b>Example 1:</b></p>

<pre>
<b>Input 1:</b> a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

<b>Input 2:</b> start coordinate (rowStart, colStart) = (0, 4)
<b>Input 3:</b> destination coordinate (rowDest, colDest) = (4, 4)

<b>Output:</b> 12

<b>Explanation:</b> One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
<img src="https://assets.leetcode.com/uploads/2018/10/12/maze_1_example_1.png" style="width: 100%; max-width:350px;" />
</pre>

<p><b>Example 2:</b></p>

<pre>
<b>Input 1:</b> a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

<b>Input 2:</b> start coordinate (rowStart, colStart) = (0, 4)
<b>Input 3:</b> destination coordinate (rowDest, colDest) = (3, 2)

<b>Output:</b> -1

<b>Explanation:</b> There is no way for the ball to stop at the destination.
<img src="https://assets.leetcode.com/uploads/2018/10/13/maze_1_example_2.png" style="width: 100%; max-width:350px;" />
</pre>

<p> </p>

<p><b>Note:</b></p>

<ol>
	<li>There is only one ball and one destination in the maze.</li>
	<li>Both the ball and the destination exist on an empty space, and they will not be at the same position initially.</li>
	<li>The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.</li>
	<li>The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.</li>
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