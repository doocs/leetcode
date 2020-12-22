# [122. Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii)

[中文文档](/solution/0100-0199/0122.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II/README.md)

## Description

<p>Say you have an array for which the <em>i</em><sup>th</sup> element is the price of a given stock on day <em>i</em>.</p>

<p>Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).</p>

<p><strong>Note:</strong> You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> [7,1,5,3,6,4]

<strong>Output:</strong> 7

<strong>Explanation:</strong> Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.

&nbsp;            Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> [1,2,3,4,5]

<strong>Output:</strong> 4

<strong>Explanation:</strong> Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.

&nbsp;            Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are

&nbsp;            engaging multiple transactions at the same time. You must sell before buying again.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> [7,6,4,3,1]

<strong>Output:</strong> 0

<strong>Explanation:</strong> In this case, no transaction is done, i.e. max profit = 0.</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

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
