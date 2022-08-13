# [剑指 Offer II 112. 最长递增路径](https://leetcode.cn/problems/fpTFWP)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个&nbsp;<code>m x n</code> 整数矩阵&nbsp;<code>matrix</code> ，找出其中 <strong>最长递增路径</strong> 的长度。</p>

<p>对于每个单元格，你可以往上，下，左，右四个方向移动。 <strong>不能</strong> 在 <strong>对角线</strong> 方向上移动或移动到 <strong>边界外</strong>（即不允许环绕）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20112.%20%E6%9C%80%E9%95%BF%E9%80%92%E5%A2%9E%E8%B7%AF%E5%BE%84/images/grid1.jpg" style="width: 242px; height: 242px;" /></p>

<pre>
<strong>输入：</strong>matrix = [[9,9,4],[6,6,8],[2,1,1]]
<strong>输出：</strong>4 
<strong>解释：</strong>最长递增路径为&nbsp;<code>[1, 2, 6, 9]</code>。</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20112.%20%E6%9C%80%E9%95%BF%E9%80%92%E5%A2%9E%E8%B7%AF%E5%BE%84/images/tmp-grid.jpg" style="width: 253px; height: 253px;" /></p>

<pre>
<strong>输入：</strong>matrix = [[3,4,5],[3,2,6],[2,2,1]]
<strong>输出：</strong>4 
<strong>解释：</strong>最长递增路径是&nbsp;<code>[3, 4, 5, 6]</code>。注意不允许在对角线方向上移动。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[1]]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>0 &lt;= matrix[i][j] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 329&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/">https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

记忆化搜索。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestIncreasingPath(self, matrix: List[List[int]]) -> int:
        @cache
        def dfs(i, j):
            ans = 1
            for a, b in [[-1, 0], [1, 0], [0, 1], [0, -1]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and matrix[x][y] > matrix[i][j]:
                    ans = max(ans, dfs(x, y) + 1)
            return ans

        m, n = len(matrix), len(matrix[0])
        return max(dfs(i, j) for i in range(m) for j in range(n))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[][] memo;
    private int[][] matrix;
    private int m;
    private int n;

    public int longestIncreasingPath(int[][] matrix) {
        this.matrix = matrix;
        m = matrix.length;
        n = matrix[0].length;
        memo = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(memo[i], -1);
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans = Math.max(ans, dfs(i, j));
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int ans = 1;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                ans = Math.max(ans, dfs(x, y) + 1);
            }
        }
        memo[i][j] = ans;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> memo;
    vector<vector<int>> matrix;
    int m;
    int n;

    int longestIncreasingPath(vector<vector<int>>& matrix) {
        m = matrix.size();
        n = matrix[0].size();
        memo.resize(m, vector<int>(n, -1));
        this->matrix = matrix;
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                ans = max(ans, dfs(i, j));
        return ans;
    }

    int dfs(int i, int j) {
        if (memo[i][j] != -1) return memo[i][j];
        int ans = 1;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j])
                ans = max(ans, dfs(x, y) + 1);
        }
        memo[i][j] = ans;
        return ans;
    }
};
```

### **Go**

```go
func longestIncreasingPath(matrix [][]int) int {
	m, n := len(matrix), len(matrix[0])
	memo := make([][]int, m)
	for i := range memo {
		memo[i] = make([]int, n)
		for j := range memo[i] {
			memo[i][j] = -1
		}
	}
	ans := -1
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if memo[i][j] != -1 {
			return memo[i][j]
		}
		ans := 1
		dirs := []int{-1, 0, 1, 0, -1}
        for k := 0; k < 4; k++ {
            x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j] {
				ans = max(ans, dfs(x, y)+1)
			}
		}
		memo[i][j] = ans
		return ans
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			ans = max(ans, dfs(i, j))
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
