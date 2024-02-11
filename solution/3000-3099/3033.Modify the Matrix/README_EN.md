# [3033. Modify the Matrix](https://leetcode.com/problems/modify-the-matrix)

[中文文档](/solution/3000-3099/3033.Modify%20the%20Matrix/README.md)

## Description

<p>Given a <strong>0-indexed</strong> <code>m x n</code> integer matrix <code>matrix</code>, create a new <strong>0-indexed</strong> matrix called <code>answer</code>. Make <code>answer</code> equal to <code>matrix</code>, then replace each element with the value <code>-1</code> with the <strong>maximum</strong> element in its respective column.</p>

<p>Return <em>the matrix</em> <code>answer</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3033.Modify%20the%20Matrix/images/matrix1.png" style="width: 491px; height: 161px;" />
<pre>
<strong>Input:</strong> matrix = [[1,2,-1],[4,-1,6],[7,8,9]]
<strong>Output:</strong> [[1,2,9],[4,8,6],[7,8,9]]
<strong>Explanation:</strong> The diagram above shows the elements that are changed (in blue).
- We replace the value in the cell [1][1] with the maximum value in the column 1, that is 8.
- We replace the value in the cell [0][2] with the maximum value in the column 2, that is 9.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3033.Modify%20the%20Matrix/images/matrix2.png" style="width: 411px; height: 111px;" />
<pre>
<strong>Input:</strong> matrix = [[3,-1],[5,2]]
<strong>Output:</strong> [[3,2],[5,2]]
<strong>Explanation:</strong> The diagram above shows the elements that are changed (in blue).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 50</code></li>
	<li><code>-1 &lt;= matrix[i][j] &lt;= 100</code></li>
	<li>The input is generated such that each column contains at least one non-negative integer.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def modifiedMatrix(self, matrix: List[List[int]]) -> List[List[int]]:
        rows = len(matrix)
        cols = len(matrix[0])
        for i in range(cols):
            max_val = float('-inf')
            for j in range(rows):
                max_val = max(max_val, matrix[j][i])
            
            for j in range(rows):
                if matrix[j][i] == -1:
                    matrix[j][i] = max_val
        
        return matrix
```

```java
class Solution {
    public int[][] modifiedMatrix(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        for (int i = 0; i < c; i++) {
            int maxs = Integer.MIN_VALUE;
            for (int j = 0; j < r; j++) {
                maxs = Math.max(maxs, matrix[j][i]);
            }
            for (int j = 0; j < r; j++) {
                if (matrix[j][i] == -1) {
                    matrix[j][i] = maxs;
                }
            }
        }
        return matrix;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> modifiedMatrix(vector<vector<int>>& matrix) {
        int r = matrix.size();
        int c = matrix[0].size();
        for (int i = 0; i < c; i++) {
            int maxs = INT_MIN;
            for (int j = 0; j < r; j++) {
                maxs = max(maxs, matrix[j][i]);
            }
            for (int j = 0; j < r; j++) {
                if (matrix[j][i] == -1) {
                    matrix[j][i] = maxs;
                }
            }
        }
        return matrix;
    }
};
```

```go
func modifiedMatrix(matrix [][]int) [][]int {
	r := len(matrix)
	c := len(matrix[0])
	for i := 0; i < c; i++ {
		maxs := math.MinInt32
		for j := 0; j < r; j++ {
			if matrix[j][i] > maxs {
				maxs = matrix[j][i]
			}
		}
		for j := 0; j < r; j++ {
			if matrix[j][i] == -1 {
				matrix[j][i] = maxs
			}
		}
	}
	return matrix
}
```

```ts
function modifiedMatrix(matrix: number[][]): number[][] {
    const rows = matrix.length;
    const cols = matrix[0].length;
    for (let i = 0; i < cols; i++) {
        let maxVal = Number.MIN_SAFE_INTEGER;
        for (let j = 0; j < rows; j++) {
            maxVal = Math.max(maxVal, matrix[j][i]);
        }
        for (let j = 0; j < rows; j++) {
            if (matrix[j][i] === -1) {
                matrix[j][i] = maxVal;
            }
        }
    }
    return matrix;
}
```

<!-- tabs:end -->

<!-- end -->
