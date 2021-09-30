# [1584. Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points)

[中文文档](/solution/1500-1599/1584.Min%20Cost%20to%20Connect%20All%20Points/README.md)

## Description

<p>You are given an array&nbsp;<code>points</code>&nbsp;representing integer coordinates of some points on a 2D-plane, where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>.</p>

<p>The cost of connecting two points <code>[x<sub>i</sub>, y<sub>i</sub>]</code> and <code>[x<sub>j</sub>, y<sub>j</sub>]</code> is the <strong>manhattan distance</strong> between them:&nbsp;<code>|x<sub>i</sub> - x<sub>j</sub>| + |y<sub>i</sub> - y<sub>j</sub>|</code>, where <code>|val|</code> denotes the absolute value of&nbsp;<code>val</code>.</p>

<p>Return&nbsp;<em>the minimum cost to make all points connected.</em> All points are connected if there is <strong>exactly one</strong> simple path between any two points.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1584.Min%20Cost%20to%20Connect%20All%20Points/images/d.png" style="width: 214px; height: 268px;" /></p>

<pre>
<strong>Input:</strong> points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
<strong>Output:</strong> 20
<strong>Explanation:
</strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1584.Min%20Cost%20to%20Connect%20All%20Points/images/c.png" style="width: 214px; height: 268px;" />
We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> points = [[3,12],[-2,5],[-4,1]]
<strong>Output:</strong> 18
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> points = [[0,0],[1,1],[1,0],[-1,1]]
<strong>Output:</strong> 4
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> points = [[-1000000,-1000000],[1000000,1000000]]
<strong>Output:</strong> 4000000
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> points = [[0,0]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 1000</code></li>
	<li><code>-10<sup>6</sup>&nbsp;&lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>6</sup></code></li>
	<li>All pairs <code>(x<sub>i</sub>, y<sub>i</sub>)</code> are distinct.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minCostConnectPoints(self, points: List[List[int]]) -> int:
        n = len(points)
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        edges = []
        for i in range(n):
            x1, y1 = points[i]
            for j in range(i + 1, n):
                x2, y2 = points[j]
                edges.append([abs(x1 - x2) + abs(y1 - y2), i, j])
        edges.sort()
        res = 0
        for cost, i, j in edges:
            if find(i) == find(j):
                continue
            p[find(i)] = find(j)
            n -= 1
            res += cost
            if n == 1:
                return res
        return 0
```

### **Java**

```java
class Solution {
    private int[] p;
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j =  i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                edges.add(new int[]{Math.abs(x1 - x2) + Math.abs(y1 - y2), i, j});
            }
        }
        edges.sort(Comparator.comparingInt(a -> a[0]));
        int res = 0;
        for (int[] e : edges) {
            if (find(e[1]) == find(e[2])) {
                continue;
            }
            p[find(e[1])] = find(e[2]);
            --n;
            res += e[0];
            if (n == 1) {
                break;
            }
        }
        return res;
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

    int minCostConnectPoints(vector<vector<int>>& points) {
        int n = points.size();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        vector<vector<int>> edges;
        for (int i = 0; i < n; ++i)
        {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j)
            {
                int x2 = points[j][0], y2 = points[j][1];
                edges.push_back({abs(x1 - x2) + abs(y1 - y2), i, j});
            }
        }
        sort(edges.begin(), edges.end());
        int res = 0;
        for (auto e : edges)
        {
            if (find(e[1]) == find(e[2])) continue;
            p[find(e[1])] = find(e[2]);
            --n;
            res += e[0];
            if (n == 1) break;
        }
        return res;
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

func minCostConnectPoints(points [][]int) int {
    n := len(points)
    p = make([]int, n)
    for i := 0; i < n; i++ {
        p[i] = i
    }
    var edges [][]int
    for i := 0; i < n; i++ {
        x1, y1 := points[i][0], points[i][1]
        for j := i + 1; j < n; j++ {
            x2, y2 := points[j][0], points[j][1]
            edges = append(edges, []int{abs(x1 - x2) + abs(y1 - y2), i, j})
        }
    }
    sort.Slice(edges, func(i, j int) bool {
        return edges[i][0] < edges[j][0]
    })
    res := 0
    for _, e := range edges {
        if find(e[1]) == find(e[2]) {
            continue
        }
        p[find(e[1])] = find(e[2])
        n--
        res += e[0]
        if n == 1 {
            break
        }
    }
    return res
}

func find(x int) int {
    if p[x] != x {
        p[x] = find(p[x])
    }
    return p[x]
}

func abs(x int) int {
    if x > 0 {
        return x
    }
    return -x
}
```

### **...**

```

```

<!-- tabs:end -->
