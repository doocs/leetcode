# [764. 最大加号标志](https://leetcode.cn/problems/largest-plus-sign)

[English Version](/solution/0700-0799/0764.Largest%20Plus%20Sign/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个 <code>n x n</code> 的矩阵&nbsp;<code>grid</code>&nbsp;中，除了在数组&nbsp;<code>mines</code>&nbsp;中给出的元素为&nbsp;<code>0</code>，其他每个元素都为&nbsp;<code>1</code>。<code>mines[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>表示&nbsp;<code>grid[x<sub>i</sub>][y<sub>i</sub>] == 0</code></p>

<p>返回 <em>&nbsp;</em><code>grid</code><em> 中包含&nbsp;<code>1</code>&nbsp;的最大的 <strong>轴对齐</strong> 加号标志的阶数</em> 。如果未找到加号标志，则返回 <code>0</code> 。</p>

<p>一个&nbsp;<code>k</code>&nbsp;阶由&nbsp;<em><code>1</code></em>&nbsp;组成的 <strong>“轴对称”加号标志</strong> 具有中心网格&nbsp;<code>grid[r][c] == 1</code>&nbsp;，以及4个从中心向上、向下、向左、向右延伸，长度为&nbsp;<code>k-1</code>，由&nbsp;<code>1</code>&nbsp;组成的臂。注意，只有加号标志的所有网格要求为 <code>1</code> ，别的网格可能为 <code>0</code> 也可能为 <code>1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0764.Largest%20Plus%20Sign/images/plus1-grid.jpg" /></p>

<pre>
<strong>输入:</strong> n = 5, mines = [[4, 2]]
<strong>输出:</strong> 2
<strong>解释: </strong>在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0764.Largest%20Plus%20Sign/images/plus2-grid.jpg" /></p>

<pre>
<strong>输入:</strong> n = 1, mines = [[0, 0]]
<strong>输出:</strong> 0
<strong>解释: </strong>没有加号标志，返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= mines.length &lt;= 5000</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub>&nbsp;&lt; n</code></li>
	<li>每一对&nbsp;<code>(x<sub>i</sub>, y<sub>i</sub>)</code>&nbsp;都 <strong>不重复</strong>​​​​​​​</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $dp[i][j]$ 表示以 $(i, j)$ 为中心的最大加号标志的阶数，答案即为所有 $dp[i][j]$ 的最大值。

我们可以发现，对于每个 $(i, j)$，其最大加号标志的阶数不会超过其上下左右四个方向上连续的 $1$ 的个数的最小值。因此，我们可以预处理出每个位置上下左右四个方向上连续的 $1$ 的个数，然后遍历所有的 $(i, j)$，求出 $dp[i][j]$ 的最大值即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为网格的边长。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def orderOfLargestPlusSign(self, n: int, mines: List[List[int]]) -> int:
        dp = [[n] * n for _ in range(n)]
        for x, y in mines:
            dp[x][y] = 0
        for i in range(n):
            left = right = up = down = 0
            for j, k in zip(range(n), reversed(range(n))):
                left = left + 1 if dp[i][j] else 0
                right = right + 1 if dp[i][k] else 0
                up = up + 1 if dp[j][i] else 0
                down = down + 1 if dp[k][i] else 0
                dp[i][j] = min(dp[i][j], left)
                dp[i][k] = min(dp[i][k], right)
                dp[j][i] = min(dp[j][i], up)
                dp[k][i] = min(dp[k][i], down)
        return max(max(v) for v in dp)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] dp = new int[n][n];
        for (var e : dp) {
            Arrays.fill(e, n);
        }
        for (var e : mines) {
            dp[e[0]][e[1]] = 0;
        }
        for (int i = 0; i < n; ++i) {
            int left = 0, right = 0, up = 0, down = 0;
            for (int j = 0, k = n - 1; j < n; ++j, --k) {
                left = dp[i][j] > 0 ? left + 1 : 0;
                right = dp[i][k] > 0 ? right + 1 : 0;
                up = dp[j][i] > 0 ? up + 1 : 0;
                down = dp[k][i] > 0 ? down + 1 : 0;
                dp[i][j] = Math.min(dp[i][j], left);
                dp[i][k] = Math.min(dp[i][k], right);
                dp[j][i] = Math.min(dp[j][i], up);
                dp[k][i] = Math.min(dp[k][i], down);
            }
        }
        return Arrays.stream(dp).flatMapToInt(Arrays::stream).max().getAsInt();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int orderOfLargestPlusSign(int n, vector<vector<int>>& mines) {
        vector<vector<int>> dp(n, vector<int>(n, n));
        for (auto& e : mines) dp[e[0]][e[1]] = 0;
        for (int i = 0; i < n; ++i) {
            int left = 0, right = 0, up = 0, down = 0;
            for (int j = 0, k = n - 1; j < n; ++j, --k) {
                left = dp[i][j] ? left + 1 : 0;
                right = dp[i][k] ? right + 1 : 0;
                up = dp[j][i] ? up + 1 : 0;
                down = dp[k][i] ? down + 1 : 0;
                dp[i][j] = min(dp[i][j], left);
                dp[i][k] = min(dp[i][k], right);
                dp[j][i] = min(dp[j][i], up);
                dp[k][i] = min(dp[k][i], down);
            }
        }
        int ans = 0;
        for (auto& e : dp) ans = max(ans, *max_element(e.begin(), e.end()));
        return ans;
    }
};
```

### **Go**

```go
func orderOfLargestPlusSign(n int, mines [][]int) (ans int) {
	dp := make([][]int, n)
	for i := range dp {
		dp[i] = make([]int, n)
		for j := range dp[i] {
			dp[i][j] = n
		}
	}
	for _, e := range mines {
		dp[e[0]][e[1]] = 0
	}
	for i := 0; i < n; i++ {
		var left, right, up, down int
		for j, k := 0, n-1; j < n; j, k = j+1, k-1 {
			left, right, up, down = left+1, right+1, up+1, down+1
			if dp[i][j] == 0 {
				left = 0
			}
			if dp[i][k] == 0 {
				right = 0
			}
			if dp[j][i] == 0 {
				up = 0
			}
			if dp[k][i] == 0 {
				down = 0
			}
			dp[i][j] = min(dp[i][j], left)
			dp[i][k] = min(dp[i][k], right)
			dp[j][i] = min(dp[j][i], up)
			dp[k][i] = min(dp[k][i], down)
		}
	}
	for _, e := range dp {
		for _, v := range e {
			ans = max(ans, v)
		}
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
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
