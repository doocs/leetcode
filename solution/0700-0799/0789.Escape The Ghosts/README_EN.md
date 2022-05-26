# [789. Escape The Ghosts](https://leetcode.com/problems/escape-the-ghosts)

[中文文档](/solution/0700-0799/0789.Escape%20The%20Ghosts/README.md)

## Description

<p>You are playing a simplified Pacman game. You&nbsp;start at the point <code>(0, 0)</code>, and your destination is<code> (target[0], target[1])</code>. There are several ghosts on the map, the i-th ghost starts at<code> (ghosts[i][0], ghosts[i][1])</code>.</p>

<p>Each turn, you and all ghosts simultaneously *may* move in one of 4 cardinal directions: north, east, west, or south, going from the previous point to a new point 1 unit of distance away.</p>

<p>You escape if and only if you can reach the target before any ghost reaches you (for any given moves the ghosts may take.)&nbsp; If you reach any square (including the target) at the same time as a ghost, it doesn&#39;t count as an escape.</p>

<p>Return True if and only if it is possible to escape.</p>

<pre>

<strong>Example 1:</strong>

<strong>Input:</strong> 

ghosts = [[1, 0], [0, 3]]

target = [0, 1]

<strong>Output:</strong> true

<strong>Explanation:</strong> 

You can directly reach the destination (0, 1) at time 1, while the ghosts located at (1, 0) or (0, 3) have no way to catch up with you.

</pre>

<pre>

<strong>Example 2:</strong>

<strong>Input:</strong> 

ghosts = [[1, 0]]

target = [2, 0]

<strong>Output:</strong> false

<strong>Explanation:</strong> 

You need to reach the destination (2, 0), but the ghost at (1, 0) lies between you and the destination.

</pre>

<pre>

<strong>Example 3:</strong>

<strong>Input:</strong> 

ghosts = [[2, 0]]

target = [1, 0]

<strong>Output:</strong> false

<strong>Explanation:</strong> 

The ghost can reach the target at the same time as you.

</pre>

<p><strong>Note:</strong></p>

<ul>
    <li>All points have coordinates with absolute value &lt;= <code>10000</code>.</li>
    <li>The number of ghosts will not exceed <code>100</code>.</li>
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
