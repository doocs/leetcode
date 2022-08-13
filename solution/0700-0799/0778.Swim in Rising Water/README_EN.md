# [778. Swim in Rising Water](https://leetcode.com/problems/swim-in-rising-water)

[中文文档](/solution/0700-0799/0778.Swim%20in%20Rising%20Water/README.md)

## Description

<p>You are given an <code>n x n</code> integer matrix <code>grid</code> where each value <code>grid[i][j]</code> represents the elevation at that point <code>(i, j)</code>.</p>

<p>The rain starts to fall. At time <code>t</code>, the depth of the water everywhere is <code>t</code>. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most <code>t</code>. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.</p>

<p>Return <em>the least time until you can reach the bottom right square </em><code>(n - 1, n - 1)</code><em> if you start at the top left square </em><code>(0, 0)</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0778.Swim%20in%20Rising%20Water/images/swim1-grid.jpg" style="width: 164px; height: 165px;" />
<pre>
<strong>Input:</strong> grid = [[0,2],[1,3]]
<strong>Output:</strong> 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0778.Swim%20in%20Rising%20Water/images/swim2-grid-1.jpg" style="width: 404px; height: 405px;" />
<pre>
<strong>Input:</strong> grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
<strong>Output:</strong> 16
<strong>Explanation:</strong> The final route is shown.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;&nbsp;n<sup>2</sup></code></li>
	<li>Each value <code>grid[i][j]</code> is <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def swimInWater(self, grid: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = len(grid)
        p = list(range(n * n))
        hi = [0] * (n * n)
        for i, row in enumerate(grid):
            for j, h in enumerate(row):
                hi[h] = i * n + j
        for t in range(n * n):
            i, j = hi[t] // n, hi[t] % n
            for a, b in [(0, -1), (0, 1), (1, 0), (-1, 0)]:
                x, y = i + a, j + b
                if 0 <= x < n and 0 <= y < n and grid[x][y] <= t:
                    p[find(x * n + y)] = find(hi[t])
                if find(0) == find(n * n - 1):
                    return t
        return -1
```

### **Java**

```java
class Solution {
    private int[] p;

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        p = new int[n * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        int[] hi = new int[n * n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                hi[grid[i][j]] = i * n + j;
            }
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int t = 0; t < n * n; ++t) {
            int i = hi[t] / n;
            int j = hi[t] % n;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] <= t) {
                    p[find(x * n + y)] = find(i * n + j);
                }
                if (find(0) == find(n * n - 1)) {
                    return t;
                }
            }
        }
        return -1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **TypeScript**

```ts
function swimInWater(grid: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    let visited = Array.from({ length: m }, () => new Array(n).fill(false));
    let ans = 0;
    let stack = [[0, 0, grid[0][0]]];
    const dir = [
        [0, 1],
        [0, -1],
        [1, 0],
        [-1, 0],
    ];

    while (stack.length) {
        let [i, j] = stack.shift();
        ans = Math.max(grid[i][j], ans);
        if (i == m - 1 && j == n - 1) break;
        for (let [dx, dy] of dir) {
            let x = i + dx,
                y = j + dy;
            if (x < m && x > -1 && y < n && y > -1 && !visited[x][y]) {
                visited[x][y] = true;
                stack.push([x, y, grid[x][y]]);
            }
        }
        stack.sort((a, b) => a[2] - b[2]);
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    int swimInWater(vector<vector<int>>& grid) {
        int n = grid.size();
        p.resize(n * n);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<int> hi(n * n);
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                hi[grid[i][j]] = i * n + j;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int t = 0; t < n * n; ++t) {
            int i = hi[t] / n, j = hi[t] % n;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] <= t)
                    p[find(x * n + y)] = find(hi[t]);
                if (find(0) == find(n * n - 1)) return t;
            }
        }
        return -1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func swimInWater(grid [][]int) int {
	n := len(grid)
	p := make([]int, n*n)
	for i := range p {
		p[i] = i
	}
	hi := make([]int, n*n)
	for i, row := range grid {
		for j, h := range row {
			hi[h] = i*n + j
		}
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	dirs := []int{-1, 0, 1, 0, -1}
	for t := 0; t < n*n; t++ {
		i, j := hi[t]/n, hi[t]%n
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] <= t {
				p[find(x*n+y)] = find(hi[t])
			}
			if find(0) == find(n*n-1) {
				return t
			}
		}
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
