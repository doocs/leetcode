---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/04.01.Route%20Between%20Nodes/README_EN.md
---

<!-- problem:start -->

# [04.01. Route Between Nodes](https://leetcode.cn/problems/route-between-nodes-lcci)

[中文文档](/lcci/04.01.Route%20Between%20Nodes/README.md)

## Description

<!-- description:start -->

<p>Given a directed graph, design an algorithm to find out whether there is a route between two nodes.</p>

<p><strong>Example1:</strong></p>

<pre>

<strong> Input</strong>: n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2

<strong> Output</strong>: true

</pre>

<p><strong>Example2:</strong></p>

<pre>

<strong> Input</strong>: n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]], start = 0, target = 4

<strong> Output</strong> true

</pre>

<p><strong>Note: </strong></p>

<ol>
	<li><code>0 &lt;= n &lt;= 100000</code></li>
	<li>All node numbers are within the range [0, n].</li>
	<li>There might be self cycles and duplicated edges.</li>
</ol>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

First, we construct an adjacency list $g$ based on the given graph, where $g[i]$ represents all the neighboring nodes of node $i$. We use a hash table or array $vis$ to record the visited nodes, and then start a depth-first search from node $start$. If we search to node $target$, we return `true`, otherwise we return `false`.

The process of depth-first search is as follows:

1. If the current node $i$ equals the target node $target$, return `true`.
2. If the current node $i$ has been visited, return `false`.
3. Otherwise, mark the current node $i$ as visited, and then traverse all the neighboring nodes $j$ of node $i$, and recursively search node $j$.

The time complexity is $O(n + m)$, and the space complexity is $O(n + m)$, where $n$ and $m$ are the number of nodes and edges respectively.

<!-- tabs:start -->

```python
class Solution:
    def findWhetherExistsPath(
        self, n: int, graph: List[List[int]], start: int, target: int
    ) -> bool:
        def dfs(i: int):
            if i == target:
                return True
            if i in vis:
                return False
            vis.add(i)
            return any(dfs(j) for j in g[i])

        g = [[] for _ in range(n)]
        for a, b in graph:
            g[a].append(b)
        vis = set()
        return dfs(start)
```

```java
class Solution {
    private List<Integer>[] g;
    private boolean[] vis;
    private int target;

    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        vis = new boolean[n];
        g = new List[n];
        this.target = target;
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : graph) {
            g[e[0]].add(e[1]);
        }
        return dfs(start);
    }

    private boolean dfs(int i) {
        if (i == target) {
            return true;
        }
        if (vis[i]) {
            return false;
        }
        vis[i] = true;
        for (int j : g[i]) {
            if (dfs(j)) {
                return true;
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    bool findWhetherExistsPath(int n, vector<vector<int>>& graph, int start, int target) {
        vector<int> g[n];
        vector<bool> vis(n);
        for (auto& e : graph) {
            g[e[0]].push_back(e[1]);
        }
        function<bool(int)> dfs = [&](int i) {
            if (i == target) {
                return true;
            }
            if (vis[i]) {
                return false;
            }
            vis[i] = true;
            for (int j : g[i]) {
                if (dfs(j)) {
                    return true;
                }
            }
            return false;
        };
        return dfs(start);
    }
};
```

```go
func findWhetherExistsPath(n int, graph [][]int, start int, target int) bool {
	g := make([][]int, n)
	vis := make([]bool, n)
	for _, e := range graph {
		g[e[0]] = append(g[e[0]], e[1])
	}
	var dfs func(int) bool
	dfs = func(i int) bool {
		if i == target {
			return true
		}
		if vis[i] {
			return false
		}
		vis[i] = true
		for _, j := range g[i] {
			if dfs(j) {
				return true
			}
		}
		return false
	}
	return dfs(start)
}
```

```ts
function findWhetherExistsPath(
    n: number,
    graph: number[][],
    start: number,
    target: number,
): boolean {
    const g: number[][] = Array.from({ length: n }, () => []);
    const vis: boolean[] = Array.from({ length: n }, () => false);
    for (const [a, b] of graph) {
        g[a].push(b);
    }
    const dfs = (i: number): boolean => {
        if (i === target) {
            return true;
        }
        if (vis[i]) {
            return false;
        }
        vis[i] = true;
        return g[i].some(dfs);
    };
    return dfs(start);
}
```

```swift
class Solution {
    private var g: [[Int]]!
    private var vis: [Bool]!
    private var target: Int!

    func findWhetherExistsPath(_ n: Int, _ graph: [[Int]], _ start: Int, _ target: Int) -> Bool {
        vis = [Bool](repeating: false, count: n)
        g = [[Int]](repeating: [], count: n)
        self.target = target
        for e in graph {
            g[e[0]].append(e[1])
        }
        return dfs(start)
    }

    private func dfs(_ i: Int) -> Bool {
        if i == target {
            return true
        }
        if vis[i] {
            return false
        }
        vis[i] = true
        for j in g[i] {
            if dfs(j) {
                return true
            }
        }
        return false
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: BFS

Similar to Solution 1, we first construct an adjacency list $g$ based on the given graph, where $g[i]$ represents all the neighboring nodes of node $i$. We use a hash table or array $vis$ to record the visited nodes, and then start a breadth-first search from node $start$. If we search to node $target$, we return `true`, otherwise we return `false`.

The time complexity is $O(n + m)$, and the space complexity is $O(n + m)$, where $n$ and $m$ are the number of nodes and edges respectively.

<!-- tabs:start -->

```python
class Solution:
    def findWhetherExistsPath(
        self, n: int, graph: List[List[int]], start: int, target: int
    ) -> bool:
        g = [[] for _ in range(n)]
        for a, b in graph:
            g[a].append(b)
        vis = {start}
        q = deque([start])
        while q:
            i = q.popleft()
            if i == target:
                return True
            for j in g[i]:
                if j not in vis:
                    vis.add(j)
                    q.append(j)
        return False
```

```java
class Solution {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        boolean[] vis = new boolean[n];
        for (int[] e : graph) {
            g[e[0]].add(e[1]);
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        vis[start] = true;
        while (!q.isEmpty()) {
            int i = q.poll();
            if (i == target) {
                return true;
            }
            for (int j : g[i]) {
                if (!vis[j]) {
                    q.offer(j);
                    vis[j] = true;
                }
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    bool findWhetherExistsPath(int n, vector<vector<int>>& graph, int start, int target) {
        vector<int> g[n];
        vector<bool> vis(n);
        for (auto& e : graph) {
            g[e[0]].push_back(e[1]);
        }
        queue<int> q{{start}};
        vis[start] = true;
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            if (i == target) {
                return true;
            }
            for (int j : g[i]) {
                if (!vis[j]) {
                    q.push(j);
                    vis[j] = true;
                }
            }
        }
        return false;
    }
};
```

```go
func findWhetherExistsPath(n int, graph [][]int, start int, target int) bool {
	g := make([][]int, n)
	vis := make([]bool, n)
	for _, e := range graph {
		g[e[0]] = append(g[e[0]], e[1])
	}
	q := []int{start}
	vis[start] = true
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		if i == target {
			return true
		}
		for _, j := range g[i] {
			if !vis[j] {
				vis[j] = true
				q = append(q, j)
			}
		}
	}
	return false
}
```

```ts
function findWhetherExistsPath(
    n: number,
    graph: number[][],
    start: number,
    target: number,
): boolean {
    const g: number[][] = Array.from({ length: n }, () => []);
    const vis: boolean[] = Array.from({ length: n }, () => false);
    for (const [a, b] of graph) {
        g[a].push(b);
    }
    const q: number[] = [start];
    vis[start] = true;
    while (q.length > 0) {
        const i = q.pop()!;
        if (i === target) {
            return true;
        }
        for (const j of g[i]) {
            if (!vis[j]) {
                vis[j] = true;
                q.push(j);
            }
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
