# [782. Transform to Chessboard](https://leetcode.com/problems/transform-to-chessboard)

[中文文档](/solution/0700-0799/0782.Transform%20to%20Chessboard/README.md)

## Description

<p>An N x N <code>board</code> contains only <code>0</code>s and <code>1</code>s. In each move, you can swap any 2 rows with each other, or any 2 columns with each other.</p>

<p>What is the minimum number of moves to transform the board into a &quot;chessboard&quot; - a board where no <code>0</code>s and no <code>1</code>s are 4-directionally adjacent? If the task is impossible, return -1.</p>

<pre>

<strong>Examples:</strong>

<strong>Input:</strong> board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]

<strong>Output:</strong> 2

<strong>Explanation:</strong>

One potential sequence of moves is shown below, from left to right:



0110     1010     1010

0110 --&gt; 1010 --&gt; 0101

1001     0101     1010

1001     0101     0101



The first move swaps the first and second column.

The second move swaps the second and third row.





<strong>Input:</strong> board = [[0, 1], [1, 0]]

<strong>Output:</strong> 0

<strong>Explanation:</strong>

Also note that the board with 0 in the top left corner,

01

10



is also a valid chessboard.



<strong>Input:</strong> board = [[1, 0], [1, 0]]

<strong>Output:</strong> -1

<strong>Explanation:</strong>

No matter what sequence of moves you make, you cannot end with a valid chessboard.

</pre>

<p><strong>Note:</strong></p>

<ul>
	<li><code>board</code> will have the same number of rows and columns, a number in the range <code>[2, 30]</code>.</li>
	<li><code>board[i][j]</code> will be only <code>0</code>s or <code>1</code>s.</li>
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
