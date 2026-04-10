---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3888.Minimum%20Operations%20to%20Make%20All%20Grid%20Elements%20Equal/README.md
tags:
    - 数组
    - 数学
    - 矩阵
    - 前缀和
---

<!-- problem:start -->

# [3888. 使所有网格元素相等的最小操作次数 🔒](https://leetcode.cn/problems/minimum-operations-to-make-all-grid-elements-equal)

[English Version](/solution/3800-3899/3888.Minimum%20Operations%20to%20Make%20All%20Grid%20Elements%20Equal/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个大小为&nbsp;<code>m × n</code>&nbsp;的 2 维整数数组&nbsp;<code>grid</code>，和一个整数&nbsp;<code>k</code>。</p>

<p>在一次操作中，你可以：</p>

<ul>
	<li>选择&nbsp;<code>grid</code>&nbsp;任意&nbsp;<code>k x k</code>&nbsp;的<strong>&nbsp;子矩阵</strong>，并且</li>
	<li>将该子矩阵中的所有元素加 1。</li>
</ul>

<p>返回使网格中所有元素相等所需的最少操作次数。如果不可能，请返回 -1。</p>
一个子矩阵 <code>(x1, y1, x2, y2)</code> 是由所有满足 <code>x1 &lt;= x &lt;= x2</code> 且&nbsp;<code>y1 &lt;= y &lt;= y2</code> 的矩阵元素 <code>matrix[x][y]</code> 组成的矩阵。

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[3,3,5],[3,3,5]], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p data-end="266" data-start="150">选择左边的 <code>2 x 2</code> 子矩阵（覆盖前两列）并应用该操作两次。</p>

<ul>
	<li>在 1 次操作后：<code>[[4, 4, 5], [4, 4, 5]]</code></li>
	<li>在 2 次操作后：<code>[[5, 5, 5], [5, 5, 5]]</code></li>
</ul>

<p>所有元素都变为 5。因此，所需的最小操作次数是 2。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[1,2],[2,3]], k = 1</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>由于&nbsp;<code>k = 1</code>，每次操作将单个单元格 <code>grid[i][j]</code> 的值加1。要使所有元素相等，最终值必须为 3。</p>

<ul>
	<li>增加&nbsp;<code>grid[0][0] = 1</code>&nbsp;到 3，需要 2&nbsp;次操作。</li>
	<li>增加 <code>grid[0][1] = 2</code> 到 3，需要 1 次操作。</li>
	<li>增加 <code>grid[1][0] = 2</code> 到 3，需要 1 次操作。</li>
</ul>

<p>因此，所需的最小操作次数是&nbsp;<code>2 + 1 + 1 + 0 = 4</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m == grid.length &lt;= 1000</code></li>
	<li><code>1 &lt;= n == grid[i].length &lt;= 1000</code></li>
	<li><code>-10<sup>5</sup> &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= min(m, n)</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二维差分 + 贪心

由于操作只能增加元素的值，因此最终网格的所有元素必须等于某个目标值 $T$，且 $T \ge \max(\textit{grid})$。

从左上角 $(0, 0)$ 开始遍历网格。对于任意位置 $(i, j)$，如果它当前的数值小于 $T$，由于后续的操作（以更靠右或更靠下的位置为左上角的操作）都无法覆盖到 $(i, j)$，因此必须在当前位置执行 $T - \text{current\_val}$ 次以 $(i, j)$ 为左上角的 $k \times k$ 增加操作。

如果每次执行操作都遍历 $k \times k$ 区域，复杂度将达到 $O(m \cdot n \cdot k^2)$。我们可以使用二维差分数组 $\textit{diff}$ 来记录操作。通过实时维护 $\textit{diff}$ 的二维前缀和，我们可以在 $O(1)$ 时间内获取当前位置的累计增量，并在 $O(1)$ 时间内更新一个 $k \times k$ 区域的未来影响。

通常情况下 $T = \max(\textit{grid})$ 即可。但在某些 $k \times k$ 覆盖重叠的情况下，较小的 $T$ 可能导致中间位置被动增加后超过 $T$。根据数学一致性，若 $T = \max(\textit{grid})$ 和 $T = \max(\textit{grid}) + 1$ 均不可行，则该网格无法通过 $k \times k$ 操作变平。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别是网格的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, grid: list[list[int]], k: int) -> int:
        m, n = len(grid), len(grid[0])
        mx = max(max(row) for row in grid)

        def check(target: int) -> int:
            diff = [[0] * (n + 2) for _ in range(m + 2)]
            total_ops = 0

            for i, row in enumerate(grid, 1):
                for j, val in enumerate(row, 1):
                    diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1]

                    cur_val = val + diff[i][j]

                    if cur_val > target:
                        return -1

                    if cur_val < target:
                        if i + k - 1 > m or j + k - 1 > n:
                            return -1

                        needed = target - cur_val
                        total_ops += needed
                        diff[i][j] += needed
                        diff[i + k][j] -= needed
                        diff[i][j + k] -= needed
                        diff[i + k][j + k] += needed
            return total_ops

        for t in range(mx, mx + 2):
            res = check(t)
            if res != -1:
                return res

        return -1
```

#### Java

```java
class Solution {
    int[][] grid;
    int m, n, k;

    public long minOperations(int[][] grid, int k) {
        this.grid = grid;
        this.k = k;
        this.m = grid.length;
        this.n = grid[0].length;

        int mx = Integer.MIN_VALUE;
        for (int[] row : grid) {
            for (int v : row) {
                mx = Math.max(mx, v);
            }
        }

        for (int t = mx; t <= mx + 1; t++) {
            long res = check(t);
            if (res != -1) {
                return res;
            }
        }
        return -1;
    }

    private long check(int target) {
        long[][] diff = new long[m + 2][n + 2];
        long totalOps = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
                long cur = grid[i - 1][j - 1] + diff[i][j];

                if (cur > target) {
                    return -1;
                }

                if (cur < target) {
                    if (i + k - 1 > m || j + k - 1 > n) {
                        return -1;
                    }

                    long need = target - cur;
                    totalOps += need;

                    diff[i][j] += need;
                    diff[i + k][j] -= need;
                    diff[i][j + k] -= need;
                    diff[i + k][j + k] += need;
                }
            }
        }
        return totalOps;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minOperations(vector<vector<int>>& grid, int k) {
        int m = grid.size();
        int n = grid[0].size();
        int mx = grid[0][0];
        for (auto& row : grid) {
            for (int val : row) {
                mx = max(mx, val);
            }
        }

        auto check = [&](int target) -> long long {
            vector<vector<long long>> diff(m + 2, vector<long long>(n + 2, 0));
            long long total_ops = 0;

            for (int i = 1; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
                    long long cur_val = grid[i - 1][j - 1] + diff[i][j];

                    if (cur_val > target) {
                        return -1;
                    }

                    if (cur_val < target) {
                        if (i + k - 1 > m || j + k - 1 > n) {
                            return -1;
                        }

                        long long needed = target - cur_val;
                        total_ops += needed;
                        diff[i][j] += needed;
                        diff[i + k][j] -= needed;
                        diff[i][j + k] -= needed;
                        diff[i + k][j + k] += needed;
                    }
                }
            }

            return total_ops;
        };

        for (int t = mx; t <= mx + 1; ++t) {
            long long res = check(t);
            if (res != -1) {
                return res;
            }
        }

        return -1;
    }
};
```

#### Go

```go
func minOperations(grid [][]int, k int) int64 {
	m, n := len(grid), len(grid[0])
	maxVal := grid[0][0]
	for _, row := range grid {
		maxVal = max(maxVal, slices.Max(row))
	}

	check := func(target int) int64 {
		diff := make([][]int64, m+2)
		for i := range diff {
			diff[i] = make([]int64, n+2)
		}
		var totalOps int64

		for i := 1; i <= m; i++ {
			for j := 1; j <= n; j++ {
				diff[i][j] += diff[i-1][j] + diff[i][j-1] - diff[i-1][j-1]
				curVal := int64(grid[i-1][j-1]) + diff[i][j]

				if curVal > int64(target) {
					return -1
				}

				if curVal < int64(target) {
					if i+k-1 > m || j+k-1 > n {
						return -1
					}
					needed := int64(target) - curVal
					totalOps += needed
					diff[i][j] += needed
					diff[i+k][j] -= needed
					diff[i][j+k] -= needed
					diff[i+k][j+k] += needed
				}
			}
		}
		return totalOps
	}

	for t := maxVal; t <= maxVal+1; t++ {
		if res := check(t); res != -1 {
			return res
		}
	}

	return -1
}
```

#### TypeScript

```ts
function minOperations(grid: number[][], k: number): number {
    const m = grid.length;
    const n = grid[0].length;
    let maxVal = grid[0][0];

    for (const row of grid) {
        for (const val of row) {
            maxVal = Math.max(maxVal, val);
        }
    }

    const check = (target: number): number => {
        const diff: number[][] = Array.from({ length: m + 2 }, () => Array(n + 2).fill(0));
        let totalOps = 0;

        for (let i = 1; i <= m; i++) {
            for (let j = 1; j <= n; j++) {
                diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
                const curVal = grid[i - 1][j - 1] + diff[i][j];

                if (curVal > target) return -1;

                if (curVal < target) {
                    if (i + k - 1 > m || j + k - 1 > n) return -1;

                    const needed = target - curVal;
                    totalOps += needed;
                    diff[i][j] += needed;
                    diff[i + k][j] -= needed;
                    diff[i][j + k] -= needed;
                    diff[i + k][j + k] += needed;
                }
            }
        }

        return totalOps;
    };

    for (let t = maxVal; t <= maxVal + 1; t++) {
        const res = check(t);
        if (res !== -1) return res;
    }

    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
