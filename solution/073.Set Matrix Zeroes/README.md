给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。

```
示例 1:
输入: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
输出: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
示例 2:

输入: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
输出: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
```

### 进阶:
一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。

一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。

你能想出一个常数空间的解决方案吗？

### 思路1

1. 创建行列辅助数组，先遍历矩阵，定位元素是0的行列，分别加入行数组，列数组
2. 从行数组中取出元素，整行置零；同理列数组

空间复杂度`O(m+n)`，而且分别进行处理和列处理时有重复操作

### 思路2（优化思路1）

1. 先检查首行首列有没有0，有的话设置bool标记
2. 从第二行第二列开始遍历，如果发现有0，设置`matrix[i][0] = 0和matrix[0][j] = 0`，即把首行首列对应行列值设置为0
3. 遍历首行首列，把值为0的行按行设置为0;列同理
4. 查看标记位，看是否需要把首行首列设置为0

这种思路没有用额外的空间，但是时间复杂度和思路1一样，都有待解决重复操作的问题

整体好于思路1，时间复杂度比思路1稳定

### Solution1

```CPP
class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        if(matrix.empty())return;
        //行数组，列数组
        int rowNum = matrix.size();
        int columnNum = matrix[0].size();
        vector<int> rowVec;
        vector<int> columnVec;
        
        
        for(int i = 0;i<rowNum;i++){
            for(int j = 0;j<columnNum;j++){
                if(matrix[i][j] == 0){
                    auto iter = find(rowVec.begin(),rowVec.end(),i);
                    if(iter == rowVec.end())rowVec.push_back(i);
                    
                    iter = find(columnVec.begin(),columnVec.end(),j);
                    if(iter == columnVec.end())columnVec.push_back(j);
                }
            }
        }
        
        rowNum = rowVec.size();
        if(rowNum == 0)return;
        
        int row;
        int column;
        
        //行处理
        for(int i = 0;i<rowNum;i++){
            row = rowVec[i];
            for(int j = 0;j<columnNum;j++){
                matrix[row][j] = 0;
            }
        }
        
        //列处理
        columnNum = columnVec.size();
        rowNum = matrix.size();
        for(int i = 0 ; i < columnNum;i++){
            column = columnVec[i];
            for(int j = 0;j<rowNum;j++){
                matrix[j][column] = 0;
            }
        }
        
    }
};
```

### Solution 2

```CPP
class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        if(matrix.empty()) return;
        int m = matrix.size();
        int n = matrix[0].size();
        bool row = false , column = false;
        
        
        for(int i = 0; i < m; i++)//判断第1列的0；
        {
            if(matrix[i][0] == 0)
            {
                column = true;
                break;
            }
        }
       for(int i = 0; i < n; i ++)//判断第1行的0；
        {
            if(matrix[0][i] == 0)
            {
                row = true;
                break;
            }
        }
        
        for(int i = 1; i < m;i++)
        {
            for(int j = 1; j < n;j++)
            {
                if(matrix[i][j] == 0)
                {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for(int i = 1; i < m;i++)
        {
            for(int j = 1; j < n;j++)
            {
                if(matrix[i][0] == 0 || matrix[0][j] == 0)
                {
                    matrix[i][j] = 0;
                }
            }
        }
        if(row) 
            for(int i = 0; i < n;i++)
                matrix[0][i] = 0;
        if(column) 
            for(int i = 0; i < m;i++)
                matrix[i][0] = 0;
        
        return;

        
    }
};
```