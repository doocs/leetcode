# [1329. Sort the Matrix Diagonally](https://leetcode.com/problems/sort-the-matrix-diagonally)

[中文文档](/solution/1300-1399/1329.Sort%20the%20Matrix%20Diagonally/README.md)

## Description

<p>A <strong>matrix diagonal</strong> is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix&#39;s end. For example, the <strong>matrix diagonal</strong> starting from <code>mat[2][0]</code>, where <code>mat</code> is a <code>6 x 3</code> matrix, includes cells <code>mat[2][0]</code>, <code>mat[3][1]</code>, and <code>mat[4][2]</code>.</p>

<p>Given an <code>m x n</code> matrix <code>mat</code> of integers, sort each <strong>matrix diagonal</strong> in ascending order and return <em>the resulting matrix</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1329.Sort%20the%20Matrix%20Diagonally/images/1482_example_1_2.png" style="width: 500px; height: 198px;" />
<pre>
<strong>Input:</strong> mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
<strong>Output:</strong> [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
<strong>Output:</strong> [[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def diagonalSort(self, mat: List[List[int]]) -> List[List[int]]:
        m, n = len(mat), len(mat[0])
        for k in range(min(m, n) - 1):
            for i in range(m - 1):
                for j in range(n - 1):
                    if mat[i][j] > mat[i + 1][j + 1]:
                        mat[i][j], mat[i + 1][j + 1] = mat[i + 1][j + 1], mat[i][j]
        return mat
```

### **Java**

```java
class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        for (int k = 0; k < Math.min(m, n) - 1; ++k) {
            for (int i = 0; i < m - 1; ++i) {
                for (int j = 0; j < n - 1; ++j) {
                    if (mat[i][j] > mat[i + 1][j + 1]) {
                        int t = mat[i][j];
                        mat[i][j] = mat[i + 1][j + 1];
                        mat[i + 1][j + 1] = t;
                    }
                }
            }
        }
        return mat;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> diagonalSort(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        for (int k = 0; k < min(m, n) - 1; ++k)
            for (int i = 0; i < m - 1; ++i)
                for (int j = 0; j < n - 1; ++j)
                    if (mat[i][j] > mat[i + 1][j + 1])
                        swap(mat[i][j], mat[i + 1][j + 1]);
        return mat;
    }
};
```

### **Go**

```go
func diagonalSort(mat [][]int) [][]int {
	m, n := len(mat), len(mat[0])
	for k := 0; k < m-1 && k < n-1; k++ {
		for i := 0; i < m-1; i++ {
			for j := 0; j < n-1; j++ {
				if mat[i][j] > mat[i+1][j+1] {
					mat[i][j], mat[i+1][j+1] = mat[i+1][j+1], mat[i][j]
				}
			}
		}
	}
	return mat
}
```

### **...**

```

```

<!-- tabs:end -->
