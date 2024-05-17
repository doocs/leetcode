---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0063.Unique%20Paths%20II/README_EN.md
tags:
    - Array
    - Dynamic Programming
    - Matrix
---

<!-- problem:start -->

# [63. Unique Paths II](https://leetcode.com/problems/unique-paths-ii)

[中文文档](/solution/0000-0099/0063.Unique%20Paths%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> integer array <code>grid</code>. There is a robot initially located at the <b>top-left corner</b> (i.e., <code>grid[0][0]</code>). The robot tries to move to the <strong>bottom-right corner</strong> (i.e., <code>grid[m - 1][n - 1]</code>). The robot can only move either down or right at any point in time.</p>

<p>An obstacle and space are marked as <code>1</code> or <code>0</code> respectively in <code>grid</code>. A path that the robot takes cannot include <strong>any</strong> square that is an obstacle.</p>

<p>Return <em>the number of possible unique paths that the robot can take to reach the bottom-right corner</em>.</p>

<p>The testcases are generated so that the answer will be less than or equal to <code>2 * 10<sup>9</sup></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0063.Unique%20Paths%20II/images/robot1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -&gt; Right -&gt; Down -&gt; Down
2. Down -&gt; Down -&gt; Right -&gt; Right
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0063.Unique%20Paths%20II/images/robot2.jpg" style="width: 162px; height: 162px;" />
<pre>
<strong>Input:</strong> obstacleGrid = [[0,1],[0,0]]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == obstacleGrid.length</code></li>
	<li><code>n == obstacleGrid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>obstacleGrid[i][j]</code> is <code>0</code> or <code>1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

We design a function $dfs(i, j)$ to represent the number of paths from the grid $(i, j)$ to the grid $(m - 1, n - 1)$, where $m$ and $n$ are the number of rows and columns of the grid, respectively.

The execution process of the function $dfs(i, j)$ is as follows:

-   If $i \ge m$ or $j \ge n$, or $obstacleGrid[i][j] = 1$, then the number of paths is $0$;
-   If $i = m - 1$ and $j = n - 1$, then the number of paths is $1$;
-   Otherwise, the number of paths is $dfs(i + 1, j) + dfs(i, j + 1)$.

To avoid repeated calculations, we can use the method of memoization search.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Where $m$ and $n$ are the number of rows and columns of the grid, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i >= m or j >= n or obstacleGrid[i][j]:
                return 0
            if i == m - 1 and j == n - 1:
                return 1
            return dfs(i + 1, j) + dfs(i, j + 1)

        m, n = len(obstacleGrid), len(obstacleGrid[0])
        return dfs(0, 0)
```

#### Java

```java
class Solution {
    private Integer[][] f;
    private int[][] obstacleGrid;
    private int m;
    private int n;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;
        this.obstacleGrid = obstacleGrid;
        f = new Integer[m][n];
        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        if (i >= m || j >= n || obstacleGrid[i][j] == 1) {
            return 0;
        }
        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        if (f[i][j] == null) {
            f[i][j] = dfs(i + 1, j) + dfs(i, j + 1);
        }
        return f[i][j];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
        int m = obstacleGrid.size(), n = obstacleGrid[0].size();
        int f[m][n];
        memset(f, -1, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) {
            if (i >= m || j >= n || obstacleGrid[i][j]) {
                return 0;
            }
            if (i == m - 1 && j == n - 1) {
                return 1;
            }
            if (f[i][j] == -1) {
                f[i][j] = dfs(i + 1, j) + dfs(i, j + 1);
            }
            return f[i][j];
        };
        return dfs(0, 0);
    }
};
```

#### Go

```go
func uniquePathsWithObstacles(obstacleGrid [][]int) int {
	m, n := len(obstacleGrid), len(obstacleGrid[0])
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= m || j >= n || obstacleGrid[i][j] == 1 {
			return 0
		}
		if i == m-1 && j == n-1 {
			return 1
		}
		if f[i][j] == -1 {
			f[i][j] = dfs(i+1, j) + dfs(i, j+1)
		}
		return f[i][j]
	}
	return dfs(0, 0)
}
```

#### TypeScript

```ts
function uniquePathsWithObstacles(obstacleGrid: number[][]): number {
    const m = obstacleGrid.length;
    const n = obstacleGrid[0].length;
    const f: number[][] = Array.from({ length: m }, () => Array(n).fill(-1));
    const dfs = (i: number, j: number): number => {
        if (i >= m || j >= n || obstacleGrid[i][j] === 1) {
            return 0;
        }
        if (i === m - 1 && j === n - 1) {
            return 1;
        }
        if (f[i][j] === -1) {
            f[i][j] = dfs(i + 1, j) + dfs(i, j + 1);
        }
        return f[i][j];
    };
    return dfs(0, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

We define $f[i][j]$ as the number of paths to reach the grid $(i,j)$.

First, initialize all values in the first column and first row of $f$. Then, traverse other rows and columns, there are two cases:

-   If $obstacleGrid[i][j] = 1$, it means the number of paths is $0$, so $f[i][j] = 0$;
-   If $obstacleGrid[i][j] = 0$, then $f[i][j] = f[i - 1][j] + f[i][j - 1]$.

Finally, return $f[m - 1][n - 1]$.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Where $m$ and $n$ are the number of rows and columns of the grid, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        m, n = len(obstacleGrid), len(obstacleGrid[0])
        f = [[0] * n for _ in range(m)]
        for i in range(m):
            if obstacleGrid[i][0] == 1:
                break
            f[i][0] = 1
        for j in range(n):
            if obstacleGrid[0][j] == 1:
                break
            f[0][j] = 1
        for i in range(1, m):
            for j in range(1, n):
                if obstacleGrid[i][j] == 0:
                    f[i][j] = f[i - 1][j] + f[i][j - 1]
        return f[-1][-1]
```

#### Java

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] f = new int[m][n];
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (obstacleGrid[i][j] == 0) {
                    f[i][j] = f[i - 1][j] + f[i][j - 1];
                }
            }
        }
        return f[m - 1][n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
        int m = obstacleGrid.size(), n = obstacleGrid[0].size();
        vector<vector<int>> f(m, vector<int>(n));
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (obstacleGrid[i][j] == 0) {
                    f[i][j] = f[i - 1][j] + f[i][j - 1];
                }
            }
        }
        return f[m - 1][n - 1];
    }
};
```

#### Go

```go
func uniquePathsWithObstacles(obstacleGrid [][]int) int {
	m, n := len(obstacleGrid), len(obstacleGrid[0])
	f := make([][]int, m)
	for i := 0; i < m; i++ {
		f[i] = make([]int, n)
	}
	for i := 0; i < m && obstacleGrid[i][0] == 0; i++ {
		f[i][0] = 1
	}
	for j := 0; j < n && obstacleGrid[0][j] == 0; j++ {
		f[0][j] = 1
	}
	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			if obstacleGrid[i][j] == 0 {
				f[i][j] = f[i-1][j] + f[i][j-1]
			}
		}
	}
	return f[m-1][n-1]
}
```

#### TypeScript

```ts
function uniquePathsWithObstacles(obstacleGrid: number[][]): number {
    const m = obstacleGrid.length;
    const n = obstacleGrid[0].length;
    const f = Array.from({ length: m }, () => Array(n).fill(0));
    for (let i = 0; i < m; i++) {
        if (obstacleGrid[i][0] === 1) {
            break;
        }
        f[i][0] = 1;
    }
    for (let i = 0; i < n; i++) {
        if (obstacleGrid[0][i] === 1) {
            break;
        }
        f[0][i] = 1;
    }
    for (let i = 1; i < m; i++) {
        for (let j = 1; j < n; j++) {
            if (obstacleGrid[i][j] === 1) {
                continue;
            }
            f[i][j] = f[i - 1][j] + f[i][j - 1];
        }
    }
    return f[m - 1][n - 1];
}
```

#### Rust

```rust
impl Solution {
    pub fn unique_paths_with_obstacles(obstacle_grid: Vec<Vec<i32>>) -> i32 {
        let m = obstacle_grid.len();
        let n = obstacle_grid[0].len();
        let mut f = vec![vec![0; n]; m];
        for i in 0..n {
            if obstacle_grid[0][i] == 1 {
                break;
            }
            f[0][i] = 1;
        }
        for i in 0..m {
            if obstacle_grid[i][0] == 1 {
                break;
            }
            f[i][0] = 1;
        }
        for i in 1..m {
            for j in 1..n {
                if obstacle_grid[i][j] == 1 {
                    continue;
                }
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        f[m - 1][n - 1]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
