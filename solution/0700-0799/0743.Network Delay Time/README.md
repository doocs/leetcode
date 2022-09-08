# [743. 网络延迟时间](https://leetcode.cn/problems/network-delay-time)

[English Version](/solution/0700-0799/0743.Network%20Delay%20Time/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code> 个网络节点，标记为&nbsp;<code>1</code>&nbsp;到 <code>n</code>。</p>

<p>给你一个列表&nbsp;<code>times</code>，表示信号经过 <strong>有向</strong> 边的传递时间。&nbsp;<code>times[i] = (u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>)</code>，其中&nbsp;<code>u<sub>i</sub></code>&nbsp;是源节点，<code>v<sub>i</sub></code>&nbsp;是目标节点， <code>w<sub>i</sub></code>&nbsp;是一个信号从源节点传递到目标节点的时间。</p>

<p>现在，从某个节点&nbsp;<code>K</code>&nbsp;发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回&nbsp;<code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0743.Network%20Delay%20Time/images/931_example_1.png" style="height: 220px; width: 200px;" /></p>

<pre>
<strong>输入：</strong>times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>times = [[1,2,1]], n = 2, k = 1
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>times = [[1,2,1]], n = 2, k = 2
<strong>输出：</strong>-1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= times.length &lt;= 6000</code></li>
	<li><code>times[i].length == 3</code></li>
	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>0 &lt;= w<sub>i</sub> &lt;= 100</code></li>
	<li>所有 <code>(u<sub>i</sub>, v<sub>i</sub>)</code> 对都 <strong>互不相同</strong>（即，不含重复边）</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

设 n 表示点数，m 表示边数。

**方法一：朴素 Dijkstra 算法**

时间复杂度 $O(n^2+m)$。

**方法二：堆优化 Dijkstra 算法**

时间复杂度 $O(m\log n)$。

**方法三：Bellman Ford 算法**

时间复杂度 $O(nm)$。

**方法四：SPFA 算法**

时间复杂度，平均情况下 $O(m)$，最坏情况下 $O(nm)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

朴素 Dijkstra 算法：

```python
class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        INF = 0x3F3F
        dist = [INF] * n
        vis = [False] * n
        g = [[INF] * n for _ in range(n)]
        for u, v, w in times:
            g[u - 1][v - 1] = w
        dist[k - 1] = 0
        for _ in range(n):
            t = -1
            for j in range(n):
                if not vis[j] and (t == -1 or dist[t] > dist[j]):
                    t = j
            vis[t] = True
            for j in range(n):
                dist[j] = min(dist[j], dist[t] + g[t][j])
        ans = max(dist)
        return -1 if ans == INF else ans
```

堆优化 Dijkstra 算法：

```python
class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        INF = 0x3f3f
        g = defaultdict(list)
        for u, v, w in times:
            g[u - 1].append((v - 1, w))
        dist = [INF] * n
        dist[k - 1] = 0
        q = [(0, k - 1)]
        while q:
            _, u = heappop(q)
            for v, w in g[u]:
                if dist[v] > dist[u] + w:
                    dist[v] = dist[u] + w
                    heappush(q, (dist[v], v))
        ans = max(dist)
        return -1 if ans == INF else ans
```

Bellman Ford 算法：

```python
class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        INF = 0x3f3f
        dist = [INF] * n
        dist[k - 1] = 0
        for _ in range(n):
            backup = dist[:]
            for u, v, w in times:
                dist[v - 1] = min(dist[v - 1], dist[u - 1] + w)
        ans = max(dist)
        return -1 if ans == INF else ans
```

SPFA 算法：

```python
class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        INF = 0x3f3f
        dist = [INF] * n
        vis = [False] * n
        g = defaultdict(list)
        for u, v, w in times:
            g[u - 1].append((v - 1, w))
        k -= 1
        dist[k] = 0
        q = deque([k])
        vis[k] = True
        while q:
            u = q.popleft()
            vis[u] = False
            for v, w in g[u]:
                if dist[v] > dist[u] + w:
                    dist[v] = dist[u] + w
                    if not vis[v]:
                        q.append(v)
                        vis[v] = True
        ans = max(dist)
        return -1 if ans == INF else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

朴素 Dijkstra 算法：

```java
class Solution {
    private static final int INF = 0x3f3f;

    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] g = new int[n][n];
        int[] dist = new int[n];
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            dist[i] = INF;
            Arrays.fill(g[i], INF);
        }
        for (int[] t : times) {
            g[t[0] - 1][t[1] - 1] = t[2];
        }
        dist[k - 1] = 0;
        for (int i = 0; i < n; ++i) {
            int t = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (t == -1 || dist[t] > dist[j])) {
                    t = j;
                }
            }
            vis[t] = true;
            for (int j = 0; j < n; ++j) {
                dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
            }
        }
        int ans = 0;
        for (int d : dist) {
            ans = Math.max(ans, d);
        }
        return ans == INF ? -1 : ans;
    }
}
```

堆优化 Dijkstra 算法：

```java
class Solution {
    private static final int INF = 0x3f3f;

    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] g = new List[n];
        int[] dist = new int[n];
        for (int i = 0; i < n; ++i) {
            dist[i] = INF;
            g[i] = new ArrayList<>();
        }
        for (int[] t : times) {
            g[t[0] - 1].add(new int[]{t[1] - 1, t[2]});
        }
        dist[k - 1] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        q.offer(new int[]{0, k - 1});
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
        int ans = 0;
        for (int d : dist) {
            ans = Math.max(ans, d);
        }
        return ans == INF ? -1 : ans;
    }
}
```

Bellman Ford 算法：

```java
class Solution {
    private static final int INF = 0x3f3f;

    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dist = new int[n];
        int[] backup = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        for (int i = 0; i < n; ++i) {
            System.arraycopy(dist, 0, backup, 0, n);
            for (int[] t : times) {
                int u = t[0] - 1, v = t[1] - 1, w = t[2];
                dist[v] = Math.min(dist[v], backup[u] + w);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, dist[i]);
        }
        return ans == INF ? -1 : ans;
    }
}
```

SPFA 算法：

```java
class Solution {
    private static final int INF = 0x3f3f;

    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dist = new int[n];
        boolean[] vis = new boolean[n];
        List<int[]>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            dist[i] = INF;
            g[i] = new ArrayList<>();
        }
        for (int[] t : times) {
            int u = t[0] - 1, v = t[1] - 1, w = t[2];
            g[u].add(new int[] {v, w});
        }
        --k;
        dist[k] = 0;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(k);
        vis[k] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            vis[u] = false;
            for (int[] ne : g[u]) {
                int v = ne[0], w = ne[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    if (!vis[v]) {
                        q.offer(v);
                        vis[v] = true;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, dist[i]);
        }
        return ans == INF ? -1 : ans;
    }
}
```

### **Go**

朴素 Dijkstra 算法：

```go
func networkDelayTime(times [][]int, n int, k int) int {
	const inf = 0x3f3f
	dist := make([]int, n)
	vis := make([]bool, n)
	g := make([][]int, n)
	for i := range dist {
		dist[i] = inf
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = inf
		}
	}
	for _, t := range times {
		g[t[0]-1][t[1]-1] = t[2]
	}
	dist[k-1] = 0
	for i := 0; i < n; i++ {
		t := -1
		for j := 0; j < n; j++ {
			if !vis[j] && (t == -1 || dist[t] > dist[j]) {
				t = j
			}
		}
		vis[t] = true
		for j := 0; j < n; j++ {
			dist[j] = min(dist[j], dist[t]+g[t][j])
		}
	}
	ans := 0
	for _, v := range dist {
		ans = max(ans, v)
	}
	if ans == inf {
		return -1
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

堆优化 Dijkstra 算法：

```go
const Inf = 0x3f3f3f3f

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

func networkDelayTime(times [][]int, n int, k int) int {
	graph := make([]pairs, n)
	for _, time := range times {
		from, to, time := time[0]-1, time[1]-1, time[2]
		graph[from] = append(graph[from], pair{to, time})
	}

	dis := make([]int, n)
	for i := range dis {
		dis[i] = Inf
	}
	dis[k-1] = 0

	vis := make([]bool, n)
	h := make(pairs, 0)
	heap.Push(&h, pair{0, k - 1})
	for len(h) > 0 {
		from := heap.Pop(&h).(pair).second
		if vis[from] {
			continue
		}
		vis[from] = true
		for _, e := range graph[from] {
			to, d := e.first, dis[from]+e.second
			if d < dis[to] {
				dis[to] = d
				heap.Push(&h, pair{d, to})
			}
		}
	}

	ans := math.MinInt32
	for _, d := range dis {
		ans = max(ans, d)
	}
	if ans == Inf {
		return -1
	}
	return ans
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
```

Bellman Ford 算法：

```go
func networkDelayTime(times [][]int, n int, k int) int {
	const inf = 0x3f3f
	dist := make([]int, n)
	backup := make([]int, n)
	for i := range dist {
		dist[i] = inf
	}
	dist[k-1] = 0
	for i := 0; i < n; i++ {
		copy(backup, dist)
		for _, e := range times {
			u, v, w := e[0]-1, e[1]-1, e[2]
			dist[v] = min(dist[v], backup[u]+w)
		}
	}
	ans := 0
	for _, v := range dist {
		ans = max(ans, v)
	}
	if ans == inf {
		return -1
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

SPFA 算法：

```go
func networkDelayTime(times [][]int, n int, k int) int {
	const inf = 0x3f3f
	dist := make([]int, n)
	vis := make([]bool, n)
	g := make([][][]int, n)
	for i := range dist {
		dist[i] = inf
	}
	for _, t := range times {
		u, v, w := t[0]-1, t[1]-1, t[2]
		g[u] = append(g[u], []int{v, w})
	}
	k--
	dist[k] = 0
	q := []int{k}
	vis[k] = true
	for len(q) > 0 {
		u := q[0]
		q = q[1:]
		vis[u] = false
		for _, ne := range g[u] {
			v, w := ne[0], ne[1]
			if dist[v] > dist[u]+w {
				dist[v] = dist[u] + w
				if !vis[v] {
					q = append(q, v)
					vis[v] = true
				}
			}
		}
	}
	ans := 0
	for _, v := range dist {
		ans = max(ans, v)
	}
	if ans == inf {
		return -1
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **C++**

朴素 Dijkstra 算法：

```cpp
class Solution {
public:
    const int inf = 0x3f3f;

    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        vector<vector<int>> g(n, vector<int>(n, inf));
        for (auto& t : times) g[t[0] - 1][t[1] - 1] = t[2];
        vector<bool> vis(n);
        vector<int> dist(n, inf);
        dist[k - 1] = 0;
        for (int i = 0; i < n; ++i) {
            int t = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (t == -1 || dist[t] > dist[j])) {
                    t = j;
                }
            }
            vis[t] = true;
            for (int j = 0; j < n; ++j) {
                dist[j] = min(dist[j], dist[t] + g[t][j]);
            }
        }
        int ans = *max_element(dist.begin(), dist.end());
        return ans == inf ? -1 : ans;
    }
};
```

堆优化 Dijkstra 算法：

```cpp
class Solution {
public:
    const int inf = 0x3f3f;

    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        vector<vector<vector<int>>> g(n);
        for (auto& t : times) g[t[0] - 1].push_back({t[1] - 1, t[2]});
        vector<int> dist(n, inf);
        dist[k - 1] = 0;
        priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> q;
        q.push({0, k - 1});
        while (!q.empty())
        {
            auto p = q.top();
            q.pop();
            int u = p[1];
            for (auto& ne : g[u])
            {
                int v = ne[0], w = ne[1];
                if (dist[v] > dist[u] + w)
                {
                    dist[v] = dist[u] + w;
                    q.push({dist[v], v});
                }
            }
        }
        int ans = *max_element(dist.begin(), dist.end());
        return ans == inf ? -1 : ans;
    }
};
```

Bellman Ford 算法：

```cpp
class Solution {
public:
    int inf = 0x3f3f;

    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        vector<int> dist(n, inf);
        dist[k - 1] = 0;
        for (int i = 0; i < n; ++i)
        {
            vector<int> backup = dist;
            for (auto& e : times)
            {
                int u = e[0] - 1, v = e[1] - 1, w = e[2];
                dist[v] = min(dist[v], backup[u] + w);
            }
        }
        int ans = *max_element(dist.begin(), dist.end());
        return ans == inf ? -1 : ans;
    }
};
```

SPFA 算法：

```cpp
class Solution {
public:
    const int inf = 0x3f3f;

    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        vector<int> dist(n, inf);
        vector<vector<vector<int>>> g(n);
        for (auto& e : times)
        {
            int u = e[0] - 1, v = e[1] - 1, w = e[2];
            g[u].push_back({v, w});
        }
        vector<bool> vis(n);
        --k;
        queue<int> q{{k}};
        vis[k] = true;
        dist[k] = 0;
        while (!q.empty())
        {
            int u = q.front();
            q.pop();
            vis[u] = false;
            for (auto& ne : g[u])
            {
                int v = ne[0], w = ne[1];
                if (dist[v] > dist[u] + w)
                {
                    dist[v] = dist[u] + w;
                    if (!vis[v])
                    {
                        q.push(v);
                        vis[v] = true;
                    }
                }
            }
        }
        int ans = *max_element(dist.begin(), dist.end());
        return ans == inf ? -1 : ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
