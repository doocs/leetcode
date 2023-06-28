# [2581. Count Number of Possible Root Nodes](https://leetcode.com/problems/count-number-of-possible-root-nodes)

[中文文档](/solution/2500-2599/2581.Count%20Number%20of%20Possible%20Root%20Nodes/README.md)

## Description

<p>Alice has an undirected tree with <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>. The tree is represented as a 2D integer array <code>edges</code> of length <code>n - 1</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree.</p>

<p>Alice wants Bob to find the root of the tree. She allows Bob to make several <strong>guesses</strong> about her tree. In one guess, he does the following:</p>

<ul>
	<li>Chooses two <strong>distinct</strong> integers <code>u</code> and <code>v</code> such that there exists an edge <code>[u, v]</code> in the tree.</li>
	<li>He tells Alice that <code>u</code> is the <strong>parent</strong> of <code>v</code> in the tree.</li>
</ul>

<p>Bob&#39;s guesses are represented by a 2D integer array <code>guesses</code> where <code>guesses[j] = [u<sub>j</sub>, v<sub>j</sub>]</code> indicates Bob guessed <code>u<sub>j</sub></code> to be the parent of <code>v<sub>j</sub></code>.</p>

<p>Alice being lazy, does not reply to each of Bob&#39;s guesses, but just says that <strong>at least</strong> <code>k</code> of his guesses are <code>true</code>.</p>

<p>Given the 2D integer arrays <code>edges</code>, <code>guesses</code> and the integer <code>k</code>, return <em>the <strong>number of possible nodes</strong> that can be the root of Alice&#39;s tree</em>. If there is no such tree, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2581.Count%20Number%20of%20Possible%20Root%20Nodes/images/ex-1.png" style="width: 727px; height: 250px;" /></p>

<pre>
<strong>Input:</strong> edges = [[0,1],[1,2],[1,3],[4,2]], guesses = [[1,3],[0,1],[1,0],[2,4]], k = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
Root = 0, correct guesses = [1,3], [0,1], [2,4]
Root = 1, correct guesses = [1,3], [1,0], [2,4]
Root = 2, correct guesses = [1,3], [1,0], [2,4]
Root = 3, correct guesses = [1,0], [2,4]
Root = 4, correct guesses = [1,3], [1,0]
Considering 0, 1, or 2 as root node leads to 3 correct guesses.

</pre>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2581.Count%20Number%20of%20Possible%20Root%20Nodes/images/ex-2.png" style="width: 600px; height: 303px;" /></p>

<pre>
<strong>Input:</strong> edges = [[0,1],[1,2],[2,3],[3,4]], guesses = [[1,0],[3,4],[2,1],[3,2]], k = 1
<strong>Output:</strong> 5
<strong>Explanation:</strong> 
Root = 0, correct guesses = [3,4]
Root = 1, correct guesses = [1,0], [3,4]
Root = 2, correct guesses = [1,0], [2,1], [3,4]
Root = 3, correct guesses = [1,0], [2,1], [3,2], [3,4]
Root = 4, correct guesses = [1,0], [2,1], [3,2]
Considering any node as root will give at least 1 correct guess. 

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>edges.length == n - 1</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= guesses.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub>, u<sub>j</sub>, v<sub>j</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>u<sub>j</sub> != v<sub>j</sub></code></li>
	<li><code>edges</code> represents a valid tree.</li>
	<li><code>guesses[j]</code> is an edge of the tree.</li>
	<li><code>guesses</code> is unique.</li>
	<li><code>0 &lt;= k &lt;= guesses.length</code></li>
</ul>

## Solutions

**Solution 1: Tree DP (change root)**

First, we traverse the given edge set $edges$ and convert it to an adjacency list $g$ where $g[i]$ represents the adjacent nodes of node $i$. Use a hash map $gs$ to record the given guess set $guesses$.

Then, we start from node $0$ and perform a DFS to count the number of edges in $guesses$ among all the nodes that can be reached from node $0$. We use the variable $cnt$ to record this number.

Next, we start from node $0$ and perform a DFS to count the number of edges in $guesses$ in each tree with $0$ as the root. If the number is greater than or equal to $k$, it means that this node is a possible root node, and we add $1$ to the answer.

Therefore, the problem becomes to count the number of edges in $guesses$ in each tree with each node as the root. We already know that there are $cnt$ edges in $guesses$ among all the nodes that can be reached from node $0$. We can maintain this value by adding or subtracting the current edge in $guesses$ in DFS.

Assume that we are currently traversing node $i$ and $cnt$ represents the number of edges in $guesses$ with $i$ as the root node. Then, for each adjacent node $j$ of $i$, we need to calculate the number of edges in $guesses$ with $j$ as the root node. If $(i, j)$ is in $guesses$, then there is no edge $(i, j)$ in the tree with $j$ as the root node, so $cnt$ should decrease by $1$. If $(j, i)$ is in $guesses$, then there is an extra edge $(i, j)$ in the tree with $j$ as the root node, so $cnt$ should increase by $1$. That is, $f[j] = f[i] + (j, i) \in guesses - (i, j) \in guesses$. Where $f[i]$ represents the number of edges in $guesses$ with $i$ as the root node.

The time complexity is $O(n + m)$ and the space complexity is $O(n + m)$, where $n$ and $m$ are the lengths of $edges$ and $guesses$ respectively.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def rootCount(
        self, edges: List[List[int]], guesses: List[List[int]], k: int
    ) -> int:
        def dfs1(i, fa):
            nonlocal cnt
            for j in g[i]:
                if j != fa:
                    cnt += gs[(i, j)]
                    dfs1(j, i)

        def dfs2(i, fa):
            nonlocal ans, cnt
            ans += cnt >= k
            for j in g[i]:
                if j != fa:
                    cnt -= gs[(i, j)]
                    cnt += gs[(j, i)]
                    dfs2(j, i)
                    cnt -= gs[(j, i)]
                    cnt += gs[(i, j)]

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        gs = Counter((u, v) for u, v in guesses)
        cnt = 0
        dfs1(0, -1)
        ans = 0
        dfs2(0, -1)
        return ans
```

### **Java**

```java
class Solution {
    private List<Integer>[] g;
    private Map<Long, Integer> gs = new HashMap<>();
    private int ans;
    private int k;
    private int cnt;
    private int n;

    public int rootCount(int[][] edges, int[][] guesses, int k) {
        this.k = k;
        n = edges.length + 1;
        g = new List[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        for (var e : guesses) {
            int a = e[0], b = e[1];
            gs.merge(f(a, b), 1, Integer::sum);
        }
        dfs1(0, -1);
        dfs2(0, -1);
        return ans;
    }

    private void dfs1(int i, int fa) {
        for (int j : g[i]) {
            if (j != fa) {
                cnt += gs.getOrDefault(f(i, j), 0);
                dfs1(j, i);
            }
        }
    }

    private void dfs2(int i, int fa) {
        ans += cnt >= k ? 1 : 0;
        for (int j : g[i]) {
            if (j != fa) {
                int a = gs.getOrDefault(f(i, j), 0);
                int b = gs.getOrDefault(f(j, i), 0);
                cnt -= a;
                cnt += b;
                dfs2(j, i);
                cnt -= b;
                cnt += a;
            }
        }
    }

    private long f(int i, int j) {
        return 1L * i * n + j;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int rootCount(vector<vector<int>>& edges, vector<vector<int>>& guesses, int k) {
        int n = edges.size() + 1;
        vector<vector<int>> g(n);
        unordered_map<long long, int> gs;
        auto f = [&](int i, int j) {
            return 1LL * i * n + j;
        };
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        for (auto& e : guesses) {
            int a = e[0], b = e[1];
            gs[f(a, b)]++;
        }
        int ans = 0;
        int cnt = 0;

        function<void(int, int)> dfs1 = [&](int i, int fa) {
            for (int& j : g[i]) {
                if (j != fa) {
                    cnt += gs[f(i, j)];
                    dfs1(j, i);
                }
            }
        };

        function<void(int, int)> dfs2 = [&](int i, int fa) {
            ans += cnt >= k;
            for (int& j : g[i]) {
                if (j != fa) {
                    int a = gs[f(i, j)];
                    int b = gs[f(j, i)];
                    cnt -= a;
                    cnt += b;
                    dfs2(j, i);
                    cnt -= b;
                    cnt += a;
                }
            }
        };
        dfs1(0, -1);
        dfs2(0, -1);
        return ans;
    }
};
```

### **Go**

```go
func rootCount(edges [][]int, guesses [][]int, k int) (ans int) {
	n := len(edges) + 1
	g := make([][]int, n)
	gs := map[int]int{}
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	f := func(i, j int) int {
		return i*n + j
	}
	for _, e := range guesses {
		a, b := e[0], e[1]
		gs[f(a, b)]++
	}

	cnt := 0
	var dfs1 func(i, fa int)
	var dfs2 func(i, fa int)
	dfs1 = func(i, fa int) {
		for _, j := range g[i] {
			if j != fa {
				cnt += gs[f(i, j)]
				dfs1(j, i)
			}
		}
	}
	dfs2 = func(i, fa int) {
		if cnt >= k {
			ans++
		}
		for _, j := range g[i] {
			if j != fa {
				a, b := gs[f(i, j)], gs[f(j, i)]
				cnt -= a
				cnt += b
				dfs2(j, i)
				cnt -= b
				cnt += a
			}
		}
	}
	dfs1(0, -1)
	dfs2(0, -1)
	return
}
```

### **...**

```

```

<!-- tabs:end -->
