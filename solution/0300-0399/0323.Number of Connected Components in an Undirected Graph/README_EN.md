# [323. Number of Connected Components in an Undirected Graph](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph)

[中文文档](/solution/0300-0399/0323.Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph/README.md)

## Description

<p>You have a graph of <code>n</code> nodes. You are given an integer <code>n</code> and an array <code>edges</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the graph.</p>

<p>Return <em>the number of connected components in the graph</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0323.Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph/images/conn1-graph.jpg" style="width: 382px; height: 222px;" />
<pre>
<strong>Input:</strong> n = 5, edges = [[0,1],[1,2],[3,4]]
<strong>Output:</strong> 2
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0323.Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph/images/conn2-graph.jpg" style="width: 382px; height: 222px;" />
<pre>
<strong>Input:</strong> n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2000</code></li>
	<li><code>1 &lt;= edges.length &lt;= 5000</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub> &lt;= b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>There are no repeated edges.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countComponents(self, n: int, edges: List[List[int]]) -> int:
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for a, b in edges:
            p[find(a)] = find(b)
        return sum(i == find(i) for i in range(n))
```

### **Java**

```java
class Solution {
    private int[] p;
    
    public int countComponents(int n, int[][] edges) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int[] e : edges) {
            p[find(e[0])] = find(e[1]);
        }

        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (i == find(i)) {
                ++cnt;
            }
        }
        return cnt;
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

    int countComponents(int n, vector<vector<int>>& edges) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        for (auto e : edges) p[find(e[0])] = find(e[1]);
        int cnt = 0;
        for (int i = 0; i < n; ++i)
        {
            if (i == find(i)) ++cnt;
        }
        return cnt;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
var p []int

func countComponents(n int, edges [][]int) int {
	p = make([]int, n)
	for i := 1; i < n; i++ {
		p[i] = i
	}
	for _, e := range edges {
		p[find(e[0])] = find(e[1])
	}
	cnt := 0
	for i := 0; i < n; i++ {
		if i == find(i) {
			cnt++
		}
	}
	return cnt
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
