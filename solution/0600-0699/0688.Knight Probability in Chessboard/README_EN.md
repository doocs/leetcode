# [688. Knight Probability in Chessboard](https://leetcode.com/problems/knight-probability-in-chessboard)

[中文文档](/solution/0600-0699/0688.Knight%20Probability%20in%20Chessboard/README.md)

<!-- tags:Dynamic Programming -->

<!-- difficulty:Medium -->

## Description

<p>On an <code>n x n</code> chessboard, a knight starts at the cell <code>(row, column)</code> and attempts to make exactly <code>k</code> moves. The rows and columns are <strong>0-indexed</strong>, so the top-left cell is <code>(0, 0)</code>, and the bottom-right cell is <code>(n - 1, n - 1)</code>.</p>

<p>A chess knight has eight possible moves it can make, as illustrated below. Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.</p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0688.Knight%20Probability%20in%20Chessboard/images/knight.png" style="width: 300px; height: 300px;" />
<p>Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.</p>

<p>The knight continues moving until it has made exactly <code>k</code> moves or has moved off the chessboard.</p>

<p>Return <em>the probability that the knight remains on the board after it has stopped moving</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 2, row = 0, column = 0
<strong>Output:</strong> 0.06250
<strong>Explanation:</strong> There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1, k = 0, row = 0, column = 0
<strong>Output:</strong> 1.00000
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 25</code></li>
	<li><code>0 &lt;= k &lt;= 100</code></li>
	<li><code>0 &lt;= row, column &lt;= n - 1</code></li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

Let $f[h][i][j]$ denotes the probability that the knight is still on the chessboard after $h$ steps starting from the position $(i, j)$. Then the final answer is $f[k][row][column]$.

When $h = 0$, the knight is always on the chessboard, so $f[0][i][j] = 1$.

When $h \gt 0$, the probability that the knight is on the position $(i, j)$ can be transferred from the probability on its $8$ adjacent positions, which are:

$$
f[h][i][j] = \sum_{a, b} f[h - 1][a][b] \times \frac{1}{8}
$$

where $(a, b)$ is one of the $8$ adjacent positions.

The final answer is $f[k][row][column]$.

The time complexity is $O(k \times n^2)$, and the space complexity is $O(k \times n^2)$. Here $k$ and $n$ are the given steps and the chessboard size, respectively.

<!-- tabs:start -->

```python
class Solution:
    def knightProbability(self, n: int, k: int, row: int, column: int) -> float:
        f = [[[0] * n for _ in range(n)] for _ in range(k + 1)]
        for i in range(n):
            for j in range(n):
                f[0][i][j] = 1
        for h in range(1, k + 1):
            for i in range(n):
                for j in range(n):
                    for a, b in pairwise((-2, -1, 2, 1, -2, 1, 2, -1, -2)):
                        x, y = i + a, j + b
                        if 0 <= x < n and 0 <= y < n:
                            f[h][i][j] += f[h - 1][x][y] / 8
        return f[k][row][column]
```

```java
class Solution {
    public double knightProbability(int n, int k, int row, int column) {
        double[][][] f = new double[k + 1][n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                f[0][i][j] = 1;
            }
        }
        int[] dirs = {-2, -1, 2, 1, -2, 1, 2, -1, -2};
        for (int h = 1; h <= k; ++h) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    for (int p = 0; p < 8; ++p) {
                        int x = i + dirs[p], y = j + dirs[p + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            f[h][i][j] += f[h - 1][x][y] / 8;
                        }
                    }
                }
            }
        }
        return f[k][row][column];
    }
}
```

```cpp
class Solution {
public:
    double knightProbability(int n, int k, int row, int column) {
        double f[k + 1][n][n];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                f[0][i][j] = 1;
            }
        }
        int dirs[9] = {-2, -1, 2, 1, -2, 1, 2, -1, -2};
        for (int h = 1; h <= k; ++h) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    for (int p = 0; p < 8; ++p) {
                        int x = i + dirs[p], y = j + dirs[p + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            f[h][i][j] += f[h - 1][x][y] / 8;
                        }
                    }
                }
            }
        }
        return f[k][row][column];
    }
};
```

```go
func knightProbability(n int, k int, row int, column int) float64 {
	f := make([][][]float64, k+1)
	for h := range f {
		f[h] = make([][]float64, n)
		for i := range f[h] {
			f[h][i] = make([]float64, n)
			for j := range f[h][i] {
				f[0][i][j] = 1
			}
		}
	}
	dirs := [9]int{-2, -1, 2, 1, -2, 1, 2, -1, -2}
	for h := 1; h <= k; h++ {
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				for p := 0; p < 8; p++ {
					x, y := i+dirs[p], j+dirs[p+1]
					if x >= 0 && x < n && y >= 0 && y < n {
						f[h][i][j] += f[h-1][x][y] / 8
					}
				}
			}
		}
	}
	return f[k][row][column]
}
```

```ts
function knightProbability(n: number, k: number, row: number, column: number): number {
    const f = new Array(k + 1)
        .fill(0)
        .map(() => new Array(n).fill(0).map(() => new Array(n).fill(0)));
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            f[0][i][j] = 1;
        }
    }
    const dirs = [-2, -1, 2, 1, -2, 1, 2, -1, -2];
    for (let h = 1; h <= k; ++h) {
        for (let i = 0; i < n; ++i) {
            for (let j = 0; j < n; ++j) {
                for (let p = 0; p < 8; ++p) {
                    const x = i + dirs[p];
                    const y = j + dirs[p + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n) {
                        f[h][i][j] += f[h - 1][x][y] / 8;
                    }
                }
            }
        }
    }
    return f[k][row][column];
}
```

```rust
const DIR: [(i32, i32); 8] = [
    (-2, -1),
    (2, -1),
    (-1, -2),
    (1, -2),
    (2, 1),
    (-2, 1),
    (1, 2),
    (-1, 2),
];
const P: f64 = 1.0 / 8.0;

impl Solution {
    #[allow(dead_code)]
    pub fn knight_probability(n: i32, k: i32, row: i32, column: i32) -> f64 {
        // Here dp[i][j][k] represents through `i` steps, the probability that the knight stays on the board
        // Starts from row: `j`, column: `k`
        let mut dp: Vec<Vec<Vec<f64>>> =
            vec![vec![vec![0 as f64; n as usize]; n as usize]; k as usize + 1];

        // Initialize the dp vector, since dp[0][j][k] should be 1
        for j in 0..n as usize {
            for k in 0..n as usize {
                dp[0][j][k] = 1.0;
            }
        }

        // Begin the actual dp process
        for i in 1..=k {
            for j in 0..n {
                for k in 0..n {
                    for (dx, dy) in DIR {
                        let x = j + dx;
                        let y = k + dy;
                        if Self::check_bounds(x, y, n, n) {
                            dp[i as usize][j as usize][k as usize] +=
                                P * dp[(i as usize) - 1][x as usize][y as usize];
                        }
                    }
                }
            }
        }

        dp[k as usize][row as usize][column as usize]
    }

    #[allow(dead_code)]
    fn check_bounds(i: i32, j: i32, n: i32, m: i32) -> bool {
        i >= 0 && i < n && j >= 0 && j < m
    }
}
```

<!-- tabs:end -->

<!-- end -->
