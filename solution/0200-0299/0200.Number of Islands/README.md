# [200. 岛屿数量](https://leetcode-cn.com/problems/number-of-islands)

[English Version](/solution/0200-0299/0200.Number%20of%20Islands/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由 <code>'1'</code>（陆地）和 <code>'0'</code>（水）组成的的二维网格，请你计算网格中岛屿的数量。</p>

<p>岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。</p>

<p>此外，你可以假设该网格的四条边均被水包围。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
<strong>输出：</strong>3
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 <= m, n <= 300</code></li>
	<li><code>grid[i][j]</code> 的值为 <code>'0'</code> 或 <code>'1'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

BFS、DFS、并查集均可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

并查集：

```python
class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        m, n = len(grid), len(grid[0])
        p = list(range(m * n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(m):
            for j in range(n):
                if grid[i][j] == '1':
                    if i < m - 1 and grid[i + 1][j] == '1':
                        p[find(i * n + j)] = find((i + 1) * n + j)
                    if j < n - 1 and grid[i][j + 1] == '1':
                        p[find(i * n + j)] = find(i * n + j + 1)

        res = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] == '1' and i * n + j == find(i * n + j):
                    res += 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

DFS：

```java
class Solution {
    public int numIslands(char[][] grid) {
        int islandNum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    infect(grid, i, j);
                    islandNum ++;
                }
            }
        }
        return islandNum;
    }

    public void infect(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length ||
                j < 0 || j >= grid[0].length ||
                grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        infect(grid, i + 1, j);
        infect(grid, i - 1, j);
        infect(grid, i, j + 1);
        infect(grid, i, j - 1);
    }
}
```

并查集：

```java
class Solution {
    private int[] p;

    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    if (i < m - 1 && grid[i + 1][j] == '1') {
                        p[find(i * n + j)] = find((i + 1) * n + j);
                    }
                    if (j < n - 1 && grid[i][j + 1] == '1') {
                        p[find(i * n + j)] = find(i * n + j + 1);
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1' && i * n + j == find(i * n + j)) {
                    ++res;
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

```ts
function numIslands(grid: string[][]): number {
  let m = grid.length,
    n = grid[0].length;
  let ans = 0;
  for (let i = 0; i < m; ++i) {
    for (let j = 0; j < n; ++j) {
      if (grid[i][j] == "1") {
        dfs(grid, i, j);
        ++ans;
      }
    }
  }
  return ans;
}

function dfs(grid: string[][], i: number, j: number) {
  let m = grid.length,
    n = grid[0].length;
  if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || grid[i][j] == "0") {
    return;
  }
  grid[i][j] = "0";
  for (let [dx, dy] of [
    [0, 1],
    [0, -1],
    [1, 0],
    [-1, 0],
  ]) {
    let x = i + dx,
      y = j + dy;
    dfs(grid, x, y);
  }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    int numIslands(vector<vector<char>>& grid) {
        int m = grid.size(), n = grid[0].size();
        p.resize(m * n);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (grid[i][j] == '1')
                {
                    if (i < m - 1 && grid[i + 1][j] == '1') p[find(i * n + j)] = find((i + 1) * n + j);
                    if (j < n - 1 && grid[i][j + 1] == '1') p[find(i * n + j)] = find(i * n + j + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (grid[i][j] == '1' && i * n + j == find(i * n + j)) ++res;
            }
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

并查集：

```go
var p []int

func numIslands(grid [][]byte) int {
	m, n := len(grid), len(grid[0])
	p = make([]int, m*n)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == '1' {
				if i < m-1 && grid[i+1][j] == '1' {
					p[find(i*n+j)] = find((i+1)*n + j)
				}
				if j < n-1 && grid[i][j+1] == '1' {
					p[find(i*n+j)] = find(i*n + j + 1)
				}
			}
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == '1' && i*n+j == find(i*n+j) {
				res++
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
```

### **...**

```

```

<!-- tabs:end -->
