# [2477. 到达首都的最少油耗](https://leetcode.cn/problems/minimum-fuel-cost-to-report-to-the-capital)

[English Version](/solution/2400-2499/2477.Minimum%20Fuel%20Cost%20to%20Report%20to%20the%20Capital/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵 <code>n</code>&nbsp;个节点的树（一个无向、连通、无环图），每个节点表示一个城市，编号从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;，且恰好有&nbsp;<code>n - 1</code>&nbsp;条路。<code>0</code>&nbsp;是首都。给你一个二维整数数组&nbsp;<code>roads</code>&nbsp;，其中&nbsp;<code>roads[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;，表示城市&nbsp;<code>a<sub>i</sub></code> 和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条&nbsp;<strong>双向路</strong>&nbsp;。</p>

<p>每个城市里有一个代表，他们都要去首都参加一个会议。</p>

<p>每座城市里有一辆车。给你一个整数&nbsp;<code>seats</code>&nbsp;表示每辆车里面座位的数目。</p>

<p>城市里的代表可以选择乘坐所在城市的车，或者乘坐其他城市的车。相邻城市之间一辆车的油耗是一升汽油。</p>

<p>请你返回到达首都最少需要多少升汽油。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2477.Minimum%20Fuel%20Cost%20to%20Report%20to%20the%20Capital/images/a4c380025e3ff0c379525e96a7d63a3.png" style="width: 303px; height: 332px;"></p>

<pre><b>输入：</b>roads = [[0,1],[0,2],[0,3]], seats = 5
<b>输出：</b>3
<b>解释：</b>
- 代表 1 直接到达首都，消耗 1 升汽油。
- 代表 2 直接到达首都，消耗 1 升汽油。
- 代表 3 直接到达首都，消耗 1 升汽油。
最少消耗 3 升汽油。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2477.Minimum%20Fuel%20Cost%20to%20Report%20to%20the%20Capital/images/2.png" style="width: 274px; height: 340px;"></p>

<pre><b>输入：</b>roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
<b>输出：</b>7
<b>解释：</b>
- 代表 2 到达城市 3 ，消耗 1 升汽油。
- 代表 2 和代表 3 一起到达城市 1 ，消耗 1 升汽油。
- 代表 2 和代表 3 一起到达首都，消耗 1 升汽油。
- 代表 1 直接到达首都，消耗 1 升汽油。
- 代表 5 直接到达首都，消耗 1 升汽油。
- 代表 6 到达城市 4 ，消耗 1 升汽油。
- 代表 4 和代表 6 一起到达首都，消耗 1 升汽油。
最少消耗 7 升汽油。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2477.Minimum%20Fuel%20Cost%20to%20Report%20to%20the%20Capital/images/efcf7f7be6830b8763639cfd01b690a.png" style="width: 108px; height: 86px;"></p>

<pre><b>输入：</b>roads = [], seats = 1
<b>输出：</b>0
<b>解释：</b>没有代表需要从别的城市到达首都。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>roads.length == n - 1</code></li>
	<li><code>roads[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>roads</code>&nbsp;表示一棵合法的树。</li>
	<li><code>1 &lt;= seats &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

建图，然后用 DFS 统计以每个点为根的子树的节点个数，假设以 $a$ 为根的子树中有 $size$ 个节点，那么到达根节点一共需要 $\lceil \frac{size}{seats} \rceil$ 辆车。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
