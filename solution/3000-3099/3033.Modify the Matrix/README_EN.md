---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3033.Modify%20the%20Matrix/README_EN.md
rating: 1180
source: Weekly Contest 384 Q1
tags:
    - Array
    - Matrix
---

<!-- problem:start -->

# [3033. Modify the Matrix](https://leetcode.com/problems/modify-the-matrix)

[中文文档](/solution/3000-3099/3033.Modify%20the%20Matrix/README.md)

## Description

<!-- description:start -->

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can follow the problem description, traverse each column, find the maximum value of each column, and then traverse each column again, replacing the elements with a value of -1 with the maximum value of that column.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the matrix, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def modifiedMatrix(self, matrix: List[List[int]]) -> List[List[int]]:
        m, n = len(matrix), len(matrix[0])
        for j in range(n):
            mx = max(matrix[i][j] for i in range(m))
            for i in range(m):
                if matrix[i][j] == -1:
                    matrix[i][j] = mx
        return matrix
```

#### Java

```java
class Solution {
    public int[][] modifiedMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int j = 0; j < n; ++j) {
            int mx = -1;
            for (int i = 0; i < m; ++i) {
                mx = Math.max(mx, matrix[i][j]);
            }
            for (int i = 0; i < m; ++i) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = mx;
                }
            }
        }
        return matrix;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> modifiedMatrix(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        for (int j = 0; j < n; ++j) {
            int mx = -1;
            for (int i = 0; i < m; ++i) {
                mx = max(mx, matrix[i][j]);
            }
            for (int i = 0; i < m; ++i) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = mx;
                }
            }
        }
        return matrix;
    }
};
```

#### Go

```go
func modifiedMatrix(matrix [][]int) [][]int {
	m, n := len(matrix), len(matrix[0])
	for j := 0; j < n; j++ {
		mx := -1
		for i := 0; i < m; i++ {
			mx = max(mx, matrix[i][j])
		}
		for i := 0; i < m; i++ {
			if matrix[i][j] == -1 {
				matrix[i][j] = mx
			}
		}
	}
	return matrix
}
```

#### TypeScript

```ts
function modifiedMatrix(matrix: number[][]): number[][] {
    const [m, n] = [matrix.length, matrix[0].length];
    for (let j = 0; j < n; ++j) {
        let mx = -1;
        for (let i = 0; i < m; ++i) {
            mx = Math.max(mx, matrix[i][j]);
        }
        for (let i = 0; i < m; ++i) {
            if (matrix[i][j] === -1) {
                matrix[i][j] = mx;
            }
        }
    }
    return matrix;
}
```

#### C#

```cs
public class Solution {
    public int[][] ModifiedMatrix(int[][] matrix) {
        int m = matrix.Length, n = matrix[0].Length;
        for (int j = 0; j < n; ++j) {
            int mx = -1;
            for (int i = 0; i < m; ++i) {
                mx = Math.Max(mx, matrix[i][j]);
            }
            for (int i = 0; i < m; ++i) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = mx;
                }
            }
        }
        return matrix;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
