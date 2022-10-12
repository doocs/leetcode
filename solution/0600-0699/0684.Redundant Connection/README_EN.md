# [684. Redundant Connection](https://leetcode.com/problems/redundant-connection)

[中文文档](/solution/0600-0699/0684.Redundant%20Connection/README.md)

## Description

<p>In this problem, a tree is an <strong>undirected graph</strong> that is connected and has no cycles.</p>

<p>You are given a graph that started as a tree with <code>n</code> nodes labeled from <code>1</code> to <code>n</code>, with one additional edge added. The added edge has two <strong>different</strong> vertices chosen from <code>1</code> to <code>n</code>, and was not an edge that already existed. The graph is represented as an array <code>edges</code> of length <code>n</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the graph.</p>

<p>Return <em>an edge that can be removed so that the resulting graph is a tree of </em><code>n</code><em> nodes</em>. If there are multiple answers, return the answer that occurs last in the input.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0684.Redundant%20Connection/images/reduntant1-1-graph.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>Input:</strong> edges = [[1,2],[1,3],[2,3]]
<strong>Output:</strong> [2,3]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0684.Redundant%20Connection/images/reduntant1-2-graph.jpg" style="width: 382px; height: 222px;" />
<pre>
<strong>Input:</strong> edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
<strong>Output:</strong> [1,4]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == edges.length</code></li>
	<li><code>3 &lt;= n &lt;= 1000</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 &lt;= a<sub>i</sub> &lt; b<sub>i</sub> &lt;= edges.length</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>There are no repeated edges.</li>
	<li>The given graph is connected.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findRedundantConnection(self, edges: List[List[int]]) -> List[int]:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(1010))
        for a, b in edges:
            if find(a) == find(b):
                return [a, b]
            p[find(a)] = find(b)
        return []
```

### **Java**

```java
class Solution {
    private int[] p;

    public int[] findRedundantConnection(int[][] edges) {
        p = new int[1010];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            if (find(a) == find(b)) {
                return e;
            }
            p[find(a)] = find(b);
        }
        return null;
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

    vector<int> findRedundantConnection(vector<vector<int>>& edges) {
        p.resize(1010);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            if (find(a) == find(b)) return e;
            p[find(a)] = find(b);
        }
        return {};
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func findRedundantConnection(edges [][]int) []int {
	p := make([]int, 1010)
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
		a, b := e[0], e[1]
		if find(a) == find(b) {
			return e
		}
		p[find(a)] = find(b)
	}
	return []int{}
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} edges
 * @return {number[]}
 */
var findRedundantConnection = function (edges) {
    let p = Array.from({ length: 1010 }, (_, i) => i);
    function find(x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
    for (let [a, b] of edges) {
        if (find(a) == find(b)) {
            return [a, b];
        }
        p[find(a)] = find(b);
    }
    return [];
};
```

### **...**

```

```

<!-- tabs:end -->
