# [329. 矩阵中的最长递增路径](https://leetcode.cn/problems/longest-increasing-path-in-a-matrix)

[English Version](/solution/0300-0399/0329.Longest%20Increasing%20Path%20in%20a%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 <code>m x n</code> 整数矩阵 <code>matrix</code> ，找出其中 <strong>最长递增路径</strong> 的长度。</p>

<p>对于每个单元格，你可以往上，下，左，右四个方向移动。 你 <strong>不能</strong> 在 <strong>对角线</strong> 方向上移动或移动到 <strong>边界外</strong>（即不允许环绕）。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0329.Longest%20Increasing%20Path%20in%20a%20Matrix/images/grid1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>输入：</strong>matrix = [[9,9,4],[6,6,8],[2,1,1]]
<strong>输出：</strong>4 
<strong>解释：</strong>最长递增路径为 <code>[1, 2, 6, 9]</code>。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0329.Longest%20Increasing%20Path%20in%20a%20Matrix/images/tmp-grid.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>输入：</strong>matrix = [[3,4,5],[3,2,6],[2,2,1]]
<strong>输出：</strong>4 
<strong>解释：</strong>最长递增路径是 <code>[3, 4, 5, 6]</code>。注意不允许在对角线方向上移动。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[1]]
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 <= m, n <= 200</code></li>
	<li><code>0 <= matrix[i][j] <= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

### 方法一：记忆化搜索

我们设计一个函数 $dfs(i, j)$，它表示从矩阵中的坐标 $(i, j)$ 出发，可以得到的最长递增路径的长度。那么答案就是 $\max_{i, j} \textit{dfs}(i, j)$。

函数 $dfs(i, j)$ 的执行逻辑如下：

-   如果 $(i, j)$ 已经被访问过，直接返回 $\textit{f}(i, j)$；
-   否则对 $(i, j)$ 进行搜索，搜索四个方向的坐标 $(x, y)$，如果满足 $0 \le x < m, 0 \le y < n$ 以及 $matrix[x][y] \gt matrix[i][j]$，那么对 $(x, y)$ 进行搜索。搜索结束后，将 $\textit{f}(i, j)$ 更新为 $\textit{f}(i, j) = \max(\textit{f}(i, j), \textit{f}(x, y) + 1)$。最后返回 $\textit{f}(i, j)$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

相似题目：[2328. 网格图中递增路径的数目](/solution/2300-2399/2328.Number%20of%20Increasing%20Paths%20in%20a%20Grid/README.md)。

<!-- tabs:start -->

```python
class Solution:
    def longestIncreasingPath(self, matrix: List[List[int]]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            ans = 0
            for a, b in pairwise((-1, 0, 1, 0, -1)):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and matrix[x][y] > matrix[i][j]:
                    ans = max(ans, dfs(x, y))
            return ans + 1

        m, n = len(matrix), len(matrix[0])
        return max(dfs(i, j) for i in range(m) for j in range(n))
```

```java
class Solution {
    private int m;
    private int n;
    private int[][] matrix;
    private int[][] f;

    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        f = new int[m][n];
        this.matrix = matrix;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans = Math.max(ans, dfs(i, j));
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        if (f[i][j] != 0) {
            return f[i][j];
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                f[i][j] = Math.max(f[i][j], dfs(x, y));
            }
        }
        return ++f[i][j];
    }
}
```

```cpp
class Solution {
public:
    int longestIncreasingPath(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        int f[m][n];
        memset(f, 0, sizeof(f));
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};

        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (f[i][j]) {
                return f[i][j];
            }
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                    f[i][j] = max(f[i][j], dfs(x, y));
                }
            }
            return ++f[i][j];
        };

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans = max(ans, dfs(i, j));
            }
        }
        return ans;
    }
};
```

```go
func longestIncreasingPath(matrix [][]int) (ans int) {
	m, n := len(matrix), len(matrix[0])
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if f[i][j] != 0 {
			return f[i][j]
		}
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if 0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j] {
				f[i][j] = max(f[i][j], dfs(x, y))
			}
		}
		f[i][j]++
		return f[i][j]
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			ans = max(ans, dfs(i, j))
		}
	}
	return
}
```

```ts
function longestIncreasingPath(matrix: number[][]): number {
    const m = matrix.length;
    const n = matrix[0].length;
    const f: number[][] = Array(m)
        .fill(0)
        .map(() => Array(n).fill(0));
    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number): number => {
        if (f[i][j] > 0) {
            return f[i][j];
        }
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                f[i][j] = Math.max(f[i][j], dfs(x, y));
            }
        }
        return ++f[i][j];
    };
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            ans = Math.max(ans, dfs(i, j));
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
