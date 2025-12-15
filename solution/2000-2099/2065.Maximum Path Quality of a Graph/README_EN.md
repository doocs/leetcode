---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/README_EN.md
rating: 2178
source: Weekly Contest 266 Q4
tags:
    - Graph
    - Array
    - Backtracking
---

<!-- problem:start -->

# [2065. Maximum Path Quality of a Graph](https://leetcode.com/problems/maximum-path-quality-of-a-graph)

[中文文档](/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/README.md)

## Description

<!-- description:start -->

<p>There is an <strong>undirected</strong> graph with <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code> (<strong>inclusive</strong>). You are given a <strong>0-indexed</strong> integer array <code>values</code> where <code>values[i]</code> is the <strong>value </strong>of the <code>i<sup>th</sup></code> node. You are also given a <strong>0-indexed</strong> 2D integer array <code>edges</code>, where each <code>edges[j] = [u<sub>j</sub>, v<sub>j</sub>, time<sub>j</sub>]</code> indicates that there is an undirected edge between the nodes <code>u<sub>j</sub></code> and <code>v<sub>j</sub></code>,<sub> </sub>and it takes <code>time<sub>j</sub></code> seconds to travel between the two nodes. Finally, you are given an integer <code>maxTime</code>.</p>

<p>A <strong>valid</strong> <strong>path</strong> in the graph is any path that starts at node <code>0</code>, ends at node <code>0</code>, and takes <strong>at most</strong> <code>maxTime</code> seconds to complete. You may visit the same node multiple times. The <strong>quality</strong> of a valid path is the <strong>sum</strong> of the values of the <strong>unique nodes</strong> visited in the path (each node&#39;s value is added <strong>at most once</strong> to the sum).</p>

<p>Return <em>the <strong>maximum</strong> quality of a valid path</em>.</p>

<p><strong>Note:</strong> There are <strong>at most four</strong> edges connected to each node.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/images/ex1drawio.png" style="width: 269px; height: 170px;" />
<pre>
<strong>Input:</strong> values = [0,32,10,43], edges = [[0,1,10],[1,2,15],[0,3,10]], maxTime = 49
<strong>Output:</strong> 75
<strong>Explanation:</strong>
One possible path is 0 -&gt; 1 -&gt; 0 -&gt; 3 -&gt; 0. The total time taken is 10 + 10 + 10 + 10 = 40 &lt;= 49.
The nodes visited are 0, 1, and 3, giving a maximal path quality of 0 + 32 + 43 = 75.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/images/ex2drawio.png" style="width: 269px; height: 170px;" />
<pre>
<strong>Input:</strong> values = [5,10,15,20], edges = [[0,1,10],[1,2,10],[0,3,10]], maxTime = 30
<strong>Output:</strong> 25
<strong>Explanation:</strong>
One possible path is 0 -&gt; 3 -&gt; 0. The total time taken is 10 + 10 = 20 &lt;= 30.
The nodes visited are 0 and 3, giving a maximal path quality of 5 + 20 = 25.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2065.Maximum%20Path%20Quality%20of%20a%20Graph/images/ex31drawio.png" style="width: 236px; height: 170px;" />
<pre>
<strong>Input:</strong> values = [1,2,3,4], edges = [[0,1,10],[1,2,11],[2,3,12],[1,3,13]], maxTime = 50
<strong>Output:</strong> 7
<strong>Explanation:</strong>
One possible path is 0 -&gt; 1 -&gt; 3 -&gt; 1 -&gt; 0. The total time taken is 10 + 13 + 13 + 10 = 46 &lt;= 50.
The nodes visited are 0, 1, and 3, giving a maximal path quality of 1 + 2 + 4 = 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == values.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= values[i] &lt;= 10<sup>8</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 2000</code></li>
	<li><code>edges[j].length == 3 </code></li>
	<li><code>0 &lt;= u<sub>j </sub>&lt; v<sub>j</sub> &lt;= n - 1</code></li>
	<li><code>10 &lt;= time<sub>j</sub>, maxTime &lt;= 100</code></li>
	<li>All the pairs <code>[u<sub>j</sub>, v<sub>j</sub>]</code> are <strong>unique</strong>.</li>
	<li>There are <strong>at most four</strong> edges connected to each node.</li>
	<li>The graph may not be connected.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

We observe the data range of the problem and find that the number of edges in each valid path starting from $0$ does not exceed $\frac{\textit{maxTime}}{\min(time_j)} = \frac{100}{10} = 10$, and each node has at most four edges. Therefore, we can directly use naive DFS to brute-force search all valid paths.

First, we store the edges of the graph in the adjacency list $g$. Then, we design a function $\textit{dfs}(u, \textit{cost}, \textit{value})$, where $u$ represents the current node number, and $\textit{cost}$ and $\textit{value}$ respectively represent the cost time and value of the current path. Additionally, we use an array $\textit{vis}$ of length $n$ to record whether each node has been visited. Initially, we mark node $0$ as visited.

The logic of the function $\textit{dfs}(u, \textit{cost}, \textit{value})$ is as follows:

- If the current node number $u$ equals $0$, it means we have returned to the starting point, so we update the answer to $\max(\textit{ans}, \textit{value})$;
- For each neighbor node $v$ of the current node $u$, if the current path's cost time plus the time $t$ of the edge $(u, v)$ does not exceed $\textit{maxTime}$, then we can choose to continue visiting node $v$;
    - If node $v$ has already been visited, we directly recursively call $\textit{dfs}(v, \textit{cost} + t, \textit{value})$;
    - If node $v$ has not been visited, we mark node $v$ as visited, then recursively call $\textit{dfs}(v, \textit{cost} + t, \textit{value} + \textit{values}[v])$, and finally restore the visit status of node $v$.

In the main function, we call $\textit{dfs}(0, 0, \textit{values}[0])$ and return the answer $\textit{ans}$.

The time complexity is $O(n + m + 4^{\frac{\textit{maxTime}}{\min(time_j)}})$, and the space complexity is $O(n + m + \frac{\textit{maxTime}}{\min(time_j)})$. Here, $n$ and $m$ respectively represent the number of nodes and edges.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximalPathQuality(
        self, values: List[int], edges: List[List[int]], maxTime: int
    ) -> int:
        def dfs(u: int, cost: int, value: int):
            if u == 0:
                nonlocal ans
                ans = max(ans, value)
            for v, t in g[u]:
                if cost + t <= maxTime:
                    if vis[v]:
                        dfs(v, cost + t, value)
                    else:
                        vis[v] = True
                        dfs(v, cost + t, value + values[v])
                        vis[v] = False

        n = len(values)
        g = [[] for _ in range(n)]
        for u, v, t in edges:
            g[u].append((v, t))
            g[v].append((u, t))
        vis = [False] * n
        vis[0] = True
        ans = 0
        dfs(0, 0, values[0])
        return ans
```

#### Java

```java
class Solution {
    private List<int[]>[] g;
    private boolean[] vis;
    private int[] values;
    private int maxTime;
    private int ans;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1], t = e[2];
            g[u].add(new int[] {v, t});
            g[v].add(new int[] {u, t});
        }
        vis = new boolean[n];
        vis[0] = true;
        this.values = values;
        this.maxTime = maxTime;
        dfs(0, 0, values[0]);
        return ans;
    }

    private void dfs(int u, int cost, int value) {
        if (u == 0) {
            ans = Math.max(ans, value);
        }
        for (var e : g[u]) {
            int v = e[0], t = e[1];
            if (cost + t <= maxTime) {
                if (vis[v]) {
                    dfs(v, cost + t, value);
                } else {
                    vis[v] = true;
                    dfs(v, cost + t, value + values[v]);
                    vis[v] = false;
                }
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximalPathQuality(vector<int>& values, vector<vector<int>>& edges, int maxTime) {
        int n = values.size();
        vector<pair<int, int>> g[n];
        for (auto& e : edges) {
            int u = e[0], v = e[1], t = e[2];
            g[u].emplace_back(v, t);
            g[v].emplace_back(u, t);
        }
        bool vis[n];
        memset(vis, false, sizeof(vis));
        vis[0] = true;
        int ans = 0;
        auto dfs = [&](this auto&& dfs, int u, int cost, int value) -> void {
            if (u == 0) {
                ans = max(ans, value);
            }
            for (auto& [v, t] : g[u]) {
                if (cost + t <= maxTime) {
                    if (vis[v]) {
                        dfs(v, cost + t, value);
                    } else {
                        vis[v] = true;
                        dfs(v, cost + t, value + values[v]);
                        vis[v] = false;
                    }
                }
            }
        };
        dfs(0, 0, values[0]);
        return ans;
    }
};
```

#### Go

```go
func maximalPathQuality(values []int, edges [][]int, maxTime int) (ans int) {
	n := len(values)
	g := make([][][2]int, n)
	for _, e := range edges {
		u, v, t := e[0], e[1], e[2]
		g[u] = append(g[u], [2]int{v, t})
		g[v] = append(g[v], [2]int{u, t})
	}
	vis := make([]bool, n)
	vis[0] = true
	var dfs func(u, cost, value int)
	dfs = func(u, cost, value int) {
		if u == 0 {
			ans = max(ans, value)
		}
		for _, e := range g[u] {
			v, t := e[0], e[1]
			if cost+t <= maxTime {
				if vis[v] {
					dfs(v, cost+t, value)
				} else {
					vis[v] = true
					dfs(v, cost+t, value+values[v])
					vis[v] = false
				}
			}
		}
	}
	dfs(0, 0, values[0])
	return
}
```

#### TypeScript

```ts
function maximalPathQuality(values: number[], edges: number[][], maxTime: number): number {
    const n = values.length;
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [u, v, t] of edges) {
        g[u].push([v, t]);
        g[v].push([u, t]);
    }
    const vis: boolean[] = Array(n).fill(false);
    vis[0] = true;
    let ans = 0;
    const dfs = (u: number, cost: number, value: number) => {
        if (u === 0) {
            ans = Math.max(ans, value);
        }
        for (const [v, t] of g[u]) {
            if (cost + t <= maxTime) {
                if (vis[v]) {
                    dfs(v, cost + t, value);
                } else {
                    vis[v] = true;
                    dfs(v, cost + t, value + values[v]);
                    vis[v] = false;
                }
            }
        }
    };
    dfs(0, 0, values[0]);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
