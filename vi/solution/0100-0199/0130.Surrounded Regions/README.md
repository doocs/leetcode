---
comments: true
difficulty: Medium
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Array
    - Matrix
---

<!-- problem:start -->

# [130. Surrounded Regions](https://leetcode.com/problems/surrounded-regions)

[中文文档](/solution/0100-0199/0130.Surrounded%20Regions/README.md)

## Mô tả

<!-- description:start -->

<p>Bạn được cho một ma trận <code>m x n</code> <code>board</code> chứa các <strong>chữ cái</strong> <code>&#39;X&#39;</code> và <code>&#39;O&#39;</code>, hãy <strong>thu giữ các vùng</strong> bị <strong>bao quanh</strong>:</p>

<ul>
	<li><strong>Kết nối</strong>: Một ô được kết nối với các ô liền kề theo chiều ngang hoặc chiều dọc.</li>
	<li><strong>Vùng</strong>: Để tạo thành một vùng, hãy <strong>kết nối mọi</strong> ô <code>&#39;O&#39;</code>.</li>
	<li><strong>Bao quanh</strong>: Một vùng được bao quanh nếu không có ô <code>&#39;O&#39;</code> nào trong vùng đó nằm trên cạnh của board. Những vùng như vậy được <strong>bao kín hoàn toàn</strong> bởi các ô <code>&#39;X&#39;</code>.</li>
</ul>

<p>Để thu giữ một <strong>vùng bị bao quanh</strong>, hãy thay thế tất cả <code>&#39;O&#39;</code> bằng <code>&#39;X&#39;</code> <strong>tại chỗ</strong> trong board ban đầu. Bạn không cần trả về gì cả.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">board = [[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;O&quot;,&quot;O&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;X&quot;,&quot;O&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;O&quot;,&quot;X&quot;,&quot;X&quot;]]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;O&quot;,&quot;X&quot;,&quot;X&quot;]]</span></p>

<p><strong>Giải thích:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0130.Surrounded%20Regions/images/xogrid.jpg" style="width: 367px; height: 158px;" />
<p>Trong sơ đồ trên, vùng ở phía dưới không bị thu giữ vì nằm trên cạnh của board và không thể bị bao quanh.</p>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">board = [[&quot;X&quot;]]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">[[&quot;X&quot;]]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>board[i][j]</code> là <code>&#39;X&#39;</code> hoặc <code>&#39;O&#39;</code>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Tìm kiếm theo chiều sâu (DFS)

<!-- thinking:start -->

> **Tư duy**
>
> Các `'O'` bị bao quanh sẽ trở thành `'X'`; các ô được kết nối với biên sẽ được giữ lại. $m,n\le 200$. Nếu bắt đầu từ các ô bên trong để hỏi “tôi có bị bao quanh không?”, chúng ta phải duyệt tràn qua cả một thành phần và rất dễ xử lý sai trường hợp nằm trên biên.
>
> Hãy đánh dấu các ô không bị bao quanh. DFS từ mọi `'O'` trên biên, tô các ô đó bằng một ký hiệu tạm thời, đổi các `'O'` còn lại thành `'X'`, sau đó khôi phục các ký hiệu tạm thời.

<!-- thinking:end -->

Chúng ta có thể bắt đầu từ biên của ma trận, lấy mỗi 'O' trên biên ma trận làm điểm bắt đầu và thực hiện tìm kiếm theo chiều sâu. Tất cả 'O' được tìm thấy trong quá trình tìm kiếm sẽ được thay thế bằng '.'.

Sau đó, chúng ta duyệt lại ma trận, với mỗi vị trí:

- Nếu là '.', thay thế nó bằng 'O';
- Ngược lại, nếu là 'O', thay thế nó bằng 'X'.

Độ phức tạp thời gian là $O(m \times n)$, và độ phức tạp không gian là $O(m \times n)$. Ở đây, $m$ và $n$ lần lượt là số hàng và số cột trong ma trận.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def solve(self, board: List[List[str]]) -> None:
        def dfs(i: int, j: int):
            if not (0 <= i < m and 0 <= j < n and board[i][j] == "O"):
                return
            board[i][j] = "."
            for a, b in pairwise((-1, 0, 1, 0, -1)):
                dfs(i + a, j + b)

        m, n = len(board), len(board[0])
        for i in range(m):
            dfs(i, 0)
            dfs(i, n - 1)
        for j in range(n):
            dfs(0, j)
            dfs(m - 1, j)
        for i in range(m):
            for j in range(n):
                if board[i][j] == ".":
                    board[i][j] = "O"
                elif board[i][j] == "O":
                    board[i][j] = "X"
```

#### Java

```java
class Solution {
    private final int[] dirs = {-1, 0, 1, 0, -1};
    private char[][] board;
    private int m;
    private int n;

    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        for (int i = 0; i < m; ++i) {
            dfs(i, 0);
            dfs(i, n - 1);
        }
        for (int j = 0; j < n; ++j) {
            dfs(0, j);
            dfs(m - 1, j);
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == '.') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') {
            return;
        }
        board[i][j] = '.';
        for (int k = 0; k < 4; ++k) {
            dfs(i + dirs[k], j + dirs[k + 1]);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    void solve(vector<vector<char>>& board) {
        int m = board.size(), n = board[0].size();
        int dirs[5] = {-1, 0, 1, 0, -1};
        function<void(int, int)> dfs = [&](int i, int j) {
            if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') {
                return;
            }
            board[i][j] = '.';
            for (int k = 0; k < 4; ++k) {
                dfs(i + dirs[k], j + dirs[k + 1]);
            }
        };
        for (int i = 0; i < m; ++i) {
            dfs(i, 0);
            dfs(i, n - 1);
        }
        for (int j = 1; j < n - 1; ++j) {
            dfs(0, j);
            dfs(m - 1, j);
        }
        for (auto& row : board) {
            for (auto& c : row) {
                if (c == '.') {
                    c = 'O';
                } else if (c == 'O') {
                    c = 'X';
                }
            }
        }
    }
};
```

#### Go

```go
func solve(board [][]byte) {
	m, n := len(board), len(board[0])
	dirs := [5]int{-1, 0, 1, 0, -1}
	var dfs func(i, j int)
	dfs = func(i, j int) {
		if i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O' {
			return
		}
		board[i][j] = '.'
		for k := 0; k < 4; k++ {
			dfs(i+dirs[k], j+dirs[k+1])
		}
	}
	for i := 0; i < m; i++ {
		dfs(i, 0)
		dfs(i, n-1)
	}
	for j := 0; j < n; j++ {
		dfs(0, j)
		dfs(m-1, j)
	}
	for i, row := range board {
		for j, c := range row {
			if c == '.' {
				board[i][j] = 'O'
			} else if c == 'O' {
				board[i][j] = 'X'
			}
		}
	}
}
```

#### TypeScript

```ts
function solve(board: string[][]): void {
    const m = board.length;
    const n = board[0].length;
    const dirs: number[] = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number): void => {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] !== 'O') {
            return;
        }
        board[i][j] = '.';
        for (let k = 0; k < 4; ++k) {
            dfs(i + dirs[k], j + dirs[k + 1]);
        }
    };
    for (let i = 0; i < m; ++i) {
        dfs(i, 0);
        dfs(i, n - 1);
    }
    for (let j = 0; j < n; ++j) {
        dfs(0, j);
        dfs(m - 1, j);
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] === '.') {
                board[i][j] = 'O';
            } else if (board[i][j] === 'O') {
                board[i][j] = 'X';
            }
        }
    }
}
```

#### Rust

```rust
impl Solution {
    pub fn solve(board: &mut Vec<Vec<char>>) {
        let m = board.len();
        let n = board[0].len();
        let dirs = vec![-1, 0, 1, 0, -1];

        fn dfs(
            board: &mut Vec<Vec<char>>,
            i: usize,
            j: usize,
            dirs: &Vec<i32>,
            m: usize,
            n: usize,
        ) {
            if i >= 0 && i < m && j >= 0 && j < n && board[i][j] == 'O' {
                board[i][j] = '.';
                for k in 0..4 {
                    dfs(
                        board,
                        ((i as i32) + dirs[k]) as usize,
                        ((j as i32) + dirs[k + 1]) as usize,
                        dirs,
                        m,
                        n,
                    );
                }
            }
        }

        for i in 0..m {
            dfs(board, i, 0, &dirs, m, n);
            dfs(board, i, n - 1, &dirs, m, n);
        }
        for j in 0..n {
            dfs(board, 0, j, &dirs, m, n);
            dfs(board, m - 1, j, &dirs, m, n);
        }

        for i in 0..m {
            for j in 0..n {
                if board[i][j] == '.' {
                    board[i][j] = 'O';
                } else if board[i][j] == 'O' {
                    board[i][j] = 'X';
                }
            }
        }
    }
}
```

#### C#

```cs
public class Solution {
    private readonly int[] dirs = {-1, 0, 1, 0, -1};
    private char[][] board;
    private int m;
    private int n;

    public void Solve(char[][] board) {
        m = board.Length;
        n = board[0].Length;
        this.board = board;

        for (int i = 0; i < m; ++i) {
            Dfs(i, 0);
            Dfs(i, n - 1);
        }
        for (int j = 0; j < n; ++j) {
            Dfs(0, j);
            Dfs(m - 1, j);
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == '.') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void Dfs(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') {
            return;
        }
        board[i][j] = '.';
        for (int k = 0; k < 4; ++k) {
            Dfs(i + dirs[k], j + dirs[k + 1]);
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Tập hợp Union-Find

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 dùng DFS để tô màu các ô được kết nối với biên. Union-find liên kết các `'O'` liền kề và nối mọi `'O'` trên biên với một nút giả. Bất kỳ `'O'` nào không thuộc thành phần đó đều bị bao quanh. Cách này không dùng ngăn xếp đệ quy và thể hiện rõ tính liên thông.

<!-- thinking:end -->

Chúng ta cũng có thể sử dụng một tập hợp union-find, kết nối mỗi 'O' trên biên ma trận với một nút siêu cấp $m \times n$, đồng thời kết nối mỗi 'O' trong ma trận với các 'O' ở phía trên, phía dưới, bên trái và bên phải của nó.

Sau đó, chúng ta duyệt ma trận này, với mỗi vị trí, nếu đó là 'O' và không được kết nối với nút siêu cấp, thì thay thế nó bằng 'X'.

Độ phức tạp thời gian là $O(m \times n \times \alpha(m \times n))$, và độ phức tạp không gian là $O(m \times n)$. Ở đây, $m$ và $n$ lần lượt là số hàng và số cột trong ma trận, còn $\alpha$ là hàm Ackermann ngược.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def solve(self, board: List[List[str]]) -> None:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        m, n = len(board), len(board[0])
        p = list(range(m * n + 1))
        for i in range(m):
            for j in range(n):
                if board[i][j] == "O":
                    if i in (0, m - 1) or j in (0, n - 1):
                        p[find(i * n + j)] = find(m * n)
                    else:
                        for a, b in pairwise((-1, 0, 1, 0, -1)):
                            x, y = i + a, j + b
                            if board[x][y] == "O":
                                p[find(x * n + y)] = find(i * n + j)
        for i in range(m):
            for j in range(n):
                if board[i][j] == "O" and find(i * n + j) != find(m * n):
                    board[i][j] = "X"
```

#### Java

```java
class Solution {
    private int[] p;

    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        p = new int[m * n + 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'O') {
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        p[find(i * n + j)] = find(m * n);
                    } else {
                        for (int k = 0; k < 4; ++k) {
                            int x = i + dirs[k];
                            int y = j + dirs[k + 1];
                            if (board[x][y] == 'O') {
                                p[find(x * n + y)] = find(i * n + j);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'O' && find(i * n + j) != find(m * n)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

#### C++

```cpp
class Solution {
public:
    void solve(vector<vector<char>>& board) {
        int m = board.size(), n = board[0].size();
        vector<int> p(m * n + 1);
        iota(p.begin(), p.end(), 0);
        function<int(int)> find = [&](int x) {
            return p[x] == x ? x : p[x] = find(p[x]);
        };
        int dirs[5] = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'O') {
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        p[find(i * n + j)] = find(m * n);
                    } else {
                        for (int k = 0; k < 4; ++k) {
                            int x = i + dirs[k];
                            int y = j + dirs[k + 1];
                            if (board[x][y] == 'O') {
                                p[find(x * n + y)] = find(i * n + j);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'O' && find(i * n + j) != find(m * n)) {
                    board[i][j] = 'X';
                }
            }
        }
    }
};
```

#### Go

```go
func solve(board [][]byte) {
	m, n := len(board), len(board[0])
	p := make([]int, m*n+1)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if board[i][j] == 'O' {
				if i == 0 || i == m-1 || j == 0 || j == n-1 {
					p[find(i*n+j)] = find(m * n)
				} else {
					for k := 0; k < 4; k++ {
						x, y := i+dirs[k], j+dirs[k+1]
						if board[x][y] == 'O' {
							p[find(x*n+y)] = find(i*n + j)
						}
					}
				}
			}
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if board[i][j] == 'O' && find(i*n+j) != find(m*n) {
				board[i][j] = 'X'
			}
		}
	}
}
```

#### TypeScript

```ts
function solve(board: string[][]): void {
    const m = board.length;
    const n = board[0].length;
    const p: number[] = Array(m * n + 1)
        .fill(0)
        .map((_, i) => i);
    const dirs: number[] = [-1, 0, 1, 0, -1];
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] === 'O') {
                if (i === 0 || i === m - 1 || j === 0 || j === n - 1) {
                    p[find(i * n + j)] = find(m * n);
                } else {
                    for (let k = 0; k < 4; ++k) {
                        const [x, y] = [i + dirs[k], j + dirs[k + 1]];
                        if (board[x][y] === 'O') {
                            p[find(x * n + y)] = find(i * n + j);
                        }
                    }
                }
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] === 'O' && find(i * n + j) !== find(m * n)) {
                board[i][j] = 'X';
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
