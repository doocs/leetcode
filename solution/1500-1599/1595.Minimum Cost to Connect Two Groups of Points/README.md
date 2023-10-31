# [1595. 连通两组点的最小成本](https://leetcode.cn/problems/minimum-cost-to-connect-two-groups-of-points)

[English Version](/solution/1500-1599/1595.Minimum%20Cost%20to%20Connect%20Two%20Groups%20of%20Points/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两组点，其中第一组中有 <code>size<sub>1</sub></code> 个点，第二组中有 <code>size<sub>2</sub></code> 个点，且 <code>size<sub>1</sub> &gt;= size<sub>2</sub></code> 。</p>

<p>任意两点间的连接成本 <code>cost</code> 由大小为 <code>size<sub>1</sub> x size<sub>2</sub></code> 矩阵给出，其中 <code>cost[i][j]</code> 是第一组中的点 <code>i</code> 和第二组中的点 <code>j</code> 的连接成本。<strong>如果两个组中的每个点都与另一组中的一个或多个点连接，则称这两组点是连通的。</strong>换言之，第一组中的每个点必须至少与第二组中的一个点连接，且第二组中的每个点必须至少与第一组中的一个点连接。</p>

<p>返回连通两组点所需的最小成本。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1595.Minimum%20Cost%20to%20Connect%20Two%20Groups%20of%20Points/images/ex1.jpg" style="height: 243px; width: 322px;"></p>

<pre><strong>输入：</strong>cost = [[15, 96], [36, 2]]
<strong>输出：</strong>17
<strong>解释：</strong>连通两组点的最佳方法是：
1--A
2--B
总成本为 17 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1595.Minimum%20Cost%20to%20Connect%20Two%20Groups%20of%20Points/images/ex2.jpg" style="height: 403px; width: 322px;"></p>

<pre><strong>输入：</strong>cost = [[1, 3, 5], [4, 1, 1], [1, 5, 3]]
<strong>输出：</strong>4
<strong>解释：</strong>连通两组点的最佳方法是：
1--A
2--B
2--C
3--A
最小成本为 4 。
请注意，虽然有多个点连接到第一组中的点 2 和第二组中的点 A ，但由于题目并不限制连接点的数目，所以只需要关心最低总成本。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>cost = [[2, 5, 1], [3, 4, 7], [8, 1, 2], [6, 2, 4], [3, 8, 8]]
<strong>输出：</strong>10
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>size<sub>1</sub> == cost.length</code></li>
	<li><code>size<sub>2</sub> == cost[i].length</code></li>
	<li><code>1 &lt;= size<sub>1</sub>, size<sub>2</sub> &lt;= 12</code></li>
	<li><code>size<sub>1</sub> &gt;=&nbsp;size<sub>2</sub></code></li>
	<li><code>0 &lt;= cost[i][j] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：状态压缩 + 动态规划**

我们记第一组的点数为 $m$，第二组的点数为 $n$。

由于 $1 \leq n \leq m \leq 12$，因此，我们可以用一个整数来表示第二组中点的状态，即二进制表示的一个长度为 $n$ 的整数，其中第 $k$ 位为 $1$ 表示第二组中的第 $k$ 个点与第一组中的点连通，为 $0$ 表示不连通。

接下来，我们定义 $f[i][j]$ 表示第一组中的前 $i$ 个点已经全部连通，且第二组中的点的状态为 $j$ 时的最小成本。初始时 $f[0][0] = 0$，其余值均为正无穷大。答案即为 $f[m][2^n - 1]$。

考虑 $f[i][j]$，其中 $i \geq 1$。我们可以枚举第二组中的每个点 $k$，如果点 $k$ 与第一组中的第 $i$ 个点连通，那么我们可以分以下两种情况讨论：

-   如果点 $k$ 只与第一组中的第 $i$ 个点连通，那么 $f[i][j]$ 可以从 $f[i][j \oplus 2^k]$ 或者 $f[i - 1][j \oplus 2^k]$ 转移而来，其中 $\oplus$ 表示异或运算；
-   如果点 $k$ 与第一组中的第 $i$ 个点以及其他点都连通，那么 $f[i][j]$ 可以从 $f[i - 1][j]$ 转移而来。

在上述两种情况中，我们需要选择转移值最小的那个，即有：

$$
f[i][j] = \min_{k \in \{0, 1, \cdots, n - 1\}} \{f[i][j \oplus 2^k], f[i - 1][j \oplus 2^k], f[i - 1][j]\} + cost[i - 1][k]
$$

最后，我们返回 $f[m][2^n - 1]$ 即可。

时间复杂度 $O(m \times n \times 2^n)$，空间复杂度 $O(m \times 2^n)$。其中 $m$ 和 $n$ 分别是第一组和第二组中的点数。

我们注意到 $f[i][j]$ 的转移只与 $f[i - 1][\cdot]$ 以及 $f[i][\cdot]$ 有关，因此我们可以使用滚动数组优化空间复杂度，将空间复杂度优化到 $O(2^n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def connectTwoGroups(self, cost: List[List[int]]) -> int:
        m, n = len(cost), len(cost[0])
        f = [[inf] * (1 << n) for _ in range(m + 1)]
        f[0][0] = 0
        for i in range(1, m + 1):
            for j in range(1 << n):
                for k in range(n):
                    if (j >> k & 1) == 0:
                        continue
                    c = cost[i - 1][k]
                    x = min(f[i][j ^ (1 << k)], f[i - 1][j], f[i - 1][j ^ (1 << k)]) + c
                    f[i][j] = min(f[i][j], x)
        return f[m][-1]
```

```python
class Solution:
    def connectTwoGroups(self, cost: List[List[int]]) -> int:
        m, n = len(cost), len(cost[0])
        f = [inf] * (1 << n)
        f[0] = 0
        g = f[:]
        for i in range(1, m + 1):
            for j in range(1 << n):
                g[j] = inf
                for k in range(n):
                    if (j >> k & 1) == 0:
                        continue
                    c = cost[i - 1][k]
                    x = min(g[j ^ (1 << k)], f[j], f[j ^ (1 << k)]) + c
                    g[j] = min(g[j], x)
            f = g[:]
        return f[-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size(), n = cost.get(0).size();
        final int inf = 1 << 30;
        int[][] f = new int[m + 1][1 << n];
        for (int[] g : f) {
            Arrays.fill(g, inf);
        }
        f[0][0] = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j < 1 << n; ++j) {
                for (int k = 0; k < n; ++k) {
                    if ((j >> k & 1) == 1) {
                        int c = cost.get(i - 1).get(k);
                        f[i][j] = Math.min(f[i][j], f[i][j ^ (1 << k)] + c);
                        f[i][j] = Math.min(f[i][j], f[i - 1][j] + c);
                        f[i][j] = Math.min(f[i][j], f[i - 1][j ^ (1 << k)] + c);
                    }
                }
            }
        }
        return f[m][(1 << n) - 1];
    }
}
```

```java
class Solution {
    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size(), n = cost.get(0).size();
        final int inf = 1 << 30;
        int[] f = new int[1 << n];
        Arrays.fill(f, inf);
        f[0] = 0;
        int[] g = f.clone();
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j < 1 << n; ++j) {
                g[j] = inf;
                for (int k = 0; k < n; ++k) {
                    if ((j >> k & 1) == 1) {
                        int c = cost.get(i - 1).get(k);
                        g[j] = Math.min(g[j], g[j ^ (1 << k)] + c);
                        g[j] = Math.min(g[j], f[j] + c);
                        g[j] = Math.min(g[j], f[j ^ (1 << k)] + c);
                    }
                }
            }
            System.arraycopy(g, 0, f, 0, 1 << n);
        }
        return f[(1 << n) - 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int connectTwoGroups(vector<vector<int>>& cost) {
        int m = cost.size(), n = cost[0].size();
        int f[m + 1][1 << n];
        memset(f, 0x3f, sizeof(f));
        f[0][0] = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j < 1 << n; ++j) {
                for (int k = 0; k < n; ++k) {
                    if (j >> k & 1) {
                        int c = cost[i - 1][k];
                        int x = min({f[i][j ^ (1 << k)], f[i - 1][j], f[i - 1][j ^ (1 << k)]}) + c;
                        f[i][j] = min(f[i][j], x);
                    }
                }
            }
        }
        return f[m][(1 << n) - 1];
    }
};
```

```cpp
class Solution {
public:
    int connectTwoGroups(vector<vector<int>>& cost) {
        int m = cost.size(), n = cost[0].size();
        const int inf = 1 << 30;
        vector<int> f(1 << n, inf);
        f[0] = 0;
        vector<int> g = f;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j < 1 << n; ++j) {
                g[j] = inf;
                for (int k = 0; k < n; ++k) {
                    if (j >> k & 1) {
                        int c = cost[i - 1][k];
                        int x = min({g[j ^ (1 << k)], f[j], f[j ^ (1 << k)]}) + c;
                        g[j] = min(g[j], x);
                    }
                }
            }
            f.swap(g);
        }
        return f[(1 << n) - 1];
    }
};
```

### **Go**

```go
func connectTwoGroups(cost [][]int) int {
	m, n := len(cost), len(cost[0])
	const inf = 1 << 30
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, 1<<n)
		for j := range f[i] {
			f[i][j] = inf
		}
	}
	f[0][0] = 0
	for i := 1; i <= m; i++ {
		for j := 0; j < 1<<n; j++ {
			for k := 0; k < n; k++ {
				c := cost[i-1][k]
				if j>>k&1 == 1 {
					f[i][j] = min(f[i][j], f[i][j^(1<<k)]+c)
					f[i][j] = min(f[i][j], f[i-1][j]+c)
					f[i][j] = min(f[i][j], f[i-1][j^(1<<k)]+c)
				}
			}
		}
	}
	return f[m][(1<<n)-1]
}
```

```go
func connectTwoGroups(cost [][]int) int {
	m, n := len(cost), len(cost[0])
	const inf = 1 << 30
	f := make([]int, 1<<n)
	for i := range f {
		f[i] = inf
	}
	f[0] = 0
	g := make([]int, 1<<n)
	for i := 1; i <= m; i++ {
		for j := 0; j < 1<<n; j++ {
			g[j] = inf
			for k := 0; k < n; k++ {
				c := cost[i-1][k]
				if j>>k&1 == 1 {
					g[j] = min(g[j], g[j^1<<k]+c)
					g[j] = min(g[j], f[j]+c)
					g[j] = min(g[j], f[j^1<<k]+c)
				}
			}
		}
		copy(f, g)
	}
	return f[1<<n-1]
}
```

### **TypeScript**

```ts
function connectTwoGroups(cost: number[][]): number {
    const m = cost.length;
    const n = cost[0].length;
    const inf = 1 << 30;
    const f: number[][] = Array(m + 1)
        .fill(0)
        .map(() => Array(1 << n).fill(inf));
    f[0][0] = 0;
    for (let i = 1; i <= m; ++i) {
        for (let j = 0; j < 1 << n; ++j) {
            for (let k = 0; k < n; ++k) {
                if (((j >> k) & 1) === 1) {
                    const c = cost[i - 1][k];
                    f[i][j] = Math.min(f[i][j], f[i][j ^ (1 << k)] + c);
                    f[i][j] = Math.min(f[i][j], f[i - 1][j] + c);
                    f[i][j] = Math.min(f[i][j], f[i - 1][j ^ (1 << k)] + c);
                }
            }
        }
    }
    return f[m][(1 << n) - 1];
}
```

```ts
function connectTwoGroups(cost: number[][]): number {
    const m = cost.length;
    const n = cost[0].length;
    const inf = 1 << 30;
    const f: number[] = new Array(1 << n).fill(inf);
    f[0] = 0;
    const g = new Array(1 << n).fill(0);
    for (let i = 1; i <= m; ++i) {
        for (let j = 0; j < 1 << n; ++j) {
            g[j] = inf;
            for (let k = 0; k < n; ++k) {
                if (((j >> k) & 1) === 1) {
                    const c = cost[i - 1][k];
                    g[j] = Math.min(g[j], g[j ^ (1 << k)] + c);
                    g[j] = Math.min(g[j], f[j] + c);
                    g[j] = Math.min(g[j], f[j ^ (1 << k)] + c);
                }
            }
        }
        f.splice(0, f.length, ...g);
    }
    return f[(1 << n) - 1];
}
```

### **...**

```

```

<!-- tabs:end -->
