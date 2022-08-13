# [2316. Count Unreachable Pairs of Nodes in an Undirected Graph](https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph)

[中文文档](/solution/2300-2399/2316.Count%20Unreachable%20Pairs%20of%20Nodes%20in%20an%20Undirected%20Graph/README.md)

## Description

<p>You are given an integer <code>n</code>. There is an <strong>undirected</strong> graph with <code>n</code> nodes, numbered from <code>0</code> to <code>n - 1</code>. You are given a 2D integer array <code>edges</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> denotes that there exists an <strong>undirected</strong> edge connecting nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>.</p>

<p>Return <em>the <strong>number of pairs</strong> of different nodes that are <strong>unreachable</strong> from each other</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2316.Count%20Unreachable%20Pairs%20of%20Nodes%20in%20an%20Undirected%20Graph/images/tc-3.png" style="width: 267px; height: 169px;" />
<pre>
<strong>Input:</strong> n = 3, edges = [[0,1],[0,2],[1,2]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no pairs of nodes that are unreachable from each other. Therefore, we return 0.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2316.Count%20Unreachable%20Pairs%20of%20Nodes%20in%20an%20Undirected%20Graph/images/tc-2.png" style="width: 295px; height: 269px;" />
<pre>
<strong>Input:</strong> n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
<strong>Output:</strong> 14
<strong>Explanation:</strong> There are 14 pairs of nodes that are unreachable from each other:
[[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]].
Therefore, we return 14.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>There are no repeated edges.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countPairs(self, n: int, edges: List[List[int]]) -> int:
        def dfs(i):
            vis[i] = True
            res = 1
            for j in g[i]:
                if not vis[j]:
                    res += dfs(j)
            return res

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = [False] * n
        arr = []
        for i in range(n):
            if not vis[i]:
                arr.append(dfs(i))
        ans = t = 0
        for v in arr:
            t += v
            ans += v * (n - t)
        return ans
```

### **Java**

```java
class Solution {
    private boolean[] vis;
    private List<Integer>[] g;

    public long countPairs(int n, int[][] edges) {
        vis = new boolean[n];
        g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                arr.add(dfs(i));
            }
        }
        int t = 0;
        long ans = 0;
        for (int v : arr) {
            t += v;
            ans += (long) v * (n - t);
        }
        return ans;
    }

    private int dfs(int i) {
        vis[i] = true;
        int res = 1;
        for (int j : g[i]) {
            if (!vis[j]) {
                res += dfs(j);
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> g;
    vector<bool> vis;

    long long countPairs(int n, vector<vector<int>>& edges) {
        vis.resize(n);
        g.resize(n, vector<int>());
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<int> arr;
        for (int i = 0; i < n; ++i)
            if (!vis[i]) arr.push_back(dfs(i));
        long long ans = 0;
        int t = 0;
        for (int& v : arr) {
            t += v;
            ans += 1ll * v * (n - t);
        }
        return ans;
    }

    int dfs(int i) {
        int res = 1;
        vis[i] = true;
        for (int j : g[i])
            if (!vis[j]) res += dfs(j);
        return res;
    }
};
```

### **Go**

```go
func countPairs(n int, edges [][]int) int64 {
	vis := make([]bool, n)
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var arr []int
	var dfs func(int) int
	dfs = func(i int) int {
		res := 1
		vis[i] = true
		for _, j := range g[i] {
			if !vis[j] {
				res += dfs(j)
			}
		}
		return res
	}

	for i := 0; i < n; i++ {
		if !vis[i] {
			arr = append(arr, dfs(i))
		}
	}
	ans := 0
	t := 0
	for _, v := range arr {
		t += v
		ans += v * (n - t)
	}
	return int64(ans)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
