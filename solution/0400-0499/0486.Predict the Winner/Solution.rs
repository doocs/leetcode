impl Solution {
    #[allow(dead_code)]
    pub fn predict_the_winner(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut dp: Vec<Vec<i32>> = vec![vec![0; n]; n];

        // Initialize the dp vector
        for i in 0..n {
            dp[i][i] = nums[i];
        }

        // Begin the dp process
        for i in (0..n - 1).rev() {
            for j in i + 1..n {
                dp[i][j] = std::cmp::max(
                    // Take i-th num
                    nums[i] - dp[i + 1][j],
                    // Take j-th num
                    nums[j] - dp[i][j - 1]
                );
            }
        }

        dp[0][n - 1] >= 0
    }
}
