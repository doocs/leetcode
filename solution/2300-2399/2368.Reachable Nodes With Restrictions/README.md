---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2368.Reachable%20Nodes%20With%20Restrictions/README.md
rating: 1476
source: 第 305 场周赛 Q2
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 图
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [2368. 受限条件下可到达节点的数目](https://leetcode.cn/problems/reachable-nodes-with-restrictions)

[English Version](/solution/2300-2399/2368.Reachable%20Nodes%20With%20Restrictions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>现有一棵由 <code>n</code> 个节点组成的无向树，节点编号从 <code>0</code> 到 <code>n - 1</code> ，共有 <code>n - 1</code> 条边。</p>

<p>给你一个二维整数数组 <code>edges</code> ，长度为 <code>n - 1</code> ，其中 <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 表示树中节点 <code>a<sub>i</sub></code> 和 <code>b<sub>i</sub></code> 之间存在一条边。另给你一个整数数组 <code>restricted</code> 表示 <strong>受限</strong> 节点。</p>

<p>在不访问受限节点的前提下，返回你可以从节点<em> </em><code>0</code><em> </em>到达的 <strong>最多</strong> 节点数目<em>。</em></p>

<p>注意，节点 <code>0</code> <strong>不</strong> 会标记为受限节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2368.Reachable%20Nodes%20With%20Restrictions/images/ex1drawio.png" style="width: 402px; height: 322px;">
<pre><strong>输入：</strong>n = 7, edges = [[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]], restricted = [4,5]
<strong>输出：</strong>4
<strong>解释：</strong>上图所示正是这棵树。
在不访问受限节点的前提下，只有节点 [0,1,2,3] 可以从节点 0 到达。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2368.Reachable%20Nodes%20With%20Restrictions/images/ex2drawio.png" style="width: 412px; height: 312px;">
<pre><strong>输入：</strong>n = 7, edges = [[0,1],[0,2],[0,5],[0,4],[3,2],[6,5]], restricted = [4,2,1]
<strong>输出：</strong>3
<strong>解释：</strong>上图所示正是这棵树。
在不访问受限节点的前提下，只有节点 [0,5,6] 可以从节点 0 到达。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>edges</code> 表示一棵有效的树</li>
	<li><code>1 &lt;= restricted.length &lt; n</code></li>
	<li><code>1 &lt;= restricted[i] &lt; n</code></li>
	<li><code>restricted</code> 中的所有值 <strong>互不相同</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们首先根据给定的边构建一个邻接表 $g$，其中 $g[i]$ 表示与节点 $i$ 相邻的节点列表。然后我们定义一个哈希表 $vis$，用于记录受限节点或者已经访问过的节点，初始时将受限节点加入到 $vis$ 中。

接下来我们定义一个深度优先搜索函数 $dfs(i)$，表示从节点 $i$ 出发，可以到达的节点数。在 $dfs(i)$ 函数中，我们首先将节点 $i$ 加入到 $vis$ 中，然后遍历节点 $i$ 的相邻节点 $j$，如果 $j$ 不在 $vis$ 中，我们递归调用 $dfs(j)$，并将返回值加到结果中。

最后我们返回 $dfs(0)$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点数。

<!-- tabs:start -->

```python
class Solution:
    def reachableNodes(
        self, n: int, edges: List[List[int]], restricted: List[int]
    ) -> int:
        def dfs(i: int) -> int:
            vis.add(i)
            return 1 + sum(j not in vis and dfs(j) for j in g[i])

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = set(restricted)
        return dfs(0)
```

```java
class Solution {
    private List<Integer>[] g;
    private boolean[] vis;

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        g = new List[n];
        vis = new boolean[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        for (int i : restricted) {
            vis[i] = true;
        }
        return dfs(0);
    }

    private int dfs(int i) {
        vis[i] = true;
        int ans = 1;
        for (int j : g[i]) {
            if (!vis[j]) {
                ans += dfs(j);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int reachableNodes(int n, vector<vector<int>>& edges, vector<int>& restricted) {
        vector<int> g[n];
        vector<int> vis(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        for (int i : restricted) {
            vis[i] = true;
        }
        function<int(int)> dfs = [&](int i) {
            vis[i] = true;
            int ans = 1;
            for (int j : g[i]) {
                if (!vis[j]) {
                    ans += dfs(j);
                }
            }
            return ans;
        };
        return dfs(0);
    }
};
```

```go
func reachableNodes(n int, edges [][]int, restricted []int) int {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	vis := make([]bool, n)
	for _, v := range restricted {
		vis[v] = true
	}
	ans := 0
	var dfs func(u int)
	dfs = func(u int) {
		if vis[u] {
			return
		}
		vis[u] = true
		ans++
		for _, v := range g[u] {
			dfs(v)
		}
	}
	dfs(0)
	return ans
}
```

```ts
function reachableNodes(n: number, edges: number[][], restricted: number[]): number {
    const vis: boolean[] = Array(n).fill(false);
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    for (const i of restricted) {
        vis[i] = true;
    }
    const dfs = (i: number): number => {
        vis[i] = true;
        let ans = 1;
        for (const j of g[i]) {
            if (!vis[j]) {
                ans += dfs(j);
            }
        }
        return ans;
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：BFS

与方法一类似，我们首先根据给定的边构建一个邻接表 $g$，然后定义一个哈希表 $vis$，用于记录受限节点或者已经访问过的节点，初始时将受限节点加入到 $vis$ 中。

接下来我们使用广度优先搜索遍历整个图，统计可以到达的节点数。我们定义一个队列 $q$，初始时将节点 $0$ 加入到 $q$ 中，并且将节点 $0$ 加入到 $vis$ 中。然后我们不断从 $q$ 中取出节点 $i$，累加答案，并将节点 $i$ 的相邻节点中未访问过的节点加入到 $q$ 中，并将这些节点加入到 $vis$ 中。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点数。

<!-- tabs:start -->

```python
class Solution:
    def reachableNodes(
        self, n: int, edges: List[List[int]], restricted: List[int]
    ) -> int:
        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = set(restricted + [0])
        q = deque([0])
        ans = 0
        while q:
            i = q.popleft()
            ans += 1
            for j in g[i]:
                if j not in vis:
                    q.append(j)
                    vis.add(j)
        return ans
```

```java
class Solution {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        List<Integer>[] g = new List[n];
        boolean[] vis = new boolean[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        for (int v : restricted) {
            vis[v] = true;
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        int ans = 0;
        for (vis[0] = true; !q.isEmpty(); ++ans) {
            int i = q.pollFirst();
            for (int j : g[i]) {
                if (!vis[j]) {
                    q.offer(j);
                    vis[j] = true;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int reachableNodes(int n, vector<vector<int>>& edges, vector<int>& restricted) {
        vector<int> g[n];
        vector<int> vis(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        for (int i : restricted) {
            vis[i] = true;
        }
        queue<int> q{{0}};
        int ans = 0;
        for (vis[0] = true; !q.empty(); ++ans) {
            int i = q.front();
            q.pop();
            for (int j : g[i]) {
                if (!vis[j]) {
                    vis[j] = true;
                    q.push(j);
                }
            }
        }
        return ans;
    }
};
```

```go
func reachableNodes(n int, edges [][]int, restricted []int) (ans int) {
	g := make([][]int, n)
	vis := make([]bool, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	for _, i := range restricted {
		vis[i] = true
	}
	q := []int{0}
	for vis[0] = true; len(q) > 0; ans++ {
		i := q[0]
		q = q[1:]
		for _, j := range g[i] {
			if !vis[j] {
				vis[j] = true
				q = append(q, j)
			}
		}
	}
	return
}
```

```ts
function reachableNodes(n: number, edges: number[][], restricted: number[]): number {
    const vis: boolean[] = Array(n).fill(false);
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    for (const i of restricted) {
        vis[i] = true;
    }
    const q: number[] = [0];
    let ans = 0;
    for (vis[0] = true; q.length; ++ans) {
        const i = q.pop()!;
        for (const j of g[i]) {
            if (!vis[j]) {
                vis[j] = true;
                q.push(j);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
