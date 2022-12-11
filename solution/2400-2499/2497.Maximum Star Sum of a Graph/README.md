# [2497. 图中最大星和](https://leetcode.cn/problems/maximum-star-sum-of-a-graph)

[English Version](/solution/2400-2499/2497.Maximum%20Star%20Sum%20of%20a%20Graph/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>n</code>&nbsp;个点的无向图，节点从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;编号。给你一个长度为 <code>n</code>&nbsp;下标从&nbsp;<strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>vals</code>&nbsp;，其中&nbsp;<code>vals[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;个节点的值。</p>

<p>同时给你一个二维整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示节点&nbsp;<code>a<sub>i</sub></code> 和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条双向边。</p>

<p><strong>星图</strong>&nbsp;是给定图中的一个子图，它包含一个中心节点和&nbsp;<code>0</code>&nbsp;个或更多个邻居。换言之，星图是给定图中一个边的子集，且这些边都有一个公共节点。</p>

<p>下图分别展示了有 <code>3</code>&nbsp;个和 <code>4</code>&nbsp;个邻居的星图，蓝色节点为中心节点。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2497.Maximum%20Star%20Sum%20of%20a%20Graph/images/max-star-sum-descdrawio.png" style="width: 400px; height: 179px;"></p>

<p><strong>星和</strong> 定义为星图中所有节点值的和。</p>

<p>给你一个整数&nbsp;<code>k</code>&nbsp;，请你返回 <strong>至多</strong>&nbsp;包含 <code>k</code>&nbsp;条边的星图中的 <strong>最大星和</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2497.Maximum%20Star%20Sum%20of%20a%20Graph/images/max-star-sum-example1drawio.png" style="width: 300px; height: 291px;"></p>

<pre><b>输入：</b>vals = [1,2,3,4,10,-10,-20], edges = [[0,1],[1,2],[1,3],[3,4],[3,5],[3,6]], k = 2
<b>输出：</b>16
<b>解释：</b>上图展示了输入示例。
最大星和对应的星图在上图中用蓝色标出。中心节点是 3 ，星图中还包含邻居 1 和 4 。
无法得到一个和大于 16 且边数不超过 2 的星图。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>vals = [-5], edges = [], k = 0
<b>输出：</b>-5
<b>解释：</b>只有一个星图，就是节点 0 自己。
所以我们返回 -5 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == vals.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= vals[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= min(n * (n - 1) / 2</code><code>, 10<sup>5</sup>)</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>0 &lt;= k &lt;= n - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 模拟**

我们先将输入的边集合转换成邻接表，其中 $g[i]$ 表示节点 $i$ 的邻居节点的值列表，且按照值的降序排列。

然后我们遍历每个节点 $i$，计算以 $i$ 为中心节点的星图的最大星和，即 $vals[i] + \sum_{j=0}^{k-1} g[i][j]$，并且更新最大星和。

最后返回最大星和即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$，其中 $n$ 为节点数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxStarSum(self, vals: List[int], edges: List[List[int]], k: int) -> int:
        g = defaultdict(list)
        for a, b in edges:
            if vals[b] > 0:
                g[a].append(vals[b])
            if vals[a] > 0:
                g[b].append(vals[a])
        for bs in g.values():
            bs.sort(reverse=True)
        return max(v + sum(g[i][:k]) for i, v in enumerate(vals))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxStarSum(int[] vals, int[][] edges, int k) {
        int n = vals.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, key -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            if (vals[b] > 0) {
                g[a].add(vals[b]);
            }
            if (vals[a] > 0) {
                g[b].add(vals[a]);
            }
        }
        for (var e : g) {
            Collections.sort(e, (a, b) -> b - a);
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            int v = vals[i];
            for (int j = 0; j < Math.min(g[i].size(), k); ++j) {
                v += g[i].get(j);
            }
            ans = Math.max(ans, v);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxStarSum(vector<int>& vals, vector<vector<int>>& edges, int k) {
        int n = vals.size();
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            if (vals[b] > 0) g[a].emplace_back(vals[b]);
            if (vals[a] > 0) g[b].emplace_back(vals[a]);
        }
        for (auto& e : g) sort(e.rbegin(), e.rend());
        int ans = INT_MIN;
        for (int i = 0; i < n; ++i) {
            int v = vals[i];
            for (int j = 0; j < min((int) g[i].size(), k); ++j) v += g[i][j];
            ans = max(ans, v);
        }
        return ans;
    }
};
```

### **Go**

```go
func maxStarSum(vals []int, edges [][]int, k int) (ans int) {
	n := len(vals)
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		if vals[b] > 0 {
			g[a] = append(g[a], vals[b])
		}
		if vals[a] > 0 {
			g[b] = append(g[b], vals[a])
		}
	}
	for _, e := range g {
		sort.Sort(sort.Reverse(sort.IntSlice(e)))
	}
	ans = math.MinInt32
	for i, v := range vals {
		for j := 0; j < min(len(g[i]), k); j++ {
			v += g[i][j]
		}
		ans = max(ans, v)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
