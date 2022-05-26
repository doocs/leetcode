# [629. K Inverse Pairs Array](https://leetcode.com/problems/k-inverse-pairs-array)

[中文文档](/solution/0600-0699/0629.K%20Inverse%20Pairs%20Array/README.md)

## Description

<p>For an integer array <code>nums</code>, an <strong>inverse pair</strong> is a pair of integers <code>[i, j]</code> where <code>0 &lt;= i &lt; j &lt; nums.length</code> and <code>nums[i] &gt; nums[j]</code>.</p>

<p>Given two integers n and k, return the number of different arrays consist of numbers from <code>1</code> to <code>n</code> such that there are exactly <code>k</code> <strong>inverse pairs</strong>. Since the answer can be huge, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 0
<strong>Output:</strong> 1
<strong>Explanation:</strong> Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pairs.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= k &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def kInversePairs(self, n: int, k: int) -> int:
        mod = 1000000007
        dp, pre = [0] * (k + 1), [0] * (k + 2)
        for i in range(1, n + 1):
            dp[0] = 1

            # dp[i][j] = dp[i - 1][j - (i - 1)] + ... + dp[i - 1][j]
            for j in range(1, k + 1):
                dp[j] = (pre[j + 1] - pre[max(0, j - i + 1)] + mod) % mod

            for j in range(1, k + 2):
                pre[j] = (pre[j - 1] + dp[j - 1]) % mod

        return dp[k]
```

`dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1] + dp[i - 1][j - 2] + ... + dp[i - 1][j - (i - 1)]` ①

`dp[i][j - 1] = dp[i - 1][j - 1] + dp[i - 1][j - 2] + ... + dp[i - 1][j - (i - 1)] + dp[i - 1][j - i]` ②

① - ②, `dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - i]`

```python
class Solution:
    def kInversePairs(self, n: int, k: int) -> int:
        N, MOD = 1010, int(1e9) + 7
        dp = [[0] * N for _ in range(N)]
        dp[1][0] = 1
        for i in range(2, n + 1):
            dp[i][0] = 1
            for j in range(1, k + 1):
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                if j >= i:
                    dp[i][j] -= dp[i - 1][j - i]
                dp[i][j] %= MOD
        return dp[n][k]
```

```python
class Solution:
    def kInversePairs(self, n: int, k: int) -> int:
        N, MOD = 1010, int(1e9) + 7
        dp = [0] * N
        dp[0] = 1
        for i in range(2, n + 1):
            t = dp.copy()
            for j in range(1, k + 1):
                dp[j] = t[j] + dp[j - 1]
                if j >= i:
                    dp[j] -= t[j - i]
                dp[j] %= MOD
        return dp[k]
```

### **Java**

```java
class Solution {

    private static final int MOD = 1000000007;

    public int kInversePairs(int n, int k) {
        int[] dp = new int[k + 1];
        int[] pre = new int[k + 2];
        for (int i = 1; i <= n; i++) {
            dp[0] = 1;

            // dp[i][j] = dp[i - 1][j - (i - 1)] + ... + dp[i - 1][j]
            for (int j = 1; j <= k; j++) {
                dp[j] = (pre[j + 1] - pre[Math.max(0, j - i + 1)] + MOD) % MOD;
            }

            for (int j = 1; j <= k + 1; j++) {
                pre[j] = (pre[j - 1] + dp[j - 1]) % MOD;
            }
        }
        return dp[k];
    }
}
```

```java
class Solution {
    public int kInversePairs(int n, int k) {
        int N = 1010, MOD = (int) (1e9 + 7);
        int[][] dp = new int[N][N];
        dp[1][0] = 1;
        for (int i = 2; i < n + 1; ++i) {
            dp[i][0] = 1;
            for (int j = 1; j < k + 1; ++j) {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
                if (j >= i) {
                    dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + MOD) % MOD;
                }
            }
        }
        return dp[n][k];
    }
}
```

### **Go**

```go
const mod int = 1e9 + 7

func kInversePairs(n int, k int) int {
	dp := make([]int, k+1)
	pre := make([]int, k+2)
	for i := 1; i <= n; i++ {
		dp[0] = 1

		// dp[i][j] = dp[i - 1][j - (i - 1)] + ... + dp[i - 1][j]
		for j := 1; j <= k; j++ {
			dp[j] = (pre[j+1] - pre[max(0, j-i+1)] + mod) % mod
		}

		for j := 1; j <= k+1; j++ {
			pre[j] = (pre[j-1] + dp[j-1]) % mod
		}
	}
	return dp[k]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **C++**

```cpp
class Solution {
private:
    static constexpr int MOD = 1e9 + 7;

public:
    int kInversePairs(int n, int k) {
        vector<int> dp(k + 1), pre(k + 2, 0);
        for (int i = 1; i <= n; ++i) {
            dp[0] = 1;

            // dp[i][j] = dp[i - 1][j - (i - 1)] + ... + dp[i - 1][j]
            for (int j = 1; j <= k; ++j) {
                dp[j] = (pre[j + 1] - pre[max(0, j - i + 1)] + MOD) % MOD;
            }

            for (int j = 1; j <= k + 1; ++j) {
                pre[j] = (pre[j - 1] + dp[j - 1]) % MOD;
            }
        }
        return dp[k];
    }
};
```

### **...**

```

```

<!-- tabs:end -->
