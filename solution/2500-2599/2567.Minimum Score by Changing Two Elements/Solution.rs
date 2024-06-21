impl Solution {
    pub fn minimize_sum(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let n = nums.len();
        (nums[n - 1] - nums[2])
            .min(nums[n - 2] - nums[1])
            .min(nums[n - 3] - nums[0])
    }
}
