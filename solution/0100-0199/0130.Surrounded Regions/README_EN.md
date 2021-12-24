# [130. Surrounded Regions](https://leetcode.com/problems/surrounded-regions)

[中文文档](/solution/0100-0199/0130.Surrounded%20Regions/README.md)

## Description

<p>Given an <code>m x n</code> matrix <code>board</code> containing <code>&#39;X&#39;</code> and <code>&#39;O&#39;</code>, <em>capture all regions surrounded by</em> <code>&#39;X&#39;</code>.</p>

<p>A region is <strong>captured</strong> by flipping all <code>&#39;O&#39;</code>s into <code>&#39;X&#39;</code>s in that surrounded region.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0130.Surrounded%20Regions/images/xogrid.jpg" style="width: 550px; height: 237px;" />
<pre>
<strong>Input:</strong> board = [[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;O&quot;,&quot;O&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;X&quot;,&quot;O&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;O&quot;,&quot;X&quot;,&quot;X&quot;]]
<strong>Output:</strong> [[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;O&quot;,&quot;X&quot;,&quot;X&quot;]]
<strong>Explanation:</strong> Surrounded regions should not be on the border, which means that any &#39;O&#39; on the border of the board are not flipped to &#39;X&#39;. Any &#39;O&#39; that is not on the border and it is not connected to an &#39;O&#39; on the border will be flipped to &#39;X&#39;. Two cells are connected if they are adjacent cells connected horizontally or vertically.
</pre>

<p><strong>Example 2:</strong></p>

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

<!-- tabs:start -->

### **Python3**

Union find.

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

Union find.

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
    let m = board.length,
        n = board[0].length;
    if (m < 3 || n < 3) return;
    let visited = Array.from({ length: m }, v => new Array(n).fill(false));
    // 第一行，最后一行， 第一列， 最后一列
    for (let i of [0, m - 1]) {
        for (let j = 0; j < n; ++j) {
            if (board[i][j] == "X") {
                visited[i][j] = true;
            } else {
                dfs(board, i, j, visited, true);
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j of [0, n - 1]) {
            if (board[i][j] == "X") {
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
}

function dfs(
    board: string[][],
    i: number,
    j: number,
    visited: boolean[][],
    edge = false
): void {
    let m = board.length,
        n = board[0].length;
    if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || visited[i][j]) {
        return;
    }

    visited[i][j] = true;
    if (board[i][j] == "X") {
        return;
    }
    if (!edge) {
        board[i][j] = "X";
    }
    for (let [dx, dy] of [
        [0, 1],
        [0, -1],
        [1, 0],
        [-1, 0],
    ]) {
        let x = i + dx,
            y = j + dy;
        dfs(board, x, y, visited, edge);
    }
}
```

### **C++**

Union find.

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

Union find.

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
