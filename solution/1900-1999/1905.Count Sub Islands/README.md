# [1905. 统计子岛屿](https://leetcode-cn.com/problems/count-sub-islands)

[English Version](/solution/1900-1999/1905.Count%20Sub%20Islands/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个 <code>m x n</code> 的二进制矩阵 <code>grid1</code> 和 <code>grid2</code> ，它们只包含 <code>0</code> （表示水域）和 <code>1</code> （表示陆地）。一个 <strong>岛屿</strong> 是由 <strong>四个方向</strong> （水平或者竖直）上相邻的 <code>1</code> 组成的区域。任何矩阵以外的区域都视为水域。</p>

<p>如果 <code>grid2</code> 的一个岛屿，被 <code>grid1</code> 的一个岛屿 <strong>完全</strong> 包含，也就是说 <code>grid2</code> 中该岛屿的每一个格子都被 <code>grid1</code> 中同一个岛屿完全包含，那么我们称 <code>grid2</code> 中的这个岛屿为 <strong>子岛屿</strong> 。</p>

<p>请你返回 <code>grid2</code> 中 <strong>子岛屿</strong> 的 <strong>数目</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1905.Count%20Sub%20Islands/images/test1.png" style="width: 493px; height: 205px;">
<pre><b>输入：</b>grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
<b>输出：</b>3
<strong>解释：</strong>如上图所示，左边为 grid1 ，右边为 grid2 。
grid2 中标红的 1 区域是子岛屿，总共有 3 个子岛屿。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1905.Count%20Sub%20Islands/images/testcasex2.png" style="width: 491px; height: 201px;">
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

深度优先搜索，或者并查集。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

DFS：

```python
class Solution:
    def countSubIslands(self, grid1: List[List[int]], grid2: List[List[int]]) -> int:
        def dfs(grid1, grid2, i, j, m, n) -> bool:
            res = grid1[i][j] == 1
            grid2[i][j] = 0
            for x, y in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                a, b = i + x, j + y
                if a >= 0 and a < m and b >= 0 and b < n and grid2[a][b] == 1:
                    if not dfs(grid1, grid2, a, b, m, n):
                        res = False
            return res

        m, n = len(grid1), len(grid1[0])
        count = 0
        for i in range(m):
            for j in range(n):
                if grid2[i][j] == 1 and dfs(grid1, grid2, i, j, m, n):
                    count += 1
        return count
```

并查集：

```python
class Solution:
    def countSubIslands(self, grid1: List[List[int]], grid2: List[List[int]]) -> int:
        m, n = len(grid1), len(grid1[0])
        p = list(range(m * n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(m):
            for j in range(n):
                if grid2[i][j] == 1:
                    idx = i * n + j
                    if i < m - 1 and grid2[i + 1][j] == 1:
                        p[find(idx)] = find((i + 1) * n + j)
                    if j < n - 1 and grid2[i][j + 1] == 1:
                        p[find(idx)] = find(i * n + j + 1)

        s = [0] * (m * n)
        for i in range(m):
            for j in range(n):
                if grid2[i][j] == 1:
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

DFS：

```java
class Solution {
    private int[][] directions = {{0, 1}, {0, - 1}, {1, 0}, {-1, 0}};

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length, n = grid1[0].length;
        int count = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1 && dfs(grid1, grid2, i, j, m, n)) {
                    ++count;
                }
            }
        }
        return count;
    }

    private boolean dfs(int[][] grid1, int[][] grid2, int i, int j, int m, int n) {
        boolean res = grid1[i][j] == 1;
        grid2[i][j] = 0;

        for (int[] direction : directions) {
            int a = i + direction[0], b = j + direction[1];
            if (a >= 0 && a < m && b >= 0 && b < n && grid2[a][b] == 1) {
                if (!dfs(grid1, grid2, a, b, m, n)) {
                    res = false;
                }
            }
        }
        return res;
    }
}
```

并查集：

```java
class Solution {
    private int[] p;

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid2.length, n = grid2[0].length;
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1) {
                    int idx = i * n + j;
                    if (i < m - 1 && grid2[i + 1][j] == 1) {
                        p[find(idx)] = find((i + 1) * n + j);
                    }
                    if (j < n - 1 && grid2[i][j + 1] == 1) {
                        p[find(idx)] = find(i * n + j + 1);
                    }
                }
            }
        }
        boolean[] s = new boolean[m * n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1) {
                    s[find(i * n + j)] = true;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int root = find(i * n + j);
                if (s[root] && grid1[i][j] == 0) {
                    s[root] = false;
                }
            }
        }
        int res = 0;
        for (boolean e : s) {
            if (e) {
                ++res;
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
    j: number
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

```cpp
class Solution {
public:
    int countSubIslands(vector<vector<int>> &grid1, vector<vector<int>> &grid2) {
        int m = grid1.size(), n = grid1[0].size();
        int count = 0;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (grid2[i][j] == 1 && dfs(grid1, grid2, i, j, m, n))
                {
                    ++count;
                }
            }
        }
        return count;
    }

private:
    vector<vector<int>> directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    bool dfs(vector<vector<int>> &grid1, vector<vector<int>> &grid2, int i, int j, int m, int n) {
        bool res = grid1[i][j] == 1;
        grid2[i][j] = 0;

        for (auto direction : directions)
        {
            int a = i + direction[0], b = j + direction[1];
            if (a >= 0 && a < m && b >= 0 && b < n && grid2[a][b] == 1)
            {
                if (!dfs(grid1, grid2, a, b, m, n))
                {
                    res = false;
                }
            }
        }
        return res;
    }
};
```

### **Go**

```go
func countSubIslands(grid1 [][]int, grid2 [][]int) int {
	m, n := len(grid1), len(grid1[0])
	count := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid2[i][j] == 1 && dfs(grid1, grid2, i, j, m, n) {
				count++
			}
		}
	}
	return count
}

func dfs(grid1 [][]int, grid2 [][]int, i, j, m, n int) bool {
	res := grid1[i][j] == 1
	grid2[i][j] = 0
	directions := [4][2]int{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}
	for _, direction := range directions {
		a, b := i+direction[0], j+direction[1]
		if a >= 0 && a < m && b >= 0 && b < n && grid2[a][b] == 1 {
			if !dfs(grid1, grid2, a, b, m, n) {
				res = false
			}
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
