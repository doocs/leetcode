---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3225.Maximum%20Score%20From%20Grid%20Operations/README.md
rating: 3027
source: 第 135 场双周赛 Q4
tags:
    - 数组
    - 动态规划
    - 矩阵
    - 前缀和
---

<!-- problem:start -->

# [3225. 网格图操作后的最大分数](https://leetcode.cn/problems/maximum-score-from-grid-operations)

[English Version](/solution/3200-3299/3225.Maximum%20Score%20From%20Grid%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个大小为 <code>n x n</code>&nbsp;的二维矩阵&nbsp;<code>grid</code>&nbsp;，一开始所有格子都是白色的。一次操作中，你可以选择任意下标为&nbsp;<code>(i, j)</code>&nbsp;的格子，并将第&nbsp;<code>j</code>&nbsp;列中从最上面到第&nbsp;<code>i</code>&nbsp;行所有格子改成黑色。</p>

<p>如果格子 <code>(i, j)</code>&nbsp;为白色，且左边或者右边的格子至少一个格子为黑色，那么我们将 <code>grid[i][j]</code>&nbsp;加到最后网格图的总分中去。</p>

<p>请你返回执行任意次操作以后，最终网格图的 <strong>最大</strong>&nbsp;总分数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[0,0,0,0,0],[0,0,3,0,0],[0,1,0,0,0],[5,0,0,3,0],[0,0,0,0,2]]</span></p>

<p><span class="example-io"><b>输出：</b>11</span></p>

<p><strong>解释：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3225.Maximum%20Score%20From%20Grid%20Operations/images/one.png" style="width: 300px; height: 200px;" />
<p>第一次操作中，我们将第 1 列中，最上面的格子到第 3 行的格子染成黑色。第二次操作中，我们将第 4 列中，最上面的格子到最后一行的格子染成黑色。最后网格图总分为&nbsp;<code>grid[3][0] + grid[1][2] + grid[3][3]</code>&nbsp;等于 11 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[10,9,0,0,15],[7,1,0,8,0],[5,20,0,11,0],[0,0,0,1,2],[8,12,1,10,3]]</span></p>

<p><span class="example-io"><b>输出：</b>94</span></p>

<p><strong>解释：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3225.Maximum%20Score%20From%20Grid%20Operations/images/two-1.png" style="width: 300px; height: 200px;" />
<p>我们对第 1 ，2 ，3 列分别从上往下染黑色到第 1 ，4， 0 行。最后网格图总分为&nbsp;<code>grid[0][0] + grid[1][0] + grid[2][1] + grid[4][1] + grid[1][3] + grid[2][3] + grid[3][3] + grid[4][3] + grid[0][4]</code>&nbsp;等于 94 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;n == grid.length &lt;= 100</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划 + 前缀和

每列 $j$ 从上往下染黑的格子数为 $k[j] \in \{0, 1, \ldots, n\}$。白色格子 $(i, j)$ 当且仅当左侧或右侧相邻格子为黑色时计入分数，且同一格子只计一次。因此第 $j$ 列的贡献为：

$$
\max\bigl(0,\ s[j][\max(k[j-1], k[j+1])] - s[j][k[j]]\bigr)
$$

其中 $s[j][h]$ 表示第 $j$ 列前 $h$ 个格子的前缀和（约定边界列高度为 $0$）。

定义 $f[h_1][h_2]$ 表示当前处理到第 $j$ 列，且 $k[j] = h_1$、$k[j-1] = h_2$ 时的最大分数。转移时枚举下一列高度 $hp = k[j+1]$：

$$
g[hp][h_1] = \max_{h_2}\bigl(f[h_1][h_2] + \max(0,\ s[j][\max(h_2, hp)] - s[j][h_1])\bigr)
$$

按 $h_2 \le hp$ 与 $h_2 > hp$ 分别维护前缀最大值与后缀最大值，将每列转移从 $O(n^3)$ 降到 $O(n^2)$。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 为网格边长。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumScore(self, grid: List[List[int]]) -> int:
        n = len(grid)
        s = [[0] * (n + 1) for _ in range(n)]
        for j in range(n):
            for i, x in enumerate(grid):
                s[j][i + 1] = s[j][i] + x[j]
        f = [[-inf] * (n + 1) for _ in range(n + 1)]
        for h in range(n + 1):
            f[h][0] = 0
        for j in range(n - 1):
            g = [[-inf] * (n + 1) for _ in range(n + 1)]
            for h1 in range(n + 1):
                pre = [-inf] * (n + 2)
                pre[0] = f[h1][0]
                for h2 in range(1, n + 1):
                    pre[h2] = max(pre[h2 - 1], f[h1][h2])
                suf = [-inf] * (n + 2)
                for h2 in range(n, -1, -1):
                    v = -inf
                    if f[h1][h2] != -inf:
                        v = f[h1][h2] + max(0, s[j][h2] - s[j][h1])
                    suf[h2] = max(suf[h2 + 1], v)
                for hp in range(n + 1):
                    add = max(0, s[j][hp] - s[j][h1])
                    v1 = -inf if pre[hp] == -inf else pre[hp] + add
                    g[hp][h1] = max(v1, suf[hp + 1])
            f = g
        ans = 0
        for h1 in range(n + 1):
            for h2 in range(n + 1):
                if f[h1][h2] != -inf:
                    ans = max(ans, f[h1][h2] + max(0, s[-1][h2] - s[-1][h1]))
        return ans
```

#### Java

```java
class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        final long inf = Long.MIN_VALUE / 2;
        long[][] s = new long[n][n + 1];
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < n; ++i) {
                s[j][i + 1] = s[j][i] + grid[i][j];
            }
        }
        long[][] f = new long[n + 1][n + 1];
        for (long[] row : f) {
            Arrays.fill(row, inf);
        }
        for (int h = 0; h <= n; ++h) {
            f[h][0] = 0;
        }
        for (int j = 0; j < n - 1; ++j) {
            long[][] g = new long[n + 1][n + 1];
            for (long[] row : g) {
                Arrays.fill(row, inf);
            }
            for (int h1 = 0; h1 <= n; ++h1) {
                long[] pre = new long[n + 2];
                pre[0] = f[h1][0];
                for (int h2 = 1; h2 <= n; ++h2) {
                    pre[h2] = Math.max(pre[h2 - 1], f[h1][h2]);
                }
                long[] suf = new long[n + 2];
                Arrays.fill(suf, inf);
                for (int h2 = n; h2 >= 0; --h2) {
                    long v = f[h1][h2] == inf ? inf : f[h1][h2] + Math.max(0, s[j][h2] - s[j][h1]);
                    suf[h2] = Math.max(suf[h2 + 1], v);
                }
                for (int hp = 0; hp <= n; ++hp) {
                    long add = Math.max(0, s[j][hp] - s[j][h1]);
                    long v1 = pre[hp] == inf ? inf : pre[hp] + add;
                    g[hp][h1] = Math.max(v1, suf[hp + 1]);
                }
            }
            f = g;
        }
        long ans = 0;
        for (int h1 = 0; h1 <= n; ++h1) {
            for (int h2 = 0; h2 <= n; ++h2) {
                if (f[h1][h2] != inf) {
                    ans = Math.max(ans, f[h1][h2] + Math.max(0, s[n - 1][h2] - s[n - 1][h1]));
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumScore(vector<vector<int>>& grid) {
        int n = grid.size();
        const long long inf = LLONG_MIN / 2;
        vector<vector<long long>> s(n, vector<long long>(n + 1));
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < n; ++i) {
                s[j][i + 1] = s[j][i] + grid[i][j];
            }
        }
        vector<vector<long long>> f(n + 1, vector<long long>(n + 1, inf));
        for (int h = 0; h <= n; ++h) {
            f[h][0] = 0;
        }
        for (int j = 0; j < n - 1; ++j) {
            vector<vector<long long>> g(n + 1, vector<long long>(n + 1, inf));
            for (int h1 = 0; h1 <= n; ++h1) {
                vector<long long> pre(n + 2, inf), suf(n + 2, inf);
                pre[0] = f[h1][0];
                for (int h2 = 1; h2 <= n; ++h2) {
                    pre[h2] = max(pre[h2 - 1], f[h1][h2]);
                }
                for (int h2 = n; h2 >= 0; --h2) {
                    long long v = f[h1][h2] == inf ? inf : f[h1][h2] + max(0LL, s[j][h2] - s[j][h1]);
                    suf[h2] = max(suf[h2 + 1], v);
                }
                for (int hp = 0; hp <= n; ++hp) {
                    long long add = max(0LL, s[j][hp] - s[j][h1]);
                    long long v1 = pre[hp] == inf ? inf : pre[hp] + add;
                    g[hp][h1] = max(v1, suf[hp + 1]);
                }
            }
            f.swap(g);
        }
        long long ans = 0;
        for (int h1 = 0; h1 <= n; ++h1) {
            for (int h2 = 0; h2 <= n; ++h2) {
                if (f[h1][h2] != inf) {
                    ans = max(ans, f[h1][h2] + max(0LL, s[n - 1][h2] - s[n - 1][h1]));
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
import "math"

func maximumScore(grid [][]int) int64 {
	n := len(grid)
	const inf = math.MinInt64 / 2
	s := make([][]int64, n)
	for j := 0; j < n; j++ {
		s[j] = make([]int64, n+1)
		for i := 0; i < n; i++ {
			s[j][i+1] = s[j][i] + int64(grid[i][j])
		}
	}
	f := make([][]int64, n+1)
	for i := range f {
		f[i] = make([]int64, n+1)
		for k := range f[i] {
			f[i][k] = inf
		}
	}
	for h := 0; h <= n; h++ {
		f[h][0] = 0
	}
	for j := 0; j < n-1; j++ {
		g := make([][]int64, n+1)
		for i := range g {
			g[i] = make([]int64, n+1)
			for k := range g[i] {
				g[i][k] = inf
			}
		}
		for h1 := 0; h1 <= n; h1++ {
			pre := make([]int64, n+2)
			pre[0] = f[h1][0]
			for h2 := 1; h2 <= n; h2++ {
				pre[h2] = max(pre[h2-1], f[h1][h2])
			}
			suf := make([]int64, n+2)
			for i := range suf {
				suf[i] = inf
			}
			for h2 := n; h2 >= 0; h2-- {
				v := int64(inf)
				if f[h1][h2] != inf {
					v = f[h1][h2] + max(int64(0), s[j][h2]-s[j][h1])
				}
				suf[h2] = max(suf[h2+1], v)
			}
			for hp := 0; hp <= n; hp++ {
				add := max(int64(0), s[j][hp]-s[j][h1])
				v1 := int64(inf)
				if pre[hp] != inf {
					v1 = pre[hp] + add
				}
				g[hp][h1] = max(v1, suf[hp+1])
			}
		}
		f = g
	}
	var ans int64
	for h1 := 0; h1 <= n; h1++ {
		for h2 := 0; h2 <= n; h2++ {
			if f[h1][h2] != inf {
				ans = max(ans, f[h1][h2]+max(int64(0), s[n-1][h2]-s[n-1][h1]))
			}
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
