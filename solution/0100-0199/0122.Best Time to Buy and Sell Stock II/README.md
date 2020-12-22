# [122. 买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii)

[English Version](/solution/0100-0199/0122.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个数组，它的第&nbsp;<em>i</em> 个元素是一支给定股票第 <em>i</em> 天的价格。</p>

<p>设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。</p>

<p><strong>注意：</strong>你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> [7,1,5,3,6,4]
<strong>输出:</strong> 7
<strong>解释:</strong> 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
&nbsp;    随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> [1,2,3,4,5]
<strong>输出:</strong> 4
<strong>解释:</strong> 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
&nbsp;    注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
&nbsp;    因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre><strong>输入:</strong> [7,6,4,3,1]
<strong>输出:</strong> 0
<strong>解释:</strong> 在这种情况下, 没有交易完成, 所以最大利润为 0。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

所有上涨交易日都做买卖，所有下跌交易日都不做买卖，这样便能实现利润最大化。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        if not prices:
            return 0
        res = 0
        for i in range(1, len(prices)):
            t = prices[i] - prices[i - 1]
            res += max(t, 0)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null) return 0;
        int res = 0;
        for (int i = 1, n = prices.length; i < n; ++i) {
            // 策略是所有上涨交易日都做买卖，所以下跌交易日都不做买卖
            int t = prices[i] - prices[i - 1];
            res += Math.max(t, 0);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int res = 0, n;
        if ((n = prices.size()) == 0) return 0;
        for (int i = 1; i < n; ++i)
        {
            int t = prices[i] - prices[i - 1];
            res += max(0, t);
        }
        return res;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
