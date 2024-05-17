---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1219.Path%20with%20Maximum%20Gold/README.md
rating: 1663
source: 第 157 场周赛 Q3
tags:
    - 数组
    - 回溯
    - 矩阵
---

<!-- problem:start -->

# [1219. 黄金矿工](https://leetcode.cn/problems/path-with-maximum-gold)

[English Version](/solution/1200-1299/1219.Path%20with%20Maximum%20Gold/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们可以枚举每个格子作为起点，然后从起点开始进行深度优先搜索。在搜索的过程中，每遇到一个非零的格子，就将其变成零，并继续搜索。当无法继续搜索时，计算当前的路径的黄金总数，然后将当前的格子变回非零的格子，从而进行回溯。

时间复杂度 $O(m \times n \times 3^k)$，其中 $k$ 是每条路径的最大长度。由于每个格子最多只能被访问一次，因此时间复杂度不会超过 $O(m \times n \times 3^k)$。空间复杂度 $O(m \times n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getMaximumGold(self, grid: List[List[int]]) -> int:
        def dfs(i: int, j: int) -> int:
            if not (0 <= i < m and 0 <= j < n and grid[i][j]):
                return 0
            v = grid[i][j]
            grid[i][j] = 0
            ans = max(dfs(i + a, j + b) for a, b in pairwise(dirs)) + v
            grid[i][j] = v
            return ans

        m, n = len(grid), len(grid[0])
        dirs = (-1, 0, 1, 0, -1)
        return max(dfs(i, j) for i in range(m) for j in range(n))
```

#### Java

```java
class Solution {
    private final int[] dirs = {-1, 0, 1, 0, -1};
    private int[][] grid;
    private int m;
    private int n;

    public int getMaximumGold(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans = Math.max(ans, dfs(i, j));
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return 0;
        }
        int v = grid[i][j];
        grid[i][j] = 0;
        int ans = 0;
        for (int k = 0; k < 4; ++k) {
            ans = Math.max(ans, v + dfs(i + dirs[k], j + dirs[k + 1]));
        }
        grid[i][j] = v;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int getMaximumGold(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        function<int(int, int)> dfs = [&](int i, int j) {
            if (i < 0 || i >= m || j < 0 || j >= n || !grid[i][j]) {
                return 0;
            }
            int v = grid[i][j];
            grid[i][j] = 0;
            int ans = v + max({dfs(i - 1, j), dfs(i + 1, j), dfs(i, j - 1), dfs(i, j + 1)});
            grid[i][j] = v;
            return ans;
        };
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans = max(ans, dfs(i, j));
            }
        }
        return ans;
    }
};
```

#### Go

```go
func getMaximumGold(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0 {
			return 0
		}
		v := grid[i][j]
		grid[i][j] = 0
		ans := 0
		dirs := []int{-1, 0, 1, 0, -1}
		for k := 0; k < 4; k++ {
			ans = max(ans, v+dfs(i+dirs[k], j+dirs[k+1]))
		}
		grid[i][j] = v
		return ans
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			ans = max(ans, dfs(i, j))
		}
	}
	return
}
```

#### TypeScript

```ts
function getMaximumGold(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const dfs = (i: number, j: number): number => {
        if (i < 0 || i >= m || j < 0 || j >= n || !grid[i][j]) {
            return 0;
        }
        const v = grid[i][j];
        grid[i][j] = 0;
        let ans = v + Math.max(dfs(i - 1, j), dfs(i + 1, j), dfs(i, j - 1), dfs(i, j + 1));
        grid[i][j] = v;
        return ans;
    };
    let ans = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            ans = Math.max(ans, dfs(i, j));
        }
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[][]} grid
 * @return {number}
 */
var getMaximumGold = function (grid) {
    const m = grid.length;
    const n = grid[0].length;
    const dfs = (i, j) => {
        if (i < 0 || i >= m || j < 0 || j >= n || !grid[i][j]) {
            return 0;
        }
        const v = grid[i][j];
        grid[i][j] = 0;
        let ans = v + Math.max(dfs(i - 1, j), dfs(i + 1, j), dfs(i, j - 1), dfs(i, j + 1));
        grid[i][j] = v;
        return ans;
    };
    let ans = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            ans = Math.max(ans, dfs(i, j));
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
