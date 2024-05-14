---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0490.The%20Maze/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 数组
    - 矩阵
---

# [490. 迷宫 🔒](https://leetcode.cn/problems/the-maze)

[English Version](/solution/0400-0499/0490.The%20Maze/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

由空地（用 <code>0</code> 表示）和墙（用 <code>1</code> 表示）组成的迷宫 <code>maze</code> 中有一个球。球可以途经空地向<strong> 上、下、左、右 </strong>四个方向滚动，且在遇到墙壁前不会停止滚动。当球停下时，可以选择向下一个方向滚动。

<div class="top-view__1vxA">
<div class="original__bRMd">
<div>
<p>给你一个大小为 <code>m x n</code> 的迷宫 <code>maze</code> ，以及球的初始位置 <code>start</code> 和目的地 <code>destination</code> ，其中 <code>start = [start<sub>row</sub>, start<sub>col</sub>]</code> 且 <code>destination = [destination<sub>row</sub>, destination<sub>col</sub>]</code> 。请你判断球能否在目的地停下：如果可以，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>你可以 <strong>假定迷宫的边缘都是墙壁</strong>（参考示例）。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0490.The%20Maze/images/maze1-1-grid.jpg" style="width: 573px; height: 573px;" />
<pre>
<strong>输入：</strong>maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
<strong>输出：</strong>true
<strong>解释：</strong>一种可能的路径是 : 左 -> 下 -> 左 -> 下 -> 右 -> 下 -> 右。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0490.The%20Maze/images/maze1-2-grid.jpg" style="width: 573px; height: 573px;" />
<pre>
<strong>输入：</strong>maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
<strong>输出：</strong>false
<strong>解释：</strong>不存在能够使球停在目的地的路径。注意，球可以经过目的地，但无法在那里停驻。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == maze.length</code></li>
	<li><code>n == maze[i].length</code></li>
	<li><code>1 <= m, n <= 100</code></li>
	<li><code>maze[i][j]</code> is <code>0</code> or <code>1</code>.</li>
	<li><code>start.length == 2</code></li>
	<li><code>destination.length == 2</code></li>
	<li><code>0 <= start<sub>row</sub>, destination<sub>row</sub> <= m</code></li>
	<li><code>0 <= start<sub>col</sub>, destination<sub>col</sub> <= n</code></li>
	<li>球和目的地都在空地上，且初始时它们不在同一位置</li>
	<li>迷宫 <strong>至少包括 2 块空地</strong></li>
</ul>
</div>
</div>
</div>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def hasPath(
        self, maze: List[List[int]], start: List[int], destination: List[int]
    ) -> bool:
        def dfs(i, j):
            if vis[i][j]:
                return
            vis[i][j] = True
            if [i, j] == destination:
                return
            for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                x, y = i, j
                while 0 <= x + a < m and 0 <= y + b < n and maze[x + a][y + b] == 0:
                    x, y = x + a, y + b
                dfs(x, y)

        m, n = len(maze), len(maze[0])
        vis = [[False] * n for _ in range(m)]
        dfs(start[0], start[1])
        return vis[destination[0]][destination[1]]
```

```java
class Solution {
    private boolean[][] vis;
    private int[][] maze;
    private int[] d;
    private int m;
    private int n;

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        m = maze.length;
        n = maze[0].length;
        d = destination;
        this.maze = maze;
        vis = new boolean[m][n];
        dfs(start[0], start[1]);
        return vis[d[0]][d[1]];
    }

    private void dfs(int i, int j) {
        if (vis[i][j]) {
            return;
        }
        vis[i][j] = true;
        if (i == d[0] && j == d[1]) {
            return;
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i, y = j;
            int a = dirs[k], b = dirs[k + 1];
            while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && maze[x + a][y + b] == 0) {
                x += a;
                y += b;
            }
            dfs(x, y);
        }
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> maze;
    vector<vector<bool>> vis;
    vector<int> d;
    int m;
    int n;

    bool hasPath(vector<vector<int>>& maze, vector<int>& start, vector<int>& destination) {
        m = maze.size();
        n = maze[0].size();
        d = destination;
        vis.resize(m, vector<bool>(n, false));
        this->maze = maze;
        dfs(start[0], start[1]);
        return vis[d[0]][d[1]];
    }

    void dfs(int i, int j) {
        if (vis[i][j]) return;
        vis[i][j] = true;
        if (i == d[0] && j == d[1]) return;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i, y = j;
            int a = dirs[k], b = dirs[k + 1];
            while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && maze[x + a][y + b] == 0) {
                x += a;
                y += b;
            }
            dfs(x, y);
        }
    }
};
```

```go
func hasPath(maze [][]int, start []int, destination []int) bool {
	m, n := len(maze), len(maze[0])
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	var dfs func(i, j int)
	dfs = func(i, j int) {
		if vis[i][j] {
			return
		}
		vis[i][j] = true
		if i == destination[0] && j == destination[1] {
			return
		}
		dirs := []int{-1, 0, 1, 0, -1}
		for k := 0; k < 4; k++ {
			x, y := i, j
			a, b := dirs[k], dirs[k+1]
			for x+a >= 0 && x+a < m && y+b >= 0 && y+b < n && maze[x+a][y+b] == 0 {
				x += a
				y += b
			}
			dfs(x, y)
		}
	}
	dfs(start[0], start[1])
	return vis[destination[0]][destination[1]]
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def hasPath(
        self, maze: List[List[int]], start: List[int], destination: List[int]
    ) -> bool:
        m, n = len(maze), len(maze[0])
        q = deque([start])
        rs, cs = start
        vis = {(rs, cs)}
        while q:
            i, j = q.popleft()
            for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                x, y = i, j
                while 0 <= x + a < m and 0 <= y + b < n and maze[x + a][y + b] == 0:
                    x, y = x + a, y + b
                if [x, y] == destination:
                    return True
                if (x, y) not in vis:
                    vis.add((x, y))
                    q.append((x, y))
        return False
```

```java
class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] vis = new boolean[m][n];
        vis[start[0]][start[1]] = true;
        Deque<int[]> q = new LinkedList<>();
        q.offer(start);
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            for (int k = 0; k < 4; ++k) {
                int x = i, y = j;
                int a = dirs[k], b = dirs[k + 1];
                while (
                    x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && maze[x + a][y + b] == 0) {
                    x += a;
                    y += b;
                }
                if (x == destination[0] && y == destination[1]) {
                    return true;
                }
                if (!vis[x][y]) {
                    vis[x][y] = true;
                    q.offer(new int[] {x, y});
                }
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    bool hasPath(vector<vector<int>>& maze, vector<int>& start, vector<int>& destination) {
        int m = maze.size();
        int n = maze[0].size();
        queue<vector<int>> q{{start}};
        vector<vector<bool>> vis(m, vector<bool>(n));
        vis[start[0]][start[1]] = true;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            auto p = q.front();
            q.pop();
            int i = p[0], j = p[1];
            for (int k = 0; k < 4; ++k) {
                int x = i, y = j;
                int a = dirs[k], b = dirs[k + 1];
                while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && maze[x + a][y + b] == 0) {
                    x += a;
                    y += b;
                }
                if (x == destination[0] && y == destination[1]) return 1;
                if (!vis[x][y]) {
                    vis[x][y] = true;
                    q.push({x, y});
                }
            }
        }
        return 0;
    }
};
```

```go
func hasPath(maze [][]int, start []int, destination []int) bool {
	m, n := len(maze), len(maze[0])
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	vis[start[0]][start[1]] = true
	q := [][]int{start}
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		i, j := q[0][0], q[0][1]
		q = q[1:]
		for k := 0; k < 4; k++ {
			x, y := i, j
			a, b := dirs[k], dirs[k+1]
			for x+a >= 0 && x+a < m && y+b >= 0 && y+b < n && maze[x+a][y+b] == 0 {
				x += a
				y += b
			}
			if x == destination[0] && y == destination[1] {
				return true
			}
			if !vis[x][y] {
				vis[x][y] = true
				q = append(q, []int{x, y})
			}
		}
	}
	return false
}
```

<!-- tabs:end -->

<!-- end -->
