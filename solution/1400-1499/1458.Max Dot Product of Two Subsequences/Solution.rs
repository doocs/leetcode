impl Solution {
    #[allow(dead_code)]
    pub fn max_dot_product(nums1: Vec<i32>, nums2: Vec<i32>) -> i32 {
        let n = nums1.len();
        let m = nums2.len();
        let mut dp = vec![vec![i32::MIN; m + 1]; n + 1];

        // Begin the actual dp process
        for i in 1..=n {
            for j in 1..=m {
                dp[i][j] = std::cmp::max(
                    std::cmp::max(dp[i - 1][j], dp[i][j - 1]),
                    std::cmp::max(dp[i - 1][j - 1], 0) + nums1[i - 1] * nums2[j - 1]
                );
            }
        }

        dp[n][m]
    }
}
