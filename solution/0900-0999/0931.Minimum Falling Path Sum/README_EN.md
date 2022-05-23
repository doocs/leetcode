# [931. Minimum Falling Path Sum](https://leetcode.com/problems/minimum-falling-path-sum)

[中文文档](/solution/0900-0999/0931.Minimum%20Falling%20Path%20Sum/README.md)

## Description

<p>Given an <code>n x n</code> array of integers <code>matrix</code>, return <em>the <strong>minimum sum</strong> of any <strong>falling path</strong> through</em> <code>matrix</code>.</p>

<p>A <strong>falling path</strong> starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position <code>(row, col)</code> will be <code>(row + 1, col - 1)</code>, <code>(row + 1, col)</code>, or <code>(row + 1, col + 1)</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0931.Minimum%20Falling%20Path%20Sum/images/failing1-grid.jpg" style="width: 499px; height: 500px;" />
<pre>
<strong>Input:</strong> matrix = [[2,1,3],[6,5,4],[7,8,9]]
<strong>Output:</strong> 13
<strong>Explanation:</strong> There are two falling paths with a minimum sum as shown.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0931.Minimum%20Falling%20Path%20Sum/images/failing2-grid.jpg" style="width: 164px; height: 365px;" />
<pre>
<strong>Input:</strong> matrix = [[-19,57],[-40,-5]]
<strong>Output:</strong> -59
<strong>Explanation:</strong> The falling path with a minimum sum is shown.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == matrix.length == matrix[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>-100 &lt;= matrix[i][j] &lt;= 100</code></li>
</ul>

## Solutions

Dynamic programming.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minFallingPathSum(self, matrix: List[List[int]]) -> int:
        n = len(matrix)
        for i in range(1, n):
            for j in range(n):
                mi = matrix[i - 1][j]
                if j > 0:
                    mi = min(mi, matrix[i - 1][j - 1])
                if j < n - 1:
                    mi = min(mi, matrix[i - 1][j + 1])
                matrix[i][j] += mi
        return min(matrix[n - 1])
```

### **Java**

```java
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int mi = matrix[i - 1][j];
                if (j > 0) {
                    mi = Math.min(mi, matrix[i - 1][j - 1]);
                }
                if (j < n - 1) {
                    mi = Math.min(mi, matrix[i - 1][j + 1]);
                }
                matrix[i][j] += mi;
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < n; ++j) {
            res = Math.min(res, matrix[n - 1][j]);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minFallingPathSum(vector<vector<int>>& matrix) {
        int n = matrix.size();
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int mi = matrix[i - 1][j];
                if (j > 0) mi = min(mi, matrix[i - 1][j - 1]);
                if (j < n - 1) mi = min(mi, matrix[i - 1][j + 1]);
                matrix[i][j] += mi;
            }
        }
        int res = INT_MAX;
        for (int j = 0; j < n; ++j) {
            res = min(res, matrix[n - 1][j]);
        }
        return res;
    }
};
```

### **Go**

```go
func minFallingPathSum(matrix [][]int) int {
    n := len(matrix)
    for i := 1; i < n; i++ {
        for j := 0; j < n; j++ {
            mi := matrix[i - 1][j]
            if j > 0 && mi > matrix[i - 1][j - 1] {
                mi = matrix[i - 1][j - 1]
            }
            if j < n - 1 && mi > matrix[i - 1][j + 1] {
                mi = matrix[i - 1][j + 1]
            }
            matrix[i][j] += mi
        }
    }
    res := 10000
    for j := 0; j < n; j++ {
        if res > matrix[n - 1][j] {
            res = matrix[n - 1][j]
        }
    }
    return res
}
```

### **...**

```

```

<!-- tabs:end -->
