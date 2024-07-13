---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0348.Design%20Tic-Tac-Toe/README_EN.md
tags:
    - Design
    - Array
    - Hash Table
    - Matrix
    - Simulation
---

<!-- problem:start -->

# [348. Design Tic-Tac-Toe 🔒](https://leetcode.com/problems/design-tic-tac-toe)

[中文文档](/solution/0300-0399/0348.Design%20Tic-Tac-Toe/README.md)

## Description

<!-- description:start -->

<p>Assume the following rules are for the tic-tac-toe game on an <code>n x n</code> board between two players:</p>

<ol>
	<li>A move is guaranteed to be valid and is placed on an empty block.</li>
	<li>Once a winning condition is reached, no more moves are allowed.</li>
	<li>A player who succeeds in placing <code>n</code> of their marks in a horizontal, vertical, or diagonal row wins the game.</li>
</ol>

<p>Implement the <code>TicTacToe</code> class:</p>

<ul>
	<li><code>TicTacToe(int n)</code> Initializes the object the size of the board <code>n</code>.</li>
	<li><code>int move(int row, int col, int player)</code> Indicates that the player with id <code>player</code> plays at the cell <code>(row, col)</code> of the board. The move is guaranteed to be a valid move, and the two players alternate in making moves. Return
	<ul>
		<li><code>0</code> if there is <strong>no winner</strong> after the move,</li>
		<li><code>1</code> if <strong>player 1</strong> is the winner after the move, or</li>
		<li><code>2</code> if <strong>player 2</strong> is the winner after the move.</li>
	</ul>
	</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We can use an array of length $n \times 2 + 2$ to record the number of pieces each player has in each row, each column, and the two diagonals. We need two such arrays to record the number of pieces for the two players respectively.

When a player has $n$ pieces in a certain row, column, or diagonal, that player wins.

In terms of time complexity, the time complexity of each move is $O(1)$. The space complexity is $O(n)$, where $n$ is the length of the side of the chessboard.

<!-- tabs:start -->

#### Python3

```python
class TicTacToe:

    def __init__(self, n: int):
        self.n = n
        self.cnt = [defaultdict(int), defaultdict(int)]

    def move(self, row: int, col: int, player: int) -> int:
        cur = self.cnt[player - 1]
        n = self.n
        cur[row] += 1
        cur[n + col] += 1
        if row == col:
            cur[n << 1] += 1
        if row + col == n - 1:
            cur[n << 1 | 1] += 1
        if any(cur[i] == n for i in (row, n + col, n << 1, n << 1 | 1)):
            return player
        return 0


# Your TicTacToe object will be instantiated and called as such:
# obj = TicTacToe(n)
# param_1 = obj.move(row,col,player)
```

#### Java

```java
class TicTacToe {
    private int n;
    private int[][] cnt;

    public TicTacToe(int n) {
        this.n = n;
        cnt = new int[2][(n << 1) + 2];
    }

    public int move(int row, int col, int player) {
        int[] cur = cnt[player - 1];
        ++cur[row];
        ++cur[n + col];
        if (row == col) {
            ++cur[n << 1];
        }
        if (row + col == n - 1) {
            ++cur[n << 1 | 1];
        }
        if (cur[row] == n || cur[n + col] == n || cur[n << 1] == n || cur[n << 1 | 1] == n) {
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

#### C++

```cpp
class TicTacToe {
private:
    int n;
    vector<vector<int>> cnt;

public:
    TicTacToe(int n)
        : n(n)
        , cnt(2, vector<int>((n << 1) + 2, 0)) {
    }

    int move(int row, int col, int player) {
        vector<int>& cur = cnt[player - 1];
        ++cur[row];
        ++cur[n + col];
        if (row == col) {
            ++cur[n << 1];
        }
        if (row + col == n - 1) {
            ++cur[n << 1 | 1];
        }
        if (cur[row] == n || cur[n + col] == n || cur[n << 1] == n || cur[n << 1 | 1] == n) {
            return player;
        }
        return 0;
    }
};

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe* obj = new TicTacToe(n);
 * int param_1 = obj->move(row,col,player);
 */
```

#### Go

```go
type TicTacToe struct {
	n   int
	cnt [][]int
}

func Constructor(n int) TicTacToe {
	cnt := make([][]int, 2)
	for i := range cnt {
		cnt[i] = make([]int, (n<<1)+2)
	}
	return TicTacToe{n, cnt}
}

func (this *TicTacToe) Move(row int, col int, player int) int {
	cur := this.cnt[player-1]
	cur[row]++
	cur[this.n+col]++
	if row == col {
		cur[this.n<<1]++
	}
	if row+col == this.n-1 {
		cur[this.n<<1|1]++
	}
	if cur[row] == this.n || cur[this.n+col] == this.n || cur[this.n<<1] == this.n || cur[this.n<<1|1] == this.n {
		return player
	}
	return 0
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Move(row,col,player);
 */
```

#### TypeScript

```ts
class TicTacToe {
    private n: number;
    private cnt: number[][];

    constructor(n: number) {
        this.n = n;
        this.cnt = [Array((n << 1) + 2).fill(0), Array((n << 1) + 2).fill(0)];
    }

    move(row: number, col: number, player: number): number {
        const cur = this.cnt[player - 1];
        cur[row]++;
        cur[this.n + col]++;
        if (row === col) {
            cur[this.n << 1]++;
        }
        if (row + col === this.n - 1) {
            cur[(this.n << 1) | 1]++;
        }
        if (
            cur[row] === this.n ||
            cur[this.n + col] === this.n ||
            cur[this.n << 1] === this.n ||
            cur[(this.n << 1) | 1] === this.n
        ) {
            return player;
        }
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * var obj = new TicTacToe(n)
 * var param_1 = obj.move(row,col,player)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
