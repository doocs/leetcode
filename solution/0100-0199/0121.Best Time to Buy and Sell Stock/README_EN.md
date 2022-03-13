# [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock)

[中文文档](/solution/0100-0199/0121.Best%20Time%20to%20Buy%20and%20Sell%20Stock/README.md)

## Description

<p>You are given an array <code>prices</code> where <code>prices[i]</code> is the price of a given stock on the <code>i<sup>th</sup></code> day.</p>

<p>You want to maximize your profit by choosing a <strong>single day</strong> to buy one stock and choosing a <strong>different day in the future</strong> to sell that stock.</p>

<p>Return <em>the maximum profit you can achieve from this transaction</em>. If you cannot achieve any profit, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [7,1,5,3,6,4]
<strong>Output:</strong> 5
<strong>Explanation:</strong> Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [7,6,4,3,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> In this case, no transactions are done and the max profit = 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= prices[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        res, mi = 0, prices[0]
        for price in prices[1:]:
            res = max(res, price - mi)
            mi = min(mi, price)
        return res
```

### **Java**

```java
class Solution {
    public int maxProfit(int[] prices) {
        int res = 0, mi = prices[0];
        for (int i = 1; i < prices.length; ++i) {
            res = Math.max(res, prices[i] - mi);
            mi = Math.min(mi, prices[i]);
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
        int res = 0, mi = prices[0];
        for (int i = 1; i < prices.size(); ++i) {
            res = max(res, prices[i] - mi);
            mi = min(mi, prices[i]);
        }
        return res;
    }
};
```

### **Go**

```go
func maxProfit(prices []int) int {
	res, mi := 0, prices[0]
	for i := 1; i < len(prices); i++ {
		res = max(res, prices[i]-mi)
		mi = min(min, prices[i])
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function (prices) {
    let res = 0;
    let mi = prices[0];
    for (let i = 1; i < prices.length; ++i) {
        res = Math.max(res, prices[i] - mi);
        mi = Math.min(mi, prices[i]);
    }
    return res;
};
```

### **C#**

```cs
public class Solution {
    public int MaxProfit(int[] prices) {
        int res = 0, mi = prices[0];
        for (int i = 1; i < prices.Length; ++i)
        {
            res = Math.Max(res, prices[i] - mi);
            mi = Math.Min(mi, prices[i]);
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function maxProfit(prices: number[]): number {
    let res = 0;
    let min = Infinity;
    for (const price of prices) {
        res = Math.max(res, price - min);
        min = Math.min(min, price);
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_profit(prices: Vec<i32>) -> i32 {
        let mut res = 0;
        let mut min = i32::MAX;
        for price in prices {
            res = res.max(price - min);
            min = min.min(price);
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
