# [2497. Maximum Star Sum of a Graph](https://leetcode.com/problems/maximum-star-sum-of-a-graph)

[中文文档](/solution/2400-2499/2497.Maximum%20Star%20Sum%20of%20a%20Graph/README.md)

## Description

<p>There is an undirected graph consisting of <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code>. You are given a <strong>0-indexed</strong> integer array <code>vals</code> of length <code>n</code> where <code>vals[i]</code> denotes the value of the <code>i<sup>th</sup></code> node.</p>

<p>You are also given a 2D integer array <code>edges</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> denotes that there exists an <strong>undirected</strong> edge connecting nodes <code>a<sub>i</sub></code> and <code>b<sub>i.</sub></code></p>

<p>A <strong>star graph</strong> is a subgraph of the given graph having a center node containing <code>0</code> or more neighbors. In other words, it is a subset of edges of the given graph such that there exists a common node for all edges.</p>

<p>The image below shows star graphs with <code>3</code> and <code>4</code> neighbors respectively, centered at the blue node.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2497.Maximum%20Star%20Sum%20of%20a%20Graph/images/max-star-sum-descdrawio.png" style="width: 400px; height: 179px;" />
<p>The <strong>star sum</strong> is the sum of the values of all the nodes present in the star graph.</p>

<p>Given an integer <code>k</code>, return <em>the <strong>maximum star sum</strong> of a star graph containing <strong>at most</strong> </em><code>k</code><em> edges.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2497.Maximum%20Star%20Sum%20of%20a%20Graph/images/max-star-sum-example1drawio.png" style="width: 300px; height: 291px;" />
<pre>
<strong>Input:</strong> vals = [1,2,3,4,10,-10,-20], edges = [[0,1],[1,2],[1,3],[3,4],[3,5],[3,6]], k = 2
<strong>Output:</strong> 16
<strong>Explanation:</strong> The above diagram represents the input graph.
The star graph with the maximum star sum is denoted by blue. It is centered at 3 and includes its neighbors 1 and 4.
It can be shown it is not possible to get a star graph with a sum greater than 16.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> vals = [-5], edges = [], k = 0
<strong>Output:</strong> -5
<strong>Explanation:</strong> There is only one possible star graph, which is node 0 itself.
Hence, we return -5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

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

## Solutions

<!-- tabs:start -->

### **Python3**

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
