---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1976.Number%20of%20Ways%20to%20Arrive%20at%20Destination/README_EN.md
rating: 2094
source: Biweekly Contest 59 Q3
tags:
    - Graph
    - Topological Sort
    - Dynamic Programming
    - Shortest Path
---

<!-- problem:start -->

# [1976. Number of Ways to Arrive at Destination](https://leetcode.com/problems/number-of-ways-to-arrive-at-destination)

[中文文档](/solution/1900-1999/1976.Number%20of%20Ways%20to%20Arrive%20at%20Destination/README.md)

## Description

<!-- description:start -->

<p>You are in a city that consists of <code>n</code> intersections numbered from <code>0</code> to <code>n - 1</code> with <strong>bi-directional</strong> roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.</p>

<p>You are given an integer <code>n</code> and a 2D integer array <code>roads</code> where <code>roads[i] = [u<sub>i</sub>, v<sub>i</sub>, time<sub>i</sub>]</code> means that there is a road between intersections <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> that takes <code>time<sub>i</sub></code> minutes to travel. You want to know in how many ways you can travel from intersection <code>0</code> to intersection <code>n - 1</code> in the <strong>shortest amount of time</strong>.</p>

<p>Return <em>the <strong>number of ways</strong> you can arrive at your destination in the <strong>shortest amount of time</strong></em>. Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1976.Number%20of%20Ways%20to%20Arrive%20at%20Destination/images/1976_corrected.png" style="width: 255px; height: 400px;" />
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

<p><strong class="example">Example 2:</strong></p>

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Naive Dijkstra Algorithm

We define the following arrays:

-   `g` represents the adjacency matrix of the graph. `g[i][j]` represents the shortest path length from point `i` to point `j`. Initially, all are infinity, while `g[0][0]` is 0. Then we traverse `roads` and update `g[u][v]` and `g[v][u]` to `t`.
-   `dist[i]` represents the shortest path length from the starting point to point `i`. Initially, all are infinity, while `dist[0]` is 0.
-   `f[i]` represents the number of shortest paths from the starting point to point `i`. Initially, all are 0, while `f[0]` is 1.
-   `vis[i]` represents whether point `i` has been visited. Initially, all are `False`.

Then, we use the naive Dijkstra algorithm to find the shortest path length from the starting point to the end point, and record the number of shortest paths for each point during the process.

Finally, we return `f[n - 1]`. Since the answer may be very large, we need to take the modulus of $10^9 + 7$.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$, where $n$ is the number of points.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPaths(self, n: int, roads: List[List[int]]) -> int:
        g = [[inf] * n for _ in range(n)]
        for u, v, t in roads:
            g[u][v] = g[v][u] = t
        g[0][0] = 0
        dist = [inf] * n
        dist[0] = 0
        f = [0] * n
        f[0] = 1
        vis = [False] * n
        for _ in range(n):
            t = -1
            for j in range(n):
                if not vis[j] and (t == -1 or dist[j] < dist[t]):
                    t = j
            vis[t] = True
            for j in range(n):
                if j == t:
                    continue
                ne = dist[t] + g[t][j]
                if dist[j] > ne:
                    dist[j] = ne
                    f[j] = f[t]
                elif dist[j] == ne:
                    f[j] += f[t]
        mod = 10**9 + 7
        return f[-1] % mod
```

#### Java

```java
class Solution {
    public int countPaths(int n, int[][] roads) {
        final long inf = Long.MAX_VALUE / 2;
        final int mod = (int) 1e9 + 7;
        long[][] g = new long[n][n];
        for (var e : g) {
            Arrays.fill(e, inf);
        }
        for (var r : roads) {
            int u = r[0], v = r[1], t = r[2];
            g[u][v] = t;
            g[v][u] = t;
        }
        g[0][0] = 0;
        long[] dist = new long[n];
        Arrays.fill(dist, inf);
        dist[0] = 0;
        long[] f = new long[n];
        f[0] = 1;
        boolean[] vis = new boolean[n];
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
                    f[j] = f[t];
                } else if (dist[j] == ne) {
                    f[j] = (f[j] + f[t]) % mod;
                }
            }
        }
        return (int) f[n - 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countPaths(int n, vector<vector<int>>& roads) {
        const long long inf = LLONG_MAX / 2;
        const int mod = 1e9 + 7;

        vector<vector<long long>> g(n, vector<long long>(n, inf));
        for (auto& e : g) {
            fill(e.begin(), e.end(), inf);
        }

        for (auto& r : roads) {
            int u = r[0], v = r[1], t = r[2];
            g[u][v] = t;
            g[v][u] = t;
        }

        g[0][0] = 0;

        vector<long long> dist(n, inf);
        fill(dist.begin(), dist.end(), inf);
        dist[0] = 0;

        vector<long long> f(n);
        f[0] = 1;

        vector<bool> vis(n);
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
                long long ne = dist[t] + g[t][j];
                if (dist[j] > ne) {
                    dist[j] = ne;
                    f[j] = f[t];
                } else if (dist[j] == ne) {
                    f[j] = (f[j] + f[t]) % mod;
                }
            }
        }
        return (int) f[n - 1];
    }
};
```

#### Go

```go
func countPaths(n int, roads [][]int) int {
	const inf = math.MaxInt64 / 2
	const mod = int(1e9 + 7)

	g := make([][]int, n)
	dist := make([]int, n)
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = inf
			dist[i] = inf
		}
	}

	for _, r := range roads {
		u, v, t := r[0], r[1], r[2]
		g[u][v] = t
		g[v][u] = t
	}

	f := make([]int, n)
	vis := make([]bool, n)
	f[0] = 1
	g[0][0] = 0
	dist[0] = 0

	for i := 0; i < n; i++ {
		t := -1
		for j := 0; j < n; j++ {
			if !vis[j] && (t == -1 || dist[j] < dist[t]) {
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
				f[j] = f[t]
			} else if dist[j] == ne {
				f[j] = (f[j] + f[t]) % mod
			}
		}
	}
	return f[n-1]
}
```

#### TypeScript

```ts
function countPaths(n: number, roads: number[][]): number {
    const mod: number = 1e9 + 7;
    const g: number[][] = Array.from({ length: n }, () => Array(n).fill(Infinity));
    for (const [u, v, t] of roads) {
        g[u][v] = t;
        g[v][u] = t;
    }
    g[0][0] = 0;

    const dist: number[] = Array(n).fill(Infinity);
    dist[0] = 0;

    const f: number[] = Array(n).fill(0);
    f[0] = 1;

    const vis: boolean[] = Array(n).fill(false);
    for (let i = 0; i < n; ++i) {
        let t: number = -1;
        for (let j = 0; j < n; ++j) {
            if (!vis[j] && (t === -1 || dist[j] < dist[t])) {
                t = j;
            }
        }
        vis[t] = true;
        for (let j = 0; j < n; ++j) {
            if (j === t) {
                continue;
            }
            const ne: number = dist[t] + g[t][j];
            if (dist[j] > ne) {
                dist[j] = ne;
                f[j] = f[t];
            } else if (dist[j] === ne) {
                f[j] = (f[j] + f[t]) % mod;
            }
        }
    }
    return f[n - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
