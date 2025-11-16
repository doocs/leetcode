---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2536.Increment%20Submatrices%20by%20One/README.md
rating: 1583
source: 第 328 场周赛 Q2
tags:
    - 数组
    - 矩阵
    - 前缀和
---

<!-- problem:start -->

# [2536. 子矩阵元素加 1](https://leetcode.cn/problems/increment-submatrices-by-one)

[English Version](/solution/2500-2599/2536.Increment%20Submatrices%20by%20One/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数 <code>n</code> ，表示最初有一个 <code>n x n</code> 、下标从 <strong>0</strong> 开始的整数矩阵 <code>mat</code> ，矩阵中填满了 0 。</p>

<p>另给你一个二维整数数组 <code>query</code> 。针对每个查询 <code>query[i] = [row1<sub>i</sub>, col1<sub>i</sub>, row2<sub>i</sub>, col2<sub>i</sub>]</code> ，请你执行下述操作：</p>

<ul>
	<li>找出 <strong>左上角</strong> 为 <code>(row1<sub>i</sub>, col1<sub>i</sub>)</code> 且 <strong>右下角</strong> 为 <code>(row2<sub>i</sub>, col2<sub>i</sub>)</code> 的子矩阵，将子矩阵中的 <strong>每个元素</strong> 加 <code>1</code> 。也就是给所有满足 <code>row1<sub>i</sub> &lt;= x &lt;= row2<sub>i</sub></code> 和 <code>col1<sub>i</sub> &lt;= y &lt;= col2<sub>i</sub></code> 的 <code>mat[x][y]</code> 加 <code>1</code> 。</li>
</ul>

<p>返回执行完所有操作后得到的矩阵 <code>mat</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2536.Increment%20Submatrices%20by%20One/images/p2example11.png" style="width: 531px; height: 121px;" /></p>

<pre>
<strong>输入：</strong>n = 3, queries = [[1,1,2,2],[0,0,1,1]]
<strong>输出：</strong>[[1,1,0],[1,2,1],[0,1,1]]
<strong>解释：</strong>上图所展示的分别是：初始矩阵、执行完第一个操作后的矩阵、执行完第二个操作后的矩阵。
- 第一个操作：将左上角为 (1, 1) 且右下角为 (2, 2) 的子矩阵中的每个元素加 1 。 
- 第二个操作：将左上角为 (0, 0) 且右下角为 (1, 1) 的子矩阵中的每个元素加 1 。 
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2536.Increment%20Submatrices%20by%20One/images/p2example22.png" style="width: 261px; height: 82px;" /></p>

<pre>
<strong>输入：</strong>n = 2, queries = [[0,0,1,1]]
<strong>输出：</strong>[[1,1],[1,1]]
<strong>解释：</strong>上图所展示的分别是：初始矩阵、执行完第一个操作后的矩阵。 
- 第一个操作：将矩阵中的每个元素加 1 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= row1<sub>i</sub> &lt;= row2<sub>i</sub> &lt; n</code></li>
	<li><code>0 &lt;= col1<sub>i</sub> &lt;= col2<sub>i</sub> &lt; n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二维差分

二维差分数组是一种用于高效处理二维数组区间更新的技巧。我们可以通过维护一个与原矩阵大小相同的差分矩阵来实现对子矩阵的快速更新。

假设我们有一个二维差分矩阵 $\textit{diff}$，初始时所有元素均为 $0$。对于每个查询 $[\textit{row1}, \textit{col1}, \textit{row2}, \textit{col2}]$，我们可以通过以下步骤更新差分矩阵：

1. 在位置 $(\textit{row1}, \textit{col1})$ 加 $1$。
2. 在位置 $(\textit{row2} + 1, \textit{col1})$ 减 $1$，前提是 $\textit{row2} + 1 < n$。
3. 在位置 $(\textit{row1}, \textit{col2} + 1)$ 减 $1$，前提是 $\textit{col2} + 1 < n$。
4. 在位置 $(\textit{row2} + 1, \textit{col2} + 1)$ 加 $1$，前提是 $\textit{row2} + 1 < n$ 且 $\textit{col2} + 1 < n$。

完成所有查询后，我们需要通过前缀和的方式将差分矩阵转换回原矩阵。即，对于每个位置 $(i, j)$，我们计算：

$$
\textit{mat}[i][j] = \textit{diff}[i][j] + (\textit{mat}[i-1][j] \text{ if } i > 0 \text{ else } 0) + (\textit{mat}[i][j-1] \text{ if } j > 0 \text{ else } 0) - (\textit{mat}[i-1][j-1] \text{ if } i > 0 \text{ and } j > 0 \text{ else } 0)
$$

时间复杂度 $O(m + n^2)$，其中 $m$ 和 $n$ 分别是 $\textit{queries}$ 的长度和给定的 $n$。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rangeAddQueries(self, n: int, queries: List[List[int]]) -> List[List[int]]:
        mat = [[0] * n for _ in range(n)]
        for x1, y1, x2, y2 in queries:
            mat[x1][y1] += 1
            if x2 + 1 < n:
                mat[x2 + 1][y1] -= 1
            if y2 + 1 < n:
                mat[x1][y2 + 1] -= 1
            if x2 + 1 < n and y2 + 1 < n:
                mat[x2 + 1][y2 + 1] += 1

        for i in range(n):
            for j in range(n):
                if i:
                    mat[i][j] += mat[i - 1][j]
                if j:
                    mat[i][j] += mat[i][j - 1]
                if i and j:
                    mat[i][j] -= mat[i - 1][j - 1]
        return mat
```

#### Java

```java
class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] mat = new int[n][n];
        for (var q : queries) {
            int x1 = q[0], y1 = q[1], x2 = q[2], y2 = q[3];
            mat[x1][y1]++;
            if (x2 + 1 < n) {
                mat[x2 + 1][y1]--;
            }
            if (y2 + 1 < n) {
                mat[x1][y2 + 1]--;
            }
            if (x2 + 1 < n && y2 + 1 < n) {
                mat[x2 + 1][y2 + 1]++;
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i > 0) {
                    mat[i][j] += mat[i - 1][j];
                }
                if (j > 0) {
                    mat[i][j] += mat[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    mat[i][j] -= mat[i - 1][j - 1];
                }
            }
        }
        return mat;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> rangeAddQueries(int n, vector<vector<int>>& queries) {
        vector<vector<int>> mat(n, vector<int>(n));
        for (auto& q : queries) {
            int x1 = q[0], y1 = q[1], x2 = q[2], y2 = q[3];
            mat[x1][y1]++;
            if (x2 + 1 < n) {
                mat[x2 + 1][y1]--;
            }
            if (y2 + 1 < n) {
                mat[x1][y2 + 1]--;
            }
            if (x2 + 1 < n && y2 + 1 < n) {
                mat[x2 + 1][y2 + 1]++;
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i > 0) {
                    mat[i][j] += mat[i - 1][j];
                }
                if (j > 0) {
                    mat[i][j] += mat[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    mat[i][j] -= mat[i - 1][j - 1];
                }
            }
        }
        return mat;
    }
};
```

#### Go

```go
func rangeAddQueries(n int, queries [][]int) [][]int {
	mat := make([][]int, n)
	for i := range mat {
		mat[i] = make([]int, n)
	}
	for _, q := range queries {
		x1, y1, x2, y2 := q[0], q[1], q[2], q[3]
		mat[x1][y1]++
		if x2+1 < n {
			mat[x2+1][y1]--
		}
		if y2+1 < n {
			mat[x1][y2+1]--
		}
		if x2+1 < n && y2+1 < n {
			mat[x2+1][y2+1]++
		}
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if i > 0 {
				mat[i][j] += mat[i-1][j]
			}
			if j > 0 {
				mat[i][j] += mat[i][j-1]
			}
			if i > 0 && j > 0 {
				mat[i][j] -= mat[i-1][j-1]
			}
		}
	}
	return mat
}
```

#### TypeScript

```ts
function rangeAddQueries(n: number, queries: number[][]): number[][] {
    const mat: number[][] = Array.from({ length: n }, () => Array(n).fill(0));

    for (const [x1, y1, x2, y2] of queries) {
        mat[x1][y1] += 1;
        if (x2 + 1 < n) mat[x2 + 1][y1] -= 1;
        if (y2 + 1 < n) mat[x1][y2 + 1] -= 1;
        if (x2 + 1 < n && y2 + 1 < n) mat[x2 + 1][y2 + 1] += 1;
    }

    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i > 0) mat[i][j] += mat[i - 1][j];
            if (j > 0) mat[i][j] += mat[i][j - 1];
            if (i > 0 && j > 0) mat[i][j] -= mat[i - 1][j - 1];
        }
    }

    return mat;
}
```

#### Rust

```rust
impl Solution {
    pub fn range_add_queries(n: i32, queries: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let n = n as usize;
        let mut mat = vec![vec![0; n]; n];

        for q in queries {
            let (x1, y1, x2, y2) = (q[0] as usize, q[1] as usize, q[2] as usize, q[3] as usize);
            mat[x1][y1] += 1;
            if x2 + 1 < n {
                mat[x2 + 1][y1] -= 1;
            }
            if y2 + 1 < n {
                mat[x1][y2 + 1] -= 1;
            }
            if x2 + 1 < n && y2 + 1 < n {
                mat[x2 + 1][y2 + 1] += 1;
            }
        }

        for i in 0..n {
            for j in 0..n {
                if i > 0 {
                    mat[i][j] += mat[i - 1][j];
                }
                if j > 0 {
                    mat[i][j] += mat[i][j - 1];
                }
                if i > 0 && j > 0 {
                    mat[i][j] -= mat[i - 1][j - 1];
                }
            }
        }

        mat
    }
}
```

#### C#

```cs
public class Solution {
    public int[][] RangeAddQueries(int n, int[][] queries) {
        int[][] mat = new int[n][];
        for (int i = 0; i < n; i++) {
            mat[i] = new int[n];
        }

        foreach (var q in queries) {
            int x1 = q[0], y1 = q[1], x2 = q[2], y2 = q[3];

            mat[x1][y1]++;

            if (x2 + 1 < n) {
                mat[x2 + 1][y1]--;
            }
            if (y2 + 1 < n) {
                mat[x1][y2 + 1]--;
            }
            if (x2 + 1 < n && y2 + 1 < n) {
                mat[x2 + 1][y2 + 1]++;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    mat[i][j] += mat[i - 1][j];
                }
                if (j > 0) {
                    mat[i][j] += mat[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    mat[i][j] -= mat[i - 1][j - 1];
                }
            }
        }

        return mat;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
