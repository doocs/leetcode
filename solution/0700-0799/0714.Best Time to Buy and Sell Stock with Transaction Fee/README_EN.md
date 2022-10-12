# [714. Best Time to Buy and Sell Stock with Transaction Fee](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee)

[中文文档](/solution/0700-0799/0714.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Transaction%20Fee/README.md)

## Description

<p>You are given an array <code>prices</code> where <code>prices[i]</code> is the price of a given stock on the <code>i<sup>th</sup></code> day, and an integer <code>fee</code> representing a transaction fee.</p>

<p>Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.</p>

<p><strong>Note:</strong> You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,3,2,8,4,9], fee = 2
<strong>Output:</strong> 8
<strong>Explanation:</strong> The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,3,7,5,10,3], fee = 3
<strong>Output:</strong> 6
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt; 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= fee &lt; 5 * 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxProfit(self, prices: List[int], fee: int) -> int:
        f1, f2 = -prices[0], 0
        for price in prices[1:]:
            f1 = max(f1, f2 - price)
            f2 = max(f2, f1 + price - fee)
        return f2
```

### **Java**

```java
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int f1 = -prices[0], f2 = 0;
        for (int i = 1; i < prices.length; ++i) {
            f1 = Math.max(f1, f2 - prices[i]);
            f2 = Math.max(f2, f1 + prices[i] - fee);
        }
        return f2;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices, int fee) {
        int f1 = -prices[0], f2 = 0;
        for (int i = 1; i < prices.size(); ++i) {
            f1 = max(f1, f2 - prices[i]);
            f2 = max(f2, f1 + prices[i] - fee);
        }
        return f2;
    }
};
```

### **Go**

```go
func maxProfit(prices []int, fee int) int {
	f1, f2 := -prices[0], 0
	for i := 1; i < len(prices); i++ {
		f1 = max(f1, f2-prices[i])
		f2 = max(f2, f1+prices[i]-fee)
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
