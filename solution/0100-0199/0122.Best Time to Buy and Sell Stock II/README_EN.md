# [122. Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii)

[中文文档](/solution/0100-0199/0122.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II/README.md)

## Description

<p>You are given an integer array <code>prices</code> where <code>prices[i]</code> is the price of a given stock on the <code>i<sup>th</sup></code> day.</p>

<p>On each day, you may decide to buy and/or sell the stock. You can only hold <strong>at most one</strong> share of the stock at any time. However, you can buy it then immediately sell it on the <strong>same day</strong>.</p>

<p>Find and return <em>the <strong>maximum</strong> profit you can achieve</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [7,1,5,3,6,4]
<strong>Output:</strong> 7
<strong>Explanation:</strong> Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,2,3,4,5]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

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

**Solution 1: Greedy**

From the second day, if the stock price on that day is greater than the previous day, buy it on the previous day and sell it on that day to get a profit. If the stock price on that day is less than the previous day, do not buy it or sell it. That is to say, all the rising trading days are bought and sold, and all the falling trading days are not bought or sold, and the final profit is the maximum.

The time complexity is $O(n)$, where $n$ is the length of the array `prices`. The space complexity is $O(1)$.

**Solution 2: Dynamic Programming**

Let $f[i][j]$ represent the maximum profit after the $i$th day of trading, where $j$ represents whether the current stock is held, and $j=0$ when the stock is held, and $j=1$ when the stock is not held. The initial state is $f[0][0]=-prices[0]$, and all other states are $0$.

If the current stock is held, it may be that the stock was held on the previous day and nothing was done today, that is, $f[i][0]=f[i-1][0]$; or the stock was not held the previous day and the stock was bought today, that is, $f[i][0]=f[i-1][1]-prices[i]$.

If the current stock is not held, it may be that the stock was not held the previous day and nothing was done today, that is, $f[i][1]=f[i-1][1]$; or the stock was held the previous day and the stock was sold today, that is, $f[i][1]=f[i-1][0]+prices[i]$.

Therefore, we can write down the state transition equation:

$$
\begin{cases}
f[i][0]=\max(f[i-1][0],f[i-1][1]-prices[i])\\
f[i][1]=\max(f[i-1][1],f[i-1][0]+prices[i])
\end{cases}
$$

The final answer is $f[n-1][1]$, where $n$ is the length of the array `prices`.

The time complexity is $O(n)$ and the space complexity is $O(n)$. $n$ is the length of the array `prices`.

**Solution 3: Dynamic Programming (space optimization)**

We can find that in Solution 2, the state of the $i$th day only depends on the state of the $i-1$th day, so we can only use two variables to maintain the state of the $i-1$th day. Therefore, the space complexity can be optimized to $O(1)$.

Time complexity $O(n)$, where $n$ is the length of the array `prices`. Space complexity $O(1)$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        return sum(max(0, b - a) for a, b in pairwise(prices))
```

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        n = len(prices)
        f = [[0] * 2 for _ in range(n)]
        f[0][0] = -prices[0]
        for i in range(1, n):
            f[i][0] = max(f[i - 1][0], f[i - 1][1] - prices[i])
            f[i][1] = max(f[i - 1][1], f[i - 1][0] + prices[i])
        return f[n - 1][1]
```

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        n = len(prices)
        f = [-prices[0], 0]
        for i in range(1, n):
            g = [0] * 2
            g[0] = max(f[0], f[1] - prices[i])
            g[1] = max(f[1], f[0] + prices[i])
            f = g
        return f[1]
```

### **Java**

```java
class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] f = new int[n][2];
        f[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] - prices[i]);
            f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] + prices[i]);
        }
        return f[n - 1][1];
    }
}
```

```java
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] f = new int[] {-prices[0], 0};
        for (int i = 1; i < n; ++i) {
            int[] g = new int[2];
            g[0] = Math.max(f[0], f[1] - prices[i]);
            g[1] = Math.max(f[1], f[0] + prices[i]);
            f = g;
        }
        return f[1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int ans = 0;
        for (int i = 1; i < prices.size(); ++i) ans += max(0, prices[i] - prices[i - 1]);
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        int f[n][2];
        f[0][0] = -prices[0];
        f[0][1] = 0;
        for (int i = 1; i < n; ++i) {
            f[i][0] = max(f[i - 1][0], f[i - 1][1] - prices[i]);
            f[i][1] = max(f[i - 1][1], f[i - 1][0] + prices[i]);
        }
        return f[n - 1][1];
    }
};
```

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        int f[2] = {-prices[0], 0};
        for (int i = 1; i < n; ++i) {
            int g[2];
            g[0] = max(f[0], f[1] - prices[i]);
            g[1] = max(f[1], f[0] + prices[i]);
            f[0] = g[0], f[1] = g[1];
        }
        return f[1];
    }
};
```

### **Go**

```go
func maxProfit(prices []int) (ans int) {
	for i, v := range prices[1:] {
		t := v - prices[i]
		if t > 0 {
			ans += t
		}
	}
	return
}
```

```go
func maxProfit(prices []int) int {
	n := len(prices)
	f := make([][2]int, n)
	f[0][0] = -prices[0]
	for i := 1; i < n; i++ {
		f[i][0] = max(f[i-1][0], f[i-1][1]-prices[i])
		f[i][1] = max(f[i-1][1], f[i-1][0]+prices[i])
	}
	return f[n-1][1]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func maxProfit(prices []int) int {
	n := len(prices)
	f := [2]int{-prices[0], 0}
	for i := 1; i < n; i++ {
		g := [2]int{}
		g[0] = max(f[0], f[1]-prices[i])
		g[1] = max(f[1], f[0]+prices[i])
		f = g
	}
	return f[1]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
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

### **C#**

```cs
public class Solution {
    public int MaxProfit(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.Length; ++i) {
            ans += Math.Max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}
```

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

### **JavaScript**

```js
/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function (prices) {
    let ans = 0;
    for (let i = 1; i < prices.length; i++) {
        ans += Math.max(0, prices[i] - prices[i - 1]);
    }
    return ans;
};
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
