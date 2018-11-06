## 杨辉三角

### 问题描述
给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。

![杨辉三角](https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif)

在杨辉三角中，每个数是它左上方和右上方的数的和。

```
示例:
输入: 5
输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
```

### 思路

杨辉三角的特征是：

- 所有行的首元素和末尾元素都是1
- 除第一和第二行外，所有非首端末端元素都是前一行同列与前一列之和`matrix[i][j] = matrix[i-1][j]+matrix[i-1][j-1];`

```CPP
class Solution {
public:
    vector<vector<int>> generate(int numRows) {
        vector<vector<int>> ans;
        
        for(int i = 0;i<numRows;i++){
            vector<int> tmp(i+1);
            tmp[0] = 1;//最左侧为1
            for(int j = 1;j<=i;j++){
                if(i == j)//最右侧为1
                {
                    tmp[j] = 1;
                    break;
                }
                tmp[j] = ans[i-1][j-1] + ans[i-1][j]; 
            }
            ans.push_back(tmp);
        }     
        return ans;  
    }
};
```

```JS
const generate = function(numRows){
  let arr = [];
  for(let i = 0; i < numRows; i++){
    let row = [];
    row[0]=1;
    row[i] = 1;

    for(let j = 1; j < row.length - 1; j++){
      row[j] = arr[i-1][j-1] + arr[i-1][j];
    }
    arr.push(row);
  }
  return arr;
}

```