# [317. 离建筑物最近的距离 🔒](https://leetcode.cn/problems/shortest-distance-from-all-buildings)

[English Version](/solution/0300-0399/0317.Shortest%20Distance%20from%20All%20Buildings/README_EN.md)

<!-- tags:广度优先搜索,数组,矩阵 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>m × n</code> 的网格，值为 <code>0</code> 、 <code>1</code> 或 <code>2</code> ，其中:</p>

<ul>
	<li>每一个 <code>0</code> 代表一块你可以自由通过的 <strong>空地</strong>&nbsp;</li>
	<li>每一个 <code>1</code> 代表一个你不能通过的 <strong>建筑</strong></li>
	<li>每个 <code>2</code> 标记一个你不能通过的 <strong>障碍</strong>&nbsp;</li>
</ul>

<p>你想要在一块空地上建造一所房子，在 <strong>最短的总旅行距离</strong> 内到达所有的建筑。你只能上下左右移动。</p>

<p>返回到该房子的 <strong>最短旅行距离</strong> 。如果根据上述规则无法建造这样的房子，则返回 <code>-1</code> 。</p>

<p><strong>总旅行距离&nbsp;</strong>是朋友们家到聚会地点的距离之和。</p>

<p>使用 <strong>曼哈顿距离</strong>&nbsp;计算距离，其中距离 <code>(p1, p2) = |p2.x - p1.x | + | p2.y - p1.y |</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp; 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0317.Shortest%20Distance%20from%20All%20Buildings/images/buildings-grid.jpg" /></p>

<pre>
<strong>输入：</strong>grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
<strong>输出：</strong>7 
<strong>解析：</strong>给定<code>三个建筑物 (0,0)、</code><code>(0,4) 和</code> <code>(2,2) 以及一个</code>位于 <code>(0,2) 的障碍物。
由于总距离之和 3+3+1=7 最优，所以位置</code> <code>(1,2)</code> 是符合要求的最优地点。
故返回7。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> grid = [[1,0]]
<strong>输出:</strong> 1
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> grid = [[1]]
<strong>输出:</strong> -1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>grid[i][j]</code>&nbsp;是&nbsp;<code>0</code>,&nbsp;<code>1</code>&nbsp;或&nbsp;<code>2</code></li>
	<li><code>grid</code>&nbsp;中 <strong>至少</strong>&nbsp;有 <strong>一幢</strong> 建筑</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def shortestDistance(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        q = deque()
        total = 0
        cnt = [[0] * n for _ in range(m)]
        dist = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    total += 1
                    q.append((i, j))
                    d = 0
                    vis = set()
                    while q:
                        d += 1
                        for _ in range(len(q)):
                            r, c = q.popleft()
                            for a, b in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                                x, y = r + a, c + b
                                if (
                                    0 <= x < m
                                    and 0 <= y < n
                                    and grid[x][y] == 0
                                    and (x, y) not in vis
                                ):
                                    cnt[x][y] += 1
                                    dist[x][y] += d
                                    q.append((x, y))
                                    vis.add((x, y))
        ans = inf
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 0 and cnt[i][j] == total:
                    ans = min(ans, dist[i][j])
        return -1 if ans == inf else ans
```

```java
class Solution {
    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Deque<int[]> q = new LinkedList<>();
        int total = 0;
        int[][] cnt = new int[m][n];
        int[][] dist = new int[m][n];
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    ++total;
                    q.offer(new int[] {i, j});
                    int d = 0;
                    boolean[][] vis = new boolean[m][n];
                    while (!q.isEmpty()) {
                        ++d;
                        for (int k = q.size(); k > 0; --k) {
                            int[] p = q.poll();
                            for (int l = 0; l < 4; ++l) {
                                int x = p[0] + dirs[l];
                                int y = p[1] + dirs[l + 1];
                                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0
                                    && !vis[x][y]) {
                                    ++cnt[x][y];
                                    dist[x][y] += d;
                                    q.offer(new int[] {x, y});
                                    vis[x][y] = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0 && cnt[i][j] == total) {
                    ans = Math.min(ans, dist[i][j]);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
```

```cpp
class Solution {
public:
    int shortestDistance(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        typedef pair<int, int> pii;
        queue<pii> q;
        int total = 0;
        vector<vector<int>> cnt(m, vector<int>(n));
        vector<vector<int>> dist(m, vector<int>(n));
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    ++total;
                    q.push({i, j});
                    vector<vector<bool>> vis(m, vector<bool>(n));
                    int d = 0;
                    while (!q.empty()) {
                        ++d;
                        for (int k = q.size(); k > 0; --k) {
                            auto p = q.front();
                            q.pop();
                            for (int l = 0; l < 4; ++l) {
                                int x = p.first + dirs[l];
                                int y = p.second + dirs[l + 1];
                                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0 && !vis[x][y]) {
                                    ++cnt[x][y];
                                    dist[x][y] += d;
                                    q.push({x, y});
                                    vis[x][y] = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        int ans = INT_MAX;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 0 && cnt[i][j] == total)
                    ans = min(ans, dist[i][j]);
        return ans == INT_MAX ? -1 : ans;
    }
};
```

```go
func shortestDistance(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	var q [][]int
	total := 0
	cnt := make([][]int, m)
	dist := make([][]int, m)
	for i := range cnt {
		cnt[i] = make([]int, n)
		dist[i] = make([]int, n)
	}
	dirs := []int{-1, 0, 1, 0, -1}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				total++
				q = append(q, []int{i, j})
				vis := make([]bool, m*n)
				d := 0
				for len(q) > 0 {
					d++
					for k := len(q); k > 0; k-- {
						p := q[0]
						q = q[1:]
						for l := 0; l < 4; l++ {
							x, y := p[0]+dirs[l], p[1]+dirs[l+1]
							if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0 && !vis[x*n+y] {
								cnt[x][y]++
								dist[x][y] += d
								q = append(q, []int{x, y})
								vis[x*n+y] = true
							}
						}
					}
				}
			}
		}
	}

	ans := math.MaxInt32
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 && cnt[i][j] == total {
				if ans > dist[i][j] {
					ans = dist[i][j]
				}
			}
		}
	}
	if ans == math.MaxInt32 {
		return -1
	}
	return ans
}
```

<!-- tabs:end -->

<!-- end -->
