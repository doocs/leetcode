---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1192.Critical%20Connections%20in%20a%20Network/README_EN.md
rating: 2084
tags:
    - Depth-First Search
    - Graph
    - Biconnected Component
---

# [1192. Critical Connections in a Network](https://leetcode.com/problems/critical-connections-in-a-network)

[中文文档](/solution/1100-1199/1192.Critical%20Connections%20in%20a%20Network/README.md)

## Description

<p>There are <code>n</code> servers numbered from <code>0</code> to <code>n - 1</code> connected by undirected server-to-server <code>connections</code> forming a network where <code>connections[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> represents a connection between servers <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>. Any server can reach other servers directly or indirectly through the network.</p>

<p>A <em>critical connection</em> is a connection that, if removed, will make some servers unable to reach some other server.</p>

<p>Return all critical connections in the network in any order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1192.Critical%20Connections%20in%20a%20Network/images/1537_ex1_2.png" style="width: 198px; height: 248px;" />
<pre>
<strong>Input:</strong> n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
<strong>Output:</strong> [[1,3]]
<strong>Explanation:</strong> [[3,1]] is also accepted.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, connections = [[0,1]]
<strong>Output:</strong> [[0,1]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n - 1 &lt;= connections.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>There are no repeated connections.</li>
</ul>

## Solutions

### Solution 1: Tarjan Algorithm

The "critical connections" in this problem can be considered as "bridges".

"Bridges": In a connected undirected graph, if removing a certain edge makes the graph disconnected, then this edge can be considered as a "bridge".

Correspondingly, there is also the concept of "articulation points".

"Articulation Points": In a connected undirected graph, if removing a certain point and all edges connected to it makes the graph disconnected, then this point can be considered as an "articulation point".

There is an algorithm called the Tarjan algorithm for finding "bridges" and "articulation points" in a graph. This algorithm uses a depth-first search (DFS) method that first recursively visits adjacent nodes and then visits the node itself. By recording the "order of visit: DFN" and updating the "earliest backtrackable node: low" when visiting the node itself after recursion ends, it can find the "bridges" and "articulation points" of the graph in $O(n)$ time. Also, this algorithm can be used to find strongly connected components in directed graphs.

<!-- tabs:start -->

```python
class Solution:
    def criticalConnections(
        self, n: int, connections: List[List[int]]
    ) -> List[List[int]]:
        def tarjan(a: int, fa: int):
            nonlocal now
            now += 1
            dfn[a] = low[a] = now
            for b in g[a]:
                if b == fa:
                    continue
                if not dfn[b]:
                    tarjan(b, a)
                    low[a] = min(low[a], low[b])
                    if low[b] > dfn[a]:
                        ans.append([a, b])
                else:
                    low[a] = min(low[a], dfn[b])

        g = [[] for _ in range(n)]
        for a, b in connections:
            g[a].append(b)
            g[b].append(a)

        dfn = [0] * n
        low = [0] * n
        now = 0
        ans = []
        tarjan(0, -1)
        return ans
```

```java
class Solution {
    private int now;
    private List<Integer>[] g;
    private List<List<Integer>> ans = new ArrayList<>();
    private int[] dfn;
    private int[] low;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        dfn = new int[n];
        low = new int[n];
        for (var e : connections) {
            int a = e.get(0), b = e.get(1);
            g[a].add(b);
            g[b].add(a);
        }
        tarjan(0, -1);
        return ans;
    }

    private void tarjan(int a, int fa) {
        dfn[a] = low[a] = ++now;
        for (int b : g[a]) {
            if (b == fa) {
                continue;
            }
            if (dfn[b] == 0) {
                tarjan(b, a);
                low[a] = Math.min(low[a], low[b]);
                if (low[b] > dfn[a]) {
                    ans.add(List.of(a, b));
                }
            } else {
                low[a] = Math.min(low[a], dfn[b]);
            }
        }
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> criticalConnections(int n, vector<vector<int>>& connections) {
        int now = 0;
        vector<int> dfn(n);
        vector<int> low(n);
        vector<int> g[n];
        for (auto& e : connections) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<vector<int>> ans;
        function<void(int, int)> tarjan = [&](int a, int fa) -> void {
            dfn[a] = low[a] = ++now;
            for (int b : g[a]) {
                if (b == fa) {
                    continue;
                }
                if (!dfn[b]) {
                    tarjan(b, a);
                    low[a] = min(low[a], low[b]);
                    if (low[b] > dfn[a]) {
                        ans.push_back({a, b});
                    }
                } else {
                    low[a] = min(low[a], dfn[b]);
                }
            }
        };
        tarjan(0, -1);
        return ans;
    }
};
```

```go
func criticalConnections(n int, connections [][]int) (ans [][]int) {
	now := 0
	g := make([][]int, n)
	dfn := make([]int, n)
	low := make([]int, n)
	for _, e := range connections {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var tarjan func(int, int)
	tarjan = func(a, fa int) {
		now++
		dfn[a], low[a] = now, now
		for _, b := range g[a] {
			if b == fa {
				continue
			}
			if dfn[b] == 0 {
				tarjan(b, a)
				low[a] = min(low[a], low[b])
				if low[b] > dfn[a] {
					ans = append(ans, []int{a, b})
				}
			} else {
				low[a] = min(low[a], dfn[b])
			}
		}
	}
	tarjan(0, -1)
	return
}
```

```ts
function criticalConnections(n: number, connections: number[][]): number[][] {
    let now: number = 0;
    const g: number[][] = Array(n)
        .fill(0)
        .map(() => []);
    const dfn: number[] = Array(n).fill(0);
    const low: number[] = Array(n).fill(0);
    for (const [a, b] of connections) {
        g[a].push(b);
        g[b].push(a);
    }
    const ans: number[][] = [];
    const tarjan = (a: number, fa: number) => {
        dfn[a] = low[a] = ++now;
        for (const b of g[a]) {
            if (b === fa) {
                continue;
            }
            if (!dfn[b]) {
                tarjan(b, a);
                low[a] = Math.min(low[a], low[b]);
                if (low[b] > dfn[a]) {
                    ans.push([a, b]);
                }
            } else {
                low[a] = Math.min(low[a], dfn[b]);
            }
        }
    };
    tarjan(0, -1);
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
