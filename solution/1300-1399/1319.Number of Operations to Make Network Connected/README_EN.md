# [1319. Number of Operations to Make Network Connected](https://leetcode.com/problems/number-of-operations-to-make-network-connected)

[中文文档](/solution/1300-1399/1319.Number%20of%20Operations%20to%20Make%20Network%20Connected/README.md)

## Description

<p>There are&nbsp;<code>n</code>&nbsp;computers numbered from&nbsp;<code>0</code>&nbsp;to&nbsp;<code>n-1</code>&nbsp;connected by&nbsp;ethernet cables&nbsp;<code>connections</code>&nbsp;forming a network where&nbsp;<code>connections[i] = [a, b]</code>&nbsp;represents a connection between computers&nbsp;<code>a</code>&nbsp;and&nbsp;<code>b</code>. Any computer&nbsp;can reach any other computer directly or indirectly through the network.</p>

<p>Given an initial computer network <code>connections</code>. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected. Return the <em>minimum number of times</em> you need to do this in order to make all the computers connected. If it&#39;s not possible, return -1.&nbsp;</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1319.Number%20of%20Operations%20to%20Make%20Network%20Connected/images/sample_1_1677.png" style="width: 570px; height: 167px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 4, connections = [[0,1],[0,2],[1,2]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> Remove cable between computer 1 and 2 and place between computers 1 and 3.
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1319.Number%20of%20Operations%20to%20Make%20Network%20Connected/images/sample_2_1677.png" style="width: 660px; height: 167px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
<strong>Output:</strong> 2
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There are not enough cables.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li><code>1 &lt;= connections.length &lt;= min(n*(n-1)/2, 10^5)</code></li>
	<li><code>connections[i].length == 2</code></li>
	<li><code>0 &lt;= connections[i][0], connections[i][1]&nbsp;&lt; n</code></li>
	<li><code>connections[i][0] != connections[i][1]</code></li>
	<li>There are no repeated connections.</li>
	<li>No two computers are connected by more than one cable.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def makeConnected(self, n: int, connections: List[List[int]]) -> int:
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        cnt = 0
        for a, b in connections:
            if find(a) == find(b):
                cnt += 1
            else:
                p[find(a)] = find(b)
        total = sum(i == find(i) for i in range(n))
        return -1 if total - 1 > cnt else total - 1
```

### **Java**

```java
class Solution {
    private int[] p;

    public int makeConnected(int n, int[][] connections) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        int cnt = 0;
        for (int[] e : connections) {
            if (find(e[0]) == find(e[1])) {
                ++cnt;
            } else {
                p[find(e[0])] = find(e[1]);
            }
        }
        int total = 0;
        for (int i = 0; i < n; ++i) {
            if (i == find(i)) {
                ++total;
            }
        }
        return total - 1 > cnt ? -1 : total - 1;
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

    int makeConnected(int n, vector<vector<int>> &connections) {
        p.resize(n);
        for (int i = 0; i < n; ++i)
        {
            p[i] = i;
        }
        int cnt = 0;
        for (auto e : connections)
        {
            if (find(e[0]) == find(e[1]))
            {
                ++cnt;
            }
            else
            {
                p[find(e[0])] = find(e[1]);
            }
        }
        int total = 0;
        for (int i = 0; i < n; ++i)
        {
            if (i == find(i))
            {
                ++total;
            }
        }
        return total - 1 > cnt ? -1 : total - 1;
    }

    int find(int x) {
        if (p[x] != x)
            p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
var p []int

func makeConnected(n int, connections [][]int) int {
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	cnt := 0
	for _, e := range connections {
		if find(e[0]) == find(e[1]) {
			cnt++
		} else {
			p[find(e[0])] = find(e[1])
		}
	}
	total := 0
	for i := 0; i < n; i++ {
		if i == find(i) {
			total++
		}
	}
	if total-1 > cnt {
		return -1
	}
	return total - 1
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
