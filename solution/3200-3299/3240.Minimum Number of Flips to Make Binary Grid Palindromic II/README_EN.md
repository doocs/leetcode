---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3240.Minimum%20Number%20of%20Flips%20to%20Make%20Binary%20Grid%20Palindromic%20II/README_EN.md
rating: 2080
source: Biweekly Contest 136 Q3
tags:
    - Array
    - Two Pointers
    - Matrix
---

<!-- problem:start -->

# [3240. Minimum Number of Flips to Make Binary Grid Palindromic II](https://leetcode.com/problems/minimum-number-of-flips-to-make-binary-grid-palindromic-ii)

[中文文档](/solution/3200-3299/3240.Minimum%20Number%20of%20Flips%20to%20Make%20Binary%20Grid%20Palindromic%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> binary matrix <code>grid</code>.</p>

<p>A row or column is considered <strong>palindromic</strong> if its values read the same forward and backward.</p>

<p>You can <strong>flip</strong> any number of cells in <code>grid</code> from <code>0</code> to <code>1</code>, or from <code>1</code> to <code>0</code>.</p>

<p>Return the <strong>minimum</strong> number of cells that need to be flipped to make <strong>all</strong> rows and columns <strong>palindromic</strong>, and the total number of <code>1</code>&#39;s in <code>grid</code> <strong>divisible</strong> by <code>4</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,0,0],[0,1,0],[0,0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3240.Minimum%20Number%20of%20Flips%20to%20Make%20Binary%20Grid%20Palindromic%20II/images/image.png" style="width: 400px; height: 105px;" /></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[0,1],[0,1],[0,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3240.Minimum%20Number%20of%20Flips%20to%20Make%20Binary%20Grid%20Palindromic%20II/images/screenshot-from-2024-07-09-01-37-48.png" style="width: 300px; height: 104px;" /></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1],[1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3240.Minimum%20Number%20of%20Flips%20to%20Make%20Binary%20Grid%20Palindromic%20II/images/screenshot-from-2024-08-01-23-05-26.png" style="width: 200px; height: 70px;" /></p>
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

### Solution 1: Case Analysis

If both rows and columns are palindromic, then for any $i \in [0, m / 2)$ and $j \in [0, n / 2)$, it must satisfy $\text{grid}[i][j] = \text{grid}[m - i - 1][j] = \text{grid}[i][n - j - 1] = \text{grid}[m - i - 1][n - j - 1]$. They must either all become $0$ or all become $1$. The number of changes to $0$ is $c_0 = \text{grid}[i][j] + \text{grid}[m - i - 1][j] + \text{grid}[i][n - j - 1] + \text{grid}[m - i - 1][n - j - 1]$, and the number of changes to $1$ is $c_1 = 4 - c_0$. We take the minimum of the two and add it to the answer.

Next, we discuss the parity of $m$ and $n$:

- If both $m$ and $n$ are even, we directly return the answer.
- If both $m$ and $n$ are odd, the center cell must be $0$ because the number of $1$s must be divisible by $4$.
- If $m$ is odd and $n$ is even, we need to consider the middle row.
- If $m$ is even and $n$ is odd, we need to consider the middle column.

For the latter two cases, we need to count the number of differing pairs of cells $\text{diff}$ in the middle row or column, and the number of cells that are the same and equal to $1$ $\text{cnt1}$. Then we discuss the following cases:

- If $\text{cnt1} \bmod 4 = 0$, we only need to change the $\text{diff}$ cells to $0$, and the number of operations is $\text{diff                }$.
- Otherwise, if $\text{cnt1} = 2$, if $\text{diff} \gt 0$, we can change one of the cells to $1$ to make $\text{cnt1} = 4$, and then change the remaining $\text{diff} - 1$ cells to $0$. The total number of operations is $\text{diff}$.
- Otherwise, if $\text{diff} = 0$, we change the $2$ cells to $0$ to make $\text{cnt1} \bmod 4 = 0$, and the number of operations is $2$.

We add the number of operations to the answer and finally return the answer.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the matrix, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minFlips(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        ans = 0
        for i in range(m // 2):
            for j in range(n // 2):
                x, y = m - i - 1, n - j - 1
                cnt1 = grid[i][j] + grid[x][j] + grid[i][y] + grid[x][y]
                ans += min(cnt1, 4 - cnt1)
        if m % 2 and n % 2:
            ans += grid[m // 2][n // 2]
        diff = cnt1 = 0
        if m % 2:
            for j in range(n // 2):
                if grid[m // 2][j] == grid[m // 2][n - j - 1]:
                    cnt1 += grid[m // 2][j] * 2
                else:
                    diff += 1
        if n % 2:
            for i in range(m // 2):
                if grid[i][n // 2] == grid[m - i - 1][n // 2]:
                    cnt1 += grid[i][n // 2] * 2
                else:
                    diff += 1
        ans += diff if cnt1 % 4 == 0 or diff else 2
        return ans
```

#### Java

```java
class Solution {
    public int minFlips(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m / 2; ++i) {
            for (int j = 0; j < n / 2; ++j) {
                int x = m - i - 1, y = n - j - 1;
                int cnt1 = grid[i][j] + grid[x][j] + grid[i][y] + grid[x][y];
                ans += Math.min(cnt1, 4 - cnt1);
            }
        }
        if (m % 2 == 1 && n % 2 == 1) {
            ans += grid[m / 2][n / 2];
        }

        int diff = 0, cnt1 = 0;
        if (m % 2 == 1) {
            for (int j = 0; j < n / 2; ++j) {
                if (grid[m / 2][j] == grid[m / 2][n - j - 1]) {
                    cnt1 += grid[m / 2][j] * 2;
                } else {
                    diff += 1;
                }
            }
        }
        if (n % 2 == 1) {
            for (int i = 0; i < m / 2; ++i) {
                if (grid[i][n / 2] == grid[m - i - 1][n / 2]) {
                    cnt1 += grid[i][n / 2] * 2;
                } else {
                    diff += 1;
                }
            }
        }
        ans += cnt1 % 4 == 0 || diff > 0 ? diff : 2;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minFlips(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int ans = 0;
        for (int i = 0; i < m / 2; ++i) {
            for (int j = 0; j < n / 2; ++j) {
                int x = m - i - 1, y = n - j - 1;
                int cnt1 = grid[i][j] + grid[x][j] + grid[i][y] + grid[x][y];
                ans += min(cnt1, 4 - cnt1);
            }
        }
        if (m % 2 == 1 && n % 2 == 1) {
            ans += grid[m / 2][n / 2];
        }

        int diff = 0, cnt1 = 0;
        if (m % 2 == 1) {
            for (int j = 0; j < n / 2; ++j) {
                if (grid[m / 2][j] == grid[m / 2][n - j - 1]) {
                    cnt1 += grid[m / 2][j] * 2;
                } else {
                    diff += 1;
                }
            }
        }
        if (n % 2 == 1) {
            for (int i = 0; i < m / 2; ++i) {
                if (grid[i][n / 2] == grid[m - i - 1][n / 2]) {
                    cnt1 += grid[i][n / 2] * 2;
                } else {
                    diff += 1;
                }
            }
        }
        ans += cnt1 % 4 == 0 || diff > 0 ? diff : 2;
        return ans;
    }
};
```

#### Go

```go
func minFlips(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	ans := 0

	for i := 0; i < m/2; i++ {
		for j := 0; j < n/2; j++ {
			x, y := m-i-1, n-j-1
			cnt1 := grid[i][j] + grid[x][j] + grid[i][y] + grid[x][y]
			ans += min(cnt1, 4-cnt1)
		}
	}

	if m%2 == 1 && n%2 == 1 {
		ans += grid[m/2][n/2]
	}

	diff, cnt1 := 0, 0

	if m%2 == 1 {
		for j := 0; j < n/2; j++ {
			if grid[m/2][j] == grid[m/2][n-j-1] {
				cnt1 += grid[m/2][j] * 2
			} else {
				diff += 1
			}
		}
	}

	if n%2 == 1 {
		for i := 0; i < m/2; i++ {
			if grid[i][n/2] == grid[m-i-1][n/2] {
				cnt1 += grid[i][n/2] * 2
			} else {
				diff += 1
			}
		}
	}

	if cnt1%4 == 0 || diff > 0 {
		ans += diff
	} else {
		ans += 2
	}

	return ans
}
```

#### TypeScript

```ts
function minFlips(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let ans = 0;

    for (let i = 0; i < Math.floor(m / 2); i++) {
        for (let j = 0; j < Math.floor(n / 2); j++) {
            const x = m - i - 1;
            const y = n - j - 1;
            const cnt1 = grid[i][j] + grid[x][j] + grid[i][y] + grid[x][y];
            ans += Math.min(cnt1, 4 - cnt1);
        }
    }

    if (m % 2 === 1 && n % 2 === 1) {
        ans += grid[Math.floor(m / 2)][Math.floor(n / 2)];
    }

    let diff = 0,
        cnt1 = 0;

    if (m % 2 === 1) {
        for (let j = 0; j < Math.floor(n / 2); j++) {
            if (grid[Math.floor(m / 2)][j] === grid[Math.floor(m / 2)][n - j - 1]) {
                cnt1 += grid[Math.floor(m / 2)][j] * 2;
            } else {
                diff += 1;
            }
        }
    }

    if (n % 2 === 1) {
        for (let i = 0; i < Math.floor(m / 2); i++) {
            if (grid[i][Math.floor(n / 2)] === grid[m - i - 1][Math.floor(n / 2)]) {
                cnt1 += grid[i][Math.floor(n / 2)] * 2;
            } else {
                diff += 1;
            }
        }
    }

    ans += cnt1 % 4 === 0 || diff > 0 ? diff : 2;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
