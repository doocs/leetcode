---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3963.Create%20Grid%20With%20Exactly%20One%20Path/README_EN.md
rating: 1310
source: Biweekly Contest 185 Q1
---

<!-- problem:start -->

# [3963. Create Grid With Exactly One Path](https://leetcode.com/problems/create-grid-with-exactly-one-path)

[中文文档](/solution/3900-3999/3963.Create%20Grid%20With%20Exactly%20One%20Path/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>m</code> and <code>n</code>, representing the number of rows and columns of a grid.</p>

<p>Construct <strong>any</strong> <code>m x n</code> grid consisting only of the characters <code>&#39;.&#39;</code> and <code>&#39;#&#39;</code>, where:</p>

<ul>
	<li><code>&#39;.&#39;</code> represents a free cell.</li>
	<li><code>&#39;#&#39;</code> represents an obstacle cell.</li>
</ul>

<p>A <strong>valid path</strong> is a sequence of free cells that:</p>

<ul>
	<li>Starts at the top-left cell <code>(0, 0)</code>.</li>
	<li>Ends at the bottom-right cell <code>(m - 1, n - 1)</code>.</li>
	<li>Moves only:
	<ul>
		<li>Right, from <code>(i, j)</code> to <code>(i, j + 1)</code>, or</li>
		<li>Down, from <code>(i, j)</code> to <code>(i + 1, j)</code>.</li>
	</ul>
	</li>
</ul>

<p>Return any grid such that there is <strong>exactly one valid path</strong> from the top-left cell to the bottom-right cell.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 2, n = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;..#&quot;,&quot;#..&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3963.Create%20Grid%20With%20Exactly%20One%20Path/images/screenshot-2026-05-26-at-61005pm.png" style="width: 200px; height: 95px;" /></p>

<p>The only valid path is: <code>(0,0) &rarr; (0,1) &rarr; (1,1) &rarr; (1,2)</code></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 3, n = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;..#&quot;,&quot;#..&quot;,&quot;##.&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3963.Create%20Grid%20With%20Exactly%20One%20Path/images/screenshot-2026-05-26-at-61129pm.png" style="width: 220px; height: 150px;" /></p>

<p>The only valid path is: <code>(0,0) &rarr; (0,1) &rarr; (1,1) &rarr; (1,2) &rarr; (2,2)</code></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 1, n = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;....&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<p>The only valid path is: <code>(0,0) &rarr; (0,1) &rarr; (0,2) &rarr; (0,3)</code></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 25</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Construction

We construct the grid as follows:

- First, construct a grid filled entirely with `#`.
- Set all elements in the first row to `.`.
- Set all elements in the last column to `.`.
- Return the constructed grid.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the number of rows and columns in the grid, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def createGrid(self, m: int, n: int) -> list[str]:
        g = [["#"] * n for _ in range(m)]
        g[0] = ["."] * n
        for i in range(m):
            g[i][-1] = "."
        return ["".join(row) for row in g]
```

#### Java

```java
class Solution {
    public String[] createGrid(int m, int n) {
        char[][] g = new char[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(g[i], '#');
        }

        Arrays.fill(g[0], '.');

        for (int i = 0; i < m; i++) {
            g[i][n - 1] = '.';
        }

        String[] ans = new String[m];
        for (int i = 0; i < m; i++) {
            ans[i] = new String(g[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> createGrid(int m, int n) {
        vector<string> g(m, string(n, '#'));

        g[0] = string(n, '.');

        for (int i = 0; i < m; i++) {
            g[i][n - 1] = '.';
        }

        return g;
    }
};
```

#### Go

```go
func createGrid(m int, n int) []string {
    g := make([][]byte, m)
    for i := range g {
        g[i] = make([]byte, n)
        for j := range g[i] {
            g[i][j] = '#'
        }
    }

    for j := 0; j < n; j++ {
        g[0][j] = '.'
    }

    for i := 0; i < m; i++ {
        g[i][n-1] = '.'
    }

    ans := make([]string, m)
    for i := range g {
        ans[i] = string(g[i])
    }
    return ans
}
```

#### TypeScript

```ts
function createGrid(m: number, n: number): string[] {
    const g: string[][] = Array.from({ length: m }, () => Array(n).fill('#'));

    g[0].fill('.');

    for (let i = 0; i < m; i++) {
        g[i][n - 1] = '.';
    }

    return g.map(row => row.join(''));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
