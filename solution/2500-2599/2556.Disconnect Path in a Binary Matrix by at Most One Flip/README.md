# [2556. 二进制矩阵中翻转最多一次使路径不连通](https://leetcode.cn/problems/disconnect-path-in-a-binary-matrix-by-at-most-one-flip)

[English Version](/solution/2500-2599/2556.Disconnect%20Path%20in%20a%20Binary%20Matrix%20by%20at%20Most%20One%20Flip/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的&nbsp;<code>m x n</code>&nbsp;<strong>二进制</strong> 矩阵&nbsp;<code>grid</code>&nbsp;。你可以从一个格子&nbsp;<code>(row, col)</code>&nbsp;移动到格子&nbsp;<code>(row + 1, col)</code>&nbsp;或者&nbsp;<code>(row, col + 1)</code>&nbsp;，前提是前往的格子值为 <code>1</code>&nbsp;。如果从&nbsp;<code>(0, 0)</code>&nbsp;到&nbsp;<code>(m - 1, n - 1)</code>&nbsp;没有任何路径，我们称该矩阵是&nbsp;<strong>不连通</strong>&nbsp;的。</p>

<p>你可以翻转 <strong>最多一个</strong>&nbsp;格子的值（也可以不翻转）。你 <strong>不能翻转</strong>&nbsp;格子&nbsp;<code>(0, 0)</code> 和&nbsp;<code>(m - 1, n - 1)</code>&nbsp;。</p>

<p>如果可以使矩阵不连通，请你返回&nbsp;<code>true</code>&nbsp;，否则返回<em>&nbsp;</em><code>false</code><em>&nbsp;</em>。</p>

<p><strong>注意</strong>&nbsp;，翻转一个格子的值，可以使它的值从&nbsp;<code>0</code>&nbsp;变&nbsp;<code>1</code>&nbsp;，或从&nbsp;<code>1</code>&nbsp;变&nbsp;<code>0</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2556.Disconnect%20Path%20in%20a%20Binary%20Matrix%20by%20at%20Most%20One%20Flip/images/yetgrid2drawio.png" style="width: 441px; height: 151px;" /></p>

<pre>
<b>输入：</b>grid = [[1,1,1],[1,0,0],[1,1,1]]
<strong>输出：</strong>true
<b>解释：</b>按照上图所示我们翻转蓝色格子里的值，翻转后从 (0, 0) 到 (2, 2) 没有路径。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2556.Disconnect%20Path%20in%20a%20Binary%20Matrix%20by%20at%20Most%20One%20Flip/images/yetgrid3drawio.png" /></p>

<pre>
<b>输入：</b>grid = [[1,1,1],[1,0,1],[1,1,1]]
<b>输出：</b>false
<b>解释：</b>无法翻转至多一个格子，使 (0, 0) 到 (2, 2) 没有路径。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>grid[0][0] == grid[m - 1][n - 1] == 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：两次 DFS**

我们先进行一次 DFS，判断从 `(0, 0)` 到 `(m - 1, n - 1)` 是否存在路径，记结果为 $a$。在 DFS 的过程中，我们将访问过的格子的值置为 $0$，以防止重复访问。

接下来，我们将 `(0, 0)` 和 `(m - 1, n - 1)` 的值置为 $1$，再进行一次 DFS，判断从 `(0, 0)` 到 `(m - 1, n - 1)` 是否存在路径，记结果为 $b$。在 DFS 的过程中，我们将访问过的格子的值置为 $0$，以防止重复访问。

最后，如果 $a$ 和 $b$ 都为 `true`，则返回 `false`，否则返回 `true`。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isPossibleToCutPath(self, grid: List[List[int]]) -> bool:
        def dfs(i, j):
            if i >= m or j >= n or grid[i][j] == 0:
                return False
            grid[i][j] = 0
            if i == m - 1 and j == n - 1:
                return True
            return dfs(i + 1, j) or dfs(i, j + 1)

        m, n = len(grid), len(grid[0])
        a = dfs(0, 0)
        grid[0][0] = grid[-1][-1] = 1
        b = dfs(0, 0)
        return not (a and b)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[][] grid;
    private int m;
    private int n;

    public boolean isPossibleToCutPath(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        boolean a = dfs(0, 0);
        grid[0][0] = 1;
        grid[m - 1][n - 1] = 1;
        boolean b = dfs(0, 0);
        return !(a && b);
    }

    private boolean dfs(int i, int j) {
        if (i >= m || j >= n || grid[i][j] == 0) {
            return false;
        }
        if (i == m - 1 && j == n - 1) {
            return true;
        }
        grid[i][j] = 0;
        return dfs(i + 1, j) || dfs(i, j + 1);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isPossibleToCutPath(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        function<bool(int, int)> dfs = [&](int i, int j) -> bool {
            if (i >= m || j >= n || grid[i][j] == 0) {
                return false;
            }
            if (i == m - 1 && j == n - 1) {
                return true;
            }
            grid[i][j] = 0;
            return dfs(i + 1, j) || dfs(i, j + 1);
        };
        bool a = dfs(0, 0);
        grid[0][0] = grid[m - 1][n - 1] = 1;
        bool b = dfs(0, 0);
        return !(a && b);
    }
};
```

### **Go**

```go
func isPossibleToCutPath(grid [][]int) bool {
	m, n := len(grid), len(grid[0])
	var dfs func(i, j int) bool
	dfs = func(i, j int) bool {
		if i >= m || j >= n || grid[i][j] == 0 {
			return false
		}
		if i == m-1 && j == n-1 {
			return true
		}
		grid[i][j] = 0
		return dfs(i+1, j) || dfs(i, j+1)
	}
	a := dfs(0, 0)
	grid[0][0], grid[m-1][n-1] = 1, 1
	b := dfs(0, 0)
	return !(a && b)
}
```

### **...**

```

```

<!-- tabs:end -->
