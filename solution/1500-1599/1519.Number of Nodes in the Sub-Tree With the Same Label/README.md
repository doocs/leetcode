---
comments: true
difficulty: 中等
rating: 1808
source: 第 198 场周赛 Q2
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 哈希表
    - 计数
    - 树形 DP
---

<!-- problem:start -->

# [1519. 子树中标签相同的节点数](https://leetcode.cn/problems/number-of-nodes-in-the-sub-tree-with-the-same-label)

[English Version](/solution/1500-1599/1519.Number%20of%20Nodes%20in%20the%20Sub-Tree%20With%20the%20Same%20Label/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵树（即，一个连通的无环无向图），这棵树由编号从 <code>0</code>&nbsp; 到 <code>n - 1</code> 的 n 个节点组成，且恰好有 <code>n - 1</code> 条 <code>edges</code> 。树的根节点为节点 <code>0</code> ，树上的每一个节点都有一个标签，也就是字符串 <code>labels</code> 中的一个小写字符（编号为 <code>i</code> 的 节点的标签就是 <code>labels[i]</code> ）</p>

<p>边数组 <code>edges</code> 以 <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 的形式给出，该格式表示节点 <code>a<sub>i</sub></code> 和 <code>b<sub>i</sub></code> 之间存在一条边。</p>

<p>返回一个大小为 <em><code>n</code></em> 的数组，其中 <code>ans[i]</code> 表示第 <code>i</code> 个节点的子树中与节点 <code>i</code> 标签相同的节点数。</p>

<p>树 <code>T</code> 中的子树是由 <code>T</code> 中的某个节点及其所有后代节点组成的树。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1519.Number%20of%20Nodes%20in%20the%20Sub-Tree%20With%20the%20Same%20Label/images/q3e1.jpg" style="height: 321px; width: 441px;" /></p>

<pre>
<strong>输入：</strong>n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = "abaedcd"
<strong>输出：</strong>[2,1,1,1,1,1,1]
<strong>解释：</strong>节点 0 的标签为 'a' ，以 'a' 为根节点的子树中，节点 2 的标签也是 'a' ，因此答案为 2 。注意树中的每个节点都是这棵子树的一部分。
节点 1 的标签为 'b' ，节点 1 的子树包含节点 1、4 和 5，但是节点 4、5 的标签与节点 1 不同，故而答案为 1（即，该节点本身）。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1519.Number%20of%20Nodes%20in%20the%20Sub-Tree%20With%20the%20Same%20Label/images/q3e2.jpg" style="height: 321px; width: 381px;" /></p>

<pre>
<strong>输入：</strong>n = 4, edges = [[0,1],[1,2],[0,3]], labels = "bbbb"
<strong>输出：</strong>[4,2,1,1]
<strong>解释：</strong>节点 2 的子树中只有节点 2 ，所以答案为 1 。
节点 3 的子树中只有节点 3 ，所以答案为 1 。
节点 1 的子树中包含节点 1 和 2 ，标签都是 'b' ，因此答案为 2 。
节点 0 的子树中包含节点 0、1、2 和 3，标签都是 'b'，因此答案为 4 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1519.Number%20of%20Nodes%20in%20the%20Sub-Tree%20With%20the%20Same%20Label/images/q3e3.jpg" style="height: 321px; width: 381px;" /></p>

<pre>
<strong>输入：</strong>n = 5, edges = [[0,1],[0,2],[1,3],[0,4]], labels = "aabab"
<strong>输出：</strong>[3,2,1,1,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>,&nbsp;b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> !=&nbsp;b<sub>i</sub></code></li>
	<li><code>labels.length == n</code></li>
	<li><code>labels</code> 仅由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

<!-- thinking:start -->

> **思考**
>
> 对每个节点统计其树中与自身标签相同的节点数。 $n\le 10^5$，若对每个节点单独再遍历子树，总时间平方级。标签只有 $26$ 种，适合在一次深度优先中维护全局计数。
>
> 进入节点 $i$ 前记下标签 $labels[i]$ 的当前出现次数，离开时再读一次，两者之差便是该子树内该标签的出现次数。先减后加的写法把「进入快照」存在 $ans[i]$ 里，回溯后加上最新计数即可，额外空间仅为字母表大小。

<!-- thinking:end -->

我们先将边数组转换为邻接表 $g$。

接下来我们从根节点 $0$ 开始遍历其子树，过程中维护一个计数器 $cnt$，用于统计当前各个字母出现的次数。

在访问某个节点 $i$ 时，我们先将 $ans[i]$ 减去 $cnt[labels[i]]$，然后将 $cnt[labels[i]]$ 加 $1$，表示当前节点 $i$ 的标签出现了一次。接下来递归访问其子节点，最后将 $ans[i]$ 加上 $cnt[labels[i]]$。也即是说，我们将每个点离开时的计数器值减去每个点进来时的计数器值，就得到了以该点为根的子树中各个字母出现的次数。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为节点数；而 $C$ 为字符集大小，本题中 $C = 26$。

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

### 方法二：显式栈

<!-- thinking:start -->

> **思考**
>
> 对每个节点统计其子树中与自身标签相同的节点数。$n\le 10^5$，对每个节点再单独遍历子树是平方时间。一条链上第一次递归总是走向唯一的孩子，深度就是 $n$，会超出默认递归上限。
>
> 标签只有 $26$ 种。进入节点时该标签计数的增量，到离开时正好覆盖整棵子树。
>
> 因此用显式栈做后序。进入时先把当前计数从 $ans[i]$ 中减去，计数加一，再压入离开标记和子节点。离开时把最新计数加回 $ans[i]$，差值就是子树内该标签的出现次数。

<!-- thinking:end -->

我们先将边数组转换为邻接表 $g$。

用显式栈从根节点 $0$ 遍历，并维护计数器 $cnt$。进入节点 $i$ 时，先将 $ans[i]$ 减去 $cnt[labels[i]]$，再把该标签的计数加 $1$，然后压入离开标记和子节点，这样子节点会先处理完。离开时把 $cnt[labels[i]]$ 加回 $ans[i]$。两次计数之差就是以 $i$ 为根的子树中该标签的出现次数。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点数。

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
