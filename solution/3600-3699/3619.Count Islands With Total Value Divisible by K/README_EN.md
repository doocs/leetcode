---
comments: true
difficulty: Medium
rating: 1461
source: Biweekly Contest 161 Q2
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Array
    - Matrix
---

<!-- problem:start -->

# [3619. Count Islands With Total Value Divisible by K](https://leetcode.com/problems/count-islands-with-total-value-divisible-by-k)

[中文文档](/solution/3600-3699/3619.Count%20Islands%20With%20Total%20Value%20Divisible%20by%20K/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> matrix <code>grid</code> and a positive integer <code>k</code>. An <strong>island</strong> is a group of <strong>positive</strong> integers (representing land) that are <strong>4-directionally</strong> connected (horizontally or vertically).</p>

<p>The <strong>total value</strong> of an island is the sum of the values of all cells in the island.</p>

<p>Return the number of islands with a total value <strong>divisible by</strong> <code>k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3619.Count%20Islands%20With%20Total%20Value%20Divisible%20by%20K/images/example1griddrawio-1.png" style="width: 200px; height: 200px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[0,2,1,0,0],[0,5,0,0,5],[0,0,1,0,0],[0,1,4,7,0],[0,2,0,0,8]], k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The grid contains four islands. The islands highlighted in blue have a total value that is divisible by 5, while the islands highlighted in red do not.</p>
</div>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3619.Count%20Islands%20With%20Total%20Value%20Divisible%20by%20K/images/example2griddrawio.png" style="width: 200px; height: 150px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[3,0,3,0], [0,3,0,3], [3,0,3,0]], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>The grid contains six islands, each with a total value that is divisible by 3.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

<!-- thinking:start -->

> **Thinking**
>
> An island is a 4-connected component of positive cells. We need each island's value sum modulo $k$, not its shape. DFS accumulates the sum while zeroing visited cells, so a cell is never expanded twice.
>
> Scan the grid and start $\textit{dfs}$ from every still-positive cell; increment the answer when the returned sum is divisible by $k$. The packed offsets $(-1,0,1,0,-1)$ generate the four neighbors.
>
> Each cell is entered once, so the time matches the grid size.

<!-- thinking:end -->

We define a function $\textit{dfs}(i, j)$, which performs DFS traversal starting from position $(i, j)$ and returns the total value of that island. We add the current position's value to the total value, then mark that position as visited (for example, by setting its value to 0). Next, we recursively visit the adjacent positions in four directions (up, down, left, right). If an adjacent position has a value greater than 0, we continue the DFS and add its value to the total value. Finally, we return the total value.

In the main function, we traverse the entire grid. For each unvisited position $(i, j)$, if its value is greater than 0, we call $\textit{dfs}(i, j)$ to calculate the total value of that island. If the total value is divisible by $k$, we increment the answer by one.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the grid, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countIslands(self, grid: List[List[int]], k: int) -> int:
        def dfs(i: int, j: int) -> int:
            s = grid[i][j]
            grid[i][j] = 0
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y]:
                    s += dfs(x, y)
            return s

        m, n = len(grid), len(grid[0])
        dirs = (-1, 0, 1, 0, -1)
        ans = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] and dfs(i, j) % k == 0:
                    ans += 1
        return ans
```

#### Java

```java
class Solution {
    private int m;
    private int n;
    private int[][] grid;
    private final int[] dirs = {-1, 0, 1, 0, -1};

    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > 0 && dfs(i, j) % k == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private long dfs(int i, int j) {
        long s = grid[i][j];
        grid[i][j] = 0;
        for (int d = 0; d < 4; ++d) {
            int x = i + dirs[d], y = j + dirs[d + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
                s += dfs(x, y);
            }
        }
        return s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countIslands(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        vector<int> dirs = {-1, 0, 1, 0, -1};

        auto dfs = [&](this auto&& dfs, int i, int j) -> long long {
            long long s = grid[i][j];
            grid[i][j] = 0;
            for (int d = 0; d < 4; ++d) {
                int x = i + dirs[d], y = j + dirs[d + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y]) {
                    s += dfs(x, y);
                }
            }
            return s;
        };

        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] && dfs(i, j) % k == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countIslands(grid [][]int, k int) (ans int) {
	m, n := len(grid), len(grid[0])
	dirs := []int{-1, 0, 1, 0, -1}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		s := grid[i][j]
		grid[i][j] = 0
		for d := 0; d < 4; d++ {
			x, y := i+dirs[d], j+dirs[d+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0 {
				s += dfs(x, y)
			}
		}
		return s
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] > 0 && dfs(i, j)%k == 0 {
				ans++
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function countIslands(grid: number[][], k: number): number {
    const m = grid.length,
        n = grid[0].length;
    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number): number => {
        let s = grid[i][j];
        grid[i][j] = 0;
        for (let d = 0; d < 4; d++) {
            const x = i + dirs[d],
                y = j + dirs[d + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
                s += dfs(x, y);
            }
        }
        return s;
    };

    let ans = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] > 0 && dfs(i, j) % k === 0) {
                ans++;
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Explicit Stack

<!-- thinking:start -->

> **Thinking**
>
> An island is a 4-connected component of positive cells, and the answer depends only on whether each component sum is divisible by $k$. A recursive walk that adds a cell and then zeroes it computes that sum and keeps the same cell out of a second island.
>
> The product $m \times n$ reaches $10^5$. A snaking island makes one recursive call per cell, so the call stack overflows before the sum is finished. Zeroing a cell is only a visit mark; a neighbor still belongs to the island exactly when it is still positive.
>
> The cells waiting to be expanded fit on an explicit stack. A cell’s value is added and the cell is zeroed before it is pushed, then its four still-positive neighbors are pushed after the pop. The sum matches the recursive walk, while the stack depth stays under our control. One island can total $10^{11}$, so the accumulator is a 64-bit integer.
>
> Scanning the grid and starting a walk from every remaining positive cell counts the islands whose sum is divisible by $k$. Each cell is pushed at most once.

<!-- thinking:end -->

We walk each island with an explicit stack. Starting from a still-positive cell $(i, j)$, record its value in the sum $s$, set the cell to $0$, and push its coordinates. Each pop checks the four neighbors. A neighbor that is still positive is added into $s$, zeroed, and pushed. When the stack is empty, $s$ is the island’s total value. Zeroing before the push keeps a cell from being counted twice.

The main loop scans the grid. Every remaining positive cell starts one walk, and the answer increases when $s \bmod k = 0$. The sum is stored in a 64-bit integer so that $10^5$ cells of value up to $10^6$ do not overflow.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the grid, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countIslands(self, grid: List[List[int]], k: int) -> int:
        def flood(i: int, j: int) -> int:
            s = grid[i][j]
            grid[i][j] = 0
            stk = [(i, j)]
            while stk:
                i, j = stk.pop()
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and grid[x][y]:
                        s += grid[x][y]
                        grid[x][y] = 0
                        stk.append((x, y))
            return s

        m, n = len(grid), len(grid[0])
        dirs = (-1, 0, 1, 0, -1)
        ans = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] and flood(i, j) % k == 0:
                    ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[] dirs = {-1, 0, 1, 0, -1};
        Deque<int[]> stk = new ArrayDeque<>();
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    continue;
                }
                long s = grid[i][j];
                grid[i][j] = 0;
                stk.push(new int[] {i, j});
                while (!stk.isEmpty()) {
                    int[] p = stk.pop();
                    int x0 = p[0], y0 = p[1];
                    for (int d = 0; d < 4; ++d) {
                        int x = x0 + dirs[d], y = y0 + dirs[d + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
                            s += grid[x][y];
                            grid[x][y] = 0;
                            stk.push(new int[] {x, y});
                        }
                    }
                }
                if (s % k == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countIslands(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        int dirs[5] = {-1, 0, 1, 0, -1};
        vector<pair<int, int>> stk;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!grid[i][j]) {
                    continue;
                }
                long long s = grid[i][j];
                grid[i][j] = 0;
                stk.emplace_back(i, j);
                while (!stk.empty()) {
                    auto [x0, y0] = stk.back();
                    stk.pop_back();
                    for (int d = 0; d < 4; ++d) {
                        int x = x0 + dirs[d], y = y0 + dirs[d + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y]) {
                            s += grid[x][y];
                            grid[x][y] = 0;
                            stk.emplace_back(x, y);
                        }
                    }
                }
                if (s % k == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countIslands(grid [][]int, k int) (ans int) {
	m, n := len(grid), len(grid[0])
	dirs := []int{-1, 0, 1, 0, -1}
	stk := [][2]int{}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 {
				continue
			}
			s := grid[i][j]
			grid[i][j] = 0
			stk = append(stk, [2]int{i, j})
			for len(stk) > 0 {
				p := stk[len(stk)-1]
				stk = stk[:len(stk)-1]
				for d := 0; d < 4; d++ {
					x, y := p[0]+dirs[d], p[1]+dirs[d+1]
					if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0 {
						s += grid[x][y]
						grid[x][y] = 0
						stk = append(stk, [2]int{x, y})
					}
				}
			}
			if s%k == 0 {
				ans++
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function countIslands(grid: number[][], k: number): number {
    const m = grid.length;
    const n = grid[0].length;
    const dirs = [-1, 0, 1, 0, -1];
    const stk: number[][] = [];
    let ans = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 0) {
                continue;
            }
            let s = grid[i][j];
            grid[i][j] = 0;
            stk.push([i, j]);
            while (stk.length) {
                const [x0, y0] = stk.pop()!;
                for (let d = 0; d < 4; d++) {
                    const x = x0 + dirs[d];
                    const y = y0 + dirs[d + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
                        s += grid[x][y];
                        grid[x][y] = 0;
                        stk.push([x, y]);
                    }
                }
            }
            if (s % k === 0) {
                ans++;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
