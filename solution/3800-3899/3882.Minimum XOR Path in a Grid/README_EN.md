---
comments: true
difficulty: Medium
rating: 1770
source: Biweekly Contest 179 Q3
tags:
    - Bit Manipulation
    - Array
    - Dynamic Programming
    - Matrix
---

<!-- problem:start -->

# [3882. Minimum XOR Path in a Grid](https://leetcode.com/problems/minimum-xor-path-in-a-grid)

[中文文档](/solution/3800-3899/3882.Minimum%20XOR%20Path%20in%20a%20Grid/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>grid</code> of size <code>m * n</code>.</p>

<p>You start at the <strong>top-left</strong> cell <code>(0, 0)</code> and want to reach the <strong>bottom-right</strong> cell <code>(m - 1, n - 1)</code>.</p>

<p>At each step, you <strong>may</strong> move either <strong>right or down</strong>.</p>

<p>The <strong>cost</strong> of a path is defined as the <strong>bitwise XOR</strong> of all the values in the cells along that path, <strong>including</strong> the start and end cells.</p>

<p>Return the <strong>minimum</strong> possible XOR value among all valid paths from <code>(0, 0)</code> to <code>(m - 1, n - 1)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,2],[3,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>There are two valid paths:</p>

<ul>
	<li><code>(0, 0) &rarr; (0, 1) &rarr; (1, 1)</code> with XOR: <code>1 XOR 2 XOR 4 = 7</code></li>
	<li><code>(0, 0) &rarr; (1, 0) &rarr; (1, 1)</code> with XOR: <code>1 XOR 3 XOR 4 = 6</code></li>
</ul>

<p>The minimum XOR value among all valid paths is 6.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[6,7],[5,8]]</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<p>There are two valid paths:</p>

<ul>
	<li><code>(0, 0) &rarr; (0, 1) &rarr; (1, 1)</code> with XOR: <code>6 XOR 7 XOR 8 = 9</code></li>
	<li><code>(0, 0) &rarr; (1, 0) &rarr; (1, 1)</code> with XOR: <code>6 XOR 5 XOR 8 = 11</code></li>
</ul>

<p>The minimum XOR value among all valid paths is 9.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[2,7,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There is only one valid path:</p>

<ul>
	<li><code>(0, 0) &rarr; (0, 1) &rarr; (0, 2)</code> with XOR: <code>2 XOR 7 XOR 5 = 0</code></li>
</ul>

<p>The XOR value of this path is 0, which is the minimum possible.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == grid.length &lt;= 1000</code></li>
	<li><code>1 &lt;= n == grid[i].length &lt;= 1000</code></li>
	<li><code>m * n &lt;= 1000</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 1023​</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> Moves are only right or down, and the goal is the minimum XOR of a whole path. There are at most $1000$ cells and values are below $2^{10}$, but the number of paths still grows exponentially with the row and column counts, so enumerating paths does not fit.
>
> XOR has no optimal substructure under numeric comparison. A smaller prefix XOR can become larger after later cells are appended, so every XOR that has already occurred must be kept.
>
> After a cell is reached, later choices depend only on the current XOR. A state $(i,j,k)$ records whether cell $(i,j)$ is reachable with path XOR $k$. Since $k < 1024$, there are about $10^6$ states.
>
> Transitions go only right or down, so a row-major scan finds the upper and left cells already finished. A boolean array $f[i][j][k]$ stores reachability; a cell of value $x$ is entered from XOR $k \oplus x$. The smallest $k$ that is true at the lower-right cell is the answer.

<!-- thinking:end -->

Define $f[i][j][k]$ as whether cell $(i, j)$ is reachable from $(0, 0)$ with the bitwise XOR of every cell on the path equal to $k$.

Let $M$ be the maximum value in the grid. Every path XOR lies in $[0, \textit{mx})$, where $\textit{mx} = 2^{b}$ and $b$ is the bit length of $M$. When $M = 0$, $b = 0$ and $\textit{mx} = 1$. Cell values are at most $1023$, so $\textit{mx} \le 1024$.

The path at the start contains only that cell, so $f[0][0][\textit{grid}[0][0]] = \text{true}$.

Cells are processed in row-major order. For cell $(i, j)$ with value $x$, if it is not the start, every $k \in [0, \textit{mx})$ is considered. Because $(k \oplus x) \oplus x = k$, the XOR before entering the cell is $k \oplus x$:

- If $i > 0$ and $f[i - 1][j][k \oplus x]$ is true, the cell can be entered from above, and $f[i][j][k] = \text{true}$.
- If $j > 0$ and $f[i][j - 1][k \oplus x]$ is true, the cell can be entered from the left, and $f[i][j][k] = \text{true}$.

Moves are only right or down, so the upper and left cells are already finished when $(i, j)$ is scanned. The answer is the smallest $k$ for which $f[m - 1][n - 1][k]$ is true. At least one path from the upper-left cell to the lower-right cell exists, so this $k$ is guaranteed.

The time complexity is $O(mn \cdot \textit{mx})$ and the space complexity is $O(mn \cdot \textit{mx})$, where $m$ and $n$ are the number of rows and columns, and $\textit{mx} \le 1024$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, grid: list[list[int]]) -> int:
        m, n = len(grid), len(grid[0])
        mx = 1 << (max(max(row) for row in grid)).bit_length()
        f = [[[False] * mx for _ in range(n)] for _ in range(m)]
        f[0][0][grid[0][0]] = True
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                if i > 0 or j > 0:
                    for k in range(mx):
                        if i > 0 and f[i - 1][j][k ^ x]:
                            f[i][j][k] = True
                        if j > 0 and f[i][j - 1][k ^ x]:
                            f[i][j][k] = True
        return next(k for k in range(mx) if f[m - 1][n - 1][k])
```

#### Java

```java
class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int mx = 0;
        for (int[] row : grid) {
            for (int x : row) {
                mx = Math.max(mx, x);
            }
        }
        mx = 1 << (Integer.SIZE - Integer.numberOfLeadingZeros(mx));
        boolean[][][] f = new boolean[m][n][mx];
        f[0][0][grid[0][0]] = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = grid[i][j];
                if (i > 0 || j > 0) {
                    for (int k = 0; k < mx; k++) {
                        if (i > 0 && f[i - 1][j][k ^ x]) {
                            f[i][j][k] = true;
                        }
                        if (j > 0 && f[i][j - 1][k ^ x]) {
                            f[i][j][k] = true;
                        }
                    }
                }
            }
        }
        for (int k = 0;; k++) {
            if (f[m - 1][n - 1][k]) {
                return k;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCost(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int mx = 0;
        for (auto& row : grid) {
            for (int x : row) {
                mx = max(mx, x);
            }
        }
        mx = 1 << bit_width(static_cast<unsigned>(mx));
        vector<vector<vector<char>>> f(m, vector<vector<char>>(n, vector<char>(mx, 0)));
        f[0][0][grid[0][0]] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = grid[i][j];
                if (i > 0 || j > 0) {
                    for (int k = 0; k < mx; k++) {
                        if (i > 0 && f[i - 1][j][k ^ x]) {
                            f[i][j][k] = 1;
                        }
                        if (j > 0 && f[i][j - 1][k ^ x]) {
                            f[i][j][k] = 1;
                        }
                    }
                }
            }
        }
        for (int k = 0;; k++) {
            if (f[m - 1][n - 1][k]) {
                return k;
            }
        }
    }
};
```

#### Go

```go
func minCost(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	mx := 0
	for _, row := range grid {
		for _, x := range row {
			if x > mx {
				mx = x
			}
		}
	}
	mx = 1 << bits.Len(uint(mx))
	f := make([][][]bool, m)
	for i := range f {
		f[i] = make([][]bool, n)
		for j := range f[i] {
			f[i][j] = make([]bool, mx)
		}
	}
	f[0][0][grid[0][0]] = true
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			x := grid[i][j]
			if i > 0 || j > 0 {
				for k := 0; k < mx; k++ {
					if i > 0 && f[i-1][j][k^x] {
						f[i][j][k] = true
					}
					if j > 0 && f[i][j-1][k^x] {
						f[i][j][k] = true
					}
				}
			}
		}
	}
	for k := 0; ; k++ {
		if f[m-1][n-1][k] {
			return k
		}
	}
}
```

#### TypeScript

```ts
function minCost(grid: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    let mx = 0;
    for (const row of grid) {
        for (const x of row) {
            mx = Math.max(mx, x);
        }
    }
    mx = 1 << (32 - Math.clz32(mx));
    const f: boolean[][][] = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => new Array<boolean>(mx).fill(false)),
    );
    f[0][0][grid[0][0]] = true;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const x = grid[i][j];
            if (i > 0 || j > 0) {
                for (let k = 0; k < mx; k++) {
                    if (i > 0 && f[i - 1][j][k ^ x]) {
                        f[i][j][k] = true;
                    }
                    if (j > 0 && f[i][j - 1][k ^ x]) {
                        f[i][j][k] = true;
                    }
                }
            }
        }
    }
    for (let k = 0; ; k++) {
        if (f[m - 1][n - 1][k]) {
            return k;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming (Rolling Array)

<!-- thinking:start -->

> **Thinking**
>
> Solution 1 keeps every reachable XOR of every cell, so the space grows with $mn$. Cell $(i, j)$ reads only the cell above and the cell to the left, and a row-major scan finishes both first. The previous row is still complete when the current row begins, and the current row only needs the left neighbor already written, so two rolling rows are enough. The transition still looks back at $k \oplus x$ for a cell of value $x$, the answer is still the smallest reachable XOR at the lower-right cell, and the space drops to $O(n \cdot \textit{mx})$.

<!-- thinking:end -->

Define $f[i \bmod 2][j][k]$ as whether row $i$ and column $j$ are reachable from $(0, 0)$ with the bitwise XOR of every cell on the path equal to $k$. The value of $\textit{mx}$ matches Solution 1, and the start cell again has $f[0][0][\textit{grid}[0][0]] = \text{true}$.

Cells are processed in row-major order. For cell $(i, j)$ with value $x$, if it is not the start, every $k \in [0, \textit{mx})$ is considered. The row index is taken modulo $2$, so the current slot may still hold an older row. Each $k$ is therefore cleared first, then set with the same conditions as Solution 1:

- If $i > 0$ and $f[(i - 1) \bmod 2][j][k \oplus x]$ is true, the cell can be entered from above, and $f[i \bmod 2][j][k] = \text{true}$.
- If $j > 0$ and $f[i \bmod 2][j - 1][k \oplus x]$ is true, the cell can be entered from the left, and $f[i \bmod 2][j][k] = \text{true}$.

Row $i$ is stored at index $i \bmod 2$, the previous row stays at $(i - 1) \bmod 2$, and the left cell shares that row. The answer is the smallest $k$ for which $f[(m - 1) \bmod 2][n - 1][k]$ is true. At least one path from the upper-left cell to the lower-right cell exists, so this $k$ is guaranteed.

The time complexity is $O(mn \cdot \textit{mx})$ and the space complexity is $O(n \cdot \textit{mx})$, where $m$ and $n$ are the number of rows and columns, and $\textit{mx} \le 1024$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, grid: list[list[int]]) -> int:
        m, n = len(grid), len(grid[0])
        mx = 1 << (max(max(row) for row in grid)).bit_length()
        f = [[[False] * mx for _ in range(n)] for _ in range(2)]
        f[0][0][grid[0][0]] = True
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                if i > 0 or j > 0:
                    for k in range(mx):
                        f[i & 1][j][k] = False
                        if i > 0 and f[(i - 1) & 1][j][k ^ x]:
                            f[i & 1][j][k] = True
                        if j > 0 and f[i & 1][j - 1][k ^ x]:
                            f[i & 1][j][k] = True
        return next(k for k in range(mx) if f[(m - 1) & 1][n - 1][k])
```

#### Java

```java
class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int mx = 0;
        for (int[] row : grid) {
            for (int x : row) {
                mx = Math.max(mx, x);
            }
        }
        mx = 1 << (Integer.SIZE - Integer.numberOfLeadingZeros(mx));
        boolean[][][] f = new boolean[2][n][mx];
        f[0][0][grid[0][0]] = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = grid[i][j];
                if (i > 0 || j > 0) {
                    for (int k = 0; k < mx; k++) {
                        f[i & 1][j][k] = false;
                        if (i > 0 && f[(i - 1) & 1][j][k ^ x]) {
                            f[i & 1][j][k] = true;
                        }
                        if (j > 0 && f[i & 1][j - 1][k ^ x]) {
                            f[i & 1][j][k] = true;
                        }
                    }
                }
            }
        }
        for (int k = 0;; k++) {
            if (f[(m - 1) & 1][n - 1][k]) {
                return k;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCost(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int mx = 0;
        for (auto& row : grid) {
            for (int x : row) {
                mx = max(mx, x);
            }
        }
        mx = 1 << bit_width(static_cast<unsigned>(mx));
        vector<vector<vector<char>>> f(2, vector<vector<char>>(n, vector<char>(mx, 0)));
        f[0][0][grid[0][0]] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = grid[i][j];
                if (i > 0 || j > 0) {
                    for (int k = 0; k < mx; k++) {
                        f[i & 1][j][k] = 0;
                        if (i > 0 && f[(i - 1) & 1][j][k ^ x]) {
                            f[i & 1][j][k] = 1;
                        }
                        if (j > 0 && f[i & 1][j - 1][k ^ x]) {
                            f[i & 1][j][k] = 1;
                        }
                    }
                }
            }
        }
        for (int k = 0;; k++) {
            if (f[(m - 1) & 1][n - 1][k]) {
                return k;
            }
        }
    }
};
```

#### Go

```go
func minCost(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	mx := 0
	for _, row := range grid {
		for _, x := range row {
			if x > mx {
				mx = x
			}
		}
	}
	mx = 1 << bits.Len(uint(mx))
	f := make([][][]bool, 2)
	for i := range f {
		f[i] = make([][]bool, n)
		for j := range f[i] {
			f[i][j] = make([]bool, mx)
		}
	}
	f[0][0][grid[0][0]] = true
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			x := grid[i][j]
			if i > 0 || j > 0 {
				for k := 0; k < mx; k++ {
					f[i&1][j][k] = false
					if i > 0 && f[(i-1)&1][j][k^x] {
						f[i&1][j][k] = true
					}
					if j > 0 && f[i&1][j-1][k^x] {
						f[i&1][j][k] = true
					}
				}
			}
		}
	}
	for k := 0; ; k++ {
		if f[(m-1)&1][n-1][k] {
			return k
		}
	}
}
```

#### TypeScript

```ts
function minCost(grid: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    let mx = 0;
    for (const row of grid) {
        for (const x of row) {
            mx = Math.max(mx, x);
        }
    }
    mx = 1 << (32 - Math.clz32(mx));
    const f: boolean[][][] = Array.from({ length: 2 }, () =>
        Array.from({ length: n }, () => new Array<boolean>(mx).fill(false)),
    );
    f[0][0][grid[0][0]] = true;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const x = grid[i][j];
            if (i > 0 || j > 0) {
                for (let k = 0; k < mx; k++) {
                    f[i & 1][j][k] = false;
                    if (i > 0 && f[(i - 1) & 1][j][k ^ x]) {
                        f[i & 1][j][k] = true;
                    }
                    if (j > 0 && f[i & 1][j - 1][k ^ x]) {
                        f[i & 1][j][k] = true;
                    }
                }
            }
        }
    }
    for (let k = 0; ; k++) {
        if (f[(m - 1) & 1][n - 1][k]) {
            return k;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
