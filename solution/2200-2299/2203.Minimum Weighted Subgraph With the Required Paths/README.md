# [2203. 得到要求路径的最小带权子图](https://leetcode.cn/problems/minimum-weighted-subgraph-with-the-required-paths)

[English Version](/solution/2200-2299/2203.Minimum%20Weighted%20Subgraph%20With%20the%20Required%20Paths/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;，它表示一个 <strong>带权有向</strong> 图的节点数，节点编号为&nbsp;<code>0</code> 到&nbsp;<code>n - 1</code>&nbsp;。</p>

<p>同时给你一个二维整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [from<sub>i</sub>, to<sub>i</sub>, weight<sub>i</sub>]</code>&nbsp;，表示从&nbsp;<code>from<sub>i</sub></code>&nbsp;到&nbsp;<code>to<sub>i</sub></code>&nbsp;有一条边权为&nbsp;<code>weight<sub>i</sub></code>&nbsp;的 <strong>有向</strong> 边。</p>

<p>最后，给你三个 <strong>互不相同</strong>&nbsp;的整数&nbsp;<code>src1</code>&nbsp;，<code>src2</code>&nbsp;和&nbsp;<code>dest</code>&nbsp;，表示图中三个不同的点。</p>

<p>请你从图中选出一个 <b>边权和最小</b>&nbsp;的子图，使得从 <code>src1</code>&nbsp;和 <code>src2</code>&nbsp;出发，在这个子图中，都 <strong>可以</strong>&nbsp;到达 <code>dest</code>&nbsp;。如果这样的子图不存在，请返回 <code>-1</code>&nbsp;。</p>

<p><strong>子图</strong>&nbsp;中的点和边都应该属于原图的一部分。子图的边权和定义为它所包含的所有边的权值之和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2203.Minimum%20Weighted%20Subgraph%20With%20the%20Required%20Paths/images/example1drawio.png" style="width: 263px; height: 250px;" /></p>

<pre>
<b>输入：</b>n = 6, edges = [[0,2,2],[0,5,6],[1,0,3],[1,4,5],[2,1,1],[2,3,3],[2,3,4],[3,4,2],[4,5,1]], src1 = 0, src2 = 1, dest = 5
<b>输出：</b>9
<strong>解释：</strong>
上图为输入的图。
蓝色边为最优子图之一。
注意，子图 [[1,0,3],[0,5,6]] 也能得到最优解，但无法在满足所有限制的前提下，得到更优解。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2203.Minimum%20Weighted%20Subgraph%20With%20the%20Required%20Paths/images/example2-1drawio.png" style="width: 350px; height: 51px;" /></p>

<pre>
<b>输入：</b>n = 3, edges = [[0,1,1],[2,1,1]], src1 = 0, src2 = 1, dest = 2
<b>输出：</b>-1
<strong>解释：</strong>
上图为输入的图。
可以看到，不存在从节点 1 到节点 2 的路径，所以不存在任何子图满足所有限制。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= from<sub>i</sub>, to<sub>i</sub>, src1, src2, dest &lt;= n - 1</code></li>
	<li><code>from<sub>i</sub> != to<sub>i</sub></code></li>
	<li><code>src1</code>&nbsp;，<code>src2</code>&nbsp;和&nbsp;<code>dest</code>&nbsp;两两不同。</li>
	<li><code>1 &lt;= weight[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举三条最短路的交汇点**

最短路问题。

我们假设从 $src1$ 出发到 $dest$ 的一条最短路径为 $A$，从 $src2$ 出发到 $dest$ 的一条最短路径为 $B$。

$A$, $B$ 两条路径一定存在着公共点 $p$，因为 $dest$ 一定是其中一个公共点。那么问题可以转换为求以下三条路径和的最小值：

1. 从 $src1$ 到 $p$ 的最短路
1. 从 $src2$ 到 $p$ 的最短路
1. 从 $p$ 到 $dest$ 的最短路（这里我们可以将原图的所有边反向，然后转换为从 $dest$ 到 $p$ 的最短路）

我们进行三次 Dijkstra 算法，就可以求出 $src1$, $src2$, $dest$ 到其他点的最短路径。

公共点可以有多个，因此我们在 $[0,n)$ 范围内枚举公共点 $p$，找出边权之和最小的值即可。

时间复杂度 $O(mlogn)$，其中 m 表示数组 $edges$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumWeight(
        self, n: int, edges: List[List[int]], src1: int, src2: int, dest: int
    ) -> int:
        def dijkstra(g, u):
            dist = [inf] * n
            dist[u] = 0
            q = [(0, u)]
            while q:
                d, u = heappop(q)
                if d > dist[u]:
                    continue
                for v, w in g[u]:
                    if dist[v] > dist[u] + w:
                        dist[v] = dist[u] + w
                        heappush(q, (dist[v], v))
            return dist

        g = defaultdict(list)
        rg = defaultdict(list)
        for f, t, w in edges:
            g[f].append((t, w))
            rg[t].append((f, w))
        d1 = dijkstra(g, src1)
        d2 = dijkstra(g, src2)
        d3 = dijkstra(rg, dest)
        ans = min(sum(v) for v in zip(d1, d2, d3))
        return -1 if ans >= inf else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final Long INF = Long.MAX_VALUE;

    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        List<Pair<Integer, Long>>[] g = new List[n];
        List<Pair<Integer, Long>>[] rg = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
            rg[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int f = e[0], t = e[1];
            long w = e[2];
            g[f].add(new Pair<>(t, w));
            rg[t].add(new Pair<>(f, w));
        }
        long[] d1 = dijkstra(g, src1);
        long[] d2 = dijkstra(g, src2);
        long[] d3 = dijkstra(rg, dest);
        long ans = -1;
        for (int i = 0; i < n; ++i) {
            if (d1[i] == INF || d2[i] == INF || d3[i] == INF) {
                continue;
            }
            long t = d1[i] + d2[i] + d3[i];
            if (ans == -1 || ans > t) {
                ans = t;
            }
        }
        return ans;
    }

    private long[] dijkstra(List<Pair<Integer, Long>>[] g, int u) {
        int n = g.length;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[u] = 0;
        PriorityQueue<Pair<Long, Integer>> q = new PriorityQueue<>(Comparator.comparingLong(Pair::getKey));
        q.offer(new Pair<>(0L, u));
        while (!q.isEmpty()) {
            Pair<Long, Integer> p = q.poll();
            long d = p.getKey();
            u = p.getValue();
            if (d > dist[u]) {
                continue;
            }
            for (Pair<Integer, Long> e : g[u]) {
                int v = e.getKey();
                long w = e.getValue();
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    q.offer(new Pair<>(dist[v], v));
                }
            }
        }
        return dist;
    }
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
