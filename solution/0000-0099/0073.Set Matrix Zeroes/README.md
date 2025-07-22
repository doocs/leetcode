---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0073.Set%20Matrix%20Zeroes/README.md
tags:
    - 数组
    - 哈希表
    - 矩阵
---

<!-- problem:start -->

# [73. 矩阵置零](https://leetcode.cn/problems/set-matrix-zeroes)

[English Version](/solution/0000-0099/0073.Set%20Matrix%20Zeroes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个&nbsp;<code><em>m</em> x <em>n</em></code> 的矩阵，如果一个元素为 <strong>0 </strong>，则将其所在行和列的所有元素都设为 <strong>0</strong> 。请使用 <strong><a href="http://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank">原地</a></strong> 算法<strong>。</strong></p>

<ul>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0073.Set%20Matrix%20Zeroes/images/mat1.jpg" style="width: 450px; height: 169px;" />
<pre>
<strong>输入：</strong>matrix = [[1,1,1],[1,0,1],[1,1,1]]
<strong>输出：</strong>[[1,0,1],[0,0,0],[1,0,1]]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0073.Set%20Matrix%20Zeroes/images/mat2.jpg" style="width: 450px; height: 137px;" />
<pre>
<strong>输入：</strong>matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
<strong>输出：</strong>[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[0].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>-2<sup>31</sup> &lt;= matrix[i][j] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>一个直观的解决方案是使用 &nbsp;<code>O(<em>m</em><em>n</em>)</code>&nbsp;的额外空间，但这并不是一个好的解决方案。</li>
	<li>一个简单的改进方案是使用 <code>O(<em>m</em>&nbsp;+&nbsp;<em>n</em>)</code> 的额外空间，但这仍然不是最好的解决方案。</li>
	<li>你能想出一个仅使用常量空间的解决方案吗？</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### Solution 1: Array Marking

Let the number of rows and columns of the matrix be $m$ and $n$, respectively. We use an array $\textit{rows}$ of length $m$ and an array $\textit{cols}$ of length $n$ to record which rows and columns need to be set to zero.

First, we traverse the matrix. When we find a zero element in the matrix, we set the corresponding row and column markers to $\text{true}$. That is, if $\textit{matrix}[i][j] = 0$, then $\textit{rows}[i] = \textit{cols}[j] = \text{true}$.

Finally, we traverse the matrix again and use the markers in $\textit{rows}$ and $\textit{cols}$ to update the elements in the matrix. When we find that $\textit{rows}[i]$ or $\textit{cols}[j]$ is $\text{true}$, we set $\textit{matrix}[i][j]$ to zero.

The time complexity is $O(m \times n)$, and the space complexity is $O(m + n)$. Here, $m$ and $n$ are the number of rows and columns of the matrix, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        m, n = len(matrix), len(matrix[0])
        row = [False] * m
        col = [False] * n
        for i in range(m):
            for j in range(n):
                if matrix[i][j] == 0:
                    row[i] = col[j] = True
        for i in range(m):
            for j in range(n):
                if row[i] or col[j]:
                    matrix[i][j] = 0
```

#### Java

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        vector<bool> row(m);
        vector<bool> col(n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
};
```

#### Go

```go
func setZeroes(matrix [][]int) {
	row := make([]bool, len(matrix))
	col := make([]bool, len(matrix[0]))
	for i := range matrix {
		for j, x := range matrix[i] {
			if x == 0 {
				row[i] = true
				col[j] = true
			}
		}
	}
	for i := range matrix {
		for j := range matrix[i] {
			if row[i] || col[j] {
				matrix[i][j] = 0
			}
		}
	}
}
```

#### TypeScript

```ts
/**
 Do not return anything, modify matrix in-place instead.
 */
function setZeroes(matrix: number[][]): void {
    const m = matrix.length;
    const n = matrix[0].length;
    const row: boolean[] = Array(m).fill(false);
    const col: boolean[] = Array(n).fill(false);
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (matrix[i][j] === 0) {
                row[i] = col[j] = true;
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (row[i] || col[j]) {
                matrix[i][j] = 0;
            }
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} matrix
 * @return {void} Do not return anything, modify matrix in-place instead.
 */
var setZeroes = function (matrix) {
    const m = matrix.length;
    const n = matrix[0].length;
    const row = Array(m).fill(false);
    const col = Array(n).fill(false);
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (matrix[i][j] === 0) {
                row[i] = col[j] = true;
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (row[i] || col[j]) {
                matrix[i][j] = 0;
            }
        }
    }
};
```

#### C#

```cs
public class Solution {
    public void SetZeroes(int[][] matrix) {
        int m = matrix.Length, n = matrix[0].Length;
        bool[] row = new bool[m], col = new bool[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：原地标记

方法一中使用了额外的数组标记待清零的行和列，实际上我们也可以直接用矩阵的第一行和第一列来标记，不需要开辟额外的数组空间。

由于第一行、第一列用来做标记，它们的值可能会因为标记而发生改变，因此，我们需要额外的变量 $i0$, $j0$ 来标记第一行、第一列是否需要被清零。

时间复杂度 $O(m\times n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        m, n = len(matrix), len(matrix[0])
        i0 = any(v == 0 for v in matrix[0])
        j0 = any(matrix[i][0] == 0 for i in range(m))
        for i in range(1, m):
            for j in range(1, n):
                if matrix[i][j] == 0:
                    matrix[i][0] = matrix[0][j] = 0
        for i in range(1, m):
            for j in range(1, n):
                if matrix[i][0] == 0 or matrix[0][j] == 0:
                    matrix[i][j] = 0
        if i0:
            for j in range(n):
                matrix[0][j] = 0
        if j0:
            for i in range(m):
                matrix[i][0] = 0
```

#### Java

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean i0 = false, j0 = false;
        for (int j = 0; j < n; ++j) {
            if (matrix[0][j] == 0) {
                i0 = true;
                break;
            }
        }
        for (int i = 0; i < m; ++i) {
            if (matrix[i][0] == 0) {
                j0 = true;
                break;
            }
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (i0) {
            for (int j = 0; j < n; ++j) {
                matrix[0][j] = 0;
            }
        }
        if (j0) {
            for (int i = 0; i < m; ++i) {
                matrix[i][0] = 0;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        bool i0 = false, j0 = false;
        for (int j = 0; j < n; ++j) {
            if (matrix[0][j] == 0) {
                i0 = true;
                break;
            }
        }
        for (int i = 0; i < m; ++i) {
            if (matrix[i][0] == 0) {
                j0 = true;
                break;
            }
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (i0) {
            for (int j = 0; j < n; ++j) {
                matrix[0][j] = 0;
            }
        }
        if (j0) {
            for (int i = 0; i < m; ++i) {
                matrix[i][0] = 0;
            }
        }
    }
};
```

#### Go

```go
func setZeroes(matrix [][]int) {
	m, n := len(matrix), len(matrix[0])
	i0, j0 := false, false
	for j := 0; j < n; j++ {
		if matrix[0][j] == 0 {
			i0 = true
			break
		}
	}
	for i := 0; i < m; i++ {
		if matrix[i][0] == 0 {
			j0 = true
			break
		}
	}
	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			if matrix[i][j] == 0 {
				matrix[i][0], matrix[0][j] = 0, 0
			}
		}
	}
	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			if matrix[i][0] == 0 || matrix[0][j] == 0 {
				matrix[i][j] = 0
			}
		}
	}
	if i0 {
		for j := 0; j < n; j++ {
			matrix[0][j] = 0
		}
	}
	if j0 {
		for i := 0; i < m; i++ {
			matrix[i][0] = 0
		}
	}
}
```

#### TypeScript

```ts
/**
 Do not return anything, modify matrix in-place instead.
 */
function setZeroes(matrix: number[][]): void {
    const m = matrix.length;
    const n = matrix[0].length;
    const i0 = matrix[0].includes(0);
    const j0 = matrix.map(row => row[0]).includes(0);
    for (let i = 1; i < m; ++i) {
        for (let j = 1; j < n; ++j) {
            if (matrix[i][j] === 0) {
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }
    for (let i = 1; i < m; ++i) {
        for (let j = 1; j < n; ++j) {
            if (matrix[i][0] === 0 || matrix[0][j] === 0) {
                matrix[i][j] = 0;
            }
        }
    }
    if (i0) {
        matrix[0].fill(0);
    }
    if (j0) {
        for (let i = 0; i < m; ++i) {
            matrix[i][0] = 0;
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} matrix
 * @return {void} Do not return anything, modify matrix in-place instead.
 */
var setZeroes = function (matrix) {
    const m = matrix.length;
    const n = matrix[0].length;
    let i0 = matrix[0].some(v => v == 0);
    let j0 = false;
    for (let i = 0; i < m; ++i) {
        if (matrix[i][0] == 0) {
            j0 = true;
            break;
        }
    }
    for (let i = 1; i < m; ++i) {
        for (let j = 1; j < n; ++j) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }
    for (let i = 1; i < m; ++i) {
        for (let j = 1; j < n; ++j) {
            if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0;
            }
        }
    }
    if (i0) {
        for (let j = 0; j < n; ++j) {
            matrix[0][j] = 0;
        }
    }
    if (j0) {
        for (let i = 0; i < m; ++i) {
            matrix[i][0] = 0;
        }
    }
};
```

#### C#

```cs
public class Solution {
    public void SetZeroes(int[][] matrix) {
        int m = matrix.Length, n = matrix[0].Length;
        bool i0 = matrix[0].Contains(0), j0 = false;
        for (int i = 0; i < m; ++i) {
            if (matrix[i][0] == 0) {
                j0 = true;
                break;
            }
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (i0) {
            for (int j = 0; j < n; ++j) {
                matrix[0][j] = 0;
            }
        }
        if (j0) {
            for (int i = 0; i < m; ++i) {
                matrix[i][0] = 0;
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
