---
comments: true
difficulty: 困难
tags:
    - 树
    - 深度优先搜索
    - 图
    - 动态规划
    - 树形 DP
---

<!-- problem:start -->

# [834. 树中距离之和](https://leetcode.cn/problems/sum-of-distances-in-tree)

[English Version](/solution/0800-0899/0834.Sum%20of%20Distances%20in%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个无向、连通的树。树中有 <code>n</code> 个标记为 <code>0...n-1</code> 的节点以及 <code>n-1</code>&nbsp;条边&nbsp;。</p>

<p>给定整数 <code>n</code> 和数组&nbsp;<code>edges</code>&nbsp;，&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>表示树中的节点&nbsp;<code>a<sub>i</sub></code>&nbsp;和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条边。</p>

<p>返回长度为 <code>n</code> 的数组&nbsp;<code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code>&nbsp;是树中第 <code>i</code> 个节点与所有其他节点之间的距离之和。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0834.Sum%20of%20Distances%20in%20Tree/images/lc-sumdist1.jpg" /></p>

<pre>
<strong>输入: </strong>n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
<strong>输出: </strong>[8,12,6,10,10,10]
<strong>解释: </strong>树如图所示。
我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5) 
也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
</pre>

<p><strong>示例 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0834.Sum%20of%20Distances%20in%20Tree/images/lc-sumdist2.jpg" />
<pre>
<strong>输入:</strong> n = 1, edges = []
<strong>输出:</strong> [0]
</pre>

<p><strong>示例 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0834.Sum%20of%20Distances%20in%20Tree/images/lc-sumdist3.jpg" />
<pre>
<strong>输入:</strong> n = 2, edges = [[1,0]]
<strong>输出:</strong> [1,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub>&nbsp;&lt; n</code></li>
	<li><code>a<sub>i</sub>&nbsp;!= b<sub>i</sub></code></li>
	<li>给定的输入保证为有效的树</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：树形 DP（换根）

<!-- thinking:start -->

> **思考**
>
> 对每个结点求到其余结点的距离和。 $n\le 3\cdot 10^4$，若以每个点为根做一次 DFS 会平方级。换根后，相邻两点的答案只差「子树大小」与「树外点数」。
>
> 先以 $0$ 为根算出 $ans[0]$ 及各子树大小，再第二次 DFS 用 $t-\textit{size}[j]+n-\textit{size}[j]$ 推到孩子。两遍遍历得到全部答案。

<!-- thinking:end -->

我们先跑一遍 DFS，计算出每个节点的子树大小，记录在数组 $size$ 中，并且统计出节点 $0$ 到其他节点的距离之和，记录在 $ans[0]$ 中。

接下来，我们再跑一遍 DFS，枚举每个点作为根节点时，其他节点到根节点的距离之和。假设当前节点 $i$ 的答案为 $t$，当我们从节点 $i$ 转移到节点 $j$ 时，距离之和变为 $t - size[j] + n - size[j]$，即距离节点 $j$ 及其子树节点的距离之和减少 $size[j]$，而距离其它节点的距离之和增加 $n - size[j]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为树的节点数。

相似题目：

- [2581. 统计可能的树根数目](https://github.com/doocs/leetcode/blob/main/solution/2500-2599/2581.Count%20Number%20of%20Possible%20Root%20Nodes/README.md)

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

### 方法二：换根 DP + 显式栈

<!-- thinking:start -->

> **思考**
>
> 对每个结点求到其余结点的距离和。$n\le 3\cdot 10^4$，以每个点为根各做一次遍历会平方级；换根后，相邻两点的答案只差子树大小与树外点数。沿一条链递归时调用深度等于 $n$，链长达到 $1000$ 就会超出 Python 的递归上限。子树大小要等孩子算完才能相加，所以第一遍用带状态的栈做后序：状态 $0$ 累加深度并压入孩子，状态 $1$ 汇总 $size$。第二遍答案只依赖父亲的答案和已经算好的 $size$，用栈按前序把 $t-size[j]+n-size[j]$ 传给孩子。

<!-- thinking:end -->

先以 $0$ 为根做后序遍历。栈里存放节点、父亲、深度和状态。状态为 $0$ 时，把深度累加到 $ans[0]$，再把同一节点以状态 $1$ 压回，并压入每个孩子。状态为 $1$ 时，孩子的子树大小都已写好，$size[i]$ 等于 $1$ 加上各孩子的 $size$。

接着用显式栈做换根。栈里初始是 $(0,-1,ans[0])$。弹出节点 $i$ 时把当前距离和 $t$ 写入 $ans[i]$。从 $i$ 走到孩子 $j$ 时，距离 $j$ 及其子树的距离和减少 $size[j]$，距离其余节点的距离和增加 $n-size[j]$，所以压入的新距离和是 $t-size[j]+n-size[j]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为树的节点数。

相似题目：

- [2581. 统计可能的树根数目](https://github.com/doocs/leetcode/blob/main/solution/2500-2599/2581.Count%20Number%20of%20Possible%20Root%20Nodes/README.md)

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
