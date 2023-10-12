impl Solution {
    pub fn num_squares(n: i32) -> i32 {
        let (row, col) = ((n as f32).sqrt().floor() as usize, n as usize);
        let mut dp = vec![vec![i32::MAX; col + 1]; row + 1];
        dp[0][0] = 0;
        for i in 1..=row {
            for j in 0..=col {
                dp[i][j] = dp[i - 1][j];
                if j >= i * i {
                    dp[i][j] = std::cmp::min(dp[i][j], dp[i][j - i * i] + 1);
                }
            }
        }
        dp[row][col]
    }
}