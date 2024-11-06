---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2267.Check%20if%20There%20Is%20a%20Valid%20Parentheses%20String%20Path/README_EN.md
rating: 2084
source: Weekly Contest 292 Q4
tags:
    - Array
    - Dynamic Programming
    - Matrix
---

<!-- problem:start -->

# [2267. Check if There Is a Valid Parentheses String Path](https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path)

[中文文档](/solution/2200-2299/2267.Check%20if%20There%20Is%20a%20Valid%20Parentheses%20String%20Path/README.md)

## Description

<!-- description:start -->

<p>A parentheses string is a <strong>non-empty</strong> string consisting only of <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code>. It is <strong>valid</strong> if <strong>any</strong> of the following conditions is <strong>true</strong>:</p>

<ul>
	<li>It is <code>()</code>.</li>
	<li>It can be written as <code>AB</code> (<code>A</code> concatenated with <code>B</code>), where <code>A</code> and <code>B</code> are valid parentheses strings.</li>
	<li>It can be written as <code>(A)</code>, where <code>A</code> is a valid parentheses string.</li>
</ul>

<p>You are given an <code>m x n</code> matrix of parentheses <code>grid</code>. A <strong>valid parentheses string path</strong> in the grid is a path satisfying <strong>all</strong> of the following conditions:</p>

<ul>
	<li>The path starts from the upper left cell <code>(0, 0)</code>.</li>
	<li>The path ends at the bottom-right cell <code>(m - 1, n - 1)</code>.</li>
	<li>The path only ever moves <strong>down</strong> or <strong>right</strong>.</li>
	<li>The resulting parentheses string formed by the path is <strong>valid</strong>.</li>
</ul>

<p>Return <code>true</code> <em>if there exists a <strong>valid parentheses string path</strong> in the grid.</em> Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2267.Check%20if%20There%20Is%20a%20Valid%20Parentheses%20String%20Path/images/example1drawio.png" style="width: 521px; height: 300px;" />
<pre>
<strong>Input:</strong> grid = [[&quot;(&quot;,&quot;(&quot;,&quot;(&quot;],[&quot;)&quot;,&quot;(&quot;,&quot;)&quot;],[&quot;(&quot;,&quot;(&quot;,&quot;)&quot;],[&quot;(&quot;,&quot;(&quot;,&quot;)&quot;]]
<strong>Output:</strong> true
<strong>Explanation:</strong> The above diagram shows two possible paths that form valid parentheses strings.
The first path shown results in the valid parentheses string &quot;()(())&quot;.
The second path shown results in the valid parentheses string &quot;((()))&quot;.
Note that there may be other valid parentheses string paths.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2267.Check%20if%20There%20Is%20a%20Valid%20Parentheses%20String%20Path/images/example2drawio.png" style="width: 165px; height: 165px;" />
<pre>
<strong>Input:</strong> grid = [[&quot;)&quot;,&quot;)&quot;],[&quot;(&quot;,&quot;(&quot;]]
<strong>Output:</strong> false
<strong>Explanation:</strong> The two possible paths form the parentheses strings &quot;))(&quot; and &quot;)((&quot;. Since neither of them are valid parentheses strings, we return false.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>grid[i][j]</code> is either <code>&#39;(&#39;</code> or <code>&#39;)&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS + Pruning

Let $m$ be the number of rows and $n$ be the number of columns in the matrix.

If $m + n - 1$ is odd, or the parentheses in the top-left and bottom-right corners do not match, then there is no valid path, and we directly return $\text{false}$.

Otherwise, we design a function $\textit{dfs}(i, j, k)$, which represents whether there is a valid path starting from $(i, j)$ with the current balance of parentheses being $k$. The balance $k$ is defined as the number of left parentheses minus the number of right parentheses in the path from $(0, 0)$ to $(i, j)$.

If the balance $k$ is less than $0$ or greater than $m + n - i - j$, then there is no valid path, and we directly return $\text{false}$. If $(i, j)$ is the bottom-right cell, then there is a valid path only if $k = 0$. Otherwise, we enumerate the next cell $(x, y)$ of $(i, j)$. If $(x, y)$ is a valid cell and $\textit{dfs}(x, y, k)$ is $\text{true}$, then there is a valid path.

The time complexity is $O(m \times n \times (m + n))$, and the space complexity is $O(m \times n \times (m + n))$. Here, $m$ and $n$ are the number of rows and columns in the matrix, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hasValidPath(self, grid: List[List[str]]) -> bool:
        @cache
        def dfs(i: int, j: int, k: int) -> bool:
            d = 1 if grid[i][j] == "(" else -1
            k += d
            if k < 0 or k > m - i + n - j:
                return False
            if i == m - 1 and j == n - 1:
                return k == 0
            for a, b in pairwise((0, 1, 0)):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and dfs(x, y, k):
                    return True
            return False

        m, n = len(grid), len(grid[0])
        if (m + n - 1) % 2 or grid[0][0] == ")" or grid[m - 1][n - 1] == "(":
            return False
        return dfs(0, 0, 0)
```

#### Java

```java
class Solution {
    private int m, n;
    private char[][] grid;
    private boolean[][][] vis;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        if ((m + n - 1) % 2 == 1 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        this.grid = grid;
        vis = new boolean[m][n][m + n];
        return dfs(0, 0, 0);
    }

    private boolean dfs(int i, int j, int k) {
        if (vis[i][j][k]) {
            return false;
        }
        vis[i][j][k] = true;
        k += grid[i][j] == '(' ? 1 : -1;
        if (k < 0 || k > m - i + n - j) {
            return false;
        }
        if (i == m - 1 && j == n - 1) {
            return k == 0;
        }
        final int[] dirs = {1, 0, 1};
        for (int d = 0; d < 2; ++d) {
            int x = i + dirs[d], y = j + dirs[d + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && dfs(x, y, k)) {
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
    bool hasValidPath(vector<vector<char>>& grid) {
        int m = grid.size(), n = grid[0].size();
        if ((m + n - 1) % 2 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        bool vis[m][n][m + n];
        memset(vis, false, sizeof(vis));
        int dirs[3] = {1, 0, 1};
        auto dfs = [&](auto&& dfs, int i, int j, int k) -> bool {
            if (vis[i][j][k]) {
                return false;
            }
            vis[i][j][k] = true;
            k += grid[i][j] == '(' ? 1 : -1;
            if (k < 0 || k > m - i + n - j) {
                return false;
            }
            if (i == m - 1 && j == n - 1) {
                return k == 0;
            }
            for (int d = 0; d < 2; ++d) {
                int x = i + dirs[d], y = j + dirs[d + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && dfs(dfs, x, y, k)) {
                    return true;
                }
            }
            return false;
        };
        return dfs(dfs, 0, 0, 0);
    }
};
```

#### Go

```go
func hasValidPath(grid [][]byte) bool {
	m, n := len(grid), len(grid[0])
	if (m+n-1)%2 == 1 || grid[0][0] == ')' || grid[m-1][n-1] == '(' {
		return false
	}
	vis := make([][][]bool, m)
	for i := range vis {
		vis[i] = make([][]bool, n)
		for j := range vis[i] {
			vis[i][j] = make([]bool, m+n)
		}
	}
	dirs := [3]int{1, 0, 1}
	var dfs func(i, j, k int) bool
	dfs = func(i, j, k int) bool {
		if vis[i][j][k] {
			return false
		}
		vis[i][j][k] = true
		if grid[i][j] == '(' {
			k++
		} else {
			k--
		}
		if k < 0 || k > m-i+n-j {
			return false
		}
		if i == m-1 && j == n-1 {
			return k == 0
		}
		for d := 0; d < 2; d++ {
			x, y := i+dirs[d], j+dirs[d+1]
			if x >= 0 && x < m && y >= 0 && y < n && dfs(x, y, k) {
				return true
			}
		}
		return false
	}
	return dfs(0, 0, 0)
}
```

#### TypeScript

```ts
function hasValidPath(grid: string[][]): boolean {
    const m = grid.length,
        n = grid[0].length;

    if ((m + n - 1) % 2 || grid[0][0] === ')' || grid[m - 1][n - 1] === '(') {
        return false;
    }

    const vis: boolean[][][] = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => Array(m + n).fill(false)),
    );
    const dirs = [1, 0, 1];

    const dfs = (i: number, j: number, k: number): boolean => {
        if (vis[i][j][k]) {
            return false;
        }

        vis[i][j][k] = true;
        k += grid[i][j] === '(' ? 1 : -1;

        if (k < 0 || k > m - i + n - j) {
            return false;
        }

        if (i === m - 1 && j === n - 1) {
            return k === 0;
        }

        for (let d = 0; d < 2; ++d) {
            const x = i + dirs[d],
                y = j + dirs[d + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && dfs(x, y, k)) {
                return true;
            }
        }

        return false;
    };

    return dfs(0, 0, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
