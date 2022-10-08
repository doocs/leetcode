impl Solution {
    pub fn max_ascending_sum(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut res = nums[0];
        let mut sum = nums[0];
        for i in 1..n {
            if nums[i - 1] >= nums[i] {
                res = res.max(sum);
                sum = 0;
            }
            sum += nums[i];
        }
        res.max(sum)
    }
}
