# [122. 买卖股票的最佳时机 II](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii)

[English Version](/solution/0100-0199/0122.Best%20Time%20to%20Buy%20and%20Sell%20Stock%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>prices</code> ，其中&nbsp;<code>prices[i]</code> 表示某支股票第 <code>i</code> 天的价格。</p>

<p>在每一天，你可以决定是否购买和/或出售股票。你在任何时候&nbsp;<strong>最多</strong>&nbsp;只能持有 <strong>一股</strong> 股票。你也可以先购买，然后在 <strong>同一天</strong> 出售。</p>

<p>返回 <em>你能获得的 <strong>最大</strong> 利润</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>prices = [7,1,5,3,6,4]
<strong>输出：</strong>7
<strong>解释：</strong>在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
&nbsp;    随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
     总利润为 4 + 3 = 7 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>prices = [1,2,3,4,5]
<strong>输出：</strong>4
<strong>解释：</strong>在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
&nbsp;    总利润为 4 。</pre>

<p><strong>示例&nbsp;3：</strong></p>

<pre>
<strong>输入：</strong>prices = [7,6,4,3,1]
<strong>输出：</strong>0
<strong>解释：</strong>在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= prices[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

### 1. 贪心法

所有上涨交易日都做买卖，所有下跌交易日都不做买卖，这样便能实现利润最大化。

### 2. 动态规划法

设 f1 表示当天结束后持有股票的最大利润，f2 表示当前结束后没有持有股票的最大利润。

初始第 1 天结束时，`f1 = -prices[0]`，`f2 = 0`。

从第 2 天开始，当天结束时：

-   若持有股票，则可能是前一天持有股票，然后继续持有；也可能是前一天没有持有股票，然后当天买入股票。最大利润 `f1 = max(f1, f2 - prices[i])`。
-   若没有持有股票，则可能是前一天没持有股票，今天也没持有股票；或者前一天持有股票，然后今天卖出。最大利润 `f2 = max(f2, f1 + prices[i])`。

最后返回 f2 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

贪心：

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        res = 0
        for i in range(1, len(prices)):
            t = prices[i] - prices[i - 1]
            res += max(t, 0)
        return res
```

动态规划：

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

贪心：

```java
class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; ++i) {
            // 策略是所有上涨交易日都做买卖，所以下跌交易日都不做买卖
            int t = prices[i] - prices[i - 1];
            res += Math.max(t, 0);
        }
        return res;
    }
}
```

动态规划：

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

拆解分析：

```ts
function maxProfit(prices: number[]): number {
    const n = prices.length;
    let res = 0;
    let max = prices[0];
    let min = prices[0];
    for (let i = 1; i < n; i++) {
        const price = prices[i];
        // 如果今天的价格比最高点要低，则交易之前的最大差值，再买入今天的
        if (price < max) {
            res += max - min;
            max = price;
            min = price;
        } else {
            // 非小于，更新最高点
            max = price;
        }
    }
    // 防止漏算最后一笔交易
    if (min < max) {
        res += max - min;
    }
    return res;
}
```

### **C++**

贪心：

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

动态规划：

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

贪心：

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

动态规划：

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

贪心：

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

动态规划：

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
