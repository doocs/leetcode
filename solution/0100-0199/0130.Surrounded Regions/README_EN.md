# [130. Surrounded Regions](https://leetcode.com/problems/surrounded-regions)

[中文文档](/solution/0100-0199/0130.Surrounded%20Regions/README.md)

<!-- tags:Depth-First Search,Breadth-First Search,Union Find,Array,Matrix -->

<!-- difficulty:Medium -->

## Description

<p>Given an <code>m x n</code> matrix <code>board</code> containing <code>&#39;X&#39;</code> and <code>&#39;O&#39;</code>, <em>capture all regions that are 4-directionally&nbsp;surrounded by</em> <code>&#39;X&#39;</code>.</p>

<p>A region is <strong>captured</strong> by flipping all <code>&#39;O&#39;</code>s into <code>&#39;X&#39;</code>s in that surrounded region.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0130.Surrounded%20Regions/images/xogrid.jpg" style="width: 550px; height: 237px;" />
<pre>
<strong>Input:</strong> board = [[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;O&quot;,&quot;O&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;X&quot;,&quot;O&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;O&quot;,&quot;X&quot;,&quot;X&quot;]]
<strong>Output:</strong> [[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;O&quot;,&quot;X&quot;,&quot;X&quot;]]
<strong>Explanation:</strong> Notice that an &#39;O&#39; should not be flipped if:
- It is on the border, or
- It is adjacent to an &#39;O&#39; that should not be flipped.
The bottom &#39;O&#39; is on the border, so it is not flipped.
The other three &#39;O&#39; form a surrounded region, so they are flipped.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> board = [[&quot;X&quot;]]
<strong>Output:</strong> [[&quot;X&quot;]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>board[i][j]</code> is <code>&#39;X&#39;</code> or <code>&#39;O&#39;</code>.</li>
</ul>

## Solutions

### Solution 1: Depth-First Search (DFS)

We can start from the boundary of the matrix, taking each 'O' on the matrix boundary as a starting point, and perform depth-first search. All 'O's found in the search are replaced with '.'.

Then we traverse the matrix again, for each position:

-   If it is '.', replace it with 'O';
-   Otherwise, if it is 'O', replace it with 'X'.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the number of rows and columns in the matrix, respectively.

<!-- tabs:start -->

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
            n: usize
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
                        n
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

### Solution 2: Union-Find Set

We can also use a union-find set, connecting each 'O' on the matrix boundary with a super node $m \times n$, and connecting each 'O' in the matrix with the 'O's above, below, left, and right of it.

Then we traverse this matrix, for each position, if it is 'O' and it is not connected to the super node, then we replace it with 'X'.

The time complexity is $O(m \times n \times \alpha(m \times n))$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the number of rows and columns in the matrix, respectively, and $\alpha$ is the inverse Ackermann function.

<!-- tabs:start -->

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

<!-- end -->
