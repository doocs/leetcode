# [2617. Minimum Number of Visited Cells in a Grid](https://leetcode.com/problems/minimum-number-of-visited-cells-in-a-grid)

[中文文档](/solution/2600-2699/2617.Minimum%20Number%20of%20Visited%20Cells%20in%20a%20Grid/README.md)

<!-- tags:Stack,Breadth-First Search,Union Find,Array,Dynamic Programming,Matrix,Monotonic Stack,Heap (Priority Queue) -->

<!-- difficulty:Hard -->

## Description

<p>You are given a <strong>0-indexed</strong> <code>m x n</code> integer matrix <code>grid</code>. Your initial position is at the <strong>top-left</strong> cell <code>(0, 0)</code>.</p>

<p>Starting from the cell <code>(i, j)</code>, you can move to one of the following cells:</p>

<ul>
	<li>Cells <code>(i, k)</code> with <code>j &lt; k &lt;= grid[i][j] + j</code> (rightward movement), or</li>
	<li>Cells <code>(k, j)</code> with <code>i &lt; k &lt;= grid[i][j] + i</code> (downward movement).</li>
</ul>

<p>Return <em>the minimum number of cells you need to visit to reach the <strong>bottom-right</strong> cell</em> <code>(m - 1, n - 1)</code>. If there is no valid path, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2617.Minimum%20Number%20of%20Visited%20Cells%20in%20a%20Grid/images/ex1.png" style="width: 271px; height: 171px;" />
<pre>
<strong>Input:</strong> grid = [[3,4,2,1],[4,2,3,1],[2,1,0,0],[2,4,0,0]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The image above shows one of the paths that visits exactly 4 cells.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2617.Minimum%20Number%20of%20Visited%20Cells%20in%20a%20Grid/images/ex2.png" style="width: 271px; height: 171px;" />
<pre>
<strong>Input:</strong> grid = [[3,4,2,1],[4,2,1,1],[2,1,1,0],[3,4,1,0]]
<strong>Output:</strong> 3
<strong>Explanation: </strong>The image above shows one of the paths that visits exactly 3 cells.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2617.Minimum%20Number%20of%20Visited%20Cells%20in%20a%20Grid/images/ex3.png" style="width: 181px; height: 81px;" />
<pre>
<strong>Input:</strong> grid = [[2,1,0],[1,0,0]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> It can be proven that no path exists.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= grid[i][j] &lt; m * n</code></li>
	<li><code>grid[m - 1][n - 1] == 0</code></li>
</ul>

## Solutions

### Solution 1: Priority Queue

Let's denote the number of rows of the grid as $m$ and the number of columns as $n$. Define $dist[i][j]$ to be the shortest distance from the coordinate $(0, 0)$ to the coordinate $(i, j)$. Initially, $dist[0][0]=1$ and $dist[i][j]=-1$ for all other $i$ and $j$.

For each grid $(i, j)$, it can come from the grid above or the grid on the left. If it comes from the grid above $(i', j)$, where $0 \leq i' \lt i$, then $(i', j)$ must satisfy $grid[i'][j] + i' \geq i$. We need to select from these grids the one that is closest.

Therefore, we maintain a priority queue (min-heap) for each column $j$. Each element of the priority queue is a pair $(dist[i][j], i)$, which represents that the shortest distance from the coordinate $(0, 0)$ to the coordinate $(i, j)$ is $dist[i][j]$. When we consider the coordinate $(i, j)$, we only need to take out the head element $(dist[i'][j], i')$ of the priority queue. If $grid[i'][j] + i' \geq i$, we can move from the coordinate $(i', j)$ to the coordinate $(i, j)$. At this time, we can update the value of $dist[i][j]$, that is, $dist[i][j] = dist[i'][j] + 1$, and add $(dist[i][j], i)$ to the priority queue.

Similarly, we can maintain a priority queue for each row $i$ and perform a similar operation.

Finally, we can obtain the shortest distance from the coordinate $(0, 0)$ to the coordinate $(m - 1, n - 1)$, that is, $dist[m - 1][n - 1]$, which is the answer.

The time complexity is $O(m \times n \times \log (m \times n))$ and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the number of rows and columns of the grid, respectively.

<!-- tabs:start -->

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
                    row[i].offer(new int[] {dist[i][j], j});
                    col[j].offer(new int[] {dist[i][j], i});
                }
            }
        }
        return dist[m - 1][n - 1];
    }
}
```

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
func (a *hp) Push(x any) { *a = append(*a, x.(pair)) }
func (a *hp) Pop() any   { l := len(*a); t := (*a)[l-1]; *a = (*a)[:l-1]; return t }
```

<!-- tabs:end -->

<!-- end -->
