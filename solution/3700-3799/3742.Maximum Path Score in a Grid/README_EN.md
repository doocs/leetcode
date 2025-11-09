---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3742.Maximum%20Path%20Score%20in%20a%20Grid/README_EN.md
---

<!-- problem:start -->

# [3742. Maximum Path Score in a Grid](https://leetcode.com/problems/maximum-path-score-in-a-grid)

[中文文档](/solution/3700-3799/3742.Maximum%20Path%20Score%20in%20a%20Grid/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> grid where each cell contains one of the values 0, 1, or 2. You are also given an integer <code>k</code>.</p>

<p>You start from the top-left corner <code>(0, 0)</code> and want to reach the bottom-right corner <code>(m - 1, n - 1)</code> by moving only <strong>right</strong> or <strong>down</strong>.</p>

<p>Each cell contributes a specific score and incurs an associated cost, according to their cell values:</p>

<ul>
	<li>0: adds 0 to your score and costs 0.</li>
	<li>1: adds 1 to your score and costs 1.</li>
	<li>2: adds 2 to your score and costs 1. ​​​​​​​</li>
</ul>

<p>Return the <strong>maximum</strong> score achievable without exceeding a total cost of <code>k</code>, or -1 if no valid path exists.</p>

<p><strong>Note:</strong> If you reach the last cell but the total cost exceeds <code>k</code>, the path is invalid.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[0, 1],[2, 0]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<p>The optimal path is:</p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">Cell</th>
			<th style="border: 1px solid black;">grid[i][j]</th>
			<th style="border: 1px solid black;">Score</th>
			<th style="border: 1px solid black;">Total<br />
			Score</th>
			<th style="border: 1px solid black;">Cost</th>
			<th style="border: 1px solid black;">Total<br />
			Cost</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">(0, 0)</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">(1, 0)</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">(1, 1)</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
	</tbody>
</table>

<p>Thus, the maximum possible score is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[0, 1],[1, 2]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no path that reaches cell <code>(1, 1)</code>​​​​​​​ without exceeding cost k. Thus, the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>3</sup>​​​​​​​</code></li>
	<li><code><sup>​​​​​​​</sup>grid[0][0] == 0</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 2</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

We define a function $\textit{dfs}(i, j, k)$ that represents the maximum score achievable when starting from position $(i, j)$ and reaching the endpoint $(0, 0)$ with remaining cost not exceeding $k$. We use memoization search to avoid redundant calculations.

Specifically, the implementation steps of function $\textit{dfs}(i, j, k)$ are as follows:

1. If the current coordinate $(i, j)$ is out of bounds or the remaining cost $k$ is less than $0$, return negative infinity to indicate that the endpoint cannot be reached.
2. If the current coordinate is the starting point $(0, 0)$, return $0$, indicating that the endpoint has been reached (the problem guarantees the starting point has value $0$).
3. Calculate the score contribution $\textit{res}$ of the current cell. If the current cell's value is not $0$, decrement the remaining cost $k$ by $1$.
4. Recursively calculate the maximum scores achievable from the upper cell $(i-1, j)$ and the left cell $(i, j-1)$ when reaching the endpoint with remaining cost not exceeding $k$, denoted as $\textit{a}$ and $\textit{b}$ respectively.
5. Add the current cell's score contribution $\textit{res}$ to $\max(\textit{a}, \textit{b})$ to get the maximum score achievable from the current cell, and return this value.

Finally, we call $\textit{dfs}(m-1, n-1, k)$ to calculate the maximum score achievable when starting from the bottom-right corner and reaching the top-left corner with remaining cost not exceeding $k$. If the result is less than $0$, return $-1$ to indicate no valid path exists; otherwise, return the result.

The time complexity is $O(m \times n \times k)$, and the space complexity is $O(m \times n \times k)$, where $m$ and $n$ are the number of rows and columns in the grid, and $k$ is the maximum allowed cost.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxPathScore(self, grid: List[List[int]], k: int) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if i < 0 or j < 0 or k < 0:
                return -inf
            if i == 0 and j == 0:
                return 0
            res = grid[i][j]
            if grid[i][j]:
                k -= 1
            a = dfs(i - 1, j, k)
            b = dfs(i, j - 1, k)
            res += max(a, b)
            return res

        ans = dfs(len(grid) - 1, len(grid[0]) - 1, k)
        dfs.cache_clear()
        return -1 if ans < 0 else ans
```

#### Java

```java
class Solution {
    private int[][] grid;
    private Integer[][][] f;
    private final int inf = 1 << 30;

    public int maxPathScore(int[][] grid, int k) {
        this.grid = grid;
        int m = grid.length;
        int n = grid[0].length;
        f = new Integer[m][n][k + 1];
        int ans = dfs(m - 1, n - 1, k);
        return ans < 0 ? -1 : ans;
    }

    private int dfs(int i, int j, int k) {
        if (i < 0 || j < 0 || k < 0) {
            return -inf;
        }
        if (i == 0 && j == 0) {
            return 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int res = grid[i][j];
        int nk = k;
        if (grid[i][j] > 0) {
            --nk;
        }
        int a = dfs(i - 1, j, nk);
        int b = dfs(i, j - 1, nk);
        res += Math.max(a, b);
        f[i][j][k] = res;
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxPathScore(vector<vector<int>>& grid, int k) {
        int m = grid.size();
        int n = grid[0].size();
        int inf = 1 << 30;
        vector f(m, vector(n, vector<int>(k + 1, -1)));

        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> int {
            if (i < 0 || j < 0 || k < 0) {
                return -inf;
            }
            if (i == 0 && j == 0) {
                return 0;
            }
            if (f[i][j][k] != -1) {
                return f[i][j][k];
            }

            int res = grid[i][j];
            int nk = k;
            if (grid[i][j] > 0) {
                --nk;
            }

            int a = dfs(i - 1, j, nk);
            int b = dfs(i, j - 1, nk);
            res += max(a, b);

            return f[i][j][k] = res;
        };

        int ans = dfs(m - 1, n - 1, k);
        return ans < 0 ? -1 : ans;
    }
};
```

#### Go

```go
func maxPathScore(grid [][]int, k int) int {
	m := len(grid)
	n := len(grid[0])
	inf := 1 << 30

	f := make([][][]int, m)
	for i := 0; i < m; i++ {
		f[i] = make([][]int, n)
		for j := 0; j < n; j++ {
			f[i][j] = make([]int, k+1)
			for t := 0; t <= k; t++ {
				f[i][j][t] = -1
			}
		}
	}

	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i < 0 || j < 0 || k < 0 {
			return -inf
		}
		if i == 0 && j == 0 {
			return 0
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}

		res := grid[i][j]
		nk := k
		if grid[i][j] != 0 {
			nk--
		}

		a := dfs(i-1, j, nk)
		b := dfs(i, j-1, nk)
		res += max(a, b)

		f[i][j][k] = res
		return res
	}

	ans := dfs(m-1, n-1, k)
	if ans < 0 {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function maxPathScore(grid: number[][], k: number): number {
    const m = grid.length;
    const n = grid[0].length;
    const inf = 1 << 30;

    const f: number[][][] = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => Array(k + 1).fill(-1)),
    );

    const dfs = (i: number, j: number, k: number): number => {
        if (i < 0 || j < 0 || k < 0) {
            return -inf;
        }
        if (i === 0 && j === 0) {
            return 0;
        }
        if (f[i][j][k] !== -1) {
            return f[i][j][k];
        }

        let res = grid[i][j];
        let nk = k;
        if (grid[i][j] !== 0) {
            --nk;
        }

        const a = dfs(i - 1, j, nk);
        const b = dfs(i, j - 1, nk);
        res += Math.max(a, b);

        f[i][j][k] = res;
        return res;
    };

    const ans = dfs(m - 1, n - 1, k);
    return ans < 0 ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
