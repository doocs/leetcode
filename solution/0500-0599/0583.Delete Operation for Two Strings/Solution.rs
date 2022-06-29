impl Solution {
    pub fn min_distance(word1: String, word2: String) -> i32 {
        let (m, n) = (word1.len(), word2.len());
        let (word1, word2) = (word1.as_bytes(), word2.as_bytes());
        let mut dp = vec![vec![0; n + 1]; m + 1];
        for i in 1..=m {
            for j in 1..=n {
                dp[i][j] = if word1[i - 1] == word2[j - 1] {
                    dp[i - 1][j - 1] + 1
                } else {
                    dp[i - 1][j].max(dp[i][j - 1])
                }
            }
        }
        let max = dp[m][n];
        ((m - max) + (n - max)) as i32
    }
}
