# [566. Reshape the Matrix](https://leetcode.com/problems/reshape-the-matrix)

[中文文档](/solution/0500-0599/0566.Reshape%20the%20Matrix/README.md)

## Description

<p>In MATLAB, there is a handy function called <code>reshape</code> which can reshape an <code>m x n</code> matrix into a new one with a different size <code>r x c</code> keeping its original data.</p>

<p>You are given an <code>m x n</code> matrix <code>mat</code> and two integers <code>r</code> and <code>c</code> representing the number of rows and the number of columns of the wanted reshaped matrix.</p>

<p>The reshaped matrix should be filled with all the elements of the original matrix in the same row-traversing order as they were.</p>

<p>If the <code>reshape</code> operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0566.Reshape%20the%20Matrix/images/reshape1-grid.jpg" style="width: 613px; height: 173px;" />
<pre>
<strong>Input:</strong> mat = [[1,2],[3,4]], r = 1, c = 4
<strong>Output:</strong> [[1,2,3,4]]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0566.Reshape%20the%20Matrix/images/reshape2-grid.jpg" style="width: 453px; height: 173px;" />
<pre>
<strong>Input:</strong> mat = [[1,2],[3,4]], r = 2, c = 4
<strong>Output:</strong> [[1,2],[3,4]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>-1000 &lt;= mat[i][j] &lt;= 1000</code></li>
	<li><code>1 &lt;= r, c &lt;= 300</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def matrixReshape(self, mat: List[List[int]], r: int, c: int) -> List[List[int]]:
        m, n = len(mat), len(mat[0])
        if m * n != r * c:
            return mat
        ans = [[0] * c for _ in range(r)]
        for i in range(m * n):
            ans[i // c][i % c] = mat[i // n][i % n]
        return ans
```

### **Java**

```java
class Solution {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length, n = mat[0].length;
        if (m * n != r * c) {
            return mat;
        }
        int[][] ans = new int[r][c];
        for (int i = 0; i < m * n; ++i) {
            ans[i / c][i % c] = mat[i / n][i % n];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> matrixReshape(vector<vector<int>>& mat, int r, int c) {
        int m = mat.size(), n = mat[0].size();
        if (m * n != r * c) {
            return mat;
        }
        vector<vector<int>> ans(r, vector<int>(c));
        for (int i = 0; i < m * n; ++i) {
            ans[i / c][i % c] = mat[i / n][i % n];
        }
        return ans;
    }
};
```

### **Go**

```go
func matrixReshape(mat [][]int, r int, c int) [][]int {
	m, n := len(mat), len(mat[0])
	if m*n != r*c {
		return mat
	}
	ans := make([][]int, r)
	for i := range ans {
		ans[i] = make([]int, c)
	}
	for i := 0; i < m*n; i++ {
		ans[i/c][i%c] = mat[i/n][i%n]
	}
	return ans
}
```

### **TypeScrpt**

```ts
function matrixReshape(mat: number[][], r: number, c: number): number[][] {
    let m = mat.length,
        n = mat[0].length;
    if (m * n != r * c) return mat;
    let ans = Array.from({ length: r }, v => new Array(c).fill(0));
    let k = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            ans[Math.floor(k / c)][k % c] = mat[i][j];
            ++k;
        }
    }
    return ans;
}
```

```ts
function matrixReshape(mat: number[][], r: number, c: number): number[][] {
    const m = mat.length;
    const n = mat[0].length;
    if (m * n !== r * c) {
        return mat;
    }
    const ans = Array.from({ length: r }, () => new Array(c).fill(0));
    for (let i = 0; i < r * c; i++) {
        ans[Math.floor(i / c)][i % c] = mat[Math.floor(i / n)][i % n];
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn matrix_reshape(mat: Vec<Vec<i32>>, r: i32, c: i32) -> Vec<Vec<i32>> {
        let r = r as usize;
        let c = c as usize;
        let m = mat.len();
        let n = mat[0].len();
        if m * n != r * c {
            return mat;
        }
        let mut i = 0;
        let mut j = 0;
        (0..r)
            .into_iter()
            .map(|_| {
                (0..c)
                    .into_iter()
                    .map(|_| {
                        let res = mat[i][j];
                        j += 1;
                        if j == n {
                            j = 0;
                            i += 1;
                        }
                        res
                    })
                    .collect()
            })
            .collect()
    }
}
```

### **C**

```c
/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
int **matrixReshape(int **mat, int matSize, int *matColSize, int r, int c, int *returnSize, int **returnColumnSizes) {
    if (matSize * matColSize[0] != r * c) {
        *returnSize = matSize;
        *returnColumnSizes = matColSize;
        return mat;
    }
    *returnSize = r;
    *returnColumnSizes = malloc(sizeof(int) * r);
    int **ans = malloc(sizeof(int *) * r);
    for (int i = 0; i < r; i++) {
        (*returnColumnSizes)[i] = c;
        ans[i] = malloc(sizeof(int) * c);
    }
    for (int i = 0; i < r * c; i++) {
        ans[i / c][i % c] = mat[i / matColSize[0]][i % matColSize[0]];
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
