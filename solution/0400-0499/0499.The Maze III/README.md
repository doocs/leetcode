---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0499.The%20Maze%20III/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 数组
    - 字符串
    - 矩阵
    - 最短路
    - 堆（优先队列）
---

<!-- problem:start -->

# [499. 迷宫 III 🔒](https://leetcode.cn/problems/the-maze-iii)

[English Version](/solution/0400-0499/0499.The%20Maze%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>由空地和墙组成的迷宫中有一个<strong>球</strong>。球可以向<strong>上（u）下（d）左（l）右（r）</strong>四个方向滚动，但在遇到墙壁前不会停止滚动。当球停下时，可以选择下一个方向（必须与上一个选择的方向不同）。迷宫中还有一个<strong>洞</strong>，当球运动经过洞时，就会掉进洞里。</p>

<p>给定球的<strong>起始位置，目的地</strong>和<strong>迷宫</strong>，找出让球以最短距离掉进洞里的路径。&nbsp;距离的定义是球从起始位置（不包括）到目的地（包括）经过的<strong>空地</strong>个数。通过'u', 'd', 'l' 和&nbsp;'r'输出球的移动<strong>方向</strong>。&nbsp;由于可能有多条最短路径，&nbsp;请输出<strong>字典序最小</strong>的路径<strong>。</strong>如果球无法进入洞，输出"impossible"。</p>

<p>迷宫由一个0和1的二维数组表示。 1表示墙壁，0表示空地。你可以假定迷宫的边缘都是墙壁。起始位置和目的地的坐标通过行号和列号给出。</p>

<p>&nbsp;</p>

<p><strong>示例1:</strong></p>

<pre>
<strong>输入 1:</strong> 迷宫由以下二维数组表示

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

<strong>输入 2:</strong> 球的初始位置 (rowBall, colBall) = (4, 3)
<strong>输入 3:</strong> 洞的位置 (rowHole, colHole) = (0, 1)

<strong>输出:</strong> "lul"

<strong>解析:</strong> 有两条让球进洞的最短路径。
第一条路径是 左 -&gt; 上 -&gt; 左, 记为 "lul".
第二条路径是 上 -&gt; 左, 记为 'ul'.
两条路径都具有最短距离6, 但'l' &lt; 'u'，故第一条路径字典序更小。因此输出"lul"。
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0499.The%20Maze%20III/images/maze_2_example_1.png" style="width: 100%;" />
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入 1:</strong> 迷宫由以下二维数组表示

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

<strong>输入 2:</strong> 球的初始位置 (rowBall, colBall) = (4, 3)
<strong>输入 3:</strong> 洞的位置 (rowHole, colHole) = (3, 0)

<strong>输出:</strong> "impossible"

<strong>示例:</strong> 球无法到达洞。
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0499.The%20Maze%20III/images/maze_2_example_2.png" style="width: 100%;" />
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>maze = [[0,0,0,0,0,0,0],[0,0,1,0,0,1,0],[0,0,0,0,1,0,0],[0,0,0,0,0,0,1]], ball = [0,4], hole = [3,5]
<b>输出：</b>"dldr"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == maze.length</code></li>
	<li><code>n == maze[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>maze[i][j]</code> is <code>0</code> or <code>1</code>.</li>
	<li><code>ball.length == 2</code></li>
	<li><code>hole.length == 2</code></li>
	<li><code>0 &lt;= ball<sub>row</sub>, hole<sub>row</sub> &lt;= m</code></li>
	<li><code>0 &lt;= ball<sub>col</sub>, hole<sub>col</sub> &lt;= n</code></li>
	<li>球和洞都存在于一个空地中，它们最初不会处于同一位置。</li>
	<li>迷宫中至少有 <code>2</code> 个空地。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findShortestWay(
        self, maze: List[List[int]], ball: List[int], hole: List[int]
    ) -> str:
        m, n = len(maze), len(maze[0])
        r, c = ball
        rh, ch = hole
        q = deque([(r, c)])
        dist = [[inf] * n for _ in range(m)]
        dist[r][c] = 0
        path = [[None] * n for _ in range(m)]
        path[r][c] = ''
        while q:
            i, j = q.popleft()
            for a, b, d in [(-1, 0, 'u'), (1, 0, 'd'), (0, -1, 'l'), (0, 1, 'r')]:
                x, y, step = i, j, dist[i][j]
                while (
                    0 <= x + a < m
                    and 0 <= y + b < n
                    and maze[x + a][y + b] == 0
                    and (x != rh or y != ch)
                ):
                    x, y = x + a, y + b
                    step += 1
                if dist[x][y] > step or (
                    dist[x][y] == step and path[i][j] + d < path[x][y]
                ):
                    dist[x][y] = step
                    path[x][y] = path[i][j] + d
                    if x != rh or y != ch:
                        q.append((x, y))
        return path[rh][ch] or 'impossible'
```

#### Java

```java
class Solution {
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length;
        int n = maze[0].length;
        int r = ball[0], c = ball[1];
        int rh = hole[0], ch = hole[1];
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c});
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[r][c] = 0;
        String[][] path = new String[m][n];
        path[r][c] = "";
        int[][] dirs = {{-1, 0, 'u'}, {1, 0, 'd'}, {0, -1, 'l'}, {0, 1, 'r'}};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            for (int[] dir : dirs) {
                int a = dir[0], b = dir[1];
                String d = String.valueOf((char) (dir[2]));
                int x = i, y = j;
                int step = dist[i][j];
                while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && maze[x + a][y + b] == 0
                    && (x != rh || y != ch)) {
                    x += a;
                    y += b;
                    ++step;
                }
                if (dist[x][y] > step
                    || (dist[x][y] == step && (path[i][j] + d).compareTo(path[x][y]) < 0)) {
                    dist[x][y] = step;
                    path[x][y] = path[i][j] + d;
                    if (x != rh || y != ch) {
                        q.offer(new int[] {x, y});
                    }
                }
            }
        }
        return path[rh][ch] == null ? "impossible" : path[rh][ch];
    }
}
```

#### C++

```cpp
class Solution {
public:
    string findShortestWay(vector<vector<int>>& maze, vector<int>& ball, vector<int>& hole) {
        int m = maze.size();
        int n = maze[0].size();
        int r = ball[0], c = ball[1];
        int rh = hole[0], ch = hole[1];
        queue<pair<int, int>> q;
        q.push({r, c});
        vector<vector<int>> dist(m, vector<int>(n, INT_MAX));
        dist[r][c] = 0;
        vector<vector<string>> path(m, vector<string>(n, ""));
        vector<vector<int>> dirs = {{-1, 0, 'u'}, {1, 0, 'd'}, {0, -1, 'l'}, {0, 1, 'r'}};
        while (!q.empty()) {
            auto p = q.front();
            q.pop();
            int i = p.first, j = p.second;
            for (auto& dir : dirs) {
                int a = dir[0], b = dir[1];
                char d = (char) dir[2];
                int x = i, y = j;
                int step = dist[i][j];
                while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && maze[x + a][y + b] == 0 && (x != rh || y != ch)) {
                    x += a;
                    y += b;
                    ++step;
                }
                if (dist[x][y] > step || (dist[x][y] == step && (path[i][j] + d < path[x][y]))) {
                    dist[x][y] = step;
                    path[x][y] = path[i][j] + d;
                    if (x != rh || y != ch) q.push({x, y});
                }
            }
        }
        return path[rh][ch] == "" ? "impossible" : path[rh][ch];
    }
};
```

#### Go

```go
import "math"

func findShortestWay(maze [][]int, ball []int, hole []int) string {
	m, n := len(maze), len(maze[0])
	r, c := ball[0], ball[1]
	rh, ch := hole[0], hole[1]
	q := [][]int{[]int{r, c}}
	dist := make([][]int, m)
	path := make([][]string, m)
	for i := range dist {
		dist[i] = make([]int, n)
		path[i] = make([]string, n)
		for j := range dist[i] {
			dist[i][j] = math.MaxInt32
			path[i][j] = ""
		}
	}
	dist[r][c] = 0
	dirs := map[string][]int{"u": {-1, 0}, "d": {1, 0}, "l": {0, -1}, "r": {0, 1}}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		i, j := p[0], p[1]
		for d, dir := range dirs {
			a, b := dir[0], dir[1]
			x, y := i, j
			step := dist[i][j]
			for x+a >= 0 && x+a < m && y+b >= 0 && y+b < n && maze[x+a][y+b] == 0 && (x != rh || y != ch) {
				x += a
				y += b
				step++
			}
			if dist[x][y] > step || (dist[x][y] == step && (path[i][j]+d) < path[x][y]) {
				dist[x][y] = step
				path[x][y] = path[i][j] + d
				if x != rh || y != ch {
					q = append(q, []int{x, y})
				}
			}
		}
	}
	if path[rh][ch] == "" {
		return "impossible"
	}
	return path[rh][ch]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
