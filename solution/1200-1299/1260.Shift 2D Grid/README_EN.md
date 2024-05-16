---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1260.Shift%202D%20Grid/README_EN.md
rating: 1337
source: Weekly Contest 163 Q1
tags:
    - Array
    - Matrix
    - Simulation
---

# [1260. Shift 2D Grid](https://leetcode.com/problems/shift-2d-grid)

[中文文档](/solution/1200-1299/1260.Shift%202D%20Grid/README.md)

## Description

<p>Given a 2D <code>grid</code> of size <code>m x n</code>&nbsp;and an integer <code>k</code>. You need to shift the <code>grid</code>&nbsp;<code>k</code> times.</p>

<p>In one shift operation:</p>

<ul>
	<li>Element at <code>grid[i][j]</code> moves to <code>grid[i][j + 1]</code>.</li>
	<li>Element at <code>grid[i][n - 1]</code> moves to <code>grid[i + 1][0]</code>.</li>
	<li>Element at <code>grid[m&nbsp;- 1][n - 1]</code> moves to <code>grid[0][0]</code>.</li>
</ul>

<p>Return the <em>2D grid</em> after applying shift operation <code>k</code> times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1260.Shift%202D%20Grid/images/e1.png" style="width: 400px; height: 178px;" />
<pre>
<strong>Input:</strong> <code>grid</code> = [[1,2,3],[4,5,6],[7,8,9]], k = 1
<strong>Output:</strong> [[9,1,2],[3,4,5],[6,7,8]]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1260.Shift%202D%20Grid/images/e2.png" style="width: 400px; height: 166px;" />
<pre>
<strong>Input:</strong> <code>grid</code> = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
<strong>Output:</strong> [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> <code>grid</code> = [[1,2,3],[4,5,6],[7,8,9]], k = 9
<strong>Output:</strong> [[1,2,3],[4,5,6],[7,8,9]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m ==&nbsp;grid.length</code></li>
	<li><code>n ==&nbsp;grid[i].length</code></li>
	<li><code>1 &lt;= m &lt;= 50</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>-1000 &lt;= grid[i][j] &lt;= 1000</code></li>
	<li><code>0 &lt;= k &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1: Flattening the 2D Array

According to the problem description, if we flatten the 2D array into a 1D array, then each shift operation is to move the elements in the array one position to the right, with the last element moving to the first position of the array.

Therefore, we can flatten the 2D array into a 1D array, then calculate the final position $idx = (x, y)$ of each element, and update the answer array `ans[x][y] = grid[i][j]`.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns in the `grid` array, respectively. We need to traverse the `grid` array once to calculate the final position of each element. Ignoring the space consumption of the answer array, the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def shiftGrid(self, grid: List[List[int]], k: int) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        ans = [[0] * n for _ in range(m)]
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                x, y = divmod((i * n + j + k) % (m * n), n)
                ans[x][y] = v
        return ans
```

```java
class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                row.add(0);
            }
            ans.add(row);
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int idx = (i * n + j + k) % (m * n);
                int x = idx / n, y = idx % n;
                ans.get(x).set(y, grid[i][j]);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> shiftGrid(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> ans(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int idx = (i * n + j + k) % (m * n);
                int x = idx / n, y = idx % n;
                ans[x][y] = grid[i][j];
            }
        }
        return ans;
    }
};
```

```go
func shiftGrid(grid [][]int, k int) [][]int {
	m, n := len(grid), len(grid[0])
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			idx := (i*n + j + k) % (m * n)
			x, y := idx/n, idx%n
			ans[x][y] = grid[i][j]
		}
	}
	return ans
}
```

```ts
function shiftGrid(grid: number[][], k: number): number[][] {
    const [m, n] = [grid.length, grid[0].length];
    const ans: number[][] = Array.from({ length: m }, () => Array.from({ length: n }, () => 0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            const idx = (i * n + j + k) % (m * n);
            const [x, y] = [Math.floor(idx / n), idx % n];
            ans[x][y] = grid[i][j];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
