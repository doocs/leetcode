# [2421. Number of Good Paths](https://leetcode.com/problems/number-of-good-paths)

[中文文档](/solution/2400-2499/2421.Number%20of%20Good%20Paths/README.md)

## Description

<p>There is a tree (i.e. a connected, undirected graph with no cycles) consisting of <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code> and exactly <code>n - 1</code> edges.</p>

<p>You are given a <strong>0-indexed</strong> integer array <code>vals</code> of length <code>n</code> where <code>vals[i]</code> denotes the value of the <code>i<sup>th</sup></code> node. You are also given a 2D integer array <code>edges</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> denotes that there exists an <strong>undirected</strong> edge connecting nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>.</p>

<p>A <strong>good path</strong> is a simple path that satisfies the following conditions:</p>

<ol>
	<li>The starting node and the ending node have the <strong>same</strong> value.</li>
	<li>All nodes between the starting node and the ending node have values <strong>less than or equal to</strong> the starting node (i.e. the starting node&#39;s value should be the maximum value along the path).</li>
</ol>

<p>Return <em>the number of distinct good paths</em>.</p>

<p>Note that a path and its reverse are counted as the <strong>same</strong> path. For example, <code>0 -&gt; 1</code> is considered to be the same as <code>1 -&gt; 0</code>. A single node is also considered as a valid path.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2421.Number%20of%20Good%20Paths/images/f9caaac15b383af9115c5586779dec5.png" style="width: 400px; height: 333px;" />
<pre>
<strong>Input:</strong> vals = [1,3,2,1,3], edges = [[0,1],[0,2],[2,3],[2,4]]
<strong>Output:</strong> 6
<strong>Explanation:</strong> There are 5 good paths consisting of a single node.
There is 1 additional good path: 1 -&gt; 0 -&gt; 2 -&gt; 4.
(The reverse path 4 -&gt; 2 -&gt; 0 -&gt; 1 is treated as the same as 1 -&gt; 0 -&gt; 2 -&gt; 4.)
Note that 0 -&gt; 2 -&gt; 3 is not a good path because vals[2] &gt; vals[0].
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2421.Number%20of%20Good%20Paths/images/149d3065ec165a71a1b9aec890776ff.png" style="width: 273px; height: 350px;" />
<pre>
<strong>Input:</strong> vals = [1,1,2,2,3], edges = [[0,1],[1,2],[2,3],[2,4]]
<strong>Output:</strong> 7
<strong>Explanation:</strong> There are 5 good paths consisting of a single node.
There are 2 additional good paths: 0 -&gt; 1 and 2 -&gt; 3.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2421.Number%20of%20Good%20Paths/images/31705e22af3d9c0a557459bc7d1b62d.png" style="width: 100px; height: 88px;" />
<pre>
<strong>Input:</strong> vals = [1], edges = []
<strong>Output:</strong> 1
<strong>Explanation:</strong> The tree consists of only one node, so there is one good path.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == vals.length</code></li>
	<li><code>1 &lt;= n &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= vals[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>edges</code> represents a valid tree.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfGoodPaths(self, vals: List[int], edges: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)

        n = len(vals)
        p = list(range(n))
        size = defaultdict(Counter)
        for i, v in enumerate(vals):
            size[i][v] = 1

        ans = n
        for v, a in sorted(zip(vals, range(n))):
            for b in g[a]:
                if vals[b] > v:
                    continue
                pa, pb = find(a), find(b)
                if pa != pb:
                    ans += size[pa][v] * size[pb][v]
                    p[pa] = pb
                    size[pb][v] += size[pa][v]
        return ans
```

### **Java**

```java
class Solution {
    private int[] p;

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        p = new int[n];
        int[][] arr = new int[n][2];
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        Map<Integer, Map<Integer, Integer>> size = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            arr[i] = new int[] {vals[i], i};
            size.computeIfAbsent(i, k -> new HashMap<>()).put(vals[i], 1);
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        int ans = n;
        for (var e : arr) {
            int v = e[0], a = e[1];
            for (int b : g[a]) {
                if (vals[b] > v) {
                    continue;
                }
                int pa = find(a), pb = find(b);
                if (pa != pb) {
                    ans += size.get(pa).getOrDefault(v, 0) * size.get(pb).getOrDefault(v, 0);
                    p[pa] = pb;
                    size.get(pb).put(
                        v, size.get(pb).getOrDefault(v, 0) + size.get(pa).getOrDefault(v, 0));
                }
            }
        }
        return ans;
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
    int numberOfGoodPaths(vector<int>& vals, vector<vector<int>>& edges) {
        int n = vals.size();
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        function<int(int)> find;
        find = [&](int x) {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        unordered_map<int, unordered_map<int, int>> size;
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {vals[i], i};
            size[i][vals[i]] = 1;
        }
        sort(arr.begin(), arr.end());
        int ans = n;
        for (auto [v, a] : arr) {
            for (int b : g[a]) {
                if (vals[b] > v) {
                    continue;
                }
                int pa = find(a), pb = find(b);
                if (pa != pb) {
                    ans += size[pa][v] * size[pb][v];
                    p[pa] = pb;
                    size[pb][v] += size[pa][v];
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func numberOfGoodPaths(vals []int, edges [][]int) int {
	n := len(vals)
	p := make([]int, n)
	size := map[int]map[int]int{}
	type pair struct{ v, i int }
	arr := make([]pair, n)
	for i, v := range vals {
		p[i] = i
		if size[i] == nil {
			size[i] = map[int]int{}
		}
		size[i][v] = 1
		arr[i] = pair{v, i}
	}

	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}

	sort.Slice(arr, func(i, j int) bool { return arr[i].v < arr[j].v })
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	ans := n
	for _, e := range arr {
		v, a := e.v, e.i
		for _, b := range g[a] {
			if vals[b] > v {
				continue
			}
			pa, pb := find(a), find(b)
			if pa != pb {
				ans += size[pb][v] * size[pa][v]
				p[pa] = pb
				size[pb][v] += size[pa][v]
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
