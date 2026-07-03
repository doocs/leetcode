---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3620.Network%20Recovery%20Pathways/README.md
rating: 1998
source: 第 161 场双周赛 Q3
tags:
    - 图
    - 拓扑排序
    - 数组
    - 二分查找
    - 动态规划
    - 最短路
    - 堆（优先队列）
---

<!-- problem:start -->

# [3620. 恢复网络路径](https://leetcode.cn/problems/network-recovery-pathways)

[English Version](/solution/3600-3699/3620.Network%20Recovery%20Pathways/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个包含 <code>n</code> 个节点（编号从 0 到 <code>n - 1</code>）的有向无环图。图由长度为 <code>m</code> 的二维数组 <code>edges</code> 表示，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, cost<sub>i</sub>]</code> 表示从节点 <code>u<sub>i</sub></code> 到节点 <code>v<sub>i</sub></code> 的单向通信，恢复成本为 <code>cost<sub>i</sub></code>。</p>

<p>一些节点可能处于离线状态。给定一个布尔数组 <code>online</code>，其中 <code>online[i] = true</code> 表示节点 <code>i</code> 在线。节点 0 和 <code>n - 1</code> 始终在线。</p>

<p>从 0 到 <code>n - 1</code> 的路径如果满足以下条件，那么它是&nbsp;<strong>有效&nbsp;</strong>的：</p>

<ul>
	<li>路径上的所有中间节点都在线。</li>
	<li>路径上所有边的总恢复成本不超过 <code>k</code>。</li>
</ul>

<p>对于每条有效路径，其&nbsp;<strong>分数&nbsp;</strong>定义为该路径上的最小边成本。</p>

<p>返回所有有效路径中的&nbsp;<strong>最大&nbsp;</strong>路径分数（即最大&nbsp;<strong>最小&nbsp;</strong>边成本）。如果没有有效路径，则返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">edges = [[0,1,5],[1,3,10],[0,2,3],[2,3,4]], online = [true,true,true,true], k = 10</span></p>

<p><strong>输出:</strong> <span class="example-io">3</span></p>

<p><strong>解释:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3620.Network%20Recovery%20Pathways/images/graph-10.png" style="width: 239px; height: 267px;" /></p>

<ul>
	<li>
	<p>图中有两条从节点 0 到节点 3 的可能路线：</p>

    <ol>
    	<li>
    	<p>路径 <code>0 → 1 → 3</code></p>

    	<ul>
    		<li>
    		<p>总成本 = <code>5 + 10 = 15</code>，超过了 k (<code>15 &gt; 10</code>)，因此此路径无效。</p>
    		</li>
    	</ul>
    	</li>
    	<li>
    	<p>路径 <code>0 → 2 → 3</code></p>

    	<ul>
    		<li>
    		<p>总成本 = <code>3 + 4 = 7 &lt;= k</code>，因此此路径有效。</p>
    		</li>
    		<li>
    		<p>此路径上的最小边成本为 <code>min(3, 4) = 3</code>。</p>
    		</li>
    	</ul>
    	</li>
    </ol>
    </li>
    <li>
    <p>没有其他有效路径。因此，所有有效路径分数中的最大值为 3。</p>
    </li>

</ul>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">edges = [[0,1,7],[1,4,5],[0,2,6],[2,3,6],[3,4,2],[2,4,6]], online = [true,true,true,false,true], k = 12</span></p>

<p><strong>输出:</strong> <span class="example-io">6</span></p>

<p><strong>解释:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3620.Network%20Recovery%20Pathways/images/graph-11.png" style="width: 343px; height: 194px;" /></p>

<ul>
	<li>
	<p>节点 3 离线，因此任何通过 3 的路径都是无效的。</p>
	</li>
	<li>
	<p>考虑从 0 到 4 的其余路线：</p>

    <ol>
    	<li>
    	<p>路径 <code>0 → 1 → 4</code></p>

    	<ul>
    		<li>
    		<p>总成本 = <code>7 + 5 = 12 &lt;= k</code>，因此此路径有效。</p>
    		</li>
    		<li>
    		<p>此路径上的最小边成本为 <code>min(7, 5) = 5</code>。</p>
    		</li>
    	</ul>
    	</li>
    	<li>
    	<p>路径 <code>0 → 2 → 3 → 4</code></p>

    	<ul>
    		<li>
    		<p>节点 3 离线，因此无论成本多少，此路径无效。</p>
    		</li>
    	</ul>
    	</li>
    	<li>
    	<p>路径 <code>0 → 2 → 4</code></p>

    	<ul>
    		<li>
    		<p>总成本 = <code>6 + 6 = 12 &lt;= k</code>，因此此路径有效。</p>
    		</li>
    		<li>
    		<p>此路径上的最小边成本为 <code>min(6, 6) = 6</code>。</p>
    		</li>
    	</ul>
    	</li>
    </ol>
    </li>
    <li>
    <p>在两条有效路径中，它们的分数分别为 5 和 6。因此，答案是 6。</p>
    </li>

</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == online.length</code></li>
	<li><code>2 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= m == edges.length &lt;= min(10<sup>5</sup>, n * (n - 1) / 2)</code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, cost<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>0 &lt;= cost<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 5 * 10<sup>13</sup></code></li>
	<li><code>online[i]</code> 是 <code>true</code> 或 <code>false</code>，且 <code>online[0]</code> 和 <code>online[n - 1]</code> 均为 <code>true</code>。</li>
	<li>给定的图是一个有向无环图。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找 + 堆优化 Dijkstra 算法

路径分数定义为路径上最小边权，求所有有效路径中的最大路径分数。对于给定的最小边权下界 $mid$，我们只保留边权不小于 $mid$ 的边，然后判断从节点 $0$ 到节点 $n - 1$ 是否存在总代价不超过 $k$ 的路径，这等价于在过滤后的图上用堆优化 Dijkstra 算法求最短路。

由于 $mid$ 越大，可用边越少，可行性单调不增，因此可以对 $mid$ 进行二分。预处理时去掉离线节点相关的边，$l$ 和 $r$ 分别为最小边权和最大边权。若最终 $check(l)$ 为真则返回 $l$，否则返回 $-1$。

时间复杂度 $O((n + m) \log n \log W)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是节点数和边数，$W$ 是边权的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMaxPathScore(
        self, edges: List[List[int]], online: List[bool], k: int
    ) -> int:
        def check(mid: int) -> int:
            dist = [inf] * n
            dist[0] = 0
            pq = [(0, 0)]
            while pq:
                d, u = heappop(pq)
                if d > k:
                    return False
                if u == n - 1:
                    return True
                if dist[u] < d:
                    continue
                for v, w in g[u]:
                    if w < mid:
                        continue
                    if dist[u] + w < dist[v]:
                        dist[v] = dist[u] + w
                        heappush(pq, (dist[v], v))
            return False

        n = len(online)
        g = [[] for _ in range(n)]
        l, r = inf, 0
        for (
            u,
            v,
            w,
        ) in edges:
            if not online[u] or not online[v]:
                continue
            g[u].append((v, w))
            l = min(l, w)
            r = max(r, w)

        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return l if check(l) else -1
```

#### Java

```java
class Solution {
    int n;
    List<int[]>[] g;
    long k;

    boolean check(int mid) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE / 4);
        dist[0] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[] {0, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];

            if (d > k) return false;
            if (u == n - 1) return true;
            if (dist[u] < d) continue;

            for (int[] e : g[u]) {
                int v = e[0], w = e[1];
                if (w < mid) continue;

                long nd = d + w;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.offer(new long[] {nd, v});
                }
            }
        }

        return false;
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        this.k = k;
        n = online.length;
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();

        int l = Integer.MAX_VALUE;
        int r = 0;

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            if (!online[u] || !online[v]) continue;

            g[u].add(new int[] {v, w});
            l = Math.min(l, w);
            r = Math.max(r, w);
        }

        while (l < r) {
            int mid = (l + r + 1) >>> 1;
            if (check(mid))
                l = mid;
            else
                r = mid - 1;
        }

        return check(l) ? l : -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMaxPathScore(vector<vector<int>>& edges, vector<bool>& online, long long k) {
        int n = online.size();
        vector<vector<pair<int, int>>> g(n);

        int l = INT_MAX, r = 0;

        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            if (!online[u] || !online[v]) continue;
            g[u].push_back({v, w});
            l = min(l, w);
            r = max(r, w);
        }

        auto check = [&](int mid) -> bool {
            vector<long long> dist(n, LLONG_MAX / 4);
            dist[0] = 0;

            using P = pair<long long, int>;
            priority_queue<P, vector<P>, greater<P>> pq;
            pq.push({0, 0});

            while (!pq.empty()) {
                auto [d, u] = pq.top();
                pq.pop();

                if (d > k) return false;
                if (u == n - 1) return true;
                if (dist[u] < d) continue;

                for (auto& ed : g[u]) {
                    int v = ed.first, w = ed.second;
                    if (w < mid) continue;

                    long long nd = d + w;
                    if (nd < dist[v]) {
                        dist[v] = nd;
                        pq.push({nd, v});
                    }
                }
            }
            return false;
        };

        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid))
                l = mid;
            else
                r = mid - 1;
        }

        return check(l) ? l : -1;
    }
};
```

#### Go

```go
type Item struct {
	d int
	u int
}

type H []Item

func (h H) Len() int            { return len(h) }
func (h H) Less(i, j int) bool  { return h[i].d < h[j].d }
func (h H) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *H) Push(x any)         { *h = append(*h, x.(Item)) }
func (h *H) Pop() any           { x := (*h)[len(*h)-1]; *h = (*h)[:len(*h)-1]; return x }

func findMaxPathScore(edges [][]int, online []bool, k int64) int {
	n := len(online)
	g := make([][][]int, n)

	l, r := int(^uint(0)>>1), 0

	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		if !online[u] || !online[v] {
			continue
		}
		g[u] = append(g[u], []int{v, w})
		if w < l {
			l = w
		}
		if w > r {
			r = w
		}
	}

	check := func(mid int) bool {
		const INF = int(^uint(0) >> 1)

		dist := make([]int, n)
		for i := range dist {
			dist[i] = INF
		}
		dist[0] = 0

		h := &H{}
		heap.Push(h, Item{0, 0})

		for h.Len() > 0 {
			cur := heap.Pop(h).(Item)
			d, u := cur.d, cur.u

			if int64(d) > k {
				return false
			}
			if u == n-1 {
				return true
			}
			if dist[u] < d {
				continue
			}

			for _, e := range g[u] {
				v, w := e[0], e[1]
				if w < mid {
					continue
				}
				nd := d + w
				if nd < dist[v] {
					dist[v] = nd
					heap.Push(h, Item{nd, v})
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

	if check(l) {
		return l
	}
	return -1
}
```

#### TypeScript

```ts
function findMaxPathScore(edges: number[][], online: boolean[], k: number): number {
    const n = online.length;
    const g: [number, number][][] = Array.from({ length: n }, () => []);

    let l = Number.MAX_SAFE_INTEGER;
    let r = 0;

    for (const [u, v, w] of edges) {
        if (!online[u] || !online[v]) continue;
        g[u].push([v, w]);
        l = Math.min(l, w);
        r = Math.max(r, w);
    }

    const check = (mid: number): boolean => {
        const INF = Number.MAX_SAFE_INTEGER / 2;
        const dist = new Array<number>(n).fill(INF);
        dist[0] = 0;

        const pq = new PriorityQueue<[number, number]>((a, b) => a[0] - b[0]);
        pq.enqueue([0, 0]);

        while (!pq.isEmpty()) {
            const [d, u] = pq.dequeue();

            if (d > k) return false;
            if (u === n - 1) return true;
            if (dist[u] < d) continue;

            for (const [v, w] of g[u]) {
                if (w < mid) continue;

                const nd = d + w;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.enqueue([nd, v]);
                }
            }
        }

        return false;
    };

    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) l = mid;
        else r = mid - 1;
    }

    return check(l) ? l : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
