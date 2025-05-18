---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3546.Equal%20Sum%20Grid%20Partition%20I/README_EN.md
tags:
    - Array
    - Enumeration
    - Matrix
    - Prefix Sum
---

<!-- problem:start -->

# [3546. Equal Sum Grid Partition I](https://leetcode.com/problems/equal-sum-grid-partition-i)

[中文文档](/solution/3500-3599/3546.Equal%20Sum%20Grid%20Partition%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> matrix <code>grid</code> of positive integers. Your task is to determine if it is possible to make <strong>either one horizontal or one vertical cut</strong> on the grid such that:</p>

<ul>
	<li>Each of the two resulting sections formed by the cut is <strong>non-empty</strong>.</li>
	<li>The sum of the elements in both sections is <strong>equal</strong>.</li>
</ul>

<p>Return <code>true</code> if such a partition exists; otherwise return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,4],[2,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3546.Equal%20Sum%20Grid%20Partition%20I/images/lc.png" style="width: 200px;" /><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3546.Equal%20Sum%20Grid%20Partition%20I/images/lc.jpeg" style="width: 200px; height: 200px;" /></p>

<p>A horizontal cut between row 0 and row 1 results in two non-empty sections, each with a sum of 5. Thus, the answer is <code>true</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,3],[2,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>No horizontal or vertical cut results in two non-empty sections with equal sums. Thus, the answer is <code>false</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == grid.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= n == grid[i].length &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Prefix Sum

First, we calculate the sum of all elements in the matrix, denoted as $s$. If $s$ is odd, it is impossible to divide the matrix into two parts with equal sums, so we directly return `false`.

If $s$ is even, we can enumerate all possible partition lines to check if there exists a line that divides the matrix into two parts with equal sums.

We traverse each row from top to bottom, calculating the sum of all elements in the rows above the current row, denoted as $\textit{pre}$. If $\textit{pre} \times 2 = s$ and the current row is not the last row, it means we can perform a horizontal partition between the current row and the next row, so we return `true`.

If no such partition line is found, we traverse each column from left to right, calculating the sum of all elements in the columns to the left of the current column, denoted as $\textit{pre}$. If $\textit{pre} \times 2 = s$ and the current column is not the last column, it means we can perform a vertical partition between the current column and the next column, so we return `true`.

If no such partition line is found, we return `false`.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns in the matrix, respectively. The space complexity is $O(1)$, as only constant extra space is used.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canPartitionGrid(self, grid: List[List[int]]) -> bool:
        s = sum(sum(row) for row in grid)
        if s % 2:
            return False
        pre = 0
        for i, row in enumerate(grid):
            pre += sum(row)
            if pre * 2 == s and i != len(grid) - 1:
                return True
        pre = 0
        for j, col in enumerate(zip(*grid)):
            pre += sum(col)
            if pre * 2 == s and j != len(grid[0]) - 1:
                return True
        return False
```

#### Java

```java
class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        long s = 0;
        for (var row : grid) {
            for (int x : row) {
                s += x;
            }
        }
        if (s % 2 != 0) {
            return false;
        }
        int m = grid.length, n = grid[0].length;
        long pre = 0;
        for (int i = 0; i < m; ++i) {
            for (int x : grid[i]) {
                pre += x;
            }
            if (pre * 2 == s && i < m - 1) {
                return true;
            }
        }
        pre = 0;
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                pre += grid[i][j];
            }
            if (pre * 2 == s && j < n - 1) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canPartitionGrid(vector<vector<int>>& grid) {
        long long s = 0;
        for (const auto& row : grid) {
            for (int x : row) {
                s += x;
            }
        }
        if (s % 2 != 0) {
            return false;
        }
        int m = grid.size(), n = grid[0].size();
        long long pre = 0;
        for (int i = 0; i < m; ++i) {
            for (int x : grid[i]) {
                pre += x;
            }
            if (pre * 2 == s && i + 1 < m) {
                return true;
            }
        }
        pre = 0;
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                pre += grid[i][j];
            }
            if (pre * 2 == s && j + 1 < n) {
                return true;
            }
        }
        return false;
    }
};
```

#### Go

```go
func canPartitionGrid(grid [][]int) bool {
	s := 0
	for _, row := range grid {
		for _, x := range row {
			s += x
		}
	}
	if s%2 != 0 {
		return false
	}
	m, n := len(grid), len(grid[0])
	pre := 0
	for i, row := range grid {
		for _, x := range row {
			pre += x
		}
		if pre*2 == s && i+1 < m {
			return true
		}
	}
	pre = 0
	for j := 0; j < n; j++ {
		for i := 0; i < m; i++ {
			pre += grid[i][j]
		}
		if pre*2 == s && j+1 < n {
			return true
		}
	}
	return false
}
```

#### TypeScript

```ts
function canPartitionGrid(grid: number[][]): boolean {
    let s = 0;
    for (const row of grid) {
        s += row.reduce((a, b) => a + b, 0);
    }
    if (s % 2 !== 0) {
        return false;
    }
    const [m, n] = [grid.length, grid[0].length];
    let pre = 0;
    for (let i = 0; i < m; ++i) {
        pre += grid[i].reduce((a, b) => a + b, 0);
        if (pre * 2 === s && i + 1 < m) {
            return true;
        }
    }
    pre = 0;
    for (let j = 0; j < n; ++j) {
        for (let i = 0; i < m; ++i) {
            pre += grid[i][j];
        }
        if (pre * 2 === s && j + 1 < n) {
            return true;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
