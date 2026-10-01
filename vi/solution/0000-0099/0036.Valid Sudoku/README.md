---
comments: true
difficulty: Medium
tags:
    - Array
    - Hash Table
    - Matrix
---

<!-- problem:start -->

# [36. Valid Sudoku](https://leetcode.com/problems/valid-sudoku)

[中文文档](/solution/0000-0099/0036.Valid%20Sudoku/README.md)

## Mô tả

<!-- description:start -->

<p>Xác định xem một bảng Sudoku <code>9 x 9</code> có hợp lệ hay không.&nbsp;Chỉ cần kiểm tra các ô đã điền&nbsp;<strong>theo các quy tắc sau</strong>:</p>

<ol>
	<li>Mỗi hàng&nbsp;phải chứa các&nbsp;chữ số&nbsp;<code>1-9</code> không lặp lại.</li>
	<li>Mỗi cột phải chứa các chữ số&nbsp;<code>1-9</code>&nbsp;không lặp lại.</li>
	<li>Mỗi trong chín&nbsp;<code>3 x 3</code> ô con của bảng phải chứa các chữ số&nbsp;<code>1-9</code>&nbsp;không lặp lại.</li>
</ol>

<p><strong>Lưu ý:</strong></p>

<ul>
	<li>Một bảng Sudoku (được điền một phần) có thể hợp lệ nhưng không nhất thiết giải được.</li>
	<li>Chỉ cần kiểm tra các ô đã điền theo các quy tắc nêu trên.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0036.Valid%20Sudoku/images/250px-Sudoku-by-L2G-20050714.svg.png" style="height:250px; width:250px" />
<pre>
<strong>Đầu vào:</strong> board = 
[[&quot;5&quot;,&quot;3&quot;,&quot;.&quot;,&quot;.&quot;,&quot;7&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]
,[&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;1&quot;,&quot;9&quot;,&quot;5&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]
,[&quot;.&quot;,&quot;9&quot;,&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;,&quot;.&quot;]
,[&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;3&quot;]
,[&quot;4&quot;,&quot;.&quot;,&quot;.&quot;,&quot;8&quot;,&quot;.&quot;,&quot;3&quot;,&quot;.&quot;,&quot;.&quot;,&quot;1&quot;]
,[&quot;7&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;2&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;]
,[&quot;.&quot;,&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;2&quot;,&quot;8&quot;,&quot;.&quot;]
,[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;4&quot;,&quot;1&quot;,&quot;9&quot;,&quot;.&quot;,&quot;.&quot;,&quot;5&quot;]
,[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;7&quot;,&quot;9&quot;]]
<strong>Đầu ra:</strong> true
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> board = 
[[&quot;8&quot;,&quot;3&quot;,&quot;.&quot;,&quot;.&quot;,&quot;7&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]
,[&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;1&quot;,&quot;9&quot;,&quot;5&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;]
,[&quot;.&quot;,&quot;9&quot;,&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;,&quot;.&quot;]
,[&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;3&quot;]
,[&quot;4&quot;,&quot;.&quot;,&quot;.&quot;,&quot;8&quot;,&quot;.&quot;,&quot;3&quot;,&quot;.&quot;,&quot;.&quot;,&quot;1&quot;]
,[&quot;7&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;2&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;6&quot;]
,[&quot;.&quot;,&quot;6&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;2&quot;,&quot;8&quot;,&quot;.&quot;]
,[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;4&quot;,&quot;1&quot;,&quot;9&quot;,&quot;.&quot;,&quot;.&quot;,&quot;5&quot;]
,[&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;8&quot;,&quot;.&quot;,&quot;.&quot;,&quot;7&quot;,&quot;9&quot;]]
<strong>Đầu ra:</strong> false
<strong>Giải thích:</strong> Giống Ví dụ 1, ngoại trừ số <strong>5</strong> ở góc trên bên trái được sửa thành <strong>8</strong>. Vì có hai số 8 trong ô con 3x3 phía trên bên trái nên bảng không hợp lệ.
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>board.length == 9</code></li>
	<li><code>board[i].length == 9</code></li>
	<li><code>board[i][j]</code> là một chữ số <code>1-9</code> hoặc <code>&#39;.&#39;</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Duyệt một lần

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là, với mỗi chữ số đã điền, duyệt lại hàng, cột và ô con của nó. Bảng chỉ có kích thước $9 \times 9$, nên cách đó vẫn chạy được.
>
> Nút thắt là việc duyệt lại cùng hàng, cột và ô con. Chúng ta chỉ cần ghi nhớ xem chữ số $v$ đã xuất hiện trong đơn vị đó hay chưa.
>
> Chỉ cần một lượt duyệt: ba bảng boolean $row[i][v]$, $col[j][v]$ và $sub[k][v]$, với chỉ số ô con $k = \lfloor i/3 \rfloor \times 3 + \lfloor j/3 \rfloor$. Nếu gặp lại cùng chữ số lần thứ hai, lập tức trả về false.

<!-- thinking:end -->

Một sudoku hợp lệ thỏa mãn ba điều kiện sau:

- Các chữ số không lặp lại trong mỗi hàng;
- Các chữ số không lặp lại trong mỗi cột;
- Các chữ số không lặp lại trong mỗi ô con $3 \times 3$.

Duyệt qua sudoku, với mỗi chữ số, kiểm tra xem hàng, cột và ô con $3 \times 3$ chứa nó đã xuất hiện chữ số đó hay chưa. Nếu đã xuất hiện, trả về `false`. Nếu duyệt xong, trả về `true`.

Độ phức tạp thời gian là $O(C)$ và độ phức tạp không gian là $O(C)$, trong đó $C$ là số ô trống trong sudoku. Trong câu hỏi này, $C=81$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        row = [[False] * 9 for _ in range(9)]
        col = [[False] * 9 for _ in range(9)]
        sub = [[False] * 9 for _ in range(9)]
        for i in range(9):
            for j in range(9):
                c = board[i][j]
                if c == '.':
                    continue
                num = int(c) - 1
                k = i // 3 * 3 + j // 3
                if row[i][num] or col[j][num] or sub[k][num]:
                    return False
                row[i][num] = True
                col[j][num] = True
                sub[k][num] = True
        return True
```

#### Java

```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] sub = new boolean[9][9];
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int num = c - '0' - 1;
                int k = i / 3 * 3 + j / 3;
                if (row[i][num] || col[j][num] || sub[k][num]) {
                    return false;
                }
                row[i][num] = true;
                col[j][num] = true;
                sub[k][num] = true;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isValidSudoku(vector<vector<char>>& board) {
        vector<vector<bool>> row(9, vector<bool>(9, false));
        vector<vector<bool>> col(9, vector<bool>(9, false));
        vector<vector<bool>> sub(9, vector<bool>(9, false));
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                char c = board[i][j];
                if (c == '.') continue;
                int num = c - '0' - 1;
                int k = i / 3 * 3 + j / 3;
                if (row[i][num] || col[j][num] || sub[k][num]) {
                    return false;
                }
                row[i][num] = true;
                col[j][num] = true;
                sub[k][num] = true;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isValidSudoku(board [][]byte) bool {
	row, col, sub := [9][9]bool{}, [9][9]bool{}, [9][9]bool{}
	for i := 0; i < 9; i++ {
		for j := 0; j < 9; j++ {
			num := board[i][j] - byte('1')
			if num < 0 || num > 9 {
				continue
			}
			k := i/3*3 + j/3
			if row[i][num] || col[j][num] || sub[k][num] {
				return false
			}
			row[i][num] = true
			col[j][num] = true
			sub[k][num] = true
		}
	}
	return true
}
```

#### TypeScript

```ts
function isValidSudoku(board: string[][]): boolean {
    const row: boolean[][] = Array.from({ length: 9 }, () =>
        Array.from({ length: 9 }, () => false),
    );
    const col: boolean[][] = Array.from({ length: 9 }, () =>
        Array.from({ length: 9 }, () => false),
    );
    const sub: boolean[][] = Array.from({ length: 9 }, () =>
        Array.from({ length: 9 }, () => false),
    );
    for (let i = 0; i < 9; ++i) {
        for (let j = 0; j < 9; ++j) {
            const num = board[i][j].charCodeAt(0) - '1'.charCodeAt(0);
            if (num < 0 || num > 8) {
                continue;
            }
            const k = Math.floor(i / 3) * 3 + Math.floor(j / 3);
            if (row[i][num] || col[j][num] || sub[k][num]) {
                return false;
            }
            row[i][num] = true;
            col[j][num] = true;
            sub[k][num] = true;
        }
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_valid_sudoku(board: Vec<Vec<char>>) -> bool {
        let mut row = vec![vec![false; 9]; 9];
        let mut col = vec![vec![false; 9]; 9];
        let mut sub = vec![vec![false; 9]; 9];

        for i in 0..9 {
            for j in 0..9 {
                let c = board[i][j];
                if c == '.' {
                    continue;
                }
                let num = (c as u8 - b'0' - 1) as usize;
                let k = i / 3 * 3 + j / 3;
                if row[i][num] || col[j][num] || sub[k][num] {
                    return false;
                }
                row[i][num] = true;
                col[j][num] = true;
                sub[k][num] = true;
            }
        }
        true
    }
}
```

#### JavaScript

```js
/**
 * @param {character[][]} board
 * @return {boolean}
 */
var isValidSudoku = function (board) {
    const row = [...Array(9)].map(() => Array(9).fill(false));
    const col = [...Array(9)].map(() => Array(9).fill(false));
    const sub = [...Array(9)].map(() => Array(9).fill(false));
    for (let i = 0; i < 9; ++i) {
        for (let j = 0; j < 9; ++j) {
            const num = board[i][j].charCodeAt() - '1'.charCodeAt();
            if (num < 0 || num > 8) {
                continue;
            }
            const k = Math.floor(i / 3) * 3 + Math.floor(j / 3);
            if (row[i][num] || col[j][num] || sub[k][num]) {
                return false;
            }
            row[i][num] = true;
            col[j][num] = true;
            sub[k][num] = true;
        }
    }
    return true;
};
```

#### C#

```cs
public class Solution {
    public bool IsValidSudoku(char[][] board) {
        bool[,] row = new bool[9, 9];
        bool[,] col = new bool[9, 9];
        bool[,] sub = new bool[9, 9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int num = c - '0' - 1;
                int k = (i / 3) * 3 + (j / 3);
                if (row[i, num] || col[j, num] || sub[k, num]) {
                    return false;
                }
                row[i, num] = true;
                col[j, num] = true;
                sub[k, num] = true;
            }
        }
        return true;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param String[][] $board
     * @return Boolean
     */
    function isValidSudoku($board) {
        $row = array_fill(0, 9, array_fill(0, 9, false));
        $col = array_fill(0, 9, array_fill(0, 9, false));
        $sub = array_fill(0, 9, array_fill(0, 9, false));

        for ($i = 0; $i < 9; $i++) {
            for ($j = 0; $j < 9; $j++) {
                $c = $board[$i][$j];
                if ($c === '.') {
                    continue;
                }
                $num = intval($c) - 1;
                $k = intdiv($i, 3) * 3 + intdiv($j, 3);
                if ($row[$i][$num] || $col[$j][$num] || $sub[$k][$num]) {
                    return false;
                }
                $row[$i][$num] = true;
                $col[$j][$num] = true;
                $sub[$k][$num] = true;
            }
        }
        return true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
