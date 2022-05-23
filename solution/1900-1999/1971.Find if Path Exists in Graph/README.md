# [1971. 寻找图中是否存在路径](https://leetcode.cn/problems/find-if-path-exists-in-graph)

[English Version](/solution/1900-1999/1971.Find%20if%20Path%20Exists%20in%20Graph/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个具有 <code>n</code>个顶点的 <strong>双向</strong> 图，其中每个顶点标记从 <code>0</code> 到 <code>n - 1</code>（包含 <code>0</code> 和 <code>n - 1</code>）。图中的边用一个二维整数数组 <code>edges</code> 表示，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示顶点 <code>ui</code> 和顶点 <code>vi</code> 之间的双向边。 每个顶点对由 <strong>最多一条</strong> 边连接，并且没有顶点存在与自身相连的边。</p>

<p>请你确定是否存在从顶点 <code>start</code> 开始，到顶点 <code>end</code> 结束的 <strong>有效路径</strong> 。</p>

<p>给你数组 <code>edges</code> 和整数 <code>n</code>、<code>start</code>和<code>end</code>，如果从 <code>start</code> 到 <code>end</code> 存在 <strong>有效路径</strong> ，则返回 <code>true</code>，否则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1971.Find%20if%20Path%20Exists%20in%20Graph/images/validpath-ex1.png" style="width: 141px; height: 121px;" />
<pre>
<strong>输入：</strong>n = 3, edges = [[0,1],[1,2],[2,0]], start = 0, end = 2
<strong>输出：</strong>true
<strong>解释：</strong>存在由顶点 0 到顶点 2 的路径:
- 0 → 1 → 2 
- 0 → 2
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1971.Find%20if%20Path%20Exists%20in%20Graph/images/validpath-ex2.png" style="width: 281px; height: 141px;" />
<pre>
<strong>输入：</strong>n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], start = 0, end = 5
<strong>输出：</strong>false
<strong>解释：</strong>不存在由顶点 0 到顶点 5 的路径.
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>0 &lt;= start, end &lt;= n - 1</code></li>
	<li>不存在双向边</li>
	<li>不存在指向顶点自身的边</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

**方法二：并查集**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def validPath(self, n: int, edges: List[List[int]], start: int, end: int) -> bool:
        def dfs(u):
            nonlocal ans
            if ans or u in vis:
                return
            vis.add(u)
            if u == end:
                ans = True
                return
            for v in g[u]:
                dfs(v)

        g = defaultdict(list)
        vis = set()
        ans = False
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        dfs(start)
        return ans
```

```python
class Solution:
    def validPath(self, n: int, edges: List[List[int]], source: int, destination: int) -> bool:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(n))
        for u, v in edges:
            p[find(u)] = find(v)
        return find(source) == find(destination)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int[] e : edges) {
            p[find(e[0])] = find(e[1]);
        }
        return find(source) == find(destination);
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    bool validPath(int n, vector<vector<int>>& edges, int source, int destination) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        for (auto& e : edges) p[find(e[0])] = find(e[1]);
        return find(source) == find(destination);
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func validPath(n int, edges [][]int, source int, destination int) bool {
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
	for _, e := range edges {
		p[find(e[0])] = find(e[1])
	}
	return find(source) == find(destination)
}
```

### **...**

```

```

<!-- tabs:end -->
