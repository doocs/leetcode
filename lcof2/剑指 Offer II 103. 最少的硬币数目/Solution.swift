class Solution {
    func coinChange(_ coins: [Int], _ amount: Int) -> Int {
        var dp = [Int](repeating: amount + 1, count: amount + 1)
        dp[0] = 0  

        for coin in coins {
            if coin > amount { continue }
            for j in coin...amount {
                dp[j] = min(dp[j], dp[j - coin] + 1)
            }
        }

        return dp[amount] > amount ? -1 : dp[amount]
    }
}
