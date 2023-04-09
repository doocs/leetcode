# [2617. Minimum Number of Visited Cells in a Grid](https://leetcode.com/problems/minimum-number-of-visited-cells-in-a-grid)

[中文文档](/solution/2600-2699/2617.Minimum%20Number%20of%20Visited%20Cells%20in%20a%20Grid/README.md)

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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumVisitedCells(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        row = [0] * m
        col = [0] * n
        q = deque([(1, 0, 0)])
        while q:
            dist, i, j = q.popleft()
            if i == m - 1 and j == n - 1:
                return dist
            for k in range(max(row[i], j) + 1, min(n, j + grid[i][j] + 1)):
                q.append((dist + 1, i, k))
                row[i] = k
            for k in range(max(col[j], i) + 1, min(m, i + grid[i][j] + 1)):
                q.append((dist + 1, k, j))
                col[j] = k
        return -1
```

### **Java**

```java
class Solution {
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {1, 0, 0});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[1], j = p[2], dist = p[0];
            if (i == m - 1 && j == n - 1) {
                return dist;
            }
            int k = Math.max(row[i], j) + 1;
            for (; k < Math.min(n, j + grid[i][j] + 1); ++k) {
                q.offer(new int[] {dist + 1, i, k});
                row[i] = k;
            }
            k = Math.max(col[j], i) + 1;
            for (; k < Math.min(m, i + grid[i][j] + 1); ++k) {
                q.offer(new int[] {dist + 1, k, j});
                col[j] = k;
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
    int minimumVisitedCells(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int row[m], col[n];
        memset(row, 0, sizeof(row));
        memset(col, 0, sizeof(col));
        queue<tuple<int, int, int>> q;
        q.emplace(1, 0, 0);
        while (!q.empty()) {
            auto [dist, i, j] = q.front();
            q.pop();
            if (i == m - 1 && j == n - 1) {
                return dist;
            }
            for (int k = max(row[i], j) + 1; k < min(n, j + grid[i][j] + 1); ++k) {
                q.emplace(dist + 1, i, k);
                row[i] = k;
            }
            for (int k = max(col[j], i) + 1; k < min(m, i + grid[i][j] + 1); ++k) {
                q.emplace(dist + 1, k, j);
                col[j] = k;
            }
        }
        return -1;
    }
};
```

### **Go**

```go
func minimumVisitedCells(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	row := make([]int, m)
	col := make([]int, n)
	q := [][3]int{{1, 0, 0}}
	for len(q) > 0 {
		p := q[0]
		dist, i, j := p[0], p[1], p[2]
		if i == m-1 && j == n-1 {
			return dist
		}
		q = q[1:]
		for k := max(row[i], j) + 1; k < min(n, j+grid[i][j]+1); k++ {
			q = append(q, [3]int{dist + 1, i, k})
			row[i] = k
		}
		for k := max(col[j], i) + 1; k < min(m, i+grid[i][j]+1); k++ {
			q = append(q, [3]int{dist + 1, k, j})
			col[j] = k
		}
	}
	return -1
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
