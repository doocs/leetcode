# [1463. Cherry Pickup II](https://leetcode.com/problems/cherry-pickup-ii)

[中文文档](/solution/1400-1499/1463.Cherry%20Pickup%20II/README.md)

## Description

<p>You are given a <code>rows x cols</code> matrix <code>grid</code> representing a field of cherries where <code>grid[i][j]</code> represents the number of cherries that you can collect from the <code>(i, j)</code> cell.</p>

<p>You have two robots that can collect cherries for you:</p>

<ul>
	<li><strong>Robot #1</strong> is located at the <strong>top-left corner</strong> <code>(0, 0)</code>, and</li>
	<li><strong>Robot #2</strong> is located at the <strong>top-right corner</strong> <code>(0, cols - 1)</code>.</li>
</ul>

<p>Return <em>the maximum number of cherries collection using both robots by following the rules below</em>:</p>

<ul>
	<li>From a cell <code>(i, j)</code>, robots can move to cell <code>(i + 1, j - 1)</code>, <code>(i + 1, j)</code>, or <code>(i + 1, j + 1)</code>.</li>
	<li>When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.</li>
	<li>When both robots stay in the same cell, only one takes the cherries.</li>
	<li>Both robots cannot move outside of the grid at any moment.</li>
	<li>Both robots should reach the bottom row in <code>grid</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1463.Cherry%20Pickup%20II/images/sample_1_1802.png" style="width: 374px; height: 501px;" />
<pre>
<strong>Input:</strong> grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
<strong>Output:</strong> 24
<strong>Explanation:</strong> Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
Total of cherries: 12 + 12 = 24.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1463.Cherry%20Pickup%20II/images/sample_2_1802.png" style="width: 500px; height: 452px;" />
<pre>
<strong>Input:</strong> grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
<strong>Output:</strong> 28
<strong>Explanation:</strong> Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
Total of cherries: 17 + 11 = 28.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>rows == grid.length</code></li>
	<li><code>cols == grid[i].length</code></li>
	<li><code>2 &lt;= rows, cols &lt;= 70</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 100</code></li>
</ul>

## Solutions

Use dynammic programming, define `dp[i][j1][j2]`: The maximum cherries that both robots can take starting on the ith row, and column j1 and j2 of Robot 1 and 2 respectively.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def cherryPickup(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        dp = [[[0] * n for _ in range(n)] for _ in range(m)]
        valid = [[[False] * n for _ in range(n)] for _ in range(m)]
        dp[0][0][n - 1] = grid[0][0] + grid[0][n - 1]
        valid[0][0][n - 1] = True
        for i in range(1, m):
            for j1 in range(n):
                for j2 in range(n):
                    t = grid[i][j1]
                    if j1 != j2:
                        t += grid[i][j2]
                    ok = False
                    for y1 in range(j1 - 1, j1 + 2):
                        for y2 in range(j2 - 1, j2 + 2):
                            if 0 <= y1 < n and 0 <= y2 < n and valid[i - 1][y1][y2]:
                                dp[i][j1][j2] = max(
                                    dp[i][j1][j2], dp[i - 1][y1][y2] + t
                                )
                                ok = True
                    valid[i][j1][j2] = ok
        return max(dp[m - 1][j1][j2] for j1 in range(n) for j2 in range(n))
```

### **Java**

```java
class Solution {
    public int cherryPickup(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][][] dp = new int[m][n][n];
        boolean[][][] valid = new boolean[m][n][n];
        dp[0][0][n - 1] = grid[0][0] + grid[0][n - 1];
        valid[0][0][n - 1] = true;

        for (int i = 1; i < m; ++i) {
            for (int j1 = 0; j1 < n; ++j1) {
                for (int j2 = 0; j2 < n; ++j2) {
                    int t = grid[i][j1];
                    if (j1 != j2) {
                        t += grid[i][j2];
                    }
                    boolean ok = false;
                    for (int y1 = j1 - 1; y1 <= j1 + 1; ++y1) {
                        for (int y2 = j2 - 1; y2 <= j2 + 1; ++y2) {
                            if (y1 >= 0 && y1 < n && y2 >= 0 && y2 < n && valid[i - 1][y1][y2]) {
                                dp[i][j1][j2] = Math.max(dp[i][j1][j2], dp[i - 1][y1][y2] + t);
                                ok = true;
                            }
                        }
                    }
                    valid[i][j1][j2] = ok;
                }
            }
        }
        int ans = 0;
        for (int j1 = 0; j1 < n; ++j1) {
            for (int j2 = 0; j2 < n; ++j2) {
                ans = Math.max(ans, dp[m - 1][j1][j2]);
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
    int cherryPickup(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<vector<int>>> dp(m, vector<vector<int>>(n, vector<int>(n)));
        vector<vector<vector<bool>>> valid(m, vector<vector<bool>>(n, vector<bool>(n)));
        dp[0][0][n - 1] = grid[0][0] + grid[0][n - 1];
        valid[0][0][n - 1] = true;
        for (int i = 1; i < m; ++i) {
            for (int j1 = 0; j1 < n; ++j1) {
                for (int j2 = 0; j2 < n; ++j2) {
                    int t = grid[i][j1];
                    if (j1 != j2) t += grid[i][j2];
                    bool ok = false;
                    for (int y1 = j1 - 1; y1 <= j1 + 1; ++y1)
                        for (int y2 = j2 - 1; y2 <= j2 + 1; ++y2)
                            if (y1 >= 0 && y1 < n && y2 >= 0 && y2 < n && valid[i - 1][y1][y2]) {
                                dp[i][j1][j2] = max(dp[i][j1][j2], dp[i - 1][y1][y2] + t);
                                ok = true;
                            }
                    valid[i][j1][j2] = ok;
                }
            }
        }
        int ans = 0;
        for (int j1 = 0; j1 < n; ++j1)
            for (int j2 = 0; j2 < n; ++j2)
                ans = max(ans, dp[m - 1][j1][j2]);
        return ans;
    }
};
```

### **Go**

```go
func cherryPickup(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dp := make([][][]int, m)
	valid := make([][][]bool, m)
	for i := range dp {
		dp[i] = make([][]int, n)
		valid[i] = make([][]bool, n)
		for j1 := range dp[i] {
			dp[i][j1] = make([]int, n)
			valid[i][j1] = make([]bool, n)
		}
	}
	dp[0][0][n-1] = grid[0][0] + grid[0][n-1]
	valid[0][0][n-1] = true
	for i := 1; i < m; i++ {
		for j1 := 0; j1 < n; j1++ {
			for j2 := 0; j2 < n; j2++ {
				t := grid[i][j1]
				if j1 != j2 {
					t += grid[i][j2]
				}
				ok := false
				for y1 := j1 - 1; y1 <= j1+1; y1++ {
					for y2 := j2 - 1; y2 <= j2+1; y2++ {
						if y1 >= 0 && y1 < n && y2 >= 0 && y2 < n && valid[i-1][y1][y2] {
							dp[i][j1][j2] = max(dp[i][j1][j2], dp[i-1][y1][y2]+t)
							ok = true
						}
					}
				}
				valid[i][j1][j2] = ok
			}
		}
	}
	ans := 0
	for j1 := 0; j1 < n; j1++ {
		for j2 := 0; j2 < n; j2++ {
			ans = max(ans, dp[m-1][j1][j2])
		}
	}
	return ans
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
