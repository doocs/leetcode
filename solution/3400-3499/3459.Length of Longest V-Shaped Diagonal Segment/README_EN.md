---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3459.Length%20of%20Longest%20V-Shaped%20Diagonal%20Segment/README_EN.md
rating: 2530
source: Weekly Contest 437 Q4
tags:
    - Memoization
    - Array
    - Dynamic Programming
    - Matrix
---

<!-- problem:start -->

# [3459. Length of Longest V-Shaped Diagonal Segment](https://leetcode.com/problems/length-of-longest-v-shaped-diagonal-segment)

[中文文档](/solution/3400-3499/3459.Length%20of%20Longest%20V-Shaped%20Diagonal%20Segment/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer matrix <code>grid</code> of size <code>n x m</code>, where each element is either <code>0</code>, <code>1</code>, or <code>2</code>.</p>

<p>A <strong>V-shaped diagonal segment</strong> is defined as:</p>

<ul>
	<li>The segment starts with <code>1</code>.</li>
	<li>The subsequent elements follow this infinite sequence: <code>2, 0, 2, 0, ...</code>.</li>
	<li>The segment:
	<ul>
		<li>Starts <strong>along</strong> a diagonal direction (top-left to bottom-right, bottom-right to top-left, top-right to bottom-left, or bottom-left to top-right).</li>
		<li>Continues the<strong> sequence</strong> in the same diagonal direction.</li>
		<li>Makes<strong> at most one clockwise 90-degree</strong><strong> turn</strong> to another diagonal direction while <strong>maintaining</strong> the sequence.</li>
	</ul>
	</li>
</ul>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3459.Length%20of%20Longest%20V-Shaped%20Diagonal%20Segment/images/length_of_longest3.jpg" style="width: 481px; height: 202px;" /></p>

<p>Return the <strong>length</strong> of the <strong>longest</strong> <strong>V-shaped diagonal segment</strong>. If no valid segment <em>exists</em>, return 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[2,2,1,2,2],[2,0,2,2,0],[2,0,1,1,0],[1,0,2,2,2],[2,0,0,2,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3459.Length%20of%20Longest%20V-Shaped%20Diagonal%20Segment/images/matrix_1-2.jpg" style="width: 201px; height: 192px;" /></p>

<p>The longest V-shaped diagonal segment has a length of 5 and follows these coordinates: <code>(0,2) &rarr; (1,3) &rarr; (2,4)</code>, takes a <strong>90-degree clockwise turn</strong> at <code>(2,4)</code>, and continues as <code>(3,3) &rarr; (4,2)</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[2,2,2,2,2],[2,0,2,2,0],[2,0,1,1,0],[1,0,2,2,2],[2,0,0,2,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3459.Length%20of%20Longest%20V-Shaped%20Diagonal%20Segment/images/matrix_2.jpg" style="width: 201px; height: 201px;" /></strong></p>

<p>The longest V-shaped diagonal segment has a length of 4 and follows these coordinates: <code>(2,3) &rarr; (3,2)</code>, takes a <strong>90-degree clockwise turn</strong> at <code>(3,2)</code>, and continues as <code>(2,1) &rarr; (1,0)</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,2,2,2,2],[2,2,2,2,0],[2,0,0,0,0],[0,0,2,2,2],[2,0,0,2,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3459.Length%20of%20Longest%20V-Shaped%20Diagonal%20Segment/images/matrix_3.jpg" style="width: 201px; height: 201px;" /></strong></p>

<p>The longest V-shaped diagonal segment has a length of 5 and follows these coordinates: <code>(0,0) &rarr; (1,1) &rarr; (2,2) &rarr; (3,3) &rarr; (4,4)</code>.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest V-shaped diagonal segment has a length of 1 and follows these coordinates: <code>(0,0)</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>m == grid[i].length</code></li>
	<li><code>1 &lt;= n, m &lt;= 500</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code>, <code>1</code> or <code>2</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoized Search

We design a function $\text{dfs}(i, j, k, \textit{cnt})$ that returns the length of the longest V-shaped diagonal segment, where $(i, j)$ is the previous position, $k$ is the current direction, and $\textit{cnt}$ is the remaining number of allowed turns.

The logic of the $\text{dfs}$ function is as follows:

First, based on the previous position and the current direction, we calculate the current position $(x, y)$ and determine the target value $\textit{target}$. If $x$ or $y$ is out of bounds, or if $\textit{grid}[x][y] \neq \textit{target}$, we return $0$.

Otherwise, we have two choices:

Continue moving in the current direction.
Make a 90-degree clockwise turn at the current position and then continue moving.
To implement the turn, if the current direction is $k$, the new direction after a 90-degree clockwise turn is $(k + 1) \bmod 4$. We take the maximum result from these two choices as the result for the current state.

In the main function, we iterate over the entire matrix. For each position with value 1, we try searching in all four directions and update the answer.

After the traversal, we return the answer.

To avoid repeated calculations, we use memoization to cache intermediate results.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns in the matrix, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lenOfVDiagonal(self, grid: List[List[int]]) -> int:
        @cache
        def dfs(i: int, j: int, k: int, cnt: int) -> int:
            x, y = i + dirs[k], j + dirs[k + 1]
            target = 2 if grid[i][j] == 1 else (2 - grid[i][j])
            if not 0 <= x < m or not 0 <= y < n or grid[x][y] != target:
                return 0
            res = dfs(x, y, k, cnt)
            if cnt > 0:
                res = max(res, dfs(x, y, (k + 1) % 4, 0))
            return 1 + res

        m, n = len(grid), len(grid[0])
        dirs = (1, 1, -1, -1, 1)
        ans = 0
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                if x == 1:
                    for k in range(4):
                        ans = max(ans, dfs(i, j, k, 1) + 1)
        return ans
```

#### Java

```java
class Solution {
    private int m, n;
    private final int[] dirs = {1, 1, -1, -1, 1};
    private Integer[][][][] f;

    public int lenOfVDiagonal(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        f = new Integer[m][n][4][2];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        ans = Math.max(ans, dfs(grid, i, j, k, 1) + 1);
                    }
                }
            }
        }
        return ans;
    }

    private int dfs(int[][] grid, int i, int j, int k, int cnt) {
        if (f[i][j][k][cnt] != null) {
            return f[i][j][k][cnt];
        }
        int x = i + dirs[k];
        int y = j + dirs[k + 1];
        int target = grid[i][j] == 1 ? 2 : (2 - grid[i][j]);
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != target) {
            f[i][j][k][cnt] = 0;
            return 0;
        }
        int res = dfs(grid, x, y, k, cnt);
        if (cnt > 0) {
            res = Math.max(res, dfs(grid, x, y, (k + 1) % 4, 0));
        }
        f[i][j][k][cnt] = 1 + res;
        return 1 + res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    static constexpr int MAXN = 501;
    int f[MAXN][MAXN][4][2];

    int lenOfVDiagonal(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int dirs[5] = {1, 1, -1, -1, 1};
        memset(f, -1, sizeof(f));

        auto dfs = [&](this auto&& dfs, int i, int j, int k, int cnt) -> int {
            if (f[i][j][k][cnt] != -1) {
                return f[i][j][k][cnt];
            }
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            int target = grid[i][j] == 1 ? 2 : (2 - grid[i][j]);
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != target) {
                f[i][j][k][cnt] = 0;
                return 0;
            }
            int res = dfs(x, y, k, cnt);
            if (cnt > 0) {
                res = max(res, dfs(x, y, (k + 1) % 4, 0));
            }
            f[i][j][k][cnt] = 1 + res;
            return 1 + res;
        };

        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; ++k) {
                        ans = max(ans, dfs(i, j, k, 1) + 1);
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func lenOfVDiagonal(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dirs := []int{1, 1, -1, -1, 1}
	f := make([][][4][2]int, m)
	for i := range f {
		f[i] = make([][4][2]int, n)
	}

	var dfs func(i, j, k, cnt int) int
	dfs = func(i, j, k, cnt int) int {
		if f[i][j][k][cnt] != 0 {
			return f[i][j][k][cnt]
		}

		x := i + dirs[k]
		y := j + dirs[k+1]

		var target int
		if grid[i][j] == 1 {
			target = 2
		} else {
			target = 2 - grid[i][j]
		}

		if x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != target {
			f[i][j][k][cnt] = 0
			return 0
		}

		res := dfs(x, y, k, cnt)
		if cnt > 0 {
			res = max(res, dfs(x, y, (k+1)%4, 0))
		}
		f[i][j][k][cnt] = res + 1
		return res + 1
	}

	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				for k := 0; k < 4; k++ {
					ans = max(ans, dfs(i, j, k, 1)+1)
				}
			}
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
