# [1334. 阈值距离内邻居最少的城市](https://leetcode.cn/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance)

[English Version](/solution/1300-1399/1334.Find%20the%20City%20With%20the%20Smallest%20Number%20of%20Neighbors%20at%20a%20Threshold%20Distance/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code> 个城市，按从 <code>0</code> 到 <code>n-1</code> 编号。给你一个边数组 <code>edges</code>，其中 <code>edges[i] = [from<sub>i</sub>, to<sub>i</sub>, weight<sub>i</sub>]</code> 代表 <code>from<sub>i</sub></code> 和 <code>to<sub>i</sub></code><sub> </sub>两个城市之间的双向加权边，距离阈值是一个整数 <code>distanceThreshold</code>。</p>

<p>返回能通过某些路径到达其他城市数目最少、且路径距离 <strong>最大</strong> 为 <code>distanceThreshold</code> 的城市。如果有多个这样的城市，则返回编号最大的城市。</p>

<p>注意，连接城市 <em><strong>i</strong></em> 和 <em><strong>j</strong></em> 的路径的距离等于沿该路径的所有边的权重之和。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1334.Find%20the%20City%20With%20the%20Smallest%20Number%20of%20Neighbors%20at%20a%20Threshold%20Distance/images/find_the_city_01.png" style="height: 225px; width: 300px;" /></p>

<pre>
<strong>输入：</strong>n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
<strong>输出：</strong>3
<strong>解释：</strong>城市分布图如上。
每个城市阈值距离 distanceThreshold = 4 内的邻居城市分别是：
城市 0 -> [城市 1, 城市 2] 
城市 1 -> [城市 0, 城市 2, 城市 3] 
城市 2 -> [城市 0, 城市 1, 城市 3] 
城市 3 -> [城市 1, 城市 2] 
城市 0 和 3 在阈值距离 4 以内都有 2 个邻居城市，但是我们必须返回城市 3，因为它的编号最大。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1334.Find%20the%20City%20With%20the%20Smallest%20Number%20of%20Neighbors%20at%20a%20Threshold%20Distance/images/find_the_city_02.png" style="height: 225px; width: 300px;" /></strong></p>

<pre>
<strong>输入：</strong>n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
<strong>输出：</strong>0
<strong>解释：</strong>城市分布图如上。 
每个城市阈值距离 distanceThreshold = 2 内的邻居城市分别是：
城市 0 -> [城市 1] 
城市 1 -> [城市 0, 城市 4] 
城市 2 -> [城市 3, 城市 4] 
城市 3 -> [城市 2, 城市 4]
城市 4 -> [城市 1, 城市 2, 城市 3] 
城市 0 在阈值距离 2 以内只有 1 个邻居城市。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 100</code></li>
	<li><code>1 <= edges.length <= n * (n - 1) / 2</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 <= from<sub>i</sub> < to<sub>i</sub> < n</code></li>
	<li><code>1 <= weight<sub>i</sub>, distanceThreshold <= 10^4</code></li>
	<li>所有 <code>(from<sub>i</sub>, to<sub>i</sub>)</code> 都是不同的。</li>
</ul>

## 解法

### 方法一：Dijkstra 算法

我们可以枚举每个城市 $i$ 作为起点，使用 Dijkstra 算法求出从 $i$ 到其他城市的最短距离，然后统计距离不超过阈值的城市个数，最后取最小的个数且编号最大的城市。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 为城市个数。

<!-- tabs:start -->

```python
class Solution:
    def findTheCity(
        self, n: int, edges: List[List[int]], distanceThreshold: int
    ) -> int:
        def dijkstra(u: int) -> int:
            dist = [inf] * n
            dist[u] = 0
            vis = [False] * n
            for _ in range(n):
                k = -1
                for j in range(n):
                    if not vis[j] and (k == -1 or dist[k] > dist[j]):
                        k = j
                vis[k] = True
                for j in range(n):
                    # dist[j] = min(dist[j], dist[k] + g[k][j])
                    if dist[k] + g[k][j] < dist[j]:
                        dist[j] = dist[k] + g[k][j]
            return sum(d <= distanceThreshold for d in dist)

        g = [[inf] * n for _ in range(n)]
        for f, t, w in edges:
            g[f][t] = g[t][f] = w
        ans, cnt = n, inf
        for i in range(n - 1, -1, -1):
            if (t := dijkstra(i)) < cnt:
                cnt, ans = t, i
        return ans
```

```java
class Solution {
    private int n;
    private int[][] g;
    private int[] dist;
    private boolean[] vis;
    private final int inf = 1 << 30;
    private int distanceThreshold;

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        this.n = n;
        this.distanceThreshold = distanceThreshold;
        g = new int[n][n];
        dist = new int[n];
        vis = new boolean[n];
        for (var e : g) {
            Arrays.fill(e, inf);
        }
        for (var e : edges) {
            int f = e[0], t = e[1], w = e[2];
            g[f][t] = w;
            g[t][f] = w;
        }
        int ans = n, cnt = inf;
        for (int i = n - 1; i >= 0; --i) {
            int t = dijkstra(i);
            if (t < cnt) {
                cnt = t;
                ans = i;
            }
        }
        return ans;
    }

    private int dijkstra(int u) {
        Arrays.fill(dist, inf);
        Arrays.fill(vis, false);
        dist[u] = 0;
        for (int i = 0; i < n; ++i) {
            int k = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (k == -1 || dist[k] > dist[j])) {
                    k = j;
                }
            }
            vis[k] = true;
            for (int j = 0; j < n; ++j) {
                dist[j] = Math.min(dist[j], dist[k] + g[k][j]);
            }
        }
        int cnt = 0;
        for (int d : dist) {
            if (d <= distanceThreshold) {
                ++cnt;
            }
        }
        return cnt;
    }
}
```

```cpp
class Solution {
public:
    int findTheCity(int n, vector<vector<int>>& edges, int distanceThreshold) {
        int g[n][n];
        int dist[n];
        bool vis[n];
        memset(g, 0x3f, sizeof(g));
        for (auto& e : edges) {
            int f = e[0], t = e[1], w = e[2];
            g[f][t] = g[t][f] = w;
        }
        auto dijkstra = [&](int u) {
            memset(dist, 0x3f, sizeof(dist));
            memset(vis, 0, sizeof(vis));
            dist[u] = 0;
            for (int i = 0; i < n; ++i) {
                int k = -1;
                for (int j = 0; j < n; ++j) {
                    if (!vis[j] && (k == -1 || dist[j] < dist[k])) {
                        k = j;
                    }
                }
                vis[k] = true;
                for (int j = 0; j < n; ++j) {
                    dist[j] = min(dist[j], dist[k] + g[k][j]);
                }
            }
            return count_if(dist, dist + n, [&](int d) { return d <= distanceThreshold; });
        };
        int ans = n, cnt = n + 1;
        for (int i = n - 1; ~i; --i) {
            int t = dijkstra(i);
            if (t < cnt) {
                cnt = t;
                ans = i;
            }
        }
        return ans;
    }
};
```

```go
func findTheCity(n int, edges [][]int, distanceThreshold int) int {
	g := make([][]int, n)
	dist := make([]int, n)
	vis := make([]bool, n)
	const inf int = 1e7
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = inf
		}
	}
	for _, e := range edges {
		f, t, w := e[0], e[1], e[2]
		g[f][t], g[t][f] = w, w
	}

	dijkstra := func(u int) (cnt int) {
		for i := range vis {
			vis[i] = false
			dist[i] = inf
		}
		dist[u] = 0
		for i := 0; i < n; i++ {
			k := -1
			for j := 0; j < n; j++ {
				if !vis[j] && (k == -1 || dist[j] < dist[k]) {
					k = j
				}
			}
			vis[k] = true
			for j := 0; j < n; j++ {
				dist[j] = min(dist[j], dist[k]+g[k][j])
			}
		}
		for _, d := range dist {
			if d <= distanceThreshold {
				cnt++
			}
		}
		return
	}

	ans, cnt := n, inf
	for i := n - 1; i >= 0; i-- {
		if t := dijkstra(i); t < cnt {
			cnt = t
			ans = i
		}
	}
	return ans
}
```

```ts
function findTheCity(n: number, edges: number[][], distanceThreshold: number): number {
    const g: number[][] = Array.from({ length: n }, () => Array(n).fill(Infinity));
    const dist: number[] = Array(n).fill(Infinity);
    const vis: boolean[] = Array(n).fill(false);
    for (const [f, t, w] of edges) {
        g[f][t] = g[t][f] = w;
    }

    const dijkstra = (u: number): number => {
        dist.fill(Infinity);
        vis.fill(false);
        dist[u] = 0;
        for (let i = 0; i < n; ++i) {
            let k = -1;
            for (let j = 0; j < n; ++j) {
                if (!vis[j] && (k === -1 || dist[j] < dist[k])) {
                    k = j;
                }
            }
            vis[k] = true;
            for (let j = 0; j < n; ++j) {
                dist[j] = Math.min(dist[j], dist[k] + g[k][j]);
            }
        }
        return dist.filter(d => d <= distanceThreshold).length;
    };

    let ans = n;
    let cnt = Infinity;
    for (let i = n - 1; i >= 0; --i) {
        const t = dijkstra(i);
        if (t < cnt) {
            cnt = t;
            ans = i;
        }
    }

    return ans;
}
```

<!-- tabs:end -->

### 方法二：Floyd 算法

我们定义 $g[i][j]$ 表示城市 $i$ 到城市 $j$ 的最短距离，初始时 $g[i][j] = \infty$, $g[i][i] = 0$，然后我们遍历所有边，对于每条边 $(f, t, w)$，我们令 $g[f][t] = g[t][f] = w$。

接下来，我们用 Floyd 算法求出任意两点之间的最短距离。具体地，我们先枚举中间点 $k$，再枚举起点 $i$ 和终点 $j$，如果 $g[i][k] + g[k][j] \lt g[i][j]$，那么我们就用更短的距离 $g[i][k] + g[k][j]$ 更新 $g[i][j]$。

最后，我们枚举每个城市 $i$ 作为起点，统计距离不超过阈值的城市个数，最后取最小的个数且编号最大的城市。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 为城市个数。

<!-- tabs:start -->

```python
class Solution:
    def findTheCity(
        self, n: int, edges: List[List[int]], distanceThreshold: int
    ) -> int:
        g = [[inf] * n for _ in range(n)]
        for f, t, w in edges:
            g[f][t] = g[t][f] = w

        for k in range(n):
            g[k][k] = 0
            for i in range(n):
                for j in range(n):
                    # g[i][j] = min(g[i][j], g[i][k] + g[k][j])
                    if g[i][k] + g[k][j] < g[i][j]:
                        g[i][j] = g[i][k] + g[k][j]

        ans, cnt = n, inf
        for i in range(n - 1, -1, -1):
            t = sum(d <= distanceThreshold for d in g[i])
            if t < cnt:
                cnt, ans = t, i
        return ans
```

```java
class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        final int inf = 1 << 29;
        int[][] g = new int[n][n];
        for (var e : g) {
            Arrays.fill(e, inf);
        }
        for (var e : edges) {
            int f = e[0], t = e[1], w = e[2];
            g[f][t] = w;
            g[t][f] = w;
        }
        for (int k = 0; k < n; ++k) {
            g[k][k] = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }
        int ans = n, cnt = inf;
        for (int i = n - 1; i >= 0; --i) {
            int t = 0;
            for (int d : g[i]) {
                if (d <= distanceThreshold) {
                    ++t;
                }
            }
            if (t < cnt) {
                cnt = t;
                ans = i;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findTheCity(int n, vector<vector<int>>& edges, int distanceThreshold) {
        int g[n][n];
        memset(g, 0x3f, sizeof(g));
        for (auto& e : edges) {
            int f = e[0], t = e[1], w = e[2];
            g[f][t] = g[t][f] = w;
        }
        for (int k = 0; k < n; ++k) {
            g[k][k] = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    g[i][j] = min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }
        int ans = n, cnt = n + 1;
        for (int i = n - 1; ~i; --i) {
            int t = count_if(g[i], g[i] + n, [&](int x) { return x <= distanceThreshold; });
            if (t < cnt) {
                cnt = t;
                ans = i;
            }
        }
        return ans;
    }
};
```

```go
func findTheCity(n int, edges [][]int, distanceThreshold int) int {
	g := make([][]int, n)
	const inf int = 1e7
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = inf
		}
	}

	for _, e := range edges {
		f, t, w := e[0], e[1], e[2]
		g[f][t], g[t][f] = w, w
	}

	for k := 0; k < n; k++ {
		g[k][k] = 0
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				g[i][j] = min(g[i][j], g[i][k]+g[k][j])
			}
		}
	}

	ans, cnt := n, n+1
	for i := n - 1; i >= 0; i-- {
		t := 0
		for _, x := range g[i] {
			if x <= distanceThreshold {
				t++
			}
		}
		if t < cnt {
			cnt, ans = t, i
		}
	}

	return ans
}
```

```ts
function findTheCity(n: number, edges: number[][], distanceThreshold: number): number {
    const g: number[][] = Array.from({ length: n }, () => Array(n).fill(Infinity));
    for (const [f, t, w] of edges) {
        g[f][t] = g[t][f] = w;
    }
    for (let k = 0; k < n; ++k) {
        g[k][k] = 0;
        for (let i = 0; i < n; ++i) {
            for (let j = 0; j < n; ++j) {
                g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
            }
        }
    }

    let ans = n,
        cnt = n + 1;
    for (let i = n - 1; i >= 0; --i) {
        const t = g[i].filter(x => x <= distanceThreshold).length;
        if (t < cnt) {
            cnt = t;
            ans = i;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
