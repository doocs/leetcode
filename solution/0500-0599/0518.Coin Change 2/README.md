# [518. 零钱兑换 II](https://leetcode-cn.com/problems/coin-change-2)

[English Version](/solution/0500-0599/0518.Coin%20Change%202/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。&nbsp;</p>

<p>&nbsp;</p>

<ul>
</ul>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> amount = 5, coins = [1, 2, 5]
<strong>输出:</strong> 4
<strong>解释:</strong> 有四种方式可以凑成总金额:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> amount = 3, coins = [2]
<strong>输出:</strong> 0
<strong>解释:</strong> 只用面额2的硬币不能凑成总金额3。
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入:</strong> amount = 10, coins = [10]
<strong>输出:</strong> 1
</pre>

<p>&nbsp;</p>

<p><strong>注意</strong><strong>:</strong></p>

<p>你可以假设：</p>

<ul>
	<li>0 &lt;= amount (总金额) &lt;= 5000</li>
	<li>1 &lt;= coin (硬币面额)&nbsp;&lt;= 5000</li>
	<li>硬币种类不超过 500 种</li>
	<li>结果符合 32 位符号整数</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划。

完全背包问题。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def change(self, amount: int, coins: List[int]) -> int:
        dp = [0] * (amount + 1)
        dp[0] = 1
        for coin in coins:
            for j in range(coin, amount + 1):
                dp[j] += dp[j - coin]
        return dp[-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int change(int amount, int[] coins) {
        int m = coins.length;
        int[][] dp = new int[m + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= amount; ++j) {
                for (int k = 0; k * coins[i - 1] <= j; ++k) {
                    dp[i][j] += dp[i - 1][j - coins[i - 1] * k];
                }
            }
        }
        return dp[m][amount];
    }
}
```

下面对 k 这层循环进行优化：

由于：

- `dp[i][j] = dp[i - 1][j] + dp[i - 1][j - v] + dp[i - 1][j - 2v] + ... + dp[i - 1][j - kv]`
- `dp[i][j - v] = dp[i - 1][j - v] + dp[i - 1][j - 2v] + ... + dp[i - 1][j - kv]`

因此 `dp[i][j] = dp[i - 1][j] + dp[i][j - v]`。

```java
class Solution {
    public int change(int amount, int[] coins) {
        int m = coins.length;
        int[][] dp = new int[m + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= m; ++i) {
            int v = coins[i - 1];
            for (int j = 0; j <= amount; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (j >= v) {
                    dp[i][j] += dp[i][j - v];
                }
            }
        }
        return dp[m][amount];
    }
}
```

空间优化：

```java
class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            // 顺序遍历，0-1背包问题是倒序遍历
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }
        return dp[amount];
    }
}
```

### **TypeScript**

```ts
function change(amount: number, coins: number[]): number {
    let dp = new Array(amount + 1).fill(0);
    dp[0] = 1;
    for (let coin of coins) {
        for (let i = coin; i <= amount; ++i) {
            dp[i] += dp[i - coin];
        }
    }
    return dp.pop();
}
```

### **Go**

```go
func change(amount int, coins []int) int {
	dp := make([]int, amount+1)
	dp[0] = 1
	for _, coin := range coins {
		for j := coin; j <= amount; j++ {
			dp[j] += dp[j-coin]
		}
	}
	return dp[amount]
}
```

### **C++**

```cpp
class Solution {
public:
    int change(int amount, vector<int>& coins) {
        vector<int> dp(amount + 1);
        dp[0] = 1;
        for (auto coin : coins) {
            for (int j = coin; j <= amount; ++j) {
                dp[j] += dp[j - coin];
            }
        }
        return dp[amount];
    }
};
```

### **...**

```

```

<!-- tabs:end -->
