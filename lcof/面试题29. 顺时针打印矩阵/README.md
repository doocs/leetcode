# [面试题29. 顺时针打印矩阵](https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/)

## 题目描述
输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

**示例 1：**

```
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
```

**示例 2：**

```
输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]
```

**限制：**

- `0 <= matrix.length <= 100`
- `0 <= matrix[i].length <= 100`

## 解法
### Python3
```python
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        if len(matrix) == 0:
            return []

        s1, e1, s2, e2 = 0, 0, len(matrix) - 1, len(matrix[0]) - 1
        res = []
        while s1 <= s2 and e1 <= e2:
            res += self._spiral_add(matrix, s1, e1, s2, e2)
            s1, e1, s2, e2 = s1 + 1, e1 + 1, s2 - 1, e2 - 1
        return res

    def _spiral_add(self, matrix, s1, e1, s2, e2) -> List[int]:
        if s1 == s2:
            return [matrix[s1][j] for j in range(e1, e2 + 1)]
        if e1 == e2:
            return [matrix[i][e1] for i in range(s1, s2 + 1)]
        return [matrix[s1][j] for j in range(e1, e2)] + \
               [matrix[i][e2] for i in range(s1, s2)] + \
               [matrix[s2][j] for j in range(e2, e1, -1)] + \
               [matrix[i][e1] for i in range(s2, s1, -1)]

```

### Java
```java
class Solution {
    private int index;
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        index = 0;
        int m = matrix.length, n = matrix[0].length;
        int s1 = 0, e1 = 0, s2 = m - 1, e2 = n - 1;
        int[] res = new int[m * n];
        while (s1 <= s2 && e1 <= e2) {
            spiralAdd(matrix, s1++, e1++, s2--, e2--, res);
        }
        return res;
    }

    public void spiralAdd(int[][] matrix, int s1, int e1, int s2, int e2, int[] res) {
        if (s1 == s2) {
            for (int j = e1; j <= e2; ++j) {
                res[index++] = matrix[s1][j];
            }
            return;
        }
        if (e1 == e2) {
            for (int i = s1; i <= s2; ++i) {
                res[index++] = matrix[i][e1];
            }
            return;
        }

        for (int j = e1; j < e2; ++j) {
            res[index++] = matrix[s1][j];
        }
        for (int i = s1; i < s2; ++i) {
            res[index++] = matrix[i][e2];
        }
        for (int j = e2; j > e1; --j) {
            res[index++] = matrix[s2][j];
        }
        for (int i = s2; i > s1; --i) {
            res[index++] = matrix[i][e1];
        }
    }
}
```

### ...
```

```
