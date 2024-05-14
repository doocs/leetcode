# [1463. Cherry Pickup II](https://leetcode.com/problems/cherry-pickup-ii)

[中文文档](/solution/1400-1499/1463.Cherry%20Pickup%20II/README.md)

<!-- tags:Array,Dynamic Programming,Matrix -->

<!-- difficulty:Hard -->

## Description

<p>You are given a <code>rows x cols</code> matrix <code>grid</code> representing a field of cherries where <code>grid[i][j]</code> represents the number of cherries that you can collect from the <code>(i, j)</code> cell.</p>

<p>You have two robots that can collect cherries for you:</p>

<ul>
	<li><strong>Robot #1</strong> is located at the <strong>top-left corner</strong> <code>(0, 0)</code>, and</li>
	<li><strong>Robot #2</strong> is located at the <strong>top-right corner</strong> <code>(0, cols - 1)</code>.</li>
</ul>

<p>Return <em>the maximum number of cherries collection using both robots by following the rules below</em>:</p>

<ul>
	<li>From a cell <code>(i, j)</code>, robots can move to cell <code>(i + 1, j - 1)</code>, <code>(i + 1, j)</code>, or <code>(i + 1, j + 1)</code>.</li>
	<li>When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.</li>
	<li>When both robots stay in the same cell, only one takes the cherries.</li>
	<li>Both robots cannot move outside of the grid at any moment.</li>
	<li>Both robots should reach the bottom row in <code>grid</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1463.Cherry%20Pickup%20II/images/sample_1_1802.png" style="width: 374px; height: 501px;" />
<pre>
<strong>Input:</strong> grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
<strong>Output:</strong> 24
<strong>Explanation:</strong> Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
Total of cherries: 12 + 12 = 24.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1463.Cherry%20Pickup%20II/images/sample_2_1802.png" style="width: 500px; height: 452px;" />
<pre>
<strong>Input:</strong> grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
<strong>Output:</strong> 28
<strong>Explanation:</strong> Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
Total of cherries: 17 + 11 = 28.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>rows == grid.length</code></li>
	<li><code>cols == grid[i].length</code></li>
	<li><code>2 &lt;= rows, cols &lt;= 70</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

We define $f[i][j_1][j_2]$ as the maximum number of cherries that can be picked when the two robots are at positions $j_1$ and $j_2$ in the $i$-th row. Initially, $f[0][0][n-1] = grid[0][0] + grid[0][n-1]$, and the other values are $-1$. The answer is $\max_{0 \leq j_1, j_2 < n} f[m-1][j_1][j_2]$.

Consider $f[i][j_1][j_2]$. If $j_1 \neq j_2$, then the number of cherries that the robots can pick in the $i$-th row is $grid[i][j_1] + grid[i][j_2]$. If $j_1 = j_2$, then the number of cherries that the robots can pick in the $i$-th row is $grid[i][j_1]$. We can enumerate the previous state of the two robots $f[i-1][y1][y2]$, where $y_1, y_2$ are the positions of the two robots in the $(i-1)$-th row, then $y_1 \in \{j_1-1, j_1, j_1+1\}$ and $y_2 \in \{j_2-1, j_2, j_2+1\}$. The state transition equation is as follows:

$$
f[i][j_1][j_2] = \max_{y_1 \in \{j_1-1, j_1, j_1+1\}, y_2 \in \{j_2-1, j_2, j_2+1\}} f[i-1][y_1][y_2] + \begin{cases} grid[i][j_1] + grid[i][j_2], & j_1 \neq j_2 \\ grid[i][j_1], & j_1 = j_2 \end{cases}
$$

Where $f[i-1][y_1][y_2]$ is ignored when it is $-1$.

The final answer is $\max_{0 \leq j_1, j_2 < n} f[m-1][j_1][j_2]$.

The time complexity is $O(m \times n^2)$, and the space complexity is $O(m \times n^2)$. Where $m$ and $n$ are the number of rows and columns of the grid, respectively.

<!-- tabs:start -->

```python
class Solution:
    def cherryPickup(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        f = [[[-1] * n for _ in range(n)] for _ in range(m)]
        f[0][0][n - 1] = grid[0][0] + grid[0][n - 1]
        for i in range(1, m):
            for j1 in range(n):
                for j2 in range(n):
                    x = grid[i][j1] + (0 if j1 == j2 else grid[i][j2])
                    for y1 in range(j1 - 1, j1 + 2):
                        for y2 in range(j2 - 1, j2 + 2):
                            if 0 <= y1 < n and 0 <= y2 < n and f[i - 1][y1][y2] != -1:
                                f[i][j1][j2] = max(f[i][j1][j2], f[i - 1][y1][y2] + x)
        return max(f[-1][j1][j2] for j1, j2 in product(range(n), range(n)))
```

```java
class Solution {
    public int cherryPickup(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][] f = new int[m][n][n];
        for (var g : f) {
            for (var h : g) {
                Arrays.fill(h, -1);
            }
        }
        f[0][0][n - 1] = grid[0][0] + grid[0][n - 1];
        for (int i = 1; i < m; ++i) {
            for (int j1 = 0; j1 < n; ++j1) {
                for (int j2 = 0; j2 < n; ++j2) {
                    int x = grid[i][j1] + (j1 == j2 ? 0 : grid[i][j2]);
                    for (int y1 = j1 - 1; y1 <= j1 + 1; ++y1) {
                        for (int y2 = j2 - 1; y2 <= j2 + 1; ++y2) {
                            if (y1 >= 0 && y1 < n && y2 >= 0 && y2 < n && f[i - 1][y1][y2] != -1) {
                                f[i][j1][j2] = Math.max(f[i][j1][j2], f[i - 1][y1][y2] + x);
                            }
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int j1 = 0; j1 < n; ++j1) {
            for (int j2 = 0; j2 < n; ++j2) {
                ans = Math.max(ans, f[m - 1][j1][j2]);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int cherryPickup(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int f[m][n][n];
        memset(f, -1, sizeof(f));
        f[0][0][n - 1] = grid[0][0] + grid[0][n - 1];
        for (int i = 1; i < m; ++i) {
            for (int j1 = 0; j1 < n; ++j1) {
                for (int j2 = 0; j2 < n; ++j2) {
                    int x = grid[i][j1] + (j1 == j2 ? 0 : grid[i][j2]);
                    for (int y1 = j1 - 1; y1 <= j1 + 1; ++y1) {
                        for (int y2 = j2 - 1; y2 <= j2 + 1; ++y2) {
                            if (y1 >= 0 && y1 < n && y2 >= 0 && y2 < n && f[i - 1][y1][y2] != -1) {
                                f[i][j1][j2] = max(f[i][j1][j2], f[i - 1][y1][y2] + x);
                            }
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int j1 = 0; j1 < n; ++j1) {
            for (int j2 = 0; j2 < n; ++j2) {
                ans = max(ans, f[m - 1][j1][j2]);
            }
        }
        return ans;
    }
};
```

```go
func cherryPickup(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	f := make([][][]int, m)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, n)
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	f[0][0][n-1] = grid[0][0] + grid[0][n-1]
	for i := 1; i < m; i++ {
		for j1 := 0; j1 < n; j1++ {
			for j2 := 0; j2 < n; j2++ {
				x := grid[i][j1]
				if j1 != j2 {
					x += grid[i][j2]
				}
				for y1 := j1 - 1; y1 <= j1+1; y1++ {
					for y2 := j2 - 1; y2 <= j2+1; y2++ {
						if y1 >= 0 && y1 < n && y2 >= 0 && y2 < n && f[i-1][y1][y2] != -1 {
							f[i][j1][j2] = max(f[i][j1][j2], f[i-1][y1][y2]+x)
						}
					}
				}
			}
		}
	}
	for j1 := 0; j1 < n; j1++ {
		ans = max(ans, slices.Max(f[m-1][j1]))
	}
	return
}
```

```ts
function cherryPickup(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const f = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => Array.from({ length: n }, () => -1)),
    );
    f[0][0][n - 1] = grid[0][0] + grid[0][n - 1];
    for (let i = 1; i < m; ++i) {
        for (let j1 = 0; j1 < n; ++j1) {
            for (let j2 = 0; j2 < n; ++j2) {
                const x = grid[i][j1] + (j1 === j2 ? 0 : grid[i][j2]);
                for (let y1 = j1 - 1; y1 <= j1 + 1; ++y1) {
                    for (let y2 = j2 - 1; y2 <= j2 + 1; ++y2) {
                        if (y1 >= 0 && y1 < n && y2 >= 0 && y2 < n && f[i - 1][y1][y2] !== -1) {
                            f[i][j1][j2] = Math.max(f[i][j1][j2], f[i - 1][y1][y2] + x);
                        }
                    }
                }
            }
        }
    }
    return Math.max(...f[m - 1].flat());
}
```

<!-- tabs:end -->

### Solution 2: Dynamic Programming (Space Optimization)

Notice that the calculation of $f[i][j_1][j_2]$ is only related to $f[i-1][y_1][y_2]$. Therefore, we can use a rolling array to optimize the space complexity. After optimizing the space complexity, the time complexity is $O(n^2)$.

<!-- tabs:start -->

```python
class Solution:
    def cherryPickup(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        f = [[-1] * n for _ in range(n)]
        g = [[-1] * n for _ in range(n)]
        f[0][n - 1] = grid[0][0] + grid[0][n - 1]
        for i in range(1, m):
            for j1 in range(n):
                for j2 in range(n):
                    x = grid[i][j1] + (0 if j1 == j2 else grid[i][j2])
                    for y1 in range(j1 - 1, j1 + 2):
                        for y2 in range(j2 - 1, j2 + 2):
                            if 0 <= y1 < n and 0 <= y2 < n and f[y1][y2] != -1:
                                g[j1][j2] = max(g[j1][j2], f[y1][y2] + x)
            f, g = g, f
        return max(f[j1][j2] for j1, j2 in product(range(n), range(n)))
```

```java
class Solution {
    public int cherryPickup(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[n][n];
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], -1);
            Arrays.fill(g[i], -1);
        }
        f[0][n - 1] = grid[0][0] + grid[0][n - 1];
        for (int i = 1; i < m; ++i) {
            for (int j1 = 0; j1 < n; ++j1) {
                for (int j2 = 0; j2 < n; ++j2) {
                    int x = grid[i][j1] + (j1 == j2 ? 0 : grid[i][j2]);
                    for (int y1 = j1 - 1; y1 <= j1 + 1; ++y1) {
                        for (int y2 = j2 - 1; y2 <= j2 + 1; ++y2) {
                            if (y1 >= 0 && y1 < n && y2 >= 0 && y2 < n && f[y1][y2] != -1) {
                                g[j1][j2] = Math.max(g[j1][j2], f[y1][y2] + x);
                            }
                        }
                    }
                }
            }
            int[][] t = f;
            f = g;
            g = t;
        }
        int ans = 0;
        for (int j1 = 0; j1 < n; ++j1) {
            for (int j2 = 0; j2 < n; ++j2) {
                ans = Math.max(ans, f[j1][j2]);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int cherryPickup(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> f(n, vector<int>(n, -1));
        vector<vector<int>> g(n, vector<int>(n, -1));
        f[0][n - 1] = grid[0][0] + grid[0][n - 1];
        for (int i = 1; i < m; ++i) {
            for (int j1 = 0; j1 < n; ++j1) {
                for (int j2 = 0; j2 < n; ++j2) {
                    int x = grid[i][j1] + (j1 == j2 ? 0 : grid[i][j2]);
                    for (int y1 = j1 - 1; y1 <= j1 + 1; ++y1) {
                        for (int y2 = j2 - 1; y2 <= j2 + 1; ++y2) {
                            if (y1 >= 0 && y1 < n && y2 >= 0 && y2 < n && f[y1][y2] != -1) {
                                g[j1][j2] = max(g[j1][j2], f[y1][y2] + x);
                            }
                        }
                    }
                }
            }
            swap(f, g);
        }
        int ans = 0;
        for (int j1 = 0; j1 < n; ++j1) {
            for (int j2 = 0; j2 < n; ++j2) {
                ans = max(ans, f[j1][j2]);
            }
        }
        return ans;
    }
};
```

```go
func cherryPickup(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	f := make([][]int, n)
	g := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		g[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -1
			g[i][j] = -1
		}
	}
	f[0][n-1] = grid[0][0] + grid[0][n-1]
	for i := 1; i < m; i++ {
		for j1 := 0; j1 < n; j1++ {
			for j2 := 0; j2 < n; j2++ {
				x := grid[i][j1]
				if j1 != j2 {
					x += grid[i][j2]
				}
				for y1 := j1 - 1; y1 <= j1+1; y1++ {
					for y2 := j2 - 1; y2 <= j2+1; y2++ {
						if y1 >= 0 && y1 < n && y2 >= 0 && y2 < n && f[y1][y2] != -1 {
							g[j1][j2] = max(g[j1][j2], f[y1][y2]+x)
						}
					}
				}
			}
		}
		f, g = g, f
	}
	for j1 := 0; j1 < n; j1++ {
		ans = max(ans, slices.Max(f[j1]))
	}
	return
}
```

```ts
function cherryPickup(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let f: number[][] = Array.from({ length: n }, () => Array.from({ length: n }, () => -1));
    let g: number[][] = Array.from({ length: n }, () => Array.from({ length: n }, () => -1));
    f[0][n - 1] = grid[0][0] + grid[0][n - 1];
    for (let i = 1; i < m; ++i) {
        for (let j1 = 0; j1 < n; ++j1) {
            for (let j2 = 0; j2 < n; ++j2) {
                const x = grid[i][j1] + (j1 === j2 ? 0 : grid[i][j2]);
                for (let y1 = j1 - 1; y1 <= j1 + 1; ++y1) {
                    for (let y2 = j2 - 1; y2 <= j2 + 1; ++y2) {
                        if (y1 >= 0 && y1 < n && y2 >= 0 && y2 < n && f[y1][y2] !== -1) {
                            g[j1][j2] = Math.max(g[j1][j2], f[y1][y2] + x);
                        }
                    }
                }
            }
        }
        [f, g] = [g, f];
    }
    return Math.max(...f.flat());
}
```

<!-- tabs:end -->

<!-- end -->
