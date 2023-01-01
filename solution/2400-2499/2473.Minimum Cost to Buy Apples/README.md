# [2473. 购买苹果的最低成本](https://leetcode.cn/problems/minimum-cost-to-buy-apples)

[English Version](/solution/2400-2499/2473.Minimum%20Cost%20to%20Buy%20Apples/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数&nbsp; <code>n</code>，表示从 <code>1</code> 到 <code>n</code> 的 <code>n</code> 个城市。还给你一个&nbsp;<strong>二维&nbsp;</strong>数组 <code>roads</code>，其中 <code>roads[i] = [a<sub>i</sub>, b<sub>i</sub>, cost<sub>i</sub>]</code> 表示在城市 <code>a<sub>i</sub></code> 和 <code>b<sub>i</sub></code> 之间有一条双向道路，其旅行成本等于 <code>cost<sub>i</sub></code>。</p>

<p>&nbsp;</p>

<p>你可以在&nbsp;<strong>任何&nbsp;</strong>城市买到苹果，但是有些城市买苹果的费用不同。给定数组 <code>appleCost</code> ，其中 <code>appleCost[i]</code>&nbsp;是从城市 <code>i</code> 购买一个苹果的成本。</p>

<p>你从某个城市开始，穿越各种道路，最终从&nbsp;<strong>任何一个&nbsp;</strong>城市买&nbsp;<strong>一个&nbsp;</strong>苹果。在你买了那个苹果之后，你必须回到你&nbsp;<strong>开始的&nbsp;</strong>城市，但现在所有道路的成本将&nbsp;<strong>乘以&nbsp;</strong>一个给定的因子 <code>k</code>。</p>

<p>给定整数 <code>k</code>，返回<em>一个大小为 <code>n</code> 的数组 <code>answer</code>，其中 <code>answer[i]</code>&nbsp;是从城市 <code>i</code> 开始购买一个苹果的&nbsp;<strong>最小&nbsp;</strong>总成本。</em></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2473.Minimum%20Cost%20to%20Buy%20Apples/images/graph55.png" style="width: 241px; height: 309px;" />
<pre>
<strong>输入:</strong> n = 4, roads = [[1,2,4],[2,3,2],[2,4,5],[3,4,1],[1,3,4]], appleCost = [56,42,102,301], k = 2
<strong>输出:</strong> [54,42,48,51]
<strong>解释:</strong> 每个起始城市的最低费用如下:
- 从城市 1 开始:你走路径 1 -&gt; 2，在城市 2 买一个苹果，最后走路径 2 -&gt; 1。总成本是 4 + 42 + 4 * 2 = 54。
- 从城市 2 开始:你直接在城市 2 买一个苹果。总费用是 42。
- 从城市 3 开始:你走路径 3 -&gt; 2，在城市 2 买一个苹果，最后走路径 2 -&gt; 3。总成本是 2 + 42 + 2 * 2 = 48。
- 从城市 4 开始:你走路径 4 -&gt; 3 -&gt; 2，然后你在城市 2 购买，最后走路径 2 -&gt; 3 -&gt; 4。总成本是 1 + 2 + 42 + 1 * 2 + 2 * 2 = 51。
</pre>

<p><strong class="example">示例 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2473.Minimum%20Cost%20to%20Buy%20Apples/images/graph4.png" style="width: 167px; height: 309px;" />
<pre>
<strong>输入:</strong> n = 3, roads = [[1,2,5],[2,3,1],[3,1,2]], appleCost = [2,3,1], k = 3
<strong>输出:</strong> [2,3,1]
<strong>解释:</strong> 在起始城市买苹果总是最优的。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= roads.length &lt;= 1000</code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>1 &lt;= cost<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>appleCost.length == n</code></li>
	<li><code>1 &lt;= appleCost[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
	<li>
	<p data-group="1-1">没有重复的边。</p>
	</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：堆优化版 Dijkstra 算法**

我们枚举起点，对于每个起点，使用 Dijkstra 算法求出到其他所有点的最短距离，更新最小值即可。

时间复杂度 $O(n \times m \times \log m)$，其中 $n$ 和 $m$ 分别是城市数量和道路数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minCost(self, n: int, roads: List[List[int]], appleCost: List[int], k: int) -> List[int]:
        def dijkstra(i):
            q = [(0, i)]
            dist = [inf] * n
            dist[i] = 0
            ans = inf
            while q:
                d, u = heappop(q)
                ans = min(ans, appleCost[u] + d * (k + 1))
                for v, w in g[u]:
                    if dist[v] > dist[u] + w:
                        dist[v] = dist[u] + w
                        heappush(q, (dist[v], v))
            return ans

        g = defaultdict(list)
        for a, b, c in roads:
            a, b = a - 1, b - 1
            g[a].append((b, c))
            g[b].append((a, c))
        return [dijkstra(i) for i in range(n)]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int k;
    private int[] cost;
    private int[] dist;
    private List<int[]>[] g;
    private static final int INF = 0x3f3f3f3f;

    public long[] minCost(int n, int[][] roads, int[] appleCost, int k) {
        cost = appleCost;
        g = new List[n];
        dist = new int[n];
        this.k = k;
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (var e : roads) {
            int a = e[0] - 1, b = e[1] - 1, c = e[2];
            g[a].add(new int[] {b, c});
            g[b].add(new int[] {a, c});
        }
        long[] ans = new long[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = dijkstra(i);
        }
        return ans;
    }

    private long dijkstra(int u) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        q.offer(new int[] {0, u});
        Arrays.fill(dist, INF);
        dist[u] = 0;
        long ans = Long.MAX_VALUE;
        while (!q.isEmpty()) {
            var p = q.poll();
            int d = p[0];
            u = p[1];
            ans = Math.min(ans, cost[u] + (long) (k + 1) * d);
            for (var ne : g[u]) {
                int v = ne[0], w = ne[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    q.offer(new int[] {dist[v], v});
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
using ll = long long;
using pii = pair<int, int>;

class Solution {
public:
    const int inf = 0x3f3f3f3f;

    vector<long long> minCost(int n, vector<vector<int>>& roads, vector<int>& appleCost, int k) {
        vector<vector<pii>> g(n);
        for (auto& e : roads) {
            int a = e[0] - 1, b = e[1] - 1, c = e[2];
            g[a].push_back({b, c});
            g[b].push_back({a, c});
        }
        int dist[n];
        auto dijkstra = [&](int u) {
            memset(dist, 63, sizeof dist);
            priority_queue<pii, vector<pii>, greater<pii>> q;
            q.push({0, u});
            dist[u] = 0;
            ll ans = LONG_MAX;
            while (!q.empty()) {
                auto p = q.top();
                q.pop();
                int d = p.first;
                u = p.second;
                ans = min(ans, appleCost[u] + 1ll * d * (k + 1));
                for (auto& ne : g[u]) {
                    auto [v, w] = ne;
                    if (dist[v] > dist[u] + w) {
                        dist[v] = dist[u] + w;
                        q.push({dist[v], v});
                    }
                }
            }
            return ans;
        };
        vector<ll> ans(n);
        for (int i = 0; i < n; ++i) ans[i] = dijkstra(i);
        return ans;
    }
};
```

### **Go**

```go
func minCost(n int, roads [][]int, appleCost []int, k int) []int64 {
	g := make([]pairs, n)
	for _, e := range roads {
		a, b, c := e[0]-1, e[1]-1, e[2]
		g[a] = append(g[a], pair{b, c})
		g[b] = append(g[b], pair{a, c})
	}
	const inf int = 0x3f3f3f3f
	dist := make([]int, n)
	dijkstra := func(u int) int64 {
		var ans int64 = math.MaxInt64
		for i := range dist {
			dist[i] = inf
		}
		dist[u] = 0
		q := make(pairs, 0)
		heap.Push(&q, pair{0, u})
		for len(q) > 0 {
			p := heap.Pop(&q).(pair)
			d := p.first
			u = p.second
			ans = min(ans, int64(appleCost[u]+d*(k+1)))
			for _, ne := range g[u] {
				v, w := ne.first, ne.second
				if dist[v] > dist[u]+w {
					dist[v] = dist[u] + w
					heap.Push(&q, pair{dist[v], v})
				}
			}
		}
		return ans
	}
	ans := make([]int64, n)
	for i := range ans {
		ans[i] = dijkstra(i)
	}
	return ans
}

func min(a, b int64) int64 {
	if a < b {
		return a
	}
	return b
}

type pair struct{ first, second int }

var _ heap.Interface = (*pairs)(nil)

type pairs []pair

func (a pairs) Len() int { return len(a) }
func (a pairs) Less(i int, j int) bool {
	return a[i].first < a[j].first || a[i].first == a[j].first && a[i].second < a[j].second
}
func (a pairs) Swap(i int, j int)   { a[i], a[j] = a[j], a[i] }
func (a *pairs) Push(x interface{}) { *a = append(*a, x.(pair)) }
func (a *pairs) Pop() interface{}   { l := len(*a); t := (*a)[l-1]; *a = (*a)[:l-1]; return t }
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
