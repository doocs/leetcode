# [348. Design Tic-Tac-Toe](https://leetcode.com/problems/design-tic-tac-toe)

[中文文档](/solution/0300-0399/0348.Design%20Tic-Tac-Toe/README.md)

## Description

<p>Design a Tic-tac-toe game that is played between two players on a <i>n</i> x <i>n</i> grid.
</p>

<p>You may assume the following rules:
<ol>
<li>A move is guaranteed to be valid and is placed on an empty block.</li>
<li>Once a winning condition is reached, no more moves is allowed.</li>
<li>A player who succeeds in placing <i>n</i> of their marks in a horizontal, vertical, or diagonal row wins the game.</li>
</ol>
</p>

<p><b>Example:</b><br />
<pre>
Given <i>n</i> = 3, assume that player 1 is "X" and player 2 is "O" in the board.

TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | | // Player 1 makes a move at (0, 0).
| | | |

toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | | // Player 2 makes a move at (0, 2).
| | | |

toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | | // Player 1 makes a move at (2, 2).
| | |X|

toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| | // Player 2 makes a move at (1, 1).
| | |X|

toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| | // Player 1 makes a move at (2, 0).
|X| |X|

toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| | // Player 2 makes a move at (1, 0).
|X| |X|

toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| | // Player 1 makes a move at (2, 1).
|X|X|X|

</pre>
</p>

<p><b>Follow up:</b><br />
Could you do better than O(<i>n</i><sup>2</sup>) per <code>move()</code> operation?
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
