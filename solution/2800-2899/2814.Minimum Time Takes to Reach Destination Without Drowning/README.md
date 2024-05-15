---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2814.Minimum%20Time%20Takes%20to%20Reach%20Destination%20Without%20Drowning/README.md
tags:
    - 广度优先搜索
    - 数组
    - 矩阵
---

# [2814. 避免淹死并到达目的地的最短时间 🔒](https://leetcode.cn/problems/minimum-time-takes-to-reach-destination-without-drowning)

[English Version](/solution/2800-2899/2814.Minimum%20Time%20Takes%20to%20Reach%20Destination%20Without%20Drowning/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现给定一个 <code>n * m</code> 的索引从 <strong>0</strong> 开始的二维字符串网格 <code>land</code>，目前你站在为&nbsp;<code>"S"</code> 的单元格上，你需要到达为&nbsp;<code>"D"</code> 的单元格。在这片区域上还有另外三种类型的单元格：</p>

<ul>
	<li><code>"."</code>：这些单元格是空的。</li>
	<li><code>"X"</code>：这些单元格是石头。</li>
	<li><code>"*"</code>：这些单元格被淹没了。</li>
</ul>

<p>每秒钟，你可以移动到与当前单元格共享边的单元格（如果它存在）。此外，每秒钟，与被淹没的单元格共享边的每个 <strong>空单元格</strong> 也会被淹没。</p>

<p>在你的旅程中，有两个需要注意的问题：</p>

<ul>
	<li>你不能踩在石头单元格上。</li>
	<li>你不能踩在被淹没的单元格上，因为你会淹死（同时，你也不能踩在在你踩上时会被淹没的单元格上）。</li>
</ul>

<p>返回从起始位置到达目标位置所需的 <strong>最小</strong> 时间（以秒为单位），如果不可能达到目标位置，则返回 <code>-1</code>。</p>

<p><strong>注意</strong>，目标位置永远不会被淹没。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>land = [["D",".","*"],[".",".","."],[".","S","."]]
<b>输出：</b>3
<strong>解释：</strong>下面的图片逐秒模拟了土地的变化。蓝色的单元格被淹没，灰色的单元格是石头。
 图片（0）显示了初始状态，图片（3）显示了当我们到达目标时的最终状态。正如你所看到的，我们需要 3 秒才能到达目标位置，答案是 3。
可以证明 3 是从 S 到 D 所需的最小时间。
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2814.Minimum%20Time%20Takes%20to%20Reach%20Destination%20Without%20Drowning/images/ex1.png" style="padding: 5px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 600px; height: 111px;" /></p>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>land = [["D","X","*"],[".",".","."],[".",".","S"]]
<b>输出：</b>-1
<b>解释：</b>下面的图片逐秒模拟了土地的变化。蓝色的单元格被淹没，灰色的单元格是石头。
图片（0）显示了初始状态。正如你所看到的，无论我们选择哪条路径，我们都会在第三秒淹没。并且从 S 到 D 的最小路径需要 4 秒。
所以答案是 -1。
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2814.Minimum%20Time%20Takes%20to%20Reach%20Destination%20Without%20Drowning/images/ex2-2.png" style="padding: 7px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 600px; height: 107px;" /></p>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>land = [["D",".",".",".","*","."],[".","X",".","X",".","."],[".",".",".",".","S","."]]
<b>输出：</b>6
<b>解释：</b>可以证明我们可以在 6 秒内到达目标位置。同时也可以证明 6 是从 S 到 D 所需的最小秒数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n, m &lt;= 100</code></li>
	<li><code>land</code>&nbsp;只由&nbsp;<code>"S"</code>, <code>"D"</code>, <code>"."</code>, <code>"*"</code> 和&nbsp;<code>"X"</code>&nbsp;组成。</li>
	<li><strong>恰好</strong>有一个单元格等于&nbsp;<code>"S"</code>。</li>
	<li><strong>恰好</strong>有一个单元格等于 <code>"D"</code>。</li>
</ul>

## 解法

### 方法一：两次 BFS

我们先跑一次 BFS，求出每个点到水域的最短距离，记录在数组 $g$ 中。然后再跑一次 BFS，从单元格 $(s_i, s_j)$ 出发，求出到达目标单元格 $(d_i, d_j)$ 的最短距离。在此过程中，如果当前单元格 $(i, j)$ 的相邻单元格 $(x, y)$ 满足 $g[x][y] \gt t + 1$，那么我们就可以从 $(x, y)$ 走到 $(i, j)$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是数组 $land$ 的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def minimumSeconds(self, land: List[List[str]]) -> int:
        m, n = len(land), len(land[0])
        vis = [[False] * n for _ in range(m)]
        g = [[inf] * n for _ in range(m)]
        q = deque()
        si = sj = 0
        for i, row in enumerate(land):
            for j, c in enumerate(row):
                match c:
                    case "*":
                        q.append((i, j))
                    case "S":
                        si, sj = i, j
        dirs = (-1, 0, 1, 0, -1)
        t = 0
        while q:
            for _ in range(len(q)):
                i, j = q.popleft()
                g[i][j] = t
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if (
                        0 <= x < m
                        and 0 <= y < n
                        and not vis[x][y]
                        and land[x][y] in ".S"
                    ):
                        vis[x][y] = True
                        q.append((x, y))
            t += 1
        t = 0
        q = deque([(si, sj)])
        vis = [[False] * n for _ in range(m)]
        vis[si][sj] = True
        while q:
            for _ in range(len(q)):
                i, j = q.popleft()
                if land[i][j] == "D":
                    return t
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    if (
                        0 <= x < m
                        and 0 <= y < n
                        and g[x][y] > t + 1
                        and not vis[x][y]
                        and land[x][y] in ".D"
                    ):
                        vis[x][y] = True
                        q.append((x, y))
            t += 1
        return -1
```

```java
class Solution {
    public int minimumSeconds(List<List<String>> land) {
        int m = land.size(), n = land.get(0).size();
        boolean[][] vis = new boolean[m][n];
        int[][] g = new int[m][n];
        Deque<int[]> q = new ArrayDeque<>();
        int si = 0, sj = 0;
        for (int i = 0; i < m; ++i) {
            Arrays.fill(g[i], 1 << 30);
            for (int j = 0; j < n; ++j) {
                String c = land.get(i).get(j);
                if ("*".equals(c)) {
                    q.offer(new int[] {i, j});
                } else if ("S".equals(c)) {
                    si = i;
                    sj = j;
                }
            }
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int t = 0; !q.isEmpty(); ++t) {
            for (int k = q.size(); k > 0; --k) {
                int[] p = q.poll();
                int i = p[0], j = p[1];
                g[i][j] = t;
                for (int d = 0; d < 4; ++d) {
                    int x = i + dirs[d], y = j + dirs[d + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y]) {
                        boolean empty = ".".equals(land.get(x).get(y));
                        boolean start = "S".equals(land.get(x).get(y));
                        if (empty || start) {
                            vis[x][y] = true;
                            q.offer(new int[] {x, y});
                        }
                    }
                }
            }
        }
        q.offer(new int[] {si, sj});
        vis = new boolean[m][n];
        vis[si][sj] = true;
        for (int t = 0; !q.isEmpty(); ++t) {
            for (int k = q.size(); k > 0; --k) {
                int[] p = q.poll();
                int i = p[0], j = p[1];
                if ("D".equals(land.get(i).get(j))) {
                    return t;
                }
                for (int d = 0; d < 4; ++d) {
                    int x = i + dirs[d], y = j + dirs[d + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && g[x][y] > t + 1) {
                        boolean empty = ".".equals(land.get(x).get(y));
                        boolean dest = "D".equals(land.get(x).get(y));
                        if (empty || dest) {
                            vis[x][y] = true;
                            q.offer(new int[] {x, y});
                        }
                    }
                }
            }
        }
        return -1;
    }
}
```

```cpp
class Solution {
public:
    int minimumSeconds(vector<vector<string>>& land) {
        int m = land.size(), n = land[0].size();
        bool vis[m][n];
        int g[m][n];
        memset(vis, false, sizeof(vis));
        memset(g, 0x3f, sizeof(g));
        queue<pair<int, int>> q;
        int si = 0, sj = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                auto c = land[i][j];
                if (c == "*") {
                    q.emplace(i, j);
                } else if (c == "S") {
                    si = i;
                    sj = j;
                }
            }
        }
        int dirs[5] = {-1, 0, 1, 0, -1};
        for (int t = 0; !q.empty(); ++t) {
            for (int k = q.size(); k; --k) {
                auto [i, j] = q.front();
                q.pop();
                g[i][j] = t;
                for (int d = 0; d < 4; ++d) {
                    int x = i + dirs[d], y = j + dirs[d + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y]) {
                        bool empty = land[x][y] == ".";
                        bool start = land[x][y] == "S";
                        if (empty || start) {
                            vis[x][y] = true;
                            q.emplace(x, y);
                        }
                    }
                }
            }
        }
        q.emplace(si, sj);
        memset(vis, false, sizeof(vis));
        vis[si][sj] = true;
        for (int t = 0; !q.empty(); ++t) {
            for (int k = q.size(); k; --k) {
                auto [i, j] = q.front();
                q.pop();
                if (land[i][j] == "D") {
                    return t;
                }
                for (int d = 0; d < 4; ++d) {
                    int x = i + dirs[d], y = j + dirs[d + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && g[x][y] > t + 1) {
                        bool empty = land[x][y] == ".";
                        bool dest = land[x][y] == "D";
                        if (empty || dest) {
                            vis[x][y] = true;
                            q.emplace(x, y);
                        }
                    }
                }
            }
        }
        return -1;
    }
};
```

```go
func minimumSeconds(land [][]string) int {
	m, n := len(land), len(land[0])
	vis := make([][]bool, m)
	g := make([][]int, m)
	q := [][2]int{}
	var si, sj int
	for i, row := range land {
		vis[i] = make([]bool, n)
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = 1 << 30
		}
		for j, c := range row {
			if c == "*" {
				q = append(q, [2]int{i, j})
			} else if c == "S" {
				si, sj = i, j
			}
		}
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	for t := 0; len(q) > 0; t++ {
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			i, j := p[0], p[1]
			g[i][j] = t
			for d := 0; d < 4; d++ {
				x, y := i+dirs[d], j+dirs[d+1]
				if x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] {
					empty := land[x][y] == "."
					start := land[x][y] == "S"
					if empty || start {
						vis[x][y] = true
						q = append(q, [2]int{x, y})
					}
				}
			}
		}
	}
	q = append(q, [2]int{si, sj})
	vis = make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	vis[si][sj] = true
	for t := 0; len(q) > 0; t++ {
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			i, j := p[0], p[1]
			if land[i][j] == "D" {
				return t
			}
			for d := 0; d < 4; d++ {
				x, y := i+dirs[d], j+dirs[d+1]
				if x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && g[x][y] > t+1 {
					empty := land[x][y] == "."
					dest := land[x][y] == "D"
					if empty || dest {
						vis[x][y] = true
						q = append(q, [2]int{x, y})
					}
				}
			}
		}
	}
	return -1
}
```

```ts
function minimumSeconds(land: string[][]): number {
    const m = land.length;
    const n = land[0].length;
    const g: number[][] = Array(m)
        .fill(0)
        .map(() => Array(n).fill(1 << 30));
    const vis: boolean[][] = Array(m)
        .fill(0)
        .map(() => Array(n).fill(false));
    const q: number[][] = [];
    let [si, sj] = [0, 0];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            const c = land[i][j];
            if (c === '*') {
                q.push([i, j]);
            } else if (c === 'S') {
                [si, sj] = [i, j];
            }
        }
    }
    const dirs = [-1, 0, 1, 0, -1];
    for (let t = 0; q.length; ++t) {
        for (let k = q.length; k; --k) {
            const [i, j] = q.shift()!;
            g[i][j] = t;
            for (let d = 0; d < 4; ++d) {
                const [x, y] = [i + dirs[d], j + dirs[d + 1]];
                if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] && 'S.'.includes(land[x][y])) {
                    vis[x][y] = true;
                    q.push([x, y]);
                }
            }
        }
    }
    q.push([si, sj]);
    for (let i = 0; i < m; ++i) {
        vis[i].fill(false);
    }
    vis[si][sj] = true;
    for (let t = 0; q.length; ++t) {
        for (let k = q.length; k; --k) {
            const [i, j] = q.shift()!;
            if (land[i][j] === 'D') {
                return t;
            }
            for (let d = 0; d < 4; ++d) {
                const [x, y] = [i + dirs[d], j + dirs[d + 1]];
                if (
                    x >= 0 &&
                    x < m &&
                    y >= 0 &&
                    y < n &&
                    !vis[x][y] &&
                    g[x][y] > t + 1 &&
                    'D.'.includes(land[x][y]) &&
                    t + 1 < g[x][y]
                ) {
                    vis[x][y] = true;
                    q.push([x, y]);
                }
            }
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- end -->
