# [695. Max Area of Island](https://leetcode.com/problems/max-area-of-island)

[中文文档](/solution/0600-0699/0695.Max%20Area%20of%20Island/README.md)

## Description

<p>You are given an <code>m x n</code> binary matrix <code>grid</code>. An island is a group of <code>1</code>&#39;s (representing land) connected <strong>4-directionally</strong> (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.</p>

<p>The <strong>area</strong> of an island is the number of cells with a value <code>1</code> in the island.</p>

<p>Return <em>the maximum <strong>area</strong> of an island in </em><code>grid</code>. If there is no island, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0695.Max%20Area%20of%20Island/images/maxarea1-grid.jpg" style="width: 500px; height: 310px;" />
<pre>
<strong>Input:</strong> grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The answer is not 11, because the island must be connected 4-directionally.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[0,0,0,0,0,0,0,0]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

DFS:

```python
class Solution:
    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        def dfs(i, j):
            grid[i][j] = 0
            ans = 1
            for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y] == 1:
                    ans += dfs(x, y)
            return ans

        m, n = len(grid), len(grid[0])
        return max(
            [dfs(i, j) for i in range(m) for j in range(n) if grid[i][j] == 1],
            default=0,
        )
```

Union find:

```python
class Solution:
    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        m, n = len(grid), len(grid[0])
        p = list(range(m * n))
        size = [1] * (m * n)
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    for a, b in [[0, 1], [1, 0]]:
                        x, y = i + a, j + b
                        if 0 <= x < m and 0 <= y < n and grid[x][y] == 1 and find(i * n + j) != find(x * n + y):
                            size[find(x * n + y)] += size[find(i * n + j)]
                            p[find(i * n + j)] = find(x * n + y)
        return max([size[i * n + j] for i in range(m) for j in range(n) if grid[i][j] == 1], default=0)
```

### **Java**

DFS:

```java
class Solution {
    private int[][] grid;
    private int m;
    private int n;

    public int maxAreaOfIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    ans = Math.max(ans, dfs(i, j));
                }
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        grid[i][j] = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        int ans = 1;
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                ans += dfs(x, y);
            }
        }
        return ans;
    }
}
```

Union find:

```java
class Solution {
    private int[] p;
    private int[] size;

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        p = new int[m * n];
        size = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        int[] dirs = {0, 1, 0};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 2; ++k) {
                        int x = i + dirs[k];
                        int y = j + dirs[k + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 && find(i * n + j) != find(x * n + y)) {
                            size[find(x * n + y)] += size[find(i * n + j)];
                            p[find(i * n + j)] = find(x * n + y);
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    ans = Math.max(ans, size[i * n + j]);
                }
            }
        }
        return ans;
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

DFS:

```ts
function maxAreaOfIsland(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let ans = 0;
    const dirs = [-1, 0, 1, 0, -1];
    let dfs = function (i, j) {
        grid[i][j] = 0;
        let ans = 1;
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                ans += dfs(x, y);
            }
        }
        return ans;
    };
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == 1) {
                ans = Math.max(ans, dfs(i, j));
            }
        }
    }
    return ans;
}
```

Union find:

```ts
function maxAreaOfIsland(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let p = new Array(m * n);
    for (let i = 0; i < p.length; ++i) {
        p[i] = i;
    }
    let size = new Array(m * n).fill(1);
    let find = function (x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    const dirs = [1, 0, 1];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == 1) {
                for (let k = 0; k < 2; ++k) {
                    const x = i + dirs[k];
                    const y = j + dirs[k + 1];
                    if (
                        x >= 0 &&
                        x < m &&
                        y >= 0 &&
                        y < n &&
                        grid[x][y] == 1 &&
                        find(x * n + y) != find(i * n + j)
                    ) {
                        size[find(x * n + y)] += size[find(i * n + j)];
                        p[find(i * n + j)] = find(x * n + y);
                    }
                }
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == 1) {
                ans = Math.max(ans, size[i * n + j]);
            }
        }
    }
    return ans;
}
```

### **C++**

DFS:

```cpp
class Solution {
public:
    int m;
    int n;

    int maxAreaOfIsland(vector<vector<int>>& grid) {
        m = grid.size();
        n = grid[0].size();
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 1)
                    ans = max(ans, dfs(i, j, grid));
        return ans;
    }

    int dfs(int i, int j, vector<vector<int>>& grid) {
        grid[i][j] = 0;
        int ans = 1;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1)
                ans += dfs(x, y, grid);
        }
        return ans;
    }
};
```

Union find:

```cpp
class Solution {
public:
    vector<int> p;
    vector<int> size;

    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        p.resize(m * n);
        size.resize(m * n, 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<int> dirs = {0, 1, 0};
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (grid[i][j])
                {
                    for (int k = 0; k < 2; ++k)
                    {
                        int x = i + dirs[k];
                        int y = j + dirs[k + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] && find(i * n + j) != find(x * n + y))
                        {
                            size[find(x * n + y)] += size[find(i * n + j)];
                            p[find(i * n + j)] = find(x * n + y);
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j])
                    ans = max(ans, size[i * n + j]);
        return ans;
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
func maxAreaOfIsland(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dirs := []int{-1, 0, 1, 0, -1}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		grid[i][j] = 0
		ans := 1
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
				ans += dfs(x, y)
			}
		}
		return ans
	}
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				ans = max(ans, dfs(i, j))
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

Union find:

```go
func maxAreaOfIsland(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	p := make([]int, m*n)
	size := make([]int, m*n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	dirs := []int{1, 0, 1}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				for k := 0; k < 2; k++ {
					x, y := i+dirs[k], j+dirs[k+1]
					if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 && find(i*n+j) != find(x*n+y) {
						size[find(x*n+y)] += size[find(i*n+j)]
						p[find(i*n+j)] = find(x*n + y)
					}
				}
			}
		}
	}
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				ans = max(ans, size[i*n+j])
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **Rust**

DFS：

```rust
impl Solution {
    fn dfs(grid: &mut Vec<Vec<i32>>, i: usize, j: usize) -> i32 {
        if i == grid.len() || j == grid[0].len() || grid[i][j] == 0 {
            return 0;
        }
        grid[i][j] = 0;
        let mut res = 1 + Self::dfs(grid, i + 1, j) + Self::dfs(grid, i, j + 1);
        if i != 0 {
            res += Self::dfs(grid, i - 1, j)
        }
        if j != 0 {
            res += Self::dfs(grid, i, j - 1)
        }
        res
    }

    pub fn max_area_of_island(mut grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut res = 0;
        for i in 0..m {
            for j in 0..n {
                res = res.max(Self::dfs(&mut grid, i, j))
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
