---
comments: true
difficulty: Medium
rating: 1682
source: Weekly Contest 188 Q3
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Hash Table
    - Tree DP
---

<!-- problem:start -->

# [1443. Minimum Time to Collect All Apples in a Tree](https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree)

[中文文档](/solution/1400-1499/1443.Minimum%20Time%20to%20Collect%20All%20Apples%20in%20a%20Tree/README.md)

## Description

<!-- description:start -->

<p>Given an undirected tree consisting of <code>n</code> vertices numbered from <code>0</code> to <code>n-1</code>, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. <em>Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at <strong>vertex 0</strong> and coming back to this vertex.</em></p>

<p>The edges of the undirected tree are given in the array <code>edges</code>, where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> means that exists an edge connecting the vertices <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>. Additionally, there is a boolean array <code>hasApple</code>, where <code>hasApple[i] = true</code> means that vertex <code>i</code> has an apple; otherwise, it does not have any apple.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1443.Minimum%20Time%20to%20Collect%20All%20Apples%20in%20a%20Tree/images/min_time_collect_apple_1.png" style="width: 300px; height: 212px;" />
<pre>
<strong>Input:</strong> n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
<strong>Output:</strong> 8 
<strong>Explanation:</strong> The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1443.Minimum%20Time%20to%20Collect%20All%20Apples%20in%20a%20Tree/images/min_time_collect_apple_2.png" style="width: 300px; height: 212px;" />
<pre>
<strong>Input:</strong> n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub> &lt; b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>hasApple.length == n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> We start at $0$, collect every apple, and return, so each used edge is traversed twice. $n\le 10^5$ allows one DFS.
>
> A subtree with no apple and no further work can be skipped. Sum the children's costs (entering a child costs $2$), and add the incoming edge only if this node has an apple or a child contributed a positive cost. The root's incoming cost is $0$.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minTime(self, n: int, edges: List[List[int]], hasApple: List[bool]) -> int:
        def dfs(u, cost):
            if vis[u]:
                return 0
            vis[u] = True
            nxt_cost = 0
            for v in g[u]:
                nxt_cost += dfs(v, 2)
            if not hasApple[u] and nxt_cost == 0:
                return 0
            return cost + nxt_cost

        g = defaultdict(list)
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        vis = [False] * n
        return dfs(0, 0)
```

#### Java

```java
class Solution {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        boolean[] vis = new boolean[n];
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        return dfs(0, 0, g, hasApple, vis);
    }

    private int dfs(int u, int cost, List<Integer>[] g, List<Boolean> hasApple, boolean[] vis) {
        if (vis[u]) {
            return 0;
        }
        vis[u] = true;
        int nxtCost = 0;
        for (int v : g[u]) {
            nxtCost += dfs(v, 2, g, hasApple, vis);
        }
        if (!hasApple.get(u) && nxtCost == 0) {
            return 0;
        }
        return cost + nxtCost;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minTime(int n, vector<vector<int>>& edges, vector<bool>& hasApple) {
        vector<bool> vis(n);
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        return dfs(0, 0, g, hasApple, vis);
    }

    int dfs(int u, int cost, vector<vector<int>>& g, vector<bool>& hasApple, vector<bool>& vis) {
        if (vis[u]) return 0;
        vis[u] = true;
        int nxt = 0;
        for (int& v : g[u]) nxt += dfs(v, 2, g, hasApple, vis);
        if (!hasApple[u] && !nxt) return 0;
        return cost + nxt;
    }
};
```

#### Go

```go
func minTime(n int, edges [][]int, hasApple []bool) int {
	vis := make([]bool, n)
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	var dfs func(int, int) int
	dfs = func(u, cost int) int {
		if vis[u] {
			return 0
		}
		vis[u] = true
		nxt := 0
		for _, v := range g[u] {
			nxt += dfs(v, 2)
		}
		if !hasApple[u] && nxt == 0 {
			return 0
		}
		return cost + nxt
	}
	return dfs(0, 0)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Explicit Stack

<!-- thinking:start -->

> **Thinking**
>
> We start at $0$, collect every apple, and return, so each used edge is walked twice. With $n \le 10^5$, recursing from the root to score every subtree is too deep: a chain makes the call depth $n$.
>
> A subtree with no apple and no further work can be skipped. After the children are done, add their round-trip costs; if this node has an apple or that sum is positive, add the cost of the incoming edge, which is $0$ at the root and $2$ elsewhere.
>
> An explicit stack of $(node, parent, state)$ runs the postorder. On entry we push the exit marker and then the children, and on exit we store that cost. The root's cost is the answer.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minTime(self, n: int, edges: List[List[int]], hasApple: List[bool]) -> int:
        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        cost = [0] * n
        stk = [(0, -1, 0)]
        while stk:
            u, fa, state = stk.pop()
            if state == 0:
                stk.append((u, fa, 1))
                for v in g[u]:
                    if v != fa:
                        stk.append((v, u, 0))
            else:
                nxt = 0
                for v in g[u]:
                    if v != fa:
                        nxt += cost[v]
                if hasApple[u] or nxt:
                    cost[u] = nxt if u == 0 else nxt + 2
        return cost[0]
```

#### Java

```java
class Solution {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        int[] cost = new int[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int u = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push(new int[] {u, fa, 1});
                for (int v : g[u]) {
                    if (v != fa) {
                        stk.push(new int[] {v, u, 0});
                    }
                }
            } else {
                int nxt = 0;
                for (int v : g[u]) {
                    if (v != fa) {
                        nxt += cost[v];
                    }
                }
                if (hasApple.get(u) || nxt > 0) {
                    cost[u] = u == 0 ? nxt : nxt + 2;
                }
            }
        }
        return cost[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minTime(int n, vector<vector<int>>& edges, vector<bool>& hasApple) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        vector<int> cost(n);
        vector<array<int, 3>> stk{{0, -1, 0}};
        while (!stk.empty()) {
            auto [u, fa, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({u, fa, 1});
                for (int v : g[u]) {
                    if (v != fa) {
                        stk.push_back({v, u, 0});
                    }
                }
            } else {
                int nxt = 0;
                for (int v : g[u]) {
                    if (v != fa) {
                        nxt += cost[v];
                    }
                }
                if (hasApple[u] || nxt) {
                    cost[u] = u == 0 ? nxt : nxt + 2;
                }
            }
        }
        return cost[0];
    }
};
```

#### Go

```go
func minTime(n int, edges [][]int, hasApple []bool) int {
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	cost := make([]int, n)
	stk := [][3]int{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		u, fa, state := cur[0], cur[1], cur[2]
		if state == 0 {
			stk = append(stk, [3]int{u, fa, 1})
			for _, v := range g[u] {
				if v != fa {
					stk = append(stk, [3]int{v, u, 0})
				}
			}
		} else {
			nxt := 0
			for _, v := range g[u] {
				if v != fa {
					nxt += cost[v]
				}
			}
			if hasApple[u] || nxt > 0 {
				if u == 0 {
					cost[u] = nxt
				} else {
					cost[u] = nxt + 2
				}
			}
		}
	}
	return cost[0]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
