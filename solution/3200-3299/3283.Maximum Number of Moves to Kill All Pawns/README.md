---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3283.Maximum%20Number%20of%20Moves%20to%20Kill%20All%20Pawns/README.md
rating: 2473
source: 第 414 场周赛 Q4
tags:
    - 位运算
    - 广度优先搜索
    - 数组
    - 数学
    - 状态压缩
    - 博弈
---

<!-- problem:start -->

# [3283. 吃掉所有兵需要的最多移动次数](https://leetcode.cn/problems/maximum-number-of-moves-to-kill-all-pawns)

[English Version](/solution/3200-3299/3283.Maximum%20Number%20of%20Moves%20to%20Kill%20All%20Pawns/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<code>50 x 50</code>&nbsp;的国际象棋棋盘，棋盘上有 <strong>一个</strong>&nbsp;马和一些兵。给你两个整数&nbsp;<code>kx</code> 和&nbsp;<code>ky</code>&nbsp;，其中&nbsp;<code>(kx, ky)</code>&nbsp;表示马所在的位置，同时还有一个二维数组&nbsp;<code>positions</code>&nbsp;，其中&nbsp;<code>positions[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;表示第 <code>i</code>&nbsp;个兵在棋盘上的位置。</p>

<p>Alice 和 Bob 玩一个回合制游戏，Alice 先手。玩家的一次操作中，可以执行以下操作：</p>

<ul>
	<li>玩家选择一个仍然在棋盘上的兵，然后移动马，通过 <strong>最少</strong>&nbsp;的 <strong>步数</strong> 吃掉这个兵。<strong>注意</strong>&nbsp;，玩家可以选择&nbsp;<strong>任意</strong>&nbsp;一个兵，<strong>不一定</strong>&nbsp;要选择从马的位置出发&nbsp;<strong>最少</strong>&nbsp;移动步数的兵。</li>
	<li><span>在马吃兵的过程中，马 <strong>可能</strong>&nbsp;会经过一些其他兵的位置，但这些兵 <strong>不会</strong>&nbsp;被吃掉。<strong>只有</strong>&nbsp;选中的兵在这个回合中被吃掉。</span></li>
</ul>

<p>Alice 的目标是 <strong>最大化</strong>&nbsp;两名玩家的 <strong>总</strong>&nbsp;移动次数，直到棋盘上不再存在兵，而 Bob 的目标是 <strong>最小化</strong>&nbsp;总移动次数。</p>

<p>假设两名玩家都采用 <strong>最优</strong>&nbsp;策略，请你返回可以达到的 <strong>最大</strong>&nbsp;总移动次数。</p>

<p>在一次&nbsp;<strong>移动</strong>&nbsp;中，如下图所示，马有 8 个可以移动到的位置，每个移动位置都是沿着坐标轴的一个方向前进 2 格，然后沿着垂直的方向前进 1 格。</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3283.Maximum%20Number%20of%20Moves%20to%20Kill%20All%20Pawns/images/chess_knight.jpg" style="width: 275px; height: 273px;" /></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>kx = 1, ky = 1, positions = [[0,0]]</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3283.Maximum%20Number%20of%20Moves%20to%20Kill%20All%20Pawns/images/gif3.gif" style="width: 275px; height: 275px;" /></p>

<p>马需要移动 4 步吃掉&nbsp;<code>(0, 0)</code>&nbsp;处的兵。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>kx = 0, ky = 2, positions = [[1,1],[2,2],[3,3]]</span></p>

<p><span class="example-io"><b>输出：</b>8</span></p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3283.Maximum%20Number%20of%20Moves%20to%20Kill%20All%20Pawns/images/gif4.gif" style="width: 320px; height: 320px;" /></strong></p>

<ul>
	<li>Alice 选择&nbsp;<code>(2, 2)</code>&nbsp;处的兵，移动马吃掉它需要 2 步：<code>(0, 2) -&gt; (1, 4) -&gt; (2, 2)</code>&nbsp;。</li>
	<li>Bob 选择&nbsp;<code>(3, 3)</code>&nbsp;处的兵，移动马吃掉它需要 2 步：<code>(2, 2) -&gt; (4, 1) -&gt; (3, 3)</code>&nbsp;。</li>
	<li>Alice 选择&nbsp;<code>(1, 1)</code>&nbsp;处的兵，移动马吃掉它需要 4 步：<code>(3, 3) -&gt; (4, 1) -&gt; (2, 2) -&gt; (0, 3) -&gt; (1, 1)</code>&nbsp;。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>kx = 0, ky = 0, positions = [[1,2],[2,4]]</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>Alice 选择&nbsp;<code>(2, 4)</code>&nbsp;处的兵，移动马吃掉它需要 2 步：<code>(0, 0) -&gt; (1, 2) -&gt; (2, 4)</code>&nbsp;。注意，<code>(1, 2)</code>&nbsp;处的兵不会被吃掉。</li>
	<li>Bob 选择&nbsp;<code>(1, 2)</code>&nbsp;处的兵，移动马吃掉它需要 1 步：<code>(2, 4) -&gt; (1, 2)</code>&nbsp;。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= kx, ky &lt;= 49</code></li>
	<li><code>1 &lt;= positions.length &lt;= 15</code></li>
	<li><code>positions[i].length == 2</code></li>
	<li><code>0 &lt;= positions[i][0], positions[i][1] &lt;= 49</code></li>
	<li><code>positions[i]</code>&nbsp;两两互不相同。</li>
	<li>输入保证对于所有&nbsp;<code>0 &lt;= i &lt; positions.length</code>&nbsp;，都有&nbsp;<code>positions[i] != [kx, ky]</code>&nbsp;。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS + 状态压缩 + 记忆化搜索

我们首先预处理出每个兵到棋盘上任意一个位置的马的最短距离，记录在数组 $\textit{dist}$ 中，即 $\textit{dist}[i][x][y]$ 表示第 $i$ 个兵到 $(x, y)$ 位置的马的最短距离。

接下来，我们设计一个函数 $\text{dfs}(\textit{last}, \textit{state}, \textit{k})$，其中 $\textit{last}$ 表示上一个吃掉的兵的编号，而 $\textit{state}$ 表示当前还剩下的兵的状态，而 $\textit{k}$ 表示当前是 Alice 还是 Bob 的回合。函数的返回值表示当前回合的最大移动次数。那么答案为 $\text{dfs}(n, 2^n-1, 1)$。这里我们初始时上一个吃掉的兵的编号记为 $n$，这也是马所在的位置。

函数 $\text{dfs}$ 的具体实现如下：

- 如果 $\textit{state} = 0$，表示没有兵了，返回 $0$；
- 如果 $\textit{k} = 1$，表示当前是 Alice 的回合，我们需要找到一个兵，使得吃掉这个兵后的移动次数最大，即 $\text{dfs}(i, \textit{state} \oplus 2^i, \textit{k} \oplus 1) + \textit{dist}[\textit{last}][x][y]$；
- 如果 $\textit{k} = 0$，表示当前是 Bob 的回合，我们需要找到一个兵，使得吃掉这个兵后的移动次数最小，即 $\text{dfs}(i, \textit{state} \oplus 2^i, \textit{k} \oplus 1) + \textit{dist}[\textit{last}][x][y]$。

为了避免重复计算，我们使用记忆化搜索，即使用哈希表记录已经计算过的状态。

时间复杂度 $O(n \times m^2 + 2^n \times n^2)$，空间复杂度 $O(n \times m^2 + 2^n \times n)$。其中 $n$ 和 $m$ 分别为兵的数量和棋盘的大小。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxMoves(self, kx: int, ky: int, positions: List[List[int]]) -> int:
        @cache
        def dfs(last: int, state: int, k: int) -> int:
            if state == 0:
                return 0
            if k:
                res = 0
                for i, (x, y) in enumerate(positions):
                    if state >> i & 1:
                        t = dfs(i, state ^ (1 << i), k ^ 1) + dist[last][x][y]
                        if res < t:
                            res = t
                return res
            else:
                res = inf
                for i, (x, y) in enumerate(positions):
                    if state >> i & 1:
                        t = dfs(i, state ^ (1 << i), k ^ 1) + dist[last][x][y]
                        if res > t:
                            res = t
                return res

        n = len(positions)
        m = 50
        dist = [[[-1] * m for _ in range(m)] for _ in range(n + 1)]
        dx = [1, 1, 2, 2, -1, -1, -2, -2]
        dy = [2, -2, 1, -1, 2, -2, 1, -1]
        positions.append([kx, ky])
        for i, (x, y) in enumerate(positions):
            dist[i][x][y] = 0
            q = deque([(x, y)])
            step = 0
            while q:
                step += 1
                for _ in range(len(q)):
                    x1, y1 = q.popleft()
                    for j in range(8):
                        x2, y2 = x1 + dx[j], y1 + dy[j]
                        if 0 <= x2 < m and 0 <= y2 < m and dist[i][x2][y2] == -1:
                            dist[i][x2][y2] = step
                            q.append((x2, y2))

        ans = dfs(n, (1 << n) - 1, 1)
        dfs.cache_clear()
        return ans
```

#### Java

```java
class Solution {
    private Integer[][][] f;
    private Integer[][][] dist;
    private int[][] positions;
    private final int[] dx = {1, 1, 2, 2, -1, -1, -2, -2};
    private final int[] dy = {2, -2, 1, -1, 2, -2, 1, -1};

    public int maxMoves(int kx, int ky, int[][] positions) {
        int n = positions.length;
        final int m = 50;
        dist = new Integer[n + 1][m][m];
        this.positions = positions;
        for (int i = 0; i <= n; ++i) {
            int x = i < n ? positions[i][0] : kx;
            int y = i < n ? positions[i][1] : ky;
            Deque<int[]> q = new ArrayDeque<>();
            q.offer(new int[] {x, y});
            for (int step = 1; !q.isEmpty(); ++step) {
                for (int k = q.size(); k > 0; --k) {
                    var p = q.poll();
                    int x1 = p[0], y1 = p[1];
                    for (int j = 0; j < 8; ++j) {
                        int x2 = x1 + dx[j], y2 = y1 + dy[j];
                        if (x2 >= 0 && x2 < m && y2 >= 0 && y2 < m && dist[i][x2][y2] == null) {
                            dist[i][x2][y2] = step;
                            q.offer(new int[] {x2, y2});
                        }
                    }
                }
            }
        }
        f = new Integer[n + 1][1 << n][2];
        return dfs(n, (1 << n) - 1, 1);
    }

    private int dfs(int last, int state, int k) {
        if (state == 0) {
            return 0;
        }
        if (f[last][state][k] != null) {
            return f[last][state][k];
        }
        int res = k == 1 ? 0 : Integer.MAX_VALUE;
        for (int i = 0; i < positions.length; ++i) {
            int x = positions[i][0], y = positions[i][1];
            if ((state >> i & 1) == 1) {
                int t = dfs(i, state ^ (1 << i), k ^ 1) + dist[last][x][y];
                res = k == 1 ? Math.max(res, t) : Math.min(res, t);
            }
        }
        return f[last][state][k] = res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxMoves(int kx, int ky, vector<vector<int>>& positions) {
        int n = positions.size();
        const int m = 50;
        const int dx[8] = {1, 1, 2, 2, -1, -1, -2, -2};
        const int dy[8] = {2, -2, 1, -1, 2, -2, 1, -1};
        int dist[n + 1][m][m];
        memset(dist, -1, sizeof(dist));
        for (int i = 0; i <= n; ++i) {
            int x = (i < n) ? positions[i][0] : kx;
            int y = (i < n) ? positions[i][1] : ky;
            queue<pair<int, int>> q;
            q.push({x, y});
            dist[i][x][y] = 0;
            for (int step = 1; !q.empty(); ++step) {
                for (int k = q.size(); k > 0; --k) {
                    auto [x1, y1] = q.front();
                    q.pop();
                    for (int j = 0; j < 8; ++j) {
                        int x2 = x1 + dx[j], y2 = y1 + dy[j];
                        if (x2 >= 0 && x2 < m && y2 >= 0 && y2 < m && dist[i][x2][y2] == -1) {
                            dist[i][x2][y2] = step;
                            q.push({x2, y2});
                        }
                    }
                }
            }
        }

        int f[n + 1][1 << n][2];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int last, int state, int k) -> int {
            if (state == 0) {
                return 0;
            }
            if (f[last][state][k] != -1) {
                return f[last][state][k];
            }
            int res = (k == 1) ? 0 : INT_MAX;

            for (int i = 0; i < positions.size(); ++i) {
                int x = positions[i][0], y = positions[i][1];
                if ((state >> i) & 1) {
                    int t = dfs(i, state ^ (1 << i), k ^ 1) + dist[last][x][y];
                    if (k == 1) {
                        res = max(res, t);
                    } else {
                        res = min(res, t);
                    }
                }
            }
            return f[last][state][k] = res;
        };
        return dfs(n, (1 << n) - 1, 1);
    }
};
```

#### Go

```go
func maxMoves(kx int, ky int, positions [][]int) int {
	n := len(positions)
	const m = 50
	dx := []int{1, 1, 2, 2, -1, -1, -2, -2}
	dy := []int{2, -2, 1, -1, 2, -2, 1, -1}
	dist := make([][][]int, n+1)
	for i := range dist {
		dist[i] = make([][]int, m)
		for j := range dist[i] {
			dist[i][j] = make([]int, m)
			for k := range dist[i][j] {
				dist[i][j][k] = -1
			}
		}
	}

	for i := 0; i <= n; i++ {
		x := kx
		y := ky
		if i < n {
			x = positions[i][0]
			y = positions[i][1]
		}
		q := [][2]int{[2]int{x, y}}
		dist[i][x][y] = 0

		for step := 1; len(q) > 0; step++ {
			for k := len(q); k > 0; k-- {
				p := q[0]
				q = q[1:]
				x1, y1 := p[0], p[1]
				for j := 0; j < 8; j++ {
					x2 := x1 + dx[j]
					y2 := y1 + dy[j]
					if x2 >= 0 && x2 < m && y2 >= 0 && y2 < m && dist[i][x2][y2] == -1 {
						dist[i][x2][y2] = step
						q = append(q, [2]int{x2, y2})
					}
				}
			}
		}
	}
	f := make([][][]int, n+1)
	for i := range f {
		f[i] = make([][]int, 1<<n)
		for j := range f[i] {
			f[i][j] = make([]int, 2)
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	var dfs func(last, state, k int) int
	dfs = func(last, state, k int) int {
		if state == 0 {
			return 0
		}
		if f[last][state][k] != -1 {
			return f[last][state][k]
		}

		var res int
		if k == 0 {
			res = math.MaxInt32
		}

		for i, p := range positions {
			x, y := p[0], p[1]
			if (state>>i)&1 == 1 {
				t := dfs(i, state^(1<<i), k^1) + dist[last][x][y]
				if k == 1 {
					res = max(res, t)
				} else {
					res = min(res, t)
				}
			}
		}
		f[last][state][k] = res
		return res
	}

	return dfs(n, (1<<n)-1, 1)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
