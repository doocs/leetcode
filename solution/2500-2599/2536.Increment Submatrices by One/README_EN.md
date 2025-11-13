---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2536.Increment%20Submatrices%20by%20One/README_EN.md
rating: 1583
source: Weekly Contest 328 Q2
tags:
    - Array
    - Matrix
    - Prefix Sum
---

<!-- problem:start -->

# [2536. Increment Submatrices by One](https://leetcode.com/problems/increment-submatrices-by-one)

[中文文档](/solution/2500-2599/2536.Increment%20Submatrices%20by%20One/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer <code>n</code>, indicating that we initially have an <code>n x n</code>&nbsp;<strong>0-indexed</strong> integer matrix <code>mat</code> filled with zeroes.</p>

<p>You are also given a 2D integer array <code>query</code>. For each <code>query[i] = [row1<sub>i</sub>, col1<sub>i</sub>, row2<sub>i</sub>, col2<sub>i</sub>]</code>, you should do the following operation:</p>

<ul>
	<li>Add <code>1</code> to <strong>every element</strong> in the submatrix with the <strong>top left</strong> corner <code>(row1<sub>i</sub>, col1<sub>i</sub>)</code> and the <strong>bottom right</strong> corner <code>(row2<sub>i</sub>, col2<sub>i</sub>)</code>. That is, add <code>1</code> to <code>mat[x][y]</code> for all <code>row1<sub>i</sub> &lt;= x &lt;= row2<sub>i</sub></code> and <code>col1<sub>i</sub> &lt;= y &lt;= col2<sub>i</sub></code>.</li>
</ul>

<p>Return<em> the matrix</em> <code>mat</code><em> after performing every query.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2536.Increment%20Submatrices%20by%20One/images/p2example11.png" style="width: 531px; height: 121px;" />
<pre>
<strong>Input:</strong> n = 3, queries = [[1,1,2,2],[0,0,1,1]]
<strong>Output:</strong> [[1,1,0],[1,2,1],[0,1,1]]
<strong>Explanation:</strong> The diagram above shows the initial matrix, the matrix after the first query, and the matrix after the second query.
- In the first query, we add 1 to every element in the submatrix with the top left corner (1, 1) and bottom right corner (2, 2).
- In the second query, we add 1 to every element in the submatrix with the top left corner (0, 0) and bottom right corner (1, 1).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2536.Increment%20Submatrices%20by%20One/images/p2example22.png" style="width: 261px; height: 82px;" />
<pre>
<strong>Input:</strong> n = 2, queries = [[0,0,1,1]]
<strong>Output:</strong> [[1,1],[1,1]]
<strong>Explanation:</strong> The diagram above shows the initial matrix and the matrix after the first query.
- In the first query we add 1 to every element in the matrix.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= row1<sub>i</sub> &lt;= row2<sub>i</sub> &lt; n</code></li>
	<li><code>0 &lt;= col1<sub>i</sub> &lt;= col2<sub>i</sub> &lt; n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: 2D Difference Array

A 2D difference array is a technique used to efficiently handle range updates on 2D arrays. We can implement fast updates on submatrices by maintaining a difference matrix of the same size as the original matrix.

Suppose we have a 2D difference matrix $\textit{diff}$, initially with all elements set to $0$. For each query $[\textit{row1}, \textit{col1}, \textit{row2}, \textit{col2}]$, we can update the difference matrix through the following steps:

1. Increment position $(\textit{row1}, \textit{col1})$ by $1$.
2. Decrement position $(\textit{row2} + 1, \textit{col1})$ by $1$, provided that $\textit{row2} + 1 < n$.
3. Decrement position $(\textit{row1}, \textit{col2} + 1)$ by $1$, provided that $\textit{col2} + 1 < n$.
4. Increment position $(\textit{row2} + 1, \textit{col2} + 1)$ by $1$, provided that $\textit{row2} + 1 < n$ and $\textit{col2} + 1 < n$.

After completing all queries, we need to convert the difference matrix back to the original matrix using prefix sums. That is, for each position $(i, j)$, we calculate:

$$
\textit{mat}[i][j] = \textit{diff}[i][j] + (\textit{mat}[i-1][j] \text{ if } i > 0 \text{ else } 0) + (\textit{mat}[i][j-1] \text{ if } j > 0 \text{ else } 0) - (\textit{mat}[i-1][j-1] \text{ if } i > 0 \text{ and } j > 0 \text{ else } 0)
$$

The time complexity is $O(m + n^2)$, where $m$ and $n$ are the length of $\textit{queries}$ and the given $n$, respectively. Ignoring the space consumed by the answer, the space complexity is $O(1)$.

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
