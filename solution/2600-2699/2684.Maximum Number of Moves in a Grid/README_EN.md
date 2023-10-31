# [2684. Maximum Number of Moves in a Grid](https://leetcode.com/problems/maximum-number-of-moves-in-a-grid)

[中文文档](/solution/2600-2699/2684.Maximum%20Number%20of%20Moves%20in%20a%20Grid/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> <code>m x n</code> matrix <code>grid</code> consisting of <strong>positive</strong> integers.</p>

<p>You can start at <strong>any</strong> cell in the first column of the matrix, and traverse the grid in the following way:</p>

<ul>
	<li>From a cell <code>(row, col)</code>, you can move to any of the cells: <code>(row - 1, col + 1)</code>, <code>(row, col + 1)</code> and <code>(row + 1, col + 1)</code> such that the value of the cell you move to, should be <strong>strictly</strong> bigger than the value of the current cell.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of <strong>moves</strong> that you can perform.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2684.Maximum%20Number%20of%20Moves%20in%20a%20Grid/images/yetgriddrawio-10.png" style="width: 201px; height: 201px;" />
<pre>
<strong>Input:</strong> grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can start at the cell (0, 0) and make the following moves:
- (0, 0) -&gt; (0, 1).
- (0, 1) -&gt; (1, 2).
- (1, 2) -&gt; (2, 3).
It can be shown that it is the maximum number of moves that can be made.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2684.Maximum%20Number%20of%20Moves%20in%20a%20Grid/images/yetgrid4drawio.png" />
<strong>Input:</strong> grid = [[3,2,4],[2,1,9],[1,1,7]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Starting from any cell in the first column we cannot perform any moves.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 1000</code></li>
	<li><code>4 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxMoves(self, grid: List[List[int]]) -> int:
        dirs = ((-1, 1), (0, 1), (1, 1))
        m, n = len(grid), len(grid[0])
        q = deque((i, 0) for i in range(m))
        dist = [[0] * n for _ in range(m)]
        ans = 0
        while q:
            i, j = q.popleft()
            for a, b in dirs:
                x, y = i + a, j + b
                if (
                    0 <= x < m
                    and 0 <= y < n
                    and grid[x][y] > grid[i][j]
                    and dist[x][y] < dist[i][j] + 1
                ):
                    dist[x][y] = dist[i][j] + 1
                    ans = max(ans, dist[x][y])
                    q.append((x, y))
        return ans
```

### **Java**

```java
class Solution {
    public int maxMoves(int[][] grid) {
        int[][] dirs = {{-1, 1}, {0, 1}, {1, 1}};
        int m = grid.length, n = grid[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; ++i) {
            q.offer(new int[] {i, 0});
        }
        int[][] dist = new int[m][n];
        int ans = 0;
        while (!q.isEmpty()) {
            var p = q.poll();
            int i = p[0], j = p[1];
            for (var dir : dirs) {
                int x = i + dir[0], y = j + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > grid[i][j]
                    && dist[x][y] < dist[i][j] + 1) {
                    dist[x][y] = dist[i][j] + 1;
                    ans = Math.max(ans, dist[x][y]);
                    q.offer(new int[] {x, y});
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxMoves(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int dist[m][n];
        memset(dist, 0, sizeof(dist));
        int ans = 0;
        queue<pair<int, int>> q;
        for (int i = 0; i < m; ++i) {
            q.emplace(i, 0);
        }
        int dirs[3][2] = {{-1, 1}, {0, 1}, {1, 1}};
        while (!q.empty()) {
            auto [i, j] = q.front();
            q.pop();
            for (int k = 0; k < 3; ++k) {
                int x = i + dirs[k][0], y = j + dirs[k][1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > grid[i][j] && dist[x][y] < dist[i][j] + 1) {
                    dist[x][y] = dist[i][j] + 1;
                    ans = max(ans, dist[x][y]);
                    q.emplace(x, y);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxMoves(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	dist := make([][]int, m)
	q := [][2]int{}
	for i := range dist {
		dist[i] = make([]int, n)
		q = append(q, [2]int{i, 0})
	}
	dirs := [][2]int{{-1, 1}, {0, 1}, {1, 1}}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		i, j := p[0], p[1]
		for _, dir := range dirs {
			x, y := i+dir[0], j+dir[1]
			if 0 <= x && x < m && 0 <= y && y < n && grid[x][y] > grid[i][j] && dist[x][y] < dist[i][j]+1 {
				dist[x][y] = dist[i][j] + 1
				ans = max(ans, dist[x][y])
				q = append(q, [2]int{x, y})
			}
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
