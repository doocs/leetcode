# [499. The Maze III](https://leetcode.com/problems/the-maze-iii)

[中文文档](/solution/0400-0499/0499.The%20Maze%20III/README.md)

## Description

<p>There is a <b>ball</b> in a maze with empty spaces and walls. The ball can go through empty spaces by rolling <b>up</b> (u), <b>down</b> (d), <b>left</b> (l) or <b>right</b> (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a <b>hole</b> in this maze. The ball will drop into the hole if it rolls on to the hole.</p>

<p>Given the <b>ball position</b>, the <b>hole position</b> and the <b>maze</b>, find out how the ball could drop into the hole by moving the <b>shortest distance</b>. The distance is defined by the number of <b>empty spaces</b> traveled by the ball from the start position (excluded) to the hole (included). Output the moving <b>directions</b> by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the <b>lexicographically smallest</b> way. If the ball cannot reach the hole, output "impossible".</p>

<p>The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.</p>

<p> </p>

<p><b>Example 1:</b></p>

<pre>
<b>Input 1:</b> a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

<b>Input 2:</b> ball coordinate (rowBall, colBall) = (4, 3)
<b>Input 3:</b> hole coordinate (rowHole, colHole) = (0, 1)

<b>Output:</b> "lul"

<b>Explanation:</b> There are two shortest ways for the ball to drop into the hole.
The first way is left -> up -> left, represented by "lul".
The second way is up -> left, represented by 'ul'.
Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".
</pre>

![](./images/maze_2_example_1.png)

<p><b>Example 2:</b></p>

<pre>
<b>Input 1:</b> a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

<b>Input 2:</b> ball coordinate (rowBall, colBall) = (4, 3)
<b>Input 3:</b> hole coordinate (rowHole, colHole) = (3, 0)

<b>Output:</b> "impossible"

<b>Explanation:</b> The ball cannot reach the hole.
</pre>

![](./images/maze_2_example_2.png)

<p> </p>

<p><b>Note:</b></p>

<ol>
	<li>There is only one ball and one hole in the maze.</li>
	<li>Both the ball and hole exist on an empty space, and they will not be at the same position initially.</li>
	<li>The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.</li>
	<li>The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.</li>
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
