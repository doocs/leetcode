# [2088. 统计农场中肥沃金字塔的数目](https://leetcode.cn/problems/count-fertile-pyramids-in-a-land)

[English Version](/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个 <strong>矩形网格</strong>&nbsp;状的农场，划分为&nbsp;<code>m</code>&nbsp;行&nbsp;<code>n</code>&nbsp;列的单元格。每个格子要么是 <strong>肥沃的</strong>&nbsp;（用 <code>1</code>&nbsp;表示），要么是 <strong>贫瘠</strong>&nbsp;的（用 <code>0</code>&nbsp;表示）。网格图以外的所有与格子都视为贫瘠的。</p>

<p>农场中的&nbsp;<strong>金字塔</strong>&nbsp;区域定义如下：</p>

<ol>
	<li>区域内格子数目 <strong>大于&nbsp;</strong><code>1</code>&nbsp;且所有格子都是 <strong>肥沃的</strong>&nbsp;。</li>
	<li>金字塔 <strong>顶端</strong>&nbsp;是这个金字塔 <strong>最上方</strong>&nbsp;的格子。金字塔的高度是它所覆盖的行数。令&nbsp;<code>(r, c)</code>&nbsp;为金字塔的顶端且高度为 <code>h</code>&nbsp;，那么金字塔区域内包含的任一格子&nbsp;<code>(i, j)</code>&nbsp;需满足&nbsp;<code>r &lt;= i &lt;= r + h - 1</code>&nbsp;<strong>且</strong>&nbsp;<code>c - (i - r) &lt;= j &lt;= c + (i - r)</code>&nbsp;。</li>
</ol>

<p>一个 <strong>倒金字塔</strong>&nbsp;类似定义如下：</p>

<ol>
	<li>区域内格子数目 <strong>大于</strong>&nbsp;<code>1</code>&nbsp;且所有格子都是 <b>肥沃的</b>&nbsp;。</li>
	<li>倒金字塔的 <strong>顶端</strong>&nbsp;是这个倒金字塔 <strong>最下方</strong>&nbsp;的格子。倒金字塔的高度是它所覆盖的行数。令&nbsp;<code>(r, c)</code>&nbsp;为金字塔的顶端且高度为 <code>h</code>&nbsp;，那么金字塔区域内包含的任一格子&nbsp;<code>(i, j)</code>&nbsp;需满足&nbsp;<code>r - h + 1 &lt;= i &lt;= r</code> <strong>且</strong> <code>c - (r - i) &lt;= j &lt;= c + (r - i)</code>&nbsp;。</li>
</ol>

<p>下图展示了部分符合定义和不符合定义的金字塔区域。黑色区域表示肥沃的格子。</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/image.png" style="width: 700px; height: 156px;"></p>

<p>给你一个下标从 <strong>0</strong>&nbsp;开始且大小为 <code>m x n</code>&nbsp;的二进制矩阵&nbsp;<code>grid</code>&nbsp;，它表示农场，请你返回 <code>grid</code>&nbsp;中金字塔和倒金字塔的&nbsp;<strong>总数目</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/eg11.png" style="width: 200px; height: 102px;">&nbsp;<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/exa12.png" style="width: 200px; height: 102px;">&nbsp;<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/exa13.png" style="width: 200px; height: 102px;"></p>

<pre><b>输入：</b>grid = [[0,1,1,0],[1,1,1,1]]
<b>输出：</b>2
<strong>解释：</strong>
2 个可能的金字塔区域分别如上图蓝色和红色区域所示。
这个网格图中没有倒金字塔区域。
所以金字塔区域总数为 2 + 0 = 2 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/eg21.png" style="width: 180px; height: 122px;">&nbsp;<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/exa22.png" style="width: 180px; height: 122px;">&nbsp;<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/exa23.png" style="width: 180px; height: 122px;"></p>

<pre><b>输入：</b>grid = [[1,1,1],[1,1,1]]
<b>输出：</b>2
<strong>解释：</strong>
金字塔区域如上图蓝色区域所示，倒金字塔如上图红色区域所示。
所以金字塔区域总数目为 1 + 1 = 2 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/eg3.png" style="width: 149px; height: 150px;"></p>

<pre><b>输入：</b>grid = [[1,0,1],[0,0,0],[1,0,1]]
<b>输出：</b>0
<strong>解释：</strong>
网格图中没有任何金字塔或倒金字塔区域。
</pre>

<p><strong>示例 4：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/eg41.png" style="width: 180px; height: 144px;">&nbsp;<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/eg42.png" style="width: 180px; height: 144px;">&nbsp;<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/eg43.png" style="width: 180px; height: 144px;">&nbsp;<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/eg44.png" style="width: 180px; height: 144px;"></p>

<pre><strong>输入：</strong>grid = [[1,1,1,1,0],[1,1,1,1,1],[1,1,1,1,1],[0,1,0,0,1]]
<b>输出：</b>13
<strong>解释：</strong>
有 7 个金字塔区域。上图第二和第三张图中展示了它们中的 3 个。
有 6 个倒金字塔区域。上图中最后一张图展示了它们中的 2 个。
所以金字塔区域总数目为 7 + 6 = 13.
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>grid[i][j]</code>&nbsp;要么是&nbsp;<code>0</code>&nbsp;，要么是&nbsp;<code>1</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i][j]$ 表示以 $(i, j)$ 为顶点的金字塔的最大高度，那么有如下状态转移方程：

$$
f[i][j] = \begin{cases} 0 & \text{grid}[i][j] = 0 \\ \min(f[i + 1][j - 1], f[i + 1][j], f[i + 1][j + 1]) + 1 & \text{grid}[i][j] = 1 \end{cases}
$$

因此，我们可以从下往上、从左往右遍历网格，计算出所有的 $f[i][j]$，并累加所有的 $f[i][j]$ 即可得到正金字塔的个数。

接下来，我们考虑倒金字塔的个数。与金字塔类似，我们定义 $g[i][j]$ 表示以 $(i, j)$ 为顶点的倒金字塔的最大高度，那么有如下状态转移方程：

$$
g[i][j] = \begin{cases} 0 & \text{grid}[i][j] = 0 \\ \min(g[i - 1][j - 1], g[i - 1][j], g[i - 1][j + 1]) + 1 & \text{grid}[i][j] = 1 \end{cases}
$$

因此，我们可以从上往下、从左往右遍历网格，计算出所有的 $g[i][j]$，并累加所有的 $g[i][j]$ 即可得到倒金字塔的个数。

最后，正金字塔的个数加上倒金字塔的个数即为答案。实际代码中，我们可以只用一个二维数组 $f[i][j]$ 即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为网格的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countPyramids(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        f = [[0] * n for _ in range(m)]
        ans = 0
        for i in range(m - 1, -1, -1):
            for j in range(n):
                if grid[i][j] == 0:
                    f[i][j] = -1
                elif not (i == m - 1 or j == 0 or j == n - 1):
                    f[i][j] = min(f[i + 1][j - 1], f[i + 1]
                                  [j], f[i + 1][j + 1]) + 1
                    ans += f[i][j]
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 0:
                    f[i][j] = -1
                elif i == 0 or j == 0 or j == n - 1:
                    f[i][j] = 0
                else:
                    f[i][j] = min(f[i - 1][j - 1], f[i - 1]
                                  [j], f[i - 1][j + 1]) + 1
                    ans += f[i][j]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countPyramids(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[m][n];
        int ans = 0;
        for (int i = m - 1; i >= 0; --i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    f[i][j] = -1;
                } else if (i == m - 1 || j == 0 || j == n - 1) {
                    f[i][j] = 0;
                } else {
                    f[i][j] = Math.min(f[i + 1][j - 1], Math.min(f[i + 1][j], f[i + 1][j + 1])) + 1;
                    ans += f[i][j];
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    f[i][j] = -1;
                } else if (i == 0 || j == 0 || j == n - 1) {
                    f[i][j] = 0;
                } else {
                    f[i][j] = Math.min(f[i - 1][j - 1], Math.min(f[i - 1][j], f[i - 1][j + 1])) + 1;
                    ans += f[i][j];
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
    int countPyramids(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int f[m][n];
        int ans = 0;
        for (int i = m - 1; ~i; --i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    f[i][j] = -1;
                } else if (i == m - 1 || j == 0 || j == n - 1) {
                    f[i][j] = 0;
                } else {
                    f[i][j] = min({f[i + 1][j - 1], f[i + 1][j], f[i + 1][j + 1]}) + 1;
                    ans += f[i][j];
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    f[i][j] = -1;
                } else if (i == 0 || j == 0 || j == n - 1) {
                    f[i][j] = 0;
                } else {
                    f[i][j] = min({f[i - 1][j - 1], f[i - 1][j], f[i - 1][j + 1]}) + 1;
                    ans += f[i][j];
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countPyramids(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	for i := m - 1; i >= 0; i-- {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 {
				f[i][j] = -1
			} else if i == m-1 || j == 0 || j == n-1 {
				f[i][j] = 0
			} else {
				f[i][j] = min(f[i+1][j-1], min(f[i+1][j], f[i+1][j+1])) + 1
				ans += f[i][j]
			}
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 {
				f[i][j] = -1
			} else if i == 0 || j == 0 || j == n-1 {
				f[i][j] = 0
			} else {
				f[i][j] = min(f[i-1][j-1], min(f[i-1][j], f[i-1][j+1])) + 1
				ans += f[i][j]
			}
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
```

### **...**

```

```

<!-- tabs:end -->
