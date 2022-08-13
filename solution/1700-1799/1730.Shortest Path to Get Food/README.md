# [1730. 获取食物的最短路径](https://leetcode.cn/problems/shortest-path-to-get-food)

[English Version](/solution/1700-1799/1730.Shortest%20Path%20to%20Get%20Food/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你现在很饿，想要尽快找东西吃。你需要找到最短的路径到达一个食物所在的格子。</p>

<p>给定一个&nbsp;<code>m x n</code>&nbsp;的字符矩阵&nbsp;<code>grid</code>&nbsp;，包含下列不同类型的格子：</p>

<ul>
	<li><code>'*'</code>&nbsp;是你的位置。矩阵中<strong>有且只有一个&nbsp;</strong><code>'*'</code>&nbsp;格子。</li>
	<li><code>'#'</code> 是食物。矩阵中可能存在<strong>多个</strong>食物。</li>
	<li><code>'O'</code>&nbsp;是空地，你可以穿过这些格子。</li>
	<li><code>'X'</code>&nbsp;是障碍，你不可以穿过这些格子。</li>
</ul>

<p>返回你到任意食物的最短路径的长度。如果不存在你到任意食物的路径，返回&nbsp;<code>-1</code>。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1730.Shortest%20Path%20to%20Get%20Food/images/img1.jpg" style="width: 300px; height: 201px;" />
<pre>
<b>输入：</b> grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
<b>输出：</b> 3
<b>解释： </b>要拿到食物，你需要走 3 步。</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1730.Shortest%20Path%20to%20Get%20Food/images/img2.jpg" style="width: 300px; height: 241px;" />
<pre>
<b>输入：</b> grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
<b>输出：</b> -1
<b>解释：</b> 你不可能拿到食物。
</pre>

<p><strong>示例&nbsp;3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1730.Shortest%20Path%20to%20Get%20Food/images/img3.jpg" style="width: 300px; height: 188px;" />
<pre>
<strong>输入:</strong> grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
<strong>输出:</strong> 6
<strong>解释:</strong> 这里有多个食物。拿到下边的食物仅需走 6 步。</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>grid[row][col]</code>&nbsp;是&nbsp;<code>'*'</code>、&nbsp;<code>'X'</code>、&nbsp;<code>'O'</code>&nbsp;或&nbsp;<code>'#'</code>&nbsp;。</li>
	<li><code>grid</code>&nbsp;中<strong>有且只有一个</strong>&nbsp;<code>'*'</code>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

BFS。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getFood(self, grid: List[List[str]]) -> int:
        def pos():
            for i in range(m):
                for j in range(n):
                    if grid[i][j] == '*':
                        return i, j

        m, n = len(grid), len(grid[0])
        q = deque([pos()])
        ans = 0
        while q:
            ans += 1
            for _ in range(len(q)):
                i, j = q.popleft()
                for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n:
                        if grid[x][y] == '#':
                            return ans
                        if grid[x][y] == 'O':
                            grid[x][y] = 'X'
                            q.append((x, y))
        return -1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int getFood(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Deque<int[]> q = new LinkedList<>();
        q.offer(pos(grid));
        int ans = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            ++ans;
            for (int i = q.size(); i > 0; --i) {
                int[] p = q.poll();
                for (int j = 0; j < 4; ++j) {
                    int x = p[0] + dirs[j];
                    int y = p[1] + dirs[j + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        if (grid[x][y] == '#') {
                            return ans;
                        }
                        if (grid[x][y] == 'O') {
                            grid[x][y] = 'X';
                            q.offer(new int[]{x, y});
                        }
                    }
                }
            }
        }
        return -1;
    }

    private int[] pos(char[][] grid) {
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == '*') {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
```

### **C++**

```cpp
typedef pair<int, int> pii;

class Solution {
public:
    int getFood(vector<vector<char>>& grid) {
        int m = grid.size(), n = grid[0].size();
        queue<pii> q {{pos(grid)}};
        int ans = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            ++ans;
            for (int i = q.size(); i > 0; --i) {
                pii p = q.front();
                q.pop();
                for (int j = 0; j < 4; ++j) {
                    int x = p.first + dirs[j];
                    int y = p.second + dirs[j + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        if (grid[x][y] == '#') return ans;
                        if (grid[x][y] == 'O') {
                            grid[x][y] = 'X';
                            q.push({x, y});
                        }
                    }
                }
            }
        }
        return -1;
    }

    pii pos(vector<vector<char>>& grid) {
        for (int i = 0; i < grid.size(); ++i)
            for (int j = 0; j < grid[0].size(); ++j)
                if (grid[i][j] == '*')
                    return {i, j};
        return {};
    }
};
```

### **Go**

```go
func getFood(grid [][]byte) int {
	m, n := len(grid), len(grid[0])
	pos := func() []int {
		for i := 0; i < m; i++ {
			for j := 0; j < n; j++ {
				if grid[i][j] == '*' {
					return []int{i, j}
				}
			}
		}
		return []int{}
	}
	q := [][]int{pos()}
	dirs := []int{-1, 0, 1, 0, -1}
	ans := 0
	for len(q) > 0 {
		ans++
		for i := len(q); i > 0; i-- {
			p := q[0]
			q = q[1:]
			for j := 0; j < 4; j++ {
				x, y := p[0]+dirs[j], p[1]+dirs[j+1]
				if x >= 0 && x < m && y >= 0 && y < n {
					if grid[x][y] == '#' {
						return ans
					}
					if grid[x][y] == 'O' {
						grid[x][y] = 'X'
						q = append(q, []int{x, y})
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
