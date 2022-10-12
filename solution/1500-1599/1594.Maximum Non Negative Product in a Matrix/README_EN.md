# [1594. Maximum Non Negative Product in a Matrix](https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix)

[中文文档](/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/README.md)

## Description

<p>You are given a <code>m x n</code> matrix <code>grid</code>. Initially, you are located at the top-left corner <code>(0, 0)</code>, and in each step, you can only <strong>move right or down</strong> in the matrix.</p>

<p>Among all possible paths starting from the top-left corner <code>(0, 0)</code> and ending in the bottom-right corner <code>(m - 1, n - 1)</code>, find the path with the <strong>maximum non-negative product</strong>. The product of a path is the product of all integers in the grid cells visited along the path.</p>

<p>Return the <em>maximum non-negative product <strong>modulo</strong> </em><code>10<sup>9</sup> + 7</code>. <em>If the maximum product is <strong>negative</strong>, return </em><code>-1</code>.</p>

<p>Notice that the modulo is performed after getting the maximum product.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/images/product1.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> grid = [[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is not possible to get non-negative product in the path from (0, 0) to (2, 2), so return -1.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/images/product2.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> grid = [[1,-2,1],[1,-2,1],[3,-4,1]]
<strong>Output:</strong> 8
<strong>Explanation:</strong> Maximum non-negative product is shown (1 * 1 * -2 * -4 * 1 = 8).
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/images/product3.jpg" style="width: 164px; height: 165px;" />
<pre>
<strong>Input:</strong> grid = [[1,3],[0,-4]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Maximum non-negative product is shown (1 * 0 * -4 = 0).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 15</code></li>
	<li><code>-4 &lt;= grid[i][j] &lt;= 4</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxProductPath(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        mod = 10**9 + 7
        dp = [[[grid[0][0]] * 2 for _ in range(n)] for _ in range(m)]
        for i in range(1, m):
            dp[i][0] = [dp[i - 1][0][0] * grid[i][0]] * 2
        for j in range(1, n):
            dp[0][j] = [dp[0][j - 1][0] * grid[0][j]] * 2
        for i in range(1, m):
            for j in range(1, n):
                v = grid[i][j]
                if v >= 0:
                    dp[i][j][0] = min(dp[i - 1][j][0], dp[i][j - 1][0]) * v
                    dp[i][j][1] = max(dp[i - 1][j][1], dp[i][j - 1][1]) * v
                else:
                    dp[i][j][0] = max(dp[i - 1][j][1], dp[i][j - 1][1]) * v
                    dp[i][j][1] = min(dp[i - 1][j][0], dp[i][j - 1][0]) * v
        ans = dp[-1][-1][1]
        return -1 if ans < 0 else ans % mod
```

### **Java**

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long[][][] dp = new long[m][n][2];
        dp[0][0][0] = grid[0][0];
        dp[0][0][1] = grid[0][0];
        for (int i = 1; i < m; ++i) {
            dp[i][0][0] = dp[i - 1][0][0] * grid[i][0];
            dp[i][0][1] = dp[i - 1][0][1] * grid[i][0];
        }
        for (int j = 1; j < n; ++j) {
            dp[0][j][0] = dp[0][j - 1][0] * grid[0][j];
            dp[0][j][1] = dp[0][j - 1][1] * grid[0][j];
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                int v = grid[i][j];
                if (v >= 0) {
                    dp[i][j][0] = Math.min(dp[i - 1][j][0], dp[i][j - 1][0]) * v;
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i][j - 1][1]) * v;
                } else {
                    dp[i][j][0] = Math.max(dp[i - 1][j][1], dp[i][j - 1][1]) * v;
                    dp[i][j][1] = Math.min(dp[i - 1][j][0], dp[i][j - 1][0]) * v;
                }
            }
        }
        long ans = dp[m - 1][n - 1][1];
        return ans < 0 ? -1 : (int) (ans % MOD);
    }
}
```

### **C++**

```cpp
using ll = long long;
const int mod = 1e9 + 7;

class Solution {
public:
    int maxProductPath(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        vector<vector<vector<ll>>> dp(m, vector<vector<ll>>(n, vector<ll>(2, grid[0][0])));
        for (int i = 1; i < m; ++i) {
            dp[i][0][0] = dp[i - 1][0][0] * grid[i][0];
            dp[i][0][1] = dp[i - 1][0][1] * grid[i][0];
        }
        for (int j = 1; j < n; ++j) {
            dp[0][j][0] = dp[0][j - 1][0] * grid[0][j];
            dp[0][j][1] = dp[0][j - 1][1] * grid[0][j];
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                int v = grid[i][j];
                if (v >= 0) {
                    dp[i][j][0] = min(dp[i - 1][j][0], dp[i][j - 1][0]) * v;
                    dp[i][j][1] = max(dp[i - 1][j][1], dp[i][j - 1][1]) * v;
                } else {
                    dp[i][j][0] = max(dp[i - 1][j][1], dp[i][j - 1][1]) * v;
                    dp[i][j][1] = min(dp[i - 1][j][0], dp[i][j - 1][0]) * v;
                }
            }
        }
        ll ans = dp[m - 1][n - 1][1];
        return ans < 0 ? -1 : (int) (ans % mod);
    }
};
```

### **Go**

```go
func maxProductPath(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dp := make([][][]int, m)
	for i := range dp {
		dp[i] = make([][]int, n)
		for j := range dp[i] {
			dp[i][j] = make([]int, 2)
		}
	}
	dp[0][0] = []int{grid[0][0], grid[0][0]}
	for i := 1; i < m; i++ {
		dp[i][0][0] = dp[i-1][0][0] * grid[i][0]
		dp[i][0][1] = dp[i-1][0][1] * grid[i][0]
	}
	for j := 1; j < n; j++ {
		dp[0][j][0] = dp[0][j-1][0] * grid[0][j]
		dp[0][j][1] = dp[0][j-1][1] * grid[0][j]
	}
	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			v := grid[i][j]
			if v >= 0 {
				dp[i][j][0] = min(dp[i-1][j][0], dp[i][j-1][0]) * v
				dp[i][j][1] = max(dp[i-1][j][1], dp[i][j-1][1]) * v
			} else {
				dp[i][j][0] = max(dp[i-1][j][1], dp[i][j-1][1]) * v
				dp[i][j][1] = min(dp[i-1][j][0], dp[i][j-1][0]) * v
			}
		}
	}
	ans := dp[m-1][n-1][1]
	if ans < 0 {
		return -1
	}
	var mod int = 1e9 + 7
	return ans % mod
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
