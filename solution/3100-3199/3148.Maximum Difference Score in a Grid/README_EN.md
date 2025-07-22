---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3148.Maximum%20Difference%20Score%20in%20a%20Grid/README_EN.md
rating: 1819
source: Weekly Contest 397 Q3
tags:
    - Array
    - Dynamic Programming
    - Matrix
---

<!-- problem:start -->

# [3148. Maximum Difference Score in a Grid](https://leetcode.com/problems/maximum-difference-score-in-a-grid)

[中文文档](/solution/3100-3199/3148.Maximum%20Difference%20Score%20in%20a%20Grid/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> matrix <code>grid</code> consisting of <strong>positive</strong> integers. You can move from a cell in the matrix to <strong>any</strong> other cell that is either to the bottom or to the right (not necessarily adjacent). The score of a move from a cell with the value <code>c1</code> to a cell with the value <code>c2</code> is <code>c2 - c1</code>.<!-- notionvc: 8819ca04-8606-4ecf-815b-fb77bc63b851 --></p>

<p>You can start at <strong>any</strong> cell, and you have to make <strong>at least</strong> one move.</p>

<p>Return the <strong>maximum</strong> total score you can achieve.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3148.Maximum%20Difference%20Score%20in%20a%20Grid/images/grid1.png" style="width: 240px; height: 240px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[9,5,7,3],[8,9,6,1],[6,7,14,3],[2,5,3,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong> We start at the cell <code>(0, 1)</code>, and we perform the following moves:<br />
- Move from the cell <code>(0, 1)</code> to <code>(2, 1)</code> with a score of <code>7 - 5 = 2</code>.<br />
- Move from the cell <code>(2, 1)</code> to <code>(2, 2)</code> with a score of <code>14 - 7 = 7</code>.<br />
The total score is <code>2 + 7 = 9</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3148.Maximum%20Difference%20Score%20in%20a%20Grid/images/moregridsdrawio-1.png" style="width: 180px; height: 116px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[4,3,2],[3,2,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong> We start at the cell <code>(0, 0)</code>, and we perform one move: <code>(0, 0)</code> to <code>(0, 1)</code>. The score is <code>3 - 4 = -1</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 1000</code></li>
	<li><code>4 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

According to the problem description, if the values of the cells we pass through are $c_1, c_2, \cdots, c_k$, then our score is $c_2 - c_1 + c_3 - c_2 + \cdots + c_k - c_{k-1} = c_k - c_1$. Therefore, the problem is transformed into: for each cell $(i, j)$ of the matrix, if we take it as the endpoint, what is the minimum value of the starting point.

We can use dynamic programming to solve this problem. We define $f[i][j]$ as the minimum value of the path with $(i, j)$ as the endpoint. Then we can get the state transition equation:

$$
f[i][j] = \min(f[i-1][j], f[i][j-1], grid[i][j])
$$

So the answer is the maximum value of $\textit{grid}[i][j] - \min(f[i-1][j], f[i][j-1])$.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Where $m$ and $n$ are the number of rows and columns of the matrix, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, grid: List[List[int]]) -> int:
        f = [[0] * len(grid[0]) for _ in range(len(grid))]
        ans = -inf
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                mi = inf
                if i:
                    mi = min(mi, f[i - 1][j])
                if j:
                    mi = min(mi, f[i][j - 1])
                ans = max(ans, x - mi)
                f[i][j] = min(x, mi)
        return ans
```

#### Java

```java
class Solution {
    public int maxScore(List<List<Integer>> grid) {
        int m = grid.size(), n = grid.get(0).size();
        final int inf = 1 << 30;
        int ans = -inf;
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int mi = inf;
                if (i > 0) {
                    mi = Math.min(mi, f[i - 1][j]);
                }
                if (j > 0) {
                    mi = Math.min(mi, f[i][j - 1]);
                }
                ans = Math.max(ans, grid.get(i).get(j) - mi);
                f[i][j] = Math.min(grid.get(i).get(j), mi);
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
    int maxScore(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        const int inf = 1 << 30;
        int ans = -inf;
        int f[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int mi = inf;
                if (i) {
                    mi = min(mi, f[i - 1][j]);
                }
                if (j) {
                    mi = min(mi, f[i][j - 1]);
                }
                ans = max(ans, grid[i][j] - mi);
                f[i][j] = min(grid[i][j], mi);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxScore(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	const inf int = 1 << 30
	ans := -inf
	for i, row := range grid {
		for j, x := range row {
			mi := inf
			if i > 0 {
				mi = min(mi, f[i-1][j])
			}
			if j > 0 {
				mi = min(mi, f[i][j-1])
			}
			ans = max(ans, x-mi)
			f[i][j] = min(x, mi)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxScore(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    const f: number[][] = Array.from({ length: m }, () => Array.from({ length: n }, () => 0));
    let ans = -Infinity;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            let mi = Infinity;
            if (i) {
                mi = Math.min(mi, f[i - 1][j]);
            }
            if (j) {
                mi = Math.min(mi, f[i][j - 1]);
            }
            ans = Math.max(ans, grid[i][j] - mi);
            f[i][j] = Math.min(mi, grid[i][j]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
