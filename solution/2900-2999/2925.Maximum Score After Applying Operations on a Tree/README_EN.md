---
comments: true
difficulty: Medium
rating: 1939
source: Weekly Contest 370 Q3
tags:
    - Tree
    - Depth-First Search
    - Dynamic Programming
    - Tree DP
---

<!-- problem:start -->

# [2925. Maximum Score After Applying Operations on a Tree](https://leetcode.com/problems/maximum-score-after-applying-operations-on-a-tree)

[中文文档](/solution/2900-2999/2925.Maximum%20Score%20After%20Applying%20Operations%20on%20a%20Tree/README.md)

## Description

<!-- description:start -->

<p>There is an undirected tree with <code>n</code> nodes labeled from 0 to <code>n - 1</code>, and rooted at node 0. You are given&nbsp;a 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree.</p>

<p>You are also given a <strong>0-indexed</strong> integer array <code>values</code> of length <code>n</code>, where <code>values[i]</code> is the <strong>value</strong> associated with the <code>i<sup>th</sup></code> node.</p>

<p>You start with a score of 0. In one operation, you choose a node <code>i</code>, <strong>add</strong> <code>values[i]</code> to your score, and then <strong>set</strong> <code>values[i]</code> to 0. All three steps happen together as a single operation.</p>

<p>A tree is <strong>healthy</strong> if, for <strong>every</strong> leaf node, the sum of the <strong>current</strong> values on the path from the root to that leaf is <strong>not equal</strong> to 0.</p>

<p>Return the <strong>maximum score</strong> you can obtain by performing this operation on the tree any number of times, so that the tree remains <strong>healthy</strong> at the end.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2925.Maximum%20Score%20After%20Applying%20Operations%20on%20a%20Tree/images/graph-13-1.png" style="width: 515px; height: 443px;" />
<pre>
<strong>Input:</strong> edges = [[0,1],[0,2],[0,3],[2,4],[4,5]], values = [5,2,5,2,1,1]
<strong>Output:</strong> 11
<strong>Explanation:</strong> We apply the operation to nodes 1, 2, 3, 4, and 5, so the values become [5,0,0,0,0,0]. The leaves are nodes 1, 3, and 5.
- The sum of values on the path from 0 to 1 is equal to 5.
- The sum of values on the path from 0 to 3 is equal to 5.
- The sum of values on the path from 0 to 5 is equal to 5.
Every leaf has a non-zero path sum, so the tree is healthy. The score is the sum of the original values of the chosen nodes: 2 + 5 + 2 + 1 + 1 = 11.
It can be shown that 11 is the maximum score obtainable by performing any number of operations on the tree.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2925.Maximum%20Score%20After%20Applying%20Operations%20on%20a%20Tree/images/graph-14-2.png" style="width: 522px; height: 245px;" />
<pre>
<strong>Input:</strong> edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], values = [20,10,9,7,4,3,5]
<strong>Output:</strong> 40
<strong>Explanation:</strong> We apply the operation to nodes 0, 2, 3, and 4, so the values become [0,10,0,0,0,3,5]. The leaves are nodes 3, 4, 5, and 6.
- The sum of values on the path from 0 to 3 is equal to 10.
- The sum of values on the path from 0 to 4 is equal to 10.
- The sum of values on the path from 0 to 5 is equal to 3.
- The sum of values on the path from 0 to 6 is equal to 5.
Every leaf has a non-zero path sum, so the tree is healthy. The score is the sum of the original values of the chosen nodes: 20 + 9 + 7 + 4 = 40.
It can be shown that 40 is the maximum score obtainable by performing any number of operations on the tree.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>values.length == n</code></li>
	<li><code>1 &lt;= values[i] &lt;= 10<sup>9</sup></code></li>
	<li>The input is generated such that <code>edges</code> represents a valid tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Tree DP

<!-- thinking:start -->

> **Thinking**
>
> Every root-to-leaf path must keep at least one unselected node; the rest may add to the score. Enumerating select/skip at each vertex while enforcing every path explodes. Tree DP localizes the constraint: skip the root and take whole subtrees, or take the root and leave each subtree still valid.
>
> $dfs$ returns the subtree sum and the best valid selection. A leaf can only leave itself unselected, so the second value is $0$. An internal node takes $\max(values[i]+b, a)$. The answer is the second value at the root.

<!-- thinking:end -->

The problem is actually asking us to select some nodes from all nodes of the tree so that the sum of these nodes' values is maximized, and there is one node on each path from the root node to the leaf node that is not selected.

We can use the method of tree DP to solve this problem.

We design a function $dfs(i, fa)$, where $i$ represents the current node with node $i$ as the root of the subtree, and $fa$ represents the parent node of $i$. The function returns an array of length $2$, where $[0]$ represents the sum of the values of all nodes in the subtree, and $[1]$ represents the maximum value of the subtree satisfying that there is one node not selected on each path.

The value of $[0]$ can be obtained directly by DFS accumulating the values of each node, while the value of $[1]$ needs to consider two situations, namely whether node $i$ is selected. If it is selected, then each subtree of node $i$ must satisfy that there is one node not selected on each path; if it is not selected, then all nodes of each subtree of node $i$ can be selected. We take the maximum of these two situations.

It should be noted that the value of $[1]$ of the leaf node is $0$, because the leaf node has no subtree, so there is no need to consider the situation where there is one node not selected on each path.

The answer is $dfs(0, -1)[1]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumScoreAfterOperations(
        self, edges: List[List[int]], values: List[int]
    ) -> int:
        def dfs(i: int, fa: int = -1) -> (int, int):
            a = b = 0
            leaf = True
            for j in g[i]:
                if j != fa:
                    leaf = False
                    aa, bb = dfs(j, i)
                    a += aa
                    b += bb
            if leaf:
                return values[i], 0
            return values[i] + a, max(values[i] + b, a)

        g = [[] for _ in range(len(values))]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        return dfs(0)[1]
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private int[] values;

    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        int n = values.length;
        g = new List[n];
        this.values = values;
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        return dfs(0, -1)[1];
    }

    private long[] dfs(int i, int fa) {
        long a = 0, b = 0;
        boolean leaf = true;
        for (int j : g[i]) {
            if (j != fa) {
                leaf = false;
                var t = dfs(j, i);
                a += t[0];
                b += t[1];
            }
        }
        if (leaf) {
            return new long[] {values[i], 0};
        }
        return new long[] {values[i] + a, Math.max(values[i] + b, a)};
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumScoreAfterOperations(vector<vector<int>>& edges, vector<int>& values) {
        int n = values.size();
        vector<int> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        using ll = long long;
        function<pair<ll, ll>(int, int)> dfs = [&](int i, int fa) -> pair<ll, ll> {
            ll a = 0, b = 0;
            bool leaf = true;
            for (int j : g[i]) {
                if (j != fa) {
                    auto [aa, bb] = dfs(j, i);
                    a += aa;
                    b += bb;
                    leaf = false;
                }
            }
            if (leaf) {
                return {values[i], 0LL};
            }
            return {values[i] + a, max(values[i] + b, a)};
        };
        auto [_, b] = dfs(0, -1);
        return b;
    }
};
```

#### Go

```go
func maximumScoreAfterOperations(edges [][]int, values []int) int64 {
	g := make([][]int, len(values))
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var dfs func(int, int) (int64, int64)
	dfs = func(i, fa int) (int64, int64) {
		a, b := int64(0), int64(0)
		leaf := true
		for _, j := range g[i] {
			if j != fa {
				leaf = false
				aa, bb := dfs(j, i)
				a += aa
				b += bb
			}
		}
		if leaf {
			return int64(values[i]), int64(0)
		}
		return int64(values[i]) + a, max(int64(values[i])+b, a)
	}
	_, b := dfs(0, -1)
	return b
}
```

#### TypeScript

```ts
function maximumScoreAfterOperations(edges: number[][], values: number[]): number {
    const g: number[][] = Array.from({ length: values.length }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const dfs = (i: number, fa: number): [number, number] => {
        let [a, b] = [0, 0];
        let leaf = true;
        for (const j of g[i]) {
            if (j !== fa) {
                const [aa, bb] = dfs(j, i);
                a += aa;
                b += bb;
                leaf = false;
            }
        }
        if (leaf) {
            return [values[i], 0];
        }
        return [values[i] + a, Math.max(values[i] + b, a)];
    };
    return dfs(0, -1)[1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Tree DP + Explicit Stack

<!-- thinking:start -->

> **Thinking**
>
> Every root-to-leaf path must keep at least one unselected node; the remaining values may add to the score. Enumerating select or skip at each vertex while enforcing every path grows with the number of paths and does not fit $n \le 2 \times 10^4$. Once the choice is localized to a subtree, skipping the current node takes each child subtree whole, and taking it requires every child subtree to stay valid; both choices depend only on the subtree sum and the valid score, so child order does not matter. Filling that pair by recursion along a chain uses a call depth equal to the node count and overflows Python's recursion limit. The stack therefore stores $(node, parent, state)$: state $0$ pushes the exit marker and then the children, and state $1$ writes the pair after the children are ready. A leaf stores second value $0$, an internal node takes $\max(values[i]+b, a)$, and the answer is the second value at the root.

<!-- thinking:end -->

The problem asks us to select some nodes so that the sum of their values is maximized, and every path from the root to a leaf keeps at least one node unselected.

We record two quantities for each subtree with tree DP and fill them in postorder on an explicit stack. For node $i$, the first value is the sum of every node in the subtree, and the second value is the maximum score the subtree can obtain while satisfying the path constraint. A leaf has no child, so it can leave an unselected node only by skipping itself: the first value is $values[i]$ and the second value is $0$. An internal node has two choices. Skipping $i$ takes every node in each child subtree, scoring the sum of those subtree sums $a$. Taking $i$ requires each child subtree to stay valid on its own, scoring $values[i]$ plus the sum of the child scores $b$. We keep the larger of the two.

Each stack frame is $(i, fa, state)$. When $state = 0$, we push $(i, fa, 1)$ and then push every neighbor other than the parent with state $0$, so the children finish first. When $state = 1$, we read the finished child results and write node $i$ by the rule above. The order in which children are pushed does not change the answer.

The answer is the second value at the root.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumScoreAfterOperations(
        self, edges: List[List[int]], values: List[int]
    ) -> int:
        n = len(values)
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        sub = [(0, 0)] * n
        stk = [(0, -1, 0)]
        while stk:
            i, fa, state = stk.pop()
            if state == 0:
                stk.append((i, fa, 1))
                for j in g[i]:
                    if j != fa:
                        stk.append((j, i, 0))
            else:
                a = b = 0
                leaf = True
                for j in g[i]:
                    if j != fa:
                        leaf = False
                        aa, bb = sub[j]
                        a += aa
                        b += bb
                if leaf:
                    sub[i] = (values[i], 0)
                else:
                    sub[i] = (values[i] + a, max(values[i] + b, a))
        return sub[0][1]
```

#### Java

```java
class Solution {
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        int n = values.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        long[] sum = new long[n];
        long[] best = new long[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push(new int[] {i, fa, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push(new int[] {j, i, 0});
                    }
                }
            } else {
                long a = 0, b = 0;
                boolean leaf = true;
                for (int j : g[i]) {
                    if (j != fa) {
                        leaf = false;
                        a += sum[j];
                        b += best[j];
                    }
                }
                if (leaf) {
                    sum[i] = values[i];
                    best[i] = 0;
                } else {
                    sum[i] = values[i] + a;
                    best[i] = Math.max(values[i] + b, a);
                }
            }
        }
        return best[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumScoreAfterOperations(vector<vector<int>>& edges, vector<int>& values) {
        int n = values.size();
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        using ll = long long;
        vector<pair<ll, ll>> sub(n);
        vector<array<int, 3>> stk{{0, -1, 0}};
        while (!stk.empty()) {
            auto [i, fa, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({i, fa, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push_back({j, i, 0});
                    }
                }
            } else {
                ll a = 0, b = 0;
                bool leaf = true;
                for (int j : g[i]) {
                    if (j != fa) {
                        leaf = false;
                        a += sub[j].first;
                        b += sub[j].second;
                    }
                }
                if (leaf) {
                    sub[i] = {values[i], 0};
                } else {
                    sub[i] = {values[i] + a, max(values[i] + b, a)};
                }
            }
        }
        return sub[0].second;
    }
};
```

#### Go

```go
func maximumScoreAfterOperations(edges [][]int, values []int) int64 {
	n := len(values)
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	type pair struct{ sum, best int64 }
	sub := make([]pair, n)
	type frame struct{ i, fa, state int }
	stk := []frame{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, fa, state := cur.i, cur.fa, cur.state
		if state == 0 {
			stk = append(stk, frame{i, fa, 1})
			for _, j := range g[i] {
				if j != fa {
					stk = append(stk, frame{j, i, 0})
				}
			}
		} else {
			var a, b int64
			leaf := true
			for _, j := range g[i] {
				if j != fa {
					leaf = false
					a += sub[j].sum
					b += sub[j].best
				}
			}
			if leaf {
				sub[i] = pair{int64(values[i]), 0}
			} else {
				sub[i] = pair{int64(values[i]) + a, max(int64(values[i])+b, a)}
			}
		}
	}
	return sub[0].best
}
```

#### TypeScript

```ts
function maximumScoreAfterOperations(edges: number[][], values: number[]): number {
    const n = values.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const sum = Array(n).fill(0);
    const best = Array(n).fill(0);
    const stk: number[][] = [[0, -1, 0]];
    while (stk.length) {
        const [i, fa, state] = stk.pop()!;
        if (state === 0) {
            stk.push([i, fa, 1]);
            for (const j of g[i]) {
                if (j !== fa) {
                    stk.push([j, i, 0]);
                }
            }
        } else {
            let a = 0;
            let b = 0;
            let leaf = true;
            for (const j of g[i]) {
                if (j !== fa) {
                    leaf = false;
                    a += sum[j];
                    b += best[j];
                }
            }
            if (leaf) {
                sum[i] = values[i];
                best[i] = 0;
            } else {
                sum[i] = values[i] + a;
                best[i] = Math.max(values[i] + b, a);
            }
        }
    }
    return best[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
