---
comments: true
difficulty: Easy
rating: 1246
source: Weekly Contest 519 Q1
---

<!-- problem:start -->

# [4052. Cyclically Shift Rows and Columns](https://leetcode.com/problems/cyclically-shift-rows-and-columns)

[中文文档](/solution/4000-4099/4052.Cyclically%20Shift%20Rows%20and%20Columns/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>, a 2D integer array <code>grid</code> of size <code>n x n</code>, and two integer arrays <code>rowShift</code> and <code>colShift</code>, each of length <code>n</code>, where:</p>

<ul>
	<li><code>rowShift[i]</code> represents the number of positions to <strong>cyclically shift</strong> the <code>i<sup>th</sup></code> row of <code>grid</code> to the <strong>left</strong>.</li>
	<li><code>colShift[j]</code> represents the number of positions to <strong>cyclically shift</strong> the <code>j<sup>th</sup></code> column of <code>grid</code> <strong>upward</strong>.</li>
</ul>

<p>First, cyclically shift each row according to <code>rowShift</code>, then cyclically shift each column of the resulting grid according to <code>colShift</code>.</p>

<p>Return the resulting grid after performing all the shifts.</p>

<p>A <strong>cyclic left shift</strong> of a row by <code>k</code> positions moves the element at column <code>j</code> to column <code>(j - k + n) % n</code>. All other rows remain unchanged.</p>

<p>A <strong>cyclic upward shift</strong> of a column by <code>k</code> positions moves the element at row <code>i</code> to row <code>(i - k + n) % n</code>. All other columns remain unchanged.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, <code>grid</code> = [[1,2],[3,4]], rowShift = [1,0], colShift = [0,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[2,4],[3,1]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The <code>grid</code> changes as follows:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4052.Cyclically%20Shift%20Rows%20and%20Columns/images/4743-1.png" style="width: 760px; height: 92px;" /></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, <code>grid</code> = [[1,2,3],[4,5,6],[7,8,9]], rowShift = [1,2,0], colShift = [2,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[7,8,5],[2,3,9],[6,4,1]]</span></p>

<p><strong>Explanation:</strong></p>

<p>The <code>grid</code> changes as follows:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4052.Cyclically%20Shift%20Rows%20and%20Columns/images/4743-2.png" style="width: 750px; height: 349px;" /></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == grid.length == grid[i].length &lt;= 10</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 100</code></li>
	<li><code>rowShift.length == colShift.length == n</code></li>
	<li><code>0 &lt;= rowShift[i], colShift[i] &lt; n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

<!-- thinking:start -->

> **Thinking**
>
> $n \le 10$, so applying the two shifts exactly as stated is enough. There is no need to fold the mapping into a single index formula first.
>
> Rows must move left before columns move up, and the upward shift uses the **new** column index. $\textit{colShift}$ cannot be applied with the original $j$.
>
> We therefore keep an intermediate grid for the row shifts, then write the column shifts into the answer.

<!-- thinking:end -->

The problem asks us to cyclically shift each row left according to $\textit{rowShift}$, then cyclically shift each column up according to $\textit{colShift}$.

Create an intermediate matrix $t$. After a left cyclic shift of $\textit{rowShift}[i]$, the entry $\textit{grid}[i][j]$ lands at

$$
t[i][(j - \textit{rowShift}[i] + n) \bmod n]
$$

Then create the answer matrix $\textit{ans}$. After an upward cyclic shift of $\textit{colShift}[j]$, $t[i][j]$ lands at

$$
\textit{ans}[(i - \textit{colShift}[j] + n) \bmod n][j]
$$

The time complexity is $O(n^2)$ and the space complexity is $O(n^2)$, where $n$ is the side length of the grid.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def cyclicShift(
        self, n: int, grid: list[list[int]], rowShift: list[int], colShift: list[int]
    ) -> list[list[int]]:
        t = [[0] * n for _ in range(n)]
        for i in range(n):
            for j in range(n):
                t[i][(j - rowShift[i] + n) % n] = grid[i][j]
        ans = [[0] * n for _ in range(n)]
        for j in range(n):
            for i in range(n):
                ans[(i - colShift[j] + n) % n][j] = t[i][j]
        return ans
```

#### Java

```java
class Solution {
    public int[][] cyclicShift(int n, int[][] grid, int[] rowShift, int[] colShift) {
        int[][] t = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                t[i][(j - rowShift[i] + n) % n] = grid[i][j];
            }
        }
        int[][] ans = new int[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                ans[(i - colShift[j] + n) % n][j] = t[i][j];
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
    vector<vector<int>> cyclicShift(int n, vector<vector<int>>& grid, vector<int>& rowShift, vector<int>& colShift) {
        vector<vector<int>> t(n, vector<int>(n));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                t[i][(j - rowShift[i] + n) % n] = grid[i][j];
            }
        }
        vector<vector<int>> ans(n, vector<int>(n));
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                ans[(i - colShift[j] + n) % n][j] = t[i][j];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func cyclicShift(n int, grid [][]int, rowShift []int, colShift []int) [][]int {
	t := make([][]int, n)
	for i := range t {
		t[i] = make([]int, n)
	}
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			t[i][(j-rowShift[i]+n)%n] = grid[i][j]
		}
	}
	ans := make([][]int, n)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	for j := 0; j < n; j++ {
		for i := 0; i < n; i++ {
			ans[(i-colShift[j]+n)%n][j] = t[i][j]
		}
	}
	return ans
}
```

#### TypeScript

```ts
function cyclicShift(
    n: number,
    grid: number[][],
    rowShift: number[],
    colShift: number[],
): number[][] {
    const t = Array.from({ length: n }, () => Array(n).fill(0));
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            t[i][(j - rowShift[i] + n) % n] = grid[i][j];
        }
    }
    const ans = Array.from({ length: n }, () => Array(n).fill(0));
    for (let j = 0; j < n; j++) {
        for (let i = 0; i < n; i++) {
            ans[(i - colShift[j] + n) % n][j] = t[i][j];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
