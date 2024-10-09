---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2146.K%20Highest%20Ranked%20Items%20Within%20a%20Price%20Range/README.md
rating: 1836
source: 第 70 场双周赛 Q3
tags:
    - 广度优先搜索
    - 数组
    - 矩阵
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [2146. 价格范围内最高排名的 K 样物品](https://leetcode.cn/problems/k-highest-ranked-items-within-a-price-range)

[English Version](/solution/2100-2199/2146.K%20Highest%20Ranked%20Items%20Within%20a%20Price%20Range/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>grid</code>&nbsp;，它的大小为&nbsp;<code>m x n</code>&nbsp;，表示一个商店中物品的分布图。数组中的整数含义为：</p>

<ul>
	<li><code>0</code>&nbsp;表示无法穿越的一堵墙。</li>
	<li><code>1</code>&nbsp;表示可以自由通过的一个空格子。</li>
	<li>所有其他正整数表示该格子内的一样物品的价格。你可以自由经过这些格子。</li>
</ul>

<p>从一个格子走到上下左右相邻格子花费&nbsp;<code>1</code>&nbsp;步。</p>

<p>同时给你一个整数数组&nbsp;<code>pricing</code> 和&nbsp;<code>start</code>&nbsp;，其中&nbsp;<code>pricing = [low, high]</code> 且&nbsp;<code>start = [row, col]</code>&nbsp;，表示你开始位置为&nbsp;<code>(row, col)</code>&nbsp;，同时你只对物品价格在<strong>&nbsp;闭区间</strong>&nbsp;<code>[low, high]</code>&nbsp;之内的物品感兴趣。同时给你一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>你想知道给定范围 <strong>内</strong>&nbsp;且 <strong>排名最高</strong>&nbsp;的 <code>k</code>&nbsp;件物品的 <strong>位置</strong>&nbsp;。排名按照优先级从高到低的以下规则制定：</p>

<ol>
	<li>距离：定义为从&nbsp;<code>start</code>&nbsp;到一件物品的最短路径需要的步数（<strong>较近</strong>&nbsp;距离的排名更高）。</li>
	<li>价格：<strong>较低</strong>&nbsp;价格的物品有更高优先级，但只考虑在给定范围之内的价格。</li>
	<li>行坐标：<strong>较小</strong>&nbsp;行坐标的有更高优先级。</li>
	<li>列坐标：<strong>较小</strong>&nbsp;列坐标的有更高优先级。</li>
</ol>

<p>请你返回给定价格内排名最高的 <code>k</code>&nbsp;件物品的坐标，将它们按照排名排序后返回。如果给定价格内少于 <code>k</code>&nbsp;件物品，那么请将它们的坐标&nbsp;<strong>全部</strong>&nbsp;返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2146.K%20Highest%20Ranked%20Items%20Within%20a%20Price%20Range/images/example1drawio.png" style="width: 200px; height: 151px;"></p>

<pre><b>输入：</b>grid = [[1,2,0,1],[1,3,0,1],[0,2,5,1]], pricing = [2,5], start = [0,0], k = 3
<b>输出：</b>[[0,1],[1,1],[2,1]]
<b>解释：</b>起点为 (0,0) 。
价格范围为 [2,5] ，我们可以选择的物品坐标为 (0,1)，(1,1)，(2,1) 和 (2,2) 。
这些物品的排名为：
- (0,1) 距离为 1
- (1,1) 距离为 2
- (2,1) 距离为 3
- (2,2) 距离为 4
所以，给定价格范围内排名最高的 3 件物品的坐标为 (0,1)，(1,1) 和 (2,1) 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2146.K%20Highest%20Ranked%20Items%20Within%20a%20Price%20Range/images/example2drawio1.png" style="width: 200px; height: 151px;"></p>

<pre><b>输入：</b>grid = [[1,2,0,1],[1,3,3,1],[0,2,5,1]], pricing = [2,3], start = [2,3], k = 2
<b>输出：</b>[[2,1],[1,2]]
<b>解释：</b>起点为 (2,3) 。
价格范围为 [2,3] ，我们可以选择的物品坐标为 (0,1)，(1,1)，(1,2) 和 (2,1) 。
这些物品的排名为： 
- (2,1) 距离为 2 ，价格为 2
- (1,2) 距离为 2 ，价格为 3
- (1,1) 距离为 3
- (0,1) 距离为 4
所以，给定价格范围内排名最高的 2 件物品的坐标为 (2,1) 和 (1,2) 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2146.K%20Highest%20Ranked%20Items%20Within%20a%20Price%20Range/images/example3.png" style="width: 149px; height: 150px;"></p>

<pre><b>输入：</b>grid = [[1,1,1],[0,0,1],[2,3,4]], pricing = [2,3], start = [0,0], k = 3
<b>输出：</b>[[2,1],[2,0]]
<b>解释：</b>起点为 (0,0) 。
价格范围为 [2,3] ，我们可以选择的物品坐标为 (2,0) 和 (2,1) 。
这些物品的排名为：
- (2,1) 距离为 5
- (2,0) 距离为 6
所以，给定价格范围内排名最高的 2 件物品的坐标为 (2,1) 和 (2,0) 。
注意，k = 3 但给定价格范围内只有 2 件物品。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>pricing.length == 2</code></li>
	<li><code>2 &lt;= low &lt;= high &lt;= 10<sup>5</sup></code></li>
	<li><code>start.length == 2</code></li>
	<li><code>0 &lt;= row &lt;= m - 1</code></li>
	<li><code>0 &lt;= col &lt;= n - 1</code></li>
	<li><code>grid[row][col] &gt; 0</code></li>
	<li><code>1 &lt;= k &lt;= m * n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS + 排序

我们可以从 $(\textit{row}, \textit{col})$ 出发，使用广度优先搜索找到所有价格在 $[\textit{low}, \textit{high}]$ 范围内的物品，将这些物品的距离、价格、行坐标和列坐标存入数组 $\textit{pq}$ 中。

最后对 $\textit{pq}$ 按照距离、价格、行坐标和列坐标的顺序排序，取前 $k$ 个物品的坐标返回。

时间复杂度 $O(m \times n \times \log (m \times n))$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是二维数组 $\textit{grid}$ 的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def highestRankedKItems(
        self, grid: List[List[int]], pricing: List[int], start: List[int], k: int
    ) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        row, col = start
        low, high = pricing
        q = deque([(row, col)])
        pq = []
        if low <= grid[row][col] <= high:
            pq.append((0, grid[row][col], row, col))
        grid[row][col] = 0
        dirs = (-1, 0, 1, 0, -1)
        step = 0
        while q:
            step += 1
            for _ in range(len(q)):
                x, y = q.popleft()
                for a, b in pairwise(dirs):
                    nx, ny = x + a, y + b
                    if 0 <= nx < m and 0 <= ny < n and grid[nx][ny] > 0:
                        if low <= grid[nx][ny] <= high:
                            pq.append((step, grid[nx][ny], nx, ny))
                        grid[nx][ny] = 0
                        q.append((nx, ny))
        pq.sort()
        return [list(x[2:]) for x in pq[:k]]
```

#### Java

```java
class Solution {
    public List<List<Integer>> highestRankedKItems(
        int[][] grid, int[] pricing, int[] start, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int row = start[0], col = start[1];
        int low = pricing[0], high = pricing[1];
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {row, col});
        List<int[]> pq = new ArrayList<>();
        if (low <= grid[row][col] && grid[row][col] <= high) {
            pq.add(new int[] {0, grid[row][col], row, col});
        }
        grid[row][col] = 0;
        final int[] dirs = {-1, 0, 1, 0, -1};
        for (int step = 1; !q.isEmpty(); ++step) {
            for (int size = q.size(); size > 0; --size) {
                int[] curr = q.poll();
                int x = curr[0], y = curr[1];
                for (int j = 0; j < 4; j++) {
                    int nx = x + dirs[j];
                    int ny = y + dirs[j + 1];
                    if (0 <= nx && nx < m && 0 <= ny && ny < n && grid[nx][ny] > 0) {
                        if (low <= grid[nx][ny] && grid[nx][ny] <= high) {
                            pq.add(new int[] {step, grid[nx][ny], nx, ny});
                        }
                        grid[nx][ny] = 0;
                        q.offer(new int[] {nx, ny});
                    }
                }
            }
        }

        pq.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            if (a[2] != b[2]) return Integer.compare(a[2], b[2]);
            return Integer.compare(a[3], b[3]);
        });

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < Math.min(k, pq.size()); i++) {
            ans.add(List.of(pq.get(i)[2], pq.get(i)[3]));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> highestRankedKItems(vector<vector<int>>& grid, vector<int>& pricing, vector<int>& start, int k) {
        int m = grid.size(), n = grid[0].size();
        int row = start[0], col = start[1];
        int low = pricing[0], high = pricing[1];
        queue<pair<int, int>> q;
        q.push({row, col});
        vector<tuple<int, int, int, int>> pq;
        if (low <= grid[row][col] && grid[row][col] <= high) {
            pq.push_back({0, grid[row][col], row, col});
        }
        grid[row][col] = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int step = 1; q.size(); ++step) {
            int sz = q.size();
            for (int i = 0; i < sz; ++i) {
                auto [x, y] = q.front();
                q.pop();
                for (int j = 0; j < 4; ++j) {
                    int nx = x + dirs[j];
                    int ny = y + dirs[j + 1];
                    if (0 <= nx && nx < m && 0 <= ny && ny < n && grid[nx][ny] > 0) {
                        if (low <= grid[nx][ny] && grid[nx][ny] <= high) {
                            pq.push_back({step, grid[nx][ny], nx, ny});
                        }
                        grid[nx][ny] = 0;
                        q.push({nx, ny});
                    }
                }
            }
        }
        sort(pq.begin(), pq.end());
        vector<vector<int>> ans;
        for (int i = 0; i < min(k, (int) pq.size()); ++i) {
            ans.push_back({get<2>(pq[i]), get<3>(pq[i])});
        }
        return ans;
    }
};
```

#### Go

```go
func highestRankedKItems(grid [][]int, pricing []int, start []int, k int) (ans [][]int) {
	m, n := len(grid), len(grid[0])
	row, col := start[0], start[1]
	low, high := pricing[0], pricing[1]
	q := [][2]int{{row, col}}
	pq := [][]int{}
	if low <= grid[row][col] && grid[row][col] <= high {
		pq = append(pq, []int{0, grid[row][col], row, col})
	}
	grid[row][col] = 0
	dirs := [5]int{-1, 0, 1, 0, -1}
	for step := 1; len(q) > 0; step++ {
		for sz := len(q); sz > 0; sz-- {
			x, y := q[0][0], q[0][1]
			q = q[1:]
			for j := 0; j < 4; j++ {
				nx, ny := x+dirs[j], y+dirs[j+1]
				if nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] > 0 {
					if low <= grid[nx][ny] && grid[nx][ny] <= high {
						pq = append(pq, []int{step, grid[nx][ny], nx, ny})
					}
					grid[nx][ny] = 0
					q = append(q, [2]int{nx, ny})
				}
			}
		}
	}
	sort.Slice(pq, func(i, j int) bool {
		a, b := pq[i], pq[j]
		if a[0] != b[0] {
			return a[0] < b[0]
		}
		if a[1] != b[1] {
			return a[1] < b[1]
		}
		if a[2] != b[2] {
			return a[2] < b[2]
		}
		return a[3] < b[3]
	})
	for i := 0; i < len(pq) && i < k; i++ {
		ans = append(ans, pq[i][2:])
	}
	return
}
```

#### TypeScript

```ts
function highestRankedKItems(
    grid: number[][],
    pricing: number[],
    start: number[],
    k: number,
): number[][] {
    const [m, n] = [grid.length, grid[0].length];
    const [row, col] = start;
    const [low, high] = pricing;
    let q: [number, number][] = [[row, col]];
    const pq: [number, number, number, number][] = [];
    if (low <= grid[row][col] && grid[row][col] <= high) {
        pq.push([0, grid[row][col], row, col]);
    }
    grid[row][col] = 0;
    const dirs = [-1, 0, 1, 0, -1];
    for (let step = 1; q.length > 0; ++step) {
        const nq: [number, number][] = [];
        for (const [x, y] of q) {
            for (let j = 0; j < 4; j++) {
                const nx = x + dirs[j];
                const ny = y + dirs[j + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] > 0) {
                    if (low <= grid[nx][ny] && grid[nx][ny] <= high) {
                        pq.push([step, grid[nx][ny], nx, ny]);
                    }
                    grid[nx][ny] = 0;
                    nq.push([nx, ny]);
                }
            }
        }
        q = nq;
    }
    pq.sort((a, b) => {
        if (a[0] !== b[0]) return a[0] - b[0];
        if (a[1] !== b[1]) return a[1] - b[1];
        if (a[2] !== b[2]) return a[2] - b[2];
        return a[3] - b[3];
    });
    const ans: number[][] = [];
    for (let i = 0; i < Math.min(k, pq.length); i++) {
        ans.push([pq[i][2], pq[i][3]]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
