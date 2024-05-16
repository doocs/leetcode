---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0714.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Transaction%20Fee/README_EN.md
tags:
    - Greedy
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [714. Best Time to Buy and Sell Stock with Transaction Fee](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee)

[中文文档](/solution/0700-0799/0714.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Transaction%20Fee/README.md)

## Description

<p>You are given an array <code>prices</code> where <code>prices[i]</code> is the price of a given stock on the <code>i<sup>th</sup></code> day, and an integer <code>fee</code> representing a transaction fee.</p>

<p>Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).</li>
	<li>The transaction fee is only charged once for each stock purchase and sale.</li>
</ul>

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

<!-- solution:start -->

### Solution 1: Memoization

We design a function $dfs(i, j)$, which represents the maximum profit that can be obtained starting from day $i$ with state $j$. Here, $j$ can take the values $0$ and $1$, representing not holding and holding a stock, respectively. The answer is $dfs(0, 0)$.

The execution logic of the function $dfs(i, j)$ is as follows:

If $i \geq n$, there are no more stocks to trade, so we return $0$.

Otherwise, we can choose not to trade, in which case $dfs(i, j) = dfs(i + 1, j)$. We can also choose to trade stocks. If $j \gt 0$, it means that we currently hold a stock and can sell it. In this case, $dfs(i, j) = prices[i] + dfs(i + 1, 0) - fee$. If $j = 0$, it means that we currently do not hold a stock and can buy one. In this case, $dfs(i, j) = -prices[i] + dfs(i + 1, 1)$. We take the maximum value as the return value of the function $dfs(i, j)$.

The answer is $dfs(0, 0)$.

To avoid redundant calculations, we use memoization to record the return value of $dfs(i, j)$ in an array $f$. If $f[i][j]$ is not equal to $-1$, it means that we have already calculated it, so we can directly return $f[i][j]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $prices$.

<!-- tabs:start -->

```python
class Solution:
    def maxProfit(self, prices: List[int], fee: int) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i >= len(prices):
                return 0
            ans = dfs(i + 1, j)
            if j:
                ans = max(ans, prices[i] + dfs(i + 1, 0) - fee)
            else:
                ans = max(ans, -prices[i] + dfs(i + 1, 1))
            return ans

        return dfs(0, 0)
```

```java
class Solution {
    private Integer[][] f;
    private int[] prices;
    private int fee;

    public int maxProfit(int[] prices, int fee) {
        f = new Integer[prices.length][2];
        this.prices = prices;
        this.fee = fee;
        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        if (i >= prices.length) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = dfs(i + 1, j);
        if (j > 0) {
            ans = Math.max(ans, prices[i] + dfs(i + 1, 0) - fee);
        } else {
            ans = Math.max(ans, -prices[i] + dfs(i + 1, 1));
        }
        return f[i][j] = ans;
    }
}
```

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices, int fee) {
        int n = prices.size();
        int f[n][2];
        memset(f, -1, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) {
            if (i >= prices.size()) {
                return 0;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            int ans = dfs(i + 1, j);
            if (j) {
                ans = max(ans, prices[i] + dfs(i + 1, 0) - fee);
            } else {
                ans = max(ans, -prices[i] + dfs(i + 1, 1));
            }
            return f[i][j] = ans;
        };
        return dfs(0, 0);
    }
};
```

```go
func maxProfit(prices []int, fee int) int {
	n := len(prices)
	f := make([][2]int, n)
	for i := range f {
		f[i] = [2]int{-1, -1}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= n {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		ans := dfs(i+1, j)
		if j > 0 {
			ans = max(ans, prices[i]+dfs(i+1, 0)-fee)
		} else {
			ans = max(ans, -prices[i]+dfs(i+1, 1))
		}
		f[i][j] = ans
		return ans
	}
	return dfs(0, 0)
}
```

```ts
function maxProfit(prices: number[], fee: number): number {
    const n = prices.length;
    const f: number[][] = Array.from({ length: n }, () => [-1, -1]);
    const dfs = (i: number, j: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        let ans = dfs(i + 1, j);
        if (j) {
            ans = Math.max(ans, prices[i] + dfs(i + 1, 0) - fee);
        } else {
            ans = Math.max(ans, -prices[i] + dfs(i + 1, 1));
        }
        return (f[i][j] = ans);
    };
    return dfs(0, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

We define $f[i][j]$ as the maximum profit that can be obtained up to day $i$ with state $j$. Here, $j$ can take the values $0$ and $1$, representing not holding and holding a stock, respectively. We initialize $f[0][0] = 0$ and $f[0][1] = -prices[0]$.

When $i \geq 1$, if we do not hold a stock at the current day, then $f[i][0]$ can be obtained by transitioning from $f[i - 1][0]$ and $f[i - 1][1] + prices[i] - fee$, i.e., $f[i][0] = \max(f[i - 1][0], f[i - 1][1] + prices[i] - fee)$. If we hold a stock at the current day, then $f[i][1]$ can be obtained by transitioning from $f[i - 1][1]$ and $f[i - 1][0] - prices[i]$, i.e., $f[i][1] = \max(f[i - 1][1], f[i - 1][0] - prices[i])$. The final answer is $f[n - 1][0]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $prices$.

We notice that the transition of the state $f[i][]$ only depends on $f[i - 1][]$ and $f[i - 2][]$. Therefore, we can use two variables $f_0$ and $f_1$ to replace the array $f$, reducing the space complexity to $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def maxProfit(self, prices: List[int], fee: int) -> int:
        n = len(prices)
        f = [[0] * 2 for _ in range(n)]
        f[0][1] = -prices[0]
        for i in range(1, n):
            f[i][0] = max(f[i - 1][0], f[i - 1][1] + prices[i] - fee)
            f[i][1] = max(f[i - 1][1], f[i - 1][0] - prices[i])
        return f[n - 1][0]
```

```java
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] f = new int[n][2];
        f[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i] - fee);
            f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] - prices[i]);
        }
        return f[n - 1][0];
    }
}
```

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices, int fee) {
        int n = prices.size();
        int f[n][2];
        memset(f, 0, sizeof(f));
        f[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = max(f[i - 1][0], f[i - 1][1] + prices[i] - fee);
            f[i][1] = max(f[i - 1][1], f[i - 1][0] - prices[i]);
        }
        return f[n - 1][0];
    }
};
```

```go
func maxProfit(prices []int, fee int) int {
	n := len(prices)
	f := make([][2]int, n)
	f[0][1] = -prices[0]
	for i := 1; i < n; i++ {
		f[i][0] = max(f[i-1][0], f[i-1][1]+prices[i]-fee)
		f[i][1] = max(f[i-1][1], f[i-1][0]-prices[i])
	}
	return f[n-1][0]
}
```

```ts
function maxProfit(prices: number[], fee: number): number {
    const n = prices.length;
    const f: number[][] = Array.from({ length: n }, () => [0, 0]);
    f[0][1] = -prices[0];
    for (let i = 1; i < n; ++i) {
        f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i] - fee);
        f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] - prices[i]);
    }
    return f[n - 1][0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3

<!-- tabs:start -->

```python
class Solution:
    def maxProfit(self, prices: List[int], fee: int) -> int:
        f0, f1 = 0, -prices[0]
        for x in prices[1:]:
            f0, f1 = max(f0, f1 + x - fee), max(f1, f0 - x)
        return f0
```

```java
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int f0 = 0, f1 = -prices[0];
        for (int i = 1; i < prices.length; ++i) {
            int g0 = Math.max(f0, f1 + prices[i] - fee);
            f1 = Math.max(f1, f0 - prices[i]);
            f0 = g0;
        }
        return f0;
    }
}
```

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices, int fee) {
        int f0 = 0, f1 = -prices[0];
        for (int i = 1; i < prices.size(); ++i) {
            int g0 = max(f0, f1 + prices[i] - fee);
            f1 = max(f1, f0 - prices[i]);
            f0 = g0;
        }
        return f0;
    }
};
```

```go
func maxProfit(prices []int, fee int) int {
	f0, f1 := 0, -prices[0]
	for _, x := range prices[1:] {
		f0, f1 = max(f0, f1+x-fee), max(f1, f0-x)
	}
	return f0
}
```

```ts
function maxProfit(prices: number[], fee: number): number {
    const n = prices.length;
    let [f0, f1] = [0, -prices[0]];
    for (const x of prices.slice(1)) {
        [f0, f1] = [Math.max(f0, f1 + x - fee), Math.max(f1, f0 - x)];
    }
    return f0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
