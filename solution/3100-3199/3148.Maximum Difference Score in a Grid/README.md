# [3148. 矩阵中的最大得分](https://leetcode.cn/problems/maximum-difference-score-in-a-grid)

[English Version](/solution/3100-3199/3148.Maximum%20Difference%20Score%20in%20a%20Grid/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由 <strong>正整数</strong> 组成、大小为 <code>m x n</code> 的矩阵 <code>grid</code>。你可以从矩阵中的任一单元格移动到另一个位于正下方或正右侧的任意单元格（不必相邻）。从值为 <code>c1</code> 的单元格移动到值为 <code>c2</code> 的单元格的得分为 <code>c2 - c1</code> 。</p>

<p>你可以从<strong> 任一</strong> 单元格开始，并且必须至少移动一次。</p>

<p>返回你能得到的 <strong>最大 </strong>总得分。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3148.Maximum%20Difference%20Score%20in%20a%20Grid/images/grid1.png" style="width: 240px; height: 240px;" />
<div class="example-block">
<p><strong>输入：</strong><span class="example-io">grid = [[9,5,7,3],[8,9,6,1],[6,7,14,3],[2,5,3,1]]</span></p>

<p><strong>输出：</strong><span class="example-io">9</span></p>

<p><strong>解释：</strong>从单元格 <code>(0, 1)</code> 开始，并执行以下移动：<br />
- 从单元格 <code>(0, 1)</code> 移动到 <code>(2, 1)</code>，得分为 <code>7 - 5 = 2</code> 。<br />
- 从单元格 <code>(2, 1)</code> 移动到 <code>(2, 2)</code>，得分为 <code>14 - 7 = 7</code> 。<br />
总得分为 <code>2 + 7 = 9</code> 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3148.Maximum%20Difference%20Score%20in%20a%20Grid/images/moregridsdrawio-1.png" style="width: 180px; height: 116px;" /></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">grid = [[4,3,2],[3,2,1]]</span></p>

<p><strong>输出：</strong><span class="example-io">-1</span></p>

<p><strong>解释：</strong>从单元格 <code>(0, 0)</code> 开始，执行一次移动：从 <code>(0, 0)</code> 到 <code>(0, 1)</code> 。得分为 <code>3 - 4 = -1</code> 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 1000</code></li>
	<li><code>4 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：动态规划

根据题目描述，如果我们经过的单元格的值依次是 $c_1, c_2, \cdots, c_k$，那么我们的得分就是 $c_2 - c_1 + c_3 - c_2 + \cdots + c_k - c_{k-1} = c_k - c_1$。因此，问题转化为：对于矩阵的每个单元格 $(i, j)$，如果我们将其作为终点，那么起点的最小值是多少。

我们可以使用动态规划来解决这个问题。我们定义 $f[i][j]$ 表示以 $(i, j)$ 为终点的路径的最小值。那么我们可以得到状态转移方程：

$$
f[i][j] = \min(f[i-1][j], f[i][j-1], grid[i][j])
$$

那么答案为 $\text{grid}[i][j] - \min(f[i-1][j], f[i][j-1])$ 的最大值。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def maxScore(self, grid: List[List[int]]) -> int:
        f = [[0] * len(grid[0]) for _ in range(len(grid))]
        ans = -inf
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                mi = inf
                if i:
                    mi = min(mi, f[i - 1][j])
                if j:
                    mi = min(mi, f[i][j - 1])
                ans = max(ans, x - mi)
                f[i][j] = min(x, mi)
        return ans
```

```java
class Solution {
    public int maxScore(List<List<Integer>> grid) {
        int m = grid.size(), n = grid.get(0).size();
        final int inf = 1 << 30;
        int ans = -inf;
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int mi = inf;
                if (i > 0) {
                    mi = Math.min(mi, f[i - 1][j]);
                }
                if (j > 0) {
                    mi = Math.min(mi, f[i][j - 1]);
                }
                ans = Math.max(ans, grid.get(i).get(j) - mi);
                f[i][j] = Math.min(grid.get(i).get(j), mi);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxScore(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        const int inf = 1 << 30;
        int ans = -inf;
        int f[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int mi = inf;
                if (i) {
                    mi = min(mi, f[i - 1][j]);
                }
                if (j) {
                    mi = min(mi, f[i][j - 1]);
                }
                ans = max(ans, grid[i][j] - mi);
                f[i][j] = min(grid[i][j], mi);
            }
        }
        return ans;
    }
};
```

```go
func maxScore(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	const inf int = 1 << 30
	ans := -inf
	for i, row := range grid {
		for j, x := range row {
			mi := inf
			if i > 0 {
				mi = min(mi, f[i-1][j])
			}
			if j > 0 {
				mi = min(mi, f[i][j-1])
			}
			ans = max(ans, x-mi)
			f[i][j] = min(x, mi)
		}
	}
	return ans
}
```

```ts
function maxScore(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    const f: number[][] = Array.from({ length: m }, () => Array.from({ length: n }, () => 0));
    let ans = -Infinity;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            let mi = Infinity;
            if (i) {
                mi = Math.min(mi, f[i - 1][j]);
            }
            if (j) {
                mi = Math.min(mi, f[i][j - 1]);
            }
            ans = Math.max(ans, grid[i][j] - mi);
            f[i][j] = Math.min(mi, grid[i][j]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
