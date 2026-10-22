---
comments: true
difficulty: 困难
rating: 2175
source: 第 185 场周赛 Q4
tags:
    - 动态规划
    - 前缀和
---

<!-- problem:start -->

# [1420. 生成数组](https://leetcode.cn/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons)

[English Version](/solution/1400-1499/1420.Build%20Array%20Where%20You%20Can%20Find%20The%20Maximum%20Exactly%20K%20Comparisons/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定三个整数 <code>n</code>、<code>m</code> 和 <code>k</code> 。考虑使用下图描述的算法找出正整数数组中最大的元素。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1420.Build%20Array%20Where%20You%20Can%20Find%20The%20Maximum%20Exactly%20K%20Comparisons/images/e.png" style="height: 372px; width: 424px;" /></p>

<p>请你构建一个具有以下属性的数组 <code>arr</code> ：</p>

<ul>
	<li><code>arr</code> 中包含确切的&nbsp;<code>n</code> 个整数。</li>
	<li><code>1 &lt;= arr[i] &lt;= m</code> 其中 <code>(0 &lt;= i &lt; n)</code> 。</li>
	<li>将上面提到的算法应用于 <code>arr</code>&nbsp;之后，<code>search_cost</code> 的值等于 <code>k</code> 。</li>
</ul>

<p>返回在满足上述条件的情况下构建数组 <code>arr</code> 的 <em>方法数量</em>&nbsp;，由于答案可能会很大，所以 <strong>必须</strong> 对 <code>10^9 + 7</code> 取余。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2, m = 3, k = 1
<strong>输出：</strong>6
<strong>解释：</strong>可能的数组分别为 [1, 1], [2, 1], [2, 2], [3, 1], [3, 2] [3, 3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 5, m = 2, k = 3
<strong>输出：</strong>0
<strong>解释：</strong>没有数组可以满足上述条件
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 9, m = 1, k = 1
<strong>输出：</strong>1
<strong>解释：</strong>唯一可能的数组是 [1, 1, 1, 1, 1, 1, 1, 1, 1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= m &lt;= 100</code></li>
	<li><code>0 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

<!-- thinking:start -->

> **思考**
>
> 搜索代价等于从左到右刷新最大值的次数。 $n\le 50$、 $m\le 100$，枚举全部数组不可行，需按「长度、代价、当前最大值」递推。
>
> 第 $i$ 位若不刷新最大值，可在 $[1,j]$ 中任取，方案数为上一层同代价、同最大值乘 $j$；若恰好写入新最大值 $j$，则由更小最大值、代价减一的状态累加。
>
> $k=0$ 不可能。对 $dp[n][k][\cdot]$ 求和即答案。

<!-- thinking:end -->

假设 $dp[i][c][j]$ 表示长度为 $i$，搜索代价为 $c$，且最大值为 $j$ 的方案数。考虑第 $i$ 个数：

若第 $i$ 个数没有改变搜索代价，说明它不严格大于前 $i-1$ 个数，也就是说， $dp[i][c][j]$ 是从 $dp[i-1][c][j]$ 转移而来，即数组的前 $i-1$ 个数的最大值已经是 $j$，并且第 $i$ 个数没有改变最大值，因此第 $i$ 个数的可选范围是 $[1,..j]$，共有 $j$ 种可选方案。即

$$
dp[i][c][j]=dp[i-1][c][j] \times j
$$

若第 $i$ 个数改变了搜索代价，说明数组前 $i-1$ 个数的最大值小于 $j$，并且第 $i$ 个数恰好为 $j$。此时 $dp[i][c][j]$ 是从所有 $dp[i-1][c-1][j']$ 转移而来，其中 $j'<j$。即

$$
dp[i][c][j] = \sum_{j'=1}^{j-1} dp[i-1][c-1][j']
$$

综上，可得

$$
dp[i][c][j] = dp[i-1][c][j] \times j + \sum_{j'=1}^{j-1} dp[i-1][c-1][j']
$$

答案为

$$
\sum_{j=1}^{m}dp[n][k][j]
$$

时间复杂度 $O(nkm^2)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numOfArrays(self, n: int, m: int, k: int) -> int:
        if k == 0:
            return 0
        dp = [[[0] * (m + 1) for _ in range(k + 1)] for _ in range(n + 1)]
        mod = 10**9 + 7
        for i in range(1, m + 1):
            dp[1][1][i] = 1
        for i in range(2, n + 1):
            for c in range(1, min(k + 1, i + 1)):
                for j in range(1, m + 1):
                    dp[i][c][j] = dp[i - 1][c][j] * j
                    for j0 in range(1, j):
                        dp[i][c][j] += dp[i - 1][c - 1][j0]
                        dp[i][c][j] %= mod
        ans = 0
        for i in range(1, m + 1):
            ans += dp[n][k][i]
            ans %= mod
        return ans
```

#### Java

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numOfArrays(int n, int m, int k) {
        if (k == 0) {
            return 0;
        }
        long[][][] dp = new long[n + 1][k + 1][m + 1];
        for (int i = 1; i <= m; ++i) {
            dp[1][1][i] = 1;
        }
        for (int i = 2; i <= n; ++i) {
            for (int c = 1; c <= Math.min(i, k); ++c) {
                for (int j = 1; j <= m; ++j) {
                    dp[i][c][j] = (dp[i - 1][c][j] * j) % MOD;
                    for (int j0 = 1; j0 < j; ++j0) {
                        dp[i][c][j] = (dp[i][c][j] + dp[i - 1][c - 1][j0]) % MOD;
                    }
                }
            }
        }
        long ans = 0;
        for (int i = 1; i <= m; ++i) {
            ans = (ans + dp[n][k][i]) % MOD;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numOfArrays(int n, int m, int k) {
        if (k == 0) return 0;
        int mod = 1e9 + 7;
        using ll = long long;
        vector<vector<vector<ll>>> dp(n + 1, vector<vector<ll>>(k + 1, vector<ll>(m + 1)));
        for (int i = 1; i <= m; ++i) dp[1][1][i] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int c = 1; c <= min(i, k); ++c) {
                for (int j = 1; j <= m; ++j) {
                    dp[i][c][j] = (dp[i - 1][c][j] * j) % mod;
                    for (int j0 = 1; j0 < j; ++j0) {
                        dp[i][c][j] = (dp[i][c][j] + dp[i - 1][c - 1][j0]) % mod;
                    }
                }
            }
        }
        ll ans = 0;
        for (int i = 1; i <= m; ++i) ans = (ans + dp[n][k][i]) % mod;
        return (int) ans;
    }
};
```

#### Go

```go
func numOfArrays(n int, m int, k int) int {
	if k == 0 {
		return 0
	}
	mod := int(1e9) + 7
	dp := make([][][]int, n+1)
	for i := range dp {
		dp[i] = make([][]int, k+1)
		for j := range dp[i] {
			dp[i][j] = make([]int, m+1)
		}
	}
	for i := 1; i <= m; i++ {
		dp[1][1][i] = 1
	}
	for i := 2; i <= n; i++ {
		for c := 1; c <= k && c <= i; c++ {
			for j := 1; j <= m; j++ {
				dp[i][c][j] = (dp[i-1][c][j] * j) % mod
				for j0 := 1; j0 < j; j0++ {
					dp[i][c][j] = (dp[i][c][j] + dp[i-1][c-1][j0]) % mod
				}
			}
		}
	}
	ans := 0
	for i := 1; i <= m; i++ {
		ans = (ans + dp[n][k][i]) % mod
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：前缀和优化

<!-- thinking:start -->

> **思考**
>
> 方法一计算每个状态时，都要重新枚举比当前最大值小的旧最大值，多出一层 $O(m)$ 循环。固定长度和查找代价后，相邻两个最大值所需的和只差前一个最大值对应的状态。我们按最大值从小到大维护这个前缀和，就能在 $O(1)$ 时间算出每个状态。转移只依赖上一层长度，因此只需保留两层 DP。

<!-- thinking:end -->

设 $dp[c][j]$ 表示当前长度、查找代价为 $c$、最大值为 $j$ 的数组个数。追加一个数时，要么最大值不变，有 $j$ 种选择；要么从较小的最大值增大到 $j$。在递增枚举 $j$ 时维护 $prefix = \sum_{x=1}^{j-1} dp_{\mathrm{prev}}[c-1][x]$，于是

$$dp_{\mathrm{next}}[c][j] = j \cdot dp_{\mathrm{prev}}[c][j] + prefix \pmod{10^9+7}.$$

长度为 $1$ 时，初始化 $dp[1][j]=1$。答案为所有 $1 \le j \le m$ 的 $dp[k][j]$ 之和。时间复杂度为 $O(nkm)$，比方法一的 $O(nkm^2)$ 少一层枚举；只保留前后两层，空间复杂度从 $O(nkm)$ 降为 $O(km)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numOfArrays(self, n: int, m: int, k: int) -> int:
        if k == 0:
            return 0
        mod = 10**9 + 7
        dp = [[0] * (m + 1) for _ in range(k + 1)]
        for j in range(1, m + 1):
            dp[1][j] = 1
        for i in range(2, n + 1):
            nxt = [[0] * (m + 1) for _ in range(k + 1)]
            for c in range(1, min(i, k) + 1):
                prefix = 0
                for j in range(1, m + 1):
                    prefix = (prefix + dp[c - 1][j - 1]) % mod
                    nxt[c][j] = (dp[c][j] * j + prefix) % mod
            dp = nxt
        return sum(dp[k]) % mod
```

#### C++

```cpp
class Solution {
public:
    int numOfArrays(int n, int m, int k) {
        if (k == 0) return 0;
        constexpr int mod = 1e9 + 7;
        vector<vector<long long>> dp(k + 1, vector<long long>(m + 1));
        for (int j = 1; j <= m; ++j) dp[1][j] = 1;
        for (int i = 2; i <= n; ++i) {
            vector<vector<long long>> nxt(k + 1, vector<long long>(m + 1));
            for (int c = 1; c <= min(i, k); ++c) {
                long long prefix = 0;
                for (int j = 1; j <= m; ++j) {
                    prefix = (prefix + dp[c - 1][j - 1]) % mod;
                    nxt[c][j] = (dp[c][j] * j + prefix) % mod;
                }
            }
            dp.swap(nxt);
        }
        long long ans = 0;
        for (int j = 1; j <= m; ++j) ans = (ans + dp[k][j]) % mod;
        return ans;
    }
};
```

#### TypeScript

```ts
function numOfArrays(n: number, m: number, k: number): number {
    if (k === 0) {
        return 0;
    }
    const mod = 1e9 + 7;
    let dp: number[][] = Array.from({ length: k + 1 }, () => Array(m + 1).fill(0));
    for (let j = 1; j <= m; ++j) {
        dp[1][j] = 1;
    }
    for (let i = 2; i <= n; ++i) {
        const next: number[][] = Array.from({ length: k + 1 }, () => Array(m + 1).fill(0));
        for (let c = 1; c <= Math.min(i, k); ++c) {
            let prefix = 0;
            for (let j = 1; j <= m; ++j) {
                prefix = (prefix + dp[c - 1][j - 1]) % mod;
                next[c][j] = (dp[c][j] * j + prefix) % mod;
            }
        }
        dp = next;
    }
    return dp[k].reduce((sum, count) => (sum + count) % mod, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
