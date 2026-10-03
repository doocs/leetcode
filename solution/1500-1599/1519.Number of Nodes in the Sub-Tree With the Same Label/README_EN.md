---
comments: true
difficulty: Medium
rating: 1808
source: Weekly Contest 198 Q2
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Hash Table
    - Counting
    - Tree DP
---

<!-- problem:start -->

# [1519. Number of Nodes in the Sub-Tree With the Same Label](https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label)

[中文文档](/solution/1500-1599/1519.Number%20of%20Nodes%20in%20the%20Sub-Tree%20With%20the%20Same%20Label/README.md)

## Description

<!-- description:start -->

<p>You are given a tree (i.e. a connected, undirected graph that has no cycles) consisting of <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code> and exactly <code>n - 1</code> <code>edges</code>. The <strong>root</strong> of the tree is the node <code>0</code>, and each node of the tree has <strong>a label</strong> which is a lower-case character given in the string <code>labels</code> (i.e. The node with the number <code>i</code> has the label <code>labels[i]</code>).</p>

<p>The <code>edges</code> array is given on the form <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>, which means there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree.</p>

<p>Return <em>an array of size <code>n</code></em> where <code>ans[i]</code> is the number of nodes in the subtree of the <code>i<sup>th</sup></code> node which have the same label as node <code>i</code>.</p>

<p>A subtree of a tree <code>T</code> is the tree consisting of a node in <code>T</code> and all of its descendant nodes.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1519.Number%20of%20Nodes%20in%20the%20Sub-Tree%20With%20the%20Same%20Label/images/q3e1.jpg" style="width: 400px; height: 291px;" />
<pre>
<strong>Input:</strong> n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = &quot;abaedcd&quot;
<strong>Output:</strong> [2,1,1,1,1,1,1]
<strong>Explanation:</strong> Node 0 has label &#39;a&#39; and its sub-tree has node 2 with label &#39;a&#39; as well, thus the answer is 2. Notice that any node is part of its sub-tree.
Node 1 has a label &#39;b&#39;. The sub-tree of node 1 contains nodes 1,4 and 5, as nodes 4 and 5 have different labels than node 1, the answer is just 1 (the node itself).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1519.Number%20of%20Nodes%20in%20the%20Sub-Tree%20With%20the%20Same%20Label/images/q3e2.jpg" style="width: 300px; height: 253px;" />
<pre>
<strong>Input:</strong> n = 4, edges = [[0,1],[1,2],[0,3]], labels = &quot;bbbb&quot;
<strong>Output:</strong> [4,2,1,1]
<strong>Explanation:</strong> The sub-tree of node 2 contains only node 2, so the answer is 1.
The sub-tree of node 3 contains only node 3, so the answer is 1.
The sub-tree of node 1 contains nodes 1 and 2, both have label &#39;b&#39;, thus the answer is 2.
The sub-tree of node 0 contains nodes 0, 1, 2 and 3, all with label &#39;b&#39;, thus the answer is 4.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1519.Number%20of%20Nodes%20in%20the%20Sub-Tree%20With%20the%20Same%20Label/images/q3e3.jpg" style="width: 300px; height: 253px;" />
<pre>
<strong>Input:</strong> n = 5, edges = [[0,1],[0,2],[1,3],[0,4]], labels = &quot;aabab&quot;
<strong>Output:</strong> [3,2,1,1,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>labels.length == n</code></li>
	<li><code>labels</code> is consisting of only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> For every node we need how many nodes in its subtree share its label. $n\le 10^5$, so a fresh walk from each node is quadratic. There are only $26$ labels, which a single DFS can count globally.
>
> Before entering $i$, remember the current frequency of $labels[i]$; after leaving, read it again. The difference is the count inside that subtree. Storing the entry snapshot in $ans[i]$ (subtract first, add later) avoids an extra array; extra memory is just the alphabet.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSubTrees(self, n: int, edges: List[List[int]], labels: str) -> List[int]:
        def dfs(i, fa):
            ans[i] -= cnt[labels[i]]
            cnt[labels[i]] += 1
            for j in g[i]:
                if j != fa:
                    dfs(j, i)
            ans[i] += cnt[labels[i]]

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        cnt = Counter()
        ans = [0] * n
        dfs(0, -1)
        return ans
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private String labels;
    private int[] ans;
    private int[] cnt;

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        this.labels = labels;
        ans = new int[n];
        cnt = new int[26];
        dfs(0, -1);
        return ans;
    }

    private void dfs(int i, int fa) {
        int k = labels.charAt(i) - 'a';
        ans[i] -= cnt[k];
        cnt[k]++;
        for (int j : g[i]) {
            if (j != fa) {
                dfs(j, i);
            }
        }
        ans[i] += cnt[k];
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> countSubTrees(int n, vector<vector<int>>& edges, string labels) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<int> ans(n);
        int cnt[26]{};
        function<void(int, int)> dfs = [&](int i, int fa) {
            int k = labels[i] - 'a';
            ans[i] -= cnt[k];
            cnt[k]++;
            for (int& j : g[i]) {
                if (j != fa) {
                    dfs(j, i);
                }
            }
            ans[i] += cnt[k];
        };
        dfs(0, -1);
        return ans;
    }
};
```

#### Go

```go
func countSubTrees(n int, edges [][]int, labels string) []int {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	ans := make([]int, n)
	cnt := [26]int{}
	var dfs func(int, int)
	dfs = func(i, fa int) {
		k := labels[i] - 'a'
		ans[i] -= cnt[k]
		cnt[k]++
		for _, j := range g[i] {
			if j != fa {
				dfs(j, i)
			}
		}
		ans[i] += cnt[k]
	}
	dfs(0, -1)
	return ans
}
```

#### TypeScript

```ts
function countSubTrees(n: number, edges: number[][], labels: string): number[] {
    const dfs = (i: number, fa: number) => {
        const k = labels.charCodeAt(i) - 97;
        ans[i] -= cnt[k];
        cnt[k]++;
        for (const j of g[i]) {
            if (j !== fa) {
                dfs(j, i);
            }
        }
        ans[i] += cnt[k];
    };
    const ans = new Array(n).fill(0),
        cnt = new Array(26).fill(0);
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    dfs(0, -1);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Explicit Stack

<!-- thinking:start -->

> **Thinking**
>
> For every node we need how many nodes in its subtree share its label. $n\le 10^5$, so a fresh walk from each node is quadratic. On a chain the first recursive call always follows the only child, so the depth is $n$ and exceeds the default recursion limit.
>
> There are only $26$ labels. The increase in a label's count between entering a node and leaving it is exactly the count inside that subtree.
>
> An explicit stack walks the tree in postorder. On entry, subtract the current count from $ans[i]$, increment the count, then push the exit marker and the children. On exit, add the new count back to $ans[i]$. The difference is the number of matching labels in the subtree.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSubTrees(self, n: int, edges: List[List[int]], labels: str) -> List[int]:
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        ans = [0] * n
        cnt = Counter()
        stk = [(0, -1, 0)]
        while stk:
            i, fa, state = stk.pop()
            if state == 0:
                ans[i] -= cnt[labels[i]]
                cnt[labels[i]] += 1
                stk.append((i, fa, 1))
                for j in g[i]:
                    if j != fa:
                        stk.append((j, i, 0))
            else:
                ans[i] += cnt[labels[i]]
        return ans
```

#### Java

```java
class Solution {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int[] ans = new int[n];
        int[] cnt = new int[26];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1], state = cur[2];
            int k = labels.charAt(i) - 'a';
            if (state == 0) {
                ans[i] -= cnt[k];
                cnt[k]++;
                stk.push(new int[] {i, fa, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push(new int[] {j, i, 0});
                    }
                }
            } else {
                ans[i] += cnt[k];
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
    vector<int> countSubTrees(int n, vector<vector<int>>& edges, string labels) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<int> ans(n);
        int cnt[26]{};
        vector<array<int, 3>> stk{{0, -1, 0}};
        while (!stk.empty()) {
            auto cur = stk.back();
            stk.pop_back();
            int i = cur[0], fa = cur[1], state = cur[2];
            int k = labels[i] - 'a';
            if (state == 0) {
                ans[i] -= cnt[k];
                cnt[k]++;
                stk.push_back({i, fa, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push_back({j, i, 0});
                    }
                }
            } else {
                ans[i] += cnt[k];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countSubTrees(n int, edges [][]int, labels string) []int {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	ans := make([]int, n)
	cnt := [26]int{}
	stk := [][3]int{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, fa, state := cur[0], cur[1], cur[2]
		k := labels[i] - 'a'
		if state == 0 {
			ans[i] -= cnt[k]
			cnt[k]++
			stk = append(stk, [3]int{i, fa, 1})
			for _, j := range g[i] {
				if j != fa {
					stk = append(stk, [3]int{j, i, 0})
				}
			}
		} else {
			ans[i] += cnt[k]
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countSubTrees(n: number, edges: number[][], labels: string): number[] {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const ans: number[] = Array(n).fill(0);
    const cnt: number[] = Array(26).fill(0);
    const stk: number[][] = [[0, -1, 0]];
    while (stk.length) {
        const [i, fa, state] = stk.pop()!;
        const k = labels.charCodeAt(i) - 97;
        if (state === 0) {
            ans[i] -= cnt[k];
            cnt[k]++;
            stk.push([i, fa, 1]);
            for (const j of g[i]) {
                if (j !== fa) {
                    stk.push([j, i, 0]);
                }
            }
        } else {
            ans[i] += cnt[k];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
