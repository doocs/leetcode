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

<!-- 这里可写通用的实现逻辑 -->

**方法一：Dijkstra 算法**

我们可以枚举每个城市 $i$ 作为起点，使用 Dijkstra 算法求出从 $i$ 到其他城市的最短距离，然后统计距离不超过阈值的城市个数，最后取最小的个数且编号最大的城市。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 为城市个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findTheCity(self, n: int, edges: List[List[int]], distanceThreshold: int) -> int:
        def dijkstra(u):
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
                    dist[j] = min(dist[j], dist[k] + g[k][j])
            return sum(d <= distanceThreshold for d in dist)

        g = [[inf] * n for _ in range(n)]
        for f, t, w in edges:
            g[f][t] = g[t][f] = w

        ans = n
        t = inf
        for i in range(n - 1, -1, -1):
            if (cnt := dijkstra(i)) < t:
                t = cnt
                ans = i
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int n;
    private int[][] g;
    private int[] dist;
    private boolean[] vis;
    private int inf = 1 << 30;
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
        int ans = n, t = inf;
        for (int i = n - 1; i >= 0; --i) {
            int cnt = dijkstra(i);
            if (t > cnt) {
                t = cnt;
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

### **C++**

```cpp
class Solution {
public:
    int findTheCity(int n, vector<vector<int>>& edges, int distanceThreshold) {
        const int inf = 1e7;
        vector<vector<int>> g(n, vector<int>(n, inf));
        vector<int> dist(n, inf);
        vector<bool> vis(n);
        for (auto& e : edges) {
            int f = e[0], t = e[1], w = e[2];
            g[f][t] = g[t][f] = w;
        }
        auto dijkstra = [&](int u) {
            dist.assign(n, inf);
            vis.assign(n, false);
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
            int cnt = 0;
            for (int& d : dist) {
                cnt += d <= distanceThreshold;
            }
            return cnt;
        };
        int ans = n, t = inf;
        for (int i = n - 1; ~i; --i) {
            int cnt = dijkstra(i);
            if (t > cnt) {
                t = cnt;
                ans = i;
            }
        }
        return ans;
    }
};
```

### **Go**

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

	ans, t := n, inf
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
	for i := n - 1; i >= 0; i-- {
		cnt := dijkstra(i)
		if t > cnt {
			t = cnt
			ans = i
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
