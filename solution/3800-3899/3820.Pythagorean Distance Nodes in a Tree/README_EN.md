---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3820.Pythagorean%20Distance%20Nodes%20in%20a%20Tree/README_EN.md
---

<!-- problem:start -->

# [3820. Pythagorean Distance Nodes in a Tree](https://leetcode.com/problems/pythagorean-distance-nodes-in-a-tree)

[中文文档](/solution/3800-3899/3820.Pythagorean%20Distance%20Nodes%20in%20a%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> and an undirected tree with <code>n</code> nodes numbered from 0 to <code>n - 1</code>. The tree is represented by a 2D array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates an undirected edge between <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>.</p>

<p>You are also given three <strong>distinct</strong> target nodes <code>x</code>, <code>y</code>, and <code>z</code>.</p>

<p>For any node <code>u</code> in the tree:</p>

<ul>
	<li>Let <code>dx</code> be the distance from <code>u</code> to node <code>x</code></li>
	<li>Let <code>dy</code> be the distance from <code>u</code> to node <code>y</code></li>
	<li>Let <code>dz</code> be the distance from <code>u</code> to node <code>z</code></li>
</ul>

<p>The node <code>u</code> is called <strong>special</strong> if the three distances form a <strong>Pythagorean Triplet</strong>.</p>

<p>Return an integer denoting the number of special nodes in the tree.</p>

<p>A <strong>Pythagorean triplet</strong> consists of three integers <code>a</code>, <code>b</code>, and <code>c</code> which, when sorted in <strong>ascending</strong> order, satisfy <code>a<sup>2</sup> + b<sup>2</sup> = c<sup>2</sup></code>.</p>

<p>The <strong>distance</strong> between two nodes in a tree is the number of edges on the unique path between them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[0,1],[0,2],[0,3]], x = 1, y = 2, z = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>For each node, we compute its distances to nodes <code>x = 1</code>, <code>y = 2</code>, and <code>z = 3</code>.</p>

<ul>
	<li>Node 0 has distances 1, 1, and 1. After sorting, the distances are 1, 1, and 1, which do not satisfy the Pythagorean condition.</li>
	<li>Node 1 has distances 0, 2, and 2. After sorting, the distances are 0, 2, and 2. Since <code>0<sup>2</sup> + 2<sup>2</sup> = 2<sup>2</sup></code>, node 1 is special.</li>
	<li>Node 2 has distances 2, 0, and 2. After sorting, the distances are 0, 2, and 2. Since <code>0<sup>2</sup> + 2<sup>2</sup> = 2<sup>2</sup></code>, node 2 is special.</li>
	<li>Node 3 has distances 2, 2, and 0. After sorting, the distances are 0, 2, and 2. This also satisfies the Pythagorean condition.</li>
</ul>

<p>Therefore, nodes 1, 2, and 3 are special, and the answer is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[0,1],[1,2],[2,3]], x = 0, y = 3, z = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>For each node, we compute its distances to nodes <code>x = 0</code>, <code>y = 3</code>, and <code>z = 2</code>.</p>

<ul>
	<li>Node 0 has distances 0, 3, and 2. After sorting, the distances are 0, 2, and 3, which do not satisfy the Pythagorean condition.</li>
	<li>Node 1 has distances 1, 2, and 1. After sorting, the distances are 1, 1, and 2, which do not satisfy the Pythagorean condition.</li>
	<li>Node 2 has distances 2, 1, and 0. After sorting, the distances are 0, 1, and 2, which do not satisfy the Pythagorean condition.</li>
	<li>Node 3 has distances 3, 0, and 1. After sorting, the distances are 0, 1, and 3, which do not satisfy the Pythagorean condition.</li>
</ul>

<p>No node satisfies the Pythagorean condition. Therefore, the answer is 0.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[0,1],[1,2],[1,3]], x = 1, y = 3, z = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>For each node, we compute its distances to nodes <code>x = 1</code>, <code>y = 3</code>, and <code>z = 0</code>.</p>

<ul>
	<li>Node 0 has distances 1, 2, and 0. After sorting, the distances are 0, 1, and 2, which do not satisfy the Pythagorean condition.</li>
	<li>Node 1 has distances 0, 1, and 1. After sorting, the distances are 0, 1, and 1. Since <code>0<sup>2</sup> + 1<sup>2</sup> = 1<sup>2</sup></code>, node 1 is special.</li>
	<li>Node 2 has distances 1, 2, and 2. After sorting, the distances are 1, 2, and 2, which do not satisfy the Pythagorean condition.</li>
	<li>Node 3 has distances 1, 0, and 2. After sorting, the distances are 0, 1, and 2, which do not satisfy the Pythagorean condition.</li>
</ul>

<p>Therefore, the answer is 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>4 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub>, x, y, z &lt;= n - 1</code></li>
	<li><code>x</code>, <code>y</code>, and <code>z</code> are pairwise <strong>distinct</strong>.</li>
	<li>The input is generated such that <code>edges</code> represent a valid tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: BFS + Enumeration

We first construct an adjacency list $g$ based on the edges given in the problem, where $g[u]$ stores all nodes adjacent to node $u$.

Next, we define a function $\text{bfs}(i)$ to calculate the distances from node $i$ to all other nodes. We use a queue to implement Breadth-First Search (BFS) and maintain a distance array $\text{dist}$, where $\text{dist}[j]$ represents the distance from node $i$ to node $j$. Initially, $\text{dist}[i] = 0$, and the distances to all other nodes are set to infinity. During the BFS process, we continuously update the distance array until all reachable nodes have been traversed.

We call $\text{bfs}(x)$, $\text{bfs}(y)$, and $\text{bfs}(z)$ to calculate the distances from nodes $x$, $y$, and $z$ to all other nodes, obtaining three distance arrays $d_1$, $d_2$, and $d_3$ respectively.

Finally, we iterate through all nodes $u$. For each node, we retrieve its distances to $x$, $y$, and $z$ as $a = d_1[u]$, $b = d_2[u]$, and $c = d_3[u]$. We sort these three distances and check if they satisfy the Pythagorean theorem condition: $a^2 + b^2 = c^2$. If the condition is met, we increment the answer count.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes in the tree.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def specialNodes(
        self, n: int, edges: List[List[int]], x: int, y: int, z: int
    ) -> int:
        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)

        def bfs(i: int) -> List[int]:
            q = deque([i])
            dist = [inf] * n
            dist[i] = 0
            while q:
                for _ in range(len(q)):
                    u = q.popleft()
                    for v in g[u]:
                        if dist[v] > dist[u] + 1:
                            dist[v] = dist[u] + 1
                            q.append(v)
            return dist

        d1 = bfs(x)
        d2 = bfs(y)
        d3 = bfs(z)
        ans = 0
        for a, b, c in zip(d1, d2, d3):
            s = a + b + c
            a, c = min(a, b, c), max(a, b, c)
            b = s - a - c
            if a * a + b * b == c * c:
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private int n;
    private final int inf = Integer.MAX_VALUE / 2;

    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        this.n = n;
        g = new ArrayList[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }

        int[] d1 = bfs(x);
        int[] d2 = bfs(y);
        int[] d3 = bfs(z);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            long[] a = new long[] {d1[i], d2[i], d3[i]};
            Arrays.sort(a);
            if (a[0] * a[0] + a[1] * a[1] == a[2] * a[2]) {
                ++ans;
            }
        }
        return ans;
    }

    private int[] bfs(int i) {
        int[] dist = new int[n];
        Arrays.fill(dist, inf);
        Deque<Integer> q = new ArrayDeque<>();
        dist[i] = 0;
        q.add(i);
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                int u = q.poll();
                for (int v : g[u]) {
                    if (dist[v] > dist[u] + 1) {
                        dist[v] = dist[u] + 1;
                        q.add(v);
                    }
                }
            }
        }
        return dist;
    }
}
```

#### C++

```cpp
class Solution {
private:
    vector<vector<int>> g;
    int n;
    const int inf = INT_MAX / 2;

    vector<int> bfs(int i) {
        vector<int> dist(n, inf);
        queue<int> q;
        dist[i] = 0;
        q.push(i);
        while (!q.empty()) {
            for (int k = q.size(); k > 0; --k) {
                int u = q.front();
                q.pop();
                for (int v : g[u]) {
                    if (dist[v] > dist[u] + 1) {
                        dist[v] = dist[u] + 1;
                        q.push(v);
                    }
                }
            }
        }
        return dist;
    }

public:
    int specialNodes(int n, vector<vector<int>>& edges, int x, int y, int z) {
        this->n = n;
        g.assign(n, {});
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }

        vector<int> d1 = bfs(x);
        vector<int> d2 = bfs(y);
        vector<int> d3 = bfs(z);

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            array<long long, 3> a = {
                (long long) d1[i],
                (long long) d2[i],
                (long long) d3[i]};
            sort(a.begin(), a.end());
            if (a[0] * a[0] + a[1] * a[1] == a[2] * a[2]) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func specialNodes(n int, edges [][]int, x int, y int, z int) int {
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}

	const inf = int(1e9)

	bfs := func(i int) []int {
		dist := make([]int, n)
		for k := 0; k < n; k++ {
			dist[k] = inf
		}
		q := make([]int, 0)
		dist[i] = 0
		q = append(q, i)
		for len(q) > 0 {
			sz := len(q)
			for ; sz > 0; sz-- {
				u := q[0]
				q = q[1:]
				for _, v := range g[u] {
					if dist[v] > dist[u]+1 {
						dist[v] = dist[u] + 1
						q = append(q, v)
					}
				}
			}
		}
		return dist
	}

	d1 := bfs(x)
	d2 := bfs(y)
	d3 := bfs(z)

	ans := 0
	for i := 0; i < n; i++ {
		a := []int{d1[i], d2[i], d3[i]}
		sort.Ints(a)
		x0, x1, x2 := int64(a[0]), int64(a[1]), int64(a[2])
		if x0*x0+x1*x1 == x2*x2 {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function specialNodes(n: number, edges: number[][], x: number, y: number, z: number): number {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }

    const inf = 1e9;

    const bfs = (i: number): number[] => {
        const dist = Array(n).fill(inf);
        let q: number[] = [i];
        dist[i] = 0;
        while (q.length) {
            const nq = [];
            for (const u of q) {
                for (const v of g[u]) {
                    if (dist[v] > dist[u] + 1) {
                        dist[v] = dist[u] + 1;
                        nq.push(v);
                    }
                }
            }
            q = nq;
        }
        return dist;
    };

    const d1 = bfs(x);
    const d2 = bfs(y);
    const d3 = bfs(z);

    let ans = 0;
    for (let i = 0; i < n; i++) {
        const a = [d1[i], d2[i], d3[i]];
        a.sort((p, q) => p - q);
        if (a[0] * a[0] + a[1] * a[1] === a[2] * a[2]) {
            ans++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
