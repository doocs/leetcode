impl Solution {
    pub fn max_sub_array(mut nums: Vec<i32>) -> i32 {
        let mut res = nums[0];
        for i in 1..nums.len() {
            nums[i] = nums[i].max(nums[i - 1] + nums[i]);
            res = res.max(nums[i]);
        }
        res
    }
}
