---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1778.Shortest%20Path%20in%20a%20Hidden%20Grid/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Array
    - Interactive
    - Matrix
---

<!-- problem:start -->

# [1778. Shortest Path in a Hidden Grid 🔒](https://leetcode.com/problems/shortest-path-in-a-hidden-grid)

[中文文档](/solution/1700-1799/1778.Shortest%20Path%20in%20a%20Hidden%20Grid/README.md)

## Description

<!-- description:start -->

<p>This is an <strong>interactive problem</strong>.</p>

<p>There is a robot in a hidden grid, and you are trying to get it from its starting cell to the target cell in this grid. The grid is of size <code>m x n</code>, and each cell in the grid is either empty or blocked. It is <strong>guaranteed</strong> that the starting cell and the target cell are different, and neither of them is blocked.</p>

<p>You want to find the minimum distance to the target cell. However, you <strong>do not know</strong> the grid&#39;s dimensions, the starting cell, nor the target cell. You are only allowed to ask queries to the <code>GridMaster</code> object.</p>

<p>Thr <code>GridMaster</code> class has the following functions:</p>

<ul>
	<li><code>boolean canMove(char direction)</code> Returns <code>true</code> if the robot can move in that direction. Otherwise, it returns <code>false</code>.</li>
	<li><code>void move(char direction)</code> Moves the robot in that direction. If this move would move the robot to a blocked cell or off the grid, the move will be <strong>ignored</strong>, and the robot will remain in the same position.</li>
	<li><code>boolean isTarget()</code> Returns <code>true</code> if the robot is currently on the target cell. Otherwise, it returns <code>false</code>.</li>
</ul>

<p>Note that <code>direction</code> in the above functions should be a character from <code>{&#39;U&#39;,&#39;D&#39;,&#39;L&#39;,&#39;R&#39;}</code>, representing the directions up, down, left, and right, respectively.</p>

<p>Return <em>the <strong>minimum distance</strong> between the robot&#39;s initial starting cell and the target cell. If there is no valid path between the cells, return </em><code>-1</code>.</p>

<p><strong>Custom testing:</strong></p>

<p>The test input is read as a 2D matrix <code>grid</code> of size <code>m x n</code> where:</p>

<ul>
	<li><code>grid[i][j] == -1</code> indicates that the robot is in cell <code>(i, j)</code> (the starting cell).</li>
	<li><code>grid[i][j] == 0</code> indicates that the cell <code>(i, j)</code> is blocked.</li>
	<li><code>grid[i][j] == 1</code> indicates that the cell <code>(i, j)</code> is empty.</li>
	<li><code>grid[i][j] == 2</code> indicates that the cell <code>(i, j)</code> is the target cell.</li>
</ul>

<p>There is exactly one <code>-1</code> and <code>2</code> in <code>grid</code>. Remember that you will <strong>not</strong> have this information in your code.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,2],[-1,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> One possible interaction is described below:
The robot is initially standing on cell (1, 0), denoted by the -1.
- master.canMove(&#39;U&#39;) returns true.
- master.canMove(&#39;D&#39;) returns false.
- master.canMove(&#39;L&#39;) returns false.
- master.canMove(&#39;R&#39;) returns false.
- master.move(&#39;U&#39;) moves the robot to the cell (0, 0).
- master.isTarget() returns false.
- master.canMove(&#39;U&#39;) returns false.
- master.canMove(&#39;D&#39;) returns true.
- master.canMove(&#39;L&#39;) returns false.
- master.canMove(&#39;R&#39;) returns true.
- master.move(&#39;R&#39;) moves the robot to the cell (0, 1).
- master.isTarget() returns true. 
We now know that the target is the cell (0, 1), and the shortest path to the target cell is 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[0,0,-1],[1,1,1],[2,0,0]]
<strong>Output:</strong> 4
<strong>Explanation:</strong>&nbsp;The minimum distance between the robot and the target cell is 4.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[-1,0],[0,2]]
<strong>Output:</strong> -1
<strong>Explanation:</strong>&nbsp;There is no path from the robot to the target cell.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, m &lt;= 500</code></li>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>grid[i][j]</code> is either <code>-1</code>, <code>0</code>, <code>1</code>, or <code>2</code>.</li>
	<li>There is <strong>exactly one</strong> <code>-1</code> in <code>grid</code>.</li>
	<li>There is <strong>exactly one</strong> <code>2</code> in <code>grid</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS for Graph Construction + BFS for Shortest Path

We can assume that the robot starts from the coordinate $(0, 0)$. Then, we can use DFS to find all reachable coordinates and record them in the hash table $vis$. In addition, we also need to record the coordinates of the endpoint $target$.

If the endpoint cannot be found, we directly return $-1$. Otherwise, we can use BFS to find the shortest path.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Where $m$ and $n$ are the number of rows and columns of the grid, respectively.

Similar problems:

-   [1810. Minimum Path Cost in a Hidden Grid](https://github.com/doocs/leetcode/blob/main/solution/1800-1899/1810.Minimum%20Path%20Cost%20in%20a%20Hidden%20Grid/README_EN.md)

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

#### Java

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

#### C++

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

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
