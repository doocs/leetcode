# [794. Valid Tic-Tac-Toe State](https://leetcode.com/problems/valid-tic-tac-toe-state)

[中文文档](/solution/0700-0799/0794.Valid%20Tic-Tac-Toe%20State/README.md)

## Description

<p>A Tic-Tac-Toe board is given as a string array <code>board</code>. Return True if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.</p>

<p>The <code>board</code> is a 3 x 3 array, and consists of characters <code>&quot; &quot;</code>, <code>&quot;X&quot;</code>, and <code>&quot;O&quot;</code>.&nbsp; The &quot; &quot; character represents an empty square.</p>

<p>Here are the rules of Tic-Tac-Toe:</p>

<ul>
	<li>Players take turns placing characters into empty squares (&quot; &quot;).</li>
	<li>The first player always places &quot;X&quot; characters, while the second player always places &quot;O&quot; characters.</li>
	<li>&quot;X&quot; and &quot;O&quot; characters are always placed into empty squares, never filled ones.</li>
	<li>The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.</li>
	<li>The game also ends if all squares are non-empty.</li>
	<li>No more moves can be played if the game is over.</li>
</ul>

<pre>

<strong>Example 1:</strong>

<strong>Input:</strong> board = [&quot;O&nbsp; &quot;, &quot;&nbsp; &nbsp;&quot;, &quot;&nbsp; &nbsp;&quot;]

<strong>Output:</strong> false

<strong>Explanation:</strong> The first player always plays &quot;X&quot;.



<strong>Example 2:</strong>

<strong>Input:</strong> board = [&quot;XOX&quot;, &quot; X &quot;, &quot;   &quot;]

<strong>Output:</strong> false

<strong>Explanation:</strong> Players take turns making moves.



<strong>Example 3:</strong>

<strong>Input:</strong> board = [&quot;XXX&quot;, &quot;   &quot;, &quot;OOO&quot;]

<strong>Output:</strong> false



<strong>Example 4:</strong>

<strong>Input:</strong> board = [&quot;XOX&quot;, &quot;O O&quot;, &quot;XOX&quot;]

<strong>Output:</strong> true

</pre>

<p><strong>Note:</strong></p>

<ul>
	<li><code>board</code> is a length-3 array of strings, where each string <code>board[i]</code> has length 3.</li>
	<li>Each <code>board[i][j]</code> is a character in the set <code>{&quot; &quot;, &quot;X&quot;, &quot;O&quot;}</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
