# [面试题 04.01. 节点间通路](https://leetcode.cn/problems/route-between-nodes-lcci)

[English Version](/lcci/04.01.Route%20Between%20Nodes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。</p>

<p><strong>示例1:</strong></p>

<pre><strong> 输入</strong>：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
<strong> 输出</strong>：true
</pre>

<p><strong>示例2:</strong></p>

<pre><strong> 输入</strong>：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]], start = 0, target = 4
<strong> 输出</strong> true
</pre>

<p><strong>提示：</strong></p>

<ol>
	<li>节点数量n在[0, 1e5]范围内。</li>
	<li>节点编号大于等于 0 小于 n。</li>
	<li>图中可能存在自环和平行边。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

**方法二：BFS**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
