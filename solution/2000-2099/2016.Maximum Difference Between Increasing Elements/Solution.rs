impl Solution {
    pub fn maximum_difference(nums: Vec<i32>) -> i32 {
        let mut min = nums[0];
        let mut res = -1;
        for i in 1..nums.len() {
            res = res.max(nums[i] - min);
            min = min.min(nums[i]);
        }
        match res {
            0 => -1,
            _ => res,
        }
    }
}
