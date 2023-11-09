impl Solution {
    #[allow(dead_code)]
    pub fn find_length_of_lcis(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        // Here dp[i] represents the longest lcis that ends with `nums[i]`, should be 1 by default
        let mut dp: Vec<i32> = vec![1; n];
        let mut ret = dp[0];

        // Let's dp
        for i in 1..n {
            dp[i] = if nums[i] > nums[i - 1] { dp[i - 1] + 1 } else { 1 };
            ret = std::cmp::max(ret, dp[i]);
        }

        ret
    }
}
