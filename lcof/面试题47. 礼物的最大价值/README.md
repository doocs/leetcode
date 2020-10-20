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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxValue(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])
        vals = [[0 for _ in range(cols)] for _ in range(rows)]
        vals[0][0] = grid[0][0]
        for i in range(1, rows):
            vals[i][0] = vals[i - 1][0] + grid[i][0]
        for j in range(1, cols):
            vals[0][j] = vals[0][j - 1] + grid[0][j]
        for i in range(1, rows):
            for j in range(1, cols):
                vals[i][j] = grid[i][j] + max(vals[i - 1][j], vals[i][j - 1])
        return vals[rows - 1][cols - 1]

```

### **Java**

```java
class Solution {
    public int maxValue(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][] vals = new int[rows][cols];
        vals[0][0] = grid[0][0];
        for (int i = 1; i < rows; ++i) {
            vals[i][0] = vals[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < cols; ++j) {
            vals[0][j] = vals[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; ++i) {
            for (int j = 1; j < cols; ++j) {
                vals[i][j] = grid[i][j] + Math.max(vals[i - 1][j], vals[i][j - 1]);
            }
        }
        return vals[rows - 1][cols - 1];
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} grid
 * @return {number}
 */
var maxValue = function (grid) {
  let row = grid.length;
  let col = grid[0].length;
  let dp = [...new Array(row + 1)].map(() => Array(col + 1).fill(0));
  for (let i = 1; i <= row; i++) {
    for (let j = 1; j <= col; j++) {
      dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
    }
  }
  return dp[row][col];
};
```

### **...**

```

```

<!-- tabs:end -->
