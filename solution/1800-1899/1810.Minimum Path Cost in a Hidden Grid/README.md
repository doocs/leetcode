---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1810.Minimum%20Path%20Cost%20in%20a%20Hidden%20Grid/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 交互
    - 堆（优先队列）
---

# [1810. 隐藏网格下的最小消耗路径 🔒](https://leetcode.cn/problems/minimum-path-cost-in-a-hidden-grid)

[English Version](/solution/1800-1899/1810.Minimum%20Path%20Cost%20in%20a%20Hidden%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

### 方法一：DFS 建图 + 堆优化版 Dijkstra 算法

<!-- tabs:start -->

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
        target = new int[] {-1, -1};
        for (int i = 0; i < N; ++i) {
            Arrays.fill(g[i], -1);
            Arrays.fill(dist[i], INF);
        }
        dfs(100, 100, master);
        if (target[0] == -1 && target[1] == -1) {
            return -1;
        }
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        q.offer(new int[] {0, 100, 100});
        dist[100][100] = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int w = p[0], i = p[1], j = p[2];
            if (i == target[0] && j == target[1]) {
                return w;
            }
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < N && y >= 0 && y < N && g[x][y] != -1
                    && dist[x][y] > w + g[x][y]) {
                    dist[x][y] = w + g[x][y];
                    q.offer(new int[] {dist[x][y], x, y});
                }
            }
        }
        return 0;
    }

    private void dfs(int i, int j, GridMaster master) {
        if (master.isTarget()) {
            target = new int[] {i, j};
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

<!-- tabs:end -->

<!-- end -->
