use std::collections::HashSet;

impl Solution {
    #[allow(dead_code)]
    pub fn min_extra_char(s: String, dictionary: Vec<String>) -> i32 {
        let n = s.len();
        let mut set = dictionary
            .iter()
            .map(|s| s.into())
            .collect::<HashSet<String>>();
        let mut dp = vec![0; n + 1];

        // Initialize the dp vector
        dp[0] = 0;

        // Begin the actual dp process
        for i in 1..=n {
            dp[i] = dp[i - 1] + 1;
            for j in 0..i {
                if set.contains(&s[j..i]) {
                    dp[i] = std::cmp::min(dp[i], dp[j]);
                }
            }
        }

        dp[n]
    }
}
