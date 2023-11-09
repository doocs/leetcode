impl Solution {
    #[allow(dead_code)]
    pub fn is_match(s: String, p: String) -> bool {
        let n = s.len();
        let m = p.len();
        let s = s.chars().collect::<Vec<char>>();
        let p = p.chars().collect::<Vec<char>>();

        let mut dp = vec![vec![false; m + 1]; n + 1];

        // Initialize the dp vector
        dp[0][0] = true;

        for i in 1..=m {
            if p[i - 1] == '*' {
                dp[0][i] = dp[0][i - 2];
            }
        }

        // Begin the actual dp process
        for i in 1..=n {
            for j in 1..=m {
                if s[i - 1] == p[j - 1] || p[j - 1] == '.' {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if p[j - 1] == '*' {
                    if j >= 2 && (s[i - 1] == p[j - 2] || p[j - 2] == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else if j >= 2 && s[i - 1] != p[j - 2] {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }

        dp[n][m]
    }
}
