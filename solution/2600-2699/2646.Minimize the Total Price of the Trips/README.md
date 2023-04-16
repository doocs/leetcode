# [2646. 最小化旅行的价格总和](https://leetcode.cn/problems/minimize-the-total-price-of-the-trips)

[English Version](/solution/2600-2699/2646.Minimize%20the%20Total%20Price%20of%20the%20Trips/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现有一棵无向、无根的树，树中有 <code>n</code> 个节点，按从 <code>0</code> 到 <code>n - 1</code> 编号。给你一个整数 <code>n</code> 和一个长度为 <code>n - 1</code> 的二维整数数组 <code>edges</code> ，其中 <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 表示树中节点 <code>a<sub>i</sub></code> 和 <code>b<sub>i</sub></code> 之间存在一条边。</p>

<p>每个节点都关联一个价格。给你一个整数数组 <code>price</code> ，其中 <code>price[i]</code> 是第 <code>i</code> 个节点的价格。</p>

<p>给定路径的 <strong>价格总和</strong> 是该路径上所有节点的价格之和。</p>

<p>另给你一个二维整数数组 <code>trips</code> ，其中 <code>trips[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> 表示您从节点 <code>start<sub>i</sub></code> 开始第 <code>i</code> 次旅行，并通过任何你喜欢的路径前往节点 <code>end<sub>i</sub></code> 。</p>

<p>在执行第一次旅行之前，你可以选择一些 <strong>非相邻节点</strong> 并将价格减半。</p>

<p>返回执行所有旅行的最小价格总和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2646.Minimize%20the%20Total%20Price%20of%20the%20Trips/images/diagram2.png" style="width: 541px; height: 181px;">
<pre><strong>输入：</strong>n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,1],[2,3]]
<strong>输出：</strong>23
<strong>解释：
</strong>上图表示将节点 2 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 、2 和 3 并使其价格减半后的树。
第 1 次旅行，选择路径 [0,1,3] 。路径的价格总和为 1 + 2 + 3 = 6 。
第 2 次旅行，选择路径 [2,1] 。路径的价格总和为 2 + 5 = 7 。
第 3 次旅行，选择路径 [2,1,3] 。路径的价格总和为 5 + 2 + 3 = 10 。
所有旅行的价格总和为 6 + 7 + 10 = 23 。可以证明，23 是可以实现的最小答案。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2646.Minimize%20the%20Total%20Price%20of%20the%20Trips/images/diagram3.png" style="width: 456px; height: 111px;">
<pre><strong>输入：</strong>n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
<strong>输出：</strong>1
<strong>解释：</strong>
上图表示将节点 0 视为根之后的树结构。第一个图表示初始树，第二个图表示选择节点 0 并使其价格减半后的树。 
第 1 次旅行，选择路径 [0] 。路径的价格总和为 1 。 
所有旅行的价格总和为 1 。可以证明，1 是可以实现的最小答案。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>edges</code> 表示一棵有效的树</li>
	<li><code>price.length == n</code></li>
	<li><code>price[i]</code> 是一个偶数</li>
	<li><code>1 &lt;= price[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= trips.length &lt;= 100</code></li>
	<li><code>0 &lt;= start<sub>i</sub>, end<sub>i</sub>&nbsp;&lt;= n - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：树形 DP**

我们可以统计每一次旅行经过的节点，记录在数组 $cnt$ 中，其中 $cnt[i]$ 表示所有旅行中经过节点 $i$ 的次数。我们设计一个函数 $dfs(i, fa, k)$，表示从节点 $i$ 开始搜索，最终到达节点 $k$，过程中记录所有经过的节点。其中 $fa$ 表示节点 $i$ 的父节点。

接下来，我们再设计一个函数 $dfs2(i, fa)$，表示从节点 $i$ 开始搜索，返回将节点 $i$ 的价格不减半或者减半后的最小价格总和。其中 $fa$ 表示节点 $i$ 的父节点。

我们从节点 $0$ 开始，对于每一个节点 $i$，我们可以选择不将其价格减半，也可以选择将其价格减半，分别记为 $a = cnt[i] \times price[i]$, $b = \frac{a}{2}$。

对于节点 $i$ 的所有邻接节点 $j$，我们依然可以选择不将其价格减半，也可以选择将其价格减半，那么 $(x, y) = dfs2(j, i)$。如果节点 $i$ 价格不减半，那么节点 $j$ 价格可以不减半，也可以减半，因此 $a = a + min(x, y)$；如果节点 $i$ 价格减半，那么节点 $j$ 价格必须不减半，因此 $b = b + x$。

最后，函数 $dfs2(i, fa)$ 的返回值为 $(a, b)$。

在主函数中，我们调用函数 $dfs2(0, -1)$，返回值为 $(a, b)$，那么最终的答案即为 $min(a, b)$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(n)$。其中 $m$ 和 $n$ 分别为旅行次数以及节点数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumTotalPrice(self, n: int, edges: List[List[int]], price: List[int], trips: List[List[int]]) -> int:
        def dfs(i: int, fa: int, k: int) -> bool:
            cnt[i] += 1
            if i == k:
                return True
            ok = any(j != fa and dfs(j, i, k) for j in g[i])
            if not ok:
                cnt[i] -= 1
            return ok

        def dfs2(i: int, fa: int) -> (int, int):
            a = cnt[i] * price[i]
            b = a // 2
            for j in g[i]:
                if j != fa:
                    x, y = dfs2(j, i)
                    a += min(x, y)
                    b += x
            return a, b

        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        cnt = Counter()
        for start, end in trips:
            dfs(start, -1, end)
        return min(dfs2(0, -1))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<Integer>[] g;
    private int[] price;
    private int[] cnt;

    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        this.price = price;
        cnt = new int[n];
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        for (var t : trips) {
            int start = t[0], end = t[1];
            dfs(start, -1, end);
        }
        int[] ans = dfs2(0, -1);
        return Math.min(ans[0], ans[1]);
    }

    private boolean dfs(int i, int fa, int k) {
        ++cnt[i];
        if (i == k) {
            return true;
        }
        boolean ok = false;
        for (int j : g[i]) {
            if (j != fa) {
                ok = dfs(j, i, k);
                if (ok) {
                    break;
                }
            }
        }
        if (!ok) {
            --cnt[i];
        }
        return ok;
    }

    private int[] dfs2(int i, int fa) {
        int a = cnt[i] * price[i];
        int b = a >> 1;
        for (int j : g[i]) {
            if (j != fa) {
                var t = dfs2(j, i);
                a += Math.min(t[0], t[1]);
                b += t[0];
            }
        }
        return new int[] {a, b};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumTotalPrice(int n, vector<vector<int>>& edges, vector<int>& price, vector<vector<int>>& trips) {
        vector<vector<int>> g(n);
        vector<int> cnt(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        function<bool(int, int, int)> dfs = [&](int i, int fa, int k) -> bool {
            ++cnt[i];
            if (i == k) {
                return true;
            }
            bool ok = false;
            for (int j : g[i]) {
                if (j != fa) {
                    ok = dfs(j, i, k);
                    if (ok) {
                        break;
                    }
                }
            }
            if (!ok) {
                --cnt[i];
            }
            return ok;
        };
        function<pair<int, int>(int, int)> dfs2 = [&](int i, int fa) -> pair<int, int> {
            int a = cnt[i] * price[i];
            int b = a >> 1;
            for (int j : g[i]) {
                if (j != fa) {
                    auto [x, y] = dfs2(j, i);
                    a += min(x, y);
                    b += x;
                }
            }
            return {a, b};
        };
        for (auto& t : trips) {
            int start = t[0], end = t[1];
            dfs(start, -1, end);
        }
        auto [a, b] = dfs2(0, -1);
        return min(a, b);
    }
};
```

### **Go**

```go
func minimumTotalPrice(n int, edges [][]int, price []int, trips [][]int) int {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	cnt := make([]int, n)
	var dfs func(int, int, int) bool
	dfs = func(i, fa, k int) bool {
		cnt[i]++
		if i == k {
			return true
		}
		ok := false
		for _, j := range g[i] {
			if j != fa {
				ok = dfs(j, i, k)
				if ok {
					break
				}
			}
		}
		if !ok {
			cnt[i]--
		}
		return ok
	}
	for _, t := range trips {
		start, end := t[0], t[1]
		dfs(start, -1, end)
	}
	var dfs2 func(int, int) (int, int)
	dfs2 = func(i, fa int) (int, int) {
		a := price[i] * cnt[i]
		b := a >> 1
		for _, j := range g[i] {
			if j != fa {
				x, y := dfs2(j, i)
				a += min(x, y)
				b += x
			}
		}
		return a, b
	}
	a, b := dfs2(0, -1)
	return min(a, b)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function minimumTotalPrice(
    n: number,
    edges: number[][],
    price: number[],
    trips: number[][],
): number {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const cnt: number[] = new Array(n).fill(0);
    const dfs = (i: number, fa: number, k: number): boolean => {
        ++cnt[i];
        if (i === k) {
            return true;
        }
        let ok = false;
        for (const j of g[i]) {
            if (j !== fa) {
                ok = dfs(j, i, k);
                if (ok) {
                    break;
                }
            }
        }
        if (!ok) {
            --cnt[i];
        }
        return ok;
    };
    for (const [start, end] of trips) {
        dfs(start, -1, end);
    }
    const dfs2 = (i: number, fa: number): number[] => {
        let a: number = price[i] * cnt[i];
        let b: number = a >> 1;
        for (const j of g[i]) {
            if (j !== fa) {
                const [x, y] = dfs2(j, i);
                a += Math.min(x, y);
                b += x;
            }
        }
        return [a, b];
    };
    const [a, b] = dfs2(0, -1);
    return Math.min(a, b);
}
```

### **...**

```

```

<!-- tabs:end -->
