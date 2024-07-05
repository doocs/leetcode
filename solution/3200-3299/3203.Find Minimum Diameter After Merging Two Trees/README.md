---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3203.Find%20Minimum%20Diameter%20After%20Merging%20Two%20Trees/README.md
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 图
---

<!-- problem:start -->

# [3203. 合并两棵树后的最小直径](https://leetcode.cn/problems/find-minimum-diameter-after-merging-two-trees)

[English Version](/solution/3200-3299/3203.Find%20Minimum%20Diameter%20After%20Merging%20Two%20Trees/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两棵 <strong>无向</strong>&nbsp;树，分别有&nbsp;<code>n</code> 和&nbsp;<code>m</code>&nbsp;个节点，节点编号分别为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;和&nbsp;<code>0</code>&nbsp;到&nbsp;<code>m - 1</code>&nbsp;。给你两个二维整数数组&nbsp;<code>edges1</code> 和&nbsp;<code>edges2</code>&nbsp;，长度分别为&nbsp;<code>n - 1</code> 和&nbsp;<code>m - 1</code>&nbsp;，其中&nbsp;<code>edges1[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示在第一棵树中节点&nbsp;<code>a<sub>i</sub></code> 和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条边，<code>edges2[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>&nbsp;表示在第二棵树中节点&nbsp;<code>u<sub>i</sub></code> 和&nbsp;<code>v<sub>i</sub></code>&nbsp;之间有一条边。</p>

<p>你必须在第一棵树和第二棵树中分别选一个节点，并用一条边连接它们。</p>

<p>请你返回添加边后得到的树中，<strong>最小直径</strong>&nbsp;为多少。</p>

<p>一棵树的 <strong>直径</strong>&nbsp;指的是树中任意两个节点之间的最长路径长度。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3203.Find%20Minimum%20Diameter%20After%20Merging%20Two%20Trees/images/example11-transformed.png" style="width: 1000px; height: 494px;" /></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>edges1 = [[0,1],[0,2],[0,3]], edges2 = [[0,1]]</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<p>将第一棵树中的节点 0 与第二棵树中的任意节点连接，得到一棵直径为 3 得树。</p>
</div>

<p><strong class="example">示例 2：<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3203.Find%20Minimum%20Diameter%20After%20Merging%20Two%20Trees/images/example211.png" style="width: 1000px; height: 492px;" /></strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>edges1 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]], edges2 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]]</span></p>

<p><span class="example-io"><b>输出：</b>5</span></p>

<p><strong>解释：</strong></p>

<p>将第一棵树中的节点 0 和第二棵树中的节点 0 连接，可以得到一棵直径为 5 的树。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n, m &lt;= 10<sup>5</sup></code></li>
	<li><code>edges1.length == n - 1</code></li>
	<li><code>edges2.length == m - 1</code></li>
	<li><code>edges1[i].length == edges2[i].length == 2</code></li>
	<li><code>edges1[i] = [a<sub>i</sub>, b<sub>i</sub>]</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>edges2[i] = [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; m</code></li>
	<li>输入保证&nbsp;<code>edges1</code> 和&nbsp;<code>edges2</code>&nbsp;分别表示一棵合法的树。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：两次 DFS

我们记 $d_1$ 和 $d_2$ 分别为两棵树的直径，那么合并后的树的直径有以下两种情况：

1. 合并后的树的直径为原始的一棵树的直径，即 $\max(d_1, d_2)$；
2. 合并后的树的直径经过原始的两棵树。我们分别计算原始的两棵树的半径 $r_1 = \lceil \frac{d_1}{2} \rceil$ 和 $r_2 = \lceil \frac{d_2}{2} \rceil$，那么合并后的树的直径为 $r_1 + r_2 + 1$。

我们取这两种情况的最大值即可。

在计算树的直径时，我们可以使用两次 DFS。首先我们任选一点出发，找到距离该点最远的点，记为 $a$。然后从点 $a$ 出发，找到距离点 $a$ 最远的点，记为 $b$。可以证明，点 $a$ 和点 $b$ 之间的路径即为树的直径。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别为两棵树的节点数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumDiameterAfterMerge(
        self, edges1: List[List[int]], edges2: List[List[int]]
    ) -> int:
        d1 = self.treeDiameter(edges1)
        d2 = self.treeDiameter(edges2)
        return max(d1, d2, (d1 + 1) // 2 + (d2 + 1) // 2 + 1)

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

    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int d1 = treeDiameter(edges1);
        int d2 = treeDiameter(edges2);
        return Math.max(Math.max(d1, d2), (d1 + 1) / 2 + (d2 + 1) / 2 + 1);
    }

    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        ans = 0;
        a = 0;
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
    int minimumDiameterAfterMerge(vector<vector<int>>& edges1, vector<vector<int>>& edges2) {
        int d1 = treeDiameter(edges1);
        int d2 = treeDiameter(edges2);
        return max({d1, d2, (d1 + 1) / 2 + (d2 + 1) / 2 + 1});
    }

    int treeDiameter(vector<vector<int>>& edges) {
        int n = edges.size() + 1;
        vector<int> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        int ans = 0, a = 0;
        auto dfs = [&](auto&& dfs, int i, int fa, int t) -> void {
            for (int j : g[i]) {
                if (j != fa) {
                    dfs(dfs, j, i, t + 1);
                }
            }
            if (ans < t) {
                ans = t;
                a = i;
            }
        };
        dfs(dfs, 0, -1, 0);
        dfs(dfs, a, -1, 0);
        return ans;
    }
};
```

#### Go

```go
func minimumDiameterAfterMerge(edges1 [][]int, edges2 [][]int) int {
	d1 := treeDiameter(edges1)
	d2 := treeDiameter(edges2)
	return max(max(d1, d2), (d1+1)/2+(d2+1)/2+1)
}

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
function minimumDiameterAfterMerge(edges1: number[][], edges2: number[][]): number {
    const d1 = treeDiameter(edges1);
    const d2 = treeDiameter(edges2);
    return Math.max(d1, d2, Math.ceil(d1 / 2) + Math.ceil(d2 / 2) + 1);
}

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

<!-- problem:end -->
