# [2617. 网格图中最少访问的格子数](https://leetcode.cn/problems/minimum-number-of-visited-cells-in-a-grid)

[English Version](/solution/2600-2699/2617.Minimum%20Number%20of%20Visited%20Cells%20in%20a%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的&nbsp;<code>m x n</code>&nbsp;整数矩阵&nbsp;<code>grid</code>&nbsp;。你一开始的位置在&nbsp;<strong>左上角</strong>&nbsp;格子&nbsp;<code>(0, 0)</code>&nbsp;。</p>

<p>当你在格子&nbsp;<code>(i, j)</code>&nbsp;的时候，你可以移动到以下格子之一：</p>

<ul>
	<li>满足 <code>j &lt; k &lt;= grid[i][j] + j</code>&nbsp;的格子&nbsp;<code>(i, k)</code>&nbsp;（向右移动），或者</li>
	<li>满足 <code>i &lt; k &lt;= grid[i][j] + i</code>&nbsp;的格子&nbsp;<code>(k, j)</code>&nbsp;（向下移动）。</li>
</ul>

<p>请你返回到达 <strong>右下角</strong>&nbsp;格子&nbsp;<code>(m - 1, n - 1)</code>&nbsp;需要经过的最少移动格子数，如果无法到达右下角格子，请你返回&nbsp;<code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2617.Minimum%20Number%20of%20Visited%20Cells%20in%20a%20Grid/images/ex1.png" style="width: 271px; height: 171px;"></p>

<pre><b>输入：</b>grid = [[3,4,2,1],[4,2,3,1],[2,1,0,0],[2,4,0,0]]
<b>输出：</b>4
<b>解释：</b>上图展示了到达右下角格子经过的 4 个格子。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2617.Minimum%20Number%20of%20Visited%20Cells%20in%20a%20Grid/images/ex2.png" style="width: 271px; height: 171px;"></p>

<pre><b>输入：</b>grid = [[3,4,2,1],[4,2,1,1],[2,1,1,0],[3,4,1,0]]
<b>输出：</b>3
<strong>解释：</strong>上图展示了到达右下角格子经过的 3 个格子。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2617.Minimum%20Number%20of%20Visited%20Cells%20in%20a%20Grid/images/ex3.png" style="width: 181px; height: 81px;"></p>

<pre><b>输入：</b>grid = [[2,1,0],[1,0,0]]
<b>输出：</b>-1
<b>解释：</b>无法到达右下角格子。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= grid[i][j] &lt; m * n</code></li>
	<li><code>grid[m - 1][n - 1] == 0</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

我们用 $row[i]$ 表示第 $i$ 行已经访问到的最右边的列，用 $col[j]$ 表示第 $j$ 列已经访问到的最下边的行。初始时，$row[0] = col[0] = 0$。

接下来，我们定义一个队列，用于存储当前可以访问的格子。队列中的元素为一个三元组 $(dist, i, j)$，表示从起点 $(0, 0)$ 到达 $(i, j)$ 的最短距离为 $dist$。初始时，我们将 $(1, 0, 0)$ 加入队列中。

在每一步中，我们从队首取出一个元素 $(dist, i, j)$，如果 $(i, j)$ 为终点，则直接返回 $dist$。否则，我们将 $(i, j)$ 右边的格子加入队列中，其中右边的格子的下标范围为 $[max(row[i], j) + 1, min(n, j + grid[i][j] + 1))$。同理，我们将 $(i, j)$ 下边的格子加入队列中，其中下边的格子的下标范围为 $[max(col[j], i) + 1, min(m, i + grid[i][j] + 1))$。加入格子后，我们需要更新 $row[i]$ 和 $col[j]$。

最后，如果我们遍历完整个队列都没有找到终点，则说明无法到达终点，返回 $-1$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为网格的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumVisitedCells(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        row = [0] * m
        col = [0] * n
        q = deque([(1, 0, 0)])
        while q:
            dist, i, j = q.popleft()
            if i == m - 1 and j == n - 1:
                return dist
            for k in range(max(row[i], j) + 1, min(n, j + grid[i][j] + 1)):
                q.append((dist + 1, i, k))
                row[i] = k
            for k in range(max(col[j], i) + 1, min(m, i + grid[i][j] + 1)):
                q.append((dist + 1, k, j))
                col[j] = k
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {1, 0, 0});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[1], j = p[2], dist = p[0];
            if (i == m - 1 && j == n - 1) {
                return dist;
            }
            int k = Math.max(row[i], j) + 1;
            for (; k < Math.min(n, j + grid[i][j] + 1); ++k) {
                q.offer(new int[] {dist + 1, i, k});
                row[i] = k;
            }
            k = Math.max(col[j], i) + 1;
            for (; k < Math.min(m, i + grid[i][j] + 1); ++k) {
                q.offer(new int[] {dist + 1, k, j});
                col[j] = k;
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
    int minimumVisitedCells(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int row[m], col[n];
        memset(row, 0, sizeof(row));
        memset(col, 0, sizeof(col));
        queue<tuple<int, int, int>> q;
        q.emplace(1, 0, 0);
        while (!q.empty()) {
            auto [dist, i, j] = q.front();
            q.pop();
            if (i == m - 1 && j == n - 1) {
                return dist;
            }
            for (int k = max(row[i], j) + 1; k < min(n, j + grid[i][j] + 1); ++k) {
                q.emplace(dist + 1, i, k);
                row[i] = k;
            }
            for (int k = max(col[j], i) + 1; k < min(m, i + grid[i][j] + 1); ++k) {
                q.emplace(dist + 1, k, j);
                col[j] = k;
            }
        }
        return -1;
    }
};
```

### **Go**

```go
func minimumVisitedCells(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	row := make([]int, m)
	col := make([]int, n)
	q := [][3]int{{1, 0, 0}}
	for len(q) > 0 {
		p := q[0]
		dist, i, j := p[0], p[1], p[2]
		if i == m-1 && j == n-1 {
			return dist
		}
		q = q[1:]
		for k := max(row[i], j) + 1; k < min(n, j+grid[i][j]+1); k++ {
			q = append(q, [3]int{dist + 1, i, k})
			row[i] = k
		}
		for k := max(col[j], i) + 1; k < min(m, i+grid[i][j]+1); k++ {
			q = append(q, [3]int{dist + 1, k, j})
			col[j] = k
		}
	}
	return -1
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
