# [695. Max Area of Island](https://leetcode.com/problems/max-area-of-island)

[中文文档](/solution/0600-0699/0695.Max%20Area%20of%20Island/README.md)

## Description

<p>Given a non-empty 2D array <code>grid</code> of 0&#39;s and 1&#39;s, an <b>island</b> is a group of <code>1</code>&#39;s (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.</p>



<p>Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)</p>



<p><b>Example 1:</b></p>



<pre>

[[0,0,1,0,0,0,0,1,0,0,0,0,0],

 [0,0,0,0,0,0,0,1,1,1,0,0,0],

 [0,1,1,0,1,0,0,0,0,0,0,0,0],

 [0,1,0,0,1,1,0,0,<b>1</b>,0,<b>1</b>,0,0],

 [0,1,0,0,1,1,0,0,<b>1</b>,<b>1</b>,<b>1</b>,0,0],

 [0,0,0,0,0,0,0,0,0,0,<b>1</b>,0,0],

 [0,0,0,0,0,0,0,1,1,1,0,0,0],

 [0,0,0,0,0,0,0,1,1,0,0,0,0]]

</pre>

Given the above grid, return <code>6</code>. Note the answer is not 11, because the island must be connected 4-directionally.



<p><b>Example 2:</b></p>



<pre>

[[0,0,0,0,0,0,0,0]]</pre>

Given the above grid, return <code>0</code>.



<p><b>Note:</b> The length of each dimension in the given <code>grid</code> does not exceed 50.</p>



## Solutions

<!-- tabs:start -->

### **Python3**

DFS:

```python
class Solution:
    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        def dfs(grid, i, j, m, n):
            if i < 0 or i >= m or j < 0 or j >= n or grid[i][j] == 0:
                return 0
            grid[i][j] = 0
            res = 1
            for x, y in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                res += dfs(grid, i + x, j + y, m, n)
            return res
        
        m, n = len(grid), len(grid[0])
        res = 0
        for i in range(m):
            for j in range(n):
                t = dfs(grid, i, j, m, n)
                res = max(res, t)
        return res
```

Union find:

```python
class Solution:
    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        p = list(range(m * n))
        size = [1] * (m * n)
        
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]
        
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    if i < m - 1 and grid[i + 1][j] == 1:
                        a, b = find(i * n + j), find((i + 1) * n + j)
                        if a != b:
                            size[a] += size[b]
                        p[b] = a
                    if j < n - 1 and grid[i][j + 1] == 1:
                        a, b = find(i * n + j), find(i * n + j + 1)
                        if a != b:
                            size[a] += size[b]
                        p[b] = a

        res = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    res = max(res, size[i * n + j])
        return res
```

### **Java**

DFS:

```java
class Solution {
    private int[][] directions = {{0, 1}, {0, - 1}, {1, 0}, {-1, 0}};

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int t = dfs(grid, i, j, m, n);
                res = Math.max(res, t);
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        int res = 1;
        for (int[] direction : directions) {
            res += dfs(grid, i + direction[0], j + direction[1], m, n);
        }
        return res;
    }
}
```

Union find:

```java
class Solution {
    private int[] p;
    private int[] size;

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        p = new int[m * n];
        size = new int[m * n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                p[i * n + j] = i * n + j;
                size[i * n + j] = 1;
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    if (i < m - 1 && grid[i + 1][j] == 1) {
                        int a = find(i * n + j), b = find((i + 1) * n + j);
                        if (a != b) {
                            size[a] += size[b];
                        }
                        p[b] = a;
                    }
                    if (j < n - 1 && grid[i][j + 1] == 1) {
                        int a = find(i * n + j), b = find(i * n + j + 1);
                        if (a != b) {
                            size[a] += size[b];
                        }
                        p[b] = a;
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, size[i * n + j]);
                }
            }
        }
        return res;
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
    let m = grid.length, n = grid[0].length;
    let res = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == 1) {
                res = Math.max(dfs(grid, i, j), res);
            }
        }
    }
    return res;
};

function dfs(grid: number[][], i: number, j: number): number {
    let m = grid.length, n = grid[0].length;
    if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || grid[i][j] == 0) {
        return 0;
    }
    grid[i][j] = 0;
    let res = 1;
    for (let [dx, dy] of [[0, 1], [0, -1], [1, 0], [-1, 0]]) {
        res += dfs(grid, i + dx, j + dy);
    }
    return res;
}
```

### **C++**

DFS:

```cpp
class Solution {
public:
    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int t = dfs(grid, i, j, m, n);
                res = max(res, t);
            }
        }
        return res;
    }
private:
    vector<vector<int>> directions = {{0, 1}, {0, - 1}, {1, 0}, {-1, 0}};

    int dfs(vector<vector<int>>& grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        int res = 1;
        for (auto direction : directions) {
            res += dfs(grid, i + direction[0], j + direction[1], m, n);
        }
        return res;
    }

};
```

Union find:

```cpp
class Solution {
public:
    vector<int> p;

    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<int> size(m * n, 1);
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                p.push_back(i * n + j);
            }
        }
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (grid[i][j] == 1)
                {
                    if (i < m - 1 && grid[i + 1][j] == 1)
                    {
                        int a = find(i * n + j), b = find((i + 1) * n + j);
                        if (a != b)
                        {
                            size[a] += size[b];
                        }
                        p[b] = a;
                    }
                    if (j < n - 1 && grid[i][j + 1] == 1)
                    {
                        int a = find(i * n + j), b = find(i * n + j + 1);
                        if (a != b)
                        {
                            size[a] += size[b];
                        }
                        p[b] = a;
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (grid[i][j] == 1)
                {
                    res = max(res, size[i * n + j]);
                }
            }
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x)
        {
            p[x] = find(p[x]);
        }
        return p[x];
    }
};
```

### **Go**

Union find:

```go
var p []int

func maxAreaOfIsland(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	p = make([]int, m*n)
	size := make([]int, m*n)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			p[i*n+j] = i*n + j
			size[i*n+j] = 1
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				if i < m-1 && grid[i+1][j] == 1 {
					a, b := find(i*n+j), find((i+1)*n+j)
					if a != b {
						size[a] += size[b]
					}
					p[b] = a
				}
				if j < n-1 && grid[i][j+1] == 1 {
					a, b := find(i*n+j), find(i*n+j+1)
					if a != b {
						size[a] += size[b]
					}
					p[b] = a
				}
			}
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				res = max(res, size[i*n+j])
			}
		}
	}
	return res
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
