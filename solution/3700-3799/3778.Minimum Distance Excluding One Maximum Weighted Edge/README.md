---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3778.Minimum%20Distance%20Excluding%20One%20Maximum%20Weighted%20Edge/README.md
---

<!-- problem:start -->

# [3778. 排除一个最大权重边的最小距离 🔒](https://leetcode.cn/problems/minimum-distance-excluding-one-maximum-weighted-edge)

[English Version](/solution/3700-3799/3778.Minimum%20Distance%20Excluding%20One%20Maximum%20Weighted%20Edge/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个正整数&nbsp;<code>n</code>&nbsp;和一个二维整数数组&nbsp;<code>edges</code>，其中&nbsp;<code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code>。</p>

<p>有一个 <strong>带权连通</strong> 简单无向图，包含 <code>n</code> 个节点，下标从 <code>0</code> 到 <code>n - 1</code>。每条边 <code>[u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code> 表示在节点 <code>u<sub>i</sub></code> 和节点&nbsp;<code>v<sub>i</sub></code> 之间有一条 <strong>正</strong> 权边 <code>w<sub>i</sub></code>。</p>

<p>路径的 <strong>开销</strong> 是路径中边的权重之 <strong>和</strong>，<strong>排除</strong> 权重 <strong>最大</strong> 的那条边。如果路径中有多个权重最大的边，<strong>只</strong> 排除 <strong>第一个</strong> 这样的边。</p>

<p>返回一个整数表示从节点 <code>0</code> 到节点 <code>n - 1</code>&nbsp;的 <strong>最小</strong> 开销。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 5, edges = [[0,1,2],[1,2,7],[2,3,7],[3,4,4]]</span></p>

<p><span class="example-io"><b>输出：</b>13</span></p>

<p><strong>解释：</strong></p>

<p>从节点 0 到节点 4 只有一条路径： <code>0 -&gt; 1 -&gt; 2 -&gt; 3 -&gt; 4</code>.</p>

<p>路径上边的权重是 2，7，7 和 4。</p>

<p>排除第一条权重最大的边，即&nbsp;<code>1 -&gt; 2</code>，路径的开销是&nbsp;<code>2 + 7 + 4 = 13</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 3, edges = [[0,1,1],[1,2,1],[0,2,50000]]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p>从节点 0 到 2 有两条路径：</p>

<ul>
	<li><code>0 -&gt; 1 -&gt; 2</code></li>
</ul>

<p>路径上边的权重是 1 和 1。</p>

<p>排除第一条权重最大的边，即&nbsp;<code>0 -&gt; 1</code>，路径的开销是&nbsp;<code>1</code>。</p>

<ul>
	<li><code>0 -&gt; 2</code></li>
</ul>

<p>这条路径上唯一的边权重是1。</p>

<p>排除第一条权重最大的边，即&nbsp;<code>0 -&gt; 2</code>，路径的开销是&nbsp;<code>0</code>。</p>

<p>最小的开销是&nbsp;<code>min(1, 0) = 0</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>n - 1 &lt;= edges.length &lt;= 10<sup>9</sup></code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub> &lt; v<sub>i</sub> &lt; n</code></li>
	<li><code>[u<sub>i</sub>, v<sub>i</sub>] != [u<sub>j</sub>, v<sub>j</sub>]</code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 5 * 10<sup>4</sup></code></li>
	<li>图是连通的。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCostExcludingMax(self, n: int, edges: List[List[int]]) -> int:
        g = [[] for _ in range(n)]
        for u, v, w in edges:
            g[u].append((v, w))
            g[v].append((u, w))
        dist = [[inf] * 2 for _ in range(n)]
        dist[0][0] = 0
        pq = [(0, 0, 0)]
        while pq:
            cur, u, used = heappop(pq)
            if cur > dist[u][used]:
                continue
            if u == n - 1 and used:
                return cur
            for v, w in g[u]:
                nxt = cur + w
                if nxt < dist[v][used]:
                    dist[v][used] = nxt
                    heappush(pq, (nxt, v, used))
                if used == 0:
                    nxt = cur
                    if nxt < dist[v][1]:
                        dist[v][1] = nxt
                        heappush(pq, (nxt, v, 1))
        return dist[n - 1][1]
```

#### Java

```java
class Solution {
    public long minCostExcludingMax(int n, int[][] edges) {
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].add(new int[]{v, w});
            g[v].add(new int[]{u, w});
        }

        long inf = Long.MAX_VALUE / 4;
        long[][] dist = new long[n][2];
        for (int i = 0; i < n; i++) {
            dist[i][0] = inf;
            dist[i][1] = inf;
        }
        dist[0][0] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.add(new long[]{0, 0, 0});

        while (!pq.isEmpty()) {
            long[] t = pq.poll();
            long cur = t[0];
            int u = (int) t[1];
            int used = (int) t[2];

            if (cur > dist[u][used]) {
                continue;
            }
            if (u == n - 1 && used == 1) {
                return cur;
            }

            for (int[] ed : g[u]) {
                int v = ed[0], w = ed[1];
                long nxt = cur + w;
                if (nxt < dist[v][used]) {
                    dist[v][used] = nxt;
                    pq.add(new long[]{nxt, v, used});
                }

                if (used == 0) {
                    nxt = cur;
                    if (nxt < dist[v][1]) {
                        dist[v][1] = nxt;
                        pq.add(new long[]{nxt, v, 1});
                    }
                }
            }
        }

        return dist[n - 1][1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minCostExcludingMax(int n, vector<vector<int>>& edges) {
        vector<vector<pair<int, int>>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].push_back({v, w});
            g[v].push_back({u, w});
        }

        long long inf = LLONG_MAX / 4;
        vector<array<long long, 2>> dist(n, {inf, inf});
        dist[0][0] = 0;

        priority_queue<array<long long, 3>, vector<array<long long, 3>>, greater<array<long long, 3>>> pq;
        pq.push({0, 0, 0});

        while (!pq.empty()) {
            auto t = pq.top();
            pq.pop();
            long long cur = t[0];
            int u = t[1];
            int used = t[2];

            if (cur > dist[u][used]) {
                continue;
            }
            if (u == n - 1 && used == 1) {
                return cur;
            }

            for (auto [v, w] : g[u]) {
                long long nxt = cur + w;
                if (nxt < dist[v][used]) {
                    dist[v][used] = nxt;
                    pq.push({nxt, v, used});
                }

                if (used == 0) {
                    nxt = cur;
                    if (nxt < dist[v][1]) {
                        dist[v][1] = nxt;
                        pq.push({nxt, v, 1});
                    }
                }
            }
        }

        return dist[n - 1][1];
    }
};
```

#### Go

```go
func minCostExcludingMax(n int, edges [][]int) int64 {
	g := make([][]edge, n)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u] = append(g[u], edge{v, w})
		g[v] = append(g[v], edge{u, w})
	}

	inf := int64(math.MaxInt64 / 4)
	dist := make([][2]int64, n)
	for i := 0; i < n; i++ {
		dist[i][0] = inf
		dist[i][1] = inf
	}
	dist[0][0] = 0

	pq := hp{{0, 0, 0}}
	for len(pq) > 0 {
		t := heap.Pop(&pq).(state)
		cur, u, used := t.cur, t.u, t.used
		if cur > dist[u][used] {
			continue
		}
		if u == n-1 && used == 1 {
			return cur
		}
		for _, ed := range g[u] {
			v, w := ed.to, int64(ed.w)

			nxt := cur + w
			if nxt < dist[v][used] {
				dist[v][used] = nxt
				heap.Push(&pq, state{nxt, v, used})
			}

			if used == 0 {
				nxt = cur
				if nxt < dist[v][1] {
					dist[v][1] = nxt
					heap.Push(&pq, state{nxt, v, 1})
				}
			}
		}
	}
	return dist[n-1][1]
}

type edge struct {
	to int
	w  int
}

type state struct {
	cur  int64
	u    int
	used int
}

type hp []state

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].cur < h[j].cur }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(x any)        { *h = append(*h, x.(state)) }
func (h *hp) Pop() (x any)      { a := *h; x = a[len(a)-1]; *h = a[:len(a)-1]; return }
```

#### TypeScript

```ts
function minCostExcludingMax(n: number, edges: number[][]): number {
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [u, v, w] of edges) {
        g[u].push([v, w]);
        g[v].push([u, w]);
    }

    const INF = Infinity;
    const dist: number[][] = Array.from({ length: n }, () => [INF, INF]);
    dist[0][0] = 0;

    const pq = new PriorityQueue<[number, number, number]>((a, b) =>
        a[0] === b[0] ? a[1] - b[1] : a[0] - b[0],
    );

    pq.enqueue([0, 0, 0]);

    while (pq.size() > 0) {
        const [cur, u, used] = pq.dequeue()!;
        if (cur > dist[u][used]) {
            continue;
        }
        if (u === n - 1 && used === 1) {
            return cur;
        }

        for (const [v, w] of g[u]) {
            const nxt1 = cur + w;
            if (nxt1 < dist[v][used]) {
                dist[v][used] = nxt1;
                pq.enqueue([nxt1, v, used]);
            }
            if (used === 0) {
                const nxt2 = cur;
                if (nxt2 < dist[v][1]) {
                    dist[v][1] = nxt2;
                    pq.enqueue([nxt2, v, 1]);
                }
            }
        }
    }

    return dist[n - 1][1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
