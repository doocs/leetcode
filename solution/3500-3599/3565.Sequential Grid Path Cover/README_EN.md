---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3565.Sequential%20Grid%20Path%20Cover/README_EN.md
tags:
    - Recursion
    - Array
    - Matrix
---

<!-- problem:start -->

# [3565. Sequential Grid Path Cover 🔒](https://leetcode.com/problems/sequential-grid-path-cover)

[中文文档](/solution/3500-3599/3565.Sequential%20Grid%20Path%20Cover/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D array <code>grid</code> of size <code>m x n</code>, and an integer <code>k</code>. There are <code>k</code> cells in <code>grid</code> containing the values from 1 to <code>k</code> <strong>exactly once</strong>, and the rest of the cells have a value 0.</p>

<p>You can start at any cell, and move from a cell to its neighbors (up, down, left, or right). You must find a path in <code>grid</code> which:</p>

<ul>
	<li>Visits each cell in <code>grid</code> <strong>exactly once</strong>.</li>
	<li>Visits the cells with values from 1 to <code>k</code> <strong>in order</strong>.</li>
</ul>

<p>Return a 2D array <code>result</code> of size <code>(m * n) x 2</code>, where <code>result[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents the <code>i<sup>th</sup></code> cell visited in the path. If there are multiple such paths, you may return <strong>any</strong> one.</p>

<p>If no such path exists, return an <strong>empty</strong> array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[0,0,0],[0,1,2]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[[0,0],[1,0],[1,1],[1,2],[0,2],[0,1]]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3565.Sequential%20Grid%20Path%20Cover/images/ezgifcom-animated-gif-maker1.gif" style="width: 200px; height: 160px;" /></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,0,4],[3,0,2]], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no possible path that satisfies the conditions.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == grid.length &lt;= 5</code></li>
	<li><code>1 &lt;= n == grid[i].length &lt;= 5</code></li>
	<li><code>1 &lt;= k &lt;= m * n</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= k</code></li>
	<li><code>grid</code> contains all integers between 1 and <code>k</code> <strong>exactly</strong> once.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: State Compression + DFS

Note that the matrix size does not exceed $6 \times 6$, so we can use state compression to represent the visited cells. We can use an integer $\textit{st}$ to represent the visited cells, where the $i$-th bit being 1 means cell $i$ has been visited, and 0 means it has not been visited.

Next, we iterate through each cell as a starting point. If the cell is 0 or 1, we start a depth-first search (DFS) from that cell. In the DFS, we add the current cell to the path and mark it as visited. Then, we check the value of the current cell. If it equals $v$, we increment $v$ by 1. Next, we try to move in four directions to adjacent cells. If the adjacent cell has not been visited and its value is 0 or $v$, we continue the DFS.

If the DFS successfully finds a complete path, we return that path. If a complete path cannot be found, we backtrack, undo the visit mark for the current cell, and try other directions.

The time complexity is $O(m^2 \times n^2)$, and the space complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the matrix, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findPath(self, grid: List[List[int]], k: int) -> List[List[int]]:
        def f(i: int, j: int) -> int:
            return i * n + j

        def dfs(i: int, j: int, v: int):
            nonlocal st
            path.append([i, j])
            if len(path) == m * n:
                return True
            st |= 1 << f(i, j)
            if grid[i][j] == v:
                v += 1
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if (
                    0 <= x < m
                    and 0 <= y < n
                    and (st & 1 << f(x, y)) == 0
                    and grid[x][y] in (0, v)
                ):
                    if dfs(x, y, v):
                        return True
            path.pop()
            st ^= 1 << f(i, j)
            return False

        m, n = len(grid), len(grid[0])
        st = 0
        path = []
        dirs = (-1, 0, 1, 0, -1)
        for i in range(m):
            for j in range(n):
                if grid[i][j] in (0, 1):
                    if dfs(i, j, 1):
                        return path
                    path.clear()
                    st = 0
        return []
```

#### Java

```java
class Solution {
    private int m, n;
    private long st = 0;
    private List<List<Integer>> path = new ArrayList<>();
    private final int[] dirs = {-1, 0, 1, 0, -1};

    private int f(int i, int j) {
        return i * n + j;
    }

    private boolean dfs(int i, int j, int v, int[][] grid) {
        path.add(Arrays.asList(i, j));
        if (path.size() == m * n) {
            return true;
        }
        st |= 1L << f(i, j);
        if (grid[i][j] == v) {
            v += 1;
        }
        for (int t = 0; t < 4; t++) {
            int a = dirs[t], b = dirs[t + 1];
            int x = i + a, y = j + b;
            if (0 <= x && x < m && 0 <= y && y < n && (st & (1L << f(x, y))) == 0
                && (grid[x][y] == 0 || grid[x][y] == v)) {
                if (dfs(x, y, v, grid)) {
                    return true;
                }
            }
        }
        path.remove(path.size() - 1);
        st ^= 1L << f(i, j);
        return false;
    }

    public List<List<Integer>> findPath(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 || grid[i][j] == 1) {
                    if (dfs(i, j, 1, grid)) {
                        return path;
                    }
                    path.clear();
                    st = 0;
                }
            }
        }
        return List.of();
    }
}
```

#### C++

```cpp
class Solution {
    int m, n;
    unsigned long long st = 0;
    vector<vector<int>> path;
    int dirs[5] = {-1, 0, 1, 0, -1};

    int f(int i, int j) {
        return i * n + j;
    }

    bool dfs(int i, int j, int v, vector<vector<int>>& grid) {
        path.push_back({i, j});
        if (path.size() == static_cast<size_t>(m * n)) {
            return true;
        }
        st |= 1ULL << f(i, j);
        if (grid[i][j] == v) {
            v += 1;
        }
        for (int t = 0; t < 4; ++t) {
            int a = dirs[t], b = dirs[t + 1];
            int x = i + a, y = j + b;
            if (0 <= x && x < m && 0 <= y && y < n && (st & (1ULL << f(x, y))) == 0
                && (grid[x][y] == 0 || grid[x][y] == v)) {
                if (dfs(x, y, v, grid)) {
                    return true;
                }
            }
        }
        path.pop_back();
        st ^= 1ULL << f(i, j);
        return false;
    }

public:
    vector<vector<int>> findPath(vector<vector<int>>& grid, int k) {
        m = grid.size();
        n = grid[0].size();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0 || grid[i][j] == 1) {
                    if (dfs(i, j, 1, grid)) {
                        return path;
                    }
                    path.clear();
                    st = 0;
                }
            }
        }
        return {};
    }
};
```

#### Go

```go
func findPath(grid [][]int, k int) [][]int {
	_ = k
	m := len(grid)
	n := len(grid[0])
	var st uint64
	path := [][]int{}
	dirs := []int{-1, 0, 1, 0, -1}

	f := func(i, j int) int { return i*n + j }

	var dfs func(int, int, int) bool
	dfs = func(i, j, v int) bool {
		path = append(path, []int{i, j})
		if len(path) == m*n {
			return true
		}
		idx := f(i, j)
		st |= 1 << idx
		if grid[i][j] == v {
			v++
		}
		for t := 0; t < 4; t++ {
			a, b := dirs[t], dirs[t+1]
			x, y := i+a, j+b
			if 0 <= x && x < m && 0 <= y && y < n {
				idx2 := f(x, y)
				if (st>>idx2)&1 == 0 && (grid[x][y] == 0 || grid[x][y] == v) {
					if dfs(x, y, v) {
						return true
					}
				}
			}
		}
		path = path[:len(path)-1]
		st ^= 1 << idx
		return false
	}

	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 || grid[i][j] == 1 {
				if dfs(i, j, 1) {
					return path
				}
				path = path[:0]
				st = 0
			}
		}
	}
	return [][]int{}
}
```

#### TypeScript

```ts
function findPath(grid: number[][], k: number): number[][] {
    const m = grid.length;
    const n = grid[0].length;

    const dirs = [-1, 0, 1, 0, -1];
    const path: number[][] = [];
    let st = 0;

    function f(i: number, j: number): number {
        return i * n + j;
    }

    function dfs(i: number, j: number, v: number): boolean {
        path.push([i, j]);
        if (path.length === m * n) {
            return true;
        }

        st |= 1 << f(i, j);
        if (grid[i][j] === v) {
            v += 1;
        }

        for (let d = 0; d < 4; d++) {
            const x = i + dirs[d];
            const y = j + dirs[d + 1];
            const pos = f(x, y);
            if (
                x >= 0 &&
                x < m &&
                y >= 0 &&
                y < n &&
                (st & (1 << pos)) === 0 &&
                (grid[x][y] === 0 || grid[x][y] === v)
            ) {
                if (dfs(x, y, v)) {
                    return true;
                }
            }
        }

        path.pop();
        st ^= 1 << f(i, j);
        return false;
    }

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 0 || grid[i][j] === 1) {
                st = 0;
                path.length = 0;
                if (dfs(i, j, 1)) {
                    return path;
                }
            }
        }
    }

    return [];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
