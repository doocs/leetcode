---
comments: true
difficulty: 中等
tags:
    - 贪心
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [714. 买卖股票的最佳时机含手续费](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee)

[English Version](/solution/0700-0799/0714.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Transaction%20Fee/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>prices</code>，其中 <code>prices[i]</code>表示第&nbsp;<code>i</code>&nbsp;天的股票价格 ；整数&nbsp;<code>fee</code> 代表了交易股票的手续费用。</p>

<p>你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。</p>

<p>返回获得利润的最大值。</p>

<p><strong>注意：</strong>这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>prices = [1, 3, 2, 8, 4, 9], fee = 2
<strong>输出：</strong>8
<strong>解释：</strong>能够达到的最大利润:  
在此处买入&nbsp;prices[0] = 1
在此处卖出 prices[3] = 8
在此处买入 prices[4] = 4
在此处卖出 prices[5] = 9
总利润:&nbsp;((8 - 1) - 2) + ((9 - 4) - 2) = 8</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>prices = [1,3,7,5,10,3], fee = 3
<strong>输出：</strong>6
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt; 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= fee &lt; 5 * 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

<!-- thinking:start -->

> **思考**
>
> 可多次买卖，每次完整交易扣除手续费 $fee$。天数可达 $5\times 10^4$，按交易次数展开递归而不记忆，状态会重复计算。
>
> 某一天只需区分「持有 / 未持有」：未持有时可买入或跳过，持有时可卖出（扣费）或继续持有。从第 $i$ 天、状态 $j$ 出发的最优利润只依赖这两个后继。
>
> 定义 $dfs(i,j)$ 并记忆化，边界为天数用尽时利润为 $0$，答案为 $dfs(0,0)$。状态数 $O(n)$，每个常数转移。

<!-- thinking:end -->

我们设计一个函数 $dfs(i, j)$，表示从第 $i$ 天开始，状态为 $j$ 时，能够获得的最大利润。其中 $j$ 的取值为 $0, 1$，分别表示当前不持有股票和持有股票。答案即为 $dfs(0, 0)$。

函数 $dfs(i, j)$ 的执行逻辑如下：

如果 $i \geq n$，那么没有股票可以交易了，此时返回 $0$；

否则，我们可以选择不交易，此时 $dfs(i, j) = dfs(i + 1, j)$。我们也可以进行股票交易，如果此时 $j \gt 0$，说明当前持有股票，可以卖出，此时 $dfs(i, j) = prices[i] + dfs(i + 1, 0) - fee$；如果此时 $j = 0$，说明当前不持有股票，可以买入，此时 $dfs(i, j) = -prices[i] + dfs(i + 1, 1)$。取最大值作为函数 $dfs(i, j)$ 的返回值。

答案为 $dfs(0, 0)$。

为了避免重复计算，我们使用记忆化搜索的方法，用一个数组 $f$ 记录 $dfs(i, j)$ 的返回值，如果 $f[i][j]$ 不为 $-1$，说明已经计算过，直接返回 $f[i][j]$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $prices$ 的长度。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

### 方法二：动态规划

<!-- thinking:start -->

> **思考**
>
> 方法一从末尾填了一张后缀表。同样的选择也可以从左往右写：$f[i][0/1]$ 为前 $i$ 天结束时未持有 / 持有的最大利润。未持有由「继续空仓」或「今日卖出并扣费」转移；持有由「继续持有」或「今日买入」转移。
>
> 自左向右填表，答案为最后一天空仓。时间仍为 $O(n)$。

<!-- thinking:end -->

我们定义 $f[i][j]$ 表示到第 $i$ 天，且状态为 $j$ 时，能够获得的最大利润。其中 $j$ 的取值为 $0, 1$，分别表示当前不持有股票和持有股票。初始时 $f[0][0] = 0$, $f[0][1] = -prices[0]$。

当 $i \geq 1$ 时，如果当前不持有股票，那么 $f[i][0]$ 可以由 $f[i - 1][0]$ 和 $f[i - 1][1] + prices[i] - fee$ 转移得到，即 $f[i][0] = \max(f[i - 1][0], f[i - 1][1] + prices[i] - fee)$；如果当前持有股票，那么 $f[i][1]$ 可以由 $f[i - 1][1]$ 和 $f[i - 1][0] - prices[i]$ 转移得到，即 $f[i][1] = \max(f[i - 1][1], f[i - 1][0] - prices[i])$。最终答案为 $f[n - 1][0]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $prices$ 的长度。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

### 方法三：动态规划（空间优化）

<!-- thinking:start -->

> **思考**
>
> 方法二的第 $i$ 天只引用第 $i-1$ 天的两个值，整表没有必要保留。
>
> 用 $f_0,f_1$ 滚动，按从左到右的顺序更新，空间降为 $O(1)$。注意同一天内先算出新的未持有利润再算持有，以免用到已覆盖的旧值；代码里用并行赋值保证读到的是更新前的一对。

<!-- thinking:end -->

$f[i]$ 只与上一天有关，用两个变量滚动即可，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProfit(self, prices: List[int], fee: int) -> int:
        f0, f1 = 0, -prices[0]
        for x in prices[1:]:
            f0, f1 = max(f0, f1 + x - fee), max(f1, f0 - x)
        return f0
```

#### Java

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

#### C++

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

#### Go

```go
func maxProfit(prices []int, fee int) int {
	f0, f1 := 0, -prices[0]
	for _, x := range prices[1:] {
		f0, f1 = max(f0, f1+x-fee), max(f1, f0-x)
	}
	return f0
}
```

#### TypeScript

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

<!-- solution:start -->

### 方法四：动态规划

<!-- thinking:start -->

> **思考**
>
> 可多次买卖，每次完整交易扣除手续费 $fee$。天数可达 $5\times 10^4$，按买卖决策树展开会重复大量子问题。
>
> 状态只是天数和是否持有。跳过当天总会先调用下一天再返回，调用链长度为 $n$，栈会溢出。
>
> 更靠后的天数在从末尾往前走时已经就绪。令 $f[i][j]$ 为从第 $i$ 天起、持有标记为 $j$ 的最大利润，从 $i=n-1$ 填到 $0$。卖出扣去 $fee$，买入则进入持有态。

<!-- thinking:end -->

令 $f[i][j]$ 表示从第 $i$ 天开始、状态为 $j$ 时能够获得的最大利润。$j$ 取 $0$ 或 $1$，分别表示当前不持有股票和持有股票。答案为 $f[0][0]$。越过最后一天的利润为 $0$，因此 $f[n][j] = 0$。

我们从 $i = n - 1$ 填到 $0$。不交易则保留 $f[i + 1][j]$。若 $j > 0$，当前持有股票，可以卖出，利润为 $prices[i] + f[i + 1][0] - fee$。若 $j = 0$，当前不持有股票，可以买入，利润为 $-prices[i] + f[i + 1][1]$。取较大值：

当 $j > 0$ 时，

$$
f[i][j] = \max(f[i + 1][j],\ prices[i] + f[i + 1][0] - fee)
$$

当 $j = 0$ 时，

$$
f[i][j] = \max(f[i + 1][j],\ -prices[i] + f[i + 1][1])
$$

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $prices$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProfit(self, prices: List[int], fee: int) -> int:
        n = len(prices)
        f = [[0] * 2 for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for j in range(2):
                ans = f[i + 1][j]
                if j:
                    ans = max(ans, prices[i] + f[i + 1][0] - fee)
                else:
                    ans = max(ans, -prices[i] + f[i + 1][1])
                f[i][j] = ans
        return f[0][0]
```

#### Java

```java
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] f = new int[n + 1][2];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < 2; ++j) {
                int ans = f[i + 1][j];
                if (j > 0) {
                    ans = Math.max(ans, prices[i] + f[i + 1][0] - fee);
                } else {
                    ans = Math.max(ans, -prices[i] + f[i + 1][1]);
                }
                f[i][j] = ans;
            }
        }
        return f[0][0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices, int fee) {
        int n = prices.size();
        vector<vector<int>> f(n + 1, vector<int>(2));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < 2; ++j) {
                int ans = f[i + 1][j];
                if (j) {
                    ans = max(ans, prices[i] + f[i + 1][0] - fee);
                } else {
                    ans = max(ans, -prices[i] + f[i + 1][1]);
                }
                f[i][j] = ans;
            }
        }
        return f[0][0];
    }
};
```

#### Go

```go
func maxProfit(prices []int, fee int) int {
	n := len(prices)
	f := make([][2]int, n+1)
	for i := n - 1; i >= 0; i-- {
		for j := 0; j < 2; j++ {
			ans := f[i+1][j]
			if j > 0 {
				ans = max(ans, prices[i]+f[i+1][0]-fee)
			} else {
				ans = max(ans, -prices[i]+f[i+1][1])
			}
			f[i][j] = ans
		}
	}
	return f[0][0]
}
```

#### TypeScript

```ts
function maxProfit(prices: number[], fee: number): number {
    const n = prices.length;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(2).fill(0));
    for (let i = n - 1; i >= 0; --i) {
        for (let j = 0; j < 2; ++j) {
            let ans = f[i + 1][j];
            if (j) {
                ans = Math.max(ans, prices[i] + f[i + 1][0] - fee);
            } else {
                ans = Math.max(ans, -prices[i] + f[i + 1][1]);
            }
            f[i][j] = ans;
        }
    }
    return f[0][0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
