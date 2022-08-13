# [348. Design Tic-Tac-Toe](https://leetcode.com/problems/design-tic-tac-toe)

[中文文档](/solution/0300-0399/0348.Design%20Tic-Tac-Toe/README.md)

## Description

<p>Assume the following rules are for the tic-tac-toe game on an <code>n x n</code> board between two players:</p>

<ol>
	<li>A move is guaranteed to be valid and is placed on an empty block.</li>
	<li>Once a winning condition is reached, no more moves are allowed.</li>
	<li>A player who succeeds in placing <code>n</code> of their marks in a horizontal, vertical, or diagonal row wins the game.</li>
</ol>

<p>Implement the&nbsp;<code>TicTacToe</code> class:</p>

<ul>
	<li><code>TicTacToe(int n)</code> Initializes the object the size of the board <code>n</code>.</li>
	<li><code>int move(int row, int col, int player)</code> Indicates that the player with id <code>player</code> plays at the cell <code>(row, col)</code> of the board. The move is guaranteed to be a valid move.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;TicTacToe&quot;, &quot;move&quot;, &quot;move&quot;, &quot;move&quot;, &quot;move&quot;, &quot;move&quot;, &quot;move&quot;, &quot;move&quot;]
[[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
<strong>Output</strong>
[null, 0, 0, 0, 0, 0, 0, 1]

<strong>Explanation</strong>
TicTacToe ticTacToe = new TicTacToe(3);
Assume that player 1 is &quot;X&quot; and player 2 is &quot;O&quot; in the board.
ticTacToe.move(0, 0, 1); // return 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

ticTacToe.move(0, 2, 2); // return 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

ticTacToe.move(2, 2, 1); // return 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

ticTacToe.move(1, 1, 2); // return 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

ticTacToe.move(2, 0, 1); // return 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

ticTacToe.move(1, 0, 2); // return 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

ticTacToe.move(2, 1, 1); // return 1&nbsp;(player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li>player is <code>1</code> or <code>2</code>.</li>
	<li><code>0 &lt;= row, col &lt; n</code></li>
	<li><code>(row, col)</code> are <strong>unique</strong> for each different call to <code>move</code>.</li>
	<li>At most <code>n<sup>2</sup></code> calls will be made to <code>move</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow-up:</strong> Could you do better than <code>O(n<sup>2</sup>)</code> per <code>move()</code> operation?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class TicTacToe:
    def __init__(self, n: int):
        """
        Initialize your data structure here.
        """
        self.n = n
        self.counter = [[0] * ((n << 1) + 2) for _ in range(2)]

    def move(self, row: int, col: int, player: int) -> int:
        """
        Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins.
        """
        n = self.n
        self.counter[player - 1][row] += 1
        self.counter[player - 1][col + n] += 1
        if row == col:
            self.counter[player - 1][n << 1] += 1
        if row + col == n - 1:
            self.counter[player - 1][(n << 1) + 1] += 1
        if (
            self.counter[player - 1][row] == n
            or self.counter[player - 1][col + n] == n
            or self.counter[player - 1][n << 1] == n
            or self.counter[player - 1][(n << 1) + 1] == n
        ):
            return player
        return 0


# Your TicTacToe object will be instantiated and called as such:
# obj = TicTacToe(n)
# param_1 = obj.move(row,col,player)
```

### **Java**

```java
class TicTacToe {
    private int n;
    private int[][] counter;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        counter = new int[2][(n << 1) + 2];
        this.n = n;
    }

    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        counter[player - 1][row] += 1;
        counter[player - 1][col + n] += 1;
        if (row == col) {
            counter[player - 1][n << 1] += 1;
        }
        if (row + col == n - 1) {
            counter[player - 1][(n << 1) + 1] += 1;
        }
        if (counter[player - 1][row] == n || counter[player - 1][col + n] == n || counter[player - 1][n << 1] == n || counter[player - 1][(n << 1) + 1] == n) {
            return player;
        }
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
```

### **...**

```

```

<!-- tabs:end -->
