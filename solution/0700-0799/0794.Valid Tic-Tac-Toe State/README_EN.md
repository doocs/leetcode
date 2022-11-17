# [794. Valid Tic-Tac-Toe State](https://leetcode.com/problems/valid-tic-tac-toe-state)

[中文文档](/solution/0700-0799/0794.Valid%20Tic-Tac-Toe%20State/README.md)

## Description

<p>Given a Tic-Tac-Toe board as a string array <code>board</code>, return <code>true</code> if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.</p>

<p>The board is a <code>3 x 3</code> array that consists of characters <code>&#39; &#39;</code>, <code>&#39;X&#39;</code>, and <code>&#39;O&#39;</code>. The <code>&#39; &#39;</code> character represents an empty square.</p>

<p>Here are the rules of Tic-Tac-Toe:</p>

<ul>
	<li>Players take turns placing characters into empty squares <code>&#39; &#39;</code>.</li>
	<li>The first player always places <code>&#39;X&#39;</code> characters, while the second player always places <code>&#39;O&#39;</code> characters.</li>
	<li><code>&#39;X&#39;</code> and <code>&#39;O&#39;</code> characters are always placed into empty squares, never filled ones.</li>
	<li>The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.</li>
	<li>The game also ends if all squares are non-empty.</li>
	<li>No more moves can be played if the game is over.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0794.Valid%20Tic-Tac-Toe%20State/images/tictactoe1-grid.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>Input:</strong> board = [&quot;O  &quot;,&quot;   &quot;,&quot;   &quot;]
<strong>Output:</strong> false
<strong>Explanation:</strong> The first player always plays &quot;X&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0794.Valid%20Tic-Tac-Toe%20State/images/tictactoe2-grid.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>Input:</strong> board = [&quot;XOX&quot;,&quot; X &quot;,&quot;   &quot;]
<strong>Output:</strong> false
<strong>Explanation:</strong> Players take turns making moves.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0794.Valid%20Tic-Tac-Toe%20State/images/tictactoe4-grid.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>Input:</strong> board = [&quot;XOX&quot;,&quot;O O&quot;,&quot;XOX&quot;]
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>board.length == 3</code></li>
	<li><code>board[i].length == 3</code></li>
	<li><code>board[i][j]</code> is either <code>&#39;X&#39;</code>, <code>&#39;O&#39;</code>, or <code>&#39; &#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
