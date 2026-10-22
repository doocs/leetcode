---
comments: true
difficulty: Hard
rating: 2175
source: Weekly Contest 185 Q4
tags:
    - Dynamic Programming
    - Prefix Sum
---

<!-- problem:start -->

# [1420. Build Array Where You Can Find The Maximum Exactly K Comparisons](https://leetcode.com/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons)

[中文文档](/solution/1400-1499/1420.Build%20Array%20Where%20You%20Can%20Find%20The%20Maximum%20Exactly%20K%20Comparisons/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code>n</code>, <code>m</code> and <code>k</code>. Consider the following algorithm to find the maximum element of an array of positive integers:</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1420.Build%20Array%20Where%20You%20Can%20Find%20The%20Maximum%20Exactly%20K%20Comparisons/images/e.png" style="width: 424px; height: 372px;" />
<p>You should build the array arr which has the following properties:</p>

<ul>
	<li><code>arr</code> has exactly <code>n</code> integers.</li>
	<li><code>1 &lt;= arr[i] &lt;= m</code> where <code>(0 &lt;= i &lt; n)</code>.</li>
	<li>After applying the mentioned algorithm to <code>arr</code>, the value <code>search_cost</code> is equal to <code>k</code>.</li>
</ul>

<p>Return <em>the number of ways</em> to build the array <code>arr</code> under the mentioned conditions. As the answer may grow large, the answer <strong>must be</strong> computed modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2, m = 3, k = 1
<strong>Output:</strong> 6
<strong>Explanation:</strong> The possible arrays are [1, 1], [2, 1], [2, 2], [3, 1], [3, 2] [3, 3]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5, m = 2, k = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no possible arrays that satisfy the mentioned conditions.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 9, m = 1, k = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> The only possible array is [1, 1, 1, 1, 1, 1, 1, 1, 1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= m &lt;= 100</code></li>
	<li><code>0 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> The search cost is how many times the prefix maximum is updated. $n\le 50$, $m\le 100$, so we DP on length, cost, and current maximum.
>
> If position $i$ does not raise the maximum, it has $j$ choices and stays at the same cost. If it writes a new maximum $j$, we sum states whose previous maximum is smaller and whose cost is one less.
>
> $k=0$ is impossible. Sum $dp[n][k][\cdot]$ for the answer.

<!-- thinking:end -->

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

### Solution 2: Prefix Sum Optimization

<!-- thinking:start -->

> **Thinking**
>
> The first DP computes each state by scanning every smaller previous maximum, which adds an $O(m)$ loop. For a fixed length and search cost, adjacent maxima need almost the same sum: the sum for maximum $j$ adds only the state at $j-1$ to the sum for $j-1$. We can maintain that prefix while increasing $j$, so each state takes $O(1)$ time. Only the previous length is needed, so two DP layers suffice.

<!-- thinking:end -->

Let $dp[c][j]$ count arrays of the current length with search cost $c$ and maximum $j$. When we append a value, it either does not raise the maximum (with $j$ choices) or raises a smaller maximum to $j$. For each new length, maintain $prefix = \sum_{x=1}^{j-1} dp_{\mathrm{prev}}[c-1][x]$ while increasing $j$. Then

$$dp_{\mathrm{next}}[c][j] = j \cdot dp_{\mathrm{prev}}[c][j] + prefix \pmod{10^9+7}.$$

Initialize $dp[1][j]=1$ for arrays of length $1$. The answer is the sum of $dp[k][j]$ for $1 \le j \le m$. The time complexity is $O(nkm)$, reduced from $O(nkm^2)$ in Solution 1. Keeping only the previous and next lengths uses $O(km)$ space instead of $O(nkm)$.

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
