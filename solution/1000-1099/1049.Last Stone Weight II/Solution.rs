impl Solution {
    #[allow(dead_code)]
    pub fn last_stone_weight_ii(stones: Vec<i32>) -> i32 {
        let n = stones.len();
        let mut sum = 0;

        for e in &stones {
            sum += *e;
        }

        let m = (sum / 2) as usize;
        let mut dp: Vec<Vec<i32>> = vec![vec![0; m + 1]; n + 1];

        // Begin the actual dp process
        for i in 1..=n {
            for j in 1..=m {
                dp[i][j] = if stones[i - 1] > (j as i32) {
                    dp[i - 1][j]
                } else {
                    std::cmp::max(
                        dp[i - 1][j],
                        dp[i - 1][j - (stones[i - 1] as usize)] + stones[i - 1]
                    )
                };
            }
        }

        sum - 2 * dp[n][m]
    }
}
