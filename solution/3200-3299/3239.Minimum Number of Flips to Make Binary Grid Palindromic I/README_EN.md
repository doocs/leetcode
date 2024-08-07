---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3239.Minimum%20Number%20of%20Flips%20to%20Make%20Binary%20Grid%20Palindromic%20I/README_EN.md
tags:
    - Array
    - Two Pointers
    - Matrix
---

<!-- problem:start -->

# [3239. Minimum Number of Flips to Make Binary Grid Palindromic I](https://leetcode.com/problems/minimum-number-of-flips-to-make-binary-grid-palindromic-i)

[中文文档](/solution/3200-3299/3239.Minimum%20Number%20of%20Flips%20to%20Make%20Binary%20Grid%20Palindromic%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> binary matrix <code>grid</code>.</p>

<p>A row or column is considered <strong>palindromic</strong> if its values read the same forward and backward.</p>

<p>You can <strong>flip</strong> any number of cells in <code>grid</code> from <code>0</code> to <code>1</code>, or from <code>1</code> to <code>0</code>.</p>

<p>Return the <strong>minimum</strong> number of cells that need to be flipped to make <strong>either</strong> all rows <strong>palindromic</strong> or all columns <strong>palindromic</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,0,0],[0,0,0],[0,0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3239.Minimum%20Number%20of%20Flips%20to%20Make%20Binary%20Grid%20Palindromic%20I/images/screenshot-from-2024-07-08-00-20-10.png" style="width: 420px; height: 108px;" /></p>

<p>Flipping the highlighted cells makes all the rows palindromic.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = </span>[[0,1],[0,1],[0,0]]</p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3239.Minimum%20Number%20of%20Flips%20to%20Make%20Binary%20Grid%20Palindromic%20I/images/screenshot-from-2024-07-08-00-31-23.png" style="width: 300px; height: 100px;" /></p>

<p>Flipping the highlighted cell makes all the columns palindromic.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1],[0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>All rows are already palindromic.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m * n &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We separately count the number of flips for rows and columns, denoted as $\textit{cnt1}$ and $\textit{cnt2}$, respectively. Finally, we take the minimum of the two.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the matrix $\textit{grid}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minFlips(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        cnt1 = cnt2 = 0
        for row in grid:
            for j in range(n // 2):
                if row[j] != row[n - j - 1]:
                    cnt1 += 1
        for j in range(n):
            for i in range(m // 2):
                if grid[i][j] != grid[m - i - 1][j]:
                    cnt2 += 1
        return min(cnt1, cnt2)
```

#### Java

```java
class Solution {
    public int minFlips(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int cnt1 = 0, cnt2 = 0;
        for (var row : grid) {
            for (int j = 0; j < n / 2; ++j) {
                if (row[j] != row[n - j - 1]) {
                    ++cnt1;
                }
            }
        }
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m / 2; ++i) {
                if (grid[i][j] != grid[m - i - 1][j]) {
                    ++cnt2;
                }
            }
        }
        return Math.min(cnt1, cnt2);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minFlips(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int cnt1 = 0, cnt2 = 0;
        for (const auto& row : grid) {
            for (int j = 0; j < n / 2; ++j) {
                if (row[j] != row[n - j - 1]) {
                    ++cnt1;
                }
            }
        }
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m / 2; ++i) {
                if (grid[i][j] != grid[m - i - 1][j]) {
                    ++cnt2;
                }
            }
        }
        return min(cnt1, cnt2);
    }
};
```

#### Go

```go
func minFlips(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	cnt1, cnt2 := 0, 0
	for _, row := range grid {
		for j := 0; j < n/2; j++ {
			if row[j] != row[n-j-1] {
				cnt1++
			}
		}
	}
	for j := 0; j < n; j++ {
		for i := 0; i < m/2; i++ {
			if grid[i][j] != grid[m-i-1][j] {
				cnt2++
			}
		}
	}
	return min(cnt1, cnt2)
}
```

#### TypeScript

```ts
function minFlips(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    let [cnt1, cnt2] = [0, 0];
    for (const row of grid) {
        for (let j = 0; j < n / 2; ++j) {
            if (row[j] !== row[n - 1 - j]) {
                ++cnt1;
            }
        }
    }
    for (let j = 0; j < n; ++j) {
        for (let i = 0; i < m / 2; ++i) {
            if (grid[i][j] !== grid[m - 1 - i][j]) {
                ++cnt2;
            }
        }
    }
    return Math.min(cnt1, cnt2);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
