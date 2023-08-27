impl Solution {
    #[allow(dead_code)]
    pub fn can_cross(stones: Vec<i32>) -> bool {
        let n = stones.len();
        let mut dp = vec![vec![false; n]; n];

        // Initialize the dp vector
        dp[0][0] = true;

        // Begin the actual dp process
        for i in 1..n {
            for j in (0..=i - 1).rev() {
                let k = (stones[i] - stones[j]) as usize;
                if k - 1 > j {
                    break;
                }
                dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                if i == n - 1 && dp[i][k] {
                    return true;
                }
            }
        }

        false
    }
}