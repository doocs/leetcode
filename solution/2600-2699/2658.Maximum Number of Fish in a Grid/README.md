# [2658. 网格图中鱼的最大数目](https://leetcode.cn/problems/maximum-number-of-fish-in-a-grid)

[English Version](/solution/2600-2699/2658.Maximum%20Number%20of%20Fish%20in%20a%20Grid/README_EN.md)

<!-- tags:深度优先搜索,广度优先搜索,并查集,数组,矩阵 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始大小为 <code>m x n</code>&nbsp;的二维整数数组&nbsp;<code>grid</code>&nbsp;，其中下标在&nbsp;<code>(r, c)</code>&nbsp;处的整数表示：</p>

<ul>
	<li>如果&nbsp;<code>grid[r][c] = 0</code>&nbsp;，那么它是一块 <strong>陆地</strong>&nbsp;。</li>
	<li>如果&nbsp;<code>grid[r][c] &gt; 0</code>&nbsp;，那么它是一块&nbsp;<strong>水域</strong>&nbsp;，且包含&nbsp;<code>grid[r][c]</code>&nbsp;条鱼。</li>
</ul>

<p>一位渔夫可以从任意 <strong>水域</strong>&nbsp;格子&nbsp;<code>(r, c)</code>&nbsp;出发，然后执行以下操作任意次：</p>

<ul>
	<li>捕捞格子&nbsp;<code>(r, c)</code>&nbsp;处所有的鱼，或者</li>
	<li>移动到相邻的 <strong>水域</strong>&nbsp;格子。</li>
</ul>

<p>请你返回渔夫最优策略下，&nbsp;<strong>最多</strong>&nbsp;可以捕捞多少条鱼。如果没有水域格子，请你返回 <code>0</code>&nbsp;。</p>

<p>格子&nbsp;<code>(r, c)</code>&nbsp;<strong>相邻</strong>&nbsp;的格子为&nbsp;<code>(r, c + 1)</code>&nbsp;，<code>(r, c - 1)</code>&nbsp;，<code>(r + 1, c)</code> 和&nbsp;<code>(r - 1, c)</code>&nbsp;，前提是相邻格子在网格图内。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2658.Maximum%20Number%20of%20Fish%20in%20a%20Grid/images/example.png" style="width: 241px; height: 161px;"></p>

<pre><b>输入：</b>grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
<b>输出：</b>7
<b>解释：</b>渔夫可以从格子 <code>(1,3)</code> 出发，捕捞 3 条鱼，然后移动到格子 <code>(2,3)</code>&nbsp;，捕捞 4 条鱼。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2658.Maximum%20Number%20of%20Fish%20in%20a%20Grid/images/example2.png"></p>

<pre><b>输入：</b>grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
<b>输出：</b>1
<b>解释：</b>渔夫可以从格子 (0,0) 或者 (3,3) ，捕捞 1 条鱼。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10</code></li>
</ul>

## 解法

### 方法一：DFS

根据题目描述，我们只需要找出每块连通的水域中鱼的数目，然后取最大值即可。因此，我们可以使用深度优先搜索的方法来解决本题。

我们定义一个函数 $dfs(i, j)$，表示从网格图中的第 $i$ 行第 $j$ 列的格子出发，可以捕捞到的最大鱼数。函数 $dfs(i, j)$ 的执行逻辑如下：

我们用一个变量 $cnt$ 来记录当前格子中的鱼的数目，然后将当前格子中的鱼的数目置为 $0$，表示已经捕捞过了。然后我们遍历当前格子的上下左右四个方向，如果某个方向的格子 $(x, y)$ 在网格图内且是水域格子，那么我们就递归调用 $dfs(x, y)$ 函数，将返回值加到 $cnt$ 中。最后返回 $cnt$ 即可。

在主函数中，我们遍历所有的格子 $(i, j)$，如果当前格子是水域格子，那么我们就调用 $dfs(i, j)$ 函数，取返回值的最大值作为答案返回即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是网格图的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def findMaxFish(self, grid: List[List[int]]) -> int:
        def dfs(i: int, j: int) -> int:
            cnt = grid[i][j]
            grid[i][j] = 0
            for a, b in pairwise((-1, 0, 1, 0, -1)):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y]:
                    cnt += dfs(x, y)
            return cnt

        m, n = len(grid), len(grid[0])
        ans = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j]:
                    ans = max(ans, dfs(i, j))
        return ans
```

```java
class Solution {
    private int[][] grid;
    private int m;
    private int n;

    public int findMaxFish(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > 0) {
                    ans = Math.max(ans, dfs(i, j));
                }
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        int cnt = grid[i][j];
        grid[i][j] = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
                cnt += dfs(x, y);
            }
        }
        return cnt;
    }
}
```

```cpp
class Solution {
public:
    int findMaxFish(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int ans = 0;
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            int cnt = grid[i][j];
            grid[i][j] = 0;
            int dirs[5] = {-1, 0, 1, 0, -1};
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y]) {
                    cnt += dfs(x, y);
                }
            }
            return cnt;
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    ans = max(ans, dfs(i, j));
                }
            }
        }
        return ans;
    }
};
```

```go
func findMaxFish(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	dirs := [5]int{-1, 0, 1, 0, -1}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		cnt := grid[i][j]
		grid[i][j] = 0
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0 {
				cnt += dfs(x, y)
			}
		}
		return cnt
	}
	for i := range grid {
		for j := range grid[i] {
			if grid[i][j] > 0 {
				ans = max(ans, dfs(i, j))
			}
		}
	}
	return
}
```

```ts
function findMaxFish(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;

    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number): number => {
        let cnt = grid[i][j];
        grid[i][j] = 0;
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
                cnt += dfs(x, y);
            }
        }
        return cnt;
    };

    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] > 0) {
                ans = Math.max(ans, dfs(i, j));
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
