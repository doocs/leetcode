impl Solution {
    #[allow(dead_code)]
    pub fn find_target_sum_ways(nums: Vec<i32>, target: i32) -> i32 {
        let mut sum = 0;
        for e in &nums {
            sum += *e;
        }

        if sum < target || (sum - target) % 2 != 0 {
            // Just directly return
            return 0;
        }

        let n = ((sum - target) / 2) as usize;
        let mut dp: Vec<i32> = vec![0; n + 1];

        // Initialize the dp vector
        dp[0] = 1;

        // Begin the actual dp phase
        for e in &nums {
            for i in (*e as usize..=n).rev() {
                dp[i] += dp[i - (*e as usize)];
            }
        }

        dp[n]
    }
}
