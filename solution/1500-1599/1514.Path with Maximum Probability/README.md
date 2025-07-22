---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1514.Path%20with%20Maximum%20Probability/README.md
rating: 1846
source: 第 197 场周赛 Q3
tags:
    - 图
    - 数组
    - 最短路
    - 堆（优先队列）
---

<!-- problem:start -->

# [1514. 概率最大的路径](https://leetcode.cn/problems/path-with-maximum-probability)

[English Version](/solution/1500-1599/1514.Path%20with%20Maximum%20Probability/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由 <code>n</code> 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成，其中 <code>edges[i] = [a, b]</code> 表示连接节点 a 和 b 的一条无向边，且该边遍历成功的概率为 <code>succProb[i]</code> 。</p>

<p>指定两个节点分别作为起点 <code>start</code> 和终点 <code>end</code> ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。</p>

<p>如果不存在从 <code>start</code> 到 <code>end</code> 的路径，请 <strong>返回 0</strong> 。只要答案与标准答案的误差不超过 <strong>1e-5 </strong>，就会被视作正确答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1514.Path%20with%20Maximum%20Probability/images/1558_ex1.png" style="height: 186px; width: 187px;"></strong></p>

<pre><strong>输入：</strong>n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
<strong>输出：</strong>0.25000
<strong>解释：</strong>从起点到终点有两条路径，其中一条的成功概率为 0.2 ，而另一条为 0.5 * 0.5 = 0.25
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1514.Path%20with%20Maximum%20Probability/images/1558_ex2.png" style="height: 186px; width: 189px;"></strong></p>

<pre><strong>输入：</strong>n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
<strong>输出：</strong>0.30000
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1514.Path%20with%20Maximum%20Probability/images/1558_ex3.png" style="height: 191px; width: 215px;"></strong></p>

<pre><strong>输入：</strong>n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
<strong>输出：</strong>0.00000
<strong>解释：</strong>节点 0 和 节点 2 之间不存在路径
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10^4</code></li>
	<li><code>0 &lt;= start, end &lt; n</code></li>
	<li><code>start != end</code></li>
	<li><code>0 &lt;= a, b &lt; n</code></li>
	<li><code>a != b</code></li>
	<li><code>0 &lt;= succProb.length == edges.length &lt;= 2*10^4</code></li>
	<li><code>0 &lt;= succProb[i] &lt;= 1</code></li>
	<li>每两个节点之间最多有一条边</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：堆优化 Dijkstra 算法

我们可以使用 Dijkstra 算法求解最短路径，这里我们稍微修改一下，求解最大概率路径。

我们可以使用一个优先队列（大根堆） $\textit{pq}$ 来存储从起点到各个节点的概率以及节点编号。初始时我们将起点的概率设为 $1$，其余节点的概率设为 $0$，然后将起点加入到 $\textit{pq}$ 中。

在每一次的迭代中，我们取出 $\textit{pq}$ 中概率最大的节点 $a$，以及 $a$ 的概率 $w$。如果节点 $a$ 的概率已经大于 $w$，那么我们就可以跳过这个节点。否则我们遍历 $a$ 的所有邻接边 $(a, b)$。如果 $b$ 的概率小于 $a$ 的概率乘以 $(a, b)$ 的概率，那么我们就可以更新 $b$ 的概率，并将 $b$ 加入到 $\textit{pq}$ 中。

最终，我们可以得到从起点到终点的最大概率。

时间复杂度 $O(m \times \log m)$，空间复杂度 $O(m)$。其中 $m$ 为边的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProbability(
        self,
        n: int,
        edges: List[List[int]],
        succProb: List[float],
        start_node: int,
        end_node: int,
    ) -> float:
        g: List[List[Tuple[int, float]]] = [[] for _ in range(n)]
        for (a, b), p in zip(edges, succProb):
            g[a].append((b, p))
            g[b].append((a, p))
        pq = [(-1, start_node)]
        dist = [0] * n
        dist[start_node] = 1
        while pq:
            w, a = heappop(pq)
            w = -w
            if dist[a] > w:
                continue
            for b, p in g[a]:
                if (t := w * p) > dist[b]:
                    dist[b] = t
                    heappush(pq, (-t, b))
        return dist[end_node]
```

#### Java

```java
class Solution {
    public double maxProbability(
        int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        List<Pair<Integer, Double>>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < edges.length; ++i) {
            var e = edges[i];
            int a = e[0], b = e[1];
            double p = succProb[i];
            g[a].add(new Pair<>(b, p));
            g[b].add(new Pair<>(a, p));
        }
        double[] dist = new double[n];
        dist[start_node] = 1;
        PriorityQueue<Pair<Integer, Double>> pq
            = new PriorityQueue<>(Comparator.comparingDouble(p -> - p.getValue()));
        pq.offer(new Pair<>(start_node, 1.0));
        while (!pq.isEmpty()) {
            var p = pq.poll();
            int a = p.getKey();
            double w = p.getValue();
            if (dist[a] > w) {
                continue;
            }
            for (var e : g[a]) {
                int b = e.getKey();
                double pab = e.getValue();
                double wab = w * pab;
                if (wab > dist[b]) {
                    dist[b] = wab;
                    pq.offer(new Pair<>(b, wab));
                }
            }
        }
        return dist[end_node];
    }
}
```

#### C++

```cpp
class Solution {
public:
    double maxProbability(int n, vector<vector<int>>& edges, vector<double>& succProb, int start_node, int end_node) {
        using pdi = pair<double, int>;
        vector<pdi> g[n];
        for (int i = 0; i < edges.size(); ++i) {
            int a = edges[i][0], b = edges[i][1];
            double p = succProb[i];
            g[a].emplace_back(p, b);
            g[b].emplace_back(p, a);
        }
        vector<double> dist(n);
        dist[start_node] = 1;
        priority_queue<pdi> pq;
        pq.emplace(1, start_node);
        while (!pq.empty()) {
            auto [w, a] = pq.top();
            pq.pop();
            if (dist[a] > w) {
                continue;
            }
            for (auto [p, b] : g[a]) {
                auto nw = w * p;
                if (nw > dist[b]) {
                    dist[b] = nw;
                    pq.emplace(nw, b);
                }
            }
        }
        return dist[end_node];
    }
};
```

#### Go

```go
func maxProbability(n int, edges [][]int, succProb []float64, start_node int, end_node int) float64 {
	g := make([][]pair, n)
	for i, e := range edges {
		a, b := e[0], e[1]
		p := succProb[i]
		g[a] = append(g[a], pair{p, b})
		g[b] = append(g[b], pair{p, a})
	}
	pq := hp{{1, start_node}}
	dist := make([]float64, n)
	dist[start_node] = 1
	for len(pq) > 0 {
		p := heap.Pop(&pq).(pair)
		w, a := p.p, p.a
		if dist[a] > w {
			continue
		}
		for _, e := range g[a] {
			b, p := e.a, e.p
			if nw := w * p; nw > dist[b] {
				dist[b] = nw
				heap.Push(&pq, pair{nw, b})
			}
		}
	}
	return dist[end_node]
}

type pair struct {
	p float64
	a int
}
type hp []pair

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].p > h[j].p }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(x any)        { *h = append(*h, x.(pair)) }
func (h *hp) Pop() (x any)      { a := *h; x = a[len(a)-1]; *h = a[:len(a)-1]; return }
```

#### TypeScript

```ts
function maxProbability(
    n: number,
    edges: number[][],
    succProb: number[],
    start_node: number,
    end_node: number,
): number {
    const pq = new MaxPriorityQueue({ priority: v => v[0] });
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (let i = 0; i < edges.length; ++i) {
        const [a, b] = edges[i];
        g[a].push([b, succProb[i]]);
        g[b].push([a, succProb[i]]);
    }
    const dist = Array.from({ length: n }, () => 0);
    dist[start_node] = 1;
    pq.enqueue([1, start_node]);
    while (!pq.isEmpty()) {
        const [w, a] = pq.dequeue().element;
        if (dist[a] > w) {
            continue;
        }
        for (const [b, p] of g[a]) {
            const nw = w * p;
            if (nw > dist[b]) {
                dist[b] = nw;
                pq.enqueue([nw, b]);
            }
        }
    }
    return dist[end_node];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
