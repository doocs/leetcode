# [2304. Minimum Path Cost in a Grid](https://leetcode.com/problems/minimum-path-cost-in-a-grid)

[中文文档](/solution/2300-2399/2304.Minimum%20Path%20Cost%20in%20a%20Grid/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> <code>m x n</code> integer matrix <code>grid</code> consisting of <strong>distinct</strong> integers from <code>0</code> to <code>m * n - 1</code>. You can move in this matrix from a cell to any other cell in the <strong>next</strong> row. That is, if you are in cell <code>(x, y)</code> such that <code>x &lt; m - 1</code>, you can move to any of the cells <code>(x + 1, 0)</code>, <code>(x + 1, 1)</code>, ..., <code>(x + 1, n - 1)</code>. <strong>Note</strong> that it is not possible to move from cells in the last row.</p>

<p>Each possible move has a cost given by a <strong>0-indexed</strong> 2D array <code>moveCost</code> of size <code>(m * n) x n</code>, where <code>moveCost[i][j]</code> is the cost of moving from a cell with value <code>i</code> to a cell in column <code>j</code> of the next row. The cost of moving from cells in the last row of <code>grid</code> can be ignored.</p>

<p>The cost of a path in <code>grid</code> is the <strong>sum</strong> of all values of cells visited plus the <strong>sum</strong> of costs of all the moves made. Return <em>the <strong>minimum</strong> cost of a path that starts from any cell in the <strong>first</strong> row and ends at any cell in the <strong>last</strong> row.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2304.Minimum%20Path%20Cost%20in%20a%20Grid/images/griddrawio-2.png" style="width: 301px; height: 281px;" />
<pre>
<strong>Input:</strong> grid = [[5,3],[4,0],[2,1]], moveCost = [[9,8],[1,5],[10,12],[18,6],[2,4],[14,3]]
<strong>Output:</strong> 17
<strong>Explanation: </strong>The path with the minimum possible cost is the path 5 -&gt; 0 -&gt; 1.
- The sum of the values of cells visited is 5 + 0 + 1 = 6.
- The cost of moving from 5 to 0 is 3.
- The cost of moving from 0 to 1 is 8.
So the total cost of the path is 6 + 3 + 8 = 17.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[5,1,2],[4,0,3]], moveCost = [[12,10,15],[20,23,8],[21,7,1],[8,1,13],[9,10,25],[5,3,2]]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The path with the minimum possible cost is the path 2 -&gt; 3.
- The sum of the values of cells visited is 2 + 3 = 5.
- The cost of moving from 2 to 3 is 1.
So the total cost of this path is 5 + 1 = 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 50</code></li>
	<li><code>grid</code> consists of distinct integers from <code>0</code> to <code>m * n - 1</code>.</li>
	<li><code>moveCost.length == m * n</code></li>
	<li><code>moveCost[i].length == n</code></li>
	<li><code>1 &lt;= moveCost[i][j] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minPathCost(self, grid: List[List[int]], moveCost: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        f = grid[0]
        for i in range(1, m):
            g = [inf] * n
            for j in range(n):
                for k in range(n):
                    g[j] = min(g[j], f[k] + moveCost[grid[i - 1][k]][j] + grid[i][j])
            f = g
        return min(f)
```

### **Java**

```java
class Solution {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length, n = grid[0].length;
        int[] f = grid[0];
        final int inf = 1 << 30;
        for (int i = 1; i < m; ++i) {
            int[] g = new int[n];
            Arrays.fill(g, inf);
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    g[j] = Math.min(g[j], f[k] + moveCost[grid[i - 1][k]][j] + grid[i][j]);
                }
            }
            f = g;
        }

        // return Arrays.stream(f).min().getAsInt();
        int ans = inf;
        for (int v : f) {
            ans = Math.min(ans, v);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minPathCost(vector<vector<int>>& grid, vector<vector<int>>& moveCost) {
        int m = grid.size(), n = grid[0].size();
        const int inf = 1 << 30;
        vector<int> f = grid[0];
        for (int i = 1; i < m; ++i) {
            vector<int> g(n, inf);
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    g[j] = min(g[j], f[k] + moveCost[grid[i - 1][k]][j] + grid[i][j]);
                }
            }
            f = move(g);
        }
        return *min_element(f.begin(), f.end());
    }
};
```

### **Go**

```go
func minPathCost(grid [][]int, moveCost [][]int) int {
	m, n := len(grid), len(grid[0])
	const inf = 1 << 30
	f := grid[0]
	for i := 1; i < m; i++ {
		g := make([]int, n)
		for j := 0; j < n; j++ {
			g[j] = inf
			for k := 0; k < n; k++ {
				g[j] = min(g[j], f[k]+moveCost[grid[i-1][k]][j]+grid[i][j])
			}
		}
		f = g
	}
	ans := inf
	for _, v := range f {
		ans = min(ans, v)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_path_cost(grid: Vec<Vec<i32>>, move_cost: Vec<Vec<i32>>) -> i32 {
        let (m, n) = (grid.len(), grid[0].len());
        let mut dp = vec![0; n];
        for i in 0..m - 1 {
            let mut counter = vec![i32::MAX; n];
            for j in 0..n {
                let val = grid[i][j];
                for k in 0..n {
                    counter[k] = counter[k].min(val + move_cost[val as usize][k] + dp[j]);
                }
            }
            for j in 0..n {
                dp[j] = counter[j];
            }
        }
        let mut res = i32::MAX;
        for i in 0..n {
            res = res.min(dp[i] + grid[m - 1][i]);
        }
        res
    }
}
```

### **TypeScript**

```ts
function minPathCost(grid: number[][], moveCost: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    let pre = grid[0].slice();
    for (let i = 1; i < m; i++) {
        let next = new Array(n);
        for (let j = 0; j < n; j++) {
            const key = grid[i - 1][j];
            for (let k = 0; k < n; k++) {
                let sum = pre[j] + moveCost[key][k] + grid[i][k];
                if (j == 0 || next[k] > sum) {
                    next[k] = sum;
                }
            }
        }
        pre = next;
    }
    return Math.min(...pre);
}
```

### **...**

```

```

<!-- tabs:end -->
