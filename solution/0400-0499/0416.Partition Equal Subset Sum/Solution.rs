impl Solution {
    #[allow(dead_code)]
    pub fn can_partition(nums: Vec<i32>) -> bool {
        let mut sum = 0;
        for e in &nums {
            sum += *e;
        }

        if sum % 2 != 0 {
            return false;
        }

        let n = nums.len();
        let m = (sum / 2) as usize;
        let mut dp: Vec<Vec<bool>> = vec![vec![false; m + 1]; n + 1];

        // Initialize the dp vector
        dp[0][0] = true;

        // Begin the actual dp process
        for i in 1..=n {
            for j in 0..=m {
                dp[i][j] = if (nums[i - 1] as usize) > j {
                    dp[i - 1][j]
                } else {
                    dp[i - 1][j] || dp[i - 1][j - (nums[i - 1] as usize)]
                };
            }
        }

        dp[n][m]
    }
}
