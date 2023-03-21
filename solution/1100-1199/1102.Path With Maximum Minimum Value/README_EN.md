# [1102. Path With Maximum Minimum Value](https://leetcode.com/problems/path-with-maximum-minimum-value)

[中文文档](/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/README.md)

## Description

<p>Given an <code>m x n</code> integer matrix <code>grid</code>, return <em>the maximum <strong>score</strong> of a path starting at </em><code>(0, 0)</code><em> and ending at </em><code>(m - 1, n - 1)</code> moving in the 4 cardinal directions.</p>

<p>The <strong>score</strong> of a path is the minimum value in that path.</p>

<ul>
	<li>For example, the score of the path <code>8 &rarr; 4 &rarr; 5 &rarr; 9</code> is <code>4</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/images/maxgrid1.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> grid = [[5,4,5],[1,2,6],[7,4,6]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The path with the maximum score is highlighted in yellow. 
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/images/maxgrid2.jpg" style="width: 484px; height: 165px;" />
<pre>
<strong>Input:</strong> grid = [[2,2,1,2,2,2],[1,2,2,2,1,2]]
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/images/maxgrid3.jpg" style="width: 404px; height: 485px;" />
<pre>
<strong>Input:</strong> grid = [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumMinimumPath(self, grid: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        m, n = len(grid), len(grid[0])
        p = list(range(m * n))
        q = [(v, i, j) for i, row in enumerate(grid) for j, v in enumerate(row)]
        q.sort()
        ans = 0
        vis = set()
        dirs = (-1, 0, 1, 0, -1)
        while find(0) != find(m * n - 1):
            v, i, j = q.pop()
            ans = v
            vis.add((i, j))
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and (x, y) in vis:
                    p[find(x * n + y)] = find(i * n + j)
        return ans
```

```python
class UnionFind:
    def __init__(self, n):
        self.p = list(range(n))
        self.size = [1] * n

    def find(self, x):
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]

    def union(self, a, b):
        pa, pb = self.find(a), self.find(b)
        if pa != pb:
            if self.size[pa] > self.size[pb]:
                self.p[pb] = pa
                self.size[pa] += self.size[pb]
            else:
                self.p[pa] = pb
                self.size[pb] += self.size[pa]

class Solution:
    def maximumMinimumPath(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        uf = UnionFind(m * n)
        q = [(v, i, j) for i, row in enumerate(grid) for j, v in enumerate(row)]
        q.sort()
        ans = 0
        vis = set()
        dirs = (-1, 0, 1, 0, -1)
        while uf.find(0) != uf.find(m * n - 1):
            v, i, j = q.pop()
            ans = v
            vis.add((i, j))
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and (x, y) in vis:
                    uf.union(x * n + y, i * n + j)
        return ans
```

### **Java**

```java
class Solution {
    private int[] p;

    public int maximumMinimumPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        p = new int[m * n];
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                q.add(new int[] {grid[i][j], i, j});
                p[i * n + j] = i * n + j;
            }
        }
        q.sort((a, b) -> b[0] - a[0]);
        boolean[][] vis = new boolean[m][n];
        int[] dirs = {-1, 0, 1, 0, -1};
        int ans = 0;
        for (int i = 0; find(0) != find(m * n - 1); ++i) {
            int[] t = q.get(i);
            vis[t[1]][t[2]] = true;
            ans = t[0];
            for (int k = 0; k < 4; ++k) {
                int x = t[1] + dirs[k], y = t[2] + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && vis[x][y]) {
                    p[find(x * n + y)] = find(t[1] * n + t[2]);
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

```java
class UnionFind {
    private int[] p;
    private int[] size;

    public UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            if (size[pa] > size[pb]) {
                p[pb] = pa;
                size[pa] += size[pb];
            } else {
                p[pa] = pb;
                size[pb] += size[pa];
            }
        }
    }
}

class Solution {
    public int maximumMinimumPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(m * n);
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                q.add(new int[] {grid[i][j], i, j});
            }
        }
        q.sort((a, b) -> b[0] - a[0]);
        boolean[][] vis = new boolean[m][n];
        int[] dirs = {-1, 0, 1, 0, -1};
        int ans = 0;
        for (int i = 0; uf.find(0) != uf.find(m * n - 1); ++i) {
            int[] t = q.get(i);
            vis[t[1]][t[2]] = true;
            ans = t[0];
            for (int k = 0; k < 4; ++k) {
                int x = t[1] + dirs[k], y = t[2] + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && vis[x][y]) {
                    uf.union(x * n + y, t[1] * n + t[2]);
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumMinimumPath(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<tuple<int, int, int>> q;
        vector<int> p(m * n);
        iota(p.begin(), p.end(), 0);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                q.emplace_back(grid[i][j], i, j);
            }
        }
        function<int(int)> find = [&](int x) {
            return p[x] == x ? x : p[x] = find(p[x]);
        };
        sort(q.begin(), q.end(), greater<tuple<int, int, int>>());
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        bool vis[m][n];
        memset(vis, false, sizeof(vis));
        for (auto& [v, i, j] : q) {
            vis[i][j] = true;
            ans = v;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && vis[x][y]) {
                    p[find(x * n + y)] = find(i * n + j);
                }
            }
            if (find(0) == find(m * n - 1)) {
                break;
            }
        }
        return ans;
    }
};
```

```cpp
class UnionFind {
public:
    UnionFind(int n) {
        p = vector<int>(n);
        size = vector<int>(n, 1);
        iota(p.begin(), p.end(), 0);
    }

    void unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            if (size[pa] > size[pb]) {
                p[pb] = pa;
                size[pa] += size[pb];
            } else {
                p[pa] = pb;
                size[pb] += size[pa];
            }
        }
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

private:
    vector<int> p, size;
};

class Solution {
public:
    int maximumMinimumPath(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<tuple<int, int, int>> q;
        UnionFind uf(m * n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                q.emplace_back(grid[i][j], i, j);
            }
        }
        sort(q.begin(), q.end(), greater<tuple<int, int, int>>());
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        bool vis[m][n];
        memset(vis, false, sizeof(vis));
        for (auto& [v, i, j] : q) {
            vis[i][j] = true;
            ans = v;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && vis[x][y]) {
                    uf.unite(x * n + y, i * n + j);
                }
            }
            if (uf.find(0) == uf.find(m * n - 1)) {
                break;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumMinimumPath(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	p := make([]int, m*n)
	vis := make([][]bool, m)
	q := [][3]int{}
	for i, row := range grid {
		vis[i] = make([]bool, n)
		for j, v := range row {
			p[i*n+j] = i*n + j
			q = append(q, [3]int{v, i, j})
		}
	}
	sort.Slice(q, func(i, j int) bool { return q[i][0] > q[j][0] })
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	for _, t := range q {
		v, i, j := t[0], t[1], t[2]
		ans = v
		vis[i][j] = true
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if 0 <= x && x < m && 0 <= y && y < n && vis[x][y] {
				p[find(x*n+y)] = find(i*n + j)
			}
		}
		if find(0) == find(m*n-1) {
			break
		}
	}
	return
}
```

```go
type unionFind struct {
	p, size []int
}

func newUnionFind(n int) *unionFind {
	p := make([]int, n)
	size := make([]int, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	return &unionFind{p, size}
}

func (uf *unionFind) find(x int) int {
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *unionFind) union(a, b int) {
	pa, pb := uf.find(a), uf.find(b)
	if pa != pb {
		if uf.size[pa] > uf.size[pb] {
			uf.p[pb] = pa
			uf.size[pa] += uf.size[pb]
		} else {
			uf.p[pa] = pb
			uf.size[pb] += uf.size[pa]
		}
	}
}

func maximumMinimumPath(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	uf := newUnionFind(m * n)
	vis := make([][]bool, m)
	q := [][3]int{}
	for i, row := range grid {
		vis[i] = make([]bool, n)
		for j, v := range row {
			q = append(q, [3]int{v, i, j})
		}
	}
	sort.Slice(q, func(i, j int) bool { return q[i][0] > q[j][0] })
	dirs := [5]int{-1, 0, 1, 0, -1}
	for _, t := range q {
		v, i, j := t[0], t[1], t[2]
		ans = v
		vis[i][j] = true
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if 0 <= x && x < m && 0 <= y && y < n && vis[x][y] {
				uf.union(x*n+y, i*n+j)
			}
		}
		if uf.find(0) == uf.find(m*n-1) {
			break
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
