# [2368. Reachable Nodes With Restrictions](https://leetcode.com/problems/reachable-nodes-with-restrictions)

[中文文档](/solution/2300-2399/2368.Reachable%20Nodes%20With%20Restrictions/README.md)

## Description

<p>There is an undirected tree with <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code> and <code>n - 1</code> edges.</p>

<p>You are given a 2D integer array <code>edges</code> of length <code>n - 1</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree. You are also given an integer array <code>restricted</code> which represents <strong>restricted</strong> nodes.</p>

<p>Return <em>the <strong>maximum</strong> number of nodes you can reach from node </em><code>0</code><em> without visiting a restricted node.</em></p>

<p>Note that node <code>0</code> will <strong>not</strong> be a restricted node.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2368.Reachable%20Nodes%20With%20Restrictions/images/ex1drawio.png" style="width: 402px; height: 322px;" />
<pre>
<strong>Input:</strong> n = 7, edges = [[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]], restricted = [4,5]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The diagram above shows the tree.
We have that [0,1,2,3] are the only nodes that can be reached from node 0 without visiting a restricted node.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2368.Reachable%20Nodes%20With%20Restrictions/images/ex2drawio.png" style="width: 412px; height: 312px;" />
<pre>
<strong>Input:</strong> n = 7, edges = [[0,1],[0,2],[0,5],[0,4],[3,2],[6,5]], restricted = [4,2,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The diagram above shows the tree.
We have that [0,5,6] are the only nodes that can be reached from node 0 without visiting a restricted node.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>edges</code> represents a valid tree.</li>
	<li><code>1 &lt;= restricted.length &lt; n</code></li>
	<li><code>1 &lt;= restricted[i] &lt; n</code></li>
	<li>All the values of <code>restricted</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reachableNodes(
        self, n: int, edges: List[List[int]], restricted: List[int]
    ) -> int:
        g = defaultdict(list)
        vis = [False] * n
        for v in restricted:
            vis[v] = True
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)

        def dfs(u):
            nonlocal ans
            if vis[u]:
                return
            ans += 1
            vis[u] = True
            for v in g[u]:
                dfs(v)

        ans = 0
        dfs(0)
        return ans
```

```python
class Solution:
    def reachableNodes(self, n: int, edges: List[List[int]], restricted: List[int]) -> int:
        s = set(restricted)
        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        q = deque([0])
        vis = [False] * n
        for v in restricted:
            vis[v] = True
        ans = 0
        while q:
            i = q.popleft()
            ans += 1
            vis[i] = True
            for j in g[i]:
                if not vis[j]:
                    q.append(j)
        return ans
```

### **Java**

```java
class Solution {
    private List<Integer>[] g;
    private boolean[] vis;
    private int ans;

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        vis = new boolean[n];
        for (int v : restricted) {
            vis[v] = true;
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }

        ans = 0;
        dfs(0);
        return ans;
    }

    private void dfs(int u) {
        if (vis[u]) {
            return;
        }
        ++ans;
        vis[u] = true;
        for (int v : g[u]) {
            dfs(v);
        }
    }
}
```

```java
class Solution {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        boolean[] vis = new boolean[n];
        for (int v : restricted) {
            vis[v] = true;
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        int ans = 0;
        while (!q.isEmpty()) {
            int i = q.pollFirst();
            ++ans;
            vis[i] = true;
            for (int j : g[i]) {
                if (!vis[j]) {
                    q.offer(j);
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int ans;

    int reachableNodes(int n, vector<vector<int>>& edges, vector<int>& restricted) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<bool> vis(n);
        for (int v : restricted) vis[v] = true;
        ans = 0;
        dfs(0, g, vis);
        return ans;
    }

    void dfs(int u, vector<vector<int>>& g, vector<bool>& vis) {
        if (vis[u]) return;
        vis[u] = true;
        ++ans;
        for (int v : g[u]) dfs(v, g, vis);
    }
};
```

```cpp
class Solution {
public:
    int reachableNodes(int n, vector<vector<int>>& edges, vector<int>& restricted) {
        vector<vector<int>> g(n);
        vector<bool> vis(n);
        for (auto& e : edges)
        {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        for (int v : restricted) vis[v] = true;
        queue<int> q{{0}};
        int ans = 0;
        while (!q.empty())
        {
            int i = q.front();
            q.pop();
            ++ans;
            vis[i] = true;
            for (int j : g[i]) if (!vis[j]) q.push(j);
        }
        return ans;
    }
};
```

### **Go**

```go
func reachableNodes(n int, edges [][]int, restricted []int) int {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	vis := make([]bool, n)
	for _, v := range restricted {
		vis[v] = true
	}
	ans := 0
	var dfs func(u int)
	dfs = func(u int) {
		if vis[u] {
			return
		}
		vis[u] = true
		ans++
		for _, v := range g[u] {
			dfs(v)
		}
	}
	dfs(0)
	return ans
}
```

```go
func reachableNodes(n int, edges [][]int, restricted []int) int {
	g := make([][]int, n)
	vis := make([]bool, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	for _, v := range restricted {
		vis[v] = true
	}
	q := []int{0}
	ans := 0
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		ans++
		vis[i] = true
		for _, j := range g[i] {
			if !vis[j] {
				q = append(q, j)
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function reachableNodes(
    n: number,
    edges: number[][],
    restricted: number[],
): number {
    let res = 0;
    const vis = new Array(n).fill(false);
    const map = new Map<number, number[]>();
    for (const [start, end] of edges) {
        map.set(start, [...(map.get(start) ?? []), end]);
        map.set(end, [...(map.get(end) ?? []), start]);
    }
    const dfs = (cur: number) => {
        if (restricted.includes(cur) || vis[cur]) {
            return;
        }
        res++;
        vis[cur] = true;
        for (const item of map.get(cur) ?? []) {
            dfs(item);
        }
    };
    dfs(0);

    return res;
}
```

```ts
function reachableNodes(
    n: number,
    edges: number[][],
    restricted: number[],
): number {
    const g = Array.from({ length: n }, () => []);
    const vis = new Array(n).fill(false);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    for (const v of restricted) {
        vis[v] = true;
    }
    const q = [0];
    let ans = 0;
    while (q.length) {
        const i = q.shift();
        ++ans;
        vis[i] = true;
        for (const j of g[i]) {
            if (!vis[j]) {
                q.push(j);
            }
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
