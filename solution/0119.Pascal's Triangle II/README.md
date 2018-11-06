## 杨辉三角 II

### 问题描述
给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。

![杨辉三角](https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif)

在杨辉三角中，每个数是它左上方和右上方的数的和。

```
示例:

输入: 3
输出: [1,3,3,1]
```
进阶：

你可以优化你的算法到 O(k) 空间复杂度吗？

----------
### 思路

利用滚动数组的思想（和118题一样，省了行坐标）:

因为每一个列坐标都是上一个行同列和前列之和，所以，只需要一组数组就可以模拟出来，并不需要二维数组

`col[j] = col[j]+col[j-1];`

**注意**：因为使用了`j-1`列和`j`列，为了防止重叠，不能从前往后跟新数组；只能从后往前

```CPP
class Solution {
public:
    vector<int> getRow(int rowIndex) {
        vector<int> ans;
        
        for(int i = 0;i <= rowIndex;i++){
            for(int j = i-1;j > 0;j--){
                ans[j] = ans[j-1] + ans[j];
            }
            ans.push_back(1);
        }
        return ans;
        
    }
};

```