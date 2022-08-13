# [1976. Number of Ways to Arrive at Destination](https://leetcode.com/problems/number-of-ways-to-arrive-at-destination)

[中文文档](/solution/1900-1999/1976.Number%20of%20Ways%20to%20Arrive%20at%20Destination/README.md)

## Description

<p>You are in a city that consists of <code>n</code> intersections numbered from <code>0</code> to <code>n - 1</code> with <strong>bi-directional</strong> roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.</p>

<p>You are given an integer <code>n</code> and a 2D integer array <code>roads</code> where <code>roads[i] = [u<sub>i</sub>, v<sub>i</sub>, time<sub>i</sub>]</code> means that there is a road between intersections <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> that takes <code>time<sub>i</sub></code> minutes to travel. You want to know in how many ways you can travel from intersection <code>0</code> to intersection <code>n - 1</code> in the <strong>shortest amount of time</strong>.</p>

<p>Return <em>the <strong>number of ways</strong> you can arrive at your destination in the <strong>shortest amount of time</strong></em>. Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1976.Number%20of%20Ways%20to%20Arrive%20at%20Destination/images/graph2.png" style="width: 235px; height: 381px;" />
<pre>
<strong>Input:</strong> n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
The four ways to get there in 7 minutes are:
- 0 ➝ 6
- 0 ➝ 4 ➝ 6
- 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
- 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, roads = [[1,0,10]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>n - 1 &lt;= roads.length &lt;= n * (n - 1) / 2</code></li>
	<li><code>roads[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= time<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>u<sub>i </sub>!= v<sub>i</sub></code></li>
	<li>There is at most one road connecting any two intersections.</li>
	<li>You can reach any intersection from any other intersection.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countPaths(self, n: int, roads: List[List[int]]) -> int:
        INF = inf
        MOD = 10**9 + 7
        g = [[INF] * n for _ in range(n)]
        for u, v, t in roads:
            g[u][v] = t
            g[v][u] = t
        g[0][0] = 0
        dist = [INF] * n
        w = [0] * n
        dist[0] = 0
        w[0] = 1
        vis = [False] * n
        for _ in range(n):
            t = -1
            for i in range(n):
                if not vis[i] and (t == -1 or dist[i] < dist[t]):
                    t = i
            vis[t] = True
            for i in range(n):
                if i == t:
                    continue
                ne = dist[t] + g[t][i]
                if dist[i] > ne:
                    dist[i] = ne
                    w[i] = w[t]
                elif dist[i] == ne:
                    w[i] += w[t]
        return w[-1] % MOD
```

### **Java**

```java
class Solution {
    private static final long INF = Long.MAX_VALUE / 2;
    private static final int MOD = (int) 1e9 + 7;

    public int countPaths(int n, int[][] roads) {
        long[][] g = new long[n][n];
        long[] dist = new long[n];
        long[] w = new long[n];
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], INF);
            Arrays.fill(dist, INF);
        }
        for (int[] r : roads) {
            int u = r[0], v = r[1], t = r[2];
            g[u][v] = t;
            g[v][u] = t;
        }
        g[0][0] = 0;
        dist[0] = 0;
        w[0] = 1;
        for (int i = 0; i < n; ++i) {
            int t = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (t == -1 || dist[j] < dist[t])) {
                    t = j;
                }
            }
            vis[t] = true;
            for (int j = 0; j < n; ++j) {
                if (j == t) {
                    continue;
                }
                long ne = dist[t] + g[t][j];
                if (dist[j] > ne) {
                    dist[j] = ne;
                    w[j] = w[t];
                } else if (dist[j] == ne) {
                    w[j] = (w[j] + w[t]) % MOD;
                }
            }
        }
        return (int) w[n - 1];
    }
}
```

### **C++**

```cpp
typedef long long ll;

class Solution {
public:
    const ll INF = LLONG_MAX / 2;
    const int MOD = 1e9 + 7;

    int countPaths(int n, vector<vector<int>>& roads) {
        vector<vector<ll>> g(n, vector<ll>(n, INF));
        vector<ll> dist(n, INF);
        vector<ll> w(n);
        vector<bool> vis(n);
        for (auto& r : roads) {
            int u = r[0], v = r[1], t = r[2];
            g[u][v] = t;
            g[v][u] = t;
        }
        g[0][0] = 0;
        dist[0] = 0;
        w[0] = 1;
        for (int i = 0; i < n; ++i) {
            int t = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (t == -1 || dist[t] > dist[j])) t = j;
            }
            vis[t] = true;
            for (int j = 0; j < n; ++j) {
                if (t == j) continue;
                ll ne = dist[t] + g[t][j];
                if (dist[j] > ne) {
                    dist[j] = ne;
                    w[j] = w[t];
                } else if (dist[j] == ne)
                    w[j] = (w[j] + w[t]) % MOD;
            }
        }
        return w[n - 1];
    }
};
```

### **Go**

```go
func countPaths(n int, roads [][]int) int {
	const inf = math.MaxInt64 / 2
	const mod = int(1e9) + 7
	g := make([][]int, n)
	dist := make([]int, n)
	w := make([]int, n)
	vis := make([]bool, n)
	for i := range g {
		g[i] = make([]int, n)
		dist[i] = inf
		for j := range g[i] {
			g[i][j] = inf
		}
	}
	for _, r := range roads {
		u, v, t := r[0], r[1], r[2]
		g[u][v], g[v][u] = t, t
	}
	g[0][0] = 0
	dist[0] = 0
	w[0] = 1
	for i := 0; i < n; i++ {
		t := -1
		for j := 0; j < n; j++ {
			if !vis[j] && (t == -1 || dist[t] > dist[j]) {
				t = j
			}
		}
		vis[t] = true
		for j := 0; j < n; j++ {
			if j == t {
				continue
			}
			ne := dist[t] + g[t][j]
			if dist[j] > ne {
				dist[j] = ne
				w[j] = w[t]
			} else if dist[j] == ne {
				w[j] = (w[j] + w[t]) % mod
			}
		}
	}
	return w[n-1]
}
```

### **...**

```

```

<!-- tabs:end -->
