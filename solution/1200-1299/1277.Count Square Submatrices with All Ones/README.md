---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1277.Count%20Square%20Submatrices%20with%20All%20Ones/README.md
rating: 1613
source: 第 165 场周赛 Q3
tags:
    - 数组
    - 动态规划
    - 矩阵
---

<!-- problem:start -->

# [1277. 统计全为 1 的正方形子矩阵](https://leetcode.cn/problems/count-square-submatrices-with-all-ones)

[English Version](/solution/1200-1299/1277.Count%20Square%20Submatrices%20with%20All%20Ones/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<code>m * n</code>&nbsp;的矩阵，矩阵中的元素不是 <code>0</code> 就是 <code>1</code>，请你统计并返回其中完全由 <code>1</code> 组成的 <strong>正方形</strong> 子矩阵的个数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>matrix =
[
&nbsp; [0,1,1,1],
&nbsp; [1,1,1,1],
&nbsp; [0,1,1,1]
]
<strong>输出：</strong>15
<strong>解释：</strong> 
边长为 1 的正方形有 <strong>10</strong> 个。
边长为 2 的正方形有 <strong>4</strong> 个。
边长为 3 的正方形有 <strong>1</strong> 个。
正方形的总数 = 10 + 4 + 1 = <strong>15</strong>.
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>matrix = 
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
<strong>输出：</strong>7
<strong>解释：</strong>
边长为 1 的正方形有 <strong>6</strong> 个。 
边长为 2 的正方形有 <strong>1</strong> 个。
正方形的总数 = 6 + 1 = <strong>7</strong>.
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length&nbsp;&lt;= 300</code></li>
	<li><code>1 &lt;= arr[0].length&nbsp;&lt;= 300</code></li>
	<li><code>0 &lt;= arr[i][j] &lt;= 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 为以 $(i,j)$ 为右下角的正方形子矩阵的边长，初始时 $f[i][j] = 0$，答案为 $\sum_{i,j} f[i][j]$。

考虑 $f[i][j]$ 如何进行状态转移。

-   当 $\text{matrix}[i][j] = 0$ 时，有 $f[i][j] = 0$。
-   当 $\text{matrix}[i][j] = 1$ 时，状态 $f[i][j]$ 的值取决于其上、左、左上三个位置的值：
    -   如果 $i = 0$ 或 $j = 0$，则 $f[i][j] = 1$。
    -   否则 $f[i][j] = \min(f[i-1][j-1], f[i-1][j], f[i][j-1]) + 1$。

答案为 $\sum_{i,j} f[i][j]$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSquares(self, matrix: List[List[int]]) -> int:
        m, n = len(matrix), len(matrix[0])
        f = [[0] * n for _ in range(m)]
        ans = 0
        for i, row in enumerate(matrix):
            for j, v in enumerate(row):
                if v == 0:
                    continue
                if i == 0 or j == 0:
                    f[i][j] = 1
                else:
                    f[i][j] = min(f[i - 1][j - 1], f[i - 1][j], f[i][j - 1]) + 1
                ans += f[i][j]
        return ans
```

#### Java

```java
class Solution {
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] f = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                if (i == 0 || j == 0) {
                    f[i][j] = 1;
                } else {
                    f[i][j] = Math.min(f[i - 1][j - 1], Math.min(f[i - 1][j], f[i][j - 1])) + 1;
                }
                ans += f[i][j];
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
    int countSquares(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        int ans = 0;
        vector<vector<int>> f(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                if (i == 0 || j == 0) {
                    f[i][j] = 1;
                } else {
                    f[i][j] = min(f[i - 1][j - 1], min(f[i - 1][j], f[i][j - 1])) + 1;
                }
                ans += f[i][j];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countSquares(matrix [][]int) int {
	m, n, ans := len(matrix), len(matrix[0]), 0
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	for i, row := range matrix {
		for j, v := range row {
			if v == 0 {
				continue
			}
			if i == 0 || j == 0 {
				f[i][j] = 1
			} else {
				f[i][j] = min(f[i-1][j-1], min(f[i-1][j], f[i][j-1])) + 1
			}
			ans += f[i][j]
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countSquares(matrix: number[][]): number {
    const m = matrix.length;
    const n = matrix[0].length;
    const f: number[][] = Array.from({ length: m }, () => Array(n).fill(0));
    let ans = 0;

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (matrix[i][j] === 0) {
                continue;
            }
            if (i === 0 || j === 0) {
                f[i][j] = 1;
            } else {
                f[i][j] = Math.min(f[i - 1][j - 1], Math.min(f[i - 1][j], f[i][j - 1])) + 1;
            }
            ans += f[i][j];
        }
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_squares(matrix: Vec<Vec<i32>>) -> i32 {
        let m = matrix.len();
        let n = matrix[0].len();
        let mut f = vec![vec![0; n]; m];
        let mut ans = 0;

        for i in 0..m {
            for j in 0..n {
                if matrix[i][j] == 0 {
                    continue;
                }
                if i == 0 || j == 0 {
                    f[i][j] = 1;
                } else {
                    f[i][j] = std::cmp::min(f[i - 1][j - 1], std::cmp::min(f[i - 1][j], f[i][j - 1])) + 1;
                }
                ans += f[i][j];
            }
        }

        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} matrix
 * @return {number}
 */
var countSquares = function (matrix) {
    const m = matrix.length;
    const n = matrix[0].length;
    const f = Array.from({ length: m }, () => Array(n).fill(0));
    let ans = 0;

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (matrix[i][j] === 0) {
                continue;
            }
            if (i === 0 || j === 0) {
                f[i][j] = 1;
            } else {
                f[i][j] = Math.min(f[i - 1][j - 1], Math.min(f[i - 1][j], f[i][j - 1])) + 1;
            }
            ans += f[i][j];
        }
    }

    return ans;
};
```

#### C#

```cs
public class Solution {
    public int CountSquares(int[][] matrix) {
        int m = matrix.Length;
        int n = matrix[0].Length;
        int[,] f = new int[m, n];
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                if (i == 0 || j == 0) {
                    f[i, j] = 1;
                } else {
                    f[i, j] = Math.Min(f[i - 1, j - 1], Math.Min(f[i - 1, j], f[i, j - 1])) + 1;
                }
                ans += f[i, j];
            }
        }

        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
