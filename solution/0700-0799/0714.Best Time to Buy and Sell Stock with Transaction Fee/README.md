# [714. 买卖股票的最佳时机含手续费](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee)

[English Version](/solution/0700-0799/0714.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20with%20Transaction%20Fee/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

我们设计一个函数 $dfs(i, j)$，表示从第 $i$ 天开始，状态为 $j$ 时，能够获得的最大利润。其中 $j$ 的取值为 $0, 1$，分别表示当前不持有股票和持有股票。答案即为 $dfs(0, 0)$。

函数 $dfs(i, j)$ 的执行逻辑如下：

如果 $i \geq n$，那么没有股票可以交易了，此时返回 $0$；

否则，我们可以选择不交易，此时 $dfs(i, j) = dfs(i + 1, j)$。我们也可以进行股票交易，如果此时 $j \gt 0$，说明当前持有股票，可以卖出，此时 $dfs(i, j) = prices[i] + dfs(i + 1, 0) - fee$；如果此时 $j = 0$，说明当前不持有股票，可以买入，此时 $dfs(i, j) = -prices[i] + dfs(i + 1, 1)$。取最大值作为函数 $dfs(i, j)$ 的返回值。

答案为 $dfs(0, 0)$。

为了避免重复计算，我们使用记忆化搜索的方法，用一个数组 $f$ 记录 $dfs(i, j)$ 的返回值，如果 $f[i][j]$ 不为 $-1$，说明已经计算过，直接返回 $f[i][j]$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $prices$ 的长度。

**方法二：动态规划**

我们定义 $f[i][j]$ 表示到第 $i$ 天，且状态为 $j$ 时，能够获得的最大利润。其中 $j$ 的取值为 $0, 1$，分别表示当前不持有股票和持有股票。初始时 $f[0][0] = 0$, $f[0][1] = -prices[0]$。

当 $i \geq 1$ 时，如果当前不持有股票，那么 $f[i][0]$ 可以由 $f[i - 1][0]$ 和 $f[i - 1][1] + prices[i] - fee$ 转移得到，即 $f[i][0] = \max(f[i - 1][0], f[i - 1][1] + prices[i] - fee)$；如果当前持有股票，那么 $f[i][1]$ 可以由 $f[i - 1][1]$ 和 $f[i - 1][0] - prices[i]$ 转移得到，即 $f[i][1] = \max(f[i - 1][1], f[i - 1][0] - prices[i])$。最终答案为 $f[n - 1][0]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $prices$ 的长度。

我们注意到，状态 $f[i][]$ 的转移只与 $f[i - 1][]$ 和 $f[i - 1][]$ 有关，因此我们可以用两个变量 $f_0, f_1$ 代替数组 $f$，将空间复杂度优化到 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

```python
class Solution:
    def maxProfit(self, prices: List[int], fee: int) -> int:
        f0, f1 = 0, -prices[0]
        for x in prices[1:]:
            f0, f1 = max(f0, f1 + x - fee), max(f1, f0 - x)
        return f0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
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

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func maxProfit(prices []int, fee int) int {
	f0, f1 := 0, -prices[0]
	for _, x := range prices[1:] {
		f0, f1 = max(f0, f1+x-fee), max(f1, f0-x)
	}
	return f0
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

### **...**

```

```

<!-- tabs:end -->
