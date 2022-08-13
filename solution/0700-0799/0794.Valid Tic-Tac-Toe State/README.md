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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def validTicTacToe(self, board: List[str]) -> bool:
        def win(p):
            for i in range(3):
                if board[i][0] == board[i][1] == board[i][2] == p:
                    return True
                if board[0][i] == board[1][i] == board[2][i] == p:
                    return True
            if board[0][0] == board[1][1] == board[2][2] == p:
                return True
            return board[0][2] == board[1][1] == board[2][0] == p

        x, o = 0, 0
        for i in range(3):
            for j in range(3):
                if board[i][j] == 'X':
                    x += 1
                elif board[i][j] == 'O':
                    o += 1

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
    public boolean validTicTacToe(String[] board) {
        int x = 0, o = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'X') {
                    x++;
                } else if (board[i].charAt(j) == 'O') {
                    o++;
                }
            }
        }
        if (x != o && x - 1 != o) {
            return false;
        }
        if (win(board, 'X') && x - 1 != o) {
            return false;
        }
        return !(win(board, 'O') && x != o);
    }

    private boolean win(String[] b, char p) {
        for (int i = 0; i < 3; i++) {
            if (b[i].charAt(0) == p && b[i].charAt(1) == p && b[i].charAt(2) == p) {
                return true;
            }
            if (b[0].charAt(i) == p && b[1].charAt(i) == p && b[2].charAt(i) == p) {
                return true;
            }
        }
        if (b[0].charAt(0) == p && b[1].charAt(1) == p && b[2].charAt(2) == p) {
            return true;
        }
        return b[0].charAt(2) == p && b[1].charAt(1) == p && b[2].charAt(0) == p;
    }
}
```

### **Go**

```go
func validTicTacToe(board []string) bool {
	x, o := 0, 0
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			if board[i][j] == 'X' {
				x++
			} else if board[i][j] == 'O' {
				o++
			}
		}
	}
	if x != o && x-1 != o {
		return false
	}
	if win(board, 'X') && x-1 != o {
		return false
	}
	return !(win(board, 'O') && x != o)
}

func win(b []string, p byte) bool {
	for i := 0; i < 3; i++ {
		if b[i][0] == p && b[i][1] == p && b[i][2] == p {
			return true
		}
		if b[0][i] == p && b[1][i] == p && b[2][i] == p {
			return true
		}
	}
	if b[0][0] == p && b[1][1] == p && b[2][2] == p {
		return true
	}
	return b[0][2] == p && b[1][1] == p && b[2][0] == p
}
```

### **C++**

```cpp
class Solution {
public:
    bool validTicTacToe(vector<string>& board) {
        int x = 0, o = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == 'X') {
                    ++x;
                } else if (board[i][j] == 'O') {
                    ++o;
                }
            }
        }
        if (x != o && x - 1 != o) {
            return false;
        }
        if (win(board, 'X') && x - 1 != o) {
            return false;
        }
        return !(win(board, 'O') && x != o);
    }

    bool win(vector<string>& b, char p) {
        for (int i = 0; i < 3; ++i) {
            if (b[i][0] == p && b[i][1] == p && b[i][2] == p) {
                return true;
            }
            if (b[0][i] == p && b[1][i] == p && b[2][i] == p) {
                return true;
            }
        }
        if (b[0][0] == p && b[1][1] == p && b[2][2] == p) {
            return true;
        }
        return b[0][2] == p && b[1][1] == p && b[2][0] == p;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
