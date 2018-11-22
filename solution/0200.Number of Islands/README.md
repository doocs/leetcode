## 岛屿的个数
### 题目描述
给定一个由`'1'`（陆地）和`'0'`（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
```
示例 1:
输入:
11110
11010
11000
00000
输出: 1
```
```
示例 2:
输入:
11000
11000
00100
00011
输出: 3
```
### 解法
对网格进行遍历，每遇到一个岛屿，都将其中陆地`1`全部变成`0`，则遇到岛屿的总数即为所求。
```python
class Solution:
    def numIslands(self, grid):
        def dp(x, y):
            if x >= 0 and x < len(grid) and y >= 0 and y < len(grid[x]) and grid[x][y] == '1':
                grid[x][y] = '0'
                dp(x-1, y)
                dp(x+1, y)
                dp(x, y-1)
                dp(x, y+1)
        ans = 0
        for i in range(len(grid)):
            for j in range(len(grid[i])):
                if grid[i][j] == '1':
                    ans += 1
                    dp(i, j)
        return ans

```
