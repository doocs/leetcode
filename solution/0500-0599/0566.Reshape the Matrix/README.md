# [566. 重塑矩阵](https://leetcode.cn/problems/reshape-the-matrix)

[English Version](/solution/0500-0599/0566.Reshape%20the%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在 MATLAB 中，有一个非常有用的函数 <code>reshape</code> ，它可以将一个&nbsp;<code>m x n</code> 矩阵重塑为另一个大小不同（<code>r x c</code>）的新矩阵，但保留其原始数据。</p>

<p>给你一个由二维数组 <code>mat</code> 表示的&nbsp;<code>m x n</code> 矩阵，以及两个正整数 <code>r</code> 和 <code>c</code> ，分别表示想要的重构的矩阵的行数和列数。</p>

<p>重构后的矩阵需要将原始矩阵的所有元素以相同的<strong> 行遍历顺序 </strong>填充。</p>

<p>如果具有给定参数的 <code>reshape</code> 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0566.Reshape%20the%20Matrix/images/reshape1-grid.jpg" style="width: 613px; height: 173px;" />
<pre>
<strong>输入：</strong>mat = [[1,2],[3,4]], r = 1, c = 4
<strong>输出：</strong>[[1,2,3,4]]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0566.Reshape%20the%20Matrix/images/reshape2-grid.jpg" style="width: 453px; height: 173px;" />
<pre>
<strong>输入：</strong>mat = [[1,2],[3,4]], r = 2, c = 4
<strong>输出：</strong>[[1,2],[3,4]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>-1000 &lt;= mat[i][j] &lt;= 1000</code></li>
	<li><code>1 &lt;= r, c &lt;= 300</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们先获取原矩阵的行数和列数，分别记为 $m$ 和 $n$。如果 $m \times n \neq r \times c$，则无法重塑矩阵，直接返回原矩阵。

否则，我们创建一个新矩阵，新矩阵的行数为 $r$，列数为 $c$。我们从原矩阵的第一个元素开始，按照行优先的顺序遍历原矩阵的所有元素，将遍历到的元素按顺序放入新矩阵中。

遍历完原矩阵的所有元素后，我们即可得到答案。

时间复杂度 $O(m \times n)$，其中 $m$ 和 $n$ 分别是原矩阵的行数和列数。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **TypeScript**

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
