---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2714.Find%20Shortest%20Path%20with%20K%20Hops/README.md
tags:
    - 图
    - 最短路
    - 堆（优先队列）
---

<!-- problem:start -->

# [2714. 找到最短路径的 K 次跨越 🔒](https://leetcode.cn/problems/find-shortest-path-with-k-hops)

[English Version](/solution/2700-2799/2714.Find%20Shortest%20Path%20with%20K%20Hops/README_EN.md)

## 题目描述

<!-- description:start -->

<p>现给定一个正整数 n ，它表示一个<strong>&nbsp;索引从 0 开始的无向带权连接图</strong> 的节点数，以及一个&nbsp;<strong>索引从 0 开始的二维数组&nbsp;</strong><code>edges</code> ，其中 <code>edges[i] = [ui, vi, wi]</code> 表示节点 <code>ui</code> 和 <code>vi</code> 之间存在权重为 <code>wi</code> 的边。</p>

<p>还给定两个节点 <code>s</code> 和 <code>d</code> ，以及一个正整数 <code>k</code> ，你的任务是找到从 s 到 d 的 <strong>最短 </strong>路径，但你可以 <strong>最多</strong> 跨越 <code>k</code> 条边。换句话说，将 <strong>最多</strong> <code>k</code> 条边的权重设为 <code>0</code>，然后找到从 <code>s</code> 到 <code>d</code> 的 <strong>最短</strong> 路径。</p>

<p>返回满足给定条件的从 <code>s</code> 到 <code>d</code> 的 <strong>最短</strong> 路径的长度。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>n = 4, edges = [[0,1,4],[0,2,2],[2,3,6]], s = 1, d = 3, k = 2
<b>输出：</b>2
<b>解释：</b>在这个例子中，只有一条从节点1（绿色节点）到节点3（红色节点）的路径，即（1-&gt;0-&gt;2-&gt;3），其长度为4 + 2 + 6 = 12。现在我们可以将两条边的权重设为 0，即将蓝色边的权重设为 0，那么路径的长度就变为 0 + 2 + 0 = 2。可以证明 2 是我们在给定条件下能够达到的最小路径长度。
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2714.Find%20Shortest%20Path%20with%20K%20Hops/images/1.jpg" style="width: 170px; height: 171px;" /></p>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>n = 7, edges = [[3,1,9],[3,2,4],[4,0,9],[0,5,6],[3,6,2],[6,0,4],[1,2,4]], s = 4, d = 1, k = 2
<b>输出：</b>6
<b>解释：</b>在这个例子中，有两条从节点4（绿色节点）到节点1（红色节点）的路径，分别是（4-&gt;0-&gt;6-&gt;3-&gt;2-&gt;1）和（4-&gt;0-&gt;6-&gt;3-&gt;1）。第一条路径的长度为 9 + 4 + 2 + 4 + 4 = 23，第二条路径的长度为 9 + 4 + 2 + 9 = 24。现在，如果我们将蓝色边的权重设为 0，那么最短路径的长度就变为 0 + 4 + 2 + 0 = 6。可以证明 6 是我们在给定条件下能够达到的最小路径长度。
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2714.Find%20Shortest%20Path%20with%20K%20Hops/images/2.jpg" style="width: 400px; height: 171px;" /></p>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>n = 5, edges = [[0,4,2],[0,1,3],[0,2,1],[2,1,4],[1,3,4],[3,4,7]], s = 2, d = 3, k = 1
<b>输出：</b>3
<b>解释：</b>在这个例子中，从节点2（绿色节点）到节点3（红色节点）有4条路径，分别是（2-&gt;1-&gt;3）、（2-&gt;0-&gt;1-&gt;3）、（2-&gt;1-&gt;0-&gt;4-&gt;3）和（2-&gt;0-&gt;4-&gt;3）。前两条路径的长度为4 + 4 = 1 + 3 + 4 = 8，第三条路径的长度为4 + 3 + 2 + 7 = 16，最后一条路径的长度为1 + 2 + 7 = 10。现在，如果我们将蓝色边的权重设为 0，那么最短路径的长度就为1 + 2 + 0 = 3。可以证明在给定条件下，3 是我们能够达到的最小路径长度。
</pre>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2714.Find%20Shortest%20Path%20with%20K%20Hops/images/3.jpg" style="width: 300px; height: 296px;" /></p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 500</code></li>
	<li><code>n - 1 &lt;= edges.length &lt;= n * (n - 1) / 2</code></li>
	<li><code>edges[i].length = 3</code></li>
	<li><code>0 &lt;= edges[i][0], edges[i][1] &lt;= n - 1</code></li>
	<li><code>1 &lt;= edges[i][2] &lt;=&nbsp;10<sup>6</sup></code></li>
	<li><code>0 &lt;= s, d, k&nbsp;&lt;= n - 1</code></li>
	<li><code>s != d</code></li>
	<li>输入的生成确保图是 <strong>连通</strong> 的，并且没有 <strong>重复的边</strong> 或 <strong>自环</strong>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：Dijkstra 算法

我们先根据给定的边构造出图 $g$，其中 $g[u]$ 表示节点 $u$ 的所有邻居节点以及对应的边权重。

然后我们使用 Dijkstra 算法求出从节点 $s$ 到节点 $d$ 的最短路径，但是在这里我们需要对 Dijkstra 算法进行一些修改：

-   我们需要记录每个节点 $u$ 到节点 $d$ 的最短路径长度，但是由于我们可以最多跨越 $k$ 条边，所以我们需要记录每个节点 $u$ 到节点 $d$ 的最短路径长度，以及跨越的边数 $t$，即 $dist[u][t]$ 表示从节点 $u$ 到节点 $d$ 的最短路径长度，且跨越的边数为 $t$。
-   我们需要使用优先队列来维护当前的最短路径，但是由于我们需要记录跨越的边数，所以我们需要使用三元组 $(dis, u, t)$ 来表示当前的最短路径，其中 $dis$ 表示当前的最短路径长度，而 $u$ 和 $t$ 分别表示当前的节点和跨越的边数。

最后我们只需要返回 $dist[d][0..k]$ 中的最小值即可。

时间复杂度 $O(n^2 \times \log n)$，空间复杂度 $O(n \times k)$。其中 $n$ 表示节点数，而 $k$ 表示最多跨越的边数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def shortestPathWithHops(
        self, n: int, edges: List[List[int]], s: int, d: int, k: int
    ) -> int:
        g = [[] for _ in range(n)]
        for u, v, w in edges:
            g[u].append((v, w))
            g[v].append((u, w))
        dist = [[inf] * (k + 1) for _ in range(n)]
        dist[s][0] = 0
        pq = [(0, s, 0)]
        while pq:
            dis, u, t = heappop(pq)
            for v, w in g[u]:
                if t + 1 <= k and dist[v][t + 1] > dis:
                    dist[v][t + 1] = dis
                    heappush(pq, (dis, v, t + 1))
                if dist[v][t] > dis + w:
                    dist[v][t] = dis + w
                    heappush(pq, (dis + w, v, t))
        return int(min(dist[d]))
```

#### Java

```java
class Solution {
    public int shortestPathWithHops(int n, int[][] edges, int s, int d, int k) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].add(new int[] {v, w});
            g[v].add(new int[] {u, w});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] {0, s, 0});
        int[][] dist = new int[n][k + 1];
        final int inf = 1 << 30;
        for (int[] e : dist) {
            Arrays.fill(e, inf);
        }
        dist[s][0] = 0;
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int dis = p[0], u = p[1], t = p[2];
            for (int[] e : g[u]) {
                int v = e[0], w = e[1];
                if (t + 1 <= k && dist[v][t + 1] > dis) {
                    dist[v][t + 1] = dis;
                    pq.offer(new int[] {dis, v, t + 1});
                }
                if (dist[v][t] > dis + w) {
                    dist[v][t] = dis + w;
                    pq.offer(new int[] {dis + w, v, t});
                }
            }
        }
        int ans = inf;
        for (int i = 0; i <= k; ++i) {
            ans = Math.min(ans, dist[d][i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int shortestPathWithHops(int n, vector<vector<int>>& edges, int s, int d, int k) {
        vector<pair<int, int>> g[n];
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].emplace_back(v, w);
            g[v].emplace_back(u, w);
        }
        priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<tuple<int, int, int>>> pq;
        pq.emplace(0, s, 0);
        int dist[n][k + 1];
        memset(dist, 0x3f, sizeof(dist));
        dist[s][0] = 0;
        while (!pq.empty()) {
            auto [dis, u, t] = pq.top();
            pq.pop();
            for (auto [v, w] : g[u]) {
                if (t + 1 <= k && dist[v][t + 1] > dis) {
                    dist[v][t + 1] = dis;
                    pq.emplace(dis, v, t + 1);
                }
                if (dist[v][t] > dis + w) {
                    dist[v][t] = dis + w;
                    pq.emplace(dis + w, v, t);
                }
            }
        }
        return *min_element(dist[d], dist[d] + k + 1);
    }
};
```

#### Go

```go
func shortestPathWithHops(n int, edges [][]int, s int, d int, k int) int {
	g := make([][][2]int, n)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u] = append(g[u], [2]int{v, w})
		g[v] = append(g[v], [2]int{u, w})
	}
	pq := hp{{0, s, 0}}
	dist := make([][]int, n)
	for i := range dist {
		dist[i] = make([]int, k+1)
		for j := range dist[i] {
			dist[i][j] = math.MaxInt32
		}
	}
	dist[s][0] = 0
	for len(pq) > 0 {
		p := heap.Pop(&pq).(tuple)
		dis, u, t := p.dis, p.u, p.t
		for _, e := range g[u] {
			v, w := e[0], e[1]
			if t+1 <= k && dist[v][t+1] > dis {
				dist[v][t+1] = dis
				heap.Push(&pq, tuple{dis, v, t + 1})
			}
			if dist[v][t] > dis+w {
				dist[v][t] = dis + w
				heap.Push(&pq, tuple{dis + w, v, t})
			}
		}
	}
	return slices.Min(dist[d])
}

type tuple struct{ dis, u, t int }
type hp []tuple

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].dis < h[j].dis }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
