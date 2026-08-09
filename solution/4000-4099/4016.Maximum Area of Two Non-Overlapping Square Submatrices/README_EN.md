---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4016.Maximum%20Area%20of%20Two%20Non-Overlapping%20Square%20Submatrices/README_EN.md
---

<!-- problem:start -->

# [4016. Maximum Area of Two Non-Overlapping Square Submatrices](https://leetcode.com/problems/maximum-area-of-two-non-overlapping-square-submatrices)

[中文文档](/solution/4000-4099/4016.Maximum%20Area%20of%20Two%20Non-Overlapping%20Square%20Submatrices/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer matrix <code>mat</code> of size <code>m &times; n</code>, where:</p>

<ul>
	<li><code>mat[r][c] == 1</code> means the cell at row <code>r</code> and column <code>c</code> is usable.</li>
	<li><code>mat[r][c] == 0</code> means it is not usable.</li>
</ul>

<p>Your task is to find <strong>two <span data-keyword="submatrix">submatrices</span></strong> that satisfy the following conditions:</p>

<ul>
	<li>Both submatrices must be squares of the same side length <code>k</code>.</li>
	<li>The two submatrices must not share any cell.</li>
	<li>Each submatrix can only cover cells where <code>mat[r][c] == 1</code>.</li>
</ul>

<p>Return the <strong>maximum possible area</strong> of each of the two squares. If it is not possible to choose two such squares, return 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4016.Maximum%20Area%20of%20Two%20Non-Overlapping%20Square%20Submatrices/images/image.png" style="width: 291px; height: 140px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">mat = [[1,1,1,0],[1,1,1,1],[0,0,1,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The largest equal non-overlapping squares have side length <code>k = 2</code> with area 4.</p>

<ul>
	<li>First square starts at top-left <code>(0, 0)</code> and covers cells <code>(0, 0)</code>, <code>(0, 1)</code>, <code>(1, 0)</code>, and <code>(1, 1)</code>.</li>
	<li>Second square starts at top-left <code>(1, 2)</code> and covers cells <code>(1, 2)</code>, <code>(1, 3)</code>, <code>(2, 2)</code>, and <code>(2, 3)</code>.</li>
</ul>

<p>Thus, the answer is 4.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4016.Maximum%20Area%20of%20Two%20Non-Overlapping%20Square%20Submatrices/images/screenshot-2026-06-13-at-83728pm.png" style="width: 155px; height: 130px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">mat = [[0,1],[1,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The largest equal non-overlapping squares have side length <code>k = 1</code> with area 1.</p>

<ul>
	<li>First square starts at top-left <code>(0, 1)</code> and covers cell <code>(0, 1)</code>.</li>
	<li>Second square starts at top-left <code>(1, 0)</code> and covers cell <code>(1, 0)</code>.</li>
</ul>

<p>Thus, the answer is 1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4016.Maximum%20Area%20of%20Two%20Non-Overlapping%20Square%20Submatrices/images/screenshot-2026-06-13-at-83751pm.png" style="width: 152px; height: 125px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">mat = [[0,0],[0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There is only one usable cell, so it is impossible to choose two non-overlapping squares. Thus, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>mat.length == m</code></li>
	<li><code>mat[i].length == n</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>mat[i][j]</code> is either 0 or 1.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming + Enumerating Dividing Lines

Two non-overlapping axis-aligned rectangles can always be separated by a horizontal line or a vertical line (their row intervals or column intervals must be disjoint). Therefore, we only need to consider the case where one square lies entirely above some horizontal dividing line and the other below it, and the case where one lies entirely to the left of some vertical dividing line and the other to its right. The latter can be handled by transposing the matrix and reusing the logic of the former.

For the horizontal case, we design a function $\textit{calc}(\textit{mat})$:

- Bottom-up dynamic programming: let $f[i][j]$ be the maximum side length of an all-$1$ square with top-left corner at $(i, j)$. If $\textit{mat}[i][j] = 1$, then $f[i][j] = \min(f[i+1][j], f[i][j+1], f[i+1][j+1]) + 1$. We use $g[i]$ to record the maximum side length in row $i$, then compute the suffix maximum $\textit{suf}[i] = \max(\textit{suf}[i+1], g[i])$, which represents the maximum side length of an all-$1$ square within rows $[i, m)$.
- Top-down dynamic programming: let $f[i][j]$ be the maximum side length of an all-$1$ square with bottom-right corner at $(i-1, j-1)$. If $\textit{mat}[i-1][j-1] = 1$, then $f[i][j] = \min(f[i-1][j], f[i][j-1], f[i-1][j-1]) + 1$. Similarly, we compute the prefix maximum $\textit{pre}[i] = \max(\textit{pre}[i-1], g[i])$, which represents the maximum side length of an all-$1$ square within rows $[0, i)$.
- Enumerate the dividing line $i \in [1, m)$ between every pair of adjacent rows. The maximum side length of an all-$1$ square above the line is $\textit{pre}[i]$, and below it is $\textit{suf}[i]$. Since the two squares must have equal side lengths, the feasible side length is $t = \min(\textit{pre}[i], \textit{suf}[i])$, and we update the answer with $t^2$.

Finally, return $\max(\textit{calc}(\textit{mat}), \textit{calc}(\textit{mat}^\top))$.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the matrix, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxArea(self, mat: list[list[int]]) -> int:
        def calc(mat: list[list[int]]) -> int:
            m, n = len(mat), len(mat[0])

            f = [[0] * (n + 1) for _ in range(m + 1)]
            g = [0] * (m + 1)
            suf = [0] * (m + 1)
            for i in range(m - 1, 0, -1):
                for j in range(n - 1, -1, -1):
                    if mat[i][j]:
                        f[i][j] = min(f[i + 1][j], f[i][j + 1], f[i + 1][j + 1]) + 1
                        g[i] = max(g[i], f[i][j])
                suf[i] = max(suf[i + 1], g[i])

            f = [[0] * (n + 1) for _ in range(m + 1)]
            g = [0] * (m + 1)
            pre = [0] * (m + 1)
            for i in range(1, m + 1):
                for j in range(1, n + 1):
                    if mat[i - 1][j - 1]:
                        f[i][j] = min(f[i - 1][j], f[i][j - 1], f[i - 1][j - 1]) + 1
                        g[i] = max(g[i], f[i][j])

                pre[i] = max(pre[i - 1], g[i])

            ans = 0
            for i in range(1, m):
                t = min(pre[i], suf[i])
                ans = max(ans, t * t)
            return ans

        def transpose(mat: list[list[int]]) -> list[list[int]]:
            m, n = len(mat), len(mat[0])
            ans = [[0] * m for _ in range(n)]
            for i in range(m):
                for j in range(n):
                    ans[j][i] = mat[i][j]
            return ans

        return max(calc(mat), calc(transpose(mat)))
```

#### Java

```java
class Solution {
    public int maxArea(int[][] mat) {
        return Math.max(calc(mat), calc(transpose(mat)));
    }

    private int calc(int[][] mat) {
        int m = mat.length, n = mat[0].length;

        int[][] f = new int[m + 1][n + 1];
        int[] g = new int[m + 1];
        int[] suf = new int[m + 1];

        for (int i = m - 1; i > 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (mat[i][j] != 0) {
                    f[i][j] = Math.min(Math.min(f[i + 1][j], f[i][j + 1]), f[i + 1][j + 1]) + 1;
                    g[i] = Math.max(g[i], f[i][j]);
                }
            }
            suf[i] = Math.max(suf[i + 1], g[i]);
        }

        f = new int[m + 1][n + 1];
        g = new int[m + 1];
        int[] pre = new int[m + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (mat[i - 1][j - 1] != 0) {
                    f[i][j] = Math.min(Math.min(f[i - 1][j], f[i][j - 1]), f[i - 1][j - 1]) + 1;
                    g[i] = Math.max(g[i], f[i][j]);
                }
            }
            pre[i] = Math.max(pre[i - 1], g[i]);
        }

        int ans = 0;
        for (int i = 1; i < m; i++) {
            int t = Math.min(pre[i], suf[i]);
            ans = Math.max(ans, t * t);
        }
        return ans;
    }

    private int[][] transpose(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] ans = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[j][i] = mat[i][j];
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
    int maxArea(vector<vector<int>>& mat) {
        return max(calc(mat), calc(transpose(mat)));
    }

private:
    int calc(const vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();

        vector<vector<int>> f(m + 1, vector<int>(n + 1));
        vector<int> g(m + 1), suf(m + 1);

        for (int i = m - 1; i > 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (mat[i][j]) {
                    f[i][j] = min({f[i + 1][j],
                                  f[i][j + 1],
                                  f[i + 1][j + 1]})
                        + 1;
                    g[i] = max(g[i], f[i][j]);
                }
            }
            suf[i] = max(suf[i + 1], g[i]);
        }

        f.assign(m + 1, vector<int>(n + 1));
        g.assign(m + 1, 0);
        vector<int> pre(m + 1);

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (mat[i - 1][j - 1]) {
                    f[i][j] = min({f[i - 1][j],
                                  f[i][j - 1],
                                  f[i - 1][j - 1]})
                        + 1;
                    g[i] = max(g[i], f[i][j]);
                }
            }
            pre[i] = max(pre[i - 1], g[i]);
        }

        int ans = 0;
        for (int i = 1; i < m; i++) {
            int t = min(pre[i], suf[i]);
            ans = max(ans, t * t);
        }

        return ans;
    }

    vector<vector<int>> transpose(const vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();

        vector<vector<int>> ans(n, vector<int>(m));

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[j][i] = mat[i][j];
            }
        }

        return ans;
    }
};
```

#### Go

```go
func maxArea(mat [][]int) int {
	return max(calc(mat), calc(transpose(mat)))
}

func calc(mat [][]int) int {
	m, n := len(mat), len(mat[0])

	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	g := make([]int, m+1)
	suf := make([]int, m+1)

	for i := m - 1; i > 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if mat[i][j] != 0 {
				f[i][j] = min(
					f[i+1][j],
					f[i][j+1],
					f[i+1][j+1],
				) + 1
				if f[i][j] > g[i] {
					g[i] = f[i][j]
				}
			}
		}
		suf[i] = max(suf[i+1], g[i])
	}

	f = make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	g = make([]int, m+1)
	pre := make([]int, m+1)

	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if mat[i-1][j-1] != 0 {
				f[i][j] = min(
					f[i-1][j],
					f[i][j-1],
					f[i-1][j-1],
				) + 1
				if f[i][j] > g[i] {
					g[i] = f[i][j]
				}
			}
		}
		pre[i] = max(pre[i-1], g[i])
	}

	ans := 0
	for i := 1; i < m; i++ {
		t := min(pre[i], suf[i])
		if t*t > ans {
			ans = t * t
		}
	}
	return ans
}

func transpose(mat [][]int) [][]int {
	m, n := len(mat), len(mat[0])
	ans := make([][]int, n)
	for i := range ans {
		ans[i] = make([]int, m)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			ans[j][i] = mat[i][j]
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxArea(mat: number[][]): number {
    return Math.max(calc(mat), calc(transpose(mat)));
}

function calc(mat: number[][]): number {
    const m = mat.length;
    const n = mat[0].length;

    let f = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    let g = Array(m + 1).fill(0);
    let suf = Array(m + 1).fill(0);

    for (let i = m - 1; i > 0; i--) {
        for (let j = n - 1; j >= 0; j--) {
            if (mat[i][j]) {
                f[i][j] = Math.min(f[i + 1][j], f[i][j + 1], f[i + 1][j + 1]) + 1;
                g[i] = Math.max(g[i], f[i][j]);
            }
        }
        suf[i] = Math.max(suf[i + 1], g[i]);
    }

    f = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    g = Array(m + 1).fill(0);
    const pre = Array(m + 1).fill(0);

    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (mat[i - 1][j - 1]) {
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1], f[i - 1][j - 1]) + 1;
                g[i] = Math.max(g[i], f[i][j]);
            }
        }
        pre[i] = Math.max(pre[i - 1], g[i]);
    }

    let ans = 0;
    for (let i = 1; i < m; i++) {
        const t = Math.min(pre[i], suf[i]);
        ans = Math.max(ans, t * t);
    }
    return ans;
}

function transpose(mat: number[][]): number[][] {
    const m = mat.length;
    const n = mat[0].length;

    const ans = Array.from({ length: n }, () => Array(m).fill(0));

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            ans[j][i] = mat[i][j];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
