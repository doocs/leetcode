# [741. Cherry Pickup](https://leetcode.com/problems/cherry-pickup)

[中文文档](/solution/0700-0799/0741.Cherry%20Pickup/README.md)

## Description

<p>You are given an <code>n x n</code> <code>grid</code> representing a field of cherries, each cell is one of three possible integers.</p>

<ul>
	<li><code>0</code> means the cell is empty, so you can pass through,</li>
	<li><code>1</code> means the cell contains a cherry that you can pick up and pass through, or</li>
	<li><code>-1</code> means the cell contains a thorn that blocks your way.</li>
</ul>

<p>Return <em>the maximum number of cherries you can collect by following the rules below</em>:</p>

<ul>
	<li>Starting at the position <code>(0, 0)</code> and reaching <code>(n - 1, n - 1)</code> by moving right or down through valid path cells (cells with value <code>0</code> or <code>1</code>).</li>
	<li>After reaching <code>(n - 1, n - 1)</code>, returning to <code>(0, 0)</code> by moving left or up through valid path cells.</li>
	<li>When passing through a path cell containing a cherry, you pick it up, and the cell becomes an empty cell <code>0</code>.</li>
	<li>If there is no valid path between <code>(0, 0)</code> and <code>(n - 1, n - 1)</code>, then no cherries can be collected.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0741.Cherry%20Pickup/images/grid.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> grid = [[0,1,-1],[1,0,-1],[1,1,1]]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The player started at (0, 0) and went down, down, right right to reach (2, 2).
4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
Then, the player went left, up, up, left to return home, picking up one more cherry.
The total number of cherries picked up is 5, and this is the maximum possible.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1,-1],[1,-1,1],[-1,1,1]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>grid[i][j]</code> is <code>-1</code>, <code>0</code>, or <code>1</code>.</li>
	<li><code>grid[0][0] != -1</code></li>
	<li><code>grid[n - 1][n - 1] != -1</code></li>
</ul>

## Solutions

Dynamic programming.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def cherryPickup(self, grid: List[List[int]]) -> int:
        n = len(grid)
        dp = [[[float('-inf')] * n for _ in range(n)] for _ in range((n << 1) -1 )]
        dp[0][0][0] = grid[0][0]
        for k in range(1, (n << 1) - 1):
            for i1 in range(n):
                for i2 in range(n):
                    j1, j2 = k - i1, k - i2
                    if j1 >= 0 and j1 < n and j2 >= 0 and j2 < n:
                        if grid[i1][j1] == -1 or grid[i2][j2] == -1:
                            continue
                        t = grid[i1][j1]
                        if i1 != i2:
                            t += grid[i2][j2]
                        for p1 in range(i1 - 1, i1 + 1):
                            for p2 in range(i2 - 1, i2 + 1):
                                if p1 >= 0 and p2 >= 0:
                                    dp[k][i1][i2] = max(dp[k][i1][i2], dp[k - 1][p1][p2] + t)
        return max(dp[-1][-1][-1], 0)
```

### **Java**

```java
class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[2 * n][n][n];
        for (int[][] item : dp) {
            for (int[] row : item) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }
        }
        dp[0][0][0] = grid[0][0];
        for (int k = 1; k < 2 * n - 1; ++k) {
            for (int i1 = 0; i1 < n; ++i1) {
                for (int i2 = 0; i2 < n; ++i2) {
                    int j1 = k - i1, j2 = k - i2;
                    if (j1 >= 0 && j1 < n && j2 >= 0 && j2 < n) {
                        if (grid[i1][j1] == -1 || grid[i2][j2] == -1) {
                            continue;
                        }
                        int t = grid[i1][j1];
                        if (i1 != i2) {
                            t += grid[i2][j2];
                        }
                        for (int p1 = i1 - 1; p1 <= i1; ++p1) {
                            for (int p2 = i2 - 1; p2 <= i2; ++p2) {
                                if (p1 >= 0 && p2 >= 0) {
                                    dp[k][i1][i2] = Math.max(dp[k][i1][i2], dp[k - 1][p1][p2] + t);
                                }
                            }
                        }
                    }
                }
            }
        }
        return Math.max(dp[2 * n - 2][n - 1][n - 1], 0);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int cherryPickup(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<vector<vector<int>>> dp(n << 1, vector<vector<int>>(n, vector<int>(n, -1e9)));
        dp[0][0][0] = grid[0][0];
        for (int k = 1; k < 2 * n - 1; ++k)
        {
            for (int i1 = 0; i1 < n; ++i1)
            {
                for (int i2 = 0; i2 < n; ++i2)
                {
                    int j1 = k - i1, j2 = k - i2;
                    if (j1 >= 0 && j1 < n && j2 >= 0 && j2 < n)
                    {
                        if (grid[i1][j1] == -1 || grid[i2][j2] == -1) continue;
                        int t = grid[i1][j1];
                        if (i1 != i2) t += grid[i2][j2];
                        for (int p1 = i1 - 1; p1 <= i1; ++p1)
                        {
                            for (int p2 = i2 - 1; p2 <= i2; ++p2)
                            {
                                if (p1 >= 0 && p2 >= 0) dp[k][i1][i2] = max(dp[k][i1][i2], dp[k - 1][p1][p2] + t);
                            }
                        }
                    }
                }
            }
        }
        return max(dp[2 * n - 2][n - 1][n - 1], 0);
    }
};
```

### **Go**

```go
func cherryPickup(grid [][]int) int {
    n := len(grid)
    dp := make([][][]int, (n << 1) - 1)
    for i := range dp {
        dp[i] = make([][]int, n)
        for j := range dp[i] {
            dp[i][j] = make([]int, n)
            for k := range dp[i][j] {
                dp[i][j][k] = int(-1e9)
            }
        }
    }
    dp[0][0][0] = grid[0][0]
    for k := 1; k < (n << 1) - 1; k++ {
        for i1 := 0; i1 < n; i1++ {
            for i2 := 0; i2 < n; i2++ {
                j1, j2 := k - i1, k - i2
                if j1 >= 0 && j1 < n && j2 >= 0 && j2 < n {
                    if grid[i1][j1] == -1 || grid[i2][j2] == -1 {
                        continue
                    }
                    t := grid[i1][j1]
                    if i1 != i2 {
                        t += grid[i2][j2]
                    }
                    for p1 := i1 - 1; p1 <= i1; p1++ {
                        for p2 := i2 - 1; p2 <= i2; p2++ {
                            if p1 >= 0 && p2 >= 0 {
                                dp[k][i1][i2] = max(dp[k][i1][i2], dp[k - 1][p1][p2] + t)
                            }
                        }
                    }
                }
            }
        }
    }

    return max(dp[(n << 1) - 2][n - 1][n - 1], 0)
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}
```

### **...**

```

```

<!-- tabs:end -->
