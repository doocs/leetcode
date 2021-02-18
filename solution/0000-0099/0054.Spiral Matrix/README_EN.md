# [54. Spiral Matrix](https://leetcode.com/problems/spiral-matrix)

[中文文档](/solution/0000-0099/0054.Spiral%20Matrix/README.md)

## Description

<p>Given a matrix of <em>m</em> x <em>n</em> elements (<em>m</em> rows, <em>n</em> columns), return all elements of the matrix in spiral order.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong>

[

 [ 1, 2, 3 ],

 [ 4, 5, 6 ],

 [ 7, 8, 9 ]

]

<strong>Output:</strong> [1,2,3,6,9,8,7,4,5]

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong>

[

  [1, 2, 3, 4],

  [5, 6, 7, 8],

  [9,10,11,12]

]

<strong>Output:</strong> [1,2,3,4,8,12,11,10,9,5,6,7]

</pre>

**Constraints:**

- m == matrix.length
- n == matrix[i].length
- 1 <= m, n <= 10
- -100 <= matrix[i][j] <= 100

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        def add(i1, j1, i2, j2):
            if i1 == i2:
                return [matrix[i1][j] for j in range(j1, j2 + 1)]
            if j1 == j2:
                return [matrix[i][j1] for i in range(i1, i2 + 1)]
            return [matrix[i1][j] for j in range(j1, j2)] + [matrix[i][j2] for i in range(i1, i2)] + [matrix[i2][j] for j in range(j2, j1, -1)] + [matrix[i][j1] for i in range(i2, i1, -1)]

        m, n = len(matrix), len(matrix[0])
        i1, j1, i2, j2 = 0, 0, m - 1, n - 1
        res = []
        while i1 <= i2 and j1 <= j2:
            res += add(i1, j1, i2, j2)
            i1, j1, i2, j2 = i1 + 1, j1 + 1, i2 - 1, j2 - 1
        return res
```

### **Java**

```java
class Solution {
    private List<Integer> res;

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        res = new ArrayList<>();
        int i1 = 0, i2 = m - 1;
        int j1 = 0, j2 = n - 1;
        while (i1 <= i2 && j1 <= j2) {
            add(matrix, i1++, j1++, i2--, j2--);
        }
        return res;
    }

    private void add(int[][] matrix, int i1, int j1, int i2, int j2) {
        if (i1 == i2) {
            for (int j = j1; j <= j2; ++j) {
                res.add(matrix[i1][j]);
            }
            return;
        }
        if (j1 == j2) {
            for (int i = i1; i <= i2; ++i) {
                res.add(matrix[i][j1]);
            }
            return;
        }
        for (int j = j1; j < j2; ++j) {
            res.add(matrix[i1][j]);
        }
        for (int i = i1; i < i2; ++i) {
            res.add(matrix[i][j2]);
        }
        for (int j = j2; j > j1; --j) {
            res.add(matrix[i2][j]);
        }
        for (int i = i2; i > i1; --i) {
            res.add(matrix[i][j1]);
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
