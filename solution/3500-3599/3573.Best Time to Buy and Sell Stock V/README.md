---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3573.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20V/README.md
rating: 1777
source: 第 158 场双周赛 Q2
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3573. 买卖股票的最佳时机 V](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-v)

[English Version](/solution/3500-3599/3573.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20V/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>prices</code>，其中 <code>prices[i]</code> 是第 <code>i</code> 天股票的价格（美元），以及一个整数 <code>k</code>。</p>

<p>你最多可以进行 <code>k</code> 笔交易，每笔交易可以是以下任一类型：</p>

<ul>
	<li>
	<p><strong>普通交易</strong>：在第 <code>i</code> 天买入，然后在之后的第 <code>j</code> 天卖出，其中 <code>i &lt; j</code>。你的利润是 <code>prices[j] - prices[i]</code>。</p>
	</li>
	<li>
	<p><strong>做空交易</strong>：在第 <code>i</code> 天卖出，然后在之后的第 <code>j</code> 天买回，其中 <code>i &lt; j</code>。你的利润是 <code>prices[i] - prices[j]</code>。</p>
	</li>
</ul>

<p><strong>注意</strong>：你必须在开始下一笔交易之前完成当前交易。此外，你不能在已经进行买入或卖出操作的同一天再次进行买入或卖出操作。</p>

<p>通过进行&nbsp;<strong>最多</strong> <code>k</code> 笔交易，返回你可以获得的最大总利润。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">prices = [1,7,9,8,2], k = 2</span></p>

<p><strong>输出:</strong> <span class="example-io">14</span></p>

<p><strong>解释:</strong></p>
我们可以通过 2 笔交易获得 14 美元的利润：

<ul>
	<li>一笔普通交易：第 0 天以 1 美元买入，第 2 天以 9 美元卖出。</li>
	<li>一笔做空交易：第 3 天以 8 美元卖出，第 4 天以 2 美元买回。</li>
</ul>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">prices = [12,16,19,19,8,1,19,13,9], k = 3</span></p>

<p><strong>输出:</strong> <span class="example-io">36</span></p>

<p><strong>解释:</strong></p>
我们可以通过 3 笔交易获得 36 美元的利润：

<ul>
	<li>一笔普通交易：第 0 天以 12 美元买入，第 2 天以 19 美元卖出。</li>
	<li>一笔做空交易：第 3 天以 19 美元卖出，第 4 天以 8 美元买回。</li>
	<li>一笔普通交易：第 5 天以 1 美元买入，第 6 天以 19 美元卖出。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>2 &lt;= prices.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= prices.length / 2</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j][k]$ 表示在前 $i$ 天内，最多进行 $j$ 笔交易，且当前状态为 $k$ 时的最大利润。这里的状态 $k$ 有三种可能：

- 若 $k = 0$，表示当前没有持有股票。
- 若 $k = 1$，表示当前持有一支股票。
- 若 $k = 2$，表示当前持有一支股票的空头。

初始时，对任意 $j \in [1, k]$，都有 $f[0][j][1] = -prices[0]$ 和 $f[0][j][2] = prices[0]$。这表示在第 0 天买入一支股票或卖出一支股票的空头。

接下来，我们可以通过状态转移来更新 $f[i][j][k]$ 的值。对于每一天 $i$ 和每笔交易 $j$，我们可以根据当前状态 $k$ 来决定如何更新：

- 若 $k = 0$，表示当前没有持有股票，这个状态可以由以下三种情况转移而来：
    - 前一天没有持有股票。
    - 前一天持有一支股票，并在今天卖出。
    - 前一天持有一支股票的空头，并在今天买回。
- 若 $k = 1$，表示当前持有一支股票，这个状态可以由以下两种情况转移而来：
    - 前一天持有一支股票。
    - 前一天没有持有股票，并在今天买入。
- 若 $k = 2$，表示当前持有一支股票的空头，这个状态可以由以下两种情况转移而来：
    - 前一天持有一支股票的空头。
    - 前一天没有持有股票，并在今天卖出。

即，对于 $1 \leq i < n$ 和 $1 \leq j \leq k$，我们有以下状态转移方程：

$$
\begin{aligned}
f[i][j][0] &= \max(f[i - 1][j][0], f[i - 1][j][1] + prices[i], f[i - 1][j][2] - prices[i]) \\
f[i][j][1] &= \max(f[i - 1][j][1], f[i - 1][j - 1][0] - prices[i]) \\
f[i][j][2] &= \max(f[i - 1][j][2], f[i - 1][j - 1][0] + prices[i])
\end{aligned}
$$

最终，我们需要返回 $f[n - 1][k][0]$，即在前 $n$ 天内，最多进行 $k$ 笔交易，且当前没有持有股票时的最大利润。

时间复杂度 $O(n \times k)$，空间复杂度 $O(n \times k)$。其中 $n$ 为数组 $\textit{prices}$ 的长度，而 $k$ 为最大交易次数。

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

#### C#

```cs
public class Solution {
    public long MaximumProfit(int[] prices, int k) {
        int n = prices.Length;
        long[,,] f = new long[n, k + 1, 3];

        for (int j = 1; j <= k; ++j) {
            f[0, j, 1] = -prices[0];
            f[0, j, 2] = prices[0];
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[i, j, 0] = Math.Max(
                    f[i - 1, j, 0],
                    Math.Max(
                        f[i - 1, j, 1] + prices[i],
                        f[i - 1, j, 2] - prices[i]
                    )
                );
                f[i, j, 1] = Math.Max(
                    f[i - 1, j, 1],
                    f[i - 1, j - 1, 0] - prices[i]
                );
                f[i, j, 2] = Math.Max(
                    f[i - 1, j, 2],
                    f[i - 1, j - 1, 0] + prices[i]
                );
            }
        }

        return f[n - 1, k, 0];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
