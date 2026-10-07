---
comments: true
difficulty: 困难
rating: 2126
source: 第 289 场周赛 Q4
tags:
    - 树
    - 深度优先搜索
    - 图
    - 拓扑排序
    - 数组
    - 字符串
---

<!-- problem:start -->

# [2246. 相邻字符不同的最长路径](https://leetcode.cn/problems/longest-path-with-different-adjacent-characters)

[English Version](/solution/2200-2299/2246.Longest%20Path%20With%20Different%20Adjacent%20Characters/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵 <strong>树</strong>（即一个连通、无向、无环图），根节点是节点 <code>0</code> ，这棵树由编号从 <code>0</code> 到 <code>n - 1</code> 的 <code>n</code> 个节点组成。用下标从 <strong>0</strong> 开始、长度为 <code>n</code> 的数组 <code>parent</code> 来表示这棵树，其中 <code>parent[i]</code> 是节点 <code>i</code> 的父节点，由于节点 <code>0</code> 是根节点，所以 <code>parent[0] == -1</code> 。</p>

<p>另给你一个字符串 <code>s</code> ，长度也是 <code>n</code> ，其中 <code>s[i]</code> 表示分配给节点 <code>i</code> 的字符。</p>

<p>请你找出路径上任意一对相邻节点都没有分配到相同字符的 <strong>最长路径</strong> ，并返回该路径的长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2246.Longest%20Path%20With%20Different%20Adjacent%20Characters/images/testingdrawio.png" style="width: 201px; height: 241px;" /></p>

<pre>
<strong>输入：</strong>parent = [-1,0,0,1,1,2], s = "abacbe"
<strong>输出：</strong>3
<strong>解释：</strong>任意一对相邻节点字符都不同的最长路径是：0 -&gt; 1 -&gt; 3 。该路径的长度是 3 ，所以返回 3 。
可以证明不存在满足上述条件且比 3 更长的路径。 
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2246.Longest%20Path%20With%20Different%20Adjacent%20Characters/images/graph2drawio.png" style="width: 201px; height: 221px;" /></p>

<pre>
<strong>输入：</strong>parent = [-1,0,0,0], s = "aabc"
<strong>输出：</strong>3
<strong>解释：</strong>任意一对相邻节点字符都不同的最长路径是：2 -&gt; 0 -&gt; 3 。该路径的长度为 3 ，所以返回 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == parent.length == s.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li>对所有 <code>i &gt;= 1</code> ，<code>0 &lt;= parent[i] &lt;= n - 1</code> 均成立</li>
	<li><code>parent[0] == -1</code></li>
	<li><code>parent</code> 表示一棵有效的树</li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：树形 DP

<!-- thinking:start -->

> **思考**
>
> 在树上找一条相邻字符均不同的最长路径。 $n \le 10^5$，不能枚举端点对。路径要么完全落在某一子树，要么在某点处把两条合法向下链拼起来。
>
> DFS 返回「从当前点向下、且第一步字符不同」的最长链长。对每个儿子先递归；仅当 $s[i]\neq s[j]$ 时，用当前次长链与这条新链更新全局答案，并维护最长向下链。根上再加一（计入自身）。一次遍历即可。

<!-- thinking:end -->

我们先根据数组 $parent$ 构建邻接表 $g$，其中 $g[i]$ 表示节点 $i$ 的所有子节点。

然后我们从根节点开始 DFS，对于每个节点 $i$，我们遍历 $g[i]$ 中的每个子节点 $j$，如果 $s[i] \neq s[j]$，那么我们就可以从 $i$ 节点出发，经过 $j$ 节点，到达某个叶子节点，这条路径的长度为 $x = 1 + dfs(j)$，我们用 $mx$ 记录最长的一条从节点 $i$ 出发的路径长度。同时，在遍历的过程中，更新答案 $ans = \max(ans, mx + x)$。

最后，我们返回 $ans + 1$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestPath(self, parent: List[int], s: str) -> int:
        def dfs(i: int) -> int:
            mx = 0
            nonlocal ans
            for j in g[i]:
                x = dfs(j) + 1
                if s[i] != s[j]:
                    ans = max(ans, mx + x)
                    mx = max(mx, x)
            return mx

        g = defaultdict(list)
        for i in range(1, len(parent)):
            g[parent[i]].append(i)
        ans = 0
        dfs(0)
        return ans + 1
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private String s;
    private int ans;

    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        g = new List[n];
        this.s = s;
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            g[parent[i]].add(i);
        }
        dfs(0);
        return ans + 1;
    }

    private int dfs(int i) {
        int mx = 0;
        for (int j : g[i]) {
            int x = dfs(j) + 1;
            if (s.charAt(i) != s.charAt(j)) {
                ans = Math.max(ans, mx + x);
                mx = Math.max(mx, x);
            }
        }
        return mx;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestPath(vector<int>& parent, string s) {
        int n = parent.size();
        vector<int> g[n];
        for (int i = 1; i < n; ++i) {
            g[parent[i]].push_back(i);
        }
        int ans = 0;
        function<int(int)> dfs = [&](int i) -> int {
            int mx = 0;
            for (int j : g[i]) {
                int x = dfs(j) + 1;
                if (s[i] != s[j]) {
                    ans = max(ans, mx + x);
                    mx = max(mx, x);
                }
            }
            return mx;
        };
        dfs(0);
        return ans + 1;
    }
};
```

#### Go

```go
func longestPath(parent []int, s string) int {
	n := len(parent)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}
	ans := 0
	var dfs func(int) int
	dfs = func(i int) int {
		mx := 0
		for _, j := range g[i] {
			x := dfs(j) + 1
			if s[i] != s[j] {
				ans = max(ans, x+mx)
				mx = max(mx, x)
			}
		}
		return mx
	}
	dfs(0)
	return ans + 1
}
```

#### TypeScript

```ts
function longestPath(parent: number[], s: string): number {
    const n = parent.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 1; i < n; ++i) {
        g[parent[i]].push(i);
    }
    let ans = 0;
    const dfs = (i: number): number => {
        let mx = 0;
        for (const j of g[i]) {
            const x = dfs(j) + 1;
            if (s[i] !== s[j]) {
                ans = Math.max(ans, mx + x);
                mx = Math.max(mx, x);
            }
        }
        return mx;
    };
    dfs(0);
    return ans + 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：树形 DP + 显式栈

<!-- thinking:start -->

> **思考**
>
> 在树上找一条相邻字符均不同的最长路径。$n \le 10^5$，从根递归求每棵子树的向下链，链上的调用深度就是 $n$，会超出递归栈。
>
> 这样的路径要么完全落在某一子树，要么在某个点把两条向下链拼起来。离开一个节点时，儿子的向下链已经知道：仅当 $s[i] \neq s[j]$ 时，这条链才能接到 $i$ 上，长度是儿子的链长加 $1$。用当前最长的一条与新链更新全局答案，并留下更长的那条作为本节点的向下链。
>
> 显式栈按 $(节点, 状态)$ 做后序。进入时先压出栈标记再压孩子，离开时按上面的规则更新答案和向下链。最后把答案加 $1$，补上路径经过的节点数。

<!-- thinking:end -->

我们先根据数组 $parent$ 构建邻接表 $g$，其中 $g[i]$ 表示节点 $i$ 的所有子节点。

用显式栈从根做后序。离开节点 $i$ 时，遍历每个子节点 $j$，记 $x$ 为从 $j$ 向下的最长链再加 $1$。若 $s[i] \neq s[j]$，就用已经记下的最长向下链 $mx$ 与 $x$ 更新答案 $ans = \max(ans, mx + x)$，再把 $mx$ 更新为 $\max(mx, x)$。$mx$ 是从 $i$ 向下、且第一步字符不同的最长链。

最后，我们返回 $ans + 1$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestPath(self, parent: List[int], s: str) -> int:
        n = len(parent)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parent[i]].append(i)
        down = [0] * n
        ans = 0
        stk = [(0, 0)]
        while stk:
            i, state = stk.pop()
            if state == 0:
                stk.append((i, 1))
                for j in g[i]:
                    stk.append((j, 0))
            else:
                mx = 0
                for j in g[i]:
                    x = down[j] + 1
                    if s[i] != s[j]:
                        ans = max(ans, mx + x)
                        mx = max(mx, x)
                down[i] = mx
        return ans + 1
```

#### Java

```java
class Solution {
    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            g[parent[i]].add(i);
        }
        int[] down = new int[n];
        int ans = 0;
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], state = cur[1];
            if (state == 0) {
                stk.push(new int[] {i, 1});
                for (int j : g[i]) {
                    stk.push(new int[] {j, 0});
                }
            } else {
                int mx = 0;
                for (int j : g[i]) {
                    int x = down[j] + 1;
                    if (s.charAt(i) != s.charAt(j)) {
                        ans = Math.max(ans, mx + x);
                        mx = Math.max(mx, x);
                    }
                }
                down[i] = mx;
            }
        }
        return ans + 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestPath(vector<int>& parent, string s) {
        int n = parent.size();
        vector<vector<int>> g(n);
        for (int i = 1; i < n; ++i) {
            g[parent[i]].push_back(i);
        }
        vector<int> down(n);
        int ans = 0;
        vector<array<int, 2>> stk{{0, 0}};
        while (!stk.empty()) {
            auto [i, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({i, 1});
                for (int j : g[i]) {
                    stk.push_back({j, 0});
                }
            } else {
                int mx = 0;
                for (int j : g[i]) {
                    int x = down[j] + 1;
                    if (s[i] != s[j]) {
                        ans = max(ans, mx + x);
                        mx = max(mx, x);
                    }
                }
                down[i] = mx;
            }
        }
        return ans + 1;
    }
};
```

#### Go

```go
func longestPath(parent []int, s string) int {
	n := len(parent)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}
	down := make([]int, n)
	ans := 0
	stk := [][2]int{{0, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, state := cur[0], cur[1]
		if state == 0 {
			stk = append(stk, [2]int{i, 1})
			for _, j := range g[i] {
				stk = append(stk, [2]int{j, 0})
			}
		} else {
			mx := 0
			for _, j := range g[i] {
				x := down[j] + 1
				if s[i] != s[j] {
					ans = max(ans, x+mx)
					mx = max(mx, x)
				}
			}
			down[i] = mx
		}
	}
	return ans + 1
}
```

#### TypeScript

```ts
function longestPath(parent: number[], s: string): number {
    const n = parent.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 1; i < n; ++i) {
        g[parent[i]].push(i);
    }
    const down = Array(n).fill(0);
    let ans = 0;
    const stk: [number, number][] = [[0, 0]];
    while (stk.length) {
        const [i, state] = stk.pop()!;
        if (state === 0) {
            stk.push([i, 1]);
            for (const j of g[i]) {
                stk.push([j, 0]);
            }
        } else {
            let mx = 0;
            for (const j of g[i]) {
                const x = down[j] + 1;
                if (s[i] !== s[j]) {
                    ans = Math.max(ans, mx + x);
                    mx = Math.max(mx, x);
                }
            }
            down[i] = mx;
        }
    }
    return ans + 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
