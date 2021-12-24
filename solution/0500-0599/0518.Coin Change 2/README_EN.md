# [518. Coin Change 2](https://leetcode.com/problems/coin-change-2)

[中文文档](/solution/0500-0599/0518.Coin%20Change%202/README.md)

## Description

<p>You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.</p>

<ul>

</ul>

<p>&nbsp;</p>

<p><b>Example 1:</b></p>

<pre>

<b>Input:</b> amount = 5, coins = [1, 2, 5]

<b>Output:</b> 4

<b>Explanation:</b> there are four ways to make up the amount:

5=5

5=2+2+1

5=2+1+1+1

5=1+1+1+1+1

</pre>

<p><b>Example 2:</b></p>

<pre>

<b>Input:</b> amount = 3, coins = [2]

<b>Output:</b> 0

<b>Explanation:</b> the amount of 3 cannot be made up just with coins of 2.

</pre>

<p><b>Example 3:</b></p>

<pre>

<b>Input:</b> amount = 10, coins = [10]

<b>Output:</b> 1

</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<p>You can assume that</p>

<ul>
	<li>0 &lt;= amount &lt;= 5000</li>
	<li>1 &lt;= coin &lt;= 5000</li>
	<li>the number of coins is less than 500</li>
	<li>the answer is guaranteed to fit into signed 32-bit integer</li>
</ul>

## Solutions

Dynamic programming.

Complete knapsack problem.

<!-- tabs:start -->

### **Python3**

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

```java
class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
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
