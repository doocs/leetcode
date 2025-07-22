---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1976.Number%20of%20Ways%20to%20Arrive%20at%20Destination/README.md
rating: 2094
source: 第 59 场双周赛 Q3
tags:
    - 图
    - 拓扑排序
    - 动态规划
    - 最短路
---

<!-- problem:start -->

# [1976. 到达目的地的方案数](https://leetcode.cn/problems/number-of-ways-to-arrive-at-destination)

[English Version](/solution/1900-1999/1976.Number%20of%20Ways%20to%20Arrive%20at%20Destination/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你在一个城市里，城市由 <code>n</code>&nbsp;个路口组成，路口编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;，某些路口之间有 <strong>双向</strong>&nbsp;道路。输入保证你可以从任意路口出发到达其他任意路口，且任意两个路口之间最多有一条路。</p>

<p>给你一个整数&nbsp;<code>n</code>&nbsp;和二维整数数组&nbsp;<code>roads</code>&nbsp;，其中&nbsp;<code>roads[i] = [u<sub>i</sub>, v<sub>i</sub>, time<sub>i</sub>]</code>&nbsp;表示在路口&nbsp;<code>u<sub>i</sub></code>&nbsp;和&nbsp;<code>v<sub>i</sub></code>&nbsp;之间有一条需要花费&nbsp;<code>time<sub>i</sub></code>&nbsp;时间才能通过的道路。你想知道花费 <strong>最少时间</strong>&nbsp;从路口&nbsp;<code>0</code>&nbsp;出发到达路口&nbsp;<code>n - 1</code>&nbsp;的方案数。</p>

<p>请返回花费 <strong>最少时间</strong>&nbsp;到达目的地的 <strong>路径数目</strong>&nbsp;。由于答案可能很大，将结果对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1976.Number%20of%20Ways%20to%20Arrive%20at%20Destination/images/1976_corrected.png" style="width: 255px; height: 400px;" />
<pre>
<b>输入：</b>n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
<b>输出：</b>4
<b>解释：</b>从路口 0 出发到路口 6 花费的最少时间是 7 分钟。
四条花费 7 分钟的路径分别为：
- 0 ➝ 6
- 0 ➝ 4 ➝ 6
- 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
- 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 2, roads = [[1,0,10]]
<b>输出：</b>1
<b>解释：</b>只有一条从路口 0 到路口 1 的路，花费 10 分钟。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>n - 1 &lt;= roads.length &lt;= n * (n - 1) / 2</code></li>
	<li><code>roads[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= time<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>u<sub>i </sub>!= v<sub>i</sub></code></li>
	<li>任意两个路口之间至多有一条路。</li>
	<li>从任意路口出发，你能够到达其他任意路口。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：朴素 Dijkstra 算法

我们定义以下几个数组，其中：

-   `g` 表示图的邻接矩阵，`g[i][j]` 表示点 `i` 到点 `j` 的最短路径长度，初始时全部为 $\infty$，而 `g[0][0]` 为 $0$；然后我们遍历 `roads`，将 `g[u][v]` 和 `g[v][u]` 更新为 `t`；
-   `dist[i]` 表示从起点到点 `i` 的最短路径长度，初始时全部为 $\infty$，而 `dist[0]` 为 $0$；
-   `f[i]` 表示从起点到点 `i` 的最短路径的条数，初始时全部为 $0$，而 `f[0]` 为 $1$；
-   `vis[i]` 表示点 `i` 是否已经被访问过，初始时全部为 `False`。

然后，我们使用朴素 Dijkstra 算法求出从起点到终点的最短路径长度，过程中同时记录下每个点的最短路径的条数。

最后，我们返回 `f[n - 1]` 即可。由于答案可能很大，我们需要对 $10^9 + 7$ 取模。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为点的个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPaths(self, n: int, roads: List[List[int]]) -> int:
        g = [[inf] * n for _ in range(n)]
        for u, v, t in roads:
            g[u][v] = g[v][u] = t
        g[0][0] = 0
        dist = [inf] * n
        dist[0] = 0
        f = [0] * n
        f[0] = 1
        vis = [False] * n
        for _ in range(n):
            t = -1
            for j in range(n):
                if not vis[j] and (t == -1 or dist[j] < dist[t]):
                    t = j
            vis[t] = True
            for j in range(n):
                if j == t:
                    continue
                ne = dist[t] + g[t][j]
                if dist[j] > ne:
                    dist[j] = ne
                    f[j] = f[t]
                elif dist[j] == ne:
                    f[j] += f[t]
        mod = 10**9 + 7
        return f[-1] % mod
```

#### Java

```java
class Solution {
    public int countPaths(int n, int[][] roads) {
        final long inf = Long.MAX_VALUE / 2;
        final int mod = (int) 1e9 + 7;
        long[][] g = new long[n][n];
        for (var e : g) {
            Arrays.fill(e, inf);
        }
        for (var r : roads) {
            int u = r[0], v = r[1], t = r[2];
            g[u][v] = t;
            g[v][u] = t;
        }
        g[0][0] = 0;
        long[] dist = new long[n];
        Arrays.fill(dist, inf);
        dist[0] = 0;
        long[] f = new long[n];
        f[0] = 1;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            int t = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (t == -1 || dist[j] < dist[t])) {
                    t = j;
                }
            }
            vis[t] = true;
            for (int j = 0; j < n; ++j) {
                if (j == t) {
                    continue;
                }
                long ne = dist[t] + g[t][j];
                if (dist[j] > ne) {
                    dist[j] = ne;
                    f[j] = f[t];
                } else if (dist[j] == ne) {
                    f[j] = (f[j] + f[t]) % mod;
                }
            }
        }
        return (int) f[n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countPaths(int n, vector<vector<int>>& roads) {
        const long long inf = LLONG_MAX / 2;
        const int mod = 1e9 + 7;

        vector<vector<long long>> g(n, vector<long long>(n, inf));
        for (auto& e : g) {
            fill(e.begin(), e.end(), inf);
        }

        for (auto& r : roads) {
            int u = r[0], v = r[1], t = r[2];
            g[u][v] = t;
            g[v][u] = t;
        }

        g[0][0] = 0;

        vector<long long> dist(n, inf);
        fill(dist.begin(), dist.end(), inf);
        dist[0] = 0;

        vector<long long> f(n);
        f[0] = 1;

        vector<bool> vis(n);
        for (int i = 0; i < n; ++i) {
            int t = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (t == -1 || dist[j] < dist[t])) {
                    t = j;
                }
            }
            vis[t] = true;
            for (int j = 0; j < n; ++j) {
                if (j == t) {
                    continue;
                }
                long long ne = dist[t] + g[t][j];
                if (dist[j] > ne) {
                    dist[j] = ne;
                    f[j] = f[t];
                } else if (dist[j] == ne) {
                    f[j] = (f[j] + f[t]) % mod;
                }
            }
        }
        return (int) f[n - 1];
    }
};
```

#### Go

```go
func countPaths(n int, roads [][]int) int {
	const inf = math.MaxInt64 / 2
	const mod = int(1e9 + 7)

	g := make([][]int, n)
	dist := make([]int, n)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = inf
			dist[i] = inf
		}
	}

	for _, r := range roads {
		u, v, t := r[0], r[1], r[2]
		g[u][v] = t
		g[v][u] = t
	}

	f := make([]int, n)
	vis := make([]bool, n)
	f[0] = 1
	g[0][0] = 0
	dist[0] = 0

	for i := 0; i < n; i++ {
		t := -1
		for j := 0; j < n; j++ {
			if !vis[j] && (t == -1 || dist[j] < dist[t]) {
				t = j
			}
		}
		vis[t] = true
		for j := 0; j < n; j++ {
			if j == t {
				continue
			}
			ne := dist[t] + g[t][j]
			if dist[j] > ne {
				dist[j] = ne
				f[j] = f[t]
			} else if dist[j] == ne {
				f[j] = (f[j] + f[t]) % mod
			}
		}
	}
	return f[n-1]
}
```

#### TypeScript

```ts
function countPaths(n: number, roads: number[][]): number {
    const mod: number = 1e9 + 7;
    const g: number[][] = Array.from({ length: n }, () => Array(n).fill(Infinity));
    for (const [u, v, t] of roads) {
        g[u][v] = t;
        g[v][u] = t;
    }
    g[0][0] = 0;

    const dist: number[] = Array(n).fill(Infinity);
    dist[0] = 0;

    const f: number[] = Array(n).fill(0);
    f[0] = 1;

    const vis: boolean[] = Array(n).fill(false);
    for (let i = 0; i < n; ++i) {
        let t: number = -1;
        for (let j = 0; j < n; ++j) {
            if (!vis[j] && (t === -1 || dist[j] < dist[t])) {
                t = j;
            }
        }
        vis[t] = true;
        for (let j = 0; j < n; ++j) {
            if (j === t) {
                continue;
            }
            const ne: number = dist[t] + g[t][j];
            if (dist[j] > ne) {
                dist[j] = ne;
                f[j] = f[t];
            } else if (dist[j] === ne) {
                f[j] = (f[j] + f[t]) % mod;
            }
        }
    }
    return f[n - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
