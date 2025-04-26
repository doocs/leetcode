impl Solution {
    pub fn count_subarrays(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        for i in 1..nums.len() - 1 {
            if (nums[i - 1] + nums[i + 1]) * 2 == nums[i] {
                ans += 1;
            }
        }
        ans
    }
}
