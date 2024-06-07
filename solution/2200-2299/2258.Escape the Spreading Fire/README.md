---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2258.Escape%20the%20Spreading%20Fire/README.md
rating: 2346
source: 第 77 场双周赛 Q4
tags:
    - 广度优先搜索
    - 数组
    - 二分查找
    - 矩阵
---

<!-- problem:start -->

# [2258. 逃离火灾](https://leetcode.cn/problems/escape-the-spreading-fire)

[English Version](/solution/2200-2299/2258.Escape%20the%20Spreading%20Fire/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找 + BFS

我们注意到，如果一个停留时间 $t$ 满足条件，那么所有小于 $t$ 的时间也都满足条件。因此我们可以考虑使用二分查找的方法找到最大的满足条件的时间。

我们定义二分查找的左边界 $l=-1$，右边界 $r = m \times n$。每一次二分查找，我们都将 $l$ 和 $r$ 的中点 $mid$ 作为当前的停留时间，判断是否满足条件。如果满足条件，那么我们将 $l$ 更新为 $mid$，否则我们将 $r$ 更新为 $mid-1$。最后，如果 $l = m \times n$，那么说明不存在满足条件的停留时间，我们返回 $10^9$，否则我们返回 $l$。

问题的关键转化为如何判断一个停留时间 $t$ 是否满足条件。我们可以使用广度优先搜索的方法，在 $t$ 时间内，模拟火的蔓延过程。如果停留 $t$ 时间后，火蔓延到了起点位置，那么说明不满足条件，提前返回。否则，我们这时候再使用广度优先搜索，每一次从当前位置向四个方向进行搜索，每一轮结束后，我们还需要将火向四个方向蔓延一次。如果在这个过程中，我们找到了一条从起点到终点的路径，那么说明满足条件。

时间复杂度 $O(m \times n \times \log (m \times n))$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为网格的行数和列数。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

<!-- solution:end -->

<!-- problem:end -->
