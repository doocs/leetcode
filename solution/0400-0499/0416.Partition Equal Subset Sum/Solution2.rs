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

        let m = (sum >> 1) as usize;

        // Here dp[i] means if it can be sum up to `i` for all the number we've traversed through so far
        // Which is actually compressing the 2-D dp vector to 1-D
        let mut dp: Vec<bool> = vec![false; m + 1];

        // Initialize the dp vector
        dp[0] = true;

        // Begin the actual dp process
        for e in &nums {
            // For every num in nums vector
            for i in (*e as usize..=m).rev() {
                // Update the current status
                dp[i] |= dp[i - (*e as usize)];
            }
        }

        dp[m]
    }
}
