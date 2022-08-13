# [2258. 逃离火灾](https://leetcode.cn/problems/escape-the-spreading-fire)

[English Version](/solution/2200-2299/2258.Escape%20the%20Spreading%20Fire/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始大小为 <code>m x n</code>&nbsp;的二维整数数组&nbsp;<code>grid</code>&nbsp;，它表示一个网格图。每个格子为下面 3 个值之一：</p>

<ul>
	<li><code>0</code> 表示草地。</li>
	<li><code>1</code> 表示着火的格子。</li>
	<li><code>2</code>&nbsp;表示一座墙，你跟火都不能通过这个格子。</li>
</ul>

<p>一开始你在最左上角的格子&nbsp;<code>(0, 0)</code>&nbsp;，你想要到达最右下角的安全屋格子&nbsp;<code>(m - 1, n - 1)</code>&nbsp;。每一分钟，你可以移动到&nbsp;<strong>相邻</strong>&nbsp;的草地格子。每次你移动 <strong>之后</strong>&nbsp;，着火的格子会扩散到所有不是墙的 <strong>相邻</strong>&nbsp;格子。</p>

<p>请你返回你在初始位置可以停留的 <strong>最多 </strong>分钟数，且停留完这段时间后你还能安全到达安全屋。如果无法实现，请你返回 <code>-1</code>&nbsp;。如果不管你在初始位置停留多久，你 <strong>总是</strong>&nbsp;能到达安全屋，请你返回&nbsp;<code>10<sup>9</sup></code>&nbsp;。</p>

<p>注意，如果你到达安全屋后，火马上到了安全屋，这视为你能够安全到达安全屋。</p>

<p>如果两个格子有共同边，那么它们为 <strong>相邻</strong>&nbsp;格子。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2258.Escape%20the%20Spreading%20Fire/images/ex1new.jpg" style="width: 650px; height: 404px;"></p>

<pre><b>输入：</b>grid = [[0,2,0,0,0,0,0],[0,0,0,2,2,1,0],[0,2,0,0,1,2,0],[0,0,2,2,2,0,2],[0,0,0,0,0,0,0]]
<b>输出：</b>3
<b>解释：</b>上图展示了你在初始位置停留 3 分钟后的情形。
你仍然可以安全到达安全屋。
停留超过 3 分钟会让你无法安全到达安全屋。</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2258.Escape%20the%20Spreading%20Fire/images/ex2new2.jpg" style="width: 515px; height: 150px;"></p>

<pre><b>输入：</b>grid = [[0,0,0,0],[0,1,2,0],[0,2,0,0]]
<b>输出：</b>-1
<b>解释：</b>上图展示了你马上开始朝安全屋移动的情形。
火会蔓延到你可以移动的所有格子，所以无法安全到达安全屋。
所以返回 -1 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2258.Escape%20the%20Spreading%20Fire/images/ex3new.jpg" style="width: 174px; height: 150px;"></p>

<pre><b>输入：</b>grid = [[0,0,0],[2,2,0],[1,2,0]]
<b>输出：</b>1000000000
<b>解释：</b>上图展示了初始网格图。
注意，由于火被墙围了起来，所以无论如何你都能安全到达安全屋。
所以返回 10<sup>9</sup> 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 300</code></li>
	<li><code>4 &lt;= m * n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>grid[i][j]</code>&nbsp;是&nbsp;<code>0</code>&nbsp;，<code>1</code>&nbsp;或者&nbsp;<code>2</code>&nbsp;。</li>
	<li><code>grid[0][0] == grid[m - 1][n - 1] == 0</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找 + BFS**

二分枚举停留时间 t，找到满足条件的最大 t。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumMinutes(self, grid: List[List[int]]) -> int:
        def spread(fire, q):
            nf = deque()
            while q:
                i, j = q.popleft()
                for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and not fire[x][y] and grid[x][y] == 0:
                        fire[x][y] = True
                        nf.append((x, y))
            return nf

        def check(t):
            fire = [[False] * n for _ in range(m)]
            f = deque()
            for i, row in enumerate(grid):
                for j, v in enumerate(row):
                    if v == 1:
                        fire[i][j] = True
                        f.append((i, j))
            while t and f:
                f = spread(fire, f)
                t -= 1
            if fire[0][0]:
                return False
            q = deque([(0, 0)])
            vis = [[False] * n for _ in range(m)]
            vis[0][0] = True
            while q:
                for _ in range(len(q)):
                    i, j = q.popleft()
                    if fire[i][j]:
                        continue
                    for a, b in [[0, -1], [0, 1], [-1, 0], [1, 0]]:
                        x, y = i + a, j + b
                        if (
                            0 <= x < m
                            and 0 <= y < n
                            and not fire[x][y]
                            and not vis[x][y]
                            and grid[x][y] == 0
                        ):
                            if x == m - 1 and y == n - 1:
                                return True
                            vis[x][y] = True
                            q.append((x, y))
                f = spread(fire, f)
            return False

        m, n = len(grid), len(grid[0])
        left, right = -1, m * n
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return int(1e9) if left == m * n else left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static int[] dirs = {-1, 0, 1, 0, -1};
    private int[][] grid;
    private int m;
    private int n;

    public int maximumMinutes(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int left = -1, right = m * n;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left == m * n ? (int) 1e9 : left;
    }

    private boolean check(int t) {
        boolean[][] fire = new boolean[m][n];
        Deque<int[]> f = new ArrayDeque<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    fire[i][j] = true;
                    f.offer(new int[]{i, j});
                }
            }
        }
        while (t-- > 0 && !f.isEmpty()) {
            f = spread(fire, f);
        }
        if (fire[0][0]) {
            return false;
        }
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] vis = new boolean[m][n];
        q.offer(new int[]{0, 0});
        vis[0][0] = true;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; --i) {
                int[] p = q.poll();
                if (fire[p[0]][p[1]]) {
                    continue;
                }
                for (int k = 0; k < 4; ++k) {
                    int x = p[0] + dirs[k], y = p[1] + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && !vis[x][y] && grid[x][y] == 0) {
                        if (x == m - 1 && y == n - 1) {
                            return true;
                        }
                        vis[x][y] = true;
                        q.offer(new int[]{x, y});
                    }
                }
            }
            f = spread(fire, f);
        }
        return false;
    }

    private Deque<int[]> spread(boolean[][] fire, Deque<int[]> q) {
        Deque<int[]> nf = new ArrayDeque<>();
        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int k = 0; k < 4; ++k) {
                int x = p[0] + dirs[k], y = p[1] + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && grid[x][y] == 0) {
                    fire[x][y] = true;
                    nf.offer(new int[]{x, y});
                }
            }
        }
        return nf;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> dirs = {-1, 0, 1, 0, -1};

    int maximumMinutes(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int left = -1, right = m * n;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid, grid))
                left = mid;
            else
                right = mid - 1;
        }
        return left == m * n ? 1e9 : left;
    }

    bool check(int t, vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<bool>> fire(m, vector<bool>(n));
        queue<vector<int>> f;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    fire[i][j] = true;
                    f.push({i, j});
                }
            }
        }
        while (t-- && f.size()) f = spread(fire, f, grid);
        queue<vector<int>> q;
        vector<vector<bool>> vis(m, vector<bool>(n));
        q.push({0, 0});
        vis[0][0] = true;
        while (!q.empty()) {
            for (int i = q.size(); i > 0; --i) {
                auto p = q.front();
                q.pop();
                if (fire[p[0]][p[1]]) continue;
                for (int k = 0; k < 4; ++k) {
                    int x = p[0] + dirs[k], y = p[1] + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && !vis[x][y] && grid[x][y] == 0) {
                        if (x == m - 1 && y == n - 1) return true;
                        vis[x][y] = true;
                        q.push({x, y});
                    }
                }
            }
            f = spread(fire, f, grid);
        }
        return false;
    }

    queue<vector<int>> spread(vector<vector<bool>>& fire, queue<vector<int>>& f, vector<vector<int>>& grid) {
        queue<vector<int>> nf;
        int m = grid.size(), n = grid[0].size();
        while (!f.empty()) {
            auto p = f.front();
            f.pop();
            for (int k = 0; k < 4; ++k) {
                int x = p[0] + dirs[k], y = p[1] + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && grid[x][y] == 0) {
                    fire[x][y] = true;
                    nf.push({x, y});
                }
            }
        }
        return nf;
    }
};
```

### **Go**

```go
func maximumMinutes(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	dirs := []int{-1, 0, 1, 0, -1}

	spread := func(fire [][]bool, q [][]int) [][]int {
		nf := [][]int{}
		for len(q) > 0 {
			p := q[0]
			q = q[1:]
			for k := 0; k < 4; k++ {
				x, y := p[0]+dirs[k], p[1]+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && grid[x][y] == 0 {
					fire[x][y] = true
					nf = append(nf, []int{x, y})
				}
			}
		}
		return nf
	}

	check := func(t int) bool {
		fire := make([][]bool, m)
		vis := make([][]bool, m)
		f := [][]int{}
		for i, row := range grid {
			fire[i] = make([]bool, n)
			vis[i] = make([]bool, n)
			for j, v := range row {
				if v == 1 {
					fire[i][j] = true
					f = append(f, []int{i, j})
				}
			}
		}
		for t > 0 && len(f) > 0 {
			f = spread(fire, f)
			t--
		}
		if fire[0][0] {
			return false
		}
		q := [][]int{{0, 0}}
		vis[0][0] = true
		for len(q) > 0 {
			for i := len(q); i > 0; i-- {
				p := q[0]
				q = q[1:]
				if fire[p[0]][p[1]] {
					continue
				}
				for k := 0; k < 4; k++ {
					x, y := p[0]+dirs[k], p[1]+dirs[k+1]
					if x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && !vis[x][y] && grid[x][y] == 0 {
						if x == m-1 && y == n-1 {
							return true
						}
						vis[x][y] = true
						q = append(q, []int{x, y})
					}
				}
			}
			f = spread(fire, f)
		}
		return false
	}

	left, right := -1, m*n
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	if left == m*n {
		return int(1e9)
	}
	return left
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
