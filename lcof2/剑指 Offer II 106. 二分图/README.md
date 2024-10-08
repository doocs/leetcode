---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20106.%20%E4%BA%8C%E5%88%86%E5%9B%BE/README.md
---

<!-- problem:start -->

# [剑指 Offer II 106. 二分图](https://leetcode.cn/problems/vEAB3K)

## 题目描述

<!-- description:start -->

<p>存在一个 <strong>无向图</strong> ，图中有 <code>n</code> 个节点。其中每个节点都有一个介于 <code>0</code> 到 <code>n - 1</code> 之间的唯一编号。</p>

<p>给定一个二维数组 <code>graph</code>&nbsp;，表示图，其中 <code>graph[u]</code> 是一个节点数组，由节点 <code>u</code> 的邻接节点组成。形式上，对于&nbsp;<code>graph[u]</code> 中的每个 <code>v</code> ，都存在一条位于节点 <code>u</code> 和节点 <code>v</code> 之间的无向边。该无向图同时具有以下属性：</p>

<ul>
	<li>不存在自环（<code>graph[u]</code> 不包含 <code>u</code>）。</li>
	<li>不存在平行边（<code>graph[u]</code> 不包含重复值）。</li>
	<li>如果 <code>v</code> 在 <code>graph[u]</code> 内，那么 <code>u</code> 也应该在 <code>graph[v]</code> 内（该图是无向图）</li>
	<li>这个图可能不是连通图，也就是说两个节点 <code>u</code> 和 <code>v</code> 之间可能不存在一条连通彼此的路径。</li>
</ul>

<p><strong>二分图</strong> 定义：如果能将一个图的节点集合分割成两个独立的子集 <code>A</code> 和 <code>B</code> ，并使图中的每一条边的两个节点一个来自 <code>A</code> 集合，一个来自 <code>B</code> 集合，就将这个图称为 <strong>二分图</strong> 。</p>

<p>如果图是二分图，返回 <code>true</code><em> </em>；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20106.%20%E4%BA%8C%E5%88%86%E5%9B%BE/images/bi2.jpg" style="width: 222px; height: 222px;" /></p>

<pre>
<strong>输入：</strong>graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
<strong>输出：</strong>false
<strong>解释：</strong><code>不能将节点分割成两个独立的子集，</code>以使每条边都连通一个子集中的一个节点与另一个子集中的一个节点。</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20106.%20%E4%BA%8C%E5%88%86%E5%9B%BE/images/bi1.jpg" style="width: 222px; height: 222px;" /></p>

<pre>
<strong>输入：</strong>graph = [[1,3],[0,2],[1,3],[0,2]]
<strong>输出：</strong>true
<strong>解释：</strong><code>可以将节点分成两组: {0, 2} 和 {1, 3} 。</code></pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>graph.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= graph[u].length &lt; n</code></li>
	<li><code>0 &lt;= graph[u][i] &lt;= n - 1</code></li>
	<li><code>graph[u]</code> 不会包含 <code>u</code></li>
	<li><code>graph[u]</code> 的所有值 <strong>互不相同</strong></li>
	<li>如果 <code>graph[u]</code> 包含 <code>v</code>，那么 <code>graph[v]</code> 也会包含 <code>u</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 785&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/is-graph-bipartite/">https://leetcode.cn/problems/is-graph-bipartite/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        n = len(graph)
        p = list(range(n))
        for u, g in enumerate(graph):
            for v in g:
                if find(u) == find(v):
                    return False
                p[find(v)] = find(g[0])
        return True
```

#### Java

```java
class Solution {
    private int[] p;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int u = 0; u < n; ++u) {
            int[] g = graph[u];
            for (int v : g) {
                if (find(u) == find(v)) {
                    return false;
                }
                p[find(v)] = find(g[0]);
            }
        }
        return true;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> p;

    bool isBipartite(vector<vector<int>>& graph) {
        int n = graph.size();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        for (int u = 0; u < n; ++u) {
            auto& g = graph[u];
            for (int v : g) {
                if (find(u) == find(v)) return 0;
                p[find(v)] = find(g[0]);
            }
        }
        return 1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

#### Go

```go
func isBipartite(graph [][]int) bool {
	n := len(graph)
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for u, g := range graph {
		for _, v := range g {
			if find(u) == find(v) {
				return false
			}
			p[find(v)] = find(g[0])
		}
	}
	return true
}
```

#### Swift

```swift
class Solution {
    private var parent: [Int] = []

    func isBipartite(_ graph: [[Int]]) -> Bool {
        let n = graph.count
        parent = Array(0..<n)

        for u in 0..<n {
            for v in graph[u] {
                if find(u) == find(v) {
                    return false
                }
                parent[find(v)] = find(graph[u][0])
            }
        }
        return true
    }

    private func find(_ x: Int) -> Int {
        if parent[x] != x {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
