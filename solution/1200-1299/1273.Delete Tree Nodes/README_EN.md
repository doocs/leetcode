---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1273.Delete%20Tree%20Nodes/README_EN.md
rating: 1732
source: Biweekly Contest 14 Q3
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Array
---

<!-- problem:start -->

# [1273. Delete Tree Nodes 🔒](https://leetcode.com/problems/delete-tree-nodes)

[中文文档](/solution/1200-1299/1273.Delete%20Tree%20Nodes/README.md)

## Description

<!-- description:start -->

<p>A tree rooted at node 0 is given as follows:</p>

<ul>
	<li>The number of nodes is <code>nodes</code>;</li>
	<li>The value of the <code>i<sup>th</sup></code> node is <code>value[i]</code>;</li>
	<li>The parent of the <code>i<sup>th</sup></code> node is <code>parent[i]</code>.</li>
</ul>

<p>Remove every subtree whose sum of values of nodes is zero.</p>

<p>Return <em>the number of the remaining nodes in the tree</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1273.Delete%20Tree%20Nodes/images/1421_sample_1.png" style="width: 403px; height: 347px;" />
<pre>
<strong>Input:</strong> nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 2:</strong></p>

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS

First, we convert the tree into a graph $g$, where $g[i]$ represents all the child nodes of node $i$.

Then we design a function $dfs(i)$, which represents the number of nodes and the sum of the weights in the subtree rooted at node $i$. The answer is $dfs(0)[1]$.

In this function, we recursively calculate the number of nodes and the sum of the weights in the subtree rooted at each child node $j$, and then accumulate these values. If the accumulated value is zero, we set the number of nodes in this subtree to zero. Finally, we return the number of nodes and the sum of the weights in the subtree rooted at node $i$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the number of nodes in the tree.

<!-- tabs:start -->

```python
class Solution:
    def deleteTreeNodes(self, nodes: int, parent: List[int], value: List[int]) -> int:
        def dfs(i):
            s, m = value[i], 1
            for j in g[i]:
                t, n = dfs(j)
                s += t
                m += n
            if s == 0:
                m = 0
            return (s, m)

        g = defaultdict(list)
        for i in range(1, nodes):
            g[parent[i]].append(i)
        return dfs(0)[1]
```

```java
class Solution {
    private List<Integer>[] g;
    private int[] value;

    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        g = new List[nodes];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < nodes; ++i) {
            g[parent[i]].add(i);
        }
        this.value = value;
        return dfs(0)[1];
    }

    private int[] dfs(int i) {
        int[] res = new int[] {value[i], 1};
        for (int j : g[i]) {
            int[] t = dfs(j);
            res[0] += t[0];
            res[1] += t[1];
        }
        if (res[0] == 0) {
            res[1] = 0;
        }
        return res;
    }
}
```

```cpp
class Solution {
public:
    int deleteTreeNodes(int nodes, vector<int>& parent, vector<int>& value) {
        vector<vector<int>> g(nodes);
        for (int i = 1; i < nodes; ++i) {
            g[parent[i]].emplace_back(i);
        }
        function<pair<int, int>(int)> dfs = [&](int i) -> pair<int, int> {
            int s = value[i], m = 1;
            for (int j : g[i]) {
                auto [t, n] = dfs(j);
                s += t;
                m += n;
            }
            if (s == 0) {
                m = 0;
            }
            return pair<int, int>{s, m};
        };
        return dfs(0).second;
    }
};
```

```go
func deleteTreeNodes(nodes int, parent []int, value []int) int {
	g := make([][]int, nodes)
	for i := 1; i < nodes; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}
	type pair struct{ s, n int }
	var dfs func(int) pair
	dfs = func(i int) pair {
		s, m := value[i], 1
		for _, j := range g[i] {
			t := dfs(j)
			s += t.s
			m += t.n
		}
		if s == 0 {
			m = 0
		}
		return pair{s, m}
	}
	return dfs(0).n
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
