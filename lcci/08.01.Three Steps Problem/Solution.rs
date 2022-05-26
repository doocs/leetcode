impl Solution {
    pub fn ways_to_step(n: i32) -> i32 {
        let mut dp = [1, 2, 4];
        let n = n as usize;
        if n <= 3 {
            return dp[n - 1];
        }
        for _ in 3..n {
            dp = [
                dp[1],
                dp[2],
                (((dp[0] + dp[1]) % 1000000007) + dp[2]) % 1000000007,
            ];
        }
        dp[2]
    }
}
