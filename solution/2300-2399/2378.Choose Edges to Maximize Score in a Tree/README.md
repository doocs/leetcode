# [2378. 选择边来最大化树的得分](https://leetcode.cn/problems/choose-edges-to-maximize-score-in-a-tree)

[English Version](/solution/2300-2399/2378.Choose%20Edges%20to%20Maximize%20Score%20in%20a%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个&nbsp;<strong>加权&nbsp;</strong>树，由 <code>n</code> 个节点组成，从 <code>0</code> 到 <code>n - 1</code>。</p>

<p>该树以节点 0 为&nbsp;<strong>根</strong>，用大小为 <code>n</code> 的二维数组 <code>edges</code> 表示，其中 <code>edges[i] = [par<sub>i</sub>, weight<sub>i</sub>]</code> 表示节点 <code>par<sub>i</sub></code> 是节点 <code>i</code>&nbsp;的&nbsp;<strong>父&nbsp;</strong>节点，它们之间的边的权重等于 <code>weight<sub>i</sub></code>。因为根结点&nbsp;<strong>没有&nbsp;</strong>父结点，所以有 <code>edges[0] = [-1, -1]</code>。</p>

<p>从树中选择一些边，使所选的两条边都不&nbsp;<strong>相邻</strong>，所选边的权值之 <strong>和</strong> 最大。</p>

<p>&nbsp;</p>

<p>返回<em>所选边的&nbsp;<strong>最大&nbsp;</strong>和。</em></p>

<p><strong>注意</strong>:</p>

<ul>
	<li>你可以&nbsp;<strong>不选择&nbsp;</strong>树中的任何边，在这种情况下权值和将为 <code>0</code>。</li>
	<li>如果树中的两条边 <code>Edge<sub>1</sub></code> 和 <code>Edge<sub>2</sub></code> 有一个&nbsp;<strong>公共&nbsp;</strong>节点，它们就是&nbsp;<strong>相邻&nbsp;</strong>的。
	<ul>
		<li>换句话说，如果 <code>Edge<sub>1</sub></code>连接节点 <code>a</code> 和 <code>b</code>, <code>Edge<sub>2</sub></code> 连接节点 <code>b</code> 和 <code>c</code>，它们是相邻的。</li>
	</ul>
	</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2378.Choose%20Edges%20to%20Maximize%20Score%20in%20a%20Tree/images/treedrawio.png" style="width: 271px; height: 221px;" />
<pre>
<strong>输入:</strong> edges = [[-1,-1],[0,5],[0,10],[2,6],[2,4]]
<strong>输出:</strong> 11
<strong>解释:</strong> 上面的图表显示了我们必须选择红色的边。
总分是 5 + 6 = 11.
可以看出，没有更好的分数可以获得。
</pre>

<p><strong class="example">示例 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2378.Choose%20Edges%20to%20Maximize%20Score%20in%20a%20Tree/images/treee1293712983719827.png" style="width: 221px; height: 181px;" />
<pre>
<strong>输入:</strong> edges = [[-1,-1],[0,5],[0,-6],[0,7]]
<strong>输出:</strong> 7
<strong>解释:</strong> 我们选择权值为 7 的边。
注意，我们不能选择一条以上的边，因为所有的边都是彼此相邻的。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == edges.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>par<sub>0</sub> == weight<sub>0</sub> == -1</code></li>
	<li><code>i &gt;= 1</code>&nbsp;时&nbsp;<code>0 &lt;= par<sub>i</sub> &lt;= n - 1</code>&nbsp;。</li>
	<li><code>par<sub>i</sub> != i</code></li>
	<li><code>i &gt;= 1</code>&nbsp;时&nbsp;<code>-10<sup>6</sup> &lt;= weight<sub>i</sub> &lt;= 10<sup>6</sup></code> 。</li>
	<li><code>edges</code> 表示有效的树。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：树形 DP**

我们设计一个函数 $dfs(i)$，表示以节点 $i$ 为根的子树中，选择一些边，使得所选的两条边都不相邻，所选边的权值之和最大。该函数返回了两个值 $(a, b)$，第一个值 $a$ 表示当前节点 $i$ 与其父节点之间的边被选中时，所选边的权值之和；第二个值 $b$ 表示当前节点 $i$ 与其父节点之间的边不被选中时，所选边的权值之和。

我们可以发现，对于当前节点 $i$：

-   如果 $i$ 与父节点的边被选择，则它与子节点的所有边都不能被选择，那么当前节点的 $a$ 值就是其所有子节点的 $b$ 值之和；
-   如果 $i$ 与父节点的边没被选择，那么可以选择它与子节点的最多一条边，那么当前节点的 $b$ 值就是其选中的子节点的 $a$ 值与未选中的子节点的 $b$ 值之和，再加上 $i$ 与选中的子节点之间的边的权值。

我们调用 $dfs(0)$ 函数，返回的第二个值即为答案，即当前根节点不与父节点之间的边被选中时，所选边的权值之和。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxScore(self, edges: List[List[int]]) -> int:
        def dfs(i):
            a = b = t = 0
            for j, w in g[i]:
                x, y = dfs(j)
                a += y
                b += y
                t = max(t, x - y + w)
            b += t
            return a, b

        g = defaultdict(list)
        for i, (p, w) in enumerate(edges[1:], 1):
            g[p].append((i, w))
        return dfs(0)[1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<int[]>[] g;

    public long maxScore(int[][] edges) {
        int n = edges.length;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            int p = edges[i][0], w = edges[i][1];
            g[p].add(new int[] {i, w});
        }
        return dfs(0)[1];
    }

    private long[] dfs(int i) {
        long a = 0, b = 0, t = 0;
        for (int[] nxt : g[i]) {
            int j = nxt[0], w = nxt[1];
            long[] s = dfs(j);
            a += s[1];
            b += s[1];
            t = Math.max(t, s[0] - s[1] + w);
        }
        b += t;
        return new long[] {a, b};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long maxScore(vector<vector<int>>& edges) {
        int n = edges.size();
        vector<vector<pair<int, int>>> g(n);
        for (int i = 1; i < n; ++i) {
            int p = edges[i][0], w = edges[i][1];
            g[p].emplace_back(i, w);
        }
        using ll = long long;
        using pll = pair<ll, ll>;
        function<pll(int)> dfs = [&](int i) -> pll {
            ll a = 0, b = 0, t = 0;
            for (auto& [j, w] : g[i]) {
                auto [x, y] = dfs(j);
                a += y;
                b += y;
                t = max(t, x - y + w);
            }
            b += t;
            return make_pair(a, b);
        };
        return dfs(0).second;
    }
};
```

### **Go**

```go
func maxScore(edges [][]int) int64 {
	n := len(edges)
	g := make([][][2]int, n)
	for i := 1; i < n; i++ {
		p, w := edges[i][0], edges[i][1]
		g[p] = append(g[p], [2]int{i, w})
	}
	var dfs func(int) [2]int
	dfs = func(i int) [2]int {
		var a, b, t int
		for _, e := range g[i] {
			j, w := e[0], e[1]
			s := dfs(j)
			a += s[1]
			b += s[1]
			t = max(t, s[0]-s[1]+w)
		}
		b += t
		return [2]int{a, b}
	}
	return int64(dfs(0)[1])
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
