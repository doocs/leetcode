---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3286.Find%20a%20Safe%20Walk%20Through%20a%20Grid/README.md
---

<!-- problem:start -->

# [3286. 穿越网格图的安全路径](https://leetcode.cn/problems/find-a-safe-walk-through-a-grid)

[English Version](/solution/3200-3299/3286.Find%20a%20Safe%20Walk%20Through%20a%20Grid/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的二进制矩形&nbsp;<code>grid</code>&nbsp;和一个整数&nbsp;<code>health</code>&nbsp;表示你的健康值。</p>

<p>你开始于矩形的左上角&nbsp;<code>(0, 0)</code>&nbsp;，你的目标是矩形的右下角&nbsp;<code>(m - 1, n - 1)</code>&nbsp;。</p>

<p>你可以在矩形中往上下左右相邻格子移动，但前提是你的健康值始终是 <b>正数</b>&nbsp;。</p>

<p>对于格子&nbsp;<code>(i, j)</code>&nbsp;，如果&nbsp;<code>grid[i][j] = 1</code>&nbsp;，那么这个格子视为 <strong>不安全</strong>&nbsp;的，会使你的健康值减少 1 。</p>

<p>如果你可以到达最终的格子，请你返回&nbsp;<code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。</p>

<p><b>注意</b>&nbsp;，当你在最终格子的时候，你的健康值也必须为<strong>&nbsp;正数</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]], health = 1</span></p>

<p><span class="example-io"><b>输出：</b>true</span></p>

<p><b>解释：</b></p>

<p>沿着下图中灰色格子走，可以安全到达最终的格子。</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3286.Find%20a%20Safe%20Walk%20Through%20a%20Grid/images/3868_examples_1drawio.png" style="width: 301px; height: 121px;" /></div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[0,1,1,0,0,0],[1,0,1,0,0,0],[0,1,1,1,0,1],[0,0,1,0,1,0]], health = 3</span></p>

<p><span class="example-io"><b>输出：</b>false</span></p>

<p><b>解释：</b></p>

<p>健康值最少为 4 才能安全到达最后的格子。</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3286.Find%20a%20Safe%20Walk%20Through%20a%20Grid/images/3868_examples_2drawio.png" style="width: 361px; height: 161px;" /></div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[1,1,1],[1,0,1],[1,1,1]], health = 5</span></p>

<p><span class="example-io"><b>输出：</b>true</span></p>

<p><b>解释：</b></p>

<p>沿着下图中灰色格子走，可以安全到达最终的格子。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3286.Find%20a%20Safe%20Walk%20Through%20a%20Grid/images/3868_examples_3drawio.png" style="width: 181px; height: 121px;" /></p>

<p>任何不经过格子&nbsp;<code>(1, 1)</code>&nbsp;的路径都是不安全的，因为你的健康值到达最终格子时，都会小于等于 0 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
<li><code>2 <= m * n</code></li>
	<li><code>1 &lt;= health &lt;= m + n</code></li>
	<li><code>grid[i][j]</code>&nbsp;要么是 0 ，要么是 1 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

我们定义一个二维数组 $\textit{dist}$，其中 $\textit{dist}[i][j]$ 表示从左上角到达 $(i, j)$ 位置的最小健康值。初始时，我们将 $\textit{dist}[0][0]$ 设为 $\textit{grid}[0][0]$，并将 $(0, 0)$ 加入队列 $\textit{q}$ 中。

随后我们不断取出队列中的元素 $(x, y)$，并尝试向四个方向移动。如果移动到了一个合法的位置 $(nx, ny)$，且从 $(x, y)$ 移动到 $(nx, ny)$ 的健康值消耗更小，那么我们就可以更新 $\textit{dist}[nx][ny] = \textit{dist}[x][y] + \textit{grid}[nx][ny]$，并将 $(nx, ny)$ 加入队列 $\textit{q}$ 中。

最终，当队列为空时，我们就可以得到 $\textit{dist}[m-1][n-1]$，即从左上角到达右下角的最小健康值。如果这个值小于 $\textit{health}$，那么我们就可以到达右下角，否则我们无法到达。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩形的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findSafeWalk(self, grid: List[List[int]], health: int) -> bool:
        m, n = len(grid), len(grid[0])
        dist = [[inf] * n for _ in range(m)]
        dist[0][0] = grid[0][0]
        q = deque([(0, 0)])
        dirs = (-1, 0, 1, 0, -1)
        while q:
            x, y = q.popleft()
            for a, b in pairwise(dirs):
                nx, ny = x + a, y + b
                if (
                    0 <= nx < m
                    and 0 <= ny < n
                    and dist[nx][ny] > dist[x][y] + grid[nx][ny]
                ):
                    dist[nx][ny] = dist[x][y] + grid[nx][ny]
                    q.append((nx, ny))
        return dist[-1][-1] < health
```

#### Java

```java
class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[0][0] = grid.get(0).get(0);
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        final int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0], y = curr[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i];
                int ny = y + dirs[i + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n
                    && dist[nx][ny] > dist[x][y] + grid.get(nx).get(ny)) {
                    dist[nx][ny] = dist[x][y] + grid.get(nx).get(ny);
                    q.offer(new int[] {nx, ny});
                }
            }
        }
        return dist[m - 1][n - 1] < health;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool findSafeWalk(vector<vector<int>>& grid, int health) {
        int m = grid.size();
        int n = grid[0].size();
        vector<vector<int>> dist(m, vector<int>(n, INT_MAX));
        dist[0][0] = grid[0][0];
        queue<pair<int, int>> q;
        q.emplace(0, 0);
        int dirs[5] = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            auto [x, y] = q.front();
            q.pop();
            for (int i = 0; i < 4; ++i) {
                int nx = x + dirs[i];
                int ny = y + dirs[i + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && dist[nx][ny] > dist[x][y] + grid[nx][ny]) {
                    dist[nx][ny] = dist[x][y] + grid[nx][ny];
                    q.emplace(nx, ny);
                }
            }
        }
        return dist[m - 1][n - 1] < health;
    }
};
```

#### Go

```go
func findSafeWalk(grid [][]int, health int) bool {
	m, n := len(grid), len(grid[0])
	dist := make([][]int, m)
	for i := range dist {
		dist[i] = make([]int, n)
		for j := range dist[i] {
			dist[i][j] = math.MaxInt32
		}
	}
	dist[0][0] = grid[0][0]
	q := [][2]int{{0, 0}}
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		curr := q[0]
		q = q[1:]
		x, y := curr[0], curr[1]
		for i := 0; i < 4; i++ {
			nx, ny := x+dirs[i], y+dirs[i+1]
			if nx >= 0 && nx < m && ny >= 0 && ny < n && dist[nx][ny] > dist[x][y]+grid[nx][ny] {
				dist[nx][ny] = dist[x][y] + grid[nx][ny]
				q = append(q, [2]int{nx, ny})
			}
		}
	}
	return dist[m-1][n-1] < health
}
```

#### TypeScript

```ts
function findSafeWalk(grid: number[][], health: number): boolean {
    const m = grid.length;
    const n = grid[0].length;
    const dist: number[][] = Array.from({ length: m }, () => Array(n).fill(Infinity));
    dist[0][0] = grid[0][0];
    const q: [number, number][] = [[0, 0]];
    const dirs = [-1, 0, 1, 0, -1];
    while (q.length > 0) {
        const [x, y] = q.shift()!;
        for (let i = 0; i < 4; i++) {
            const nx = x + dirs[i];
            const ny = y + dirs[i + 1];
            if (
                nx >= 0 &&
                nx < m &&
                ny >= 0 &&
                ny < n &&
                dist[nx][ny] > dist[x][y] + grid[nx][ny]
            ) {
                dist[nx][ny] = dist[x][y] + grid[nx][ny];
                q.push([nx, ny]);
            }
        }
    }
    return dist[m - 1][n - 1] < health;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
