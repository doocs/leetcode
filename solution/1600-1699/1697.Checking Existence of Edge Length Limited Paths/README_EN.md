# [1697. Checking Existence of Edge Length Limited Paths](https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths)

[中文文档](/solution/1600-1699/1697.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths/README.md)

## Description

<p>An undirected graph of <code>n</code> nodes is defined by <code>edgeList</code>, where <code>edgeList[i] = [u<sub>i</sub>, v<sub>i</sub>, dis<sub>i</sub>]</code> denotes an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> with distance <code>dis<sub>i</sub></code>. Note that there may be <strong>multiple</strong> edges between two nodes.</p>

<p>Given an array <code>queries</code>, where <code>queries[j] = [p<sub>j</sub>, q<sub>j</sub>, limit<sub>j</sub>]</code>, your task is to determine for each <code>queries[j]</code> whether there is a path between <code>p<sub>j</sub></code> and <code>q<sub>j</sub></code><sub> </sub>such that each edge on the path has a distance <strong>strictly less than</strong> <code>limit<sub>j</sub></code> .</p>

<p>Return <em>a <strong>boolean array</strong> </em><code>answer</code><em>, where </em><code>answer.length == queries.length</code> <em>and the </em><code>j<sup>th</sup></code> <em>value of </em><code>answer</code> <em>is </em><code>true</code><em> if there is a path for </em><code>queries[j]</code><em> is </em><code>true</code><em>, and </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1697.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths/images/h.png" style="width: 267px; height: 262px;" />
<pre>
<strong>Input:</strong> n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
<strong>Output:</strong> [false,true]
<strong>Explanation:</strong> The above figure shows the given graph. Note that there are two overlapping edges between 0 and 1 with distances 2 and 16.
For the first query, between 0 and 1 there is no path where each distance is less than 2, thus we return false for this query.
For the second query, there is a path (0 -&gt; 1 -&gt; 2) of two edges with distances less than 5, thus we return true for this query.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1697.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths/images/q.png" style="width: 390px; height: 358px;" />
<pre>
<strong>Input:</strong> n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
<strong>Output:</strong> [true,false]
<strong>Exaplanation:</strong> The above figure shows the given graph.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= edgeList.length, queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edgeList[i].length == 3</code></li>
	<li><code>queries[j].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub>, p<sub>j</sub>, q<sub>j</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>p<sub>j</sub> != q<sub>j</sub></code></li>
	<li><code>1 &lt;= dis<sub>i</sub>, limit<sub>j</sub> &lt;= 10<sup>9</sup></code></li>
	<li>There may be <strong>multiple</strong> edges between two nodes.</li>
</ul>

## Solutions

Union find.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def distanceLimitedPathsExist(
        self, n: int, edgeList: List[List[int]], queries: List[List[int]]
    ) -> List[bool]:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(n))
        edgeList.sort(key=lambda x: x[2])

        m = len(queries)
        indexes = list(range(m))
        indexes.sort(key=lambda i: queries[i][2])
        ans = [False] * m
        i = 0
        for j in indexes:
            pj, qj, limit = queries[j]
            while i < len(edgeList) and edgeList[i][2] < limit:
                u, v, _ = edgeList[i]
                p[find(u)] = find(v)
                i += 1
            ans[j] = find(pj) == find(qj)
        return ans
```

### **Java**

```java
class Solution {
    private int[] p;

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        int m = queries.length;
        Integer[] indexes = new Integer[m];
        for (int i = 0; i < m; ++i) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, Comparator.comparingInt(i -> queries[i][2]));
        Arrays.sort(edgeList, Comparator.comparingInt(a -> a[2]));
        boolean[] ans = new boolean[m];
        int i = 0;
        for (int j : indexes) {
            int pj = queries[j][0], qj = queries[j][1], limit = queries[j][2];
            while (i < edgeList.length && edgeList[i][2] < limit) {
                int u = edgeList[i][0], v = edgeList[i][1];
                p[find(u)] = find(v);
                ++i;
            }
            ans[j] = find(pj) == find(qj);
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
    vector<int> p;

    vector<bool> distanceLimitedPathsExist(int n, vector<vector<int>>& edgeList, vector<vector<int>>& queries) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        sort(edgeList.begin(), edgeList.end(), [](const auto& e1, const auto& e2) {
            return e1[2] < e2[2];
        });
        int m = queries.size();
        vector<int> indexes(m);
        for (int i = 0; i < m; ++i) indexes[i] = i;
        sort(indexes.begin(), indexes.end(), [&](int i, int j) {
            return queries[i][2] < queries[j][2];
        });

        vector<bool> ans(m, false);
        int i = 0;
        for (int j : indexes) {
            int pj = queries[j][0], qj = queries[j][1], limit = queries[j][2];
            while (i < edgeList.size() && edgeList[i][2] < limit) {
                int u = edgeList[i][0], v = edgeList[i][1];
                p[find(u)] = find(v);
                ++i;
            }
            ans[j] = find(pj) == find(qj);
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func distanceLimitedPathsExist(n int, edgeList [][]int, queries [][]int) []bool {
	p := make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	sort.Slice(edgeList, func(i, j int) bool {
		return edgeList[i][2] < edgeList[j][2]
	})
	m := len(queries)
	indexes := make([]int, m)
	for i := 0; i < m; i++ {
		indexes[i] = i
	}
	sort.Slice(indexes, func(i, j int) bool {
		return queries[indexes[i]][2] < queries[indexes[j]][2]
	})
	ans := make([]bool, m)
	i := 0
	for _, j := range indexes {
		pj, qj, limit := queries[j][0], queries[j][1], queries[j][2]
		for i < len(edgeList) && edgeList[i][2] < limit {
			u, v := edgeList[i][0], edgeList[i][1]
			p[find(u)] = find(v)
			i++
		}
		ans[j] = find(pj) == find(qj)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
