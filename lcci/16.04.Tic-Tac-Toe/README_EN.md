---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.04.Tic-Tac-Toe/README_EN.md
---

# [16.04. Tic-Tac-Toe](https://leetcode.cn/problems/tic-tac-toe-lcci)

[中文文档](/lcci/16.04.Tic-Tac-Toe/README.md)

## Description

<p>Design an algorithm to figure out if someone has won a game of tic-tac-toe.&nbsp;Input is a string array&nbsp;of size N x N, including characters &quot; &quot;, &quot;X&quot; and &quot;O&quot;, where &quot; &quot; represents a empty grid.</p>
<p>The rules of tic-tac-toe are as follows:</p>
<ul>
	<li>Players place characters into an empty grid(&quot; &quot;) in turn.</li>
	<li>The first player always place character &quot;O&quot;, and the second one place &quot;X&quot;.</li>
	<li>Players are only allowed to place characters in empty grid. Replacing a character is not allowed.</li>
	<li>If there is any row, column or diagonal filled with N&nbsp;same characters, the game ends. The player who place the last charater wins.</li>
	<li>When there is no empty grid, the game ends.</li>
	<li>If the game ends, players cannot place any character further.</li>
</ul>
<p>If there is any winner, return the character that the winner used. If there&#39;s a draw, return &quot;Draw&quot;. If the game doesn&#39;t end and there is no winner, return &quot;Pending&quot;.</p>
<p><strong>Example 1: </strong></p>
<pre>

<strong>Input: </strong> board = [&quot;O X&quot;,&quot; XO&quot;,&quot;X O&quot;]

<strong>Output: </strong> &quot;X&quot;

</pre>
<p><strong>Example 2: </strong></p>
<pre>

<strong>Input: </strong> board = [&quot;OOX&quot;,&quot;XXO&quot;,&quot;OXO&quot;]

<strong>Output: </strong> &quot;Draw&quot;

<strong>Explanation: </strong> no player wins and no empty grid left

</pre>
<p><strong>Example 3: </strong></p>
<pre>

<strong>Input: </strong> board = [&quot;OOX&quot;,&quot;XXO&quot;,&quot;OX &quot;]

<strong>Output: </strong> &quot;Pending&quot;

<strong>Explanation: </strong> no player wins but there is still a empty grid

</pre>
<p><strong>Note: </strong></p>
<ul>
	<li><code>1 &lt;= board.length == board[i].length &lt;= 100</code></li>
	<li>Input follows the rules.</li>
</ul>

## Solutions

### Solution 1: Counting

For each cell, if it is `X`, we can add $1$ to the count; if it is `O`, we can subtract $1$ from the count. When the absolute value of the count of a row, column, or diagonal equals $n$, it means that the current player has placed $n$ identical characters in that row, column, or diagonal, and the game is over. We can return the corresponding character.

Specifically, we use a one-dimensional array $rows$ and $cols$ of length $n$ to represent the count of each row and column, and use $dg$ and $udg$ to represent the count of the two diagonals. When a player places a character at $(i, j)$, we update the corresponding elements in the arrays $rows$, $cols$, $dg$, and $udg$ based on whether the character is `X` or `O`. After each update, we check whether the absolute value of the corresponding element equals $n$. If it does, it means that the current player has placed $n$ identical characters in that row, column, or diagonal, and the game is over. We can return the corresponding character.

Finally, we traverse the entire board. If there is a character ` `, it means that the game is not over yet, and we return `Pending`. Otherwise, we return `Draw`.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$, where $n$ is the side length of the board.

<!-- tabs:start -->

```python
class Solution:
    def tictactoe(self, board: List[str]) -> str:
        n = len(board)
        rows = [0] * n
        cols = [0] * n
        dg = udg = 0
        has_empty_grid = False
        for i, row in enumerate(board):
            for j, c in enumerate(row):
                v = 1 if c == 'X' else -1
                if c == ' ':
                    has_empty_grid = True
                    v = 0
                rows[i] += v
                cols[j] += v
                if i == j:
                    dg += v
                if i + j + 1 == n:
                    udg += v
                if (
                    abs(rows[i]) == n
                    or abs(cols[j]) == n
                    or abs(dg) == n
                    or abs(udg) == n
                ):
                    return c
        return 'Pending' if has_empty_grid else 'Draw'
```

```java
class Solution {
    public String tictactoe(String[] board) {
        int n = board.length;
        int[] rows = new int[n];
        int[] cols = new int[n];
        int dg = 0, udg = 0;
        boolean hasEmptyGrid = false;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = board[i].charAt(j);
                if (c == ' ') {
                    hasEmptyGrid = true;
                    continue;
                }
                int v = c == 'X' ? 1 : -1;
                rows[i] += v;
                cols[j] += v;
                if (i == j) {
                    dg += v;
                }
                if (i + j + 1 == n) {
                    udg += v;
                }
                if (Math.abs(rows[i]) == n || Math.abs(cols[j]) == n || Math.abs(dg) == n
                    || Math.abs(udg) == n) {
                    return String.valueOf(c);
                }
            }
        }
        return hasEmptyGrid ? "Pending" : "Draw";
    }
}
```

```cpp
class Solution {
public:
    string tictactoe(vector<string>& board) {
        int n = board.size();
        vector<int> rows(n), cols(n);
        int dg = 0, udg = 0;
        bool hasEmptyGrid = false;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = board[i][j];
                if (c == ' ') {
                    hasEmptyGrid = true;
                    continue;
                }
                int v = c == 'X' ? 1 : -1;
                rows[i] += v;
                cols[j] += v;
                if (i == j) {
                    dg += v;
                }
                if (i + j + 1 == n) {
                    udg += v;
                }
                if (abs(rows[i]) == n || abs(cols[j]) == n || abs(dg) == n || abs(udg) == n) {
                    return string(1, c);
                }
            }
        }
        return hasEmptyGrid ? "Pending" : "Draw";
    }
};
```

```go
func tictactoe(board []string) string {
	n := len(board)
	rows := make([]int, n)
	cols := make([]int, n)
	dg, udg := 0, 0
	hasEmptyGrid := false
	for i, row := range board {
		for j, c := range row {
			if c == ' ' {
				hasEmptyGrid = true
				continue
			}
			v := 1
			if c == 'O' {
				v = -1
			}
			rows[i] += v
			cols[j] += v
			if i == j {
				dg += v
			}
			if i+j == n-1 {
				udg += v
			}
			if abs(rows[i]) == n || abs(cols[j]) == n || abs(dg) == n || abs(udg) == n {
				return string(c)
			}
		}
	}
	if hasEmptyGrid {
		return "Pending"
	}
	return "Draw"
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function tictactoe(board: string[]): string {
    const n = board.length;
    const rows = Array(n).fill(0);
    const cols = Array(n).fill(0);
    let [dg, udg] = [0, 0];
    let hasEmptyGrid = false;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            const c = board[i][j];
            if (c === ' ') {
                hasEmptyGrid = true;
                continue;
            }
            const v = c === 'X' ? 1 : -1;
            rows[i] += v;
            cols[j] += v;
            if (i === j) {
                dg += v;
            }
            if (i + j === n - 1) {
                udg += v;
            }
            if (
                Math.abs(rows[i]) === n ||
                Math.abs(cols[j]) === n ||
                Math.abs(dg) === n ||
                Math.abs(udg) === n
            ) {
                return c;
            }
        }
    }
    return hasEmptyGrid ? 'Pending' : 'Draw';
}
```

```swift
class Solution {
    func tictactoe(_ board: [String]) -> String {
        let n = board.count
        var rows = Array(repeating: 0, count: n)
        var cols = Array(repeating: 0, count: n)
        var diagonal = 0, antiDiagonal = 0
        var hasEmptyGrid = false

        for i in 0..<n {
            for j in 0..<n {
                let c = Array(board[i])[j]
                if c == " " {
                    hasEmptyGrid = true
                    continue
                }
                let value = c == "X" ? 1 : -1
                rows[i] += value
                cols[j] += value
                if i == j {
                    diagonal += value
                }
                if i + j == n - 1 {
                    antiDiagonal += value
                }
                if abs(rows[i]) == n || abs(cols[j]) == n || abs(diagonal) == n || abs(antiDiagonal) == n {
                    return String(c)
                }
            }
        }

        return hasEmptyGrid ? "Pending" : "Draw"
    }
}
```

<!-- tabs:end -->

<!-- end -->
