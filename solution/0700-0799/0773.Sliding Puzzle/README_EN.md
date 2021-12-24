# [773. Sliding Puzzle](https://leetcode.com/problems/sliding-puzzle)

[中文文档](/solution/0700-0799/0773.Sliding%20Puzzle/README.md)

## Description

<p>On a 2x3 <code>board</code>, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.</p>

<p>A move consists of choosing <code>0</code>&nbsp;and a 4-directionally adjacent number and swapping it.</p>

<p>The state of the board is <em>solved</em> if and only if the <code>board</code> is <code>[[1,2,3],[4,5,0]].</code></p>

<p>Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.</p>

<p><strong>Examples:</strong></p>

<pre>

<strong>Input:</strong> board = [[1,2,3],[4,0,5]]

<strong>Output:</strong> 1

<strong>Explanation:</strong> Swap the 0 and the 5 in one move.

</pre>

<pre>

<strong>Input:</strong> board = [[1,2,3],[5,4,0]]

<strong>Output:</strong> -1

<strong>Explanation:</strong> No number of moves will make the board solved.

</pre>

<pre>

<strong>Input:</strong> board = [[4,1,2],[5,0,3]]

<strong>Output:</strong> 5

<strong>Explanation:</strong> 5 is the smallest number of moves that solves the board.

An example path:

After move 0: [[4,1,2],[5,0,3]]

After move 1: [[4,1,2],[0,5,3]]

After move 2: [[0,1,2],[4,5,3]]

After move 3: [[1,0,2],[4,5,3]]

After move 4: [[1,2,0],[4,5,3]]

After move 5: [[1,2,3],[4,5,0]]

</pre>

<pre>

<strong>Input:</strong> board = [[3,2,4],[1,5,0]]

<strong>Output:</strong> 14

</pre>

<p><strong>Note:</strong></p>

<ul>
	<li><code>board</code> will be a 2 x 3 array as described above.</li>
	<li><code>board[i][j]</code> will be a permutation of <code>[0, 1, 2, 3, 4, 5]</code>.</li>
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
