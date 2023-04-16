# [1594. 矩阵的最大非负积](https://leetcode.cn/problems/maximum-non-negative-product-in-a-matrix)

[English Version](/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>m x n</code> 的矩阵 <code>grid</code> 。最初，你位于左上角 <code>(0, 0)</code> ，每一步，你可以在矩阵中 <strong>向右</strong> 或 <strong>向下</strong> 移动。</p>

<p>在从左上角 <code>(0, 0)</code> 开始到右下角 <code>(m - 1, n - 1)</code> 结束的所有路径中，找出具有 <strong>最大非负积</strong> 的路径。路径的积是沿路径访问的单元格中所有整数的乘积。</p>

<p>返回 <strong>最大非负积 </strong>对<strong><em> </em><code>10<sup>9</sup>&nbsp;+ 7</code></strong> <strong>取余</strong> 的结果。如果最大积为 <strong>负数</strong> ，则返回<em> </em><code>-1</code> 。</p>

<p><strong>注意，</strong>取余是在得到最大积之后执行的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/images/product1.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>输入：</strong>grid = [[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]
<strong>输出：</strong>-1
<strong>解释：</strong>从 (0, 0) 到 (2, 2) 的路径中无法得到非负积，所以返回 -1 。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/images/product2.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>输入：</strong>grid = [[1,-2,1],[1,-2,1],[3,-4,1]]
<strong>输出：</strong>8
<strong>解释：</strong>最大非负积对应的路径如图所示 (1 * 1 * -2 * -4 * 1 = 8)
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1594.Maximum%20Non%20Negative%20Product%20in%20a%20Matrix/images/product3.jpg" style="width: 164px; height: 165px;" />
<pre>
<strong>输入：</strong>grid = [[1,3],[0,-4]]
<strong>输出：</strong>0
<strong>解释：</strong>最大非负积对应的路径如图所示 (1 * 0 * -4 = 0)
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 15</code></li>
	<li><code>-4 &lt;= grid[i][j] &lt;= 4</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

时间复杂度 $O(m\times n)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
