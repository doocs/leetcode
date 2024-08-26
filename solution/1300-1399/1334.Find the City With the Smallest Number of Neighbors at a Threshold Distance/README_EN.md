---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1334.Find%20the%20City%20With%20the%20Smallest%20Number%20of%20Neighbors%20at%20a%20Threshold%20Distance/README_EN.md
rating: 1854
source: Weekly Contest 173 Q3
tags:
    - Graph
    - Dynamic Programming
    - Shortest Path
---

<!-- problem:start -->

# [1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance](https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance)

[中文文档](/solution/1300-1399/1334.Find%20the%20City%20With%20the%20Smallest%20Number%20of%20Neighbors%20at%20a%20Threshold%20Distance/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> cities numbered from <code>0</code> to <code>n-1</code>. Given the array <code>edges</code> where <code>edges[i] = [from<sub>i</sub>, to<sub>i</sub>, weight<sub>i</sub>]</code> represents a bidirectional and weighted edge between cities <code>from<sub>i</sub></code> and <code>to<sub>i</sub></code>, and given the integer <code>distanceThreshold</code>.</p>

<p>Return the city with the smallest number of cities that are reachable through some path and whose distance is <strong>at most</strong> <code>distanceThreshold</code>, If there are multiple such cities, return the city with the greatest number.</p>

<p>Notice that the distance of a path connecting cities <em><strong>i</strong></em> and <em><strong>j</strong></em> is equal to the sum of the edges&#39; weights along that path.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1334.Find%20the%20City%20With%20the%20Smallest%20Number%20of%20Neighbors%20at%20a%20Threshold%20Distance/images/problem1334example1.png" style="width: 300px; height: 224px;" /></p>

<pre>
<strong>Input:</strong> n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
<strong>Output:</strong> 3
<strong>Explanation: </strong>The figure above describes the graph.&nbsp;
The neighboring cities at a distanceThreshold = 4 for each city are:
City 0 -&gt; [City 1, City 2]&nbsp;
City 1 -&gt; [City 0, City 2, City 3]&nbsp;
City 2 -&gt; [City 0, City 1, City 3]&nbsp;
City 3 -&gt; [City 1, City 2]&nbsp;
Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1334.Find%20the%20City%20With%20the%20Smallest%20Number%20of%20Neighbors%20at%20a%20Threshold%20Distance/images/problem1334example0.png" style="width: 300px; height: 224px;" /></p>

<pre>
<strong>Input:</strong> n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
<strong>Output:</strong> 0
<strong>Explanation: </strong>The figure above describes the graph.&nbsp;
The neighboring cities at a distanceThreshold = 2 for each city are:
City 0 -&gt; [City 1]&nbsp;
City 1 -&gt; [City 0, City 4]&nbsp;
City 2 -&gt; [City 3, City 4]&nbsp;
City 3 -&gt; [City 2, City 4]
City 4 -&gt; [City 1, City 2, City 3]&nbsp;
The city 0 has 1 neighboring city at a distanceThreshold = 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= edges.length &lt;= n * (n - 1) / 2</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= from<sub>i</sub> &lt; to<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= weight<sub>i</sub>,&nbsp;distanceThreshold &lt;= 10^4</code></li>
	<li>All pairs <code>(from<sub>i</sub>, to<sub>i</sub>)</code> are distinct.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findTheCity(
        self, n: int, edges: List[List[int]], distanceThreshold: int
    ) -> int:
        def dijkstra(u: int) -> int:
            dist = [inf] * n
            dist[u] = 0
            vis = [False] * n
            for _ in range(n):
                k = -1
                for j in range(n):
                    if not vis[j] and (k == -1 or dist[k] > dist[j]):
                        k = j
                vis[k] = True
                for j in range(n):
                    # dist[j] = min(dist[j], dist[k] + g[k][j])
                    if dist[k] + g[k][j] < dist[j]:
                        dist[j] = dist[k] + g[k][j]
            return sum(d <= distanceThreshold for d in dist)

        g = [[inf] * n for _ in range(n)]
        for f, t, w in edges:
            g[f][t] = g[t][f] = w
        ans, cnt = n, inf
        for i in range(n - 1, -1, -1):
            if (t := dijkstra(i)) < cnt:
                cnt, ans = t, i
        return ans
```

#### Java

```java
class Solution {
    private int n;
    private int[][] g;
    private int[] dist;
    private boolean[] vis;
    private final int inf = 1 << 30;
    private int distanceThreshold;

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        this.n = n;
        this.distanceThreshold = distanceThreshold;
        g = new int[n][n];
        dist = new int[n];
        vis = new boolean[n];
        for (var e : g) {
            Arrays.fill(e, inf);
        }
        for (var e : edges) {
            int f = e[0], t = e[1], w = e[2];
            g[f][t] = w;
            g[t][f] = w;
        }
        int ans = n, cnt = inf;
        for (int i = n - 1; i >= 0; --i) {
            int t = dijkstra(i);
            if (t < cnt) {
                cnt = t;
                ans = i;
            }
        }
        return ans;
    }

    private int dijkstra(int u) {
        Arrays.fill(dist, inf);
        Arrays.fill(vis, false);
        dist[u] = 0;
        for (int i = 0; i < n; ++i) {
            int k = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (k == -1 || dist[k] > dist[j])) {
                    k = j;
                }
            }
            vis[k] = true;
            for (int j = 0; j < n; ++j) {
                dist[j] = Math.min(dist[j], dist[k] + g[k][j]);
            }
        }
        int cnt = 0;
        for (int d : dist) {
            if (d <= distanceThreshold) {
                ++cnt;
            }
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findTheCity(int n, vector<vector<int>>& edges, int distanceThreshold) {
        int g[n][n];
        int dist[n];
        bool vis[n];
        memset(g, 0x3f, sizeof(g));
        for (auto& e : edges) {
            int f = e[0], t = e[1], w = e[2];
            g[f][t] = g[t][f] = w;
        }
        auto dijkstra = [&](int u) {
            memset(dist, 0x3f, sizeof(dist));
            memset(vis, 0, sizeof(vis));
            dist[u] = 0;
            for (int i = 0; i < n; ++i) {
                int k = -1;
                for (int j = 0; j < n; ++j) {
                    if (!vis[j] && (k == -1 || dist[j] < dist[k])) {
                        k = j;
                    }
                }
                vis[k] = true;
                for (int j = 0; j < n; ++j) {
                    dist[j] = min(dist[j], dist[k] + g[k][j]);
                }
            }
            return count_if(dist, dist + n, [&](int d) { return d <= distanceThreshold; });
        };
        int ans = n, cnt = n + 1;
        for (int i = n - 1; ~i; --i) {
            int t = dijkstra(i);
            if (t < cnt) {
                cnt = t;
                ans = i;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findTheCity(n int, edges [][]int, distanceThreshold int) int {
	g := make([][]int, n)
	dist := make([]int, n)
	vis := make([]bool, n)
	const inf int = 1e7
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = inf
		}
	}
	for _, e := range edges {
		f, t, w := e[0], e[1], e[2]
		g[f][t], g[t][f] = w, w
	}

	dijkstra := func(u int) (cnt int) {
		for i := range vis {
			vis[i] = false
			dist[i] = inf
		}
		dist[u] = 0
		for i := 0; i < n; i++ {
			k := -1
			for j := 0; j < n; j++ {
				if !vis[j] && (k == -1 || dist[j] < dist[k]) {
					k = j
				}
			}
			vis[k] = true
			for j := 0; j < n; j++ {
				dist[j] = min(dist[j], dist[k]+g[k][j])
			}
		}
		for _, d := range dist {
			if d <= distanceThreshold {
				cnt++
			}
		}
		return
	}

	ans, cnt := n, inf
	for i := n - 1; i >= 0; i-- {
		if t := dijkstra(i); t < cnt {
			cnt = t
			ans = i
		}
	}
	return ans
}
```

#### TypeScript

```ts
function findTheCity(n: number, edges: number[][], distanceThreshold: number): number {
    const g: number[][] = Array.from({ length: n }, () => Array(n).fill(Infinity));
    const dist: number[] = Array(n).fill(Infinity);
    const vis: boolean[] = Array(n).fill(false);
    for (const [f, t, w] of edges) {
        g[f][t] = g[t][f] = w;
    }

    const dijkstra = (u: number): number => {
        dist.fill(Infinity);
        vis.fill(false);
        dist[u] = 0;
        for (let i = 0; i < n; ++i) {
            let k = -1;
            for (let j = 0; j < n; ++j) {
                if (!vis[j] && (k === -1 || dist[j] < dist[k])) {
                    k = j;
                }
            }
            vis[k] = true;
            for (let j = 0; j < n; ++j) {
                dist[j] = Math.min(dist[j], dist[k] + g[k][j]);
            }
        }
        return dist.filter(d => d <= distanceThreshold).length;
    };

    let ans = n;
    let cnt = Infinity;
    for (let i = n - 1; i >= 0; --i) {
        const t = dijkstra(i);
        if (t < cnt) {
            cnt = t;
            ans = i;
        }
    }

    return ans;
}
```

#### JavaScript

```js
function findTheCity(n, edges, distanceThreshold) {
    const g = Array.from({ length: n }, () => Array(n).fill(Infinity));
    const dist = Array(n).fill(Infinity);
    const vis = Array(n).fill(false);
    for (const [f, t, w] of edges) {
        g[f][t] = g[t][f] = w;
    }

    const dijkstra = u => {
        dist.fill(Infinity);
        vis.fill(false);
        dist[u] = 0;
        for (let i = 0; i < n; ++i) {
            let k = -1;
            for (let j = 0; j < n; ++j) {
                if (!vis[j] && (k === -1 || dist[j] < dist[k])) {
                    k = j;
                }
            }
            vis[k] = true;
            for (let j = 0; j < n; ++j) {
                dist[j] = Math.min(dist[j], dist[k] + g[k][j]);
            }
        }
        return dist.filter(d => d <= distanceThreshold).length;
    };

    let ans = n;
    let cnt = Infinity;
    for (let i = n - 1; i >= 0; --i) {
        const t = dijkstra(i);
        if (t < cnt) {
            cnt = t;
            ans = i;
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findTheCity(
        self, n: int, edges: List[List[int]], distanceThreshold: int
    ) -> int:
        g = [[inf] * n for _ in range(n)]
        for f, t, w in edges:
            g[f][t] = g[t][f] = w

        for k in range(n):
            g[k][k] = 0
            for i in range(n):
                for j in range(n):
                    # g[i][j] = min(g[i][j], g[i][k] + g[k][j])
                    if g[i][k] + g[k][j] < g[i][j]:
                        g[i][j] = g[i][k] + g[k][j]

        ans, cnt = n, inf
        for i in range(n - 1, -1, -1):
            t = sum(d <= distanceThreshold for d in g[i])
            if t < cnt:
                cnt, ans = t, i
        return ans
```

#### Java

```java
class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        final int inf = 1 << 29;
        int[][] g = new int[n][n];
        for (var e : g) {
            Arrays.fill(e, inf);
        }
        for (var e : edges) {
            int f = e[0], t = e[1], w = e[2];
            g[f][t] = w;
            g[t][f] = w;
        }
        for (int k = 0; k < n; ++k) {
            g[k][k] = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }
        int ans = n, cnt = inf;
        for (int i = n - 1; i >= 0; --i) {
            int t = 0;
            for (int d : g[i]) {
                if (d <= distanceThreshold) {
                    ++t;
                }
            }
            if (t < cnt) {
                cnt = t;
                ans = i;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findTheCity(int n, vector<vector<int>>& edges, int distanceThreshold) {
        int g[n][n];
        memset(g, 0x3f, sizeof(g));
        for (auto& e : edges) {
            int f = e[0], t = e[1], w = e[2];
            g[f][t] = g[t][f] = w;
        }
        for (int k = 0; k < n; ++k) {
            g[k][k] = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    g[i][j] = min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }
        int ans = n, cnt = n + 1;
        for (int i = n - 1; ~i; --i) {
            int t = count_if(g[i], g[i] + n, [&](int x) { return x <= distanceThreshold; });
            if (t < cnt) {
                cnt = t;
                ans = i;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findTheCity(n int, edges [][]int, distanceThreshold int) int {
	g := make([][]int, n)
	const inf int = 1e7
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = inf
		}
	}

	for _, e := range edges {
		f, t, w := e[0], e[1], e[2]
		g[f][t], g[t][f] = w, w
	}

	for k := 0; k < n; k++ {
		g[k][k] = 0
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				g[i][j] = min(g[i][j], g[i][k]+g[k][j])
			}
		}
	}

	ans, cnt := n, n+1
	for i := n - 1; i >= 0; i-- {
		t := 0
		for _, x := range g[i] {
			if x <= distanceThreshold {
				t++
			}
		}
		if t < cnt {
			cnt, ans = t, i
		}
	}

	return ans
}
```

#### TypeScript

```ts
function findTheCity(n: number, edges: number[][], distanceThreshold: number): number {
    const g: number[][] = Array.from({ length: n }, () => Array(n).fill(Infinity));
    for (const [f, t, w] of edges) {
        g[f][t] = g[t][f] = w;
    }
    for (let k = 0; k < n; ++k) {
        g[k][k] = 0;
        for (let i = 0; i < n; ++i) {
            for (let j = 0; j < n; ++j) {
                g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
            }
        }
    }

    let ans = n,
        cnt = n + 1;
    for (let i = n - 1; i >= 0; --i) {
        const t = g[i].filter(x => x <= distanceThreshold).length;
        if (t < cnt) {
            cnt = t;
            ans = i;
        }
    }
    return ans;
}
```

#### JavaScript

```js
function findTheCity(n, edges, distanceThreshold) {
    const g = Array.from({ length: n }, () => Array(n).fill(Infinity));
    for (const [f, t, w] of edges) {
        g[f][t] = g[t][f] = w;
    }
    for (let k = 0; k < n; ++k) {
        g[k][k] = 0;
        for (let i = 0; i < n; ++i) {
            for (let j = 0; j < n; ++j) {
                g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
            }
        }
    }

    let ans = n,
        cnt = n + 1;
    for (let i = n - 1; i >= 0; --i) {
        const t = g[i].filter(x => x <= distanceThreshold).length;
        if (t < cnt) {
            cnt = t;
            ans = i;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3

<!-- tabs:start -->

#### TypeScript

```ts
function findTheCity(n: number, edges: number[][], distanceThreshold: number): number {
    const MAX = Number.POSITIVE_INFINITY;
    const g = Array.from({ length: n }, () => new Map<number, number>());
    const dist: number[] = Array(n).fill(MAX);
    const vis: boolean[] = Array(n).fill(false);
    for (const [f, t, w] of edges) {
        g[f].set(t, w);
        g[t].set(f, w);
    }

    const dijkstra = (u: number): number => {
        dist.fill(MAX);
        vis.fill(false);
        dist[u] = 0;
        const pq = new MinPriorityQueue();
        pq.enqueue(u, 0);

        while (!pq.isEmpty()) {
            const u = pq.dequeue().element;
            if (vis[u]) continue;
            vis[u] = true;

            for (const [v, w] of g[u]) {
                if (vis[v]) continue;

                const wNext = dist[u] + w;
                if (wNext < dist[v]) {
                    dist[v] = wNext;
                    pq.enqueue(v, dist[v]);
                }
            }
        }

        return dist.filter(d => d <= distanceThreshold).length;
    };

    let ans = n;
    let cnt = MAX;
    for (let i = n - 1; i >= 0; --i) {
        const t = dijkstra(i);
        if (t < cnt) {
            cnt = t;
            ans = i;
        }
    }

    return ans;
}
```

#### JavaScript

```js
export function findTheCity(n, edges, distanceThreshold) {
    const MAX = Number.POSITIVE_INFINITY;
    const g = Array.from({ length: n }, () => new Map());
    const dist = Array(n).fill(MAX);
    const vis = Array(n).fill(false);
    for (const [f, t, w] of edges) {
        g[f].set(t, w);
        g[t].set(f, w);
    }

    const dijkstra = u => {
        dist.fill(MAX);
        vis.fill(false);
        dist[u] = 0;
        const pq = new MinPriorityQueue();
        pq.enqueue(u, 0);

        while (!pq.isEmpty()) {
            const u = pq.dequeue().element;
            if (vis[u]) continue;
            vis[u] = true;

            for (const [v, w] of g[u]) {
                if (vis[v]) continue;

                const wNext = dist[u] + w;
                if (wNext < dist[v]) {
                    dist[v] = wNext;
                    pq.enqueue(v, dist[v]);
                }
            }
        }

        return dist.filter(d => d <= distanceThreshold).length;
    };

    let ans = n;
    let cnt = MAX;
    for (let i = n - 1; i >= 0; --i) {
        const t = dijkstra(i);
        if (t < cnt) {
            cnt = t;
            ans = i;
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
