# [1786. 从第一个节点出发到最后一个节点的受限路径数](https://leetcode.cn/problems/number-of-restricted-paths-from-first-to-last-node)

[English Version](/solution/1700-1799/1786.Number%20of%20Restricted%20Paths%20From%20First%20to%20Last%20Node/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现有一个加权无向连通图。给你一个正整数 <code>n</code> ，表示图中有 <code>n</code> 个节点，并按从 <code>1</code> 到 <code>n</code> 给节点编号；另给你一个数组 <code>edges</code> ，其中每个 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, weight<sub>i</sub>]</code> 表示存在一条位于节点 <code>u<sub>i</sub></code> 和 <code>v<sub>i</sub></code> 之间的边，这条边的权重为 <code>weight<sub>i</sub></code> 。</p>

<p>从节点 <code>start</code> 出发到节点 <code>end</code> 的路径是一个形如 <code>[z<sub>0</sub>, z<sub>1</sub>,<sub> </sub>z<sub>2</sub>, ..., z<sub>k</sub>]</code> 的节点序列，满足 <code>z<sub>0 </sub>= start</code> 、<code>z<sub>k</sub> = end</code> 且在所有符合 <code>0 <= i <= k-1</code> 的节点 <code>z<sub>i</sub></code> 和 <code>z<sub>i+1</sub></code> 之间存在一条边。</p>

<p>路径的距离定义为这条路径上所有边的权重总和。用 <code>distanceToLastNode(x)</code> 表示节点 <code>n</code> 和 <code>x</code> 之间路径的最短距离。<strong>受限路径</strong> 为满足 <code>distanceToLastNode(z<sub>i</sub>) > distanceToLastNode(z<sub>i+1</sub>)</code> 的一条路径，其中 <code>0 <= i <= k-1</code> 。</p>

<p>返回从节点 <code>1</code> 出发到节点 <code>n</code> 的 <strong>受限路径数</strong> 。由于数字可能很大，请返回对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1786.Number%20of%20Restricted%20Paths%20From%20First%20to%20Last%20Node/images/restricted_paths_ex1.png" style="width: 351px; height: 341px;" />
<pre>
<strong>输入：</strong>n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
<strong>输出：</strong>3
<strong>解释：</strong>每个圆包含黑色的节点编号和蓝色的 distanceToLastNode 值。三条受限路径分别是：
1) 1 --> 2 --> 5
2) 1 --> 2 --> 3 --> 5
3) 1 --> 3 --> 5
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1786.Number%20of%20Restricted%20Paths%20From%20First%20to%20Last%20Node/images/restricted_paths_ex22.png" style="width: 356px; height: 401px;" />
<pre>
<strong>输入：</strong>n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
<strong>输出：</strong>1
<strong>解释：</strong>每个圆包含黑色的节点编号和蓝色的 distanceToLastNode 值。唯一一条受限路径是：1 --> 3 --> 7 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 2 * 10<sup>4</sup></code></li>
	<li><code>n - 1 <= edges.length <= 4 * 10<sup>4</sup></code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>1 <= u<sub>i</sub>, v<sub>i</sub> <= n</code></li>
	<li><code>u<sub>i </sub>!= v<sub>i</sub></code></li>
	<li><code>1 <= weight<sub>i</sub> <= 10<sup>5</sup></code></li>
	<li>任意两个节点之间至多存在一条边</li>
	<li>任意两个节点之间至少存在一条路径</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：堆优化 Dijkstra + 记忆化搜索**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

```python
class Solution:
    def countRestrictedPaths(self, n: int, edges: List[List[int]]) -> int:
        g = defaultdict(list)
        for u, v, w in edges:
            g[u].append((v, w))
            g[v].append((u, w))
        dist = [inf] * (n + 1)
        dist[n] = 0
        q = [(0, n)]
        mod = 10**9 + 7
        while q:
            _, u = heappop(q)
            for v, w in g[u]:
                if dist[v] > dist[u] + w:
                    dist[v] = dist[u] + w
                    heappush(q, (dist[v], v))
        arr = list(range(1, n + 1))
        arr.sort(key=lambda i: dist[i])
        f = [0] * (n + 1)
        f[n] = 1
        for i in arr:
            for j, _ in g[i]:
                if dist[i] > dist[j]:
                    f[i] = (f[i] + f[j]) % mod
        return f[1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
            g[u].add(new int[] {v, w});
            g[v].add(new int[] {u, w});
        }
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        q.offer(new int[] {0, n});
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
                    q.offer(new int[] {dist[v], v});
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

```java
class Solution {
    private static final int INF = Integer.MAX_VALUE;
    private static final int MOD = (int) 1e9 + 7;

    public int countRestrictedPaths(int n, int[][] edges) {
        List<int[]>[] g = new List[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            g[u].add(new int[] {v, w});
            g[v].add(new int[] {u, w});
        }
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        q.offer(new int[] {0, n});
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[n] = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int u = p[1];
            for (int[] ne : g[u]) {
                int v = ne[0], w = ne[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    q.offer(new int[] {dist[v], v});
                }
            }
        }
        int[] f = new int[n + 1];
        f[n] = 1;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = i + 1;
        }
        Arrays.sort(arr, (i, j) -> dist[i] - dist[j]);
        for (int i : arr) {
            for (int[] ne : g[i]) {
                int j = ne[0];
                if (dist[i] > dist[j]) {
                    f[i] = (f[i] + f[j]) % MOD;
                }
            }
        }
        return f[1];
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
