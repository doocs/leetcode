---
comments: true
difficulty: Medium
rating: 1604
source: Biweekly Contest 81 Q2
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Graph
---

<!-- problem:start -->

# [2316. Count Unreachable Pairs of Nodes in an Undirected Graph](https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph)

[中文文档](/solution/2300-2399/2316.Count%20Unreachable%20Pairs%20of%20Nodes%20in%20an%20Undirected%20Graph/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>. There is an <strong>undirected</strong> graph with <code>n</code> nodes, numbered from <code>0</code> to <code>n - 1</code>. You are given a 2D integer array <code>edges</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> denotes that there exists an <strong>undirected</strong> edge connecting nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>.</p>

<p>Return <em>the <strong>number of pairs</strong> of different nodes that are <strong>unreachable</strong> from each other</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2316.Count%20Unreachable%20Pairs%20of%20Nodes%20in%20an%20Undirected%20Graph/images/tc-3.png" style="width: 267px; height: 169px;" />
<pre>
<strong>Input:</strong> n = 3, edges = [[0,1],[0,2],[1,2]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no pairs of nodes that are unreachable from each other. Therefore, we return 0.
</pre>

<p><strong class="example">Example 2:</strong></p>
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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

<!-- thinking:start -->

> **Thinking**
>
> Unreachable pairs sit in different components. $n \le 10^5$, so we cannot enumerate pairs. The count is the product of component sizes.
>
> DFS yields each component size $t$. Multiply by the sum $s$ of earlier sizes, then add $t$ into $s$. Each vertex and edge is visited once.

<!-- thinking:end -->

For any two nodes in an undirected graph, if there is a path between them, then they are mutually reachable.

Therefore, we can use depth-first search to find the number of nodes $t$ in each connected component, and then multiply the current number of nodes $t$ in the connected component by the number of nodes $s$ in all previous connected components to obtain the number of unreachable node pairs in the current connected component, which is $s \times t$. Then, we add $t$ to $s$ and continue to search for the next connected component until all connected components have been searched, and we can obtain the final answer.

The time complexity is $O(n + m)$, and the space complexity is $O(n + m)$. Here, $n$ and $m$ are the number of nodes and edges, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPairs(self, n: int, edges: List[List[int]]) -> int:
        def dfs(i: int) -> int:
            if vis[i]:
                return 0
            vis[i] = True
            return 1 + sum(dfs(j) for j in g[i])

        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = [False] * n
        ans = s = 0
        for i in range(n):
            t = dfs(i)
            ans += s * t
            s += t
        return ans
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private boolean[] vis;

    public long countPairs(int n, int[][] edges) {
        g = new List[n];
        vis = new boolean[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        long ans = 0, s = 0;
        for (int i = 0; i < n; ++i) {
            int t = dfs(i);
            ans += s * t;
            s += t;
        }
        return ans;
    }

    private int dfs(int i) {
        if (vis[i]) {
            return 0;
        }
        vis[i] = true;
        int cnt = 1;
        for (int j : g[i]) {
            cnt += dfs(j);
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countPairs(int n, vector<vector<int>>& edges) {
        vector<int> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        bool vis[n];
        memset(vis, 0, sizeof(vis));
        function<int(int)> dfs = [&](int i) {
            if (vis[i]) {
                return 0;
            }
            vis[i] = true;
            int cnt = 1;
            for (int j : g[i]) {
                cnt += dfs(j);
            }
            return cnt;
        };
        long long ans = 0, s = 0;
        for (int i = 0; i < n; ++i) {
            int t = dfs(i);
            ans += s * t;
            s += t;
        }
        return ans;
    }
};
```

#### Go

```go
func countPairs(n int, edges [][]int) (ans int64) {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	vis := make([]bool, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if vis[i] {
			return 0
		}
		vis[i] = true
		cnt := 1
		for _, j := range g[i] {
			cnt += dfs(j)
		}
		return cnt
	}
	var s int64
	for i := 0; i < n; i++ {
		t := int64(dfs(i))
		ans += s * t
		s += t
	}
	return
}
```

#### TypeScript

```ts
function countPairs(n: number, edges: number[][]): number {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const vis: boolean[] = Array(n).fill(false);
    const dfs = (i: number): number => {
        if (vis[i]) {
            return 0;
        }
        vis[i] = true;
        let cnt = 1;
        for (const j of g[i]) {
            cnt += dfs(j);
        }
        return cnt;
    };
    let [ans, s] = [0, 0];
    for (let i = 0; i < n; ++i) {
        const t = dfs(i);
        ans += s * t;
        s += t;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_pairs(n: i32, edges: Vec<Vec<i32>>) -> i64 {
        let n = n as usize;
        let mut g = vec![vec![]; n];
        let mut vis = vec![false; n];
        for e in edges {
            let u = e[0] as usize;
            let v = e[1] as usize;
            g[u].push(v);
            g[v].push(u);
        }

        fn dfs(g: &Vec<Vec<usize>>, vis: &mut Vec<bool>, u: usize) -> i64 {
            if vis[u] {
                return 0;
            }
            vis[u] = true;
            let mut cnt = 1;
            for &v in &g[u] {
                cnt += dfs(g, vis, v);
            }
            cnt
        }

        let mut ans = 0;
        let mut s = 0;
        for u in 0..n {
            let t = dfs(&g, &mut vis, u);
            ans += t * s;
            s += t;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Explicit Stack

<!-- thinking:start -->

> **Thinking**
>
> Unreachable pairs sit in different components. With $n \le 10^5$ we cannot enumerate pairs. The count is the product of component sizes.
>
> Recursing from a node into each neighbor reaches depth equal to the component size on a chain. In Python the generator inside `sum` adds another frame, so a few hundred nodes already overflow the call stack. A component's size does not depend on visit order.
>
> Each component therefore uses an explicit stack: a node is marked when it is pushed, counted when it is popped, and only unmarked neighbors are pushed. Multiply the size $t$ by the sum $s$ of earlier sizes, then add $t$ into $s$.

<!-- thinking:end -->

For any two nodes in an undirected graph, if there is a path between them, then they are mutually reachable.

An explicit stack finds the number of nodes $t$ in each connected component. Starting from an unvisited node, we mark a node when pushing it, count it when popping it, and push only unmarked neighbors. Multiply $t$ by the number of nodes $s$ in all previous components to obtain the unreachable pairs $s \times t$, then add $t$ to $s$. Repeat until every node has been visited.

The time complexity is $O(n + m)$, and the space complexity is $O(n + m)$. Here, $n$ and $m$ are the number of nodes and edges, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPairs(self, n: int, edges: List[List[int]]) -> int:
        def dfs(i: int) -> int:
            if vis[i]:
                return 0
            vis[i] = True
            stk = [i]
            cnt = 0
            while stk:
                u = stk.pop()
                cnt += 1
                for j in g[u]:
                    if not vis[j]:
                        vis[j] = True
                        stk.append(j)
            return cnt

        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = [False] * n
        ans = s = 0
        for i in range(n):
            t = dfs(i)
            ans += s * t
            s += t
        return ans
```

#### Java

```java
class Solution {
    public long countPairs(int n, int[][] edges) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        boolean[] vis = new boolean[n];
        long ans = 0, s = 0;
        for (int i = 0; i < n; ++i) {
            int t = dfs(g, vis, i);
            ans += s * t;
            s += t;
        }
        return ans;
    }

    private int dfs(List<Integer>[] g, boolean[] vis, int i) {
        if (vis[i]) {
            return 0;
        }
        vis[i] = true;
        int cnt = 0;
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(i);
        while (!stk.isEmpty()) {
            int u = stk.pop();
            ++cnt;
            for (int j : g[u]) {
                if (!vis[j]) {
                    vis[j] = true;
                    stk.push(j);
                }
            }
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countPairs(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<char> vis(n);
        auto dfs = [&](int i) {
            if (vis[i]) {
                return 0;
            }
            vis[i] = 1;
            int cnt = 0;
            vector<int> stk{i};
            while (!stk.empty()) {
                int u = stk.back();
                stk.pop_back();
                ++cnt;
                for (int j : g[u]) {
                    if (!vis[j]) {
                        vis[j] = 1;
                        stk.push_back(j);
                    }
                }
            }
            return cnt;
        };
        long long ans = 0, s = 0;
        for (int i = 0; i < n; ++i) {
            int t = dfs(i);
            ans += s * t;
            s += t;
        }
        return ans;
    }
};
```

#### Go

```go
func countPairs(n int, edges [][]int) (ans int64) {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	vis := make([]bool, n)
	dfs := func(i int) int {
		if vis[i] {
			return 0
		}
		vis[i] = true
		stk := []int{i}
		cnt := 0
		for len(stk) > 0 {
			u := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			cnt++
			for _, j := range g[u] {
				if !vis[j] {
					vis[j] = true
					stk = append(stk, j)
				}
			}
		}
		return cnt
	}
	var s int64
	for i := 0; i < n; i++ {
		t := int64(dfs(i))
		ans += s * t
		s += t
	}
	return
}
```

#### TypeScript

```ts
function countPairs(n: number, edges: number[][]): number {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const vis: boolean[] = Array(n).fill(false);
    const dfs = (i: number): number => {
        if (vis[i]) {
            return 0;
        }
        vis[i] = true;
        const stk: number[] = [i];
        let cnt = 0;
        while (stk.length) {
            const u = stk.pop()!;
            cnt++;
            for (const j of g[u]) {
                if (!vis[j]) {
                    vis[j] = true;
                    stk.push(j);
                }
            }
        }
        return cnt;
    };
    let [ans, s] = [0, 0];
    for (let i = 0; i < n; ++i) {
        const t = dfs(i);
        ans += s * t;
        s += t;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_pairs(n: i32, edges: Vec<Vec<i32>>) -> i64 {
        let n = n as usize;
        let mut g = vec![vec![]; n];
        let mut vis = vec![false; n];
        for e in edges {
            let u = e[0] as usize;
            let v = e[1] as usize;
            g[u].push(v);
            g[v].push(u);
        }

        fn dfs(g: &Vec<Vec<usize>>, vis: &mut Vec<bool>, u: usize) -> i64 {
            if vis[u] {
                return 0;
            }
            vis[u] = true;
            let mut stk = vec![u];
            let mut cnt = 0;
            while let Some(x) = stk.pop() {
                cnt += 1;
                for &v in &g[x] {
                    if !vis[v] {
                        vis[v] = true;
                        stk.push(v);
                    }
                }
            }
            cnt
        }

        let mut ans = 0;
        let mut s = 0;
        for u in 0..n {
            let t = dfs(&g, &mut vis, u);
            ans += t * s;
            s += t;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
