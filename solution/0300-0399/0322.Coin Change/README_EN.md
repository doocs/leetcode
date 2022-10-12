# [322. Coin Change](https://leetcode.com/problems/coin-change)

[中文文档](/solution/0300-0399/0322.Coin%20Change/README.md)

## Description

<p>You are given an integer array <code>coins</code> representing coins of different denominations and an integer <code>amount</code> representing a total amount of money.</p>

<p>Return <em>the fewest number of coins that you need to make up that amount</em>. If that amount of money cannot be made up by any combination of the coins, return <code>-1</code>.</p>

<p>You may assume that you have an infinite number of each kind of coin.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> coins = [1,2,5], amount = 11
<strong>Output:</strong> 3
<strong>Explanation:</strong> 11 = 5 + 5 + 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> coins = [2], amount = 3
<strong>Output:</strong> -1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> coins = [1], amount = 0
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= coins.length &lt;= 12</code></li>
	<li><code>1 &lt;= coins[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>0 &lt;= amount &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

Dynamic programming.

Similar to the idea of ​​a complete backpack, there is no limit to the number of coins. Find the minimum number of coins required to make up the total amount.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        m, n = len(coins), amount
        dp = [[n + 1] * (n + 1) for _ in range(m + 1)]
        dp[0][0] = 0
        for i in range(1, m + 1):
            for j in range(n + 1):
                dp[i][j] = dp[i - 1][j]
                if j >= coins[i - 1]:
                    dp[i][j] = min(dp[i][j], dp[i][j - coins[i - 1]] + 1)
        return -1 if dp[-1][-1] > n else dp[-1][-1]
```

```python
class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        dp = [amount + 1] * (amount + 1)
        dp[0] = 0
        for coin in coins:
            for j in range(coin, amount + 1):
                dp[j] = min(dp[j], dp[j - coin] + 1)
        return -1 if dp[-1] > amount else dp[-1]
```

### **Java**

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int m = coins.length;
        int[][] dp = new int[m + 1][amount + 1];
        for (int i = 0; i <= m; ++i) {
            Arrays.fill(dp[i], amount + 1);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= m; ++i) {
            int v = coins[i - 1];
            for (int j = 0; j <= amount; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (j >= v) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - v] + 1);
                }
            }
        }
        return dp[m][amount] > amount ? - 1 : dp[m][amount];
    }
}
```

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} coins
 * @param {number} amount
 * @return {number}
 */
var coinChange = function (coins, amount) {
    let dp = Array(amount + 1).fill(amount + 1);
    dp[0] = 0;
    for (const coin of coins) {
        for (let j = coin; j <= amount; ++j) {
            dp[j] = Math.min(dp[j], dp[j - coin] + 1);
        }
    }
    return dp[amount] > amount ? -1 : dp[amount];
};
```

### **C++**

```cpp
class Solution {
public:
    int coinChange(vector<int>& coins, int amount) {
        vector<int> dp(amount + 1, amount + 1);
        dp[0] = 0;
        for (auto& coin : coins)
            for (int j = coin; j <= amount; ++j)
                dp[j] = min(dp[j], dp[j - coin] + 1);
        return dp[amount] > amount ? -1 : dp[amount];
    }
};
```

### **Go**

```go
func coinChange(coins []int, amount int) int {
	dp := make([]int, amount+1)
	for i := 1; i <= amount; i++ {
		dp[i] = amount + 1
	}
	for _, coin := range coins {
		for j := coin; j <= amount; j++ {
			dp[j] = min(dp[j], dp[j-coin]+1)
		}
	}
	if dp[amount] > amount {
		return -1
	}
	return dp[amount]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function coinChange(coins: number[], amount: number): number {
    let dp = new Array(amount + 1).fill(amount + 1);
    dp[0] = 0;
    for (const coin of coins) {
        for (let j = coin; j <= amount; ++j) {
            dp[j] = Math.min(dp[j], dp[j - coin] + 1);
        }
    }
    return dp[amount] > amount ? -1 : dp[amount];
}
```

### **Rust**

```rust
impl Solution {
    pub fn coin_change(coins: Vec<i32>, amount: i32) -> i32 {
        let n = coins.len();
        let amount = amount as usize;
        let mut dp = vec![amount + 1; amount + 1];
        dp[0] = 0;
        for i in 1..=amount {
            for j in 0..n {
                let coin = coins[j] as usize;
                if coin <= i {
                    dp[i] = dp[i].min(dp[i - coin] + 1);
                }
            }
        }
        if dp[amount] > amount {
            -1
        } else {
            dp[amount] as i32
        }
    }
}
```

### **....**

```

```

<!-- tabs:end -->
