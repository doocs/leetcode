# [2617. 网格图中最少访问的格子数](https://leetcode.cn/problems/minimum-number-of-visited-cells-in-a-grid)

[English Version](/solution/2600-2699/2617.Minimum%20Number%20of%20Visited%20Cells%20in%20a%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的&nbsp;<code>m x n</code>&nbsp;整数矩阵&nbsp;<code>grid</code>&nbsp;。你一开始的位置在&nbsp;<strong>左上角</strong>&nbsp;格子&nbsp;<code>(0, 0)</code>&nbsp;。</p>

<p>当你在格子&nbsp;<code>(i, j)</code>&nbsp;的时候，你可以移动到以下格子之一：</p>

<ul>
	<li>满足 <code>j &lt; k &lt;= grid[i][j] + j</code>&nbsp;的格子&nbsp;<code>(i, k)</code>&nbsp;（向右移动），或者</li>
	<li>满足 <code>i &lt; k &lt;= grid[i][j] + i</code>&nbsp;的格子&nbsp;<code>(k, j)</code>&nbsp;（向下移动）。</li>
</ul>

<p>请你返回到达 <strong>右下角</strong>&nbsp;格子&nbsp;<code>(m - 1, n - 1)</code>&nbsp;需要经过的最少移动格子数，如果无法到达右下角格子，请你返回&nbsp;<code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2617.Minimum%20Number%20of%20Visited%20Cells%20in%20a%20Grid/images/ex1.png" style="width: 271px; height: 171px;"></p>

<pre><b>输入：</b>grid = [[3,4,2,1],[4,2,3,1],[2,1,0,0],[2,4,0,0]]
<b>输出：</b>4
<b>解释：</b>上图展示了到达右下角格子经过的 4 个格子。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2617.Minimum%20Number%20of%20Visited%20Cells%20in%20a%20Grid/images/ex2.png" style="width: 271px; height: 171px;"></p>

<pre><b>输入：</b>grid = [[3,4,2,1],[4,2,1,1],[2,1,1,0],[3,4,1,0]]
<b>输出：</b>3
<strong>解释：</strong>上图展示了到达右下角格子经过的 3 个格子。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2617.Minimum%20Number%20of%20Visited%20Cells%20in%20a%20Grid/images/ex3.png" style="width: 181px; height: 81px;"></p>

<pre><b>输入：</b>grid = [[2,1,0],[1,0,0]]
<b>输出：</b>-1
<b>解释：</b>无法到达右下角格子。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= grid[i][j] &lt; m * n</code></li>
	<li><code>grid[m - 1][n - 1] == 0</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：优先队列**

我们记网格的行数为 $m$，列数为 $n$，定义 $dist[i][j]$ 表示从坐标 $(0, 0)$ 移动到坐标 $(i, j)$ 的最短距离，初始时 $dist[0][0] = 1$，其它 $dist[i][j]=-1$。

对于每个格子 $(i, j)$，它可以从上边或者左边的格子移动过来。如果是从上边的格子 $(i', j)$ 移动过来，其中 $0 \leq i' \lt i$，那么 $(i', j)$ 需要满足 $grid[i'][j] + i' \geq i$，我们要从这些格子中选择一个距离最近的格子。

因此，我们可以对每一列 $j$ 维护一个优先队列（小根堆），优先队列中每个元素是一个二元组 $(dist[i][j], i)$，表示从坐标 $(0, 0)$ 移动到坐标 $(i, j)$ 的最短距离为 $dist[i][j]$。当我们考虑坐标 $(i, j)$ 时，我们只需要从优先队列中取出队头元素 $(dist[i'][j], i')$，如果 $grid[i'][j] + i' \geq i$，那么就可以从坐标 $(i', j)$ 移动到坐标 $(i, j)$，此时我们就可以更新 $dist[i][j]$ 的值，即 $dist[i][j] = dist[i'][j] + 1$，并将 $(dist[i][j], i)$ 加入到优先队列中。

同理，我们可以对每一行 $i$ 维护一个优先队列，然后进行与上述类似的操作。

最终，我们可以得到从坐标 $(0, 0)$ 移动到坐标 $(m - 1, n - 1)$ 的最短距离 $dist[m - 1][n - 1]$，即为答案。

时间复杂度 $O(m \times n \times \log (m \times n))$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为网格的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumVisitedCells(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        dist = [[-1] * n for _ in range(m)]
        dist[0][0] = 1
        row = [[] for _ in range(m)]
        col = [[] for _ in range(n)]
        for i in range(m):
            for j in range(n):
                while row[i] and grid[i][row[i][0][1]] + row[i][0][1] < j:
                    heappop(row[i])
                if row[i] and (dist[i][j] == -1 or dist[i][j] > row[i][0][0] + 1):
                    dist[i][j] = row[i][0][0] + 1
                while col[j] and grid[col[j][0][1]][j] + col[j][0][1] < i:
                    heappop(col[j])
                if col[j] and (dist[i][j] == -1 or dist[i][j] > col[j][0][0] + 1):
                    dist[i][j] = col[j][0][0] + 1
                if dist[i][j] != -1:
                    heappush(row[i], (dist[i][j], j))
                    heappush(col[j], (dist[i][j], i))
        return dist[-1][-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];
        PriorityQueue<int[]>[] row = new PriorityQueue[m];
        PriorityQueue<int[]>[] col = new PriorityQueue[n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dist[i], -1);
            row[i] = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        }
        for (int i = 0; i < n; ++i) {
            col[i] = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        }
        dist[0][0] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                while (!row[i].isEmpty() && grid[i][row[i].peek()[1]] + row[i].peek()[1] < j) {
                    row[i].poll();
                }
                if (!row[i].isEmpty() && (dist[i][j] == -1 || row[i].peek()[0] + 1 < dist[i][j])) {
                    dist[i][j] = row[i].peek()[0] + 1;
                }
                while (!col[j].isEmpty() && grid[col[j].peek()[1]][j] + col[j].peek()[1] < i) {
                    col[j].poll();
                }
                if (!col[j].isEmpty() && (dist[i][j] == -1 || col[j].peek()[0] + 1 < dist[i][j])) {
                    dist[i][j] = col[j].peek()[0] + 1;
                }
                if (dist[i][j] != -1) {
                    row[i].offer(new int[]{dist[i][j], j});
                    col[j].offer(new int[]{dist[i][j], i});
                }
            }
        }
        return dist[m - 1][n - 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumVisitedCells(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> dist(m, vector<int>(n, -1));
        using pii = pair<int, int>;
        priority_queue<pii, vector<pii>, greater<pii>> row[m];
        priority_queue<pii, vector<pii>, greater<pii>> col[n];
        dist[0][0] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                while (!row[i].empty() && grid[i][row[i].top().second] + row[i].top().second < j) {
                    row[i].pop();
                }
                if (!row[i].empty() && (dist[i][j] == -1 || row[i].top().first + 1 < dist[i][j])) {
                    dist[i][j] = row[i].top().first + 1;
                }
                while (!col[j].empty() && grid[col[j].top().second][j] + col[j].top().second < i) {
                    col[j].pop();
                }
                if (!col[j].empty() && (dist[i][j] == -1 || col[j].top().first + 1 < dist[i][j])) {
                    dist[i][j] = col[j].top().first + 1;
                }
                if (dist[i][j] != -1) {
                    row[i].emplace(dist[i][j], j);
                    col[j].emplace(dist[i][j], i);
                }
            }
        }
        return dist[m - 1][n - 1];
    }
};
```

### **Go**

```go
func minimumVisitedCells(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dist := make([][]int, m)
	row := make([]hp, m)
	col := make([]hp, n)
	for i := range dist {
		dist[i] = make([]int, n)
		for j := range dist[i] {
			dist[i][j] = -1
		}
	}
	dist[0][0] = 1
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for len(row[i]) > 0 && grid[i][row[i][0].second]+row[i][0].second < j {
				heap.Pop(&row[i])
			}
			if len(row[i]) > 0 && (dist[i][j] == -1 || row[i][0].first+1 < dist[i][j]) {
				dist[i][j] = row[i][0].first + 1
			}
			for len(col[j]) > 0 && grid[col[j][0].second][j]+col[j][0].second < i {
				heap.Pop(&col[j])
			}
			if len(col[j]) > 0 && (dist[i][j] == -1 || col[j][0].first+1 < dist[i][j]) {
				dist[i][j] = col[j][0].first + 1
			}
			if dist[i][j] != -1 {
				heap.Push(&row[i], pair{dist[i][j], j})
				heap.Push(&col[j], pair{dist[i][j], i})
			}
		}
	}
	return dist[m-1][n-1]
}

type pair struct {
	first  int
	second int
}

type hp []pair

func (a hp) Len() int      { return len(a) }
func (a hp) Swap(i, j int) { a[i], a[j] = a[j], a[i] }
func (a hp) Less(i, j int) bool {
	return a[i].first < a[j].first || (a[i].first == a[j].first && a[i].second < a[j].second)
}
func (a *hp) Push(x interface{}) { *a = append(*a, x.(pair)) }
func (a *hp) Pop() interface{}   { l := len(*a); t := (*a)[l-1]; *a = (*a)[:l-1]; return t }
```

### **...**

```

```

<!-- tabs:end -->
