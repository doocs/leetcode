# [827. Making A Large Island](https://leetcode.com/problems/making-a-large-island)

[中文文档](/solution/0800-0899/0827.Making%20A%20Large%20Island/README.md)

## Description

<p>You are given an <code>n x n</code> binary matrix <code>grid</code>. You are allowed to change <strong>at most one</strong> <code>0</code> to be <code>1</code>.</p>

<p>Return <em>the size of the largest <strong>island</strong> in</em> <code>grid</code> <em>after applying this operation</em>.</p>

<p>An <strong>island</strong> is a 4-directionally connected group of <code>1</code>s.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> grid = [[1,0],[0,1]]

<strong>Output:</strong> 3

<strong>Explanation:</strong> Change one 0 to 1 and connect two 1s, then we get an island with area = 3.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> grid = [[1,1],[1,0]]

<strong>Output:</strong> 4

<strong>Explanation: </strong>Change the 0 to 1 and make the island bigger, only one island with area = 4.</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> grid = [[1,1],[1,1]]

<strong>Output:</strong> 4

<strong>Explanation:</strong> Can&#39;t change any 0 to 1, only one island with area = 4.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
    <li><code>n == grid.length</code></li>
    <li><code>n == grid[i].length</code></li>
    <li><code>1 &lt;= n &lt;= 500</code></li>
    <li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

Union find.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestIsland(self, grid: List[List[int]]) -> int:
        n = len(grid)
        p = list(range(n * n))
        size = [1] * (n * n)

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
            return 0 <= i < n and 0 <= j < n and grid[i][j] == 1

        for i in range(n):
            for j in range(n):
                if grid[i][j] == 1:
                    for x, y in [[1, 0], [0, 1]]:
                        if check(i + x, j + y):
                            union(i * n + j, (i + x) * n + j + y)

        res = max(size)
        for i in range(n):
            for j in range(n):
                if grid[i][j] == 0:
                    t = 1
                    s = set()
                    for x, y in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                        if check(i + x, j + y):
                            root = find((i + x) * n + j + y)
                            if root not in s:
                                t += size[root]
                                s.add(root)
                    res = max(res, t)
        return res
```

### **Java**

```java
class Solution {
    private int n;
    private int[] p;
    private int[] size;
    private int mx;
    private int[][] grid;
    private int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int largestIsland(int[][] grid) {
        n = grid.length;
        mx = 1;
        this.grid = grid;
        p = new int[n * n];
        size = new int[n * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    for (int[] e : dirs) {
                        if (check(i + e[0], j + e[1])) {
                            union(i * n + j, (i + e[0]) * n + j + e[1]);
                        }
                    }
                }
            }
        }
        int res = mx;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int t = 1;
                    Set<Integer> s = new HashSet<>();
                    for (int[] e : dirs) {
                        if (check(i + e[0], j + e[1])) {
                            int root = find((i + e[0]) * n + j + e[1]);
                            if (!s.contains(root)) {
                                t += size[root];
                                s.add(root);
                            }
                        }
                    }
                    res = Math.max(res, t);
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

    private void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            size[pb] += size[pa];
            mx = Math.max(mx, size[pb]);
            p[pa] = pb;
        }
    }

    private boolean check(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < n && grid[i][j] == 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;
    vector<int> size;
    int n, mx;
    int dirs[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    int largestIsland(vector<vector<int>>& grid) {
        n = grid.size();
        mx = 1;
        p.resize(n * n);
        size.resize(n * n);
        for (int i = 0; i < p.size(); ++i) {
            p[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    for (auto e : dirs) {
                        if (check(i + e[0], j + e[1], grid)) unite(i * n + j, (i + e[0]) * n + j + e[1]);
                    }
                }
            }
        }
        int res = mx;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int t = 1;
                    unordered_set<int> s;
                    for (auto e : dirs) {
                        if (check(i + e[0], j + e[1], grid)) {
                            int root = find((i + e[0]) * n + j + e[1]);
                            if (!s.count(root)) {
                                t += size[root];
                                s.insert(root);
                            }
                        }
                    }
                    res = max(res, t);
                }
            }
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            size[pb] += size[pa];
            mx = max(mx, size[pb]);
            p[pa] = pb;
        }
    }

    bool check(int i, int j, vector<vector<int>>& grid) {
        return i >= 0 && i < n && j >= 0 && j < n && grid[i][j] == 1;
    }
};
```

### **Go**

```go
var p []int
var size []int
var n int
var mx int

func largestIsland(grid [][]int) int {
	n, mx = len(grid), 1
	p = make([]int, n*n)
	size = make([]int, n*n)
	for i := 0; i < len(p); i++ {
		p[i] = i
		size[i] = 1
	}

	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				for _, e := range dirs {
					if check(i+e[0], j+e[1], grid) {
						union(i*n+j, (i+e[0])*n+j+e[1])
					}
				}
			}
		}
	}
	res := mx
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 {
				t := 1
				s := make(map[int]bool)
				for _, e := range dirs {
					if check(i+e[0], j+e[1], grid) {
						root := find((i+e[0])*n + j + e[1])
						if !s[root] {
							t += size[root]
							s[root] = true
						}
					}
				}
				res = max(res, t)
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

func union(a, b int) {
	pa, pb := find(a), find(b)
	if pa != pb {
		size[pb] += size[pa]
		mx = max(mx, size[pb])
		p[pa] = pb
	}
}

func check(i, j int, grid [][]int) bool {
	return i >= 0 && i < n && j >= 0 && j < n && grid[i][j] == 1
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
