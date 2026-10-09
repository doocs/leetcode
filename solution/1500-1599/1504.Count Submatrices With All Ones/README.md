---
comments: true
difficulty: 中等
rating: 1845
source: 第 196 场周赛 Q3
tags:
    - 栈
    - 数组
    - 动态规划
    - 矩阵
    - 单调栈
---

<!-- problem:start -->

# [1504. 统计全 1 子矩形](https://leetcode.cn/problems/count-submatrices-with-all-ones)

[English Version](/solution/1500-1599/1504.Count%20Submatrices%20With%20All%20Ones/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的二进制矩阵&nbsp;<code>mat</code>&nbsp;，请你返回有多少个&nbsp;<strong>子矩形</strong>&nbsp;的元素全部都是 1 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1504.Count%20Submatrices%20With%20All%20Ones/images/ones1-grid.jpg" /></p>

<pre>
<strong>输入：</strong>mat = [[1,0,1],[1,1,0],[1,1,0]]
<strong>输出：</strong>13
<strong>解释：
</strong>有 <strong>6</strong>&nbsp;个 1x1 的矩形。
有 <strong>2</strong> 个 1x2 的矩形。
有 <strong>3</strong> 个 2x1 的矩形。
有 <strong>1</strong> 个 2x2 的矩形。
有 <strong>1</strong> 个 3x1 的矩形。
矩形数目总共 = 6 + 2 + 3 + 1 + 1 = <strong>13</strong>&nbsp;。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1504.Count%20Submatrices%20With%20All%20Ones/images/ones2-grid.jpg" /></p>

<pre>
<strong>输入：</strong>mat = [[0,1,1,0],[0,1,1,1],[1,1,1,0]]
<strong>输出：</strong>24
<strong>解释：</strong>
有 <strong>8</strong> 个 1x1 的子矩形。
有 <strong>5</strong> 个 1x2 的子矩形。
有 <strong>2</strong> 个 1x3 的子矩形。
有 <strong>4</strong> 个 2x1 的子矩形。
有 <strong>2</strong> 个 2x2 的子矩形。
有 <strong>2</strong> 个 3x1 的子矩形。
有 <strong>1</strong> 个 3x2 的子矩形。
矩形数目总共 = 8 + 5 + 2 + 4 + 2 + 2 + 1 = <strong>24</strong><strong> 。</strong>

</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 150</code></li>
	<li><code>mat[i][j]</code>&nbsp;仅包含&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举 + 前缀和

<!-- thinking:start -->

> **思考**
>
> 统计全 $1$ 子矩阵的个数，若枚举四个边界再检查内部，时间可达 $O(m^2 n^2\cdot mn)$。即便改为枚举右下角与左上角后用二维前缀和判断，仍是 $O(m^2 n^2)$。矩阵边长可达 $150$，需要再降一维。
>
> 固定右下角 $(i,j)$ 后，子矩阵由向上延伸的高度决定。先对每一行预处理「以该格为右端的连续 $1$ 宽度」 $g[i][j]$，再从 $i$ 向上枚举上边界 $k$，宽度取沿途 $g[k][j]$ 的最小值，每升高一行就累加当前宽度。这样每个右下角用 $O(m)$ 完成统计，总时间 $O(m^2 n)$。

<!-- thinking:end -->

我们可以枚举矩阵的右下角 $(i, j)$，然后向上枚举矩阵的第一行 $k$，那么每一行以 $(i, j)$ 为右下角的矩阵的宽度就是 $\min_{k \leq i} \textit{g}[k][j]$，其中 $\textit{g}[k][j]$ 表示第 $k$ 行以 $(k, j)$ 为右下角的矩阵的宽度。

因此，我们可以预处理得到二维数组 $g[i][j]$，其中 $g[i][j]$ 表示第 $i$ 行中，从第 $j$ 列向左连续的 $1$ 的个数。

时间复杂度 $O(m^2 \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numSubmat(self, mat: List[List[int]]) -> int:
        m, n = len(mat), len(mat[0])
        g = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                if mat[i][j]:
                    g[i][j] = 1 if j == 0 else 1 + g[i][j - 1]
        ans = 0
        for i in range(m):
            for j in range(n):
                col = inf
                for k in range(i, -1, -1):
                    col = min(col, g[k][j])
                    ans += col
        return ans
```

#### Java

```java
class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] g = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 1) {
                    g[i][j] = j == 0 ? 1 : 1 + g[i][j - 1];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int col = 1 << 30;
                for (int k = i; k >= 0 && col > 0; --k) {
                    col = Math.min(col, g[k][j]);
                    ans += col;
                }
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
    int numSubmat(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<int>> g(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 1) {
                    g[i][j] = j == 0 ? 1 : 1 + g[i][j - 1];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int col = 1 << 30;
                for (int k = i; k >= 0 && col > 0; --k) {
                    col = min(col, g[k][j]);
                    ans += col;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numSubmat(mat [][]int) (ans int) {
	m, n := len(mat), len(mat[0])
	g := make([][]int, m)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			if mat[i][j] == 1 {
				if j == 0 {
					g[i][j] = 1
				} else {
					g[i][j] = 1 + g[i][j-1]
				}
			}
		}
	}
	for i := range g {
		for j := range g[i] {
			col := 1 << 30
			for k := i; k >= 0 && col > 0; k-- {
				col = min(col, g[k][j])
				ans += col
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function numSubmat(mat: number[][]): number {
    const m = mat.length;
    const n = mat[0].length;
    const g: number[][] = Array.from({ length: m }, () => Array(n).fill(0));

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (mat[i][j]) {
                g[i][j] = j === 0 ? 1 : 1 + g[i][j - 1];
            }
        }
    }

    let ans = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            let col = Infinity;
            for (let k = i; k >= 0; k--) {
                col = Math.min(col, g[k][j]);
                ans += col;
            }
        }
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn num_submat(mat: Vec<Vec<i32>>) -> i32 {
        let m = mat.len();
        let n = mat[0].len();
        let mut g = vec![vec![0; n]; m];

        for i in 0..m {
            for j in 0..n {
                if mat[i][j] == 1 {
                    if j == 0 {
                        g[i][j] = 1;
                    } else {
                        g[i][j] = 1 + g[i][j - 1];
                    }
                }
            }
        }

        let mut ans = 0;
        for i in 0..m {
            for j in 0..n {
                let mut col = i32::MAX;
                let mut k = i as i32;
                while k >= 0 && col > 0 {
                    col = col.min(g[k as usize][j]);
                    ans += col;
                    k -= 1;
                }
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} mat
 * @return {number}
 */
var numSubmat = function (mat) {
    const m = mat.length;
    const n = mat[0].length;
    const g = Array.from({ length: m }, () => Array(n).fill(0));

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (mat[i][j]) {
                g[i][j] = j === 0 ? 1 : 1 + g[i][j - 1];
            }
        }
    }

    let ans = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            let col = Infinity;
            for (let k = i; k >= 0; k--) {
                col = Math.min(col, g[k][j]);
                ans += col;
            }
        }
    }

    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：前缀和 + 单调栈

<!-- thinking:start -->

方法一的代码在双层循环中，是从当前行向上枚举，计算每一行的最小宽度并累加：

```
for k in range(i, -1, -1):
    col = min(col, g[k][j])
    ans += col
```

由此可见单调性：若 `g[k - 1][j] <= g[k][j]`，则 `g[k - 1][j]` 会限制上方所有行的宽度，它们的贡献都会被 `g[k - 1][j]` 限制。

于是我们可对每一列 `j` 各维护一个栈，栈内 `table` 值**严格递增**（`>=` 的元素会被弹出）。

处理 `(i, j)` 时，弹出所有 `table >= 当前值` 的元素，栈顶 `prev` 就是向上第一个**严格小于**当前值的位置。于是分成两段：

- **第 `prev + 1` 到 `i` 行**：这段的值都 `>= table[i][j]`，最小值就是 `table[i][j]` 本身，贡献 `table[i][j] × (i - prev)`。
- **第 `prev` 行及以上**：这段的最小值会被 `table[prev][j]` 压住，而 `table[prev][j] < table[i][j]` 且小于等于中间所有值，所以结果与以 `(prev, j)` 为右下角时**完全相同**，直接继承 `prev` 的 `cumulated_count`。

因此若 `prev` 存在的话：

```
cumulated_count(i, j) = cumulated_count(prev, j) + table[i][j] × (i - prev)
```

反之，若 `prev` 不存在，也就是 `prev` 为-1时：

```
cumulated_count(i, j) = table[i][j] × (i - prev) = table[i][j] × (i + 1)
```

每个格子**恰好 push 一次**，且**至多 pop 一次**。

所以 `while` 循环的总执行次数最多为 `m·n`，均摊后每个格子 O(1)：

| | 复杂度 |
|---|---|
| 时间 | O(m·n) |
| 空间 | O(m·n)（`table` 与各列的栈） |

外层双循环本身就是 `m·n` 次，而内层 `while` 的弹出次数被 push 总数限制住，因此不会让复杂度退化成 O(m²·n)。

<!-- tabs:start -->

#### Python3

```python
from typing import NamedTuple


class Tuple(NamedTuple):
    entry: int
    row_idx: int
    cumulated_count: int


class Solution:
    def numSubmat(self, mat: list[list[int]]) -> int:
        rows = len(mat)
        cols = len(mat[0])

        table = [[0] * cols for _ in range(rows)]

        # `stack[j]`: each jth column's remaining tuples:
        # (table entry, row idx, cumulated count until [i][j]).
        stack: list[list[Tuple]] = [[] for _ in range(cols)]

        submatrices_count = 0

        for row_idx in range(rows):
            for col_idx in range(cols):
                if mat[row_idx][col_idx] == 1:
                    table[row_idx][col_idx] = 1
                    if col_idx > 0:
                        table[row_idx][col_idx] += table[row_idx][col_idx - 1]

                current_entry = table[row_idx][col_idx]

                while stack[col_idx] and stack[col_idx][-1].entry >= current_entry:
                    stack[col_idx].pop(-1)

                cumulated_count = 0
                prev_row_idx = -1

                if stack[col_idx]:
                    prev_row_idx = stack[col_idx][-1].row_idx  # Previous barrier.

                    cumulated_count += stack[col_idx][
                        -1
                    ].cumulated_count  # Inheritance.

                cumulated_count += current_entry * (row_idx - prev_row_idx)
                submatrices_count += cumulated_count

                stack[col_idx].append(Tuple(current_entry, row_idx, cumulated_count))

        return submatrices_count
```

#### C++

```cpp
struct Tuple {
    int entry;
    int rowIdx;
    int cumulatedCount;
};

class Solution {
public:
    int numSubmat(vector<vector<int>>& mat) {
        int rows = mat.size();
        int cols = mat[0].size();

        vector<vector<int>> table(rows, vector<int>(cols, 0));

        // `stacks[j]`: each jth column's remaining tuples:
        // {table entry, row idx, cumulated count until [i][j]}.
        vector<stack<Tuple>> stacks(cols);

        int submatricesCount = 0;

        for (int rowIdx = 0; rowIdx < rows; rowIdx++) {
            for (int colIdx = 0; colIdx < cols; colIdx++) {
                if (mat[rowIdx][colIdx] == 1) {
                    table[rowIdx][colIdx] = 1;
                    if (colIdx > 0)
                        table[rowIdx][colIdx] += table[rowIdx][colIdx - 1];
                }

                int currentEntry = table[rowIdx][colIdx];

                while (!stacks[colIdx].empty() && stacks[colIdx].top().entry >= currentEntry)
                    stacks[colIdx].pop();

                int cumulatedCount = 0;
                int prevRowIdx = -1;

                if (!stacks[colIdx].empty()) {
                    prevRowIdx = stacks[colIdx].top().rowIdx; // Previous barrier.
                    cumulatedCount += stacks[colIdx].top().cumulatedCount; // Inheritance.
                }

                cumulatedCount += currentEntry * (rowIdx - prevRowIdx);
                submatricesCount += cumulatedCount;

                stacks[colIdx].push({currentEntry, rowIdx, cumulatedCount});
            }
        }

        return submatricesCount;
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
