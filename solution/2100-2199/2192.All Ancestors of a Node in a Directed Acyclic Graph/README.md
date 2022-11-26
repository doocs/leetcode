# [2192. 有向无环图中一个节点的所有祖先](https://leetcode.cn/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph)

[English Version](/solution/2100-2199/2192.All%20Ancestors%20of%20a%20Node%20in%20a%20Directed%20Acyclic%20Graph/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getAncestors(self, n: int, edges: List[List[int]]) -> List[List[int]]:
        g = defaultdict(list)
        for u, v in edges:
            g[v].append(u)
        ans = []
        for i in range(n):
            if not g[i]:
                ans.append([])
                continue
            q = deque([i])
            vis = [False] * n
            vis[i] = True
            t = []
            while q:
                for _ in range(len(q)):
                    v = q.popleft()
                    for u in g[v]:
                        if not vis[u]:
                            vis[u] = True
                            q.append(u)
                            t.append(u)
            ans.append(sorted(t))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            g[e[1]].add(e[0]);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            List<Integer> t = new ArrayList<>();
            if (g[i].isEmpty()) {
                ans.add(t);
                continue;
            }
            Deque<Integer> q = new ArrayDeque<>();
            q.offer(i);
            boolean[] vis = new boolean[n];
            vis[i] = true;
            while (!q.isEmpty()) {
                for (int j = q.size(); j > 0; --j) {
                    int v = q.poll();
                    for (int u : g[v]) {
                        if (!vis[u]) {
                            vis[u] = true;
                            q.offer(u);
                            t.add(u);
                        }
                    }
                }
            }
            Collections.sort(t);
            ans.add(t);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> getAncestors(int n, vector<vector<int>>& edges) {
        vector<vector<int>> g(n);
        for (auto& e : edges) g[e[1]].push_back(e[0]);
        vector<vector<int>> ans;
        for (int i = 0; i < n; ++i) {
            vector<int> t;
            if (g[i].empty()) {
                ans.push_back(t);
                continue;
            }
            queue<int> q {{i}};
            vector<bool> vis(n);
            vis[i] = true;
            while (!q.empty()) {
                for (int j = q.size(); j > 0; --j) {
                    int v = q.front();
                    q.pop();
                    for (int u : g[v]) {
                        if (vis[u]) continue;
                        vis[u] = true;
                        q.push(u);
                        t.push_back(u);
                    }
                }
            }
            sort(t.begin(), t.end());
            ans.push_back(t);
        }
        return ans;
    }
};
```

### **Go**

```go
func getAncestors(n int, edges [][]int) [][]int {
	g := make([][]int, n)
	for _, e := range edges {
		g[e[1]] = append(g[e[1]], e[0])
	}
	var ans [][]int
	for i := 0; i < n; i++ {
		var t []int
		if len(g[i]) == 0 {
			ans = append(ans, t)
			continue
		}
		q := []int{i}
		vis := make([]bool, n)
		vis[i] = true
		for len(q) > 0 {
			for j := len(q); j > 0; j-- {
				v := q[0]
				q = q[1:]
				for _, u := range g[v] {
					if !vis[u] {
						vis[u] = true
						q = append(q, u)
						t = append(t, u)
					}
				}
			}
		}
		sort.Ints(t)
		ans = append(ans, t)
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
