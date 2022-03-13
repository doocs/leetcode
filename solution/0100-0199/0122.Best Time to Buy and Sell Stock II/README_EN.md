# [122. Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii)

[中文文档](/solution/0100-0199/0122.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II/README.md)

## Description

<p>You are given an integer array <code>prices</code> where <code>prices[i]</code> is the price of a given stock on the <code>i<sup>th</sup></code> day.</p>

<p>On each day, you may decide to buy and/or sell the stock. You can only hold <strong>at most one</strong> share of the stock at any time. However, you can buy it then immediately sell it on the <strong>same day</strong>.</p>

<p>Find and return <em>the <strong>maximum</strong> profit you can achieve</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [7,1,5,3,6,4]
<strong>Output:</strong> 7
<strong>Explanation:</strong> Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,2,3,4,5]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> prices = [7,6,4,3,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
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

```ts
function maxProfit(prices: number[]): number {
    const n = prices.length;
    let res = 0;
    let max = prices[0];
    let min = prices[0];
    for (let i = 1; i < n; i++) {
        const price = prices[i];
        if (price < max) {
            res += max - min;
            max = price;
            min = price;
        } else {
            max = price;
        }
    }
    if (min < max) {
        res += max - min;
    }
    return res;
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

### **Rust**

```rust
impl Solution {
    pub fn max_profit(prices: Vec<i32>) -> i32 {
        let mut res = 0;
        for i in 1..prices.len() {
            res += 0.max(prices[i] - prices[i - 1]);
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
