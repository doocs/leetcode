# [2257. Count Unguarded Cells in the Grid](https://leetcode.com/problems/count-unguarded-cells-in-the-grid)

[中文文档](/solution/2200-2299/2257.Count%20Unguarded%20Cells%20in%20the%20Grid/README.md)

## Description

<p>You are given two integers <code>m</code> and <code>n</code> representing a <strong>0-indexed</strong> <code>m x n</code> grid. You are also given two 2D integer arrays <code>guards</code> and <code>walls</code> where <code>guards[i] = [row<sub>i</sub>, col<sub>i</sub>]</code> and <code>walls[j] = [row<sub>j</sub>, col<sub>j</sub>]</code> represent the positions of the <code>i<sup>th</sup></code> guard and <code>j<sup>th</sup></code> wall respectively.</p>

<p>A guard can see <b>every</b> cell in the four cardinal directions (north, east, south, or west) starting from their position unless <strong>obstructed</strong> by a wall or another guard. A cell is <strong>guarded</strong> if there is <strong>at least</strong> one guard that can see it.</p>

<p>Return<em> the number of unoccupied cells that are <strong>not</strong> <strong>guarded</strong>.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2257.Count%20Unguarded%20Cells%20in%20the%20Grid/images/example1drawio2.png" style="width: 300px; height: 204px;" />
<pre>
<strong>Input:</strong> m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
<strong>Output:</strong> 7
<strong>Explanation:</strong> The guarded and unguarded cells are shown in red and green respectively in the above diagram.
There are a total of 7 unguarded cells, so we return 7.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2257.Count%20Unguarded%20Cells%20in%20the%20Grid/images/example2drawio.png" style="width: 200px; height: 201px;" />
<pre>
<strong>Input:</strong> m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The unguarded cells are shown in green in the above diagram.
There are a total of 4 unguarded cells, so we return 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= guards.length, walls.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>2 &lt;= guards.length + walls.length &lt;= m * n</code></li>
	<li><code>guards[i].length == walls[j].length == 2</code></li>
	<li><code>0 &lt;= row<sub>i</sub>, row<sub>j</sub> &lt; m</code></li>
	<li><code>0 &lt;= col<sub>i</sub>, col<sub>j</sub> &lt; n</code></li>
	<li>All the positions in <code>guards</code> and <code>walls</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countUnguarded(
        self, m: int, n: int, guards: List[List[int]], walls: List[List[int]]
    ) -> int:
        g = [[None] * n for _ in range(m)]
        for r, c in guards:
            g[r][c] = 'g'
        for r, c in walls:
            g[r][c] = 'w'
        for i, j in guards:
            for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                x, y = i, j
                while (
                    0 <= x + a < m
                    and 0 <= y + b < n
                    and g[x + a][y + b] != 'w'
                    and g[x + a][y + b] != 'g'
                ):
                    x, y = x + a, y + b
                    g[x][y] = 'v'
        return sum(not v for row in g for v in row)
```

### **Java**

```java
class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        char[][] g = new char[m][n];
        for (int[] e : guards) {
            int r = e[0], c = e[1];
            g[r][c] = 'g';
        }
        for (int[] e : walls) {
            int r = e[0], c = e[1];
            g[r][c] = 'w';
        }
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        for (int[] p : guards) {
            for (int[] dir : dirs) {
                int a = dir[0], b = dir[1];
                int x = p[0], y = p[1];
                while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && g[x + a][y + b] != 'w' && g[x + a][y + b] != 'g') {
                    x += a;
                    y += b;
                    g[x][y] = 'v';
                }
            }
        }
        int ans = 0;
        for (char[] row : g) {
            for (char v : row) {
                if (v == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countUnguarded(int m, int n, vector<vector<int>>& guards, vector<vector<int>>& walls) {
        vector<vector<char>> g(m, vector<char>(n));
        for (auto& e : guards) g[e[0]][e[1]] = 'g';
        for (auto& e : walls) g[e[0]][e[1]] = 'w';
        vector<vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (auto& p : guards) {
            for (auto& dir : dirs) {
                int a = dir[0], b = dir[1];
                int x = p[0], y = p[1];
                while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && g[x + a][y + b] != 'w' && g[x + a][y + b] != 'g') {
                    x += a;
                    y += b;
                    g[x][y] = 'v';
                }
            }
        }
        int ans = 0;
        for (auto& row : g)
            for (auto& v : row)
                ans += v == 0;
        return ans;
    }
};
```

### **Go**

```go
func countUnguarded(m int, n int, guards [][]int, walls [][]int) int {
	g := make([][]int, m)
	for i := range g {
		g[i] = make([]int, n)
	}
	for _, e := range guards {
		g[e[0]][e[1]] = 1
	}
	for _, e := range walls {
		g[e[0]][e[1]] = 2
	}
	dirs := [][]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for _, p := range guards {
		for _, dir := range dirs {
			a, b := dir[0], dir[1]
			x, y := p[0], p[1]
			for x+a >= 0 && x+a < m && y+b >= 0 && y+b < n && g[x+a][y+b] != 1 && g[x+a][y+b] != 2 {
				x, y = x+a, y+b
				g[x][y] = 3
			}
		}
	}
	ans := 0
	for _, row := range g {
		for _, v := range row {
			if v == 0 {
				ans++
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
