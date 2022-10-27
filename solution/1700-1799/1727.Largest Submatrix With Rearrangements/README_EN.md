# [1727. Largest Submatrix With Rearrangements](https://leetcode.com/problems/largest-submatrix-with-rearrangements)

[中文文档](/solution/1700-1799/1727.Largest%20Submatrix%20With%20Rearrangements/README.md)

## Description

<p>You are given a binary matrix <code>matrix</code> of size <code>m x n</code>, and you are allowed to rearrange the <strong>columns</strong> of the <code>matrix</code> in any order.</p>

<p>Return <em>the area of the largest submatrix within </em><code>matrix</code><em> where <strong>every</strong> element of the submatrix is </em><code>1</code><em> after reordering the columns optimally.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1727.Largest%20Submatrix%20With%20Rearrangements/images/screenshot-2020-12-30-at-40536-pm.png" style="width: 500px; height: 240px;" />
<pre>
<strong>Input:</strong> matrix = [[0,0,1],[1,1,1],[1,0,1]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 4.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1727.Largest%20Submatrix%20With%20Rearrangements/images/screenshot-2020-12-30-at-40852-pm.png" style="width: 500px; height: 62px;" />
<pre>
<strong>Input:</strong> matrix = [[1,0,1,0,1]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[1,1,0],[1,0,1]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Notice that you must rearrange entire columns, and there is no way to make a submatrix of 1s larger than an area of 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>matrix[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestSubmatrix(self, matrix: List[List[int]]) -> int:
        for i in range(1, len(matrix)):
            for j in range(len(matrix[0])):
                if matrix[i][j]:
                    matrix[i][j] = matrix[i - 1][j] + 1
        ans = 0
        for row in matrix:
            row.sort(reverse=True)
            for j, v in enumerate(row, 1):
                ans = max(ans, j * v)
        return ans
```

### **Java**

```java
class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = matrix[i - 1][j] + 1;
                }
            }
        }
        int ans = 0;
        for (var row : matrix) {
            Arrays.sort(row);
            for (int j = n - 1, k = 1; j >= 0 && row[j] > 0; --j, ++k) {
                int s = row[j] * k;
                ans = Math.max(ans, s);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int largestSubmatrix(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j]) {
                    matrix[i][j] = matrix[i - 1][j] + 1;
                }
            }
        }
        int ans = 0;
        for (auto& row : matrix) {
            sort(row.rbegin(), row.rend());
            for (int j = 0; j < n; ++j) {
                ans = max(ans, (j + 1) * row[j]);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func largestSubmatrix(matrix [][]int) int {
	m, n := len(matrix), len(matrix[0])
	for i := 1; i < m; i++ {
		for j := 0; j < n; j++ {
			if matrix[i][j] == 1 {
				matrix[i][j] = matrix[i-1][j] + 1
			}
		}
	}
	ans := 0
	for _, row := range matrix {
		sort.Ints(row)
		for j, k := n-1, 1; j >= 0 && row[j] > 0; j, k = j-1, k+1 {
			ans = max(ans, row[j]*k)
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
