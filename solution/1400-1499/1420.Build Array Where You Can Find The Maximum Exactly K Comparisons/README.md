# [1420. 生成数组](https://leetcode.cn/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons)

[English Version](/solution/1400-1499/1420.Build%20Array%20Where%20You%20Can%20Find%20The%20Maximum%20Exactly%20K%20Comparisons/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你三个整数 <code>n</code>、<code>m</code> 和 <code>k</code> 。下图描述的算法用于找出正整数数组中最大的元素。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1420.Build%20Array%20Where%20You%20Can%20Find%20The%20Maximum%20Exactly%20K%20Comparisons/images/e.png" style="height: 372px; width: 424px;"></p>

<p>请你生成一个具有下述属性的数组 <code>arr</code> ：</p>

<ul>
	<li><code>arr</code> 中有 <code>n</code> 个整数。</li>
	<li><code>1 &lt;= arr[i] &lt;= m</code> 其中 <code>(0 &lt;= i &lt; n)</code> 。</li>
	<li>将上面提到的算法应用于 <code>arr</code> ，<code>search_cost</code> 的值等于 <code>k</code> 。</li>
</ul>

<p>返回上述条件下生成数组 <code>arr</code> 的 <strong>方法数</strong> ，由于答案可能会很大，所以 <strong>必须</strong> 对 <code>10^9 + 7</code> 取余。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 2, m = 3, k = 1
<strong>输出：</strong>6
<strong>解释：</strong>可能的数组分别为 [1, 1], [2, 1], [2, 2], [3, 1], [3, 2] [3, 3]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 5, m = 2, k = 3
<strong>输出：</strong>0
<strong>解释：</strong>没有数组可以满足上述条件
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 9, m = 1, k = 1
<strong>输出：</strong>1
<strong>解释：</strong>可能的数组只有 [1, 1, 1, 1, 1, 1, 1, 1, 1]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>n = 50, m = 100, k = 25
<strong>输出：</strong>34549172
<strong>解释：</strong>不要忘了对 1000000007 取余
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>n = 37, m = 17, k = 7
<strong>输出：</strong>418930126
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= m &lt;= 100</code></li>
	<li><code>0 &lt;= k &lt;= n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

假设 $dp[i][c][j]$ 表示长度为 $i$，搜索代价为 $c$，且最大值为 $j$ 的方案数。考虑第 $i$ 个数：

若第 $i$ 个数没有改变搜索代价，说明它不严格大于前 $i-1$ 个数，也就是说，$dp[i][c][j]$ 是从 $dp[i-1][c][j]$ 转移而来，即数组的前 $i-1$ 个数的最大值已经是 $j$，并且第 $i$ 个数没有改变最大值，因此第 $i$ 个数的可选范围是 $[1,..j]$，共有 $j$ 种可选方案。即

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

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
