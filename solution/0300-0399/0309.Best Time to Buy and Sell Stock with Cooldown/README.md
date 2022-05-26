# [309. 最佳买卖股票时机含冷冻期](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown)

[English Version](/solution/0300-0399/0309.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Cooldown/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组<meta charset="UTF-8" /><code>prices</code>，其中第&nbsp;<em>&nbsp;</em><code>prices[i]</code>&nbsp;表示第&nbsp;<code><em>i</em></code>&nbsp;天的股票价格 。​</p>

<p>设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:</p>

<ul>
	<li>卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。</li>
</ul>

<p><strong>注意：</strong>你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> prices = [1,2,3,0,2]
<strong>输出: </strong>3 
<strong>解释:</strong> 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> prices = [1]
<strong>输出:</strong> 0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 5000</code></li>
	<li><code>0 &lt;= prices[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划法。

设 f1 表示当天买入股票后的最大利润，f2 表示当天卖出股票后的最大利润，f3 表示当天空仓后的最大利润。

初始第 1 天结束时，`f1 = -prices[0]`，`f2 = 0`，`f3 = 0`。

从第 2 天开始，当天结束时：

-   若买入，则说明前一天空仓，然后今天买入，`f1 = max(f1, f3 - prices[i])`。
-   若卖出，则只能是之前某一天买入，然后今天卖出，`f2 = max(f2, f1 + prices[i])`。
-   若空仓，则只能是之前某一天卖出后，然后今天保持空仓，`f3 = max(f3, f2)`。

最后返回 f2 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        # 买入，卖出，继续空仓
        f1, f2, f3 = -prices[0], 0, 0
        for price in prices[1:]:
            pf1, pf2, pf3 = f1, f2, f3
            f1 = max(pf1, pf3 - price)
            f2 = max(pf2, pf1 + price)
            f3 = max(pf3, pf2)
        return f2
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxProfit(int[] prices) {
        // 买入，卖出，继续空仓
        int f1 = -prices[0], f2 = 0, f3 = 0;
        for (int i = 1; i < prices.length; ++i) {
            int pf1 = f1, pf2 = f2, pf3 = f3;
            f1 = Math.max(pf1, pf3 - prices[i]);
            f2 = Math.max(pf2, pf1 + prices[i]);
            f3 = Math.max(pf3, pf2);
        }
        return f2;
    }
}
```

### **TypeScript**

```ts
function maxProfit(prices: number[]): number {
    const n = prices.length;
    let dp = Array.from({ length: n }, v => new Array(3).fill(0));
    dp[0] = [0, -prices[0], Number.MIN_SAFE_INTEGER];
    for (let i = 1; i < n; i++) {
        dp[i] = [
            Math.max(dp[i - 1][0], dp[i - 1][2]),
            Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]),
            dp[i - 1][1] + prices[i],
        ];
    }
    return Math.max(dp[n - 1][0], dp[n - 1][2]);
}
```

### **C++**

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int f1 = -prices[0], f2 = 0, f3 = 0;
        for (int i = 1; i < prices.size(); ++i) {
            int pf1 = f1, pf2 = f2, pf3 = f3;
            f1 = max(pf1, pf3 - prices[i]);
            f2 = max(pf2, pf1 + prices[i]);
            f3 = max(pf3, pf2);
        }
        return f2;
    }
};
```

### **Go**

```go
func maxProfit(prices []int) int {
	f1, f2, f3 := -prices[0], 0, 0
	for i := 1; i < len(prices); i++ {
		pf1, pf2, pf3 := f1, f2, f3
		f1 = max(pf1, pf3-prices[i])
		f2 = max(pf2, pf1+prices[i])
		f3 = max(pf3, pf2)
	}
	return f2
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
