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

<!-- problem:start -->

# [2711. Difference of Number of Distinct Values on Diagonals](https://leetcode.com/problems/difference-of-number-of-distinct-values-on-diagonals)

[中文文档](/solution/2700-2799/2711.Difference%20of%20Number%20of%20Distinct%20Values%20on%20Diagonals/README.md)

## Description

<!-- description:start -->

<p>Given a 2D <code>grid</code> of size <code>m x n</code>, you should find the matrix <code>answer</code> of size <code>m x n</code>.</p>

<p>The cell <code>answer[r][c]</code> is calculated by looking at the diagonal values of the cell <code>grid[r][c]</code>:</p>

<ul>
	<li>Let <code>leftAbove[r][c]</code> be the number of <strong>distinct</strong> values on the diagonal to the left and above the cell <code>grid[r][c]</code> not including the cell <code>grid[r][c]</code> itself.</li>
	<li>Let <code>rightBelow[r][c]</code> be the number of <strong>distinct</strong> values on the diagonal to the right and below the cell <code>grid[r][c]</code>, not including the cell <code>grid[r][c]</code> itself.</li>
	<li>Then <code>answer[r][c] = |leftAbove[r][c] - rightBelow[r][c]|</code>.</li>
</ul>

<p>A <strong>matrix diagonal</strong> is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until the end of the matrix is reached.</p>

<ul>
	<li>For example, in the below diagram the diagonal is highlighted using the cell with indices <code>(2, 3)</code> colored gray:

    <ul>
    	<li>Red-colored cells are left and above the cell.</li>
    	<li>Blue-colored cells are right and below the cell.</li>
    </ul>
    </li>

</ul>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2711.Difference%20of%20Number%20of%20Distinct%20Values%20on%20Diagonals/images/diagonal.png" style="width: 200px; height: 160px;" /></p>

<p>Return the matrix <code>answer</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,2,3],[3,1,5],[3,2,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">Output: [[1,1,0],[1,0,1],[0,1,1]]</span></p>

<p><strong>Explanation:</strong></p>

<p>To calculate the <code>answer</code> cells:</p>

<table>
	<thead>
		<tr>
			<th>answer</th>
			<th>left-above elements</th>
			<th>leftAbove</th>
			<th>right-below elements</th>
			<th>rightBelow</th>
			<th>|leftAbove - rightBelow|</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>[0][0]</td>
			<td>[]</td>
			<td>0</td>
			<td>[grid[1][1], grid[2][2]]</td>
			<td>|{1, 1}| = 1</td>
			<td>1</td>
		</tr>
		<tr>
			<td>[0][1]</td>
			<td>[]</td>
			<td>0</td>
			<td>[grid[1][2]]</td>
			<td>|{5}| = 1</td>
			<td>1</td>
		</tr>
		<tr>
			<td>[0][2]</td>
			<td>[]</td>
			<td>0</td>
			<td>[]</td>
			<td>0</td>
			<td>0</td>
		</tr>
		<tr>
			<td>[1][0]</td>
			<td>[]</td>
			<td>0</td>
			<td>[grid[2][1]]</td>
			<td>|{2}| = 1</td>
			<td>1</td>
		</tr>
		<tr>
			<td>[1][1]</td>
			<td>[grid[0][0]]</td>
			<td>|{1}| = 1</td>
			<td>[grid[2][2]]</td>
			<td>|{1}| = 1</td>
			<td>0</td>
		</tr>
		<tr>
			<td>[1][2]</td>
			<td>[grid[0][1]]</td>
			<td>|{2}| = 1</td>
			<td>[]</td>
			<td>0</td>
			<td>1</td>
		</tr>
		<tr>
			<td>[2][0]</td>
			<td>[]</td>
			<td>0</td>
			<td>[]</td>
			<td>0</td>
			<td>0</td>
		</tr>
		<tr>
			<td>[2][1]</td>
			<td>[grid[1][0]]</td>
			<td>|{3}| = 1</td>
			<td>[]</td>
			<td>0</td>
			<td>1</td>
		</tr>
		<tr>
			<td>[2][2]</td>
			<td>[grid[0][0], grid[1][1]]</td>
			<td>|{1, 1}| = 1</td>
			<td>[]</td>
			<td>0</td>
			<td>1</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">Output: [[0]]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n, grid[i][j] &lt;= 50</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can simulate the process described in the problem statement, calculating the number of distinct values on the top-left diagonal $tl$ and the bottom-right diagonal $br$ for each cell, then compute their difference $|tl - br|$.

The time complexity is $O(m \times n \times \min(m, n))$, and the space complexity is $O(m \times n)$.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

<!-- solution:end -->

<!-- problem:end -->
