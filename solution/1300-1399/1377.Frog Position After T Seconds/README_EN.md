# [1377. Frog Position After T Seconds](https://leetcode.com/problems/frog-position-after-t-seconds)

[中文文档](/solution/1300-1399/1377.Frog%20Position%20After%20T%20Seconds/README.md)

## Description

<p>Given an undirected tree consisting of <code>n</code> vertices numbered from <code>1</code> to <code>n</code>. A frog starts jumping from <strong>vertex 1</strong>. In one second, the frog jumps from its current vertex to another <strong>unvisited</strong> vertex if they are directly connected. The frog can not jump back to a visited vertex. In case the frog can jump to several vertices, it jumps randomly to one of them with the same probability. Otherwise, when the frog can not jump to any unvisited vertex, it jumps forever on the same vertex.</p>

<p>The edges of the undirected tree are given in the array <code>edges</code>, where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> means that exists an edge connecting the vertices <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>.</p>

<p><em>Return the probability that after <code>t</code> seconds the frog is on the vertex <code>target</code>. </em>Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1377.Frog%20Position%20After%20T%20Seconds/images/frog1.jpg" style="width: 338px; height: 304px;" />
<pre>
<strong>Input:</strong> n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
<strong>Output:</strong> 0.16666666666666666 
<strong>Explanation:</strong> The figure above shows the given graph. The frog starts at vertex 1, jumping with 1/3 probability to the vertex 2 after <strong>second 1</strong> and then jumping with 1/2 probability to vertex 4 after <strong>second 2</strong>. Thus the probability for the frog is on the vertex 4 after 2 seconds is 1/3 * 1/2 = 1/6 = 0.16666666666666666. 
</pre>

<p><strong>Example 2:</strong></p>
<strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1377.Frog%20Position%20After%20T%20Seconds/images/frog2.jpg" style="width: 304px; height: 304px;" /></strong>

<pre>
<strong>Input:</strong> n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
<strong>Output:</strong> 0.3333333333333333
<strong>Explanation: </strong>The figure above shows the given graph. The frog starts at vertex 1, jumping with 1/3 = 0.3333333333333333 probability to the vertex 7 after <strong>second 1</strong>. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n</code></li>
	<li><code>1 &lt;= t &lt;= 50</code></li>
	<li><code>1 &lt;= target &lt;= n</code></li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def frogPosition(
        self, n: int, edges: List[List[int]], t: int, target: int
    ) -> float:
        g = defaultdict(list)
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        q = deque([(1, 1.0)])
        vis = [False] * (n + 1)
        vis[1] = True
        while q and t >= 0:
            for _ in range(len(q)):
                u, p = q.popleft()
                nxt = [v for v in g[u] if not vis[v]]
                if u == target and (not nxt or t == 0):
                    return p
                for v in nxt:
                    vis[v] = True
                    q.append((v, p / len(nxt)))
            t -= 1
        return 0
```

### **Java**

```java
class Solution {
    public double frogPosition(int n, int[][] edges, int t, int target) {
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        Deque<Pair<Integer, Double>> q = new ArrayDeque<>();
        q.offer(new Pair<>(1, 1.0));
        boolean[] vis = new boolean[n + 1];
        vis[1] = true;
        while (!q.isEmpty() && t >= 0) {
            for (int k = q.size(); k > 0; --k) {
                Pair<Integer, Double> x = q.poll();
                int u = x.getKey();
                double p = x.getValue();
                List<Integer> nxt = new ArrayList<>();
                for (int v : g[u]) {
                    if (!vis[v]) {
                        nxt.add(v);
                        vis[v] = true;
                    }
                }
                if (u == target && (nxt.isEmpty() || t == 0)) {
                    return p;
                }
                for (int v : nxt) {
                    q.offer(new Pair<>(v, p / nxt.size()));
                }
            }
            --t;
        }
        return 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double frogPosition(int n, vector<vector<int>>& edges, int t, int target) {
        vector<vector<int>> g(n + 1);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        typedef pair<int, double> pid;
        queue<pid> q;
        q.push({1, 1.0});
        vector<bool> vis(n + 1);
        vis[1] = true;
        while (!q.empty() && t >= 0) {
            for (int k = q.size(); k; --k) {
                auto x = q.front();
                q.pop();
                int u = x.first;
                double p = x.second;
                vector<int> nxt;
                for (int v : g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        nxt.push_back(v);
                    }
                }
                if (u == target && (t == 0 || nxt.empty())) return p;
                for (int v : nxt) q.push({v, p / nxt.size()});
            }
            --t;
        }
        return 0;
    }
};
```

### **Go**

```go
type pid struct {
	x int
	p float64
}

func frogPosition(n int, edges [][]int, t int, target int) float64 {
	g := make([][]int, n+1)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	q := []pid{pid{1, 1.0}}
	vis := make([]bool, n+1)
	vis[1] = true
	for len(q) > 0 && t >= 0 {
		for k := len(q); k > 0; k-- {
			x := q[0]
			q = q[1:]
			u, p := x.x, x.p
			var nxt []int
			for _, v := range g[u] {
				if !vis[v] {
					vis[v] = true
					nxt = append(nxt, v)
				}
			}
			if u == target && (len(nxt) == 0 || t == 0) {
				return p
			}
			for _, v := range nxt {
				q = append(q, pid{v, p / float64(len(nxt))})
			}
		}
		t--
	}
	return 0
}
```

### **...**

```

```

<!-- tabs:end -->
