---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1289.Minimum%20Falling%20Path%20Sum%20II/README.md
rating: 1697
source: 第 15 场双周赛 Q4
tags:
    - 数组
    - 动态规划
    - 矩阵
---

<!-- problem:start -->

# [1289. 下降路径最小和 II](https://leetcode.cn/problems/minimum-falling-path-sum-ii)

[English Version](/solution/1200-1299/1289.Minimum%20Falling%20Path%20Sum%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<code>n x n</code> 整数矩阵&nbsp;<code>grid</code>&nbsp;，请你返回 <strong>非零偏移下降路径</strong> 数字和的最小值。</p>

<p><strong>非零偏移下降路径</strong> 定义为：从&nbsp;<code>grid</code> 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1289.Minimum%20Falling%20Path%20Sum%20II/images/falling-grid.jpg" style="width: 244px; height: 245px;" /></p>

<pre>
<strong>输入：</strong>grid = [[1,2,3],[4,5,6],[7,8,9]]
<strong>输出：</strong>13
<strong>解释：</strong>
所有非零偏移下降路径包括：
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
下降路径中数字和最小的是&nbsp;[1,5,7] ，所以答案是&nbsp;13 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[7]]
<strong>输出：</strong>7
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>-99 &lt;= grid[i][j] &lt;= 99</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 表示前 $i$ 行，且最后一个数字在第 $j$ 列的最小数字和。那么状态转移方程为：

$$
f[i][j] = \min_{k \neq j} f[i - 1][k] + grid[i - 1][j]
$$

其中 $k$ 表示第 $i - 1$ 行的数字在第 $k$ 列，第 $i$ 行第 $j$ 列的数字为 $grid[i - 1][j]$。

最后答案为 $f[n]$ 中的最小值。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 为矩阵的行数。

我们注意到，状态 $f[i][j]$ 只与 $f[i - 1][k]$ 有关，因此我们可以使用滚动数组优化空间复杂度，将空间复杂度优化到 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minFallingPathSum(self, grid: List[List[int]]) -> int:
        n = len(grid)
        f = [0] * n
        for row in grid:
            g = row[:]
            for i in range(n):
                g[i] += min((f[j] for j in range(n) if j != i), default=0)
            f = g
        return min(f)
```

#### Java

```java
class Solution {
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        int[] f = new int[n];
        final int inf = 1 << 30;
        for (int[] row : grid) {
            int[] g = row.clone();
            for (int i = 0; i < n; ++i) {
                int t = inf;
                for (int j = 0; j < n; ++j) {
                    if (j != i) {
                        t = Math.min(t, f[j]);
                    }
                }
                g[i] += (t == inf ? 0 : t);
            }
            f = g;
        }
        return Arrays.stream(f).min().getAsInt();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minFallingPathSum(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<int> f(n);
        const int inf = 1e9;
        for (const auto& row : grid) {
            vector<int> g = row;
            for (int i = 0; i < n; ++i) {
                int t = inf;
                for (int j = 0; j < n; ++j) {
                    if (j != i) {
                        t = min(t, f[j]);
                    }
                }
                g[i] += (t == inf ? 0 : t);
            }
            f = move(g);
        }
        return ranges::min(f);
    }
};
```

#### Go

```go
func minFallingPathSum(grid [][]int) int {
	f := make([]int, len(grid))
	const inf = math.MaxInt32
	for _, row := range grid {
		g := slices.Clone(row)
		for i := range f {
			t := inf
			for j := range row {
				if j != i {
					t = min(t, f[j])
				}
			}
			if t != inf {
				g[i] += t
			}
		}
		f = g
	}
	return slices.Min(f)
}
```

#### TypeScript

```ts
function minFallingPathSum(grid: number[][]): number {
    const n = grid.length;
    const f: number[] = Array(n).fill(0);
    for (const row of grid) {
        const g = [...row];
        for (let i = 0; i < n; ++i) {
            let t = Infinity;
            for (let j = 0; j < n; ++j) {
                if (j !== i) {
                    t = Math.min(t, f[j]);
                }
            }
            g[i] += t === Infinity ? 0 : t;
        }
        f.splice(0, n, ...g);
    }
    return Math.min(...f);
}
```

#### Rust

```rust
impl Solution {
    pub fn min_falling_path_sum(grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let mut f = vec![0; n];
        let inf = i32::MAX;

        for row in grid {
            let mut g = row.clone();
            for i in 0..n {
                let mut t = inf;
                for j in 0..n {
                    if j != i {
                        t = t.min(f[j]);
                    }
                }
                g[i] += if t == inf { 0 } else { t };
            }
            f = g;
        }

        *f.iter().min().unwrap()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
