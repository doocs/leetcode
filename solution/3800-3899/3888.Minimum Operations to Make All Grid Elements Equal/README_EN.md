---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3888.Minimum%20Operations%20to%20Make%20All%20Grid%20Elements%20Equal/README_EN.md
tags:
    - Array
    - Math
    - Matrix
    - Prefix Sum
---

<!-- problem:start -->

# [3888. Minimum Operations to Make All Grid Elements Equal 🔒](https://leetcode.com/problems/minimum-operations-to-make-all-grid-elements-equal)

[中文文档](/solution/3800-3899/3888.Minimum%20Operations%20to%20Make%20All%20Grid%20Elements%20Equal/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>grid</code> of size <code>m &times; n</code>, and an integer <code>k</code>.</p>

<p>In one operation, you can:</p>

<ul>
	<li>Select any <code>k x k</code> <strong>submatrix</strong> of <code>grid</code>, and</li>
	<li>Increment <strong>all elements</strong> inside that <strong>submatrix</strong> by 1.</li>
</ul>

<p>Return the <strong>minimum</strong> number of operations required to make all elements in the grid <strong>equal</strong>. If it is not possible, return -1.</p>
A submatrix <code>(x1, y1, x2, y2)</code> is a matrix that forms by choosing all cells <code>matrix[x][y]</code> where <code>x1 &lt;= x &lt;= x2</code> and <code>y1 &lt;= y &lt;= y2</code>.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[3,3,5],[3,3,5]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p data-end="266" data-start="150">Choose the left <code>2 x 2</code> submatrix (covering the first two columns) and apply the operation twice.</p>

<ul>
	<li>After 1 operation: <code>[[4, 4, 5], [4, 4, 5]]</code></li>
	<li>After 2 operations: <code>[[5, 5, 5], [5, 5, 5]]</code></li>
</ul>

<p>All elements become equal to 5. Thus, the minimum number of operations is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,2],[2,3]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Since <code>k = 1</code>, each operation increments a single cell <code>grid[i][j]</code> by 1. To make all elements equal, the final value must be 3.</p>

<ul>
	<li>Increase <code>grid[0][0] = 1</code> to 3, requiring 2 operations.</li>
	<li>Increase <code>grid[0][1] = 2</code> to 3, requiring 1 operation.</li>
	<li>Increase <code>grid[1][0] = 2</code> to 3, requiring 1 operation.</li>
</ul>

<p>Thus, the minimum number of operations is <code>2 + 1 + 1 + 0 = 4</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == grid.length &lt;= 1000</code></li>
	<li><code>1 &lt;= n == grid[i].length &lt;= 1000</code></li>
	<li><code>-10<sup>5</sup> &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= min(m, n)</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: 2D Difference Array + Greedy

Since the operation can only increase the value of elements, all elements in the final grid must be equal to some target value $T$, and $T \ge \max(\textit{grid})$.

Start traversing the grid from the top-left corner $(0, 0)$. For any position $(i, j)$, if its current value is less than $T$, since subsequent operations (with a more rightward or downward position as the top-left corner) cannot cover $(i, j)$, it is necessary to perform $T - \text{current\_val}$ operations at the current position, each using $(i, j)$ as the top-left corner of a $k \times k$ increment operation.

If each operation traverses the $k \times k$ region, the complexity will reach $O(m \cdot n \cdot k^2)$. We can use a 2D difference array $\textit{diff}$ to record the operations. By maintaining the 2D prefix sum of $\textit{diff}$ in real time, we can obtain the cumulative increment at the current position in $O(1)$ time, and update the future impact of a $k \times k$ region in $O(1)$ time.

In most cases, $T = \max(\textit{grid})$ is sufficient. However, in some cases where $k \times k$ regions overlap, a smaller $T$ may cause the middle positions to be passively increased beyond $T$. According to mathematical consistency, if both $T = \max(\textit{grid})$ and $T = \max(\textit{grid}) + 1$ are not feasible, then it is impossible to flatten the grid using $k \times k$ operations.

The time complexity is $O(m \times n)$ and the space complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the grid, respectively.

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
