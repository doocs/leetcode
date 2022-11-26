# [2477. Minimum Fuel Cost to Report to the Capital](https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital)

[中文文档](/solution/2400-2499/2477.Minimum%20Fuel%20Cost%20to%20Report%20to%20the%20Capital/README.md)

## Description

<p>There is a tree (i.e., a connected, undirected graph with no cycles) structure country network consisting of <code>n</code> cities numbered from <code>0</code> to <code>n - 1</code> and exactly <code>n - 1</code> roads. The capital city is city <code>0</code>. You are given a 2D integer array <code>roads</code> where <code>roads[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> denotes that there exists a <strong>bidirectional road</strong> connecting cities <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>.</p>

<p>There is a meeting for the representatives of each city. The meeting is in the capital city.</p>

<p>There is a car in each city. You are given an integer <code>seats</code> that indicates the number of seats in each car.</p>

<p>A representative can use the car in their city to travel or change the car and ride with another representative. The cost of traveling between two cities is one liter of fuel.</p>

<p>Return <em>the minimum number of liters of fuel to reach the capital city</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2477.Minimum%20Fuel%20Cost%20to%20Report%20to%20the%20Capital/images/a4c380025e3ff0c379525e96a7d63a3.png" style="width: 303px; height: 332px;" />
<pre>
<strong>Input:</strong> roads = [[0,1],[0,2],[0,3]], seats = 5
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
- Representative<sub>1</sub> goes directly to the capital with 1 liter of fuel.
- Representative<sub>2</sub> goes directly to the capital with 1 liter of fuel.
- Representative<sub>3</sub> goes directly to the capital with 1 liter of fuel.
It costs 3 liters of fuel at minimum. 
It can be proven that 3 is the minimum number of liters of fuel needed.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2477.Minimum%20Fuel%20Cost%20to%20Report%20to%20the%20Capital/images/2.png" style="width: 274px; height: 340px;" />
<pre>
<strong>Input:</strong> roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
<strong>Output:</strong> 7
<strong>Explanation:</strong> 
- Representative<sub>2</sub> goes directly to city 3 with 1 liter of fuel.
- Representative<sub>2</sub> and representative<sub>3</sub> go together to city 1 with 1 liter of fuel.
- Representative<sub>2</sub> and representative<sub>3</sub> go together to the capital with 1 liter of fuel.
- Representative<sub>1</sub> goes directly to the capital with 1 liter of fuel.
- Representative<sub>5</sub> goes directly to the capital with 1 liter of fuel.
- Representative<sub>6</sub> goes directly to city 4 with 1 liter of fuel.
- Representative<sub>4</sub> and representative<sub>6</sub> go together to the capital with 1 liter of fuel.
It costs 7 liters of fuel at minimum. 
It can be proven that 7 is the minimum number of liters of fuel needed.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2477.Minimum%20Fuel%20Cost%20to%20Report%20to%20the%20Capital/images/efcf7f7be6830b8763639cfd01b690a.png" style="width: 108px; height: 86px;" />
<pre>
<strong>Input:</strong> roads = [], seats = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> No representatives need to travel to the capital city.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>roads.length == n - 1</code></li>
	<li><code>roads[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>roads</code> represents a valid tree.</li>
	<li><code>1 &lt;= seats &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumFuelCost(self, roads: List[List[int]], seats: int) -> int:
        def dfs(a, fa):
            size = 1
            for b in g[a]:
                if b != fa:
                    t = dfs(b, a)
                    nonlocal ans
                    ans += (t + seats - 1) // seats
                    size += t
            return size

        g = defaultdict(list)
        for a, b in roads:
            g[a].append(b)
            g[b].append(a)
        ans = 0
        dfs(0, -1)
        return ans
```

### **Java**

```java
class Solution {
    private List<Integer>[] g;
    private long ans;
    private int seats;

    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length + 1;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        this.seats = seats;
        for (var e : roads) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        dfs(0, -1);
        return ans;
    }

    private int dfs(int a, int fa) {
        int size = 1;
        for (int b : g[a]) {
            if (b != fa) {
                int t = dfs(b, a);
                ans += (t + seats - 1) / seats;
                size += t;
            }
        }
        return size;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long minimumFuelCost(vector<vector<int>>& roads, int seats) {
        int n = roads.size() + 1;
        vector<vector<int>> g(n);
        for (auto& e : roads) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        long long ans = 0;
        function<int(int, int)> dfs = [&](int a, int fa) -> int {
            int size = 1;
            for (int b : g[a]) {
                if (b != fa) {
                    int t = dfs(b, a);
                    ans += (t + seats - 1) / seats;
                    size += t;
                }
            }
            return size;
        };
        dfs(0, -1);
        return ans;
    }
};
```

### **Go**

```go
func minimumFuelCost(roads [][]int, seats int) int64 {
	n := len(roads) + 1
	g := make([][]int, n)
	for _, e := range roads {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	ans := 0
	var dfs func(int, int) int
	dfs = func(a, fa int) int {
		size := 1
		for _, b := range g[a] {
			if b != fa {
				t := dfs(b, a)
				ans += (t + seats - 1) / seats
				size += t
			}
		}
		return size
	}
	dfs(0, -1)
	return int64(ans)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
