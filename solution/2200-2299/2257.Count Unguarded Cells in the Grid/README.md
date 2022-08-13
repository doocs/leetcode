# [2257. 统计网格图中没有被保卫的格子数](https://leetcode.cn/problems/count-unguarded-cells-in-the-grid)

[English Version](/solution/2200-2299/2257.Count%20Unguarded%20Cells%20in%20the%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数&nbsp;<code>m</code>&nbsp;和&nbsp;<code>n</code>&nbsp;表示一个下标从<strong>&nbsp;0</strong>&nbsp;开始的&nbsp;<code>m x n</code>&nbsp;网格图。同时给你两个二维整数数组&nbsp;<code>guards</code> 和&nbsp;<code>walls</code>&nbsp;，其中&nbsp;<code>guards[i] = [row<sub>i</sub>, col<sub>i</sub>]</code>&nbsp;且&nbsp;<code>walls[j] = [row<sub>j</sub>, col<sub>j</sub>]</code>&nbsp;，分别表示第 <code>i</code>&nbsp;个警卫和第 <code>j</code>&nbsp;座墙所在的位置。</p>

<p>一个警卫能看到 4 个坐标轴方向（即东、南、西、北）的 <strong>所有</strong>&nbsp;格子，除非他们被一座墙或者另外一个警卫 <strong>挡住</strong>&nbsp;了视线。如果一个格子能被 <strong>至少</strong>&nbsp;一个警卫看到，那么我们说这个格子被 <strong>保卫</strong>&nbsp;了。</p>

<p>请你返回空格子中，有多少个格子是 <strong>没被保卫</strong>&nbsp;的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2257.Count%20Unguarded%20Cells%20in%20the%20Grid/images/example1drawio2.png" style="width: 300px; height: 204px;"></p>

<pre><b>输入：</b>m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
<b>输出：</b>7
<strong>解释：</strong>上图中，被保卫和没有被保卫的格子分别用红色和绿色表示。
总共有 7 个没有被保卫的格子，所以我们返回 7 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2257.Count%20Unguarded%20Cells%20in%20the%20Grid/images/example2drawio.png" style="width: 200px; height: 201px;"></p>

<pre><b>输入：</b>m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
<b>输出：</b>4
<b>解释：</b>上图中，没有被保卫的格子用绿色表示。
总共有 4 个没有被保卫的格子，所以我们返回 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= guards.length, walls.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>2 &lt;= guards.length + walls.length &lt;= m * n</code></li>
	<li><code>guards[i].length == walls[j].length == 2</code></li>
	<li><code>0 &lt;= row<sub>i</sub>, row<sub>j</sub> &lt; m</code></li>
	<li><code>0 &lt;= col<sub>i</sub>, col<sub>j</sub> &lt; n</code></li>
	<li><code>guards</code>&nbsp;和&nbsp;<code>walls</code>&nbsp;中所有位置 <strong>互不相同</strong>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
