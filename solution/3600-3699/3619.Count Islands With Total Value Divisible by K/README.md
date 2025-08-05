---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3619.Count%20Islands%20With%20Total%20Value%20Divisible%20by%20K/README.md
rating: 1461
source: 第 161 场双周赛 Q2
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 数组
    - 矩阵
---

<!-- problem:start -->

# [3619. 总价值可以被 K 整除的岛屿数目](https://leetcode.cn/problems/count-islands-with-total-value-divisible-by-k)

[English Version](/solution/3600-3699/3619.Count%20Islands%20With%20Total%20Value%20Divisible%20by%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <code>m x n</code> 的矩阵 <code>grid</code> 和一个正整数 <code>k</code>。一个&nbsp;<strong>岛屿&nbsp;</strong>是由&nbsp;<strong>正&nbsp;</strong>整数（表示陆地）组成的，并且陆地间&nbsp;<strong>四周&nbsp;</strong>连通（水平或垂直）。</p>

<p>一个岛屿的总价值是该岛屿中所有单元格的值之和。</p>

<p>返回总价值可以被 <code>k</code> <strong>整除&nbsp;</strong>的岛屿数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3619.Count%20Islands%20With%20Total%20Value%20Divisible%20by%20K/images/example1griddrawio-1.png" style="width: 200px; height: 200px;" />
<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">grid = [[0,2,1,0,0],[0,5,0,0,5],[0,0,1,0,0],[0,1,4,7,0],[0,2,0,0,8]], k = 5</span></p>

<p><strong>输出:</strong> <span class="example-io">2</span></p>

<p><strong>解释:</strong></p>

<p>网格中包含四个岛屿。蓝色高亮显示的岛屿的总价值可以被 5 整除，而红色高亮显示的岛屿则不能。</p>
</div>

<p><strong class="example">示例 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3619.Count%20Islands%20With%20Total%20Value%20Divisible%20by%20K/images/example2griddrawio.png" style="width: 200px; height: 150px;" />
<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">grid = [[3,0,3,0], [0,3,0,3], [3,0,3,0]], k = 3</span></p>

<p><strong>输出:</strong> <span class="example-io">6</span></p>

<p><strong>解释:</strong></p>

<p>网格中包含六个岛屿，每个岛屿的总价值都可以被 3 整除。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt; = 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们定义一个函数 $\textit{dfs}(i, j)$，它从位置 $(i, j)$ 开始进行 DFS 遍历，并且返回该岛屿的总价值。我们将当前位置的值加入总价值，然后将该位置标记为已访问（例如，将其值设为 0）。接着，我们递归地访问四个方向（上、下、左、右）的相邻位置，如果相邻位置的值大于 0，则继续进行 DFS，并将其值加入总价值。最后，我们返回总价值。

在主函数中，我们遍历整个网格，对于每个未访问的位置 $(i, j)$，如果其值大于 0，则调用 $\textit{dfs}(i, j)$ 来计算该岛屿的总价值。如果总价值可以被 $k$ 整除，则将答案加一。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是网格的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countIslands(self, grid: List[List[int]], k: int) -> int:
        def dfs(i: int, j: int) -> int:
            s = grid[i][j]
            grid[i][j] = 0
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y]:
                    s += dfs(x, y)
            return s

        m, n = len(grid), len(grid[0])
        dirs = (-1, 0, 1, 0, -1)
        ans = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] and dfs(i, j) % k == 0:
                    ans += 1
        return ans
```

#### Java

```java
class Solution {
    private int m;
    private int n;
    private int[][] grid;
    private final int[] dirs = {-1, 0, 1, 0, -1};

    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > 0 && dfs(i, j) % k == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private long dfs(int i, int j) {
        long s = grid[i][j];
        grid[i][j] = 0;
        for (int d = 0; d < 4; ++d) {
            int x = i + dirs[d], y = j + dirs[d + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
                s += dfs(x, y);
            }
        }
        return s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countIslands(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        vector<int> dirs = {-1, 0, 1, 0, -1};

        auto dfs = [&](this auto&& dfs, int i, int j) -> long long {
            long long s = grid[i][j];
            grid[i][j] = 0;
            for (int d = 0; d < 4; ++d) {
                int x = i + dirs[d], y = j + dirs[d + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y]) {
                    s += dfs(x, y);
                }
            }
            return s;
        };

        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] && dfs(i, j) % k == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countIslands(grid [][]int, k int) (ans int) {
	m, n := len(grid), len(grid[0])
	dirs := []int{-1, 0, 1, 0, -1}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		s := grid[i][j]
		grid[i][j] = 0
		for d := 0; d < 4; d++ {
			x, y := i+dirs[d], j+dirs[d+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0 {
				s += dfs(x, y)
			}
		}
		return s
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] > 0 && dfs(i, j)%k == 0 {
				ans++
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function countIslands(grid: number[][], k: number): number {
    const m = grid.length,
        n = grid[0].length;
    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number): number => {
        let s = grid[i][j];
        grid[i][j] = 0;
        for (let d = 0; d < 4; d++) {
            const x = i + dirs[d],
                y = j + dirs[d + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
                s += dfs(x, y);
            }
        }
        return s;
    };

    let ans = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] > 0 && dfs(i, j) % k === 0) {
                ans++;
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
