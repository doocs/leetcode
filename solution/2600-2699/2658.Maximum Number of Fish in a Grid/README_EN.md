---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2658.Maximum%20Number%20of%20Fish%20in%20a%20Grid/README_EN.md
rating: 1489
source: Biweekly Contest 103 Q3
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Array
    - Matrix
---

<!-- problem:start -->

# [2658. Maximum Number of Fish in a Grid](https://leetcode.com/problems/maximum-number-of-fish-in-a-grid)

[中文文档](/solution/2600-2699/2658.Maximum%20Number%20of%20Fish%20in%20a%20Grid/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> 2D matrix <code>grid</code> of size <code>m x n</code>, where <code>(r, c)</code> represents:</p>

<ul>
	<li>A <strong>land</strong> cell if <code>grid[r][c] = 0</code>, or</li>
	<li>A <strong>water</strong> cell containing <code>grid[r][c]</code> fish, if <code>grid[r][c] &gt; 0</code>.</li>
</ul>

<p>A fisher can start at any <strong>water</strong> cell <code>(r, c)</code> and can do the following operations any number of times:</p>

<ul>
	<li>Catch all the fish at cell <code>(r, c)</code>, or</li>
	<li>Move to any adjacent <strong>water</strong> cell.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of fish the fisher can catch if he chooses his starting cell optimally, or </em><code>0</code> if no water cell exists.</p>

<p>An <strong>adjacent</strong> cell of the cell <code>(r, c)</code>, is one of the cells <code>(r, c + 1)</code>, <code>(r, c - 1)</code>, <code>(r + 1, c)</code> or <code>(r - 1, c)</code> if it exists.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2658.Maximum%20Number%20of%20Fish%20in%20a%20Grid/images/example.png" style="width: 241px; height: 161px;" />
<pre>
<strong>Input:</strong> grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
<strong>Output:</strong> 7
<strong>Explanation:</strong> The fisher can start at cell <code>(1,3)</code> and collect 3 fish, then move to cell <code>(2,3)</code>&nbsp;and collect 4 fish.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2658.Maximum%20Number%20of%20Fish%20in%20a%20Grid/images/example2.png" />
<pre>
<strong>Input:</strong> grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The fisher can start at cells (0,0) or (3,3) and collect a single fish. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

According to the problem description, we only need to find the number of fish in each connected water area and then take the maximum value. Therefore, we can use the depth-first search method to solve this problem.

We define a function $dfs(i, j)$, which indicates the maximum number of fish that can be caught starting from the cell in the $i$-th row and the $j$-th column. The execution logic of the function $dfs(i, j)$ is as follows:

We use a variable $cnt$ to record the number of fish in the current cell, and then set the number of fish in the current cell to $0$, indicating that it has been fished. Then we traverse the four directions of the current cell, if the cell $(x, y)$ in a certain direction is in the grid and is a water cell, then we recursively call the $dfs(x, y)$ function and add the return value to $cnt$. Finally, return $cnt$.

In the main function, we traverse all the cells $(i, j)$. If the current cell is a water cell, we call the $dfs(i, j)$ function, take the maximum value of the return value as the answer, and return it.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. where $m$ and $n$ are the number of rows and columns of the grid graph respectively.

<!-- tabs:start -->

```python
class Solution:
    def findMaxFish(self, grid: List[List[int]]) -> int:
        def dfs(i: int, j: int) -> int:
            cnt = grid[i][j]
            grid[i][j] = 0
            for a, b in pairwise((-1, 0, 1, 0, -1)):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y]:
                    cnt += dfs(x, y)
            return cnt

        m, n = len(grid), len(grid[0])
        ans = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j]:
                    ans = max(ans, dfs(i, j))
        return ans
```

```java
class Solution {
    private int[][] grid;
    private int m;
    private int n;

    public int findMaxFish(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > 0) {
                    ans = Math.max(ans, dfs(i, j));
                }
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        int cnt = grid[i][j];
        grid[i][j] = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
                cnt += dfs(x, y);
            }
        }
        return cnt;
    }
}
```

```cpp
class Solution {
public:
    int findMaxFish(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int ans = 0;
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            int cnt = grid[i][j];
            grid[i][j] = 0;
            int dirs[5] = {-1, 0, 1, 0, -1};
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y]) {
                    cnt += dfs(x, y);
                }
            }
            return cnt;
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    ans = max(ans, dfs(i, j));
                }
            }
        }
        return ans;
    }
};
```

```go
func findMaxFish(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	dirs := [5]int{-1, 0, 1, 0, -1}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		cnt := grid[i][j]
		grid[i][j] = 0
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0 {
				cnt += dfs(x, y)
			}
		}
		return cnt
	}
	for i := range grid {
		for j := range grid[i] {
			if grid[i][j] > 0 {
				ans = max(ans, dfs(i, j))
			}
		}
	}
	return
}
```

```ts
function findMaxFish(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;

    const dirs = [-1, 0, 1, 0, -1];
    const dfs = (i: number, j: number): number => {
        let cnt = grid[i][j];
        grid[i][j] = 0;
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
                cnt += dfs(x, y);
            }
        }
        return cnt;
    };

    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] > 0) {
                ans = Math.max(ans, dfs(i, j));
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
