# [1289. 下降路径最小和 II](https://leetcode.cn/problems/minimum-falling-path-sum-ii)

[English Version](/solution/1200-1299/1289.Minimum%20Falling%20Path%20Sum%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>n x n</code> 整数矩阵&nbsp;<code>arr</code>&nbsp;，请你返回 <strong>非零偏移下降路径</strong> 数字和的最小值。</p>

<p><strong>非零偏移下降路径</strong> 定义为：从&nbsp;<code>arr</code> 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1289.Minimum%20Falling%20Path%20Sum%20II/images/falling-grid.jpg" style="width: 244px; height: 245px;" /></p>

<pre>
<strong>输入：</strong>arr = [[1,2,3],[4,5,6],[7,8,9]]
<strong>输出：</strong>13
<strong>解释：</strong>
所有非零偏移下降路径包括：
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
下降路径中数字和最小的是&nbsp;[1,5,7] ，所以答案是&nbsp;13 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[7]]
<strong>输出：</strong>7
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>-99 &lt;= grid[i][j] &lt;= 99</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i][j]$ 表示前 $i$ 行，且最后一个数字在第 $j$ 列的最小数字和。那么状态转移方程为：

$$
f[i][j] = \min_{k \neq j} f[i - 1][k] + grid[i - 1][j]
$$

其中 $k$ 表示第 $i - 1$ 行的数字在第 $k$ 列，第 $i$ 行第 $j$ 列的数字为 $grid[i - 1][j]$。

最后答案为 $f[n]$ 中的最小值。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 为矩阵的行数。

实际上，我们也可以维护三个变量 $f$, $g$ 和 $fp$，分别表示前 $i$ 行的最小数字和、第 $i$ 行的第二小数字和以及第 $i$ 行的最小数字在第 $fp$ 列。这样我们就可以将时间复杂度降低到 $O(n^2)$，空间复杂度降低到 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minFallingPathSum(self, grid: List[List[int]]) -> int:
        n = len(grid)
        f = [[0] * n for _ in range(n + 1)]
        for i, row in enumerate(grid, 1):
            for j, v in enumerate(row):
                x = min((f[i - 1][k] for k in range(n) if k != j), default=0)
                f[i][j] = v + x
        return min(f[n])
```

```python
class Solution:
    def minFallingPathSum(self, grid: List[List[int]]) -> int:
        f = g = 0
        fp = -1
        for row in grid:
            ff = gg = inf
            ffp = -1
            for j, v in enumerate(row):
                s = (g if j == fp else f) + v
                if s < ff:
                    gg = ff
                    ff = s
                    ffp = j
                elif s < gg:
                    gg = s
            f, g, fp, = ff, gg, ffp
        return f
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        int[][] f = new int[n + 1][n];
        final int inf = 1 << 30;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < n; ++j) {
                int x = inf;
                for (int k = 0; k < n; ++k) {
                    if (k != j) {
                        x = Math.min(x, f[i - 1][k]);
                    }
                }
                f[i][j] = grid[i - 1][j] + (x == inf ? 0 : x);
            }
        }
        int ans = inf;
        for (int x : f[n]) {
            ans = Math.min(ans, x);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int minFallingPathSum(int[][] grid) {
        int f = 0, g = 0;
        int fp = -1;
        final int inf = 1 << 30;
        for (int[] row : grid) {
            int ff = inf, gg = inf;
            int ffp = -1;
            for (int j = 0; j < row.length; ++j) {
                int s = (j != fp ? f : g) + row[j];
                if (s < ff) {
                    gg = ff;
                    ff = s;
                    ffp = j;
                } else if (s < gg) {
                    gg = s;
                }
            }
            f = ff;
            g = gg;
            fp = ffp;
        }
        return f;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minFallingPathSum(vector<vector<int>>& grid) {
        int n = grid.size();
        int f[n + 1][n];
        memset(f, 0, sizeof(f));
        const int inf = 1 << 30;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < n; ++j) {
                int x = inf;
                for (int k = 0; k < n; ++k) {
                    if (k != j) {
                        x = min(x, f[i - 1][k]);
                    }
                }
                f[i][j] = grid[i - 1][j] + (x == inf ? 0 : x);
            }
        }
        return *min_element(f[n], f[n] + n);
    }
};
```

```cpp
class Solution {
public:
    int minFallingPathSum(vector<vector<int>>& grid) {
        int n = grid.size();
        int f = 0, g = 0, fp = -1;
        const int inf = 1 << 30;
        for (auto& row : grid) {
            int ff = inf, gg = inf;
            int ffp = -1;
            for (int j = 0; j < n; ++j) {
                int s = (fp != j ? f : g) + row[j];
                if (s < ff) {
                    gg = ff;
                    ff = s;
                    ffp = j;
                } else if (s < gg) {
                    gg = s;
                }
            }
            f = ff;
            g = gg;
            fp = ffp;
        }
        return f;
    }
};
```

### **Go**

```go
func minFallingPathSum(grid [][]int) int {
	n := len(grid)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, n)
	}
	const inf = 1 << 30
	for i, row := range grid {
		i++
		for j, v := range row {
			x := inf
			for k := range row {
				if k != j {
					x = min(x, f[i-1][k])
				}
			}
			if x == inf {
				x = 0
			}
			f[i][j] = v + x
		}
	}
	ans := inf
	for _, x := range f[n] {
		ans = min(ans, x)
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

```go
func minFallingPathSum(grid [][]int) int {
	const inf = 1 << 30
	f, g := 0, 0
	fp := -1
	for _, row := range grid {
		ff, gg := inf, inf
		ffp := -1
		for j, v := range row {
			s := f
			if j == fp {
				s = g
			}
			s += v
			if s < ff {
				ff, gg, ffp = s, ff, j
			} else if s < gg {
				gg = s
			}
		}
		f, g, fp = ff, gg, ffp
	}
	return f
}
```

### **...**

```

```

<!-- tabs:end -->
