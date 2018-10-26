## 螺旋矩阵 II
### 题目描述

给定一个正整数 n，生成一个包含 1 到 n² 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

**示例:**
```
输入: 3
输出:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
```

### 解法
定义一个变量 `val`，由外往里，一圈圈遍历矩阵，进行赋值，每次赋值后 `val++`。


```java
class Solution {
    public int[][] generateMatrix(int n) {
        if (n < 1) {
            return null;
        }
        
        int[][] res = new int[n][n];
        int val = 1;
        
        int m1 = 0;
        int m2 = n - 1;
        while (m1 < m2) {
            for (int j = m1; j < m2; ++j) {
                res[m1][j] = val++;
            }
            for (int i = m1; i < m2; ++i) {
                res[i][m2] = val++;
            }
            for (int j = m2; j > m1; --j) {
                res[m2][j] = val++;
            }
            for (int i = m2; i > m1; --i) {
                res[i][m1] = val++;
            }
            ++m1;
            --m2;
        }
        if (m1 == m2) {
            res[m1][m1] = val;
        }
        
        return res;
    }
}
```