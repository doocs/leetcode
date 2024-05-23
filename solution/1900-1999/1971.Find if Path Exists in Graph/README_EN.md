---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1971.Find%20if%20Path%20Exists%20in%20Graph/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Graph
---

<!-- problem:start -->

# [1971. Find if Path Exists in Graph](https://leetcode.com/problems/find-if-path-exists-in-graph)

[中文文档](/solution/1900-1999/1971.Find%20if%20Path%20Exists%20in%20Graph/README.md)

## Description

<!-- description:start -->

<p>There is a <strong>bi-directional</strong> graph with <code>n</code> vertices, where each vertex is labeled from <code>0</code> to <code>n - 1</code> (<strong>inclusive</strong>). The edges in the graph are represented as a 2D integer array <code>edges</code>, where each <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> denotes a bi-directional edge between vertex <code>u<sub>i</sub></code> and vertex <code>v<sub>i</sub></code>. Every vertex pair is connected by <strong>at most one</strong> edge, and no vertex has an edge to itself.</p>

<p>You want to determine if there is a <strong>valid path</strong> that exists from vertex <code>source</code> to vertex <code>destination</code>.</p>

<p>Given <code>edges</code> and the integers <code>n</code>, <code>source</code>, and <code>destination</code>, return <code>true</code><em> if there is a <strong>valid path</strong> from </em><code>source</code><em> to </em><code>destination</code><em>, or </em><code>false</code><em> otherwise</em><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1971.Find%20if%20Path%20Exists%20in%20Graph/images/validpath-ex1.png" style="width: 141px; height: 121px;" />
<pre>
<strong>Input:</strong> n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
<strong>Output:</strong> true
<strong>Explanation:</strong> There are two paths from vertex 0 to vertex 2:
- 0 &rarr; 1 &rarr; 2
- 0 &rarr; 2
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1971.Find%20if%20Path%20Exists%20in%20Graph/images/validpath-ex2.png" style="width: 281px; height: 141px;" />
<pre>
<strong>Input:</strong> n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no path from vertex 0 to vertex 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>0 &lt;= source, destination &lt;= n - 1</code></li>
	<li>There are no duplicate edges.</li>
	<li>There are no self edges.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def validPath(
        self, n: int, edges: List[List[int]], source: int, destination: int
    ) -> bool:
        def dfs(i):
            if i == destination:
                return True
            vis.add(i)
            for j in g[i]:
                if j not in vis and dfs(j):
                    return True
            return False

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = set()
        return dfs(source)
```

#### Java

```java
class Solution {
    private boolean[] vis;
    private List<Integer>[] g;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        vis = new boolean[n];
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        return dfs(source, destination);
    }

    private boolean dfs(int source, int destination) {
        if (source == destination) {
            return true;
        }
        vis[source] = true;
        for (int nxt : g[source]) {
            if (!vis[nxt] && dfs(nxt, destination)) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool validPath(int n, vector<vector<int>>& edges, int source, int destination) {
        vector<bool> vis(n);
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        function<bool(int)> dfs = [&](int i) -> bool {
            if (i == destination) return true;
            vis[i] = true;
            for (int& j : g[i]) {
                if (!vis[j] && dfs(j)) {
                    return true;
                }
            }
            return false;
        };
        return dfs(source);
    }
};
```

#### Go

```go
func validPath(n int, edges [][]int, source int, destination int) bool {
	vis := make([]bool, n)
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var dfs func(int) bool
	dfs = func(i int) bool {
		if i == destination {
			return true
		}
		vis[i] = true
		for _, j := range g[i] {
			if !vis[j] && dfs(j) {
				return true
			}
		}
		return false
	}
	return dfs(source)
}
```

#### Rust

```rust
impl Solution {
    pub fn valid_path(n: i32, edges: Vec<Vec<i32>>, source: i32, destination: i32) -> bool {
        let mut disjoint_set: Vec<i32> = vec![0; n as usize];
        // Initialize the set
        for i in 0..n {
            disjoint_set[i as usize] = i;
        }

        // Traverse the edges
        for p_vec in &edges {
            let parent_one = Solution::find(p_vec[0], &mut disjoint_set);
            let parent_two = Solution::find(p_vec[1], &mut disjoint_set);
            disjoint_set[parent_one as usize] = parent_two;
        }

        let p_s = Solution::find(source, &mut disjoint_set);
        let p_d = Solution::find(destination, &mut disjoint_set);

        p_s == p_d
    }

    pub fn find(x: i32, d_set: &mut Vec<i32>) -> i32 {
        if d_set[x as usize] != x {
            d_set[x as usize] = Solution::find(d_set[x as usize], d_set);
        }
        d_set[x as usize]
    }
}
```

#### TypeScript

```ts
export function validPath(
    n: number,
    edges: number[][],
    source: number,
    destination: number,
): boolean {
    const graph: number[][] = Array.from({ length: n }, () => []);

    for (const [a, b] of edges) {
        graph[a].push(b);
        graph[b].push(a);
    }

    const seen = new Set<number>();
    const dfs = (i: number) => {
        if (i === destination) return true;
        if (seen.has(i)) return false;

        seen.add(i);
        return graph[i].some(dfs);
    };

    return dfs(source);
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
    def validPath(
        self, n: int, edges: List[List[int]], source: int, destination: int
    ) -> bool:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(n))
        for u, v in edges:
            p[find(u)] = find(v)
        return find(source) == find(destination)
```

#### Java

```java
class Solution {
    private int[] p;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int[] e : edges) {
            p[find(e[0])] = find(e[1]);
        }
        return find(source) == find(destination);
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
    bool validPath(int n, vector<vector<int>>& edges, int source, int destination) {
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        function<int(int)> find = [&](int x) -> int {
            if (p[x] != x) p[x] = find(p[x]);
            return p[x];
        };
        for (auto& e : edges) p[find(e[0])] = find(e[1]);
        return find(source) == find(destination);
    }
};
```

#### Go

```go
func validPath(n int, edges [][]int, source int, destination int) bool {
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
	for _, e := range edges {
		p[find(e[0])] = find(e[1])
	}
	return find(source) == find(destination)
}
```

#### TypeScript

```ts
export function validPath(
    n: number,
    edges: number[][],
    source: number,
    destination: number,
): boolean {
    const graph: number[][] = Array.from({ length: n }, () => []);

    for (const [a, b] of edges) {
        graph[a].push(b);
        graph[b].push(a);
    }

    const seen = new Set<number>();
    const q = [source];

    while (q.length) {
        const i = q.pop()!;

        if (i === destination) return true;
        if (seen.has(i)) continue;

        seen.add(i);
        q.push(...graph[i]);
    }

    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
