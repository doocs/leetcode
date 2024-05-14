# [2493. 将节点分成尽可能多的组](https://leetcode.cn/problems/divide-nodes-into-the-maximum-number-of-groups)

[English Version](/solution/2400-2499/2493.Divide%20Nodes%20Into%20the%20Maximum%20Number%20of%20Groups/README_EN.md)

<!-- tags:广度优先搜索,并查集,图 -->

<!-- difficulty:困难 -->

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

### 方法一：BFS + 枚举

由于题目给定的图可能是不连通的，所以我们需要对每个连通分量进行处理，找出每个连通分量的最大分组数，累加得到最终结果。

我们可以枚举每一个点作为第一组的节点，然后使用 BFS 遍历整个连通分量，用一个数组 $d$ 记录每个连通分量的最大分组数。在代码实现上，我们使用连通分量中的最小节点作为该连通分量的根节点。

在 BFS 的过程中，我们使用一个队列 $q$ 存储当前遍历到的节点，用一个数组 $dist$ 记录每个节点到起始节点的距离，用一个变量 $mx$ 记录当前连通分量的最大深度，用一个变量 $root$ 记录当前连通分量的根节点。

在遍历过程中，如果发现某个节点 $b$ 的 $dist[b]$ 为 $0$，说明 $b$ 还没有被遍历到，我们将 $b$ 的距离设为 $dist[a] + 1$，更新 $mx$，并将 $b$ 加入队列 $q$ 中。如果 $b$ 的距离已经被更新过，我们检查 $b$ 和 $a$ 之间的距离是否为 $1$，如果不是，说明无法满足题目要求，直接返回 $-1$。

时间复杂度 $O(n \times (n + m))$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别为节点数和边数。

<!-- tabs:start -->

```python
class Solution:
    def magnificentSets(self, n: int, edges: List[List[int]]) -> int:
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a - 1].append(b - 1)
            g[b - 1].append(a - 1)
        d = defaultdict(int)
        for i in range(n):
            q = deque([i])
            dist = [0] * n
            dist[i] = mx = 1
            root = i
            while q:
                a = q.popleft()
                root = min(root, a)
                for b in g[a]:
                    if dist[b] == 0:
                        dist[b] = dist[a] + 1
                        mx = max(mx, dist[b])
                        q.append(b)
                    elif abs(dist[b] - dist[a]) != 1:
                        return -1
            d[root] = max(d[root], mx)
        return sum(d.values())
```

```java
class Solution {
    public int magnificentSets(int n, int[][] edges) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].add(b);
            g[b].add(a);
        }
        int[] d = new int[n];
        int[] dist = new int[n];
        for (int i = 0; i < n; ++i) {
            Deque<Integer> q = new ArrayDeque<>();
            q.offer(i);
            Arrays.fill(dist, 0);
            dist[i] = 1;
            int mx = 1;
            int root = i;
            while (!q.isEmpty()) {
                int a = q.poll();
                root = Math.min(root, a);
                for (int b : g[a]) {
                    if (dist[b] == 0) {
                        dist[b] = dist[a] + 1;
                        mx = Math.max(mx, dist[b]);
                        q.offer(b);
                    } else if (Math.abs(dist[b] - dist[a]) != 1) {
                        return -1;
                    }
                }
            }
            d[root] = Math.max(d[root], mx);
        }
        return Arrays.stream(d).sum();
    }
}
```

```cpp
class Solution {
public:
    int magnificentSets(int n, vector<vector<int>>& edges) {
        vector<int> g[n];
        for (auto& e : edges) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<int> d(n);
        for (int i = 0; i < n; ++i) {
            queue<int> q{{i}};
            vector<int> dist(n);
            dist[i] = 1;
            int mx = 1;
            int root = i;
            while (q.size()) {
                int a = q.front();
                q.pop();
                root = min(root, a);
                for (int b : g[a]) {
                    if (dist[b] == 0) {
                        dist[b] = dist[a] + 1;
                        mx = max(mx, dist[b]);
                        q.push(b);
                    } else if (abs(dist[b] - dist[a]) != 1) {
                        return -1;
                    }
                }
            }
            d[root] = max(d[root], mx);
        }
        return accumulate(d.begin(), d.end(), 0);
    }
};
```

```go
func magnificentSets(n int, edges [][]int) (ans int) {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0]-1, e[1]-1
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	d := make([]int, n)
	for i := range d {
		q := []int{i}
		dist := make([]int, n)
		dist[i] = 1
		mx := 1
		root := i
		for len(q) > 0 {
			a := q[0]
			q = q[1:]
			root = min(root, a)
			for _, b := range g[a] {
				if dist[b] == 0 {
					dist[b] = dist[a] + 1
					mx = max(mx, dist[b])
					q = append(q, b)
				} else if abs(dist[b]-dist[a]) != 1 {
					return -1
				}
			}
		}
		d[root] = max(d[root], mx)
	}
	for _, x := range d {
		ans += x
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```js
/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {number}
 */
var magnificentSets = function (n, edges) {
    const g = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a - 1].push(b - 1);
        g[b - 1].push(a - 1);
    }
    const d = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        const q = [i];
        const dist = Array(n).fill(0);
        dist[i] = 1;
        let mx = 1;
        let root = i;
        while (q.length) {
            const a = q.shift();
            root = Math.min(root, a);
            for (const b of g[a]) {
                if (dist[b] === 0) {
                    dist[b] = dist[a] + 1;
                    mx = Math.max(mx, dist[b]);
                    q.push(b);
                } else if (Math.abs(dist[b] - dist[a]) !== 1) {
                    return -1;
                }
            }
        }
        d[root] = Math.max(d[root], mx);
    }
    return d.reduce((a, b) => a + b);
};
```

<!-- tabs:end -->

<!-- end -->
