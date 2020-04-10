# [322. Coin Change](https://leetcode.com/problems/coin-change)

## Description
<p>You are given coins of different denominations and a total amount of money <i>amount</i>. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return <code>-1</code>.</p>



<p><b>Example 1:</b></p>



<pre>

<strong>Input: </strong>coins = <code>[1, 2, 5]</code>, amount = <code>11</code>

<strong>Output: </strong><code>3</code> 

<strong>Explanation:</strong> 11 = 5 + 5 + 1</pre>



<p><b>Example 2:</b></p>



<pre>

<strong>Input: </strong>coins = <code>[2]</code>, amount = <code>3</code>

<strong>Output: </strong>-1

</pre>



<p><b>Note</b>:<br />

You may assume that you have an infinite number of each kind of coin.</p>




## Solutions


### Python3

```python

```

### Java

```java

```

### JavaScript
Dynamic programming.

```javascript
/**
 * @param {number[]} coins
 * @param {number} amount
 * @return {number}
 */
var coinChange = function (coins, amount) {
    var dp = Array(amount + 1).fill(amount + 1);
    dp[0] = 0;
    for (var i = 1; i <= amount; i++) {
        for (var j = 0; j < coins.length; j++) {
            if (coins[j] <= i) {
                dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
    }

    return dp[amount] > amount ? -1 : dp[amount];
};
