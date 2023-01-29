# [2316. 统计无向图中无法互相到达点对数](https://leetcode.cn/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph)

[English Version](/solution/2300-2399/2316.Count%20Unreachable%20Pairs%20of%20Nodes%20in%20an%20Undirected%20Graph/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

对于无向图中的任意两个节点，如果它们之间存在一条路径，那么它们之间就是互相可达的。

因此，我们可以通过深度优先搜索的方式，找出每一个连通分量中的节点个数 $t$，然后将当前连通分量中的节点个数 $t$ 与之前所有连通分量中的节点个数 $s$ 相乘，即可得到当前连通分量中的不可达点对数目 $s \times t$，然后将 $t$ 加到 $s$ 中。继续搜索下一个连通分量，直到搜索完所有连通分量，即可得到答案。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是节点数和边数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countPairs(self, n: int, edges: List[List[int]]) -> int:
        def dfs(i):
            vis.add(i)
            cnt = 1
            for j in g[i]:
                if j not in vis:
                    cnt += dfs(j)
            return cnt

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = set()
        ans = s = 0
        for i in range(n):
            if i not in vis:
                t = dfs(i)
                ans += s * t
                s += t
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private boolean[] vis;
    private List<Integer>[] g;

    public long countPairs(int n, int[][] edges) {
        vis = new boolean[n];
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        long ans = 0, s = 0;
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                long t = dfs(i);
                ans += s * t;
                s += t;
            }
        }
        return ans;
    }

    private int dfs(int i) {
        vis[i] = true;
        int cnt = 1;
        for (int j : g[i]) {
            if (!vis[j]) {
                cnt += dfs(j);
            }
        }
        return cnt;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long countPairs(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        vector<bool> vis(n);
        function<int(int)> dfs = [&](int i) -> int {
            vis[i] = true;
            int cnt = 1;
            for (int j : g[i]) {
                if (!vis[j]) {
                    cnt += dfs(j);
                }
            }
            return cnt;
        };
        long long ans = 0, s = 0;
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                long long t = dfs(i);
                ans += s * t;
                s += t;
            }
        }
        return ans;
    }
};
```

### **Go**

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
		vis[i] = true
		cnt := 1
		for _, j := range g[i] {
			if !vis[j] {
				cnt += dfs(j)
			}
		}
		return cnt
	}
	var s int64
	for i := 0; i < n; i++ {
		if !vis[i] {
			t := int64(dfs(i))
			ans += s * t
			s += t
		}
	}
	return
}
```

### **TypeScript**

```ts
function countPairs(n: number, edges: number[][]): number {
    const g = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const vis = new Array(n).fill(false);
    const dfs = (i: number) => {
        vis[i] = true;
        let cnt = 1;
        for (const j of g[i]) {
            if (!vis[j]) {
                cnt += dfs(j);
            }
        }
        return cnt;
    };
    let ans = 0;
    let s = 0;
    for (let i = 0; i < n; ++i) {
        if (!vis[i]) {
            const t = dfs(i);
            ans += s * t;
            s += t;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
