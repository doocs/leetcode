# [2258. Escape the Spreading Fire](https://leetcode.com/problems/escape-the-spreading-fire)

[中文文档](/solution/2200-2299/2258.Escape%20the%20Spreading%20Fire/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> 2D integer array <code>grid</code> of size <code>m x n</code> which represents a field. Each cell has one of three values:</p>

<ul>
	<li><code>0</code> represents grass,</li>
	<li><code>1</code> represents fire,</li>
	<li><code>2</code> represents a wall that you and fire cannot pass through.</li>
</ul>

<p>You are situated in the top-left cell, <code>(0, 0)</code>, and you want to travel to the safehouse at the bottom-right cell, <code>(m - 1, n - 1)</code>. Every minute, you may move to an <strong>adjacent</strong> grass cell. <strong>After</strong> your move, every fire cell will spread to all <strong>adjacent</strong> cells that are not walls.</p>

<p>Return <em>the <strong>maximum</strong> number of minutes that you can stay in your initial position before moving while still safely reaching the safehouse</em>. If this is impossible, return <code>-1</code>. If you can <strong>always</strong> reach the safehouse regardless of the minutes stayed, return <code>10<sup>9</sup></code>.</p>

<p>Note that even if the fire spreads to the safehouse immediately after you have reached it, it will be counted as safely reaching the safehouse.</p>

<p>A cell is <strong>adjacent</strong> to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2258.Escape%20the%20Spreading%20Fire/images/ex1new.jpg" style="width: 650px; height: 404px;" />
<pre>
<strong>Input:</strong> grid = [[0,2,0,0,0,0,0],[0,0,0,2,2,1,0],[0,2,0,0,1,2,0],[0,0,2,2,2,0,2],[0,0,0,0,0,0,0]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The figure above shows the scenario where you stay in the initial position for 3 minutes.
You will still be able to safely reach the safehouse.
Staying for more than 3 minutes will not allow you to safely reach the safehouse.</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2258.Escape%20the%20Spreading%20Fire/images/ex2new2.jpg" style="width: 515px; height: 150px;" />
<pre>
<strong>Input:</strong> grid = [[0,0,0,0],[0,1,2,0],[0,2,0,0]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> The figure above shows the scenario where you immediately move towards the safehouse.
Fire will spread to any cell you move towards and it is impossible to safely reach the safehouse.
Thus, -1 is returned.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2258.Escape%20the%20Spreading%20Fire/images/ex3new.jpg" style="width: 174px; height: 150px;" />
<pre>
<strong>Input:</strong> grid = [[0,0,0],[2,2,0],[1,2,0]]
<strong>Output:</strong> 1000000000
<strong>Explanation:</strong> The figure above shows the initial grid.
Notice that the fire is contained by walls and you will always be able to safely reach the safehouse.
Thus, 10<sup>9</sup> is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 300</code></li>
	<li><code>4 &lt;= m * n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>grid[i][j]</code> is either <code>0</code>, <code>1</code>, or <code>2</code>.</li>
	<li><code>grid[0][0] == grid[m - 1][n - 1] == 0</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
