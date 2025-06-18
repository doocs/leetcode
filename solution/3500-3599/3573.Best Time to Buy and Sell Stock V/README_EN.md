---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3573.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20V/README_EN.md
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3573. Best Time to Buy and Sell Stock V](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-v)

[中文文档](/solution/3500-3599/3573.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20V/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>prices</code> where <code>prices[i]</code> is the price of a stock in dollars on the <code>i<sup>th</sup></code> day, and an integer <code>k</code>.</p>

<p>You are allowed to make at most <code>k</code> transactions, where each transaction can be either of the following:</p>

<ul>
	<li>
	<p><strong>Normal transaction</strong>: Buy on day <code>i</code>, then sell on a later day <code>j</code> where <code>i &lt; j</code>. You profit <code>prices[j] - prices[i]</code>.</p>
	</li>
	<li>
	<p><strong>Short selling transaction</strong>: Sell on day <code>i</code>, then buy back on a later day <code>j</code> where <code>i &lt; j</code>. You profit <code>prices[i] - prices[j]</code>.</p>
	</li>
</ul>

<p><strong>Note</strong> that you must complete each transaction before starting another. Additionally, you can't buy or sell on the same day you are selling or buying back as part of a previous transaction.</p>

<p>Return the <strong>maximum</strong> total profit you can earn by making <strong>at most</strong> <code>k</code> transactions.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [1,7,9,8,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">14</span></p>

<p><strong>Explanation:</strong></p>
We can make $14 of profit through 2 transactions:

<ul>
	<li>A normal transaction: buy the stock on day 0 for $1 then sell it on day 2 for $9.</li>
	<li>A short selling transaction: sell the stock on day 3 for $8 then buy back on day 4 for $2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [12,16,19,19,8,1,19,13,9], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">36</span></p>

<p><strong>Explanation:</strong></p>
We can make $36 of profit through 3 transactions:

<ul>
	<li>A normal transaction: buy the stock on day 0 for $12 then sell it on day 2 for $19.</li>
	<li>A short selling transaction: sell the stock on day 3 for $19 then buy back on day 4 for $8.</li>
	<li>A normal transaction: buy the stock on day 5 for $1 then sell it on day 6 for $19.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= prices.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= prices.length / 2</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j][k]$ to represent the maximum profit on the first $i$ days, with at most $j$ transactions, and the current state $k$. Here, the state $k$ has three possibilities:

-   If $k = 0$, it means we do not hold any stock.
-   If $k = 1$, it means we are holding a stock.
-   If $k = 2$, it means we are holding a short position.

Initially, for any $j \in [1, k]$, we have $f[0][j][1] = -prices[0]$ and $f[0][j][2] = prices[0]$. This means buying a stock or opening a short position on day 0.

Next, we update $f[i][j][k]$ using state transitions. For each day $i$ and each transaction $j$, we update according to the current state $k$:

-   If $k = 0$, meaning no stock is held, this state can be reached from three situations:
    -   No stock was held the previous day.
    -   A stock was held the previous day and sold today.
    -   A short position was held the previous day and bought back today.
-   If $k = 1$, meaning a stock is held, this state can be reached from two situations:
    -   A stock was held the previous day.
    -   No stock was held the previous day and a stock is bought today.
-   If $k = 2$, meaning a short position is held, this state can be reached from two situations:
    -   A short position was held the previous day.
    -   No stock was held the previous day and a short position is opened (sold) today.

That is, for $1 \leq i < n$ and $1 \leq j \leq k$, we have the following state transition equations:

$$
\begin{aligned}
f[i][j][0] &= \max(f[i - 1][j][0], f[i - 1][j][1] + prices[i], f[i - 1][j][2] - prices[i]) \\
f[i][j][1] &= \max(f[i - 1][j][1], f[i - 1][j - 1][0] - prices[i]) \\
f[i][j][2] &= \max(f[i - 1][j][2], f[i - 1][j - 1][0] + prices[i])
\end{aligned}
$$

Finally, we return $f[n - 1][k][0]$, which is the maximum profit after at most $k$ transactions and not holding any stock at the end of $n$ days.

The time complexity is $O(n \times k)$, and the space complexity is $O(n \times k)$, where $n$ is the length of the array $\textit{prices}$ and $k$ is the maximum number of transactions.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumProfit(self, prices: List[int], k: int) -> int:
        n = len(prices)
        f = [[[0] * 3 for _ in range(k + 1)] for _ in range(n)]
        for j in range(1, k + 1):
            f[0][j][1] = -prices[0]
            f[0][j][2] = prices[0]
        for i in range(1, n):
            for j in range(1, k + 1):
                f[i][j][0] = max(
                    f[i - 1][j][0],
                    f[i - 1][j][1] + prices[i],
                    f[i - 1][j][2] - prices[i],
                )
                f[i][j][1] = max(f[i - 1][j][1], f[i - 1][j - 1][0] - prices[i])
                f[i][j][2] = max(f[i - 1][j][2], f[i - 1][j - 1][0] + prices[i])
        return f[n - 1][k][0]
```

#### Java

```java
class Solution {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        long[][][] f = new long[n][k + 1][3];
        for (int j = 1; j <= k; ++j) {
            f[0][j][1] = -prices[0];
            f[0][j][2] = prices[0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[i][j][0] = Math.max(f[i - 1][j][0],
                    Math.max(f[i - 1][j][1] + prices[i], f[i - 1][j][2] - prices[i]));
                f[i][j][1] = Math.max(f[i - 1][j][1], f[i - 1][j - 1][0] - prices[i]);
                f[i][j][2] = Math.max(f[i - 1][j][2], f[i - 1][j - 1][0] + prices[i]);
            }
        }
        return f[n - 1][k][0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumProfit(vector<int>& prices, int k) {
        int n = prices.size();
        long long f[n][k + 1][3];
        memset(f, 0, sizeof(f));
        for (int j = 1; j <= k; ++j) {
            f[0][j][1] = -prices[0];
            f[0][j][2] = prices[0];
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[i][j][0] = max({f[i - 1][j][0], f[i - 1][j][1] + prices[i], f[i - 1][j][2] - prices[i]});
                f[i][j][1] = max(f[i - 1][j][1], f[i - 1][j - 1][0] - prices[i]);
                f[i][j][2] = max(f[i - 1][j][2], f[i - 1][j - 1][0] + prices[i]);
            }
        }

        return f[n - 1][k][0];
    }
};
```

#### Go

```go
func maximumProfit(prices []int, k int) int64 {
	n := len(prices)
	f := make([][][3]int, n)
	for i := range f {
		f[i] = make([][3]int, k+1)
	}

	for j := 1; j <= k; j++ {
		f[0][j][1] = -prices[0]
		f[0][j][2] = prices[0]
	}

	for i := 1; i < n; i++ {
		for j := 1; j <= k; j++ {
			f[i][j][0] = max(f[i-1][j][0], f[i-1][j][1]+prices[i], f[i-1][j][2]-prices[i])
			f[i][j][1] = max(f[i-1][j][1], f[i-1][j-1][0]-prices[i])
			f[i][j][2] = max(f[i-1][j][2], f[i-1][j-1][0]+prices[i])
		}
	}

	return int64(f[n-1][k][0])
}
```

#### TypeScript

```ts
function maximumProfit(prices: number[], k: number): number {
    const n = prices.length;
    const f: number[][][] = Array.from({ length: n }, () =>
        Array.from({ length: k + 1 }, () => Array(3).fill(0)),
    );

    for (let j = 1; j <= k; ++j) {
        f[0][j][1] = -prices[0];
        f[0][j][2] = prices[0];
    }

    for (let i = 1; i < n; ++i) {
        for (let j = 1; j <= k; ++j) {
            f[i][j][0] = Math.max(
                f[i - 1][j][0],
                f[i - 1][j][1] + prices[i],
                f[i - 1][j][2] - prices[i],
            );
            f[i][j][1] = Math.max(f[i - 1][j][1], f[i - 1][j - 1][0] - prices[i]);
            f[i][j][2] = Math.max(f[i - 1][j][2], f[i - 1][j - 1][0] + prices[i]);
        }
    }

    return f[n - 1][k][0];
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_profit(prices: Vec<i32>, k: i32) -> i64 {
        let n = prices.len();
        let k = k as usize;
        let mut f = vec![vec![vec![0i64; 3]; k + 1]; n];
        for j in 1..=k {
            f[0][j][1] = -(prices[0] as i64);
            f[0][j][2] = prices[0] as i64;
        }
        for i in 1..n {
            for j in 1..=k {
                f[i][j][0] = f[i - 1][j][0]
                    .max(f[i - 1][j][1] + prices[i] as i64)
                    .max(f[i - 1][j][2] - prices[i] as i64);
                f[i][j][1] = f[i - 1][j][1].max(f[i - 1][j - 1][0] - prices[i] as i64);
                f[i][j][2] = f[i - 1][j][2].max(f[i - 1][j - 1][0] + prices[i] as i64);
            }
        }
        f[n - 1][k][0]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
