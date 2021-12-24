# [1391. Check if There is a Valid Path in a Grid](https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid)

[中文文档](/solution/1300-1399/1391.Check%20if%20There%20is%20a%20Valid%20Path%20in%20a%20Grid/README.md)

## Description

Given a <em>m</em> x <em>n</em> <code>grid</code>. Each cell of the <code>grid</code> represents a street. The street of&nbsp;<code>grid[i][j]</code> can be:

<ul>
	<li><strong>1</strong> which means a street connecting the left cell and the right cell.</li>
	<li><strong>2</strong> which means a street connecting the upper cell and the lower cell.</li>
	<li><b>3</b>&nbsp;which means a street connecting the left cell and the lower cell.</li>
	<li><b>4</b> which means a street connecting the right cell and the lower cell.</li>
	<li><b>5</b> which means a street connecting the left cell and the upper cell.</li>
	<li><b>6</b> which means a street connecting the right cell and the upper cell.</li>
</ul>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1391.Check%20if%20There%20is%20a%20Valid%20Path%20in%20a%20Grid/images/main.png" style="width: 450px; height: 708px;" /></p>

<p>You will initially start at the street of the&nbsp;upper-left cell <code>(0,0)</code>. A valid path in the grid is a path which starts from the upper left&nbsp;cell <code>(0,0)</code> and ends at the bottom-right&nbsp;cell <code>(m - 1, n - 1)</code>. <strong>The path should only follow the streets</strong>.</p>

<p><strong>Notice</strong> that you are <strong>not allowed</strong> to change any street.</p>

<p>Return <i>true</i>&nbsp;if there is a valid path in the grid or <em>false</em> otherwise.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1391.Check%20if%20There%20is%20a%20Valid%20Path%20in%20a%20Grid/images/e1.png" style="width: 455px; height: 311px;" />
<pre>
<strong>Input:</strong> grid = [[2,4,3],[6,5,2]]
<strong>Output:</strong> true
<strong>Explanation:</strong> As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1391.Check%20if%20There%20is%20a%20Valid%20Path%20in%20a%20Grid/images/e2.png" style="width: 455px; height: 293px;" />
<pre>
<strong>Input:</strong> grid = [[1,2,1],[1,2,1]]
<strong>Output:</strong> false
<strong>Explanation:</strong> As shown you the street at cell (0, 0) is not connected with any street of any other cell and you will get stuck at cell (0, 0)
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1,2]]
<strong>Output:</strong> false
<strong>Explanation:</strong> You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1,1,1,1,1,3]]
<strong>Output:</strong> true
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> grid = [[2],[2],[2],[2],[2],[2],[6]]
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 6</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def hasValidPath(self, grid: List[List[int]]) -> bool:
        m, n = len(grid), len(grid[0])
        p = list(range(m * n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def left(i, j):
            if j > 0 and grid[i][j - 1] in (1, 4, 6):
                p[find(i * n + j)] = find(i * n + j - 1)

        def right(i, j):
            if j < n - 1 and grid[i][j + 1] in (1, 3, 5):
                p[find(i * n + j)] = find(i * n + j + 1)

        def up(i, j):
            if i > 0 and grid[i - 1][j] in (2, 3, 4):
                p[find(i * n + j)] = find((i - 1) * n + j)

        def down(i, j):
            if i < m - 1 and grid[i + 1][j] in (2, 5, 6):
                p[find(i * n + j)] = find((i + 1) * n + j)

        for i in range(m):
            for j in range(n):
                e = grid[i][j]
                if e == 1:
                    left(i, j)
                    right(i, j)
                elif e == 2:
                    up(i, j)
                    down(i, j)
                elif e == 3:
                    left(i, j)
                    down(i, j)
                elif e == 4:
                    right(i, j)
                    down(i, j)
                elif e == 5:
                    left(i, j)
                    up(i, j)
                else:
                    right(i, j)
                    up(i, j)
        return find(0) == find(m * n - 1)
```

### **Java**

```java
class Solution {
    private int[] p;
    private int[][] grid;
    private int m;
    private int n;

    public boolean hasValidPath(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int e = grid[i][j];
                if (e == 1) {
                    left(i, j);
                    right(i, j);
                } else if (e == 2) {
                    up(i, j);
                    down(i, j);
                } else if (e == 3) {
                    left(i, j);
                    down(i, j);
                } else if (e == 4) {
                    right(i, j);
                    down(i, j);
                } else if (e == 5) {
                    left(i, j);
                    up(i, j);
                } else {
                    right(i, j);
                    up(i, j);
                }
            }
        }
        return find(0) == find(m * n - 1);
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void left(int i, int j) {
        if (j > 0 && (grid[i][j - 1] == 1 || grid[i][j - 1] == 4 || grid[i][j - 1] == 6)) {
            p[find(i * n + j)] = find(i * n + j - 1);
        }
    }

    private void right(int i, int j) {
        if (j < n - 1 && (grid[i][j + 1] == 1 || grid[i][j + 1] == 3 || grid[i][j + 1] == 5)) {
            p[find(i * n + j)] = find(i * n + j + 1);
        }
    }

    private void up(int i, int j) {
        if (i > 0 && (grid[i - 1][j] == 2 || grid[i - 1][j] == 3 || grid[i - 1][j] == 4)) {
            p[find(i * n + j)] = find((i - 1) * n + j);
        }
    }

    private void down(int i, int j) {
        if (i < m - 1 && (grid[i + 1][j] == 2 || grid[i + 1][j] == 5 || grid[i + 1][j] == 6)) {
            p[find(i * n + j)] = find((i + 1) * n + j);
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
