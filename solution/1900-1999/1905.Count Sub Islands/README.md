# [1905. 统计子岛屿](https://leetcode.cn/problems/count-sub-islands)

[English Version](/solution/1900-1999/1905.Count%20Sub%20Islands/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个 <code>m x n</code> 的二进制矩阵 <code>grid1</code> 和 <code>grid2</code> ，它们只包含 <code>0</code> （表示水域）和 <code>1</code> （表示陆地）。一个 <strong>岛屿</strong> 是由 <strong>四个方向</strong> （水平或者竖直）上相邻的 <code>1</code> 组成的区域。任何矩阵以外的区域都视为水域。</p>

<p>如果 <code>grid2</code> 的一个岛屿，被 <code>grid1</code> 的一个岛屿 <strong>完全</strong> 包含，也就是说 <code>grid2</code> 中该岛屿的每一个格子都被 <code>grid1</code> 中同一个岛屿完全包含，那么我们称 <code>grid2</code> 中的这个岛屿为 <strong>子岛屿</strong> 。</p>

<p>请你返回 <code>grid2</code> 中 <strong>子岛屿</strong> 的 <strong>数目</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1905.Count%20Sub%20Islands/images/test1.png" style="width: 493px; height: 205px;">
<pre><b>输入：</b>grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
<b>输出：</b>3
<strong>解释：</strong>如上图所示，左边为 grid1 ，右边为 grid2 。
grid2 中标红的 1 区域是子岛屿，总共有 3 个子岛屿。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1905.Count%20Sub%20Islands/images/testcasex2.png" style="width: 491px; height: 201px;">
<pre><b>输入：</b>grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
<b>输出：</b>2 
<strong>解释：</strong>如上图所示，左边为 grid1 ，右边为 grid2 。
grid2 中标红的 1 区域是子岛屿，总共有 2 个子岛屿。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid1.length == grid2.length</code></li>
	<li><code>n == grid1[i].length == grid2[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>grid1[i][j]</code> 和 <code>grid2[i][j]</code> 都要么是 <code>0</code> 要么是 <code>1</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

我们可以遍历矩阵 `grid2` 中的每一个格子 $(i, j)$，如果该格子为 $1$，则从该格子开始进行深度优先搜索，将与该格子相连的所有格子的值都置为 $0$，并记录与该格子相连的所有格子中，`grid1` 中对应格子的值是否为 $1$，如果为 $1$，则说明该格子在 `grid1` 中也是一个岛屿，否则不是。最后统计 `grid2` 中子岛屿的数量即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵 `grid1` 和 `grid2` 的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countSubIslands(self, grid1: List[List[int]], grid2: List[List[int]]) -> int:
        def dfs(i: int, j: int) -> int:
            ok = grid1[i][j]
            grid2[i][j] = 0
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid2[x][y] and not dfs(x, y):
                    ok = 0
            return ok

        m, n = len(grid1), len(grid1[0])
        dirs = (-1, 0, 1, 0, -1)
        return sum(dfs(i, j) for i in range(m) for j in range(n) if grid2[i][j])
```

```python
class Solution:
    def countSubIslands(self, grid1: List[List[int]], grid2: List[List[int]]) -> int:
        def bfs(i: int, j: int) -> int:
            ok = grid1[i][j]
            q = deque([(i, j)])
            grid2[i][j] = 0
            while q:
                i, j = q.popleft()
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and grid2[x][y]:
                        q.append((x, y))
                        ok = ok & grid1[x][y]
                        grid2[x][y] = 0
            return ok

        m, n = len(grid1), len(grid1[0])
        dirs = (-1, 0, 1, 0, -1)
        return sum(bfs(i, j) for i in range(m) for j in range(n) if grid2[i][j])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private final int[] dirs = {-1, 0, 1, 0, -1};
    private int[][] grid1;
    private int[][] grid2;
    private int m;
    private int n;

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        m = grid1.length;
        n = grid1[0].length;
        this.grid1 = grid1;
        this.grid2 = grid2;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1) {
                    ans += dfs(i, j);
                }
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        int ok = grid1[i][j];
        grid2[i][j] = 0;
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1) {
                ok &= dfs(x, y);
            }
        }
        return ok;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countSubIslands(vector<vector<int>>& grid1, vector<vector<int>>& grid2) {
        int m = grid1.size(), n = grid1[0].size();
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        function<int(int, int)> dfs = [&](int i, int j) {
            int ok = grid1[i][j];
            grid2[i][j] = 0;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y]) {
                    ok &= dfs(x, y);
                }
            }
            return ok;
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j]) {
                    ans += dfs(i, j);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countSubIslands(grid1 [][]int, grid2 [][]int) (ans int) {
	m, n := len(grid1), len(grid1[0])
	dirs := [5]int{-1, 0, 1, 0, -1}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		ok := grid1[i][j]
		grid2[i][j] = 0
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1 && dfs(x, y) == 0 {
				ok = 0
			}
		}
		return ok
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid2[i][j] == 1 {
				ans += dfs(i, j)
			}
		}
	}
	return
}
```

### **TypeScript**

```ts
function countSubIslands(grid1: number[][], grid2: number[][]): number {
    const [m, n] = [grid1.length, grid1[0].length];
    let ans = 0;
    const dirs: number[] = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number): number => {
        let ok = grid1[i][j];
        grid2[i][j] = 0;
        for (let k = 0; k < 4; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y]) {
                ok &= dfs(x, y);
            }
        }
        return ok;
    };
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; j++) {
            if (grid2[i][j]) {
                ans += dfs(i, j);
            }
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
