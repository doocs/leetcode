---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1289.Minimum%20Falling%20Path%20Sum%20II/README_EN.md
rating: 1697
source: Biweekly Contest 15 Q4
tags:
    - Array
    - Dynamic Programming
    - Matrix
---

<!-- problem:start -->

# [1289. Minimum Falling Path Sum II](https://leetcode.com/problems/minimum-falling-path-sum-ii)

[中文文档](/solution/1200-1299/1289.Minimum%20Falling%20Path%20Sum%20II/README.md)

## Description

<!-- description:start -->

<p>Given an <code>n x n</code> integer matrix <code>grid</code>, return <em>the minimum sum of a <strong>falling path with non-zero shifts</strong></em>.</p>

<p>A <strong>falling path with non-zero shifts</strong> is a choice of exactly one element from each row of <code>grid</code> such that no two elements chosen in adjacent rows are in the same column.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1289.Minimum%20Falling%20Path%20Sum%20II/images/falling-grid.jpg" style="width: 244px; height: 245px;" />
<pre>
<strong>Input:</strong> grid = [[1,2,3],[4,5,6],[7,8,9]]
<strong>Output:</strong> 13
<strong>Explanation:</strong> 
The possible falling paths are:
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
The falling path with the smallest sum is&nbsp;[1,5,7], so the answer is&nbsp;13.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[7]]
<strong>Output:</strong> 7
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>-99 &lt;= grid[i][j] &lt;= 99</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j]$ to represent the minimum sum of the first $i$ rows, with the last number in the $j$-th column. The state transition equation is:

$$
f[i][j] = \min_{k \neq j} f[i - 1][k] + grid[i - 1][j]
$$

where $k$ represents the column of the number in the $(i - 1)$-th row, and the number in the $i$-th row and $j$-th column is $grid[i - 1][j]$.

The final answer is the minimum value in $f[n]$.

The time complexity is $O(n^3)$, and the space complexity is $O(n^2)$. Here, $n$ is the number of rows in the matrix.

We note that the state $f[i][j]$ only depends on $f[i - 1][k]$, so we can use a rolling array to optimize the space complexity to $O(n)$.

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
