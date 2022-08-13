# [剑指 Offer II 099. 最小路径之和](https://leetcode.cn/problems/0i0mDW)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个包含非负整数的 <code><em>m</em>&nbsp;x&nbsp;<em>n</em></code>&nbsp;网格&nbsp;<code>grid</code> ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。</p>

<p><strong>说明：</strong>一个机器人每次只能向下或者向右移动一步。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20099.%20%E6%9C%80%E5%B0%8F%E8%B7%AF%E5%BE%84%E4%B9%8B%E5%92%8C/images/minpath.jpg" style="width: 242px; height: 242px;" /></p>

<pre>
<strong>输入：</strong>grid = [[1,3,1],[1,5,1],[4,2,1]]
<strong>输出：</strong>7
<strong>解释：</strong>因为路径 1&rarr;3&rarr;1&rarr;1&rarr;1 的总和最小。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[1,2,3],[4,5,6]]
<strong>输出：</strong>12
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 100</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 64&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/minimum-path-sum/">https://leetcode.cn/problems/minimum-path-sum/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划。假设 `dp[i][j]` 表示到达网格 `(i,j)` 的最小数字和，先初始化 dp 第一列和第一行的所有值，然后利用递推公式：`dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]` 求得 dp。

最后返回 `dp[m - 1][n - 1]` 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        dp = [[grid[0][0]] * n for _ in range(m)]
        for i in range(1, m):
            dp[i][0] = dp[i - 1][0] + grid[i][0]
        for j in range(1, n):
            dp[0][j] = dp[0][j - 1] + grid[0][j]
        for i in range(1, m):
            for j in range(1, n):
                dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
        return dp[-1][-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; ++i) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; ++j) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
```

### **TypeScript**

```ts
function minPathSum(grid: number[][]): number {
    let m = grid.length,
        n = grid[0].length;
    let dp = Array.from({ length: m }, v => new Array(n).fill(0));
    dp[0][0] = grid[0][0];
    for (let i = 1; i < m; ++i) {
        dp[i][0] = dp[i - 1][0] + grid[i][0];
    }
    for (let j = 1; j < n; ++j) {
        dp[0][j] = dp[0][j - 1] + grid[0][j];
    }
    // dp
    for (let i = 1; i < m; ++i) {
        for (let j = 1; j < n; ++j) {
            let cur = grid[i][j];
            dp[i][j] = cur + Math.min(dp[i - 1][j], dp[i][j - 1]);
        }
    }
    return dp[m - 1][n - 1];
}
```

### **C++**

```cpp
class Solution {
public:
    int minPathSum(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> dp(m, vector<int>(n, grid[0][0]));
        for (int i = 1; i < m; ++i) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; ++j) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
};
```

### **Go**

```go
func minPathSum(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dp := make([][]int, m)
	for i := 0; i < m; i++ {
		dp[i] = make([]int, n)
	}
	dp[0][0] = grid[0][0]
	for i := 1; i < m; i++ {
		dp[i][0] = dp[i-1][0] + grid[i][0]
	}
	for j := 1; j < n; j++ {
		dp[0][j] = dp[0][j-1] + grid[0][j]
	}
	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
		}
	}
	return dp[m-1][n-1]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **C#**

```cs
public class Solution {
    public int MinPathSum(int[][] grid) {
        int m = grid.Length, n = grid[0].Length;
        int[,] dp = new int[m, n];
        dp[0, 0] = grid[0][0];
        for (int i = 1; i < m; ++i)
        {
            dp[i, 0] = dp[i - 1, 0] + grid[i][0];
        }
        for (int j = 1; j < n; ++j)
        {
            dp[0, j] = dp[0, j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; ++i)
        {
            for (int j = 1; j < n; ++j)
            {
                dp[i, j] = Math.Min(dp[i - 1, j], dp[i, j - 1]) + grid[i][j];
            }
        }
        return dp[m- 1, n - 1];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
