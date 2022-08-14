# [2359. 找到离给定两个节点最近的节点](https://leetcode.cn/problems/find-closest-node-to-given-two-nodes)

[English Version](/solution/2300-2399/2359.Find%20Closest%20Node%20to%20Given%20Two%20Nodes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>n</code>&nbsp;个节点的 <strong>有向图</strong>&nbsp;，节点编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;，每个节点 <strong>至多</strong>&nbsp;有一条出边。</p>

<p>有向图用大小为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的数组&nbsp;<code>edges</code>&nbsp;表示，表示节点&nbsp;<code>i</code>&nbsp;有一条有向边指向&nbsp;<code>edges[i]</code>&nbsp;。如果节点&nbsp;<code>i</code>&nbsp;没有出边，那么&nbsp;<code>edges[i] == -1</code>&nbsp;。</p>

<p>同时给你两个节点&nbsp;<code>node1</code> 和&nbsp;<code>node2</code>&nbsp;。</p>

<p>请你返回一个从 <code>node1</code>&nbsp;和 <code>node2</code>&nbsp;都能到达节点的编号，使节点 <code>node1</code>&nbsp;和节点 <code>node2</code>&nbsp;到这个节点的距离 <b>较大值最小化</b>。如果有多个答案，请返回 <strong>最小</strong>&nbsp;的节点编号。如果答案不存在，返回 <code>-1</code>&nbsp;。</p>

<p>注意&nbsp;<code>edges</code>&nbsp;可能包含环。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2359.Find%20Closest%20Node%20to%20Given%20Two%20Nodes/images/graph4drawio-2.png" style="width: 321px; height: 161px;"></p>

<pre><b>输入：</b>edges = [2,2,3,-1], node1 = 0, node2 = 1
<b>输出：</b>2
<b>解释：</b>从节点 0 到节点 2 的距离为 1 ，从节点 1 到节点 2 的距离为 1 。
两个距离的较大值为 1 。我们无法得到一个比 1 更小的较大值，所以我们返回节点 2 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2359.Find%20Closest%20Node%20to%20Given%20Two%20Nodes/images/graph4drawio-4.png" style="width: 195px; height: 161px;"></p>

<pre><b>输入：</b>edges = [1,2,-1], node1 = 0, node2 = 2
<b>输出：</b>2
<b>解释：</b>节点 0 到节点 2 的距离为 2 ，节点 2 到它自己的距离为 0 。
两个距离的较大值为 2 。我们无法得到一个比 2 更小的较大值，所以我们返回节点 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == edges.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-1 &lt;= edges[i] &lt; n</code></li>
	<li><code>edges[i] != i</code></li>
	<li><code>0 &lt;= node1, node2 &lt; n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举公共点**

最短路问题。

枚举 $node1$，$node2$ 到某个公共点 $p$ 的距离较大值，求最小的距离即可。

最小距离可以用 $dijkstra$ 算法求得。

相似题目：[2203.得到要求路径的最小带权子图](/solution/2200-2299/2203.Minimum%20Weighted%20Subgraph%20With%20the%20Required%20Paths/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def closestMeetingNode(self, edges: List[int], node1: int, node2: int) -> int:
        def dijkstra(g, u):
            dist = [inf] * n
            dist[u] = 0
            q = [(0, u)]
            while q:
                d, u = heappop(q)
                if d > dist[u]:
                    continue
                for v in g[u]:
                    if dist[v] > dist[u] + 1:
                        dist[v] = dist[u] + 1
                        heappush(q, (dist[v], v))
            return dist

        g = defaultdict(list)
        n = len(edges)
        for i, v in enumerate(edges):
            if v != -1:
                g[i].append(v)
        d1 = dijkstra(g, node1)
        d2 = dijkstra(g, node2)
        d = inf
        ans = -1
        for i, (a, b) in enumerate(zip(d1, d2)):
            if d > max(a, b):
                d = max(a, b)
                ans = i
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int INF = 0x3f3f3f3f;

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; ++i) {
            if (edges[i] != -1) {
                g[i].add(edges[i]);
            }
        }
        int[] d1 = dijkstra(g, node1);
        int[] d2 = dijkstra(g, node2);
        int d = INF;
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            int t = Math.max(d1[i], d2[i]);
            if (d > t) {
                d = t;
                ans = i;
            }
        }
        return ans;
    }

    private int[] dijkstra(List<Integer>[] g, int u) {
        int n = g.length;
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[u] = 0;
        PriorityQueue<Pair<Integer, Integer>> q = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));
        q.offer(new Pair<>(0, u));
        while (!q.isEmpty()) {
            Pair<Integer, Integer> p = q.poll();
            int d = p.getKey();
            u = p.getValue();
            if (d > dist[u]) {
                continue;
            }
            for (int v : g[u]) {
                if (dist[v] > dist[u] + 1) {
                    dist[v] = dist[u] + 1;
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
