# [1905. 统计子岛屿](https://leetcode.cn/problems/count-sub-islands)

[English Version](/solution/1900-1999/1905.Count%20Sub%20Islands/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个 <code>m x n</code> 的二进制矩阵 <code>grid1</code> 和 <code>grid2</code> ，它们只包含 <code>0</code> （表示水域）和 <code>1</code> （表示陆地）。一个 <strong>岛屿</strong> 是由 <strong>四个方向</strong> （水平或者竖直）上相邻的 <code>1</code> 组成的区域。任何矩阵以外的区域都视为水域。</p>

<p>如果 <code>grid2</code> 的一个岛屿，被 <code>grid1</code> 的一个岛屿 <strong>完全</strong> 包含，也就是说 <code>grid2</code> 中该岛屿的每一个格子都被 <code>grid1</code> 中同一个岛屿完全包含，那么我们称 <code>grid2</code> 中的这个岛屿为 <strong>子岛屿</strong> 。</p>

<p>请你返回 <code>grid2</code> 中 <strong>子岛屿</strong> 的 <strong>数目</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1905.Count%20Sub%20Islands/images/test1.png" style="width: 493px; height: 205px;">
<pre><b>输入：</b>grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
<b>输出：</b>3
<strong>解释：</strong>如上图所示，左边为 grid1 ，右边为 grid2 。
grid2 中标红的 1 区域是子岛屿，总共有 3 个子岛屿。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1905.Count%20Sub%20Islands/images/testcasex2.png" style="width: 491px; height: 201px;">
<pre><b>输入：</b>grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
<b>输出：</b>2 
<strong>解释：</strong>如上图所示，左边为 grid1 ，右边为 grid2 。
grid2 中标红的 1 区域是子岛屿，总共有 2 个子岛屿。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid1.length == grid2.length</code></li>
	<li><code>n == grid1[i].length == grid2[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>grid1[i][j]</code> 和 <code>grid2[i][j]</code> 都要么是 <code>0</code> 要么是 <code>1</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

BFS、DFS 或者并查集。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

BFS - Flood Fill 算法：

```python
class Solution:
    def countSubIslands(self, grid1: List[List[int]], grid2: List[List[int]]) -> int:
        def bfs(i, j):
            q = deque([(i, j)])
            grid2[i][j] = 0
            ans = True
            while q:
                i, j = q.popleft()
                if grid1[i][j] == 0:
                    ans = False
                for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and grid2[x][y]:
                        q.append((x, y))
                        grid2[x][y] = 0
            return ans

        m, n = len(grid1), len(grid1[0])
        return sum(grid2[i][j] and bfs(i, j) for i in range(m) for j in range(n))
```

DFS：

```python
class Solution:
    def countSubIslands(self, grid1: List[List[int]], grid2: List[List[int]]) -> int:
        def dfs(i, j):
            ans = grid1[i][j] == 1
            grid2[i][j] = 0
            for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid2[x][y] == 1 and not dfs(x, y):
                    ans = False
            return ans

        m, n = len(grid1), len(grid1[0])
        return sum(grid2[i][j] == 1 and dfs(i, j) for i in range(m) for j in range(n))
```

并查集：

```python
class Solution:
    def countSubIslands(self, grid1: List[List[int]], grid2: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        m, n = len(grid1), len(grid1[0])
        p = list(range(m * n))
        for i in range(m):
            for j in range(n):
                if grid2[i][j]:
                    for a, b in [[0, 1], [1, 0]]:
                        x, y = i + a, j + b
                        if x < m and y < n and grid2[x][y]:
                            p[find(x * n + y)] = find(i * n + j)
        s = [0] * (m * n)
        for i in range(m):
            for j in range(n):
                if grid2[i][j]:
                    s[find(i * n + j)] = 1
        for i in range(m):
            for j in range(n):
                root = find(i * n + j)
                if s[root] and grid1[i][j] == 0:
                    s[root] = 0
        return sum(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

BFS - Flood Fill 算法：

```java
class Solution {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1 && bfs(i, j, m, n, grid1, grid2)) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private boolean bfs(int i, int j, int m, int n, int[][] grid1, int[][] grid2) {
        Queue<int[]> q = new ArrayDeque<>();
        grid2[i][j] = 0;
        q.offer(new int[]{i, j});
        boolean ans = true;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            i = p[0];
            j = p[1];
            if (grid1[i][j] == 0) {
                ans = false;
            }
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1) {
                    q.offer(new int[]{x, y});
                    grid2[x][y] = 0;
                }
            }
        }
        return ans;
    }
}
```

DFS：

```java
class Solution {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1 && dfs(i, j, m, n, grid1, grid2)) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private boolean dfs(int i, int j, int m, int n, int[][] grid1, int[][] grid2) {
        boolean ans = grid1[i][j] == 1;
        grid2[i][j] = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1 && !dfs(x, y, m, n, grid1, grid2)) {
                ans = false;
            }
        }
        return ans;
    }
}
```

并查集：

```java
class Solution {
    private int[] p;

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        int[] dirs = {1, 0, 1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1) {
                    for (int k = 0; k < 2; ++k) {
                        int x = i + dirs[k];
                        int y = j + dirs[k + 1];
                        if (x < m && y < n && grid2[x][y] == 1) {
                            p[find(x * n + y)] = find(i * n + j);
                        }
                    }
                }
            }
        }
        int[] s = new int[m * n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1) {
                    s[find(i * n + j)] = 1;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int root = find(i * n + j);
                if (s[root] == 1 && grid1[i][j] == 0) {
                    s[root] = 0;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < s.length; ++i) {
            ans += s[i];
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

```ts
function countSubIslands(grid1: number[][], grid2: number[][]): number {
    let m = grid1.length,
        n = grid1[0].length;
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid2[i][j] == 1 && dfs(grid1, grid2, i, j)) {
                ++ans;
            }
        }
    }
    return ans;
}

function dfs(
    grid1: number[][],
    grid2: number[][],
    i: number,
    j: number,
): boolean {
    let m = grid1.length,
        n = grid1[0].length;
    let ans = true;
    if (grid1[i][j] == 0) {
        ans = false;
    }
    grid2[i][j] = 0;
    for (let [dx, dy] of [
        [0, 1],
        [0, -1],
        [1, 0],
        [-1, 0],
    ]) {
        let x = i + dx,
            y = j + dy;
        if (x < 0 || x > m - 1 || y < 0 || y > n - 1 || grid2[x][y] == 0) {
            continue;
        }
        if (!dfs(grid1, grid2, x, y)) {
            ans = false;
        }
    }
    return ans;
}
```

### **C++**

BFS - Flood Fill 算法：

```cpp
class Solution {
public:
    int countSubIslands(vector<vector<int>>& grid1, vector<vector<int>>& grid2) {
        int m = grid1.size();
        int n = grid1[0].size();
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                ans += (grid2[i][j] == 1 && bfs(i, j, m, n, grid1, grid2));
        return ans;
    }

    bool bfs(int i, int j, int m, int n, vector<vector<int>>& grid1, vector<vector<int>>& grid2) {
        queue<pair<int, int>> q;
        q.push({i, j});
        grid2[i][j] = 0;
        bool ans = true;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            auto p = q.front();
            q.pop();
            i = p.first;
            j = p.second;
            if (grid1[i][j] == 0) ans = false;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y]) {
                    q.push({x, y});
                    grid2[x][y] = 0;
                }
            }
        }
        return ans;
    }
};
```

DFS：

```cpp
class Solution {
public:
    int countSubIslands(vector<vector<int>>& grid1, vector<vector<int>>& grid2) {
        int m = grid1.size();
        int n = grid1[0].size();
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid2[i][j] == 1 && dfs(i, j, m, n, grid1, grid2))
                    ++ans;
        return ans;
    }

    bool dfs(int i, int j, int m, int n, vector<vector<int>>& grid1, vector<vector<int>>& grid2) {
        bool ans = grid1[i][j];
        grid2[i][j] = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k)
        {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] && !dfs(x, y, m, n, grid1, grid2))
                ans = false;
        }
        return ans;
    }
};
```

### **Go**

BFS - Flood Fill 算法：

```go
func countSubIslands(grid1 [][]int, grid2 [][]int) int {
	m, n := len(grid1), len(grid1[0])
	ans := 0
	bfs := func(i, j int) bool {
		q := [][]int{{i, j}}
		grid2[i][j] = 0
		res := true
		dirs := []int{-1, 0, 1, 0, -1}
		for len(q) > 0 {
			i, j = q[0][0], q[0][1]
			if grid1[i][j] == 0 {
				res = false
			}
			q = q[1:]
			for k := 0; k < 4; k++ {
				x, y := i+dirs[k], j+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1 {
					q = append(q, []int{x, y})
					grid2[x][y] = 0
				}
			}
		}
		return res
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid2[i][j] == 1 && bfs(i, j) {
				ans++
			}
		}
	}
	return ans
}
```

DFS：

```go
func countSubIslands(grid1 [][]int, grid2 [][]int) int {
	m, n := len(grid1), len(grid1[0])
	ans := 0
	var dfs func(i, j int) bool
	dfs = func(i, j int) bool {
		res := grid1[i][j] == 1
		grid2[i][j] = 0
		dirs := []int{-1, 0, 1, 0, -1}
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1 && !dfs(x, y) {
				res = false
			}
		}
		return res
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid2[i][j] == 1 && dfs(i, j) {
				ans++
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
