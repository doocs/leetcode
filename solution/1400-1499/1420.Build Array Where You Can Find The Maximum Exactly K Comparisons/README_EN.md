# [1420. Build Array Where You Can Find The Maximum Exactly K Comparisons](https://leetcode.com/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons)

[中文文档](/solution/1400-1499/1420.Build%20Array%20Where%20You%20Can%20Find%20The%20Maximum%20Exactly%20K%20Comparisons/README.md)

## Description

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
<strong>Explanation:</strong> There are no possible arrays that satisify the mentioned conditions.
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

## Solutions

Dynamic Programming.

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
