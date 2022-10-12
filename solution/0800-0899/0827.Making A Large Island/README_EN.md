# [827. Making A Large Island](https://leetcode.com/problems/making-a-large-island)

[中文文档](/solution/0800-0899/0827.Making%20A%20Large%20Island/README.md)

## Description

<p>You are given an <code>n x n</code> binary matrix <code>grid</code>. You are allowed to change <strong>at most one</strong> <code>0</code> to be <code>1</code>.</p>

<p>Return <em>the size of the largest <strong>island</strong> in</em> <code>grid</code> <em>after applying this operation</em>.</p>

<p>An <strong>island</strong> is a 4-directionally connected group of <code>1</code>s.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> grid = [[1,0],[0,1]]

<strong>Output:</strong> 3

<strong>Explanation:</strong> Change one 0 to 1 and connect two 1s, then we get an island with area = 3.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> grid = [[1,1],[1,0]]

<strong>Output:</strong> 4

<strong>Explanation: </strong>Change the 0 to 1 and make the island bigger, only one island with area = 4.</pre>

<p><strong class="example">Example 3:</strong></p>

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
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa == pb:
                return
            p[pa] = pb
            size[pb] += size[pa]

        n = len(grid)
        p = list(range(n * n))
        size = [1] * (n * n)
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v:
                    for a, b in [[0, -1], [-1, 0]]:
                        x, y = i + a, j + b
                        if 0 <= x < n and 0 <= y < n and grid[x][y]:
                            union(x * n + y, i * n + j)
        ans = max(size)
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v == 0:
                    vis = set()
                    t = 1
                    for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                        x, y = i + a, j + b
                        if 0 <= x < n and 0 <= y < n and grid[x][y]:
                            root = find(x * n + y)
                            if root not in vis:
                                vis.add(root)
                                t += size[root]
                    ans = max(ans, t)
        return ans
```

```python
class Solution:
    def largestIsland(self, grid: List[List[int]]) -> int:
        def dfs(i, j):
            p[i][j] = root
            cnt[root] += 1
            for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < n and 0 <= y < n and grid[x][y] and p[x][y] == 0:
                    dfs(x, y)

        n = len(grid)
        cnt = Counter()
        p = [[0] * n for _ in range(n)]
        root = 0
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v and p[i][j] == 0:
                    root += 1
                    dfs(i, j)

        ans = max(cnt.values(), default=0)
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v == 0:
                    t = 1
                    vis = set()
                    for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                        x, y = i + a, j + b
                        if 0 <= x < n and 0 <= y < n:
                            root = p[x][y]
                            if root not in vis:
                                vis.add(root)
                                t += cnt[root]
                    ans = max(ans, t)
        return ans
```

### **Java**

```java
class Solution {
    private int n;
    private int[] p;
    private int[] size;
    private int ans = 1;
    private int[] dirs = new int[] {-1, 0, 1, 0, -1};

    public int largestIsland(int[][] grid) {
        n = grid.length;
        p = new int[n * n];
        size = new int[n * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1) {
                            int pa = find(x * n + y), pb = find(i * n + j);
                            if (pa == pb) {
                                continue;
                            }
                            p[pa] = pb;
                            size[pb] += size[pa];
                            ans = Math.max(ans, size[pb]);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int t = 1;
                    Set<Integer> vis = new HashSet<>();
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1) {
                            int root = find(x * n + y);
                            if (!vis.contains(root)) {
                                vis.add(root);
                                t += size[root];
                            }
                        }
                    }
                    ans = Math.max(ans, t);
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
class Solution {
    private int n;
    private int ans;
    private int root;
    private int[][] p;
    private int[][] grid;
    private int[] cnt;
    private int[] dirs = new int[] {-1, 0, 1, 0, -1};

    public int largestIsland(int[][] grid) {
        n = grid.length;
        cnt = new int[n * n + 1];
        p = new int[n][n];
        this.grid = grid;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1 && p[i][j] == 0) {
                    ++root;
                    dfs(i, j);
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int t = 1;
                    Set<Integer> vis = new HashSet<>();
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            int root = p[x][y];
                            if (!vis.contains(root)) {
                                vis.add(root);
                                t += cnt[root];
                            }
                        }
                    }
                    ans = Math.max(ans, t);
                }
            }
        }
        return ans;
    }

    private void dfs(int i, int j) {
        p[i][j] = root;
        ++cnt[root];
        ans = Math.max(ans, cnt[root]);
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1 && p[x][y] == 0) {
                dfs(x, y);
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const static inline vector<int> dirs = {-1, 0, 1, 0, -1};

    int largestIsland(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<int> p(n * n);
        vector<int> size(n * n, 1);
        iota(p.begin(), p.end(), 0);

        function<int(int)> find;
        find = [&](int x) {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };

        int ans = 1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y]) {
                            int pa = find(x * n + y), pb = find(i * n + j);
                            if (pa == pb) continue;
                            p[pa] = pb;
                            size[pb] += size[pa];
                            ans = max(ans,size[pb]);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!grid[i][j]) {
                    int t = 1;
                    unordered_set<int> vis;
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y]) {
                            int root = find(x * n + y);
                            if (!vis.count(root)) {
                                vis.insert(root);
                                t += size[root];
                            }
                        }
                    }
                    ans = max(ans, t);
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    const static inline vector<int> dirs = {-1, 0, 1, 0, -1};

    int largestIsland(vector<vector<int>>& grid) {
        int n = grid.size();
        int ans = 0;
        int root = 0;
        vector<vector<int>> p(n, vector<int>(n));
        vector<int> cnt(n * n + 1);

        function<void(int, int)> dfs;
        dfs = [&](int i, int j) {
            p[i][j] = root;
            ++cnt[root];
            ans = max(ans, cnt[root]);
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] && p[x][y] == 0) {
                    dfs(x, y);
                }
            }
        };

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] && p[i][j] == 0) {
                    ++root;
                    dfs(i, j);
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!grid[i][j]) {
                    int t = 1;
                    unordered_set<int> vis;
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            int root = p[x][y];
                            if (!vis.count(root)) {
                                vis.insert(root);
                                t += cnt[root];
                            }
                        }
                    }
                    ans = max(ans, t);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func largestIsland(grid [][]int) int {
	n := len(grid)
	p := make([]int, n*n)
	size := make([]int, n*n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	dirs := []int{-1, 0, 1, 0, -1}
	ans := 1
	for i, row := range grid {
		for j, v := range row {
			if v == 1 {
				for k := 0; k < 4; k++ {
					x, y := i+dirs[k], j+dirs[k+1]
					if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1 {
						pa, pb := find(x*n+y), find(i*n+j)
						if pa != pb {
							p[pa] = pb
							size[pb] += size[pa]
							ans = max(ans, size[pb])
						}
					}
				}
			}
		}
	}
	for i, row := range grid {
		for j, v := range row {
			if v == 0 {
				t := 1
				vis := map[int]struct{}{}
				for k := 0; k < 4; k++ {
					x, y := i+dirs[k], j+dirs[k+1]
					if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1 {
						root := find(x*n + y)
						if _, ok := vis[root]; !ok {
							vis[root] = struct{}{}
							t += size[root]
						}
					}
				}
				ans = max(ans, t)
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

```go
func largestIsland(grid [][]int) int {
	n := len(grid)
	p := make([][]int, n)
	for i := range p {
		p[i] = make([]int, n)
	}
	cnt := make([]int, n*n+1)
	dirs := []int{-1, 0, 1, 0, -1}
	ans, root := 0, 0

	var dfs func(i, j int)
	dfs = func(i, j int) {
		p[i][j] = root
		cnt[root]++
		ans = max(ans, cnt[root])
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1 && p[x][y] == 0 {
				dfs(x, y)
			}
		}
	}

	for i, row := range grid {
		for j, v := range row {
			if v == 1 && p[i][j] == 0 {
				root++
				dfs(i, j)
			}
		}
	}
	for i, row := range grid {
		for j, v := range row {
			if v == 0 {
				t := 1
				vis := map[int]struct{}{}
				for k := 0; k < 4; k++ {
					x, y := i+dirs[k], j+dirs[k+1]
					if x >= 0 && x < n && y >= 0 && y < n {
						root := p[x][y]
						if _, ok := vis[root]; !ok {
							vis[root] = struct{}{}
							t += cnt[root]
						}
					}
				}
				ans = max(ans, t)
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

### **TypeScript**

```ts
function largestIsland(grid: number[][]): number {
    const n = grid.length;
    const vis = Array.from({ length: n }, () => new Array(n).fill(false));
    const group = Array.from({ length: n }, () => new Array(n).fill(0));
    const dfs = (i: number, j: number, paths: [number, number][]) => {
        if (
            i < 0 ||
            j < 0 ||
            i === n ||
            j === n ||
            vis[i][j] ||
            grid[i][j] !== 1
        ) {
            return;
        }
        vis[i][j] = true;
        paths.push([i, j]);
        dfs(i + 1, j, paths);
        dfs(i, j + 1, paths);
        dfs(i - 1, j, paths);
        dfs(i, j - 1, paths);
    };
    let count = 1;
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            const paths: [number, number][] = [];
            dfs(i, j, paths);
            if (paths.length !== 0) {
                for (const [x, y] of paths) {
                    group[x][y] = count;
                    grid[x][y] = paths.length;
                }
                count++;
            }
        }
    }

    let res = 0;
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            let sum = grid[i][j];
            if (grid[i][j] === 0) {
                sum++;
                const set = new Set();
                if (i !== 0) {
                    sum += grid[i - 1][j];
                    set.add(group[i - 1][j]);
                }
                if (i !== n - 1 && !set.has(group[i + 1][j])) {
                    sum += grid[i + 1][j];
                    set.add(group[i + 1][j]);
                }
                if (j !== 0 && !set.has(group[i][j - 1])) {
                    sum += grid[i][j - 1];
                    set.add(group[i][j - 1]);
                }
                if (j !== n - 1 && !set.has(group[i][j + 1])) {
                    sum += grid[i][j + 1];
                }
            }
            res = Math.max(res, sum);
        }
    }
    return res;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    fn dfs(
        i: usize,
        j: usize,
        grid: &Vec<Vec<i32>>,
        paths: &mut Vec<(usize, usize)>,
        vis: &mut Vec<Vec<bool>>,
    ) {
        let n = vis.len();
        if vis[i][j] || grid[i][j] != 1 {
            return;
        }
        paths.push((i, j));
        vis[i][j] = true;
        if i != 0 {
            Self::dfs(i - 1, j, grid, paths, vis);
        }
        if j != 0 {
            Self::dfs(i, j - 1, grid, paths, vis);
        }
        if i != n - 1 {
            Self::dfs(i + 1, j, grid, paths, vis);
        }
        if j != n - 1 {
            Self::dfs(i, j + 1, grid, paths, vis);
        }
    }

    pub fn largest_island(mut grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let mut vis = vec![vec![false; n]; n];
        let mut group = vec![vec![0; n]; n];
        let mut count = 1;
        for i in 0..n {
            for j in 0..n {
                let mut paths: Vec<(usize, usize)> = Vec::new();
                Self::dfs(i, j, &grid, &mut paths, &mut vis);
                let m = paths.len() as i32;
                if m != 0 {
                    for (x, y) in paths {
                        grid[x][y] = m;
                        group[x][y] = count;
                    }
                    count += 1;
                }
            }
        }
        let mut res = 0;
        for i in 0..n {
            for j in 0..n {
                let mut sum = grid[i][j];
                if grid[i][j] == 0 {
                    sum += 1;
                    let mut set = HashSet::new();
                    if i != 0 {
                        sum += grid[i - 1][j];
                        set.insert(group[i - 1][j]);
                    }
                    if j != 0 && !set.contains(&group[i][j - 1]) {
                        sum += grid[i][j - 1];
                        set.insert(group[i][j - 1]);
                    }
                    if i != n - 1 && !set.contains(&group[i + 1][j]) {
                        sum += grid[i + 1][j];
                        set.insert(group[i + 1][j]);
                    }
                    if j != n - 1 && !set.contains(&group[i][j + 1]) {
                        sum += grid[i][j + 1];
                        set.insert(group[i][j + 1]);
                    }
                }
                res = res.max(sum);
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
