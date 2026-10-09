---
comments: true
difficulty: 中等
rating: 1792
source: 第 12 场双周赛 Q3
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 拓扑排序
    - 树形 DP
---

<!-- problem:start -->

# [1245. 树的直径 🔒](https://leetcode.cn/problems/tree-diameter)

[English Version](/solution/1200-1299/1245.Tree%20Diameter/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你这棵「无向树」，请你测算并返回它的「直径」：这棵树上最长简单路径的 <strong>边数</strong>。</p>

<p>我们用一个由所有「边」组成的数组 <code>edges</code>&nbsp;来表示一棵无向树，其中&nbsp;<code>edges[i] = [u, v]</code>&nbsp;表示节点&nbsp;<code>u</code> 和 <code>v</code>&nbsp;之间的双向边。</p>

<p>树上的节点都已经用&nbsp;<code>{0, 1, ..., edges.length}</code>&nbsp;中的数做了标记，每个节点上的标记都是独一无二的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1245.Tree%20Diameter/images/1397_example_1.png" style="height: 233px; width: 226px;"></p>

<pre><strong>输入：</strong>edges = [[0,1],[0,2]]
<strong>输出：</strong>2
<strong>解释：</strong>
这棵树上最长的路径是 1 - 0 - 2，边数为 2。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1245.Tree%20Diameter/images/1397_example_2.png" style="height: 316px; width: 350px;"></p>

<pre><strong>输入：</strong>edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
<strong>输出：</strong>4
<strong>解释： </strong>
这棵树上最长的路径是 3 - 2 - 1 - 4 - 5，边数为 4。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= edges.length &lt;&nbsp;10^4</code></li>
	<li><code>edges[i][0] != edges[i][1]</code></li>
	<li><code>0 &lt;= edges[i][j] &lt;= edges.length</code></li>
	<li><code>edges</code>&nbsp;会形成一棵无向树</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：两次 DFS

<!-- thinking:start -->

> **思考**
>
> 树的直径是最长简单路径。 $n \le 10^4$，枚举所有端点对过慢。树上任意点出发的最远点必为某条直径的一端，再从该端走最远即得直径。
>
> 两次 DFS：第一次从 $0$ 找到最远点 $a$，第二次从 $a$ 得到距离即为直径长度。树无环，DFS 与 BFS 等价于求最远点。

<!-- thinking:end -->

我们首先任选一个节点，从该节点开始进行深度优先搜索，找到距离该节点最远的节点，记为节点 $a$。然后从节点 $a$ 开始进行深度优先搜索，找到距离节点 $a$ 最远的节点，记为节点 $b$。可以证明，节点 $a$ 和节点 $b$ 之间的路径即为树的直径。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点数。

相似题目：

- [1522. N 叉树的直径 🔒](https://github.com/doocs/leetcode/blob/main/solution/1500-1599/1522.Diameter%20of%20N-Ary%20Tree/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def treeDiameter(self, edges: List[List[int]]) -> int:
        def dfs(i: int, fa: int, t: int):
            for j in g[i]:
                if j != fa:
                    dfs(j, i, t + 1)
            nonlocal ans, a
            if ans < t:
                ans = t
                a = i

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        ans = a = 0
        dfs(0, -1, 0)
        dfs(a, -1, 0)
        return ans
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private int ans;
    private int a;

    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        dfs(0, -1, 0);
        dfs(a, -1, 0);
        return ans;
    }

    private void dfs(int i, int fa, int t) {
        for (int j : g[i]) {
            if (j != fa) {
                dfs(j, i, t + 1);
            }
        }
        if (ans < t) {
            ans = t;
            a = i;
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int treeDiameter(vector<vector<int>>& edges) {
        int n = edges.size() + 1;
        vector<int> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        int ans = 0, a = 0;
        auto dfs = [&](this auto&& dfs, int i, int fa, int t) -> void {
            for (int j : g[i]) {
                if (j != fa) {
                    dfs(j, i, t + 1);
                }
            }
            if (ans < t) {
                ans = t;
                a = i;
            }
        };
        dfs(0, -1, 0);
        dfs(a, -1, 0);
        return ans;
    }
};
```

#### Go

```go
func treeDiameter(edges [][]int) (ans int) {
	n := len(edges) + 1
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	a := 0
	var dfs func(i, fa, t int)
	dfs = func(i, fa, t int) {
		for _, j := range g[i] {
			if j != fa {
				dfs(j, i, t+1)
			}
		}
		if ans < t {
			ans = t
			a = i
		}
	}
	dfs(0, -1, 0)
	dfs(a, -1, 0)
	return
}
```

#### TypeScript

```ts
function treeDiameter(edges: number[][]): number {
    const n = edges.length + 1;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    let [ans, a] = [0, 0];
    const dfs = (i: number, fa: number, t: number): void => {
        for (const j of g[i]) {
            if (j !== fa) {
                dfs(j, i, t + 1);
            }
        }
        if (ans < t) {
            ans = t;
            a = i;
        }
    };
    dfs(0, -1, 0);
    dfs(a, -1, 0);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：两次显式栈

<!-- thinking:start -->

> **思考**
>
> 树的直径是最长简单路径的边数。$n \le 10^4$，枚举所有端点对再两两求距离无法接受。从任意一点走到最远的点，一定是某条直径的一个端点，再从该端点走到最远就得到直径长度。沿一条链递归时调用深度等于节点数，长度达到 $1000$ 就会超出 Python 的递归上限。因此每次搜索用栈保存 $(节点, 父节点, 距离)$：弹出时若距离更大就记下当前点，再把其余邻接点以距离加一压入。第一次从 $0$ 得到端点 $a$，第二次从 $a$ 得到的最远距离就是直径。

<!-- thinking:end -->

我们从节点 $0$ 出发，用显式栈找出离它最远的节点 $a$，再从 $a$ 做同样的搜索。第二次得到的最远距离就是树的直径。

栈里每个元素是 $(i, fa, t)$，表示走到节点 $i$、父节点为 $fa$、已经经过 $t$ 条边。弹出后如果 $t$ 更大，就更新最远点和答案，然后把除父节点以外的邻接点以距离 $t + 1$ 压入。树没有环，每个节点只会入栈一次。同距离的端点哪一个先被记下，不影响第二次搜索的长度。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点数。

相似题目：

- [1522. N 叉树的直径 🔒](https://github.com/doocs/leetcode/blob/main/solution/1500-1599/1522.Diameter%20of%20N-Ary%20Tree/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def treeDiameter(self, edges: List[List[int]]) -> int:
        n = len(edges) + 1
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)

        def farthest(start: int) -> (int, int):
            ans = 0
            node = start
            stk = [(start, -1, 0)]
            while stk:
                i, fa, t = stk.pop()
                if ans < t:
                    ans = t
                    node = i
                for j in g[i]:
                    if j != fa:
                        stk.append((j, i, t + 1))
            return node, ans

        node, _ = farthest(0)
        return farthest(node)[1]
```

#### Java

```java
class Solution {
    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int node = farthest(g, 0)[0];
        return farthest(g, node)[1];
    }

    private int[] farthest(List<Integer>[] g, int start) {
        int ans = 0, node = start;
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {start, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1], t = cur[2];
            if (ans < t) {
                ans = t;
                node = i;
            }
            for (int j : g[i]) {
                if (j != fa) {
                    stk.push(new int[] {j, i, t + 1});
                }
            }
        }
        return new int[] {node, ans};
    }
}
```

#### C++

```cpp
class Solution {
public:
    int treeDiameter(vector<vector<int>>& edges) {
        int n = edges.size() + 1;
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        auto farthest = [&](int start) -> pair<int, int> {
            int ans = 0, node = start;
            vector<array<int, 3>> stk{{start, -1, 0}};
            while (!stk.empty()) {
                auto [i, fa, t] = stk.back();
                stk.pop_back();
                if (ans < t) {
                    ans = t;
                    node = i;
                }
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push_back({j, i, t + 1});
                    }
                }
            }
            return {node, ans};
        };
        int node = farthest(0).first;
        return farthest(node).second;
    }
};
```

#### Go

```go
func treeDiameter(edges [][]int) int {
	n := len(edges) + 1
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	farthest := func(start int) (int, int) {
		ans, node := 0, start
		stk := [][3]int{{start, -1, 0}}
		for len(stk) > 0 {
			cur := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			i, fa, t := cur[0], cur[1], cur[2]
			if ans < t {
				ans, node = t, i
			}
			for _, j := range g[i] {
				if j != fa {
					stk = append(stk, [3]int{j, i, t + 1})
				}
			}
		}
		return node, ans
	}
	node, _ := farthest(0)
	_, ans := farthest(node)
	return ans
}
```

#### TypeScript

```ts
function treeDiameter(edges: number[][]): number {
    const n = edges.length + 1;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const farthest = (start: number): [number, number] => {
        let ans = 0;
        let node = start;
        const stk: number[][] = [[start, -1, 0]];
        while (stk.length) {
            const [i, fa, t] = stk.pop()!;
            if (ans < t) {
                ans = t;
                node = i;
            }
            for (const j of g[i]) {
                if (j !== fa) {
                    stk.push([j, i, t + 1]);
                }
            }
        }
        return [node, ans];
    };
    const [node] = farthest(0);
    return farthest(node)[1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
