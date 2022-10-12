# [499. The Maze III](https://leetcode.com/problems/the-maze-iii)

[中文文档](/solution/0400-0499/0499.The%20Maze%20III/README.md)

## Description

<p>There is a ball in a <code>maze</code> with empty spaces (represented as <code>0</code>) and walls (represented as <code>1</code>). The ball can go through the empty spaces by rolling <strong>up, down, left or right</strong>, but it won&#39;t stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls onto the hole.</p>

<p>Given the <code>m x n</code> <code>maze</code>, the ball&#39;s position <code>ball</code> and the hole&#39;s position <code>hole</code>, where <code>ball = [ball<sub>row</sub>, ball<sub>col</sub>]</code> and <code>hole = [hole<sub>row</sub>, hole<sub>col</sub>]</code>, return <em>a string </em><code>instructions</code><em> of all the instructions that the ball should follow to drop in the hole with the <strong>shortest distance</strong> possible</em>. If there are multiple valid instructions, return the <strong>lexicographically minimum</strong> one. If the ball can&#39;t drop in the hole, return <code>&quot;impossible&quot;</code>.</p>

<p>If there is a way for the ball to drop in the hole, the answer <code>instructions</code> should contain the characters <code>&#39;u&#39;</code> (i.e., up), <code>&#39;d&#39;</code> (i.e., down), <code>&#39;l&#39;</code> (i.e., left), and <code>&#39;r&#39;</code> (i.e., right).</p>

<p>The <strong>distance</strong> is the number of <strong>empty spaces</strong> traveled by the ball from the start position (excluded) to the destination (included).</p>

<p>You may assume that <strong>the borders of the maze are all walls</strong> (see examples).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0499.The%20Maze%20III/images/maze3-1-grid.jpg" style="width: 573px; height: 573px;" />
<pre>
<strong>Input:</strong> maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], ball = [4,3], hole = [0,1]
<strong>Output:</strong> &quot;lul&quot;
<strong>Explanation:</strong> There are two shortest ways for the ball to drop into the hole.
The first way is left -&gt; up -&gt; left, represented by &quot;lul&quot;.
The second way is up -&gt; left, represented by &#39;ul&#39;.
Both ways have shortest distance 6, but the first way is lexicographically smaller because &#39;l&#39; &lt; &#39;u&#39;. So the output is &quot;lul&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0499.The%20Maze%20III/images/maze3-2-grid.jpg" style="width: 573px; height: 573px;" />
<pre>
<strong>Input:</strong> maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], ball = [4,3], hole = [3,0]
<strong>Output:</strong> &quot;impossible&quot;
<strong>Explanation:</strong> The ball cannot reach the hole.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> maze = [[0,0,0,0,0,0,0],[0,0,1,0,0,1,0],[0,0,0,0,1,0,0],[0,0,0,0,0,0,1]], ball = [0,4], hole = [3,5]
<strong>Output:</strong> &quot;dldr&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == maze.length</code></li>
	<li><code>n == maze[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>maze[i][j]</code> is <code>0</code> or <code>1</code>.</li>
	<li><code>ball.length == 2</code></li>
	<li><code>hole.length == 2</code></li>
	<li><code>0 &lt;= ball<sub>row</sub>, hole<sub>row</sub> &lt;= m</code></li>
	<li><code>0 &lt;= ball<sub>col</sub>, hole<sub>col</sub> &lt;= n</code></li>
	<li>Both the ball and the hole exist in an empty space, and they will not be in the same position initially.</li>
	<li>The maze contains <strong>at least 2 empty spaces</strong>.</li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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
                char d = (char)dir[2];
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

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
