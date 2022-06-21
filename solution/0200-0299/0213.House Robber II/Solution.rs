impl Solution {
    pub fn rob(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        if n == 1 {
            return nums[0];
        }
        let rob_range = |left, right| {
            let mut dp = [0, 0];
            for i in left..right {
                dp = [dp[1], dp[1].max(dp[0] + nums[i])];
            }
            dp[1]
        };
        rob_range(0, n - 1).max(rob_range(1, n))
    }
}
