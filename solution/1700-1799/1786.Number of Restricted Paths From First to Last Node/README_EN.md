# [1786. Number of Restricted Paths From First to Last Node](https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node)

[中文文档](/solution/1700-1799/1786.Number%20of%20Restricted%20Paths%20From%20First%20to%20Last%20Node/README.md)

## Description

<p>There is an undirected weighted connected graph. You are given a positive integer <code>n</code> which denotes that the graph has <code>n</code> nodes labeled from <code>1</code> to <code>n</code>, and an array <code>edges</code> where each <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, weight<sub>i</sub>]</code> denotes that there is an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> with weight equal to <code>weight<sub>i</sub></code>.</p>

<p>A path from node <code>start</code> to node <code>end</code> is a sequence of nodes <code>[z<sub>0</sub>, z<sub>1</sub>,<sub> </sub>z<sub>2</sub>, ..., z<sub>k</sub>]</code> such that <code>z<sub>0 </sub>= start</code> and <code>z<sub>k</sub> = end</code> and there is an edge between <code>z<sub>i</sub></code> and <code>z<sub>i+1</sub></code> where <code>0 &lt;= i &lt;= k-1</code>.</p>

<p>The distance of a path is the sum of the weights on the edges of the path. Let <code>distanceToLastNode(x)</code> denote the shortest distance of a path between node <code>n</code> and node <code>x</code>. A <strong>restricted path</strong> is a path that also satisfies that <code>distanceToLastNode(z<sub>i</sub>) &gt; distanceToLastNode(z<sub>i+1</sub>)</code> where <code>0 &lt;= i &lt;= k-1</code>.</p>

<p>Return <em>the number of restricted paths from node</em> <code>1</code> <em>to node</em> <code>n</code>. Since that number may be too large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1786.Number%20of%20Restricted%20Paths%20From%20First%20to%20Last%20Node/images/restricted_paths_ex1.png" style="width: 351px; height: 341px;" />
<pre>
<strong>Input:</strong> n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Each circle contains the node number in black and its <code>distanceToLastNode value in blue. </code>The three restricted paths are:
1) 1 --&gt; 2 --&gt; 5
2) 1 --&gt; 2 --&gt; 3 --&gt; 5
3) 1 --&gt; 3 --&gt; 5
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1786.Number%20of%20Restricted%20Paths%20From%20First%20to%20Last%20Node/images/restricted_paths_ex22.png" style="width: 356px; height: 401px;" />
<pre>
<strong>Input:</strong> n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> Each circle contains the node number in black and its <code>distanceToLastNode value in blue. </code>The only restricted path is 1 --&gt; 3 --&gt; 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>n - 1 &lt;= edges.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
	<li><code>u<sub>i </sub>!= v<sub>i</sub></code></li>
	<li><code>1 &lt;= weight<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li>There is at most one edge between any two nodes.</li>
	<li>There is at least one path between any two nodes.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countRestrictedPaths(self, n: int, edges: List[List[int]]) -> int:
        @cache
        def dfs(i):
            if i == n:
                return 1
            ans = 0
            for j, _ in g[i]:
                if dist[i] > dist[j]:
                    ans = (ans + dfs(j)) % mod
            return ans

        g = defaultdict(list)
        for u, v, w in edges:
            g[u].append((v, w))
            g[v].append((u, w))
        q = [(0, n)]
        dist = [inf] * (n + 1)
        dist[n] = 0
        mod = 10**9 + 7
        while q:
            _, u = heappop(q)
            for v, w in g[u]:
                if dist[v] > dist[u] + w:
                    dist[v] = dist[u] + w
                    heappush(q, (dist[v], v))
        return dfs(1)
```

### **Java**

```java
class Solution {
    private static final int INF = Integer.MAX_VALUE;
    private static final int MOD = (int) 1e9 + 7;
    private List<int[]>[] g;
    private int[] dist;
    private int[] f;
    private int n;

    public int countRestrictedPaths(int n, int[][] edges) {
        this.n = n;
        g = new List[n + 1];
        for (int i = 0; i < g.length; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].add(new int[]{v, w});
            g[v].add(new int[]{u, w});
        }
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        q.offer(new int[]{0, n});
        dist = new int[n + 1];
        f = new int[n + 1];
        Arrays.fill(dist, INF);
        Arrays.fill(f, -1);
        dist[n] = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int u = p[1];
            for (int[] ne : g[u]) {
                int v = ne[0], w = ne[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    q.offer(new int[]{dist[v], v});
                }
            }
        }
        return dfs(1);
    }

    private int dfs(int i) {
        if (f[i] != -1) {
            return f[i];
        }
        if (i == n) {
            return 1;
        }
        int ans = 0;
        for (int[] ne : g[i]) {
            int j = ne[0];
            if (dist[i] > dist[j]) {
                ans = (ans + dfs(j)) % MOD;
            }
        }
        f[i] = ans;
        return ans;
    }
}
```

### **C++**

```cpp
using pii = pair<int, int>;

class Solution {
public:
    const int inf = INT_MAX;
    const int mod = 1e9 + 7;
    vector<vector<pii>> g;
    vector<int> dist;
    vector<int> f;
    int n;

    int countRestrictedPaths(int n, vector<vector<int>>& edges) {
        this->n = n;
        g.resize(n + 1);
        dist.assign(n + 1, inf);
        f.assign(n + 1, -1);
        dist[n] = 0;
        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].emplace_back(v, w);
            g[v].emplace_back(u, w);
        }
        priority_queue<pii, vector<pii>, greater<pii>> q;
        q.emplace(0, n);
        while (!q.empty()) {
            auto [_, u] = q.top();
            q.pop();
            for (auto [v, w] : g[u]) {
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    q.emplace(dist[v], v);
                }
            }
        }
        return dfs(1);
    }

    int dfs(int i) {
        if (f[i] != -1) return f[i];
        if (i == n) return 1;
        int ans = 0;
        for (auto [j, _] : g[i]) {
            if (dist[i] > dist[j]) {
                ans = (ans + dfs(j)) % mod;
            }
        }
        f[i] = ans;
        return ans;
    }
};
```

### **Go**

```go
const inf = math.MaxInt32
const mod = 1e9 + 7

type pair struct {
	first  int
	second int
}

var _ heap.Interface = (*pairs)(nil)

type pairs []pair

func (a pairs) Len() int { return len(a) }
func (a pairs) Less(i int, j int) bool {
	return a[i].first < a[j].first || a[i].first == a[j].first && a[i].second < a[j].second
}
func (a pairs) Swap(i int, j int)   { a[i], a[j] = a[j], a[i] }
func (a *pairs) Push(x interface{}) { *a = append(*a, x.(pair)) }
func (a *pairs) Pop() interface{}   { l := len(*a); t := (*a)[l-1]; *a = (*a)[:l-1]; return t }

func countRestrictedPaths(n int, edges [][]int) int {
	g := make([]pairs, n+1)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u] = append(g[u], pair{v, w})
		g[v] = append(g[v], pair{u, w})
	}
	dist := make([]int, n+1)
	f := make([]int, n+1)
	for i := range dist {
		dist[i] = inf
		f[i] = -1
	}
	dist[n] = 0
	h := make(pairs, 0)
	heap.Push(&h, pair{0, n})
	for len(h) > 0 {
		u := heap.Pop(&h).(pair).second
		for _, ne := range g[u] {
			v, w := ne.first, ne.second
			if dist[v] > dist[u]+w {
				dist[v] = dist[u] + w
				heap.Push(&h, pair{dist[v], v})
			}
		}
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if f[i] != -1 {
			return f[i]
		}
		if i == n {
			return 1
		}
		ans := 0
		for _, ne := range g[i] {
			j := ne.first
			if dist[i] > dist[j] {
				ans = (ans + dfs(j)) % mod
			}
		}
		f[i] = ans
		return ans
	}
	return dfs(1)
}
```

### **...**

```

```

<!-- tabs:end -->
