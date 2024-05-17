---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2925.Maximum%20Score%20After%20Applying%20Operations%20on%20a%20Tree/README.md
rating: 1939
source: 第 370 场周赛 Q3
tags:
    - 树
    - 深度优先搜索
    - 动态规划
---

<!-- problem:start -->

# [2925. 在树上执行操作以后得到的最大分数](https://leetcode.cn/problems/maximum-score-after-applying-operations-on-a-tree)

[English Version](/solution/2900-2999/2925.Maximum%20Score%20After%20Applying%20Operations%20on%20a%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有一棵 <code>n</code>&nbsp;个节点的无向树，节点编号为 <code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;，根节点编号为 <code>0</code>&nbsp;。给你一个长度为 <code>n - 1</code>&nbsp;的二维整数数组&nbsp;<code>edges</code>&nbsp;表示这棵树，其中&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示树中节点&nbsp;<code>a<sub>i</sub></code>&nbsp;和&nbsp;<code>b<sub>i</sub></code>&nbsp;有一条边。</p>

<p>同时给你一个长度为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>values</code>&nbsp;，其中&nbsp;<code>values[i]</code>&nbsp;表示第 <code>i</code>&nbsp;个节点的值。</p>

<p>一开始你的分数为 <code>0</code>&nbsp;，每次操作中，你将执行：</p>

<ul>
	<li>选择节点&nbsp;<code>i</code>&nbsp;。</li>
	<li>将&nbsp;<code>values[i]</code>&nbsp;加入你的分数。</li>
	<li>将&nbsp;<code>values[i]</code>&nbsp;变为&nbsp;<code>0</code>&nbsp;。</li>
</ul>

<p>如果从根节点出发，到任意叶子节点经过的路径上的节点值之和都不等于 0 ，那么我们称这棵树是 <strong>健康的</strong>&nbsp;。</p>

<p>你可以对这棵树执行任意次操作，但要求执行完所有操作以后树是&nbsp;<strong>健康的</strong>&nbsp;，请你返回你可以获得的 <strong>最大分数</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2925.Maximum%20Score%20After%20Applying%20Operations%20on%20a%20Tree/images/graph-13-1.png" style="width: 515px; height: 443px;" /></p>

<pre>
<b>输入：</b>edges = [[0,1],[0,2],[0,3],[2,4],[4,5]], values = [5,2,5,2,1,1]
<b>输出：</b>11
<b>解释：</b>我们可以选择节点 1 ，2 ，3 ，4 和 5 。根节点的值是非 0 的。所以从根出发到任意叶子节点路径上节点值之和都不为 0 。所以树是健康的。你的得分之和为 values[1] + values[2] + values[3] + values[4] + values[5] = 11 。
11 是你对树执行任意次操作以后可以获得的最大得分之和。
</pre>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2925.Maximum%20Score%20After%20Applying%20Operations%20on%20a%20Tree/images/graph-14-2.png" style="width: 522px; height: 245px;" /></p>

<pre>
<b>输入：</b>edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], values = [20,10,9,7,4,3,5]
<b>输出：</b>40
<b>解释：</b>我们选择节点 0 ，2 ，3 和 4 。
- 从 0 到 4 的节点值之和为 10 。
- 从 0 到 3 的节点值之和为 10 。
- 从 0 到 5 的节点值之和为 3 。
- 从 0 到 6 的节点值之和为 5 。
所以树是健康的。你的得分之和为 values[0] + values[2] + values[3] + values[4] = 40 。
40 是你对树执行任意次操作以后可以获得的最大得分之和。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>values.length == n</code></li>
	<li><code>1 &lt;= values[i] &lt;= 10<sup>9</sup></code></li>
	<li>输入保证&nbsp;<code>edges</code>&nbsp;构成一棵合法的树。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：树形 DP

题目实际上是让我们从树的所有节点中选出一些节点，使得这些节点的值之和最大，并且每条从根节点到叶子节点的路径上都有一个点没有被选中。

我们可以使用树形 DP 的方法解决这个问题。

我们设计一个函数 $dfs(i, fa)$，其中 $i$ 表示当前以节点 $i$ 作为子树的根节点，且 $fa$ 表示 $i$ 的父节点，函数返回一个长度为 $2$ 的数组，其中 $[0]$ 表示该子树中所有节点的值之和，而 $[1]$ 表示该子树满足每条路径上都有一个点没有被选中的最大值。

其中 $[0]$ 的值可以直接通过 DFS 累加每个节点的值得到，而 $[1]$ 的值，则需要考虑两种情况，即节点 $i$ 是否被选中。如果被选中，那么节点 $i$ 的每个子树得必须满足每条路径上都有一个点没有被选中；如果没有被选中，那么节点 $i$ 的每个子树可以选取所有节点。我们取这两种情况中的最大值即可。

需要注意的是，叶子节点的 $[1]$ 的值为 $0$，因为叶子节点没有子树，所以不需要考虑每条路径上都有一个点没有被选中的情况。

答案为 $dfs(0, -1)[1]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点数。

<!-- tabs:start -->

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

<!-- problem:end -->
