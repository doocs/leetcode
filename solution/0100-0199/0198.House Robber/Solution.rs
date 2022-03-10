impl Solution {
    pub fn rob(nums: Vec<i32>) -> i32 {
        let mut dp = [0, 0];
        for num in nums {
            dp = [dp[1], dp[1].max(dp[0] + num)]
        }
        dp[1]
    }
}
