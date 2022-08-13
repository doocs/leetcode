# [1810. Minimum Path Cost in a Hidden Grid](https://leetcode.com/problems/minimum-path-cost-in-a-hidden-grid)

[中文文档](/solution/1800-1899/1810.Minimum%20Path%20Cost%20in%20a%20Hidden%20Grid/README.md)

## Description

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
<p><strong>Example 1:</strong></p>

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

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[0,3,1],[3,4,2],[1,2,0]], r1 = 2, c1 = 0, r2 = 0, c2 = 2
<strong>Output:</strong> 9
<strong>Explanation:</strong> The minimum cost path is (2,0) -&gt; (2,1) -&gt; (1,1) -&gt; (1,2) -&gt; (0,2).
</pre>

<p><strong>Example 3:</strong></p>

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

## Solutions

<!-- tabs:start -->

### **Python3**

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
#    def isTarget(self) -> None:
#
#


class Solution(object):
    def findShortestPath(self, master: 'GridMaster') -> int:
        def dfs(i, j):
            nonlocal target
            if master.isTarget():
                target = (i, j)
            for dir, (a, b, ndir) in dirs.items():
                x, y = i + a, j + b
                if 0 <= x < N and 0 <= y < N and master.canMove(dir) and g[x][y] == -1:
                    g[x][y] = master.move(dir)
                    dfs(x, y)
                    master.move(ndir)

        target = (-1, -1)
        N = 200
        INF = 0x3F3F3F3F
        g = [[-1] * N for _ in range(N)]
        dirs = {
            'U': (-1, 0, 'D'),
            'D': (1, 0, 'U'),
            'L': (0, -1, 'R'),
            'R': (0, 1, 'L'),
        }
        dfs(100, 100)
        if target == (-1, -1):
            return -1
        q = [(0, 100, 100)]
        dist = [[INF] * N for _ in range(N)]
        dist[100][100] = 0
        while q:
            w, i, j = heappop(q)
            if (i, j) == target:
                return w
            for a, b, _ in dirs.values():
                x, y = i + a, j + b
                if (
                    0 <= x < N
                    and 0 <= y < N
                    and g[x][y] != -1
                    and dist[x][y] > w + g[x][y]
                ):
                    dist[x][y] = w + g[x][y]
                    heappush(q, (dist[x][y], x, y))
        return 0
```

### **Java**

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
    private static final char[] dir = {'U', 'R', 'D', 'L'};
    private static final char[] ndir = {'D', 'L', 'U', 'R'};
    private static final int[] dirs = {-1, 0, 1, 0, -1};
    private static final int N = 200;
    private static final int INF = 0x3f3f3f3f;
    private static int[][] g = new int[N][N];
    private static int[][] dist = new int[N][N];
    private int[] target;

    public int findShortestPath(GridMaster master) {
        target = new int[]{-1, -1};
        for (int i = 0; i < N; ++i) {
            Arrays.fill(g[i], -1);
            Arrays.fill(dist[i], INF);
        }
        dfs(100, 100, master);
        if (target[0] == -1 && target[1] == -1) {
            return -1;
        }
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        q.offer(new int[]{0, 100, 100});
        dist[100][100] = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int w = p[0], i = p[1], j = p[2];
            if (i == target[0] && j == target[1]) {
                return w;
            }
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < N && y >= 0 && y < N && g[x][y] != -1 && dist[x][y] > w + g[x][y]) {
                    dist[x][y] = w + g[x][y];
                    q.offer(new int[]{dist[x][y], x, y});
                }
            }
        }
        return 0;
    }

    private void dfs(int i, int j, GridMaster master) {
        if (master.isTarget()) {
            target = new int[]{i, j};
        }
        for (int k = 0; k < 4; ++k) {
            char d = dir[k], nd = ndir[k];
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < N && y >= 0 && y < N && master.canMove(d) && g[x][y] == -1) {
                g[x][y] = master.move(d);
                dfs(x, y, master);
                master.move(nd);
            }
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
