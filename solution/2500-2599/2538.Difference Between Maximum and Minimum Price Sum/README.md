# [2538. 最大价值和与最小价值和的差值](https://leetcode.cn/problems/difference-between-maximum-and-minimum-price-sum)

[English Version](/solution/2500-2599/2538.Difference%20Between%20Maximum%20and%20Minimum%20Price%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>n</code>&nbsp;个节点的无向无根图，节点编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;。给你一个整数&nbsp;<code>n</code>&nbsp;和一个长度为 <code>n - 1</code>&nbsp;的二维整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示树中节点&nbsp;<code>a<sub>i</sub></code> 和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条边。</p>

<p>每个节点都有一个价值。给你一个整数数组&nbsp;<code>price</code>&nbsp;，其中&nbsp;<code>price[i]</code>&nbsp;是第 <code>i</code>&nbsp;个节点的价值。</p>

<p>一条路径的 <strong>价值和</strong>&nbsp;是这条路径上所有节点的价值之和。</p>

<p>你可以选择树中任意一个节点作为根节点&nbsp;<code>root</code>&nbsp;。选择 <code>root</code>&nbsp;为根的 <strong>开销</strong>&nbsp;是以 <code>root</code>&nbsp;为起点的所有路径中，<strong>价值和</strong>&nbsp;最大的一条路径与最小的一条路径的差值。</p>

<p>请你返回所有节点作为根节点的选择中，<strong>最大</strong>&nbsp;的 <strong>开销</strong>&nbsp;为多少。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2538.Difference%20Between%20Maximum%20and%20Minimum%20Price%20Sum/images/example14.png" style="width: 556px; height: 231px;" /></p>

<pre>
<b>输入：</b>n = 6, edges = [[0,1],[1,2],[1,3],[3,4],[3,5]], price = [9,8,7,6,10,5]
<b>输出：</b>24
<b>解释：</b>上图展示了以节点 2 为根的树。左图（红色的节点）是最大价值和路径，右图（蓝色的节点）是最小价值和路径。
- 第一条路径节点为 [2,1,3,4]：价值为 [7,8,6,10] ，价值和为 31 。
- 第二条路径节点为 [2] ，价值为 [7] 。
最大路径和与最小路径和的差值为 24 。24 是所有方案中的最大开销。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2538.Difference%20Between%20Maximum%20and%20Minimum%20Price%20Sum/images/p1_example2.png" style="width: 352px; height: 184px;" /></p>

<pre>
<b>输入：</b>n = 3, edges = [[0,1],[1,2]], price = [1,1,1]
<b>输出：</b>2
<b>解释：</b>上图展示了以节点 0 为根的树。左图（红色的节点）是最大价值和路径，右图（蓝色的节点）是最小价值和路径。
- 第一条路径包含节点 [0,1,2]：价值为 [1,1,1] ，价值和为 3 。
- 第二条路径节点为 [0] ，价值为 [1] 。
最大路径和与最小路径和的差值为 2 。2 是所有方案中的最大开销。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>edges</code> 表示一棵符合题面要求的树。</li>
	<li><code>price.length == n</code></li>
	<li><code>1 &lt;= price[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：树形 DP**

由于每个节点价值均为正整数，因此，以节点 $root$ 作为根节点的最小的一条路径就是 $root$ 节点本身，那么价值和最大的一条路径与最小的一条路径的差值就等价于去掉路径的一个端点。

我们设计一个函数 $dfs(i, fa)$，表示以节点 $i$ 为根节点的子树中，不去掉端点的最大路径和以及去掉端点的最大路径和。其中，$fa$ 表示节点 $i$ 的父节点。

函数 $dfs(i, fa)$ 的实现逻辑如下：

初始化 $a = price[i]$, $b = 0$，表示初始只有一个节点，不去掉端点的最大路径和为 $price[i]$，去掉端点的最大路径和为 $0$。

对于节点 $i$ 的每个子节点 $j$，如果 $j \ne fa$，则递归调用函数 $dfs(j, i)$，这里返回了以节点 $j$ 为根节点的子树中，不去掉端点的最大路径和以及去掉端点的最大路径和，记为 $c$ 和 $d$。此时答案有两种情况：

-   前面不去掉端点的最大路径和加上当前节点去掉端点的最大路径和，即 $a + d$；
-   前面去掉端点的最大路径和加上当前节点不去掉端点的最大路径和，即 $b + c$。

我们更新答案的最大值，即 $ans = max(ans, a + d, b + c)$。

然后更新 $a$ 和 $b$，即 $a = max(a, price[i] + c)$, $b = max(b, price[i] + d)$，最后返回。

时间复杂度为 $O(n)$，空间复杂度为 $O(n)$。其中 $n$ 为节点个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxOutput(self, n: int, edges: List[List[int]], price: List[int]) -> int:
        def dfs(i, fa):
            a, b = price[i], 0
            for j in g[i]:
                if j != fa:
                    c, d = dfs(j, i)
                    nonlocal ans
                    ans = max(ans, a + d, b + c)
                    a = max(a, price[i] + c)
                    b = max(b, price[i] + d)
            return a, b

        g = defaultdict(list)
        for a, b in edges:
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
    private int[] price;

    public long maxOutput(int n, int[][] edges, int[] price) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        this.price = price;
        dfs(0, -1);
        return ans;
    }

    private long[] dfs(int i, int fa) {
        long a = price[i], b = 0;
        for (int j : g[i]) {
            if (j != fa) {
                var e = dfs(j, i);
                long c = e[0], d = e[1];
                ans = Math.max(ans, Math.max(a + d, b + c));
                a = Math.max(a, price[i] + c);
                b = Math.max(b, price[i] + d);
            }
        }
        return new long[] {a, b};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long maxOutput(int n, vector<vector<int>>& edges, vector<int>& price) {
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        using ll = long long;
        using pll = pair<ll, ll>;
        ll ans = 0;
        function<pll(int, int)> dfs = [&](int i, int fa) {
            ll a = price[i], b = 0;
            for (int j : g[i]) {
                if (j != fa) {
                    auto [c, d] = dfs(j, i);
                    ans = max({ans, a + d, b + c});
                    a = max(a, price[i] + c);
                    b = max(b, price[i] + d);
                }
            }
            return pll{a, b};
        };
        dfs(0, -1);
        return ans;
    }
};
```

### **Go**

```go
func maxOutput(n int, edges [][]int, price []int) int64 {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	type pair struct{ a, b int }
	ans := 0
	var dfs func(i, fa int) pair
	dfs = func(i, fa int) pair {
		a, b := price[i], 0
		for _, j := range g[i] {
			if j != fa {
				e := dfs(j, i)
				c, d := e.a, e.b
				ans = max(ans, max(a+d, b+c))
				a = max(a, price[i]+c)
				b = max(b, price[i]+d)
			}
		}
		return pair{a, b}
	}
	dfs(0, -1)
	return int64(ans)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
