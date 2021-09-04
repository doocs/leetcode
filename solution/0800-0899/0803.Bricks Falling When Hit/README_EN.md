# [803. Bricks Falling When Hit](https://leetcode.com/problems/bricks-falling-when-hit)

[中文文档](/solution/0800-0899/0803.Bricks%20Falling%20When%20Hit/README.md)

## Description

<p>You are given an <code>m x n</code> binary <code>grid</code>, where each <code>1</code> represents a brick and <code>0</code> represents an empty space. A brick is <strong>stable</strong> if:</p>

<ul>
	<li>It is directly connected to the top of the grid, or</li>
	<li>At least one other brick in its four adjacent cells is <strong>stable</strong>.</li>
</ul>

<p>You are also given an array <code>hits</code>, which is a sequence of erasures we want to apply. Each time we want to erase the brick at the location <code>hits[i] = (row<sub>i</sub>, col<sub>i</sub>)</code>. The brick on that location&nbsp;(if it exists) will disappear. Some other bricks may no longer be stable because of that erasure and will <strong>fall</strong>. Once a brick falls, it is <strong>immediately</strong> erased from the <code>grid</code> (i.e., it does not land on other stable bricks).</p>

<p>Return <em>an array </em><code>result</code><em>, where each </em><code>result[i]</code><em> is the number of bricks that will <strong>fall</strong> after the </em><code>i<sup>th</sup></code><em> erasure is applied.</em></p>

<p><strong>Note</strong> that an erasure may refer to a location with no brick, and if it does, no bricks drop.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
<strong>Output:</strong> [2]
<strong>Explanation: </strong>Starting with the grid:
[[1,0,0,0],
 [<u>1</u>,1,1,0]]
We erase the underlined brick at (1,0), resulting in the grid:
[[1,0,0,0],
 [0,<u>1</u>,<u>1</u>,0]]
The two underlined bricks are no longer stable as they are no longer connected to the top nor adjacent to another stable brick, so they will fall. The resulting grid is:
[[1,0,0,0],
 [0,0,0,0]]
Hence the result is [2].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
<strong>Output:</strong> [0,0]
<strong>Explanation: </strong>Starting with the grid:
[[1,0,0,0],
 [1,<u>1</u>,0,0]]
We erase the underlined brick at (1,1), resulting in the grid:
[[1,0,0,0],
 [1,0,0,0]]
All remaining bricks are still stable, so no bricks fall. The grid remains the same:
[[1,0,0,0],
 [<u>1</u>,0,0,0]]
Next, we erase the underlined brick at (1,0), resulting in the grid:
[[1,0,0,0],
 [0,0,0,0]]
Once again, all remaining bricks are still stable, so no bricks fall.
Hence the result is [0,0].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>grid[i][j]</code> is <code>0</code> or <code>1</code>.</li>
	<li><code>1 &lt;= hits.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>hits[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i&nbsp;</sub>&lt;= m - 1</code></li>
	<li><code>0 &lt;=&nbsp;y<sub>i</sub> &lt;= n - 1</code></li>
	<li>All <code>(x<sub>i</sub>, y<sub>i</sub>)</code> are unique.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def hitBricks(self, grid: List[List[int]], hits: List[List[int]]) -> List[int]:
        m, n = len(grid), len(grid[0])
        p = list(range(m * n + 1))
        size = [1] * len(p)
        g = [[grid[i][j] for j in range(n)] for i in range(m)]

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa != pb:
                size[pb] += size[pa]
                p[pa] = pb

        def check(i, j):
            return 0 <= i < m and 0 <= j < n and g[i][j] == 1

        for i, j in hits:
            g[i][j] = 0

        for j in range(n):
            if g[0][j] == 1:
                union(j, m * n)

        for i in range(1, m):
            for j in range(n):
                if g[i][j] == 0:
                    continue
                if g[i - 1][j] == 1:
                    union(i * n + j, (i - 1) * n + j)
                if j > 0 and g[i][j - 1] == 1:
                    union(i * n + j, i * n + j - 1)

        res = []
        for i, j in hits[::-1]:
            if grid[i][j] == 0:
                res.append(0)
                continue
            origin = size[find(m * n)]
            if i == 0:
                union(j, m * n)
            for x, y in [(-1, 0), (1, 0), (0, 1), (0, -1)]:
                if check(i + x, j + y):
                    union(i * n + j, (i + x) * n + (j + y))
            cur = size[find(m * n)]
            res.append(max(0, cur - origin - 1))
            g[i][j] = 1
        return res[::-1]
```

### **Java**

```java
class Solution {
    private int[] p;
    private int[] size;
    private int[][] g;
    private int m;
    private int n;
    private int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int[] hitBricks(int[][] grid, int[][] hits) {
        m = grid.length;
        n = grid[0].length;
        p = new int[m * n + 1];
        size = new int[m * n + 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        g = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                g[i][j] = grid[i][j];
            }
        }
        for (int[] e : hits) {
            g[e[0]][e[1]] = 0;
        }
        for (int j = 0; j < n; ++j) {
            if (g[0][j] == 1) {
                union(j, m * n);
            }
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (g[i][j] == 0) {
                    continue;
                }
                if (g[i - 1][j] == 1) {
                    union(i * n + j, (i - 1) * n + j);
                }
                if (j > 0 && g[i][j - 1] == 1) {
                    union(i * n + j, i * n + j - 1);
                }
            }
        }
        int[] res = new int[hits.length];
        for (int k = hits.length - 1; k >= 0; --k) {
            int i = hits[k][0], j = hits[k][1];
            if (grid[i][j] == 0) {
                continue;
            }
            int origin = size[find(m * n)];
            if (i == 0) {
                union(j, m * n);
            }
            for (int[] e : dirs) {
                if (check(i + e[0], j + e[1])) {
                    union(i * n + j, (i + e[0]) * n + j + e[1]);
                }
            }
            int cur = size[find(m * n)];
            res[k] = Math.max(0, cur - origin - 1);
            g[i][j] = 1;
        }
        return res;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            size[pb] += size[pa];
            p[pa] = pb;
        }
    }

    private boolean check(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n && g[i][j] == 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;
    vector<int> size;
    vector<vector<int>> g;
    int m;
    int n;
    int dirs[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    vector<int> hitBricks(vector<vector<int>>& grid, vector<vector<int>>& hits) {
        m = grid.size();
        n = grid[0].size();
        for (int i = 0; i < m * n + 1; ++i)
        {
            p.push_back(i);
            size.push_back(1);
        }
        g = grid;
        for (auto e : hits)
            g[e[0]][e[1]] = 0;
        for (int j = 0; j < n; ++j)
            if (g[0][j] == 1)
                merge(j, m * n);
        for (int i = 1; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (g[i][j] == 0) continue;
                if (g[i - 1][j] == 1) merge(i * n + j, (i - 1) * n + j);
                if (j > 0 && g[i][j - 1] == 1) merge(i * n + j, i * n + j - 1);
            }
        }
        vector<int> res(hits.size());
        for (int k = hits.size() - 1; k >= 0; --k)
        {
            int i = hits[k][0], j = hits[k][1];
            if (grid[i][j] == 0) continue;
            int origin = size[find(m * n)];
            if (i == 0)
                merge(j, m * n);
            for (auto dir : dirs)
                if (check(i + dir[0], j + dir[1]))
                    merge(i * n + j, ((i + dir[0]) * n + j + dir[1]));
            int cur = size[find(m * n)];
            res[k] = max(0, cur - origin - 1);
            g[i][j] = 1;
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void merge(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb)
        {
            size[pb] += size[pa];
            p[pa] = pb;
        }
    }

    bool check(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n && g[i][j] == 1;
    }
};
```

### **Go**

```go
var p []int
var size []int
var g [][]int
var m int
var n int

func hitBricks(grid [][]int, hits [][]int) []int {
	m, n = len(grid), len(grid[0])
	p = make([]int, m*n+1)
	size = make([]int, m*n+1)
	for i := 0; i < len(p); i++ {
		p[i] = i
		size[i] = 1
	}
	g = make([][]int, m)
	for i := 0; i < m; i++ {
		g[i] = make([]int, n)
		for j := 0; j < n; j++ {
			g[i][j] = grid[i][j]
		}
	}
	for _, e := range hits {
		g[e[0]][e[1]] = 0
	}
	for j := 0; j < n; j++ {
		if g[0][j] == 1 {
			union(j, m*n)
		}
	}
	for i := 1; i < m; i++ {
		for j := 0; j < n; j++ {
			if g[i][j] == 0 {
				continue
			}
			if g[i-1][j] == 1 {
				union(i*n+j, (i-1)*n+j)
			}
			if j > 0 && g[i][j-1] == 1 {
				union(i*n+j, i*n+j-1)
			}
		}
	}

	res := make([]int, len(hits))
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for k := len(hits) - 1; k >= 0; k-- {
		i, j := hits[k][0], hits[k][1]
		if grid[i][j] == 0 {
			continue
		}
		origin := size[find(m*n)]
		if i == 0 {
			union(j, m*n)
		}
		for _, dir := range dirs {
			if check(i+dir[0], j+dir[1]) {
				union(i*n+j, (i+dir[0])*n+j+dir[1])
			}
		}
		cur := size[find(m*n)]
		res[k] = max(0, cur-origin-1)
		g[i][j] = 1
	}
	return res
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}

func union(a, b int) {
	pa, pb := find(a), find(b)
	if pa != pb {
		size[pb] += size[pa]
		p[pa] = pb
	}
}

func check(i, j int) bool {
	return i >= 0 && i < m && j >= 0 && j < n && g[i][j] == 1
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
