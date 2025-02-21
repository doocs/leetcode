---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0785.Is%20Graph%20Bipartite/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Graph
---

<!-- problem:start -->

# [785. Is Graph Bipartite](https://leetcode.com/problems/is-graph-bipartite)

[中文文档](/solution/0700-0799/0785.Is%20Graph%20Bipartite/README.md)

## Description

<!-- description:start -->

<p>There is an <strong>undirected</strong> graph with <code>n</code> nodes, where each node is numbered between <code>0</code> and <code>n - 1</code>. You are given a 2D array <code>graph</code>, where <code>graph[u]</code> is an array of nodes that node <code>u</code> is adjacent to. More formally, for each <code>v</code> in <code>graph[u]</code>, there is an undirected edge between node <code>u</code> and node <code>v</code>. The graph has the following properties:</p>

<ul>
	<li>There are no self-edges (<code>graph[u]</code> does not contain <code>u</code>).</li>
	<li>There are no parallel edges (<code>graph[u]</code> does not contain duplicate values).</li>
	<li>If <code>v</code> is in <code>graph[u]</code>, then <code>u</code> is in <code>graph[v]</code> (the graph is undirected).</li>
	<li>The graph may not be connected, meaning there may be two nodes <code>u</code> and <code>v</code> such that there is no path between them.</li>
</ul>

<p>A graph is <strong>bipartite</strong> if the nodes can be partitioned into two independent sets <code>A</code> and <code>B</code> such that <strong>every</strong> edge in the graph connects a node in set <code>A</code> and a node in set <code>B</code>.</p>

<p>Return <code>true</code><em> if and only if it is <strong>bipartite</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0785.Is%20Graph%20Bipartite/images/bi2.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>Input:</strong> graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0785.Is%20Graph%20Bipartite/images/bi1.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>Input:</strong> graph = [[1,3],[0,2],[1,3],[0,2]]
<strong>Output:</strong> true
<strong>Explanation:</strong> We can partition the nodes into two sets: {0, 2} and {1, 3}.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>graph.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= graph[u].length &lt; n</code></li>
	<li><code>0 &lt;= graph[u][i] &lt;= n - 1</code></li>
	<li><code>graph[u]</code>&nbsp;does not contain&nbsp;<code>u</code>.</li>
	<li>All the values of <code>graph[u]</code> are <strong>unique</strong>.</li>
	<li>If <code>graph[u]</code> contains <code>v</code>, then <code>graph[v]</code> contains <code>u</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Coloring Method to Determine Bipartite Graph

Traverse all nodes for coloring. For example, initially color them white, and use DFS to color the adjacent nodes with another color. If the target color to be colored is different from the color that the node has already been colored, it means that it cannot form a bipartite graph.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the number of nodes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        def dfs(a: int, c: int) -> bool:
            color[a] = c
            for b in graph[a]:
                if color[b] == c or (color[b] == 0 and not dfs(b, -c)):
                    return False
            return True

        n = len(graph)
        color = [0] * n
        for i in range(n):
            if color[i] == 0 and not dfs(i, 1):
                return False
        return True
```

#### Java

```java
class Solution {
    private int[] color;
    private int[][] g;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new int[n];
        g = graph;
        for (int i = 0; i < n; ++i) {
            if (color[i] == 0 && !dfs(i, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int a, int c) {
        color[a] = c;
        for (int b : g[a]) {
            if (color[b] == c || (color[b] == 0 && !dfs(b, -c))) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isBipartite(vector<vector<int>>& graph) {
        int n = graph.size();
        vector<int> color(n);
        for (int i = 0; i < n; ++i)
            if (!color[i] && !dfs(i, 1, color, graph))
                return false;
        return true;
    }

    bool dfs(int u, int c, vector<int>& color, vector<vector<int>>& g) {
        color[u] = c;
        for (int& v : g[u]) {
            if (!color[v]) {
                if (!dfs(v, 3 - c, color, g)) return false;
            } else if (color[v] == c)
                return false;
        }
        return true;
    }
};
```

#### Go

```go
func isBipartite(graph [][]int) bool {
	n := len(graph)
	color := make([]int, n)
	var dfs func(int, int) bool
	dfs = func(a, c int) bool {
		color[a] = c
		for _, b := range graph[a] {
			if color[b] == c || (color[b] == 0 && !dfs(b, -c)) {
				return false
			}
		}
		return true
	}
	for i := range graph {
		if color[i] == 0 && !dfs(i, 1) {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function isBipartite(graph: number[][]): boolean {
    const n = graph.length;
    const color: number[] = Array(n).fill(0);
    const dfs = (a: number, c: number): boolean => {
        color[a] = c;
        for (const b of graph[a]) {
            if (color[b] === c || (color[b] === 0 && !dfs(b, -c))) {
                return false;
            }
        }
        return true;
    };
    for (let i = 0; i < n; i++) {
        if (color[i] === 0 && !dfs(i, 1)) {
            return false;
        }
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_bipartite(graph: Vec<Vec<i32>>) -> bool {
        let n = graph.len();
        let mut color = vec![0; n];

        fn dfs(a: usize, c: i32, graph: &Vec<Vec<i32>>, color: &mut Vec<i32>) -> bool {
            color[a] = c;
            for &b in &graph[a] {
                if color[b as usize] == c
                    || (color[b as usize] == 0 && !dfs(b as usize, -c, graph, color))
                {
                    return false;
                }
            }
            true
        }

        for i in 0..n {
            if color[i] == 0 && !dfs(i, 1, &graph, &mut color) {
                return false;
            }
        }
        true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Union-Find

For this problem, if it is a bipartite graph, then all adjacent nodes of each vertex in the graph should belong to the same set and not be in the same set as the vertex. Therefore, we can use the union-find method. Traverse each vertex in the graph, and if it is found that the current vertex and its corresponding adjacent nodes are in the same set, it means that it is not a bipartite graph. Otherwise, merge the adjacent nodes of the current node.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the number of nodes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(len(graph)))
        for a, bs in enumerate(graph):
            for b in bs:
                pa, pb = find(a), find(b)
                if pa == pb:
                    return False
                p[pb] = find(bs[0])
        return True
```

#### Java

```java
class Solution {
    private int[] p;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int a = 0; a < n; ++a) {
            for (int b : graph[a]) {
                int pa = find(a), pb = find(b);
                if (pa == pb) {
                    return false;
                }
                p[pb] = find(graph[a][0]);
            }
        }
        return true;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isBipartite(vector<vector<int>>& graph) {
        int n = graph.size();
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        auto find = [&](this auto&& find, int x) -> int {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        for (int a = 0; a < n; ++a) {
            for (int b : graph[a]) {
                int pa = find(a), pb = find(b);
                if (pa == pb) {
                    return false;
                }
                p[pb] = find(graph[a][0]);
            }
        }
        return true;
    }
};
```

#### Go

```go
func isBipartite(graph [][]int) bool {
	n := len(graph)
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for a, bs := range graph {
		for _, b := range bs {
			pa, pb := find(a), find(b)
			if pa == pb {
				return false
			}
			p[pb] = find(bs[0])
		}
	}
	return true
}
```

#### TypeScript

```ts
function isBipartite(graph: number[][]): boolean {
    const n = graph.length;
    const p: number[] = Array.from({ length: n }, (_, i) => i);
    const find = (x: number): number => {
        if (x !== p[x]) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    for (let a = 0; a < n; ++a) {
        for (const b of graph[a]) {
            const [pa, pb] = [find(a), find(b)];
            if (pa === pb) {
                return false;
            }
            p[pb] = find(graph[a][0]);
        }
    }
    return true;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_bipartite(graph: Vec<Vec<i32>>) -> bool {
        let n = graph.len();
        let mut p: Vec<usize> = (0..n).collect();

        fn find(x: usize, p: &mut Vec<usize>) -> usize {
            if p[x] != x {
                p[x] = find(p[x], p);
            }
            p[x]
        }

        for a in 0..n {
            for &b in &graph[a] {
                let pa = find(a, &mut p);
                let pb = find(b as usize, &mut p);
                if pa == pb {
                    return false;
                }
                p[pb] = find(graph[a][0] as usize, &mut p);
            }
        }
        true
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
