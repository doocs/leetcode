# [1463. 摘樱桃 II](https://leetcode.cn/problems/cherry-pickup-ii)

[English Version](/solution/1400-1499/1463.Cherry%20Pickup%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>rows x cols</code> 的矩阵&nbsp;<code>grid</code>&nbsp;来表示一块樱桃地。 <code>grid</code>&nbsp;中每个格子的数字表示你能获得的樱桃数目。</p>

<p>你有两个机器人帮你收集樱桃，机器人 1 从左上角格子 <code>(0,0)</code> 出发，机器人 2 从右上角格子 <code>(0, cols-1)</code> 出发。</p>

<p>请你按照如下规则，返回两个机器人能收集的最多樱桃数目：</p>

<ul>
	<li>从格子&nbsp;<code>(i,j)</code> 出发，机器人可以移动到格子&nbsp;<code>(i+1, j-1)</code>，<code>(i+1, j)</code> 或者&nbsp;<code>(i+1, j+1)</code>&nbsp;。</li>
	<li>当一个机器人经过某个格子时，它会把该格子内所有的樱桃都摘走，然后这个位置会变成空格子，即没有樱桃的格子。</li>
	<li>当两个机器人同时到达同一个格子时，它们中只有一个可以摘到樱桃。</li>
	<li>两个机器人在任意时刻都不能移动到 <code>grid</code>&nbsp;外面。</li>
	<li>两个机器人最后都要到达&nbsp;<code>grid</code>&nbsp;最底下一行。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1463.Cherry%20Pickup%20II/images/sample_1_1802.png" style="height: 182px; width: 139px;"></strong></p>

<pre><strong>输入：</strong>grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
<strong>输出：</strong>24
<strong>解释：</strong>机器人 1 和机器人 2 的路径在上图中分别用绿色和蓝色表示。
机器人 1 摘的樱桃数目为 (3 + 2 + 5 + 2) = 12 。
机器人 2 摘的樱桃数目为 (1 + 5 + 5 + 1) = 12 。
樱桃总数为： 12 + 12 = 24 。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1463.Cherry%20Pickup%20II/images/sample_2_1802.png" style="height: 257px; width: 284px;"></strong></p>

<pre><strong>输入：</strong>grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
<strong>输出：</strong>28
<strong>解释：</strong>机器人 1 和机器人 2 的路径在上图中分别用绿色和蓝色表示。
机器人 1 摘的樱桃数目为 (1 + 9 + 5 + 2) = 17 。
机器人 2 摘的樱桃数目为 (1 + 3 + 4 + 3) = 11 。
樱桃总数为： 17 + 11 = 28 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>grid = [[1,0,0,3],[0,0,0,3],[0,0,3,3],[9,0,3,3]]
<strong>输出：</strong>22
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>grid = [[1,1],[1,1]]
<strong>输出：</strong>4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>rows == grid.length</code></li>
	<li><code>cols == grid[i].length</code></li>
	<li><code>2 &lt;= rows, cols &lt;= 70</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 100&nbsp;</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

线性 DP。定义 `dp[i][j1][j2]` 表示两个机器人从起始点分别走到坐标 `(i, j1)`, `(i, j2)` 的所有路线中，可获得的樱桃数量的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
