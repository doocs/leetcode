---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3212.Count%20Submatrices%20With%20Equal%20Frequency%20of%20X%20and%20Y/README_EN.md
tags:
    - Array
    - Matrix
    - Prefix Sum
---

<!-- problem:start -->

# [3212. Count Submatrices With Equal Frequency of X and Y](https://leetcode.com/problems/count-submatrices-with-equal-frequency-of-x-and-y)

[中文文档](/solution/3200-3299/3212.Count%20Submatrices%20With%20Equal%20Frequency%20of%20X%20and%20Y/README.md)

## Description

<!-- description:start -->

<p>Given a 2D character matrix <code>grid</code>, where <code>grid[i][j]</code> is either <code>&#39;X&#39;</code>, <code>&#39;Y&#39;</code>, or <code>&#39;.&#39;</code>, return the number of <span data-keyword="submatrix">submatrices</span> that contain:</p>

<ul>
	<li><code>grid[0][0]</code></li>
	<li>an <strong>equal</strong> frequency of <code>&#39;X&#39;</code> and <code>&#39;Y&#39;</code>.</li>
	<li><strong>at least</strong> one <code>&#39;X&#39;</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[&quot;X&quot;,&quot;Y&quot;,&quot;.&quot;],[&quot;Y&quot;,&quot;.&quot;,&quot;.&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3212.Count%20Submatrices%20With%20Equal%20Frequency%20of%20X%20and%20Y/images/examplems.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 175px; height: 350px;" /></strong></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;Y&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>No submatrix has an equal frequency of <code>&#39;X&#39;</code> and <code>&#39;Y&#39;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[&quot;.&quot;,&quot;.&quot;],[&quot;.&quot;,&quot;.&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>No submatrix has at least one <code>&#39;X&#39;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= grid.length, grid[i].length &lt;= 1000</code></li>
	<li><code>grid[i][j]</code> is either <code>&#39;X&#39;</code>, <code>&#39;Y&#39;</code>, or <code>&#39;.&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: 2D Prefix Sum

According to the problem description, we only need to calculate the prefix sums $s[i][j][0]$ and $s[i][j][1]$ for each position $(i, j)$, which represent the number of characters `X` and `Y` in the submatrix from $(0, 0)$ to $(i, j)$, respectively. If $s[i][j][0] > 0$ and $s[i][j][0] = s[i][j][1]$, it means the condition is met, and we increment the answer by one.

After traversing all positions, return the answer.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ represent the number of rows and columns of the matrix, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfSubmatrices(self, grid: List[List[str]]) -> int:
        m, n = len(grid), len(grid[0])
        s = [[[0] * 2 for _ in range(n + 1)] for _ in range(m + 1)]
        ans = 0
        for i, row in enumerate(grid, 1):
            for j, x in enumerate(row, 1):
                s[i][j][0] = s[i - 1][j][0] + s[i][j - 1][0] - s[i - 1][j - 1][0]
                s[i][j][1] = s[i - 1][j][1] + s[i][j - 1][1] - s[i - 1][j - 1][1]
                if x != ".":
                    s[i][j][ord(x) & 1] += 1
                if s[i][j][0] > 0 and s[i][j][0] == s[i][j][1]:
                    ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][] s = new int[m + 1][n + 1][2];
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                s[i][j][0] = s[i - 1][j][0] + s[i][j - 1][0] - s[i - 1][j - 1][0]
                    + (grid[i - 1][j - 1] == 'X' ? 1 : 0);
                s[i][j][1] = s[i - 1][j][1] + s[i][j - 1][1] - s[i - 1][j - 1][1]
                    + (grid[i - 1][j - 1] == 'Y' ? 1 : 0);
                if (s[i][j][0] > 0 && s[i][j][0] == s[i][j][1]) {
                    ++ans;
                }
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
    int numberOfSubmatrices(vector<vector<char>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<vector<int>>> s(m + 1, vector<vector<int>>(n + 1, vector<int>(2)));
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                s[i][j][0] = s[i - 1][j][0] + s[i][j - 1][0] - s[i - 1][j - 1][0]
                    + (grid[i - 1][j - 1] == 'X' ? 1 : 0);
                s[i][j][1] = s[i - 1][j][1] + s[i][j - 1][1] - s[i - 1][j - 1][1]
                    + (grid[i - 1][j - 1] == 'Y' ? 1 : 0);
                if (s[i][j][0] > 0 && s[i][j][0] == s[i][j][1]) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfSubmatrices(grid [][]byte) (ans int) {
	m, n := len(grid), len(grid[0])
	s := make([][][]int, m+1)
	for i := range s {
		s[i] = make([][]int, n+1)
		for j := range s[i] {
			s[i][j] = make([]int, 2)
		}
	}

	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			s[i][j][0] = s[i-1][j][0] + s[i][j-1][0] - s[i-1][j-1][0]
			if grid[i-1][j-1] == 'X' {
				s[i][j][0]++
			}
			s[i][j][1] = s[i-1][j][1] + s[i][j-1][1] - s[i-1][j-1][1]
			if grid[i-1][j-1] == 'Y' {
				s[i][j][1]++
			}
			if s[i][j][0] > 0 && s[i][j][0] == s[i][j][1] {
				ans++
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function numberOfSubmatrices(grid: string[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    const s = Array.from({ length: m + 1 }, () => Array.from({ length: n + 1 }, () => [0, 0]));
    let ans = 0;

    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            s[i][j][0] =
                s[i - 1][j][0] +
                s[i][j - 1][0] -
                s[i - 1][j - 1][0] +
                (grid[i - 1][j - 1] === 'X' ? 1 : 0);
            s[i][j][1] =
                s[i - 1][j][1] +
                s[i][j - 1][1] -
                s[i - 1][j - 1][1] +
                (grid[i - 1][j - 1] === 'Y' ? 1 : 0);
            if (s[i][j][0] > 0 && s[i][j][0] === s[i][j][1]) {
                ++ans;
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
