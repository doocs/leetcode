---
comments: true
difficulty: 困难
rating: 2294
source: 第 113 场双周赛 Q4
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 动态规划
---

<!-- problem:start -->

# [2858. 可以到达每一个节点的最少边反转次数](https://leetcode.cn/problems/minimum-edge-reversals-so-every-node-is-reachable)

[English Version](/solution/2800-2899/2858.Minimum%20Edge%20Reversals%20So%20Every%20Node%20Is%20Reachable/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <code>n</code>&nbsp;个点的 <strong>简单有向图</strong>&nbsp;（没有重复边的有向图），节点编号为 <code>0</code>&nbsp;到 <code>n - 1</code>&nbsp;。如果这些边是双向边，那么这个图形成一棵&nbsp;<strong>树</strong>&nbsp;。</p>

<p>给你一个整数&nbsp;<code>n</code>&nbsp;和一个 <strong>二维</strong>&nbsp;整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>&nbsp;表示从节点&nbsp;<code>u<sub>i</sub></code>&nbsp;到节点&nbsp;<code>v<sub>i</sub></code>&nbsp;有一条&nbsp;<strong>有向边</strong>&nbsp;。</p>

<p><strong>边反转</strong>&nbsp;指的是将一条边的方向反转，也就是说一条从节点&nbsp;<code>u<sub>i</sub></code>&nbsp;到节点&nbsp;<code>v<sub>i</sub></code>&nbsp;的边会变为一条从节点&nbsp;<code>v<sub>i</sub></code>&nbsp;到节点&nbsp;<code>u<sub>i</sub></code>&nbsp;的边。</p>

<p>对于范围&nbsp;<code>[0, n - 1]</code>&nbsp;中的每一个节点 <code>i</code>&nbsp;，你的任务是分别 <strong>独立</strong> 计算 <strong>最少</strong>&nbsp;需要多少次 <strong>边反转</strong>&nbsp;，从节点 <code>i</code>&nbsp;出发经过 <strong>一系列有向边</strong>&nbsp;，可以到达所有的节点。</p>

<p>请你返回一个长度为 <code>n</code>&nbsp;的整数数组<em>&nbsp;</em><code>answer</code><em>&nbsp;</em>，其中<em>&nbsp;</em><code>answer[i]</code>表示从节点&nbsp;<code>i</code>&nbsp;出发，可以到达所有节点的&nbsp;<strong>最少边反转</strong>&nbsp;次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img height="246" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2858.Minimum%20Edge%20Reversals%20So%20Every%20Node%20Is%20Reachable/images/image-20230826221104-3.png" width="312" /></p>

<pre>
<b>输入：</b>n = 4, edges = [[2,0],[2,1],[1,3]]
<b>输出：</b>[1,1,0,2]
<b>解释：</b>上图表示了与输入对应的简单有向图。
对于节点 0 ：反转 [2,0] ，从节点 0 出发可以到达所有节点。
所以 answer[0] = 1 。
对于节点 1 ：反转 [2,1] ，从节点 1 出发可以到达所有节点。
所以 answer[1] = 1 。
对于节点 2 ：不需要反转就可以从节点 2 出发到达所有节点。
所以 answer[2] = 0 。
对于节点 3 ：反转 [1,3] 和 [2,1] ，从节点 3 出发可以到达所有节点。
所以 answer[3] = 2 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<p><img height="217" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2858.Minimum%20Edge%20Reversals%20So%20Every%20Node%20Is%20Reachable/images/image-20230826225541-2.png" width="322" /></p>

<pre>
<b>输入：</b>n = 3, edges = [[1,2],[2,0]]
<b>输出：</b>[2,0,1]
<b>解释：</b>上图表示了与输入对应的简单有向图。
对于节点 0 ：反转 [2,0] 和 [1,2] ，从节点 0 出发可以到达所有节点。
所以 answer[0] = 2 。
对于节点 1 ：不需要反转就可以从节点 2 出发到达所有节点。
所以 answer[1] = 0 。
对于节点 2 ：反转 [1,2] ，从节点 2 出发可以到达所有节点。
所以 answer[2] = 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= u<sub>i</sub> == edges[i][0] &lt; n</code></li>
	<li><code>0 &lt;= v<sub>i</sub> == edges[i][1] &lt; n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li>输入保证如果边是双向边，可以得到一棵树。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：树形 DP

<!-- thinking:start -->

> **思考**
>
> 以每个结点为根，统计把整棵树定向为自己可达所需的最少翻边。先以 $0$ 为根 DFS，把逆边计入 $ans[0]$；换根时沿边走一步，答案增减 $1$ 或 $-1$，一次再 DFS 即可得到全部根的答案。

<!-- thinking:end -->

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minEdgeReversals(self, n: int, edges: List[List[int]]) -> List[int]:
        ans = [0] * n
        g = [[] for _ in range(n)]
        for x, y in edges:
            g[x].append((y, 1))
            g[y].append((x, -1))

        def dfs(i: int, fa: int):
            for j, k in g[i]:
                if j != fa:
                    ans[0] += int(k < 0)
                    dfs(j, i)

        dfs(0, -1)

        def dfs2(i: int, fa: int):
            for j, k in g[i]:
                if j != fa:
                    ans[j] = ans[i] + k
                    dfs2(j, i)

        dfs2(0, -1)
        return ans
```

#### Java

```java
class Solution {
    private List<int[]>[] g;
    private int[] ans;

    public int[] minEdgeReversals(int n, int[][] edges) {
        ans = new int[n];
        g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int x = e[0], y = e[1];
            g[x].add(new int[] {y, 1});
            g[y].add(new int[] {x, -1});
        }
        dfs(0, -1);
        dfs2(0, -1);
        return ans;
    }

    private void dfs(int i, int fa) {
        for (var ne : g[i]) {
            int j = ne[0], k = ne[1];
            if (j != fa) {
                ans[0] += k < 0 ? 1 : 0;
                dfs(j, i);
            }
        }
    }

    private void dfs2(int i, int fa) {
        for (var ne : g[i]) {
            int j = ne[0], k = ne[1];
            if (j != fa) {
                ans[j] = ans[i] + k;
                dfs2(j, i);
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> minEdgeReversals(int n, vector<vector<int>>& edges) {
        vector<pair<int, int>> g[n];
        vector<int> ans(n);
        for (auto& e : edges) {
            int x = e[0], y = e[1];
            g[x].emplace_back(y, 1);
            g[y].emplace_back(x, -1);
        }
        function<void(int, int)> dfs = [&](int i, int fa) {
            for (auto& [j, k] : g[i]) {
                if (j != fa) {
                    ans[0] += k < 0;
                    dfs(j, i);
                }
            }
        };
        function<void(int, int)> dfs2 = [&](int i, int fa) {
            for (auto& [j, k] : g[i]) {
                if (j != fa) {
                    ans[j] = ans[i] + k;
                    dfs2(j, i);
                }
            }
        };
        dfs(0, -1);
        dfs2(0, -1);
        return ans;
    }
};
```

#### Go

```go
func minEdgeReversals(n int, edges [][]int) []int {
	g := make([][][2]int, n)
	for _, e := range edges {
		x, y := e[0], e[1]
		g[x] = append(g[x], [2]int{y, 1})
		g[y] = append(g[y], [2]int{x, -1})
	}
	ans := make([]int, n)
	var dfs func(int, int)
	var dfs2 func(int, int)
	dfs = func(i, fa int) {
		for _, ne := range g[i] {
			j, k := ne[0], ne[1]
			if j != fa {
				if k < 0 {
					ans[0]++
				}
				dfs(j, i)
			}
		}
	}
	dfs2 = func(i, fa int) {
		for _, ne := range g[i] {
			j, k := ne[0], ne[1]
			if j != fa {
				ans[j] = ans[i] + k
				dfs2(j, i)
			}
		}
	}
	dfs(0, -1)
	dfs2(0, -1)
	return ans
}
```

#### TypeScript

```ts
function minEdgeReversals(n: number, edges: number[][]): number[] {
    const g: number[][][] = Array.from({ length: n }, () => []);
    for (const [x, y] of edges) {
        g[x].push([y, 1]);
        g[y].push([x, -1]);
    }
    const ans: number[] = Array(n).fill(0);
    const dfs = (i: number, fa: number) => {
        for (const [j, k] of g[i]) {
            if (j !== fa) {
                ans[0] += k < 0 ? 1 : 0;
                dfs(j, i);
            }
        }
    };
    const dfs2 = (i: number, fa: number) => {
        for (const [j, k] of g[i]) {
            if (j !== fa) {
                ans[j] = ans[i] + k;
                dfs2(j, i);
            }
        }
    };
    dfs(0, -1);
    dfs2(0, -1);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：换根 + 显式栈

<!-- thinking:start -->

> **思考**
>
> 以每个结点为根，统计把整棵树定向为从该点可达所需的最少翻边。$n \le 10^5$，对每个根各搜一次是 $O(n^2)$；从 $0$ 递归统计逆边，链上的调用深度就是 $n$，会超出递归栈。
>
> 先把有向边 $x \to y$ 记成从 $x$ 走向 $y$ 的权 $1$、从 $y$ 走回 $x$ 的权 $-1$。以 $0$ 为根时，走向孩子的权为负就说明这条边背离根，需要翻一次。换根走到孩子 $j$ 时，答案变成 $ans[i] + k$：权 $1$ 表示原来顺着走、换根后要翻，权 $-1$ 表示原来要翻、换根后不用再翻。
>
> 两次遍历都用显式栈，从父节点走向孩子。第一次只累加根 $0$ 的逆边，第二次按上面的增量写下每个新根。栈里保存 $(节点, 父节点)$，父节点的答案写好之后再处理孩子。

<!-- thinking:end -->

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minEdgeReversals(self, n: int, edges: List[List[int]]) -> List[int]:
        ans = [0] * n
        g = [[] for _ in range(n)]
        for x, y in edges:
            g[x].append((y, 1))
            g[y].append((x, -1))
        stk = [(0, -1)]
        while stk:
            i, fa = stk.pop()
            for j, k in g[i]:
                if j != fa:
                    if k < 0:
                        ans[0] += 1
                    stk.append((j, i))
        stk = [(0, -1)]
        while stk:
            i, fa = stk.pop()
            for j, k in g[i]:
                if j != fa:
                    ans[j] = ans[i] + k
                    stk.append((j, i))
        return ans
```

#### Java

```java
class Solution {
    public int[] minEdgeReversals(int n, int[][] edges) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            int x = e[0], y = e[1];
            g[x].add(new int[] {y, 1});
            g[y].add(new int[] {x, -1});
        }
        int[] ans = new int[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1];
            for (var ne : g[i]) {
                int j = ne[0], k = ne[1];
                if (j != fa) {
                    ans[0] += k < 0 ? 1 : 0;
                    stk.push(new int[] {j, i});
                }
            }
        }
        stk.push(new int[] {0, -1});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1];
            for (var ne : g[i]) {
                int j = ne[0], k = ne[1];
                if (j != fa) {
                    ans[j] = ans[i] + k;
                    stk.push(new int[] {j, i});
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
    vector<int> minEdgeReversals(int n, vector<vector<int>>& edges) {
        vector<vector<pair<int, int>>> g(n);
        vector<int> ans(n);
        for (auto& e : edges) {
            int x = e[0], y = e[1];
            g[x].emplace_back(y, 1);
            g[y].emplace_back(x, -1);
        }
        vector<pair<int, int>> stk{{0, -1}};
        while (!stk.empty()) {
            auto [i, fa] = stk.back();
            stk.pop_back();
            for (auto& [j, k] : g[i]) {
                if (j != fa) {
                    ans[0] += k < 0;
                    stk.emplace_back(j, i);
                }
            }
        }
        stk.emplace_back(0, -1);
        while (!stk.empty()) {
            auto [i, fa] = stk.back();
            stk.pop_back();
            for (auto& [j, k] : g[i]) {
                if (j != fa) {
                    ans[j] = ans[i] + k;
                    stk.emplace_back(j, i);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minEdgeReversals(n int, edges [][]int) []int {
	g := make([][][2]int, n)
	for _, e := range edges {
		x, y := e[0], e[1]
		g[x] = append(g[x], [2]int{y, 1})
		g[y] = append(g[y], [2]int{x, -1})
	}
	ans := make([]int, n)
	stk := [][2]int{{0, -1}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, fa := cur[0], cur[1]
		for _, ne := range g[i] {
			j, k := ne[0], ne[1]
			if j != fa {
				if k < 0 {
					ans[0]++
				}
				stk = append(stk, [2]int{j, i})
			}
		}
	}
	stk = append(stk, [2]int{0, -1})
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, fa := cur[0], cur[1]
		for _, ne := range g[i] {
			j, k := ne[0], ne[1]
			if j != fa {
				ans[j] = ans[i] + k
				stk = append(stk, [2]int{j, i})
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function minEdgeReversals(n: number, edges: number[][]): number[] {
    const g: number[][][] = Array.from({ length: n }, () => []);
    for (const [x, y] of edges) {
        g[x].push([y, 1]);
        g[y].push([x, -1]);
    }
    const ans: number[] = Array(n).fill(0);
    const stk: [number, number][] = [[0, -1]];
    while (stk.length) {
        const [i, fa] = stk.pop()!;
        for (const [j, k] of g[i]) {
            if (j !== fa) {
                ans[0] += k < 0 ? 1 : 0;
                stk.push([j, i]);
            }
        }
    }
    stk.push([0, -1]);
    while (stk.length) {
        const [i, fa] = stk.pop()!;
        for (const [j, k] of g[i]) {
            if (j !== fa) {
                ans[j] = ans[i] + k;
                stk.push([j, i]);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
