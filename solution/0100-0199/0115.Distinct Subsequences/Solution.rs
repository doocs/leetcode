impl Solution {
    #[allow(dead_code)]
    pub fn num_distinct(s: String, t: String) -> i32 {
        let n = s.len();
        let m = t.len();
        let mut dp: Vec<Vec<u64>> = vec![vec![0; m + 1]; n + 1];

        // Initialize the dp vector
        for i in 0..=n {
            dp[i][0] = 1;
        }

        // Begin the actual dp process
        for i in 1..=n {
            for j in 1..=m {
                dp[i][j] = if s.as_bytes()[i - 1] == t.as_bytes()[j - 1] {
                    dp[i - 1][j] + dp[i - 1][j - 1]
                } else {
                    dp[i - 1][j]
                };
            }
        }

        dp[n][m] as i32
    }
}
