---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2664.The%20Knight%E2%80%99s%20Tour/README_EN.md
tags:
    - Array
    - Backtracking
    - Matrix
---

# [2664. The Knight’s Tour 🔒](https://leetcode.com/problems/the-knights-tour)

[中文文档](/solution/2600-2699/2664.The%20Knight%E2%80%99s%20Tour/README.md)

## Description

<p>Given two positive integers <code>m</code> and <code>n</code> which are the height and width of a <strong>0-indexed</strong> 2D-array <code>board</code>, a pair of positive integers <code>(r, c)</code> which is the starting position of the knight on the board.</p>

<p>Your task is to find an order of movements for the knight, in a manner that every cell of the&nbsp;<code>board</code> gets visited <strong>exactly</strong> once (the starting cell is considered visited and you <strong>shouldn&#39;t</strong> visit it again).</p>

<p>Return <em>the array</em> <code>board</code> <em>in which the cells&#39; values show the order of visiting the cell starting from 0 (the initial place of the knight).</em></p>

<p>Note that a <strong>knight</strong> can <strong>move</strong> from cell <code>(r1, c1)</code> to cell <code>(r2, c2)</code> if <code>0 &lt;= r2 &lt;= m - 1</code> and <code>0 &lt;= c2 &lt;= n - 1</code> and <code>min(abs(r1 - r2), abs(c1 - c2)) = 1</code> and <code>max(abs(r1 - r2), abs(c1 - c2)) = 2</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> m = 1, n = 1, r = 0, c = 0
<strong>Output:</strong> [[0]]
<strong>Explanation:</strong> There is only 1 cell and the knight is initially on it so there is only a 0 inside the 1x1 grid.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> m = 3, n = 4, r = 0, c = 0
<strong>Output:</strong> [[0,3,6,9],[11,8,1,4],[2,5,10,7]]
<strong>Explanation:</strong> By the following order of movements we can visit the entire board.
(0,0)-&gt;(1,2)-&gt;(2,0)-&gt;(0,1)-&gt;(1,3)-&gt;(2,1)-&gt;(0,2)-&gt;(2,3)-&gt;(1,1)-&gt;(0,3)-&gt;(2,2)-&gt;(1,0)</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m,&nbsp;n &lt;= 5</code></li>
	<li><code>0 &lt;= r &lt;= m - 1</code></li>
	<li><code>0 &lt;= c &lt;= n - 1</code></li>
	<li>The inputs will be generated such that there exists at least one&nbsp;possible order of movements with the given condition</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def tourOfKnight(self, m: int, n: int, r: int, c: int) -> List[List[int]]:
        def dfs(i: int, j: int):
            nonlocal ok
            if g[i][j] == m * n - 1:
                ok = True
                return
            for a, b in pairwise((-2, -1, 2, 1, -2, 1, 2, -1, -2)):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and g[x][y] == -1:
                    g[x][y] = g[i][j] + 1
                    dfs(x, y)
                    if ok:
                        return
                    g[x][y] = -1

        g = [[-1] * n for _ in range(m)]
        g[r][c] = 0
        ok = False
        dfs(r, c)
        return g
```

```java
class Solution {
    private int[][] g;
    private int m;
    private int n;
    private boolean ok;

    public int[][] tourOfKnight(int m, int n, int r, int c) {
        this.m = m;
        this.n = n;
        this.g = new int[m][n];
        for (var row : g) {
            Arrays.fill(row, -1);
        }
        g[r][c] = 0;
        dfs(r, c);
        return g;
    }

    private void dfs(int i, int j) {
        if (g[i][j] == m * n - 1) {
            ok = true;
            return;
        }
        int[] dirs = {-2, -1, 2, 1, -2, 1, 2, -1, -2};
        for (int k = 0; k < 8; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] == -1) {
                g[x][y] = g[i][j] + 1;
                dfs(x, y);
                if (ok) {
                    return;
                }
                g[x][y] = -1;
            }
        }
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> tourOfKnight(int m, int n, int r, int c) {
        vector<vector<int>> g(m, vector<int>(n, -1));
        g[r][c] = 0;
        int dirs[9] = {-2, -1, 2, 1, -2, 1, 2, -1, -2};
        bool ok = false;
        function<void(int, int)> dfs = [&](int i, int j) {
            if (g[i][j] == m * n - 1) {
                ok = true;
                return;
            }
            for (int k = 0; k < 8; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] == -1) {
                    g[x][y] = g[i][j] + 1;
                    dfs(x, y);
                    if (ok) {
                        return;
                    }
                    g[x][y] = -1;
                }
            }
        };
        dfs(r, c);
        return g;
    }
};
```

```go
func tourOfKnight(m int, n int, r int, c int) [][]int {
	g := make([][]int, m)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = -1
		}
	}
	g[r][c] = 0
	ok := false
	var dfs func(i, j int)
	dfs = func(i, j int) {
		if g[i][j] == m*n-1 {
			ok = true
			return
		}
		dirs := []int{-2, -1, 2, 1, -2, 1, 2, -1, -2}
		for k := 0; k < 8; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && g[x][y] == -1 {
				g[x][y] = g[i][j] + 1
				dfs(x, y)
				if ok {
					return
				}
				g[x][y] = -1
			}
		}
	}
	dfs(r, c)
	return g
}
```

```ts
function tourOfKnight(m: number, n: number, r: number, c: number): number[][] {
    const g: number[][] = Array.from({ length: m }, () => Array(n).fill(-1));
    const dirs = [-2, -1, 2, 1, -2, 1, 2, -1, -2];
    let ok = false;
    const dfs = (i: number, j: number) => {
        if (g[i][j] === m * n - 1) {
            ok = true;
            return;
        }
        for (let k = 0; k < 8; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && x < m && y >= 0 && y < n && g[x][y] === -1) {
                g[x][y] = g[i][j] + 1;
                dfs(x, y);
                if (ok) {
                    return;
                }
                g[x][y] = -1;
            }
        }
    };
    g[r][c] = 0;
    dfs(r, c);
    return g;
}
```

```rust
impl Solution {
    pub fn tour_of_knight(m: i32, n: i32, r: i32, c: i32) -> Vec<Vec<i32>> {
        let mut g: Vec<Vec<i32>> = vec![vec![-1; n as usize]; m as usize];
        g[r as usize][c as usize] = 0;
        let dirs: [i32; 9] = [-2, -1, 2, 1, -2, 1, 2, -1, -2];
        let mut ok = false;

        fn dfs(
            i: usize,
            j: usize,
            g: &mut Vec<Vec<i32>>,
            m: i32,
            n: i32,
            dirs: &[i32; 9],
            ok: &mut bool
        ) {
            if g[i][j] == m * n - 1 {
                *ok = true;
                return;
            }
            for k in 0..8 {
                let x = ((i as i32) + dirs[k]) as usize;
                let y = ((j as i32) + dirs[k + 1]) as usize;
                if x < (m as usize) && y < (n as usize) && g[x][y] == -1 {
                    g[x][y] = g[i][j] + 1;
                    dfs(x, y, g, m, n, dirs, ok);
                    if *ok {
                        return;
                    }
                    g[x][y] = -1;
                }
            }
        }

        dfs(r as usize, c as usize, &mut g, m, n, &dirs, &mut ok);
        g
    }
}
```

<!-- tabs:end -->

<!-- end -->
