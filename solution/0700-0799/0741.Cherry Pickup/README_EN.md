# [741. Cherry Pickup](https://leetcode.com/problems/cherry-pickup)

[中文文档](/solution/0700-0799/0741.Cherry%20Pickup/README.md)

<!-- tags:Array,Dynamic Programming,Matrix -->

<!-- difficulty:Hard -->

## Description

<p>You are given an <code>n x n</code> <code>grid</code> representing a field of cherries, each cell is one of three possible integers.</p>

<ul>
	<li><code>0</code> means the cell is empty, so you can pass through,</li>
	<li><code>1</code> means the cell contains a cherry that you can pick up and pass through, or</li>
	<li><code>-1</code> means the cell contains a thorn that blocks your way.</li>
</ul>

<p>Return <em>the maximum number of cherries you can collect by following the rules below</em>:</p>

<ul>
	<li>Starting at the position <code>(0, 0)</code> and reaching <code>(n - 1, n - 1)</code> by moving right or down through valid path cells (cells with value <code>0</code> or <code>1</code>).</li>
	<li>After reaching <code>(n - 1, n - 1)</code>, returning to <code>(0, 0)</code> by moving left or up through valid path cells.</li>
	<li>When passing through a path cell containing a cherry, you pick it up, and the cell becomes an empty cell <code>0</code>.</li>
	<li>If there is no valid path between <code>(0, 0)</code> and <code>(n - 1, n - 1)</code>, then no cherries can be collected.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0741.Cherry%20Pickup/images/grid.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> grid = [[0,1,-1],[1,0,-1],[1,1,1]]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The player started at (0, 0) and went down, down, right right to reach (2, 2).
4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
Then, the player went left, up, up, left to return home, picking up one more cherry.
The total number of cherries picked up is 5, and this is the maximum possible.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1,-1],[1,-1,1],[-1,1,1]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>grid[i][j]</code> is <code>-1</code>, <code>0</code>, or <code>1</code>.</li>
	<li><code>grid[0][0] != -1</code></li>
	<li><code>grid[n - 1][n - 1] != -1</code></li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

According to the problem description, the player starts from $(0, 0)$, reaches $(n-1, n-1)$, and then returns to the starting point $(0, 0)$. We can consider the player as starting from $(0, 0)$ to $(n-1, n-1)$ twice.

Therefore, we define $f[k][i_1][i_2]$ as the maximum number of cherries that can be picked when both have walked $k$ steps and reached $(i_1, k-i_1)$ and $(i_2, k-i_2)$ respectively. Initially, $f[0][0][0] = grid[0][0]$. The initial values of other $f[k][i_1][i_2]$ are negative infinity. The answer is $\max(0, f[2n-2][n-1][n-1])$.

According to the problem description, we can get the state transition equation:

$$
f[k][i_1][i_2] = \max(f[k-1][x_1][x_2] + t, f[k][i_1][i_2])
$$

Where $t$ represents the number of cherries at positions $(i_1, k-i_1)$ and $(i_2, k-i_2)$, and $x_1, x_2$ represent the previous step positions of $(i_1, k-i_1)$ and $(i_2, k-i_2)$ respectively.

The time complexity is $O(n^3)$, and the space complexity is $O(n^3)$. Where $n$ is the side length of the grid.

<!-- tabs:start -->

```python
class Solution:
    def cherryPickup(self, grid: List[List[int]]) -> int:
        n = len(grid)
        f = [[[-inf] * n for _ in range(n)] for _ in range((n << 1) - 1)]
        f[0][0][0] = grid[0][0]
        for k in range(1, (n << 1) - 1):
            for i1 in range(n):
                for i2 in range(n):
                    j1, j2 = k - i1, k - i2
                    if (
                        not 0 <= j1 < n
                        or not 0 <= j2 < n
                        or grid[i1][j1] == -1
                        or grid[i2][j2] == -1
                    ):
                        continue
                    t = grid[i1][j1]
                    if i1 != i2:
                        t += grid[i2][j2]
                    for x1 in range(i1 - 1, i1 + 1):
                        for x2 in range(i2 - 1, i2 + 1):
                            if x1 >= 0 and x2 >= 0:
                                f[k][i1][i2] = max(f[k][i1][i2], f[k - 1][x1][x2] + t)
        return max(0, f[-1][-1][-1])
```

```java
class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] f = new int[n * 2][n][n];
        f[0][0][0] = grid[0][0];
        for (int k = 1; k < n * 2 - 1; ++k) {
            for (int i1 = 0; i1 < n; ++i1) {
                for (int i2 = 0; i2 < n; ++i2) {
                    int j1 = k - i1, j2 = k - i2;
                    f[k][i1][i2] = Integer.MIN_VALUE;
                    if (j1 < 0 || j1 >= n || j2 < 0 || j2 >= n || grid[i1][j1] == -1
                        || grid[i2][j2] == -1) {
                        continue;
                    }
                    int t = grid[i1][j1];
                    if (i1 != i2) {
                        t += grid[i2][j2];
                    }
                    for (int x1 = i1 - 1; x1 <= i1; ++x1) {
                        for (int x2 = i2 - 1; x2 <= i2; ++x2) {
                            if (x1 >= 0 && x2 >= 0) {
                                f[k][i1][i2] = Math.max(f[k][i1][i2], f[k - 1][x1][x2] + t);
                            }
                        }
                    }
                }
            }
        }
        return Math.max(0, f[n * 2 - 2][n - 1][n - 1]);
    }
}
```

```cpp
class Solution {
public:
    int cherryPickup(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<vector<vector<int>>> f(n << 1, vector<vector<int>>(n, vector<int>(n, -1e9)));
        f[0][0][0] = grid[0][0];
        for (int k = 1; k < n * 2 - 1; ++k) {
            for (int i1 = 0; i1 < n; ++i1) {
                for (int i2 = 0; i2 < n; ++i2) {
                    int j1 = k - i1, j2 = k - i2;
                    if (j1 < 0 || j1 >= n || j2 < 0 || j2 >= n || grid[i1][j1] == -1 || grid[i2][j2] == -1) {
                        continue;
                    }
                    int t = grid[i1][j1];
                    if (i1 != i2) {
                        t += grid[i2][j2];
                    }
                    for (int x1 = i1 - 1; x1 <= i1; ++x1) {
                        for (int x2 = i2 - 1; x2 <= i2; ++x2) {
                            if (x1 >= 0 && x2 >= 0) {
                                f[k][i1][i2] = max(f[k][i1][i2], f[k - 1][x1][x2] + t);
                            }
                        }
                    }
                }
            }
        }
        return max(0, f[n * 2 - 2][n - 1][n - 1]);
    }
};
```

```go
func cherryPickup(grid [][]int) int {
	n := len(grid)
	f := make([][][]int, (n<<1)-1)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, n)
		}
	}
	f[0][0][0] = grid[0][0]
	for k := 1; k < (n<<1)-1; k++ {
		for i1 := 0; i1 < n; i1++ {
			for i2 := 0; i2 < n; i2++ {
				f[k][i1][i2] = int(-1e9)
				j1, j2 := k-i1, k-i2
				if j1 < 0 || j1 >= n || j2 < 0 || j2 >= n || grid[i1][j1] == -1 || grid[i2][j2] == -1 {
					continue
				}
				t := grid[i1][j1]
				if i1 != i2 {
					t += grid[i2][j2]
				}
				for x1 := i1 - 1; x1 <= i1; x1++ {
					for x2 := i2 - 1; x2 <= i2; x2++ {
						if x1 >= 0 && x2 >= 0 {
							f[k][i1][i2] = max(f[k][i1][i2], f[k-1][x1][x2]+t)
						}
					}
				}
			}
		}
	}
	return max(0, f[n*2-2][n-1][n-1])
}
```

```ts
function cherryPickup(grid: number[][]): number {
    const n: number = grid.length;
    const f: number[][][] = Array.from({ length: n * 2 - 1 }, () =>
        Array.from({ length: n }, () => Array.from({ length: n }, () => -Infinity)),
    );
    f[0][0][0] = grid[0][0];
    for (let k = 1; k < n * 2 - 1; ++k) {
        for (let i1 = 0; i1 < n; ++i1) {
            for (let i2 = 0; i2 < n; ++i2) {
                const [j1, j2]: [number, number] = [k - i1, k - i2];
                if (
                    j1 < 0 ||
                    j1 >= n ||
                    j2 < 0 ||
                    j2 >= n ||
                    grid[i1][j1] == -1 ||
                    grid[i2][j2] == -1
                ) {
                    continue;
                }
                const t: number = grid[i1][j1] + (i1 != i2 ? grid[i2][j2] : 0);
                for (let x1 = i1 - 1; x1 <= i1; ++x1) {
                    for (let x2 = i2 - 1; x2 <= i2; ++x2) {
                        if (x1 >= 0 && x2 >= 0) {
                            f[k][i1][i2] = Math.max(f[k][i1][i2], f[k - 1][x1][x2] + t);
                        }
                    }
                }
            }
        }
    }
    return Math.max(0, f[n * 2 - 2][n - 1][n - 1]);
}
```

```js
/**
 * @param {number[][]} grid
 * @return {number}
 */
var cherryPickup = function (grid) {
    const n = grid.length;
    const f = Array.from({ length: n * 2 - 1 }, () =>
        Array.from({ length: n }, () => Array.from({ length: n }, () => -Infinity)),
    );
    f[0][0][0] = grid[0][0];
    for (let k = 1; k < n * 2 - 1; ++k) {
        for (let i1 = 0; i1 < n; ++i1) {
            for (let i2 = 0; i2 < n; ++i2) {
                const [j1, j2] = [k - i1, k - i2];
                if (
                    j1 < 0 ||
                    j1 >= n ||
                    j2 < 0 ||
                    j2 >= n ||
                    grid[i1][j1] == -1 ||
                    grid[i2][j2] == -1
                ) {
                    continue;
                }
                const t = grid[i1][j1] + (i1 != i2 ? grid[i2][j2] : 0);
                for (let x1 = i1 - 1; x1 <= i1; ++x1) {
                    for (let x2 = i2 - 1; x2 <= i2; ++x2) {
                        if (x1 >= 0 && x2 >= 0) {
                            f[k][i1][i2] = Math.max(f[k][i1][i2], f[k - 1][x1][x2] + t);
                        }
                    }
                }
            }
        }
    }
    return Math.max(0, f[n * 2 - 2][n - 1][n - 1]);
};
```

<!-- tabs:end -->

<!-- end -->
