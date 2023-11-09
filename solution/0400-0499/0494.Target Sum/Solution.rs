impl Solution {
    #[allow(dead_code)]
    pub fn find_target_sum_ways(nums: Vec<i32>, target: i32) -> i32 {
        let mut sum = 0;
        for e in &nums {
            sum += *e;
        }

        // -x + (sum - x) = target <-> -2 * x + sum = target <-> 2 * x = sum - target
        if sum < target || (sum - target) % 2 != 0 {
            // There is no way to get any expression in this case
            return 0;
        }
        let n = nums.len();
        let m = (sum - target) / 2;

        let mut dp: Vec<Vec<i32>> = vec![vec![0; m as usize + 1]; n + 1];

        // Initialize the dp vector
        dp[0][0] = 1;

        // Begin the actual dp phase
        for i in 1..=n {
            for j in 0..=m as usize {
                // nums[i - 1] is not included
                dp[i][j] = dp[i - 1][j];
                if nums[i - 1] <= (j as i32) {
                    // nums[i - 1] is included
                    dp[i][j] += dp[i - 1][j - (nums[i - 1] as usize)];
                }
            }
        }

        dp[n][m as usize]
    }
}
