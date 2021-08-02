# [面试题 47. 礼物的最大价值](https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/)

## 题目描述

在一个 `m*n` 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

**示例 1:**

```
输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 12
解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
```

**提示：**

- `0 < grid.length <= 200`
- `0 < grid[0].length <= 200`

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
      dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
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

### **...**

```

```

<!-- tabs:end -->
