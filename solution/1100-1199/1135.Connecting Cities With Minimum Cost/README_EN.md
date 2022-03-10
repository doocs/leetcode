# [1135. Connecting Cities With Minimum Cost](https://leetcode.com/problems/connecting-cities-with-minimum-cost)

[中文文档](/solution/1100-1199/1135.Connecting%20Cities%20With%20Minimum%20Cost/README.md)

## Description

<p>There are <code>n</code> cities labeled from <code>1</code> to <code>n</code>. You are given the integer <code>n</code> and an array <code>connections</code> where <code>connections[i] = [x<sub>i</sub>, y<sub>i</sub>, cost<sub>i</sub>]</code> indicates that the cost of connecting city <code>x<sub>i</sub></code> and city <code>y<sub>i</sub></code> (bidirectional connection) is <code>cost<sub>i</sub></code>.</p>

<p>Return <em>the minimum <strong>cost</strong> to connect all the </em><code>n</code><em> cities such that there is at least one path between each pair of cities</em>. If it is impossible to connect all the <code>n</code> cities, return <code>-1</code>,</p>

<p>The <strong>cost</strong> is the sum of the connections&#39; costs used.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1135.Connecting%20Cities%20With%20Minimum%20Cost/images/1314_ex2.png" style="width: 161px; height: 141px;" />
<pre>
<strong>Input:</strong> n = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
<strong>Output:</strong> 6
<strong>Explanation:</strong> Choosing any 2 edges will connect all cities so we choose the minimum 2.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1135.Connecting%20Cities%20With%20Minimum%20Cost/images/1314_ex1.png" style="width: 136px; height: 91px;" />
<pre>
<strong>Input:</strong> n = 4, connections = [[1,2,3],[3,4,4]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no way to connect all cities even if all edges are used.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= connections.length &lt;= 10<sup>4</sup></code></li>
	<li><code>connections[i].length == 3</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= n</code></li>
	<li><code>x<sub>i</sub> != y<sub>i</sub></code></li>
	<li><code>0 &lt;= cost<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumCost(self, n: int, connections: List[List[int]]) -> int:
        p = list(range(n))
        connections.sort(key=lambda x: x[2])
        res = 0

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a - 1), find(b - 1)
            if pa == pb:
                return False
            p[pa] = pb
            return True

        for c1, c2, cost in connections:
            if union(c1, c2):
                n -= 1
                res += cost
                if n == 1:
                    return res
        return -1
```

### **Java**

```java
class Solution {
    private int[] p;

    public int minimumCost(int n, int[][] connections) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);
        int res = 0;
        for (int[] e : connections) {
            if (union(e[0], e[1])) {
                res += e[2];
                --n;
                if (n == 1) {
                    return res;
                }
            }
        }
        return -1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private boolean union(int a, int b) {
        int pa = find(a - 1), pb = find(b - 1);
        if (pa == pb) {
            return false;
        }
        p[pa] = pb;
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    int minimumCost(int n, vector<vector<int>> &connections) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        auto cmp = [](auto &a, auto &b)
        {
            return a[2] < b[2];
        };
        sort(connections.begin(), connections.end(), cmp);
        int res = 0;
        for (auto e : connections)
        {
            if (unite(e[0], e[1]))
            {
                res += e[2];
                --n;
                if (n == 1) return res;
            }
        }
        return -1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    bool unite(int a, int b) {
        int pa = find(a - 1), pb = find(b - 1);
        if (pa == pb) return false;
        p[pa] = pb;
        return true;
    }
};
```

### **Go**

```go
var p []int

func minimumCost(n int, connections [][]int) int {
	p = make([]int, n)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	sort.Slice(connections, func(i, j int) bool {
		return connections[i][2] < connections[j][2]
	})
	res := 0
	for _, e := range connections {
		if union(e[0], e[1]) {
			res += e[2]
			n--
			if n == 1 {
				return res
			}
		}
	}
	return -1
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}

func union(a, b int) bool {
	pa, pb := find(a-1), find(b-1)
	if pa == pb {
		return false
	}
	p[pa] = pb
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
