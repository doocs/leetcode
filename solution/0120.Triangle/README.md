## 三角形最小路径和

### 问题描述

给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

例如，给定三角形：
```
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
```
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

说明：

如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。

----------
### 思想：

方法和119题如出一辙，都是利用**滚动数组**的原理(既对数组按特定规则进行条件层层处理，如同滚动)

方法是对每一个元素（除第一行）都构建最短路径

- 对于**左右边界**，最短路径只能是

```
triangle[i][0] = triangle[i-1][0]
以及
triangle[i][j] = triangle[i][j] + triangle[i-1][j-1] (j = i时成立)
```

- 对于**非左右边界**的其他任意值，其最短路径公式为

```
triangle[i][j] = triangle[i][j] + min(triangle[i-1][j],triangle[i-1][j-1]
```

- 对重新整理好的数组，遍历最下面一行元素，找最小值

- O(1) 额外空间！！！！

```CPP
class Solution {
public:
    int minimumTotal(vector<vector<int>>& triangle) {
        size_t rowNum = triangle.size();
        
        //特殊值处理
        if(rowNum == 0)return 0;
        if(rowNum == 1){
            if(triangle[0].empty())return 0;
            else return triangle[0][0];
        }
        
        for(int i = 1;i<rowNum;i++){
            for(int j = i;j>=0;j--){
                //边界处理
                if(j == 0){triangle[i][j] = triangle[i][j] + triangle[i-1][j];continue;}
                if(j == i){triangle[i][j] = triangle[i][j] + triangle[i-1][j-1];continue;}
                
                //一般处理
                triangle[i][j] = triangle[i][j] + min(triangle[i-1][j],triangle[i-1][j-1]);
            }
        }
        
        int ans = INT_MAX;
        for(auto v : triangle[rowNum-1]){
            if(ans > v)ans = v;
        } 
        return ans;
    }
};

```