---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2711.Difference%20of%20Number%20of%20Distinct%20Values%20on%20Diagonals/README_EN.md
rating: 1428
source: Weekly Contest 347 Q2
tags:
    - Array
    - Hash Table
    - Matrix
---

# [2711. Difference of Number of Distinct Values on Diagonals](https://leetcode.com/problems/difference-of-number-of-distinct-values-on-diagonals)

[中文文档](/solution/2700-2799/2711.Difference%20of%20Number%20of%20Distinct%20Values%20on%20Diagonals/README.md)

## Description

<p>Given a <strong>0-indexed</strong> 2D <code>grid</code> of size <code>m x n</code>, you should find the matrix <code>answer</code> of size <code>m x n</code>.</p>

<p>The value of each cell <code>(r, c)</code> of the matrix <code>answer</code> is calculated in the following way:</p>

<ul>
	<li>Let <code>topLeft[r][c]</code> be the number of <strong>distinct</strong> values in the top-left diagonal of the cell <code>(r, c)</code> in the matrix <code>grid</code>.</li>
	<li>Let <code>bottomRight[r][c]</code> be the number of <strong>distinct</strong> values in the bottom-right diagonal of the cell <code>(r, c)</code> in the matrix <code>grid</code>.</li>
</ul>

<p>Then <code>answer[r][c] = |topLeft[r][c] - bottomRight[r][c]|</code>.</p>

<p>Return <em>the matrix</em> <code>answer</code>.</p>

<p>A <strong>matrix diagonal</strong> is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix&#39;s end.</p>

<p>A cell <code>(r<sub>1</sub>, c<sub>1</sub>)</code> belongs to the top-left diagonal of the cell <code>(r, c)</code>, if both belong to the same diagonal and <code>r<sub>1</sub> &lt; r</code>. Similarly is defined bottom-right diagonal.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2711.Difference%20of%20Number%20of%20Distinct%20Values%20on%20Diagonals/images/ex2.png" style="width: 786px; height: 121px;" />
<pre>
<strong>
Input:</strong> grid = [[1,2,3],[3,1,5],[3,2,1]]
<strong>Output:</strong> [[1,1,0],[1,0,1],[0,1,1]]
<strong>Explanation:</strong> The 1<sup>st</sup> diagram denotes the initial grid.&nbsp;
The 2<sup>nd</sup> diagram denotes a grid for cell (0,0), where blue-colored cells are cells on its bottom-right diagonal.
The 3<sup>rd</sup> diagram denotes a grid for cell (1,2), where red-colored cells are cells on its top-left diagonal.
The 4<sup>th</sup> diagram denotes a grid for cell (1,1), where blue-colored cells are cells on its bottom-right diagonal and red-colored cells are cells on its top-left diagonal.
- The cell (0,0) contains [1,1] on its bottom-right diagonal and [] on its top-left diagonal. The answer is |1 - 0| = 1.
- The cell (1,2) contains [] on its bottom-right diagonal and [2] on its top-left diagonal. The answer is |0 - 1| = 1.
- The cell (1,1) contains [1] on its bottom-right diagonal and [1] on its top-left diagonal. The answer is |1 - 1| = 0.
The answers of other cells are similarly calculated.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1]]
<strong>Output:</strong> [[0]]
<strong>Explanation:</strong> - The cell (0,0) contains [] on its bottom-right diagonal and [] on its top-left diagonal. The answer is |0 - 0| = 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n, grid[i][j] &lt;= 50</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def differenceOfDistinctValues(self, grid: List[List[int]]) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        ans = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                x, y = i, j
                s = set()
                while x and y:
                    x, y = x - 1, y - 1
                    s.add(grid[x][y])
                tl = len(s)
                x, y = i, j
                s = set()
                while x + 1 < m and y + 1 < n:
                    x, y = x + 1, y + 1
                    s.add(grid[x][y])
                br = len(s)
                ans[i][j] = abs(tl - br)
        return ans
```

```java
class Solution {
    public int[][] differenceOfDistinctValues(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int x = i, y = j;
                Set<Integer> s = new HashSet<>();
                while (x > 0 && y > 0) {
                    s.add(grid[--x][--y]);
                }
                int tl = s.size();
                x = i;
                y = j;
                s.clear();
                while (x < m - 1 && y < n - 1) {
                    s.add(grid[++x][++y]);
                }
                int br = s.size();
                ans[i][j] = Math.abs(tl - br);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> differenceOfDistinctValues(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> ans(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int x = i, y = j;
                unordered_set<int> s;
                while (x > 0 && y > 0) {
                    s.insert(grid[--x][--y]);
                }
                int tl = s.size();
                x = i;
                y = j;
                s.clear();
                while (x < m - 1 && y < n - 1) {
                    s.insert(grid[++x][++y]);
                }
                int br = s.size();
                ans[i][j] = abs(tl - br);
            }
        }
        return ans;
    }
};
```

```go
func differenceOfDistinctValues(grid [][]int) [][]int {
	m, n := len(grid), len(grid[0])
	ans := make([][]int, m)
	for i := range grid {
		ans[i] = make([]int, n)
		for j := range grid[i] {
			x, y := i, j
			s := map[int]bool{}
			for x > 0 && y > 0 {
				x, y = x-1, y-1
				s[grid[x][y]] = true
			}
			tl := len(s)
			x, y = i, j
			s = map[int]bool{}
			for x+1 < m && y+1 < n {
				x, y = x+1, y+1
				s[grid[x][y]] = true
			}
			br := len(s)
			ans[i][j] = abs(tl - br)
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function differenceOfDistinctValues(grid: number[][]): number[][] {
    const m = grid.length;
    const n = grid[0].length;
    const ans: number[][] = Array(m)
        .fill(0)
        .map(() => Array(n).fill(0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            let [x, y] = [i, j];
            const s = new Set<number>();
            while (x && y) {
                s.add(grid[--x][--y]);
            }
            const tl = s.size;
            [x, y] = [i, j];
            s.clear();
            while (x + 1 < m && y + 1 < n) {
                s.add(grid[++x][++y]);
            }
            const br = s.size;
            ans[i][j] = Math.abs(tl - br);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
