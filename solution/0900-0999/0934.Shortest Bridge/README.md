# [934. 最短的桥](https://leetcode.cn/problems/shortest-bridge)

[English Version](/solution/0900-0999/0934.Shortest%20Bridge/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在给定的二维二进制数组 <code>A</code> 中，存在两座岛。（岛是由四面相连的 <code>1</code> 形成的一个最大组。）</p>

<p>现在，我们可以将 <code>0</code> 变为 <code>1</code>，以使两座岛连接起来，变成一座岛。</p>

<p>返回必须翻转的 <code>0</code> 的最小数目。（可以保证答案至少是 <code>1</code> 。）</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>A = [[0,1],[1,0]]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>A = [[0,1,0],[0,0,0],[0,0,1]]
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
<strong>输出：</strong>1</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= A.length == A[0].length <= 100</code></li>
	<li><code>A[i][j] == 0</code> 或 <code>A[i][j] == 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS & BFS。

先通过 DFS 将其中一个岛屿的所有 1 置为 2，并将这个岛屿所有坐标点放入队列中；然后通过 BFS 一层层向外扩散，直至碰到另一个岛屿。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shortestBridge(self, grid: List[List[int]]) -> int:
        def find():
            for i in range(m):
                for j in range(n):
                    if grid[i][j] == 1:
                        return i, j

        def dfs(i, j):
            q.append((i, j))
            grid[i][j] = 2
            for a, b in dirs:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and grid[x][y] == 1:
                    dfs(x, y)

        m, n = len(grid), len(grid[0])
        q = deque()
        dirs = [[0, 1], [0, -1], [1, 0], [-1, 0]]
        i, j = find()
        dfs(i, j)
        ans = -1
        while q:
            ans += 1
            for _ in range(len(q)):
                i, j = q.popleft()
                for a, b in dirs:
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n:
                        if grid[x][y] == 1:
                            return ans
                        if grid[x][y] == 0:
                            grid[x][y] = 2
                            q.append((x, y))
        return 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[][] grid;
    private int[] dirs = {-1, 0, 1, 0, -1};
    private int m;
    private int n;

    public int shortestBridge(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int[] start = find();
        Queue<int[]> q = new LinkedList<>();
        dfs(start[0], start[1], q);
        int ans = -1;
        while (!q.isEmpty()) {
            ++ans;
            for (int k = q.size(); k > 0; --k) {
                int[] p = q.poll();
                for (int i = 0; i < 4; ++i) {
                    int x = p[0] + dirs[i];
                    int y = p[1] + dirs[i + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        if (grid[x][y] == 1) {
                            return ans;
                        }
                        if (grid[x][y] == 0) {
                            grid[x][y] = 2;
                            q.offer(new int[]{x, y});
                        }
                    }
                }
            }
        }
        return 0;
    }

    private void dfs(int i, int j, Queue<int[]> q) {
        grid[i][j] = 2;
        q.offer(new int[]{i, j});
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                dfs(x, y, q);
            }
        }
    }

    private int[] find() {
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> dirs = {-1, 0, 1, 0, -1};

    int shortestBridge(vector<vector<int>>& grid) {
        vector<int> start = find(grid);
        queue<vector<int>> q;
        dfs(start[0], start[1], q, grid);
        int ans = -1;
        while (!q.empty()) {
            ++ans;
            for (int k = q.size(); k > 0; --k) {
                auto p = q.front();
                q.pop();
                for (int i = 0; i < 4; ++i) {
                    int x = p[0] + dirs[i];
                    int y = p[1] + dirs[i + 1];
                    if (x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size()) {
                        if (grid[x][y] == 1) return ans;
                        if (grid[x][y] == 0) {
                            grid[x][y] = 2;
                            q.push({x, y});
                        }
                    }
                }
            }
        }
        return 0;
    }

    void dfs(int i, int j, queue<vector<int>>& q, vector<vector<int>>& grid) {
        grid[i][j] = 2;
        q.push({i, j});
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size() && grid[x][y] == 1)
                dfs(x, y, q, grid);
        }
    }

    vector<int> find(vector<vector<int>>& grid) {
        for (int i = 0; i < grid.size(); ++i)
            for (int j = 0; j < grid[0].size(); ++j)
                if (grid[i][j] == 1)
                    return {i, j};
        return {0, 0};
    }
};
```

### **Go**

```go
func shortestBridge(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	find := func() []int {
		for i := 0; i < m; i++ {
			for j := 0; j < n; j++ {
				if grid[i][j] == 1 {
					return []int{i, j}
				}
			}
		}
		return []int{0, 0}
	}
	start := find()
	var q [][]int
	dirs := []int{-1, 0, 1, 0, -1}
	var dfs func(i, j int)
	dfs = func(i, j int) {
		grid[i][j] = 2
		q = append(q, []int{i, j})
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 {
				dfs(x, y)
			}
		}
	}
	dfs(start[0], start[1])
	ans := -1
	for len(q) > 0 {
		ans++
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			for i := 0; i < 4; i++ {
				x, y := p[0]+dirs[i], p[1]+dirs[i+1]
				if x >= 0 && x < m && y >= 0 && y < n {
					if grid[x][y] == 1 {
						return ans
					}
					if grid[x][y] == 0 {
						grid[x][y] = 2
						q = append(q, []int{x, y})
					}
				}
			}
		}
	}
	return 0
}
```

### **...**

```

```

<!-- tabs:end -->
