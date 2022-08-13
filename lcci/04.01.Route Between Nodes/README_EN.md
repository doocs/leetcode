# [04.01. Route Between Nodes](https://leetcode.cn/problems/route-between-nodes-lcci)

[中文文档](/lcci/04.01.Route%20Between%20Nodes/README.md)

## Description

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

## Solutions

DFS or BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findWhetherExistsPath(
        self, n: int, graph: List[List[int]], start: int, target: int
    ) -> bool:
        def dfs(u):
            if u == target:
                return True
            for v in g[u]:
                if v not in vis:
                    vis.add(v)
                    if dfs(v):
                        return True
            return False

        g = defaultdict(list)
        for u, v in graph:
            g[u].append(v)
        vis = {start}
        return dfs(start)
```

```python
class Solution:
    def findWhetherExistsPath(self, n: int, graph: List[List[int]], start: int, target: int) -> bool:
        g = defaultdict(list)
        for u, v in graph:
            g[u].append(v)
        q = deque([start])
        vis = {start}
        while q:
            u = q.popleft()
            if u == target:
                return True
            for v in g[u]:
                if v not in vis:
                    vis.add(v)
                    q.append(v)
        return False
```

### **Java**

```java
class Solution {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int[] e : graph) {
            g.computeIfAbsent(e[0], k -> new ArrayList<>()).add(e[1]);
        }
        Set<Integer> vis = new HashSet<>();
        vis.add(start);
        return dfs(start, target, g, vis);
    }

    private boolean dfs(int u, int target, Map<Integer, List<Integer>> g, Set<Integer> vis) {
        if (u == target) {
            return true;
        }
        for (int v : g.getOrDefault(u, Collections.emptyList())) {
            if (!vis.contains(v)) {
                vis.add(v);
                if (dfs(v, target, g, vis)) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

```java
class Solution {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int[] e : graph) {
            g.computeIfAbsent(e[0], k -> new ArrayList<>()).add(e[1]);
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        Set<Integer> vis = new HashSet<>();
        vis.add(start);
        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == target) {
                return true;
            }
            for (int v : g.getOrDefault(u, Collections.emptyList())) {
                if (!vis.contains(v)) {
                    vis.add(v);
                    q.offer(v);
                }
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool findWhetherExistsPath(int n, vector<vector<int>>& graph, int start, int target) {
        unordered_map<int, vector<int>> g;
        for (auto& e : graph) g[e[0]].push_back(e[1]);
        unordered_set<int> vis {{start}};
        return dfs(start, target, g, vis);
    }

    bool dfs(int u, int& target, unordered_map<int, vector<int>>& g, unordered_set<int>& vis) {
        if (u == target) return true;
        for (int& v : g[u]) {
            if (!vis.count(v)) {
                vis.insert(v);
                if (dfs(v, target, g, vis)) return true;
            }
        }
        return false;
    }
};
```

```cpp
class Solution {
public:
    bool findWhetherExistsPath(int n, vector<vector<int>>& graph, int start, int target) {
        unordered_map<int, vector<int>> g;
        for (auto& e : graph) g[e[0]].push_back(e[1]);
        queue<int> q {{start}};
        unordered_set<int> vis {{start}};
        while (!q.empty()) {
            int u = q.front();
            if (u == target) return true;
            q.pop();
            for (int v : g[u]) {
                if (!vis.count(v)) {
                    vis.insert(v);
                    q.push(v);
                }
            }
        }
        return false;
    }
};
```

### **Go**

```go
func findWhetherExistsPath(n int, graph [][]int, start int, target int) bool {
	g := map[int][]int{}
	for _, e := range graph {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
	}
	vis := map[int]bool{start: true}
	var dfs func(int) bool
	dfs = func(u int) bool {
		if u == target {
			return true
		}
		for _, v := range g[u] {
			if !vis[v] {
				vis[v] = true
				if dfs(v) {
					return true
				}
			}
		}
		return false
	}
	return dfs(start)
}
```

```go
func findWhetherExistsPath(n int, graph [][]int, start int, target int) bool {
	g := map[int][]int{}
	for _, e := range graph {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
	}
	q := []int{start}
	vis := map[int]bool{start: true}
	for len(q) > 0 {
		u := q[0]
		if u == target {
			return true
		}
		q = q[1:]
		for _, v := range g[u] {
			if !vis[v] {
				vis[v] = true
				q = append(q, v)
			}
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
