# [518. Coin Change II](https://leetcode.com/problems/coin-change-ii)

[中文文档](/solution/0500-0599/0518.Coin%20Change%20II/README.md)

## Description

<p>You are given an integer array <code>coins</code> representing coins of different denominations and an integer <code>amount</code> representing a total amount of money.</p>

<p>Return <em>the number of combinations that make up that amount</em>. If that amount of money cannot be made up by any combination of the coins, return <code>0</code>.</p>

<p>You may assume that you have an infinite number of each kind of coin.</p>

<p>The answer is <strong>guaranteed</strong> to fit into a signed <strong>32-bit</strong> integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> amount = 5, coins = [1,2,5]
<strong>Output:</strong> 4
<strong>Explanation:</strong> there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> amount = 3, coins = [2]
<strong>Output:</strong> 0
<strong>Explanation:</strong> the amount of 3 cannot be made up just with coins of 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> amount = 10, coins = [10]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= coins.length &lt;= 300</code></li>
	<li><code>1 &lt;= coins[i] &lt;= 5000</code></li>
	<li>All the values of <code>coins</code> are <strong>unique</strong>.</li>
	<li><code>0 &lt;= amount &lt;= 5000</code></li>
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
