# [3067. 在带权树网络中统计可连接服务器对数目](https://leetcode.cn/problems/count-pairs-of-connectable-servers-in-a-weighted-tree-network)

[English Version](/solution/3000-3099/3067.Count%20Pairs%20of%20Connectable%20Servers%20in%20a%20Weighted%20Tree%20Network/README_EN.md)

<!-- tags:树,深度优先搜索,数组 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵无根带权树，树中总共有 <code>n</code>&nbsp;个节点，分别表示 <code>n</code>&nbsp;个服务器，服务器从 <code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;编号。同时给你一个数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>, weight<sub>i</sub>]</code>&nbsp;表示节点&nbsp;<code>a<sub>i</sub></code> 和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条双向边，边的权值为&nbsp;<code>weight<sub>i</sub></code>&nbsp;。再给你一个整数&nbsp;<code>signalSpeed</code>&nbsp;。</p>

<p>如果两个服务器 <code>a</code>&nbsp;，<code>b</code>&nbsp;和 <code>c</code>&nbsp;满足以下条件，那么我们称服务器 <code>a</code>&nbsp;和 <code>b</code>&nbsp;是通过服务器 <code>c</code>&nbsp;<strong>可连接的</strong>&nbsp;：</p>

<ul>
	<li><code>a &lt; b</code>&nbsp;，<code>a != c</code> 且&nbsp;<code>b != c</code>&nbsp;。</li>
	<li>从&nbsp;<code>c</code>&nbsp;到&nbsp;<code>a</code>&nbsp;的距离是可以被&nbsp;<code>signalSpeed</code>&nbsp;整除的。</li>
	<li>从&nbsp;<code>c</code>&nbsp;到&nbsp;<code>b</code>&nbsp;的距离是可以被&nbsp;<code>signalSpeed</code>&nbsp;整除的。</li>
	<li>从&nbsp;<code>c</code>&nbsp;到&nbsp;<code>b</code>&nbsp;的路径与从&nbsp;<code>c</code>&nbsp;到&nbsp;<code>a</code>&nbsp;的路径没有任何公共边。</li>
</ul>

<p>请你返回一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>count</code>&nbsp;，其中&nbsp;<code>count[i]</code> 表示通过服务器&nbsp;<code>i</code>&nbsp;<strong>可连接</strong>&nbsp;的服务器对的&nbsp;<strong>数目</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3067.Count%20Pairs%20of%20Connectable%20Servers%20in%20a%20Weighted%20Tree%20Network/images/example22.png" style="width: 438px; height: 243px; padding: 10px; background: #fff; border-radius: .5rem;" /></p>

<pre>
<b>输入：</b>edges = [[0,1,1],[1,2,5],[2,3,13],[3,4,9],[4,5,2]], signalSpeed = 1
<b>输出：</b>[0,4,6,6,4,0]
<b>解释：</b>由于 signalSpeed 等于 1 ，count[c] 等于所有从 c 开始且没有公共边的路径对数目。
在输入图中，count[c] 等于服务器 c 左边服务器数目乘以右边服务器数目。
</pre>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3067.Count%20Pairs%20of%20Connectable%20Servers%20in%20a%20Weighted%20Tree%20Network/images/example11.png" style="width: 495px; height: 484px; padding: 10px; background: #fff; border-radius: .5rem;" /></p>

<pre>
<b>输入：</b>edges = [[0,6,3],[6,5,3],[0,3,1],[3,2,7],[3,1,6],[3,4,2]], signalSpeed = 3
<b>输出：</b>[2,0,0,0,0,0,2]
<b>解释：</b>通过服务器 0 ，有 2 个可连接服务器对(4, 5) 和 (4, 6) 。
通过服务器 6 ，有 2 个可连接服务器对 (4, 5) 和 (0, 5) 。
所有服务器对都必须通过服务器 0 或 6 才可连接，所以其他服务器对应的可连接服务器对数目都为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>, weight<sub>i</sub>]</code><!-- notionvc: a2623897-1bb1-4c07-84b6-917ffdcd83ec --></li>
	<li><code>1 &lt;= weight<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= signalSpeed &lt;= 10<sup>6</sup></code></li>
	<li>输入保证&nbsp;<code>edges</code>&nbsp;构成一棵合法的树。</li>
</ul>

## 解法

### 方法一：枚举 + DFS

我们先根据题目给定的边构建出一个邻接表 $g$，其中 $g[a]$ 表示节点 $a$ 的所有邻居节点以及对应的边权。

然后，我们可以枚举每一个节点 $a$ 作为连接的中间节点，通过深度优先搜索计算出从 $a$ 的邻居节点 $b$ 出发的，且到节点 $a$ 的距离可以被 $signalSpeed$ 整除的节点数 $t$。那么，节点 $a$ 的可连接节点对数目增加了 $s \times t$，其中 $s$ 表示节点 $a$ 的邻居节点 $b$ 出发的，且到节点 $a$ 的距离不可以被 $signalSpeed$ 整除的累计节点数。然后我们更新 $s$ 为 $s + t$。

枚举完所有节点 $a$ 之后，我们就可以得到所有节点的可连接节点对数目。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 表示节点数。

<!-- tabs:start -->

```python
class Solution:
    def countPairsOfConnectableServers(
        self, edges: List[List[int]], signalSpeed: int
    ) -> List[int]:
        def dfs(a: int, fa: int, ws: int) -> int:
            cnt = 0 if ws % signalSpeed else 1
            for b, w in g[a]:
                if b != fa:
                    cnt += dfs(b, a, ws + w)
            return cnt

        n = len(edges) + 1
        g = [[] for _ in range(n)]
        for a, b, w in edges:
            g[a].append((b, w))
            g[b].append((a, w))
        ans = [0] * n
        for a in range(n):
            s = 0
            for b, w in g[a]:
                t = dfs(b, a, w)
                ans[a] += s * t
                s += t
        return ans
```

```java
class Solution {
    private int signalSpeed;
    private List<int[]>[] g;

    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        g = new List[n];
        this.signalSpeed = signalSpeed;
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1], w = e[2];
            g[a].add(new int[] {b, w});
            g[b].add(new int[] {a, w});
        }
        int[] ans = new int[n];
        for (int a = 0; a < n; ++a) {
            int s = 0;
            for (var e : g[a]) {
                int b = e[0], w = e[1];
                int t = dfs(b, a, w);
                ans[a] += s * t;
                s += t;
            }
        }
        return ans;
    }

    private int dfs(int a, int fa, int ws) {
        int cnt = ws % signalSpeed == 0 ? 1 : 0;
        for (var e : g[a]) {
            int b = e[0], w = e[1];
            if (b != fa) {
                cnt += dfs(b, a, ws + w);
            }
        }
        return cnt;
    }
}
```

```cpp
class Solution {
public:
    vector<int> countPairsOfConnectableServers(vector<vector<int>>& edges, int signalSpeed) {
        int n = edges.size() + 1;
        vector<pair<int, int>> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1], w = e[2];
            g[a].emplace_back(b, w);
            g[b].emplace_back(a, w);
        }
        function<int(int, int, int)> dfs = [&](int a, int fa, int ws) {
            int cnt = ws % signalSpeed == 0;
            for (auto& [b, w] : g[a]) {
                if (b != fa) {
                    cnt += dfs(b, a, ws + w);
                }
            }
            return cnt;
        };
        vector<int> ans(n);
        for (int a = 0; a < n; ++a) {
            int s = 0;
            for (auto& [b, w] : g[a]) {
                int t = dfs(b, a, w);
                ans[a] += s * t;
                s += t;
            }
        }
        return ans;
    }
};
```

```go
func countPairsOfConnectableServers(edges [][]int, signalSpeed int) []int {
	n := len(edges) + 1
	type pair struct{ x, w int }
	g := make([][]pair, n)
	for _, e := range edges {
		a, b, w := e[0], e[1], e[2]
		g[a] = append(g[a], pair{b, w})
		g[b] = append(g[b], pair{a, w})
	}
	var dfs func(a, fa, ws int) int
	dfs = func(a, fa, ws int) int {
		cnt := 0
		if ws%signalSpeed == 0 {
			cnt++
		}
		for _, e := range g[a] {
			b, w := e.x, e.w
			if b != fa {
				cnt += dfs(b, a, ws+w)
			}
		}
		return cnt
	}
	ans := make([]int, n)
	for a := 0; a < n; a++ {
		s := 0
		for _, e := range g[a] {
			b, w := e.x, e.w
			t := dfs(b, a, w)
			ans[a] += s * t
			s += t
		}
	}
	return ans
}
```

```ts
function countPairsOfConnectableServers(edges: number[][], signalSpeed: number): number[] {
    const n = edges.length + 1;
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [a, b, w] of edges) {
        g[a].push([b, w]);
        g[b].push([a, w]);
    }
    const dfs = (a: number, fa: number, ws: number): number => {
        let cnt = ws % signalSpeed === 0 ? 1 : 0;
        for (const [b, w] of g[a]) {
            if (b != fa) {
                cnt += dfs(b, a, ws + w);
            }
        }
        return cnt;
    };
    const ans: number[] = Array(n).fill(0);
    for (let a = 0; a < n; ++a) {
        let s = 0;
        for (const [b, w] of g[a]) {
            const t = dfs(b, a, w);
            ans[a] += s * t;
            s += t;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
