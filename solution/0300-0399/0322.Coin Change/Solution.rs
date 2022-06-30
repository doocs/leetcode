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
