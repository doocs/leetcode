# [1219. 黄金矿工](https://leetcode-cn.com/problems/path-with-maximum-gold)

[English Version](/solution/1200-1299/1219.Path%20with%20Maximum%20Gold/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为&nbsp;<code>m * n</code> 的网格 <code>grid</code> 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 <code>0</code>。</p>

<p>为了使收益最大化，矿工需要按以下规则来开采黄金：</p>

<ul>
	<li>每当矿工进入一个单元，就会收集该单元格中的所有黄金。</li>
	<li>矿工每次可以从当前位置向上下左右四个方向走。</li>
	<li>每个单元格只能被开采（进入）一次。</li>
	<li><strong>不得开采</strong>（进入）黄金数目为 <code>0</code> 的单元格。</li>
	<li>矿工可以从网格中 <strong>任意一个</strong> 有黄金的单元格出发或者是停止。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>grid = [[0,6,0],[5,8,7],[0,9,0]]
<strong>输出：</strong>24
<strong>解释：</strong>
[[0,6,0],
 [5,8,7],
 [0,9,0]]
一种收集最多黄金的路线是：9 -&gt; 8 -&gt; 7。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
<strong>输出：</strong>28
<strong>解释：</strong>
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
一种收集最多黄金的路线是：1 -&gt; 2 -&gt; 3 -&gt; 4 -&gt; 5 -&gt; 6 -&gt; 7。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= grid.length,&nbsp;grid[i].length &lt;= 15</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 100</code></li>
	<li>最多 <strong>25 </strong>个单元格中有黄金。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getMaximumGold(self, grid: List[List[int]]) -> int:
        def dfs(i, j):
            if not (0 <= i < m and 0 <= j < n and grid[i][j]):
                return 0
            t = grid[i][j]
            grid[i][j] = 0
            res = 0
            for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                res = max(res, t + dfs(i + a, j + b))
            grid[i][j] = t
            return res

        m, n = len(grid), len(grid[0])
        ans = 0
        for i in range(m):
            for j in range(n):
                ans = max(ans, dfs(i, j))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[][] grid;

    public int getMaximumGold(int[][] grid) {
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                ans = Math.max(ans, dfs(i, j));
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        int t = grid[i][j];
        grid[i][j] = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        int res = 0;
        for (int k = 0; k < 4; ++k) {
            res = Math.max(res, t + dfs(i + dirs[k], j + dirs[k + 1]));
        }
        grid[i][j] = t;
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> grid;
    vector<int> dirs = {-1, 0, 1, 0, -1};

    int getMaximumGold(vector<vector<int>>& grid) {
        this->grid = grid;
        int ans = 0;
        for (int i = 0; i < grid.size(); ++i)
            for (int j = 0; j < grid[0].size(); ++j)
                ans = max(ans, dfs(i, j));
        return ans;
    }

    int dfs(int i, int j) {
        if (i < 0 || i >= grid.size() || j < 0 || j >= grid[0].size() || grid[i][j] == 0) return 0;
        int t = grid[i][j];
        grid[i][j] = 0;
        int res = 0;
        for (int k = 0; k < 4; ++k) res = max(res, t + dfs(i + dirs[k], j + dirs[k + 1]));
        grid[i][j] = t;
        return res;
    }
};
```

### **Go**

```go
func getMaximumGold(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0 {
			return 0
		}
		t := grid[i][j]
		grid[i][j] = 0
		res := 0
		dirs := []int{-1, 0, 1, 0, -1}
		for k := 0; k < 4; k++ {
			res = max(res, t+dfs(i+dirs[k], j+dirs[k+1]))
		}
		grid[i][j] = t
		return res
	}
	ans := 0
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
