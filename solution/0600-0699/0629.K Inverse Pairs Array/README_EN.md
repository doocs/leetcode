# [629. K Inverse Pairs Array](https://leetcode.com/problems/k-inverse-pairs-array)

[中文文档](/solution/0600-0699/0629.K%20Inverse%20Pairs%20Array/README.md)

## Description

<p>Given two integers <code>n</code> and <code>k</code>, find how many different arrays consist of numbers from <code>1</code> to <code>n</code> such that there are exactly <code>k</code> inverse pairs.</p>



<p>We define an inverse pair as following: For <code>i<sub>th</sub></code> and <code>j<sub>th</sub></code> element in the array, if <code>i</code> &lt; <code>j</code> and <code>a[i]</code> &gt; <code>a[j]</code> then it&#39;s an inverse pair; Otherwise, it&#39;s not.</p>



<p>Since the answer may be very large, the answer should be modulo 10<sup>9</sup> + 7.</p>



<p><b>Example 1:</b></p>



<pre>

<b>Input:</b> n = 3, k = 0

<b>Output:</b> 1

<b>Explanation:</b>

Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pair.

</pre>



<p>&nbsp;</p>



<p><b>Example 2:</b></p>



<pre>

<b>Input:</b> n = 3, k = 1

<b>Output:</b> 2

<b>Explanation:</b>

The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.

</pre>



<p>&nbsp;</p>



<p><b>Note:</b></p>



<ol>
	<li>The integer <code>n</code> is in the range [1, 1000] and <code>k</code> is in the range [0, 1000].</li>
</ol>



<p>&nbsp;</p>



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
