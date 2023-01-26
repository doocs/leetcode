# [1514. Path with Maximum Probability](https://leetcode.com/problems/path-with-maximum-probability)

[中文文档](/solution/1500-1599/1514.Path%20with%20Maximum%20Probability/README.md)

## Description

<p>You are given an undirected weighted graph of&nbsp;<code>n</code>&nbsp;nodes (0-indexed), represented by an edge list where&nbsp;<code>edges[i] = [a, b]</code>&nbsp;is an undirected edge connecting the nodes&nbsp;<code>a</code>&nbsp;and&nbsp;<code>b</code>&nbsp;with a probability of success of traversing that edge&nbsp;<code>succProb[i]</code>.</p>

<p>Given two nodes&nbsp;<code>start</code>&nbsp;and&nbsp;<code>end</code>, find the path with the maximum probability of success to go from&nbsp;<code>start</code>&nbsp;to&nbsp;<code>end</code>&nbsp;and return its success probability.</p>

<p>If there is no path from&nbsp;<code>start</code>&nbsp;to&nbsp;<code>end</code>, <strong>return&nbsp;0</strong>. Your answer will be accepted if it differs from the correct answer by at most <strong>1e-5</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1514.Path%20with%20Maximum%20Probability/images/1558_ex1.png" style="width: 187px; height: 186px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
<strong>Output:</strong> 0.25000
<strong>Explanation:</strong>&nbsp;There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1514.Path%20with%20Maximum%20Probability/images/1558_ex2.png" style="width: 189px; height: 186px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
<strong>Output:</strong> 0.30000
</pre>

<p><strong class="example">Example 3:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1514.Path%20with%20Maximum%20Probability/images/1558_ex3.png" style="width: 215px; height: 191px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
<strong>Output:</strong> 0.00000
<strong>Explanation:</strong>&nbsp;There is no path between 0 and 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10^4</code></li>
	<li><code>0 &lt;= start, end &lt; n</code></li>
	<li><code>start != end</code></li>
	<li><code>0 &lt;= a, b &lt; n</code></li>
	<li><code>a != b</code></li>
	<li><code>0 &lt;= succProb.length == edges.length &lt;= 2*10^4</code></li>
	<li><code>0 &lt;= succProb[i] &lt;= 1</code></li>
	<li>There is at most one edge between every two nodes.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxProbability(
        self,
        n: int,
        edges: List[List[int]],
        succProb: List[float],
        start: int,
        end: int,
    ) -> float:
        g = defaultdict(list)
        for (a, b), s in zip(edges, succProb):
            g[a].append((b, s))
            g[b].append((a, s))
        q = [(-1, start)]
        d = [0] * n
        d[start] = 1
        while q:
            w, u = heappop(q)
            w = -w
            if d[u] > w:
                continue
            for v, t in g[u]:
                if d[v] < d[u] * t:
                    d[v] = d[u] * t
                    heappush(q, (-d[v], v))
        return d[end]
```

```python
class Solution:
    def maxProbability(self, n: int, edges: List[List[int]], succProb: List[float], start: int, end: int) -> float:
        g = defaultdict(list)
        for (a, b), s in zip(edges, succProb):
            g[a].append((b, s))
            g[b].append((a, s))
        d = [0] * n
        vis = [False] * n
        d[start] = 1
        q = deque([start])
        vis[start] = True
        while q:
            i = q.popleft()
            vis[i] = False
            for j, s in g[i]:
                if d[j] < d[i] * s:
                    d[j] = d[i] * s
                    if not vis[j]:
                        q.append(j)
                        vis[j] = True
        return d[end]
```

### **Java**

```java
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<Pair<Integer, Double>>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < edges.length; ++i) {
            int a = edges[i][0], b = edges[i][1];
            double s = succProb[i];
            g[a].add(new Pair<>(b, s));
            g[b].add(new Pair<>(a, s));
        }
        PriorityQueue<Pair<Double, Integer>> q
            = new PriorityQueue<>(Comparator.comparingDouble(Pair::getKey));
        double[] d = new double[n];
        d[start] = 1.0;
        q.offer(new Pair<>(-1.0, start));
        while (!q.isEmpty()) {
            Pair<Double, Integer> p = q.poll();
            double w = p.getKey();
            w *= -1;
            int u = p.getValue();
            for (Pair<Integer, Double> ne : g[u]) {
                int v = ne.getKey();
                double t = ne.getValue();
                if (d[v] < d[u] * t) {
                    d[v] = d[u] * t;
                    q.offer(new Pair<>(-d[v], v));
                }
            }
        }
        return d[end];
    }
}
```

```java
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<Pair<Integer, Double>>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < edges.length; ++i) {
            int a = edges[i][0], b = edges[i][1];
            double s = succProb[i];
            g[a].add(new Pair<>(b, s));
            g[b].add(new Pair<>(a, s));
        }
        double[] d = new double[n];
        d[start] = 1.0;
        boolean[] vis = new boolean[n];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        vis[start] = true;
        while (!q.isEmpty()) {
            int i = q.poll();
            vis[i] = false;
            for (Pair<Integer, Double> ne : g[i]) {
                int j = ne.getKey();
                double s = ne.getValue();
                if (d[j] < d[i] * s) {
                    d[j] = d[i] * s;
                    if (!vis[j]) {
                        q.offer(j);
                        vis[j] = true;
                    }
                }
            }
        }
        return d[end];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double maxProbability(int n, vector<vector<int>>& edges, vector<double>& succProb, int start, int end) {
        vector<vector<pair<int, double>>> g(n);
        for (int i = 0; i < edges.size(); ++i) {
            int a = edges[i][0], b = edges[i][1];
            double s = succProb[i];
            g[a].push_back({b, s});
            g[b].push_back({a, s});
        }
        vector<double> d(n);
        d[start] = 1.0;
        queue<pair<double, int>> q;
        q.push({1.0, start});
        while (!q.empty()) {
            auto p = q.front();
            q.pop();
            double w = p.first;
            int u = p.second;
            if (d[u] > w) continue;
            for (auto& e : g[u]) {
                int v = e.first;
                double t = e.second;
                if (d[v] < d[u] * t) {
                    d[v] = d[u] * t;
                    q.push({d[v], v});
                }
            }
        }
        return d[end];
    }
};
```

```cpp
class Solution {
public:
    double maxProbability(int n, vector<vector<int>>& edges, vector<double>& succProb, int start, int end) {
        vector<vector<pair<int, double>>> g(n);
        for (int i = 0; i < edges.size(); ++i)
        {
            int a = edges[i][0], b = edges[i][1];
            double s = succProb[i];
            g[a].push_back({b, s});
            g[b].push_back({a, s});
        }
        vector<double> d(n);
        vector<bool> vis(n);
        d[start] = 1.0;
        queue<int> q{{start}};
        vis[start] = true;
        while (!q.empty())
        {
            int i = q.front();
            q.pop();
            vis[i] = false;
            for (auto& ne : g[i])
            {
                int j = ne.first;
                double s = ne.second;
                if (d[j] < d[i] * s)
                {
                    d[j] = d[i] * s;
                    if (!vis[j])
                    {
                        q.push(j);
                        vis[j] = true;
                    }
                }
            }
        }
        return d[end];
    }
};
```

### **Go**

```go
func maxProbability(n int, edges [][]int, succProb []float64, start int, end int) float64 {
	g := make([][]pair, n)
	for i, e := range edges {
		a, b, s := e[0], e[1], succProb[i]
		g[a] = append(g[a], pair{b, s})
		g[b] = append(g[b], pair{a, s})
	}
	d := make([]float64, n)
	d[start] = 1
	vis := make([]bool, n)
	q := []int{start}
	vis[start] = true
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		vis[i] = false
		for _, ne := range g[i] {
			j, s := ne.idx, ne.s
			if d[j] < d[i]*s {
				d[j] = d[i] * s
				if !vis[j] {
					q = append(q, j)
					vis[j] = true
				}
			}
		}
	}
	return d[end]
}

type pair struct {
	idx int
	s   float64
}
```

### **...**

```

```

<!-- tabs:end -->
