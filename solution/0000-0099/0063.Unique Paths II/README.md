---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0063.Unique%20Paths%20II/README.md
tags:
    - 数组
    - 动态规划
    - 矩阵
---

<!-- problem:start -->

# [63. 不同路径 II](https://leetcode.cn/problems/unique-paths-ii)

[English Version](/solution/0000-0099/0063.Unique%20Paths%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个&nbsp;<code>m x n</code>&nbsp;的整数数组&nbsp;<code>grid</code>。一个机器人初始位于 <strong>左上角</strong>（即 <code>grid[0][0]</code>）。机器人尝试移动到 <strong>右下角</strong>（即 <code>grid[m - 1][n - 1]</code>）。机器人每次只能向下或者向右移动一步。</p>

<p>网格中的障碍物和空位置分别用 <code>1</code> 和 <code>0</code> 来表示。机器人的移动路径中不能包含 <strong>任何</strong>&nbsp;有障碍物的方格。</p>

<p>返回机器人能够到达右下角的不同路径数量。</p>

<p>测试用例保证答案小于等于 <code>2 * 10<sup>9</sup></code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0063.Unique%20Paths%20II/images/robot1.jpg" />
<pre>
<strong>输入：</strong>obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
<strong>输出：</strong>2
<strong>解释：</strong>3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 <code>2</code> 条不同的路径：
1. 向右 -&gt; 向右 -&gt; 向下 -&gt; 向下
2. 向下 -&gt; 向下 -&gt; 向右 -&gt; 向右
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0063.Unique%20Paths%20II/images/robot2.jpg" />
<pre>
<strong>输入：</strong>obstacleGrid = [[0,1],[0,0]]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m ==&nbsp;obstacleGrid.length</code></li>
	<li><code>n ==&nbsp;obstacleGrid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>obstacleGrid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们设计一个函数 $\textit{dfs}(i, j)$ 表示从网格 $(i, j)$ 到网格 $(m - 1, n - 1)$ 的路径数。其中 $m$ 和 $n$ 分别是网格的行数和列数。

函数 $\textit{dfs}(i, j)$ 的执行过程如下：

-   如果 $i \ge m$ 或者 $j \ge n$，或者 $\textit{obstacleGrid}[i][j] = 1$，则路径数为 $0$；
-   如果 $i = m - 1$ 且 $j = n - 1$，则路径数为 $1$；
-   否则，路径数为 $\textit{dfs}(i + 1, j) + \textit{dfs}(i, j + 1)$。

为了避免重复计算，我们可以使用记忆化搜索的方法。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是网格的行数和列数。

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
        vector<vector<int>> f(m, vector<int>(n, -1));
        auto dfs = [&](this auto&& dfs, int i, int j) {
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

#### Rust

```rust
impl Solution {
    pub fn unique_paths_with_obstacles(obstacle_grid: Vec<Vec<i32>>) -> i32 {
        let m = obstacle_grid.len();
        let n = obstacle_grid[0].len();
        let mut f = vec![vec![-1; n]; m];
        Self::dfs(0, 0, &obstacle_grid, &mut f)
    }

    fn dfs(i: usize, j: usize, obstacle_grid: &Vec<Vec<i32>>, f: &mut Vec<Vec<i32>>) -> i32 {
        let m = obstacle_grid.len();
        let n = obstacle_grid[0].len();
        if i >= m || j >= n || obstacle_grid[i][j] == 1 {
            return 0;
        }
        if i == m - 1 && j == n - 1 {
            return 1;
        }
        if f[i][j] != -1 {
            return f[i][j];
        }
        let down = Self::dfs(i + 1, j, obstacle_grid, f);
        let right = Self::dfs(i, j + 1, obstacle_grid, f);
        f[i][j] = down + right;
        f[i][j]
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} obstacleGrid
 * @return {number}
 */
var uniquePathsWithObstacles = function (obstacleGrid) {
    const m = obstacleGrid.length;
    const n = obstacleGrid[0].length;
    const f = Array.from({ length: m }, () => Array(n).fill(-1));
    const dfs = (i, j) => {
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
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

我们可以使用动态规划的方法，定义一个二维数组 $f$，其中 $f[i][j]$ 表示从网格 $(0,0)$ 到网格 $(i,j)$ 的路径数。

我们首先初始化 $f$ 的第一列和第一行的所有值，然后遍历其它行和列，有两种情况：

-   若 $\textit{obstacleGrid}[i][j] = 1$，说明路径数为 $0$，那么 $f[i][j] = 0$；
-   若 $\textit{obstacleGrid}[i][j] = 0$，则 $f[i][j] = f[i - 1][j] + f[i][j - 1]$。

最后返回 $f[m - 1][n - 1]$ 即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是网格的行数和列数。

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

#### JavaScript

```js
/**
 * @param {number[][]} obstacleGrid
 * @return {number}
 */
var uniquePathsWithObstacles = function (obstacleGrid) {
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
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
