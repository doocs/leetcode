---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0867.Transpose%20Matrix/README_EN.md
tags:
    - Array
    - Matrix
    - Simulation
---

<!-- problem:start -->

# [867. Transpose Matrix](https://leetcode.com/problems/transpose-matrix)

[中文文档](/solution/0800-0899/0867.Transpose%20Matrix/README.md)

## Description

<!-- description:start -->

<p>Given a 2D integer array <code>matrix</code>, return <em>the <strong>transpose</strong> of</em> <code>matrix</code>.</p>

<p>The <strong>transpose</strong> of a matrix is the matrix flipped over its main diagonal, switching the matrix&#39;s row and column indices.</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0867.Transpose%20Matrix/images/hint_transpose.png" style="width: 600px; height: 197px;" /></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[1,2,3],[4,5,6],[7,8,9]]
<strong>Output:</strong> [[1,4,7],[2,5,8],[3,6,9]]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[1,2,3],[4,5,6]]
<strong>Output:</strong> [[1,4],[2,5],[3,6]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= matrix[i][j] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

Let $m$ be the number of rows and $n$ be the number of columns in the matrix $\textit{matrix}$. According to the definition of transpose, the transposed matrix $\textit{ans}$ will have $n$ rows and $m$ columns.

For any position $(i, j)$ in $\textit{ans}$, it corresponds to the position $(j, i)$ in the matrix $\textit{matrix}$. Therefore, we traverse each element in the matrix $\textit{matrix}$ and transpose it to the corresponding position in $\textit{ans}$.

After the traversal, we return $\textit{ans}$.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns in the matrix $\textit{matrix}$, respectively. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def transpose(self, matrix: List[List[int]]) -> List[List[int]]:
        return list(zip(*matrix))
```

#### Java

```java
class Solution {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                ans[i][j] = matrix[j][i];
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> transpose(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        vector<vector<int>> ans(n, vector<int>(m));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                ans[i][j] = matrix[j][i];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func transpose(matrix [][]int) [][]int {
	m, n := len(matrix), len(matrix[0])
	ans := make([][]int, n)
	for i := range ans {
		ans[i] = make([]int, m)
		for j := range ans[i] {
			ans[i][j] = matrix[j][i]
		}
	}
	return ans
}
```

#### TypeScript

```ts
function transpose(matrix: number[][]): number[][] {
    const [m, n] = [matrix.length, matrix[0].length];
    const ans: number[][] = Array.from({ length: n }, () => Array(m).fill(0));
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < m; ++j) {
            ans[i][j] = matrix[j][i];
        }
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[][]} matrix
 * @return {number[][]}
 */
var transpose = function (matrix) {
    const [m, n] = [matrix.length, matrix[0].length];
    const ans = Array.from({ length: n }, () => Array(m).fill(0));
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < m; ++j) {
            ans[i][j] = matrix[j][i];
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
