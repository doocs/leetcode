# [1976. 到达目的地的方案数](https://leetcode.cn/problems/number-of-ways-to-arrive-at-destination)

[English Version](/solution/1900-1999/1976.Number%20of%20Ways%20to%20Arrive%20at%20Destination/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你在一个城市里，城市由 <code>n</code>&nbsp;个路口组成，路口编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;，某些路口之间有 <strong>双向</strong>&nbsp;道路。输入保证你可以从任意路口出发到达其他任意路口，且任意两个路口之间最多有一条路。</p>

<p>给你一个整数&nbsp;<code>n</code>&nbsp;和二维整数数组&nbsp;<code>roads</code>&nbsp;，其中&nbsp;<code>roads[i] = [u<sub>i</sub>, v<sub>i</sub>, time<sub>i</sub>]</code>&nbsp;表示在路口&nbsp;<code>u<sub>i</sub></code>&nbsp;和&nbsp;<code>v<sub>i</sub></code>&nbsp;之间有一条需要花费&nbsp;<code>time<sub>i</sub></code>&nbsp;时间才能通过的道路。你想知道花费 <strong>最少时间</strong>&nbsp;从路口&nbsp;<code>0</code>&nbsp;出发到达路口&nbsp;<code>n - 1</code>&nbsp;的方案数。</p>

<p>请返回花费 <strong>最少时间</strong>&nbsp;到达目的地的 <strong>路径数目</strong>&nbsp;。由于答案可能很大，将结果对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1976.Number%20of%20Ways%20to%20Arrive%20at%20Destination/images/graph2.png" style="width: 235px; height: 381px;">
<pre><b>输入：</b>n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
<b>输出：</b>4
<b>解释：</b>从路口 0 出发到路口 6 花费的最少时间是 7 分钟。
四条花费 7 分钟的路径分别为：
- 0 ➝ 6
- 0 ➝ 4 ➝ 6
- 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
- 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>n = 2, roads = [[1,0,10]]
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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：朴素 Dijkstra 算法**

在求最短路的过程中顺便记录到达某个点最短路径的方案数。松弛优化时，如果发现有更优的路径，则方案数也赋值最优路径的前驱的方案数。如果发现与最优的路径长度相同，则累加当前前驱的方案数。

由于图有可能非常稠密，所以采用朴素的 Dijkstra 算法。

时间复杂度 $O(n^2)$。

> 注意：最短路的长度可能会超过 32 位整数的范围。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countPaths(self, n: int, roads: List[List[int]]) -> int:
        INF = inf
        MOD = 10**9 + 7
        g = [[INF] * n for _ in range(n)]
        for u, v, t in roads:
            g[u][v] = t
            g[v][u] = t
        g[0][0] = 0
        dist = [INF] * n
        w = [0] * n
        dist[0] = 0
        w[0] = 1
        vis = [False] * n
        for _ in range(n):
            t = -1
            for i in range(n):
                if not vis[i] and (t == -1 or dist[i] < dist[t]):
                    t = i
            vis[t] = True
            for i in range(n):
                if i == t:
                    continue
                ne = dist[t] + g[t][i]
                if dist[i] > ne:
                    dist[i] = ne
                    w[i] = w[t]
                elif dist[i] == ne:
                    w[i] += w[t]
        return w[-1] % MOD
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final long INF = Long.MAX_VALUE / 2;
    private static final int MOD = (int) 1e9 + 7;

    public int countPaths(int n, int[][] roads) {
        long[][] g = new long[n][n];
        long[] dist = new long[n];
        long[] w = new long[n];
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], INF);
            Arrays.fill(dist, INF);
        }
        for (int[] r : roads) {
            int u = r[0], v = r[1], t = r[2];
            g[u][v] = t;
            g[v][u] = t;
        }
        g[0][0] = 0;
        dist[0] = 0;
        w[0] = 1;
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
                    w[j] = w[t];
                } else if (dist[j] == ne) {
                    w[j] = (w[j] + w[t]) % MOD;
                }
            }
        }
        return (int) w[n - 1];
    }
}
```

### **C++**

```cpp
typedef long long ll;

class Solution {
public:
    const ll INF = LLONG_MAX / 2;
    const int MOD = 1e9 + 7;

    int countPaths(int n, vector<vector<int>>& roads) {
        vector<vector<ll>> g(n, vector<ll>(n, INF));
        vector<ll> dist(n, INF);
        vector<ll> w(n);
        vector<bool> vis(n);
        for (auto& r : roads) {
            int u = r[0], v = r[1], t = r[2];
            g[u][v] = t;
            g[v][u] = t;
        }
        g[0][0] = 0;
        dist[0] = 0;
        w[0] = 1;
        for (int i = 0; i < n; ++i) {
            int t = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (t == -1 || dist[t] > dist[j])) t = j;
            }
            vis[t] = true;
            for (int j = 0; j < n; ++j) {
                if (t == j) continue;
                ll ne = dist[t] + g[t][j];
                if (dist[j] > ne) {
                    dist[j] = ne;
                    w[j] = w[t];
                } else if (dist[j] == ne)
                    w[j] = (w[j] + w[t]) % MOD;
            }
        }
        return w[n - 1];
    }
};
```

### **Go**

```go
func countPaths(n int, roads [][]int) int {
	const inf = math.MaxInt64 / 2
	const mod = int(1e9) + 7
	g := make([][]int, n)
	dist := make([]int, n)
	w := make([]int, n)
	vis := make([]bool, n)
	for i := range g {
		g[i] = make([]int, n)
		dist[i] = inf
		for j := range g[i] {
			g[i][j] = inf
		}
	}
	for _, r := range roads {
		u, v, t := r[0], r[1], r[2]
		g[u][v], g[v][u] = t, t
	}
	g[0][0] = 0
	dist[0] = 0
	w[0] = 1
	for i := 0; i < n; i++ {
		t := -1
		for j := 0; j < n; j++ {
			if !vis[j] && (t == -1 || dist[t] > dist[j]) {
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
				w[j] = w[t]
			} else if dist[j] == ne {
				w[j] = (w[j] + w[t]) % mod
			}
		}
	}
	return w[n-1]
}
```

### **...**

```

```

<!-- tabs:end -->
