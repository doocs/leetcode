# [130. 被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions)

[English Version](/solution/0100-0199/0130.Surrounded%20Regions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你一个 <code>m x n</code> 的矩阵 <code>board</code> ，由若干字符 <code>'X'</code> 和 <code>'O'</code> ，找到所有被 <code>'X'</code> 围绕的区域，并将这些区域里所有的 <code>'O'</code> 用 <code>'X'</code> 填充。
<div class="original__bRMd">
<div>
<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0130.Surrounded%20Regions/images/xogrid.jpg" style="width: 550px; height: 237px;" />
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

并查集。

```python
class Solution:
    def solve(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        m, n = len(board), len(board[0])
        p = list(range(m * n + 1))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]
        
        for i in range(m):
            for j in range(n):
                if board[i][j] == 'O':
                    if i == 0 or j == 0 or i == m - 1 or j == n - 1:
                        p[find(i * n + j)] = find(m * n)
                    else:
                        for x, y in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                            if board[i + x][j + y] == "O":
                                p[find(i * n + j)] = find((i + x) * n + j + y)
                    
        for i in range(m):
            for j in range(n):
                if board[i][j] == 'O' and find(i * n + j) != find(m * n):
                    board[i][j] = 'X'
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

并查集。

```java
class Solution {
    private int[] p;
    private int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        p = new int[m * n + 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'O') {
                    if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                        p[find(i * n + j)] = find(m * n);
                    } else {
                        for (int[] e : dirs) {
                            if (board[i + e[0]][j + e[1]] == 'O') {
                                p[find(i * n + j)] = find((i + e[0]) * n + j + e[1]);
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

```ts
/**
 Do not return anything, modify board in-place instead.
 */
function solve(board: string[][]): void {
    let m = board.length, n = board[0].length;
    if (m < 3 || n < 3) return;
    let visited = Array.from({ length: m }, v => new Array(n).fill(false));
    // 第一行，最后一行， 第一列， 最后一列
    for (let i of [0, m-1]) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] == 'X') {
                visited[i][j] = true;
            } else {
                dfs(board, i, j, visited, true);
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j of [0, n - 1]) {
            if (board[i][j] == 'X') {
                visited[i][j] = true;
            } else {
                dfs(board, i, j, visited, true);
            }
        }
    }
    for (let i = 1; i < m - 1; ++i) {
        for (let j = 1; j < n - 1; ++j) {
            !visited[i][j] && dfs(board, i, j, visited);
        }
    }
};

function dfs(board: string[][], i: number, j: number, visited: boolean[][], edge = false): void {
    let m = board.length, n = board[0].length;
    if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || visited[i][j]) {
        return;
    }

    visited[i][j] = true;
    if (board[i][j] == 'X') {
        return;
    }
    if (!edge) {
        board[i][j] = 'X';
    }
    for (let [dx, dy] of [[0, 1], [0, -1], [1, 0], [-1, 0]]) {
        let x = i + dx, y = j + dy;
        dfs(board, x, y, visited, edge);
    }
}
```

### **C++**

并查集。

```cpp
class Solution {
public:
    vector<int> p;
    int dirs[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    void solve(vector<vector<char>>& board) {
        int m = board.size(), n = board[0].size();
        p.resize(m * n + 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (board[i][j] == 'O')
                {
                    if (i == 0 || j == 0 || i == m - 1 || j == n - 1) p[find(i * n + j)] = find(m * n);
                    else
                    {
                        for (auto e : dirs)
                        {
                            if (board[i + e[0]][j + e[1]] == 'O') p[find(i * n + j)] = find((i + e[0]) * n + j + e[1]);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (board[i][j] == 'O' && find(i * n + j) != find(m * n)) board[i][j] = 'X';
            }
        }
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

并查集。

```go
var p []int

func solve(board [][]byte) {
	m, n := len(board), len(board[0])
	p = make([]int, m*n+1)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if board[i][j] == 'O' {
				if i == 0 || j == 0 || i == m-1 || j == n-1 {
					p[find(i*n+j)] = find(m * n)
				} else {
					for _, e := range dirs {
						if board[i+e[0]][j+e[1]] == 'O' {
							p[find(i*n+j)] = find((i+e[0])*n + j + e[1])
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

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}
```

### **...**

```

```

<!-- tabs:end -->
