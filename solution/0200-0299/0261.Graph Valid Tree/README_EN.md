# [261. Graph Valid Tree](https://leetcode.com/problems/graph-valid-tree)

[中文文档](/solution/0200-0299/0261.Graph%20Valid%20Tree/README.md)

## Description

<p>You have a graph of <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>. You are given an integer n and a list of <code>edges</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an undirected edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the graph.</p>

<p>Return <code>true</code> <em>if the edges of the given graph make up a valid tree, and</em> <code>false</code> <em>otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0261.Graph%20Valid%20Tree/images/tree1-graph.jpg" style="width: 222px; height: 302px;" />
<pre>
<strong>Input:</strong> n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
<strong>Output:</strong> true
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0261.Graph%20Valid%20Tree/images/tree2-graph.jpg" style="width: 382px; height: 222px;" />
<pre>
<strong>Input:</strong> n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= 2000 &lt;= n</code></li>
	<li><code>0 &lt;= edges.length &lt;= 5000</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>There are no self-loops or repeated edges.</li>
</ul>

## Solutions

Union find.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for a, b in edges:
            if find(a) == find(b):
                return False
            p[find(a)] = find(b)
            n -= 1
        return n == 1
```

### **Java**

```java
class Solution {
    private int[] p;

    public boolean validTree(int n, int[][] edges) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int[] e : edges) {
            if (find(e[0]) == find(e[1])) {
                return false;
            }
            p[find(e[0])] = find(e[1]);
            --n;
        }
        return n == 1;
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

    bool validTree(int n, vector<vector<int>> &edges) {
        for (int i = 0; i < n; ++i)
        {
            p.push_back(i);
        }
        for (auto e : edges)
        {
            if (find(e[0]) == find(e[1]))
                return false;
            p[find(e[0])] = find(e[1]);
            --n;
        }
        return n == 1;
    }

    int find(int x) {
        if (p[x] != x)
        {
            p[x] = find(p[x]);
        }
        return p[x];
    }
};
```

### **Go**

```go
var p []int

func validTree(n int, edges [][]int) bool {
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	for _, e := range edges {
		if find(e[0]) == find(e[1]) {
			return false
		}
		p[find(e[0])] = find(e[1])
		n--
	}
	return n == 1
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}
```

### **...**

```

```

<!-- tabs:end -->
