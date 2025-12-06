---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1810.Minimum%20Path%20Cost%20in%20a%20Hidden%20Grid/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Graph
    - Array
    - Interactive
    - Matrix
    - Shortest Path
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [1810. Minimum Path Cost in a Hidden Grid 🔒](https://leetcode.com/problems/minimum-path-cost-in-a-hidden-grid)

[中文文档](/solution/1800-1899/1810.Minimum%20Path%20Cost%20in%20a%20Hidden%20Grid/README.md)

## Description

<!-- description:start -->

<p>This is an <strong>interactive problem</strong>.</p>

<p>There is a robot in a hidden grid, and you are trying to get it from its starting cell to the target cell in this grid. The grid is of size <code>m x n</code>, and each cell in the grid is either empty or blocked. It is <strong>guaranteed</strong> that the starting cell and the target cell are different, and neither of them is blocked.</p>

<p>Each cell has a <strong>cost</strong> that you need to pay each time you <strong>move</strong> to the cell. The starting cell&#39;s cost is <strong>not</strong> applied before the robot moves.</p>

<p>You want to find the minimum total cost to move the robot to the target cell. However, you <strong>do not know</strong> the grid&#39;s dimensions, the starting cell, nor the target cell. You are only allowed to ask queries to the <code>GridMaster</code> object.</p>

<p>The <code>GridMaster</code> class has the following functions:</p>

<ul>
	<li><code>boolean canMove(char direction)</code> Returns <code>true</code> if the robot can move in that direction. Otherwise, it returns <code>false</code>.</li>
	<li><code>int move(char direction)</code> Moves the robot in that direction and returns the cost of moving to that cell. If this move would move the robot to a blocked cell or off the grid, the move will be <strong>ignored</strong>, the robot will remain in the same position, and the function will return <code>-1</code>.</li>
	<li><code>boolean isTarget()</code> Returns <code>true</code> if the robot is currently on the target cell. Otherwise, it returns <code>false</code>.</li>
</ul>

<p>Note that <code>direction</code> in the above functions should be a character from <code>{&#39;U&#39;,&#39;D&#39;,&#39;L&#39;,&#39;R&#39;}</code>, representing the directions up, down, left, and right, respectively.</p>

<p>Return <em>the <strong>minimum total cost</strong> to get the robot from its initial starting cell to the target cell. If there is no valid path between the cells, return </em><code>-1</code>.</p>

<p><strong>Custom testing:</strong></p>

<p>The test input is read as a 2D matrix <code>grid</code> of size <code>m x n</code> and four integers <code>r1</code>, <code>c1</code>, <code>r2</code>, and <code><font face="monospace">c2</font></code> where:</p>

<ul>
	<li><code>grid[i][j] == 0</code> indicates that the cell <code>(i, j)</code> is blocked.</li>
	<li><code>grid[i][j] &gt;= 1</code> indicates that the cell <code>(i, j)</code> is empty and <code>grid[i][j]</code> is the <strong>cost</strong> to move to that cell.</li>
	<li><code>(r1, c1)</code> is the starting cell of the robot.</li>
	<li><code>(r2, c2)</code> is the target cell of the robot.</li>
</ul>

<p>Remember that you will <strong>not</strong> have this information in your code.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> grid = [[2,3],[1,1]], r1 = 0, c1 = 1, r2 = 1, c2 = 0
<strong>Output:</strong> 2
<strong>Explanation:</strong> One possible interaction is described below:
The robot is initially standing on cell (0, 1), denoted by the 3.
- master.canMove(&#39;U&#39;) returns false.
- master.canMove(&#39;D&#39;) returns true.
- master.canMove(&#39;L&#39;) returns true.
- master.canMove(&#39;R&#39;) returns false.
- master.move(&#39;L&#39;) moves the robot to the cell (0, 0) and returns 2.
- master.isTarget() returns false.
- master.canMove(&#39;U&#39;) returns false.
- master.canMove(&#39;D&#39;) returns true.
- master.canMove(&#39;L&#39;) returns false.
- master.canMove(&#39;R&#39;) returns true.
- master.move(&#39;D&#39;) moves the robot to the cell (1, 0) and returns 1.
- master.isTarget() returns true.
- master.move(&#39;L&#39;) doesn&#39;t move the robot and returns -1.
- master.move(&#39;R&#39;) moves the robot to the cell (1, 1) and returns 1.
We now know that the target is the cell (1, 0), and the minimum total cost to reach it is 2. </pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[0,3,1],[3,4,2],[1,2,0]], r1 = 2, c1 = 0, r2 = 0, c2 = 2
<strong>Output:</strong> 9
<strong>Explanation:</strong> The minimum cost path is (2,0) -&gt; (2,1) -&gt; (1,1) -&gt; (1,2) -&gt; (0,2).
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0],[0,1]], r1 = 0, c1 = 0, r2 = 1, c2 = 1
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no path from the robot to the target cell.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, m &lt;= 100</code></li>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS Graph Construction + Heap-Optimized Dijkstra Algorithm

We observe that the grid size is $m \times n$, where $m, n \leq 100$. Therefore, we can initialize the starting coordinates as $(sx, sy) = (100, 100)$ and assume the grid size is $200 \times 200$. Then, we can use depth-first search (DFS) to explore the entire grid and construct a 2D array $g$ representing the grid, where $g[i][j]$ represents the movement cost from the starting point $(sx, sy)$ to coordinates $(i, j)$. If a cell is unreachable, we set its value to $-1$. We store the target coordinates in $\textit{target}$, and if the target cannot be reached, then $\textit{target} = (-1, -1)$.

Next, we can use the heap-optimized Dijkstra algorithm to calculate the minimum cost path from the starting point $(sx, sy)$ to the target $\textit{target}$. We use a priority queue to store the current path cost and coordinates, and use a 2D array $\textit{dist}$ to record the minimum cost from the starting point to each cell. When we pop a node from the priority queue, if that node is the target, we return the current path cost as the answer. If the path cost of that node is greater than the value recorded in $\textit{dist}$, we skip that node. Otherwise, we traverse the four neighbors of that node. If a neighbor is reachable and the path cost to reach the neighbor through this node is smaller, we update the neighbor's path cost and add it to the priority queue.

The time complexity is $O(m \times n \log(m \times n))$, and the space complexity is $O(m \times n)$. Where $m$ and $n$ are the number of rows and columns in the grid, respectively.

<!-- tabs:start -->

#### Python3

```python
# """
# This is GridMaster's API interface.
# You should not implement it, or speculate about its implementation
# """
# class GridMaster(object):
#    def canMove(self, direction: str) -> bool:
#
#
#    def move(self, direction: str) -> int:
#
#
#    def isTarget(self) -> bool:
#
#


class Solution(object):
    def findShortestPath(self, master: "GridMaster") -> int:
        def dfs(x: int, y: int) -> None:
            nonlocal target
            if master.isTarget():
                target = (x, y)
            for k in range(4):
                dx, dy = dirs[k], dirs[k + 1]
                nx, ny = x + dx, y + dy
                if (
                    0 <= nx < m
                    and 0 <= ny < n
                    and g[nx][ny] == -1
                    and master.canMove(s[k])
                ):
                    g[nx][ny] = master.move(s[k])
                    dfs(nx, ny)
                    master.move(s[(k + 2) % 4])

        dirs = (-1, 0, 1, 0, -1)
        s = "URDL"
        m = n = 200
        g = [[-1] * n for _ in range(m)]
        target = (-1, -1)
        sx = sy = 100
        dfs(sx, sy)
        if target == (-1, -1):
            return -1
        pq = [(0, sx, sy)]
        dist = [[inf] * n for _ in range(m)]
        dist[sx][sy] = 0
        while pq:
            w, x, y = heappop(pq)
            if (x, y) == target:
                return w
            for dx, dy in pairwise(dirs):
                nx, ny = x + dx, y + dy
                if (
                    0 <= nx < m
                    and 0 <= ny < n
                    and g[nx][ny] != -1
                    and w + g[nx][ny] < dist[nx][ny]
                ):
                    dist[nx][ny] = w + g[nx][ny]
                    heappush(pq, (dist[nx][ny], nx, ny))
        return -1
```

#### Java

```java
/**
 * // This is the GridMaster's API interface.
 * // You should not implement it, or speculate about its implementation
 * class GridMaster {
 *     boolean canMove(char direction);
 *     int move(char direction);
 *     boolean isTarget();
 * }
 */

class Solution {
    private final int m = 200;
    private final int n = 200;
    private final int inf = Integer.MAX_VALUE / 2;
    private final int[] dirs = {-1, 0, 1, 0, -1};
    private final char[] s = {'U', 'R', 'D', 'L'};
    private int[][] g;
    private int sx = 100, sy = 100;
    private int tx = -1, ty = -1;
    private GridMaster master;

    public int findShortestPath(GridMaster master) {
        this.master = master;
        g = new int[m][n];
        for (var gg : g) {
            Arrays.fill(gg, -1);
        }
        dfs(sx, sy);
        if (tx == -1 && ty == -1) {
            return -1;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] {0, sx, sy});
        int[][] dist = new int[m][n];
        for (var gg : dist) {
            Arrays.fill(gg, inf);
        }
        dist[sx][sy] = 0;
        while (!pq.isEmpty()) {
            var p = pq.poll();
            int w = p[0], x = p[1], y = p[2];
            if (x == tx && y == ty) {
                return w;
            }
            if (w > dist[x][y]) {
                continue;
            }
            for (int k = 0; k < 4; ++k) {
                int nx = x + dirs[k], ny = y + dirs[k + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && g[nx][ny] != -1
                    && w + g[nx][ny] < dist[nx][ny]) {
                    dist[nx][ny] = w + g[nx][ny];
                    pq.offer(new int[] {dist[nx][ny], nx, ny});
                }
            }
        }
        return -1;
    }

    private void dfs(int x, int y) {
        if (master.isTarget()) {
            tx = x;
            ty = y;
        }
        for (int k = 0; k < 4; ++k) {
            int dx = dirs[k], dy = dirs[k + 1];
            int nx = x + dx, ny = y + dy;
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && g[nx][ny] == -1 && master.canMove(s[k])) {
                g[nx][ny] = master.move(s[k]);
                dfs(nx, ny);
                master.move(s[(k + 2) % 4]);
            }
        }
    }
}
```

#### C++

```cpp
/**
 * // This is the GridMaster's API interface.
 * // You should not implement it, or speculate about its implementation
 * class GridMaster {
 *   public:
 *     bool canMove(char direction);
 *     int move(char direction);
 *     boolean isTarget();
 * };
 */

class Solution {
public:
    int findShortestPath(GridMaster& master) {
        const int m = 200, n = 200;
        const int sx = 100, sy = 100;
        const int INF = INT_MAX / 2;
        int dirs[5] = {-1, 0, 1, 0, -1};
        char s[4] = {'U', 'R', 'D', 'L'};

        vector<vector<int>> g(m, vector<int>(n, -1));
        pair<int, int> target = {-1, -1};

        auto dfs = [&](this auto& dfs, int x, int y) -> void {
            if (master.isTarget()) {
                target = {x, y};
            }
            for (int k = 0; k < 4; ++k) {
                int dx = dirs[k], dy = dirs[k + 1];
                int nx = x + dx, ny = y + dy;
                if (0 <= nx && nx < m && 0 <= ny && ny < n && g[nx][ny] == -1 && master.canMove(s[k])) {
                    g[nx][ny] = master.move(s[k]);
                    dfs(nx, ny);
                    master.move(s[(k + 2) % 4]);
                }
            }
        };

        g[sx][sy] = 0;
        dfs(sx, sy);

        if (target.first == -1 && target.second == -1) {
            return -1;
        }

        vector<vector<int>> dist(m, vector<int>(n, INF));
        dist[sx][sy] = 0;

        using Node = tuple<int, int, int>;
        priority_queue<Node, vector<Node>, greater<Node>> pq;
        pq.emplace(0, sx, sy);

        while (!pq.empty()) {
            auto [w, x, y] = pq.top();
            pq.pop();
            if (x == target.first && y == target.second) {
                return w;
            }
            if (w > dist[x][y]) {
                continue;
            }

            for (int k = 0; k < 4; ++k) {
                int nx = x + dirs[k], ny = y + dirs[k + 1];
                if (0 <= nx && nx < m && 0 <= ny && ny < n && g[nx][ny] != -1) {
                    int nd = w + g[nx][ny];
                    if (nd < dist[nx][ny]) {
                        dist[nx][ny] = nd;
                        pq.emplace(nd, nx, ny);
                    }
                }
            }
        }

        return -1;
    }
};
```

#### JavaScript

```js
/**
 * // This is the GridMaster's API interface.
 * // You should not implement it, or speculate about its implementation
 * function GridMaster() {
 *
 *     @param {character} direction
 *     @return {boolean}
 *     this.canMove = function(direction) {
 *         ...
 *     };
 *     @param {character} direction
 *     @return {integer}
 *     this.move = function(direction) {
 *         ...
 *     };
 *     @return {boolean}
 *     this.isTarget = function() {
 *         ...
 *     };
 * };
 */

/**
 * @param {GridMaster} master
 * @return {integer}
 */
var findShortestPath = function (master) {
    const [m, n] = [200, 200];
    const [sx, sy] = [100, 100];
    const inf = Number.MAX_SAFE_INTEGER;
    const dirs = [-1, 0, 1, 0, -1];
    const s = ['U', 'R', 'D', 'L'];
    const g = Array.from({ length: m }, () => Array(n).fill(-1));
    let target = [-1, -1];
    const dfs = (x, y) => {
        if (master.isTarget()) {
            target = [x, y];
        }
        for (let k = 0; k < 4; ++k) {
            const dx = dirs[k],
                dy = dirs[k + 1];
            const nx = x + dx,
                ny = y + dy;
            if (
                0 <= nx &&
                nx < m &&
                0 <= ny &&
                ny < n &&
                g[nx][ny] === -1 &&
                master.canMove(s[k])
            ) {
                g[nx][ny] = master.move(s[k]);
                dfs(nx, ny);
                master.move(s[(k + 2) % 4]);
            }
        }
    };
    g[sx][sy] = 0;
    dfs(sx, sy);
    if (target[0] === -1 && target[1] === -1) {
        return -1;
    }
    const dist = Array.from({ length: m }, () => Array(n).fill(inf));
    dist[sx][sy] = 0;
    const pq = new MinPriorityQueue(node => node[0]);
    pq.enqueue([0, sx, sy]);
    while (!pq.isEmpty()) {
        const [w, x, y] = pq.dequeue();
        if (x === target[0] && y === target[1]) {
            return w;
        }
        if (w > dist[x][y]) {
            continue;
        }
        for (let k = 0; k < 4; ++k) {
            const nx = x + dirs[k],
                ny = y + dirs[k + 1];
            if (0 <= nx && nx < m && 0 <= ny && ny < n && g[nx][ny] !== -1) {
                const nd = w + g[nx][ny];
                if (nd < dist[nx][ny]) {
                    dist[nx][ny] = nd;
                    pq.enqueue([nd, nx, ny]);
                }
            }
        }
    }
    return -1;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
