# [122. Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii)

[中文文档](/solution/0100-0199/0122.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II/README.md)

## Description

<p>You are given an array <code>prices</code> where <code>prices[i]</code> is the price of a given stock on the <code>i<sup>th</sup></code> day.</p>

<p>Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).</p>

<p><strong>Note:</strong> You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [7,1,5,3,6,4]
<strong>Output:</strong> 7
<strong>Explanation:</strong> Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,2,3,4,5]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> prices = [7,6,4,3,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> In this case, no transaction is done, i.e., max profit = 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= prices[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

Greedy or Dynamic Programming.

<!-- tabs:start -->

### **Python3**

Greedy:

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        res = 0
        for i in range(1, len(prices)):
            t = prices[i] - prices[i - 1]
            res += max(t, 0)
        return res
```

Dynamic Programming:

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        f1, f2 = -prices[0], 0
        for price in prices[1:]:
            f1 = max(f1, f2 - price)
            f2 = max(f2, f1 + price)
        return f2
```

### **Java**

Greedy:

```java
class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; ++i) {
            int t = prices[i] - prices[i - 1];
            res += Math.max(t, 0);
        }
        return res;
    }
}
```

Dynamic Programming:

```java
class Solution {
    public int maxProfit(int[] prices) {
        int f1 = -prices[0], f2 = 0;
        for (int i = 1; i < prices.length; ++i) {
            f1 = Math.max(f1, f2 - prices[i]);
            f2 = Math.max(f2, f1 + prices[i]);
        }
        return f2;
    }
}
```

### **TypeScript**

```ts
function maxProfit(prices: number[]): number {
    let ans = 0;
    for (let i = 1; i < prices.length; i++) {
        ans += Math.max(0, prices[i] - prices[i - 1]);
    }
    return ans;
}
```

### **C++**

Greedy:

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int res = 0;
        for (int i = 1; i < prices.size(); ++i) {
            int t = prices[i] - prices[i - 1];
            res += max(t, 0);
        }
        return res;
    }
};
```

Dynamic Programming:

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int f1 = -prices[0], f2 = 0;
        for (int i = 1; i < prices.size(); ++i) {
            f1 = max(f1, f2 - prices[i]);
            f2 = max(f2, f1 + prices[i]);
        }
        return f2;
    }
};
```

### **Go**

Greedy:

```go
func maxProfit(prices []int) int {
	res := 0
	for i := 1; i < len(prices); i++ {
		t := prices[i] - prices[i-1]
		if t > 0 {
			res += t
		}
	}
	return res
}
```

Dynamic Programming:

```go
func maxProfit(prices []int) int {
	f1, f2 := -prices[0], 0
	for _, price := range prices[1:] {
		f1 = max(f1, f2-price)
		f2 = max(f2, f1+price)
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

### **C#**

Greedy:

```cs
public class Solution {
    public int MaxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.Length; ++i)
        {
            int t = prices[i] - prices[i - 1];
            res += Math.Max(t, 0);
        }
        return res;
    }
}
```

Dynamic Programming:

```cs
public class Solution {
    public int MaxProfit(int[] prices) {
        int f1 = -prices[0], f2 = 0;
        for (int i = 1; i < prices.Length; ++i)
        {
            f1 = Math.Max(f1, f2 - prices[i]);
            f2 = Math.Max(f2, f1 + prices[i]);
        }
        return f2;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
