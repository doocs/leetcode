# [1293. 网格中的最短路径](https://leetcode.cn/problems/shortest-path-in-a-grid-with-obstacles-elimination)

[English Version](/solution/1200-1299/1293.Shortest%20Path%20in%20a%20Grid%20with%20Obstacles%20Elimination/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>m * n</code>&nbsp;的网格，其中每个单元格不是&nbsp;<code>0</code>（空）就是&nbsp;<code>1</code>（障碍物）。每一步，您都可以在空白单元格中上、下、左、右移动。</p>

<p>如果您 <strong>最多</strong> 可以消除 <code>k</code> 个障碍物，请找出从左上角 <code>(0, 0)</code> 到右下角 <code>(m-1, n-1)</code> 的最短路径，并返回通过该路径所需的步数。如果找不到这样的路径，则返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1293.Shortest%20Path%20in%20a%20Grid%20with%20Obstacles%20Elimination/images/short1-grid.jpg" /></p>

<pre>
<strong>输入：</strong> grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
<strong>输出：</strong>6
<strong>解释：
</strong>不消除任何障碍的最短路径是 10。
消除位置 (3,2) 处的障碍后，最短路径是 6 。该路径是 <code>(0,0) -&gt; (0,1) -&gt; (0,2) -&gt; (1,2) -&gt; (2,2) -&gt; <strong>(3,2)</strong> -&gt; (4,2)</code>.
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1293.Shortest%20Path%20in%20a%20Grid%20with%20Obstacles%20Elimination/images/short2-grid.jpg" /></p>

<pre>
<strong>输入：</strong>grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
<strong>输出：</strong>-1
<strong>解释：</strong>我们至少需要消除两个障碍才能找到这样的路径。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>grid.length&nbsp;== m</code></li>
	<li><code>grid[0].length&nbsp;== n</code></li>
	<li><code>1 &lt;= m, n &lt;= 40</code></li>
	<li><code>1 &lt;= k &lt;= m*n</code></li>
	<li><code>grid[i][j]</code>&nbsp;是&nbsp;<code>0</code>&nbsp;或<strong>&nbsp;</strong><code>1</code></li>
	<li><code>grid[0][0] == grid[m-1][n-1] == 0</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

BFS 最短路模型。

对于本题，如果 `k >= m + n - 3`，那么最短路径长度一定是 `m + n - 2`，直接返回，无需 BFS 计算。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shortestPath(self, grid: List[List[int]], k: int) -> int:
        m, n = len(grid), len(grid[0])
        if k >= m + n - 3:
            return m + n - 2
        q = deque([(0, 0, k)])
        vis = {(0, 0, k)}
        ans = 0
        while q:
            ans += 1
            for _ in range(len(q)):
                i, j, k = q.popleft()
                for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n:
                        if x == m - 1 and y == n - 1:
                            return ans
                        if grid[x][y] == 0 and (x, y, k) not in vis:
                            q.append((x, y, k))
                            vis.add((x, y, k))
                        if grid[x][y] == 1 and k > 0 and (x, y, k - 1) not in vis:
                            q.append((x, y, k - 1))
                            vis.add((x, y, k - 1))
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        if (k >= m + n - 3) {
            return m + n - 2;
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0, k});
        boolean[][][] vis = new boolean[m][n][k + 1];
        vis[0][0][k] = true;
        int ans = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            ++ans;
            for (int i = q.size(); i > 0; --i) {
                int[] p = q.poll();
                k = p[2];
                for (int j = 0; j < 4; ++j) {
                    int x = p[0] + dirs[j];
                    int y = p[1] + dirs[j + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        if (x == m - 1 && y == n - 1) {
                            return ans;
                        }
                        if (grid[x][y] == 0 && !vis[x][y][k]) {
                            q.offer(new int[] {x, y, k});
                            vis[x][y][k] = true;
                        } else if (grid[x][y] == 1 && k > 0 && !vis[x][y][k - 1]) {
                            q.offer(new int[] {x, y, k - 1});
                            vis[x][y][k - 1] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int shortestPath(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        if (k >= m + n - 3) return m + n - 2;
        queue<vector<int>> q;
        q.push({0, 0, k});
        vector<vector<vector<bool>>> vis(m, vector<vector<bool>>(n, vector<bool>(k + 1)));
        vis[0][0][k] = true;
        int ans = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            ++ans;
            for (int i = q.size(); i > 0; --i) {
                auto p = q.front();
                k = p[2];
                q.pop();
                for (int j = 0; j < 4; ++j) {
                    int x = p[0] + dirs[j], y = p[1] + dirs[j + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        if (x == m - 1 && y == n - 1) return ans;
                        if (grid[x][y] == 0 && !vis[x][y][k]) {
                            q.push({x, y, k});
                            vis[x][y][k] = true;
                        } else if (grid[x][y] == 1 && k > 0 && !vis[x][y][k - 1]) {
                            q.push({x, y, k - 1});
                            vis[x][y][k - 1] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }
};
```

### **Go**

```go
func shortestPath(grid [][]int, k int) int {
	m, n := len(grid), len(grid[0])
	if k >= m+n-3 {
		return m + n - 2
	}
	q := [][]int{[]int{0, 0, k}}
	vis := make([][][]bool, m)
	for i := range vis {
		vis[i] = make([][]bool, n)
		for j := range vis[i] {
			vis[i][j] = make([]bool, k+1)
		}
	}
	vis[0][0][k] = true
	dirs := []int{-1, 0, 1, 0, -1}
	ans := 0
	for len(q) > 0 {
		ans++
		for i := len(q); i > 0; i-- {
			p := q[0]
			q = q[1:]
			k = p[2]
			for j := 0; j < 4; j++ {
				x, y := p[0]+dirs[j], p[1]+dirs[j+1]
				if x >= 0 && x < m && y >= 0 && y < n {
					if x == m-1 && y == n-1 {
						return ans
					}
					if grid[x][y] == 0 && !vis[x][y][k] {
						q = append(q, []int{x, y, k})
						vis[x][y][k] = true
					} else if grid[x][y] == 1 && k > 0 && !vis[x][y][k-1] {
						q = append(q, []int{x, y, k - 1})
						vis[x][y][k-1] = true
					}
				}
			}
		}
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
