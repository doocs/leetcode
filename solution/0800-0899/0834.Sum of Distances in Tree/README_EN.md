---
comments: true
difficulty: Hard
tags:
    - Tree
    - Depth-First Search
    - Graph
    - Dynamic Programming
    - Tree DP
---

<!-- problem:start -->

# [834. Sum of Distances in Tree](https://leetcode.com/problems/sum-of-distances-in-tree)

[中文文档](/solution/0800-0899/0834.Sum%20of%20Distances%20in%20Tree/README.md)

## Description

<!-- description:start -->

<p>There is an undirected connected tree with <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code> and <code>n - 1</code> edges.</p>

<p>You are given the integer <code>n</code> and the array <code>edges</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree.</p>

<p>Return an array <code>answer</code> of length <code>n</code> where <code>answer[i]</code> is the sum of the distances between the <code>i<sup>th</sup></code> node in the tree and all other nodes.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0834.Sum%20of%20Distances%20in%20Tree/images/lc-sumdist1.jpg" style="width: 304px; height: 224px;" />
<pre>
<strong>Input:</strong> n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
<strong>Output:</strong> [8,12,6,10,10,10]
<strong>Explanation:</strong> The tree is shown above.
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.
Hence, answer[0] = 8, and so on.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0834.Sum%20of%20Distances%20in%20Tree/images/lc-sumdist2.jpg" style="width: 64px; height: 65px;" />
<pre>
<strong>Input:</strong> n = 1, edges = []
<strong>Output:</strong> [0]
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0834.Sum%20of%20Distances%20in%20Tree/images/lc-sumdist3.jpg" style="width: 144px; height: 145px;" />
<pre>
<strong>Input:</strong> n = 2, edges = [[1,0]]
<strong>Output:</strong> [1,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>The given input represents a valid tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Tree DP (Re-rooting)

<!-- thinking:start -->

> **Thinking**
>
> We need the sum of distances from every node. $n\le 3\cdot 10^4$, so a DFS from each root is quadratic. After rerooting, neighboring answers differ only by subtree size versus the rest of the tree.
>
> Root at $0$ to get $ans[0]$ and subtree sizes, then a second DFS pushes $t-\textit{size}[j]+n-\textit{size}[j]$ to each child. Two traversals fill every answer.

<!-- thinking:end -->

First, we run a DFS to calculate the size of each node's subtree, recorded in the array $size$, and compute the sum of distances from node $0$ to all other nodes, recorded in $ans[0]$.

Next, we run another DFS to enumerate the sum of distances from each node when it is considered as the root. Suppose the answer for the current node $i$ is $t$. When we move from node $i$ to node $j$, the sum of distances changes to $t - size[j] + n - size[j]$, meaning the sum of distances to node $j$ and its subtree nodes decreases by $size[j]$, while the sum of distances to other nodes increases by $n - size[j]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes in the tree.

Similar problems:

- [2581. Count Number of Possible Root Nodes](https://github.com/doocs/leetcode/blob/main/solution/2500-2599/2581.Count%20Number%20of%20Possible%20Root%20Nodes/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOfDistancesInTree(self, n: int, edges: List[List[int]]) -> List[int]:
        def dfs1(i: int, fa: int, d: int):
            ans[0] += d
            size[i] = 1
            for j in g[i]:
                if j != fa:
                    dfs1(j, i, d + 1)
                    size[i] += size[j]

        def dfs2(i: int, fa: int, t: int):
            ans[i] = t
            for j in g[i]:
                if j != fa:
                    dfs2(j, i, t - size[j] + n - size[j])

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)

        ans = [0] * n
        size = [0] * n
        dfs1(0, -1, 0)
        dfs2(0, -1, ans[0])
        return ans
```

#### Java

```java
class Solution {
    private int n;
    private int[] ans;
    private int[] size;
    private List<Integer>[] g;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        this.n = n;
        g = new List[n];
        ans = new int[n];
        size = new int[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        dfs1(0, -1, 0);
        dfs2(0, -1, ans[0]);
        return ans;
    }

    private void dfs1(int i, int fa, int d) {
        ans[0] += d;
        size[i] = 1;
        for (int j : g[i]) {
            if (j != fa) {
                dfs1(j, i, d + 1);
                size[i] += size[j];
            }
        }
    }

    private void dfs2(int i, int fa, int t) {
        ans[i] = t;
        for (int j : g[i]) {
            if (j != fa) {
                dfs2(j, i, t - size[j] + n - size[j]);
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> sumOfDistancesInTree(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<int> ans(n);
        vector<int> size(n);

        function<void(int, int, int)> dfs1 = [&](int i, int fa, int d) {
            ans[0] += d;
            size[i] = 1;
            for (int& j : g[i]) {
                if (j != fa) {
                    dfs1(j, i, d + 1);
                    size[i] += size[j];
                }
            }
        };

        function<void(int, int, int)> dfs2 = [&](int i, int fa, int t) {
            ans[i] = t;
            for (int& j : g[i]) {
                if (j != fa) {
                    dfs2(j, i, t - size[j] + n - size[j]);
                }
            }
        };

        dfs1(0, -1, 0);
        dfs2(0, -1, ans[0]);
        return ans;
    }
};
```

#### Go

```go
func sumOfDistancesInTree(n int, edges [][]int) []int {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	ans := make([]int, n)
	size := make([]int, n)
	var dfs1 func(i, fa, d int)
	dfs1 = func(i, fa, d int) {
		ans[0] += d
		size[i] = 1
		for _, j := range g[i] {
			if j != fa {
				dfs1(j, i, d+1)
				size[i] += size[j]
			}
		}
	}
	var dfs2 func(i, fa, t int)
	dfs2 = func(i, fa, t int) {
		ans[i] = t
		for _, j := range g[i] {
			if j != fa {
				dfs2(j, i, t-size[j]+n-size[j])
			}
		}
	}
	dfs1(0, -1, 0)
	dfs2(0, -1, ans[0])
	return ans
}
```

#### TypeScript

```ts
function sumOfDistancesInTree(n: number, edges: number[][]): number[] {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const ans: number[] = new Array(n).fill(0);
    const size: number[] = new Array(n).fill(0);
    const dfs1 = (i: number, fa: number, d: number) => {
        ans[0] += d;
        size[i] = 1;
        for (const j of g[i]) {
            if (j !== fa) {
                dfs1(j, i, d + 1);
                size[i] += size[j];
            }
        }
    };
    const dfs2 = (i: number, fa: number, t: number) => {
        ans[i] = t;
        for (const j of g[i]) {
            if (j !== fa) {
                dfs2(j, i, t - size[j] + n - size[j]);
            }
        }
    };
    dfs1(0, -1, 0);
    dfs2(0, -1, ans[0]);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Re-rooting DP + Explicit Stack

<!-- thinking:start -->

> **Thinking**
>
> We need the sum of distances from every node. With $n\le 3\cdot 10^4$, a walk from each root is quadratic. After rerooting, neighboring answers differ only by subtree size versus the rest of the tree. Recursion along a chain uses a call depth equal to $n$ and overflows Python once the chain reaches length $1000$. Subtree sizes are known only after the children finish, so the first walk is a post-order stack: state $0$ adds the depth and pushes the children, and state $1$ totals $size$. The second answer depends only on the parent's answer and the finished sizes, so a pre-order stack passes $t-size[j]+n-size[j]$ to each child.

<!-- thinking:end -->

Root the tree at $0$ and walk in post-order. The stack holds a node, its parent, a depth, and a state. In state $0$, add the depth to $ans[0]$, push the same node with state $1$, and push every child. In state $1$, every child size is already stored, and $size[i]$ is $1$ plus those sizes.

Then reroot with an explicit stack. It starts with $(0,-1,ans[0])$. When node $i$ is popped, write the current sum $t$ into $ans[i]$. Moving from $i$ to a child $j$ decreases the distances inside $j$'s subtree by $size[j]$ and increases the distances to every other node by $n-size[j]$, so the pushed sum is $t-size[j]+n-size[j]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes in the tree.

Similar problems:

- [2581. Count Number of Possible Root Nodes](https://github.com/doocs/leetcode/blob/main/solution/2500-2599/2581.Count%20Number%20of%20Possible%20Root%20Nodes/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumOfDistancesInTree(self, n: int, edges: List[List[int]]) -> List[int]:
        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        ans = [0] * n
        size = [0] * n
        stk = [(0, -1, 0, 0)]
        while stk:
            i, fa, d, state = stk.pop()
            if state == 0:
                ans[0] += d
                stk.append((i, fa, d, 1))
                for j in g[i]:
                    if j != fa:
                        stk.append((j, i, d + 1, 0))
            else:
                size[i] = 1
                for j in g[i]:
                    if j != fa:
                        size[i] += size[j]
        walk = [(0, -1, ans[0])]
        while walk:
            i, fa, t = walk.pop()
            ans[i] = t
            for j in g[i]:
                if j != fa:
                    walk.append((j, i, t - size[j] + n - size[j]))
        return ans
```

#### Java

```java
class Solution {
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int[] ans = new int[n];
        int[] size = new int[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1, 0, 0});
        while (!stk.isEmpty()) {
            int[] f = stk.pop();
            int i = f[0], fa = f[1], d = f[2], state = f[3];
            if (state == 0) {
                ans[0] += d;
                stk.push(new int[] {i, fa, d, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push(new int[] {j, i, d + 1, 0});
                    }
                }
            } else {
                size[i] = 1;
                for (int j : g[i]) {
                    if (j != fa) {
                        size[i] += size[j];
                    }
                }
            }
        }
        Deque<int[]> walk = new ArrayDeque<>();
        walk.push(new int[] {0, -1, ans[0]});
        while (!walk.isEmpty()) {
            int[] f = walk.pop();
            int i = f[0], fa = f[1], t = f[2];
            ans[i] = t;
            for (int j : g[i]) {
                if (j != fa) {
                    walk.push(new int[] {j, i, t - size[j] + n - size[j]});
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> sumOfDistancesInTree(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<int> ans(n);
        vector<int> size(n);
        vector<array<int, 4>> stk{{0, -1, 0, 0}};
        while (!stk.empty()) {
            auto [i, fa, d, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                ans[0] += d;
                stk.push_back({i, fa, d, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push_back({j, i, d + 1, 0});
                    }
                }
            } else {
                size[i] = 1;
                for (int j : g[i]) {
                    if (j != fa) {
                        size[i] += size[j];
                    }
                }
            }
        }
        vector<array<int, 3>> walk{{0, -1, ans[0]}};
        while (!walk.empty()) {
            auto [i, fa, t] = walk.back();
            walk.pop_back();
            ans[i] = t;
            for (int j : g[i]) {
                if (j != fa) {
                    walk.push_back({j, i, t - size[j] + n - size[j]});
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func sumOfDistancesInTree(n int, edges [][]int) []int {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	ans := make([]int, n)
	size := make([]int, n)
	type frame struct{ i, fa, d, state int }
	stk := []frame{{0, -1, 0, 0}}
	for len(stk) > 0 {
		f := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		if f.state == 0 {
			ans[0] += f.d
			stk = append(stk, frame{f.i, f.fa, f.d, 1})
			for _, j := range g[f.i] {
				if j != f.fa {
					stk = append(stk, frame{j, f.i, f.d + 1, 0})
				}
			}
		} else {
			size[f.i] = 1
			for _, j := range g[f.i] {
				if j != f.fa {
					size[f.i] += size[j]
				}
			}
		}
	}
	type step struct{ i, fa, t int }
	walk := []step{{0, -1, ans[0]}}
	for len(walk) > 0 {
		f := walk[len(walk)-1]
		walk = walk[:len(walk)-1]
		ans[f.i] = f.t
		for _, j := range g[f.i] {
			if j != f.fa {
				walk = append(walk, step{j, f.i, f.t - size[j] + n - size[j]})
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function sumOfDistancesInTree(n: number, edges: number[][]): number[] {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const ans: number[] = new Array(n).fill(0);
    const size: number[] = new Array(n).fill(0);
    const stk: number[][] = [[0, -1, 0, 0]];
    while (stk.length) {
        const [i, fa, d, state] = stk.pop()!;
        if (state === 0) {
            ans[0] += d;
            stk.push([i, fa, d, 1]);
            for (const j of g[i]) {
                if (j !== fa) {
                    stk.push([j, i, d + 1, 0]);
                }
            }
        } else {
            size[i] = 1;
            for (const j of g[i]) {
                if (j !== fa) {
                    size[i] += size[j];
                }
            }
        }
    }
    const walk: number[][] = [[0, -1, ans[0]]];
    while (walk.length) {
        const [i, fa, t] = walk.pop()!;
        ans[i] = t;
        for (const j of g[i]) {
            if (j !== fa) {
                walk.push([j, i, t - size[j] + n - size[j]]);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
