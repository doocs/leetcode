---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/README_EN.md
tags:
    - Graph
    - Topological Sort
---

<!-- problem:start -->

# [1059. All Paths from Source Lead to Destination 🔒](https://leetcode.com/problems/all-paths-from-source-lead-to-destination)

[中文文档](/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/README.md)

## Description

<!-- description:start -->

<p>Given the <code>edges</code> of a directed graph where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>, and two nodes <code>source</code> and <code>destination</code> of this graph, determine whether or not all paths starting from <code>source</code> eventually, end at <code>destination</code>, that is:</p>

<ul>
	<li>At least one path exists from the <code>source</code> node to the <code>destination</code> node</li>
	<li>If a path exists from the <code>source</code> node to a node with no outgoing edges, then that node is equal to <code>destination</code>.</li>
	<li>The number of possible paths from <code>source</code> to <code>destination</code> is a finite number.</li>
</ul>

<p>Return <code>true</code> if and only if all roads from <code>source</code> lead to <code>destination</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/images/485_example_1.png" style="width: 200px; height: 208px;" />
<pre>
<strong>Input:</strong> n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
<strong>Output:</strong> false
<strong>Explanation:</strong> It is possible to reach and get stuck on both node 1 and node 2.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/images/485_example_2.png" style="width: 200px; height: 230px;" />
<pre>
<strong>Input:</strong> n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
<strong>Output:</strong> false
<strong>Explanation:</strong> We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/images/485_example_3.png" style="width: 200px; height: 183px;" />
<pre>
<strong>Input:</strong> n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>4</sup></code></li>
	<li><code>edges.length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>0 &lt;= source &lt;= n - 1</code></li>
	<li><code>0 &lt;= destination &lt;= n - 1</code></li>
	<li>The given graph may have self-loops and parallel edges.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

We use a state array $\textit{state}$ to record the status of each node, where:

- State 0 indicates the node has not been visited;
- State 1 indicates the node is currently being visited;
- State 2 indicates the node has been visited and can lead to the destination.

First, we build the graph as an adjacency list, then perform a depth-first search (DFS) starting from the source node. During the DFS process:

- If the current node's state is 1, it means we have encountered a cycle, and we return $\text{false}$ directly;
- If the current node's state is 2, it means the node has been visited and can lead to the destination, and we return $\text{true}$ directly;
- If the current node has no outgoing edges, we check whether it is the destination node. If so, return $\text{true}$; otherwise, return $\text{false}$;
- Otherwise, set the current node's state to 1 and recursively visit all adjacent nodes;
- If all adjacent nodes can lead to the destination, set the current node's state to 2 and return $\text{true}$; otherwise, return $\text{false}$.

The answer is the result of $\text{dfs}(\text{source})$.

The time complexity is $O(n + m)$, where $n$ and $m$ are the number of nodes and edges, respectively. The space complexity is $O(n + m)$, used to store the graph's adjacency list and state array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def leadsToDestination(
        self, n: int, edges: List[List[int]], source: int, destination: int
    ) -> bool:
        def dfs(i: int) -> bool:
            if st[i]:
                return st[i] == 2
            if not g[i]:
                return i == destination

            st[i] = 1
            for j in g[i]:
                if not dfs(j):
                    return False
            st[i] = 2
            return True

        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
        if g[destination]:
            return False

        st = [0] * n
        return dfs(source)
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private int[] st;
    private int destination;

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        this.destination = destination;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
        }
        if (!g[destination].isEmpty()) {
            return false;
        }
        st = new int[n];
        return dfs(source);
    }

    private boolean dfs(int i) {
        if (st[i] != 0) {
            return st[i] == 2;
        }
        if (g[i].isEmpty()) {
            return i == destination;
        }
        st[i] = 1;
        for (int j : g[i]) {
            if (!dfs(j)) {
                return false;
            }
        }
        st[i] = 2;
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> g;
    vector<int> st;
    int destination;

    bool leadsToDestination(int n, vector<vector<int>>& edges, int source, int destination) {
        this->destination = destination;
        g.assign(n, {});
        for (auto& e : edges) {
            g[e[0]].push_back(e[1]);
        }
        if (!g[destination].empty()) {
            return false;
        }
        st.assign(n, 0);
        return dfs(source);
    }

    bool dfs(int i) {
        if (st[i] != 0) {
            return st[i] == 2;
        }
        if (g[i].empty()) {
            return i == destination;
        }
        st[i] = 1;
        for (int j : g[i]) {
            if (!dfs(j)) {
                return false;
            }
        }
        st[i] = 2;
        return true;
    }
};
```

#### Go

```go
func leadsToDestination(n int, edges [][]int, source int, destination int) bool {
	g := make([][]int, n)
	for _, e := range edges {
		g[e[0]] = append(g[e[0]], e[1])
	}
	if len(g[destination]) > 0 {
		return false
	}

	st := make([]int, n)

	var dfs func(i int) bool
	dfs = func(i int) bool {
		if st[i] != 0 {
			return st[i] == 2
		}
		if len(g[i]) == 0 {
			return i == destination
		}
		st[i] = 1
		for _, j := range g[i] {
			if !dfs(j) {
				return false
			}
		}
		st[i] = 2
		return true
	}

	return dfs(source)
}
```

#### TypeScript

```ts
function leadsToDestination(
    n: number,
    edges: number[][],
    source: number,
    destination: number,
): boolean {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
    }
    if (g[destination].length > 0) {
        return false;
    }

    const st: number[] = Array(n).fill(0);

    const dfs = (i: number): boolean => {
        if (st[i] !== 0) {
            return st[i] === 2;
        }
        if (g[i].length === 0) {
            return i === destination;
        }
        st[i] = 1;
        for (const j of g[i]) {
            if (!dfs(j)) {
                return false;
            }
        }
        st[i] = 2;
        return true;
    };

    return dfs(source);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
