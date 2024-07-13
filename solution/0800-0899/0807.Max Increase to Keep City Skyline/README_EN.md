---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0807.Max%20Increase%20to%20Keep%20City%20Skyline/README_EN.md
tags:
    - Greedy
    - Array
    - Matrix
---

<!-- problem:start -->

# [807. Max Increase to Keep City Skyline](https://leetcode.com/problems/max-increase-to-keep-city-skyline)

[中文文档](/solution/0800-0899/0807.Max%20Increase%20to%20Keep%20City%20Skyline/README.md)

## Description

<!-- description:start -->

<p>There is a city composed of <code>n x n</code> blocks, where each block contains a single building shaped like a vertical square prism. You are given a <strong>0-indexed</strong> <code>n x n</code> integer matrix <code>grid</code> where <code>grid[r][c]</code> represents the <strong>height</strong> of the building located in the block at row <code>r</code> and column <code>c</code>.</p>

<p>A city&#39;s <strong>skyline</strong> is the&nbsp;outer contour formed by all the building when viewing the side of the city from a distance. The <strong>skyline</strong> from each cardinal direction north, east, south, and west may be different.</p>

<p>We are allowed to increase the height of <strong>any number of buildings by any amount</strong> (the amount can be different per building). The height of a <code>0</code>-height building can also be increased. However, increasing the height of a building should <strong>not</strong> affect the city&#39;s <strong>skyline</strong> from any cardinal direction.</p>

<p>Return <em>the <strong>maximum total sum</strong> that the height of the buildings can be increased by <strong>without</strong> changing the city&#39;s <strong>skyline</strong> from any cardinal direction</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0807.Max%20Increase%20to%20Keep%20City%20Skyline/images/807-ex1.png" style="width: 700px; height: 603px;" />
<pre>
<strong>Input:</strong> grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]
<strong>Output:</strong> 35
<strong>Explanation:</strong> The building heights are shown in the center of the above image.
The skylines when viewed from each cardinal direction are drawn in red.
The grid after increasing the height of buildings without affecting skylines is:
gridNew = [ [8, 4, 8, 7],
            [7, 4, 7, 7],
            [9, 4, 8, 7],
            [3, 3, 3, 3] ]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[0,0,0],[0,0,0],[0,0,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> Increasing the height of any building will result in the skyline changing.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[r].length</code></li>
	<li><code>2 &lt;= n &lt;= 50</code></li>
	<li><code>0 &lt;= grid[r][c] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

According to the problem description, we can increase the value of each cell $(i, j)$ to the smaller value between the maximum value of the $i$-th row and the $j$-th column, ensuring it does not affect the skyline. Thus, the height added to each cell is $\min(\text{rowMax}[i], \text{colMax}[j]) - \text{grid}[i][j]$.

Therefore, we can first traverse the matrix once to calculate the maximum value of each row and column, storing them in the arrays $\text{rowMax}$ and $\text{colMax}$, respectively. Then, we traverse the matrix again to compute the answer.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$, where $n$ is the side length of the matrix $\text{grid}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxIncreaseKeepingSkyline(self, grid: List[List[int]]) -> int:
        row_max = [max(row) for row in grid]
        col_max = [max(col) for col in zip(*grid)]
        return sum(
            min(row_max[i], col_max[j]) - x
            for i, row in enumerate(grid)
            for j, x in enumerate(row)
        )
```

#### Java

```java
class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rowMax = new int[m];
        int[] colMax = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rowMax[i] = Math.max(rowMax[i], grid[i][j]);
                colMax[j] = Math.max(colMax[j], grid[i][j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans += Math.min(rowMax[i], colMax[j]) - grid[i][j];
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
    int maxIncreaseKeepingSkyline(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        vector<int> rowMax(m);
        vector<int> colMax(n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rowMax[i] = max(rowMax[i], grid[i][j]);
                colMax[j] = max(colMax[j], grid[i][j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans += min(rowMax[i], colMax[j]) - grid[i][j];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxIncreaseKeepingSkyline(grid [][]int) (ans int) {
	rowMax := make([]int, len(grid))
	colMax := make([]int, len(grid[0]))
	for i, row := range grid {
		for j, x := range row {
			rowMax[i] = max(rowMax[i], x)
			colMax[j] = max(colMax[j], x)
		}
	}
	for i, row := range grid {
		for j, x := range row {
			ans += min(rowMax[i], colMax[j]) - x
		}
	}
	return
}
```

#### TypeScript

```ts
function maxIncreaseKeepingSkyline(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const rowMax = Array(m).fill(0);
    const colMax = Array(n).fill(0);
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            rowMax[i] = Math.max(rowMax[i], grid[i][j]);
            colMax[j] = Math.max(colMax[j], grid[i][j]);
        }
    }
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            ans += Math.min(rowMax[i], colMax[j]) - grid[i][j];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
