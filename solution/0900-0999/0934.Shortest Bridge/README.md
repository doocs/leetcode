# [934. 最短的桥](https://leetcode.cn/problems/shortest-bridge)

[English Version](/solution/0900-0999/0934.Shortest%20Bridge/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>n x n</code> 的二元矩阵 <code>grid</code> ，其中 <code>1</code> 表示陆地，<code>0</code> 表示水域。</p>

<p><strong>岛</strong> 是由四面相连的 <code>1</code> 形成的一个最大组，即不会与非组内的任何其他 <code>1</code> 相连。<code>grid</code> 中 <strong>恰好存在两座岛</strong> 。</p>

<div class="original__bRMd">
<div>
<p>你可以将任意数量的 <code>0</code> 变为 <code>1</code> ，以使两座岛连接起来，变成 <strong>一座岛</strong> 。</p>

<p>返回必须翻转的 <code>0</code> 的最小数目。</p>
</div>
</div>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>grid = [[0,1],[1,0]]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[0,1,0],[0,0,0],[0,0,1]]
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
	<li><code>grid</code> 中恰有两个岛</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS + BFS**

题目求解的是最小翻转次数，使得两个岛屿相连，实际上等价于求解两个岛屿之间的最短距离。

因此，我们可以先通过 DFS 将其中一个岛屿的所有点找出来，放到一个队列 $q$ 中。然后通过 BFS 一层层向外扩展，直至碰到另一个岛屿，此时将当前扩展的层数作为答案返回即可。

在 DFS 和 BFS 搜索的过程中，我们直接将已经访问过的点标记为 $2$，这样就不会重复访问。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为矩阵的行数或列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shortestBridge(self, grid: List[List[int]]) -> int:
        def dfs(i, j):
            q.append((i, j))
            grid[i][j] = 2
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if 0 <= x < n and 0 <= y < n and grid[x][y] == 1:
                    dfs(x, y)

        n = len(grid)
        dirs = (-1, 0, 1, 0, -1)
        q = deque()
        i, j = next((i, j) for i in range(n) for j in range(n) if grid[i][j])
        dfs(i, j)
        ans = 0
        while 1:
            for _ in range(len(q)):
                i, j = q.popleft()
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < n and 0 <= y < n:
                        if grid[x][y] == 1:
                            return ans
                        if grid[x][y] == 0:
                            grid[x][y] = 2
                            q.append((x, y))
            ans += 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] dirs = {-1, 0, 1, 0, -1};
    private Deque<int[]> q = new ArrayDeque<>();
    private int[][] grid;
    private int n;

    public int shortestBridge(int[][] grid) {
        this.grid = grid;
        n = grid.length;
        for (int i = 0, x = 1; i < n && x == 1; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    dfs(i, j);
                    x = 0;
                    break;
                }
            }
        }
        int ans = 0;
        while (true) {
            for (int i = q.size(); i > 0; --i) {
                var p = q.pollFirst();
                for (int k = 0; k < 4; ++k) {
                    int x = p[0] + dirs[k], y = p[1] + dirs[k + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n) {
                        if (grid[x][y] == 1) {
                            return ans;
                        }
                        if (grid[x][y] == 0) {
                            grid[x][y] = 2;
                            q.offer(new int[] {x, y});
                        }
                    }
                }
            }
            ++ans;
        }
    }

    private void dfs(int i, int j) {
        grid[i][j] = 2;
        q.offer(new int[] {i, j});
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1) {
                dfs(x, y);
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const static inline vector<int> dirs = {-1, 0, 1, 0, -1};

    int shortestBridge(vector<vector<int>>& grid) {
        int n = grid.size();
        queue<pair<int, int>> q;
        function<void(int, int)> dfs = [&](int i, int j) {
            grid[i][j] = 2;
            q.emplace(i, j);
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1) {
                    dfs(x, y);
                }
            }
        };
        for (int i = 0, x = 1; i < n && x; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    dfs(i, j);
                    x = 0;
                    break;
                }
            }
        }
        int ans = 0;
        while (1) {
            for (int h = q.size(); h; --h) {
                auto [i, j] = q.front();
                q.pop();
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n) {
                        if (grid[x][y] == 1) return ans;
                        if (grid[x][y] == 0) {
                            grid[x][y] = 2;
                            q.emplace(x, y);
                        }
                    }
                }
            }
            ++ans;
        }
    }
};
```

### **Go**

```go
func shortestBridge(grid [][]int) (ans int) {
	n := len(grid)
	dirs := []int{-1, 0, 1, 0, -1}
	type pair struct{ i, j int }
	q := []pair{}
	var dfs func(int, int)
	dfs = func(i, j int) {
		grid[i][j] = 2
		q = append(q, pair{i, j})
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1 {
				dfs(x, y)
			}
		}
	}
	for i, x := 0, 1; i < n && x == 1; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				dfs(i, j)
				x = 0
				break
			}
		}
	}
	for {
		for i := len(q); i > 0; i-- {
			p := q[0]
			q = q[1:]
			for k := 0; k < 4; k++ {
				x, y := p.i+dirs[k], p.j+dirs[k+1]
				if x >= 0 && x < n && y >= 0 && y < n {
					if grid[x][y] == 1 {
						return
					}
					if grid[x][y] == 0 {
						grid[x][y] = 2
						q = append(q, pair{x, y})
					}
				}
			}
		}
		ans++
	}
}
```

### **...**

```

```

<!-- tabs:end -->
