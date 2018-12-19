## 不同路径 II
### 题目描述

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

![robot_maze](/img/robot_maze.png)

网格中的障碍物和空位置分别用 1 和 0 来表示。

说明：m 和 n 的值均不超过 100。

示例 1:
```
输入:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
输出: 2
解释:
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右
```

### 解法
与上题相比，这一题仅仅多了一个条件，就是网格中存在障碍物。对于最左侧和最上方，如果存在障碍物，那么往右或者往下的网格中路径均为 0。而其它格子，如果该格子有障碍物，那么路径为 0，否则是它“左方格子的路径数+上方格子的路径数之和”（递推式）。同样开辟一个二维数组存放中间结果。

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] res = new int[n][m];
        int i = 0;
        while (i < n && obstacleGrid[i][0] == 0) {
            // 无障碍物
            res[i++][0] = 1;
        }
        while (i < n) {
            res[i++][0] = 0;
        }
        
        i = 0;
        while (i < m && obstacleGrid[0][i] == 0) {
            // 无障碍物
            res[0][i++] = 1;
        }
        while (i < m) {
            res[0][i++] = 0;
        }
        
        for (int k = 1; k < n; ++k) {
            for (int j = 1; j < m; ++j) {
                res[k][j] = obstacleGrid[k][j] == 1 ? 0 : (res[k - 1][j] + res[k][j - 1]);
            }
        }
        
        return res[n - 1][m - 1];
        
    }
}
```