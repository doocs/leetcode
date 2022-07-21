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

动态规划法。

我们假设 `dp[i][j]` 表示走到格子 `(i, j)` 的礼物最大累计价值，则 `dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1]`。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxValue(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        dp = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1]
        return dp[m][n]
```

### **Java**

```java
class Solution {
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return dp[m][n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxValue(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return dp[m][n];
    }
};
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
    let dp = new Array(m + 1);
    for (let i = 0; i < m + 1; ++i) {
        dp[i] = new Array(n + 1).fill(0);
    }
    for (let i = 1; i < m + 1; ++i) {
        for (let j = 1; j < n + 1; ++j) {
            dp[i][j] =
                Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
        }
    }
    return dp[m][n];
};
```

### **Go**

```go
func maxValue(grid [][]int) int {
    m, n := len(grid), len(grid[0])
    dp := make([][]int, m + 1)
    for i := 0; i < m + 1; i++ {
        dp[i] = make([]int, n + 1)
    }
    for i := 1; i < m + 1; i++ {
        for j := 1; j < n + 1; j++ {
            dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1]
        }
    }
    return dp[m][n]
}

func max(a, b int) int {
    if (a > b) {
        return a
    }
    return b
}
```

### **TypeScript**

```ts
function maxValue(grid: number[][]): number {
    let n = grid.length;
    let m = grid[0].length;
    for (let i = 1; i < n; i++) {
        grid[i][0] += grid[i - 1][0];
    }
    for (let i = 1; i < m; i++) {
        grid[0][i] += grid[0][i - 1];
    }
    for (let i = 1; i < n; i++) {
        for (let j = 1; j < m; j++) {
            grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
        }
    }
    return grid[n - 1][m - 1];
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
        int[,] dp = new int[m+1,n+1];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                dp[i,j] = Math.Max(dp[i-1,j], dp[i,j-1]) + grid[i-1][j-1];
            }
        }
        return dp[m,n];

    }
}
```

### **...**

```

```

<!-- tabs:end -->
