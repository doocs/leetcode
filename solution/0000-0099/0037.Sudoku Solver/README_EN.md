---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0037.Sudoku%20Solver/README_EN.md
tags:
    - Array
    - Hash Table
    - Backtracking
    - Matrix
---

<!-- problem:start -->

# [37. Sudoku Solver](https://leetcode.com/problems/sudoku-solver)

[中文文档](/solution/0000-0099/0037.Sudoku%20Solver/README.md)

## Description

<!-- description:start -->

<p>Write a program to solve a Sudoku puzzle by filling the empty cells.</p>

<p>A sudoku solution must satisfy <strong>all of the following rules</strong>:</p>

<ol>
	<li>Each of the digits <code>1-9</code> must occur exactly once in each row.</li>
	<li>Each of the digits <code>1-9</code> must occur exactly once in each column.</li>
	<li>Each of the digits <code>1-9</code> must occur exactly once in each of the 9 <code>3x3</code> sub-boxes of the grid.</li>
</ol>

<p>The <code>&#39;.&#39;</code> character indicates empty cells.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0037.Sudoku%20Solver/images/250px-Sudoku-by-L2G-20050714.svg.png" style="height:250px; width:250px" />
<pre>
<strong>Input:</strong> board = [[&quot;5&quot;,&quot;3&quot;,&quot;.&quot;,&quot;.&quot;,&quot;7&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;1&quot;,&quot;9&quot;,&quot;5&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;9&quot;,&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;,&quot;.&quot;],[&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;3&quot;],[&quot;4&quot;,&quot;.&quot;,&quot;.&quot;,&quot;8&quot;,&quot;.&quot;,&quot;3&quot;,&quot;.&quot;,&quot;.&quot;,&quot;1&quot;],[&quot;7&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;2&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;],[&quot;.&quot;,&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;2&quot;,&quot;8&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;4&quot;,&quot;1&quot;,&quot;9&quot;,&quot;.&quot;,&quot;.&quot;,&quot;5&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;7&quot;,&quot;9&quot;]]
<strong>Output:</strong> [[&quot;5&quot;,&quot;3&quot;,&quot;4&quot;,&quot;6&quot;,&quot;7&quot;,&quot;8&quot;,&quot;9&quot;,&quot;1&quot;,&quot;2&quot;],[&quot;6&quot;,&quot;7&quot;,&quot;2&quot;,&quot;1&quot;,&quot;9&quot;,&quot;5&quot;,&quot;3&quot;,&quot;4&quot;,&quot;8&quot;],[&quot;1&quot;,&quot;9&quot;,&quot;8&quot;,&quot;3&quot;,&quot;4&quot;,&quot;2&quot;,&quot;5&quot;,&quot;6&quot;,&quot;7&quot;],[&quot;8&quot;,&quot;5&quot;,&quot;9&quot;,&quot;7&quot;,&quot;6&quot;,&quot;1&quot;,&quot;4&quot;,&quot;2&quot;,&quot;3&quot;],[&quot;4&quot;,&quot;2&quot;,&quot;6&quot;,&quot;8&quot;,&quot;5&quot;,&quot;3&quot;,&quot;7&quot;,&quot;9&quot;,&quot;1&quot;],[&quot;7&quot;,&quot;1&quot;,&quot;3&quot;,&quot;9&quot;,&quot;2&quot;,&quot;4&quot;,&quot;8&quot;,&quot;5&quot;,&quot;6&quot;],[&quot;9&quot;,&quot;6&quot;,&quot;1&quot;,&quot;5&quot;,&quot;3&quot;,&quot;7&quot;,&quot;2&quot;,&quot;8&quot;,&quot;4&quot;],[&quot;2&quot;,&quot;8&quot;,&quot;7&quot;,&quot;4&quot;,&quot;1&quot;,&quot;9&quot;,&quot;6&quot;,&quot;3&quot;,&quot;5&quot;],[&quot;3&quot;,&quot;4&quot;,&quot;5&quot;,&quot;2&quot;,&quot;8&quot;,&quot;6&quot;,&quot;1&quot;,&quot;7&quot;,&quot;9&quot;]]
<strong>Explanation:</strong>&nbsp;The input board is shown above and the only valid solution is shown below:

<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0037.Sudoku%20Solver/images/250px-Sudoku-by-L2G-20050714_solution.svg.png" style="height:250px; width:250px" />
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>board.length == 9</code></li>
	<li><code>board[i].length == 9</code></li>
	<li><code>board[i][j]</code> is a digit or <code>&#39;.&#39;</code>.</li>
	<li>It is <strong>guaranteed</strong> that the input board has only one solution.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Backtracking

We use arrays `row`, `col`, and `box` to record whether a number has appeared in each row, each column, and each 3x3 grid respectively. If the number `i` has appeared in the `r`th row, the `c`th column, and the `b`th 3x3 grid, then `row[r][i]`, `col[c][i]`, and `box[b][i]` are all `true`.

We traverse each empty space in `board`, enumerate the numbers `v` that it can fill in. If `v` has not appeared in the current row, the current column, and the current 3x3 grid, then we can try to fill in the number `v` and continue to search for the next empty space. If we search to the end and all spaces are filled, it means that a feasible solution has been found.

The time complexity is $O(9^{81})$, and the space complexity is $O(9^2)$.

<!-- tabs:start -->

```python
class Solution:
    def solveSudoku(self, board: List[List[str]]) -> None:
        def dfs(k):
            nonlocal ok
            if k == len(t):
                ok = True
                return
            i, j = t[k]
            for v in range(9):
                if row[i][v] == col[j][v] == block[i // 3][j // 3][v] == False:
                    row[i][v] = col[j][v] = block[i // 3][j // 3][v] = True
                    board[i][j] = str(v + 1)
                    dfs(k + 1)
                    row[i][v] = col[j][v] = block[i // 3][j // 3][v] = False
                if ok:
                    return

        row = [[False] * 9 for _ in range(9)]
        col = [[False] * 9 for _ in range(9)]
        block = [[[False] * 9 for _ in range(3)] for _ in range(3)]
        t = []
        ok = False
        for i in range(9):
            for j in range(9):
                if board[i][j] == '.':
                    t.append((i, j))
                else:
                    v = int(board[i][j]) - 1
                    row[i][v] = col[j][v] = block[i // 3][j // 3][v] = True
        dfs(0)
```

```java
class Solution {
    private boolean ok;
    private char[][] board;
    private List<Integer> t = new ArrayList<>();
    private boolean[][] row = new boolean[9][9];
    private boolean[][] col = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];

    public void solveSudoku(char[][] board) {
        this.board = board;
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    t.add(i * 9 + j);
                } else {
                    int v = board[i][j] - '1';
                    row[i][v] = col[j][v] = block[i / 3][j / 3][v] = true;
                }
            }
        }
        dfs(0);
    }

    private void dfs(int k) {
        if (k == t.size()) {
            ok = true;
            return;
        }
        int i = t.get(k) / 9, j = t.get(k) % 9;
        for (int v = 0; v < 9; ++v) {
            if (!row[i][v] && !col[j][v] && !block[i / 3][j / 3][v]) {
                row[i][v] = col[j][v] = block[i / 3][j / 3][v] = true;
                board[i][j] = (char) (v + '1');
                dfs(k + 1);
                row[i][v] = col[j][v] = block[i / 3][j / 3][v] = false;
            }
            if (ok) {
                return;
            }
        }
    }
}
```

```cpp
using pii = pair<int, int>;

class Solution {
public:
    void solveSudoku(vector<vector<char>>& board) {
        bool row[9][9] = {false};
        bool col[9][9] = {false};
        bool block[3][3][9] = {false};
        bool ok = false;
        vector<pii> t;
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    t.push_back({i, j});
                } else {
                    int v = board[i][j] - '1';
                    row[i][v] = col[j][v] = block[i / 3][j / 3][v] = true;
                }
            }
        }
        function<void(int k)> dfs = [&](int k) {
            if (k == t.size()) {
                ok = true;
                return;
            }
            int i = t[k].first, j = t[k].second;
            for (int v = 0; v < 9; ++v) {
                if (!row[i][v] && !col[j][v] && !block[i / 3][j / 3][v]) {
                    row[i][v] = col[j][v] = block[i / 3][j / 3][v] = true;
                    board[i][j] = v + '1';
                    dfs(k + 1);
                    row[i][v] = col[j][v] = block[i / 3][j / 3][v] = false;
                }
                if (ok) {
                    return;
                }
            }
        };
        dfs(0);
    }
};
```

```go
func solveSudoku(board [][]byte) {
	var row, col [9][9]bool
	var block [3][3][9]bool
	var t [][2]int
	ok := false
	for i := 0; i < 9; i++ {
		for j := 0; j < 9; j++ {
			if board[i][j] == '.' {
				t = append(t, [2]int{i, j})
			} else {
				v := int(board[i][j] - '1')
				row[i][v], col[j][v], block[i/3][j/3][v] = true, true, true
			}
		}
	}
	var dfs func(int)
	dfs = func(k int) {
		if k == len(t) {
			ok = true
			return
		}
		i, j := t[k][0], t[k][1]
		for v := 0; v < 9; v++ {
			if !row[i][v] && !col[j][v] && !block[i/3][j/3][v] {
				row[i][v], col[j][v], block[i/3][j/3][v] = true, true, true
				board[i][j] = byte(v + '1')
				dfs(k + 1)
				row[i][v], col[j][v], block[i/3][j/3][v] = false, false, false
			}
			if ok {
				return
			}
		}
	}
	dfs(0)
}
```

```cs
public class Solution {
    public void SolveSudoku(char[][] board) {
        this.board = new ushort?[9,9];
        for (var i = 0; i < 9; ++i)
        {
            for (var j = 0; j < 9; ++j)
            {
                if (board[i][j] != '.')
                {
                    this.board[i, j] = (ushort) (1 << (board[i][j] - '0' - 1));
                }
            }
        }

        if (SolveSudoku(0, 0))
        {
            for (var i = 0; i < 9; ++i)
            {
                for (var j = 0; j < 9; ++j)
                {
                    if (board[i][j] == '.')
                    {
                        board[i][j] = '0';
                        while (this.board[i, j].Value != 0)
                        {
                            board[i][j] = (char)(board[i][j] + 1);
                            this.board[i, j] >>= 1;
                        }
                    }
                }
            }
        }
    }

    private ushort?[,] board;

    private bool ValidateHorizontalRule(int row)
    {
        ushort temp = 0;
        for (var i = 0; i < 9; ++i)
        {
            if (board[row, i].HasValue)
            {
                if ((temp | board[row, i].Value) == temp)
                {
                    return false;
                }
                temp |= board[row, i].Value;
            }
        }
        return true;
    }

    private bool ValidateVerticalRule(int column)
    {
        ushort temp = 0;
        for (var i = 0; i < 9; ++i)
        {
            if (board[i, column].HasValue)
            {
                if ((temp | board[i, column].Value) == temp)
                {
                    return false;
                }
                temp |= board[i, column].Value;
            }
        }
        return true;
    }

    private bool ValidateBlockRule(int row, int column)
    {
        var startRow = row / 3 * 3;
        var startColumn = column / 3 * 3;
        ushort temp = 0;
        for (var i = startRow; i < startRow + 3; ++i)
        {
            for (var j = startColumn; j < startColumn + 3; ++j)
            {
                if (board[i, j].HasValue)
                {
                    if ((temp | board[i, j].Value) == temp)
                    {
                        return false;
                    }
                    temp |= board[i, j].Value;
                }
            }
        }
        return true;
    }

    private bool SolveSudoku(int i, int j)
    {
        while (true)
        {
            if (j == 9)
            {
                ++i;
                j = 0;
            }
            if (i == 9)
            {
                return true;
            }
            if (board[i, j].HasValue)
            {
                ++j;
            }
            else
            {
                break;
            }
        }

        ushort stop = 1 << 9;
        for (ushort t = 1; t != stop; t <<= 1)
        {
            board[i, j] = t;
            if (ValidateHorizontalRule(i) && ValidateVerticalRule(j) && ValidateBlockRule(i, j))
            {
                if (SolveSudoku(i, j + 1))
                {
                    return true;
                }
            }
        }
        board[i, j] = null;
        return false;
    }
}
```

```php
class Solution {
    /**
     * @param string[][] $board
     * @return bool
     */

    public function solveSudoku(&$board) {
        if (isSolved($board)) {
            return true;
        }

        $emptyCell = findEmptyCell($board);
        $row = $emptyCell[0];
        $col = $emptyCell[1];

        for ($num = 1; $num <= 9; $num++) {
            if (isValid($board, $row, $col, $num)) {
                $board[$row][$col] = (string) $num;
                if ($this->solveSudoku($board)) {
                    return true;
                }
                $board[$row][$col] = '.';
            }
        }
        return false;
    }
}

function isSolved($board) {
    foreach ($board as $row) {
        if (in_array('.', $row)) {
            return false;
        }
    }
    return true;
}

function findEmptyCell($board) {
    for ($row = 0; $row < 9; $row++) {
        for ($col = 0; $col < 9; $col++) {
            if ($board[$row][$col] === '.') {
                return [$row, $col];
            }
        }
    }

    return null;
}

function isValid($board, $row, $col, $num) {
    for ($i = 0; $i < 9; $i++) {
        if ($board[$row][$i] == $num) {
            return false;
        }
    }

    for ($i = 0; $i < 9; $i++) {
        if ($board[$i][$col] == $num) {
            return false;
        }
    }

    $startRow = floor($row / 3) * 3;
    $endCol = floor($col / 3) * 3;

    for ($i = 0; $i < 3; $i++) {
        for ($j = 0; $j < 3; $j++) {
            if ($board[$startRow + $i][$endCol + $j] == $num) {
                return false;
            }
        }
    }

    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
