# [787. Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops)

[中文文档](/solution/0700-0799/0787.Cheapest%20Flights%20Within%20K%20Stops/README.md)

## Description

<p>There are <code>n</code> cities connected by some number of flights. You are given an array <code>flights</code> where <code>flights[i] = [from<sub>i</sub>, to<sub>i</sub>, price<sub>i</sub>]</code> indicates that there is a flight from city <code>from<sub>i</sub></code> to city <code>to<sub>i</sub></code> with cost <code>price<sub>i</sub></code>.</p>

<p>You are also given three integers <code>src</code>, <code>dst</code>, and <code>k</code>, return <em><strong>the cheapest price</strong> from </em><code>src</code><em> to </em><code>dst</code><em> with at most </em><code>k</code><em> stops. </em>If there is no such route, return<em> </em><code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0787.Cheapest%20Flights%20Within%20K%20Stops/images/995.png" style="height: 360px; width: 492px;" />
<pre>
<strong>Input:</strong> n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
<strong>Output:</strong> 200
<strong>Explanation:</strong> The graph is shown.
The cheapest price from city <code>0</code> to city <code>2</code> with at most 1 stop costs 200, as marked red in the picture.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0787.Cheapest%20Flights%20Within%20K%20Stops/images/995.png" style="height: 360px; width: 492px;" />
<pre>
<strong>Input:</strong> n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
<strong>Output:</strong> 500
<strong>Explanation:</strong> The graph is shown.
The cheapest price from city <code>0</code> to city <code>2</code> with at most 0 stop costs 500, as marked blue in the picture.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= flights.length &lt;= (n * (n - 1) / 2)</code></li>
	<li><code>flights[i].length == 3</code></li>
	<li><code>0 &lt;= from<sub>i</sub>, to<sub>i</sub> &lt; n</code></li>
	<li><code>from<sub>i</sub> != to<sub>i</sub></code></li>
	<li><code>1 &lt;= price<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>There will not be any multiple flights between two cities.</li>
	<li><code>0 &lt;= src, dst, k &lt; n</code></li>
	<li><code>src != dst</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
from functools import lru_cache


class Solution:
    def findCheapestPrice(self, n: int, flights: List[List[int]], src: int, dst: int, k: int) -> int:
        @lru_cache(None)
        def dfs(u, k):
            if u == dst:
                return 0
            if k <= 0:
                return float('inf')
            k -= 1
            ans = float('inf')
            for v, p in g[u]:
                ans = min(ans, dfs(v, k) + p)
            return ans

        g = defaultdict(list)
        for u, v, p in flights:
            g[u].append((v, p))
        ans = dfs(src, k + 1)
        return -1 if ans >= float('inf') else ans
```

### **Java**

```java
class Solution {
    private int[][] memo;
    private int[][] g;
    private int dst;
    private static final int INF = (int) 1e6;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        n += 10;
        memo = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(memo[i], -1);
        }
        g = new int[n][n];
        for (int[] e : flights) {
            g[e[0]][e[1]] = e[2];
        }
        this.dst = dst;
        int ans = dfs(src, k + 1);
        return ans >= INF ? -1 : ans;
    }

    private int dfs(int u, int k) {
        if (memo[u][k] != -1) {
            return memo[u][k];
        }
        if (u == dst) {
            return 0;
        }
        if (k <= 0) {
            return INF;
        }
        int ans = INF;
        for (int v = 0; v < g[u].length; ++v) {
            if (g[u][v] > 0) {
                ans = Math.min(ans, dfs(v, k - 1) + g[u][v]);
            }
        }
        memo[u][k] = ans;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> memo;
    vector<vector<int>> g;
    int dst;
    int inf = 1e6;

    int findCheapestPrice(int n, vector<vector<int>>& flights, int src, int dst, int k) {
        n += 10;
        memo.resize(n, vector<int>(n, -1));
        g.resize(n, vector<int>(n));
        for (auto& e : flights) g[e[0]][e[1]] = e[2];
        this->dst = dst;
        int ans = dfs(src, k + 1);
        return ans >= inf ? -1 : ans;
    }

    int dfs(int u, int k) {
        if (memo[u][k] != -1) return memo[u][k];
        if (u == dst) return 0;
        if (k <= 0) return inf;
        int ans = inf;
        for (int v = 0; v < g[u].size(); ++v)
            if (g[u][v] > 0)
                ans = min(ans, dfs(v, k - 1) + g[u][v]);
        memo[u][k] = ans;
        return memo[u][k];
    }
};
```

### **Go**

```go
func findCheapestPrice(n int, flights [][]int, src int, dst int, k int) int {
	n += 10
	memo := make([][]int, n)
	g := make([][]int, n)
	for i := range memo {
		memo[i] = make([]int, n)
		g[i] = make([]int, n)
		for j := range memo[i] {
			memo[i][j] = -1
		}
	}

	for _, e := range flights {
		g[e[0]][e[1]] = e[2]
	}
	inf := int(1e6)
	var dfs func(u, k int) int
	dfs = func(u, k int) int {
		if memo[u][k] != -1 {
			return memo[u][k]
		}
		if u == dst {
			return 0
		}
		if k <= 0 {
			return inf
		}
		ans := inf
		for v, p := range g[u] {
			if p > 0 {
				ans = min(ans, dfs(v, k-1)+p)
			}
		}
		memo[u][k] = ans
		return ans
	}
	ans := dfs(src, k+1)
	if ans >= inf {
		return -1
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
