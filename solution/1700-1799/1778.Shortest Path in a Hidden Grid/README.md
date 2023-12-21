# [1778. 未知网格中的最短路径](https://leetcode.cn/problems/shortest-path-in-a-hidden-grid)

[English Version](/solution/1700-1799/1778.Shortest%20Path%20in%20a%20Hidden%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>这是一个<strong>交互式的问题。</strong></p>

<p>一个未知的网格里有一个机器人，你需要让机器人从起点走到终点。这个网格的大小是 <code>m x n</code>，网格中的每个位置只会是可通行和不可通行两种状态。题目<strong>保证</strong>机器人的起点和终点不同，且都是可通行的。</p>

<p>你需要找到起点到终点的最短路径，然而你<strong>不知道</strong>网格的大小、起点和终点。你只能向 <code>GridMaster</code> 对象查询。</p>

<p><code>GridMaster</code>有这些方法：</p>

<ul>
	<li><code>boolean canMove(char direction)</code> 当机器人能向对应方向移动时，返回 <code>true</code>，否则返回 <code>false</code>。</li>
	<li><code>void move(char direction)</code> 把机器人向这个方向移动。如果移动方向上是不可通行的或是网格的边界，则这次移动会被<strong>忽略</strong>，机器人会待在原地。</li>
	<li><code>boolean isTarget()</code> 如果机器人当前位于终点，返回 <code>true</code>，否则返回 <code>false</code>。</li>
</ul>

<p>注意上述方法中的direction应该是 <code>{'U','D','L','R'}</code> 中的一个，分别代表上下左右四个方向。</p>

<p>返回机器人的初始位置到终点的最短距离。如果在起点和终点间没有路径联通，返回 <code>-1</code>。</p>

<p><strong>自定义测试用例</strong></p>

<p>测试用例是一个 <code>m x n</code> 的二维矩阵 <code>grid</code>，其中：</p>

<ul>
	<li><code>grid[i][j] == -1</code> 表明机器人一开始位于位置 <code>(i, j)</code> （即起点）。</li>
	<li><code>grid[i][j] == 0</code> 表明位置 <code>(i, j)</code> 不可通行。</li>
	<li><code>grid[i][j] == 1</code> 表明位置 <code>(i, j)</code> 可以通行.</li>
	<li><code>grid[i][j] == 2</code> 表明位置 <code>(i, j)</code> 是终点.</li>
</ul>

<p><code>grid</code> 里恰有一个<code>-1</code> 和一个 <code>2</code>。记住在你的代码中，你对这些信息将<strong>一无所知</strong>。</p>

<p><strong>示例1：</strong></p>

<pre>
<strong>输入:</strong> grid = [[1,2],[-1,0]]
<strong>输出:</strong> 2
<strong>解释:</strong> 一种可能的交互过程如下：
The robot is initially standing on cell (1, 0), denoted by the -1.
- master.canMove('U') 返回 true.
- master.canMove('D') 返回false.
- master.canMove('L') 返回 false.
- master.canMove('R') 返回 false.
- master.move('U') 把机器人移动到 (0, 0).
- master.isTarget() 返回 false.
- master.canMove('U') 返回 false.
- master.canMove('D') 返回 true.
- master.canMove('L') 返回 false.
- master.canMove('R') 返回 true.
- master.move('R') 把机器人移动到 (0, 1).
- master.isTarget() 返回 true. 
我们现在知道终点是点 (0, 1)，而且最短的路径是2.
</pre>

<p><strong>示例2:</strong></p>

<pre>
<strong>输入:</strong> grid = [[0,0,-1],[1,1,1],[2,0,0]]
<strong>输出:</strong> 4
<strong>解释:</strong> 机器人和终点的最短路径长是4.</pre>

<p><strong>示例3:</strong></p>

<pre>
<strong>输入:</strong> grid = [[-1,0],[0,2]]
<strong>输出:</strong> -1
<strong>解释:</strong> 机器人和终点间没有可通行的路径.</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n, m <= 500</code></li>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>grid[i][j]</code> 只可能是 <code>-1</code>, <code>0</code>, <code>1</code> 或 <code>2</code></li>
	<li><code>grid</code> 中 <strong>有且只有一个</strong> <code>-1</code></li>
	<li><code>grid</code> 中<strong> 有且只有一个</strong> <code>2</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS 建图 + BFS 求最短路**

我们不妨假设机器人从坐标 $(0, 0)$ 出发，那么我们可以通过 DFS，找到所有可达的坐标，记录在哈希表 $vis$ 中。另外，我们还需要记录终点的坐标 $target$。

如果找不到终点，那么直接返回 $-1$。否则，我们可以通过 BFS，求出最短路。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是网格的行数和列数。

相似题目：

-   [1810. 隐藏网格下的最小消耗路径](/solution/1800-1899/1810.Minimum%20Path%20Cost%20in%20a%20Hidden%20Grid/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# """
# This is GridMaster's API interface.
# You should not implement it, or speculate about its implementation
# """
# class GridMaster(object):
#    def canMove(self, direction: str) -> bool:
#
#
#    def move(self, direction: str) -> bool:
#
#
#    def isTarget(self) -> None:
#
#


class Solution(object):
    def findShortestPath(self, master: "GridMaster") -> int:
        def dfs(i: int, j: int):
            if master.isTarget():
                nonlocal target
                target = (i, j)
                return
            for k, c in enumerate(s):
                x, y = i + dirs[k], j + dirs[k + 1]
                if master.canMove(c) and (x, y) not in vis:
                    vis.add((x, y))
                    master.move(c)
                    dfs(x, y)
                    master.move(s[(k + 2) % 4])

        s = "URDL"
        dirs = (-1, 0, 1, 0, -1)
        target = None
        vis = set()
        dfs(0, 0)
        if target is None:
            return -1
        vis.discard((0, 0))
        q = deque([(0, 0)])
        ans = -1
        while q:
            ans += 1
            for _ in range(len(q)):
                i, j = q.popleft()
                if (i, j) == target:
                    return ans
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if (x, y) in vis:
                        vis.remove((x, y))
                        q.append((x, y))
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/**
 * // This is the GridMaster's API interface.
 * // You should not implement it, or speculate about its implementation
 * class GridMaster {
 *     boolean canMove(char direction);
 *     void move(char direction);
 *     boolean isTarget();
 * }
 */

class Solution {
    private int[] target;
    private GridMaster master;
    private final int n = 2010;
    private final String s = "URDL";
    private final int[] dirs = {-1, 0, 1, 0, -1};
    private final Set<Integer> vis = new HashSet<>();

    public int findShortestPath(GridMaster master) {
        this.master = master;
        dfs(0, 0);
        if (target == null) {
            return -1;
        }
        vis.remove(0);
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        for (int ans = 0; !q.isEmpty(); ++ans) {
            for (int m = q.size(); m > 0; --m) {
                var p = q.poll();
                if (p[0] == target[0] && p[1] == target[1]) {
                    return ans;
                }
                for (int k = 0; k < 4; ++k) {
                    int x = p[0] + dirs[k], y = p[1] + dirs[k + 1];
                    if (vis.remove(x * n + y)) {
                        q.offer(new int[] {x, y});
                    }
                }
            }
        }
        return -1;
    }

    private void dfs(int i, int j) {
        if (master.isTarget()) {
            target = new int[] {i, j};
            return;
        }
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (master.canMove(s.charAt(k)) && vis.add(x * n + y)) {
                master.move(s.charAt(k));
                dfs(x, y);
                master.move(s.charAt((k + 2) % 4));
            }
        }
    }
}
```

### **C++**

```cpp
/**
 * // This is the GridMaster's API interface.
 * // You should not implement it, or speculate about its implementation
 * class GridMaster {
 *   public:
 *     bool canMove(char direction);
 *     void move(char direction);
 *     boolean isTarget();
 * };
 */

class Solution {
private:
    const int n = 2010;
    int dirs[5] = {-1, 0, 1, 0, -1};
    string s = "URDL";
    int target;
    unordered_set<int> vis;

public:
    int findShortestPath(GridMaster& master) {
        target = n * n;
        vis.insert(0);
        dfs(0, 0, master);
        if (target == n * n) {
            return -1;
        }
        vis.erase(0);
        queue<pair<int, int>> q;
        q.emplace(0, 0);
        for (int ans = 0; q.size(); ++ans) {
            for (int m = q.size(); m; --m) {
                auto [i, j] = q.front();
                q.pop();
                if (i * n + j == target) {
                    return ans;
                }
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (vis.count(x * n + y)) {
                        vis.erase(x * n + y);
                        q.emplace(x, y);
                    }
                }
            }
        }
        return -1;
    }

    void dfs(int i, int j, GridMaster& master) {
        if (master.isTarget()) {
            target = i * n + j;
        }
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (master.canMove(s[k]) && !vis.count(x * n + y)) {
                vis.insert(x * n + y);
                master.move(s[k]);
                dfs(x, y, master);
                master.move(s[(k + 2) % 4]);
            }
        }
    }
};
```

### **...**

```

```

<!-- tabs:end -->
