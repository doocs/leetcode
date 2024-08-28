---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1905.Count%20Sub%20Islands/README_EN.md
rating: 1678
source: Weekly Contest 246 Q3
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Array
    - Matrix
---

<!-- problem:start -->

# [1905. Count Sub Islands](https://leetcode.com/problems/count-sub-islands)

[中文文档](/solution/1900-1999/1905.Count%20Sub%20Islands/README.md)

## Description

<!-- description:start -->

<p>You are given two <code>m x n</code> binary matrices <code>grid1</code> and <code>grid2</code> containing only <code>0</code>&#39;s (representing water) and <code>1</code>&#39;s (representing land). An <strong>island</strong> is a group of <code>1</code>&#39;s connected <strong>4-directionally</strong> (horizontal or vertical). Any cells outside of the grid are considered water cells.</p>

<p>An island in <code>grid2</code> is considered a <strong>sub-island </strong>if there is an island in <code>grid1</code> that contains <strong>all</strong> the cells that make up <strong>this</strong> island in <code>grid2</code>.</p>

<p>Return the <em><strong>number</strong> of islands in </em><code>grid2</code> <em>that are considered <strong>sub-islands</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1905.Count%20Sub%20Islands/images/test1.png" style="width: 493px; height: 205px;" />
<pre>
<strong>Input:</strong> grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
<strong>Output:</strong> 3
<strong>Explanation: </strong>In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1905.Count%20Sub%20Islands/images/testcasex2.png" style="width: 491px; height: 201px;" />
<pre>
<strong>Input:</strong> grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
<strong>Output:</strong> 2
<strong>Explanation: </strong>In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid1.length == grid2.length</code></li>
	<li><code>n == grid1[i].length == grid2[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>grid1[i][j]</code> and <code>grid2[i][j]</code> are either <code>0</code> or <code>1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

We can traverse each cell $(i, j)$ in the matrix `grid2`. If the value of the cell is $1$, we start a depth-first search from this cell, set the value of all cells connected to this cell to $0$, and record whether the corresponding cell in `grid1` is also $1$ for all cells connected to this cell. If it is $1$, it means that this cell is also an island in `grid1`, otherwise it is not. Finally, we count the number of sub-islands in `grid2`.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the number of rows and columns of the matrices `grid1` and `grid2`, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSubIslands(self, grid1: List[List[int]], grid2: List[List[int]]) -> int:
        def dfs(i: int, j: int) -> int:
            ok = grid1[i][j]
            grid2[i][j] = 0
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid2[x][y] and not dfs(x, y):
                    ok = 0
            return ok

        m, n = len(grid1), len(grid1[0])
        dirs = (-1, 0, 1, 0, -1)
        return sum(dfs(i, j) for i in range(m) for j in range(n) if grid2[i][j])
```

#### Java

```java
class Solution {
    private final int[] dirs = {-1, 0, 1, 0, -1};
    private int[][] grid1;
    private int[][] grid2;
    private int m;
    private int n;

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        m = grid1.length;
        n = grid1[0].length;
        this.grid1 = grid1;
        this.grid2 = grid2;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1) {
                    ans += dfs(i, j);
                }
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        int ok = grid1[i][j];
        grid2[i][j] = 0;
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1) {
                ok &= dfs(x, y);
            }
        }
        return ok;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countSubIslands(vector<vector<int>>& grid1, vector<vector<int>>& grid2) {
        int m = grid1.size(), n = grid1[0].size();
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        function<int(int, int)> dfs = [&](int i, int j) {
            int ok = grid1[i][j];
            grid2[i][j] = 0;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y]) {
                    ok &= dfs(x, y);
                }
            }
            return ok;
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j]) {
                    ans += dfs(i, j);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countSubIslands(grid1 [][]int, grid2 [][]int) (ans int) {
	m, n := len(grid1), len(grid1[0])
	dirs := [5]int{-1, 0, 1, 0, -1}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		ok := grid1[i][j]
		grid2[i][j] = 0
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1 && dfs(x, y) == 0 {
				ok = 0
			}
		}
		return ok
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid2[i][j] == 1 {
				ans += dfs(i, j)
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function countSubIslands(grid1: number[][], grid2: number[][]): number {
    const [m, n] = [grid1.length, grid1[0].length];
    let ans = 0;
    const dirs: number[] = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number): number => {
        let ok = grid1[i][j];
        grid2[i][j] = 0;
        for (let k = 0; k < 4; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y]) {
                ok &= dfs(x, y);
            }
        }
        return ok;
    };
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; j++) {
            if (grid2[i][j]) {
                ans += dfs(i, j);
            }
        }
    }
    return ans;
}
```

#### JavaScript

```js
function countSubIslands(grid1, grid2) {
    const [m, n] = [grid1.length, grid1[0].length];
    let ans = 0;
    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i, j) => {
        let ok = grid1[i][j];
        grid2[i][j] = 0;
        for (let k = 0; k < 4; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y]) {
                ok &= dfs(x, y);
            }
        }
        return ok;
    };
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; j++) {
            if (grid2[i][j]) {
                ans += dfs(i, j);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSubIslands(self, grid1: List[List[int]], grid2: List[List[int]]) -> int:
        def bfs(i: int, j: int) -> int:
            ok = grid1[i][j]
            q = deque([(i, j)])
            grid2[i][j] = 0
            while q:
                i, j = q.popleft()
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and grid2[x][y]:
                        q.append((x, y))
                        ok = ok & grid1[x][y]
                        grid2[x][y] = 0
            return ok

        m, n = len(grid1), len(grid1[0])
        dirs = (-1, 0, 1, 0, -1)
        return sum(bfs(i, j) for i in range(m) for j in range(n) if grid2[i][j])
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
