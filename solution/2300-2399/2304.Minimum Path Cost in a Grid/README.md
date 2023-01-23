# [2304. 网格中的最小路径代价](https://leetcode.cn/problems/minimum-path-cost-in-a-grid)

[English Version](/solution/2300-2399/2304.Minimum%20Path%20Cost%20in%20a%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数矩阵&nbsp;<code>grid</code> ，矩阵大小为 <code>m x n</code> ，由从 <code>0</code> 到 <code>m * n - 1</code> 的不同整数组成。你可以在此矩阵中，从一个单元格移动到 <strong>下一行</strong> 的任何其他单元格。如果你位于单元格 <code>(x, y)</code> ，且满足 <code>x &lt; m - 1</code> ，你可以移动到 <code>(x + 1, 0)</code>, <code>(x + 1, 1)</code>, ..., <code>(x + 1, n - 1)</code><strong> </strong>中的任何一个单元格。<strong>注意：</strong>&nbsp;在最后一行中的单元格不能触发移动。</p>

<p>每次可能的移动都需要付出对应的代价，代价用一个下标从 <strong>0</strong> 开始的二维数组 <code>moveCost</code> 表示，该数组大小为 <code>(m * n) x n</code> ，其中 <code>moveCost[i][j]</code> 是从值为 <code>i</code> 的单元格移动到下一行第 <code>j</code> 列单元格的代价。从&nbsp;<code>grid</code> 最后一行的单元格移动的代价可以忽略。</p>

<p><code>grid</code> 一条路径的代价是：所有路径经过的单元格的 <strong>值之和</strong> 加上 所有移动的 <strong>代价之和 </strong>。从 <strong>第一行</strong> 任意单元格出发，返回到达 <strong>最后一行</strong> 任意单元格的最小路径代价<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2304.Minimum%20Path%20Cost%20in%20a%20Grid/images/griddrawio-2.png" style="width: 301px; height: 281px;" /></p>

<pre>
<strong>输入：</strong>grid = [[5,3],[4,0],[2,1]], moveCost = [[9,8],[1,5],[10,12],[18,6],[2,4],[14,3]]
<strong>输出：</strong>17
<strong>解释：</strong>最小代价的路径是 5 -&gt; 0 -&gt; 1 。
- 路径途经单元格值之和 5 + 0 + 1 = 6 。
- 从 5 移动到 0 的代价为 3 。
- 从 0 移动到 1 的代价为 8 。
路径总代价为 6 + 3 + 8 = 17 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[5,1,2],[4,0,3]], moveCost = [[12,10,15],[20,23,8],[21,7,1],[8,1,13],[9,10,25],[5,3,2]]
<strong>输出：</strong>6
<strong>解释：</strong>
最小代价的路径是 2 -&gt; 3 。 
- 路径途经单元格值之和 2 + 3 = 5 。 
- 从 2 移动到 3 的代价为 1 。 
路径总代价为 5 + 1 = 6 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 50</code></li>
	<li><code>grid</code> 由从 <code>0</code> 到 <code>m * n - 1</code> 的不同整数组成</li>
	<li><code>moveCost.length == m * n</code></li>
	<li><code>moveCost[i].length == n</code></li>
	<li><code>1 &lt;= moveCost[i][j] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i][j]$ 表示从第一行出发，到达第 $i$ 行第 $j$ 列的最小路径代价。由于每次只能从上一行的某一列移动到当前行的某一列，因此 $f[i][j]$ 的值可以从 $f[i - 1][k]$ 转移而来，其中 $k$ 的取值范围为 $[0, n - 1]$。因此状态转移方程为：

$$
f[i][j] = \min_{0 \leq k < n} \{f[i - 1][k] + \text{moveCost}[grid[i - 1][k]][j] + grid[i][j]\}
$$

其中 $\text{moveCost}[grid[i - 1][k]][j]$ 表示从第 $i - 1$ 行第 $k$ 列移动到第 $i$ 行第 $j$ 列的代价。

最终答案即为 $f[m - 1][j]$ 的最小值，其中 $j$ 的取值范围为 $[0, n - 1]$。

由于每次转移只需要用到上一行的状态，因此可以将空间复杂度优化到 $O(n)$。

时间复杂度 $O(m \times n^2)$，空间复杂度 $O(n)$。其中 $m$ 和 $n$ 分别为网格的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minPathCost(self, grid: List[List[int]], moveCost: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        f = grid[0]
        for i in range(1, m):
            g = [inf] * n
            for j in range(n):
                for k in range(n):
                    g[j] = min(g[j], f[k] + moveCost[grid[i - 1][k]][j] + grid[i][j])
            f = g
        return min(f)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length, n = grid[0].length;
        int[] f = grid[0];
        final int inf = 1 << 30;
        for (int i = 1; i < m; ++i) {
            int[] g = new int[n];
            Arrays.fill(g, inf);
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    g[j] = Math.min(g[j], f[k] + moveCost[grid[i - 1][k]][j] + grid[i][j]);
                }
            }
            f = g;
        }

        // return Arrays.stream(f).min().getAsInt();
        int ans = inf;
        for (int v : f) {
            ans = Math.min(ans, v);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minPathCost(vector<vector<int>>& grid, vector<vector<int>>& moveCost) {
        int m = grid.size(), n = grid[0].size();
        const int inf = 1 << 30;
        vector<int> f = grid[0];
        for (int i = 1; i < m; ++i) {
            vector<int> g(n, inf);
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    g[j] = min(g[j], f[k] + moveCost[grid[i - 1][k]][j] + grid[i][j]);
                }
            }
            f = move(g);
        }
        return *min_element(f.begin(), f.end());
    }
};
```

### **Go**

```go
func minPathCost(grid [][]int, moveCost [][]int) int {
	m, n := len(grid), len(grid[0])
	const inf = 1 << 30
	f := grid[0]
	for i := 1; i < m; i++ {
		g := make([]int, n)
		for j := 0; j < n; j++ {
			g[j] = inf
			for k := 0; k < n; k++ {
				g[j] = min(g[j], f[k]+moveCost[grid[i-1][k]][j]+grid[i][j])
			}
		}
		f = g
	}
	ans := inf
	for _, v := range f {
		ans = min(ans, v)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_path_cost(grid: Vec<Vec<i32>>, move_cost: Vec<Vec<i32>>) -> i32 {
        let (m, n) = (grid.len(), grid[0].len());
        let mut dp = vec![0; n];
        for i in 0..m - 1 {
            let mut counter = vec![i32::MAX; n];
            for j in 0..n {
                let val = grid[i][j];
                for k in 0..n {
                    counter[k] = counter[k].min(val + move_cost[val as usize][k] + dp[j]);
                }
            }
            for j in 0..n {
                dp[j] = counter[j];
            }
        }
        let mut res = i32::MAX;
        for i in 0..n {
            res = res.min(dp[i] + grid[m - 1][i]);
        }
        res
    }
}
```

### **TypeScript**

```ts
function minPathCost(grid: number[][], moveCost: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    let pre = grid[0].slice();
    for (let i = 1; i < m; i++) {
        let next = new Array(n);
        for (let j = 0; j < n; j++) {
            const key = grid[i - 1][j];
            for (let k = 0; k < n; k++) {
                let sum = pre[j] + moveCost[key][k] + grid[i][k];
                if (j == 0 || next[k] > sum) {
                    next[k] = sum;
                }
            }
        }
        pre = next;
    }
    return Math.min(...pre);
}
```

### **...**

```

```

<!-- tabs:end -->
