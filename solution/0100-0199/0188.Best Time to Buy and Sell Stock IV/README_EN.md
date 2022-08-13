# [188. Best Time to Buy and Sell Stock IV](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv)

[中文文档](/solution/0100-0199/0188.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20IV/README.md)

## Description

<p>You are given an integer array <code>prices</code> where <code>prices[i]</code> is the price of a given stock on the <code>i<sup>th</sup></code> day, and an integer <code>k</code>.</p>

<p>Find the maximum profit you can achieve. You may complete at most <code>k</code> transactions.</p>

<p><strong>Note:</strong> You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> k = 2, prices = [2,4,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> k = 2, prices = [3,2,6,5,0,3]
<strong>Output:</strong> 7
<strong>Explanation:</strong> Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= k &lt;= 100</code></li>
	<li><code>0 &lt;= prices.length &lt;= 1000</code></li>
	<li><code>0 &lt;= prices[i] &lt;= 1000</code></li>
</ul>

## Solutions

Dynamic programming.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxProfit(self, k: int, prices: List[int]) -> int:
        n = len(prices)
        if n < 2:
            return 0
        dp = [[[0] * 2 for _ in range(k + 1)] for _ in range(n)]
        for i in range(1, k + 1):
            dp[0][i][1] = -prices[0]
        for i in range(1, n):
            for j in range(1, k + 1):
                dp[i][j][0] = max(dp[i - 1][j][1] + prices[i], dp[i - 1][j][0])
                dp[i][j][1] = max(dp[i - 1][j - 1][0] - prices[i], dp[i - 1][j][1])
        return dp[-1][k][0]
```

### **Java**

```java
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        int[][][] dp = new int[n][k + 1][2];
        for (int i = 1; i <= k; ++i) {
            dp[0][i][1] = -prices[0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j <= k; ++j) {
                dp[i][j][0] = Math.max(dp[i - 1][j][1] + prices[i], dp[i - 1][j][0]);
                dp[i][j][1] = Math.max(dp[i - 1][j - 1][0] - prices[i], dp[i - 1][j][1]);
            }
        }
        return dp[n - 1][k][0];
    }
}
```

### **C++**

`dp[i][0]` Indicates the income after the _i<sup>th_ purchase, `dp[i][1]` Indicates the income after the _i<sup>th_ sell.

State transition equation:

```bash
dp[i][0] = max{dp[i][0], dp[i - 1][1] - prices[i]}

dp[i][1] = max{dp[i][1], dp[i][0] + prices[i]}
```

```cpp
class Solution {
public:
    int maxProfit(int k, vector<int>& prices) {
        int dp[k + 1][2];
        memset(dp, 0, sizeof(dp));
        for (int i = 1; i <= k && !prices.empty(); ++i) {
            dp[i][0] = -prices[0];
        }
        for (int i = 1; i < prices.size(); ++i) {
            for (int j = 1; j <= k; ++j) {
                dp[j][0] = max(dp[j][0], dp[j - 1][1] - prices[i]);
                dp[j][1] = max(dp[j][1], dp[j][0] + prices[i]);
            }
        }
        return dp[k][1];
    }
};
```

### **Go**

```go
func maxProfit(k int, prices []int) int {
	n := len(prices)
	if n < 2 {
		return 0
	}
	dp := make([][][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([][]int, k+1)
		for j := 0; j <= k; j++ {
			dp[i][j] = make([]int, 2)
		}
	}
	for i := 1; i <= k; i++ {
		dp[0][i][1] = -prices[0]
	}
	for i := 1; i < n; i++ {
		for j := 1; j <= k; j++ {
			dp[i][j][0] = max(dp[i-1][j][1]+prices[i], dp[i-1][j][0])
			dp[i][j][1] = max(dp[i-1][j-1][0]-prices[i], dp[i-1][j][1])
		}
	}
	return dp[n-1][k][0]
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
