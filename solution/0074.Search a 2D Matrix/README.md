## 搜索二维矩阵

### 问题描述
编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

- 每行中的整数从左到右按升序排列。
- 每行的第一个整数大于前一行的最后一个整数。

```
示例 1:
输入:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
输出: true

示例 2:
输入:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
输出: false
```

### 思路

1. 因为矩阵按特性排列，所以先定位行坐标
2. 定位行坐标后直接调函数

一开始本来想定位到行之后用二分查找的，但是考虑到这个元素本身可能不存在，所以建议不调迭代器的话用顺序查找吧

```CPP
class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        if(matrix.empty())return false;
        
        size_t row = matrix.size();
        size_t column = matrix[0].size();
        if(column == 0 || column == 0)return false;
        
        if(target < matrix[0][0] || target > matrix[row-1][column-1])return false;
        
        for(int i = 0;i<row;i++){
            if(matrix[i][column-1]<target)continue;

            auto iter = find(matrix[i].begin(),matrix[i].end(),target);
            if(iter != matrix[i].end())return true;
            else return false;
        }
        
        return false;
    } 
};
```