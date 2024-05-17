---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1254.Number%20of%20Closed%20Islands/README_EN.md
rating: 1658
source: Weekly Contest 162 Q3
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Array
    - Matrix
---

<!-- problem:start -->

# [1254. Number of Closed Islands](https://leetcode.com/problems/number-of-closed-islands)

[中文文档](/solution/1200-1299/1254.Number%20of%20Closed%20Islands/README.md)

## Description

<!-- description:start -->

<p>Given a 2D&nbsp;<code>grid</code> consists of <code>0s</code> (land)&nbsp;and <code>1s</code> (water).&nbsp; An <em>island</em> is a maximal 4-directionally connected group of <code><font face="monospace">0</font>s</code> and a <em>closed island</em>&nbsp;is an island <strong>totally</strong>&nbsp;(all left, top, right, bottom) surrounded by <code>1s.</code></p>

<p>Return the number of <em>closed islands</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1254.Number%20of%20Closed%20Islands/images/sample_3_1610.png" style="width: 240px; height: 120px;" /></p>

<pre>
<strong>Input:</strong> grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
Islands in gray are closed because they are completely surrounded by water (group of 1s).</pre>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1254.Number%20of%20Closed%20Islands/images/sample_4_1610.png" style="width: 160px; height: 80px;" /></p>

<pre>
<strong>Input:</strong> grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1,1,1,1,1,1],
&nbsp;              [1,0,0,0,0,0,1],
&nbsp;              [1,0,1,1,1,0,1],
&nbsp;              [1,0,1,0,1,0,1],
&nbsp;              [1,0,1,1,1,0,1],
&nbsp;              [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= grid.length, grid[0].length &lt;= 100</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;=1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

We traverse the matrix, and for each piece of land, we perform a depth-first search to find all the land connected to it. Then we check if there is any land on the boundary. If there is, it is not a closed island; otherwise, it is a closed island, and we increment the answer by one.

Finally, we return the answer.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Where $m$ and $n$ are the number of rows and columns in the matrix, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def closedIsland(self, grid: List[List[int]]) -> int:
        def dfs(i: int, j: int) -> int:
            res = int(0 < i < m - 1 and 0 < j < n - 1)
            grid[i][j] = 1
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y] == 0:
                    res &= dfs(x, y)
            return res

        m, n = len(grid), len(grid[0])
        dirs = (-1, 0, 1, 0, -1)
        return sum(grid[i][j] == 0 and dfs(i, j) for i in range(m) for j in range(n))
```

#### Java

```java
class Solution {
    private int m;
    private int n;
    private int[][] grid;

    public int closedIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    ans += dfs(i, j);
                }
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        int res = i > 0 && i < m - 1 && j > 0 && j < n - 1 ? 1 : 0;
        grid[i][j] = 1;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0) {
                res &= dfs(x, y);
            }
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int closedIsland(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            int res = i > 0 && i < m - 1 && j > 0 && j < n - 1 ? 1 : 0;
            grid[i][j] = 1;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0) {
                    res &= dfs(x, y);
                }
            }
            return res;
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans += grid[i][j] == 0 && dfs(i, j);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func closedIsland(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	dirs := [5]int{-1, 0, 1, 0, -1}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		res := 1
		if i == 0 || i == m-1 || j == 0 || j == n-1 {
			res = 0
		}
		grid[i][j] = 1
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0 {
				res &= dfs(x, y)
			}
		}
		return res
	}
	for i, row := range grid {
		for j, v := range row {
			if v == 0 {
				ans += dfs(i, j)
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function closedIsland(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number): number => {
        let res = i > 0 && j > 0 && i < m - 1 && j < n - 1 ? 1 : 0;
        grid[i][j] = 1;
        for (let k = 0; k < 4; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] === 0) {
                res &= dfs(x, y);
            }
        }
        return res;
    };
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 0) {
                ans += dfs(i, j);
            }
        }
    }
    return ans;
}
```

#### C#

```cs
public class Solution {
    private int m;
    private int n;
    private int[][] grid;

    public int ClosedIsland(int[][] grid) {
        m = grid.Length;
        n = grid[0].Length;
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    ans += dfs(i, j);
                }
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        int res = i > 0 && i < m - 1 && j > 0 && j < n - 1 ? 1 : 0;
        grid[i][j] = 1;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0) {
                res &= dfs(x, y);
            }
        }
        return res;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Union-Find

We can use a union-find set to maintain each piece of connected land.

We traverse the matrix, if the current position is on the boundary, we connect it with the virtual node $m \times n$. If the current position is land, we connect it with the land below and to the right.

Then, we traverse the matrix again, for each piece of land, if its root node is itself, we increment the answer by one.

Finally, we return the answer.

The time complexity is $O(m \times n \times \alpha(m \times n))$, and the space complexity is $O(m \times n)$. Where $m$ and $n$ are the number of rows and columns in the matrix, respectively.

<!-- tabs:start -->

#### Python3

```python
class UnionFind:
    def __init__(self, n: int):
        self.p = list(range(n))
        self.size = [1] * n

    def find(self, x: int) -> int:
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]

    def union(self, a: int, b: int):
        pa, pb = self.find(a), self.find(b)
        if pa != pb:
            if self.size[pa] > self.size[pb]:
                self.p[pb] = pa
                self.size[pa] += self.size[pb]
            else:
                self.p[pa] = pb
                self.size[pb] += self.size[pa]


class Solution:
    def closedIsland(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        uf = UnionFind(m * n + 1)
        for i in range(m):
            for j in range(n):
                if i == 0 or i == m - 1 or j == 0 or j == n - 1:
                    uf.union(i * n + j, m * n)
                if grid[i][j] == 0:
                    if i < m - 1 and grid[i + 1][j] == 0:
                        uf.union(i * n + j, (i + 1) * n + j)
                    if j < n - 1 and grid[i][j + 1] == 0:
                        uf.union(i * n + j, i * n + j + 1)
        ans = 0
        for i in range(m):
            for j in range(n):
                ans += grid[i][j] == 0 and uf.find(i * n + j) == i * n + j
        return ans
```

#### Java

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
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(m * n + 1);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    uf.union(i * n + j, m * n);
                }
                if (grid[i][j] == 0) {
                    if (i + 1 < m && grid[i + 1][j] == 0) {
                        uf.union(i * n + j, (i + 1) * n + j);
                    }
                    if (j + 1 < n && grid[i][j + 1] == 0) {
                        uf.union(i * n + j, i * n + j + 1);
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0 && uf.find(i * n + j) == i * n + j) {
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
    int closedIsland(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        UnionFind uf(m * n + 1);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    uf.unite(i * n + j, m * n);
                }
                if (grid[i][j] == 0) {
                    if (i + 1 < m && grid[i + 1][j] == 0) {
                        uf.unite(i * n + j, (i + 1) * n + j);
                    }
                    if (j + 1 < n && grid[i][j + 1] == 0) {
                        uf.unite(i * n + j, i * n + j + 1);
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans += grid[i][j] == 0 && uf.find(i * n + j) == i * n + j;
            }
        }
        return ans;
    }
};
```

#### Go

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

func closedIsland(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	uf := newUnionFind(m*n + 1)
	for i, row := range grid {
		for j, v := range row {
			if i == 0 || i == m-1 || j == 0 || j == n-1 {
				uf.union(i*n+j, m*n)
			}
			if v == 0 {
				if i+1 < m && grid[i+1][j] == 0 {
					uf.union(i*n+j, (i+1)*n+j)
				}
				if j+1 < n && grid[i][j+1] == 0 {
					uf.union(i*n+j, i*n+j+1)
				}
			}
		}
	}
	for i, row := range grid {
		for j, v := range row {
			if v == 0 && uf.find(i*n+j) == i*n+j {
				ans++
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function closedIsland(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const uf = new UnionFind(m * n + 1);
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i === 0 || i === m - 1 || j === 0 || j === n - 1) {
                uf.union(i * n + j, m * n);
            }
            if (grid[i][j] === 0) {
                if (i + 1 < m && grid[i + 1][j] === 0) {
                    uf.union(i * n + j, (i + 1) * n + j);
                }
                if (j + 1 < n && grid[i][j + 1] === 0) {
                    uf.union(i * n + j, i * n + j + 1);
                }
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 0 && uf.find(i * n + j) === i * n + j) {
                ++ans;
            }
        }
    }
    return ans;
}

class UnionFind {
    private p: number[];
    private size: number[];

    constructor(n: number) {
        this.p = Array(n)
            .fill(0)
            .map((_, i) => i);
        this.size = Array(n).fill(1);
    }

    find(x: number): number {
        if (this.p[x] !== x) {
            this.p[x] = this.find(this.p[x]);
        }
        return this.p[x];
    }

    union(a: number, b: number): void {
        const [pa, pb] = [this.find(a), this.find(b)];
        if (pa === pb) {
            return;
        }
        if (this.size[pa] > this.size[pb]) {
            this.p[pb] = pa;
            this.size[pa] += this.size[pb];
        } else {
            this.p[pa] = pb;
            this.size[pb] += this.size[pa];
        }
    }
}
```

#### C#

```cs
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

public class Solution {
    public int ClosedIsland(int[][] grid) {
        int m = grid.Length, n = grid[0].Length;
        UnionFind uf = new UnionFind(m * n + 1);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    uf.union(i * n + j, m * n);
                }
                if (grid[i][j] == 0) {
                    if (i + 1 < m && grid[i + 1][j] == 0) {
                        uf.union(i * n + j, (i + 1) * n + j);
                    }
                    if (j + 1 < n && grid[i][j + 1] == 0) {
                        uf.union(i * n + j, i * n + j + 1);
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0 && uf.find(i * n + j) == i * n + j) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
