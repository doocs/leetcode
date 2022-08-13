# [1273. Delete Tree Nodes](https://leetcode.com/problems/delete-tree-nodes)

[中文文档](/solution/1200-1299/1273.Delete%20Tree%20Nodes/README.md)

## Description

<p>A tree rooted at node 0 is given as follows:</p>

<ul>
	<li>The number of nodes is <code>nodes</code>;</li>
	<li>The value of the <code>i<sup>th</sup></code> node is <code>value[i]</code>;</li>
	<li>The parent of the <code>i<sup>th</sup></code> node is <code>parent[i]</code>.</li>
</ul>

<p>Remove every subtree whose sum of values of nodes is zero.</p>

<p>Return <em>the number of the remaining nodes in the tree</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1273.Delete%20Tree%20Nodes/images/1421_sample_1.png" style="width: 403px; height: 347px;" />
<pre>
<strong>Input:</strong> nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
<strong>Output:</strong> 2
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-2]
<strong>Output:</strong> 6
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nodes &lt;= 10<sup>4</sup></code></li>
	<li><code>parent.length == nodes</code></li>
	<li><code>0 &lt;= parent[i] &lt;= nodes - 1</code></li>
	<li><code>parent[0] == -1</code> which indicates that <code>0</code> is the root.</li>
	<li><code>value.length == nodes</code></li>
	<li><code>-10<sup>5</sup> &lt;= value[i] &lt;= 10<sup>5</sup></code></li>
	<li>The given input is <strong>guaranteed</strong> to represent a <strong>valid tree</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def deleteTreeNodes(self, nodes: int, parent: List[int], value: List[int]) -> int:
        def dfs(u):
            for v in g[u]:
                dfs(v)
                value[u] += value[v]
                counter[u] += counter[v]
            if value[u] == 0:
                counter[u] = 0

        g = defaultdict(list)
        for i, p in enumerate(parent):
            if p != -1:
                g[p].append(i)
        counter = [1] * nodes
        dfs(0)
        return counter[0]
```

### **Java**

```java
class Solution {
    private Map<Integer, List<Integer>> g;
    private int[] counter;
    private int[] value;

    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        g = new HashMap<>();
        for (int i = 0; i < nodes; ++i) {
            if (parent[i] != -1) {
                g.computeIfAbsent(parent[i], k -> new ArrayList<>()).add(i);
            }
        }
        this.value = value;
        counter = new int[nodes];
        Arrays.fill(counter, 1);
        dfs(0);
        return counter[0];
    }

    private void dfs(int u) {
        for (int v : g.getOrDefault(u, Collections.emptyList())) {
            dfs(v);
            value[u] += value[v];
            counter[u] += counter[v];
        }
        if (value[u] == 0) {
            counter[u] = 0;
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    unordered_map<int, vector<int>> g;
    vector<int> counter;
    vector<int> value;

    int deleteTreeNodes(int nodes, vector<int>& parent, vector<int>& value) {
        for (int i = 0; i < nodes; ++i)
            if (parent[i] != -1)
                g[parent[i]].push_back(i);
        counter.resize(nodes, 1);
        this->value = value;
        dfs(0);
        return counter[0];
    }

    void dfs(int u) {
        for (int v : g[u]) {
            dfs(v);
            value[u] += value[v];
            counter[u] += counter[v];
        }
        if (value[u] == 0) counter[u] = 0;
    }
};
```

### **Go**

```go
func deleteTreeNodes(nodes int, parent []int, value []int) int {
	g := make(map[int][]int)
	for i, p := range parent {
		if p != -1 {
			g[p] = append(g[p], i)
		}
	}
	counter := make([]int, nodes)
	for i := range counter {
		counter[i] = 1
	}
	var dfs func(u int)
	dfs = func(u int) {
		if vs, ok := g[u]; ok {
			for _, v := range vs {
				dfs(v)
				value[u] += value[v]
				counter[u] += counter[v]
			}
		}
		if value[u] == 0 {
			counter[u] = 0
		}
	}
	dfs(0)
	return counter[0]
}
```

### **...**

```

```

<!-- tabs:end -->
