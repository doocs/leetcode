# [802. Find Eventual Safe States](https://leetcode.com/problems/find-eventual-safe-states)

[中文文档](/solution/0800-0899/0802.Find%20Eventual%20Safe%20States/README.md)

## Description

<p>There is a directed graph of <code>n</code> nodes with each node labeled from <code>0</code> to <code>n - 1</code>. The graph is represented by a <strong>0-indexed</strong> 2D integer array <code>graph</code> where <code>graph[i]</code> is an integer array of nodes adjacent to node <code>i</code>, meaning there is an edge from node <code>i</code> to each node in <code>graph[i]</code>.</p>

<p>A node is a <strong>terminal node</strong> if there are no outgoing edges. A node is a <strong>safe node</strong> if every possible path starting from that node leads to a <strong>terminal node</strong> (or another safe node).</p>

<p>Return <em>an array containing all the <strong>safe nodes</strong> of the graph</em>. The answer should be sorted in <strong>ascending</strong> order.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="Illustration of graph" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0802.Find%20Eventual%20Safe%20States/images/picture1.png" style="height: 171px; width: 600px;" />
<pre>
<strong>Input:</strong> graph = [[1,2],[2,3],[5],[0],[5],[],[]]
<strong>Output:</strong> [2,4,5,6]
<strong>Explanation:</strong> The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
<strong>Output:</strong> [4]
<strong>Explanation:</strong>
Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == graph.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= graph[i].length &lt;= n</code></li>
	<li><code>0 &lt;= graph[i][j] &lt;= n - 1</code></li>
	<li><code>graph[i]</code> is sorted in a strictly increasing order.</li>
	<li>The graph may contain self-loops.</li>
	<li>The number of edges in the graph will be in the range <code>[1, 4 * 10<sup>4</sup>]</code>.</li>
</ul>

## Solutions

The point with zero out-degree is safe, and if a point can **only** reach the safe point, then it is also safe, so the problem can be converted to topological sorting.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def eventualSafeNodes(self, graph: List[List[int]]) -> List[int]:
        rg = defaultdict(list)
        indeg = [0] * len(graph)
        for i, vs in enumerate(graph):
            for j in vs:
                rg[j].append(i)
            indeg[i] = len(vs)
        q = deque([i for i, v in enumerate(indeg) if v == 0])
        while q:
            i = q.popleft()
            for j in rg[i]:
                indeg[j] -= 1
                if indeg[j] == 0:
                    q.append(j)
        return [i for i, v in enumerate(indeg) if v == 0]
```

```python
class Solution:
    def eventualSafeNodes(self, graph: List[List[int]]) -> List[int]:
        def dfs(i):
            if color[i]:
                return color[i] == 2
            color[i] = 1
            for j in graph[i]:
                if not dfs(j):
                    return False
            color[i] = 2
            return True

        n = len(graph)
        color = [0] * n
        return [i for i in range(n) if dfs(i)]
```

### **Java**

```java
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] indeg = new int[n];
        List<Integer>[] rg = new List[n];
        for (int i = 0; i < n; ++i) {
            rg[i] = new ArrayList<>();
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            for (int j : graph[i]) {
                rg[j].add(i);
            }
            indeg[i] = graph[i].length;
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int i = q.pollFirst();
            for (int j : rg[i]) {
                if (--indeg[j] == 0) {
                    q.offer(j);
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (indeg[i] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    private int[] color;
    private int[][] g;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        color = new int[n];
        g = graph;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (dfs(i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    private boolean dfs(int i) {
        if (color[i] > 0) {
            return color[i] == 2;
        }
        color[i] = 1;
        for (int j : g[i]) {
            if (!dfs(j)) {
                return false;
            }
        }
        color[i] = 2;
        return true;
    }
}
```

### **Go**

```go
func eventualSafeNodes(graph [][]int) []int {
	n := len(graph)
	indeg := make([]int, n)
	rg := make([][]int, n)
	q := []int{}
	for i, vs := range graph {
		for _, j := range vs {
			rg[j] = append(rg[j], i)
		}
		indeg[i] = len(vs)
		if indeg[i] == 0 {
			q = append(q, i)
		}
	}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		for _, j := range rg[i] {
			indeg[j]--
			if indeg[j] == 0 {
				q = append(q, j)
			}
		}
	}
	ans := []int{}
	for i, v := range indeg {
		if v == 0 {
			ans = append(ans, i)
		}
	}
	return ans
}
```

```go
func eventualSafeNodes(graph [][]int) []int {
	n := len(graph)
	color := make([]int, n)
	var dfs func(int) bool
	dfs = func(i int) bool {
		if color[i] > 0 {
			return color[i] == 2
		}
		color[i] = 1
		for _, j := range graph[i] {
			if !dfs(j) {
				return false
			}
		}
		color[i] = 2
		return true
	}
	ans := []int{}
	for i := range graph {
		if dfs(i) {
			ans = append(ans, i)
		}
	}
	return ans
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> eventualSafeNodes(vector<vector<int>>& graph) {
        int n = graph.size();
        vector<int> indeg(n);
        vector<vector<int>> rg(n);
        queue<int> q;
        for (int i = 0; i < n; ++i) {
            for (int j : graph[i]) rg[j].push_back(i);
            indeg[i] = graph[i].size();
            if (indeg[i] == 0) q.push(i);
        }
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            for (int j : rg[i])
                if (--indeg[j] == 0) q.push(j);
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i)
            if (indeg[i] == 0) ans.push_back(i);
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> color;

    vector<int> eventualSafeNodes(vector<vector<int>>& graph) {
        int n = graph.size();
        color.assign(n, 0);
        vector<int> ans;
        for (int i = 0; i < n; ++i) if (dfs(i, graph)) ans.push_back(i);
        return ans;
    }

    bool dfs(int i, vector<vector<int>>& g) {
        if (color[i]) return color[i] == 2;
        color[i] = 1;
        for (int j : g[i]) if (!dfs(j, g)) return false;
        color[i] = 2;
        return true;
    }
};
```

### **JavaScript**

```js
/**
 * @param {number[][]} graph
 * @return {number[]}
 */
var eventualSafeNodes = function (graph) {
    const n = graph.length;
    const rg = new Array(n).fill(0).map(() => new Array());
    const indeg = new Array(n).fill(0);
    const q = [];
    for (let i = 0; i < n; ++i) {
        for (let j of graph[i]) {
            rg[j].push(i);
        }
        indeg[i] = graph[i].length;
        if (indeg[i] == 0) {
            q.push(i);
        }
    }
    while (q.length) {
        const i = q.shift();
        for (let j of rg[i]) {
            if (--indeg[j] == 0) {
                q.push(j);
            }
        }
    }
    let ans = [];
    for (let i = 0; i < n; ++i) {
        if (indeg[i] == 0) {
            ans.push(i);
        }
    }
    return ans;
};
```

```js
/**
 * @param {number[][]} graph
 * @return {number[]}
 */
var eventualSafeNodes = function (graph) {
    const n = graph.length;
    const color = new Array(n).fill(0);
    function dfs(i) {
        if (color[i]) {
            return color[i] == 2;
        }
        color[i] = 1;
        for (const j of graph[i]) {
            if (!dfs(j)) {
                return false;
            }
        }
        color[i] = 2;
        return true;
    }
    let ans = [];
    for (let i = 0; i < n; ++i) {
        if (dfs(i)) {
            ans.push(i);
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
