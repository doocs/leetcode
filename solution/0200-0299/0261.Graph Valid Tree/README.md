---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0261.Graph%20Valid%20Tree/README.md
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 图
---

<!-- problem:start -->

# [261. 以图判树 🔒](https://leetcode.cn/problems/graph-valid-tree)

[English Version](/solution/0200-0299/0261.Graph%20Valid%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定编号从 <code>0</code> 到 <code>n - 1</code>&nbsp;的&nbsp;<code>n</code> 个结点。给定一个整数&nbsp;<code>n</code>&nbsp;和一个&nbsp;<code>edges</code>&nbsp;列表，其中&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示图中节点&nbsp;<code>a<sub>i</sub></code>&nbsp;和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间存在一条无向边。</p>

<p>如果这些边能够形成一个合法有效的树结构，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0261.Graph%20Valid%20Tree/images/tree1-graph.jpg" /></p>

<pre>
<strong>输入:</strong> <code>n = 5</code>, edges<code> = [[0,1],[0,2],[0,3],[1,4]]</code>
<strong>输出:</strong> true</pre>

<p><strong>示例 2:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0261.Graph%20Valid%20Tree/images/tree2-graph.jpg" /></p>

<pre>
<strong>输入:</strong> <code>n = 5, </code>edges<code> = [[0,1],[1,2],[2,3],[1,3],[1,4]]</code>
<strong>输出:</strong> false</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2000</code></li>
	<li><code>0 &lt;= edges.length &lt;= 5000</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub>&nbsp;&lt; n</code></li>
	<li><code>a<sub>i</sub>&nbsp;!= b<sub>i</sub></code></li>
	<li>不存在自循环或重复的边</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：并查集

判断是否是树，需要满足以下两个条件：

1. 边的数量等于节点数减一；
2. 不存在环。

我们可以使用并查集来判断是否存在环。遍历边，如果两个节点已经在同一个集合中，说明存在环。否则，我们将两个节点合并到同一个集合中。然后将连通分量的数量减一，最后判断连通分量的数量是否为 $1$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是节点数。

<!-- tabs:start -->

```python
class Solution:
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(n))
        for a, b in edges:
            pa, pb = find(a), find(b)
            if pa == pb:
                return False
            p[pa] = pb
            n -= 1
        return n == 1
```

```java
class Solution {
    private int[] p;

    public boolean validTree(int n, int[][] edges) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (var e : edges) {
            int pa = find(e[0]), pb = find(e[1]);
            if (pa == pb) {
                return false;
            }
            p[pa] = pb;
            --n;
        }
        return n == 1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

```cpp
class Solution {
public:
    bool validTree(int n, vector<vector<int>>& edges) {
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        function<int(int)> find = [&](int x) {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        for (auto& e : edges) {
            int pa = find(e[0]), pb = find(e[1]);
            if (pa == pb) {
                return false;
            }
            p[pa] = pb;
            --n;
        }
        return n == 1;
    }
};
```

```go
func validTree(n int, edges [][]int) bool {
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, e := range edges {
		pa, pb := find(e[0]), find(e[1])
		if pa == pb {
			return false
		}
		p[pa] = pb
		n--
	}
	return n == 1
}
```

```js
/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {boolean}
 */
var validTree = function (n, edges) {
    const p = Array.from({ length: n }, (_, i) => i);
    const find = x => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    for (const [a, b] of edges) {
        const pa = find(a);
        const pb = find(b);
        if (pa === pb) {
            return false;
        }
        p[pa] = pb;
        --n;
    }
    return n === 1;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：DFS

我们也可以使用深度优先搜索来判断是否存在环。我们可以使用一个数组 $vis$ 来记录访问过的节点，搜索时，我们先将节点标记为已访问，然后遍历与该节点相邻的节点，如果相邻节点已经访问过，则跳过，否则递归访问相邻节点。最后，我们判断是否所有节点都被访问过，如果有未访问过的节点，说明无法构成树，返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是节点数。

<!-- tabs:start -->

```python
class Solution:
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        def dfs(i: int):
            vis.add(i)
            for j in g[i]:
                if j not in vis:
                    dfs(j)

        if len(edges) != n - 1:
            return False
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = set()
        dfs(0)
        return len(vis) == n
```

```java
class Solution {
    private List<Integer>[] g;
    private Set<Integer> vis = new HashSet<>();

    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        dfs(0);
        return vis.size() == n;
    }

    private void dfs(int i) {
        vis.add(i);
        for (int j : g[i]) {
            if (!vis.contains(j)) {
                dfs(j);
            }
        }
    }
}
```

```cpp
class Solution {
public:
    bool validTree(int n, vector<vector<int>>& edges) {
        if (edges.size() != n - 1) {
            return false;
        }
        vector<int> g[n];
        vector<int> vis(n);
        function<void(int)> dfs = [&](int i) {
            vis[i] = true;
            --n;
            for (int j : g[i]) {
                if (!vis[j]) {
                    dfs(j);
                }
            }
        };
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        dfs(0);
        return n == 0;
    }
};
```

```go
func validTree(n int, edges [][]int) bool {
	if len(edges) != n-1 {
		return false
	}
	g := make([][]int, n)
	vis := make([]bool, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var dfs func(int)
	dfs = func(i int) {
		vis[i] = true
		n--
		for _, j := range g[i] {
			if !vis[j] {
				dfs(j)
			}
		}
	}
	dfs(0)
	return n == 0
}
```

```js
/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {boolean}
 */
var validTree = function (n, edges) {
    if (edges.length !== n - 1) {
        return false;
    }
    const g = Array.from({ length: n }, () => []);
    const vis = Array.from({ length: n }, () => false);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const dfs = i => {
        vis[i] = true;
        --n;
        for (const j of g[i]) {
            if (!vis[j]) {
                dfs(j);
            }
        }
    };
    dfs(0);
    return n === 0;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
