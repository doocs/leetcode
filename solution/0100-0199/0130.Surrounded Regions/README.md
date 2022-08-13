# [130. 被围绕的区域](https://leetcode.cn/problems/surrounded-regions)

[English Version](/solution/0100-0199/0130.Surrounded%20Regions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你一个 <code>m x n</code> 的矩阵 <code>board</code> ，由若干字符 <code>'X'</code> 和 <code>'O'</code> ，找到所有被 <code>'X'</code> 围绕的区域，并将这些区域里所有的  <code>'O'</code> 用 <code>'X'</code> 填充。

<div class="original__bRMd">
<div>
<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0130.Surrounded%20Regions/images/xogrid.jpg" style="width: 550px; height: 237px;" />
<pre>
<strong>输入：</strong>board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
<strong>输出：</strong>[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
<strong>解释：</strong>被围绕的区间不会存在于边界上，换句话说，任何边界上的 <code>'O'</code> 都不会被填充为 <code>'X'</code>。 任何不在边界上，或不与边界上的 <code>'O'</code> 相连的 <code>'O'</code> 最终都会被填充为 <code>'X'</code>。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>board = [["X"]]
<strong>输出：</strong>[["X"]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 <= m, n <= 200</code></li>
	<li><code>board[i][j]</code> 为 <code>'X'</code> 或 <code>'O'</code></li>
</ul>
</div>
</div>

## 解法

DFS、BFS、并查集均可。

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

DFS：

```python
class Solution:
    def solve(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """

        def dfs(i, j):
            board[i][j] = '.'
            for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and board[x][y] == 'O':
                    dfs(x, y)

        m, n = len(board), len(board[0])
        for i in range(m):
            for j in range(n):
                if board[i][j] == 'O' and (
                    i == 0 or i == m - 1 or j == 0 or j == n - 1
                ):
                    dfs(i, j)
        for i in range(m):
            for j in range(n):
                if board[i][j] == 'O':
                    board[i][j] = 'X'
                elif board[i][j] == '.':
                    board[i][j] = 'O'
```

并查集：

```python
class Solution:
    def solve(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        m, n = len(board), len(board[0])
        p = list(range(m * n + 1))
        for i in range(m):
            for j in range(n):
                if board[i][j] == 'O':
                    if i == 0 or i == m - 1 or j == 0 or j == n - 1:
                        p[find(i * n + j)] = find(m * n)
                    else:
                        for a, b in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                            x, y = i + a, j + b
                            if board[x][y] == 'O':
                                p[find(x * n + y)] = find(i * n + j)
        for i in range(m):
            for j in range(n):
                if board[i][j] == 'O' and find(i * n + j) != find(m * n):
                    board[i][j] = 'X'
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

DFS：

```java
class Solution {
    private char[][] board;
    private int m;
    private int n;

    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && board[i][j] == 'O') {
                    dfs(i, j);
                }
            }
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
        board[i][j] = '.';
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O') {
                dfs(x, y);
            }
        }
    }
}
```

并查集：

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

### **TypeScript**

DFS：

```ts
/**
 Do not return anything, modify board in-place instead.
 */
function solve(board: string[][]): void {
    function dfs(i, j) {
        board[i][j] = '.';
        const dirs = [-1, 0, 1, 0, -1];
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O') {
                dfs(x, y);
            }
        }
    }
    const m = board.length;
    const n = board[0].length;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (
                (i == 0 || i == m - 1 || j == 0 || j == n - 1) &&
                board[i][j] == 'O'
            ) {
                dfs(i, j);
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] == '.') {
                board[i][j] = 'O';
            } else if (board[i][j] == 'O') {
                board[i][j] = 'X';
            }
        }
    }
}
```

并查集：

```ts
/**
 Do not return anything, modify board in-place instead.
 */
function solve(board: string[][]): void {
    const m = board.length;
    const n = board[0].length;
    let p = new Array(m * n + 1);
    for (let i = 0; i < p.length; ++i) {
        p[i] = i;
    }
    function find(x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
    const dirs = [-1, 0, 1, 0, -1];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] == 'O') {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    p[find(i * n + j)] = find(m * n);
                } else {
                    for (let k = 0; k < 4; ++k) {
                        const x = i + dirs[k];
                        const y = j + dirs[k + 1];
                        if (board[x][y] == 'O') {
                            p[find(x * n + y)] = find(i * n + j);
                        }
                    }
                }
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] == 'O' && find(i * n + j) != find(m * n)) {
                board[i][j] = 'X';
            }
        }
    }
}
```

### **C++**

DFS：

```cpp
class Solution {
public:
    void solve(vector<vector<char>>& board) {
        int m = board.size(), n = board[0].size();
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && board[i][j] == 'O')
                    dfs(board, i, j);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == '.')
                    board[i][j] = 'O';
                else if (board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }
    }

    void dfs(vector<vector<char>>& board, int i, int j) {
        board[i][j] = '.';
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < board.size() && y >= 0 && y < board[0].size() && board[x][y] == 'O')
                dfs(board, x, y);
        }
    }
};
```

并查集：

```cpp
class Solution {
public:
    vector<int> p;

    void solve(vector<vector<char>>& board) {
        int m = board.size(), n = board[0].size();
        p.resize(m * n + 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (board[i][j] == 'O')
                {
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) p[find(i * n + j)] = find(m * n);
                    else
                    {
                        for (int k = 0; k < 4; ++k)
                        {
                            int x = i + dirs[k], y = j + dirs[k + 1];
                            if (board[x][y] == 'O') p[find(x * n + y)] = find(i * n + j);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (board[i][j] == 'O' && find(i * n + j) != find(m * n))
                    board[i][j] = 'X';
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

DFS:

```go
func solve(board [][]byte) {
	m, n := len(board), len(board[0])
	var dfs func(i, j int)
	dfs = func(i, j int) {
		board[i][j] = '.'
		dirs := []int{-1, 0, 1, 0, -1}
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O' {
				dfs(x, y)
			}
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if (i == 0 || i == m-1 || j == 0 || j == n-1) && board[i][j] == 'O' {
				dfs(i, j)
			}
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if board[i][j] == '.' {
				board[i][j] = 'O'
			} else if board[i][j] == 'O' {
				board[i][j] = 'X'
			}
		}
	}
}
```

并查集：

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
	dirs := []int{-1, 0, 1, 0, -1}
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

### **Rust**

```rust
impl Solution {
    fn dfs(i: usize, j: usize, mark: char, vis: &mut Vec<Vec<bool>>, board: &mut Vec<Vec<char>>) {
        if vis[i][j] || board[i][j] != mark {
            return;
        }
        vis[i][j] = true;
        if i > 0 {
            Self::dfs(i - 1, j, mark, vis, board);
        }
        if i < vis.len() - 1 {
            Self::dfs(i + 1, j, mark, vis, board);
        }
        if j > 0 {
            Self::dfs(i, j - 1, mark, vis, board);
        }
        if j < vis[0].len() - 1 {
            Self::dfs(i, j + 1, mark, vis, board);
        }
    }

    pub fn solve(board: &mut Vec<Vec<char>>) {
        let m = board.len();
        let n = board[0].len();
        let mut vis = vec![vec![false; n]; m];
        for i in 0..m {
            Self::dfs(i, 0, board[i][0], &mut vis, board);
            Self::dfs(i, n - 1, board[i][n - 1], &mut vis, board);
        }
        for i in 0..n {
            Self::dfs(0, i, board[0][i], &mut vis, board);
            Self::dfs(m - 1, i, board[m - 1][i], &mut vis, board);
        }
        for i in 0..m {
            for j in 0..n {
                if vis[i][j] {
                    continue;
                }
                board[i][j] = 'X';
            }
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
