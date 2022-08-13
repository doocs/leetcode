# [01.08. Zero Matrix](https://leetcode.cn/problems/zero-matrix-lcci)

[中文文档](/lcci/01.08.Zero%20Matrix/README.md)

## Description

<p>Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.</p>

<p>&nbsp;</p>

<p><strong>Example 1: </strong></p>

<pre>

<strong>Input: </strong>

[

  [1,1,1],

  [1,0,1],

  [1,1,1]

]

<strong>Output: </strong>

[

  [1,0,1],

  [0,0,0],

  [1,0,1]

]

</pre>

<p><strong>Example 2: </strong></p>

<pre>

<strong>Input: </strong>

[

  [0,1,2,0],

  [3,4,5,2],

  [1,3,1,5]

]

<strong>Output: </strong>

[

  [0,0,0,0],

  [0,4,5,0],

  [0,3,1,0]

]

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        rows, cols = len(matrix), len(matrix[0])
        zero_rows, zero_cols = set(), set()
        for i in range(rows):
            for j in range(cols):
                if matrix[i][j] == 0:
                    zero_rows.add(i)
                    zero_cols.add(j)

        for i in zero_rows:
            for j in range(cols):
                matrix[i][j] = 0

        for j in zero_cols:
            for i in range(rows):
                matrix[i][j] = 0

        return matrix
```

### **Java**

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        Set<Integer> zeroRows = new HashSet<>();
        Set<Integer> zeroCols = new HashSet<>();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (matrix[i][j] == 0) {
                    zeroRows.add(i);
                    zeroCols.add(j);
                }
            }
        }

        for (int row : zeroRows) {
            for (int j = 0; j < cols; ++j) {
                matrix[row][j] = 0;
            }
        }

        for (int col : zeroCols) {
            for (int i = 0; i < rows; ++i) {
                matrix[i][col] = 0;
            }
        }
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} matrix
 * @return {void} Do not return anything, modify matrix in-place instead.
 */
var setZeroes = function (matrix) {
    let m = matrix.length,
        n = matrix[0].length;
    let rows = new Array(m).fill(false);
    let cols = new Array(n).fill(false);
    // 标记
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (matrix[i][j] == 0) {
                rows[i] = true;
                cols[j] = true;
            }
        }
    }
    // 清零
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (matrix[i][j] != 0 && (rows[i] || cols[j])) {
                matrix[i][j] = 0;
            }
        }
    }
};
```

### **...**

```

```

<!-- tabs:end -->
