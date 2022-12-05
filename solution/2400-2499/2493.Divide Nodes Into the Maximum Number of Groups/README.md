# [2493. 将节点分成尽可能多的组](https://leetcode.cn/problems/divide-nodes-into-the-maximum-number-of-groups)

[English Version](/solution/2400-2499/2493.Divide%20Nodes%20Into%20the%20Maximum%20Number%20of%20Groups/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数&nbsp;<code>n</code>&nbsp;，表示一个 <strong>无向</strong>&nbsp;图中的节点数目，节点编号从&nbsp;<code>1</code>&nbsp;到&nbsp;<code>n</code>&nbsp;。</p>

<p>同时给你一个二维整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [a<sub>i, </sub>b<sub>i</sub>]</code>&nbsp;表示节点&nbsp;<code>a<sub>i</sub></code> 和&nbsp;<code>b<sub>i</sub></code><sub>&nbsp;</sub>之间有一条&nbsp;<strong>双向</strong>&nbsp;边。注意给定的图可能是不连通的。</p>

<p>请你将图划分为&nbsp;<code>m</code>&nbsp;个组（编号从 <strong>1</strong>&nbsp;开始），满足以下要求：</p>

<ul>
	<li>图中每个节点都只属于一个组。</li>
	<li>图中每条边连接的两个点&nbsp;<code>[a<sub>i, </sub>b<sub>i</sub>]</code>&nbsp;，如果&nbsp;<code>a<sub>i</sub></code>&nbsp;属于编号为&nbsp;<code>x</code>&nbsp;的组，<code>b<sub>i</sub></code>&nbsp;属于编号为&nbsp;<code>y</code>&nbsp;的组，那么&nbsp;<code>|y - x| = 1</code>&nbsp;。</li>
</ul>

<p>请你返回最多可以将节点分为多少个组（也就是最大的<em>&nbsp;</em><code>m</code>&nbsp;）。如果没办法在给定条件下分组，请你返回&nbsp;<code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2493.Divide%20Nodes%20Into%20the%20Maximum%20Number%20of%20Groups/images/example1.png" style="width: 352px; height: 201px;"></p>

<pre><b>输入：</b>n = 6, edges = [[1,2],[1,4],[1,5],[2,6],[2,3],[4,6]]
<b>输出：</b>4
<b>解释：</b>如上图所示，
- 节点 5 在第一个组。
- 节点 1 在第二个组。
- 节点 2 和节点 4 在第三个组。
- 节点 3 和节点 6 在第四个组。
所有边都满足题目要求。
如果我们创建第五个组，将第三个组或者第四个组中任何一个节点放到第五个组，至少有一条边连接的两个节点所属的组编号不符合题目要求。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>n = 3, edges = [[1,2],[2,3],[3,1]]
<b>输出：</b>-1
<b>解释：</b>如果我们将节点 1 放入第一个组，节点 2 放入第二个组，节点 3 放入第三个组，前两条边满足题目要求，但第三条边不满足题目要求。
没有任何符合题目要求的分组方式。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= edges.length &lt;= 10<sup>4</sup></code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>两个点之间至多只有一条边。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS + BFS**

注意到图可能不连通，因此我们可以通过 DFS 找到每个连通块。

然后对于每个连通块，枚举该连通块中的每个点作为起点，使用 BFS 对图进行分层。分层结束后，校验是否合法。若合法，更新该连通块的最大值。若某个连通块不存在合法的分层，说明没办法在给定条件下分组，直接返回 $-1$。否则，将该连通块的最大值加入答案中。

时间复杂度 $O(n \times (n + m))$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别为节点数和边数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def magnificentSets(self, n: int, edges: List[List[int]]) -> int:
        def dfs(i):
            arr.append(i)
            vis[i] = True
            for j in g[i]:
                if not vis[j]:
                    dfs(j)

        def bfs(i):
            ans = 1
            dist = [inf] * (n + 1)
            dist[i] = 1
            q = deque([i])
            while q:
                i = q.popleft()
                for j in g[i]:
                    if dist[j] == inf:
                        ans = dist[j] = dist[i] + 1
                        q.append(j)
            for i in arr:
                if dist[i] == inf:
                    ans += 1
                    dist[i] = ans
            for i in arr:
                for j in g[i]:
                    if abs(dist[i] - dist[j]) != 1:
                        return -1
            return ans

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        vis = [False] * (n + 1)
        ans = 0
        for i in range(1, n + 1):
            if not vis[i]:
                arr = []
                dfs(i)
                t = max(bfs(v) for v in arr)
                if t == -1:
                    return -1
                ans += t
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<Integer>[] g;
    private List<Integer> arr = new ArrayList<>();
    private boolean[] vis;
    private int n;

    public int magnificentSets(int n, int[][] edges) {
        g = new List[n + 1];
        this.n = n;
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }

        vis = new boolean[n + 1];
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            if (!vis[i]) {
                dfs(i);
                int t = -1;
                for (int v : arr) {
                    t = Math.max(t, bfs(v));
                }
                if (t == -1) {
                    return -1;
                }
                ans += t;
                arr.clear();
            }
        }
        return ans;
    }

    private int bfs(int k) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 1 << 30);
        dist[k] = 1;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(k);
        int ans = 1;
        while (!q.isEmpty()) {
            int i = q.pollFirst();
            for (int j : g[i]) {
                if (dist[j] == 1 << 30) {
                    dist[j] = dist[i] + 1;
                    ans = dist[j];
                    q.offer(j);
                }
            }
        }
        for (int i : arr) {
            if (dist[i] == 1 << 30) {
                dist[i] = ++ans;
            }
        }
        for (int i : arr) {
            for (int j : g[i]) {
                if (Math.abs(dist[i] - dist[j]) != 1) {
                    return -1;
                }
            }
        }
        return ans;
    }

    private void dfs(int i) {
        arr.add(i);
        vis[i] = true;
        for (int j : g[i]) {
            if (!vis[j]) {
                dfs(j);
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int magnificentSets(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n + 1);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        vector<int> arr;
        bool vis[n + 1];
        memset(vis, 0, sizeof vis);
        int ans = 0;
        function<void(int)> dfs = [&](int i) {
            arr.emplace_back(i);
            vis[i] = true;
            for (int& j : g[i]) {
                if (!vis[j]) {
                    dfs(j);
                }
            }
        };
        auto bfs = [&](int k) {
            int ans = 1;
            int dist[n + 1];
            memset(dist, 0x3f, sizeof dist);
            dist[k] = 1;
            queue<int> q{{k}};
            while (!q.empty()) {
                int i = q.front();
                q.pop();
                for (int& j : g[i]) {
                    if (dist[j] == 0x3f3f3f3f) {
                        ans = dist[j] = dist[i] + 1;
                        q.push(j);
                    }
                }
            }
            for (int& i : arr) {
                if (dist[i] == 0x3f3f3f3f) {
                    dist[i] = ++ans;
                }
            }
            for (int& i : arr) {
                for (int& j : g[i]) {
                    if (abs(dist[i] - dist[j]) != 1) {
                        return -1;
                    }
                }
            }
            return ans;
        };
        for (int i = 1; i <= n; ++i) {
            if (!vis[i]) {
                dfs(i);
                int t = -1;
                for (int& v : arr) t = max(t, bfs(v));
                if (t == -1) return -1;
                ans += t;
                arr.clear();
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func magnificentSets(n int, edges [][]int) int {
	g := make([][]int, n+1)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	arr := []int{}
	vis := make([]bool, n+1)
	ans := 0
	var dfs func(int)
	dfs = func(i int) {
		arr = append(arr, i)
		vis[i] = true
		for _, j := range g[i] {
			if !vis[j] {
				dfs(j)
			}
		}
	}
	bfs := func(k int) int {
		ans := 1
		dist := make([]int, n+1)
		for i := range dist {
			dist[i] = 1 << 30
		}
		q := []int{k}
		dist[k] = 1
		for len(q) > 0 {
			i := q[0]
			q = q[1:]
			for _, j := range g[i] {
				if dist[j] == 1<<30 {
					dist[j] = dist[i] + 1
					ans = dist[j]
					q = append(q, j)
				}
			}
		}
		for _, i := range arr {
			if dist[i] == 1<<30 {
				ans++
				dist[i] = ans
			}
		}
		for _, i := range arr {
			for _, j := range g[i] {
				if abs(dist[i]-dist[j]) != 1 {
					return -1
				}
			}
		}
		return ans
	}
	for i := 1; i <= n; i++ {
		if !vis[i] {
			dfs(i)
			t := -1
			for _, v := range arr {
				t = max(t, bfs(v))
			}
			if t == -1 {
				return -1
			}
			ans += t
			arr = []int{}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **JavaScript**

```js
var magnificentSets = function (n, edges) {
    const graph = Array.from({ length: n + 1 }, () => new Set());
    for (const [u, v] of edges) {
        graph[u].add(v);
        graph[v].add(u);
    }
    const hash = new Map();

    // 2. BFS
    for (let i = 1; i <= n; i++) {
        let queue = [i];
        const dis = Array(n + 1).fill(0);
        dis[i] = 1;
        let mx = 1,
            mn = n;
        while (queue.length) {
            let next = [];
            for (let u of queue) {
                mn = Math.min(mn, u);
                for (const v of graph[u]) {
                    if (!dis[v]) {
                        dis[v] = dis[u] + 1;
                        mx = Math.max(mx, dis[v]);
                        next.push(v);
                    }
                    if (Math.abs(dis[u] - dis[v]) != 1) {
                        return -1;
                    }
                }
            }
            queue = next;
        }
        hash.set(mn, Math.max(mx, hash.get(mn) || 0));
    }

    let ans = 0;
    for (const [u, v] of hash) {
        ans += v;
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
