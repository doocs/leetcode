# [348. 设计井字棋](https://leetcode.cn/problems/design-tic-tac-toe)

[English Version](/solution/0300-0399/0348.Design%20Tic-Tac-Toe/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请在 n &times;&nbsp;n 的棋盘上，实现一个判定井字棋（Tic-Tac-Toe）胜负的神器，判断每一次玩家落子后，是否有胜出的玩家。</p>

<p>在这个井字棋游戏中，会有 2 名玩家，他们将轮流在棋盘上放置自己的棋子。</p>

<p>在实现这个判定器的过程中，你可以假设以下这些规则一定成立：</p>

<p>&nbsp; &nbsp; &nbsp; 1. 每一步棋都是在棋盘内的，并且只能被放置在一个空的格子里；</p>

<p>&nbsp; &nbsp; &nbsp; 2. 一旦游戏中有一名玩家胜出的话，游戏将不能再继续；</p>

<p>&nbsp; &nbsp; &nbsp; 3. 一个玩家如果在同一行、同一列或者同一斜对角线上都放置了自己的棋子，那么他便获得胜利。</p>

<p><strong>示例:</strong></p>

<pre>给定棋盘边长 <em>n</em> = 3, 玩家 1 的棋子符号是 &quot;X&quot;，玩家 2 的棋子符号是 &quot;O&quot;。

TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -&gt; 函数返回 0 (此时，暂时没有玩家赢得这场对决)
|X| | |
| | | |    // 玩家 1 在 (0, 0) 落子。
| | | |

toe.move(0, 2, 2); -&gt; 函数返回 0 (暂时没有玩家赢得本场比赛)
|X| |O|
| | | |    // 玩家 2 在 (0, 2) 落子。
| | | |

toe.move(2, 2, 1); -&gt; 函数返回 0 (暂时没有玩家赢得比赛)
|X| |O|
| | | |    // 玩家 1 在 (2, 2) 落子。
| | |X|

toe.move(1, 1, 2); -&gt; 函数返回 0 (暂没有玩家赢得比赛)
|X| |O|
| |O| |    // 玩家 2 在 (1, 1) 落子。
| | |X|

toe.move(2, 0, 1); -&gt; 函数返回 0 (暂无玩家赢得比赛)
|X| |O|
| |O| |    // 玩家 1 在 (2, 0) 落子。
|X| |X|

toe.move(1, 0, 2); -&gt; 函数返回 0 (没有玩家赢得比赛)
|X| |O|
|O|O| |    // 玩家 2 在 (1, 0) 落子.
|X| |X|

toe.move(2, 1, 1); -&gt; 函数返回 1 (此时，玩家 1 赢得了该场比赛)
|X| |O|
|O|O| |    // 玩家 1 在 (2, 1) 落子。
|X|X|X|
</pre>

<p>&nbsp;</p>

<p><strong>进阶:</strong><br>
您有没有可能将每一步的&nbsp;<code>move()</code>&nbsp;操作优化到比&nbsp;O(<em>n</em><sup>2</sup>) 更快吗?</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

思路同[1275. 找出井字棋的获胜者](solution/1200-1299/1275.Find%20Winner%20on%20a%20Tic%20Tac%20Toe%20Game/README)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class TicTacToe {
    private int n;
    private int[][] counter;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        counter = new int[2][(n << 1) + 2];
        this.n = n;
    }

    /**
       Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        counter[player - 1][row] += 1;
        counter[player - 1][col + n] += 1;
        if (row == col) {
            counter[player - 1][n << 1] += 1;
        }
        if (row + col == n - 1) {
            counter[player - 1][(n << 1) + 1] += 1;
        }
        if (counter[player - 1][row] == n || counter[player - 1][col + n] == n
            || counter[player - 1][n << 1] == n || counter[player - 1][(n << 1) + 1] == n) {
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
