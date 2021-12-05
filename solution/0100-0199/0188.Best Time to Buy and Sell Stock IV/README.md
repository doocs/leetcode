# [188. 买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv)

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

动态规划

**dp[i][0]** 表示第 *i* 次买入后的收益，

**dp[i][1]** 表示第 *i* 次卖出后的收益。

状态转移方程：

**dp[i][0] = max{dp[i][0], dp[i - 1][1] + prices[i]}**

**dp[i][1] = max{dp[i][1], dp[i][0] + prices[i]}**
<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **C++**

```cpp
class Solution {
public:
    int maxProfit(int k, vector<int>& prices) {
        int dp[k + 1][2];
        memset(dp, 0, sizeof(dp));
        for(int i = 1; i <= k && !prices.empty(); ++i) {
            dp[i][0] = -prices[0];
        }
        for(int i = 1; i < prices.size(); ++i) {
            for(int j = 1; j <= k; ++j) {
                dp[j][0] = max(dp[j][0], dp[j - 1][1] - prices[i]);
                dp[j][1] = max(dp[j][1], dp[j][0] + prices[i]);
            }
        }
        return dp[k][1];
    }
};
```

<!-- tabs:end -->
