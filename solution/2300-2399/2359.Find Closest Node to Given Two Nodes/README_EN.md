# [2359. Find Closest Node to Given Two Nodes](https://leetcode.com/problems/find-closest-node-to-given-two-nodes)

[中文文档](/solution/2300-2399/2359.Find%20Closest%20Node%20to%20Given%20Two%20Nodes/README.md)

## Description

<p>You are given a <strong>directed</strong> graph of <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code>, where each node has <strong>at most one</strong> outgoing edge.</p>

<p>The graph is represented with a given <strong>0-indexed</strong> array <code>edges</code> of size <code>n</code>, indicating that there is a directed edge from node <code>i</code> to node <code>edges[i]</code>. If there is no outgoing edge from <code>i</code>, then <code>edges[i] == -1</code>.</p>

<p>You are also given two integers <code>node1</code> and <code>node2</code>.</p>

<p>Return <em>the <strong>index</strong> of the node that can be reached from both </em><code>node1</code><em> and </em><code>node2</code><em>, such that the <strong>maximum</strong> between the distance from </em><code>node1</code><em> to that node, and from </em><code>node2</code><em> to that node is <strong>minimized</strong></em>. If there are multiple answers, return the node with the <strong>smallest</strong> index, and if no possible answer exists, return <code>-1</code>.</p>

<p>Note that <code>edges</code> may contain cycles.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2359.Find%20Closest%20Node%20to%20Given%20Two%20Nodes/images/graph4drawio-2.png" style="width: 321px; height: 161px;" />
<pre>
<strong>Input:</strong> edges = [2,2,3,-1], node1 = 0, node2 = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> The distance from node 0 to node 2 is 1, and the distance from node 1 to node 2 is 1.
The maximum of those two distances is 1. It can be proven that we cannot get a node with a smaller maximum distance than 1, so we return node 2.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2359.Find%20Closest%20Node%20to%20Given%20Two%20Nodes/images/graph4drawio-4.png" style="width: 195px; height: 161px;" />
<pre>
<strong>Input:</strong> edges = [1,2,-1], node1 = 0, node2 = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> The distance from node 0 to node 2 is 2, and the distance from node 2 to itself is 0.
The maximum of those two distances is 2. It can be proven that we cannot get a node with a smaller maximum distance than 2, so we return node 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == edges.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-1 &lt;= edges[i] &lt; n</code></li>
	<li><code>edges[i] != i</code></li>
	<li><code>0 &lt;= node1, node2 &lt; n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
