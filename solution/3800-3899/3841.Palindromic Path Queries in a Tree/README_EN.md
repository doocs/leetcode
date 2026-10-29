---
comments: true
difficulty: Hard
rating: 2384
source: Biweekly Contest 176 Q4
tags:
    - Bit Manipulation
    - Tree
    - Depth-First Search
    - Binary Indexed Tree
    - Segment Tree
    - Array
    - String
    - Divide and Conquer
---

<!-- problem:start -->

# [3841. Palindromic Path Queries in a Tree](https://leetcode.com/problems/palindromic-path-queries-in-a-tree)

[中文文档](/solution/3800-3899/3841.Palindromic%20Path%20Queries%20in%20a%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given an undirected tree with <code>n</code> nodes labeled 0 to <code>n - 1</code>. This is represented by a 2D array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates an undirected edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>.</p>

<p>You are also given a string <code>s</code> of length <code>n</code> consisting of lowercase English letters, where <code>s[i]</code> represents the character assigned to node <code>i</code>.</p>

<p>You are also given a string array <code>queries</code>, where each <code>queries[i]</code> is either:</p>

<ul>
	<li><code>&quot;update u<sub>i</sub> c&quot;</code>: Change the character at node <code>u<sub>i</sub></code> to <code>c</code>. Formally, update <code>s[u<sub>i</sub>] = c</code>.</li>
	<li><code>&quot;query u<sub>i</sub> v<sub>i</sub>&quot;</code>: Determine whether the string formed by the characters on the <strong>unique</strong> path from <code>u<sub>i</sub></code> to <code>v<sub>i</sub></code> (inclusive) can be <strong>rearranged</strong> into a <strong><span data-keyword="palindrome-string">palindrome</span></strong>.</li>
</ul>

<p>Return a boolean array <code>answer</code>, where <code>answer[j]</code> is <code>true</code> if the <code>j<sup>th</sup></code> query of type <code>&quot;query u<sub>i</sub> v<sub>i</sub>&quot;​​​​​​​</code> can be rearranged into a <strong>palindrome</strong>, and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, edges = [[0,1],[1,2]], s = &quot;aac&quot;, queries = [&quot;query 0 2&quot;,&quot;update 1 b&quot;,&quot;query 0 2&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[true,false]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>&quot;query 0 2&quot;</code>: Path <code>0 &rarr; 1 &rarr; 2</code> gives <code>&quot;aac&quot;</code>, which can be rearranged to form <code>&quot;aca&quot;</code>, a palindrome. Thus, <code>answer[0] = true</code>.</li>
	<li><code>&quot;update 1 b&quot;</code>: Update node 1 to <code>&#39;b&#39;</code>, now <code>s = &quot;abc&quot;</code>.</li>
	<li><code>&quot;query 0 2&quot;</code>: Path characters are <code>&quot;abc&quot;</code>, which cannot be rearranged to form a palindrome. Thus, <code>answer[1] = false</code>.</li>
</ul>

<p>Thus, <code>answer = [true, false]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, edges = [[0,1],[0,2],[0,3]], s = &quot;abca&quot;, queries = [&quot;query 1 2&quot;,&quot;update 0 b&quot;,&quot;query 2 3&quot;,&quot;update 3 a&quot;,&quot;query 1 3&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[false,false,true]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>&quot;query 1 2&quot;</code>: Path <code>1 &rarr; 0 &rarr; 2</code> gives <code>&quot;bac&quot;</code>, which cannot be rearranged to form a palindrome. Thus, <code>answer[0] = false</code>.</li>
	<li><code>&quot;update 0 b&quot;</code>: Update node 0 to <code>&#39;b&#39;</code>, now <code>s = &quot;bbca&quot;</code>.</li>
	<li><code>&quot;query 2 3&quot;</code>: Path <code>2 &rarr; 0 &rarr; 3</code> gives <code>&quot;cba&quot;</code>, which cannot be rearranged to form a palindrome. Thus, <code>answer[1] = false</code>.</li>
	<li><code>&quot;update 3 a&quot;</code>: Update node 3 to <code>&#39;a&#39;</code>, <code>s = &quot;bbca&quot;</code>.</li>
	<li><code>&quot;query 1 3&quot;</code>: Path <code>1 &rarr; 0 &rarr; 3</code> gives <code>&quot;bba&quot;</code>, which can be rearranged to form <code>&quot;bab&quot;</code>, a palindrome. Thus, <code>answer[2] = true</code>.</li>
</ul>

<p>Thus, <code>answer = [false, false, true]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
	<li><code>1 &lt;= queries.length &lt;= 5 * 10<sup>4</sup></code>​​​​​​​
	<ul>
		<li><code>queries[i] = &quot;update u<sub>i</sub> c&quot;</code> or</li>
		<li><code>queries[i] = &quot;query u<sub>i</sub> v<sub>i</sub>&quot;</code></li>
		<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
		<li><code>c</code> is a lowercase English letter.</li>
	</ul>
	</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Euler Tour + Binary Indexed Tree + Binary Lifting

<!-- thinking:start -->

> **Thinking**
>
> A path can be rearranged into a palindrome iff at most one character has an odd count. With $n$ and the number of queries at most $5 \times 10^4$, walking each path is too slow.
>
> The bottleneck is supporting point updates while still reading the parity of every letter on an arbitrary path.
>
> Pack the $26$ letter parities into a bitmask. The path mask is the XOR of the two rootward prefixes, then the XOR of the LCA's character: the shared prefix and the LCA itself both cancel. Writing each node's mask at its Euler enter and exit times makes a prefix of an XOR Fenwick tree equal the rootward mask.
>
> Binary lifting finds the LCA. The path is palindromic when the mask has at most one bit set. An update XORs the difference of the old and new masks at that node's enter and exit times.

<!-- thinking:end -->

We store the parity of each character in a $26$-bit mask: bit $c$ is $1$ when character $c$ occurs an odd number of times. The characters on a path can be rearranged into a palindrome if and only if this mask has at most one bit set, including the all-zero mask. Equivalently, the bitwise AND of the mask and the mask minus one is $0$.

Let $\textit{pref}(u)$ be the XOR of the masks from the root to node $u$, inclusive. If $w$ is the lowest common ancestor of $u$ and $v$, the mask of the path from $u$ to $v$ is

$$\textit{pref}(u) \oplus \textit{pref}(v) \oplus \textit{bit}[w].$$

The portion from the root to $w$ appears once in each prefix and cancels. Node $w$ itself also cancels, so we XOR $\textit{bit}[w]$ to put it back on the path.

We maintain $\textit{pref}(u)$ with an Euler tour and an XOR binary indexed tree. During the DFS we record the entry time $\textit{tin}[u]$ and the exit time $\textit{tout}[u]$, and add $\textit{bit}[u]$ at both positions. A prefix query that ends at $\textit{tin}[u]$ counts each node on the path from the root to $u$ exactly once: its entry time lies inside the prefix and its exit time lies outside. A subtree that has already been fully exited is counted twice and cancels. The tree accumulates from index $1$, so $\textit{query}(\textit{tin}[u] + 1)$ in the code is $\textit{pref}(u)$.

When the character of node $u$ changes, let $\delta$ be the XOR of the old and new masks, and XOR $\delta$ at $\textit{tin}[u]$ and $\textit{tout}[u]$. The LCA is precomputed with binary lifting and each query takes $O(\log n)$.

The time complexity is $O((n + q) \log n)$ and the space complexity is $O(n \log n)$, where $n$ is the number of nodes and $q$ is the number of operations.

<!-- tabs:start -->

#### Python3

```python
class BinaryIndexedTree:
    def __init__(self, n: int):
        self.n = n
        self.tree = [0] * (n + 1)

    def update(self, i: int, delta: int) -> None:
        i += 1
        while i <= self.n:
            self.tree[i] ^= delta
            i += i & -i

    def query(self, i: int) -> int:
        res = 0
        while i:
            res ^= self.tree[i]
            i -= i & -i
        return res


class Solution:
    def palindromePath(
        self, n: int, edges: list[list[int]], s: str, queries: list[str]
    ) -> list[bool]:
        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)

        parent = [-1] * n
        depth = [0] * n
        tin = [0] * n
        tout = [0] * n
        timer = 0

        def dfs(u: int, p: int) -> None:
            nonlocal timer
            parent[u] = p
            tin[u] = timer
            timer += 1
            for v in g[u]:
                if v != p:
                    depth[v] = depth[u] + 1
                    dfs(v, u)
            tout[u] = timer

        dfs(0, -1)

        m = n.bit_length()
        up = [parent[:]]
        up[0][0] = 0
        for j in range(1, m):
            prev = up[-1]
            up.append([prev[prev[i]] for i in range(n)])

        def lca(u: int, v: int) -> int:
            if depth[u] < depth[v]:
                u, v = v, u
            d = depth[u] - depth[v]
            j = 0
            while d:
                if d & 1:
                    u = up[j][u]
                d >>= 1
                j += 1
            if u == v:
                return u
            for j in range(m - 1, -1, -1):
                if up[j][u] != up[j][v]:
                    u = up[j][u]
                    v = up[j][v]
            return parent[u]

        bit = [1 << (ord(c) - ord("a")) for c in s]
        tree = BinaryIndexedTree(n)

        for u in range(n):
            tree.update(tin[u], bit[u])
            tree.update(tout[u], bit[u])

        ans = []
        for q in queries:
            op, a, b = q.split()
            u = int(a)
            if op == "update":
                x = bit[u]
                y = 1 << (ord(b) - ord("a"))
                if x != y:
                    delta = x ^ y
                    tree.update(tin[u], delta)
                    tree.update(tout[u], delta)
                    bit[u] = y
            else:
                v = int(b)
                w = lca(u, v)
                mask = tree.query(tin[u] + 1) ^ tree.query(tin[v] + 1) ^ bit[w]
                ans.append(mask & (mask - 1) == 0)

        return ans
```

#### Java

```java
class BinaryIndexedTree {
    int n;
    int[] tree;

    BinaryIndexedTree(int n) {
        this.n = n;
        tree = new int[n + 1];
    }

    void update(int i, int delta) {
        for (i++; i <= n; i += i & -i) {
            tree[i] ^= delta;
        }
    }

    int query(int i) {
        int res = 0;
        for (; i > 0; i -= i & -i) {
            res ^= tree[i];
        }
        return res;
    }
}

class Solution {
    List<Integer>[] g;
    int[] parent, depth, tin, tout, bit;
    int[][] up;
    int timer, m;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        timer = 0;
        dfs(0, -1);

        m = 32 - Integer.numberOfLeadingZeros(n);
        up = new int[m][n];
        System.arraycopy(parent, 0, up[0], 0, n);
        for (int j = 1; j < m; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        bit = new int[n];
        for (int i = 0; i < n; i++) {
            bit[i] = 1 << (s.charAt(i) - 'a');
        }

        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        for (int i = 0; i < n; i++) {
            tree.update(tin[i], bit[i]);
            tree.update(tout[i], bit[i]);
        }

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            int u = Integer.parseInt(parts[1]);
            if (parts[0].equals("update")) {
                int x = bit[u];
                int y = 1 << (parts[2].charAt(0) - 'a');
                if (x != y) {
                    int delta = x ^ y;
                    tree.update(tin[u], delta);
                    tree.update(tout[u], delta);
                    bit[u] = y;
                }
            } else {
                int v = Integer.parseInt(parts[2]);
                int w = lca(u, v);
                int mask = tree.query(tin[u] + 1) ^ tree.query(tin[v] + 1) ^ bit[w];
                ans.add((mask & (mask - 1)) == 0);
            }
        }
        return ans;
    }

    void dfs(int u, int p) {
        parent[u] = p == -1 ? 0 : p;
        tin[u] = timer++;
        for (int v : g[u]) {
            if (v != p) {
                depth[v] = depth[u] + 1;
                dfs(v, u);
            }
        }
        tout[u] = timer;
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }
        int d = depth[u] - depth[v];
        for (int j = 0; d > 0; j++, d >>= 1) {
            if ((d & 1) != 0) {
                u = up[j][u];
            }
        }
        if (u == v) {
            return u;
        }
        for (int j = m - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }
        return parent[u];
    }
}
```

#### C++

```cpp
class BinaryIndexedTree {
    int n;
    vector<int> tree;

public:
    BinaryIndexedTree(int n)
        : n(n)
        , tree(n + 1) {}

    void update(int i, int delta) {
        for (++i; i <= n; i += i & -i) {
            tree[i] ^= delta;
        }
    }

    int query(int i) {
        int res = 0;
        for (; i > 0; i -= i & -i) {
            res ^= tree[i];
        }
        return res;
    }
};

class Solution {
    vector<vector<int>> g, up;
    vector<int> parent, depth, tin, tout, bit;
    int timer, m;

    void dfs(int u, int p) {
        parent[u] = p == -1 ? 0 : p;
        tin[u] = timer++;
        for (int v : g[u]) {
            if (v != p) {
                depth[v] = depth[u] + 1;
                dfs(v, u);
            }
        }
        tout[u] = timer;
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            swap(u, v);
        }
        int d = depth[u] - depth[v];
        for (int j = 0; d; j++, d >>= 1) {
            if (d & 1) {
                u = up[j][u];
            }
        }
        if (u == v) {
            return u;
        }
        for (int j = m - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }
        return parent[u];
    }

public:
    vector<bool> palindromePath(int n, vector<vector<int>>& edges, string s, vector<string>& queries) {
        g.assign(n, {});
        for (auto& e : edges) {
            g[e[0]].push_back(e[1]);
            g[e[1]].push_back(e[0]);
        }

        parent.resize(n);
        depth.assign(n, 0);
        tin.resize(n);
        tout.resize(n);
        timer = 0;
        dfs(0, -1);

        m = 32 - __builtin_clz(n);
        up.assign(m, vector<int>(n));
        up[0] = parent;
        for (int j = 1; j < m; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        bit.resize(n);
        for (int i = 0; i < n; i++) {
            bit[i] = 1 << (s[i] - 'a');
        }

        BinaryIndexedTree tree(n);
        for (int i = 0; i < n; i++) {
            tree.update(tin[i], bit[i]);
            tree.update(tout[i], bit[i]);
        }

        vector<bool> ans;
        for (string& q : queries) {
            stringstream ss(q);
            string op;
            int u;
            ss >> op >> u;
            if (op == "update") {
                string c;
                ss >> c;
                int y = 1 << (c[0] - 'a');
                if (bit[u] != y) {
                    int delta = bit[u] ^ y;
                    tree.update(tin[u], delta);
                    tree.update(tout[u], delta);
                    bit[u] = y;
                }
            } else {
                int v;
                ss >> v;
                int w = lca(u, v);
                int mask = tree.query(tin[u] + 1) ^ tree.query(tin[v] + 1) ^ bit[w];
                ans.push_back((mask & (mask - 1)) == 0);
            }
        }
        return ans;
    }
};
```

#### Go

```go
type BinaryIndexedTree struct {
	n    int
	tree []int
}

func NewBinaryIndexedTree(n int) *BinaryIndexedTree {
	return &BinaryIndexedTree{n: n, tree: make([]int, n+1)}
}

func (b *BinaryIndexedTree) update(i, delta int) {
	for i++; i <= b.n; i += i & -i {
		b.tree[i] ^= delta
	}
}

func (b *BinaryIndexedTree) query(i int) int {
	res := 0
	for ; i > 0; i -= i & -i {
		res ^= b.tree[i]
	}
	return res
}

func palindromePath(n int, edges [][]int, s string, queries []string) []bool {
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}

	parent := make([]int, n)
	depth := make([]int, n)
	tin := make([]int, n)
	tout := make([]int, n)
	timer := 0

	var dfs func(int, int)
	dfs = func(u, p int) {
		if p == -1 {
			parent[u] = 0
		} else {
			parent[u] = p
		}
		tin[u] = timer
		timer++
		for _, v := range g[u] {
			if v != p {
				depth[v] = depth[u] + 1
				dfs(v, u)
			}
		}
		tout[u] = timer
	}
	dfs(0, -1)

	m := 1
	for (1 << m) <= n {
		m++
	}
	up := make([][]int, m)
	up[0] = append([]int(nil), parent...)
	for j := 1; j < m; j++ {
		up[j] = make([]int, n)
		for i := 0; i < n; i++ {
			up[j][i] = up[j-1][up[j-1][i]]
		}
	}

	bit := make([]int, n)
	for i := 0; i < n; i++ {
		bit[i] = 1 << (s[i] - 'a')
	}

	tree := NewBinaryIndexedTree(n)
	for i := 0; i < n; i++ {
		tree.update(tin[i], bit[i])
		tree.update(tout[i], bit[i])
	}

	lca := func(u, v int) int {
		if depth[u] < depth[v] {
			u, v = v, u
		}
		d := depth[u] - depth[v]
		for j := 0; d > 0; j++ {
			if d&1 != 0 {
				u = up[j][u]
			}
			d >>= 1
		}
		if u == v {
			return u
		}
		for j := m - 1; j >= 0; j-- {
			if up[j][u] != up[j][v] {
				u = up[j][u]
				v = up[j][v]
			}
		}
		return parent[u]
	}

	ans := make([]bool, 0)
	for _, q := range queries {
		var op string
		var u int
		fmt.Sscanf(q, "%s %d", &op, &u)
		if op == "update" {
			var c string
			fmt.Sscanf(q, "%s %d %s", &op, &u, &c)
			y := 1 << (c[0] - 'a')
			if bit[u] != y {
				delta := bit[u] ^ y
				tree.update(tin[u], delta)
				tree.update(tout[u], delta)
				bit[u] = y
			}
		} else {
			var v int
			fmt.Sscanf(q, "%s %d %d", &op, &u, &v)
			w := lca(u, v)
			mask := tree.query(tin[u]+1) ^ tree.query(tin[v]+1) ^ bit[w]
			ans = append(ans, mask&(mask-1) == 0)
		}
	}
	return ans
}
```

#### TypeScript

```ts
class BinaryIndexedTree {
    n: number;
    tree: number[];

    constructor(n: number) {
        this.n = n;
        this.tree = Array(n + 1).fill(0);
    }

    update(i: number, delta: number): void {
        for (i++; i <= this.n; i += i & -i) {
            this.tree[i] ^= delta;
        }
    }

    query(i: number): number {
        let res = 0;
        for (; i > 0; i -= i & -i) {
            res ^= this.tree[i];
        }
        return res;
    }
}

function palindromePath(n: number, edges: number[][], s: string, queries: string[]): boolean[] {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }

    const parent = Array(n).fill(0);
    const depth = Array(n).fill(0);
    const tin = Array(n).fill(0);
    const tout = Array(n).fill(0);
    let timer = 0;

    function dfs(u: number, p: number): void {
        parent[u] = p === -1 ? 0 : p;
        tin[u] = timer++;
        for (const v of g[u]) {
            if (v !== p) {
                depth[v] = depth[u] + 1;
                dfs(v, u);
            }
        }
        tout[u] = timer;
    }
    dfs(0, -1);

    const m = 32 - Math.clz32(n);
    const up: number[][] = Array.from({ length: m }, () => Array(n).fill(0));
    up[0] = [...parent];
    for (let j = 1; j < m; j++) {
        for (let i = 0; i < n; i++) {
            up[j][i] = up[j - 1][up[j - 1][i]];
        }
    }

    const bit = Array(n).fill(0);
    for (let i = 0; i < n; i++) {
        bit[i] = 1 << (s.charCodeAt(i) - 97);
    }

    const tree = new BinaryIndexedTree(n);
    for (let i = 0; i < n; i++) {
        tree.update(tin[i], bit[i]);
        tree.update(tout[i], bit[i]);
    }

    function lca(u: number, v: number): number {
        if (depth[u] < depth[v]) {
            [u, v] = [v, u];
        }
        let d = depth[u] - depth[v];
        for (let j = 0; d > 0; j++, d >>= 1) {
            if (d & 1) {
                u = up[j][u];
            }
        }
        if (u === v) {
            return u;
        }
        for (let j = m - 1; j >= 0; j--) {
            if (up[j][u] !== up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }
        return parent[u];
    }

    const ans: boolean[] = [];
    for (const q of queries) {
        const parts = q.split(' ');
        const u = Number(parts[1]);
        if (parts[0] === 'update') {
            const y = 1 << (parts[2].charCodeAt(0) - 97);
            if (bit[u] !== y) {
                const delta = bit[u] ^ y;
                tree.update(tin[u], delta);
                tree.update(tout[u], delta);
                bit[u] = y;
            }
        } else {
            const v = Number(parts[2]);
            const w = lca(u, v);
            const mask = tree.query(tin[u] + 1) ^ tree.query(tin[v] + 1) ^ bit[w];
            ans.push((mask & (mask - 1)) === 0);
        }
    }
    return ans;
}
```

#### Rust

```rust
struct BinaryIndexedTree {
    n: usize,
    tree: Vec<i32>,
}

impl BinaryIndexedTree {
    fn new(n: usize) -> Self {
        Self {
            n,
            tree: vec![0; n + 1],
        }
    }

    fn update(&mut self, mut i: usize, delta: i32) {
        i += 1;
        while i <= self.n {
            self.tree[i] ^= delta;
            i += i & i.wrapping_neg();
        }
    }

    fn query(&self, mut i: usize) -> i32 {
        let mut res = 0;
        while i > 0 {
            res ^= self.tree[i];
            i -= i & i.wrapping_neg();
        }
        res
    }
}

impl Solution {
    pub fn palindrome_path(
        n: i32,
        edges: Vec<Vec<i32>>,
        s: String,
        queries: Vec<String>,
    ) -> Vec<bool> {
        let n = n as usize;
        let mut g = vec![vec![]; n];
        for e in &edges {
            let u = e[0] as usize;
            let v = e[1] as usize;
            g[u].push(v);
            g[v].push(u);
        }

        let mut parent = vec![0usize; n];
        let mut depth = vec![0usize; n];
        let mut tin = vec![0usize; n];
        let mut tout = vec![0usize; n];
        let mut timer = 0usize;

        fn dfs(
            u: usize,
            p: usize,
            g: &[Vec<usize>],
            parent: &mut [usize],
            depth: &mut [usize],
            tin: &mut [usize],
            tout: &mut [usize],
            timer: &mut usize,
        ) {
            parent[u] = if p == usize::MAX { 0 } else { p };
            tin[u] = *timer;
            *timer += 1;
            for &v in &g[u] {
                if v != p {
                    depth[v] = depth[u] + 1;
                    dfs(v, u, g, parent, depth, tin, tout, timer);
                }
            }
            tout[u] = *timer;
        }
        dfs(
            0,
            usize::MAX,
            &g,
            &mut parent,
            &mut depth,
            &mut tin,
            &mut tout,
            &mut timer,
        );

        let m = (32 - (n as u32).leading_zeros()) as usize;
        let mut up = vec![vec![0usize; n]; m];
        up[0].clone_from(&parent);
        for j in 1..m {
            for i in 0..n {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        let lca = |mut u: usize, mut v: usize| -> usize {
            if depth[u] < depth[v] {
                std::mem::swap(&mut u, &mut v);
            }
            let mut d = depth[u] - depth[v];
            let mut j = 0;
            while d > 0 {
                if d & 1 == 1 {
                    u = up[j][u];
                }
                d >>= 1;
                j += 1;
            }
            if u == v {
                return u;
            }
            for j in (0..m).rev() {
                if up[j][u] != up[j][v] {
                    u = up[j][u];
                    v = up[j][v];
                }
            }
            parent[u]
        };

        let bytes = s.as_bytes();
        let mut bit = vec![0i32; n];
        for i in 0..n {
            bit[i] = 1 << (bytes[i] - b'a') as u32;
        }

        let mut tree = BinaryIndexedTree::new(n);
        for i in 0..n {
            tree.update(tin[i], bit[i]);
            tree.update(tout[i], bit[i]);
        }

        let mut ans = Vec::new();
        for q in &queries {
            let parts: Vec<&str> = q.split(' ').collect();
            let u: usize = parts[1].parse().unwrap();
            if parts[0] == "update" {
                let x = bit[u];
                let y = 1 << (parts[2].as_bytes()[0] - b'a') as u32;
                if x != y {
                    let delta = x ^ y;
                    tree.update(tin[u], delta);
                    tree.update(tout[u], delta);
                    bit[u] = y;
                }
            } else {
                let v: usize = parts[2].parse().unwrap();
                let w = lca(u, v);
                let mask = tree.query(tin[u] + 1) ^ tree.query(tin[v] + 1) ^ bit[w];
                ans.push(mask & (mask - 1) == 0);
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
