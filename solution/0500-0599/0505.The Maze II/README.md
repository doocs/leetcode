---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0505.The%20Maze%20II/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 数组
    - 矩阵
    - 最短路
    - 堆（优先队列）
---

<!-- problem:start -->

# [505. 迷宫 II 🔒](https://leetcode.cn/problems/the-maze-ii)

[English Version](/solution/0500-0599/0505.The%20Maze%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p><strong>迷宫</strong>中有一个球，它有空地 (表示为 <code>0</code>) 和墙 (表示为 <code>1</code>)。球可以<strong>向上</strong>、<strong>向下</strong>、<strong>向左</strong>或<strong>向右</strong>滚过空地，但直到撞上墙之前它都不会停止滚动。当球停止时，它才可以选择下一个滚动方向。</p>

<p>给定 <code>m × n</code> 的<strong>迷宫</strong>(<code>maze</code>)，球的<strong>起始位置&nbsp;</strong>(<code>start = [start<sub>row</sub>, start<sub>col</sub>]</code>) 和<strong>目的地&nbsp;</strong>(<code>destination = [destination<sub>row</sub>, destination<sub>col</sub>]</code>)，返回球在<strong>目的地&nbsp;</strong>(<code>destination</code>) 停止的最短<strong>距离</strong>。如果球不能在<strong>目的地&nbsp;</strong>(<code>destination</code>) 停止，返回 <code>-1</code>。</p>

<p><strong>距离</strong>是指球从起始位置 ( 不包括 ) 到终点 ( 包括 ) 所经过的<strong>空地</strong>数。</p>

<p>你可以假设<strong>迷宫的边界都是墙&nbsp;</strong>( 见例子 )。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0505.The%20Maze%20II/images/maze1-1-grid.jpg" /></p>

<pre>
<strong>输入:</strong> maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
<strong>输出:</strong> 12
<strong>解析:</strong> 一条最短路径 : left -&gt; down -&gt; left -&gt; down -&gt; right -&gt; down -&gt; right。
             总距离为 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12。

</pre>

<p><strong>示例&nbsp;2:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0505.The%20Maze%20II/images/maze1-2-grid.jpg" /></p>

<pre>
<strong>输入:</strong> maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
<strong>输出:</strong> -1
<strong>解析:</strong> 球不可能在目的地停下来。注意，你可以经过目的地，但不能在那里停下来。
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre>
<strong>输入:</strong> maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
<strong>输出:</strong> -1
</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong></p>

<ul>
	<li><code>m == maze.length</code></li>
	<li><code>n == maze[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>maze[i][j]</code>&nbsp;是&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>.</li>
	<li><code>start.length == 2</code></li>
	<li><code>destination.length == 2</code></li>
	<li><code>0 &lt;= start<sub>row</sub>, destination<sub>row</sub>&nbsp;&lt; m</code></li>
	<li><code>0 &lt;= start<sub>col</sub>, destination<sub>col</sub>&nbsp;&lt; n</code></li>
	<li>球和目的地都存在于一个空地中，它们最初不会处于相同的位置。</li>
	<li>
	<p data-group="1-1">迷宫<strong>至少包含两个空地</strong>。</p>
	</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

我们定义一个二维数组 $dist$，其中 $dist[i][j]$ 表示从起始位置到达 $(i,j)$ 的最短路径长度。初始时，$dist$ 中的所有元素都被初始化为一个很大的数，除了起始位置，因为起始位置到自身的距离是 $0$。

然后，我们定义一个队列 $q$，将起始位置加入队列。随后不断进行以下操作：弹出队列中的首元素，将其四个方向上可以到达的位置加入队列中，并且在 $dist$ 中记录这些位置的距离，直到队列为空。

最后，如果终点位置的距离仍然是一个很大的数，说明从起始位置无法到达终点位置，返回 $-1$，否则返回终点位置的距离。

时间复杂度 $O(m \times n \times \max(m, n))$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是迷宫的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def shortestDistance(
        self, maze: List[List[int]], start: List[int], destination: List[int]
    ) -> int:
        m, n = len(maze), len(maze[0])
        dirs = (-1, 0, 1, 0, -1)
        si, sj = start
        di, dj = destination
        q = deque([(si, sj)])
        dist = [[inf] * n for _ in range(m)]
        dist[si][sj] = 0
        while q:
            i, j = q.popleft()
            for a, b in pairwise(dirs):
                x, y, k = i, j, dist[i][j]
                while 0 <= x + a < m and 0 <= y + b < n and maze[x + a][y + b] == 0:
                    x, y, k = x + a, y + b, k + 1
                if k < dist[x][y]:
                    dist[x][y] = k
                    q.append((x, y))
        return -1 if dist[di][dj] == inf else dist[di][dj]
```

```java
class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        final int inf = 1 << 30;
        int[][] dist = new int[m][n];
        for (var row : dist) {
            Arrays.fill(row, inf);
        }
        int si = start[0], sj = start[1];
        int di = destination[0], dj = destination[1];
        dist[si][sj] = 0;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {si, sj});
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            var p = q.poll();
            int i = p[0], j = p[1];
            for (int d = 0; d < 4; ++d) {
                int x = i, y = j, k = dist[i][j];
                int a = dirs[d], b = dirs[d + 1];
                while (
                    x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && maze[x + a][y + b] == 0) {
                    x += a;
                    y += b;
                    ++k;
                }
                if (k < dist[x][y]) {
                    dist[x][y] = k;
                    q.offer(new int[] {x, y});
                }
            }
        }
        return dist[di][dj] == inf ? -1 : dist[di][dj];
    }
}
```

```cpp
class Solution {
public:
    int shortestDistance(vector<vector<int>>& maze, vector<int>& start, vector<int>& destination) {
        int m = maze.size(), n = maze[0].size();
        int dist[m][n];
        memset(dist, 0x3f, sizeof(dist));
        int si = start[0], sj = start[1];
        int di = destination[0], dj = destination[1];
        dist[si][sj] = 0;
        queue<pair<int, int>> q;
        q.emplace(si, sj);
        int dirs[5] = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            auto [i, j] = q.front();
            q.pop();
            for (int d = 0; d < 4; ++d) {
                int x = i, y = j, k = dist[i][j];
                int a = dirs[d], b = dirs[d + 1];
                while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && maze[x + a][y + b] == 0) {
                    x += a;
                    y += b;
                    ++k;
                }
                if (k < dist[x][y]) {
                    dist[x][y] = k;
                    q.emplace(x, y);
                }
            }
        }
        return dist[di][dj] == 0x3f3f3f3f ? -1 : dist[di][dj];
    }
};
```

```go
func shortestDistance(maze [][]int, start []int, destination []int) int {
	m, n := len(maze), len(maze[0])
	dist := make([][]int, m)
	const inf = 1 << 30
	for i := range dist {
		dist[i] = make([]int, n)
		for j := range dist[i] {
			dist[i][j] = inf
		}
	}
	dist[start[0]][start[1]] = 0
	q := [][]int{start}
	dirs := [5]int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		i, j := p[0], p[1]
		for d := 0; d < 4; d++ {
			x, y, k := i, j, dist[i][j]
			a, b := dirs[d], dirs[d+1]
			for x+a >= 0 && x+a < m && y+b >= 0 && y+b < n && maze[x+a][y+b] == 0 {
				x, y, k = x+a, y+b, k+1
			}
			if k < dist[x][y] {
				dist[x][y] = k
				q = append(q, []int{x, y})
			}
		}
	}
	di, dj := destination[0], destination[1]
	if dist[di][dj] == inf {
		return -1
	}
	return dist[di][dj]
}
```

```ts
function shortestDistance(maze: number[][], start: number[], destination: number[]): number {
    const m = maze.length;
    const n = maze[0].length;
    const dist: number[][] = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => Infinity),
    );
    const [si, sj] = start;
    const [di, dj] = destination;
    dist[si][sj] = 0;
    const q: number[][] = [[si, sj]];
    const dirs = [-1, 0, 1, 0, -1];
    while (q.length) {
        const [i, j] = q.shift()!;
        for (let d = 0; d < 4; ++d) {
            let [x, y, k] = [i, j, dist[i][j]];
            const [a, b] = [dirs[d], dirs[d + 1]];
            while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && maze[x + a][y + b] === 0) {
                x += a;
                y += b;
                ++k;
            }
            if (k < dist[x][y]) {
                dist[x][y] = k;
                q.push([x, y]);
            }
        }
    }
    return dist[di][dj] === Infinity ? -1 : dist[di][dj];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
