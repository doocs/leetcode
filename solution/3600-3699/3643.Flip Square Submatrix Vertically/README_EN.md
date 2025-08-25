---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3643.Flip%20Square%20Submatrix%20Vertically/README_EN.md
rating: 1234
source: Weekly Contest 462 Q1
tags:
    - Array
    - Two Pointers
    - Matrix
---

<!-- problem:start -->

# [3643. Flip Square Submatrix Vertically](https://leetcode.com/problems/flip-square-submatrix-vertically)

[中文文档](/solution/3600-3699/3643.Flip%20Square%20Submatrix%20Vertically/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> integer matrix <code>grid</code>, and three integers <code>x</code>, <code>y</code>, and <code>k</code>.</p>

<p>The integers <code>x</code> and <code>y</code> represent the row and column indices of the <strong>top-left</strong> corner of a <strong>square</strong> submatrix and the integer <code>k</code> represents the size (side length) of the square submatrix.</p>

<p>Your task is to flip the submatrix by reversing the order of its rows vertically.</p>

<p>Return the updated matrix.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3643.Flip%20Square%20Submatrix%20Vertically/images/gridexmdrawio.png" style="width: 300px; height: 116px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = </span>[[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]]<span class="example-io">, x = 1, y = 0, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[[1,2,3,4],[13,14,15,8],[9,10,11,12],[5,6,7,16]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The diagram above shows the grid before and after the transformation.</p>
</div>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3643.Flip%20Square%20Submatrix%20Vertically/images/gridexm2drawio.png" style="width: 350px; height: 68px;" />​​​​​​​
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[3,4,2,3],[2,3,4,2]], x = 0, y = 2, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[[3,4,4,2],[2,3,2,3]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The diagram above shows the grid before and after the transformation.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 100</code></li>
	<li><code>0 &lt;= x &lt; m</code></li>
	<li><code>0 &lt;= y &lt; n</code></li>
	<li><code>1 &lt;= k &lt;= min(m - x, n - y)</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We start from row $x$ and flip a total of $\lfloor \frac{k}{2} \rfloor$ rows.

For each row $i$, we need to swap it with the corresponding row $i_2$, where $i_2 = x + k - 1 - (i - x)$.

During the swap, we need to traverse $j \in [y, y + k)$ and swap $\text{grid}[i][j]$ with $\text{grid}[i_2][j]$.

Finally, return the updated matrix.

The time complexity is $O(k^2)$, where $k$ is the side length of the submatrix. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseSubmatrix(
        self, grid: List[List[int]], x: int, y: int, k: int
    ) -> List[List[int]]:
        for i in range(x, x + k // 2):
            i2 = x + k - 1 - (i - x)
            for j in range(y, y + k):
                grid[i][j], grid[i2][j] = grid[i2][j], grid[i][j]
        return grid
```

#### Java

```java
class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        for (int i = x; i < x + k / 2; i++) {
            int i2 = x + k - 1 - (i - x);
            for (int j = y; j < y + k; j++) {
                int t = grid[i][j];
                grid[i][j] = grid[i2][j];
                grid[i2][j] = t;
            }
        }
        return grid;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> reverseSubmatrix(vector<vector<int>>& grid, int x, int y, int k) {
        for (int i = x; i < x + k / 2; i++) {
            int i2 = x + k - 1 - (i - x);
            for (int j = y; j < y + k; j++) {
                swap(grid[i][j], grid[i2][j]);
            }
        }
        return grid;
    }
};
```

#### Go

```go
func reverseSubmatrix(grid [][]int, x int, y int, k int) [][]int {
	for i := x; i < x+k/2; i++ {
		i2 := x + k - 1 - (i - x)
		for j := y; j < y+k; j++ {
			grid[i][j], grid[i2][j] = grid[i2][j], grid[i][j]
		}
	}
	return grid
}
```

#### TypeScript

```ts
function reverseSubmatrix(grid: number[][], x: number, y: number, k: number): number[][] {
    for (let i = x; i < x + Math.floor(k / 2); i++) {
        const i2 = x + k - 1 - (i - x);
        for (let j = y; j < y + k; j++) {
            [grid[i][j], grid[i2][j]] = [grid[i2][j], grid[i][j]];
        }
    }
    return grid;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
