---
comments: true
difficulty: Medium
rating: 1707
source: Weekly Contest 528 Q2
---

<!-- problem:start -->

# [4112. Maximize Score After Zeroing Subtrees](https://leetcode.com/problems/maximize-score-after-zeroing-subtrees)

[中文文档](/solution/4100-4199/4112.Maximize%20Score%20After%20Zeroing%20Subtrees/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>parent</code> of length <code>n</code> representing a rooted tree with nodes labeled from 0 to <code>n - 1</code>, where <code>parent[i]</code> denotes the parent of node <code>i</code>. The tree is rooted at node 0, so <code>parent[0] = -1</code>.</p>

<p>You are also given two integer arrays <code>op</code> and <code>score</code>, both of length <code>n</code>, where <code>op[i]</code> determines the operation allowed at node <code>i</code>, and <code>score[i]</code> represents its score.</p>

<p>You need to process each <strong>non-<span data-keyword="leaf-rooted">leaf</span></strong> node <code>i</code> according to the following rules:</p>

<ul>
	<li>If <code>op[i] = 0</code>, you can choose <strong>at most</strong> one child of node <code>i</code> and set the scores of all nodes in that child&#39;s <span data-keyword="subtree">subtree</span> to 0.</li>
	<li>If <code>op[i] = 1</code>, you must choose <strong>exactly</strong> one child of node <code>i</code> and set the scores of all nodes in that child&#39;s subtree to 0.</li>
</ul>

<p>Each non-leaf node must be processed exactly once. You may process the nodes in any order.</p>

<p>Return the <strong>maximum possible sum</strong> of the scores after performing the operations.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4112.Maximize%20Score%20After%20Zeroing%20Subtrees/images/ex1_ok.gif" style="width: 533px; height: 300px;" />​​​​​​​</p>

<p><strong>Input:</strong> <span class="example-io">parent = [-1,0,0,0,1,2,3,3], op = [1,1,0,0,1,1,0,1], score = [5,6,1,2,3,2,3,-3]</span></p>

<p><strong>Output:</strong> <span class="example-io">16</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal way is to process the nodes in the following order:</p>

<ul>
	<li>Nodes 4, 5, 6, and 7<strong>:</strong> They are leaf nodes, so no operations are performed on them.</li>
	<li>Node 1<strong>:</strong> Since <code>op[1] = 1</code>, we must select one of its children. Choose node 4, setting the scores of all nodes in its subtree (node 4) to 0.</li>
	<li>Node 2: Since <code>op[2] = 0</code>, we may choose a child or do nothing. For this node, we do nothing.</li>
	<li>Node 3: Since <code>op[3] = 0</code>, we may choose a child or do nothing. Choose node 7, setting the scores of all nodes in its subtree (node 7) to 0.</li>
	<li>Node 0: Since <code>op[0] = 1</code>, we must select one of its children. Choose node 2, setting the scores of all nodes in its subtree (nodes 2 and 5) to 0.</li>
</ul>

<p>After performing these operations, the scores are <code>[5, 6, 0, 2, 0, 0, 3, 0]</code>, giving a total sum of 16.</p>

<p>No valid sequence of operations can produce a larger sum. Therefore, the answer is 16.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4112.Maximize%20Score%20After%20Zeroing%20Subtrees/images/ex2_fix.png" style="width: 220px; height: 180px;" />​​​​​​​</p>

<p><strong>Input:</strong> <span class="example-io">parent = [-1,0,0], op = [0,0,0], score = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>We only need to process node 0, since node 1 and node 2 are leaves.</li>
	<li>Since <code>op[0] = 0</code>, we can choose to do nothing.</li>
	<li>Therefore, all scores are preserved, giving the maximum possible sum of 6.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4112.Maximize%20Score%20After%20Zeroing%20Subtrees/images/ex3.png" style="width: 139px; height: 60px;" />​​​​​​​</p>

<p><strong>Input:</strong> <span class="example-io">parent = [-1], op = [1], score = [-10]</span></p>

<p><strong>Output:</strong> <span class="example-io">-10</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The only node in the tree is a leaf, so we can&#39;t process any node.</li>
	<li>Therefore, the maximum possible score sum is -10.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n == parent.length == op.length == score.length</code></li>
	<li><code>parent[0] == -1</code></li>
	<li><code>0 &lt;= parent[i] &lt;= n - 1</code> for all <code>i</code> in <code>[1, n - 1]</code></li>
	<li><code>0 &lt;= op[i] &lt;= 1</code></li>
	<li><code>-10<sup>4</sup> &lt;= score[i] &lt;= 10<sup>4</sup></code></li>
	<li>The input is generated such that the array <code>parent</code> represents a valid tree rooted at node 0.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Depth-First Search

<!-- thinking:start -->

> **Thinking**
>
> $n$ reaches $10^5$. Every non-leaf chooses which child subtree to zero, and enumerating those combinations is exponential.
>
> After an ancestor zeros a whole subtree, operations inside it only touch nodes that are already $0$. If the subtree is kept, its best remaining score depends only on choices made inside it.
>
> Define $\textit{dfs}(i)$ bottom-up as the maximum score of the subtree of node $i$ when no ancestor zeros that subtree. A leaf has no operation and keeps $\textit{score}[i]$. A non-leaf first takes the $\textit{dfs}$ value of every child, then uses $op[i]$ to drop the smallest value, or to drop it only when that value is negative.
>
> Each node is visited once. After the recursive calls return from the leaves, a non-leaf adds every child score and subtracts the value dropped according to $op[i]$.

<!-- thinking:end -->

We build the child lists from $\textit{parent}$. $\textit{dfs}(i)$ is the maximum score kept in the subtree of node $i$ when no ancestor zeros that whole subtree.

If $i$ is a leaf, return $\textit{score}[i]$.

Otherwise set $\textit{tot}=\textit{score}[i]$. For each child $j$, compute $t=\textit{dfs}(j)$, add $t$ to $\textit{tot}$, and let $mn$ be the smallest such $t$. Then $\textit{tot}$ is the score when every child subtree is kept.

If $op[i]=1$, exactly one child subtree must be zeroed. Zero the subtree whose value is $mn$, and return $\textit{tot}-mn$. If $op[i]=0$, zeroing a child subtree is optional. The total becomes larger than keeping every child only when $mn<0$, so return $\textit{tot}-\min(0,mn)$.

The answer is $\textit{dfs}(0)$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, parent: list[int], op: list[int], score: list[int]) -> int:
        def dfs(i: int) -> int:
            if not g[i]:
                return score[i]
            tot = score[i]
            mn = inf
            for j in g[i]:
                t = dfs(j)
                mn = min(mn, t)
                tot += t
            if op[i] == 0:
                return tot - min(0, mn)
            return tot - mn

        n = len(parent)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parent[i]].append(i)
        return dfs(0)
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private int[] op;
    private int[] score;

    public int maxScore(int[] parent, int[] op, int[] score) {
        this.op = op;
        this.score = score;
        int n = parent.length;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            g[parent[i]].add(i);
        }
        return dfs(0);
    }

    private int dfs(int i) {
        if (g[i].isEmpty()) {
            return score[i];
        }
        int tot = score[i];
        int mn = Integer.MAX_VALUE;
        for (int j : g[i]) {
            int t = dfs(j);
            mn = Math.min(mn, t);
            tot += t;
        }
        if (op[i] == 0) {
            return tot - Math.min(0, mn);
        }
        return tot - mn;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxScore(vector<int> parent, vector<int> op, vector<int> score) {
        int n = parent.size();
        vector<vector<int>> g(n);
        for (int i = 1; i < n; i++) {
            g[parent[i]].push_back(i);
        }
        auto dfs = [&](this auto&& dfs, int i) -> int {
            if (g[i].empty()) {
                return score[i];
            }
            int tot = score[i];
            int mn = INT_MAX;
            for (int j : g[i]) {
                int t = dfs(j);
                mn = min(mn, t);
                tot += t;
            }
            if (op[i] == 0) {
                return tot - min(0, mn);
            }
            return tot - mn;
        };
        return dfs(0);
    }
};
```

#### Go

```go
func maxScore(parent []int, op []int, score []int) int {
	n := len(parent)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}
	var dfs func(i int) int
	dfs = func(i int) int {
		if len(g[i]) == 0 {
			return score[i]
		}
		tot := score[i]
		mn := int(^uint(0) >> 1)
		for _, j := range g[i] {
			t := dfs(j)
			if t < mn {
				mn = t
			}
			tot += t
		}
		if op[i] == 0 {
			return tot - min(0, mn)
		}
		return tot - mn
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function maxScore(parent: number[], op: number[], score: number[]): number {
    const n = parent.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 1; i < n; i++) {
        g[parent[i]].push(i);
    }
    const dfs = (i: number): number => {
        if (g[i].length === 0) {
            return score[i];
        }
        let tot = score[i];
        let mn = Infinity;
        for (const j of g[i]) {
            const t = dfs(j);
            mn = Math.min(mn, t);
            tot += t;
        }
        if (op[i] === 0) {
            return tot - Math.min(0, mn);
        }
        return tot - mn;
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
