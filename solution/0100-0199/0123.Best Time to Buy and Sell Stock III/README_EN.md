---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0123.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20III/README_EN.md
tags:
    - Array
    - Dynamic Programming
---

# [123. Best Time to Buy and Sell Stock III](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii)

[中文文档](/solution/0100-0199/0123.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20III/README.md)

## Description

<p>You are given an array <code>prices</code> where <code>prices[i]</code> is the price of a given stock on the <code>i<sup>th</sup></code> day.</p>

<p>Find the maximum profit you can achieve. You may complete <strong>at most two transactions</strong>.</p>

<p><strong>Note:</strong> You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [3,3,5,0,0,3,1,4]
<strong>Output:</strong> 6
<strong>Explanation:</strong> Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,2,3,4,5]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> prices = [7,6,4,3,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> In this case, no transaction is done, i.e. max profit = 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

We define the following variables:

-   `f1` represents the maximum profit after the first purchase of the stock;
-   `f2` represents the maximum profit after the first sale of the stock;
-   `f3` represents the maximum profit after the second purchase of the stock;
-   `f4` represents the maximum profit after the second sale of the stock.

During the traversal, we directly calculate `f1`, `f2`, `f3`, `f4`. We consider that buying and selling on the same day will result in a profit of $0$, which will not affect the answer.

Finally, return `f4`.

The time complexity is $O(n)$, where $n$ is the length of the `prices` array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        # 第一次买入，第一次卖出，第二次买入，第二次卖出
        f1, f2, f3, f4 = -prices[0], 0, -prices[0], 0
        for price in prices[1:]:
            f1 = max(f1, -price)
            f2 = max(f2, f1 + price)
            f3 = max(f3, f2 - price)
            f4 = max(f4, f3 + price)
        return f4
```

```java
class Solution {
    public int maxProfit(int[] prices) {
        // 第一次买入，第一次卖出，第二次买入，第二次卖出
        int f1 = -prices[0], f2 = 0, f3 = -prices[0], f4 = 0;
        for (int i = 1; i < prices.length; ++i) {
            f1 = Math.max(f1, -prices[i]);
            f2 = Math.max(f2, f1 + prices[i]);
            f3 = Math.max(f3, f2 - prices[i]);
            f4 = Math.max(f4, f3 + prices[i]);
        }
        return f4;
    }
}
```

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int f1 = -prices[0], f2 = 0, f3 = -prices[0], f4 = 0;
        for (int i = 1; i < prices.size(); ++i) {
            f1 = max(f1, -prices[i]);
            f2 = max(f2, f1 + prices[i]);
            f3 = max(f3, f2 - prices[i]);
            f4 = max(f4, f3 + prices[i]);
        }
        return f4;
    }
};
```

```go
func maxProfit(prices []int) int {
	f1, f2, f3, f4 := -prices[0], 0, -prices[0], 0
	for i := 1; i < len(prices); i++ {
		f1 = max(f1, -prices[i])
		f2 = max(f2, f1+prices[i])
		f3 = max(f3, f2-prices[i])
		f4 = max(f4, f3+prices[i])
	}
	return f4
}
```

```ts
function maxProfit(prices: number[]): number {
    let [f1, f2, f3, f4] = [-prices[0], 0, -prices[0], 0];
    for (let i = 1; i < prices.length; ++i) {
        f1 = Math.max(f1, -prices[i]);
        f2 = Math.max(f2, f1 + prices[i]);
        f3 = Math.max(f3, f2 - prices[i]);
        f4 = Math.max(f4, f3 + prices[i]);
    }
    return f4;
}
```

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn max_profit(prices: Vec<i32>) -> i32 {
        let mut f1 = -prices[0];
        let mut f2 = 0;
        let mut f3 = -prices[0];
        let mut f4 = 0;
        let n = prices.len();

        for i in 1..n {
            f1 = std::cmp::max(f1, -prices[i]);
            f2 = std::cmp::max(f2, f1 + prices[i]);
            f3 = std::cmp::max(f3, f2 - prices[i]);
            f4 = std::cmp::max(f4, f3 + prices[i]);
        }

        f4
    }
}
```

```cs
public class Solution {
    public int MaxProfit(int[] prices) {
        int f1 = -prices[0], f2 = 0, f3 = -prices[0], f4 = 0;
        for (int i = 1; i < prices.Length; ++i) {
            f1 = Math.Max(f1, -prices[i]);
            f2 = Math.Max(f2, f1 + prices[i]);
            f3 = Math.Max(f3, f2 - prices[i]);
            f4 = Math.Max(f4, f3 + prices[i]);
        }
        return f4;
    }
}
```

<!-- tabs:end -->

<!-- end -->
