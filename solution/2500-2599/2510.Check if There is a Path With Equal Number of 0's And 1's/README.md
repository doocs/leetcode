---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2510.Check%20if%20There%20is%20a%20Path%20With%20Equal%20Number%20of%200%27s%20And%201%27s/README.md
tags:
    - 数组
    - 动态规划
    - 矩阵
---

# [2510. 检查是否有路径经过相同数量的 0 和 1 🔒](https://leetcode.cn/problems/check-if-there-is-a-path-with-equal-number-of-0s-and-1s)

[English Version](/solution/2500-2599/2510.Check%20if%20There%20is%20a%20Path%20With%20Equal%20Number%20of%200%27s%20And%201%27s/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 <strong>下标从 0 开始</strong> 的 <code>m x n</code> 的 <strong>二进制</strong> 矩阵 <code>grid</code> ，从坐标为 <code>(row, col)</code> 的元素可以向右走 <code>(row, col+1)</code> 或向下走 <code>(row+1, col)</code> 。</p>

<p>返回一个布尔值，表示从 <code>(0, 0)</code> 出发是否存在一条路径，经过 <strong>相同</strong> 数量的 <code>0</code> 和 <code>1</code>，到达终点 <code>(m-1, n-1)</code> 。如果存在这样的路径返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1 ：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2510.Check%20if%20There%20is%20a%20Path%20With%20Equal%20Number%20of%200%27s%20And%201%27s/images/yetgriddrawio-4.png" />
<pre>
<b>输入：</b>grid = [[0,1,0,0],[0,1,0,0],[1,0,1,0]]
<b>输出：</b>true
<b>解释：</b>以上图中用蓝色标记的路径是一个有效的路径，因为路径上有 3 个值为 1 的单元格和 3 个值为 0 的单元格。由于存在一个有效的路径，因此返回 true 。
</pre>

<p><strong class="example">示例 2 ：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2510.Check%20if%20There%20is%20a%20Path%20With%20Equal%20Number%20of%200%27s%20And%201%27s/images/yetgrid2drawio-1.png" style="width: 151px; height: 151px;" />
<pre>
<b>输入：</b>grid = [[1,1,0],[0,0,1],[1,0,0]]
<b>输出：</b>false
<b>解释：</b>这个网格中没有一条路径经过相等数量的0和1。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 100</code></li>
	<li><code>grid[i][j]</code> 不是&nbsp;<code>0</code> 就是&nbsp;<code>1</code> 。</li>
</ul>

## 解法

### 方法一：记忆化搜索

根据题目描述我们知道，从左上角到右下角的路径上 $0$ 的个数和 $1$ 的个数相等，个数总和为 $m + n - 1$，即 $0$ 的个数和 $1$ 的个数都为 $(m + n - 1) / 2$。

因此我们可以使用记忆化搜索，从左上角开始，向右或向下移动，直到到达右下角，判断路径上 $0$ 的个数和 $1$ 的个数是否相等即可。

时间复杂度 $O(m \times n \times (m + n))$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def isThereAPath(self, grid: List[List[int]]) -> bool:
        @cache
        def dfs(i, j, k):
            if i >= m or j >= n:
                return False
            k += grid[i][j]
            if k > s or i + j + 1 - k > s:
                return False
            if i == m - 1 and j == n - 1:
                return k == s
            return dfs(i + 1, j, k) or dfs(i, j + 1, k)

        m, n = len(grid), len(grid[0])
        s = m + n - 1
        if s & 1:
            return False
        s >>= 1
        return dfs(0, 0, 0)
```

```java
class Solution {
    private int s;
    private int m;
    private int n;
    private int[][] grid;
    private Boolean[][][] f;

    public boolean isThereAPath(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        s = m + n - 1;
        f = new Boolean[m][n][s];
        if (s % 2 == 1) {
            return false;
        }
        s >>= 1;
        return dfs(0, 0, 0);
    }

    private boolean dfs(int i, int j, int k) {
        if (i >= m || j >= n) {
            return false;
        }
        k += grid[i][j];
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        if (k > s || i + j + 1 - k > s) {
            return false;
        }
        if (i == m - 1 && j == n - 1) {
            return k == s;
        }
        f[i][j][k] = dfs(i + 1, j, k) || dfs(i, j + 1, k);
        return f[i][j][k];
    }
}
```

```cpp
class Solution {
public:
    bool isThereAPath(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int s = m + n - 1;
        if (s & 1) return false;
        int f[m][n][s];
        s >>= 1;
        memset(f, -1, sizeof f);
        function<bool(int, int, int)> dfs = [&](int i, int j, int k) -> bool {
            if (i >= m || j >= n) return false;
            k += grid[i][j];
            if (f[i][j][k] != -1) return f[i][j][k];
            if (k > s || i + j + 1 - k > s) return false;
            if (i == m - 1 && j == n - 1) return k == s;
            f[i][j][k] = dfs(i + 1, j, k) || dfs(i, j + 1, k);
            return f[i][j][k];
        };
        return dfs(0, 0, 0);
    }
};
```

```go
func isThereAPath(grid [][]int) bool {
	m, n := len(grid), len(grid[0])
	s := m + n - 1
	if s%2 == 1 {
		return false
	}
	s >>= 1
	f := [100][100][200]int{}
	var dfs func(i, j, k int) bool
	dfs = func(i, j, k int) bool {
		if i >= m || j >= n {
			return false
		}
		k += grid[i][j]
		if f[i][j][k] != 0 {
			return f[i][j][k] == 1
		}
		f[i][j][k] = 2
		if k > s || i+j+1-k > s {
			return false
		}
		if i == m-1 && j == n-1 {
			return k == s
		}
		res := dfs(i+1, j, k) || dfs(i, j+1, k)
		if res {
			f[i][j][k] = 1
		}
		return res
	}
	return dfs(0, 0, 0)
}
```

<!-- tabs:end -->

<!-- end -->
