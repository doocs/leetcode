# [面试题 47. 礼物的最大价值](https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof/)

## 题目描述

<p>在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> 
<code>[
&nbsp; [1,3,1],
&nbsp; [1,5,1],
&nbsp; [4,2,1]
]</code>
<strong>输出:</strong> <code>12
</code><strong>解释:</strong> 路径 1&rarr;3&rarr;5&rarr;2&rarr;1 可以拿到最多价值的礼物</pre>

<p>&nbsp;</p>

<p>提示：</p>

<ul>
	<li><code>0 &lt; grid.length &lt;= 200</code></li>
	<li><code>0 &lt; grid[0].length &lt;= 200</code></li>
</ul>

## 解法

**方法一：动态规划**

我们定义 $f[i][j]$ 为从棋盘左上角走到 $(i-1, j-1)$ 的礼物最大累计价值，那么 $f[i][j]$ 的值由 $f[i-1][j]$ 和 $f[i][j-1]$ 决定，即从上方格子和左方格子走过来的两个方案中选择一个价值较大的方案。因此我们可以写出动态规划转移方程：

$$
f[i][j] = max(f[i-1][j], f[i][j-1]) + grid[i-1][j-1]
$$

答案为 $f[m][n]$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为棋盘的行数和列数。

我们注意到 $f[i][j]$ 只与 $f[i-1][j]$ 和 $f[i][j-1]$ 有关，因此我们可以仅用两行数组 $f[2][n+1]$ 来存储状态，从而将空间复杂度优化到 $O(n)$。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxValue(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for i, row in enumerate(grid, 1):
            for j, v in enumerate(row, 1):
                f[i][j] = max(f[i - 1][j], f[i][j - 1]) + v
        return f[m][n]
```

```python
class Solution:
    def maxValue(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        f = [[0] * (n + 1) for _ in range(2)]
        for i, row in enumerate(grid, 1):
            for j, v in enumerate(row, 1):
                f[i & 1][j] = max(f[i & 1 ^ 1][j], f[i & 1][j - 1]) + v
        return f[m & 1][n]
```

### **Java**

```java
class Solution {
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return f[m][n];
    }
}
```

```java
class Solution {
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[2][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i & 1][j] = Math.max(f[i & 1 ^ 1][j], f[i & 1][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return f[m & 1][n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxValue(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> f(m + 1, vector<int>(n + 1, 0));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i][j] = max(f[i - 1][j], f[i][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return f[m][n];
    }
};
```

```cpp
class Solution {
public:
    int maxValue(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> f(2, vector<int>(n + 1, 0));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i & 1][j] = max(f[i & 1 ^ 1][j], f[i & 1][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return f[m & 1][n];
    }
};
```

### **Go**

```go
func maxValue(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			f[i][j] = max(f[i-1][j], f[i][j-1]) + grid[i-1][j-1]
		}
	}
	return f[m][n]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func maxValue(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	f := make([][]int, 2)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			f[i&1][j] = max(f[i&1^1][j], f[i&1][j-1]) + grid[i-1][j-1]
		}
	}
	return f[m&1][n]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} grid
 * @return {number}
 */
var maxValue = function (grid) {
    const m = grid.length;
    const n = grid[0].length;
    const f = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]) + grid[i - 1][j - 1];
        }
    }
    return f[m][n];
};
```

```js
/**
 * @param {number[][]} grid
 * @return {number}
 */
var maxValue = function (grid) {
    const m = grid.length;
    const n = grid[0].length;
    const f = new Array(2).fill(0).map(() => new Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            f[i & 1][j] =
                Math.max(f[(i & 1) ^ 1][j], f[i & 1][j - 1]) +
                grid[i - 1][j - 1];
        }
    }
    return f[m & 1][n];
};
```

### **TypeScript**

```ts
function maxValue(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const f = Array.from({ length: m + 1 }, _ => new Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]) + grid[i - 1][j - 1];
        }
    }
    return f[m][n];
}
```

```ts
function maxValue(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const f = Array.from({ length: 2 }, _ => new Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            f[i & 1][j] =
                Math.max(f[(i & 1) ^ 1][j], f[i & 1][j - 1]) +
                grid[i - 1][j - 1];
        }
    }
    return f[m & 1][n];
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_value(mut grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let m = grid[0].len();
        for i in 1..n {
            grid[i][0] += grid[i - 1][0];
        }
        for i in 1..m {
            grid[0][i] += grid[0][i - 1];
        }
        for i in 1..n {
            for j in 1..m {
                grid[i][j] += grid[i][j - 1].max(grid[i - 1][j]);
            }
        }
        grid[n - 1][m - 1]
    }
}
```

### **C#**

```cs
public class Solution {
    public int MaxValue(int[][] grid) {
        int m = grid.Length, n = grid[0].Length;
        int[, ] f = new int[m + 1, n + 1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                f[i, j] = Math.Max(f[i - 1, j], f[i, j - 1]) + grid[i - 1][j - 1];
            }
        }
        return f[m, n];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
