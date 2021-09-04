# [803. 打砖块](https://leetcode-cn.com/problems/bricks-falling-when-hit)

[English Version](/solution/0800-0899/0803.Bricks%20Falling%20When%20Hit/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个 <code>m x n</code> 的二元网格，其中 <code>1</code> 表示砖块，<code>0</code> 表示空白。砖块 <strong>稳定</strong>（不会掉落）的前提是：</p>

<ul>
	<li>一块砖直接连接到网格的顶部，或者</li>
	<li>至少有一块相邻（4 个方向之一）砖块<strong> 稳定 </strong>不会掉落时</li>
</ul>

<p>给你一个数组 <code>hits</code> ，这是需要依次消除砖块的位置。每当消除 <code>hits[i] = (rowi, coli)</code> 位置上的砖块时，对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而掉落。一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。</p>

<p>返回一个数组 <code>result</code> ，其中 <code>result[i]</code> 表示第 <code>i</code> 次消除操作对应掉落的砖块数目。</p>

<p><strong>注意</strong>，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
<strong>输出：</strong>[2]
<strong>解释：</strong>
网格开始为：
[[1,0,0,0]，
 [<strong>1</strong>,1,1,0]]
消除 (1,0) 处加粗的砖块，得到网格：
[[1,0,0,0]
 [0,<strong>1</strong>,<strong>1</strong>,0]]
两个加粗的砖不再稳定，因为它们不再与顶部相连，也不再与另一个稳定的砖相邻，因此它们将掉落。得到网格：
[[1,0,0,0],
 [0,0,0,0]]
因此，结果为 [2] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
<strong>输出：</strong>[0,0]
<strong>解释：</strong>
网格开始为：
[[1,0,0,0],
 [1,<strong>1</strong>,0,0]]
消除 (1,1) 处加粗的砖块，得到网格：
[[1,0,0,0],
 [1,0,0,0]]
剩下的砖都很稳定，所以不会掉落。网格保持不变：
[[1,0,0,0], 
 [<strong>1</strong>,0,0,0]]
接下来消除 (1,0) 处加粗的砖块，得到网格：
[[1,0,0,0],
 [0,0,0,0]]
剩下的砖块仍然是稳定的，所以不会有砖块掉落。
因此，结果为 [0,0] 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 <= m, n <= 200</code></li>
	<li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
	<li><code>1 <= hits.length <= 4 * 10<sup>4</sup></code></li>
	<li><code>hits[i].length == 2</code></li>
	<li><code>0 <= x<sub>i </sub><= m - 1</code></li>
	<li><code>0 <= y<sub>i</sub> <= n - 1</code></li>
	<li>所有 <code>(x<sub>i</sub>, y<sub>i</sub>)</code> 互不相同</li>
</ul>

## 解法

逆向并查集，此过程中用 size 数组维护每个连通分量的大小。

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
