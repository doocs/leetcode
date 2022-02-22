# [1368. Minimum Cost to Make at Least One Valid Path in a Grid](https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid)

[中文文档](/solution/1300-1399/1368.Minimum%20Cost%20to%20Make%20at%20Least%20One%20Valid%20Path%20in%20a%20Grid/README.md)

## Description

Given a <em>m</em> x <em>n</em> <code>grid</code>. Each cell of the <code>grid</code> has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of <code>grid[i][j]</code> can be:

<ul>
	<li><strong>1</strong> which means go to the cell to the right. (i.e go from <code>grid[i][j]</code> to <code>grid[i][j + 1]</code>)</li>
	<li><strong>2</strong> which means go to the cell to the left. (i.e go from <code>grid[i][j]</code> to <code>grid[i][j - 1]</code>)</li>
	<li><strong>3</strong> which means go to the lower cell. (i.e go from <code>grid[i][j]</code> to <code>grid[i + 1][j]</code>)</li>
	<li><strong>4</strong> which means go to the upper cell. (i.e go from <code>grid[i][j]</code> to <code>grid[i - 1][j]</code>)</li>
</ul>

<p>Notice&nbsp;that there could be some <strong>invalid signs</strong> on the cells of the <code>grid</code> which points outside the <code>grid</code>.</p>

<p>You will initially start at the upper left cell <code>(0,0)</code>. A valid path in the grid is a path which starts from the upper left&nbsp;cell <code>(0,0)</code> and ends at the bottom-right&nbsp;cell <code>(m - 1, n - 1)</code> following the signs on the grid. The valid path <strong>doesn&#39;t have to be the shortest</strong>.</p>

<p>You can modify the sign on a cell with <code>cost = 1</code>. You can modify the sign on a cell <strong>one time only</strong>.</p>

<p>Return <em>the minimum cost</em> to make the grid have at least one valid path.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1368.Minimum%20Cost%20to%20Make%20at%20Least%20One%20Valid%20Path%20in%20a%20Grid/images/grid1.png" style="width: 542px; height: 528px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> You will start at point (0, 0).
The path to (3, 3) is as follows. (0, 0) --&gt; (0, 1) --&gt; (0, 2) --&gt; (0, 3) change the arrow to down with cost = 1 --&gt; (1, 3) --&gt; (1, 2) --&gt; (1, 1) --&gt; (1, 0) change the arrow to down with cost = 1 --&gt; (2, 0) --&gt; (2, 1) --&gt; (2, 2) --&gt; (2, 3) change the arrow to down with cost = 1 --&gt; (3, 3)
The total cost = 3.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1368.Minimum%20Cost%20to%20Make%20at%20Least%20One%20Valid%20Path%20in%20a%20Grid/images/grid2.png" style="width: 419px; height: 408px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,3],[3,2,2],[1,1,4]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> You can follow the path from (0, 0) to (2, 2).
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1368.Minimum%20Cost%20to%20Make%20at%20Least%20One%20Valid%20Path%20in%20a%20Grid/images/grid3.png" style="width: 314px; height: 302px;" />
<pre>
<strong>Input:</strong> grid = [[1,2],[4,3]]
<strong>Output:</strong> 1
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> grid = [[2,2,2],[2,2,2]]
<strong>Output:</strong> 3
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> grid = [[4]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minCost(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        dirs = [[0, 0], [0, 1], [0, -1], [1, 0], [-1, 0]]
        q = deque([(0, 0, 0)])
        vis = set()
        while q:
            i, j, d = q.popleft()
            if (i, j) in vis:
                continue
            vis.add((i, j))
            if i == m - 1 and j == n - 1:
                return d
            for k in range(1, 5):
                x, y = i + dirs[k][0], j + dirs[k][1]
                if 0 <= x < m and 0 <= y < n:
                    if grid[i][j] == k:
                        q.appendleft((x, y, d))
                    else:
                        q.append((x, y, d + 1))
        return -1
```

### **Java**

```java
class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 0});
        int[][] dirs = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1], d = p[2];
            if (i == m - 1 && j == n - 1) {
                return d;
            }
            if (vis[i][j]) {
                continue;
            }
            vis[i][j] = true;
            for (int k = 1; k <= 4; ++k) {
                int x = i + dirs[k][0], y = j + dirs[k][1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    if (grid[i][j] == k) {
                        q.offerFirst(new int[]{x, y, d});
                    } else {
                        q.offer(new int[]{x, y, d + 1});
                    }
                }
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minCost(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<bool>> vis(m, vector<bool>(n));
        vector<vector<int>> dirs = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        deque<pair<int, int>> q;
        q.push_back({0, 0});
        while (!q.empty())
        {
            auto p = q.front();
            q.pop_front();
            int i = p.first / n, j = p.first % n, d = p.second;
            if (i == m - 1 && j == n - 1) return d;
            if (vis[i][j]) continue;
            vis[i][j] = true;
            for (int k = 1; k <= 4; ++k)
            {
                int x = i + dirs[k][0], y = j + dirs[k][1];
                if (x >= 0 && x < m && y >= 0 && y < n)
                {
                    if (grid[i][j] == k) q.push_front({x * n + y, d});
                    else q.push_back({x * n + y, d + 1});
                }
            }
        }
        return -1;
    }
};
```

### **Go**

```go
func minCost(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	q := doublylinkedlist.New()
	q.Add([]int{0, 0, 0})
	dirs := [][]int{{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}}
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	for !q.Empty() {
		v, _ := q.Get(0)
		p := v.([]int)
		q.Remove(0)
		i, j, d := p[0], p[1], p[2]
		if i == m-1 && j == n-1 {
			return d
		}
		if vis[i][j] {
			continue
		}
		vis[i][j] = true
		for k := 1; k <= 4; k++ {
			x, y := i+dirs[k][0], j+dirs[k][1]
			if x >= 0 && x < m && y >= 0 && y < n {
				if grid[i][j] == k {
					q.Insert(0, []int{x, y, d})
				} else {
					q.Add([]int{x, y, d + 1})
				}
			}
		}
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
