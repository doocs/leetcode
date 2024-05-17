---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2192.All%20Ancestors%20of%20a%20Node%20in%20a%20Directed%20Acyclic%20Graph/README.md
rating: 1787
source: 第 73 场双周赛 Q3
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 拓扑排序
---

<!-- problem:start -->

# [2192. 有向无环图中一个节点的所有祖先](https://leetcode.cn/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph)

[English Version](/solution/2100-2199/2192.All%20Ancestors%20of%20a%20Node%20in%20a%20Directed%20Acyclic%20Graph/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数&nbsp;<code>n</code>&nbsp;，它表示一个 <strong>有向无环图</strong>&nbsp;中节点的数目，节点编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;（包括两者）。</p>

<p>给你一个二维整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [from<sub>i</sub>, to<sub>i</sub>]</code>&nbsp;表示图中一条从&nbsp;<code>from<sub>i</sub></code>&nbsp;到&nbsp;<code>to<sub>i</sub></code>&nbsp;的单向边。</p>

<p>请你返回一个数组&nbsp;<code>answer</code>，其中<em>&nbsp;</em><code>answer[i]</code>是第&nbsp;<code>i</code>&nbsp;个节点的所有&nbsp;<strong>祖先</strong>&nbsp;，这些祖先节点&nbsp;<strong>升序</strong>&nbsp;排序。</p>

<p>如果 <code>u</code>&nbsp;通过一系列边，能够到达 <code>v</code>&nbsp;，那么我们称节点 <code>u</code>&nbsp;是节点 <code>v</code>&nbsp;的 <strong>祖先</strong>&nbsp;节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2192.All%20Ancestors%20of%20a%20Node%20in%20a%20Directed%20Acyclic%20Graph/images/e1.png" style="width: 322px; height: 265px;"></p>

<pre><b>输入：</b>n = 8, edgeList = [[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]
<b>输出：</b>[[],[],[],[0,1],[0,2],[0,1,3],[0,1,2,3,4],[0,1,2,3]]
<strong>解释：</strong>
上图为输入所对应的图。
- 节点 0 ，1 和 2 没有任何祖先。
- 节点 3 有 2 个祖先 0 和 1 。
- 节点 4 有 2 个祖先 0 和 2 。
- 节点 5 有 3 个祖先 0 ，1 和 3 。
- 节点 6 有 5 个祖先 0 ，1 ，2 ，3 和 4 。
- 节点 7 有 4 个祖先 0 ，1 ，2 和 3 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2192.All%20Ancestors%20of%20a%20Node%20in%20a%20Directed%20Acyclic%20Graph/images/e2.png" style="width: 343px; height: 299px;"></p>

<pre><b>输入：</b>n = 5, edgeList = [[0,1],[0,2],[0,3],[0,4],[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
<b>输出：</b>[[],[0],[0,1],[0,1,2],[0,1,2,3]]
<strong>解释：</strong>
上图为输入所对应的图。
- 节点 0 没有任何祖先。
- 节点 1 有 1 个祖先 0 。
- 节点 2 有 2 个祖先 0 和 1 。
- 节点 3 有 3 个祖先 0 ，1 和 2 。
- 节点 4 有 4 个祖先 0 ，1 ，2 和 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= edges.length &lt;= min(2000, n * (n - 1) / 2)</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= from<sub>i</sub>, to<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>from<sub>i</sub> != to<sub>i</sub></code></li>
	<li>图中不会有重边。</li>
	<li>图是 <strong>有向</strong> 且 <strong>无环</strong> 的。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

我们先根据二维数组 $edges$ 构建邻接表 $g$，其中 $g[i]$ 表示节点 $i$ 的所有后继节点。

然后我们从小到大枚举节点 $i$ 作为祖先节点，使用 BFS 搜索节点 $i$ 的所有后继节点，把节点 $i$ 加入这些后继节点的祖先列表中。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是节点数。

<!-- tabs:start -->

```python
class Solution:
    def getAncestors(self, n: int, edges: List[List[int]]) -> List[List[int]]:
        def bfs(s: int):
            q = deque([s])
            vis = {s}
            while q:
                i = q.popleft()
                for j in g[i]:
                    if j not in vis:
                        vis.add(j)
                        q.append(j)
                        ans[j].append(s)

        g = defaultdict(list)
        for u, v in edges:
            g[u].append(v)
        ans = [[] for _ in range(n)]
        for i in range(n):
            bfs(i)
        return ans
```

```java
class Solution {
    private int n;
    private List<Integer>[] g;
    private List<List<Integer>> ans;

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        g = new List[n];
        this.n = n;
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : edges) {
            g[e[0]].add(e[1]);
        }
        ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            ans.add(new ArrayList<>());
        }
        for (int i = 0; i < n; ++i) {
            bfs(i);
        }
        return ans;
    }

    private void bfs(int s) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(s);
        boolean[] vis = new boolean[n];
        vis[s] = true;
        while (!q.isEmpty()) {
            int i = q.poll();
            for (int j : g[i]) {
                if (!vis[j]) {
                    vis[j] = true;
                    q.offer(j);
                    ans.get(j).add(s);
                }
            }
        }
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> getAncestors(int n, vector<vector<int>>& edges) {
        vector<int> g[n];
        for (auto& e : edges) {
            g[e[0]].push_back(e[1]);
        }
        vector<vector<int>> ans(n);
        auto bfs = [&](int s) {
            queue<int> q;
            q.push(s);
            bool vis[n];
            memset(vis, 0, sizeof(vis));
            vis[s] = true;
            while (q.size()) {
                int i = q.front();
                q.pop();
                for (int j : g[i]) {
                    if (!vis[j]) {
                        vis[j] = true;
                        ans[j].push_back(s);
                        q.push(j);
                    }
                }
            }
        };
        for (int i = 0; i < n; ++i) {
            bfs(i);
        }
        return ans;
    }
};
```

```go
func getAncestors(n int, edges [][]int) [][]int {
	g := make([][]int, n)
	for _, e := range edges {
		g[e[0]] = append(g[e[0]], e[1])
	}
	ans := make([][]int, n)
	bfs := func(s int) {
		q := []int{s}
		vis := make([]bool, n)
		vis[s] = true
		for len(q) > 0 {
			i := q[0]
			q = q[1:]
			for _, j := range g[i] {
				if !vis[j] {
					vis[j] = true
					q = append(q, j)
					ans[j] = append(ans[j], s)
				}
			}
		}
	}
	for i := 0; i < n; i++ {
		bfs(i)
	}
	return ans
}
```

```ts
function getAncestors(n: number, edges: number[][]): number[][] {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
    }
    const ans: number[][] = Array.from({ length: n }, () => []);
    const bfs = (s: number) => {
        const q: number[] = [s];
        const vis: boolean[] = Array.from({ length: n }, () => false);
        vis[s] = true;
        while (q.length) {
            const i = q.pop()!;
            for (const j of g[i]) {
                if (!vis[j]) {
                    vis[j] = true;
                    ans[j].push(s);
                    q.push(j);
                }
            }
        }
    };
    for (let i = 0; i < n; ++i) {
        bfs(i);
    }
    return ans;
}
```

```cs
public class Solution {
    private int n;
    private List<int>[] g;
    private IList<IList<int>> ans;

    public IList<IList<int>> GetAncestors(int n, int[][] edges) {
        g = new List<int>[n];
        this.n = n;
        for (int i = 0; i < n; i++) {
            g[i] = new List<int>();
        }
        foreach (var e in edges) {
            g[e[0]].Add(e[1]);
        }
        ans = new List<IList<int>>();
        for (int i = 0; i < n; ++i) {
            ans.Add(new List<int>());
        }
        for (int i = 0; i < n; ++i) {
            BFS(i);
        }
        return ans;
    }

    private void BFS(int s) {
        Queue<int> q = new Queue<int>();
        q.Enqueue(s);
        bool[] vis = new bool[n];
        vis[s] = true;
        while (q.Count > 0) {
            int i = q.Dequeue();
            foreach (int j in g[i]) {
                if (!vis[j]) {
                    vis[j] = true;
                    q.Enqueue(j);
                    ans[j].Add(s);
                }
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
