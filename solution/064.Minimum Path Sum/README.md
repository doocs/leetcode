给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

```
示例:
输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。
```

--------------
### 思路：

和62题《不同路径》是同一个思路，都是动态规划，区别是这里是带权值的路径

1. 创建二维数组`path[row][column]`，`path[i][j]  i∈[0,row-1],j∈[0,column-1]`表示到坐标`(i+1,j+1)`的**最短路径和**
2. 首行首列初始化；**首行**初始化是**上一行最短路径和+该位置权值**，对应公式`path[i][0] = path[i-1][0] + grid[i][0]; i∈[1,row-1],j∈[1,column-1]` 同理**首列**初始化g公式为`path[0][i] = path[0][i-1] + grid[0][i];`
3. 对各点`path[i][j]`求最短路径和，坐标`(i,j)`的最短路径可以由上一行得来，或者是前一列得来，动态规划方程为:`（前一列最小路径||前一行最小路径）两者较小值+当前坐标权值`，公式为:`path[i][j] = min(path[i-1][j],path[i][j]) + grid[i][j];`

```CPP
class Solution {
public:
    int minPathSum(vector<vector<int>>& grid) {
        int row = grid.size();
        if(row == 0)return 0;
        int column = grid[0].size();
        
        vector<vector<int>> path(row,vector<int>(column,0));
        path[0][0] = grid[0][0];
        for(int i = 1 ; i < column ; i++)path[0][i] = path[0][i-1] + grid[0][i];
        for(int i = 1 ; i < row;i++)path[i][0] = path[i-1][0]+grid[i][0];
        
        for(int i = 1;i<row;i++){
            for(int j = 1;j<column;j++){
                path[i][j] = min(path[i-1][j],path[i][j-1]) + grid[i][j];
            }
        }
        
        return path[row-1][column-1];
    }
};
```