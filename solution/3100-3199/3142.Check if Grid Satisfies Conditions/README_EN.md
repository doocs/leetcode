---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3142.Check%20if%20Grid%20Satisfies%20Conditions/README_EN.md
---

<!-- problem:start -->

# [3142. Check if Grid Satisfies Conditions](https://leetcode.com/problems/check-if-grid-satisfies-conditions)

[中文文档](/solution/3100-3199/3142.Check%20if%20Grid%20Satisfies%20Conditions/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D matrix <code>grid</code> of size <code>m x n</code>. You need to check if each cell <code>grid[i][j]</code> is:</p>

<ul>
	<li>Equal to the cell below it, i.e. <code>grid[i][j] == grid[i + 1][j]</code> (if it exists).</li>
	<li>Different from the cell to its right, i.e. <code>grid[i][j] != grid[i][j + 1]</code> (if it exists).</li>
</ul>

<p>Return <code>true</code> if <strong>all</strong> the cells satisfy these conditions, otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,0,2],[1,0,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3142.Check%20if%20Grid%20Satisfies%20Conditions/images/examplechanged.png" style="width: 254px; height: 186px;padding: 10px; background: #fff; border-radius: .5rem;" /></strong></p>

<p>All the cells in the grid satisfy the conditions.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,1,1],[0,0,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3142.Check%20if%20Grid%20Satisfies%20Conditions/images/example21.png" style="width: 254px; height: 186px;padding: 10px; background: #fff; border-radius: .5rem;" /></strong></p>

<p>All cells in the first row are equal.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1],[2],[3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3142.Check%20if%20Grid%20Satisfies%20Conditions/images/changed.png" style="width: 86px; height: 277px;padding: 10px; background: #fff; border-radius: .5rem;" /></p>

<p>Cells in the first column have different values.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, m &lt;= 10</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can iterate through each cell and determine whether it meets the conditions specified in the problem. If there is a cell that does not meet the conditions, we return `false`, otherwise, we return `true`.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the matrix `grid` respectively. The space complexity is $O(1)`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def satisfiesConditions(self, grid: List[List[int]]) -> bool:
        m, n = len(grid), len(grid[0])
        for i, row in enumerate(grid):
            for j, x in enumerate(row):
                if i + 1 < m and x != grid[i + 1][j]:
                    return False
                if j + 1 < n and x == grid[i][j + 1]:
                    return False
        return True
```

#### Java

```java
class Solution {
    public boolean satisfiesConditions(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i + 1 < m && grid[i][j] != grid[i + 1][j]) {
                    return false;
                }
                if (j + 1 < n && grid[i][j] == grid[i][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool satisfiesConditions(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i + 1 < m && grid[i][j] != grid[i + 1][j]) {
                    return false;
                }
                if (j + 1 < n && grid[i][j] == grid[i][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
};
```

#### Go

```go
func satisfiesConditions(grid [][]int) bool {
	m, n := len(grid), len(grid[0])
	for i, row := range grid {
		for j, x := range row {
			if i+1 < m && x != grid[i+1][j] {
				return false
			}
			if j+1 < n && x == grid[i][j+1] {
				return false
			}
		}
	}
	return true
}
```

#### TypeScript

```ts
function satisfiesConditions(grid: number[][]): boolean {
    const [m, n] = [grid.length, grid[0].length];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i + 1 < m && grid[i][j] !== grid[i + 1][j]) {
                return false;
            }
            if (j + 1 < n && grid[i][j] === grid[i][j + 1]) {
                return false;
            }
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
