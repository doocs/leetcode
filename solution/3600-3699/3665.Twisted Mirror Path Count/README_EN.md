---
comments: true
difficulty: Medium
rating: 1883
source: Biweekly Contest 164 Q3
tags:
    - Array
    - Dynamic Programming
    - Matrix
---

<!-- problem:start -->

# [3665. Twisted Mirror Path Count](https://leetcode.com/problems/twisted-mirror-path-count)

[中文文档](/solution/3600-3699/3665.Twisted%20Mirror%20Path%20Count/README.md)

## Description

<!-- description:start -->

<p>Given an <code>m x n</code> binary grid <code>grid</code> where:</p>

<ul>
	<li><code>grid[i][j] == 0</code> represents an empty cell, and</li>
	<li><code>grid[i][j] == 1</code> represents a mirror.</li>
</ul>

<p>A robot starts at the top-left corner of the grid <code>(0, 0)</code> and wants to reach the bottom-right corner <code>(m - 1, n - 1)</code>. It can move only <strong>right</strong> or <strong>down</strong>. If the robot attempts to move into a mirror cell, it is <strong>reflected</strong> before entering that cell:</p>

<ul>
	<li>If it tries to move <strong>right</strong> into a mirror, it is turned <strong>down</strong> and moved into the cell directly below the mirror.</li>
	<li>If it tries to move <strong>down</strong> into a mirror, it is turned <strong>right</strong> and moved into the cell directly to the right of the mirror.</li>
</ul>

<p>If this reflection would cause the robot to move outside the <code>grid</code> boundaries, the path is considered invalid and should not be counted.</p>

<p>Return the number of unique valid paths from <code>(0, 0)</code> to <code>(m - 1, n - 1)</code>.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p><strong>Note</strong>: If a reflection moves the robot into a mirror cell, the robot is immediately reflected again based on the direction it used to enter that mirror: if it entered while moving right, it will be turned down; if it entered while moving down, it will be turned right. This process will continue until either the last cell is reached, the robot moves out of bounds or the robot moves to a non-mirror cell.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[0,1,0],[0,0,1],[1,0,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th align="center" style="border: 1px solid black;">Number</th>
			<th align="left" style="border: 1px solid black;">Full Path</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="left" style="border: 1px solid black;">(0, 0) &rarr; (0, 1) [M] &rarr; (1, 1) &rarr; (1, 2) [M] &rarr; (2, 2)</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="left" style="border: 1px solid black;">(0, 0) &rarr; (0, 1) [M] &rarr; (1, 1) &rarr; (2, 1) &rarr; (2, 2)</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">3</td>
			<td align="left" style="border: 1px solid black;">(0, 0) &rarr; (1, 0) &rarr; (1, 1) &rarr; (1, 2) [M] &rarr; (2, 2)</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">4</td>
			<td align="left" style="border: 1px solid black;">(0, 0) &rarr; (1, 0) &rarr; (1, 1) &rarr; (2, 1) &rarr; (2, 2)</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">5</td>
			<td align="left" style="border: 1px solid black;">(0, 0) &rarr; (1, 0) &rarr; (2, 0) [M] &rarr; (2, 1) &rarr; (2, 2)</td>
		</tr>
	</tbody>
</table>

<ul data-end="606" data-start="521">
	<li data-end="606" data-start="521">
	<p data-end="606" data-start="523"><code>[M]</code> indicates the robot attempted to enter a mirror cell and instead reflected.</p>
	</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[0,0],[0,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th align="center" style="border: 1px solid black;">Number</th>
			<th align="left" style="border: 1px solid black;">Full Path</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="left" style="border: 1px solid black;">(0, 0) &rarr; (0, 1) &rarr; (1, 1)</td>
		</tr>
		<tr>
			<td align="center" style="border: 1px solid black;">2</td>
			<td align="left" style="border: 1px solid black;">(0, 0) &rarr; (1, 0) &rarr; (1, 1)</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = </span>[[0,1,1],[1,1,0]]</p>

<p><strong>Output:</strong> 1</p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th align="center" style="border: 1px solid black;">Number</th>
			<th align="left" style="border: 1px solid black;">Full Path</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center" style="border: 1px solid black;">1</td>
			<td align="left" style="border: 1px solid black;">(0, 0) &rarr; (0, 1) [M] &rarr; (1, 1) [M] &rarr; (1, 2)</td>
		</tr>
	</tbody>
</table>
<code>(0, 0) &rarr; (1, 0) [M] &rarr; (1, 1) [M] &rarr; (2, 1)</code> goes out of bounds, so it is invalid.</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li data-end="41" data-start="21"><code data-end="39" data-start="21">m == grid.length</code></li>
	<li data-end="67" data-start="44"><code data-end="65" data-start="44">n == grid[i].length</code></li>
	<li data-end="91" data-start="70"><code data-end="89" data-start="70">2 &lt;= m, n &lt;= 500</code></li>
	<li data-end="129" data-start="94"><code data-end="106" data-start="94">grid[i][j]</code> is either <code data-end="120" data-is-only-node="" data-start="117">0</code> or <code data-end="127" data-start="124">1</code>.</li>
	<li data-end="169" data-start="132"><code data-end="167" data-start="132">grid[0][0] == grid[m - 1][n - 1] == 0</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> Without mirrors, paths from the top-left to the bottom-right that only step right or down can be counted cell by cell. Sides reach $500$, so listing every path grows exponentially and does not fit.
>
> A mirror bends the direction. Entering to the right turns the robot downward, entering downward turns it right, a chain of mirrors bends again at once, and a step that leaves the board is not counted. An arrival count alone no longer says which way the next step must go.
>
> The robot never moves left or up, so the cells have no cycles. Each cell only needs the number of ways that leave it to the right and the number that leave it downward. On an empty cell both equal the sum of the left and upper inflows; on a mirror the two inflows are swapped.
>
> Fill an $m \times n$ table from left to right, row by row. The rightward component of the left neighbor and the downward component of the cell above are already known. The origin has no predecessor, so it is seeded with $1$ way. The destination is an empty cell, and either component there is the answer.

<!-- thinking:end -->

We define $f[i][j][0]$ as the number of ways to leave cell $(i, j)$ to the right, and $f[i][j][1]$ as the number of ways to leave downward. Every value is taken modulo $10^9+7$.

Let $r$ be the number of ways to enter $(i, j)$ from the left, and $d$ the number of ways to enter from above. If $j > 0$, then $r = f[i][j - 1][0]$; otherwise $r = 0$. If $i > 0$, then $d = f[i - 1][j][1]$. The origin has no predecessor, so set $d = 1$ there. Every other cell in the first row has no cell above, so $d = 0$.

On an empty cell, every path that arrives may continue either right or down, so both outgoing counts equal $r + d$:

$$
f[i][j][0] = f[i][j][1] = (r + d) \bmod (10^9+7).
$$

On a mirror, a path that enters from above is reflected to the right, and a path that enters from the left is reflected downward. The path does not stop on the mirror:

$$
f[i][j][0] = d, \quad f[i][j][1] = r.
$$

A reflection that crosses the border has no successor and is dropped. The problem guarantees $\textit{grid}[0][0] = \textit{grid}[m - 1][n - 1] = 0$, so both components at the destination equal the number of ways to arrive. Return $f[m - 1][n - 1][0]$.

Compute cells in increasing row order, and from left to right inside a row. Each cell depends only on the left neighbor and the cell above, both of which are already filled.

The time complexity is $O(mn)$ and the space complexity is $O(mn)$, where $m$ and $n$ are the number of rows and columns.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def uniquePaths(self, grid: List[List[int]]) -> int:
        mod = 10**9 + 7
        m, n = len(grid), len(grid[0])
        f = [[[0, 0] for _ in range(n)] for _ in range(m)]
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                r = f[i][j - 1][0] if j else 0
                d = f[i - 1][j][1] if i else 0
                if i == 0 and j == 0:
                    d = 1
                if x == 1:
                    f[i][j] = [d, r]
                else:
                    s = (r + d) % mod
                    f[i][j] = [s, s]
        return f[-1][-1][0]
```

#### Java

```java
class Solution {
    public int uniquePaths(int[][] grid) {
        final int mod = (int) 1e9 + 7;
        int m = grid.length, n = grid[0].length;
        int[][][] f = new int[m][n][2];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int r = j > 0 ? f[i][j - 1][0] : 0;
                int d = i > 0 ? f[i - 1][j][1] : 0;
                if (i == 0 && j == 0) {
                    d = 1;
                }
                if (grid[i][j] == 1) {
                    f[i][j][0] = d;
                    f[i][j][1] = r;
                } else {
                    int s = (r + d) % mod;
                    f[i][j][0] = f[i][j][1] = s;
                }
            }
        }
        return f[m - 1][n - 1][0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int uniquePaths(vector<vector<int>>& grid) {
        const int mod = 1e9 + 7;
        int m = grid.size(), n = grid[0].size();
        vector<vector<array<int, 2>>> f(m, vector<array<int, 2>>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int r = j > 0 ? f[i][j - 1][0] : 0;
                int d = i > 0 ? f[i - 1][j][1] : 0;
                if (i == 0 && j == 0) {
                    d = 1;
                }
                if (grid[i][j] == 1) {
                    f[i][j] = {d, r};
                } else {
                    int s = (r + d) % mod;
                    f[i][j] = {s, s};
                }
            }
        }
        return f[m - 1][n - 1][0];
    }
};
```

#### Go

```go
func uniquePaths(grid [][]int) int {
	const mod = int(1e9 + 7)
	m, n := len(grid), len(grid[0])
	f := make([][][2]int, m)
	for i := range f {
		f[i] = make([][2]int, n)
	}
	for i, row := range grid {
		for j, x := range row {
			r, d := 0, 0
			if j > 0 {
				r = f[i][j-1][0]
			}
			if i > 0 {
				d = f[i-1][j][1]
			}
			if i == 0 && j == 0 {
				d = 1
			}
			if x == 1 {
				f[i][j] = [2]int{d, r}
			} else {
				s := (r + d) % mod
				f[i][j] = [2]int{s, s}
			}
		}
	}
	return f[m-1][n-1][0]
}
```

#### TypeScript

```ts
function uniquePaths(grid: number[][]): number {
    const mod = 1e9 + 7;
    const m = grid.length;
    const n = grid[0].length;
    const f: number[][][] = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => [0, 0]),
    );
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            let r = j > 0 ? f[i][j - 1][0] : 0;
            let d = i > 0 ? f[i - 1][j][1] : 0;
            if (i === 0 && j === 0) {
                d = 1;
            }
            if (grid[i][j] === 1) {
                f[i][j] = [d, r];
            } else {
                const s = (r + d) % mod;
                f[i][j] = [s, s];
            }
        }
    }
    return f[m - 1][n - 1][0];
}
```

#### Rust

```rust
impl Solution {
    pub fn unique_paths(grid: Vec<Vec<i32>>) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let m = grid.len();
        let n = grid[0].len();
        let mut f = vec![vec![(0i32, 0i32); n]; m];
        for i in 0..m {
            for j in 0..n {
                let r = if j > 0 { f[i][j - 1].0 } else { 0 };
                let mut d = if i > 0 { f[i - 1][j].1 } else { 0 };
                if i == 0 && j == 0 {
                    d = 1;
                }
                f[i][j] = if grid[i][j] == 1 {
                    (d, r)
                } else {
                    let s = (r + d) % MOD;
                    (s, s)
                };
            }
        }
        f[m - 1][n - 1].0
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming (Space Optimization)

<!-- thinking:start -->

> **Thinking**
>
> In Solution 1, $f[i][j]$ reads only the rightward flow of the cell to its left on the same row, and the downward flow of the cell above in the same column. Once a row is finished, earlier rows are never read again.
>
> A rolling array of length $n + 1$ keeps one pair per column. While column $j$ is updated, the left neighbor already holds this row's rightward flow and this column still holds the previous row's downward flow. Overwriting in place brings the space from $O(mn)$ down to $O(n)$.

<!-- thinking:end -->

In Solution 1, cell $f[i][j]$ depends only on the rightward flow of the left neighbor and the downward flow of the cell above, so finished rows can be discarded.

Let $f$ be an array of length $n + 1$ that stores the latest pair for each column. $f[j + 1][0]$ and $f[j + 1][1]$ are the rightward and downward ways leaving column $j$. The extra slot $f[0]$ is the left border, and both of its components stay $0$.

Scanning a row from left to right, read $r = f[j][0]$ and $d = f[j + 1][1]$ before updating column $j$, then write the same transition as in Solution 1 back into $f[j + 1]$. At that moment $r$ is already the new value from the left cell of this row, while $d$ is still the previous row's downward flow.

Initialize $f[1] = (1, 1)$, so the origin reads an inflow of $1$ from above. Return $f[n][0]$.

The time complexity is $O(mn)$ and the space complexity is $O(n)$, where $m$ and $n$ are the number of rows and columns.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def uniquePaths(self, grid: List[List[int]]) -> int:
        mod = 10**9 + 7
        n = len(grid[0])
        f = [[0, 0] for _ in range(n + 1)]
        f[1] = [1, 1]
        for row in grid:
            for j, x in enumerate(row):
                r, d = f[j][0], f[j + 1][1]
                if x == 1:
                    f[j + 1] = [d, r]
                else:
                    s = (r + d) % mod
                    f[j + 1] = [s, s]
        return f[n][0]
```

#### Java

```java
class Solution {
    public int uniquePaths(int[][] grid) {
        final int mod = (int) 1e9 + 7;
        int n = grid[0].length;
        int[][] f = new int[n + 1][2];
        f[1][0] = f[1][1] = 1;
        for (int[] row : grid) {
            for (int j = 0; j < n; ++j) {
                int r = f[j][0], d = f[j + 1][1];
                if (row[j] == 1) {
                    f[j + 1][0] = d;
                    f[j + 1][1] = r;
                } else {
                    int s = (r + d) % mod;
                    f[j + 1][0] = f[j + 1][1] = s;
                }
            }
        }
        return f[n][0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int uniquePaths(vector<vector<int>>& grid) {
        const int mod = 1e9 + 7;
        int n = grid[0].size();
        vector<array<int, 2>> f(n + 1);
        f[1] = {1, 1};
        for (auto& row : grid) {
            for (int j = 0; j < n; ++j) {
                int r = f[j][0], d = f[j + 1][1];
                if (row[j] == 1) {
                    f[j + 1] = {d, r};
                } else {
                    int s = (r + d) % mod;
                    f[j + 1] = {s, s};
                }
            }
        }
        return f[n][0];
    }
};
```

#### Go

```go
func uniquePaths(grid [][]int) int {
	const mod = int(1e9 + 7)
	n := len(grid[0])
	f := make([][2]int, n+1)
	f[1] = [2]int{1, 1}
	for _, row := range grid {
		for j, x := range row {
			r, d := f[j][0], f[j+1][1]
			if x == 1 {
				f[j+1] = [2]int{d, r}
			} else {
				s := (r + d) % mod
				f[j+1] = [2]int{s, s}
			}
		}
	}
	return f[n][0]
}
```

#### TypeScript

```ts
function uniquePaths(grid: number[][]): number {
    const mod = 1e9 + 7;
    const n = grid[0].length;
    const f: number[][] = Array.from({ length: n + 1 }, () => [0, 0]);
    f[1] = [1, 1];
    for (const row of grid) {
        for (let j = 0; j < n; ++j) {
            const r = f[j][0];
            const d = f[j + 1][1];
            if (row[j] === 1) {
                f[j + 1] = [d, r];
            } else {
                const s = (r + d) % mod;
                f[j + 1] = [s, s];
            }
        }
    }
    return f[n][0];
}
```

#### Rust

```rust
impl Solution {
    pub fn unique_paths(grid: Vec<Vec<i32>>) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let n = grid[0].len();
        let mut f = vec![(0i32, 0i32); n + 1];
        f[1] = (1, 1);
        for row in &grid {
            for (j, &x) in row.iter().enumerate() {
                let (r, d) = (f[j].0, f[j + 1].1);
                f[j + 1] = if x == 1 {
                    (d, r)
                } else {
                    let s = (r + d) % MOD;
                    (s, s)
                };
            }
        }
        f[n].0
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
