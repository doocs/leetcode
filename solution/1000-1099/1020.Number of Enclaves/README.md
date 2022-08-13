# [1020. 飞地的数量](https://leetcode.cn/problems/number-of-enclaves)

[English Version](/solution/1000-1099/1020.Number%20of%20Enclaves/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>m x n</code> 的二进制矩阵 <code>grid</code> ，其中 <code>0</code> 表示一个海洋单元格、<code>1</code> 表示一个陆地单元格。</p>

<p>一次 <strong>移动</strong> 是指从一个陆地单元格走到另一个相邻（<strong>上、下、左、右</strong>）的陆地单元格或跨过 <code>grid</code> 的边界。</p>

<p>返回网格中<strong> 无法 </strong>在任意次数的移动中离开网格边界的陆地单元格的数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1020.Number%20of%20Enclaves/images/enclaves1.jpg" style="height: 200px; width: 200px;" />
<pre>
<strong>输入：</strong>grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
<strong>输出：</strong>3
<strong>解释：</strong>有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1020.Number%20of%20Enclaves/images/enclaves2.jpg" style="height: 200px; width: 200px;" />
<pre>
<strong>输入：</strong>grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
<strong>输出：</strong>0
<strong>解释：</strong>所有 1 都在边界上或可以到达边界。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>grid[i][j]</code> 的值为 <code>0</code> 或 <code>1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

从矩阵边缘所有 1 开始进行深搜，遇到 1 则改为 0。搜索结束后，统计剩余 1 的个数，即为结果。

**方法二：并查集**

并查集模板 1——朴素并查集：

```python
# 初始化，p存储每个点的父节点
p = list(range(n))

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        # 路径压缩
        p[x] = find(p[x])
    return p[x]


# 合并a和b所在的两个集合
p[find(a)] = find(b)
```

模板 2——维护 size 的并查集：

```python
# 初始化，p存储每个点的父节点，size只有当节点是祖宗节点时才有意义，表示祖宗节点所在集合中，点的数量
p = list(range(n))
size = [1] * n

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        # 路径压缩
        p[x] = find(p[x])
    return p[x]

# 合并a和b所在的两个集合
if find(a) != find(b):
    size[find(b)] += size[find(a)]
    p[find(a)] = find(b)
```

模板 3——维护到祖宗节点距离的并查集：

```python
# 初始化，p存储每个点的父节点，d[x]存储x到p[x]的距离
p = list(range(n))
d = [0] * n

# 返回x的祖宗节点
def find(x):
    if p[x] != x:
        t = find(p[x])
        d[x] += d[p[x]]
        p[x] = t
    return p[x]

# 合并a和b所在的两个集合
p[find(a)] = find(b)
d[find(a)] = distance
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

DFS：

```python
class Solution:
    def numEnclaves(self, grid: List[List[int]]) -> int:
        def dfs(i, j):
            grid[i][j] = 0
            for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y] == 1:
                    dfs(x, y)

        m, n = len(grid), len(grid[0])
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1 and (i == 0 or i == m - 1 or j == 0 or j == n - 1):
                    dfs(i, j)
        return sum(grid[i][j] for i in range(m) for j in range(n))
```

并查集：

```python
class Solution:
    def numEnclaves(self, grid: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        m, n = len(grid), len(grid[0])
        p = list(range(m * n + 1))
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    if i == 0 or i == m - 1 or j == 0 or j == n - 1:
                        p[find(i * n + j)] = find(m * n)
                    else:
                        for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                            x, y = i + a, j + b
                            if grid[x][y] == 1:
                                p[find(i * n + j)] = find(x * n + y)
        return sum(grid[i][j] == 1 and find(i * n + j) != find(m * n) for i in range(m) for j in range(n))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

DFS：

```java
class Solution {
    private int[][] grid;
    private int m;
    private int n;

    public int numEnclaves(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1 && (i == 0 || i == m - 1 || j == 0 || j == n - 1)) {
                    dfs(i, j);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private void dfs(int i, int j) {
        grid[i][j] = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                dfs(x, y);
            }
        }
    }
}
```

并查集：

```java
class Solution {
    private int[] p;

    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        p = new int[m * n + 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        p[find(i * n + j)] = find(m * n);
                    } else {
                        for (int k = 0; k < 4; ++k) {
                            int x = i + dirs[k];
                            int y = j + dirs[k + 1];
                            if (grid[x][y] == 1) {
                                p[find(i * n + j)] = find(x * n + y);
                            }
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1 && find(i * n + j) != find(m * n)) {
                    ++ans;
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

### **C++**

DFS：

```cpp
class Solution {
public:
    int m;
    int n;

    int numEnclaves(vector<vector<int>>& grid) {
        m = grid.size();
        n = grid[0].size();
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 1 && (i == 0 || i == m - 1 || j == 0 || j == n - 1))
                    dfs(i, j, grid);
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 1)
                    ++ans;
        return ans;
    }

    void dfs(int i, int j, vector<vector<int>>& grid) {
        grid[i][j] = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1)
                dfs(x, y, grid);
        }
    }
};
```

并查集：

```cpp
class Solution {
public:
    vector<int> p;

    int numEnclaves(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        p.resize(m * n + 1);
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (grid[i][j] == 1)
                {
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) p[find(i * n + j)] = find(m * n);
                    else
                    {
                        for (int k = 0; k < 4; ++k)
                        {
                            int x = i + dirs[k];
                            int y = j + dirs[k + 1];
                            if (grid[x][y] == 1) p[find(i * n + j)] = find(x * n + y);
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 1 && find(i * n + j) != find(m * n))
                    ++ans;
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

DFS：

```go
func numEnclaves(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dirs := []int{-1, 0, 1, 0, -1}
	var dfs func(i, j int)
	dfs = func(i, j int) {
		grid[i][j] = 0
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
				dfs(x, y)
			}
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 && (i == 0 || i == m-1 || j == 0 || j == n-1) {
				dfs(i, j)
			}
		}
	}
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				ans++
			}
		}
	}
	return ans
}
```

并查集：

```go
func numEnclaves(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	p := make([]int, m*n+1)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	dirs := []int{-1, 0, 1, 0, -1}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				if i == 0 || i == m-1 || j == 0 || j == n-1 {
					p[find(i*n+j)] = find(m * n)
				} else {
					for k := 0; k < 4; k++ {
						x, y := i+dirs[k], j+dirs[k+1]
						if grid[x][y] == 1 {
							p[find(i*n+j)] = find(x*n + y)
						}
					}
				}
			}
		}
	}
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 && find(i*n+j) != find(m*n) {
				ans++
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function numEnclaves(grid: number[][]): number {
    let res = 0;
    const m = grid.length;
    const n = grid[0].length;
    const dfs = (y: number, x: number) => {
        if (x < 0 || x >= n || y < 0 || y >= m || grid[y][x] === 0) {
            return;
        }
        grid[y][x] = 0;
        dfs(y + 1, x);
        dfs(y, x + 1);
        dfs(y - 1, x);
        dfs(y, x - 1);
    };
    for (let i = 0; i < n; i++) {
        dfs(0, i);
        dfs(m - 1, i);
    }
    for (let i = 0; i < m; i++) {
        dfs(i, 0);
        dfs(i, n - 1);
    }
    for (let i = 1; i < m - 1; i++) {
        for (let j = 1; j < n - 1; j++) {
            if (grid[i][j] === 1) {
                res++;
            }
        }
    }
    return res;
}
```

多源 BFS

```ts
function numEnclaves(grid: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    let ans = 0;
    let queue = [];
    // 统计全部1, 临边的1加入队列
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            let cur = grid[i][j];
            if (cur) {
                ans++;
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    queue.push([i, j]);
                    ans--;
                }
            }
        }
    }

    let directions = [
        [-1, 0],
        [1, 0],
        [0, -1],
        [0, 1],
    ];
    while (queue.length) {
        let nextQueue = [];
        for (let [x, y] of queue) {
            for (let [dx, dy] of directions) {
                let [i, j] = [x + dx, y + dy];
                if (i > 0 && i < m - 1 && j > 0 && j < n - 1 && grid[i][j]) {
                    nextQueue.push([i, j]);
                    ans--;
                    grid[i][j] = 0;
                }
            }
            queue = nextQueue;
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(grid: &mut Vec<Vec<i32>>, y: usize, x: usize) {
        if y >= grid.len() || x >= grid[0].len() || grid[y][x] == 0 {
            return;
        }
        grid[y][x] = 0;
        Solution::dfs(grid, y + 1, x);
        Solution::dfs(grid, y, x + 1);
        if y != 0 {
            Solution::dfs(grid, y - 1, x);
        }
        if x != 0 {
            Solution::dfs(grid, y, x - 1);
        }
    }
    pub fn num_enclaves(mut grid: Vec<Vec<i32>>) -> i32 {
        let mut res = 0;
        let m = grid.len();
        let n = grid[0].len();
        for i in 0..m {
            Solution::dfs(&mut grid, i, 0);
            Solution::dfs(&mut grid, i, n - 1);
        }
        for i in 0..n {
            Solution::dfs(&mut grid, 0, i);
            Solution::dfs(&mut grid, m - 1, i);
        }
        for i in 1..m - 1 {
            for j in 1..n - 1 {
                if grid[i][j] == 1 {
                    res += 1;
                }
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
