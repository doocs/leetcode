# [3123. 最短路径中的边](https://leetcode.cn/problems/find-edges-in-shortest-paths)

[English Version](/solution/3100-3199/3123.Find%20Edges%20in%20Shortest%20Paths/README_EN.md)

<!-- tags:深度优先搜索,广度优先搜索,图,最短路,堆（优先队列） -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>n</code>&nbsp;个节点的无向带权图，节点编号为 <code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;。图中总共有 <code>m</code>&nbsp;条边，用二维数组&nbsp;<code>edges</code>&nbsp;表示，其中&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>, w<sub>i</sub>]</code>&nbsp;表示节点 <code>a<sub>i</sub></code> 和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条边权为&nbsp;<code>w<sub>i</sub></code>&nbsp;的边。</p>

<p>对于节点 <code>0</code>&nbsp;为出发点，节点 <code>n - 1</code>&nbsp;为结束点的所有最短路，你需要返回一个长度为 <code>m</code>&nbsp;的 <strong>boolean</strong>&nbsp;数组&nbsp;<code>answer</code>&nbsp;，如果&nbsp;<code>edges[i]</code>&nbsp;<strong>至少</strong>&nbsp;在其中一条最短路上，那么&nbsp;<code>answer[i]</code>&nbsp;为&nbsp;<code>true</code>&nbsp;，否则&nbsp;<code>answer[i]</code>&nbsp;为&nbsp;<code>false</code>&nbsp;。</p>

<p>请你返回数组&nbsp;<code>answer</code>&nbsp;。</p>

<p><b>注意</b>，图可能不连通。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3123.Find%20Edges%20in%20Shortest%20Paths/images/graph35drawio-1.png" style="height: 129px; width: 250px;" /></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 6, edges = [[0,1,4],[0,2,1],[1,3,2],[1,4,3],[1,5,1],[2,3,1],[3,5,3],[4,5,2]]</span></p>

<p><span class="example-io"><b>输出：</b>[true,true,true,false,true,true,true,false]</span></p>

<p><strong>解释：</strong></p>

<p>以下为节点 0 出发到达节点 5 的 <strong>所有</strong>&nbsp;最短路：</p>

<ul>
	<li>路径&nbsp;<code>0 -&gt; 1 -&gt; 5</code>&nbsp;：边权和为&nbsp;<code>4 + 1 = 5</code>&nbsp;。</li>
	<li>路径&nbsp;<code>0 -&gt; 2 -&gt; 3 -&gt; 5</code>&nbsp;：边权和为&nbsp;<code>1 + 1 + 3 = 5</code>&nbsp;。</li>
	<li>路径&nbsp;<code>0 -&gt; 2 -&gt; 3 -&gt; 1 -&gt; 5</code>&nbsp;：边权和为&nbsp;<code>1 + 1 + 2 + 1 = 5</code>&nbsp;。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3123.Find%20Edges%20in%20Shortest%20Paths/images/graphhhh.png" style="width: 185px; height: 136px;" /></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 4, edges = [[2,0,1],[0,1,1],[0,3,4],[3,2,2]]</span></p>

<p><span class="example-io"><b>输出：</b>[true,false,false,true]</span></p>

<p><strong>解释：</strong></p>

<p>只有一条从节点 0 出发到达节点 3 的最短路&nbsp;<code>0 -&gt; 2 -&gt; 3</code>&nbsp;，边权和为&nbsp;<code>1 + 2 = 3</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>m == edges.length</code></li>
	<li><code>1 &lt;= m &lt;= min(5 * 10<sup>4</sup>, n * (n - 1) / 2)</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>1 &lt;= w<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li>图中没有重边。</li>
</ul>

## 解法

### 方法一：堆优化的 Dijkstra

我们先创建一个邻接表 $g$，用于存储图的边。然后创建一个数组 $dist$，用于存储从节点 $0$ 到其他节点的最短距离。初始化 $dist[0] = 0$，其余节点的距离初始化为无穷大。

然后，我们使用 Dijkstra 算法计算从节点 $0$ 到其他节点的最短距离。具体步骤如下：

1. 创建一个优先队列 $q$，用于存储节点的距离和节点编号，初始时将节点 $0$ 加入队列，距离为 $0$。
2. 从队列中取出一个节点 $a$，如果 $a$ 的距离 $da$ 大于 $dist[a]$，说明 $a$ 已经被更新过了，直接跳过。
3. 遍历节点 $a$ 的所有邻居节点 $b$，如果 $dist[b] > dist[a] + w$，则更新 $dist[b] = dist[a] + w$，并将节点 $b$ 加入队列。
4. 重复步骤 2 和步骤 3，直到队列为空。

接着，我们创建一个长度为 $m$ 的答案数组 $ans$，初始时所有元素都为 $false$。如果 $dist[n - 1]$ 为无穷大，说明节点 $0$ 无法到达节点 $n - 1$，直接返回 $ans$。否则，我们从节点 $n - 1$ 开始，遍历所有的边，如果边 $(a, b, i)$ 满足 $dist[a] = dist[b] + w$，则将 $ans[i]$ 置为 $true$，并将节点 $b$ 加入队列。

最后，返回答案即可。

时间复杂度 $O(m \times \log m)$，空间复杂度 $O(n + m)$，其中 $n$ 和 $m$ 分别为节点数和边数。

<!-- tabs:start -->

```python
class Solution:
    def findAnswer(self, n: int, edges: List[List[int]]) -> List[bool]:
        g = defaultdict(list)
        for i, (a, b, w) in enumerate(edges):
            g[a].append((b, w, i))
            g[b].append((a, w, i))
        dist = [inf] * n
        dist[0] = 0
        q = [(0, 0)]
        while q:
            da, a = heappop(q)
            if da > dist[a]:
                continue
            for b, w, _ in g[a]:
                if dist[b] > dist[a] + w:
                    dist[b] = dist[a] + w
                    heappush(q, (dist[b], b))
        m = len(edges)
        ans = [False] * m
        if dist[n - 1] == inf:
            return ans
        q = deque([n - 1])
        while q:
            a = q.popleft()
            for b, w, i in g[a]:
                if dist[a] == dist[b] + w:
                    ans[i] = True
                    q.append(b)
        return ans
```

```java
class Solution {
    public boolean[] findAnswer(int n, int[][] edges) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        int m = edges.length;
        for (int i = 0; i < m; ++i) {
            int a = edges[i][0], b = edges[i][1], w = edges[i][2];
            g[a].add(new int[] {b, w, i});
            g[b].add(new int[] {a, w, i});
        }
        int[] dist = new int[n];
        final int inf = 1 << 30;
        Arrays.fill(dist, inf);
        dist[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] {0, 0});
        while (!pq.isEmpty()) {
            var p = pq.poll();
            int da = p[0], a = p[1];
            if (da > dist[a]) {
                continue;
            }
            for (var e : g[a]) {
                int b = e[0], w = e[1];
                if (dist[b] > dist[a] + w) {
                    dist[b] = dist[a] + w;
                    pq.offer(new int[] {dist[b], b});
                }
            }
        }
        boolean[] ans = new boolean[m];
        if (dist[n - 1] == inf) {
            return ans;
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(n - 1);
        while (!q.isEmpty()) {
            int a = q.poll();
            for (var e : g[a]) {
                int b = e[0], w = e[1], i = e[2];
                if (dist[a] == dist[b] + w) {
                    ans[i] = true;
                    q.offer(b);
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<bool> findAnswer(int n, vector<vector<int>>& edges) {
        vector<vector<array<int, 3>>> g(n);
        int m = edges.size();
        for (int i = 0; i < m; ++i) {
            auto e = edges[i];
            int a = e[0], b = e[1], w = e[2];
            g[a].push_back({b, w, i});
            g[b].push_back({a, w, i});
        }
        const int inf = 1 << 30;
        vector<int> dist(n, inf);
        dist[0] = 0;

        using pii = pair<int, int>;
        priority_queue<pii, vector<pii>, greater<pii>> pq;
        pq.push({0, 0});

        while (!pq.empty()) {
            auto [da, a] = pq.top();
            pq.pop();
            if (da > dist[a]) {
                continue;
            }

            for (auto [b, w, _] : g[a]) {
                if (dist[b] > dist[a] + w) {
                    dist[b] = dist[a] + w;
                    pq.push({dist[b], b});
                }
            }
        }
        vector<bool> ans(m);
        if (dist[n - 1] == inf) {
            return ans;
        }
        queue<int> q{{n - 1}};
        while (!q.empty()) {
            int a = q.front();
            q.pop();
            for (auto [b, w, i] : g[a]) {
                if (dist[a] == dist[b] + w) {
                    ans[i] = true;
                    q.push(b);
                }
            }
        }
        return ans;
    }
};
```

```go
func findAnswer(n int, edges [][]int) []bool {
	g := make([][][3]int, n)
	for i, e := range edges {
		a, b, w := e[0], e[1], e[2]
		g[a] = append(g[a], [3]int{b, w, i})
		g[b] = append(g[b], [3]int{a, w, i})
	}
	dist := make([]int, n)
	const inf int = 1 << 30
	for i := range dist {
		dist[i] = inf
	}
	dist[0] = 0
	pq := hp{{0, 0}}
	for len(pq) > 0 {
		p := heap.Pop(&pq).(pair)
		da, a := p.dis, p.u
		if da > dist[a] {
			continue
		}
		for _, e := range g[a] {
			b, w := e[0], e[1]
			if dist[b] > dist[a]+w {
				dist[b] = dist[a] + w
				heap.Push(&pq, pair{dist[b], b})
			}
		}
	}
	ans := make([]bool, len(edges))
	if dist[n-1] == inf {
		return ans
	}
	q := []int{n - 1}
	for len(q) > 0 {
		a := q[0]
		q = q[1:]
		for _, e := range g[a] {
			b, w, i := e[0], e[1], e[2]
			if dist[a] == dist[b]+w {
				ans[i] = true
				q = append(q, b)
			}
		}
	}
	return ans
}

type pair struct{ dis, u int }
type hp []pair

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].dis < h[j].dis }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

<!-- tabs:end -->

<!-- end -->
