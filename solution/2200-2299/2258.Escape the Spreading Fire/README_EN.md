---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2258.Escape%20the%20Spreading%20Fire/README_EN.md
rating: 2346
source: Biweekly Contest 77 Q4
tags:
    - Breadth-First Search
    - Array
    - Binary Search
    - Matrix
---

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
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2258.Escape%20the%20Spreading%20Fire/images/ex1new.jpg" style="width: 650px; height: 404px;" />
<pre>
<strong>Input:</strong> grid = [[0,2,0,0,0,0,0],[0,0,0,2,2,1,0],[0,2,0,0,1,2,0],[0,0,2,2,2,0,2],[0,0,0,0,0,0,0]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The figure above shows the scenario where you stay in the initial position for 3 minutes.
You will still be able to safely reach the safehouse.
Staying for more than 3 minutes will not allow you to safely reach the safehouse.</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2258.Escape%20the%20Spreading%20Fire/images/ex2new2.jpg" style="width: 515px; height: 150px;" />
<pre>
<strong>Input:</strong> grid = [[0,0,0,0],[0,1,2,0],[0,2,0,0]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> The figure above shows the scenario where you immediately move towards the safehouse.
Fire will spread to any cell you move towards and it is impossible to safely reach the safehouse.
Thus, -1 is returned.
</pre>

<p><strong class="example">Example 3:</strong></p>
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

### Solution 1: Binary Search + BFS

We notice that if a stay time $t$ satisfies the condition, then all stay times less than $t$ also satisfy the condition. Therefore, we can consider using binary search to find the maximum stay time that satisfies the condition.

We define the left boundary of binary search as $l=-1$ and the right boundary as $r=m \times n$. In each iteration of binary search, we take the midpoint $mid$ of $l$ and $r$ as the current stay time and check if it satisfies the condition. If it does, we update $l$ to $mid$, otherwise we update $r$ to $mid-1$. Finally, if $l=m \times n$, it means there is no stay time that satisfies the condition, so we return $10^9$, otherwise we return $l$.

The key problem is how to determine whether a stay time $t$ satisfies the condition. We can use breadth-first search to simulate the spread of fire within $t$ time. If the fire spreads to the starting position after staying for $t$ time, it means the condition is not satisfied and we return early. Otherwise, we use breadth-first search again, searching in four directions from the current position each time, and after each round, we need to spread the fire in four directions. If we find a path from the starting position to the ending position during this process, it means the condition is satisfied.

The time complexity is $O(m \times n \times \log (m \times n))$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the number of rows and columns in the grid, respectively.

<!-- tabs:start -->

```python
class Solution:
    def maximumMinutes(self, grid: List[List[int]]) -> int:
        def spread(q: Deque[int]) -> Deque[int]:
            nq = deque()
            while q:
                i, j = q.popleft()
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and not fire[x][y] and grid[x][y] == 0:
                        fire[x][y] = True
                        nq.append((x, y))
            return nq

        def check(t: int) -> bool:
            for i in range(m):
                for j in range(n):
                    fire[i][j] = False
            q1 = deque()
            for i, row in enumerate(grid):
                for j, x in enumerate(row):
                    if x == 1:
                        fire[i][j] = True
                        q1.append((i, j))
            while t and q1:
                q1 = spread(q1)
                t -= 1
            if fire[0][0]:
                return False
            q2 = deque([(0, 0)])
            vis = [[False] * n for _ in range(m)]
            vis[0][0] = True
            while q2:
                for _ in range(len(q2)):
                    i, j = q2.popleft()
                    if fire[i][j]:
                        continue
                    for a, b in pairwise(dirs):
                        x, y = i + a, j + b
                        if (
                            0 <= x < m
                            and 0 <= y < n
                            and not vis[x][y]
                            and not fire[x][y]
                            and grid[x][y] == 0
                        ):
                            if x == m - 1 and y == n - 1:
                                return True
                            vis[x][y] = True
                            q2.append((x, y))
                q1 = spread(q1)
            return False

        m, n = len(grid), len(grid[0])
        l, r = -1, m * n
        dirs = (-1, 0, 1, 0, -1)
        fire = [[False] * n for _ in range(m)]
        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return int(1e9) if l == m * n else l
```

```java
class Solution {
    private int[][] grid;
    private boolean[][] fire;
    private boolean[][] vis;
    private final int[] dirs = {-1, 0, 1, 0, -1};
    private int m;
    private int n;

    public int maximumMinutes(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        fire = new boolean[m][n];
        vis = new boolean[m][n];
        int l = -1, r = m * n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l == m * n ? 1000000000 : l;
    }

    private boolean check(int t) {
        for (int i = 0; i < m; ++i) {
            Arrays.fill(fire[i], false);
            Arrays.fill(vis[i], false);
        }
        Deque<int[]> q1 = new ArrayDeque<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    q1.offer(new int[] {i, j});
                    fire[i][j] = true;
                }
            }
        }
        for (; t > 0 && !q1.isEmpty(); --t) {
            q1 = spread(q1);
        }
        if (fire[0][0]) {
            return false;
        }
        Deque<int[]> q2 = new ArrayDeque<>();
        q2.offer(new int[] {0, 0});
        vis[0][0] = true;
        for (; !q2.isEmpty(); q1 = spread(q1)) {
            for (int d = q2.size(); d > 0; --d) {
                int[] p = q2.poll();
                if (fire[p[0]][p[1]]) {
                    continue;
                }
                for (int k = 0; k < 4; ++k) {
                    int x = p[0] + dirs[k], y = p[1] + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && !vis[x][y]
                        && grid[x][y] == 0) {
                        if (x == m - 1 && y == n - 1) {
                            return true;
                        }
                        vis[x][y] = true;
                        q2.offer(new int[] {x, y});
                    }
                }
            }
        }
        return false;
    }

    private Deque<int[]> spread(Deque<int[]> q) {
        Deque<int[]> nq = new ArrayDeque<>();
        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int k = 0; k < 4; ++k) {
                int x = p[0] + dirs[k], y = p[1] + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && grid[x][y] == 0) {
                    fire[x][y] = true;
                    nq.offer(new int[] {x, y});
                }
            }
        }
        return nq;
    }
}
```

```cpp
class Solution {
public:
    int maximumMinutes(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        bool vis[m][n];
        bool fire[m][n];
        int dirs[5] = {-1, 0, 1, 0, -1};
        auto spread = [&](queue<pair<int, int>>& q) {
            queue<pair<int, int>> nq;
            while (q.size()) {
                auto [i, j] = q.front();
                q.pop();
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && grid[x][y] == 0) {
                        fire[x][y] = true;
                        nq.emplace(x, y);
                    }
                }
            }
            return nq;
        };
        auto check = [&](int t) {
            memset(vis, false, sizeof(vis));
            memset(fire, false, sizeof(fire));
            queue<pair<int, int>> q1;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == 1) {
                        q1.emplace(i, j);
                        fire[i][j] = true;
                    }
                }
            }
            for (; t && q1.size(); --t) {
                q1 = spread(q1);
            }
            if (fire[0][0]) {
                return false;
            }
            queue<pair<int, int>> q2;
            q2.emplace(0, 0);
            vis[0][0] = true;
            for (; q2.size(); q1 = spread(q1)) {
                for (int d = q2.size(); d; --d) {
                    auto [i, j] = q2.front();
                    q2.pop();
                    if (fire[i][j]) {
                        continue;
                    }
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && !fire[x][y] && grid[x][y] == 0) {
                            if (x == m - 1 && y == n - 1) {
                                return true;
                            }
                            vis[x][y] = true;
                            q2.emplace(x, y);
                        }
                    }
                }
            }
            return false;
        };
        int l = -1, r = m * n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l == m * n ? 1e9 : l;
    }
};
```

```go
func maximumMinutes(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	fire := make([][]bool, m)
	vis := make([][]bool, m)
	dirs := [5]int{-1, 0, 1, 0, -1}
	for i := range fire {
		fire[i] = make([]bool, n)
		vis[i] = make([]bool, n)
	}
	l, r := -1, m*n
	spread := func(q [][2]int) [][2]int {
		nq := [][2]int{}
		for len(q) > 0 {
			p := q[0]
			q = q[1:]
			for k := 0; k < 4; k++ {
				x, y := p[0]+dirs[k], p[1]+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && grid[x][y] == 0 {
					fire[x][y] = true
					nq = append(nq, [2]int{x, y})
				}
			}
		}
		return nq
	}
	check := func(t int) bool {
		for i := range fire {
			for j := range fire[i] {
				fire[i][j] = false
				vis[i][j] = false
			}
		}
		q1 := [][2]int{}
		for i := 0; i < m; i++ {
			for j := 0; j < n; j++ {
				if grid[i][j] == 1 {
					q1 = append(q1, [2]int{i, j})
					fire[i][j] = true
				}
			}
		}
		for ; t > 0 && len(q1) > 0; t-- {
			q1 = spread(q1)
		}
		q2 := [][2]int{{0, 0}}
		vis[0][0] = true
		for ; len(q2) > 0; q1 = spread(q1) {
			for d := len(q2); d > 0; d-- {
				p := q2[0]
				q2 = q2[1:]
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
						q2 = append(q2, [2]int{x, y})
					}
				}
			}
		}
		return false
	}
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	if l == m*n {
		return int(1e9)
	}
	return l
}
```

```ts
function maximumMinutes(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const fire = Array.from({ length: m }, () => Array.from({ length: n }, () => false));
    const vis = Array.from({ length: m }, () => Array.from({ length: n }, () => false));
    const dirs: number[] = [-1, 0, 1, 0, -1];
    let [l, r] = [-1, m * n];
    const spread = (q: number[][]): number[][] => {
        const nq: number[][] = [];
        while (q.length) {
            const [i, j] = q.shift()!;
            for (let k = 0; k < 4; ++k) {
                const [x, y] = [i + dirs[k], j + dirs[k + 1]];
                if (x >= 0 && x < m && y >= 0 && y < n && !fire[x][y] && grid[x][y] === 0) {
                    fire[x][y] = true;
                    nq.push([x, y]);
                }
            }
        }
        return nq;
    };
    const check = (t: number): boolean => {
        for (let i = 0; i < m; ++i) {
            fire[i].fill(false);
            vis[i].fill(false);
        }
        let q1: number[][] = [];
        for (let i = 0; i < m; ++i) {
            for (let j = 0; j < n; ++j) {
                if (grid[i][j] === 1) {
                    q1.push([i, j]);
                    fire[i][j] = true;
                }
            }
        }
        for (; t && q1.length; --t) {
            q1 = spread(q1);
        }
        if (fire[0][0]) {
            return false;
        }
        const q2: number[][] = [[0, 0]];
        vis[0][0] = true;
        for (; q2.length; q1 = spread(q1)) {
            for (let d = q2.length; d; --d) {
                const [i, j] = q2.shift()!;
                if (fire[i][j]) {
                    continue;
                }
                for (let k = 0; k < 4; ++k) {
                    const [x, y] = [i + dirs[k], j + dirs[k + 1]];
                    if (
                        x >= 0 &&
                        x < m &&
                        y >= 0 &&
                        y < n &&
                        !vis[x][y] &&
                        !fire[x][y] &&
                        grid[x][y] === 0
                    ) {
                        if (x === m - 1 && y === n - 1) {
                            return true;
                        }
                        vis[x][y] = true;
                        q2.push([x, y]);
                    }
                }
            }
        }
        return false;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l === m * n ? 1e9 : l;
}
```

<!-- tabs:end -->

<!-- end -->
