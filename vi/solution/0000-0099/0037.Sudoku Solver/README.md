---
comments: true
difficulty: Hard
tags:
    - Array
    - Hash Table
    - Backtracking
    - Matrix
    - Algorithm X
    - Dancing Links
---

<!-- problem:start -->

# [37. Sudoku Solver](https://leetcode.com/problems/sudoku-solver)

[中文文档](/solution/0000-0099/0037.Sudoku%20Solver/README.md)

## Mô tả

<!-- description:start -->

<p>Viết một chương trình để giải một câu đố Sudoku bằng cách điền vào các ô trống.</p>

<p>Một lời giải Sudoku phải thỏa mãn <strong>tất cả các quy tắc sau</strong>:</p>

<ol>
	<li>Mỗi chữ số từ <code>1-9</code> phải xuất hiện chính xác một lần trong mỗi hàng.</li>
	<li>Mỗi chữ số từ <code>1-9</code> phải xuất hiện chính xác một lần trong mỗi cột.</li>
	<li>Mỗi chữ số từ <code>1-9</code> phải xuất hiện chính xác một lần trong mỗi một trong 9 ô vuông con <code>3x3</code> của lưới.</li>
</ol>

<p>Ký tự <code>&#39;.&#39;</code> biểu thị các ô trống.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0037.Sudoku%20Solver/images/250px-Sudoku-by-L2G-20050714.svg.png" style="height:250px; width:250px" />
<pre>
<strong>Đầu vào:</strong> board = [[&quot;5&quot;,&quot;3&quot;,&quot;.&quot;,&quot;.&quot;,&quot;7&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;1&quot;,&quot;9&quot;,&quot;5&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;9&quot;,&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;,&quot;.&quot;],[&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;3&quot;],[&quot;4&quot;,&quot;.&quot;,&quot;.&quot;,&quot;8&quot;,&quot;.&quot;,&quot;3&quot;,&quot;.&quot;,&quot;.&quot;,&quot;1&quot;],[&quot;7&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;2&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;],[&quot;.&quot;,&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;2&quot;,&quot;8&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;4&quot;,&quot;1&quot;,&quot;9&quot;,&quot;.&quot;,&quot;.&quot;,&quot;5&quot;],[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;7&quot;,&quot;9&quot;]]
<strong>Đầu ra:</strong> [[&quot;5&quot;,&quot;3&quot;,&quot;4&quot;,&quot;6&quot;,&quot;7&quot;,&quot;8&quot;,&quot;9&quot;,&quot;1&quot;,&quot;2&quot;],[&quot;6&quot;,&quot;7&quot;,&quot;2&quot;,&quot;1&quot;,&quot;9&quot;,&quot;5&quot;,&quot;3&quot;,&quot;4&quot;,&quot;8&quot;],[&quot;1&quot;,&quot;9&quot;,&quot;8&quot;,&quot;3&quot;,&quot;4&quot;,&quot;2&quot;,&quot;5&quot;,&quot;6&quot;,&quot;7&quot;],[&quot;8&quot;,&quot;5&quot;,&quot;9&quot;,&quot;7&quot;,&quot;6&quot;,&quot;1&quot;,&quot;4&quot;,&quot;2&quot;,&quot;3&quot;],[&quot;4&quot;,&quot;2&quot;,&quot;6&quot;,&quot;8&quot;,&quot;5&quot;,&quot;3&quot;,&quot;7&quot;,&quot;9&quot;,&quot;1&quot;],[&quot;7&quot;,&quot;1&quot;,&quot;3&quot;,&quot;9&quot;,&quot;2&quot;,&quot;4&quot;,&quot;8&quot;,&quot;5&quot;,&quot;6&quot;],[&quot;9&quot;,&quot;6&quot;,&quot;1&quot;,&quot;5&quot;,&quot;3&quot;,&quot;7&quot;,&quot;2&quot;,&quot;8&quot;,&quot;4&quot;],[&quot;2&quot;,&quot;8&quot;,&quot;7&quot;,&quot;4&quot;,&quot;1&quot;,&quot;9&quot;,&quot;6&quot;,&quot;3&quot;,&quot;5&quot;],[&quot;3&quot;,&quot;4&quot;,&quot;5&quot;,&quot;2&quot;,&quot;8&quot;,&quot;6&quot;,&quot;1&quot;,&quot;7&quot;,&quot;9&quot;]]
<strong>Giải thích:</strong>&nbsp;Bảng đầu vào được hiển thị ở trên và lời giải hợp lệ duy nhất được hiển thị bên dưới:

<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0037.Sudoku%20Solver/images/250px-Sudoku-by-L2G-20050714_solution.svg.png" style="height:250px; width:250px" />
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>board.length == 9</code></li>
	<li><code>board[i].length == 9</code></li>
	<li><code>board[i][j]</code> là một chữ số hoặc <code>&#39;.&#39;</code>.</li>
	<li><strong>Đảm bảo</strong> rằng bảng đầu vào chỉ có một lời giải.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Quay lui

<!-- thinking:start -->

> **Tư duy**
>
> Có thể có hàng chục ô trống và mỗi ô có $9$ lựa chọn, nên vét cạn là bất khả thi. Chúng ta phải dừng ngay khi một hàng, cột hoặc ô vuông bị vi phạm.
>
> Cũng như trong Valid Sudoku, nút thắt nằm ở việc liên tục kiểm tra xem một chữ số đã được sử dụng hay chưa. Ghi các chữ số đã cho vào $row$, $col$ và $block$, sau đó chỉ tìm kiếm trong danh sách ô trống $t$.
>
> $dfs(k)$ điền vào ô trống thứ $k$: thử từng giá trị chưa được sử dụng $v$, ghi giá trị đó rồi gọi đệ quy. Khi tìm thấy một lời giải, $ok$ dừng các lần thử tiếp theo. Quay lui khôi phục các cờ đánh dấu đã sử dụng; các chữ số trên nhánh thành công vẫn được giữ lại trên bảng.

<!-- thinking:end -->

Chúng ta sử dụng các mảng $\textit{row}$, $\textit{col}$ và $\textit{box}$ để ghi nhận mỗi số đã xuất hiện trong từng hàng, từng cột và từng ô vuông con 3x3 tương ứng. Nếu số $i$ đã xuất hiện ở hàng $r$, cột $c$ hoặc ô vuông con 3x3 thứ $b$, thì $\text{row[r][i]}$, $\text{col[c][i]}$ và $\text{box[b][i]}$ đều được đặt thành $true$.

Chúng ta duyệt qua mọi ô trống trong $\textit{board}$ và liệt kê các số $v$ có thể điền vào. Nếu $v$ chưa xuất hiện trong hàng, cột hoặc ô vuông con 3x3 hiện tại, chúng ta có thể thử điền $v$ rồi tiếp tục tìm kiếm ô trống tiếp theo. Nếu đi đến cuối và tất cả các ô đã được điền, điều đó có nghĩa là chúng ta đã tìm thấy một lời giải hợp lệ.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

```cpp
class Solution {
public:
    void solveSudoku(vector<vector<char>>& board) {
        bool row[9][9] = {false};
        bool col[9][9] = {false};
        bool block[3][3][9] = {false};
        bool ok = false;
        vector<pair<int, int>> t;
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
        auto dfs = [&](this auto&& dfs, int k) -> void {
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

#### Go

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

#### C#

```cs
public class Solution {
    public void SolveSudoku(char[][] board) {
        bool[,] row = new bool[9, 9];
        bool[,] col = new bool[9, 9];
        bool[,,] block = new bool[3, 3, 9];
        bool ok = false;
        var t = new List<(int, int)>();

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    t.Add((i, j));
                } else {
                    int v = board[i][j] - '1';
                    row[i, v] = col[j, v] = block[i / 3, j / 3, v] = true;
                }
            }
        }

        void Dfs(int k) {
            if (k == t.Count) {
                ok = true;
                return;
            }
            var (i, j) = t[k];
            for (int v = 0; v < 9; ++v) {
                if (!row[i, v] && !col[j, v] && !block[i / 3, j / 3, v]) {
                    row[i, v] = col[j, v] = block[i / 3, j / 3, v] = true;
                    board[i][j] = (char)(v + '1');
                    Dfs(k + 1);
                    if (ok) return;
                    row[i, v] = col[j, v] = block[i / 3, j / 3, v] = false;
                }
            }
        }

        Dfs(0);
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param String[][] $board
     * @return NULL
     */
    function solveSudoku(&$board) {
        $row = array_fill(0, 9, array_fill(0, 9, false));
        $col = array_fill(0, 9, array_fill(0, 9, false));
        $block = array_fill(0, 3, array_fill(0, 3, array_fill(0, 9, false)));
        $ok = false;
        $t = [];

        for ($i = 0; $i < 9; ++$i) {
            for ($j = 0; $j < 9; ++$j) {
                if ($board[$i][$j] === '.') {
                    $t[] = [$i, $j];
                } else {
                    $v = ord($board[$i][$j]) - ord('1');
                    $row[$i][$v] = true;
                    $col[$j][$v] = true;
                    $block[intval($i / 3)][intval($j / 3)][$v] = true;
                }
            }
        }

        $dfs = function ($k) use (&$dfs, &$board, &$row, &$col, &$block, &$ok, &$t) {
            if ($k === count($t)) {
                $ok = true;
                return;
            }
            [$i, $j] = $t[$k];
            for ($v = 0; $v < 9; ++$v) {
                if (!$row[$i][$v] && !$col[$j][$v] && !$block[intval($i / 3)][intval($j / 3)][$v]) {
                    $row[$i][$v] = $col[$j][$v] = $block[intval($i / 3)][intval($j / 3)][$v] = true;
                    $board[$i][$j] = chr($v + ord('1'));
                    $dfs($k + 1);
                    if ($ok) {
                        return;
                    }
                    $row[$i][$v] = $col[$j][$v] = $block[intval($i / 3)][intval($j / 3)][
                        $v
                    ] = false;
                }
            }
        };

        $dfs(0);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
