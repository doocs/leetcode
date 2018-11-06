## 螺旋矩阵
### 题目描述

给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

**示例 1:**
```
输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
输出: [1,2,3,6,9,8,7,4,5]
```

**示例 2:**
```
输入:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
输出: [1,2,3,4,8,12,11,10,9,5,6,7]
```

### 解法
由外往里，一圈圈遍历矩阵即可。遍历时，如果只有 1 行或者 1 列。直接遍历添加这一行/列元素。否则遍历一圈，陆续添加元素。


```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<Integer>();
        }
        int m = matrix.length;
        int n = matrix[0].length;
        
        int m1 = 0;
        int n1 = 0;
        int m2 = m - 1;
        int n2 = n - 1;
        List<Integer> res = new ArrayList<>();
        
        while (m1 <= m2 && n1 <= n2) {
            goCircle(res, matrix, m1++, n1++, m2--, n2--);
        }
        
        return res;
    }
    
    private void goCircle(List<Integer> res, int[][] matrix, int m1, int n1, int m2, int n2) {
        
        if (m1 == m2) {
            for (int j = n1; j <= n2; ++j) {
                res.add(matrix[m1][j]);
            }
        } else if (n1 == n2) {
            for (int i = m1; i <= m2; ++i) {
                res.add(matrix[i][n1]);
            }
        } else {
            for (int j = n1; j < n2; ++j) {
                res.add(matrix[m1][j]);
            }
            for (int i = m1; i < m2; ++i) {
                res.add(matrix[i][n2]);
            }
            for (int j = n2; j > n1; --j) {
                res.add(matrix[m2][j]);
            }
            for (int i = m2; i > m1; --i) {
                res.add(matrix[i][n1]);
            }
        }
        
        
    }
}
```