impl Solution {
    pub fn find_longest_chain(mut pairs: Vec<Vec<i32>>) -> i32 {
        pairs.sort_by(|a, b| a[0].cmp(&b[0]));
        let n = pairs.len();
        let mut dp = vec![1; n];
        for i in 0..n {
            for j in 0..i {
                if pairs[i][0] > pairs[j][1] {
                    dp[i] = dp[i].max(dp[j] + 1);
                }
            }
        }
        dp[n - 1]
    }
}
