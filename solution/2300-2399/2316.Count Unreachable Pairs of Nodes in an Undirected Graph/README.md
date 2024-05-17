---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2316.Count%20Unreachable%20Pairs%20of%20Nodes%20in%20an%20Undirected%20Graph/README.md
rating: 1604
source: 第 81 场双周赛 Q2
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 图
---

<!-- problem:start -->

# [2316. 统计无向图中无法互相到达点对数](https://leetcode.cn/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph)

[English Version](/solution/2300-2399/2316.Count%20Unreachable%20Pairs%20of%20Nodes%20in%20an%20Undirected%20Graph/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;，表示一张<strong>&nbsp;无向图</strong>&nbsp;中有 <code>n</code>&nbsp;个节点，编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;。同时给你一个二维整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示节点&nbsp;<code>a<sub>i</sub></code> 和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条&nbsp;<strong>无向</strong>&nbsp;边。</p>

<p>请你返回 <strong>无法互相到达</strong>&nbsp;的不同 <strong>点对数目</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2316.Count%20Unreachable%20Pairs%20of%20Nodes%20in%20an%20Undirected%20Graph/images/tc-3.png" style="width: 267px; height: 169px;"></p>

<pre><b>输入：</b>n = 3, edges = [[0,1],[0,2],[1,2]]
<b>输出：</b>0
<b>解释：</b>所有点都能互相到达，意味着没有点对无法互相到达，所以我们返回 0 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2316.Count%20Unreachable%20Pairs%20of%20Nodes%20in%20an%20Undirected%20Graph/images/tc-2.png" style="width: 295px; height: 269px;"></p>

<pre><b>输入：</b>n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
<b>输出：</b>14
<b>解释：</b>总共有 14 个点对互相无法到达：
[[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]]
所以我们返回 14 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>不会有重复边。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

对于无向图中的任意两个节点，如果它们之间存在一条路径，那么它们之间就是互相可达的。

因此，我们可以通过深度优先搜索的方式，找出每一个连通分量中的节点个数 $t$，然后将当前连通分量中的节点个数 $t$ 与之前所有连通分量中的节点个数 $s$ 相乘，即可得到当前连通分量中的不可达点对数目 $s \times t$，然后将 $t$ 加到 $s$ 中。继续搜索下一个连通分量，直到搜索完所有连通分量，即可得到答案。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是节点数和边数。

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

<!-- problem:end -->
