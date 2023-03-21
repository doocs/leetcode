# [1504. 统计全 1 子矩形](https://leetcode.cn/problems/count-submatrices-with-all-ones)

[English Version](/solution/1500-1599/1504.Count%20Submatrices%20With%20All%20Ones/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的二进制矩阵&nbsp;<code>mat</code>&nbsp;，请你返回有多少个&nbsp;<strong>子矩形</strong>&nbsp;的元素全部都是 1 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1504.Count%20Submatrices%20With%20All%20Ones/images/ones1-grid.jpg" /></p>

<pre>
<strong>输入：</strong>mat = [[1,0,1],[1,1,0],[1,1,0]]
<strong>输出：</strong>13
<strong>解释：
</strong>有 <strong>6</strong>&nbsp;个 1x1 的矩形。
有 <strong>2</strong> 个 1x2 的矩形。
有 <strong>3</strong> 个 2x1 的矩形。
有 <strong>1</strong> 个 2x2 的矩形。
有 <strong>1</strong> 个 3x1 的矩形。
矩形数目总共 = 6 + 2 + 3 + 1 + 1 = <strong>13</strong>&nbsp;。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1504.Count%20Submatrices%20With%20All%20Ones/images/ones2-grid.jpg" /></p>

<pre>
<strong>输入：</strong>mat = [[0,1,1,0],[0,1,1,1],[1,1,1,0]]
<strong>输出：</strong>24
<strong>解释：</strong>
有 <strong>8</strong> 个 1x1 的子矩形。
有 <strong>5</strong> 个 1x2 的子矩形。
有 <strong>2</strong> 个 1x3 的子矩形。
有 <strong>4</strong> 个 2x1 的子矩形。
有 <strong>2</strong> 个 2x2 的子矩形。
有 <strong>2</strong> 个 3x1 的子矩形。
有 <strong>1</strong> 个 3x2 的子矩形。
矩形数目总共 = 8 + 5 + 2 + 4 + 2 + 2 + 1 = <strong>24</strong><strong> 。</strong>

</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 150</code></li>
	<li><code>mat[i][j]</code>&nbsp;仅包含&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举 + 前缀和**

我们可以枚举矩阵的右下角 $(i, j)$，然后向上枚举矩阵的第一行 $k$，那么每一行以 $(i, j)$ 为右下角的矩阵的宽度就是 $\min_{k \leq i} \textit{g}[k][j]$，其中 $\textit{g}[k][j]$ 表示第 $k$ 行以 $(k, j)$ 为右下角的矩阵的宽度。

因此，我们可以预处理得到二维数组 $g[i][j]$，其中 $g[i][j]$ 表示第 $i$ 行中，从第 $j$ 列向左连续的 $1$ 的个数。

时间复杂度 $O(m^2 \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numSubmat(self, mat: List[List[int]]) -> int:
        m, n = len(mat), len(mat[0])
        g = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                if mat[i][j]:
                    g[i][j] = 1 if j == 0 else 1 + g[i][j - 1]
        ans = 0
        for i in range(m):
            for j in range(n):
                col = inf
                for k in range(i, -1, -1):
                    col = min(col, g[k][j])
                    ans += col
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] g = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 1) {
                    g[i][j] = j == 0 ? 1 : 1 + g[i][j - 1];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int col = 1 << 30;
                for (int k = i; k >= 0 && col > 0; --k) {
                    col = Math.min(col, g[k][j]);
                    ans += col;
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
    int numSubmat(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<int>> g(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 1) {
                    g[i][j] = j == 0 ? 1 : 1 + g[i][j - 1];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int col = 1 << 30;
                for (int k = i; k >= 0 && col > 0; --k) {
                    col = min(col, g[k][j]);
                    ans += col;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func numSubmat(mat [][]int) (ans int) {
	m, n := len(mat), len(mat[0])
	g := make([][]int, m)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			if mat[i][j] == 1 {
				if j == 0 {
					g[i][j] = 1
				} else {
					g[i][j] = 1 + g[i][j-1]
				}
			}
		}
	}
	for i := range g {
		for j := range g[i] {
			col := 1 << 30
			for k := i; k >= 0 && col > 0; k-- {
				col = min(col, g[k][j])
				ans += col
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
