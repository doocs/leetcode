# [794. 有效的井字游戏](https://leetcode.cn/problems/valid-tic-tac-toe-state)

[English Version](/solution/0700-0799/0794.Valid%20Tic-Tac-Toe%20State/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组 <code>board</code> 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 <code>board</code> 所显示的状态时，才返回 <code>true</code> 。</p>

<p>井字游戏的棋盘是一个 <code>3 x 3</code> 数组，由字符 <code>' '</code>，<code>'X'</code> 和 <code>'O'</code> 组成。字符 <code>' '</code> 代表一个空位。</p>

<p>以下是井字游戏的规则：</p>

<ul>
	<li>玩家轮流将字符放入空位（<code>' '</code>）中。</li>
	<li>玩家 1 总是放字符 <code>'X'</code> ，而玩家 2 总是放字符 <code>'O'</code> 。</li>
	<li><code>'X'</code> 和 <code>'O'</code> 只允许放置在空位中，不允许对已放有字符的位置进行填充。</li>
	<li>当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。</li>
	<li>当所有位置非空时，也算为游戏结束。</li>
	<li>如果游戏结束，玩家不允许再放置字符。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0794.Valid%20Tic-Tac-Toe%20State/images/tictactoe1-grid.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>输入：</strong>board = ["O  ","   ","   "]
<strong>输出：</strong>false
<strong>解释：</strong>玩家 1 总是放字符 "X" 。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0794.Valid%20Tic-Tac-Toe%20State/images/tictactoe2-grid.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>输入：</strong>board = ["XOX"," X ","   "]
<strong>输出：</strong>false
<strong>解释：</strong>玩家应该轮流放字符。
</pre>

<p><strong>示例 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0794.Valid%20Tic-Tac-Toe%20State/images/tictactoe4-grid.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>输入：</strong>board = ["XOX","O O","XOX"]
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>board.length == 3</code></li>
	<li><code>board[i].length == 3</code></li>
	<li><code>board[i][j]</code> 为 <code>'X'</code>、<code>'O'</code> 或 <code>' '</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：分类讨论**

我们先统计当前棋盘上 `'X'` 和 `'O'` 的数量，记为 $x$ 和 $o$。接下来，我们分情况讨论：

-   如果 $x \neq o$ 且 $x - 1 \neq o$，则当前棋盘不可能是有效棋盘，返回 `false`。
-   如果当前棋盘上玩家 1 获胜，但 $x-1 \neq o$，则当前棋盘不可能是有效棋盘，返回 `false`。
-   如果当前棋盘上玩家 2 获胜，但 $x \neq o$，则当前棋盘不可能是有效棋盘，返回 `false`。
-   其他情况下，当前棋盘是有效棋盘，返回 `true`。

时间复杂度 $O(C)$，空间复杂度 $O(1)$。其中 $C$ 是棋盘上的格子数。本题中 $C = 9$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def validTicTacToe(self, board: List[str]) -> bool:
        def win(x):
            for i in range(3):
                if all(board[i][j] == x for j in range(3)):
                    return True
                if all(board[j][i] == x for j in range(3)):
                    return True
            if all(board[i][i] == x for i in range(3)):
                return True
            return all(board[i][2 - i] == x for i in range(3))

        x = sum(board[i][j] == 'X' for i in range(3) for j in range(3))
        o = sum(board[i][j] == 'O' for i in range(3) for j in range(3))
        if x != o and x - 1 != o:
            return False
        if win('X') and x - 1 != o:
            return False
        return not (win('O') and x != o)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private String[] board;

    public boolean validTicTacToe(String[] board) {
        this.board = board;
        int x = count('X'), o = count('O');
        if (x != o && x - 1 != o) {
            return false;
        }
        if (win('X') && x - 1 != o) {
            return false;
        }
        return !(win('O') && x != o);
    }

    private boolean win(char x) {
        for (int i = 0; i < 3; ++i) {
            if (board[i].charAt(0) == x && board[i].charAt(1) == x && board[i].charAt(2) == x) {
                return true;
            }
            if (board[0].charAt(i) == x && board[1].charAt(i) == x && board[2].charAt(i) == x) {
                return true;
            }
        }
        if (board[0].charAt(0) == x && board[1].charAt(1) == x && board[2].charAt(2) == x) {
            return true;
        }
        return board[0].charAt(2) == x && board[1].charAt(1) == x && board[2].charAt(0) == x;
    }

    private int count(char x) {
        int cnt = 0;
        for (var row : board) {
            for (var c : row.toCharArray()) {
                if (c == x) {
                    ++cnt;
                }
            }
        }
        return cnt;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool validTicTacToe(vector<string>& board) {
        auto count = [&](char x) {
            int ans = 0;
            for (auto& row : board) for (auto& c : row) ans += c == x;
            return ans;
        };
        auto win = [&](char x) {
            for (int i = 0; i < 3; ++i) {
                if (board[i][0] == x && board[i][1] == x && board[i][2] == x) return true;
                if (board[0][i] == x && board[1][i] == x && board[2][i] == x) return true;
            }
            if (board[0][0] == x && board[1][1] == x && board[2][2] == x) return true;
            return board[0][2] == x && board[1][1] == x && board[2][0] == x;
        };
        int x = count('X'), o = count('O');
        if (x != o && x - 1 != o) return false;
        if (win('X') && x - 1 != o) return false;
        return !(win('O') && x != o);
    }
};
```

### **Go**

```go
func validTicTacToe(board []string) bool {
	var x, o int
	for _, row := range board {
		for _, c := range row {
			if c == 'X' {
				x++
			} else if c == 'O' {
				o++
			}
		}
	}
	win := func(x byte) bool {
		for i := 0; i < 3; i++ {
			if board[i][0] == x && board[i][1] == x && board[i][2] == x {
				return true
			}
			if board[0][i] == x && board[1][i] == x && board[2][i] == x {
				return true
			}
		}
		if board[0][0] == x && board[1][1] == x && board[2][2] == x {
			return true
		}
		return board[0][2] == x && board[1][1] == x && board[2][0] == x
	}
	if x != o && x-1 != o {
		return false
	}
	if win('X') && x-1 != o {
		return false
	}
	return !(win('O') && x != o)
}
```

### **JavaScript**

```js
/**
 * @param {string[]} board
 * @return {boolean}
 */
var validTicTacToe = function (board) {
    function count(x) {
        let cnt = 0;
        for (const row of board) {
            for (const c of row) {
                cnt += c == x;
            }
        }
        return cnt;
    }
    function win(x) {
        for (let i = 0; i < 3; ++i) {
            if (board[i][0] == x && board[i][1] == x && board[i][2] == x) {
                return true;
            }
            if (board[0][i] == x && board[1][i] == x && board[2][i] == x) {
                return true;
            }
        }
        if (board[0][0] == x && board[1][1] == x && board[2][2] == x) {
            return true;
        }
        return board[0][2] == x && board[1][1] == x && board[2][0] == x;
    }
    const [x, o] = [count('X'), count('O')];
    if (x != o && x - 1 != o) {
        return false;
    }
    if (win('X') && x - 1 != o) {
        return false;
    }
    return !(win('O') && x != o);
};
```

### **...**

```

```

<!-- tabs:end -->
