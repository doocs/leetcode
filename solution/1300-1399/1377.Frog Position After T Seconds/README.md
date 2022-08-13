# [1377. T 秒后青蛙的位置](https://leetcode.cn/problems/frog-position-after-t-seconds)

[English Version](/solution/1300-1399/1377.Frog%20Position%20After%20T%20Seconds/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 <code>n</code>。青蛙从 <strong>顶点 1</strong> 开始起跳。规则如下：</p>

<ul>
	<li>在一秒内，青蛙从它所在的当前顶点跳到另一个 <strong>未访问</strong> 过的顶点（如果它们直接相连）。</li>
	<li>青蛙无法跳回已经访问过的顶点。</li>
	<li>如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。</li>
	<li>如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。</li>
</ul>

<p>无向树的边用数组 <code>edges</code> 描述，其中 <code>edges[i] = [from<sub>i</sub>, to<sub>i</sub>]</code> 意味着存在一条直接连通 <code>from<sub>i</sub></code> 和 <code>to<sub>i</sub></code> 两个顶点的边。</p>

<p>返回青蛙在 <em><code>t</code></em> 秒后位于目标顶点 <em><code>target</code> </em>上的概率。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1377.Frog%20Position%20After%20T%20Seconds/images/frog1.jpg" /></p>

<pre>
<strong>输入：</strong>n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
<strong>输出：</strong>0.16666666666666666 
<strong>解释：</strong>上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，第 <strong>1 秒</strong> 有 1/3 的概率跳到顶点 2 ，然后第 <strong>2 秒</strong> 有 1/2 的概率跳到顶点 4，因此青蛙在 2 秒后位于顶点 4 的概率是 1/3 * 1/2 = 1/6 = 0.16666666666666666 。 
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1377.Frog%20Position%20After%20T%20Seconds/images/frog2.jpg" /></p>

<pre>
<strong>输入：</strong>n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
<strong>输出：</strong>0.3333333333333333
<strong>解释：</strong>上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，有 1/3 = 0.3333333333333333 的概率能够 <strong>1 秒</strong> 后跳到顶点 7 。 
</pre>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub>&nbsp;&lt;= n</code></li>
	<li><code>1 &lt;= t &lt;= 50</code></li>
	<li><code>1 &lt;= target &lt;= n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def frogPosition(
        self, n: int, edges: List[List[int]], t: int, target: int
    ) -> float:
        g = defaultdict(list)
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        q = deque([(1, 1.0)])
        vis = [False] * (n + 1)
        vis[1] = True
        while q and t >= 0:
            for _ in range(len(q)):
                u, p = q.popleft()
                nxt = [v for v in g[u] if not vis[v]]
                if u == target and (not nxt or t == 0):
                    return p
                for v in nxt:
                    vis[v] = True
                    q.append((v, p / len(nxt)))
            t -= 1
        return 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public double frogPosition(int n, int[][] edges, int t, int target) {
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        Deque<Pair<Integer, Double>> q = new ArrayDeque<>();
        q.offer(new Pair<>(1, 1.0));
        boolean[] vis = new boolean[n + 1];
        vis[1] = true;
        while (!q.isEmpty() && t >= 0) {
            for (int k = q.size(); k > 0; --k) {
                Pair<Integer, Double> x = q.poll();
                int u = x.getKey();
                double p = x.getValue();
                List<Integer> nxt = new ArrayList<>();
                for (int v : g[u]) {
                    if (!vis[v]) {
                        nxt.add(v);
                        vis[v] = true;
                    }
                }
                if (u == target && (nxt.isEmpty() || t == 0)) {
                    return p;
                }
                for (int v : nxt) {
                    q.offer(new Pair<>(v, p / nxt.size()));
                }
            }
            --t;
        }
        return 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double frogPosition(int n, vector<vector<int>>& edges, int t, int target) {
        vector<vector<int>> g(n + 1);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
        }
        typedef pair<int, double> pid;
        queue<pid> q;
        q.push({1, 1.0});
        vector<bool> vis(n + 1);
        vis[1] = true;
        while (!q.empty() && t >= 0) {
            for (int k = q.size(); k; --k) {
                auto x = q.front();
                q.pop();
                int u = x.first;
                double p = x.second;
                vector<int> nxt;
                for (int v : g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        nxt.push_back(v);
                    }
                }
                if (u == target && (t == 0 || nxt.empty())) return p;
                for (int v : nxt) q.push({v, p / nxt.size()});
            }
            --t;
        }
        return 0;
    }
};
```

### **Go**

```go
type pid struct {
	x int
	p float64
}

func frogPosition(n int, edges [][]int, t int, target int) float64 {
	g := make([][]int, n+1)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	q := []pid{pid{1, 1.0}}
	vis := make([]bool, n+1)
	vis[1] = true
	for len(q) > 0 && t >= 0 {
		for k := len(q); k > 0; k-- {
			x := q[0]
			q = q[1:]
			u, p := x.x, x.p
			var nxt []int
			for _, v := range g[u] {
				if !vis[v] {
					vis[v] = true
					nxt = append(nxt, v)
				}
			}
			if u == target && (len(nxt) == 0 || t == 0) {
				return p
			}
			for _, v := range nxt {
				q = append(q, pid{v, p / float64(len(nxt))})
			}
		}
		t--
	}
	return 0
}
```

### **...**

```

```

<!-- tabs:end -->
