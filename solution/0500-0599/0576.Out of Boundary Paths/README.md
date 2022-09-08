# [576. 出界的路径数](https://leetcode.cn/problems/out-of-boundary-paths)

[English Version](/solution/0500-0599/0576.Out%20of%20Boundary%20Paths/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>m x n</code> 的网格和一个球。球的起始坐标为 <code>[startRow, startColumn]</code> 。你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 <strong>最多</strong> 可以移动 <code>maxMove</code> 次球。</p>

<p>给你五个整数 <code>m</code>、<code>n</code>、<code>maxMove</code>、<code>startRow</code> 以及 <code>startColumn</code> ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 后的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0576.Out%20of%20Boundary%20Paths/images/out_of_boundary_paths_1.png" style="width: 500px; height: 296px;" />
<pre>
<strong>输入：</strong>m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
<strong>输出：</strong>6
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0576.Out%20of%20Boundary%20Paths/images/out_of_boundary_paths_2.png" style="width: 500px; height: 293px;" />
<pre>
<strong>输入：</strong>m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
<strong>输出：</strong>12
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>0 &lt;= maxMove &lt;= 50</code></li>
	<li><code>0 &lt;= startRow &lt; m</code></li>
	<li><code>0 &lt;= startColumn &lt; n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

定义 `dfs(i, j, k)` 表示当前位于坐标 $(i, j)$，且剩余移动次数为 $k$ 时，可以出界的路径数。记忆化搜索即可。

时间复杂度 $O(m\times n\times k)$，空间复杂度 $O(m\times n\times k)$。其中 $m$, $n$, $k$ 分别表示网格的行数、列数、最大可移动次数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findPaths(self, m: int, n: int, maxMove: int, startRow: int, startColumn: int) -> int:
        @cache
        def dfs(i, j, k):
            if i < 0 or j < 0 or i >= m or j >= n:
                return 1
            if k <= 0:
                return 0
            res = 0
            for a, b in [[-1, 0], [1, 0], [0, 1], [0, -1]]:
                x, y = i + a, j + b
                res += dfs(x, y, k - 1)
                res %= mod
            return res

        mod = 10**9 + 7
        return dfs(startRow, startColumn, maxMove)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int m;
    private int n;
    private int[][][] f;
    private static final int[] DIRS = {-1, 0, 1, 0, -1};
    private static final int MOD = (int) 1e9 + 7;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        this.m = m;
        this.n = n;
        f = new int[m + 1][n + 1][maxMove + 1];
        for (var a : f) {
            for (var b : a) {
                Arrays.fill(b, -1);
            }
        }
        return dfs(startRow, startColumn, maxMove);
    }

    private int dfs(int i, int j, int k) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return 1;
        }
        if (f[i][j][k] != -1) {
            return f[i][j][k];
        }
        if (k == 0) {
            return 0;
        }
        int res = 0;
        for (int t = 0; t < 4; ++t) {
            int x = i + DIRS[t];
            int y = j + DIRS[t + 1];
            res += dfs(x, y, k - 1);
            res %= MOD;
        }
        f[i][j][k] = res;
        return res;
    }
}
```

```java
class Solution {
    public int findPaths(int m, int n, int N, int i, int j) {
        final int MOD = (int) (1e9 + 7);
        final int[] dirs = new int[] {-1, 0, 1, 0, -1};
        int[][] f = new int[m][n];
        f[i][j] = 1;
        int res = 0;
        for (int step = 0; step < N; ++step) {
            int[][] temp = new int[m][n];
            for (int x = 0; x < m; ++x) {
                for (int y = 0; y < n; ++y) {
                    for (int k = 0; k < 4; ++k) {
                        int tx = x + dirs[k], ty = y + dirs[k + 1];
                        if (tx >= 0 && tx < m && ty >= 0 && ty < n) {
                            temp[tx][ty] += f[x][y];
                            temp[tx][ty] %= MOD;
                        } else {
                            res += f[x][y];
                            res %= MOD;
                        }
                    }
                }
            }
            f = temp;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int m;
    int n;
    const int mod = 1e9 + 7;
    int f[51][51][51];
    int dirs[5] = {-1, 0, 1, 0, -1};

    int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        memset(f, 0xff, sizeof(f));
        this->m = m;
        this->n = n;
        return dfs(startRow, startColumn, maxMove);
    }

    int dfs(int i, int j, int k) {
        if (i < 0 || i >= m || j < 0 || j >= n) return 1;
        if (f[i][j][k] != -1) return f[i][j][k];
        if (k == 0) return 0;
        int res = 0;
        for (int t = 0; t < 4; ++t) {
            int x = i + dirs[t], y = j + dirs[t + 1];
            res += dfs(x, y, k - 1);
            res %= mod;
        }
        f[i][j][k] = res;
        return res;
    }
};
```

### **Go**

```go
func findPaths(m int, n int, maxMove int, startRow int, startColumn int) int {
	f := make([][][]int, m+1)
	for i := range f {
		f[i] = make([][]int, n+1)
		for j := range f[i] {
			f[i][j] = make([]int, maxMove+1)
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	var mod int = 1e9 + 7
	dirs := []int{-1, 0, 1, 0, -1}
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i < 0 || i >= m || j < 0 || j >= n {
			return 1
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		if k == 0 {
			return 0
		}
		res := 0
		for t := 0; t < 4; t++ {
			x, y := i+dirs[t], j+dirs[t+1]
			res += dfs(x, y, k-1)
			res %= mod
		}
		f[i][j][k] = res
		return res
	}
	return dfs(startRow, startColumn, maxMove)
}
```

### **...**

```

```

<!-- tabs:end -->
