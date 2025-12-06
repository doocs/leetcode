---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1810.Minimum%20Path%20Cost%20in%20a%20Hidden%20Grid/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 数组
    - 交互
    - 矩阵
    - 最短路
    - 堆（优先队列）
---

<!-- problem:start -->

# [1810. 隐藏网格下的最小消耗路径 🔒](https://leetcode.cn/problems/minimum-path-cost-in-a-hidden-grid)

[English Version](/solution/1800-1899/1810.Minimum%20Path%20Cost%20in%20a%20Hidden%20Grid/README_EN.md)

## 题目描述

<!-- description:start -->

<p>这是一个交互问题。</p>

<p>有一个机器人存在于网格中，你需要通过不断尝试使他从初始单元到达目标单元。网格的规格为m x n，并且每个单元的属性值要不为空，要不已被占用。题目<strong>保证</strong>初始网格和目标网格不同且均为空。</p>

<p>每个单元格都有<b>消耗</b>值，你需要在每次<strong>移动</strong>至此单元格后支付该费用。在机器人启动前，初始单元的费用不被计算在内。</p>

<p>你需要找到机器人移动至目标网格的最小总消耗。但可惜的是你并<strong>不知道</strong>网格的尺寸、初始单元和目标单元。你只允许通过询问<code>GridMaster</code>类获得信息。</p>

<p><code>GridMaster</code>类存在以下功能：</p>

<ul>
	<li><code>boolean canMove(char direction)</code> 当机器人可以向这个方向移动时，返回<code>true</code>；反之返回<code>false</code>。</li>
	<li><code>int move(char direction)</code> 沿该方向移动机器人，并返回移动到该单元的消耗值。如果此移动将机器人移动到被占有的单元格或离开网格，则移动将被<strong>忽略</strong>，机器人将保持在相同的位置，函数将返回<code>-1</code>。</li>
	<li><code>boolean isTarget()</code> ：如果机器人当前位于目标单元格上，则返回<code>true</code>；<span style="">反之返回</span> <code>false</code> 。</li>
</ul>

<p>请注意，上述函数中的方向应该是<code>{ 'U'、'D'、'L'、'R' }</code>中的字符，分别表示向上、向下、左和右方向。</p>

<p>返回使机器人从其初始起始单元到目标单元的<strong>最小总消耗</strong>。如果单元格之间不存在有效路径，则返回<code>-1</code>。</p>

<p><strong>测试实例:</strong></p>

<p>测试输入一个大小为<code>m x n</code>的二维数组 <code>grid</code> 和四个<code>int</code>型参数 <code>r1</code>, <code>c1</code>, <code>r2</code>, 和 <code><font face="monospace">c2</font></code> :</p>

<ul>
	<li><code>grid[i][j] == 0</code> 表示网格 <code>(i, j)</code> 已被占用。</li>
	<li><code>grid[i][j] >= 1</code> 表示网格单元 <code>(i, j)</code> 为空并且 <code>grid[i][j]</code> 的值为移动至此网格的成本值。</li>
	<li><code>(r1, c1)</code> 为初始单元。</li>
	<li><code>(r2, c2)</code> 为目标单元。</li>
</ul>

<p>请注意，你将无法在你的代码中获知这些信息。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> grid = [[2,3],[1,1]], r1 = 0, c1 = 1, r2 = 1, c2 = 0
<strong>输出:</strong> 2
<strong>解释:</strong> 其中一种可能路径描述如下：
机器人最开始站在单元格 (0, 1) ，用 3 表示
- master.canMove('U') 返回 false
- master.canMove('D') 返回 true
- master.canMove('L') 返回 true
- master.canMove('R') 返回 false
- master.move('L') 机器人移动到单元格 (0, 0) 并返回 2
- master.isTarget() 返回 false
- master.canMove('U') 返回 false
- master.canMove('D') 返回 true
- master.canMove('L') 返回 false
- master.canMove('R') 返回 true
- master.move('D') 机器人移动到单元格 (1, 0) 并返回 1
- master.isTarget() 返回 true
- master.move('L') 机器人不移动并返回 -1
- master.move('R') 机器人移动到单元格 (1, 1) 并返回 1
现在我们知道了机器人达到目标单元(1, 0)的最小消耗成本为2。 </pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> grid = [[0,3,1],[3,4,2],[1,2,0]], r1 = 2, c1 = 0, r2 = 0, c2 = 2
<strong>输出:</strong> 9
<strong>解释:</strong> 最小消耗路径为 (2,0) -> (2,1) -> (1,1) -> (1,2) -> (0,2).
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> grid = [[1,0],[0,1]], r1 = 0, c1 = 0, r2 = 1, c2 = 1
<strong>输出:</strong> -1
<strong>解释:</strong> 不存在可使机器人到达目标单元的路径。
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 <= n, m <= 100</code></li>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>0 <= grid[i][j] <= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS 建图 + 堆优化版 Dijkstra 算法

我们注意到，网格的大小为 $m \times n$，其中 $m, n \leq 100$。因此，我们可以初始化起点坐标 $(sx, sy) = (100, 100)$，并假设网格的大小为 $200 \times 200$。然后，我们可以使用深度优先搜索（DFS）来探索整个网格，并构建一个表示网格的二维数组 $g$，其中 $g[i][j]$ 表示从起点 $(sx, sy)$ 到坐标 $(i, j)$ 的移动消耗。如果某个单元格不可达，则将其值设为 $-1$。我们将终点坐标存储在 $\textit{target}$ 中，如果无法到达终点，则 $\textit{target} = (-1, -1)$。

接下来，我们可以使用堆优化版的 Dijkstra 算法来计算从起点 $(sx, sy)$ 到终点 $\textit{target}$ 的最小消耗路径。我们使用一个优先队列来存储当前的路径消耗和坐标，并使用一个二维数组 $\textit{dist}$ 来记录从起点到每个单元格的最小消耗。当我们从优先队列中取出一个节点时，如果该节点是终点，则返回当前的路径消耗作为答案。如果该节点的路径消耗大于 $\textit{dist}$ 中记录的值，则跳过该节点。否则，我们遍历该节点的四个邻居，如果邻居可达且通过该节点到达邻居的路径消耗更小，则更新邻居的路径消耗并将其加入优先队列。

时间复杂度 $O(m \times n \log(m \times n))$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是网格的行数和列数。

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
