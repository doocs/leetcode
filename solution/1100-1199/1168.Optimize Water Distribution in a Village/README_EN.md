# [1168. Optimize Water Distribution in a Village](https://leetcode.com/problems/optimize-water-distribution-in-a-village)

[中文文档](/solution/1100-1199/1168.Optimize%20Water%20Distribution%20in%20a%20Village/README.md)

## Description

<p>There are <code>n</code> houses in a village. We want to supply water for all the houses by building wells and laying pipes.</p>

<p>For each house <code>i</code>, we can either build a well inside it directly with cost <code>wells[i - 1]</code> (note the <code>-1</code> due to <strong>0-indexing</strong>), or pipe in water from another well to it. The costs to lay pipes between houses are given by the array <code>pipes</code>, where each <code>pipes[j] = [house1<sub>j</sub>, house2<sub>j</sub>, cost<sub>j</sub>]</code> represents the cost to connect <code>house1<sub>j</sub></code> and <code>house2<sub>j</sub></code> together using a pipe. Connections are bidirectional.</p>

<p>Return <em>the minimum total cost to supply water to all houses</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1168.Optimize%20Water%20Distribution%20in%20a%20Village/images/1359_ex1.png" style="width: 189px; height: 196px;" /></strong></p>

<pre>
<strong>Input:</strong> n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
<strong>Output:</strong> 3
<strong>Explanation: </strong>
The image shows the costs of connecting houses using pipes.
The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>wells.length == n</code></li>
	<li><code>0 &lt;= wells[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= pipes.length &lt;= 10<sup>4</sup></code></li>
	<li><code>pipes[j].length == 3</code></li>
	<li><code>1 &lt;= house1<sub>j</sub>, house2<sub>j</sub> &lt;= n</code></li>
	<li><code>0 &lt;= cost<sub>j</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>house1<sub>j</sub> != house2<sub>j</sub></code></li>
</ul>

## Solutions

Union find.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minCostToSupplyWater(self, n: int, wells: List[int], pipes: List[List[int]]) -> int:
        for i, w in enumerate(wells):
            pipes.append([0, i + 1, w])
        pipes.sort(key=lambda x: x[2])

        p = list(range(n + 1))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        res = 0
        for u, v, w in pipes:
            if find(u) == find(v):
                continue
            p[find(u)] = find(v)
            res += w
            n -= 1
            if n == 0:
                break
        return res
```

### **Java**

```java
class Solution {
    private int[] p;

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        int[][] all = new int[pipes.length + n][3];
        int idx = 0;
        for (int[] pipe : pipes) {
            all[idx++] = pipe;
        }
        for (int j = 0; j < n; ++j) {
            all[idx++] = new int[]{0, j + 1, wells[j]};
        }
        p = new int[n + 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        Arrays.sort(all, Comparator.comparingInt(a -> a[2]));
        int res = 0;
        for (int[] e : all) {
            if (find(e[0]) == find(e[1])) {
                continue;
            }
            p[find(e[0])] = find(e[1]);
            res += e[2];
            --n;
            if (n == 0) {
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

    int minCostToSupplyWater(int n, vector<int>& wells, vector<vector<int>>& pipes) {
        p.resize(n + 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (int i = 0; i < n; ++i) pipes.push_back({0, i + 1, wells[i]});
        sort(pipes.begin(), pipes.end(), [](const auto& a, const auto& b) {
            return a[2] < b[2];
        });
        int res = 0;
        for (auto e : pipes)
        {
            if (find(e[0]) == find(e[1])) continue;
            p[find(e[0])] = find(e[1]);
            res += e[2];
            --n;
            if (n == 0) break;
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

func minCostToSupplyWater(n int, wells []int, pipes [][]int) int {
	p = make([]int, n+1)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	for i, w := range wells {
		pipes = append(pipes, []int{0, i + 1, w})
	}
	sort.Slice(pipes, func(i, j int) bool {
		return pipes[i][2] < pipes[j][2]
	})
	res := 0
	for _, e := range pipes {
		if find(e[0]) == find(e[1]) {
			continue
		}
		p[find(e[0])] = find(e[1])
		res += e[2]
		n--
		if n == 0 {
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
```

### **...**

```

```

<!-- tabs:end -->
