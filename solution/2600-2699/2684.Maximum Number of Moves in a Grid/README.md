# [2684. 矩阵中移动的最大次数](https://leetcode.cn/problems/maximum-number-of-moves-in-a-grid)

[English Version](/solution/2600-2699/2684.Maximum%20Number%20of%20Moves%20in%20a%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始、大小为 <code>m x n</code> 的矩阵 <code>grid</code> ，矩阵由若干 <strong>正</strong> 整数组成。</p>

<p>你可以从矩阵第一列中的 <strong>任一</strong> 单元格出发，按以下方式遍历&nbsp;<code>grid</code> ：</p>

<ul>
	<li>从单元格 <code>(row, col)</code> 可以移动到&nbsp;<code>(row - 1, col + 1)</code>、<code>(row, col + 1)</code> 和 <code>(row + 1, col + 1)</code> 三个单元格中任一满足值 <strong>严格</strong> 大于当前单元格的单元格。</li>
</ul>

<p>返回你在矩阵中能够 <strong>移动</strong> 的 <strong>最大</strong> 次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2684.Maximum%20Number%20of%20Moves%20in%20a%20Grid/images/yetgriddrawio-10.png" style="width: 201px; height: 201px;">
<pre><strong>输入：</strong>grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
<strong>输出：</strong>3
<strong>解释：</strong>可以从单元格 (0, 0) 开始并且按下面的路径移动：
- (0, 0) -&gt; (0, 1).
- (0, 1) -&gt; (1, 2).
- (1, 2) -&gt; (2, 3).
可以证明这是能够移动的最大次数。</pre>

<p><strong>示例 2：</strong></p>

<pre><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2684.Maximum%20Number%20of%20Moves%20in%20a%20Grid/images/yetgrid4drawio.png">
<strong>输入：</strong>grid = [[3,2,4],[2,1,9],[1,1,7]]
<strong>输出：</strong>0
<strong>解释：</strong>从第一列的任一单元格开始都无法移动。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 1000</code></li>
	<li><code>4 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

我们定义一个队列 $q$，初始时将第一列的所有单元格 $(i, 0)$ 加入队列，同时定义一个二维数组 $dist$，其中 $dist[i][j]$ 表示到达单元格 $(i, j)$ 的最大移动次数。初始时，$dist[i][j] = 0$。

接下来，我们开始进行广度优先搜索。每次取出队首的单元格 $(i, j)$，并考虑其可以到达的下一层的单元格 $(x, y)$。如果 $x$ 和 $y$ 满足 $0 \leq x < m, 0 \leq y < n$，且 $grid[x][y] \gt grid[i][j]$，并且 $dist[x][y] \lt dist[i][j] + 1$，那么我们就可以从单元格 $(i, j)$ 移动到单元格 $(x, y)$，此时我们将 $dist[x][y]$ 更新为 $dist[i][j] + 1$，并将单元格 $(x, y)$ 加入队列 $q$ 中，然后更新答案 $ans = \max(ans, dist[x][y])$。

当队列为空时，我们就找到了矩阵中移动的最大次数，返回 $ans$ 即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxMoves(self, grid: List[List[int]]) -> int:
        dirs = ((-1, 1), (0, 1), (1, 1))
        m, n = len(grid), len(grid[0])
        q = deque((i, 0) for i in range(m))
        dist = [[0] * n for _ in range(m)]
        ans = 0
        while q:
            i, j = q.popleft()
            for a, b in dirs:
                x, y = i + a, j + b
                if (
                    0 <= x < m
                    and 0 <= y < n
                    and grid[x][y] > grid[i][j]
                    and dist[x][y] < dist[i][j] + 1
                ):
                    dist[x][y] = dist[i][j] + 1
                    ans = max(ans, dist[x][y])
                    q.append((x, y))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxMoves(int[][] grid) {
        int[][] dirs = {{-1, 1}, {0, 1}, {1, 1}};
        int m = grid.length, n = grid[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; ++i) {
            q.offer(new int[] {i, 0});
        }
        int[][] dist = new int[m][n];
        int ans = 0;
        while (!q.isEmpty()) {
            var p = q.poll();
            int i = p[0], j = p[1];
            for (var dir : dirs) {
                int x = i + dir[0], y = j + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > grid[i][j]
                    && dist[x][y] < dist[i][j] + 1) {
                    dist[x][y] = dist[i][j] + 1;
                    ans = Math.max(ans, dist[x][y]);
                    q.offer(new int[] {x, y});
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxMoves(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int dist[m][n];
        memset(dist, 0, sizeof(dist));
        int ans = 0;
        queue<pair<int, int>> q;
        for (int i = 0; i < m; ++i) {
            q.emplace(i, 0);
        }
        int dirs[3][2] = {{-1, 1}, {0, 1}, {1, 1}};
        while (!q.empty()) {
            auto [i, j] = q.front();
            q.pop();
            for (int k = 0; k < 3; ++k) {
                int x = i + dirs[k][0], y = j + dirs[k][1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > grid[i][j] && dist[x][y] < dist[i][j] + 1) {
                    dist[x][y] = dist[i][j] + 1;
                    ans = max(ans, dist[x][y]);
                    q.emplace(x, y);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxMoves(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	dist := make([][]int, m)
	q := [][2]int{}
	for i := range dist {
		dist[i] = make([]int, n)
		q = append(q, [2]int{i, 0})
	}
	dirs := [][2]int{{-1, 1}, {0, 1}, {1, 1}}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		i, j := p[0], p[1]
		for _, dir := range dirs {
			x, y := i+dir[0], j+dir[1]
			if 0 <= x && x < m && 0 <= y && y < n && grid[x][y] > grid[i][j] && dist[x][y] < dist[i][j]+1 {
				dist[x][y] = dist[i][j] + 1
				ans = max(ans, dist[x][y])
				q = append(q, [2]int{x, y})
			}
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
