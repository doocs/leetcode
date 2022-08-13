# [188. 买卖股票的最佳时机 IV](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv)

[English Version](/solution/0100-0199/0188.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20IV/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组 <code>prices</code> ，它的第<em> </em><code>i</code> 个元素 <code>prices[i]</code> 是一支给定的股票在第 <code>i</code><em> </em>天的价格。</p>

<p>设计一个算法来计算你所能获取的最大利润。你最多可以完成 <strong>k</strong> 笔交易。</p>

<p><strong>注意：</strong>你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>k = 2, prices = [2,4,1]
<strong>输出：</strong>2
<strong>解释：</strong>在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>k = 2, prices = [3,2,6,5,0,3]
<strong>输出：</strong>7
<strong>解释：</strong>在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= k <= 100</code></li>
	<li><code>0 <= prices.length <= 1000</code></li>
	<li><code>0 <= prices[i] <= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划。

`dp[i][j][2]` 表示第 i 天，进行了 j 次交易后是否持有股票。其中 `dp[i][j][0]` 表示未持有股票，`dp[i][j][1]` 表示持有股票。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

`dp[i][0]` 表示第 _i_ 次买入后的收益，`dp[i][1]` 表示第 _i_ 次卖出后的收益。

状态转移方程：

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
