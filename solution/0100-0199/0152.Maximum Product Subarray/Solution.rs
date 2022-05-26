impl Solution {
    pub fn max_product(nums: Vec<i32>) -> i32 {
        let mut min = nums[0];
        let mut max = nums[0];
        let mut res = nums[0];
        for &num in nums.iter().skip(1) {
            let (pre_min, pre_max) = (min, max);
            min = num.min(num * pre_min).min(num * pre_max);
            max = num.max(num * pre_min).max(num * pre_max);
            res = res.max(max);
        }
        res
    }
}
