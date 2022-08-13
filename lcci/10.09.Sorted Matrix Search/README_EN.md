# [10.09. Sorted Matrix Search](https://leetcode.cn/problems/sorted-matrix-search-lcci)

[中文文档](/lcci/10.09.Sorted%20Matrix%20Search/README.md)

## Description

<p>Given an M x N matrix in which each row and each column is sorted in ascending order, write a method to find an element.</p>

<p><strong>Example:</strong></p>

<p>Given matrix:</p>

<pre>

[

  [1,   4,  7, 11, 15],

  [2,   5,  8, 12, 19],

  [3,   6,  9, 16, 22],

  [10, 13, 14, 17, 24],

  [18, 21, 23, 26, 30]

]

</pre>

<p>Given target&nbsp;=&nbsp;5,&nbsp;return&nbsp;<code>true.</code></p>

<p>Given target&nbsp;=&nbsp;20, return&nbsp;<code>false.</code></p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        if not matrix or not matrix[0]:
            return False
        m, n = len(matrix), len(matrix[0])
        i, j = m - 1, 0
        while i >= 0 and j < n:
            if matrix[i][j] == target:
                return True
            if matrix[i][j] > target:
                i -= 1
            else:
                j += 1
        return False
```

### **Java**

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                --i;
            } else {
                ++j;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        if (matrix.size() == 0 || matrix[0].size() == 0) return false;
        int m = matrix.size(), n = matrix[0].size();
        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] == target) return true;
            if (matrix[i][j] > target)
                --i;
            else
                ++j;
        }
        return false;
    }
};
```

### **Go**

```go
func searchMatrix(matrix [][]int, target int) bool {
	if len(matrix) == 0 || len(matrix[0]) == 0 {
		return false
	}
	m, n := len(matrix), len(matrix[0])
	i, j := m-1, 0
	for i >= 0 && j < n {
		if matrix[i][j] == target {
			return true
		}
		if matrix[i][j] > target {
			i--
		} else {
			j++
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
