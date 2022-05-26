# [749. Contain Virus](https://leetcode.com/problems/contain-virus)

[中文文档](/solution/0700-0799/0749.Contain%20Virus/README.md)

## Description

<p>

A virus is spreading rapidly, and your task is to quarantine the infected area by installing walls.

</p><p>

The world is modeled as a 2-D array of cells, where <code>0</code> represents uninfected cells, and <code>1</code> represents cells contaminated with the virus. A wall (and only one wall) can be installed <b>between any two 4-directionally adjacent cells</b>, on the shared boundary.

</p><p>

Every night, the virus spreads to all neighboring cells in all four directions unless blocked by a wall.

Resources are limited. Each day, you can install walls around only one region -- the affected area (continuous block of infected cells) that threatens the most uninfected cells the following night. There will never be a tie.

</p><p>

Can you save the day? If so, what is the number of walls required? If not, and the world becomes fully infected, return the number of walls used.

</p><p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> grid = 

[[0,1,0,0,0,0,0,1],

 [0,1,0,0,0,0,0,1],

 [0,0,0,0,0,0,0,1],

 [0,0,0,0,0,0,0,0]]

<b>Output:</b> 10

<b>Explanation:</b>

There are 2 contaminated regions.

On the first day, add 5 walls to quarantine the viral region on the left. The board after the virus spreads is:



[[0,1,0,0,0,0,1,1],

 [0,1,0,0,0,0,1,1],

 [0,0,0,0,0,0,1,1],

 [0,0,0,0,0,0,0,1]]



On the second day, add 5 walls to quarantine the viral region on the right. The virus is fully contained.

</pre>

</p>

<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> grid = 

[[1,1,1],

 [1,0,1],

 [1,1,1]]

<b>Output:</b> 4

<b>Explanation:</b> Even though there is only one cell saved, there are 4 walls built.

Notice that walls are only built on the shared boundary of two different cells.

</pre>

</p>

<p><b>Example 3:</b><br />

<pre>

<b>Input:</b> grid = 

[[1,1,1,0,0,0,0,0,0],

 [1,0,1,0,1,1,1,1,1],

 [1,1,1,0,0,0,0,0,0]]

<b>Output:</b> 13

<b>Explanation:</b> The region on the left only builds two new walls.

</pre>

</p>

<p><b>Note:</b><br>

<ol>

<li>The number of rows and columns of <code>grid</code> will each be in the range <code>[1, 50]</code>.</li>

<li>Each <code>grid[i][j]</code> will be either <code>0</code> or <code>1</code>.</li>

<li>Throughout the described process, there is always a contiguous viral region that will infect <b>strictly more</b> uncontaminated squares in the next round.</li>

</ol>

</p>

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
