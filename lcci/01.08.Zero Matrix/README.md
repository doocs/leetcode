# [面试题 01.08. 零矩阵](https://leetcode.cn/problems/zero-matrix-lcci)

[English Version](/lcci/01.08.Zero%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
<strong>输出：</strong>
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
<strong>输出：</strong>
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数组标记**

我们分别用数组 `rows` 和 `cols` 标记待清零的行和列。

然后再遍历一遍矩阵，将 `rows` 和 `cols` 中标记的行和列对应的元素清零。

时间复杂度 $O(m\times n)$，空间复杂度 $O(m+n)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

**方法二：原地标记**

方法一中使用了额外的数组标记待清零的行和列，实际上我们也可以直接用矩阵的第一行和第一列来标记，不需要开辟额外的数组空间。

由于第一行、第一列用来做标记，它们的值可能会因为标记而发生改变，因此，我们需要额外的变量 $i0$, $j0$ 来标记第一行、第一列是否需要被清零。

时间复杂度 $O(m\times n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        m, n = len(matrix), len(matrix[0])
        rows = [0] * m
        cols = [0] * n
        for i, row in enumerate(matrix):
            for j, v in enumerate(row):
                if v == 0:
                    rows[i] = cols[j] = 1
        for i in range(m):
            for j in range(n):
                if rows[i] or cols[j]:
                    matrix[i][j] = 0
```

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rows[i] || cols[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
```

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

### **C++**

```cpp
class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        vector<bool> rows(m);
        vector<bool> cols(n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!matrix[i][j]) {
                    rows[i] = 1;
                    cols[j] = 1;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rows[i] || cols[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
};
```

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

### **Go**

```go
func setZeroes(matrix [][]int) {
	m, n := len(matrix), len(matrix[0])
	rows := make([]bool, m)
	cols := make([]bool, n)
	for i, row := range matrix {
		for j, v := range row {
			if v == 0 {
				rows[i] = true
				cols[j] = true
			}
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if rows[i] || cols[j] {
				matrix[i][j] = 0
			}
		}
	}
}
```

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

### **JavaScript**

```js
/**
 * @param {number[][]} matrix
 * @return {void} Do not return anything, modify matrix in-place instead.
 */
var setZeroes = function (matrix) {
    const m = matrix.length;
    const n = matrix[0].length;
    const rows = new Array(m).fill(false);
    const cols = new Array(n).fill(false);
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (matrix[i][j] == 0) {
                rows[i] = true;
                cols[j] = true;
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (rows[i] || cols[j]) {
                matrix[i][j] = 0;
            }
        }
    }
};
```

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

### **C**

```c
void setZeroes(int **matrix, int matrixSize, int *matrixColSize) {
    int m = matrixSize;
    int n = matrixColSize[0];
    int *rows = (int *) malloc(sizeof(int) * m);
    int *cols = (int *) malloc(sizeof(int) * n);
    memset(rows, 0, sizeof(int) * m);
    memset(cols, 0, sizeof(int) * n);
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; ++j) {
            if (matrix[i][j] == 0) {
                rows[i] = 1;
                cols[j] = 1;
            }
        }
    }
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; ++j) {
            if (rows[i] || cols[j]) {
                matrix[i][j] = 0;
            }
        }
    }
    free(rows);
    free(cols);
}
```

```c
void setZeroes(int **matrix, int matrixSize, int *matrixColSize) {
    int m = matrixSize;
    int n = matrixColSize[0];
    int l0 = 0;
    int r0 = 0;
    for (int i = 0; i < m; i++) {
        if (matrix[i][0] == 0) {
            l0 = 1;
            break;
        }
    }
    for (int j = 0; j < n; j++) {
        if (matrix[0][j] == 0) {
            r0 = 1;
            break;
        }
    }
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0;
            }
        }
    }
    if (l0) {
        for (int i = 0; i < m; i++) {
            matrix[i][0] = 0;
        }
    }
    if (r0) {
        for (int j = 0; j < n; j++) {
            matrix[0][j] = 0;
        }
    }
}
```

### **TypeScript**

```ts
/**
 Do not return anything, modify matrix in-place instead.
 */
function setZeroes(matrix: number[][]): void {
    const m = matrix.length;
    const n = matrix[0].length;
    const rows = new Array(m).fill(false);
    const cols = new Array(n).fill(false);
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (matrix[i][j] === 0) {
                rows[i] = true;
                cols[j] = true;
            }
        }
    }
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (rows[i] || cols[j]) {
                matrix[i][j] = 0;
            }
        }
    }
}
```

```ts
/**
 Do not return anything, modify matrix in-place instead.
 */
function setZeroes(matrix: number[][]): void {
    const m = matrix.length;
    const n = matrix[0].length;
    let l0 = false;
    let r0 = false;
    for (let i = 0; i < m; i++) {
        if (matrix[i][0] === 0) {
            l0 = true;
            break;
        }
    }
    for (let j = 0; j < n; j++) {
        if (matrix[0][j] === 0) {
            r0 = true;
            break;
        }
    }
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (matrix[i][j] === 0) {
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }
    for (let i = 1; i < m; i++) {
        for (let j = 1; j < n; j++) {
            if (matrix[i][0] === 0 || matrix[0][j] === 0) {
                matrix[i][j] = 0;
            }
        }
    }
    if (l0) {
        for (let i = 0; i < m; i++) {
            matrix[i][0] = 0;
        }
    }
    if (r0) {
        for (let j = 0; j < n; j++) {
            matrix[0][j] = 0;
        }
    }
}
```

### **Rust**

```rust
impl Solution {
    pub fn set_zeroes(matrix: &mut Vec<Vec<i32>>) {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut rows = vec![false; m];
        let mut cols = vec![false; n];
        for i in 0..m {
            for j in 0..n {
                if matrix[i][j] == 0 {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }
        for i in 0..m {
            for j in 0..n {
                if rows[i] || cols[j] {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
```

```rust
impl Solution {
    pub fn set_zeroes(matrix: &mut Vec<Vec<i32>>) {
        let m = matrix.len();
        let n = matrix[0].len();
        let l0 = {
            let mut res = false;
            for j in 0..n {
                if matrix[0][j] == 0 {
                    res = true;
                    break;
                }
            }
            res
        };
        let r0 = {
            let mut res = false;
            for i in 0..m {
                if matrix[i][0] == 0 {
                    res = true;
                    break;
                }
            }
            res
        };
        for i in 0..m {
            for j in 0..n {
                if matrix[i][j] == 0 {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for i in 1..m {
            for j in 1..n {
                if matrix[i][0] == 0 || matrix[0][j] == 0 {
                    matrix[i][j] = 0;
                }
            }
        }
        if l0 {
            for j in 0..n {
                matrix[0][j] = 0;
            }
        }
        if r0 {
            for i in 0..m {
                matrix[i][0] = 0;
            }
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
