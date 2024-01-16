# [2204. 无向图中到环的距离](https://leetcode.cn/problems/distance-to-a-cycle-in-undirected-graph)

[English Version](/solution/2200-2299/2204.Distance%20to%20a%20Cycle%20in%20Undirected%20Graph/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个正整数 <code>n</code>，表示一个 <strong>连通无向图</strong> 中的节点数，该图&nbsp;<strong>只包含一个&nbsp;</strong>环。节点编号为 <code>0</code> ~ <code>n - 1</code>(<strong>含</strong>)。</p>

<p>你还得到了一个二维整数数组 <code>edges</code>，其中 <code>edges[i] = [node1<sub>i</sub>, node2<sub>i</sub>]</code> 表示有一条&nbsp;<strong>双向&nbsp;</strong>边连接图中的 <code>node1<sub>i</sub></code> 和 <code>node2<sub>i</sub></code>。</p>

<p>两个节点 <code>a</code> 和 <code>b</code> 之间的距离定义为从 <code>a</code> 到 <code>b</code> 所需的&nbsp;<strong>最小边数</strong>。</p>

<p>返回<em>一个长度为 <code>n</code> 的整数数组 <code>answer</code>，其中 </em><code>answer[i]</code><em> 是第 <code>i</code> 个节点与环中任何节点之间的最小距离</em>。</p>

<p><strong class="example">示例 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2204.Distance%20to%20a%20Cycle%20in%20Undirected%20Graph/images/image-20220315154238-1.png" style="width: 350px; height: 237px;" />
<pre>
<strong>输入:</strong> n = 7, edges = [[1,2],[2,4],[4,3],[3,1],[0,1],[5,2],[6,5]]
<strong>输出:</strong> [1,0,0,0,0,1,2]
<strong>解释:</strong>
节点 1, 2, 3, 和 4 来自环。
0 到 1 的距离是 1。
1 到 1 的距离是 0。
2 到 2 的距离是 0。
3 到 3 的距离是 0。
4 到 4 的距离是 0。
5 到 2 的距离是 1。
6 到 2 的距离是 2。
</pre>

<p><strong class="example">示例 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2204.Distance%20to%20a%20Cycle%20in%20Undirected%20Graph/images/image-20220315154634-1.png" style="width: 400px; height: 297px;" />
<pre>
<strong>输入:</strong> n = 9, edges = [[0,1],[1,2],[0,2],[2,6],[6,7],[6,8],[0,3],[3,4],[3,5]]
<strong>输出:</strong> [0,0,0,1,2,2,1,2,2]
<strong>解释:</strong>
节点 0, 1, 和 2 来自环.
0 到 0 的距离是 0。
1 到 1 的距离是 0。
2 到 2 的距离是 0。
3 到 1 的距离是 1。
4 到 1 的距离是 2。
5 到 1 的距离是 2。
6 到 2 的距离是 1。
7 到 2 的距离是 2。
8 到 2 的距离是 2。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= node1<sub>i</sub>, node2<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>node1<sub>i</sub> != node2<sub>i</sub></code></li>
	<li>图是连通的。</li>
	<li>这个图只有一个环。</li>
	<li>任何顶点对之间最多只有一条边。</li>
</ul>

## 解法

### 方法一：拓扑排序

我们可以先将 $edges$ 中的边转换成邻接表 $g$，其中 $g[i]$ 表示节点 $i$ 的所有邻接节点，用集合表示。

接下来，我们由外向内，逐层删除节点，直到最终只剩下一个环。具体做法如下：

我们先找出所有度为 $1$ 的节点，将这些节点从图中删除，如果删除后，其邻接节点的度变为 $1$，则将其加入队列 $q$ 中。过程中，我们按顺序记录下所有被删除的节点，记为 $seq$；并且，我们用一个数组 $f$ 记录每个节点相邻的且更接近环的节点，即 $f[i]$ 表示节点 $i$ 的相邻且更接近环的节点。

最后，我们初始化一个长度为 $n$ 的答案数组 $ans$，其中 $ans[i]$ 表示节点 $i$ 到环中任意节点的最小距离，初始时 $ans[i] = 0$。然后，我们从 $seq$ 的末尾开始遍历，对于每个节点 $i$，我们可以由它的相邻节点 $f[i]$ 得到 $ans[i]$ 的值，即 $ans[i] = ans[f[i]] + 1$。

遍历结束后，返回答案数组 $ans$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点数。

相似题目：

-   [2603. 收集树中金币](/solution/2600-2699/2603.Collect%20Coins%20in%20a%20Tree/README.md)

<!-- tabs:start -->

```python
class Solution:
    def distanceToCycle(self, n: int, edges: List[List[int]]) -> List[int]:
        g = defaultdict(set)
        for a, b in edges:
            g[a].add(b)
            g[b].add(a)
        q = deque(i for i in range(n) if len(g[i]) == 1)
        f = [0] * n
        seq = []
        while q:
            i = q.popleft()
            seq.append(i)
            for j in g[i]:
                g[j].remove(i)
                f[i] = j
                if len(g[j]) == 1:
                    q.append(j)
            g[i].clear()
        ans = [0] * n
        for i in seq[::-1]:
            ans[i] = ans[f[i]] + 1
        return ans
```

```java
class Solution {
    public int[] distanceToCycle(int n, int[][] edges) {
        Set<Integer>[] g = new Set[n];
        Arrays.setAll(g, k -> new HashSet<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (g[i].size() == 1) {
                q.offer(i);
            }
        }
        int[] f = new int[n];
        Deque<Integer> seq = new ArrayDeque<>();
        while (!q.isEmpty()) {
            int i = q.poll();
            seq.push(i);
            for (int j : g[i]) {
                g[j].remove(i);
                f[i] = j;
                if (g[j].size() == 1) {
                    q.offer(j);
                }
            }
        }
        int[] ans = new int[n];
        while (!seq.isEmpty()) {
            int i = seq.pop();
            ans[i] = ans[f[i]] + 1;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> distanceToCycle(int n, vector<vector<int>>& edges) {
        unordered_set<int> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].insert(b);
            g[b].insert(a);
        }
        queue<int> q;
        for (int i = 0; i < n; ++i) {
            if (g[i].size() == 1) {
                q.push(i);
            }
        }
        int f[n];
        int seq[n];
        int k = 0;
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            seq[k++] = i;
            for (int j : g[i]) {
                g[j].erase(i);
                f[i] = j;
                if (g[j].size() == 1) {
                    q.push(j);
                }
            }
            g[i].clear();
        }
        vector<int> ans(n);
        for (; k; --k) {
            int i = seq[k - 1];
            ans[i] = ans[f[i]] + 1;
        }
        return ans;
    }
};
```

```go
func distanceToCycle(n int, edges [][]int) []int {
	g := make([]map[int]bool, n)
	for i := range g {
		g[i] = map[int]bool{}
	}
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a][b] = true
		g[b][a] = true
	}
	q := []int{}
	for i := 0; i < n; i++ {
		if len(g[i]) == 1 {
			q = append(q, i)
		}
	}
	f := make([]int, n)
	seq := []int{}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		seq = append(seq, i)
		for j := range g[i] {
			delete(g[j], i)
			f[i] = j
			if len(g[j]) == 1 {
				q = append(q, j)
			}
		}
		g[i] = map[int]bool{}
	}
	ans := make([]int, n)
	for k := len(seq) - 1; k >= 0; k-- {
		i := seq[k]
		ans[i] = ans[f[i]] + 1
	}
	return ans
}
```

```ts
function distanceToCycle(n: number, edges: number[][]): number[] {
    const g: Set<number>[] = new Array(n).fill(0).map(() => new Set<number>());
    for (const [a, b] of edges) {
        g[a].add(b);
        g[b].add(a);
    }
    const q: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (g[i].size === 1) {
            q.push(i);
        }
    }
    const f: number[] = Array(n).fill(0);
    const seq: number[] = [];
    while (q.length) {
        const i = q.pop()!;
        seq.push(i);
        for (const j of g[i]) {
            g[j].delete(i);
            f[i] = j;
            if (g[j].size === 1) {
                q.push(j);
            }
        }
        g[i].clear();
    }
    const ans: number[] = Array(n).fill(0);
    while (seq.length) {
        const i = seq.pop()!;
        ans[i] = ans[f[i]] + 1;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
